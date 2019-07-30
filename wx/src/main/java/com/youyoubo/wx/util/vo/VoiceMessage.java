package com.youyoubo.wx.util.vo;

/**  
 * @Title: VoiceMessage.java
 * @Description: 回复语音消息
 */
public class VoiceMessage extends BaseMessage {

	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		this.Voice = voice;
	}
	
}
