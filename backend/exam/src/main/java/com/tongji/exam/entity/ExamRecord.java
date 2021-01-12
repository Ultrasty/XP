package com.tongji.exam.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class ExamRecord {
    /**
     * 主键
     */
    @Id
    private String examRecordId;
    /**
     * 参与的考试的id
     */
    private String examId;

    /**
     * 考生作答地每个题目的选项(题目和题目之间用_分隔，题目有多个选项地话用-分隔),用于查看考试详情
     */
    private String answerOptionIds;

    /**
     * 参与者，即user的id
     */
    private String examJoinerId;
    /**
     * 参加考试的日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date examJoinDate;
    /**
     * 考试耗时(秒)
     */
    private Integer examTimeCost;
    /**
     * 考试得分
     */
    private Integer examJoinScore;
    /**
     * 考试得分水平
     */
    private Integer examResultLevel;
}
