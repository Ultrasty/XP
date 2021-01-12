package com.tongji.exam.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {


    ADMIN(1, "管理员"),
    TEACHER(2, "教师"),
    STUDENT(3, "学生");


    RoleEnum(Integer id, String role) {
        this.id = id;
        this.role = role;
    }

    private Integer id;
    private String role;
}
