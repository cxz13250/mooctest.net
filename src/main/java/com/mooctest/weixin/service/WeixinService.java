package com.mooctest.weixin.service;


import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.mooctest.weixin.manager.LoggerManager;
import com.mooctest.weixin.pojo.UserRequest;
import com.mooctest.weixin.util.MessageUtil;


/**
 * 核心服务类
 * 
 * @author cxz
 * @date 2017:03:22
 */
public class WeixinService {
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	private static Logger logger = Logger.getLogger(WeixinService.class); 
	
	public static String processRequest(HttpServletRequest request) {
		// xml格式的消息数据
		String respXml = null;
		
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			UserRequest userRequest = new UserRequest(requestMap);
			//return TeacherService.processRequest(userRequest);
			if (userRequest.isMoocUser()){
				LoggerManager.info(logger, "(MoocUserRequest)" +userRequest.getFromUserName() 
						+ "---->"+ userRequest.getToUserName());
				return MoocUserService.processRequest(userRequest);
			}else{
				LoggerManager.info(logger, "(GuestRequest)" +userRequest.getFromUserName() 
						+ "---->"+ userRequest.getToUserName());
				return GuestService.processRequest(userRequest);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
	
	public static ArrayList<String> teacher_list = new ArrayList<String>();
	public static void addTeacher(String openId){
		if (!isTeacher(openId)) {
			teacher_list.add(openId);
		}
	}
	public static void removeTeacher(String openId){
		if (isTeacher(openId)) {
			teacher_list.remove(openId);
		}
	}
	public static boolean isTeacher(String openId){
		if (teacher_list.contains(openId)){
			return true;
		}else {
			return false;
		}
	}
	public static boolean isStudent(String openId){
		return true;
	}
}
