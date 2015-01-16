/*
 * QideaPaymentServiceProxy.java, 2012-11-15 上午09:20:09 xyluo
 * 
 * Copyright (c) 2011 Shanghai Qi(dea) Information Technology Co.,Ltd 
 * All rights reserved.
 * 
 * This software is copyrighted and owned by Qi(dea) or the copyright holder
 * specified, unless otherwise noted, and may not be reproduced or distributed
 * in whole or in part in any form or medium without express written permission.
 */
package com.qidea.payment;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.qidea.payment.alipay.ResponseResult;
import com.qidea.payment.alipay.Secure;
import com.qidea.payment.alipay.Payment;
import com.qidea.payment.controller.PaymentController;
import com.qidea.payment.util.ParameterUtil;
import com.qidea.payment.util.StringUtil;
import com.qidea.payment.util.XMapUtil;
import com.qidea.security.RSASignature;

/**
 * 
 * 
 * @author xyluo
 * @version 1.0, 2012-11-15 
 * @since 1.0
 */
@Service(value = "serviceProxy")
public class QideaPaymentServiceProxy {

	private static final Logger log = LogManager.getLogger(QideaPaymentServiceProxy.class);
	
	@Autowired
	@Qualifier(value = "propertyConfigurer")
	private PropertyPlaceholder propertyConfigurer;
	
	private String domain;
	
	private String notifyUrl;
	
	private String callbackUrl;
	
	/**
	 * Sign arithmetic
	 * <p>
	 * Arithmetic,	Arithmetic,	Comment
	 * 0001,	    RSA,		--
	 */
	public static final String SEC_ID="0001";
	
	public QideaPaymentServiceProxy() {
		
	}
	
	@PostConstruct
	public void init() {
		domain = propertyConfigurer.getContextProperty("domain");
		notifyUrl = domain+"/rest/pay/notify";
		callbackUrl = domain+"/rest/callback";
	}
	
	public String getCallbackServiceDefinitions() {
		return callbackUrl;
	}
	
	public String getBackendNotificationServiceDefinitions() {
		return notifyUrl;
	}
	
	/**
	 * 
	 * 
	 * @param request
	 * @return
	 */
	public Map<String, String> initParameters(HttpServletRequest request) {
		Map<String, String> commonParams = new HashMap<String, String>();
		commonParams.put("service", "alipay.wap.trade.create.direct");
		commonParams.put("sec_id", SEC_ID);
		commonParams.put("partner", "2088801781083842");
		commonParams.put("call_back_url", callbackUrl);
		commonParams.put("format", "xml");
		commonParams.put("v", "2.0");
		return commonParams;
	}
	
