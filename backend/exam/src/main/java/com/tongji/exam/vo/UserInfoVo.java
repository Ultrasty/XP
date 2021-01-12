package com.tongji.exam.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVo {

    @JsonProperty("id")
    private String userId;

    @JsonProperty("avatar")
    @NotEmpty
    private String userAvatar;

    @JsonProperty("name")
    @NotEmpty
    private String userNickname;

    @JsonProperty("username")
    @NotEmpty
    private String userUsername;

    /**
     * 密码不用拷贝，免得泄露信息
     */
    @NotEmpty
    private String password = "";

    @JsonProperty("email")
    @Email
    private String userEmail;

    @JsonProperty("telephone")
    private String userPhone;

    @JsonProperty("roleId")
    private String roleName;

    @JsonProperty("welcome")
    private String userDescription;

    @JsonProperty("role")
    private RoleVo roleVo;
}
