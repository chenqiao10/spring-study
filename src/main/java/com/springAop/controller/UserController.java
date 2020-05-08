package com.springAop.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springAop.model.User;
import com.springAop.service.IUserService;

@Controller
@RequestMapping("/")
public class UserController {
	@Resource
	private IUserService userService;
	@RequestMapping("/insertUser")
	public String insertUser() {
		User user=new User();
		user.setName("111111");
		userService.insertUser(user);
		return "200";
	}
}
