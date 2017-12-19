package com.spring.acorn.user.model.sql;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.acorn.user.model.vo.UserVO;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SqlSession session;
	
	@Override
	public UserVO loginRow(UserVO user) {
		// TODO Auto-generated method stub
		System.out.println("user login dao");
		System.out.println("param:" + user.toString());
		return session.selectOne("login", user);
	}
}
