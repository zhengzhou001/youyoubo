package com.chuxi.access.entity;

import com.tcwy.distribute.entity.BaseEntity;

public class WxMessageListEntity extends BaseEntity{
	private String ID;
	private String FROMUSERNAME;
	private String TOUSERNAME;
	private String EVENT;
	private String EVENTKEY;
	private String MSGTYPE;
	private String CREATETIME;
	
	 
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getFROMUSERNAME() {
		return FROMUSERNAME;
	}
	public void setFROMUSERNAME(String fROMUSERNAME) {
		FROMUSERNAME = fROMUSERNAME;
	}
	public String getTOUSERNAME() {
		return TOUSERNAME;
	}
	public void setTOUSERNAME(String tOUSERNAME) {
		TOUSERNAME = tOUSERNAME;
	}
	public String getEVENT() {
		return EVENT;
	}
	public void setEVENT(String eVENT) {
		EVENT = eVENT;
	}
	public String getEVENTKEY() {
		return EVENTKEY;
	}
	public void setEVENTKEY(String eVENTKEY) {
		EVENTKEY = eVENTKEY;
	}
	public String getMSGTYPE() {
		return MSGTYPE;
	}
	public void setMSGTYPE(String mSGTYPE) {
		MSGTYPE = mSGTYPE;
	}
	public String getCREATETIME() {
		return CREATETIME;
	}
	public void setCREATETIME(String cREATETIME) {
		CREATETIME = cREATETIME;
	}
	
	
	

}
