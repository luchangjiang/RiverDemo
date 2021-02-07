package com.river.heartbeat.service.impl;

import com.river.heartbeat.HeartbeatLinstener;
import com.river.heartbeat.entity.Cmder;
import com.river.heartbeat.entity.HeartbeatEntity;
import com.river.heartbeat.service.HeartbeatHandler;

import java.util.Map;

public class HeartbeatHandlerImpl implements HeartbeatHandler {
    public Cmder sendHeartBeat(HeartbeatEntity info) {
        HeartbeatLinstener linstener = HeartbeatLinstener.getInstance();
 
        // 添加节点
        if (!linstener.checkNodeValid(info.getNodeID())) {
            linstener.registerNode(info.getNodeID(), info);
        }
 
        // 其他操作
        Cmder cmder = new Cmder();
       cmder.setNodeID(info.getNodeID());
        // ...
 
        System.out.println("current all the nodes: ");
        Map<String, Object> nodes = linstener.getNodes();
        for (Map.Entry e : nodes.entrySet()) {
            System.out.println(e.getKey() + " : " + e.getValue());
        }
        System.out.println("hadle a heartbeat");
        return cmder;
    }
}