package org.penpi.core.commons.helper;

import org.penpi.core.commons.queue.base.BaseQueueHelper;
import org.penpi.core.commons.queue.data.SmallPictEventData;
import org.penpi.core.commons.queue.event.SmallPictEvent;
import org.penpi.core.commons.queue.handler.SmallPictEventHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SmallPictImageQueueHelper extends BaseQueueHelper<SmallPictEventData, SmallPictEvent, SmallPictEventHandler>{

	private static final int QUEUE_SIZE = 1024;
	
	private int threadNum;

    @Value("${app.queue.smallPict.threadNum:1}") //默认只要单线程写入小图
	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}

	@Override
	protected int getThreadNum() {
		return threadNum;
	}

	@Override
	protected int getQueueSize() {
		return QUEUE_SIZE;
	}
}
