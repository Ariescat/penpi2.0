package org.penpi.subsys.search;

import org.penpi.subsys.entity.SmallPictSetup;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SmallPictSetupSearchRepository extends ElasticsearchRepository<SmallPictSetup, Integer>{
	 
}
