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
    
    //�ر�ʱִ��
    @OnClose
    public void onClose(Session session){
    	Iterator<Entry<String, Session>> it = userSocketSessionMap.entrySet().iterator();
		// �Ƴ�Socket�Ự
		while (it.hasNext()) {
			Entry<String, Session> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				userSocketSessionMap.remove(entry.getKey());
				System.err.println("Socket�Ự�Ѿ��Ƴ�:�û�ID" + entry.getKey());
				break;
			}
		}
    }
    //�յ���Ϣʱִ��
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
    	JSONObject jsonStr = JSONObject.fromObject(message);
    	System.err.println(message);
    	// �������ݿ�
    	String cid = this.getUUID();
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ctime = df.format(new Date());
		int sendStatus = chatService.addChat(cid, jsonStr.getString("uid"), jsonStr.getString("message"), ctime, 0, jsonStr.getString("ruid"));
		
		// ����洢ʧ�ܣ��򷵻ظ�֪�ͻ���; �洢�ɹ������֪�ͻ��˲�������Ϣ�����շ�
		// �жϽ��շ��Ƿ���userSocketSessionMap�������ͣ�����ִ�в���
		Session sendSession =  userSocketSessionMap.get(jsonStr.getString("uid"));
		if(sendStatus < 1) {
			JSONObject json = new JSONObject();
			json.put("errMsg", "����ʧ��");
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
    
    //���Ӵ���ʱִ��
    @OnError
    public void onError(Session session, Throwable error){
        error.printStackTrace();
    }
}
