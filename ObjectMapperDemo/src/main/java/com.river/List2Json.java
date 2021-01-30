package com.river;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-04-25 22:50
 **/
public class List2Json {
    public static void main(String[] args) throws IOException {
        List<XwjUser> userList = new ArrayList<>();
        userList.add(new XwjUser(1, "aaa", new Date()));
        userList.add(new XwjUser(2, "bbb", new Date()));
        userList.add(new XwjUser(3, "ccc", new Date()));
        userList.add(new XwjUser(4, "ddd", new Date()));

        String jsonStr = ObjectMapperDemo.mapper.writeValueAsString(userList);
        System.out.println("集合转为字符串：" + jsonStr);

        List<XwjUser> userListDes = ObjectMapperDemo.mapper.readValue(jsonStr, List.class);
        System.out.println("字符串转集合：" + userListDes);
    }

}
