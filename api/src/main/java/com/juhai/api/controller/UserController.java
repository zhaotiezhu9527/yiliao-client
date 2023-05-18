package com.juhai.api.controller;

import com.juhai.api.controller.request.RegisterPhoneRequest;
import com.juhai.commons.entity.User;
import com.juhai.commons.service.UserService;
import com.juhai.commons.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Api(value = "用户相关", tags = "用户")
@RequestMapping("/user")
@RestController
public class UserController {


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "注册1")
    @GetMapping("/test")
    public R test(HttpServletRequest httpServletRequest) {
        List<User> list = userService.list();
        return R.ok().put("users", list);
    }

    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public R registerPhone(@Validated RegisterPhoneRequest request, HttpServletRequest httpServletRequest) {
        return R.error();
    }
}
