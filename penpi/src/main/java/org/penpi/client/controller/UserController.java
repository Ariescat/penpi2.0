package org.penpi.client.controller;

 
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.penpi.core.utils.PageBean;
import org.penpi.subsys.ControllerMapping;
import org.penpi.subsys.entity.OperateRecord;
import org.penpi.subsys.entity.User;
import org.penpi.subsys.service.OperateRecordService;
import org.penpi.subsys.service.UserSearchService;
import org.penpi.subsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
 

 

@RestController("adminUserController")
@RequestMapping(ControllerMapping.ADMIN_USER)   /* /admin/user */
public class UserController {
	
	@Autowired
	UserSearchService userSearchService;
	@Autowired
	UserService userService;
	 
	@Autowired
	OperateRecordService operateRecordService;
	

	@RequestMapping(ControllerMapping.ADMIN_FIND_ALL_USER)
	public ModelAndView findAllUser(Integer pageNum,HttpSession session) {	
		 
		PageBean<User> pagebean = userSearchService.findUsersByPage(pageNum); 
		session.setAttribute("pagebean", pagebean);
		return new ModelAndView("forward:/WEB-INF/pages/alluser.jsp");		
	}
	
	@RequestMapping(value=ControllerMapping.ADMIN_FIND_USERS_BY_PAGE,produces="text/html;charset=UTF-8;")
	public String findUsersByPage(String pageNum) throws JsonProcessingException {	
	 
		PageBean<User> userPageBean = userSearchService.findUsersByPage(Integer.parseInt(pageNum)); 
	
		ObjectMapper mapper = new ObjectMapper();  
		String userPageBeanJson = mapper.writeValueAsString(userPageBean); 
		System.out.println(userPageBeanJson);
		return userPageBeanJson;	
	}
	 
	@RequestMapping(value=ControllerMapping.ADMIN_FIND_USERS_BY_CONDITIONS,produces="text/html;charset=UTF-8;")
	public String findUsersByConditions(User user,String pageNum) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper(); 
		PageBean<User> userPageBean = userSearchService.findUsersByConditions(user, Integer.parseInt(pageNum));
		String userPageBeanJson = mapper.writeValueAsString(userPageBean); 
		return userPageBeanJson;	
	}
	
	@RequestMapping(value=ControllerMapping.ADMIN_UPDATE_USERS,produces="text/html;charset=UTF-8;")
	public String updateUsers(@RequestBody User user,String operater) throws JsonProcessingException {
		//System.out.println(operater);
		List<OperateRecord> opList = userService.updateOne(user,operater);
		for(int i=0;i<opList.size();i++) {
			System.out.println(opList.get(i).getOpContent());
			operateRecordService.saveOr(opList.get(i));
		}
		 
		 return null;
	}
	
	@RequestMapping(value=ControllerMapping.ADMIN_UPDATE_USERSTATCODE,produces="text/html;charset=UTF-8;")
	public String updateUserStatCode(String userStatCode,String userIds,String operater) throws JsonParseException, JsonMappingException, IOException {
		List<Integer> userIdListInt = new ArrayList<Integer>();
		List<String> userIdList = Arrays.asList(userIds.split(","));  
        for(int i=0;i<userIdList.size();i++)
        	userIdListInt.add(Integer.valueOf(userIdList.get(i)));
        List<OperateRecord> opList = userService.updateUserStatCode(userStatCode,userIdListInt,operater);
        for(int i=0;i<opList.size();i++) {
        	System.out.println(opList.get(i).getOpContent());
        	operateRecordService.saveOr(opList.get(i));
        }
    
        return null;
	}
	@RequestMapping(value=ControllerMapping.ADMIN_PRINT_USER,produces="text/html;charset=UTF-8;")
	public String printUser(String type) throws IOException {
		return userSearchService.allUserExcel(type);	
	}
	
	@RequestMapping(value=ControllerMapping.ADMIN_USER_SEX_STATISTIC,produces="text/html;charset=UTF-8;")
	public String userSexStatistic() throws IOException {

		int femaleCount = userSearchService.getRepository().findByUserSexCode("0").size();
		int manCount = userSearchService.getRepository().findByUserSexCode("1").size();
		 return "{\"femaleCount\":\"" + femaleCount + "\",\"manCount\":\"" + manCount+ "\"}";
	}
	
	@RequestMapping(value=ControllerMapping.ADMIN_USER_STAT_STATISTIC,produces="text/html;charset=UTF-8;")
	public String userStatStatistic() throws IOException {

		int normalUser = userSearchService.getRepository().findByUserStatCode("0").size();
		int freezeUser = userSearchService.getRepository().findByUserStatCode("1").size();
		int deleteUser = userSearchService.getRepository().findByUserStatCode("2").size();
		 return "{\"normalUser\":\"" + normalUser + "\",\"freezeUser\":\"" + freezeUser+ "\",\"deleteUser\":\""+deleteUser+"\"}";
	}
}
