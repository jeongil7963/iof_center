package com.spring.acorn.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spring.acorn.user.model.sql.UserDao;
import com.spring.acorn.user.model.vo.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource(name="userDao")
	private UserDao dao;
	
	@Override
	public UserVO login(UserVO user) {
		// TODO Auto-generated method stub
		System.out.println("user login service");
		
		return dao.loginRow(user);
	}
	
}
