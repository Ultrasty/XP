package com.tongji.exam.controller;

import com.tongji.exam.qo.RegisterDTO;
import com.tongji.exam.entity.User;
import com.tongji.exam.enums.ResultEnum;
import com.tongji.exam.qo.LoginQo;
import com.tongji.exam.qo.UserInfoQo;
import com.tongji.exam.service.UserService;
import com.tongji.exam.vo.ResultVO;
import com.tongji.exam.vo.UserInfoVo;
import com.tongji.exam.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;


@RestController
@Api(tags = "User APIs")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     * @param registerDTO
     * @return
     */
    @PostMapping("/register")
    @ApiOperation("注册")
    ResultVO<User> register(@RequestBody RegisterDTO registerDTO) {
        ResultVO<User> resultVO;
        // 注册信息的完善，还有唯一性校验没(用户名、邮箱和手机号)已经在user表中通过unique来设置了
        User user = userService.register(registerDTO);
        if (user != null) {
            // 注册成功
            resultVO = new ResultVO<>(ResultEnum.REGISTER_SUCCESS.getCode(), ResultEnum.REGISTER_SUCCESS.getMessage(), user);
        } else {
            resultVO = new ResultVO<>(ResultEnum.REGISTER_FAILED.getCode(), ResultEnum.REGISTER_FAILED.getMessage(), null);
        }
        return resultVO;
    }

    /**
     * 根据用户名或邮箱登录,登录成功返回token
     * @param loginQo
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("根据用户名或邮箱登录,登录成功返回token")
    ResultVO<String> login(@RequestBody LoginQo loginQo) { // 这里不用手机号是因为手机号和用户名难以进行格式区分，而用户名和
        // 用户登录
        ResultVO<String> resultVO;
        String token = userService.login(loginQo);
        if (token != null) {
            // 登录成功
            resultVO = new ResultVO<>(ResultEnum.LOGIN_SUCCESS.getCode(), ResultEnum.LOGIN_SUCCESS.getMessage(), token);
        } else {
            // 登录失败
            resultVO = new ResultVO<>(ResultEnum.LOGIN_FAILED.getCode(), ResultEnum.LOGIN_FAILED.getMessage(), null);
        }
        return resultVO;
    }

    /**
     * 获取用户信息
     * @param request
     * @return
     */
    @GetMapping("/user-info")
    @ApiOperation("获取用户信息")
    ResultVO<UserVo> getUserInfo(HttpServletRequest request) {
        String userId = (String) request.getAttribute("user_id");
        UserVo userVo = userService.getUserInfo(userId);
        return new ResultVO<>(ResultEnum.GET_INFO_SUCCESS.getCode(), ResultEnum.GET_INFO_SUCCESS.getMessage(), userVo);
    }

    /**
     * 获取用户的详细信息，包括个人信息页面和操作权限
     * @param request
     * @return
     */
    @GetMapping("/info")
    @ApiOperation("获取用户的详细信息，包括个人信息页面和操作权限")
    ResultVO<UserInfoVo> getInfo(HttpServletRequest request) {
        System.out.println("进入/user/info的获取用户信息的接口");
        String userId = (String) request.getAttribute("user_id");
        UserInfoVo userInfoVo = userService.getInfo(userId);
        return new ResultVO<>(ResultEnum.GET_INFO_SUCCESS.getCode(), ResultEnum.GET_INFO_SUCCESS.getMessage(), userInfoVo);
    }

    @GetMapping("/test")
    @ApiOperation("测试接口")
    String test(HttpServletRequest request) {
        // 下面这两个属性都是登录拦截器从token中解析地，当用户名不对或者token过期时是走不到接口内的
        String userId = (String) request.getAttribute("user_id");
        String username = (String) request.getAttribute("username");
        System.out.println("用户id：" + userId);
        System.out.println("用户名：" + username);
        return "用户id：" + userId + "\n用户名：" + username;
    }

    /**
     * 修改用户信息
     * @param userInfoQo
     * @param request
     * @return
     */
    @PostMapping("/user-info/update")
    @ApiOperation("修改用户信息")
    String updateUserInfo(@RequestBody UserInfoQo userInfoQo,HttpServletRequest request){
        String userId = (String) request.getAttribute("user_id");
        String avatar=userInfoQo.getUserAvatar();
        String str[]=avatar.split("\"",3);
        System.out.println(str[1]);
        String base64Avatar=str[1];
        userInfoQo.setUserAvatar(base64Avatar);
        String result=userService.updateInfo(userInfoQo,userId);
        return result;
    }
}
