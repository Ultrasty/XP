package com.tongji.exam.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String email;
    private String password;
    private String password2;
    private String mobile;
    private String upup;
    /**
     * 验证码
     */
    private String captcha;
}
