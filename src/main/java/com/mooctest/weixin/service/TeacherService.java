package com.mooctest.weixin.service;

import org.apache.log4j.Logger;

import com.mooctest.weixin.manager.LoggerManager;
import com.mooctest.weixin.pojo.UserRequest;
import com.mooctest.weixin.util.MessageUtil;

public class TeacherService extends GuestService{

	private static Logger logger=Logger.getLogger(TeacherService.class);	
	public static String defaultContent = "未知的消息类型！";
	
	public static String processRequest(UserRequest userRequest) {
		String respXml = "";
		String respContent = defaultContent;

		try {
			String fromUserName = userRequest.getFromUserName(); // 发送方帐号
			String toUserName = userRequest.getToUserName(); // 开发者微信号
			String msgType = userRequest.getMsgType(); // 消息类型
			String content = userRequest.getContent(); // 内容
			String createTime = userRequest.getContent(); // 生成时间
			LoggerManager.info(logger, "(UserRequest)[" + msgType + "]"
					+ createTime + ":" + fromUserName + "---->" + toUserName);

			// 处理Session
			if (processSession(userRequest)) {
				return userRequest.getResultXml();
			}		
			// 处理Text
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				LoggerManager.info(logger, "(StudentText)" + createTime + ":" + fromUserName + "---->" + toUserName + ":" + content);
				respContent = "您发送的是文本消息！";
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			// 语音消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是语音消息！";
			}
			// 视频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				respContent = "您发送的是视频消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			
			// 处理Event
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = userRequest.getRequestMap().get("Event");
				// 关注
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					processSubscribeEvent(userRequest);
					return userRequest.getResultXml();
				}
				// 取消关注
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
				}
				// 扫描带参数二维码
				else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					processScanEvent(userRequest);
					return userRequest.getResultXml();
					// TODO 处理扫描带参数二维码事件
				}
				// 上报地理位置
				else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
					// TODO 处理上报地理位置事件
					return null;
					//return userRequest.getResultXml();
				}
				// 自定义菜单事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					String eventKey = userRequest.getRequestMap().get("EventKey");
					if (eventKey.equals("account")) {
						processAccount(userRequest);
						return userRequest.getResultXml();
					}
				}
			}			
			processHelpMessage(userRequest);
			return userRequest.getResultXml();
		} catch (Exception e) {
			logger.error(e.getStackTrace());
			e.printStackTrace();
		}

		return respXml;
	}

//提示用户进入账号信息页面
	protected static void processAccount(UserRequest userRequest){
		String respContent= "请点击<a href='"+userRequest.accountUrl()+"'>账号页面</a>查看账号信息";
		userRequest.getTextMessage().setContent(respContent);
		userRequest.setResultXml(MessageUtil.messageToXml(userRequest.getTextMessage()));
	}	
}