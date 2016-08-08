package com.pb.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.pb.client.bootstrap.BootStrapClient;
import com.server.model.Message;

public class nettyClient {
	private static final int PORT = 8000;
	//private static final String HOST = "172.26.84.27";
	private static final String HOST = "localhost";

	public static void main(String[] args) {
		BootStrapClient client = new BootStrapClient();
		client.connect(HOST, PORT);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		String user = "";
		String pwd = "";
		while (true) {
			try {
				System.out.println("user:");
				user = reader.readLine();
				System.out.println("pwd:");
				pwd = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (client.login(user, pwd))
				break;
		}
		while (true) {
			String receiver = "";
			String content = "";
			try {
				System.out.println("to:");
				receiver = reader.readLine();
				System.out.println("message:");
				content = reader.readLine();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Message msg = new Message();
			msg.setReceiver_uid(receiver);
			msg.setContent(content);
			msg.setSender_uid(user);
			msg.setTitle("message");
			msg.setType("message");
			msg.setTime(System.currentTimeMillis());
			client.getChannel().writeAndFlush(msg);

		}
	}

}
