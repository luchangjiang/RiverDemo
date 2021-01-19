package com.river.agent;

import com.river.agent.service.UserServiceImpl;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-11-02 19:02
 **/
public class IdigAgentTest {
    public static void main(String[] args) {
        System.out.println("hello world idig!");
        UserServiceImpl userService = new UserServiceImpl();
        userService.hello();
    }
}
