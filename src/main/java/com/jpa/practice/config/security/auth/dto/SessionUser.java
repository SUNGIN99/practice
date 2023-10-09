package com.jpa.practice.config.security.auth.dto;

import com.jpa.practice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;


@Getter
public class SessionUser implements Serializable {
    private String name;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.picture = user.getPicture();
    }

}
