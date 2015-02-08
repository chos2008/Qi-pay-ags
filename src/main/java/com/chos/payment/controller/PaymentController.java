/*
 * PaymentController.java, 2011-10-11 ����05:44:19 xyluo
 * 
 * Copyright (c) 2011 Shanghai Qi(dea) Information Technology Co.,Ltd 
 * All rights reserved.
 * 
 * This software is copyrighted and owned by Qi(dea) or the copyright holder
 * specified, unless otherwise noted, and may not be reproduced or distributed
 * in whole or in part in any form or medium without express written permission.
 */
package com.chos.payment.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.nuxeo.common.xmap.XMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chos.payment.CallbackJSONResponse;
import com.chos.payment.PropertyPlaceholder;
import com.chos.payment.QideaPaymentServiceProxy;
import com.chos.payment.Ta;
import com.chos.payment.TaA;
import com.chos.payment.ThirdPaymentService;
import com.chos.payment.alipay.Secure;
import com.chos.payment.alipay.web.mobile.Body;
import com.chos.payment.log.Level;
import com.chos.payment.notification.Message;
import com.chos.payment.notification.NotificationService;
import com.chos.payment.rest.service.AbstractPaymentRestService;
import com.chos.payment.rest.service.PaymentRestService;
import com.chos.payment.transaction.CallbackEndpoint;
import com.chos.payment.transaction.Transaction;
import com.chos.payment.transaction.TransactionService;
import com.chos.payment.transaction.log.TransactionLog;
import com.chos.payment.transaction.log.TransactionLogger;
import com.chos.security.RSASignature;

/**
 * 
 * @author xiaoyong
 * @version 1.0, 2011-10-11 
 * @since 1.0
 */
@Controller(value = "PaymentController")
//@RequestMapping(value = "/rest")
public class PaymentController extends AbstractPaymentRestService {

	private static final Logger logger = Logger.getLogger(PaymentController.class);
	
	@Autowired()
	private ThirdPaymentService thirdPaymentService;

	@Autowired
	@Qualifier(value = "propertyConfigurer")
	private PropertyPlaceholder propertyConfigurer;
	
	@Autowired
	private PaymentRestService paymentRestService;
	
	@Autowired()
	private TransactionService transactionService;
	
	@Autowired()
	private NotificationService notificationService;

	String basePath = "";

	@Autowired
	private QideaPaymentServiceProxy proxy;
	
	@Autowired()
	private TransactionLogger transactionLogger;
	
	/*
	 * 
	 * 
	 * 
	 */
	public static final String REQUEST_PARAM_DOMAIN_TA = "ta";
	
	public static final String REQUEST_PARAM_DOMAIN_TR = "tr";
	
	public static final String REQUEST_PARAM_DOMAIN_ORDER_SN = "OrderSN";
	
	public static final String REQUEST_PARAM_DOMAIN_SUBJECT = "subject";
	
	public static final String REQUEST_PARAM_DOMAIN_BODY = "body";
	
	public static final String REQUEST_PARAM_DOMAIN_AMOUNT = "amount";
	
	public static final String REQUEST_PARAM_DOMAIN_NOTIFY_URL = "notify_url";
	
	public static final String REQUEST_PARAM_DOMAIN_CALLBACK = "forward";

	
	/*
	 * 
	 * 
	 */
	public static final String PARAM_DOMAIN_TRANSACTION_SN = "tsn";
	
	/*
	 * 
	 * 
	 */
	public static final String PAYMENT_CENTER = "/pay/center";
	
