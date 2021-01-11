package com.tongji.exam.qo;


import lombok.Data;

@Data
public class UserInfoQo {
    private String userNickname;
    private String userPassword;
    private String userAvatar;
    private String userDescription;
    private String userEmail;
    private String userPhone;
}
