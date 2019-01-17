package org.penpi.subsys.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.penpi.app.dto.BigImage;
import org.penpi.app.dto.NoteEx;
import org.penpi.app.dto.SearchResult;
import org.penpi.app.dto.TopicDetail;
import org.penpi.app.dto.TopicEx;
import org.penpi.app.dto.UserEx;
import org.penpi.core.commons.base.BaseSearchService;
import org.penpi.core.commons.exception.BusinessException;
import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.core.commons.web.ClientUser;
import org.penpi.core.commons.web.WebContextHolder;
import org.penpi.core.utils.PageBean;
import org.penpi.subsys.Global;
import org.penpi.subsys.ResGlobal;
import org.penpi.subsys.code.BoolCodeEnum;
import org.penpi.subsys.code.SearchCodeEnum;
import org.penpi.subsys.entity.Note;
import org.penpi.subsys.entity.NoteFile;
import org.penpi.subsys.entity.Topic;
import org.penpi.subsys.entity.TopicIdentity;
import org.penpi.subsys.entity.User;
import org.penpi.subsys.search.TopicSearchRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicSearchService extends BaseSearchService<Topic, Integer, TopicSearchRepository> {

	
	@Autowired  
    private ElasticsearchTemplate elasticsearchTemplate;
	
	@Transactional(readOnly = true)
	public List<TopicEx> getTopicsByCategoryId(Integer categoryId) {
		List<Topic> list = SpringContextHelper.getBean(this.getClass()).findByKey(Topic.TOPIC_CATEGORY_ID, categoryId);
		List<TopicEx> retList = new ArrayList<>();
		for (Topic item : list) {
			TopicEx topicItem = new TopicEx();
			BeanUtils.copyProperties(item, topicItem);
			topicItem.setTopicHeadPictStr(SpringContextHelper.getBean(FileInfService.class)
					.getSmallPictUrl(topicItem.getTopicHeadPictId(), Global.DEFAULT_SMALL_PICT_SIZE, Global.DEFAULT_SMALL_PICT_SIZE));
			retList.add(topicItem);
		}
		return retList;
	}
	
	@Transactional(readOnly = true)
	public TopicDetail getTopicDetail(Integer userId, Integer topicId) {
		
//		ClientUser clientUser = WebContextHolder.getSessionContextStore().getServerValue(Global.SESSION_LOGIN_MEMBER);
//		if (clientUser == null || !userId.equals(clientUser.getUserId())) {
//			throw new BusinessException(ResGlobal.ERRORS_USER_DEFINED, ResGlobal.LOGIG_ILLEGAL);
//		}
		
		TopicDetail topicDetail = new TopicDetail();
		Topic topic = SpringContextHelper.getBean(this.getClass()).getById(topicId);
		BeanUtils.copyProperties(topic, topicDetail);
		
		topicDetail.setIfFollow(SpringContextHelper.getBean(TopicIdentityService.class).checkifFollow(userId, topicId));
		topicDetail.setTopicHeadPictStr(SpringContextHelper.getBean(FileInfService.class).getSmallPictUrl(topic.getTopicHeadPictId(), Global.DEFAULT_SMALL_PICT_SIZE, Global.DEFAULT_SMALL_PICT_SIZE));
		
		topicDetail.setFollowerAmount(SpringContextHelper.getBean(TopicIdentitySearchService.class).countFollowerAmount(topicId));
		
		List<TopicDetail.Follower> followers = new ArrayList<>();
		List<TopicIdentity> identities = SpringContextHelper.getBean(TopicIdentitySearchService.class).findByKey(TopicIdentity.TOPIC_ID, topicId);
		for (TopicIdentity item : identities) {
			if (item.getIfFreeze().equals(BoolCodeEnum.NO.toCode())) {
				int followerUserId = item.getUserId();
				User user = SpringContextHelper.getBean(UserSearchService.class).getById(followerUserId);
				String userHeadPictStr = SpringContextHelper.getBean(FileInfService.class)
						.getSmallPictUrl(user.getUserHeadPictId(), Global.DEFAULT_SMALL_PICT_SIZE, Global.DEFAULT_SMALL_PICT_SIZE);
				TopicDetail.Follower follower = topicDetail.new Follower(userId, user.getUserNm(), user.getUserHeadPictId(), userHeadPictStr);
				followers.add(follower);
			}
		}
		topicDetail.setFollowers(followers);
		
		List<NoteEx> noteItems = new ArrayList<>();
		List<Note> notes = SpringContextHelper.getBean(NoteSearchService.class).findByKey(Note.TOPIC_ID, topicId, new org.springframework.data.domain.Sort.Order(Direction.DESC, Note.LAST_UPD_TIME));
		for (Note note : notes) {
			NoteEx noteItem = new NoteEx();
			BeanUtils.copyProperties(note, noteItem);
			List<NoteFile> noteFiles = SpringContextHelper.getBean(NoteFileSearchService.class).findByKey(NoteFile.NOTE_ID, note.getNoteId());
			for (NoteFile noteFile : noteFiles) {
				String smallPictUrl = SpringContextHelper.getBean(FileInfService.class).getSmallPictUrl(
						noteFile.getFileInfId(), Global.DEFAULT_SMALL_PICT_SIZE, Global.DEFAULT_SMALL_PICT_SIZE);
				NoteEx.NoteFileEx noteFileExt = noteItem.new NoteFileEx(noteFile.getFileInfId(), smallPictUrl);
				noteItem.getNoteFiles().add(noteFileExt);
			}
			User sendUser = SpringContextHelper.getBean(UserSearchService.class).getById(noteItem.getSendNoteUserId());
			noteItem.setUserHeadPictStr(SpringContextHelper.getBean(FileInfService.class).getSmallPictUrl(
					sendUser.getUserHeadPictId(), Global.DEFAULT_SMALL_PICT_SIZE, Global.DEFAULT_SMALL_PICT_SIZE));
			noteItem.setCommentAmount(SpringContextHelper.getBean(NoteCommentSearchService.class).getCommentAmountByNoteId(noteItem.getNoteId()));
			noteItems.add(noteItem);
		}
		topicDetail.setNotes(noteItems);
		
		return topicDetail;
	}

	@Transactional(readOnly = true)
	public SearchResult search(String type, String param) {
		SearchResult searchResult = new SearchResult();
		searchResult.setType(type);
		
		if(StringUtils.isNotBlank(param)){
			UserSearchService userSearchService = SpringContextHelper.getBean(UserSearchService.class);
			FileInfService fileInfService = SpringContextHelper.getBean(FileInfService.class);
			QueryBuilder builder;
			switch (SearchCodeEnum.fromCode(type)) {
			case USER:
				builder = QueryBuilders.fuzzyQuery(User.USER_NM, param);
				Iterable<User> userIt = userSearchService.getRepository().search(builder);
				for (User user : userIt) {
					UserEx userEx = new UserEx();
					BeanUtils.copyProperties(user, userEx);
					userEx.setUserHeadPictStr(fileInfService.getSmallPictUrl(userEx.getUserHeadPictId(),
							Global.DEFAULT_SMALL_PICT_SIZE, Global.DEFAULT_SMALL_PICT_SIZE));
					searchResult.getUserExlist().add(userEx);
				}
				break;
			case TOPIC:
				builder = QueryBuilders.fuzzyQuery(Topic.TOPIC_NM, param);
				Iterable<Topic> topicIt = this.getRepository().search(builder);
				for (Topic topic : topicIt) {
					TopicEx topicEx = new TopicEx();
					BeanUtils.copyProperties(topic, topicEx);
					topicEx.setTopicHeadPictStr(fileInfService.getSmallPictUrl(topicEx.getTopicHeadPictId(),
							Global.DEFAULT_SMALL_PICT_SIZE, Global.DEFAULT_SMALL_PICT_SIZE));
					searchResult.getTopicExlist().add(topicEx);
				}
				break;
			case NOTE:
				builder = QueryBuilders.fuzzyQuery(Note.NOTE_CONT, param);
				Iterable<Note> noteIt = SpringContextHelper.getBean(NoteSearchService.class).getRepository().search(builder);
				for (Note note : noteIt) {
					NoteEx noteEx = new NoteEx();
					BeanUtils.copyProperties(note, noteEx);
					User sendUser = userSearchService.getById(noteEx.getSendNoteUserId());
					noteEx.setUserHeadPictStr(fileInfService.getSmallPictUrl(sendUser.getUserHeadPictId(),
							Global.DEFAULT_SMALL_PICT_SIZE, Global.DEFAULT_SMALL_PICT_SIZE));
					
					List<NoteFile> noteFiles = SpringContextHelper.getBean(NoteFileSearchService.class).findByKey(NoteFile.NOTE_ID, note.getNoteId());
					for (NoteFile noteFile : noteFiles) {
						String smallPictUrl = fileInfService.getSmallPictUrl(noteFile.getFileInfId(),
								Global.DEFAULT_SMALL_PICT_SIZE, Global.DEFAULT_SMALL_PICT_SIZE);
						NoteEx.NoteFileEx noteFileExt = noteEx.new NoteFileEx(noteFile.getFileInfId(), smallPictUrl);
						noteEx.getNoteFiles().add(noteFileExt);
					}
					
					searchResult.getNoteExlist().add(noteEx);
				}
				break;
			}
		}
		return searchResult;
	}

	@Transactional(readOnly = true)
	public BigImage getTopicBigImage(Integer topicId) {
		Topic topic = SpringContextHelper.getBean(TopicSearchService.class).getById(topicId);
		String pictUrl = SpringContextHelper.getBean(FileInfService.class).getPictUrl(topic.getTopicHeadPictId());
		return new BigImage(pictUrl);
	}
	
	@Transactional(readOnly = true)
	public PageBean<TopicEx> getTopicsByCategoryIdByPage(String topicCategoryId,Integer pageNum) {
		PageRequest pageable = null;
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		queryBuilder.must(QueryBuilders.matchQuery(Topic.TOPIC_CATEGORY_ID, topicCategoryId));
		pageable=new PageRequest(pageNum-1, Global.size, new Sort(new org.springframework.data.domain.Sort.Order(Direction.DESC,Topic.TOPIC_ID)));
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).withPageable(pageable).build();
		AggregatedPage<Topic> topics = elasticsearchTemplate.queryForPage(searchQuery, Topic.class); 

		List<Topic> topicList = topics.getContent();//获取结果集  
		List<TopicEx> retList = new ArrayList<>();
		for (Topic item : topics) {
			TopicEx topicItem = new TopicEx();
			BeanUtils.copyProperties(item, topicItem);
			topicItem.setTopicHeadPictStr(SpringContextHelper.getBean(FileInfService.class)
					.getSmallPictUrl(topicItem.getTopicHeadPictId(), Global.DEFAULT_SMALL_PICT_SIZE, Global.DEFAULT_SMALL_PICT_SIZE));
			retList.add(topicItem);
		}
		 PageBean<TopicEx> topicExPageBean = new PageBean<TopicEx>(pageNum, Global.size, topics.getTotalPages());  
		 topicExPageBean.setData(retList);
		 return topicExPageBean;
	}
	
	@Transactional(readOnly = true)
	public PageBean<TopicEx> getTopicsByKeyWordByPage(String keyWord,Integer topicCategory,Integer pageNum) {
		PageRequest pageable = null;
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		queryBuilder.must(QueryBuilders.matchQuery(Topic.TOPIC_NM,keyWord));
		queryBuilder.must(QueryBuilders.matchQuery(Topic.TOPIC_CATEGORY_ID,topicCategory));
		pageable=new PageRequest(pageNum-1, Global.size, new Sort(new org.springframework.data.domain.Sort.Order(Direction.DESC,Topic.TOPIC_ID)));
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).withPageable(pageable).build();
		AggregatedPage<Topic> topics = elasticsearchTemplate.queryForPage(searchQuery, Topic.class); 

		List<Topic> topicList = topics.getContent();//获取结果集  
		List<TopicEx> retList = new ArrayList<>();
		for (Topic item : topics) {
			TopicEx topicItem = new TopicEx();
			BeanUtils.copyProperties(item, topicItem);
			topicItem.setTopicHeadPictStr(SpringContextHelper.getBean(FileInfService.class)
					.getSmallPictUrl(topicItem.getTopicHeadPictId(), Global.DEFAULT_SMALL_PICT_SIZE, Global.DEFAULT_SMALL_PICT_SIZE));
			retList.add(topicItem);
		}
		 PageBean<TopicEx> topicExPageBean = new PageBean<TopicEx>(pageNum, Global.size, topics.getTotalPages());  
		 topicExPageBean.setData(retList);
		 return topicExPageBean;
	}

	@Transactional(readOnly=true)
	public TopicDetail getTopicDetailByNoteKeyWord(Integer topicId, String noteKeyWord) {
		TopicDetail topicDetail = new TopicDetail();
		Topic topic = SpringContextHelper.getBean(this.getClass()).getById(topicId);
		BeanUtils.copyProperties(topic, topicDetail);
		
		topicDetail.setTopicHeadPictStr(SpringContextHelper.getBean(FileInfService.class).getSmallPictUrl(topic.getTopicHeadPictId(), Global.DEFAULT_SMALL_PICT_SIZE, Global.DEFAULT_SMALL_PICT_SIZE));
		
		topicDetail.setFollowerAmount(SpringContextHelper.getBean(TopicIdentitySearchService.class).countFollowerAmount(topicId));
		
		List<TopicDetail.Follower> followers = new ArrayList<>();
		List<TopicIdentity> identities = SpringContextHelper.getBean(TopicIdentitySearchService.class).findByKey(TopicIdentity.TOPIC_ID, topicId);
		for (TopicIdentity item : identities) {
			int userId = item.getUserId();
			User user = SpringContextHelper.getBean(UserSearchService.class).getById(userId);
			String userHeadPictStr = SpringContextHelper.getBean(FileInfService.class)
					.getSmallPictUrl(user.getUserHeadPictId(), Global.DEFAULT_SMALL_PICT_SIZE, Global.DEFAULT_SMALL_PICT_SIZE);
			TopicDetail.Follower follower = topicDetail.new Follower(userId, user.getUserNm(), user.getUserHeadPictId(), userHeadPictStr);
			followers.add(follower);
		}
		topicDetail.setFollowers(followers);
		
		List<NoteEx> noteItems = new ArrayList<>();
		  BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		  queryBuilder.must(QueryBuilders.matchQuery(Note.NOTE_CONT,noteKeyWord));
		  SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();
		  List<Note> notes = elasticsearchTemplate.queryForList(searchQuery, Note.class);
		  
		for (Note note : notes) {
			NoteEx noteItem = new NoteEx();
			BeanUtils.copyProperties(note, noteItem);
			List<NoteFile> noteFiles = SpringContextHelper.getBean(NoteFileSearchService.class).findByKey(NoteFile.NOTE_ID, note.getNoteId());
			for (NoteFile noteFile : noteFiles) {
				String smallPictUrl = SpringContextHelper.getBean(FileInfService.class).getSmallPictUrl(
						noteFile.getFileInfId(), Global.DEFAULT_SMALL_PICT_SIZE, Global.DEFAULT_SMALL_PICT_SIZE);
				NoteEx.NoteFileEx noteFileExt = noteItem.new NoteFileEx(noteFile.getFileInfId(), smallPictUrl);
				noteItem.getNoteFiles().add(noteFileExt);
			}
			User sendUser = SpringContextHelper.getBean(UserSearchService.class).getById(noteItem.getSendNoteUserId());
			noteItem.setUserHeadPictStr(SpringContextHelper.getBean(FileInfService.class).getSmallPictUrl(
					sendUser.getUserHeadPictId(), Global.DEFAULT_SMALL_PICT_SIZE, Global.DEFAULT_SMALL_PICT_SIZE));
			noteItem.setCommentAmount(SpringContextHelper.getBean(NoteCommentSearchService.class).getCommentAmountByNoteId(noteItem.getNoteId()));
			noteItems.add(noteItem);
		}
		topicDetail.setNotes(noteItems);
		
		return topicDetail;
	} 

}
