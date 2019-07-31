package com.youyoubo.wx.access.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.base.core.tools.BaseTools;
import com.youyoubo.wx.access.entity.WxMessageListEntity;
import com.youyoubo.wx.access.entity.WxUserListEntity;
import com.youyoubo.wx.access.mapper.WxMessageListMapper;
import com.youyoubo.wx.access.mapper.WxUserListMapper;
import com.youyoubo.wx.access.service.IMessageService;
import com.youyoubo.wx.util.AccessTokenController;
import com.youyoubo.wx.util.CreateMenu;
import com.youyoubo.wx.util.HttpRequestUtil;
import com.youyoubo.wx.util.MessageUtil;
import com.youyoubo.wx.util.enu.EventTypeEnum;
import com.youyoubo.wx.util.enu.ReceiveMsgTypeEnum;
import com.youyoubo.wx.util.vo.TextMessage;
@Service
public class MessageServiceImpl implements IMessageService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	WxMessageListMapper wxMessageListMapper;

	@Autowired
	WxUserListMapper wxUserListMapper;

	public  String processRequest(HttpServletRequest request) throws Exception {
		String respMessage = null;
		// 默认返回的文本消息内容
		String respContent = "";

		// xml请求解析
		Map<String, String> requestMap = MessageUtil.parseXml(request);
		// 发送方帐号（open_id）
		String fromUserName = requestMap.get("FromUserName");
		// 公众帐号
		String toUserName = requestMap.get("ToUserName");
		// 消息类型
		String msgType = requestMap.get("MsgType");
		// 回复文本消息
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(ReceiveMsgTypeEnum.text.toString());


		//记录消息日志
		WxMessageListEntity wxMessageListEntity = new WxMessageListEntity();
		wxMessageListEntity.setID(BaseTools.getNextSeq());
		wxMessageListEntity.setFROMUSERNAME(fromUserName);
		wxMessageListEntity.setTOUSERNAME(toUserName);
		wxMessageListEntity.setMSGTYPE(msgType);
		wxMessageListEntity.setEVENT(requestMap.get("Event"));
		wxMessageListEntity.setEVENTKEY(requestMap.get("EventKey"));
		wxMessageListEntity.setCREATETIME(getDateStr(requestMap.get("CreateTime")));
		wxMessageListMapper.insertWxMessageList(wxMessageListEntity);


		logger.info(JSON.toJSONString(requestMap));
		if (msgType.equals(ReceiveMsgTypeEnum.event.toString())) { 
			// 事件类型
			String eventType = requestMap.get("Event");
			String EventKey = requestMap.get("EventKey");
			// 订阅
			if (eventType.equals(EventTypeEnum.subscribe.toString())) {
				if (StringUtils.isBlank(EventKey)) {
					WxUserListEntity wxUserListEntity = new WxUserListEntity();
					wxUserListEntity.setUserId(fromUserName);
					wxUserListEntity.setGzhId(toUserName);
					int count = wxUserListMapper.selectWxUserListCount(wxUserListEntity);
					if (count==0) {
						wxUserListEntity.setId(BaseTools.getNextSeq());
						wxUserListMapper.insertWxUserList(wxUserListEntity);
					}
					//
					String token = AccessTokenController.getInstance().getAccess_token();
					String url = CreateMenu.GetUserInfoUrl;
					url = String.format(url,token,fromUserName);
					String json = HttpRequestUtil.httpGet(url);
					logger.info(json);
					
					respContent = "谢谢关注";
				}else {
					//带场景关注
					respContent = "谢谢关注2";
				}
			}// 取消订阅
			else if (eventType.equals(EventTypeEnum.unsubscribe.toString())) {
				WxUserListEntity wxUserListEntity = new WxUserListEntity();
				wxUserListEntity.setUserId(fromUserName);
				wxUserListEntity.setGzhId(toUserName);
				wxUserListMapper.deleteWxUserList(wxUserListEntity);
			}
		} else {  //如果是其他类型不做处理返回空串
			return respContent;
		}
		textMessage.setContent(respContent);
		respMessage = MessageUtil.messageToXml(textMessage);



		return respMessage;
	}


	private String getDateStr(String secods){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		return sdf.format(new Date(Long.valueOf(secods+"000"))); 

	}

}
