package com.tongji.exam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * created by kz on
 */
@Entity
@Table(name = "up_code", schema = "exam")
public class UpCodeEntity {
    private String code;

    @Id
    @Column(name = "code", nullable = false, length = 100)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
