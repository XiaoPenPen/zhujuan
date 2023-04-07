package com.game.zhujuan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.game.zhujuan.entity.User;
import com.game.zhujuan.mapper.UserMapper;
import com.game.zhujuan.service.UserService;
import org.springframework.stereotype.Service;

/**   
 * @author: xuchunpeng
 * 
 */
@Service
public class UserServiceImpl  extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User login(User user) {
        return null;
    }
}