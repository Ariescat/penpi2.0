package org.penpi.subsys.code;

import java.util.ArrayList;
import java.util.List;

import org.penpi.core.commons.adapter.ICodeEnum;


public enum UserStatCodeEnum implements ICodeEnum {
    /**
	 * 正常
	 */
	NORMAL,

    /**
	 * 冻结
	 */
	FREEZE,
	
    /**
	 * 已删除
	 */
	DELETED;

	public static UserStatCodeEnum fromCode(String code) {
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
		for(UserStatCodeEnum value: values()){
			result.add(value);
		}
		return result;
	}


}

