package org.penpi.subsys.search;

import org.penpi.subsys.entity.Topic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TopicSearchRepository extends ElasticsearchRepository<Topic, Integer>{
	 
}
