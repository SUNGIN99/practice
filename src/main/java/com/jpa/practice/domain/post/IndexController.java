package com.jpa.practice.domain.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

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
