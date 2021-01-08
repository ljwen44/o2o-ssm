package com.sgxy.o2o.websocket;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

public class HandShake implements HandshakeInterceptor {
	public boolean beforeHandshake(ServerHttpRequest request,ServerHttpResponse response, WebSocketHandler wsHandler,
		Map<String, Object> attributes) throws Exception {
		System.err.println("Websocket:�û�[ID:"+ ((ServletServerHttpRequest) request).getServletRequest()
		.getSession(false).getAttribute("uid") + "]�Ѿ���������");
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession(false);
			// ����û� 
			Long uid = (Long) session.getAttribute("uid");
			if (uid != null) {
				attributes.put("uid", uid);
			} else {
				return false;
			}
		}
		return true;
	}
	public void afterHandshake(ServerHttpRequest request,ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
	}

}
