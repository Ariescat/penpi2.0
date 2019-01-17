package org.penpi.subsys.service;

import java.util.Date;

import org.penpi.app.dto.NoteCommentReplyEx;
import org.penpi.core.commons.base.BaseService;
import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.subsys.Global;
import org.penpi.subsys.entity.NoteCommentReply;
import org.penpi.subsys.entity.User;
import org.penpi.subsys.repository.NoteCommentReplyRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoteCommentReplyService extends BaseService<NoteCommentReply, Integer, NoteCommentReplyRepository> {

	@Transactional
	public NoteCommentReplyEx reply(NoteCommentReply noteCommentReply) {
		noteCommentReply.setCreateDate(new Date());
		SpringContextHelper.getBean(this.getClass()).save(noteCommentReply);
		
		NoteCommentReplyEx noteCommentReplyEx = new NoteCommentReplyEx();
		BeanUtils.copyProperties(noteCommentReply, noteCommentReplyEx);
		UserSearchService userSearchService = SpringContextHelper.getBean(UserSearchService.class);
		FileInfService fileInfService = SpringContextHelper.getBean(FileInfService.class);
		
		User commontUser = userSearchService.getById(noteCommentReplyEx.getCommentUserId());
		noteCommentReplyEx.setCommentUserNm(commontUser.getUserNm());
		noteCommentReplyEx.setCommentUserHeadPictStr(fileInfService.getSmallPictUrl(commontUser.getUserHeadPictId(), Global.DEFAULT_SMALL_PICT_SIZE, Global.DEFAULT_SMALL_PICT_SIZE));
		User atUser = userSearchService.getById(noteCommentReplyEx.getAtUserId());
		if (atUser != null)
			noteCommentReplyEx.setAtUserNm(atUser.getUserNm());
		
		return noteCommentReplyEx;
	}
}
