package com.jpa.practice.domain;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping("/hi")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @GetMapping("/hello")
    public String index2(){
        return "index";
    }


    @GetMapping("/a")
    public String index3(){
        return "test";
    }

}
