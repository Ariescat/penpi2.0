package org.penpi.subsys.code;

import java.util.ArrayList;
import java.util.List;

import org.penpi.core.commons.adapter.ICodeEnum;

/**
 * 搜索类型代码
 */
public enum SearchCodeEnum implements ICodeEnum {

	/**
	 * 用户
	 */
	USER("用户"),
	/**
	 * 话题
	 */
	TOPIC("话题"),
	/**
	 * 帖子
	 */
	NOTE("帖子");

	private String code;

	private SearchCodeEnum(String code) {
		this.code = code;
	}

	public static SearchCodeEnum fromCode(String code) {
		for (SearchCodeEnum boolCodeEnum : SearchCodeEnum.values()) {
			if (boolCodeEnum.code.equals(code)) {
				return boolCodeEnum;
			}
		}
		return null;
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
		for (SearchCodeEnum value : values()) {
			result.add(value);
		}
		return result;
	}

}
