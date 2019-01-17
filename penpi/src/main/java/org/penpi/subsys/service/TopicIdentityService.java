package org.penpi.subsys.service;

import org.penpi.core.commons.base.BaseService;
import org.penpi.core.commons.exception.BusinessException;
import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.core.commons.web.ClientUser;
import org.penpi.core.commons.web.WebContextHolder;
import org.penpi.subsys.Global;
import org.penpi.subsys.ResGlobal;
import org.penpi.subsys.code.BoolCodeEnum;
import org.penpi.subsys.code.TopicIdentityCodeEnum;
import org.penpi.subsys.entity.TopicIdentity;
import org.penpi.subsys.repository.TopicIdentityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicIdentityService extends BaseService<TopicIdentity, Integer, TopicIdentityRepository> {

	@Transactional
	public TopicIdentity follower(Integer userId, Integer topicId, Boolean ifFollow) {
		
		ClientUser clientUser = WebContextHolder.getSessionContextStore().getServerValue(Global.SESSION_LOGIN_MEMBER);
		if (clientUser == null || !userId.equals(clientUser.getUserId())) {
			throw new BusinessException(ResGlobal.ERRORS_USER_DEFINED, ResGlobal.LOGIG_ILLEGAL);
		}
		
		TopicIdentityService topicIdentityService = SpringContextHelper.getBean(TopicIdentityService.class);
		TopicIdentity identity = SpringContextHelper.getBean(TopicIdentitySearchService.class).getByUserIdAndTopicId(userId, topicId);
		if (identity == null) {
			identity = new TopicIdentity();
			identity.setUserId(userId);
			identity.setTopicId(topicId);
			identity.setTopicIdentityCode(TopicIdentityCodeEnum.NORMAL.toCode());
			identity.setIfFreeze(BoolCodeEnum.NO.toCode());
			return topicIdentityService.save(identity);
		}
		// 如果ifFollow为false，那ifFreeze应设为"Y"
		identity.setIfFreeze(BoolCodeEnum.fromValue(!ifFollow).toCode());
		return topicIdentityService.save(identity);
	}
	
	@Transactional(readOnly = true)
	public Boolean checkifFollow(Integer userId, Integer topicId) {
		TopicIdentity identity = SpringContextHelper.getBean(TopicIdentitySearchService.class).getByUserIdAndTopicId(userId, topicId);
		//如果没用这个数据或该数据为冻结状态，则返回false
		return !(identity == null || identity.getIfFreeze().equals(BoolCodeEnum.YES.toCode()));
	}

}
