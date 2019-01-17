package org.penpi.subsys.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.penpi.app.dto.UserEx;
import org.penpi.core.commons.base.BaseSearchService;
import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.core.commons.utils.ListExcelWriter;
import org.penpi.core.commons.web.WebContextHolder;
import org.penpi.core.utils.PageBean;
import org.penpi.subsys.Global;
import org.penpi.subsys.entity.User;
import org.penpi.subsys.search.UserSearchRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
public class UserSearchService extends BaseSearchService<User, Integer, UserSearchRepository> {
	
	private final Integer size = 6;//设置每页显示的数据数 
	private final Order order = new Order(Direction.DESC,User.USER_ID); //设置排序方式  
	private final Sort sort=new Sort(order);  
	
	@Autowired
	private UserSearchRepository user_search_repository;
	@Autowired  
    private ElasticsearchTemplate elasticsearchTemplate;
	
	@Transactional(readOnly = true)
	public List<User> findByLoginIdAndUserPsw(String userNm, String userPsw){
		return user_search_repository.findByLoginIdAndUserPsw(userNm, userPsw);
	}
	
	@Transactional(readOnly = true)
	public PageBean<User> findUsersByPage(Integer pageNum){  
	      
	    PageRequest pageable=new PageRequest(pageNum-1, size, sort);         
	    Page<User> pageUser = user_search_repository.findAll(pageable);  
	        
	    List<User> users = pageUser.getContent();//获取结果集  
	    PageBean<User> userPageBean = new PageBean<User>(pageNum,size, pageUser.getTotalPages());  
	    userPageBean.setData(users);
	    
	    return userPageBean; 
	}
	
	
	@Transactional(readOnly = true)
	public PageBean<User> findUsersByConditions(User user,Integer pageNum) {
		//System.out.println(user.getUserNm());
		PageBean<User> userPageBean;
		if(user == null) {
			userPageBean = this.findUsersByPage(pageNum);
		} 
		else if(user.getUserNm().trim().equals("")&&user.getUserMobile().trim().equals("")&&user.getUserSexCode().equals("-1")&&user.getUserStatCode().equals("-1")&&user.getIfAdmin().equals("-1")){	 
			userPageBean = this.findUsersByPage(pageNum);
		}
		else {
		    BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		    
			if(!user.getUserSexCode().equals("-1")) {
				queryBuilder.must(QueryBuilders.matchQuery(User.USER_SEX_CODE, user.getUserSexCode()));
			}
			if(!user.getIfAdmin().equals("-1")) {
				queryBuilder.must(QueryBuilders.matchQuery(User.IF_ADMIN, user.getIfAdmin()));
			}	
			if(!user.getUserStatCode().equals("-1")) {
				queryBuilder.must(QueryBuilders.matchQuery(User.USER_STAT_CODE, user.getUserStatCode()));
			}	
			if(!user.getUserNm().trim().equals("")) {
				queryBuilder.must(QueryBuilders.matchPhraseQuery(User.USER_NM, user.getUserNm()).operator(Operator.AND));		  
			}	
			if(!user.getUserMobile().trim().equals("")) {
				queryBuilder.must(QueryBuilders.wildcardQuery(User.USER_MOBILE, user.getUserMobile()));
			}
			PageRequest pageable=new PageRequest(pageNum-1, size, sort);         
			SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).withPageable(pageable).build();
			 	 
			AggregatedPage<User> users = elasticsearchTemplate.queryForPage(searchQuery, User.class); 
			//System.out.println(users.getContent());
			userPageBean = new PageBean<User>(pageNum,size, users.getTotalPages());  
			userPageBean.setData(users.getContent());
		}
        return userPageBean;
	}
	
	@Transactional(readOnly = true)
	public String allUserExcel(String type) throws IOException{
		Iterable<User> users = null;
		if(type.equals("0"))
			users = SpringContextHelper.getBean(this.getClass()).getRepository().findAll();
		else if(type.equals("1"))
			users = SpringContextHelper.getBean(this.getClass()).getRepository().findByIfAdmin("Y");
		else if(type.equals("2"))
			users = SpringContextHelper.getBean(this.getClass()).getRepository().findByIfAdmin("N");		
		else if(type.equals("3"))
			users = SpringContextHelper.getBean(this.getClass()).getRepository().findByUserSexCode("1");
		else if(type.equals("4"))
			users = SpringContextHelper.getBean(this.getClass()).getRepository().findByUserSexCode("0");
		else if(type.equals("5"))
			users = SpringContextHelper.getBean(this.getClass()).getRepository().findByUserStatCode("0");
		else if(type.equals("6"))
			users = SpringContextHelper.getBean(this.getClass()).getRepository().findByUserStatCode("1");
		else if(type.equals("7"))
			users = SpringContextHelper.getBean(this.getClass()).getRepository().findByUserStatCode("2");
		
		ListExcelWriter writer = new ListExcelWriter(WebContextHolder.getWarPath()+File.separator+"exceltemplate"+File.separator+"allUserExcelExample.xls");   
		 List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		for(User user:users){  
			 Map<String, Object> data = new HashMap<String, Object>();
			 data.put("userId",user.getUserId());
			 data.put("loginId",user.getLoginId());
			 data.put("userNm",user.getUserNm());
			 data.put("userPsw", user.getUserPsw());
			 if(user.getUserSexCode().equals(0))
				 data.put("userSex", "女");
			 else 
				 data.put("userSex", "男");
			 data.put("userMobile", user.getUserMobile());
			 data.put("userEmail", user.getUserEmail());
			 data.put("userIdentify", user.getIfAdmin().equals("N")?"普通用户":"管理员");
			 data.put("userCreateTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getCreateDate()));
			 data.put("userStat", user.getUserStatCode().equals("0")?"正常":(user.getUserStatCode().equals("1")?"冻结":"删除"));
			 dataList.add(data);
	   }  
		UUID name = UUID.randomUUID();
		String outFileName = WebContextHolder.getWarPath()+File.separator+"temp"+File.separator + name + ".xls";
		 writer.fillToFile(dataList, outFileName);
	 
		 return name+".xls";
	}

	@Transactional(readOnly = true)
	public UserEx getUserProfile(Integer userId) {
		User user = SpringContextHelper.getBean(this.getClass()).getById(userId);
		UserEx ex = new UserEx();
		BeanUtils.copyProperties(user, ex);
		ex.setUserHeadPictStr(SpringContextHelper.getBean(FileInfService.class).getSmallPictUrl(user.getUserHeadPictId(), Global.DEFAULT_SMALL_PICT_SIZE, Global.DEFAULT_SMALL_PICT_SIZE));
		return ex;
	}

}
