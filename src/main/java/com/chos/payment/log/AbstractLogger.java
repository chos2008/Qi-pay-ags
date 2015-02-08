/*
 * AbstractLogger.java, 2012-10-16 ����09:34:53 xyluo
 * 
 * Copyright (c) 2011 Shanghai Qi(dea) Information Technology Co.,Ltd 
 * All rights reserved.
 * 
 * This software is copyrighted and owned by Qi(dea) or the copyright holder
 * specified, unless otherwise noted, and may not be reproduced or distributed
 * in whole or in part in any form or medium without express written permission.
 */
package com.chos.payment.log;

/**
 * 
 * 
 * @author xyluo
 * @version 1.0, 2012-10-16 
 * @since 1.0
 */
public abstract interface AbstractLogger<T> {

	/**
	 * @param <T>
	 * @param info
	 */
	void info(T info);
	
	/**
	 * @param <T>
	 * @param level
	 * @param log
	 */
	void log(Level level, T log);
	
	/**
	 * @param <T>
	 * @param warn
	 */
	void warn(T warn);
	
	/**
	 * @param <T>
	 * @param error
	 */
	void error(T error);
}
