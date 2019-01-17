package org.penpi.subsys.search;

import org.penpi.subsys.entity.Note;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface NoteSearchRepository extends ElasticsearchRepository<Note, Integer>{
	 
}
