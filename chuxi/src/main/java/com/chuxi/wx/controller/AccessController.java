package com.chuxi.wx.controller;

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

import com.chuxi.config.BaseConfig;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;

@Controller
@RequestMapping("/")
@Scope("prototype")
public class AccessController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired 
	private BaseConfig baseConfig;

	@Autowired
	private WxMpService wxMpService;
	 


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
            //消息处理
            String messageType=inMessage.getMsgType();   //消息类型
            String fromUser=inMessage.getFromUser();
            String touser=inMessage.getToUser();
            String content=inMessage.getContent();
            // 如果是明文传输 ，保存 微信接收 信息,保存到数据库，用于微信消息管理中的客服互动
            //wXLogService.doSaveReceiveLog(inMessage);
            this.logger.info("\n保存微信接受信息WxMpXmlMessage：\n{}", inMessage);
            WxMpXmlOutMessage outMessage = WxMpXmlOutTextMessage.TEXT().toUser(fromUser).fromUser(touser)
            		.content(content).build();
            
            if (outMessage == null) {
                return "";
            }
 
            out = outMessage.toXml();
            // 日志出口时，保存微信发出去的XML（给用户）
            //wXLogService.doSaveMsgLogOut(outMessage);
           this.logger.info("\n保存微信输出日志WxMpXmlOutMessage信息：\n{}", outMessage);
           
        }
        this.logger.debug("\n组装回复信息：{}", out);
 
        return out;
	}


	public static void main(String[] args) {
		System.out.println(1);

	}

}
