/*
 * 
 * 
 * 
 */
package com.chos.payment;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author lxy
 * @version 1.0
 * @since 1.0
 */
public class Service {

	/**
	 * 
	 */
	private String service;
	
	/**
	 * 
	 */
	private Map<String, String> requestParameterDomainList;
	
	/**
	 * @param service
	 */
	public Service(String service) {
		this(service, new HashMap<String, String>());
	}
	
	/**
	 * @param service
	 * @param requestParameterDomainList
	 */
	public Service(String service, Map<String, String> requestParameterDomainList) {
		this.service = service;
		this.requestParameterDomainList = requestParameterDomainList;
	}
	
	/**
	 * @param parameter
	 * @param value
	 */
	public void addParameterDomain(String parameter, String value) {
		requestParameterDomainList.put(parameter, value);
	}
	
	/**
	 * @return
	 */
	public String getService() {
		return service;
	}
	
	/**
	 * @return
	 */
	public Map<String, String> getRequestParameterDomainList() {
		return requestParameterDomainList;
	}

	/**
	 * @param requestParameterDomainList
	 */
	public void setRequestParameterDomainList(
			Map<String, String> requestParameterDomainList) {
		this.requestParameterDomainList = requestParameterDomainList;
	}
}