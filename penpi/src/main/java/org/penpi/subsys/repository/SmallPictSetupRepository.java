package org.penpi.subsys.repository;

import java.util.List;

import org.penpi.core.commons.base.BaseRepository;
import org.penpi.subsys.entity.SmallPictSetup;

public interface SmallPictSetupRepository extends BaseRepository<SmallPictSetup, Integer> {

	List<SmallPictSetup> findByBusinessClassNmAndBusinessFieldNm(String businessClassNm, String fusinessFieldNm);

}
