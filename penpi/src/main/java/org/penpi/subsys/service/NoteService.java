package org.penpi.subsys.service;

import java.util.Date;
import java.util.List;

import org.penpi.app.dto.CreateNote;
import org.penpi.core.commons.base.BaseService;
import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.subsys.code.NoteStatCodeEnum;
import org.penpi.subsys.entity.Note;
import org.penpi.subsys.entity.NoteFile;
import org.penpi.subsys.entity.NoticeMsg;
import org.penpi.subsys.entity.User;
import org.penpi.subsys.repository.NoteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoteService extends BaseService<Note, Integer, NoteRepository> {

	@Transactional
	public Note createNote(CreateNote createNote) {
		Note note = new Note();
		BeanUtils.copyProperties(createNote, note);
		User user = SpringContextHelper.getBean(UserSearchService.class).getById(note.getSendNoteUserId());
		note.setSendNoteUserNm(user.getUserNm());
		note.setNoteStatCode(NoteStatCodeEnum.NORMAL.toCode());
		Date now = new Date();
		note.setCreateDate(now);
		note.setLastUpdTime(now);
		SpringContextHelper.getBean(this.getClass()).save(note);
		
		List<NoteFile> noteFiles = createNote.getNoteFiles();
		if (noteFiles.size() > 0) {
			NoteFileService noteFileService = SpringContextHelper.getBean(NoteFileService.class);
			FileInfService fileInfService = SpringContextHelper.getBean(FileInfService.class);
			for (NoteFile noteFile : noteFiles) {
				noteFile.setNoteId(note.getNoteId());
				noteFileService.save(noteFile);
				fileInfService.syncBusinessObject(noteFile.getNoteFileId(), noteFile, null, NoteFile.class);
				noteFileService.save(noteFile);
			}
		}
		return note;
	}
	@Transactional
	public void deleteNode(Integer noteId,String deleteReason) {
		Note note = SpringContextHelper.getBean(NoteSearchService.class).getById(noteId);
 
		SpringContextHelper.getBean(NoteService.class).delete(note);
		NoticeMsg noticeMsg = new NoticeMsg();
		noticeMsg.setReceiverUserId(note.getSendNoteUserId());
		noticeMsg.setMsgCont(deleteReason);
		noticeMsg.setSysNoticeTypeCode("0");
		noticeMsg.setMsgTime(new Date());
		SpringContextHelper.getBean(NoticeMsgService.class).save(noticeMsg);
	}

}
