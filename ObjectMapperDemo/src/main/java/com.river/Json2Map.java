package com.river;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-04-25 22:52
 **/
public class Json2Map {
    public static void main(String[] args) {
        Map<String, Object> testMap = new HashMap<>();
        testMap.put("name", "merry");
        testMap.put("age", 30);
        testMap.put("date", new Date());
        testMap.put("user", new XwjUser(1, "Hello World", new Date()));

        try {
            String jsonStr = ObjectMapperDemo.mapper.writeValueAsString(testMap);
            System.out.println("Map转为字符串：" + jsonStr);
            try {
                Map<String, Object> testMapDes = ObjectMapperDemo.mapper.readValue(jsonStr, Map.class);
                System.out.println("字符串转Map：" + testMapDes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
