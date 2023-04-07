package com.example.websocketdemo1.config;

import com.example.websocketdemo1.component.MyWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 实现了 WebSocketConfigurer 接口，并重写了其中的 registerWebSocketHandlers 方法
 * 在该方法中，将 MyWebSocketHandler 注册为 WebSocket 处理器，并将其映射到 /websocket 路径上。
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private MyWebSocketHandler myWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWebSocketHandler, "/websocket");
    }
}