	/**
	 * 
	 * 
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public Map<String, String> initData(Map<String, String> params, HttpServletRequest request) throws UnsupportedEncodingException {
		String subject = request.getParameter(PaymentController.REQUEST_PARAM_DOMAIN_SUBJECT);
		String amount = request.getParameter(PaymentController.REQUEST_PARAM_DOMAIN_AMOUNT);
		String tsn = request.getParameter(PaymentController.PARAM_DOMAIN_TRANSACTION_SN); //next(ta);
		String sellerAccountName = "jacky@qidea.cc";

		String body = 
		"<direct_trade_create_req>" + 
			"<subject>" + subject + "</subject>" + 
			"<out_trade_no>" + tsn + "</out_trade_no>" + 
			"<total_fee>" + amount + "</total_fee>" + 
			"<seller_account_name>" + sellerAccountName + "</seller_account_name>" + 
			"<notify_url>" + notifyUrl + "</notify_url>" + 
			"<call_back_url>" + callbackUrl + "</call_back_url>" + 
			"<merchant_url>" + domain+ "</merchant_url>" + 
		"</direct_trade_create_req>";
		params.put("req_data", body);
		return params;
	}
	
	/**
	 * 
	 * 
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public Map<String, String> init(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("req_id", System.currentTimeMillis() + "");
		params.putAll(initParameters(request));
		initData(params, request);
		return params;
	}
	
	public String service(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		Map<String, String> params = init(request);
		String sign = sign(params, SEC_ID, Secure.RSA_PRIVATE);
		params.put("sign", sign);

		ResponseResult resResult = new ResponseResult();
		String businessResult = "";
		try {
			resResult = send(params, Payment.ALIPAY_PAYMENT_SERVICE, SEC_ID);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (resResult.isSuccess()) {
			businessResult = resResult.getBusinessResult();
		} else {
			return "f";
		}
		DirectTradeCreateResponse directTradeCreateRes = null;
		XMapUtil.register(DirectTradeCreateResponse.class);
		try {
			directTradeCreateRes = (DirectTradeCreateResponse) XMapUtil
			.load(new ByteArrayInputStream(businessResult
					.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			
		} catch (Exception e) {
			
		}
		String requestToken = directTradeCreateRes.getRequestToken();
		System.out.println("Token:" + requestToken);
		Map<String, String> authParams = prepareAuthParamsMap(request, requestToken);
		String authSign = sign(authParams,SEC_ID, Secure.RSA_PRIVATE);
		authParams.put("sign", authSign);
		String redirectURL = "";
		try {
			redirectURL = getRedirectUrl(authParams, Payment.ALIPAY_PAYMENT_SERVICE);
			System.out.println("authAndExecute URL:" + redirectURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (StringUtil.isNotBlank(redirectURL)) {
			response.sendRedirect(redirectURL);
			return "f";
		}
		return "f";
	}

	public static String next(String ta) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String str = sdf.format(new Date());
		String yy = str.substring(2, 4); //年2位
		String mm = str.substring(4, 6); //月2位
		String dd = str.substring(6, 8); //天2位

		Random random = new Random();
		long p = Math.abs(random.nextLong());
		String rn = String.valueOf(p).substring(1, 6);  //随机数5位

		return ta + "-" + yy+mm+dd+rn;
	}

	/**
	 * 
	 * 
	 * @param request
	 * @param requestToken
	 * @return
	 */
	private Map<String, String> prepareAuthParamsMap(
			HttpServletRequest request, String requestToken) {
		Map<String, String> requestParams = new HashMap<String, String>();
		String reqData = 
		"<auth_and_execute_req>" + 
			"<request_token>" + requestToken + "</request_token>" + 
		"</auth_and_execute_req>";
		requestParams.put("req_data", reqData);
		requestParams.putAll(initParameters(request));

		requestParams.put("call_back_url", getCallbackServiceDefinitions());
		requestParams.put("service", "alipay.wap.auth.authAndExecute");
		return requestParams;
	}
	
	public static String prepareData(Map<String, String> params) {
        StringBuffer content = new StringBuffer();

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            if ("sign".equals(key) || "sign_type".equals(key)) {
                continue;
            }
            String value = (String) params.get(key);
            if (value != null) {
                content.append((i == 0 ? "" : "&") + key + "=" + value);
            } else {
                content.append((i == 0 ? "" : "&") + key + "=");
            }

        }

