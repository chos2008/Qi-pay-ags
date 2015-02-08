/*
 * TransactionServiceTest.java, 2012-11-13 下午09:42:24 xyluo
 * 
 * Copyright (c) 2011 Shanghai Qi(dea) Information Technology Co.,Ltd 
 * All rights reserved.
 * 
 * This software is copyrighted and owned by Qi(dea) or the copyright holder
 * specified, unless otherwise noted, and may not be reproduced or distributed
 * in whole or in part in any form or medium without express written permission.
 */
package com.chos.payment.transaction;

import java.rmi.server.UID;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit38.AbstractJUnit38SpringContextTests;

/**
 * 
 * 
 * @author xyluo
 * @version 1.0, 2012-11-13 
 * @since 1.0
 */
@ContextConfiguration(locations = {"/applicationContext-ibatis.xml", 
		"/applicationContext-service.xml"})
public class TransactionServiceTest extends AbstractJUnit38SpringContextTests {

	private static final Logger logger = LogManager.getLogger(TransactionServiceTest.class);

	@Resource
	private TransactionService transactionService;

	@Test
	public void testCreateCallbackEndpoint() {
		CallbackEndpoint ce = new CallbackEndpoint();
		ce.setTransactionSN(new UID().toString());
		ce.setCallbackEndpoint("http://qidt.zdsanguo.com/api/v1/pay/notify");
		transactionService.createCallbackEndpoint(ce);
	}

}
