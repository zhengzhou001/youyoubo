package com.chuxi.util.vo.menu;


/**
 * @Desc:   菜单vo
 */
public class MenusVO {
	/**
	 * access_token 是 调用接口凭证 agentid 是 企业应用的id，整型。可在应用的设置页面查看 button 是
	 * 一级菜单数组，个数应为1~3个 sub_button 否 二级菜单数组，个数应为1~5个 type 是 菜单的响应动作类型 name 是
	 * 菜单标题，不超过16个字节，子菜单不超过40个字节 key click等点击类型必须 菜单KEY值，用于消息接口推送，不超过128字节 url
	 * view类型必须 网页链接，员工点击菜单可打开链接，不超过256字节
	 */
	private String agentid;

	private MenuButton[] button;

	public MenuButton[] getButton() {
		return button;
	}

	public void setButton(MenuButton[] button) {
		this.button = button;
	}

	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}

}
