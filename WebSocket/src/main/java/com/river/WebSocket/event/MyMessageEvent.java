package com.river.WebSocket.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyMessageEvent {
    private String msg;
}
