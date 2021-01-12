package com.tongji.exam.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
public class Question {
    @Id
    private String questionId;
    private String questionName;
    private Integer questionScore;
    private String questionCreatorId;
    private Integer questionLevelId;
    private Integer questionTypeId;
    private Integer questionCategoryId;
    private String questionDescription;
    private String questionOptionIds;
    private String questionAnswerOptionIds;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
