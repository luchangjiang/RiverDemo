package com.river.compress.compress2front;

import com.river.compress.compress2front.util.PakoGzipUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-05-01 09:57
 **/
@RestController
@RequestMapping("/compress/")
public class CompressController {
    @PostMapping("pakoGzip")
    public String pakoGzipTest() throws IOException {
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<100000;i++) {
            sb.append("Hello!").append("My name is pakoGzip.");
            sb.append("What's your name?");
            sb.append("Hi,我是中国人");
        }
        String a = sb.toString();
        System.out.println(a.length());
        String result = PakoGzipUtils.compress(a);
        System.out.println(result.length());
        return result;
    }
}
