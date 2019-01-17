package org.penpi.subsys.service;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.penpi.core.commons.base.BaseSearchService;
import org.penpi.core.utils.PageBean;
import org.penpi.subsys.entity.OperateRecord;
import org.penpi.subsys.search.OperateRecordSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
@Service
public class OperateRecordSearchService extends BaseSearchService<OperateRecord, Integer, OperateRecordSearchRepository>{

	private final Integer size = 6;//设置每页显示的数据数 
	private final Order order = new Order(Direction.DESC,"opId"); //设置排序方式  
	private final Sort sort=new Sort(order);
	
	@Autowired  
    private ElasticsearchTemplate elasticsearchTemplate;
	
	@Transactional(readOnly = true)
	public PageBean<OperateRecord> findUserOpLogByPage(Integer pageNum) {
		 BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		 PageRequest pageable=new PageRequest(pageNum-1, size, sort);         
		 
		 queryBuilder.must(QueryBuilders.matchQuery("opType", "0"));       
		 SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).withPageable(pageable).build();
		 AggregatedPage<OperateRecord> ors = elasticsearchTemplate.queryForPage(searchQuery, OperateRecord.class);  
		 
		 PageBean<OperateRecord> userOrsPageBean = new PageBean<OperateRecord>(pageNum,size, ors.getTotalPages());  
		 userOrsPageBean.setData(ors.getContent());
		 return userOrsPageBean;
	}
	
	@Transactional(readOnly = true)
	public PageBean<OperateRecord> findOpUserLogByOper(String userLogOper,Integer pageNum) {
		PageBean<OperateRecord> userOrsPageBean;
		if(userLogOper == null ||userLogOper.trim().equals("")) {
			userOrsPageBean = this.findUserOpLogByPage(pageNum);
		}
		else {
			BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
			 PageRequest pageable=new PageRequest(pageNum-1, size, sort);         
			 
			 queryBuilder.must(QueryBuilders.matchQuery("opType", "0"));  
			 queryBuilder.must(QueryBuilders.matchPhraseQuery("opUserLoginId",userLogOper).operator(Operator.AND));		   
			 SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).withPageable(pageable).build();
			 AggregatedPage<OperateRecord> ors = elasticsearchTemplate.queryForPage(searchQuery, OperateRecord.class);  
			 
			 userOrsPageBean = new PageBean<OperateRecord>(pageNum,size, ors.getTotalPages());  
			 userOrsPageBean.setData(ors.getContent());			
		}
		 return userOrsPageBean;
	}
}
