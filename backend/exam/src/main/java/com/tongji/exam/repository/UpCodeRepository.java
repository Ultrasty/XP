package com.tongji.exam.repository;

import com.tongji.exam.entity.UpCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpCodeRepository  extends JpaRepository<UpCodeEntity, String> {

    UpCodeEntity findUpCodeEntityByCode(String code);
}
