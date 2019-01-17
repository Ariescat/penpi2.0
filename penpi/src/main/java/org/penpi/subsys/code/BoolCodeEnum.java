package org.penpi.subsys.code;

import java.util.ArrayList;
import java.util.List;

import org.penpi.core.commons.adapter.ICodeEnum;

/**
 * 是否值代码
 */
public enum BoolCodeEnum implements ICodeEnum {

	/**
	 * 是
	 */
	YES("Y"),
	/**
	 * 否
	 */
	NO("N");

	private String code;

	private BoolCodeEnum(String code) {
		this.code = code;
	}

	public static BoolCodeEnum fromCode(String code) {
		for (BoolCodeEnum boolCodeEnum : BoolCodeEnum.values()) {
			if (boolCodeEnum.code.equals(code)) {
				return boolCodeEnum;
			}
		}
		return null;
	}

	public static BoolCodeEnum fromValue(boolean value) {
		if (value) {
			return YES;
		}
		return NO;
	}

	public static String toCode(boolean value) {
		if (value) {
			return YES.toCode();
		}
		return NO.toCode();
	}

	public static boolean toBoolValue(String value) {
		return fromCode(value).boolValue();
	}

	public String toCode() {
		return code;
	}

	public boolean boolValue() {
		return this.ordinal() == 0;
	}

	@Override
	public List<ICodeEnum> valueList() {
		List<ICodeEnum> result = new ArrayList<ICodeEnum>();
		for (BoolCodeEnum value : values()) {
			result.add(value);
		}
		return result;
	}

}
