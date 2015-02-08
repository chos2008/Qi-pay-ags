package com.chos.payment;

import java.util.List;

/**
 * 
 * 
 * @author luo
 */
public interface ThirdPaymentService {

	/**
	 * @return
	 */
	public List<Ta> list();
	
	/**
	 * @param ta
	 * @return
	 */
	public TaA get(String ta);
}