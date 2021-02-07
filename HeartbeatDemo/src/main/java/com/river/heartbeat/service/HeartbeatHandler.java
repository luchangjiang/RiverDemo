package com.river.heartbeat.service;

import com.river.heartbeat.entity.Cmder;
import com.river.heartbeat.entity.HeartbeatEntity;

public interface HeartbeatHandler {
    public Cmder sendHeartBeat(HeartbeatEntity info);
}