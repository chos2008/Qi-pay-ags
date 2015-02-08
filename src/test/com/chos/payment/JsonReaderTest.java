/*
 * t.java, 2012-10-11 ����02:51:21 xyluo
 * 
 * Copyright (c) 2011 Shanghai Qi(dea) Information Technology Co.,Ltd 
 * All rights reserved.
 * 
 * This software is copyrighted and owned by Qi(dea) or the copyright holder
 * specified, unless otherwise noted, and may not be reproduced or distributed
 * in whole or in part in any form or medium without express written permission.
 */
package com.chos.payment;

import java.io.StringReader;

import org.junit.Test;

import com.google.gson.stream.JsonReader;

/**
 * 
 * 
 * @author xyluo
 * @version 1.0, 2012-10-11 
 * @since 1.0
 */
public class JsonReaderTest {

	@Test
	public void test() {
		String jsonText = "resultStatus={9000}";
		jsonText = "{\"id\":1,\"name\":\"֧����\",\"index\":0,\"logo\":\"daily_reward_ball.png\"}";
		
//		jsonText = "{notify_url=\"http://s0.glsanguo.com/api/v1/payment/notify\"&body=\"body\"&partner=\"2088002126619063\"&out_trade_no=\"1A2B-12101159466\"&seller=\"2088002126619063\"&total_fee=\"0.01\"&subject=\"subject\"&success=\"true\"&sign_type=\"RSA\"&sign=\"s7xRk+AI6EW+MJgchYfAE5HRKCHBKbBdNVqSSN5z5XJ2JMSc7crtcgSRiMzIDYa/jZOI5AHeFfvFr0+AxzkXyqwR7uB1dStLeAGzpOpw4DpsworEFtj8YAuD3R/G6C1OC1ivmdY31aK8k8CzdcTzLeDF69LXcvRFsmZrMR5+MSA=\"}";
//		jsonText.replaceAll("&", ",");
		
//		jsonText = "{\"notify_url\"=\"http://s0.glsanguo.com/api/v1/payment/notify\"&\"body\"=\"body\"&\"partner\"=\"2088002126619063\"&\"out_trade_no\"=\"1A2B-12101159466\"&\"seller\"=\"2088002126619063\"&\"total_fee\"=\"0.01\"&\"subject\"=\"subject\"&\"success\"=\"true\"&\"sign_type\"=\"RSA\"&\"sign\"=\"s7xRk+AI6EW+MJgchYfAE5HRKCHBKbBdNVqSSN5z5XJ2JMSc7crtcgSRiMzIDYa/jZOI5AHeFfvFr0+AxzkXyqwR7uB1dStLeAGzpOpw4DpsworEFtj8YAuD3R/G6C1OC1ivmdY31aK8k8CzdcTzLeDF69LXcvRFsmZrMR5+MSA=\"}";
		
		JsonReader reader = new JsonReader(new StringReader(jsonText));
		reader.setLenient(true);
		
		String status = null;
		String message;
		try {
			reader.beginObject();
			// �жϵ�ǰJSONObject�������滹��û��һ���ֵ��
			while (reader.hasNext()) {
				// ȡ��ǰ��ֵ�Ե�key
				String tagName = reader.nextName();
				System.out.println("key--->" + tagName);
				
				// ȡ��key���Ӧ��value
				status = reader.nextString();
				System.out.println("value--->" + status);
			}
			// ����������
			reader.endObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
