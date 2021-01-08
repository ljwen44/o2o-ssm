package com.sgxy.o2o.websocket;

import javax.annotation.Resource;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer{
	@Resource
	MyWebSocketHandler handler;
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//		registry.addHandler(handler, "/socketServer/webSocket").addInterceptors(new HandShake());
//		registry.addHandler(handler, "/socketjs/socketServer").addInterceptors(new HandShake()).withSockJS();
		//�����֧��websocket
		registry.addHandler(handler, "/ws").addInterceptors(new HandShake());
		//�������֧�ֵĻ�����Ҫsocketjs����֧��
		registry.addHandler(handler, "/ws/sockjs").addInterceptors(new HandShake()).withSockJS();
	}

}
