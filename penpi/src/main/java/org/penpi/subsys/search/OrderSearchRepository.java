package org.penpi.subsys.search;

import java.util.List;

import org.penpi.subsys.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface OrderSearchRepository extends ElasticsearchRepository<Order, Integer>{

	public Page<Order> findByOrderStatCode(String orderType,Pageable pageable);

	public Page<Order> findByOrderStatCodeAndSendUserMobile(String orderType, String sendUserMobile,Pageable pageable);
	
	public List<Order> findByOrderStatCode(String orderType);
}
