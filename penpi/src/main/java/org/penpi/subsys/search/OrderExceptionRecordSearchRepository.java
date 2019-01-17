package org.penpi.subsys.search;

import java.util.List;

import org.penpi.subsys.entity.OrderExceptionRecord;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface OrderExceptionRecordSearchRepository extends ElasticsearchRepository<OrderExceptionRecord, Integer>{

	OrderExceptionRecord findByOrderIdAndIfFixException(Integer orderId,String ifFixException);
	
	List<OrderExceptionRecord> findByIfFixException(String ifFixException);
}
