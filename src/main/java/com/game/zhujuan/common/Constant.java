package com.game.zhujuan.common;

/**
 * @author xuchunpeng 2021/12/10
 */
public interface Constant {

    interface Redis {
        // 验证码token前缀
        String CAPTCHA_TOKEN = "captcha_token";
        // 验证码前缀
        String CAPTCHA = "captcha";

        String AUTHORIZATION = "Authorization";
    }

}
