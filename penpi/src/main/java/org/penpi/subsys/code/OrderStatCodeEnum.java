package org.penpi.subsys.code;

import java.util.ArrayList;
import java.util.List;

import org.penpi.core.commons.adapter.ICodeEnum;

public enum OrderStatCodeEnum implements ICodeEnum{

    /**
     * 已发
     */
	HAS_SEND,
	/**
	 * 已抢
	 */
	HAS_GRAB,
	/**
	 * 已送达
	 */
	HAS_ARRIVE,
	/**
	 * 已付款
	 */
	HAS_PAY,
	/**
	 * 异常冻结
	 */
	FREEZE;

	
	public static OrderStatCodeEnum fromCode(String code) {
		try {
			return values()[Integer.parseInt(code)];
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String toCode() {
		return Integer.toString(this.ordinal());
	}

	@Override
	public List<ICodeEnum> valueList() {
		List<ICodeEnum> result = new ArrayList<ICodeEnum>();
		for(OrderStatCodeEnum value: values()){
			result.add(value);
		}
		return result;
	}
}
