/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-05-14 08:27
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.tongji.exam.repository;

import com.tongji.exam.entity.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionOptionRepository extends JpaRepository<QuestionOption, String> {
}
