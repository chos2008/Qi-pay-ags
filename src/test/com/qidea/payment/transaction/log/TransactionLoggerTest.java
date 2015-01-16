/*
 * TransactionLoggerTest.java, 2012-10-16 上午11:12:54 xyluo
 * 
 * Copyright (c) 2011 Shanghai Qi(dea) Information Technology Co.,Ltd 
 * All rights reserved.
 * 
 * This software is copyrighted and owned by Qi(dea) or the copyright holder
 * specified, unless otherwise noted, and may not be reproduced or distributed
 * in whole or in part in any form or medium without express written permission.
 */
package com.qidea.payment.transaction.log;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit38.AbstractJUnit38SpringContextTests;

import com.qidea.payment.log.Level;

/**
 * 
 * 
 * @author xyluo
 * @version 1.0, 2012-10-16 
 * @since 1.0
 */
@ContextConfiguration(locations = {"/applicationContext-service.xml", 
		"/applicationContext-ibatis.xml"})
public class TransactionLoggerTest extends AbstractJUnit38SpringContextTests {

	@Resource
	private TransactionLogger transactionLogger;
	
	public void setTransactionLogger(TransactionLogger transactionLogger) {
		this.transactionLogger = transactionLogger;
	}
	
	@Test
	public void testError() {
		String comment = "通知接入方(接入号=" + "" + ", 回调接口=" + "" + ")失败：确认失败,回调接口响应失败。原因=获取回调接口响应内容失败.";
		
		TransactionLog message = new TransactionLog();
    	message.setLevel(Level.ERROR.value());
    	message.setTimestamp(new Date());
		message.setComment(comment);
    	transactionLogger.error(message);
	}
}