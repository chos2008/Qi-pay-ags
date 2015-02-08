/*
 * CallbackResponse.java, 2007-6-27 下午2:00:59 xyluo
 * 
 * Copyright (c) 2011 Shanghai Qi(dea) Information Technology Co.,Ltd 
 * All rights reserved.
 * 
 * This software is copyrighted and owned by Qi(dea) or the copyright holder
 * specified, unless otherwise noted, and may not be reproduced or distributed
 * in whole or in part in any form or medium without express written permission.
 */
package com.chos.payment;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 回调后所接收的<b>JSON</b>格式数据的状态响应定义， 根据响应结果状态数据构造对象，
 * 调用重写后的{@link #toString()}方法可将构造对象转化成<b>JSON</b>格式的数据；
 * 调用{@link #toObject(String)}方法可将<b>JSON</b>格式的数据重新封装成<code>
 * {@link CallbackJSONResponse}</code>对象。
 * <p>
 * <ul>
 * <li>
 * 成功: </br>
 * {</br>
 * “status”:”success”</br>
 * }</br>
 * </li>
 * <li>
 * 失败: </br>
 * {</br>
 * “status”:”failure”</br>
 * }</br>
 * 
 * @author xyluo
 * @version 1.0, 2007-6-27
 * @since JDK1.6
 */
public class CallbackJSONResponse {
	
	private Logger logger;
	{
		Class<?> clazz = getClass();
		logger = LogManager.getLogger(clazz);
	}
	
	/**
	 * 回调成功状态响应
	 */
	public static final String SUCCESS = "success";
	
	/**
	 * 回调失败状态响应
	 */
	public static final String FAILURE = "fail";

	protected String status;
	
	protected String message;
	
	/**
	 * 
	 */
	public CallbackJSONResponse() {}
	
	/**
	 * 根据响应结果状态数据构造对象
	 * 
	 * @param status  {@link #SUCCESS} or {@link #FAILURE}
	 */
	public CallbackJSONResponse(String status) {
		this(status, null);
	}
	
	/**
	 * @param status
	 * @param message
	 */
	public CallbackJSONResponse(String status, String message) {
		this.status = status;
		this.message = message;
	}

	/**
	 * 将<b>JSON</b>格式的数据重新封装成<code>{@link CallbackJSONResponse}</code>对象
	 * 
	 * @param jsonString
	 * @return
	 * @throws JSONException
	 */
	public static CallbackJSONResponse toObject(String jsonString) throws JSONException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(jsonString, CallbackJSONResponse.class);
		} catch (IOException e) {
			throw new JSONException("Error to read the JSON string as object", e);
		}
	}
	
	/**
	 * 将构造对象转化成<b>JSON</b>格式的数据
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (IOException e) {
			logger.warn("Warning to re-write the object as JSON string", e);
		}
		return null;
	}
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
