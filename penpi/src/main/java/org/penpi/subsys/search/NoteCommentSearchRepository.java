package org.penpi.subsys.search;

import org.penpi.subsys.entity.NoteComment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface NoteCommentSearchRepository extends ElasticsearchRepository<NoteComment, Integer>{
	 
	long countByNoteId(Integer noteId);
}
