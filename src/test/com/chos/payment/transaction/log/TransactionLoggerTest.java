/*
 * TransactionLoggerTest.java, 2012-10-16 ����11:12:54 xyluo
 * 
 * Copyright (c) 2011 Shanghai Qi(dea) Information Technology Co.,Ltd 
 * All rights reserved.
 * 
 * This software is copyrighted and owned by Qi(dea) or the copyright holder
 * specified, unless otherwise noted, and may not be reproduced or distributed
 * in whole or in part in any form or medium without express written permission.
 */
package com.chos.payment.transaction.log;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit38.AbstractJUnit38SpringContextTests;

import com.chos.payment.log.Level;

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
		String comment = "֪ͨ���뷽(�����=" + "" + ", �ص�ӿ�=" + "" + ")ʧ�ܣ�ȷ��ʧ��,�ص�ӿ���Ӧʧ�ܡ�ԭ��=��ȡ�ص�ӿ���Ӧ����ʧ��.";
		
		TransactionLog message = new TransactionLog();
    	message.setLevel(Level.ERROR.value());
    	message.setTimestamp(new Date());
		message.setComment(comment);
    	transactionLogger.error(message);
	}
}