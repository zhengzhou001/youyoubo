package com.chuxi.member.controller;
import java.net.URLEncoder;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chuxi.config.BaseConfig;
import com.chuxi.member.service.IWX_MEMBERService;
import com.chuxi.user.mapper.WX_USER_INFOMapper;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Controller
@RequestMapping("/wechat")
public class WechatController {
	@Autowired
	private BaseConfig baseConfig;
	
	private Logger logger = LoggerFactory.getLogger(WechatController.class);
	@Autowired
	private WxMpService wxMpService;
	@Autowired
	private IWX_MEMBERService WX_MEMBERService;
	

	@Autowired
	WX_USER_INFOMapper wx_USER_INFOMapper;

	@GetMapping("/authorize")
	public String authorize(@RequestParam("returnUrl") String returnUrl){
		String url = "http://"+baseConfig.getYm()+"/wechat/userInfo";
		String redirectURL = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
		return "redirect:" + redirectURL;
	}

	@GetMapping("/userInfo")
	public String userInfo(@RequestParam("code") String code,
			@RequestParam("state") String returnUrl) throws Exception {
		WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
		try {
			wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
		} catch (WxErrorException e) {
			logger.error("【微信网页授权】{}", e);
			throw new Exception(e.getError().getErrorMsg());
		}
		String openId = wxMpOAuth2AccessToken.getOpenId();

		//查询openid是否已经是会员
		int count = WX_MEMBERService.selectWX_MEMBERCount(ArrayUtils.toMap( new String[][]{
			{"OPENID",openId},
			{"GZHID",baseConfig.getAppID()}
		}));

		if (count==0) {
			//不是会员，需要注册
			WxMpUser user =  wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, "zh_CN");
			wx_USER_INFOMapper.updateWX_USER_INFO(ArrayUtils.toMap( new String[][]{
				{"OPENID",openId},
				{"GZHID",baseConfig.getAppID()},
				{"NICKNAME_NEW", user.getNickname()},
				{"SEX_NEW", user.getSex().toString()},
				{"LANGUAGE_NEW", user.getLanguage()},
				{"CITY_NEW", user.getCity()},
				{"PROVINCE_NEW", user.getProvince()},
				{"COUNTRY_NEW", user.getCountry()},
				{"HEADIMGURL_NEW", user.getHeadImgUrl()},
				{"REMARK_NEW", user.getRemark()}
			}));
		} 
		return "redirect:" + returnUrl + "?openid=" + openId+"&gzhid="+baseConfig.getAppID();
 	}
}