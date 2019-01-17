package org.penpi.subsys.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.penpi.app.dto.UserEx;
import org.penpi.core.commons.base.BaseService;
import org.penpi.core.commons.exception.BusinessException;
import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.core.commons.web.ClientUser;
import org.penpi.core.commons.web.WebContextHolder;
import org.penpi.core.utils.UtilValidateCode;
import org.penpi.subsys.Global;
import org.penpi.subsys.ResGlobal;
import org.penpi.subsys.code.BoolCodeEnum;
import org.penpi.subsys.code.SexCodeEnum;
import org.penpi.subsys.code.UserStatCodeEnum;
import org.penpi.subsys.entity.OperateRecord;
import org.penpi.subsys.entity.User;
import org.penpi.subsys.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService extends BaseService<User, Integer, UserRepository> {
	
	private UtilValidateCode validateCodeUtil = new UtilValidateCode();

	public String getValidateCode(String loginId) {
		return validateCodeUtil.getValidateCode(loginId);
	}
	
	public ClientUser memberLogin(String loginId, String validateCode) {
		if (validateCodeUtil.verificate(loginId, validateCode)) {
			User user = SpringContextHelper.getBean(UserSearchService.class).getByKey(User.LOGIN_ID, loginId);
			if (user == null) {
				user = new User();
				user.setLoginId(loginId);
				user.setUserNm(loginId);
				user.setUserMobile(loginId);
				user.setIfAdmin(BoolCodeEnum.NO.toCode());
				user.setUserSexCode(SexCodeEnum.SECRECY.toCode());
				user.setCreateDate(new Date());
				user.setUserStatCode(UserStatCodeEnum.NORMAL.toCode());
				SpringContextHelper.getBean(UserService.class).save(user);
			}
			WebContextHolder.getSessionContextStore().setServerValue(Global.SESSION_LOGIN_MEMBER, ClientUser.create(user));
			return ClientUser.create(user);
		}
		throw new BusinessException(ResGlobal.ERRORS_USER_DEFINED, new String[] { "验证码校验错误" });
	}
	
	public ClientUser relogin(String loginId) {
		User user = SpringContextHelper.getBean(UserSearchService.class).getByKey(User.LOGIN_ID, loginId);
		if (user == null) {
			user = new User();
			user.setLoginId(loginId);
			user.setUserNm(loginId);
			user.setUserMobile(loginId);
			user.setIfAdmin(BoolCodeEnum.NO.toCode());
			user.setUserSexCode(SexCodeEnum.SECRECY.toCode());
			user.setCreateDate(new Date());
			user.setUserStatCode(UserStatCodeEnum.NORMAL.toCode());
			SpringContextHelper.getBean(UserService.class).save(user);
		}
		WebContextHolder.getSessionContextStore().setServerValue(Global.SESSION_LOGIN_MEMBER, ClientUser.create(user));
		return ClientUser.create(user);
	}
	
	@Transactional
	public UserEx updateUserHeadPict(User user) {
		
		try {
			SpringContextHelper.getBean(this.getClass()).save(user);
			
			User oldUser = SpringContextHelper.getBean(UserSearchService.class).getById(user.getUserId());
			if (oldUser.getUserHeadPictId() == null) {
				SpringContextHelper.getBean(FileInfService.class).syncBusinessObject(user.getUserId(), user, null, User.class);
			} else {
				SpringContextHelper.getBean(FileInfService.class).syncBusinessObject(user.getUserId(), user, oldUser, User.class);
			}
		} catch (Exception e) {
			throw new BusinessException(ResGlobal.ERRORS_USER_DEFINED, new String[]{"图片修改失败，请重新修改"});
		}
		
		UserEx ex = new UserEx();
		BeanUtils.copyProperties(user, ex);
		ex.setUserHeadPictStr(SpringContextHelper.getBean(FileInfService.class).getSmallPictUrl(user.getUserHeadPictId(), Global.DEFAULT_SMALL_PICT_SIZE, Global.DEFAULT_SMALL_PICT_SIZE));
		return ex;
	}
	
	@Transactional
	public List<OperateRecord> updateOne(User user,String operater) {
		//System.out.println(user.getUserNm()+" "+user.getIfAdmin());
		User oldUser = SpringContextHelper.getBean(this.getClass()).getById(user.getUserId());
		List<OperateRecord> opList = new ArrayList();
		
		if(!oldUser.getIfAdmin().equals(user.getIfAdmin())) {	
			OperateRecord or = new OperateRecord();
			or.setOpType("0"); //0表示对用户的操作记录
			or.setOpDate(new Date());
			or.setOpUserLoginId(operater);
			if(user.getIfAdmin().equals("Y"))
				or.setOpContent("用户账号为:"+user.getLoginId()+"的用户权限由普通用户变为管理员");
			else
				or.setOpContent("用户账号为:"+user.getLoginId()+"的用户权限由管理员变为普通用户");
			opList.add(or);
		}
		if(!oldUser.getUserStatCode().equals(user.getUserStatCode())) {
			OperateRecord or = new OperateRecord();
			or.setOpType("0"); //0表示对用户的操作记录
			or.setOpDate(new Date());
			or.setOpUserLoginId(operater);
			if(user.getUserStatCode().equals("0")) {
				 if(oldUser.getUserStatCode().equals("1")) {
					 or.setOpContent("用户账号为:"+user.getLoginId()+"的用户状态由冻结恢复正常了");
				 }
				 else if(oldUser.getUserStatCode().equals("2")){
					 or.setOpContent("用户账号为:"+user.getLoginId()+"的用户状态由已删除恢复为正常");
				 }		 
			 }
			 else if(user.getUserStatCode().equals("1")) {
				 if(oldUser.getUserStatCode().equals("0")) {
					 or.setOpContent("用户账号为:"+user.getLoginId()+"的用户状态由正常变为冻结");
					 
				 }
				 else if(oldUser.getUserStatCode().equals("2")){
					 or.setOpContent("用户账号为:"+user.getLoginId()+"的用户状态由已删除变为冻结");
				 }	 
			 }
			 else{
				 if(oldUser.getUserStatCode().equals("0")) {
					 or.setOpContent("用户账号为:"+user.getLoginId()+"的用户状态由正常变为删除");
				 }
				 else if(oldUser.getUserStatCode().equals("1")){
					 or.setOpContent("用户账号为:"+user.getLoginId()+"的用户状态由冻结变为删除");
				 }
			 }
			opList.add(or);
		}
		for(int i=0;i<opList.size();i++) {
			System.out.println(opList.get(i).getOpContent());
		}
			SpringContextHelper.getBean(this.getClass()).save(user);
			return opList;
	     
	}
	
	@Transactional
	public List<OperateRecord> updateUserStatCode(String userStatCode,List<Integer> userId,String operaterNm) { 
		User user = new User();
		List<OperateRecord> opList = new ArrayList();
		for(int i=0;i<userId.size();i++) {
			user = SpringContextHelper.getBean(this.getClass()).getById(userId.get(i));
			//WriteLog.UserLog(user,userStatCode,operaterNm);
			OperateRecord or = new OperateRecord();
			or.setOpType("0"); //0表示对用户的操作记录
			or.setOpDate(new Date());
			or.setOpUserLoginId(operaterNm);
			if(userStatCode.equals("0")) {
				 if(user.getUserStatCode().equals("1")) {
					 or.setOpContent("用户账号为:"+user.getLoginId()+"的用户状态由冻结恢复正常了");
				 }
				 else if(user.getUserStatCode().equals("2")){
					 or.setOpContent("用户账号为:"+user.getLoginId()+"的用户状态由已删除恢复为正常");
				 }		 
			 }
			 else if(userStatCode.equals("1")) {
				 if(user.getUserStatCode().equals("0")) {
					 or.setOpContent("用户账号为:"+user.getLoginId()+"的用户状态由正常变为冻结");
					 
				 }
				 else if(user.getUserStatCode().equals("2")){
					 or.setOpContent("用户账号为:"+user.getLoginId()+"的用户状态由已删除变为冻结");
				 }	 
			 }
			 else{
				 if(user.getUserStatCode().equals("0")) {
					 or.setOpContent("用户账号为:"+user.getLoginId()+"的用户状态由正常变为删除");
				 }
				 else if(user.getUserStatCode().equals("1")){
					 or.setOpContent("用户账号为:"+user.getLoginId()+"的用户状态由冻结变为删除");
				 }
			 }
		 
			if(or.getOpContent() != null)
				opList.add(or);
			user.setUserStatCode(userStatCode);	
			SpringContextHelper.getBean(this.getClass()).save(user);
		} 
		//WriteLog.Write(WriteLog.userLogFile);
		return opList;
		
	}

}
