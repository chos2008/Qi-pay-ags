/*
 * Transaction.java, 2012-10-11 ����09:27:20 xyluo
 * 
 * Copyright (c) 2011 Shanghai Qi(dea) Information Technology Co.,Ltd 
 * All rights reserved.
 * 
 * This software is copyrighted and owned by Qi(dea) or the copyright holder
 * specified, unless otherwise noted, and may not be reproduced or distributed
 * in whole or in part in any form or medium without express written permission.
 */
package com.qidea.payment.transaction;

import java.util.Date;

/**
 * 
 * 
 * @author xyluo
 * @version 1.0, 2012-10-11 
 * @since 1.0
 */
public class Transaction {

	/**
	 * 
	 */
	public static final char TRANSACTION_DEFAULT_STATUS = 'D';
	
	/**
	 * 
	 */
	public static final char TRADE_FINISHED = 'F';
	
	/**
	 * 
	 */
	public static final char TRADE_SUCCESS = 'S';
	
	/**
	 * 
	 */
	public static final char WAIT_BUYER_PAY = 'W';
	
	private int id;
	
	private String transactionSN;
	
	private String orderSN;
	
	private String taTransactionSN;
	
	private Date timestamp;
	
	private Date transactionTimestamp;
	
	private String ta;
	
	private String tr;
	
	private float amount;
	
	private char status;

	public Transaction() {
		status = TRANSACTION_DEFAULT_STATUS;
	}
	
	public Transaction(String transactionSN) {
		this();
		this.transactionSN = transactionSN;
	}
	
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

	public String getOrderSN() {
		return orderSN;
	}

	public void setOrderSN(String orderSN) {
		this.orderSN = orderSN;
	}

	public String getTaTransactionSN() {
		return taTransactionSN;
	}

	public void setTaTransactionSN(String taTransactionSN) {
		this.taTransactionSN = taTransactionSN;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Date getTransactionTimestamp() {
		return transactionTimestamp;
	}

	public void setTransactionTimestamp(Date transactionTimestamp) {
		this.transactionTimestamp = transactionTimestamp;
	}

	public String getTa() {
		return ta;
	}

	public void setTa(String ta) {
		this.ta = ta;
	}

	public String getTr() {
		return tr;
	}

	public void setTr(String tr) {
		this.tr = tr;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
}