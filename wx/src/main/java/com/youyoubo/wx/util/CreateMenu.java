package com.youyoubo.wx.util;

import org.apache.http.entity.StringEntity;

import com.youyoubo.wx.util.vo.menu.MenuButton;
import com.youyoubo.wx.util.vo.menu.MenuSubButton;
import com.youyoubo.wx.util.vo.menu.MenusVO;

public class CreateMenu {
	private static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
	public static String AccessTokenUrl ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
	
	public static String GetUserInfoUrl ="https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";
	
	
	public static String AppID ="wxae7179cb0002eee9";
	public static String AppSecret="90fe45ff46ce55e1f48cf1a0ba0437b3";
 	
	
	public static void main(String[] args) {
		
		String token = AccessTokenController.getInstance().getAccess_token();
		String url = CreateMenu.GetUserInfoUrl;
		url = String.format(url,token,"o_uZNwBSDkW7qD6HyHyNUh3P44gA");
		String json = HttpRequestUtil.httpGet(url);
		System.out.println(json);
		/*
		String token = AccessTokenController.getInstance().getAccess_token();
   		createMenu(token);*/
 	}
	
	
	/**
	 * @Desc:  创建菜单
	 * @param  @param token
	 * @param  @return
	 * @return String
	 */
	private static String createMenu(String token) {
		String resutlStr = null;
		try {
			//格式化url，将获取到的token传入参数
			String url = String.format(MENU_CREATE_URL, token);
			//获取菜单
			MenusVO menus = getMenus();
			
			//
			StringEntity se = HttpRequestUtil.convertToStringEntity(menus);
			//post方式请求接口，返回json状态码字符串
			resutlStr = HttpRequestUtil.httpPost(url, se);
			System.out.println(resutlStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resutlStr;
	}
 
	/**
	 * @Desc:  获取菜单，手动设置
	 * @param  @return
	 * @return MenusVO
	 */
	private static MenusVO getMenus() {
		MenusVO menusVO = new MenusVO();
		
		//==================================活动订阅==================================
		// 
		MenuButton one = new MenuButton();
		one.setName("菜单一");
		one.setType("click");
		one.setKey("hddy_msb");
		// 
		MenuButton two = new MenuButton();
		two.setName("菜单二");
		two.setType("click");
		two.setKey("cszb_msb");
		//
		MenuButton three = new MenuButton();
		three.setName("菜单三");
		three.setType("click");
		three.setKey("cszb_msba");
		
		
		
		//将一级菜单添加到菜单中
		menusVO.setButton(new MenuButton[] {one,two,three});
		
		  
		return menusVO;
	}
}