	private Map<String, Object> authenticate(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> data = new HashMap<String, Object>();
		
		String param = request.getParameter(REQUEST_PARAM_DOMAIN_TA);
		if (StringUtils.isBlank(param)) {
			CallbackJSONResponse status = new CallbackJSONResponse(CallbackJSONResponse.FAILURE, "接入号不能为空, 请确认您已签约为合法接入方, 如未签约，请先签约再接入！");
			model.addAttribute("error", status);
			return null;
		}
		
		CallbackJSONResponse status = authenticate(param, null, model);
		if (! PaymentRestService.STATUS_OK.equals(status.getStatus())) {
			logger.warn("Illegal third access request, Ta=" + param);
			model.addAttribute("error", status);
			return null;
		}
		logger.info("from third access request, Ta=" + param);
		data.put(REQUEST_PARAM_DOMAIN_TA, param);
		
		param = request.getParameter(REQUEST_PARAM_DOMAIN_TR);
		if (StringUtils.isBlank(param)) {
			status = new CallbackJSONResponse(CallbackJSONResponse.FAILURE, "买家帐户不能为空, 请确认参数是否正确！");
			model.addAttribute("error", status);
			return null;
		}
		logger.info("买家帐户==========================" + param);
		param = encode(param);
		logger.info("买家帐户==========================" + param);
		data.put(REQUEST_PARAM_DOMAIN_TR, param);
		
		param = request.getParameter(REQUEST_PARAM_DOMAIN_ORDER_SN);
		if (StringUtils.isBlank(param)) {
			status = new CallbackJSONResponse(CallbackJSONResponse.FAILURE, "订单号不能为空, 请确认参数是否正确！");
			model.addAttribute("error", status);
			return null;
		}
		data.put(REQUEST_PARAM_DOMAIN_ORDER_SN, param);
		
		param = request.getParameter(REQUEST_PARAM_DOMAIN_SUBJECT);
		if (StringUtils.isBlank(param)) {
			status = new CallbackJSONResponse(CallbackJSONResponse.FAILURE, "商品名称不能为空, 请确认参数是否正确！");
			model.addAttribute("error", status);
			return null;
		}
		logger.info("商品名称==========================" + param);
		param = encode(param);
		logger.info("商品名称==========================" + param);
		data.put(REQUEST_PARAM_DOMAIN_SUBJECT, param);
		
		param = request.getParameter(REQUEST_PARAM_DOMAIN_BODY);
		if (StringUtils.isBlank(param)) {
			status = new CallbackJSONResponse(CallbackJSONResponse.FAILURE, "商品描述不能为空, 请确认参数是否正确！");
			model.addAttribute("error", status);
			return null;
		}
		param = encode(param);
		data.put(REQUEST_PARAM_DOMAIN_BODY, param);
		
		param = request.getParameter(REQUEST_PARAM_DOMAIN_AMOUNT);
		if (StringUtils.isBlank(param)) {
			status = new CallbackJSONResponse(CallbackJSONResponse.FAILURE, "交易金额不能为空, 请确认参数是否正确！");
			model.addAttribute("error", status);
			return null;
		}
		data.put(REQUEST_PARAM_DOMAIN_AMOUNT, param);
		
		param = request.getParameter(REQUEST_PARAM_DOMAIN_NOTIFY_URL);
		if (StringUtils.isBlank(param)) {
			status = new CallbackJSONResponse(CallbackJSONResponse.FAILURE, "后台通知URL不能为空, 请确认参数是否正确！");
			model.addAttribute("error", status);
			return null;
		}
		data.put(REQUEST_PARAM_DOMAIN_NOTIFY_URL, param);
		
		param = request.getParameter(REQUEST_PARAM_DOMAIN_CALLBACK);
		/*
		if (StringUtils.isBlank(param)) {
			status = new CallbackJSONResponse(CallbackJSONResponse.FAILURE, "回调跳转页面地址不能为空, 请确认参数是否正确！");
			model.addAttribute("error", status);
			return null;
		}
		*/
		data.put(REQUEST_PARAM_DOMAIN_CALLBACK, param);
		return data;
	}
	
