package com.tongji.exam.enums;

import lombok.Getter;

@Getter
public enum QuestionEnum {

    RADIO(1, "单选题"),
    CHECK(2, "多选题"),
    JUDGE(3, "判断题");


    QuestionEnum(Integer id, String role) {
        this.id = id;
        this.role = role;
    }

    private Integer id;
    private String role;
}
