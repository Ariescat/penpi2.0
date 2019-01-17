package org.penpi.subsys.service;

import java.util.List;

import org.penpi.core.commons.base.BaseSearchService;
import org.penpi.subsys.entity.TopicIdentity;
import org.penpi.subsys.search.TopicIdentitySearchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicIdentitySearchService extends BaseSearchService<TopicIdentity, Integer, TopicIdentitySearchRepository> {

	@Transactional(readOnly = true)
	public Integer countFollowerAmount(Integer topicId) {
		return (int) this.getRepository().countByTopicId(topicId);
	}
	
	@Transactional(readOnly = true)
	public TopicIdentity getByUserIdAndTopicId(Integer userId, Integer topicId) {
		List<TopicIdentity> list = this.getRepository().findByUserIdAndTopicId(userId, topicId);
		return list.isEmpty() ? null : list.get(0);
	}
}
