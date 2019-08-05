package com.youyoubo.wx.util;

import org.apache.http.entity.StringEntity;

import com.youyoubo.wx.util.enu.SendMsgTypeEnum;
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
		
		/*String token = AccessTokenController.getInstance().getAccess_token();
		String url = CreateMenu.GetUserInfoUrl;
		url = String.format(url,token,"o_uZNwBSDkW7qD6HyHyNUh3P44gA");
		String json = HttpRequestUtil.httpGet(url);
		System.out.println(json);*/
		
		String token = AccessTokenController.getInstance().getAccess_token();
   		createMenu(token);
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
		one.setName("美容美发");
		//one.setType("click");
		//one.setKey("menu1");
		
		
		MenuSubButton one_subButton= new MenuSubButton();
		one_subButton.setName("价格");
		one_subButton.setUrl("http://2h240448c1.51mypc.cn/html/price/price.html");
		one_subButton.setType("view");
		
		
		MenuSubButton one_subButton2= new MenuSubButton();
		one_subButton2.setName("位置");
		one_subButton2.setUrl("http://2h240448c1.51mypc.cn/html/contact/contact.html");
		one_subButton2.setType("view");
		one_subButton2.setKey("");
		
		one.setSub_button(new MenuSubButton[]{one_subButton,one_subButton2});
		// 
		MenuButton two = new MenuButton();
		two.setName("会员");
		two.setType("click");
		two.setKey("menu2");
		//
		MenuButton three = new MenuButton();
		three.setName("现场直播");
		three.setType("click");
		three.setKey("menu3");
		
		
		
		//将一级菜单添加到菜单中
		menusVO.setButton(new MenuButton[] {one,two,three});
		
		  
		return menusVO;
	}
}
