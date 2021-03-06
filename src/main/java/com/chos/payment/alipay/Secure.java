package com.chos.payment.alipay;

/**
 * 
 * 
 * @author xyluo
 * @version 1.0, 2008-09-06 
 * @since 1.0
 */
public class Secure {
	
	/**
	 * 
	 */
	public static final String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAOmwD9LFEKq3Nof5YwnLtgUJL9rDprgqZpyB/rN5ydSV9vW208Lk1gY+KP+BKq1dS12g7bjctGU7RrAIYLLwILv1Pg4U5bB4wDXZAJ77hMsuOsJdnli8/fA2E7xDycPYsJjv8s7gsXmG0WzolyQ7tHhhxgYB+zUYU3cfBXOnVjeHAgMBAAECgYAD2AeKmf86L6VP+0QrxxQJZStKV+mIIfpqHKFJ5njxbD4RsJ4vGICEvcUC3ucKzvwMYDdI0SiOebkh8CFXECgZp1Nq2HEnhbRgvygfUCxPKRugVYtRa+w53sZVDursce0xu9eV1kNz2T675r0pVZdn1PcnDqLAhoHObR1NtdsqcQJBAP7PotwqL4G4rY2hyjA4dpYyVE9he7UZ6kk6xCePA3ETs7axQJg+J1N9yh5cSYF6WBT0D7dLDYUt/yAKIkH0u7sCQQDqxzHJHk13SmTIjUPc9gwQe/njkxNbF14HZZhZByILFSOr4bD9k5dgyK1dNo/7Hrc/SaUf/NJCU1FOXPZnjCilAkEAvHAtTohcDaDXqFAnyDVZbYXW18WNyMZbfsBY47BdqTgaxVacIHqGdrYlzQIO0hP6I7TCEpkMbJsxey5DW2dzlQJAF8P2LC+fE9Y6AXsbRYC6k+iY0QqQSQZChGkqVMi+D3UiWUHNUr3f8vIdanQdewQO9J8V0fkbp48bid0rLCwp6QJARj6mCoCGPA6gW3HWbeLI3oUpu8QH6x6Z9xMzP8+N8cccfYbFLQzFnGUovcOyJD4aR8PUw/KVsrHDlR54nmeu0w==";
	//public static final String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAOmwD9LFEKq3Nof5YwnLtgUJL9rDprgqZpyB/rN5ydSV9vW208Lk1gY+KP+BKq1dS12g7bjctGU7RrAIYLLwILv1Pg4U5bB4wDXZAJ77hMsuOsJdnli8/fA2E7xDycPYsJjv8s7gsXmG0WzolyQ7tHhhxgYB+zUYU3cfBXOnVjeHAgMBAAECgYAD2AeKmf86L6VP+0QrxxQJZStKV+mIIfpqHKFJ5njxbD4RsJ4vGICEvcUC3ucKzvwMYDdI0SiOebkh8CFXECgZp1Nq2HEnhbRgvygfUCxPKRugVYtRa+w53sZVDursce0xu9eV1kNz2T675r0pVZdn1PcnDqLAhoHObR1NtdsqcQJBAP7PotwqL4G4rY2hyjA4dpYyVE9he7UZ6kk6xCePA3ETs7axQJg+J1N9yh5cSYF6WBT0D7dLDYUt/yAKIkH0u7sCQQDqxzHJHk13SmTIjUPc9gwQe/njkxNbF14HZZhZByILFSOr4bD9k5dgyK1dNo/7Hrc/SaUf/NJCU1FOXPZnjCilAkEAvHAtTohcDaDXqFAnyDVZbYXW18WNyMZbfsBY47BdqTgaxVacIHqGdrYlzQIO0hP6I7TCEpkMbJsxey5DW2dzlQJAF8P2LC+fE9Y6AXsbRYC6k+iY0QqQSQZChGkqVMi+D3UiWUHNUr3f8vIdanQdewQO9J8V0fkbp48bid0rLCwp6QJARj6mCoCGPA6gW3HWbeLI3oUpu8QH6x6Z9xMzP8+N8cccfYbFLQzFnGUovcOyJD4aR8PUw/KVsrHDlR54nmeu0w==";
	
	/**
	 * 
	 */
	//public static final String RSA_ALIPAY_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+2ydIXopRD+bMcApq1cF3xD7e88iXGD2kL97O reIuh59/Y5gjCkDuzh0B9VmWRTXm1W84JRQjZLSyIiX6PeeNHLvZ7v0ZbDgm/m/BzBfQq+OdJwhQ ndIeF9/0HA2oXkNbuyEUsgWIjwbZwQd9cF/AYJIczXd1/l8HOL1c++6gnwIDAQAB";
	public static final String RSA_ALIPAY_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCQUtXjo8YEuIat8w+gR0/+RCgiKhedG6odZla1 FYVlH0ZNuBjeXJa1W96YY3rTGOhB4fLyHemNcs5BTxi6uCvGoDcMaIYi8QIzWxYAR8LgDiyh3VSN 5NNrqwY7lxSrET5pkb36DIRuq17LGJnXRVbtiqGAk2WJlfC0R+Sjf9zlewIDAQAB";
	//public static final String RSA_ALIPAY_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDpsA/SxRCqtzaH+WMJy7YFCS/aw6a4Kmacgf6zecnUlfb1ttPC5NYGPij/gSqtXUtdoO243LRlO0awCGCy8CC79T4OFOWweMA12QCe+4TLLjrCXZ5YvP3wNhO8Q8nD2LCY7/LO4LF5htFs6JckO7R4YcYGAfs1GFN3HwVzp1Y3hwIDAQAB";
	
	/**
	 * 
	 */
	public static final String KEY="es3z15ve6iza8rhgqik6m4z8gxwc610g";
}