/*
 * NotificationServiceImpl.java, 2012-10-11 下午11:11:29 xyluo
 * 
 * Copyright (c) 2011 Shanghai Qi(dea) Information Technology Co.,Ltd 
 * All rights reserved.
 * 
 * This software is copyrighted and owned by Qi(dea) or the copyright holder
 * specified, unless otherwise noted, and may not be reproduced or distributed
 * in whole or in part in any form or medium without express written permission.
 */
package com.chos.payment.notification;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chos.payment.CallbackJSONResponse;
import com.chos.payment.log.Level;
import com.chos.payment.log.Logger;
import com.chos.payment.rest.service.Callback;
import com.chos.payment.transaction.log.TransactionLog;
import com.chos.payment.transaction.log.TransactionLogger;

/**
 * 
 * 
 * @author xyluo
 * @version 1.0, 2012-10-11 
 * @since 1.0
 */
@Service()
public class NotificationServiceImpl implements NotificationService {

	private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(NotificationServiceImpl.class);
	
	/**
	 * 
	 */
	private Logger logger;
	
	@Autowired()
	private TransactionLogger transactionLogger;
	
	/**
	 * 
	 */
	private Executor executor;
	
	public NotificationServiceImpl() {
		executor = Executors.newCachedThreadPool();
	}
	
	public void toNotify(Message message) {
		doInNotification(message);
	}
	
	public void doInNotification(final Message message) {
		Runnable t = new Runnable() {
			
			@Override
			public void run() {
				
				DefaultHttpClient httpclient = new DefaultHttpClient();
				
				HttpPost httpRequest = new HttpPost(message.getUrl());
				
				Map<String, String> parameterMap = message.getParameterMap();
				Set<Entry<String, String>> entrySet = parameterMap.entrySet();

				HttpParams params = new BasicHttpParams();
				params.setParameter("charset", HTTP.UTF_8);
//				for (Entry<String, String> entry : entrySet) {
//					params.setParameter(entry.getKey(), entry.getValue());
//				}
				httpRequest.setParams(params);
//				httpRequest.addHeader("Content-Type","application/json");  
				httpRequest.addHeader("charset", HTTP.UTF_8);
				
				List <NameValuePair> nvps = new ArrayList<NameValuePair>();
	            //nvps.add(new BasicNameValuePair("IDToken1", "username"));
	            //nvps.add(new BasicNameValuePair("IDToken2", "password"));
				for (Entry<String, String> entry : entrySet) {
					nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}

	            httpRequest.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

	            HttpResponse response = null;
				try {
					response = httpclient.execute(httpRequest);
				} catch (IOException e) {
					e.printStackTrace();
				}
				postCallback(message, response);
			}
		};
		executor.execute(t);
	}
	
	private void postCallback(Message request, HttpResponse response) {
		Map<String, String> parameterMap = request.getParameterMap();
		
		HttpEntity entity = response.getEntity();
        
        StatusLine statusLine = response.getStatusLine();
        int status = statusLine.getStatusCode();
        if (status != 200) {
        	String comment = "通知接入方(接入号=" + parameterMap.get("ta") + ", 回调接口=" + request.getUrl() + ")失败：HTTP状态响应码=" + status + ", 原因=" + statusLine.getReasonPhrase();
        	log.error(comment);
        	//logger.<TransactionLog>error(null);
        	TransactionLog message = new TransactionLog();
        	message.setLevel(Level.ERROR.value());
        	message.setTimestamp(new Date());
			message.setComment(comment);
        	transactionLogger.error(message);
        	return;
        }
        InputStream stream = null;
		try {
			stream = entity.getContent();
		} catch (IOException e1) {
			String comment = "通知接入方(接入号=" + parameterMap.get("ta") + ", 回调接口=" + request.getUrl() + ")失败：确认失败,回调接口响应失败。原因=获取回调接口响应内容失败.";
			log.error(comment);
        	//logger.<TransactionLog>error(null);
        	TransactionLog message = new TransactionLog();
        	message.setLevel(Level.ERROR.value());
        	message.setTimestamp(new Date());
			message.setComment(comment);
        	transactionLogger.error(message);
        	return;
		}
		
		StringBuffer sb = new StringBuffer();
        BufferedInputStream bis = new BufferedInputStream(stream);
        try {
        	byte[] b = new byte[1024];
        	int i = 0;
			while ((i = bis.read(b)) != -1) {
				sb.append(new String(b, 0, i));
			}
		} catch (IOException e) {
			String comment = "通知接入方(接入号=" + parameterMap.get("ta") + ", 回调接口=" + request.getUrl() + ")失败：确认失败,回调接口响应失败。原因=读取回调接口响应内容失败.";
			log.error(comment);
        	//logger.<TransactionLog>error(null);
        	TransactionLog message = new TransactionLog();
        	message.setLevel(Level.ERROR.value());
        	message.setTimestamp(new Date());
			message.setComment(comment);
        	transactionLogger.error(message);
        	return;
		}
		postProcess(request, sb.toString());
	}
	
	private void postProcess(Message request, String responseText) {
//		try {
//			responseText = new String(responseText.getBytes("ISO-8859-1"), "GBK");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		Map<String, String> parameterMap = request.getParameterMap();
		log.debug("Response content: " + responseText);
		
//		String[] sa = responseText.split("&");
		
		String status = null;
		String message = null;
		String body = null;
//		for (String s : sa) {
//			String[] ss = s.split("=");
//			String st = ss[0].trim();
//			if ("status".equals(st)) {
//				status = st;
//			} else if ("message".equals(st)) {
//				message = st;
//			} else if ("body".equals(st)) {
//				body = st;
//			}
//		}
		
		CallbackJSONResponse c = CallbackJSONResponse.toObject(responseText);
		status = c.getStatus();
		message = c.getMessage();
		if (! Callback.STATUS_OK.equals(status)) {
			TransactionLog tlog = new TransactionLog();
			tlog.setLevel(Level.WARN.value());
			tlog.setTimestamp(new Date());
			tlog.setComment("通知接入方(接入号=" + parameterMap.get("ta") + ", 回调接口=" + request.getUrl() + ")失败：确认失败,回调接口响应失败。原因=响应状态: " + Callback.STATUS_OK + ", 描述： 响应状态不正确！.");
			transactionLogger.warn(tlog);
			return;
		}
		TransactionLog tlog = new TransactionLog();
		tlog.setLevel(Level.INFO.value());
		tlog.setTimestamp(new Date());
		tlog.setComment("通知接入方(接入号=" + parameterMap.get("ta") + ", 回调接口=" + request.getUrl() + ")成功。响应状态: " + Callback.STATUS_OK);
		transactionLogger.info(tlog);
	}
}
