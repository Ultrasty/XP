package com.tongji.exam.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class QuestionVo {
    @JsonProperty("id")
    private String questionId;

    @JsonProperty("name")
    private String questionName;

    @JsonProperty("score")
    private Integer questionScore;

    //根据questionCreatorId查询创建人
    @JsonProperty("creator")
    private String questionCreator;

    //根据questionLevelId查询问题难度
    @JsonProperty("level")
    private String questionLevel;

    /**
     * 问题难度级别在数据库表question_level中的id
     */
    @JsonProperty("levelId")
    private int questionLevelId;

    //问题类型，根据questionTypeId获取
    @JsonProperty("type")
    private String questionType;

    //问题类型在数据库表question_type中的id
    @JsonProperty("typeId")
    private int questionTypeId;

    //问题分类，根据questionCategoryId获得
    @JsonProperty("category")
    private String questionCategory;

    //问题分类在数据库表question_category中的id
    @JsonProperty("categoryId")
    private int questionCategoryId;


    @JsonProperty("description")
    private String questionDescription;

    //问题选项列表，从questionOptionIds获得,需要自己额外给isAnswer赋值
    @JsonProperty("options")
    private List<QuestionOptionVo> questionOptionVoList;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
