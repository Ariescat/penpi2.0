package org.penpi.subsys.search;

import org.penpi.subsys.entity.FileInf;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface FileInfSearchRepository extends ElasticsearchRepository<FileInf, Integer>{
	 
}
