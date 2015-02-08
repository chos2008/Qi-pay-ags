/*
 * LoggerProxyFactoryBean.java, 2012-10-12 ����12:12:13 xyluo
 * 
 * Copyright (c) 2011 Shanghai Qi(dea) Information Technology Co.,Ltd 
 * All rights reserved.
 * 
 * This software is copyrighted and owned by Qi(dea) or the copyright holder
 * specified, unless otherwise noted, and may not be reproduced or distributed
 * in whole or in part in any form or medium without express written permission.
 */
package com.chos.payment.log;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chos.payment.transaction.log.TransactionLog;

/**
 * 
 * 
 * @author xyluo
 * @version 1.0, 2012-10-12 
 * @since 1.0
 */
@Service()
public class LoggerProxyFactoryBean {

	@Autowired()
	private SqlSessionTemplate template;

	public String prepareStatement(Class<?> clazz) {
		if (clazz.equals(TransactionLog.class)) {
			return "transaction.log";
		}
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(TransactionLog.class.getClass());
	}
}
