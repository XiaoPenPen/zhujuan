package com.game.zhujuan.controller;

import cn.hutool.json.JSONObject;
import com.game.zhujuan.common.Result;
import com.game.zhujuan.controller.dto.RegisterDto;
import com.game.zhujuan.entity.User;
import com.game.zhujuan.service.CheckService;
import com.game.zhujuan.service.UserService;
import com.game.zhujuan.util.MyPasswordEncoder;
import com.game.zhujuan.util.RedisUtil;
import com.game.zhujuan.util.TokenUtil;
import com.game.zhujuan.util.UUIDUtil;
import com.google.common.collect.ImmutableMap;
import com.wf.captcha.GifCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * x=token的key  c=验证码的key  p=密码对称加密公钥 Authorization
 * @author xuchunpeng 2021/11/29
 */
@Api(tags = "匿名帐号")
@Slf4j
@RestController
@RequestMapping("anonymity")
public class AnonymityController {

    @Resource
    private RedisUtil redisUtil;
    @Resource
    private UserService userService;
    @Resource
    private MyPasswordEncoder myPasswordEncoder;
    @Resource
    private CheckService checkService;

    @ApiOperation("登录")
    @PostMapping("login")
    public Result login(@RequestBody RegisterDto registerDto, HttpServletRequest httpServletRequest){
        User user = checkService.checkLogin(registerDto);
        String authorization = UUIDUtil.getUUID();
        redisUtil.set(TokenUtil.buildAuthorizationKey(authorization), user, 120, TimeUnit.MINUTES);
        log.info("用户登陆 >>>>>>> [{}]", user.getUsername());
        user.setPassword("");
        return Result.init(ImmutableMap.of("user", user, "Authorization", authorization));
    }

    @ApiOperation("注册账号")
    @PostMapping("register")
    public Result register(@RequestBody @Validated RegisterDto registerDto){
        checkService.checkRegister(registerDto);
        User user = new User();
        user.setId(UUIDUtil.getUUID());
        user.setUsername(registerDto.getUsername());
        user.setNickname(registerDto.getNickname());
        user.setPassword(myPasswordEncoder.encode(registerDto.getPassword()));
        userService.save(user);
        // TODO 跳转到登录页面
        return Result.init();
    }

    @ApiOperation("验证码token")
    @GetMapping("token")
    public Result tokens(){
        String x = UUIDUtil.getUUID();
        String c = UUIDUtil.getUUID();
        String p = UUIDUtil.getUUID();
        JSONObject jsonObject = new JSONObject()
                .set("x",x)
                .set("c", c)
                .set("p", p);
        redisUtil.set(TokenUtil.buildCaptchaTokenKey(x), jsonObject, 3, TimeUnit.MINUTES);
        return Result.init(jsonObject);
    }

    @ApiOperation("获取验证码")
    @GetMapping("captcha")
    public void getCaptcha(HttpServletResponse response, @RequestParam String x) throws IOException {
        JSONObject jsonObject = redisUtil.get(TokenUtil.buildCaptchaTokenKey(x));
        if (jsonObject == null){
            PrintWriter writer = response.getWriter();
            writer.write("已失效");
            writer.flush();
            writer.close();
            response.setStatus(401);
            return;
        }

        ServletOutputStream outputStream = response.getOutputStream();

        //算术验证码 数字加减乘除. 建议2位运算就行:captcha.setLen(2);
//        ArithmeticCaptcha captcha = new ArithmeticCaptcha(120, 40);

        // 中文验证码
//        ChineseCaptcha captcha = new ChineseCaptcha(120, 40);

        // 英文与数字验证码
//        SpecCaptcha captcha = new SpecCaptcha(120, 40);

        //英文与数字动态验证码
        GifCaptcha captcha = new GifCaptcha(120, 40);

        // 中文动态验证码
//        ChineseGifCaptcha captcha = new ChineseGifCaptcha(120, 40);
        // 几位数运算，默认是两位
        captcha.setLen(4);
        // 获取运算的结果
        String result = captcha.text();
        redisUtil.set(TokenUtil.buildCaptchaKey(jsonObject.getStr("c")), result.toLowerCase(), 3, TimeUnit.MINUTES);
        captcha.out(outputStream);
    }

}