	/**
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = PAYMENT_CENTER)
	public String pay(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("from " + request.getRemoteAddr() + " QiPay request...");
		String pc = "pc";
		
		Map<String, Object> data = authenticate(request, response, model);
		if (data == null) {
			return pc;
		}
		
		CallbackJSONResponse status = new CallbackJSONResponse(CallbackJSONResponse.SUCCESS, "Ok！");
		model.addAttribute("error", status);
		
		String tsn = QideaPaymentServiceProxy.next(request.getParameter(REQUEST_PARAM_DOMAIN_TA));
		data.put(PARAM_DOMAIN_TRANSACTION_SN, tsn);
		
		List<Ta> tas = thirdPaymentService.list();
		model.addAttribute("tas", tas);
		model.addAttribute("data", data);
		return pc;
	}

	/**
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/p")
	public String p(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		//		Service service = new Service("https://wappaygw.alipay.com/service/rest.htm");
		//		
		//		Map<String, String> list = new HashMap<String, String>();
		//		list.put("service", "alipay.wap.trade.create.direct");
		//		service.setRequestParameterDomainList(list);
		//		
		//		model.addAttribute("service", service);
		//		return "f";
		
		////////////////////////////////////////////////////////////
		//Map<String, Object> data = authenticate(request, response, model);
		
		handleTransaction(request);
		return proxy.service(request, response, model);
	}

	@RequestMapping(value = "/callback")
	public String callback(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		CallbackJSONResponse status;
		
		String sign = request.getParameter("sign");
		String result = request.getParameter("result");
		String requestToken = request.getParameter("request_token");
		String outTradeNo = request.getParameter("out_trade_no");
		String tradeNo = request.getParameter("trade_no");
		
		Map<String,String> resMap  = new HashMap<String,String>();
		resMap.put("result", result);
		resMap.put("request_token", requestToken);
		resMap.put("out_trade_no", outTradeNo);
		resMap.put("trade_no", tradeNo);
		String verifyData = QideaPaymentServiceProxy.prepareData(resMap);
		boolean verified = false;

		try {
			verified = RSASignature.doCheck(verifyData, sign, Secure.RSA_ALIPAY_PUBLIC,"");

			if (! verified || ! result.equals("success")) {
				
			} else {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Transaction transaction = transactionService.get(outTradeNo);
		if (transaction == null) {
			logger.warn("Transaction with No. " + outTradeNo + " not found, Pls checkout the transaction No. " + outTradeNo + "!");
			return null;
		}
		logger.debug("Transaction, Info: " + ToStringBuilder.reflectionToString(transaction, ToStringStyle.DEFAULT_STYLE));
		
		String ta = transaction.getTa();
		String forwordDestination = null;
		CallbackEndpoint ce = transactionService.getCallbackEndpoint(outTradeNo);
		if (ce == null || StringUtils.isBlank(ce.getCallbackEndpoint())) {
			TaA tr = thirdPaymentService.get(ta);
			logger.debug("TaA: " + tr);
			
			forwordDestination = tr.getForwardUrl();
			logger.info("With default url config for forward for Ta " + ta);
		} else {
			forwordDestination = ce.getForwardDestination();
			logger.info("With specific url config for forward for Ta " + ta);
		}
		if (StringUtils.isEmpty(forwordDestination)) {
			logger.info("No url config for forward for Ta " + ta);
		}
		
		if (StringUtils.isBlank(outTradeNo)) {
			outTradeNo = "1A2B-12101594708";
		}
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put(PaymentController.REQUEST_PARAM_DOMAIN_TR, transaction.getTr());
		if (forwordDestination != null) {
			data.put(PaymentController.REQUEST_PARAM_DOMAIN_CALLBACK, forwordDestination);
		}
		model.addAttribute("data", data);
		
		status = new CallbackJSONResponse(CallbackJSONResponse.SUCCESS, "Ok！");
		model.addAttribute("error", status);
		return "callback";
	}

	private Body handleRequest(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> data = new HashMap<String, String>();
		
		String param = request.getParameter("service");
		if (StringUtils.isBlank(param)) {
			logger.warn("缺少参数, 来自支付宝返回过来的通知数据参数为空, 参数=service");
			return null;
		}
		data.put("service", param);
		
		param = request.getParameter("sec_id");
		data.put("sec_id", param);
		if (StringUtils.isBlank(param)) {
			logger.warn("缺少参数, 来自支付宝返回过来的通知数据参数为空, 参数=sec_id");
			return null;
		}
		
		data.put("v", request.getParameter("v"));
		
		String content = request.getParameter("notify_data");
		if (StringUtils.isBlank(content)) {
			logger.warn("缺少参数, 来自支付宝返回过来的通知数据参数为空, 参数=notify_data");
			return null;
		}
		try {
			content= RSASignature.decrypt(content, Secure.RSA_PRIVATE);
			data.put("notify_data", content);
		} catch (Exception e) {
			logger.warn("对来自支付宝返回过来的通知数据解密失败!");
		}
		
		String signature = request.getParameter("sign");
		logger.info("Signature: " + signature);
		// 生成待签名数据
		String plain = QideaPaymentServiceProxy.prepareData(data);
		logger.info("Plain Data before signature: " + plain);
		
		String comment = "来自支付网关的交易通知：通知内容=" + plain + ", 签名信息=" + signature;
		logger.debug(comment);
		//logger.<TransactionLog>error(null);
		TransactionLog log = new TransactionLog();
		log.setLevel(Level.INFO.value());
		log.setTimestamp(new Date());
		log.setComment(comment);
		transactionLogger.info(log);
		
		boolean b = RSASignature.doCheck(plain, signature, Secure.RSA_ALIPAY_PUBLIC, "");
		if (! b) {
			logger.warn("验签失败，对来自支付宝返回过来的通知数据验签失败!");
		}
		
		XMap xm = new XMap();
		xm.register(Body.class);
		Body body = null;
		try {
			body = (Body) xm.load(new ByteArrayInputStream(content.getBytes("UTF-8")));
		} catch (Exception e) {
			logger.warn("数据主体解析失败，对来自支付宝返回过来的通知数据主体" + content + "解析失败!", e);
		}
		return body;
	}
	
	/**
	 * 
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value = "/pay/notify")
	public void notify(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Body body = handleRequest(request, response);
		
		String transactionSN = body.getTransactionSN();
		String status = body.getStatus();
		char istatus = Transaction.TRANSACTION_DEFAULT_STATUS;
		if ("TRADE_FINISHED".equals(status)) {
			istatus = Transaction.TRADE_FINISHED;
		} else if ("WAIT_BUYER_PAY".equals(status)) {
			istatus = Transaction.WAIT_BUYER_PAY;
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("status", istatus);
		param.put("taTransactionSN", body.getTaTransactionSN());
		transactionService.toUpdateTransaction(transactionSN, param);
		
		Message message = createCallbackMessage(body);
		notificationService.toNotify(message);
		PrintWriter out = response.getWriter();
		out.write("success");
	}

	private Message createCallbackMessage(Body body) {
		String tsn = body.getTransactionSN();
		
		Transaction transaction = transactionService.get(tsn);
		if (transaction == null) {
			logger.warn("Transaction with No. " + tsn + " not found, Pls checkout the transaction No. " + tsn + "!");
			return null;
		}
		logger.debug("Transaction, Info: " + ToStringBuilder.reflectionToString(transaction, ToStringStyle.DEFAULT_STYLE));
		
		String ta = transaction.getTa();
		String callbackEndpoint = null;
		CallbackEndpoint ce = transactionService.getCallbackEndpoint(tsn);
		if (ce == null || StringUtils.isBlank(ce.getCallbackEndpoint())) {
			TaA tr = thirdPaymentService.get(ta);
			logger.debug("TaA: " + tr);
			
			callbackEndpoint = tr.getNotifyUrl();
		} else {
			callbackEndpoint = ce.getCallbackEndpoint();
		}
		if (StringUtils.isEmpty(callbackEndpoint)) {
			logger.error("No url config for notification for Ta " + ta);
			return null;
		}
		
		HashMap<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("ta", ta);
		parameterMap.put("tsn", transaction.getTransactionSN());
		parameterMap.put("OrderSN", transaction.getOrderSN());
		parameterMap.put("amount", transaction.getAmount() + "");
		parameterMap.put("timestamp", transaction.getTimestamp().getTime() + "");
		parameterMap.put("TransactionStatus", transaction.getStatus() + "");
		Message message = new Message(callbackEndpoint, parameterMap);
		return message;
	}
}