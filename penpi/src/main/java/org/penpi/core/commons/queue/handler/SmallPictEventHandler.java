package org.penpi.core.commons.queue.handler;

import java.io.File;
import java.util.Date;

import org.penpi.core.commons.file.image.SmallPictUtil;
import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.core.commons.queue.data.SmallPictEventData;
import org.penpi.core.commons.queue.event.SmallPictEvent;
import org.penpi.core.commons.web.WebContextHolder;
import org.penpi.subsys.entity.SmallPict;
import org.penpi.subsys.service.SmallPictService;

import com.lmax.disruptor.WorkHandler;

/**
 * 小图队列消息图片处理器.
 * 
 * @author JIM
 *
 */
public class SmallPictEventHandler implements WorkHandler<SmallPictEvent> {

	@Override
	public void onEvent(SmallPictEvent event) throws Exception {
		try {
			SmallPictEventData smallPictEventData = event.getValue();
			int size = SmallPictUtil.generateSmallPict(smallPictEventData.getWidth(), smallPictEventData.getHeight(),
					WebContextHolder.getWarPath() + File.separator + smallPictEventData.getFilePath(),
					smallPictEventData.isInnerCut());
			if (size > -1) {
				SmallPict smallPict = new SmallPict();
				smallPict.setFileInfId(smallPictEventData.getFileInfId());
				smallPict.setSmallPictSetupId(smallPictEventData.getSmallPictSetupId());
				smallPict.setSmallPictWidth(smallPictEventData.getWidth());
				smallPict.setSmallPictHeight(smallPictEventData.getHeight());
				smallPict.setFileSizeKb(size);
				smallPict.setFileTime(new Date());
				SpringContextHelper.getBean(SmallPictService.class).save(smallPict);
			}
		} catch (Throwable tr) {
			tr.printStackTrace();
		}
	}
}
