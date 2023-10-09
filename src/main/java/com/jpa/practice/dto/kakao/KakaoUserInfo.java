package com.jpa.practice.dto.kakao;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class KakaoUserInfo {
    private long kakaoId;
    private String name;
    private String email;
    private String birth;
    private String phone;

    @Builder
    public KakaoUserInfo(long kakaoId, String name, String email, String birth, String phone) {
        this.kakaoId = kakaoId;
        this.name = name;
        this.email = email;
        this.birth = birth;
        this.phone = phone;
    }
}
