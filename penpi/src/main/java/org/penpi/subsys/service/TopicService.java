package org.penpi.subsys.service;

import java.util.Date;

import org.penpi.core.commons.base.BaseService;
import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.subsys.entity.Topic;
import org.penpi.subsys.repository.TopicRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicService extends BaseService<Topic, Integer, TopicRepository> {

	@Transactional
	public Topic createTopic(Topic topic) {
		topic.setCreateDate(new Date());
		SpringContextHelper.getBean(TopicService.class).save(topic);
		SpringContextHelper.getBean(FileInfService.class).syncBusinessObject(topic.getTopicId(), topic, null, Topic.class);
		return topic;
	}
	
	@Transactional
	public void modifyTopic(Integer topicId,Integer topicCategory) {
		Topic topic = SpringContextHelper.getBean(TopicSearchService.class).getById(topicId);
		topic.setTopicCategoryId(topicCategory);
		SpringContextHelper.getBean(TopicService.class).save(topic);
	}

}
