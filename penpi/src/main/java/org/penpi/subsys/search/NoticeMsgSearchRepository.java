package org.penpi.subsys.search;

import org.penpi.subsys.entity.NoticeMsg;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface NoticeMsgSearchRepository extends ElasticsearchRepository<NoticeMsg, Integer>{

	Iterable<NoticeMsg> findBySysNoticeTypeCode(String sysNoticeTypeCode);
}
