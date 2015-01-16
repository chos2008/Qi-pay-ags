/*
 * TransactionLoggerImpl.java, 2012-10-16 ÉÏÎç09:33:23 xyluo
 * 
 * Copyright (c) 2011 Shanghai Qi(dea) Information Technology Co.,Ltd 
 * All rights reserved.
 * 
 * This software is copyrighted and owned by Qi(dea) or the copyright holder
 * specified, unless otherwise noted, and may not be reproduced or distributed
 * in whole or in part in any form or medium without express written permission.
 */
package com.qidea.payment.transaction.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qidea.payment.log.Level;
import com.qidea.payment.log.Logger;

/**
 * 
 * 
 * @author xyluo
 * @version 1.0, 2012-10-16 
 * @since 1.0
 */
@Service
public class TransactionLoggerImpl implements TransactionLogger {

	@Autowired()
	private Logger logger;
	
	public TransactionLoggerImpl() {
		//logger = new LoggerProxy();
	}
	
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.qidea.payment.log.AbstractLogger#error(java.lang.Object)
	 */
	@Override
	public void error(TransactionLog error) {
		logger.error(error);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.qidea.payment.log.AbstractLogger#info(java.lang.Object)
	 */
	@Override
	public void info(TransactionLog info) {
		logger.info(info);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.qidea.payment.log.AbstractLogger#log(com.qidea.payment.log.Level, java.lang.Object)
	 */
	@Override
	public void log(Level level, TransactionLog log) {
		logger.log(level, log);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.qidea.payment.log.AbstractLogger#warn(java.lang.Object)
	 */
	@Override
	public void warn(TransactionLog warn) {
		logger.warn(warn);
	}

}
