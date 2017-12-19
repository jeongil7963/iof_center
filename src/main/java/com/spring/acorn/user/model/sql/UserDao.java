package com.spring.acorn.user.model.sql;


import com.spring.acorn.user.model.vo.UserVO;


public interface UserDao {
	
	public UserVO loginRow(UserVO user);
}
