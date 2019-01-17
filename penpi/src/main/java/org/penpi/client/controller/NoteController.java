package org.penpi.client.controller;

import javax.servlet.http.HttpSession;

import org.penpi.app.dto.NoteDetail;
import org.penpi.app.dto.TopicDetail;
import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.subsys.ControllerMapping;
import org.penpi.subsys.service.NoteCommentService;
import org.penpi.subsys.service.NoteSearchService;
import org.penpi.subsys.service.NoteService;
import org.penpi.subsys.service.TopicSearchService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController("adminNoteController")
@RequestMapping(ControllerMapping.ADMIN_NOTE)   /* /admin/note */
public class NoteController {
	
	@RequestMapping(ControllerMapping.ADMIN_NOTE_DETAIL)
	public ModelAndView noteDetail(String noteId,HttpSession session) {
		NoteDetail noteDetail = SpringContextHelper.getBean(NoteSearchService.class).getNoteDetail(Integer.valueOf(noteId));
		session.setAttribute("noteDetail", noteDetail); 
		session.setAttribute("noteId", noteId);
		return new ModelAndView("forward:/WEB-INF/pages/noteDetail.jsp");
	}
	@RequestMapping(ControllerMapping.ADMIN_NOTE_DELETE)
	public ModelAndView deleteNote(Integer noteId,String deleteReason,HttpSession session) {
		SpringContextHelper.getBean(NoteService.class).deleteNode(noteId,deleteReason);
		TopicDetail bfTopicDetail = (TopicDetail) session.getAttribute("topicDetail");
		TopicDetail topicDetail = SpringContextHelper.getBean(TopicSearchService.class).getTopicDetail(50001, bfTopicDetail.getTopicId());
		session.setAttribute("topicDetail", topicDetail);	 
		return new ModelAndView("forward:/WEB-INF/pages/topicDetail.jsp");
	}
	
	@RequestMapping(ControllerMapping.ADMIN_NOTE_COMMENT_DELETE)
	public ModelAndView deleteNoteComment(Integer noteCommentId,String deleteReason,HttpSession session) {
		SpringContextHelper.getBean(NoteCommentService.class).deleteNodeComment(noteCommentId,deleteReason);	
		String noteId =  (String) session.getAttribute("noteId");
		NoteDetail noteDetail = SpringContextHelper.getBean(NoteSearchService.class).getNoteDetail(Integer.valueOf(noteId));
		session.setAttribute("noteDetail", noteDetail); 
		return new ModelAndView("forward:/WEB-INF/pages/noteDetail.jsp");
	}
	@RequestMapping(ControllerMapping.ADMIN_FIND_NOTE_BY_KEY_WORD)
	public ModelAndView findNoteByKeyWord(String noteKeyWord,HttpSession session) {
		 
		TopicDetail topicDetailBf = (TopicDetail) session.getAttribute("topicDetail");
		TopicDetail topicDetail = SpringContextHelper.getBean(TopicSearchService.class).getTopicDetailByNoteKeyWord(topicDetailBf.getTopicId(),noteKeyWord);
		session.setAttribute("topicDetail", topicDetail);
		 session.setAttribute("noteKeyWord", noteKeyWord);
		return new ModelAndView("forward:/WEB-INF/pages/topicDetail.jsp");
	
	}
	
}
