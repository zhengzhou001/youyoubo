package com.youyoubo.wx.util.vo;

/**  
 * @Title: VideoMessage.java
 * @Description: 回复视频消息
 */
public class VideoMessage extends BaseMessage {

	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		this.Video = video;
	}
}
