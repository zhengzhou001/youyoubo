package com.youyoubo.wx;


import org.apache.http.entity.StringEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.youyoubo.wx.config.BaseConfig;
import com.youyoubo.wx.util.AccessTokenController;
import com.youyoubo.wx.util.HttpRequestUtil;
import com.youyoubo.wx.util.vo.menu.MenuButton;
import com.youyoubo.wx.util.vo.menu.MenuSubButton;
import com.youyoubo.wx.util.vo.menu.MenusVO;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("pro")
public class CreateMenu {
	@Autowired
	private BaseConfig baseConfig;
	@Autowired
	AccessTokenController accessTokenController;
 
	@Test
	public void createMenu(){
		String token = accessTokenController.getInstance().getAccess_token();
   		createMenu(token);
	}
	
	public static void main(String[] args) {
		System.out.println(2);
		
		/*String token = AccessTokenController.getInstance().getAccess_token();
		String url = CreateMenu.GetUserInfoUrl;
		url = String.format(url,token,"o_uZNwBSDkW7qD6HyHyNUh3P44gA");
		String json = HttpRequestUtil.httpGet(url);
		System.out.println(json);*/
		
		/*String token = AccessTokenController.getInstance().getAccess_token();
   		createMenu(token);*/
 	}
	
	
	/**
	 * @Desc:  创建菜单
	 * @param  @param token
	 * @param  @return
	 * @return String
	 */
	private  String createMenu(String token) {
		String resutlStr = null;
		try {
			//格式化url，将获取到的token传入参数
			String url = String.format(BaseConfig.MENU_CREATE_URL, token);
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
	private  MenusVO getMenus() {
		MenusVO menusVO = new MenusVO();
		
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
		
		
		
		//将一级菜单添加到菜单中
		menusVO.setButton(new MenuButton[] {one,two,three});
		
		  
		return menusVO;
	}
}
