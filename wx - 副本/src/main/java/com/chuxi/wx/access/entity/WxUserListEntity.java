package com.chuxi.access.entity;

import com.tcwy.distribute.entity.BaseEntity;

public class WxUserListEntity extends BaseEntity{
	private String id;
	private String userId;
	private String gzhId;
	private String createDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGzhId() {
		return gzhId;
	}
	public void setGzhId(String gzhId) {
		this.gzhId = gzhId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
