package com.jpa.practice.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name= "Merchandisers")
public class Seller extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "birthday", nullable = false, length = 50)
    private String birthday;

    @Column(name = "phone_num", nullable = false, length = 50)
    private String phoneNum;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "passsword", nullable = false, length = 100)
    private String password;

    @Column(name = "salt", length = 80)
    private String salt;

    @Column(name = "first_login", nullable = false)
    private boolean firstLogin;

    @Column(name = "menu_register", nullable = false)
    private boolean menuRegister;

    @Column(name = "status", nullable = false, length = 10)
    private String status;

    @Enumerated(EnumType.STRING)
    @Column(name="role", nullable = false)
    private Role role;

    @Builder
    public Seller(Long id, String name, String birthday, String phoneNum, String email, String password, String salt, boolean firstLogin, boolean menuRegister, String status, Role role) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.phoneNum = phoneNum;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.firstLogin = firstLogin;
        this.menuRegister = menuRegister;
        this.status = status;
        this.role = role;
    }
}
