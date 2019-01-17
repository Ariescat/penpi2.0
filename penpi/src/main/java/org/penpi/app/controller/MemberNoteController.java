package org.penpi.app.controller;

import java.util.List;

import org.penpi.app.dto.BigImage;
import org.penpi.app.dto.CreateNote;
import org.penpi.app.dto.NoteCommentReplyEx;
import org.penpi.app.dto.NoteDetail;
import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.subsys.ControllerMapping;
import org.penpi.subsys.entity.Note;
import org.penpi.subsys.entity.NoteComment;
import org.penpi.subsys.entity.NoteCommentReply;
import org.penpi.subsys.service.NoteCommentReplyService;
import org.penpi.subsys.service.NoteCommentService;
import org.penpi.subsys.service.NoteSearchService;
import org.penpi.subsys.service.NoteService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerMapping.APP_NOTE)
public class MemberNoteController {
	
	/**
	 * 获取帖子下的评论等
	 */
	@RequestMapping(value = ControllerMapping.GET_NOTE_DETAIL)
	public NoteDetail getNoteDetail(Integer noteId) {
		return SpringContextHelper.getBean(NoteSearchService.class).getNoteDetail(noteId);
	}
	
	/**
	 * 获取帖子的大图
	 */
	@RequestMapping(value = ControllerMapping.GET_NOTE_BIG_IMAGE)
	public List<BigImage> getNoteBigImage(Integer noteId) {
		return SpringContextHelper.getBean(NoteSearchService.class).getNoteBigImage(noteId);
	}
	
	/**
	 * 发表帖子
	 */
	@RequestMapping(value = ControllerMapping.CREATE_NOTE, method = RequestMethod.POST)
	public Note createNote(@RequestBody CreateNote createNote) {
		return SpringContextHelper.getBean(NoteService.class).createNote(createNote);
	}
	
	/**
	 * 评论帖子
	 */
	@RequestMapping(value = ControllerMapping.COMMENT, method = RequestMethod.POST)
	public NoteComment comment(@RequestBody NoteComment noteComment) {
		return SpringContextHelper.getBean(NoteCommentService.class).comment(noteComment);
	}
	
	/**
	 * 评论的回复
	 */
	@RequestMapping(value = ControllerMapping.REPLY, method = RequestMethod.POST)
	public NoteCommentReplyEx reply(@RequestBody NoteCommentReply noteCommentReply) {
		return SpringContextHelper.getBean(NoteCommentReplyService.class).reply(noteCommentReply);
	}
	
	/**
	 * 2018.5.24
	 */
	@RequestMapping(value = ControllerMapping.QUERY_NOTE_BY_ID)
	public List<Note> queryNoteById(Integer userId) {
		return SpringContextHelper.getBean(NoteSearchService.class).findByKey(Note.SEND_NOTE_USER_ID, userId);
	}
}
