package org.penpi.subsys.service;

import java.util.List;

import org.penpi.core.commons.base.BaseService;
import org.penpi.subsys.entity.SmallPictSetup;
import org.penpi.subsys.repository.SmallPictSetupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SmallPictSetupService extends BaseService<SmallPictSetup, Integer, SmallPictSetupRepository> {

	@Transactional(readOnly=true)
	public List<SmallPictSetup> findByBusinessClassNmAndBusinessFieldNm(String businessClassNm, String fusinessFieldNm){
		return this.getRepository().findByBusinessClassNmAndBusinessFieldNm(businessClassNm, fusinessFieldNm);
	}

}
