/*
 * 
 * 
 * 
 */
package com.chos.payment.log;

/**
 * @author luo, xiaoyong
 * @version 1.0
 * @since 1.0
 */
public enum Level {

	/**
	 * info level log definitions
	 */
	INFO('I'), 
	
	/**
	 * warn level log definitions
	 */
	WARN('W'), 
	
	/**
	 * error level log definitions
	 */
	ERROR('E');
	
	private char value;
	
	private Level(char value) {
		this.value = value;
	}
	
	public char value() {
		return value;
	}
}
