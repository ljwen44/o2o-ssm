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
		//浏览器支持websocket
		registry.addHandler(handler, "/ws").addInterceptors(new HandShake());
		//浏览器不支持的话，需要socketjs引入支持
		registry.addHandler(handler, "/ws/sockjs").addInterceptors(new HandShake()).withSockJS();
	}

}
