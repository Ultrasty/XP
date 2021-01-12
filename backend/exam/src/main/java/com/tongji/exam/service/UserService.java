package com.tongji.exam.service;

import com.tongji.exam.dto.RegisterDTO;
import com.tongji.exam.entity.User;
import com.tongji.exam.qo.LoginQo;
import com.tongji.exam.qo.UserInfoQo;
import com.tongji.exam.vo.UserInfoVo;
import com.tongji.exam.vo.UserVo;

public interface UserService {

    User register(RegisterDTO registerDTO);


    String login(LoginQo loginQo);


    UserVo getUserInfo(String userId);


    UserInfoVo getInfo(String userId);


    String updateInfo(UserInfoQo userInfoQo,String user_id);
}
