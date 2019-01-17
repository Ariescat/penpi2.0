package org.penpi.subsys.service;

import org.penpi.core.commons.base.BaseService;
import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.subsys.entity.OperateRecord;
import org.penpi.subsys.repository.OperateRecordRepository;
import org.springframework.stereotype.Service;
 
@Service
public class OperateRecordService extends BaseService<OperateRecord, Integer, OperateRecordRepository>{

	public void saveOr(OperateRecord or) {
		SpringContextHelper.getBean(this.getClass()).save(or);
	}
}
