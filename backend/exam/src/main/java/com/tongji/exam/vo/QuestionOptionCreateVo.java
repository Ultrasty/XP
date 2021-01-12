package com.tongji.exam.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QuestionOptionCreateVo {

    /**
     * 问题的内容
     */
    @JsonProperty("content")
    private String questionOptionContent;

    /**
     * 当前的选项是否是问题大答案
     */
    @JsonProperty("answer")
    private Boolean answer = false;

}
