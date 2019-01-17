package org.penpi.subsys.search;

import java.util.List;

import org.penpi.subsys.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserSearchRepository extends ElasticsearchRepository<User, Integer>{
	
	List<User> findByLoginIdAndUserPsw(String loginId, String userPsw);
	
	List<User> findByUserSexCode(String userSexCode);
	
	List<User> findByIfAdmin(String ifAdmin);
	
	List<User> findByUserStatCode(String userStatCode);
	
	 
}
