package org.penpi.subsys.service;

import org.penpi.core.commons.base.BaseSearchService;
import org.penpi.subsys.entity.NoteFile;
import org.penpi.subsys.search.NoteFileSearchRepository;
import org.springframework.stereotype.Service;

@Service
public class NoteFileSearchService extends BaseSearchService<NoteFile, Integer, NoteFileSearchRepository> {

}
