package com.river.WebFluxSocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/echo")
public class EchoController {
    private static final String PREFIX= "/";

    @GetMapping("/ws")
    public String Echo(Model model){ return PREFIX + "echo.html"; }

}
