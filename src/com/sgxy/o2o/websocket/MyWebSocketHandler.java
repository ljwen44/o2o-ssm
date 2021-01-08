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
    
    //关闭时执行
    @OnClose
    public void onClose(){
        System.err.println("连接断开");
    }
    
    //收到消息时执行
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
//        session.getBasicRemote().sendText("收到来自浏览器 "+Websocket.userId+" 的消息 "); //回复用户
    	System.err.println("收到消息：" + message);
    }
    
    //连接错误时执行
    @OnError
    public void onError(Session session, Throwable error){
        error.printStackTrace();
    }
	/**
	 * 建立连接后
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
	 * 消息处理，在客户端通过Websocket API发送的消息会经过这里，然后进行相应的处理
	 */
	public void handleMessage(WebSocketSession session,WebSocketMessage<?> message) throws Exception {
		if (message.getPayloadLength() == 0)
			return;
		
	}
 
	/**
	 * 消息传输错误处理
	 */
	public void handleTransportError(WebSocketSession session,Throwable exception) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		Iterator<Entry<Long, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
		// 移除Socket会话
		while (it.hasNext()) {
			Entry<Long, WebSocketSession> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				userSocketSessionMap.remove(entry.getKey());
				System.out.println("Socket会话已经移除:用户ID" + entry.getKey());
				break;
			}
		}
	}
 
	/**
	 * 关闭连接后
	 */
	public void afterConnectionClosed(WebSocketSession session,CloseStatus closeStatus) throws Exception {
		System.out.println("Websocket:" + session.getId() + "已经关闭");
		Iterator<Entry<Long, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
		// 移除Socket会话
		while (it.hasNext()) {
			Entry<Long, WebSocketSession> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				userSocketSessionMap.remove(entry.getKey());
				System.out.println("Socket会话已经移除:用户ID" + entry.getKey());
				break;
			}
		}
	}
 
	public boolean supportsPartialMessages() {
		return false;
	}
 
	/**
	 * 给某个用户发送消息
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
