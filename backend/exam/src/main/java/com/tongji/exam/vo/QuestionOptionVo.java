package com.tongji.exam.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QuestionOptionVo {
    @JsonProperty("id")
    private String questionOptionId;

    @JsonProperty("content")
    private String questionOptionContent;

    @JsonProperty("answer")
    private Boolean answer = false;

    @JsonProperty("description")
    private String questionOptionDescription;
}
