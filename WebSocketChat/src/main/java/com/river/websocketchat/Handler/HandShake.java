package com.river.websocketchat.Handler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
public class HandShake implements HandshakeInterceptor {
    @Override
    @SneakyThrows
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler, Map<String, Object> attributes){
        if(request instanceof ServletServerHttpRequest){
            HttpSession session = ((ServletServerHttpRequest) request).getServletRequest().getSession(false);
            Object uid = session.getAttribute("uid");
            if(uid != null){
                attributes.put("uid", uid.toString());
                log.info("用户[{}]已上线", uid.toString());
            }
            else {
                return false;
            }
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler, @Nullable Exception e){

    }
}
