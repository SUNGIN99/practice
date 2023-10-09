package com.jpa.practice.controller;

import com.jpa.practice.config.security.auth.dto.SessionUser;
import com.jpa.practice.dto.kakao.KakaoUserInfo;
import com.jpa.practice.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final HttpSession httpSession;
    private final KakaoService kakaoService;

    @GetMapping("/hi")
    public String index(){
        return new String("index");
    }

    @GetMapping("/")
    public String main(Model model, @RequestParam(value = "code", required = false) String code){
        model.addAttribute("login_uri", kakaoService.getKakaoLogin());

        if (code != null){
            KakaoUserInfo kakaoInfo = kakaoService.getKakaoInfo(code);
            model.addAttribute("userName", kakaoInfo.getName());
        }


        return "posts/index";
    }


}
