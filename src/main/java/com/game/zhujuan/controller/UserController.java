package com.game.zhujuan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.game.zhujuan.common.Result;
import com.game.zhujuan.entity.User;
import com.game.zhujuan.service.UserService;
import com.game.zhujuan.util.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: xuchunpeng
 * @time    Tue Nov 23 23:14:38 GMT+08:00 2021
 *
 */

@Api(tags="用户信息" )
@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserService userService;

    /**
     * 新增
     */
    @PutMapping("/user")
    @ApiOperation(value = "新增")
    public Result insert(@RequestBody User user){
        user.setId(UUIDUtil.getUUID());
        userService.save(user);
        return Result.init(user);
    }

    /**
     * 批量删除
     * @param   ids
     */
    @DeleteMapping("/user")
    @ApiOperation(value = "批量删除")
    public Result delete(@RequestParam List<String> ids){
        userService.removeByIds(ids);
        return Result.init();
    }

    /**
    * 删除单个
    * @param   id
    */
    @DeleteMapping("/user/{id}")
    @ApiOperation(value = "删除单个")
    public Result deleteById(@PathVariable String id){
        userService.removeById(id);
        return Result.init();
    }

    /**
    * 修改
    * @param
    */
    @PostMapping("/user")
    @ApiOperation(value = "修改")
    public Result update(@RequestBody User user){
        userService.updateById(user);
        return Result.init();
    }

    /**
    * 查询列表
    * @param
    */
    @GetMapping("/users")
    @ApiOperation(value = "查询列表")
    public Result listUsers(@RequestParam @ApiParam("当前页码") Integer pageIndex,@RequestParam @ApiParam("当前页大小") Integer pageSize){
        IPage page = userService.getBaseMapper().selectPage(new Page<>(pageIndex, pageSize), null);
        return Result.init(page);
    }

    /**
    * 查询单个
    * @param
    */
    @GetMapping("/user/{id}")
    @ApiOperation(value = "查询单个")
    public Result get(@PathVariable String id){
        User user = userService.getById(id);
        return Result.init(user);
    }


}