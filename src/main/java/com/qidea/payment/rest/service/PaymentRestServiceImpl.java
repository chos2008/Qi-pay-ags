/*
 * PaymentRestServiceImpl.java, 2011-10-11 下午05:44:19 xyluo
 * 
 * Copyright (c) 2011 Shanghai Qi(dea) Information Technology Co.,Ltd 
 * All rights reserved.
 * 
 * This software is copyrighted and owned by Qi(dea) or the copyright holder
 * specified, unless otherwise noted, and may not be reproduced or distributed
 * in whole or in part in any form or medium without express written permission.
 */
package com.qidea.payment.rest.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qidea.payment.CallbackJSONResponse;
import com.qidea.payment.QideaPaymentServiceProxy;
import com.qidea.payment.Ta;
import com.qidea.payment.TaA;
import com.qidea.payment.log.Level;
import com.qidea.payment.notification.Message;
import com.qidea.payment.notification.NotificationService;
import com.qidea.payment.transaction.CallbackEndpoint;
import com.qidea.payment.transaction.DataFormatException;
import com.qidea.payment.transaction.Transaction;
import com.qidea.payment.transaction.log.TransactionLog;
import com.qidea.payment.transaction.log.TransactionLogger;

/**
 * 
 * 
 * @author luo, xiaoyong
 * @version 1.0, 2011-10-11 
 * @since 1.0
 */
@Controller(value = "PaymentRestService")
public class PaymentRestServiceImpl extends AbstractPaymentRestService implements PaymentRestService {

	private static final Logger logger = Logger.getLogger(PaymentRestServiceImpl.class);
	
	/**
	 * 
	 */
	private static final String NOTIFY_DATA = "notify_data";
	
	/**
	 * 
	 */
	private static final String SIGNATURE = "sign";
	
	/**
	 * 
	 */
	@Autowired()
	private NotificationService notificationService;
	
	/**
	 * 
	 */
	@Autowired()
	private TransactionLogger transactionLogger;
	
	/**
	 * Acts as proxy object to process the requests from third access applications, clients and any others 
	 * <p>
	 * 
	 */
	@Autowired
	private QideaPaymentServiceProxy proxy;
	
	/**
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/services")
	@ResponseBody()
	public List<Ta> pay(HttpServletRequest request) {
		return thirdPaymentService.list();
	}
	
	@RequestMapping(value = "/s/{ta}")
	@ResponseBody()
	public CallbackJSONResponse authenticate(@PathVariable String ta, String password, HttpServletRequest request, HttpServletResponse response) {
		return authenticate(ta, password);
	}
	
	@RequestMapping(value = "/pay")
	public void p(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	@RequestMapping(value = "/pay/0")
	@ResponseBody()
	public CallbackJSONResponse p0(HttpServletRequest request, HttpServletResponse response) {
		String ta = request.getParameter("ta");
		TaA t = thirdPaymentService.get(ta);
		
		if (t == null) {
			CallbackJSONResponse r = new CallbackJSONResponse(TAA_NOT_EXISTS, TAA_NOT_EXISTS_MESSAGE);
			return r;
		}
		
		Transaction transaction = handleTransaction(request);
		
		Auth r = new Auth(STATUS_OK, STATUS_OK_MESSAGE);
		r.setAuthID(ta);
		return r;
	}
	
	@RequestMapping(value = "/notify")
	public void notify(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String body = request.getParameter(NOTIFY_DATA);
		logger.debug("Data: " + body);
		if (StringUtils.isBlank(body)) {
			logger.error("Parameter '" + NOTIFY_DATA + "' error! Pls request technicians for support.");
			return;
		}
		String signature = request.getParameter(SIGNATURE);
		logger.debug("Signature: " + signature);
		if (StringUtils.isBlank(signature)) {
			logger.error("Parameter '" + SIGNATURE + "' error! Pls request technicians for support.");
			return;
		}
		
		String comment = "来自支付网关的交易通知：通知内容=" + body + ", 签名信息=" + signature;
		logger.debug(comment);
		//logger.<TransactionLog>error(null);
		TransactionLog log = new TransactionLog();
		log.setLevel(Level.INFO.value());
		log.setTimestamp(new Date());
		log.setComment(comment);
		transactionLogger.info(log);
		
		RequestBody b;
		try {
			b = new RequestBody(body);
		} catch (DataFormatException e) {
			logger.error("Parse parameter '" + NOTIFY_DATA + "' error! Pls request technicians for support.", e);
			return;
		}
		logger.debug("Parse parameter '" + NOTIFY_DATA + "' as object: \n" + b);
		
		String tsn = b.getOutTradeNo();
		String tradeStatus = b.getTradeStatus();
		
		char istatus = Transaction.TRANSACTION_DEFAULT_STATUS;
		if ("TRADE_FINISHED".equals(tradeStatus)) {
			istatus = Transaction.TRADE_FINISHED;
		} else if ("WAIT_BUYER_PAY".equals(tradeStatus)) {
			istatus = Transaction.WAIT_BUYER_PAY;
		} else if ("TRADE_SUCCESS".equals(tradeStatus)) {
			istatus = Transaction.TRADE_SUCCESS;
		}
		// transactionService.toState(tsn, istatus);
		// to change ...
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("status", istatus);
		param.put("taTransactionSN", b.getTradeNo());
		transactionService.toUpdateTransaction(tsn, param);
		
		Message message = createCallbackMessage(b);
		notificationService.toNotify(message);
		PrintWriter out = response.getWriter();
		out.write("success");
	}
	
	protected void doInNotification(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	private Message createCallbackMessage(RequestBody body) {
		String tsn = body.getOutTradeNo();
		
		Transaction transaction = transactionService.get(tsn);
		logger.debug("Transaction: " + transaction);
		if (transaction == null) {
			return null;
		}
		
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
	
	public void test2() {
		
	}
	
	public void test() {
		
	}
}