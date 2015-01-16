/*
 * AbstractPaymentRestService.java, 2012-11-16 上午11:01:21 xyluo
 * 
 * Copyright (c) 2011 Shanghai Qi(dea) Information Technology Co.,Ltd 
 * All rights reserved.
 * 
 * This software is copyrighted and owned by Qi(dea) or the copyright holder
 * specified, unless otherwise noted, and may not be reproduced or distributed
 * in whole or in part in any form or medium without express written permission.
 */
package com.qidea.payment.rest.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.qidea.payment.CallbackJSONResponse;
import com.qidea.payment.TaA;
import com.qidea.payment.ThirdPaymentService;
import com.qidea.payment.controller.PaymentController;
import com.qidea.payment.transaction.CallbackEndpoint;
import com.qidea.payment.transaction.Transaction;
import com.qidea.payment.transaction.TransactionService;

/**
 * 
 * 
 * @author xyluo
 * @version 1.0, 2012-11-16 
 * @since 1.0
 */
public abstract class AbstractPaymentRestService implements PaymentRestService {

	private static final Logger log = LogManager.getLogger(AbstractPaymentRestService.class);
	
	@Autowired()
	protected ThirdPaymentService thirdPaymentService;
	
	@Autowired()
	protected TransactionService transactionService;
	
	protected CallbackJSONResponse authenticate(String ta, String password, Model model) {
		TaA t = thirdPaymentService.get(ta);
		
		if (t == null) {
			CallbackJSONResponse r = new CallbackJSONResponse(TAA_NOT_EXISTS, TAA_NOT_EXISTS_MESSAGE);
			return r;
		}
		
		if (model != null) {
			model.addAttribute("taa", t);
		}
		Auth r = new Auth(STATUS_OK, STATUS_OK_MESSAGE);
		r.setAuthID(ta);
		return r;
	}
	
	public CallbackJSONResponse authenticate(String ta, String password) {
		return authenticate(ta, password, null);
	}
	
	protected Transaction handleTransaction(HttpServletRequest request) {
		String tsn = request.getParameter(PaymentController.PARAM_DOMAIN_TRANSACTION_SN);
		// Create one transaction with default status. The default status definitions to refer 
		// to {@link Transaction} status definitions TRANSACTION_DEFAULT_STATUS
		// 
		// With the specific transaction No., with default status
		Transaction transaction = new Transaction(tsn);
		transaction.setOrderSN(request.getParameter(PaymentController.REQUEST_PARAM_DOMAIN_ORDER_SN));
		transaction.setTimestamp(new Date());
		transaction.setTa(request.getParameter(PaymentController.REQUEST_PARAM_DOMAIN_TA));
		transaction.setTr(request.getParameter(PaymentController.REQUEST_PARAM_DOMAIN_TR));
		transaction.setAmount(Float.parseFloat(request.getParameter(PaymentController.REQUEST_PARAM_DOMAIN_AMOUNT)));
		transactionService.create(transaction);
		log.info("[A] Transaction No " + tsn + ", Info: " + ToStringBuilder.reflectionToString(transaction, ToStringStyle.DEFAULT_STYLE));
		
		CallbackEndpoint ce = new CallbackEndpoint();
		ce.setTransactionSN(transaction.getTransactionSN());
		ce.setCallbackEndpoint(request.getParameter(PaymentController.REQUEST_PARAM_DOMAIN_NOTIFY_URL));
		ce.setForwardDestination(request.getParameter(PaymentController.REQUEST_PARAM_DOMAIN_CALLBACK));
		transactionService.createCallbackEndpoint(ce);
		log.info("[A] Ta Callback Info: " + ToStringBuilder.reflectionToString(ce, ToStringStyle.DEFAULT_STYLE));
		return transaction;
	}
	
	protected String encode(String string) {
		try {
			return URLDecoder.decode(string, "UTF-8");
//			return new String(string.getBytes("ISO-8859-1"), "GBK");
		} catch (UnsupportedEncodingException e) {
			log.warn("Exception occurs while encoding string, Unsupported encoding!");
			return string;
		}
	}
}
