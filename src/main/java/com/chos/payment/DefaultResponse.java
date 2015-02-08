/*
 * DefaultResponse.java, 2012-10-15 下午02:00:35 xyluo
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
 * @version 1.0, 2012-10-15 
 * @since 1.0
 */
public class DefaultResponse {

	/**
	 * 
	 */
	protected String status;

	public DefaultResponse(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}