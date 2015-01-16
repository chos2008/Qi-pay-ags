/*
 * TransactionServiceImpl.java, 2011-10-11 ����05:54:36 xyluo
 * 
 * Copyright (c) 2011 Shanghai Qi(dea) Information Technology Co.,Ltd 
 * All rights reserved.
 * 
 * This software is copyrighted and owned by Qi(dea) or the copyright holder
 * specified, unless otherwise noted, and may not be reproduced or distributed
 * in whole or in part in any form or medium without express written permission.
 */
package com.qidea.payment.transaction;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qidea.payment.log.Logger;

/**
 * 
 * 
 * @author xyluo
 * @version 1.0, 2011-10-11 
 * @since 1.0
 */
@Service(value = "transactionService")
public class TransactionServiceImpl implements TransactionService {

	@Autowired()
	private SqlSessionTemplate template;
	
	private Logger logger;
	
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.qidea.payment.transaction.TransactionService#create(com.qidea.payment.transaction.Transaction)
	 */
	public void create(Transaction transaction) {
		template.insert("transaction.insert", transaction);
	}
	
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.qidea.payment.transaction.TransactionService#update(com.qidea.payment.transaction.Transaction)
	 */
	public void update(Transaction transaction) {
		template.insert("transaction.update", transaction);
	}
	
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.qidea.payment.transaction.TransactionService#toState(java.lang.String, char)
	 */
	public void toState(String tsn, char status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("transactionSN", tsn);
		map.put("status", status);
		
		template.insert("transaction.toState", map);
	}
	
	public void toUpdateTransaction(String tsn, Map<String, Object> param) {
		param.put("transactionSN", tsn);
		template.insert("transaction.toUpdateTransaction", param);
	}
	
	public Transaction get(String transactionSN) {
		return template.<Transaction>selectOne("transaction.select", transactionSN);
	}
	
	public CallbackEndpoint getCallbackEndpoint(String transactionSN) {
		return template.<CallbackEndpoint>selectOne("transaction.callback_endpoint_select", transactionSN);
	}
	
	public void createCallbackEndpoint(CallbackEndpoint ce) {
		template.insert("transaction.callback_endpoint", ce);
	}
}
