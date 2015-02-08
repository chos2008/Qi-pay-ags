/*
 * 
 * 
 */
package com.chos.payment;

import javax.annotation.Resource;

import org.junit.Assert;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit38.AbstractJUnit38SpringContextTests;

import com.chos.payment.ThirdPaymentService;

/**
 * 
 * 
 * 
 * 
 * @author luo, xiaoyong
 *
 */
@ContextConfiguration(locations = {"/applicationContext-service.xml", 
		"/applicationContext-ibatis.xml"})
public class ThirdPaymentServiceTest extends AbstractJUnit38SpringContextTests {
	
//	@Autowired()
	@Resource
	private ThirdPaymentService thirdPaymentService;

	
	
	public void setThirdPaymentService(ThirdPaymentService thirdPaymentService) {
		this.thirdPaymentService = thirdPaymentService;
	}

	public void setUp() {
		
		
	}
	
	/**
	 */
//	@Test
	public void testListGemTemp() {
		
		Assert.assertTrue(true);
	}

	public void tearDown() {
		
	}

}