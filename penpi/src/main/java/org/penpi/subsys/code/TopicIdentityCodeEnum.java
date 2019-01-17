package org.penpi.subsys.code;

import java.util.ArrayList;
import java.util.List;

import org.penpi.core.commons.adapter.ICodeEnum;

public enum TopicIdentityCodeEnum implements ICodeEnum{

    /**
     * 普通
     */
	NORMAL,
	/**
	 * 管理员
	 */
	ADMIN;

	
	public static TopicIdentityCodeEnum fromCode(String code) {
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
		for(TopicIdentityCodeEnum value: values()){
			result.add(value);
		}
		return result;
	}
}
