package org.penpi.subsys.service;

import java.util.Date;

import org.penpi.core.commons.base.BaseService;
import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.subsys.entity.NoteComment;
import org.penpi.subsys.entity.NoticeMsg;
import org.penpi.subsys.repository.NoteCommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoteCommentService extends BaseService<NoteComment, Integer, NoteCommentRepository> {
	
	@Transactional
	public NoteComment comment(NoteComment noteComment) {
		noteComment.setCreateDate(new Date());
		SpringContextHelper.getBean(this.getClass()).save(noteComment);
		return noteComment;
	}
	
 
	@Transactional
	public void deleteNodeComment(Integer noteCommentId,String deleteReason) {
		NoteComment noteComment = SpringContextHelper.getBean(NoteCommentSearchService.class).getById(noteCommentId);
 
		SpringContextHelper.getBean(NoteCommentService.class).delete(noteComment);
		NoticeMsg noticeMsg = new NoticeMsg();
		noticeMsg.setReceiverUserId(noteComment.getCommentUserId());
		noticeMsg.setMsgCont(deleteReason);
		noticeMsg.setSysNoticeTypeCode("0");
		noticeMsg.setMsgTime(new Date());
		SpringContextHelper.getBean(NoticeMsgService.class).save(noticeMsg);
	}
}
