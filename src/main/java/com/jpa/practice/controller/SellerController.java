package com.jpa.practice.controller;

import com.jpa.practice.config.BusinessException;
import com.jpa.practice.config.response.DataMessage;
import com.jpa.practice.config.response.ResponseCode;
import com.jpa.practice.dto.seller.PosgSignUpReq;
import com.jpa.practice.dto.seller.PostSignUpRes;
import com.jpa.practice.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers")
public class SellerController {

    private final SellerService sellerService;

    @PostMapping("")
    ResponseEntity<DataMessage> signUp(@RequestBody PosgSignUpReq sign){
        Long sellerIdx = sellerService.signUp(sign);
        PostSignUpRes postSignUpRes = new PostSignUpRes(sellerIdx);

        DataMessage<PostSignUpRes> reponseBody = DataMessage.success(postSignUpRes);

        return new ResponseEntity<>(reponseBody, HttpStatus.OK);
    }



}
