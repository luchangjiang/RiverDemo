package com.river.thread;

import java.sql.Connection;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;

/**
 * @author ：River
 * @date ：Created in 7/10/2019 9:44 AM
 * @description：通过FutureTask实现连接池
 * @modified By：
 * @version: $
 */
public class FutureConnectionDemo {
    private ConcurrentHashMap<String, FutureTask<Connection>> connectionPool = new ConcurrentHashMap<String, FutureTask<Connection>>();

    public Connection getConnection(String key) throws Exception{
        FutureTask<Connection>connectionTask=connectionPool.get(key);
        if(connectionTask!=null){
            return connectionTask.get();
        }
        else{
            Callable<Connection> callable = new Callable<Connection>(){
                @Override
                public Connection call() throws Exception {
                    // TODO Auto-generated method stub
                    return createConnection();
                }
            };
            FutureTask<Connection>newTask = new FutureTask<Connection>(callable);
            connectionTask = connectionPool.putIfAbsent(key, newTask);
            if(connectionTask==null){
                connectionTask = newTask;
                connectionTask.run();
            }
            return connectionTask.get();
        }
    }

    //创建Connection（根据业务需求，自定义Connection）
    private Connection createConnection(){
        return null;
    }
}
