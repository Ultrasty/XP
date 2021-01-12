package com.tongji.exam.repository;

import com.tongji.exam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUserUsername(String username);

    //根据用户邮箱查找合适用户
    User findByUserEmail(String email);

    User findByUserId(String userId);
}
