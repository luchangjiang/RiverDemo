package com.river.websocketchat.Handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.river.websocketchat.entity.Message;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class MyWebSocketHandler implements WebSocketHandler {
    private static Map<Long, Set<WebSocketSession>> userSocketSessionMap= new ConcurrentHashMap<>();

    @Override
    @SneakyThrows
    public void afterConnectionEstablished(WebSocketSession session){
        long uid = (long)session.getAttributes().get("uid");

        boolean isNewUser = true;
        for(Object o: userSocketSessionMap.keySet()){
            if(((Map.Entry) o).getKey().equals(uid)){
                userSocketSessionMap.get(uid).add(session);
                isNewUser=false;
                break;
            }
        }
        if(isNewUser){
            Set<WebSocketSession> sessions = new HashSet<>();
            sessions.add(session);
            userSocketSessionMap.put(uid, sessions);
        }
        log.info("当前在线用户人数：{}", userSocketSessionMap.values().size());
    }

    @Override
    @SneakyThrows
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message){
        Long uid = (Long)session.getAttributes().get("uid");
        Message msg = new Gson().fromJson(message.getPayload().toString(), Message.class);
        msg.setDate(new Date());
        TextMessage textMessage = new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg));
        sendUserMessage(uid, textMessage);
    }

    /**
     * 给某个用户发送消息
     *
     * @param uid
     * @param message
     * @throws IOException
     */
    @SneakyThrows
    private void sendUserMessage(Long uid, TextMessage message){
        for(Long id: userSocketSessionMap.keySet()){
            if(id.equals(uid)){
                for(WebSocketSession session: userSocketSessionMap.get("uid")){
                    try {
                        session.sendMessage(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
        }
    }

    @Override
    @SneakyThrows
    public void handleTransportError(WebSocketSession var1, Throwable var2){

    }

    @Override
    @SneakyThrows
    public void afterConnectionClosed(WebSocketSession var1, CloseStatus var2){

    }

    @Override
    public boolean supportsPartialMessages(){
        return false;
    }
}
