package org.penpi.app.controller;

import java.util.List;

import org.penpi.app.dto.BigImage;
import org.penpi.app.dto.SearchResult;
import org.penpi.app.dto.TopicDetail;
import org.penpi.app.dto.TopicEx;
import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.subsys.ControllerMapping;
import org.penpi.subsys.entity.Topic;
import org.penpi.subsys.entity.TopicCategory;
import org.penpi.subsys.entity.TopicIdentity;
import org.penpi.subsys.service.TopicCategorySearchService;
import org.penpi.subsys.service.TopicIdentityService;
import org.penpi.subsys.service.TopicSearchService;
import org.penpi.subsys.service.TopicService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerMapping.APP_TOPIC)
public class MemberTopicController {
	
	/**
	 * 获取话题分类
	 */
	@RequestMapping(value = ControllerMapping.GET_ALL_TOPIC_CATEGORYS)
	public List<TopicCategory> getAllTopicCategorys() {
		return SpringContextHelper.getBean(TopicCategorySearchService.class).getAllTopicCategorys();
	}
	
	/**
	 * 获取分类下的话题
	 */
	@RequestMapping(value = ControllerMapping.GET_TOPICS_BY_CATEGORYID)
	public List<TopicEx> getTopicsByCategoryId(Integer categoryId) {
		return SpringContextHelper.getBean(TopicSearchService.class).getTopicsByCategoryId(categoryId);
	}
	
	/**
	 * 获取话题下的帖子
	 */
	@RequestMapping(value = ControllerMapping.GET_TOPIC_DETAIL)
	public TopicDetail getTopicDetail(Integer userId, Integer topicId) {
		return SpringContextHelper.getBean(TopicSearchService.class).getTopicDetail(userId, topicId);
	}
	
	/**
	 * 获取话题的大图
	 */
	@RequestMapping(value = ControllerMapping.GET_TOPIC_BIG_IMAGE)
	public BigImage getTopicBigImage(Integer topicId) {
		return SpringContextHelper.getBean(TopicSearchService.class).getTopicBigImage(topicId);
	}
	
	/**
	 * 创建话题
	 */
	@RequestMapping(value = ControllerMapping.CREATE_TOPIC, method = RequestMethod.POST)
	public Topic createTopic(@RequestBody Topic topic) {
		return SpringContextHelper.getBean(TopicService.class).createTopic(topic);
	}
	
	/**
	 * 搜索话题/帖子/用户
	 */
	@RequestMapping(value = ControllerMapping.SEARCH)
	public SearchResult search(String type, String param) {
		return SpringContextHelper.getBean(TopicSearchService.class).search(type, param);
	}
	
	/**
	 * 话题关注
	 */
	@RequestMapping(value = ControllerMapping.TOPIC_FOLLOWER)
	public TopicIdentity follower(Integer userId, Integer topicId, Boolean ifFollow) {
		return SpringContextHelper.getBean(TopicIdentityService.class).follower(userId, topicId, ifFollow);
	}
}
