package com.pb.client.filter;

import java.util.List;

import com.pb.client.util.ByteObjConverter;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class MessageDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf inbuf,
			List<Object> out) throws Exception {
		System.out.println("Message decode:");
		byte[] bytes = new byte[inbuf.readableBytes()];
		inbuf.readBytes(bytes);
		out.add(ByteObjConverter.ByteToObject(bytes));
	}

}
