package org.penpi.core.commons.adapter;

import java.util.List;

public interface ICodeEnum {
	
	String toCode();
	
	List<ICodeEnum> valueList();
}
