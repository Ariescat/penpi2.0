package org.penpi.subsys.search;

import org.penpi.subsys.entity.NoteCommentReply;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface NoteCommentReplySearchRepository extends ElasticsearchRepository<NoteCommentReply, Integer>{
	 
}
