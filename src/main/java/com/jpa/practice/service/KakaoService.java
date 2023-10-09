package com.jpa.practice.service;

import com.jpa.practice.config.BusinessException;
import com.jpa.practice.dto.kakao.KakaoUserInfo;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;

import static com.jpa.practice.config.response.ResponseCode.KAKAO_TOKEN_FAILED;
import static com.jpa.practice.config.response.ResponseCode.KAKAO_USERINFO_FAILED;


@Service
public class KakaoService {

    @Value("${kakao.kakao_key}")
    private String kakaoKey;

    @Value("${kakao.redirect_uri}")
    private String redirect_uri;

    @Value("${kakao.kakao_secret}")
    private String kakaoSecret;

    private final static String KAKAO_AUTH_URI = "https://kauth.kakao.com";
    private final static String KAKAO_API_URI = "https://kapi.kakao.com";


    public String getKakaoLogin(){
        return KAKAO_AUTH_URI + "/oauth/authorize"
                + "?client_id=" + kakaoKey
                + "&redirect_uri=" + redirect_uri
                + "&response_type=code";
    }

    public KakaoUserInfo getKakaoInfo(String code){
        String accessToken = "";
        String refreshToken = "";

        try{
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("grant_type", "authorization_code");
            body.add("client_id", kakaoKey);
            body.add("redirect_uri", redirect_uri);
            body.add("code", code);
            body.add("client_secret", kakaoSecret);
            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(
                    KAKAO_AUTH_URI + "/oauth/token",
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(response.getBody());
            accessToken = (String) jsonObj.get("access_token");
            refreshToken = (String) jsonObj.get("refresh_token");

        } catch(Exception e){
            throw new BusinessException(KAKAO_TOKEN_FAILED);
        }
        return getUserInfoByToken(accessToken);
    }

    private KakaoUserInfo getUserInfoByToken(String accessToken){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + accessToken);
            headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    KAKAO_API_URI + "/v2/user/me",
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );

            System.out.println(1);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody());
            JSONObject account = (JSONObject) jsonObject.get("kakao_account");
            JSONObject profile = (JSONObject) account.get("profile");

            long id = (long) jsonObject.get("id");
            String name = String.valueOf(profile.get("nickname"));
            String email = String.valueOf(account.get("email"));
            String birth = String.valueOf(account.get("birthyear")) + String.valueOf(account.get("birthday"));
            //String phone = String.valueOf(account.get("phone_number"));

            return KakaoUserInfo.builder()
                    .kakaoId(id)
                    .name(name)
                    .email(email)
                    .birth(birth)
                    .phone("010-1234-5678")
                    .build();

        }
        catch (Exception e){
            throw new BusinessException(KAKAO_USERINFO_FAILED);
        }
    }
}