        return content.toString();
    }

	/**
	 * @param reqParams
	 * @param signAlgo
	 * @param key
	 * @return
	 */
	private String sign(Map<String, String> reqParams,String signAlgo,String key) {

		String signData = prepareData(reqParams);
		System.out.println("Unsigned Data:"+signData);
		String sign = "";
		try {
			sign = RSASignature.sign(signData, key,"");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return sign;
	}

	/**
	 * 
	 * @param reqParams
	 * @return
	 * @throws Exception
	 */
	private String getRedirectUrl(Map<String, String> reqParams,String reqUrl)
	throws Exception {
		String redirectUrl = reqUrl + "?";
		redirectUrl = redirectUrl + ParameterUtil.mapToUrl(reqParams);
		return redirectUrl;
	}

	/**
	 * @param reqParams
	 * @param reqUrl
	 * @param secId
	 * @return
	 * @throws Exception
	 */
	private ResponseResult send(Map<String, String> reqParams,String reqUrl,String secId) throws Exception {
		String response = "";
		String invokeUrl = reqUrl  + "?";
		URL serverUrl = new URL(invokeUrl);
		HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();

		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.connect();
		String params = ParameterUtil.mapToUrl(reqParams);
		System.out.println("Request Token:"+URLDecoder.decode(params, "utf-8"));
		conn.getOutputStream().write(params.getBytes());

		InputStream is = conn.getInputStream();

		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = in.readLine()) != null) {
			buffer.append(line);
		}
		System.out.println("Response:"+buffer.toString());
		String moreURL = new String(buffer.toString().getBytes("ISO8859-1"), "UTF-8");
		System.out.println("Response:"+moreURL);
		response = URLDecoder.decode(buffer.toString(), "GBK");
		System.out.println("Response:"+response);
		conn.disconnect();
		return praseResult(response,secId);
	}

	/**
	 * @param response
	 * @param secId
	 * @return
	 * @throws Exception
	 */
	private ResponseResult praseResult(String response,String secId) throws Exception {
		// ���óɹ�
		HashMap<String, String> resMap = new HashMap<String, String>();
		String v = ParameterUtil.getParameter(response, "v");
		String service = ParameterUtil.getParameter(response, "service");
		String partner = ParameterUtil.getParameter(response, "partner");
		String sign = ParameterUtil.getParameter(response, "sign");
		String reqId = ParameterUtil.getParameter(response, "req_id");
		resMap.put("v", v);
		resMap.put("service", service);
		resMap.put("partner", partner);
		resMap.put("sec_id", secId);
		resMap.put("req_id", reqId);
		String businessResult = "";
		ResponseResult result = new ResponseResult();
		System.out.println("Token Result:"+response);
		if (response.contains("<err>")) {
			result.setSuccess(false);
			businessResult = ParameterUtil.getParameter(response, "res_error");

			// ת��������Ϣ
			XMapUtil.register(XmlResponse.class);
			XmlResponse errorCode = (XmlResponse) XMapUtil
			.load(new ByteArrayInputStream(businessResult
					.getBytes("UTF-8")));
			result.setErrorMessage(errorCode);

			resMap.put("res_error", ParameterUtil.getParameter(response,
			"res_error"));
		} else {
			businessResult = ParameterUtil.getParameter(response, "res_data");
			result.setSuccess(true);
			//�Է��ص�res_data��������̻�˽Կ����
			String resData= RSASignature.decrypt(businessResult, Secure.RSA_PRIVATE);
			result.setBusinessResult(resData);
			resMap.put("res_data", resData);
		}
		//��ȡ��ǩ�����
		String verifyData = prepareData(resMap);
		System.out.println("verifyData:"+verifyData);
		//�Դ�ǩ�����ʹ��֧������Կ��ǩ��
		boolean verified = RSASignature.doCheck(verifyData, sign, Secure.RSA_ALIPAY_PUBLIC,"");


		if (!verified) {
			throw new Exception("验签失败！");
		}
		return result;
	}

	public String mapToUrl(Map<String, String> params) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (String key : params.keySet()) {
			String value = (String)params.get(key);
			if (isFirst) {
				sb.append(key + "=" + URLEncoder.encode(value, "utf-8"));
				isFirst = false;
			} else {
				if (value != null) {
					sb.append("&" + key + "=" + URLEncoder.encode(value, "utf-8"));
				} else {
					sb.append("&" + key + "=");
				}
			}
		}
		return sb.toString();
	}

	/**
	 * @param map
	 * @return
	 */
	private String getVerifyData(Map map) {
		String service = (String) ((Object[]) map.get("service"))[0];
		String v = (String) ((Object[]) map.get("v"))[0];
		String sec_id = (String) ((Object[]) map.get("sec_id"))[0];
		String notify_data = (String) ((Object[]) map.get("notify_data"))[0];
		try {
			//�Է��ص�notify_data������̻�˽Կ����
			notify_data=RSASignature.decrypt(notify_data, Secure.RSA_PRIVATE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("֪ͨ����Ϊ��"+"service=" + service + "&v=" + v + "&sec_id=" + sec_id + "&notify_data="+ notify_data);
		return "service=" + service + "&v=" + v + "&sec_id=" + sec_id + "&notify_data="
		+ notify_data;
	}
}
