package com.sgxy.o2o.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;


@ServerEndpoint("/webSocket/{uid}")
public class MyWebSocketHandler implements WebSocketHandler{
	public static final Map<Long, WebSocketSession> userSocketSessionMap;
	static {
		userSocketSessionMap = new HashMap<Long, WebSocketSession>();
	}
	@OnOpen
    public void onOpen(@PathParam("uid") String uid,Session session) throws IOException{
        System.err.println(uid);
    }
    
    //�ر�ʱִ��
    @OnClose
    public void onClose(){
        System.err.println("���ӶϿ�");
    }
    
    //�յ���Ϣʱִ��
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
//        session.getBasicRemote().sendText("�յ���������� "+Websocket.userId+" ����Ϣ "); //�ظ��û�
    	System.err.println("�յ���Ϣ��" + message);
    }
    
    //���Ӵ���ʱִ��
    @OnError
    public void onError(Session session, Throwable error){
        error.printStackTrace();
    }
	/**
	 * �������Ӻ�
	 */
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.err.println(session);
		String uid = (String) session.getAttributes().get("uid");
		System.err.println(uid);
//		if (userSocketSessionMap.get(uid) == null) {
//			userSocketSessionMap.put(uid, session);
//		}
	}
	/**
	 * ��Ϣ�����ڿͻ���ͨ��Websocket API���͵���Ϣ�ᾭ�����Ȼ�������Ӧ�Ĵ���
	 */
	public void handleMessage(WebSocketSession session,WebSocketMessage<?> message) throws Exception {
		if (message.getPayloadLength() == 0)
			return;
		
	}
 
	/**
	 * ��Ϣ���������
	 */
	public void handleTransportError(WebSocketSession session,Throwable exception) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		Iterator<Entry<Long, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
		// �Ƴ�Socket�Ự
		while (it.hasNext()) {
			Entry<Long, WebSocketSession> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				userSocketSessionMap.remove(entry.getKey());
				System.out.println("Socket�Ự�Ѿ��Ƴ�:�û�ID" + entry.getKey());
				break;
			}
		}
	}
 
	/**
	 * �ر����Ӻ�
	 */
	public void afterConnectionClosed(WebSocketSession session,CloseStatus closeStatus) throws Exception {
		System.out.println("Websocket:" + session.getId() + "�Ѿ��ر�");
		Iterator<Entry<Long, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
		// �Ƴ�Socket�Ự
		while (it.hasNext()) {
			Entry<Long, WebSocketSession> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				userSocketSessionMap.remove(entry.getKey());
				System.out.println("Socket�Ự�Ѿ��Ƴ�:�û�ID" + entry.getKey());
				break;
			}
		}
	}
 
	public boolean supportsPartialMessages() {
		return false;
	}
 
	/**
	 * ��ĳ���û�������Ϣ
	 * 
	 * @param userName
	 * @param message
	 * @throws IOException
	 */
	public void sendMessageToUser(Long uid, TextMessage message)throws IOException {
		WebSocketSession session = userSocketSessionMap.get(uid);
		if (session != null && session.isOpen()) {
			session.sendMessage(message);
		}
	}
}
