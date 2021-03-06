package com.server.model;

import java.util.HashMap;

public class Message {
	private byte type;// 消息类型
	private byte encode;// 数据编码格式。已定义：0：UTF-8，1：GBK，2：GB2312，3：ISO8859-1
	private byte enzip;// 加密类型。0表示不加密
	private int length;// 消息体长度
	private long msg_id;// 消息id
	private HashMap<String, String> content = new HashMap<String, String>();

	public Message() {
		this.encode = 1;
		this.enzip = 1;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public byte getEncode() {
		return encode;
	}

	public void setEncode(byte encode) {
		this.encode = encode;
	}

	public byte getEnzip() {
		return enzip;
	}

	public void setEnzip(byte enzip) {
		this.enzip = enzip;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public long getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(long msg_id){ this.msg_id = msg_id; }

	public HashMap<String, String> getContent() {
		return content;
	}

	public void setContent(HashMap<String, String> content) {
		this.content = content;
	}

	public void setParam(String key, String value) {
		content.put(key, value);
	}

	public String get(String key) {
		return content.get(key);
	}

	@Override
	public String toString() {
		return "Message{" +
				"type=" + type +
				", encode=" + encode +
				", enzip=" + enzip +
				", length=" + length +
				", msg_id=" + msg_id +
				", content=" + content +
				'}';
	}
}
