package com.chuxi;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.chuxi.config.BaseConfig;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.mp.api.WxMpService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("pro")
public class CreateMenu {
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
		button3.setName("衣服鞋帽");
		button3.setSubButtons(Arrays.asList(button1_2,button1_1));
		
		
		/*MenusVO menusVO = new MenusVO();

		//==================================活动订阅==================================
		// 
		MenuButton one = new MenuButton();
		one.setName("美容美发");
		//one.setType("click");
		//one.setKey("menu1");


		MenuSubButton one_subButton= new MenuSubButton();
		one_subButton.setName("价格");
		one_subButton.setUrl("http://"+baseConfig.getYm()+"/html/price/price.html");
		one_subButton.setType("view");


		MenuSubButton one_subButton2= new MenuSubButton();
		one_subButton2.setName("联系我们");
		one_subButton2.setUrl("http://"+baseConfig.getYm()+"/html/contact/contact.html");
		one_subButton2.setType("view");
		one_subButton2.setKey("");

		one.setSub_button(new MenuSubButton[]{one_subButton,one_subButton2});
		// 
		MenuButton two = new MenuButton();
		two.setName("会员");
		//two.setType("click");
		//two.setKey("menu2");

		MenuSubButton two_subButton= new MenuSubButton();
		two_subButton.setName("会员介绍");
		two_subButton.setUrl("http://"+baseConfig.getYm()+"/html/member/info.html");
		two_subButton.setType("view");


		MenuSubButton two_subButton2= new MenuSubButton();
		two_subButton2.setName("会员办理");
		two_subButton2.setUrl("http://"+baseConfig.getYm()+"/wechat/authorize?returnUrl=http://"+baseConfig.getYm()+"/html/member/register.html");
		two_subButton2.setType("view");

		MenuSubButton two_subButton3= new MenuSubButton();
		two_subButton3.setName("会员信息");
		two_subButton3.setUrl("http://"+baseConfig.getYm()+"/wechat/authorize?returnUrl=http://"+baseConfig.getYm()+"/html/member/memberInfo.html");
		two_subButton3.setType("view");

		//openid获取
		two.setSub_button(new MenuSubButton[]{two_subButton,two_subButton2,two_subButton3});

		//
		MenuButton three = new MenuButton();
		three.setName("现场直播");
		three.setType("click");
		three.setKey("menu3");
		 */
		/*	MenuSubButton three_subButton= new MenuSubButton();
		three_subButton.setName("测试");
		three_subButton.setUrl("http://"+baseConfig.getYm()+"/html/member/test.html");
		three_subButton.setType("view");


		three.setSub_button(new MenuSubButton[]{three_subButton});
		 */
	 
		menu.setButtons(Arrays.asList(button1,button2,button3));

		return menu;
	}
}
