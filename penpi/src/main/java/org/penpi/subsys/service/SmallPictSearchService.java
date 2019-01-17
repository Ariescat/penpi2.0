package org.penpi.subsys.service;

import org.penpi.core.commons.base.BaseSearchService;
import org.penpi.subsys.entity.SmallPict;
import org.penpi.subsys.search.SmallPictSearchRepository;
import org.springframework.stereotype.Service;

@Service
public class SmallPictSearchService extends BaseSearchService<SmallPict, Integer, SmallPictSearchRepository> {

}
