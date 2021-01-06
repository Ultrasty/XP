/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-05-14 08:28
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.tongji.exam.repository;

import com.tongji.exam.entity.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionTypeRepository extends JpaRepository<QuestionType, Integer> {
}
