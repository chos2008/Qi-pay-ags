/*
 * LoggerProxy.java, 2012-10-11 ÏÂÎç11:59:39 xyluo
 * 
 * Copyright (c) 2011 Shanghai Qi(dea) Information Technology Co.,Ltd 
 * All rights reserved.
 * 
 * This software is copyrighted and owned by Qi(dea) or the copyright holder
 * specified, unless otherwise noted, and may not be reproduced or distributed
 * in whole or in part in any form or medium without express written permission.
 */
package com.qidea.payment.log;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * 
 * @author xyluo
 * @version 1.0, 2012-10-11 
 * @since 1.0
 */
@Service
public class LoggerProxy implements Logger {

	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LoggerProxy.class);
	
	@Autowired()
	private LoggerProxyFactoryBean proxyFactory;
	
	@Autowired()
	private SqlSessionTemplate template;
	
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.qidea.payment.log.Logger#error(java.lang.Object)
	 */
	@Override
	public <T> void error(T error) {
		log(Level.ERROR, error);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.qidea.payment.log.Logger#info(java.lang.Object)
	 */
	@Override
	public <T> void info(T info) {
		log(Level.INFO, info);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.qidea.payment.log.Logger#log(com.qidea.payment.log.Level, java.lang.Object)
	 */
	@Override
	public <T> void log(Level level, T log) {
		Class<?> clazz = log.getClass();
		String st = proxyFactory.prepareStatement(clazz);
		if (st == null) {
			logger.warn("No logger adapte for " + clazz.getName());
			return;
		}
		logger.debug("Found logger adapte for " + clazz.getName() + ", logger statement: " + st);
		template.insert(st, log);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.qidea.payment.log.Logger#warn(java.lang.Object)
	 */
	@Override
	public <T> void warn(T warn) {
		log(Level.WARN, warn);
	}
}