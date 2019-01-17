package org.penpi.subsys.code;

import java.util.ArrayList;
import java.util.List;

import org.penpi.core.commons.adapter.ICodeEnum;

public enum SexCodeEnum implements ICodeEnum{

    /**
     * 男士
     */
	MAN,
	/**
	 * 女士
	 */
	WOMAN,
	/**
	 * 保密
	 */
	SECRECY;

	
	public static SexCodeEnum fromCode(String code) {
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
		for(SexCodeEnum value: values()){
			result.add(value);
		}
		return result;
	}
}
