package com.youyoubo.wx.util.vo;

/**  
 * @Title: Voice.java
 * @Description: 回复语音消息中的Voice类
 */
public class Voice {

	//通过素材管理接口上传多媒体文件，得到的id
	private long MediaId;

	public long getMediaId() {
		return MediaId;
	}

	public void setMediaId(long mediaId) {
		this.MediaId = mediaId;
	}
	
}
