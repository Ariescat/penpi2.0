package org.penpi.core.utils;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.math.RandomUtils;

public class UtilValidateCode {
	
	private final static int MAX_ENTRIES = 1024; //验证码最大值
	private final static int OVER_DUE_STEP = 120; //验证码过期时间120秒
	
	private static Map<String, ValidateCode> validateCodeMap = new LinkedHashMap<String, ValidateCode>() {

		private static final long serialVersionUID = 1L;
		
		@Override
		protected boolean removeEldestEntry(java.util.Map.Entry<String, ValidateCode> eldest) {
			return size() > MAX_ENTRIES;
		}
		
	};

	/**
	 * 获取验证码
	 */
	public synchronized String getValidateCode(String key) {
		ValidateCode code = validateCodeMap.get(key);
		if (code != null && !code.ifOverDue()) {
			return validateCodeMap.get(key).getCode();
		} else {
			code = new ValidateCode();
			validateCodeMap.put(key, code);
			return code.getCode();
		}
	}

	/**
	 * 验证方法
	 */
	public boolean verificate(String key, String code) {
		ValidateCode val = validateCodeMap.get(key);
		if (val == null) {
			return false;
		}
		if (val.verificate(code)) {
			validateCodeMap.remove(key);// 验证成功后验证码就需要去掉了
			return true;
		}
		return false;
	}

	// 验证码内部类
	class ValidateCode {
		
		private String code;
		private Date overDueTime;

		public ValidateCode(String code, Date overdueTime) {
			this.code = code;
			this.overDueTime = overdueTime;
		}

		// 无参构造，自动生成验证码与设置过期时间
		public ValidateCode() {
			this.code = String.format("%.10f", RandomUtils.nextDouble()).substring(2, 8);
			this.overDueTime = UtilDateTime.addSeconds(new Date(), OVER_DUE_STEP);
		}

		public boolean ifOverDue() {
			if (this.overDueTime.compareTo(new Date()) <= 0) {
				return true;
			} else
				return false;
		}

		public String getCode() {
			return code;
		}

		public boolean verificate(String code) {
			if (overDueTime.compareTo(new Date()) < 0) {
				return false;
			}
			return this.code.equals(code);
		}
	}

}
