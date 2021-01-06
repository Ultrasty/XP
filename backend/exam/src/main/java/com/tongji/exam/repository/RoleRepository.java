/***********************************************************
 * @Description : 角色的数据库操作类
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-05-14 08:29
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.tongji.exam.repository;

import com.tongji.exam.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
