package com.river.websocketchat.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Message {
    private long from;
    private String fromName;
    private long to;
    private String text;
    private Date date;
}
