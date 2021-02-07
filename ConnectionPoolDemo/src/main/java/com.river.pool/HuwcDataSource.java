package com.river.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HuwcDataSource extends HuwcAbstractDataSource {
    //空闲的连接
    private List<ConnectionProxy> idleConnections = new ArrayList<>();

    //活动的连接
    private List<ConnectionProxy> activeConnections = new ArrayList<>();

    //是否为本数据源第一次获取连接标志
    private boolean firstFlag = true ;
    //最小的空闲连接数
    private Integer minIdleConnectionsCount = 2;
    //最大的空闲连接数
    private Integer maxIdleConnectionsCount = 5;
    //最大的活动连接数
    private Integer maxActiveConnectionsCount = 10;
    //获取连接的最大的等待时间（毫秒）
    private Integer maxWaitToTimeOut = 30000;

    private String url;

    private String driver;

    private String user;

    private String password;

    private Object monitor = new Object();

    public boolean isFirstFlag() {
        return firstFlag;
    }

    public void setFirstFlag(boolean firstFlag) {
        this.firstFlag = firstFlag;
    }

    public List<ConnectionProxy> getIdleConnections() {
        return idleConnections;
    }

    public void setIdleConnections(List<ConnectionProxy> idleConnections) {
        this.idleConnections = idleConnections;
    }

    public List<ConnectionProxy> getActiveConnections() {
        return activeConnections;
    }

    public void setActiveConnections(List<ConnectionProxy> activeConnections) {
        this.activeConnections = activeConnections;
    }

    public Integer getMaxIdleConnectionsCount() {
        return maxIdleConnectionsCount;
    }

    public void setMaxIdleConnectionsCount(Integer maxIdleConnectionsCount) {
        this.maxIdleConnectionsCount = maxIdleConnectionsCount;
    }

    public Integer getMaxActiveConnectionsCount() {
        return maxActiveConnectionsCount;
    }

    public void setMaxActiveConnectionsCount(Integer maxActiveConnectionsCount) {
        this.maxActiveConnectionsCount = maxActiveConnectionsCount;
    }

    public Integer getMaxWaitToTimeOut() {
        return maxWaitToTimeOut;
    }

    public void setMaxWaitToTimeOut(Integer maxWaitToTimeOut) {
        this.maxWaitToTimeOut = maxWaitToTimeOut;
    }



    public Object getMonitor() {
        return monitor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return getConnection(user, password);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return doGetConnection(username, password);
    }

    private Connection doGetConnection(String username, String password) throws SQLException {
        while(true) {
            synchronized (monitor) {
                //如果是本数据源第一次获取连接，那么首先进行init操作
                if(firstFlag)
                    init();
                //首先从空闲连接list中获取
                if (idleConnections.size() > 0) {
                    return idleConnections.remove(0).getProxyConnection();
                } else {//空闲连接list中如果没有连接的情况
                    //如果活动连接list的数量还没有达到阈值
                    if (maxActiveConnectionsCount > activeConnections.size()) {
                        //获取一个真实的Connection，并且封装为ConnectionProxy，装入活动连接list
                        ConnectionProxy connectionProxy = new ConnectionProxy(DriverManager.getConnection(url, user, password), this);
                        activeConnections.add(connectionProxy);
                        return connectionProxy.getProxyConnection();
                    } else {
                        //如果活动连接list的数量已经达到了阈值
                        //等待超时时间,并等待被唤醒
                        try {
                            monitor.wait(maxWaitToTimeOut);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                }
            }
        }
        return null ;
    }

    private void init() {
        //首先进行锁定处理
        firstFlag = false;
        for(int i=0;i<minIdleConnectionsCount;i++){
            try {
                ConnectionProxy connectionProxy = new ConnectionProxy(DriverManager.getConnection(url, user, password), this);
                this.idleConnections.add(connectionProxy);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}