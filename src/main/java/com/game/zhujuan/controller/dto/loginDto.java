package com.game.zhujuan.controller.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author xuchunpeng 2021/12/10
 */
@Data
public class loginDto {
    @NotBlank(message = "登陆账号不能为空")
    @Pattern(regexp = "^[0-9A-Za-z]{5,15}$", message = "登陆账号必须由字母+数字组成，且长度在5 - 15之间")
    private String username;

    @Length(min = 2, max = 8, message = "昵称长度必须在2-8之间。")
    @NotBlank(message = "昵称不能为空。")
    private String nickname;

    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "\"^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,12}$\"", message = "至少含有一个字母和一个数字和一个特殊字符, 且长度在6 - 12之间")
    private String password;

    private String captcha;

    private String c;

    private String p;

}
