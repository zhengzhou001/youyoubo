package com.chuxi.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.chuxi.config.BaseConfig;
 
/**  
 * @Description: 获取access token和刷新的中控服务器 
 */
@Component
public class AccessTokenController {
@Autowired
BaseConfig baseConfig;
	private static AccessTokenController instance;
	
	//access token凭证
	private String access_token;
	//凭证有效时间，单位：秒
	private long expires_in;
	//调用接口成功时间
	private long success_time;
	
	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(long expires_in) {
		this.expires_in = expires_in;
	}

	public synchronized  AccessTokenController getInstance() {
		if(instance == null) {
			instance = getAccessToken();
		} else {
			if((System.currentTimeMillis() / 1000 - instance.success_time) > (instance.expires_in - 1800)) {
				instance = getAccessToken();
			}
		}
		
		return instance;
	}
	
	/**
	 * @Desc:  获取token
	 * @param  @return
	 * @return AccessTokenController
	 */
	public  AccessTokenController getAccessToken() {
		String url = BaseConfig.AccessTokenUrl ;
		url = String.format(url,baseConfig.getAppID(),baseConfig.getAppSecret());
		String json = HttpRequestUtil.httpGet(url);
		instance = JSON.parseObject(json, AccessTokenController.class);
		instance.success_time = System.currentTimeMillis() / 1000;
		
		return instance;
	}
	
}
