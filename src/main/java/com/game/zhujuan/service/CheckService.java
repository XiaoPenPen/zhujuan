package com.game.zhujuan.service;


import com.game.zhujuan.controller.dto.RegisterDto;
import com.game.zhujuan.entity.User;

/**
 * @author xuchunpeng 2021/12/7
 */
public interface CheckService {

    void checkRegister(RegisterDto registerDto);

    User checkLogin(RegisterDto registerDto);

}
