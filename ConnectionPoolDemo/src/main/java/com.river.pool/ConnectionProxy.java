package com.river.pool;

import javax.sql.DataSource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * 对Connection对象的代理，主要作用为拦截connection的close方法
 */
public class ConnectionProxy implements InvocationHandler {
    //真实的连接
    private Connection realConnection ;
    //代理的连接
    private Connection proxyConnection ;
    //数据源
    private HuwcDataSource dataSource ;

    //这是一个构造器
    public ConnectionProxy(Connection realConnection, HuwcDataSource dataSource) {
        this.realConnection = realConnection;
        this.dataSource = dataSource;
        //生成代理的Connection
        this.proxyConnection = (Connection) Proxy.newProxyInstance(Connection.class.getClassLoader(), new Class[]{Connection.class}, this);
    }

    public Connection getRealConnection() {
        return realConnection;
    }

    public void setRealConnection(Connection realConnection) {
        this.realConnection = realConnection;
    }

    public Connection getProxyConnection() {
        return proxyConnection;
    }

    public void setProxyConnection(Connection proxyConnection) {
        this.proxyConnection = proxyConnection;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(HuwcDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        //拦截连接关闭的方法，对其进行扩展处理
        if(methodName.equalsIgnoreCase("close")){
            synchronized (dataSource.getMonitor()){
                //活动连接list中remove这个ConnectionProxy
                dataSource.getActiveConnections().remove(this);
                //如果空闲连接list中的数量没有达到阈值，则装入空间连接list，并notifyAll
                if(dataSource.getIdleConnections().size() < dataSource.getMaxIdleConnectionsCount()){
                    dataSource.getIdleConnections().add(this);
                    dataSource.getMonitor().notifyAll();
                }else{
                    //否则，直接关闭这个Connection
                    realConnection.close();
                }
            }
        }else{
            return method.invoke(realConnection, args) ;
        }
        return null;
    }
}