package com.chuxi.wx.controller;

import java.net.URLEncoder;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chuxi.config.BaseConfig;
import com.chuxi.user.service.IUSER_INFOService;
import com.chuxi.wx.service.IWX_USERService;
import com.tcwy.distribute.controller.BaseController;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Controller
@RequestMapping("/oauth")
@Scope("prototype")
public class OauthController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BaseConfig baseConfig;
	@Autowired
	private IUSER_INFOService USER_INFOService ;
	@Autowired
	private WxMpService wxMpService;
	@Autowired
	private IWX_USERService WX_USERService ;

	@GetMapping("/authorize")
	public String authorize(@RequestParam("returnUrl") String returnUrl){
		String url = "http://"+baseConfig.getYm()+"/oauth/userInfo";
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
		//查询openid是否已经有基本信息
		int count =WX_USERService.selectWX_USERCount(ArrayUtils.toMap( new String[][]{
			{"OPENID",openId},
		}));

		if (count==0) {
			//没有基本信息，获取基本信息
			WxMpUser user =  wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, "zh_CN");
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
		}else{
			
		}
		return "redirect:" + returnUrl + "?OPENID=" + openId;
	}
}
