package org.penpi.subsys.service;

import org.penpi.core.commons.base.BaseSearchService;
import org.penpi.subsys.entity.NoteCommentReply;
import org.penpi.subsys.search.NoteCommentReplySearchRepository;
import org.springframework.stereotype.Service;

@Service
public class NoteCommentReplySearchService extends BaseSearchService<NoteCommentReply, Integer, NoteCommentReplySearchRepository> {

}
