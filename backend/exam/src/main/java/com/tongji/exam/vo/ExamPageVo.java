package com.tongji.exam.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ExamPageVo {
    //分页时每个分页的大小
    private Integer pageSize;

    //当前是在第几页，比前端传过来小1
    private Integer pageNo;

    //一共有多少条符合条件的记录
    private Long totalCount;

    //一共有多少页
    private Integer totalPage;

    //当前页的详细数据
    @JsonProperty("data")
    private List<ExamVo> examVoList;
}
