/*
 * TransactionService.java, 2012-10-11 ÏÂÎç09:54:19 xyluo
 * 
 * Copyright (c) 2011 Shanghai Qi(dea) Information Technology Co.,Ltd 
 * All rights reserved.
 * 
 * This software is copyrighted and owned by Qi(dea) or the copyright holder
 * specified, unless otherwise noted, and may not be reproduced or distributed
 * in whole or in part in any form or medium without express written permission.
 */
package com.qidea.payment.transaction;

import java.util.Map;


/**
 * 
 * 
 * @author xyluo
 * @version 1.0, 2012-10-11 
 * @since 1.0
 */
public interface TransactionService {

	/**
	 * @param taTransactionSN
	 * @return
	 */
	public Transaction get(String taTransactionSN);
	
	/**
	 * @param transaction
	 */
	public void create(Transaction transaction);
	
	/**
	 * @param transaction
	 */
	public void update(Transaction transaction);
	
	/**
	 * @param tsn
	 * @param status
	 */
	public void toState(String tsn, char status);
	
	/**
	 * @param tsn
	 * @param param
	 */
	public void toUpdateTransaction(String tsn, Map<String, Object> param);
	
	/**
	 * @param transactionSN
	 * @return
	 */
	public CallbackEndpoint getCallbackEndpoint(String transactionSN);
	
	/**
	 * @param ce
	 */
	public void createCallbackEndpoint(CallbackEndpoint ce);
}
