
/*
 * RequestBody.java, 2012-10-11 下午08:43:45 xyluo
 * 
 * Copyright (c) 2011 Shanghai Qi(dea) Information Technology Co.,Ltd 
 * All rights reserved.
 * 
 * This software is copyrighted and owned by Qi(dea) or the copyright holder
 * specified, unless otherwise noted, and may not be reproduced or distributed
 * in whole or in part in any form or medium without express written permission.
 */
package com.qidea.payment.rest.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.qidea.payment.transaction.DataFormatException;

/**
 * 
 * 
 * @author xyluo
 * @version 1.0, 2012-10-11 
 * @since 1.0
 */
public class RequestBody {
	
	private static final Logger log = Logger.getLogger(RequestBody.class);

	public  void test() {
		
	}
	private String partner;
	
	private String discount;
	
	private String paymentType;
	
	private String subject;
	
	private String tradeNo;
	
	private String buyerEmail;
	
	private String gmtCreate;
	
	private String quantity;
	
	private String outTradeNo;
	
	private String sellerId;
	
	private String tradeStatus;
	
	private String isTotalFeeAdjust;
	
	private String totalFee;
	
	private String gmtPayment;
	
	private String sellerEmail;
	
	private String price;
	
	private String buyerId;
	
	private String useCoupon;
	
	private Document parse(String text) throws DataFormatException {
		InputStream is = new ByteArrayInputStream(text.getBytes());
		DocumentBuilder builder;
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = builder.parse(is);
			return document;
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("Fail to parse xml data: " + text, e);
		}
		return null;
	}
	
	/**
	 * 参考Qi游戏支付-概要设计文档.docx请求域-主体部分，更多信息可参考支付宝
	 * 接入文档规范【安全支付服务Android应用开发指南(20120720).pdf】4.2 notify_url通知说明
	 * 部分内容
	 * <p>
	 * 数据的组织遵循XML标准，类似
	 * 
	 * 
	 * @param data
	 */
	public RequestBody(String data) throws DataFormatException {
		Document document = parse(data);
		Element root = document.getDocumentElement();
		NodeList cn = root.getChildNodes();
		try {
			for (int i = 0, length = cn.getLength(); i < length; i++) {
				Node n = cn.item(i);
				String nn = n.getNodeName();
				if ("partner".equals(nn)) {
					partner = n.getTextContent();
				} else if ("discount".equals(nn)) {
					discount = n.getTextContent();
				} else if ("payment_type".equals(nn)) {
					paymentType = n.getTextContent();
				} else if ("subject".equals(nn)) {
					subject = n.getTextContent();
				} else if ("trade_no".equals(nn)) {
					tradeNo = n.getTextContent();
				} else if ("buyer_email".equals(nn)) {
					buyerEmail = n.getTextContent();
				} else if ("gmt_create".equals(nn)) {
					gmtCreate = n.getTextContent();
				} else if ("quantity".equals(nn)) {
					quantity = n.getTextContent();
				} else if ("out_trade_no".equals(nn)) {
					outTradeNo = n.getTextContent();
				} else if ("seller_id".equals(nn)) {
					sellerId = n.getTextContent();
				} else if ("trade_status".equals(nn)) {
					tradeStatus = n.getTextContent();
				} else if ("is_total_fee_adjust".equals(nn)) {
					isTotalFeeAdjust = n.getTextContent();
				} else if ("total_fee".equals(nn)) {
					totalFee = n.getTextContent();
				} else if ("gmt_payment".equals(nn)) {
					gmtPayment = n.getTextContent();
				} else if ("seller_email".equals(nn)) {
					sellerEmail = n.getTextContent();
				} else if ("price".equals(nn)) {
					price = n.getTextContent();
				} else if ("buyer_id".equals(nn)) {
					buyerId = n.getTextContent();
				} else if ("use_coupon".equals(nn)) {
					useCoupon = n.getTextContent();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public String getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getIsTotalFeeAdjust() {
		return isTotalFeeAdjust;
	}

	public void setIsTotalFeeAdjust(String isTotalFeeAdjust) {
		this.isTotalFeeAdjust = isTotalFeeAdjust;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getGmtPayment() {
		return gmtPayment;
	}

	public void setGmtPayment(String gmtPayment) {
		this.gmtPayment = gmtPayment;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getUseCoupon() {
		return useCoupon;
	}

	public void setUseCoupon(String useCoupon) {
		this.useCoupon = useCoupon;
	}
}