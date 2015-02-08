package com.chos.payment;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.chos.payment.alipay.Rsa;

public class SecurityServiceTest {

	@BeforeClass()
	public static void init() {
//		iAlixPay = IAlixPay.Stub.asInterface(arg0)
	}
	
	@Before()
	public void setUp() {
		
	}
	
	private static String publicKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAOmwD9LFEKq3Nof5YwnLtgUJL9rDprgqZpyB/rN5ydSV9vW208Lk1gY+KP+BKq1dS12g7bjctGU7RrAIYLLwILv1Pg4U5bB4wDXZAJ77hMsuOsJdnli8/fA2E7xDycPYsJjv8s7gsXmG0WzolyQ7tHhhxgYB+zUYU3cfBXOnVjeHAgMBAAECgYAD2AeKmf86L6VP+0QrxxQJZStKV+mIIfpqHKFJ5njxbD4RsJ4vGICEvcUC3ucKzvwMYDdI0SiOebkh8CFXECgZp1Nq2HEnhbRgvygfUCxPKRugVYtRa+w53sZVDursce0xu9eV1kNz2T675r0pVZdn1PcnDqLAhoHObR1NtdsqcQJBAP7PotwqL4G4rY2hyjA4dpYyVE9he7UZ6kk6xCePA3ETs7axQJg+J1N9yh5cSYF6WBT0D7dLDYUt/yAKIkH0u7sCQQDqxzHJHk13SmTIjUPc9gwQe/njkxNbF14HZZhZByILFSOr4bD9k5dgyK1dNo/7Hrc/SaUf/NJCU1FOXPZnjCilAkEAvHAtTohcDaDXqFAnyDVZbYXW18WNyMZbfsBY47BdqTgaxVacIHqGdrYlzQIO0hP6I7TCEpkMbJsxey5DW2dzlQJAF8P2LC+fE9Y6AXsbRYC6k+iY0QqQSQZChGkqVMi+D3UiWUHNUr3f8vIdanQdewQO9J8V0fkbp48bid0rLCwp6QJARj6mCoCGPA6gW3HWbeLI3oUpu8QH6x6Z9xMzP8+N8cccfYbFLQzFnGUovcOyJD4aR8PUw/KVsrHDlR54nmeu0w==";
		
	private static String privateKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDpsA/SxRCqtzaH+WMJy7YFCS/aw6a4Kmacgf6zecnUlfb1ttPC5NYGPij/gSqtXUtdoO243LRlO0awCGCy8CC79T4OFOWweMA12QCe+4TLLjrCXZ5YvP3wNhO8Q8nD2LCY7/LO4LF5htFs6JckO7R4YcYGAfs1GFN3HwVzp1Y3hwIDAQAB";
	
	private static String sign(String data) {
    	String charset = "utf-8";
    	byte[] pd = Base64.base64ToByteArray(publicKey);
    	PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(pd);
    	
		try {
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);

			Signature signature = Signature.getInstance("SHA1WithRSA");
			signature.initSign(priKey);
			signature.update(data.getBytes(charset));

			byte[] signed = signature.sign();
			return Base64.byteArrayToBase64(signed);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
    
    private static boolean verify(String data, String signature) {
    	try {
    		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    		byte[] pd = Base64.base64ToByteArray(privateKey);
    		
			PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(pd));

			Signature s = Signature.getInstance("SHA1WithRSA");

			s.initVerify(pubKey);
			s.update(data.getBytes("utf-8"));

			boolean bverify = s.verify(Base64.base64ToByteArray(signature));
			return bverify;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
    }
    
    public static void main(String args[]) throws Exception {
    	String data = "notify_url=\"http://s0.glsanguo.com/api/v1/payment/notify\"&body=\"body\"&partner=\"2088002126619063\"&out_trade_no=\"1A2B-12101152139\"&seller=\"2088002126619063\"&total_fee=\"0.03\"&subject=\"subject\"";
    	data = "partner=\"2088002007260245\"&seller=\"2088002007260245\"&out_trade_no=\"500000000006548\"&subject=\"��Ʒ���\"&body=\"������Ʒ����\"&total_fee=\"30\"&notify_url=\"http://notify.java.jpxx.org/index.jsp\"";
    	System.out.println("Data: " + data);
    	
    	String signature = sign(data);
    	System.out.println("Signature: " + signature);
    	
    	boolean success = verify(data, signature);
    	System.out.println("Verify: " + (success ? "success" : "failure"));
    	
    	//////////////////////////////////////
    	signature = Rsa.sign(data, publicKey);
    	System.out.println("Signature: " + signature);
    	
//    	System.out.println("Verify: " + (success ? "success" : "failure"));
    }
    
	@After()
	public void tearDown() {
		
	}
	
	@AfterClass()
	public static void destroy() {
		
	}
}

