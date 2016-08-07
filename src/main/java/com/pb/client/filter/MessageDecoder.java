package com.pb.client.filter;

import java.util.List;

import com.pb.client.util.PBProtocol;
import com.server.model.Message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class MessageDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf inbuf,
						  List<Object> out) throws Exception {
		int body_length = inbuf.readInt();
		byte encode = inbuf.readByte();
		byte enzip = inbuf.readByte();
		byte type = inbuf.readByte();
		byte extend = inbuf.readByte();
		Message msg = new Message();
		msg.setEncode(encode);
		msg.setEnzip(enzip);
		msg.setType(type);
		msg.setExtend(extend);
		msg.setLength(body_length);
		msg.setContent(PBProtocol.Decode(encode,enzip,inbuf));
		out.add(msg);
	}

}
