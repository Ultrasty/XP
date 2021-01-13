package com.tongji.exam.service;

import com.tongji.exam.qo.RegisterDTO;
import com.tongji.exam.entity.User;
import com.tongji.exam.qo.LoginQo;
import com.tongji.exam.qo.UserInfoQo;
import com.tongji.exam.vo.UserInfoVo;
import com.tongji.exam.vo.UserVo;

public interface UserService {

    /**
     * 用户注册
     * @param registerDTO 用户注册所需的信息类
     * @return 注册成功的用户对象
     */
    User register(RegisterDTO registerDTO);

    /**
     * 用户登录
     * @param loginQo
     * @return
     */
    String login(LoginQo loginQo);

    /**
     * 获取用户信息
     * @param userId 用户id
     * @return 用户Vo
     */
    UserVo getUserInfo(String userId);

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    UserInfoVo getInfo(String userId);

    /**
     * 更新个人信息
     * @param userInfoQo 前端传来的要修改的用户信息
     * @param user_id 用户id
     * @return 更新结果，成功返回ok，失败返回null
     */
    String updateInfo(UserInfoQo userInfoQo,String user_id);
}
