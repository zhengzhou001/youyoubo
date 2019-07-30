package com.youyoubo.wx.util.vo;

/**  
 * @Title: Image.java
 * @Description: 回复图片消息中的Image类
 */
public class Image {

	//通过素材管理接口上传多媒体文件，得到的id
	private long MediaId;

	public long getMediaId() {
		return MediaId;
	}

	public void setMediaId(long mediaId) {
		this.MediaId = mediaId;
	}
}
