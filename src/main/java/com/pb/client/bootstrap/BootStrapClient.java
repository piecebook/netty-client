package com.pb.client.bootstrap;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.concurrent.TimeUnit;
import com.pb.client.constant.PBCONSTANT;
import com.pb.client.handler.clientHandler;
import com.server.model.loginMsg;
import io.netty.handler.timeout.IdleStateHandler;

public class BootStrapClient {
	private SocketChannel channel = null;
	private static final int READ_IDEL_TIME_OUT = 4; // 读超时
	private static final int WRITE_IDEL_TIME_OUT = 5;// 写超时
	private static final int ALL_IDEL_TIME_OUT = 7; // 所有超时


	public boolean login(String user, String pwd) {
		if (channel == null) {
			System.out.println("Connect first!");
			return false;
		} else {
			loginMsg msg = new loginMsg();
			msg.setTitle("login");
			msg.setType("login");
			msg.setReceiver_uid("pb system");

			msg.setTime(System.currentTimeMillis());
			msg.setSender_uid(user);
			msg.setContent(pwd);
			System.out.println(channel.remoteAddress());

			// login msg
			msg.setDeviceId(user + "@ver1.0");
			msg.setClientVersion("ver1.0");
			msg.setChannel("android");
			msg.setDeviceModel("mx2");
			msg.setSystemVersion("android 5.0");
			channel.writeAndFlush(msg);
			System.out.println("login:"+msg.toString());
			while (true) {
				// System.out.println(PBCONSTANT.flag);
				if (PBCONSTANT.flag == 1) {
					PBCONSTANT.user = user;
					return true;
				} else if (PBCONSTANT.flag == -1) {
					PBCONSTANT.flag = 0;
					return false;
				}
			}
		}
	}

	public void connect(String host, int port) {
		EventLoopGroup workergroup = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(workergroup);
			bootstrap.channel(NioSocketChannel.class);
			bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
			bootstrap.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel channel)
						throws Exception {
					channel.pipeline().addLast(new ObjectEncoder());
					// channel.pipeline().addLast(new MessageDecoder());
					channel.pipeline().addLast(
							new ObjectDecoder(ClassResolvers
									.cacheDisabled(null)));
					channel.pipeline().addLast(new IdleStateHandler(READ_IDEL_TIME_OUT,WRITE_IDEL_TIME_OUT, ALL_IDEL_TIME_OUT, TimeUnit.SECONDS));
					channel.pipeline().addLast(new clientHandler());

				}

			});
			ChannelFuture future = bootstrap.connect(host, port).sync();
			if (future.isSuccess()) {
				channel = (SocketChannel) future.channel();
				System.out.println("Connect Server:" + channel.remoteAddress() +" Success!");
			}
		} catch (Exception e) {
			System.out.println("Connect Server Fail!");
			//e.printStackTrace();
			System.exit(-1);
		}

	}

	public SocketChannel getChannel() {
		return channel;
	}
}
