package com.river.design.ServiceLocatorPattern;

import com.river.design.ServiceLocatorPattern.service.Service;

/**
 * @program: RiverDemo
 * @description: 服务定位器模式
 * @author: River
 * @create: 2021-01-30 12:35
 **/
public class ServiceLocatorPatternDemo {
    public static void main(String[] args) {
        Service service = ServiceLocator.getService("Service1");
        service.execute();
        service = ServiceLocator.getService("Service2");
        service.execute();
        service = ServiceLocator.getService("Service1");
        service.execute();
        service = ServiceLocator.getService("Service2");
        service.execute();
    }
}
