package com.jpa.practice.domain.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("")
    ResponseEntity<String> getHello(){
        return ResponseEntity.ok("hello");
    }

}
