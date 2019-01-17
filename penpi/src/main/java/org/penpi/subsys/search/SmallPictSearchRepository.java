package org.penpi.subsys.search;

import org.penpi.subsys.entity.SmallPict;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SmallPictSearchRepository extends ElasticsearchRepository<SmallPict, Integer>{
	 
}
