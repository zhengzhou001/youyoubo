package com.chuxi.wx.access.action;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tcwy.distribute.controller.BaseController;
import com.tcwy.distribute.result.BaseResult;
import com.chuxi.config.BaseConfig;
import com.chuxi.util.HttpRequestUtil;

@RestController
@RequestMapping("/oauth")
@Scope("prototype")
public class OauthAction extends BaseController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BaseConfig baseConfig;
	
	@RequestMapping(value={"/getUserIfo"}, method={RequestMethod.POST})
	public BaseResult getUserIfo(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			String access_token_url ="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+baseConfig.getAppID()+
					"&secret="+baseConfig.getAppSecret()+"&code="+MapUtils.getString(map, "code")+"&grant_type=authorization_code";
			String access_token_json = HttpRequestUtil.httpGet(access_token_url);
			/**
			 * {
    "access_token":"ACCESS_TOKEN",
    "expires_in":7200,
    "refresh_token":"REFRESH_TOKEN",
    "openid":"OPENID",
    "scope":"SCOPE" 
 }
			 */
			JSONObject access_token_jsonObject = JSON.parseObject(access_token_json);
			String user_info_url = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token_jsonObject.getString("access_token")+
					"&openid="+access_token_jsonObject.getString("openid")+"&lang=zh_CN";
			/**
			 * {   
    "openid":" OPENID",
    " nickname": NICKNAME,
    "sex":"1",
    "province":"PROVINCE"
    "city":"CITY",
    "country":"COUNTRY",
    "headimgurl":       "http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46",
    "privilege":[ "PRIVILEGE1" "PRIVILEGE2"     ],
    "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
}
			 */
			String user_info_json = HttpRequestUtil.httpGet(user_info_url);
			JSONObject user_info_jsonObject = JSON.parseObject(user_info_json);
			result.data=user_info_jsonObject;
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
}
