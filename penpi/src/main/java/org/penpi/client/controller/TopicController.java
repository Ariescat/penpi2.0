package org.penpi.client.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.penpi.app.dto.TopicDetail;
import org.penpi.app.dto.TopicEx;
import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.core.utils.PageBean;
import org.penpi.subsys.ControllerMapping;
import org.penpi.subsys.entity.TopicCategory;
import org.penpi.subsys.service.TopicCategorySearchService;
import org.penpi.subsys.service.TopicSearchService;
import org.penpi.subsys.service.TopicService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController("adminTopicController")
@RequestMapping(ControllerMapping.ADMIN_TOPIC)   /* /admin/topic */
public class TopicController {
	
	@RequestMapping(ControllerMapping.ADMIN_TOPIC_MAIN)
	public ModelAndView topicMain(Integer pageNum,String topicCategoryId,HttpSession session) {

		List<TopicCategory> topicCategoryList =  SpringContextHelper.getBean(TopicCategorySearchService.class).getAllTopicCategorys();
		PageBean<TopicEx> topicExList = SpringContextHelper.getBean(TopicSearchService.class).getTopicsByCategoryIdByPage(topicCategoryId,pageNum);
		session.removeAttribute("keyWord");
		session.setAttribute("topicCategorySelected", topicCategoryId);
		session.setAttribute("topicCategoryList", topicCategoryList);
		session.setAttribute("topicExList", topicExList);
		return new ModelAndView("forward:/WEB-INF/pages/alltopic.jsp");		
	}
	@RequestMapping(ControllerMapping.ADMIN_TOPIC_DETAIL)
	public ModelAndView topicDetail(Integer topicId,HttpSession session) {
		
		TopicDetail topicDetail = SpringContextHelper.getBean(TopicSearchService.class).getTopicDetail(50001, topicId);
		session.setAttribute("topicDetail", topicDetail);
		 session.removeAttribute("noteKeyWord");
		return new ModelAndView("forward:/WEB-INF/pages/topicDetail.jsp");
	}
	
	@RequestMapping(ControllerMapping.ADMIN_MODIFY_TOPIC)
	public void modifyTopic(String topicId,String topicCategory,HttpSession session) {
		SpringContextHelper.getBean(TopicService.class).modifyTopic(Integer.valueOf(topicId),Integer.valueOf(topicCategory) );
		 
	}
	
	@RequestMapping(ControllerMapping.ADMIN_FIND_TOPIC_BY_KEY_WORD)
	public ModelAndView findTopicByKeyWord(String keyWord, Integer FindtopicCategoryId,HttpSession session) {
		PageBean<TopicEx> topicExList = SpringContextHelper.getBean(TopicSearchService.class).getTopicsByKeyWordByPage(keyWord,FindtopicCategoryId, 1);
		session.setAttribute("topicExList", topicExList);
		session.setAttribute("keyWord", keyWord);
		return new ModelAndView("forward:/WEB-INF/pages/alltopic.jsp");		 
	}
	
	@RequestMapping(ControllerMapping.ADMIN_TOPIC_TYPE_STATISTIC)
	public String topicTypeStatistic() {
			Integer follow = SpringContextHelper.getBean(TopicSearchService.class).getTopicsByCategoryId(50001).size();
			Integer hot = SpringContextHelper.getBean(TopicSearchService.class).getTopicsByCategoryId(50002).size();
			Integer school = SpringContextHelper.getBean(TopicSearchService.class).getTopicsByCategoryId(50003).size();
			Integer comic = SpringContextHelper.getBean(TopicSearchService.class).getTopicsByCategoryId(50004).size();
			Integer technology = SpringContextHelper.getBean(TopicSearchService.class).getTopicsByCategoryId(50005).size();
			Integer game = SpringContextHelper.getBean(TopicSearchService.class).getTopicsByCategoryId(50006).size();
			Integer interact = SpringContextHelper.getBean(TopicSearchService.class).getTopicsByCategoryId(50007).size();
			Integer emotion = SpringContextHelper.getBean(TopicSearchService.class).getTopicsByCategoryId(50008).size();
			Integer entertainment = SpringContextHelper.getBean(TopicSearchService.class).getTopicsByCategoryId(50009).size();
			 return "{\"follow\":\"" + follow + "\",\"hot\":\"" + hot+ "\",\"school\":\""+school+"\",\"comic\":\""+comic+"\",\"technology\":\""+technology+"\",\"game\":\""+game+"\",\"interact\":\""+interact+"\",\"emotion\":\""+emotion+"\",\"entertainment\":\""+entertainment+"\"}";
		}
			 
 
	

	

}
