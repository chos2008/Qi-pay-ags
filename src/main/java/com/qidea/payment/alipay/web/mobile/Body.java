/*
 * Body.java, 2012-11-15 下午09:54:58 xyluo
 * 
 * Copyright (c) 2011 Shanghai Qi(dea) Information Technology Co.,Ltd 
 * All rights reserved.
 * 
 * This software is copyrighted and owned by Qi(dea) or the copyright holder
 * specified, unless otherwise noted, and may not be reproduced or distributed
 * in whole or in part in any form or medium without express written permission.
 */
package com.qidea.payment.alipay.web.mobile;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

/**
 * 
 * 
 * @author xyluo
 * @version 1.0, 2012-11-15 
 * @since 1.0
 */
@XObject("notify")
public class Body {

	@XNode("payment_type")
	private String type;
	
	@XNode("subject")
	private String subject;
	
	@XNode("trade_no")
	private String taTransactionSN;
	
	@XNode("buyer_email")
	private String email;
	
	@XNode("gmt_create")
	private String creation;
	
	@XNode("notify_type")
	private String notifyType;
	
	@XNode("quantity")
	private String quantity;
	
	@XNode("out_trade_no")
	private String transactionSN;
	
	@XNode("notify_time")
	private String timestamp;
	
	@XNode("seller_id")
	private String sellerId;
	
	@XNode("trade_status")
	private String status;
	
	@XNode("is_total_fee_adjust")
	private String adjust;
	
	@XNode("total_fee")
	private String totalFee;
	
	@XNode("gmt_payment")
	private String gmtTimestamp;
	
	@XNode("seller_email")
	private String sellerEmail;
	
	@XNode("gmt_close")
	private String gmtCloseTimestamp;
	
	@XNode("price")
	private String price;
	
	@XNode("buyer_id")
	private String buyerId;
	
	@XNode("notify_id")
	private String id;
	
	@XNode("use_coupon")
	private String useCoupon;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTaTransactionSN() {
		return taTransactionSN;
	}

	public void setTaTransactionSN(String taTransactionSN) {
		this.taTransactionSN = taTransactionSN;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreation() {
		return creation;
	}

	public void setCreation(String creation) {
		this.creation = creation;
	}

	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getTransactionSN() {
		return transactionSN;
	}

	public void setTransactionSN(String transactionSN) {
		this.transactionSN = transactionSN;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAdjust() {
		return adjust;
	}

	public void setAdjust(String adjust) {
		this.adjust = adjust;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getGmtTimestamp() {
		return gmtTimestamp;
	}

	public void setGmtTimestamp(String gmtTimestamp) {
		this.gmtTimestamp = gmtTimestamp;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public String getGmtCloseTimestamp() {
		return gmtCloseTimestamp;
	}

	public void setGmtCloseTimestamp(String gmtCloseTimestamp) {
		this.gmtCloseTimestamp = gmtCloseTimestamp;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUseCoupon() {
		return useCoupon;
	}

	public void setUseCoupon(String useCoupon) {
		this.useCoupon = useCoupon;
	}
}
