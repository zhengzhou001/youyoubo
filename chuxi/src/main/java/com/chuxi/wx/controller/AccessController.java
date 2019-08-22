package com.chuxi.wx.controller;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.core.tools.BaseTools;
import com.chuxi.config.BaseConfig;
import com.chuxi.wx.service.IWX_MSGService;
import com.chuxi.wx.service.IWX_SUBSCRIBEService;
import com.chuxi.wx.service.IWX_USERService;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Controller
@RequestMapping("/")
@Scope("prototype")
public class AccessController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired 
	private BaseConfig baseConfig;

	@Autowired
	private WxMpService wxMpService;

	@Autowired
	private IWX_MSGService WX_MSGService;

	@Autowired
	private IWX_SUBSCRIBEService WX_SUBSCRIBEService;
	
	@Autowired
	private IWX_USERService WX_USERService;



	@RequestMapping(value={"/access"}, method={RequestMethod.GET})
	@ResponseBody
	public String doGet(String signature,String timestamp,String nonce,String echostr){
		//微信接口验证
		this.logger.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature, timestamp, nonce, echostr);

		if (wxMpService.checkSignature(timestamp, nonce, signature)) {
			return echostr;
		} 
		return "";
	}
	@RequestMapping(value={"/access"}, method={RequestMethod.POST})
	@ResponseBody
	public String doPost(@RequestBody String requestBody, @RequestParam("signature") String signature, @RequestParam(name = "encrypt_type", required = false) String encType, @RequestParam(name = "msg_signature", required = false) String msgSignature,
			@RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce ){
		this.logger.info("\n接收微信请求：[signature=[{}], encType=[{}], msgSignature=[{}]," + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ", signature, encType, msgSignature, timestamp, nonce, requestBody);
		if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
			throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
		} 

		String out = null;
		if (encType == null) {
			// 明文传输的消息
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);


			//保存 微信接收 信息,保存到数据库，用于微信消息管理中的客服互动
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						WX_MSGService.insertWX_MSG(ArrayUtils.toMap(new Object[][]{
							{"TOUSER",inMessage.getToUser()},
							{"FROMUSER",inMessage.getFromUser()},
							{"CREATETIME",inMessage.getCreateTime()},
							{"MSGTYPE",inMessage.getMsgType()},
							{"CONTENT",inMessage.getContent()},
							{"MENUID",inMessage.getMenuId()},
							{"MSGID",inMessage.getMsgId()},
							{"PICURL",inMessage.getPicUrl()},
							{"MEDIAID",inMessage.getMediaId()},
							{"FORMAT",inMessage.getFormat()},
							{"THUMBMEDIAID",inMessage.getThumbMediaId()},
							{"LOCATIONX",inMessage.getLocationX()},
							{"LOCATIONY",inMessage.getLocationY()},
							{"SCALE",inMessage.getScale()},
							{"LABEL",inMessage.getLabel()},
							{"TITLE",inMessage.getTitle()},
							{"DESCRIPTION",inMessage.getDescription()},
							{"URL",inMessage.getUrl()},
							{"EVENT",inMessage.getEvent()},
							{"EVENTKEY",inMessage.getEventKey()},
							{"TICKET",inMessage.getTicket()},
							{"LATITUDE",inMessage.getLatitude()},
							{"LONGITUDE",inMessage.getLongitude()},
							{"PRECISION",inMessage.getPrecision()},
							{"RECOGNITION",inMessage.getRecognition()},
						}));
					} catch (Exception e) {
						logger.error(e.getMessage());
					}


				}
			}).start();
			//消息处理
			String messageType=inMessage.getMsgType();   //消息类型
			String fromUser=inMessage.getFromUser();
			String touser=inMessage.getToUser();

			WxMpXmlOutMessage outMessage =null ;
			if (messageType.equals(WxConsts.XmlMsgType.EVENT)) {
				//事件
				String event = inMessage.getEvent();
				if (event.equals(WxConsts.EventType.SUBSCRIBE)) {
					//关注事件
					int count=WX_SUBSCRIBEService.selectWX_SUBSCRIBECount(ArrayUtils.toMap(new Object[][]{
						{"TOUSER",touser},
						{"FROMUSER",fromUser},
					}));
					if (count>0) {
					}else{
						try {
							WX_SUBSCRIBEService.insertWX_SUBSCRIBE(ArrayUtils.toMap(new Object[][]{
								{"TOUSER",touser},
								{"FROMUSER",fromUser},
							}));
						} catch (Exception e) {
							logger.error(e.getMessage());
							return "";
						}
					}
					//异步获取用户信息
					//异步获取用户基本信息
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							 try {
							WxMpUser user =	wxMpService.getUserService().userInfo(fromUser);
							WX_USERService.deleteWX_USER(ArrayUtils.toMap(new Object[][]{
								{"OPENID",user.getOpenId()}
							}));
							WX_USERService.insertWX_USER(ArrayUtils.toMap(new Object[][]{
								{"SUBSCRIBE",user.getSubscribe()},
								{"OPENID",user.getOpenId()},
								{"NICKNAME",user.getNickname()},
								{"SEXDESC",user.getSexDesc()},
								{"SEX",user.getSex()},
								{"LANGUAGE",user.getLanguage()},
								{"CITY",user.getCity()},
								{"PROVINCE",user.getProvince()},
								{"COUNTRY",user.getCountry()},
								{"HEADIMGURL",user.getHeadImgUrl()},
								{"SUBSCRIBETIME",user.getSubscribeTime()},
								{"UNIONID",user.getUnionId()},
								{"REMARK",user.getRemark()},
								{"GROUPID",user.getGroupId()},
								{"TAGIDS",StringUtils.join(user.getTagIds(),",")},
								{"PRIVILEGES",StringUtils.join(user.getPrivileges(),",")},
								{"SUBSCRIBESCENE",user.getSubscribeScene()},
								{"QRSCENE",user.getQrScene()},
								{"QRSCENESTR",user.getQrSceneStr()},
							}));
							} catch (Exception e) {
								logger.error(e.getMessage());
							}
							
						}
					}).start();
					outMessage = WxMpXmlOutTextMessage.TEXT().toUser(fromUser).fromUser(touser)
							.content("感谢关注").build();
				}else if (event.equals(WxConsts.EventType.UNSUBSCRIBE)) {
					//取消关注
					try {
						WX_SUBSCRIBEService.deleteWX_SUBSCRIBE(ArrayUtils.toMap(new Object[][]{
							{"TOUSER",touser},
							{"FROMUSER",fromUser},
						}));
					} catch (Exception e) {
						logger.error(e.getMessage());
						return "";
					}
					outMessage = WxMpXmlOutTextMessage.TEXT().toUser(fromUser).fromUser(touser)
							.content("取消关注").build();
				}
			}else if (messageType.equals(WxConsts.XmlMsgType.TEXT)) {
				outMessage = WxMpXmlOutTextMessage.TEXT().toUser(fromUser).fromUser(touser)
						.content(inMessage.getContent()).build();
			}else{
				return "";
			}




			if (outMessage == null) {
				return "";
			}

			out = outMessage.toXml();
			// 日志出口时，保存微信发出去的XML（给用户）
			//wXLogService.doSaveMsgLogOut(outMessage);
			this.logger.info("\n保存微信输出日志WxMpXmlOutMessage信息：\n{}", outMessage);

		}
		this.logger.info("\n组装回复信息：{}", out);

		return out;
	}


	public static void main(String[] args) {
		System.out.println(1);

	}

}
