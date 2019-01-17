package org.penpi.subsys.service;

import org.penpi.core.commons.base.BaseSearchService;
import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.subsys.entity.NoticeMsg;
import org.penpi.subsys.search.NoticeMsgSearchRepository;
import org.springframework.stereotype.Service;

@Service
public class NoticeMsgSearchService extends BaseSearchService<NoticeMsg, Integer, NoticeMsgSearchRepository>{

	public Iterable<NoticeMsg> findByMsgType(String messageType){
		Iterable<NoticeMsg> noticeMsgInterable;
		if(messageType.equals("-1")) {
			noticeMsgInterable = SpringContextHelper.getBean(this.getClass()).getRepository().findAll();
		}else{
			noticeMsgInterable = SpringContextHelper.getBean(this.getClass()).getRepository().findBySysNoticeTypeCode(messageType);
			
		}
		 
		return noticeMsgInterable;
	}
}
