package com.river.httpsDemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2019-12-21 15:15
 **/
@Controller
@RequestMapping
public class ViewController {

    @GetMapping("index")
    public String index(){
        return "index";
    }
}
