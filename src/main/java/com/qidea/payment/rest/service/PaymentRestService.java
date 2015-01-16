/*
 * PaymentRestService.java, 2011-10-11 下午05:44:19 xyluo
 * 
 * Copyright (c) 2011 Shanghai Qi(dea) Information Technology Co.,Ltd 
 * All rights reserved.
 * 
 * This software is copyrighted and owned by Qi(dea) or the copyright holder
 * specified, unless otherwise noted, and may not be reproduced or distributed
 * in whole or in part in any form or medium without express written permission.
 */
package com.qidea.payment.rest.service;

import com.qidea.payment.CallbackJSONResponse;

/**
 * 
 * 
 * @author xiaoyong
 * @version 1.0, 2011-10-11 
 * @since 1.0
 */
/**
 * 
 * 
 * @author xyluo
 * @version 1.0, 2012-10-12 
 * @since 1.0
 */
public interface PaymentRestService {

	/**
	 * 
	 */
	public static final String STATUS_OK = "1000000";
	
	/**
	 * 
	 */
	public static final String STATUS_OK_MESSAGE = "Ok!!!";
	
	/**
	 * 
	 */
	public static final String TAA_NOT_EXISTS = "1000001";
	
	/**
	 * 
	 */
	public static final String TAA_NOT_EXISTS_MESSAGE = "该接入号不存在， 请检查你是否为合法接入！";
	
	/**
	 * @param ta
	 * @param password
	 * @return
	 */
	public CallbackJSONResponse authenticate(String ta, String password);
}