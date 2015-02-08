package com.chos.payment;

import java.util.Date;
import java.util.UUID;

public class UUIDTest {

	public static void main(String[] args) throws Exception {
		UUID uuid = UUID.randomUUID();
		String string = uuid.toString();
		
		System.out.println("uuid: " + string);
		System.out.println("uuid length: " + string.length());
		
		String s = string.replaceAll("-", "");
		System.out.println("uuid: " + s);
		System.out.println("uuid length: " + s.length());
		
		
		//173547032428147 �ɽ�ʱ�䣺2012-08-31 11:02 
		Date datetime = new Date(2012 - 1900 , 9, 31, 11, 2, 0);
		System.out.println("->" + datetime);
		System.out.println("->" + datetime.getTime());
		
		Date d = new Date(17354703242L);
		System.out.println("->" + d);
		System.out.println("->" + d.getTime());
		
		
		//155724558488147 �ɽ�ʱ�䣺2012-06-17 23:56
		datetime = new Date(2012 - 1900 , 7, 17, 23, 56, 0);
		System.out.println("->" + datetime);
		System.out.println("->" + datetime.getTime());
		
		d = new Date(15572455848L);
		System.out.println("->" + d);
		System.out.println("->" + d.getTime());
	}
}
