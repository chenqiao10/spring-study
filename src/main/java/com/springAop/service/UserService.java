package com.springAop.service;

import org.springframework.stereotype.Service;

import com.springAop.model.User;
@Service
public class UserService implements IUserService{

	@Override
	public void insertUser(User user) {
		user.setId(1);
	}

}
