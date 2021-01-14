package com.sgxy.o2o.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import org.springframework.web.context.ContextLoader;

import com.sgxy.o2o.basic.controller.BasicController;
import com.sgxy.o2o.service.ChatService;

import net.sf.json.JSONObject;



@ServerEndpoint("/webSocket/{uid}")
public class Websocket extends BasicController{
	private ChatService chatService = (ChatService) ContextLoader.getCurrentWebApplicationContext().getBean("chatService");
	public static final Map<String, Session> userSocketSessionMap;
	static {
		userSocketSessionMap = new HashMap<String, Session>();
	}
	@OnOpen
    public void onOpen(@PathParam("uid") String uid,Session session) throws IOException{
		userSocketSessionMap.put(uid, session);
    }
    
    //关闭时执行
    @OnClose
    public void onClose(Session session){
    	Iterator<Entry<String, Session>> it = userSocketSessionMap.entrySet().iterator();
		// 移除Socket会话
		while (it.hasNext()) {
			Entry<String, Session> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				userSocketSessionMap.remove(entry.getKey());
				System.err.println("Socket会话已经移除:用户ID" + entry.getKey());
				break;
			}
		}
    }
    //收到消息时执行
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
    	JSONObject jsonStr = JSONObject.fromObject(message);
    	System.err.println(message);
    	// 存入数据库
    	String cid = this.getUUID();
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ctime = df.format(new Date());
		int sendStatus = chatService.addChat(cid, jsonStr.getString("uid"), jsonStr.getString("message"), ctime, 0, jsonStr.getString("ruid"));
		
		// 如果存储失败，则返回告知客户端; 存储成功，则告知客户端并发送消息给接收方
		// 判断接收方是否在userSocketSessionMap，是则发送，否则不执行操作
		Session sendSession =  userSocketSessionMap.get(jsonStr.getString("uid"));
		if(sendStatus < 1) {
			JSONObject json = new JSONObject();
			json.put("errMsg", "发送失败");
			String notice = json.toString();
			sendSession.getBasicRemote().sendText(notice);
		} else {
			JSONObject sendJson = new JSONObject();
			sendJson.put("errMsg", "OK");
			sendJson.put("message", jsonStr.getString("message"));
			sendJson.put("time", ctime);
			String sendNotice = sendJson.toString();
			sendSession.getBasicRemote().sendText(sendNotice);
			Session recSession = userSocketSessionMap.get(jsonStr.getString("ruid"));
			if (recSession != null && recSession.isOpen()) {
				JSONObject recJson = new JSONObject();
				recJson.put("errMsg", "");
				recJson.put("message", jsonStr.getString("message"));
				recJson.put("userName", jsonStr.getString("userName"));
				recJson.put("avatar", jsonStr.getString("avatar"));
				recJson.put("userUUID", jsonStr.getString("uid"));
				recJson.put("time", ctime);
				String recNotice = recJson.toString();
				recSession.getBasicRemote().sendText(recNotice);
			}
		}
    }
    
    //连接错误时执行
    @OnError
    public void onError(Session session, Throwable error){
        error.printStackTrace();
    }
}
