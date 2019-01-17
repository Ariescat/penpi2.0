package org.penpi.subsys.service;

import java.util.Date;

import org.penpi.core.commons.base.BaseService;
import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.subsys.entity.NoticeMsg;
import org.penpi.subsys.repository.NoticeMsgRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoticeMsgService extends BaseService<NoticeMsg, Integer, NoticeMsgRepository>{

	
	@Transactional
	public void msgPublish(String msgCont) {
		NoticeMsg noticeMsg = new NoticeMsg();
		noticeMsg.setMsgCont(msgCont);
		noticeMsg.setSysNoticeTypeCode("1");
		noticeMsg.setMsgTime(new Date());
		SpringContextHelper.getBean(this.getClass()).save(noticeMsg);
	}
}
