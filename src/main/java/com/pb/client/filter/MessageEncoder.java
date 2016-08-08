package com.pb.client.filter;

import com.pb.client.util.PBProtocol;
import com.server.model.Message;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.ByteBuffer;

public class MessageEncoder extends MessageToByteEncoder<Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf outbuf)
            throws Exception {
        byte[] body = PBProtocol.Encode(msg.getEncode(), msg.getEnzip(), msg.getContent());
        int body_length = body.length;
        System.out.println(body_length);
        /*ByteBuffer header = ByteBuffer.allocate(body_length+8);
        header.put(msg.getEncode());
        header.put(msg.getEnzip());
        header.put(msg.getType());
        header.put(msg.getExtend());
        header.putInt(body_length);
        header.put(body);
        header.flip();*/
        outbuf.writeInt(body_length);
        outbuf.writeByte(msg.getEncode());
        outbuf.writeByte(msg.getEnzip());
        outbuf.writeByte(msg.getType());
        outbuf.writeByte(msg.getExtend());

        outbuf.writeBytes(body);
        System.out.println(outbuf.readableBytes());
        ctx.flush();
    }

}
