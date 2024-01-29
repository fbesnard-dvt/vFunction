package com.oracle.medrec.chat;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * Web socket server end point of patients of MedRec chat room based on
 * {@link ChatService}.
 * 
 * @author Xiaojun Wu. <br>
 *         Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@ServerEndpoint("/patient/{id}/{name}")
public class PatientChatWebSocket implements RequestMessageType {

    private ChatService service = ChatService.getInstance();

    @OnOpen
    public void onOpen(Session session, EndpointConfig conf,
            @PathParam("id") String id, @PathParam("name") String name) {
        session.getUserProperties().put("id", id);
        session.getUserProperties().put("name", name);
        service.patientLogin(session, id, name);
        session.setMaxIdleTimeout(120000);
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        JsonReader jsonReader = Json.createReader(new StringReader(message));
        JsonObject json = jsonReader.readObject();
        String type = json.getString("type");
        if (type.equals(TYPE_LIST)) {
            service.getAllOnlinePhysicans(session);
        } else if (type.equals(TYPE_JOIN)) {
            service.patientJoin(session, json.getString("room_id"));
        } else if (type.equals(TYPE_LEAVE)) {
            service.patientleave(session);
        } else if (type.equals(TYPE_CHAT)) {
            String msg = json.getString("message");
            if (msg == null || msg.equals("")) {
                return;
            }
            service.patientChat(session, msg);
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        service.patientLogout(session);
    }

    @OnError
    public void error(Session session, Throwable t) {
        t.printStackTrace();
    }

}
