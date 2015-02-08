/**
 * Alipay.com Inc.
 * Copyright (c) 2005-2006 All Rights Reserved.
 */
package com.chos.payment.alipay;

import com.chos.payment.XmlResponse;

/**
 * ����֧�������񷵻صĽ��
 * 
 * @author 3y
 * @version $Id: ResponseResult.java, v 0.1 2011-8-28 1:37:15 PM 3y Exp $
 */
public class ResponseResult {

	/**
	 * �Ƿ���óɹ� Ĭ��Ϊfalse ������ÿ�ε��ö������������ֵΪtrue��
	 */
	private boolean isSuccess = false;
	
	/**
	 * ���õ�ҵ��ɹ���� ������ʧ�� ������ǿ�ֵ
	 */
	private String businessResult;

	/**
	 * ������Ϣ
	 */
	private XmlResponse errorMessage;

	/**
	 * @return Returns the errorMessage.
	 */
	public XmlResponse getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            The errorMessage to set.
	 */
	public void setErrorMessage(XmlResponse errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return Returns the businessResult.
	 */
	public String getBusinessResult() {
		return businessResult;
	}

	/**
	 * @param businessResult
	 *            The businessResult to set.
	 */
	public void setBusinessResult(String businessResult) {
		this.businessResult = businessResult;
	}

	/**
	 * @return Returns the isSuccess.
	 */
	public boolean isSuccess() {
		return isSuccess;
	}

	/**
	 * @param isSuccess
	 *            The isSuccess to set.
	 */
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
}
