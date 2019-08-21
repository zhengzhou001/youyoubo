package com.chuxi.util.vo.menu;

/**
 * @Desc:   菜单button
 */
public class MenuButton {
	/**
	 * type 是 菜单的响应动作类型 name 是 菜单标题，不超过16个字节，子菜单不超过40个字节 key click等点击类型必须
	 * 菜单KEY值，用于消息接口推送，不超过128字节 url view类型必须 网页链接，员工点击菜单可打开链接，不超过256字节
	 */
	private String type;
	private String name;
	private String key;
	private String url;

	private MenuSubButton[] sub_button;

	public MenuSubButton[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(MenuSubButton[] sub_button) {
		this.sub_button = sub_button;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
