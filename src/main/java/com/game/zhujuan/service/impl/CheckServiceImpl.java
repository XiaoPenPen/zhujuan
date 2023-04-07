package com.game.zhujuan.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.game.zhujuan.controller.dto.RegisterDto;
import com.game.zhujuan.controller.handler.exception.ParamCheckException;
import com.game.zhujuan.entity.User;
import com.game.zhujuan.service.CheckService;
import com.game.zhujuan.service.UserService;
import com.game.zhujuan.util.MyPasswordEncoder;
import com.game.zhujuan.util.RedisUtil;
import com.game.zhujuan.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuchunpeng 2021/12/7
 */
@Service
public class CheckServiceImpl implements CheckService {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private MyPasswordEncoder myPasswordEncoder;

    @Override
    public void checkRegister(RegisterDto registerDto) {
        checkCaptcha(registerDto);
        checkUsername(registerDto.getUsername(), false);
        checkNickname(registerDto.getNickname(), false);
    }

    @Override
    public User checkLogin(RegisterDto registerDto) {
        checkCaptcha(registerDto);
        checkUsername(registerDto.getUsername(), true);
        User user = userService.getOne(new QueryWrapper<User>().eq("username", registerDto.getUsername()));
        checkPassword(user, registerDto.getPasswordEncryptStr(), registerDto.getP());
        return user;
    }

    private void checkCaptcha(RegisterDto registerDto) {
        String captcha = redisUtil.get(TokenUtil.buildCaptchaKey(registerDto.getC()));
        if (StrUtil.isBlank(captcha)){
            throw new ParamCheckException("验证码已过期");
        }
        if (!captcha.equals(registerDto.getCaptcha().toLowerCase())){
            throw new ParamCheckException("验证码输入错误");
        }
    }

    private void checkUsername(String username, boolean isExist) {
        List<User> userList = userService.list(new QueryWrapper<User>().eq("username", username));
        if (isExist){
            if (userList.isEmpty()){
                throw new ParamCheckException("账号不存在!");
            }
        } else if (!userList.isEmpty()){
            throw new ParamCheckException("账号已存在!");
        }
    }

    private void checkPassword(User user, String passwordEncryptStr, String key){
        if (StrUtil.isBlank(passwordEncryptStr)){
            throw new RuntimeException("密码不能为空!");
        }
        String password = MyPasswordEncoder.decrypt(passwordEncryptStr, key);
        if (myPasswordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("密码错误!");
        }

    }

    private void checkNickname(String nickname, boolean isExist) {
        List<User> userList = userService.list(new QueryWrapper<User>().eq("nickname", nickname));
        if (isExist){
            if (userList.isEmpty()){
                throw new ParamCheckException("昵称不存在!");
            }
        } else if (!userList.isEmpty()){
            throw new ParamCheckException("昵称已存在!");
        }
    }


}
