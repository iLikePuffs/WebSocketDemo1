package com.example.websocketdemo1.component;

import com.example.websocketdemo1.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

/**
 * 这个类是一个WebSocket处理器
 * 用于处理WebSocket连接的建立、消息的接收、传输错误、连接的关闭等
 */

@Component
@Slf4j
public class MyWebSocketHandler implements WebSocketHandler {

    /**
     * 连接建立后的处理逻辑(校验token)
     * @param session
     * @throws Exception
     */
    //q:你知道启动WebSocket同步指的是什么吗？
    //a:
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {


        //获取客户端传输过来的消息中的token
        String token = session.getUri().getQuery().split("=")[1];

        //校验token
        try {
            Claims claims = JwtUtils.parseJWT(token);
            //获取token中的服务器id
            String serverId = (String) claims.get("serverId");
            //向客户端发送一条消息，告诉客户端连接建立成功
            session.sendMessage(new TextMessage("连接建立成功，你的服务器是：" + serverId));
        } catch (Exception e) {
            log.info("校验token失败，请重试");
            //关闭连接
            session.close();
        }


    }

    /**
     * 处理接收到的消息(当客户端发送消息过来时，会调用该方法)
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        //获取客户端传输过来的消息(无论是文本还是二进制数据)
        String msg = (String) message.getPayload();

        //因为还不知道校验token成功后要干什么，所以这里的逻辑先空着
    }

    /**
     * 处理传输错误
     *
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // 处理传输错误
    }

    /**
     * 连接关闭后的处理逻辑
     *
     * @param session
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        //向客户端发送一条消息，告诉客户端连接已关闭
        session.sendMessage(new TextMessage("连接已关闭"));
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
