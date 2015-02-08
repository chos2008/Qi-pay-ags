/*
 * CallbackEndpoint.java, 2007-05-11 ����09:27:20 xyluo
 * 
 * Copyright (c) 2011 Shanghai Qi(dea) Information Technology Co.,Ltd 
 * All rights reserved.
 * 
 * This software is copyrighted and owned by Qi(dea) or the copyright holder
 * specified, unless otherwise noted, and may not be reproduced or distributed
 * in whole or in part in any form or medium without express written permission.
 */
package com.chos.payment.transaction;

import java.util.Date;

/**
 * 
 * 
 * @author xyluo
 * @version 1.0, 2007-05-11
 * @since 1.0
 */
public class CallbackEndpoint {

	private int id;
	
	private String transactionSN;
	
	private String callbackEndpoint;
	
	private String forwardDestination;
	
	private Date creation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTransactionSN() {
		return transactionSN;
	}

	public void setTransactionSN(String transactionSN) {
		this.transactionSN = transactionSN;
	}

	public String getCallbackEndpoint() {
		return callbackEndpoint;
	}

	public void setCallbackEndpoint(String callbackEndpoint) {
		this.callbackEndpoint = callbackEndpoint;
	}

	public String getForwardDestination() {
		return forwardDestination;
	}

	public void setForwardDestination(String forwardDestination) {
		this.forwardDestination = forwardDestination;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}
}