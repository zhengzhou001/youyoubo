package com.youyoubo.wx.access.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.core.tools.BaseTools;
import com.youyoubo.wx.access.entity.WxUserListEntity;
import com.youyoubo.wx.access.mapper.WxUserListMapper;
import com.youyoubo.wx.access.service.IMessageService;
import com.youyoubo.wx.message.mapper.WX_MESSAGE_LISTMapper;
import com.youyoubo.wx.user.mapper.WX_USER_INFOMapper;
import com.youyoubo.wx.util.AccessTokenController;
import com.youyoubo.wx.util.CreateMenu;
import com.youyoubo.wx.util.HttpRequestUtil;
import com.youyoubo.wx.util.MessageUtil;
import com.youyoubo.wx.util.enu.EventTypeEnum;
import com.youyoubo.wx.util.enu.ReceiveMsgTypeEnum;
import com.youyoubo.wx.util.enu.SendMsgTypeEnum;
import com.youyoubo.wx.util.vo.Article;
import com.youyoubo.wx.util.vo.NewsMessage;
import com.youyoubo.wx.util.vo.TextMessage;
@Service
public class MessageServiceImpl implements IMessageService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	WX_MESSAGE_LISTMapper WX_MESSAGE_LISTMapper;

	@Autowired
	WxUserListMapper wxUserListMapper;
	
	@Autowired
	WX_USER_INFOMapper wx_USER_INFOMapper;

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


		//异步记录消息日志
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				WX_MESSAGE_LISTMapper.insertWX_MESSAGE_LIST(ArrayUtils.toMap(new Object[][]{
					{"ID",BaseTools.getNextSeq()},
					{"FROMUSERNAME",fromUserName},
					{"TOUSERNAME",toUserName},
					{"EVENT",requestMap.get("Event")},
					{"EVENTKEY",requestMap.get("EventKey")},
					{"MSGTYPE",msgType},
					{"CREATETIME",getDateStr(requestMap.get("CreateTime"))},
					
					{"MSGID",requestMap.get("MsgId")},
					{"CONTENT",requestMap.get("Content")},
					{"MEDIAID",requestMap.get("MediaId")},
					{"PICURL",requestMap.get("PicUrl")},
					{"FORMAT",requestMap.get("Format")},
					{"RECOGNITION",requestMap.get("Recognition")},
					{"THUMBMEDIAID",requestMap.get("ThumbMediaId")},
					{"LOCATION_X",requestMap.get("Location_X")},
					{"LOCATION_Y",requestMap.get("Location_Y")},
					{"SCALE",requestMap.get("Scale")},
					{"LABEL",requestMap.get("Label")},
					
				}));
			}
		}).start();
		


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
					//异步获取用户基本信息
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							String token = AccessTokenController.getInstance().getAccess_token();
							String url = CreateMenu.GetUserInfoUrl;
							url = String.format(url,token,fromUserName);
							String json = HttpRequestUtil.httpGet(url);
							JSONObject jsonObject = JSONObject.parseObject(json);
							Map map = JSON.parseObject(json, Map.class);
							map.put("OPENID", jsonObject.getString("openid"));
							map.put("GZHID", toUserName);
							wx_USER_INFOMapper.deleteWX_USER_INFO(map);
							
							map.put("NICKNAME", jsonObject.getString("nickname"));
							map.put("SEX", jsonObject.getString("sex"));
							map.put("LANGUAGE", jsonObject.getString("language"));
							map.put("CITY", jsonObject.getString("city"));
							map.put("PROVINCE", jsonObject.getString("province"));
							map.put("COUNTRY", jsonObject.getString("country"));
							map.put("HEADIMGURL", jsonObject.getString("headimgurl"));
							map.put("SUBSCRIBE_TIME", jsonObject.getString("subscribe_time"));
							map.put("REMARK", jsonObject.getString("remark"));
							map.put("GROUPID", jsonObject.getString("groupid"));
							map.put("TAGID_LIST", jsonObject.getJSONArray("tagid_list").toJSONString());
							map.put("SUBSCRIBE_SCENE", jsonObject.getString("subscribe_scene"));
							map.put("QR_SCENE", jsonObject.getString("qr_scene"));
							map.put("QR_SCENE_STR", jsonObject.getString("qr_scene_str"));
							map.put("UNIONID", jsonObject.getString("unionid"));
							map.put("GZHID", toUserName);
							
							map.put("ID", BaseTools.getNextSeq());
							wx_USER_INFOMapper.insertWX_USER_INFO(map);
							
						}
					}).start();
					//
					
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
			//自定义菜单
			else if (eventType.equals(EventTypeEnum.CLICK.toString())) {
				
				if("menu1".equals(requestMap.get("EventKey"))) {  //菜单1
					String responseData = getMenu1(requestMap);
					return responseData;
				} 
				
				
				else if ("menu2".equals(requestMap.get("EventKey"))) {//菜单2
					respContent="功能开发中";
				}else if ("menu3".equals(requestMap.get("EventKey"))) {//菜单2
					respContent="功能开发中";
				}
			}
		}else if (msgType.equals(ReceiveMsgTypeEnum.text.toString())) {
			//用户发送了文本消息
			respContent=requestMap.get("Content")+",互动功能开发中，简单演示【学说话】";
		} else {  //如果是其他类型不做处理返回空串
			return respContent;
		}
		textMessage.setContent(respContent);
		respMessage = MessageUtil.messageToXml(textMessage);



		return respMessage;
	}
	
	
	/**
	 * @Desc:  菜单1xml
	 * @param  @param map
	 * @param  @return
	 * @return String
	 * @throws
	 * @author dingshuangbo
	 * @date   2017-07-07
	 */
	private static String getMenu1(Map<String, String> map) {
		String result = null;
		Article cszb = new Article();
		cszb.setTitle("活动订阅");
		cszb.setDescription("财税直播间");
		cszb.setPicUrl("http://2h240448c1.51mypc.cn/images/wx/1.png");

		//在线培训
		Article yyzb = new Article();
		yyzb.setTitle("在线培训预约");
		yyzb.setDescription("在线培训预约");
		yyzb.setPicUrl("http://2h240448c1.51mypc.cn/images/wx/1.png");
		 
		yyzb.setUrl("http://2h240448c1.51mypc.cn/images/wx/1.png");

		//线下培训
		Article spzb = new Article();
		spzb.setTitle("线下培训预约");
		spzb.setDescription("线下培训预约");
		spzb.setPicUrl("http://2h240448c1.51mypc.cn/images/wx/1.png");
		String spzbxUrl="";
		spzb.setUrl(spzbxUrl);

		
		//预约查询
		Article yycx = new Article();
		yycx.setTitle("预约查询");
		yycx.setDescription("预约查询");
		yycx.setPicUrl("http://2h240448c1.51mypc.cn/images/wx/1.png");
		String yycxUrl="";
		yycx.setUrl(yycxUrl);
		
		
		//添加到图文块集合中
		List<Article> articles = new ArrayList<Article>();
		articles.add(cszb);
		articles.add(yyzb);
		articles.add(spzb);
		articles.add(yycx);

		//封装图文消息对象
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(map.get("FromUserName"));
		newsMessage.setFromUserName(map.get("ToUserName"));
		newsMessage.setCreateTime(System.currentTimeMillis());
		newsMessage.setMsgType(SendMsgTypeEnum.news.toString());
		newsMessage.setArticleCount(articles.size());
		newsMessage.setArticles(articles);

		//将图文消息对象转换为xml文件格式字符串
		result = MessageUtil.newsMessageToXml(newsMessage);

		return result;
	}


	private String getDateStr(String secods){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		return sdf.format(new Date(Long.valueOf(secods+"000"))); 

	}

}
