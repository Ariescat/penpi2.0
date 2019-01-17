package org.penpi.core.commons.adapter;

import java.io.Serializable;

public interface IHasSearchBuilder<E extends Serializable, ID extends Serializable> {

	/**
	 * 构建索引
	 */
	void buildAllIndex();

	Integer getPriority();
}
