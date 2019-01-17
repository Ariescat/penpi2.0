package org.penpi.subsys.search;

import org.penpi.subsys.entity.NoteFile;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface NoteFileSearchRepository extends ElasticsearchRepository<NoteFile, Integer>{
	 
}
