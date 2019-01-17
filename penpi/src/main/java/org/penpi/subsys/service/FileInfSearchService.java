package org.penpi.subsys.service;

import org.penpi.core.commons.base.BaseSearchService;
import org.penpi.subsys.entity.FileInf;
import org.penpi.subsys.search.FileInfSearchRepository;
import org.springframework.stereotype.Service;

@Service
public class FileInfSearchService extends BaseSearchService<FileInf, Integer, FileInfSearchRepository> {
	
}
