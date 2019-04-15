package com.river.websocketchat.Handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.river.websocketchat.entity.Message;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import javax.jnlp.UnavailableServiceException;
import java.io.IOException;
import java.rmi.activation.UnknownObjectException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class MyWebSocketHandler implements WebSocketHandler {
    private static Map<Long, WebSocketSession> userSocketSessionMap= new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
        Object uidObj = session.getAttributes().get("uid");
        if(uidObj == null){
            throw new UnavailableServiceException();
        }
        Long uid = Long.valueOf(uidObj.toString());

        userSocketSessionMap.put(uid, session);
        log.info("当前在线用户人数：{}", userSocketSessionMap.size());
    }

    @Override
    @SneakyThrows
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message){
        Object uidObj = session.getAttributes().get("uid");
        if(uidObj == null){
            throw new UnavailableServiceException();
        }
        Long uid = Long.valueOf(uidObj.toString());

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
                WebSocketSession session = userSocketSessionMap.get(id);
                try {
                    session.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public void broadcast(Message message){
        for(WebSocketSession session: userSocketSessionMap.values()){
            Long uid = (Long)session.getAttributes().get("uid");
            TextMessage textMessage = new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(message));
            sendUserMessage(uid, textMessage);
        }
    }

    @Override
    @SneakyThrows
    public void handleTransportError(WebSocketSession session, Throwable exception){
        if(session.isOpen()){
            session.close();
        }
        Long uid = (Long)session.getAttributes().get("uid");
        userSocketSessionMap.remove(uid);
    }

    @Override
    @SneakyThrows
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status){
        Object uidObj = session.getAttributes().get("uid");
        if(uidObj == null){
            throw new UnavailableServiceException();
        }
        Long uid = Long.valueOf(uidObj.toString());

        userSocketSessionMap.remove(uid);
    }

    @Override
    public boolean supportsPartialMessages(){
        return false;
    }
}
