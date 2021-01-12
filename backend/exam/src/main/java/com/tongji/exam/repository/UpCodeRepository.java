package com.tongji.exam.repository;

import com.tongji.exam.entity.UpCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpCodeRepository  extends JpaRepository<UpCode, String> {

    UpCode findUpCodeEntityByCode(String code);
}
