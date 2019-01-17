package org.penpi.subsys.service;

import java.util.List;

import org.penpi.core.commons.base.BaseSearchService;
import org.penpi.subsys.entity.TopicCategory;
import org.penpi.subsys.search.TopicCategorySearchRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

@Service
public class TopicCategorySearchService extends BaseSearchService<TopicCategory, Integer, TopicCategorySearchRepository> {

	@Transactional(readOnly = true)
	public List<TopicCategory> getAllTopicCategorys() {
		Iterable<TopicCategory> iterable = this.getRepository()
				.findAll(new Sort(new Order(Direction.ASC, TopicCategory.TOPIC_CATEGORY_ID)));
		List<TopicCategory> list = Lists.newArrayList();
		iterable.forEach(item -> {
			list.add(item);
		});
		return list;
	}
}
