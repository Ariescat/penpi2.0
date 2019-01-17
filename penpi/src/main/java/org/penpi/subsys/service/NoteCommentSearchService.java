package org.penpi.subsys.service;

import org.penpi.core.commons.base.BaseSearchService;
import org.penpi.subsys.entity.NoteComment;
import org.penpi.subsys.search.NoteCommentSearchRepository;
import org.springframework.stereotype.Service;

@Service
public class NoteCommentSearchService extends BaseSearchService<NoteComment, Integer, NoteCommentSearchRepository> {

	public int getCommentAmountByNoteId(Integer noteId) {
		return (int) this.getRepository().countByNoteId(noteId);
	}
}
