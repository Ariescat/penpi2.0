package org.penpi.subsys.service;

import java.util.ArrayList;
import java.util.List;

import org.penpi.app.dto.BigImage;
import org.penpi.app.dto.NoteCommentEx;
import org.penpi.app.dto.NoteCommentReplyEx;
import org.penpi.app.dto.NoteDetail;
import org.penpi.core.commons.base.BaseSearchService;
import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.subsys.Global;
import org.penpi.subsys.entity.Note;
import org.penpi.subsys.entity.NoteComment;
import org.penpi.subsys.entity.NoteCommentReply;
import org.penpi.subsys.entity.NoteFile;
import org.penpi.subsys.entity.User;
import org.penpi.subsys.search.NoteSearchRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoteSearchService extends BaseSearchService<Note, Integer, NoteSearchRepository> {

	@Transactional(readOnly = true)
	public NoteDetail getNoteDetail(Integer noteId) {
		
		NoteDetail noteDetail = new NoteDetail();
		UserSearchService userSearchService = SpringContextHelper.getBean(UserSearchService.class);
		FileInfService fileInfService = SpringContextHelper.getBean(FileInfService.class);
		 
		//查询出所有评论
		List<NoteComment> noteComments = SpringContextHelper.getBean(NoteCommentSearchService.class).findByKey(NoteComment.NOTE_ID, noteId, new Order(Direction.ASC, NoteComment.NOTE_COMMENT_ID));
		for (NoteComment noteComment : noteComments) {
			NoteCommentEx noteCommentEx = new NoteCommentEx();
			BeanUtils.copyProperties(noteComment, noteCommentEx);
			User user = userSearchService.getById(noteCommentEx.getCommentUserId());
			noteCommentEx.setUserNm(user.getUserNm());
			noteCommentEx.setUserHeadPictStr(fileInfService.getSmallPictUrl(user.getUserHeadPictId(), Global.DEFAULT_SMALL_PICT_SIZE, Global.DEFAULT_SMALL_PICT_SIZE));
			
			// 查找出评论下的回复
			List<NoteCommentReply> noteCommentReplies = SpringContextHelper.getBean(NoteCommentReplySearchService.class).findByKey(NoteCommentReply.NOTE_COMMENT_ID, noteComment.getNoteCommentId(), new Order(Direction.ASC, NoteCommentReply.NOTE_COMMENT_REPLY_ID));
			if (noteCommentReplies.size() > 0) {
				for (NoteCommentReply noteCommentReply : noteCommentReplies) {
					NoteCommentReplyEx noteCommentReplyEx = new NoteCommentReplyEx();
					BeanUtils.copyProperties(noteCommentReply, noteCommentReplyEx);
					User commontUser = userSearchService.getById(noteCommentReplyEx.getCommentUserId());
					noteCommentReplyEx.setCommentUserNm(commontUser.getUserNm());
					noteCommentReplyEx.setCommentUserHeadPictStr(fileInfService.getSmallPictUrl(commontUser.getUserHeadPictId(), Global.DEFAULT_SMALL_PICT_SIZE, Global.DEFAULT_SMALL_PICT_SIZE));
					
					User atUser = userSearchService.getById(noteCommentReplyEx.getAtUserId());
					if (atUser != null)
						noteCommentReplyEx.setAtUserNm(atUser.getUserNm());

					noteCommentEx.getCommentReplies().add(noteCommentReplyEx);
				}
			}
			noteDetail.getNoteCommentExs().add(noteCommentEx);
		}
		return noteDetail;
	}

	@Transactional(readOnly = true)
	public List<BigImage> getNoteBigImage(Integer noteId) {
		List<NoteFile> noteFiles = SpringContextHelper.getBean(NoteFileSearchService.class).findByKey(NoteFile.NOTE_ID, noteId);
		List<BigImage> bigImages = new ArrayList<>();
		for (NoteFile noteFile : noteFiles) {
			String pictUrl = SpringContextHelper.getBean(FileInfService.class).getPictUrl(noteFile.getFileInfId());
			bigImages.add(new BigImage(pictUrl));
		}
		return bigImages;
	}

}
