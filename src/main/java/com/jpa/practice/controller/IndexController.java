package com.jpa.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/hi")
    public String index(){
        return new String("index");
    }

    @GetMapping("/")
    public String main(Model model){

        return "posts/index";
    }


}
