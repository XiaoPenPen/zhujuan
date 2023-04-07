/**
 * @filename:UserService Tue Nov 23 23:14:38 GMT+08:00 2021
 * @project 用户认证  V1.0
 * Copyright(c) 2020 xuchunpeng Co. Ltd. 
 * All right reserved. 
 */
package com.game.zhujuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.game.zhujuan.entity.User;

/**
 * @author: xuchunpeng
 * 
 */
public interface UserService extends IService<User> {

    User login(User user);
	
}