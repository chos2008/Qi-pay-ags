/*
 * JSONException.java, 2007-6-27 下午2:24:13 xyluo
 * 
 * Copyright (c) 2011 Shanghai Qi(dea) Information Technology Co.,Ltd 
 * All rights reserved.
 * 
 * This software is copyrighted and owned by Qi(dea) or the copyright holder
 * specified, unless otherwise noted, and may not be reproduced or distributed
 * in whole or in part in any form or medium without express written permission.
 */
package com.chos.payment;

/**
 * 
 * 
 * @author xyluo
 * @version 1.0, 2007-6-27
 * @since JDK1.6
 */
public class JSONException extends RuntimeException {

	private static final long serialVersionUID = 8522783113989214997L;

	/**
	 * @param message
	 * @param cause
	 */
	public JSONException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public JSONException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public JSONException(Throwable cause) {
		super(cause);
	}
}
