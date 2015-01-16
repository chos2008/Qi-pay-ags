package com.qidea.payment.rest.service;

import com.qidea.payment.CallbackJSONResponse;

public class Auth extends CallbackJSONResponse {

	private String authID;

	public Auth() {
		super();
	}

	public Auth(String status, String message) {
		super(status, message);
	}

	public Auth(String status) {
		super(status);
	}

	public String getAuthID() {
		return authID;
	}

	public void setAuthID(String authID) {
		this.authID = authID;
	}
	
	
}
