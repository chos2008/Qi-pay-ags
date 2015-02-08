/*
 * 
 * 
 * 
 */
package com.chos.payment.transaction.log;

import java.util.Date;

/**
 * @author luo, xiaoyong
 * @version 1.0
 * @since 1.0
 */
public class TransactionLog {

	private int id;
	
	private Date timestamp;
	
	private String comment;
	
	private char level;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public char getLevel() {
		return level;
	}

	public void setLevel(char level) {
		this.level = level;
	}
}