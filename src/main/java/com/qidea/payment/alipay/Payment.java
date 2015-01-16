
package com.qidea.payment.alipay;

/**
 * 
 * 
 * @author xyluo
 * @version 1.0, 2008-09-05 
 * @since 1.0
 */
public class Payment {
	
    public static final String ALIPAY_PAYMENT_SERVICE = "https://wappaygw.alipay.com/service/rest.htm";
    
    public static final String SUBJECT = "黄金";
    
    public static final String TOTAL_FEE = "0.01";
    
    private String tr;
    
    private String subject;
    
    private float amount;

	public String getTr() {
		return tr;
	}

	public void setTr(String tr) {
		this.tr = tr;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
}