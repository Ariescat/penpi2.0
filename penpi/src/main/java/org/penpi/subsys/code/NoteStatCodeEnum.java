package org.penpi.subsys.code;

import java.util.ArrayList;
import java.util.List;

import org.penpi.core.commons.adapter.ICodeEnum;

public enum NoteStatCodeEnum implements ICodeEnum{

    /**
     * 正常
     */
	NORMAL,
	/**
	 * 异常冻结
	 */
	FREEZE;

	
	public static NoteStatCodeEnum fromCode(String code) {
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
		for(NoteStatCodeEnum value: values()){
			result.add(value);
		}
		return result;
	}
}
