package com.jpa.practice.service;

import com.jpa.practice.config.BusinessException;
import com.jpa.practice.config.SHA256;
import com.jpa.practice.dto.seller.PosgSignUpReq;
import com.jpa.practice.entity.Role;
import com.jpa.practice.entity.Seller;
import com.jpa.practice.repository.SellerRepository;
import com.jpa.practice.utils.jwt.SellerAuthnetication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.jpa.practice.config.response.ResponseCode.*;
import static com.jpa.practice.utils.ValidationRegex.*;

@RequiredArgsConstructor
@Service
public class SellerService {

    private final SellerRepository sellerRepository;

    public SellerAuthnetication loadUserByUserIdx(Long userIdx) {
        Seller seller = sellerRepository.findById(userIdx).orElse(null);

        if (seller != null) {
            return new SellerAuthnetication(seller.getId(), seller.getEmail(), seller.getPassword());
        }

        return null;
    }

    @Transactional
    public Long signUp(PosgSignUpReq sign) {
        if (!isRegexEmail(sign.getEmail())) {
            throw new BusinessException(SIGN_UP_EMAIL_INVALID);
        }
        if (!isRegexPassword(sign.getPassword())) {
            throw new BusinessException(SIGN_UP_PW_INVALID);
        }
        if (!isRegexBirth(sign.getBirthday())) {
            throw new BusinessException(SIGN_UP_BIRTH_INVALID);
        }
        if (!isRegexPhone(sign.getPhone())) {
            throw new BusinessException(SIGN_UP_PHONE_INVALID);
        }

        String salt;
        try {
            salt = SHA256.createSalt(sign.getPassword());
            String pwd = new SHA256().encrypt(sign.getPassword(), salt); // 비밀번호 암호화
            sign.setPassword(pwd);
        } catch (Exception e){
                throw new BusinessException(SHA_ERROR);
        }

        try {
            Seller seller = Seller.builder()
                    .name(sign.getName())
                    .birthday(sign.getBirthday())
                    .phoneNum(sign.getPhone())
                    .email(sign.getEmail())
                    .password(sign.getPassword())
                    .salt(salt)
                    .firstLogin(false)
                    .menuRegister(false)
                    .status("A")
                    .role(Role.SELLER)
                    .build();

            Seller savedSeller = sellerRepository.save(seller);
            if (savedSeller.getId() == null) {
                throw new BusinessException(SING_UP_FAILED);
            }

            return savedSeller.getId();
        } catch (Exception e2) {
            throw new BusinessException(SING_UP_FAILED);
        }
    }
}
