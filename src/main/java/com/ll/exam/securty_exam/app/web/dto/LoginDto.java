package com.ll.exam.securty_exam.app.web.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginDto {
    @NotEmpty("아이디를 입력해주세요.")
    private String username;
    @NotEmpty("비밀번호를 입력해주세요.")
    private String password;

    public boolean isNotValid() {
        return username == null || password == null || username.trim().length() == 0 || password.trim().length() == 0;
    }
}