package org.penpi.subsys.search;

import org.penpi.subsys.entity.OperateRecord;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface OperateRecordSearchRepository extends ElasticsearchRepository<OperateRecord, Integer>{

}
