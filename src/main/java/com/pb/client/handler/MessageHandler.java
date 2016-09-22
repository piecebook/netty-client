package com.pb.client.handler;

import com.pb.server.session.PBSession;
import com.server.constant.PBCONSTANT;
import com.server.model.Message;

/**
 * Created by DW on 2016/8/8.
 */
public class MessageHandler {
    public void process(PBSession session, Message msg) {
        System.out.println("From " + msg.get("s_uid") + " :"
                + msg.toString());
        Message reply = new Message();
        String msg_key = msg.get("s_uid")+ "-" + msg.getMsg_id();
        reply.setParam("msg_key", msg_key);
        reply.setMsg_id(PBCONSTANT.getMsg_id());
        reply.setType(PBCONSTANT.ACK_FLAG);
        session.write(reply);
    }
}
