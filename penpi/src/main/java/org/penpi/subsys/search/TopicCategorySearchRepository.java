package org.penpi.subsys.search;

import org.penpi.subsys.entity.TopicCategory;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TopicCategorySearchRepository extends ElasticsearchRepository<TopicCategory, Integer>{
	 
}
