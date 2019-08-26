package com.chuxi.config;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
 
@Component
public class WeChatMpConfig {
	@Autowired
	private BaseConfig baseConfig;
	
    @Bean
    public WxMpService wxMpService(){
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }
 
    @Bean
    public WxMpConfigStorage wxMpConfigStorage(){
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId(baseConfig.getAppID());
        wxMpConfigStorage.setSecret(baseConfig.getAppSecret());
        wxMpConfigStorage.setToken(baseConfig.getToken());
        wxMpConfigStorage.setTmpDirFile(new File(baseConfig.getTmpFilePath()));
        return wxMpConfigStorage;
    }
}