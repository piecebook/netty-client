package com.pb.client.constant;

public class PBCONSTANT {
	public static final byte LOGIN_FLAG = 1;
	public static final byte LOGIN_REPLY_FLAG = 11;
	public static final byte MESSAGE_FLAG = 2;
	public static final byte MESSAGE_REPLY_FLAG = 21;
	public static final byte LOGOUT_FLAG =3;



	public static final String PING = null;
	public static final String LOGIN = "login";
	public static final String LOGIN_REPLY = "login_reply";
	public static final String MESSAGE = "message";
	public static final String SYSTEM = "PB_sys";
	public static final String SUCCESS = "success";

	public static final String MESSAGE_REPLY = "message_reply";
	public static final String USER_OFFLINE = "user has off line";

	public static volatile int flag = 0;
	public static volatile String user = "guest";
}
