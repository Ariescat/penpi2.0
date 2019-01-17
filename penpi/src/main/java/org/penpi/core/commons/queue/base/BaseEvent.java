package org.penpi.core.commons.queue.base;

public class BaseEvent<T> {

	private T value;

	public BaseEvent() {
	}

	public BaseEvent(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
}
