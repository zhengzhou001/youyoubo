package com.chuxi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseConfig {
 
	@Value("${wx.AppID}")
	private String AppID;

	@Value("${wx.AppSecret}")
	private String AppSecret;

	@Value("${wx.token}")
	private String token;
	
	@Value("${wx.mchId}")
	private String mchId;
	
	@Value("${wx.mchKey}")
	private String mchKey;
	
	@Value("${wx.subAppId}")
	private String subAppId;
	
	@Value("${wx.subMchId}")
	private String subMchId;
	
	@Value("${wx.keyPath}")
	private String keyPath;
	
	@Value("${wx.notifyUrl}")
	private String notifyUrl;
	
	@Value("${ym}")
	private String ym;
	
	@Value("${tmpFilePath}")
	private String tmpFilePath;
	 
	@Value("${filePath}")
	private String filePath;

	
	public String getTmpFilePath() {
		return tmpFilePath;
	}

	public void setTmpFilePath(String tmpFilePath) {
		this.tmpFilePath = tmpFilePath;
	}

	public String getAppID() {
		return AppID;
	}

	public void setAppID(String appID) {
		AppID = appID;
	}

	public String getAppSecret() {
		return AppSecret;
	}

	public void setAppSecret(String appSecret) {
		AppSecret = appSecret;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getMchKey() {
		return mchKey;
	}

	public void setMchKey(String mchKey) {
		this.mchKey = mchKey;
	}

	public String getSubAppId() {
		return subAppId;
	}

	public void setSubAppId(String subAppId) {
		this.subAppId = subAppId;
	}

	public String getSubMchId() {
		return subMchId;
	}

	public void setSubMchId(String subMchId) {
		this.subMchId = subMchId;
	}

	public String getKeyPath() {
		return keyPath;
	}

	public void setKeyPath(String keyPath) {
		this.keyPath = keyPath;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	
}
