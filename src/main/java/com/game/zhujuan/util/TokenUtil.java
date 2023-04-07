package com.game.zhujuan.util;


import com.game.zhujuan.common.Constant;

/**
 * @author xuchunpeng 2021/12/10
 */
public class TokenUtil {

    public static String buildCaptchaTokenKey(String token){
        return String.join(":", Constant.Redis.CAPTCHA_TOKEN, token);
    }

    public static String buildCaptchaKey(String captcha){
        return String.join(":", Constant.Redis.CAPTCHA, captcha);
    }

    public static String buildAuthorizationKey(String authorization){
        return String.join(":", Constant.Redis.AUTHORIZATION, authorization);
    }

}
