package com.river.WebFluxSocket.session;

import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.FluxSink;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2019-08-17 18:50
 **/
public class WebSocketSender {
    private WebSocketSession session;
    private FluxSink<WebSocketMessage> sink;
    public WebSocketSender(WebSocketSession session, FluxSink<WebSocketMessage> sink) {
        this.session = session;
        this.sink = sink;
    }
    public void sendData(String data) {
        sink.next(session.textMessage(data));
    }
}
