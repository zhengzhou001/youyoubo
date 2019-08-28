package com.chuxi;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.chuxi.config.BaseConfig;
import com.chuxi.dm.service.IDM_CITYService;
import com.chuxi.dm.service.IDM_PROVINCEService;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.mp.api.WxMpService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class CreateMenu {
	@Autowired
	private IDM_PROVINCEService DM_PROVINCEService;
	@Autowired
	private IDM_CITYService DM_CITYService;
	@Autowired
	private BaseConfig baseConfig;
	@Autowired
	WxMpService wxMpService;
	@Test
	public void createMenu() throws Exception{
		WxMenu	 menu = getMenus();
		String ret = wxMpService.getMenuService().menuCreate(menu);
		System.out.println("=========");
		System.out.println(ret);
	}


	/**
	 * @Desc:  获取菜单，手动设置
	 * @param  @return
	 * @return MenusVO
	 */
	private  WxMenu getMenus() {
		WxMenu menu = new WxMenu();
		WxMenuButton button1 =new WxMenuButton();
		button1.setName("除夕网络");

		WxMenuButton button1_1 =new WxMenuButton();
		button1_1.setName("联系我们");
		button1_1.setType(WxConsts.MenuButtonType.VIEW);
		button1_1.setUrl("http://"+baseConfig.getYm()+"/html/wx/contact/contact.html");
		
		
		WxMenuButton button1_2 =new WxMenuButton();
		button1_2.setName("价格介绍");
		button1_2.setType(WxConsts.MenuButtonType.VIEW);
		button1_2.setUrl("http://"+baseConfig.getYm()+"/html/wx/price/price.html");
		
		
		
		
		button1.setSubButtons(Arrays.asList(button1_2,button1_1));
		
		
		
		WxMenuButton button2 =new WxMenuButton();
		button2.setName("休闲娱乐");
		button2.setSubButtons(Arrays.asList(button1_2,button1_1));
		
		WxMenuButton button3 =new WxMenuButton();
		button3.setName("会员事宜");
		
		
		
		WxMenuButton button3_1 =new WxMenuButton();
		button3_1.setName("会员信息");
		button3_1.setType(WxConsts.MenuButtonType.VIEW);
		button3_1.setUrl("http://"+baseConfig.getYm()+"/oauth/authorize?returnUrl=http://"+baseConfig.getYm()+"/html/user/preInfo.html");
		
		
		button3.setSubButtons(Arrays.asList(button3_1));
		
		menu.setButtons(Arrays.asList(button1,button2,button3));

		return menu;
	}
	
	
	@Test
	public void createCity() throws Exception{
		 List<Map> provinceList = DM_PROVINCEService.selectDM_PROVINCE(new HashMap());
		 List<Map> provinceList2 = new ArrayList<>();
		 for (int i = 0; i < provinceList.size(); i++) {
			Map map = provinceList.get(i);
			Map map2 = new HashMap<>();
			map2.put("value", MapUtils.getString(map, "ID"));
			map2.put("text", MapUtils.getString(map, "NAME"));
			List citylist = 	 DM_CITYService.selectDM_CITY(ArrayUtils.toMap(new String[][]{
				{"PROVINCE_ID",MapUtils.getString(map, "ID")}
			}));	
			List citylist2 = new ArrayList<>();
			for (int j = 0; j < citylist.size(); j++) {
				Map map3 = (Map) citylist.get(j);
				Map map4 = new HashMap<>();
				map4.put("value", MapUtils.getString(map3, "ID"));
				map4.put("text", MapUtils.getString(map3, "NAME"));
				citylist2.add(map4);
			}
			map2.put("children", citylist2);
			provinceList2.add(map2);
		}
		 
		 System.out.println(JSON.toJSONString(provinceList2));
		 
	}
}
