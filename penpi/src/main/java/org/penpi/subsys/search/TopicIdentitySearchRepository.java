package org.penpi.subsys.search;

import java.util.List;

import org.penpi.subsys.entity.TopicIdentity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TopicIdentitySearchRepository extends ElasticsearchRepository<TopicIdentity, Integer> {

	long countByTopicId(Integer topicId);
	
	List<TopicIdentity> findByUserIdAndTopicId(Integer userId, Integer topicId);
}
