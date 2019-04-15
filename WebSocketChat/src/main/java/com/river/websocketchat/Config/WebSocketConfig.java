package com.river.websocketchat.Config;

import com.river.websocketchat.Handler.HandShake;
import com.river.websocketchat.Handler.MyWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurationSupport;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig extends WebSocketConfigurationSupport implements WebSocketConfigurer {

    @Bean
    public WebSocketHandler myHandler(){
        return new MyWebSocketHandler();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){
        registry.addHandler(myHandler(), "/ws").addInterceptors(new HandShake());
        registry.addHandler(myHandler(), "/ws/socketjs").addInterceptors(new HandShake()).withSockJS();
    }
}
