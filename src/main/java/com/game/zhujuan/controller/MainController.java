package com.game.zhujuan.controller;

import com.game.zhujuan.common.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuchunpeng 2020/9/22
 */
@Api(tags = "测试接口")
@Slf4j
@RestController
@RequestMapping("/main")
public class MainController {

    @GetMapping
    public Result test(){
        return Result.init();
    }
}
