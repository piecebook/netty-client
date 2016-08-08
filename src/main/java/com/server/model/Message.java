package com.server.model;


public class Message{
	private String type;// 消息类型
	private String title;// 消息标题
	private String content;// 消息内容
	private Long time;// 发送时间
	private String sender_uid;// 发送者的id
	private String receiver_uid;// 接受者的id

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getSender_uid() {
		return sender_uid;
	}

	public void setSender_uid(String sender_uid) {
		this.sender_uid = sender_uid;
	}

	public String getReceiver_uid() {
		return receiver_uid;
	}

	public void setReceiver_uid(String receiver_uid) {
		this.receiver_uid = receiver_uid;
	}

	@Override
	public String toString() {
		return "Message{" +
				"type='" + type + '\'' +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", time=" + time +
				", sender_uid='" + sender_uid + '\'' +
				", receiver_uid='" + receiver_uid + '\'' +
				'}';
	}
}
