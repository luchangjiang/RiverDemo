package com.river.design.DelegatePattern;

/**
 * @program: RiverDemo
 * @description: 业务代理模式
 * @author: River
 * @create: 2021-01-30 12:04
 **/
public class DelegatePatternDemo {
    public static void main(String[] args) {

        BusinessDelegate businessDelegate = new BusinessDelegate();
        businessDelegate.setServiceType("EJB");

        Client client = new Client(businessDelegate);
        client.doTask();

        businessDelegate.setServiceType("JMS");
        client.doTask();
    }
}
