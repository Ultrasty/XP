package com.tongji.exam.repository;

import com.tongji.exam.entity.UpCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpCodeRepository  extends JpaRepository<UpCode, String> {

    //升级码，用户注册时升级成老师
    UpCode findUpCodeEntityByCode(String code);
}
