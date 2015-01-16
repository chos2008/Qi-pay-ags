/*
 * Logger.java, 2012-10-11 ÏÂÎç11:59:59 xyluo
 * 
 * Copyright (c) 2011 Shanghai Qi(dea) Information Technology Co.,Ltd 
 * All rights reserved.
 * 
 * This software is copyrighted and owned by Qi(dea) or the copyright holder
 * specified, unless otherwise noted, and may not be reproduced or distributed
 * in whole or in part in any form or medium without express written permission.
 */
package com.qidea.payment.log;

/**
 * 
 * 
 * @author xyluo
 * @version 1.0, 2012-10-11 
 * @since 1.0
 */
public interface Logger {

	/**
	 * @param <T>
	 * @param info
	 */
	<T> void info(T info);
	
	/**
	 * @param <T>
	 * @param level
	 * @param log
	 */
	<T> void log(Level level, T log);
	
	/**
	 * @param <T>
	 * @param warn
	 */
	<T> void warn(T warn);
	
	/**
	 * @param <T>
	 * @param error
	 */
	<T> void error(T error);
}
