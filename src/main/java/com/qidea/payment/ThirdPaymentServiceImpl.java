/*
 * 
 * 
 * 
 */
package com.qidea.payment;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * 
 * 
 * 
 * @author luo, xiaoyong
 *
 */
@Service(value = "thirdPaymentService")
public class ThirdPaymentServiceImpl implements ThirdPaymentService {

	@Autowired()
	private SqlSessionTemplate template;
	
	public List<Ta> list() {
		return template.<Ta>selectList("ta.list");
	}
	
	public List<TaA> listTa() {
		return template.<TaA>selectList("TaA.list");
	}
	
	public TaA get(String ta) {
		return template.<TaA>selectOne("TaA.select", ta);
	}
}