package com.jpa.practice.config.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    // 200
    SUCCESS(200, "SUCCESS", "요청 성공"),

    // 400
    INVALID_JWT(400, "JWT-ERROR-01", "유효하지 않은 JWT"),
    EMPTY_JWT(400, "JWT-ERROR-00", "JWT 에러"),
    SHA_ERROR(400, "SHA-256-ERROR", "비밀번호 암호화 에러"),

    // 400 - DB
    SING_UP_FAILED(401, "USER-SIGNUP-ERROR00", "회원 가입 실패"),
    SIGN_UP_EMAIL_INVALID(401, "USER-SIGNUP-ERROR01", "이메일 양식이 올바르지 않습니다"),
    SIGN_UP_PW_INVALID(401, "USER-SIGNUP-ERROR02", "비밀번호 양식이 올바르지 않습니다"),
    SIGN_UP_BIRTH_INVALID(401, "USER-SIGNUP-ERROR03", "생년월일 양식이 올바르지 않습니다"),
    SIGN_UP_PHONE_INVALID(401, "USER-SIGNUP-ERROR04", "전화번호 양식이 올바르지 않습니다"),

    // 500
    INTERNAL_SERVER_ERROR(500, "SERVER-ERROR-00", "서버 에러"),


    ;

    private final int status;
    private final String code;
    private final String description;

}
