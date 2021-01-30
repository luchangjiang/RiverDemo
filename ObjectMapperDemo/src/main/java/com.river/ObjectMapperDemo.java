package com.river;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-04-25 22:31
 **/
public class ObjectMapperDemo {
    public static ObjectMapper mapper = new ObjectMapper();
    public static void main(String[] args) throws IOException {
        XwjUser user = new XwjUser(1, "Hello World", new Date());

//        mapper.writeValue(new File(ObjectMapperDemo.class.getResource("text.txt").getPath()), user); // 写到文件中
         mapper.writeValue(System.out, user); //写到控制台

        String jsonStr = mapper.writeValueAsString(user);
        System.out.println("对象转为字符串：" + jsonStr);

        byte[] byteArr = mapper.writeValueAsBytes(user);
        System.out.println("对象转为byte数组：" + byteArr);

        XwjUser userDe = mapper.readValue(jsonStr, XwjUser.class);
        System.out.println("json字符串转为对象：" + userDe);

        XwjUser useDe2 = mapper.readValue(byteArr, XwjUser.class);
        System.out.println("byte数组转为对象：" + useDe2);
    }

    static {
        // 转换为格式化的json
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // 如果json中有新增的字段并且是实体类类中不存在的，不报错
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
