package com.hytx.chapterService.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hytx.chapterService.model.UserDomain;
import com.hytx.chapterService.service.UserService;

@Api(value = "用户接口", tags = {"User Api"})
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
    private UserService userService;
	
	@ApiOperation(value = "添加用户信息", response = Integer.class, notes = "添加用户信息")
    @PostMapping("/add")
    public int addUser(){
    	System.out.println("保存用户！");
    	UserDomain u = new UserDomain();
    	u.setPassword("123");
    	u.setPhone("5311");
    	u.setUserName("xiaoweiwei");
        return userService.addUser(u);
    }

   @ApiOperation(value = "查询用户集合", response = Integer.class, notes = "查询用户集合")
   @GetMapping("/all")
   public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize){
        return userService.findAllUser(pageNum,pageSize);
    }
    
    
}
