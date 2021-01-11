/***********************************************************
 * @Description : 对外REST接口
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-05-16 23:45
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.tongji.exam.controller;

import com.tongji.exam.dto.RegisterDTO;
import com.tongji.exam.entity.User;
import com.tongji.exam.enums.ResultEnum;
import com.tongji.exam.qo.LoginQo;
import com.tongji.exam.service.UserService;
import com.tongji.exam.vo.ResultVO;
import com.tongji.exam.vo.UserInfoVo;
import com.tongji.exam.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Api(tags = "User APIs")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    RedisTemplate<String,String> redisTemplate;

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

    @GetMapping("/user-info")
    @ApiOperation("获取用户信息")
    ResultVO<UserVo> getUserInfo(HttpServletRequest request) {
        String userId = (String) request.getAttribute("user_id");
        UserVo userVo = userService.getUserInfo(userId);
        return new ResultVO<>(ResultEnum.GET_INFO_SUCCESS.getCode(), ResultEnum.GET_INFO_SUCCESS.getMessage(), userVo);
    }

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

    @PostMapping("/getSmsCaptcha")
    @ApiOperation("获取短信验证码")
    String getSmsCaptcha(@RequestBody User user){
        try {
            String captcha=getLinkNo();
            String url = "https://sms-api.upyun.com/api/messages";

            Map<String, String> jsonMap = new HashMap<>();
            //如果需要其它的请求头信息、都可以在这里追加
            HttpHeaders httpHeaders = new HttpHeaders();

            httpHeaders.add("Authorization", "BNm0ib0FdEgibBO8u3LDvFytBcEeD3");

            MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");

            httpHeaders.setContentType(type);

            httpHeaders.setConnection("Keep-Alive");

            jsonMap.put("template_id", "4027");
            jsonMap.put("mobile", user.getUserPhone());
            jsonMap.put("vars", captcha);
            redisTemplate.opsForValue().set(user.getUserPhone(),captcha,60, TimeUnit.SECONDS);

            HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(jsonMap, httpHeaders);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> apiResponse = restTemplate.postForEntity(url, httpEntity, String.class);
            return "验证码发送成功，请注意短信通知。";
        }catch (Exception e){
            e.printStackTrace();
            return "出现问题，尝试重新获取...";
        }
    }

    public String getLinkNo() {
        // 用字符数组的方式随机
        String model = "01234567890";
        StringBuilder sb=new StringBuilder();
        char[] m = model.toCharArray();
        for (int j = 0; j < 6; j++) {
            char c = m[(int) (Math.random() * 10)];
            sb.append(c);
        }
        return sb.toString();
    }
}
