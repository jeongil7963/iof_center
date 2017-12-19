package com.iof.center.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iof.center.user.dao.UserDao;
import com.iof.center.user.vo.UserVO;

@Service("UserService")
public class UserServiceImpl implements UserService {

	@Resource(name="UserDao")
	private UserDao dao;
	
	@Override
	public void insertUser (UserVO vo) {
		dao.insertUser(vo);
	}
	
	@Override
	public void insertUser2 (UserVO vo) {
		dao.insertUser2(vo);
	}
	
	@Override
	public void updateUser(UserVO vo){
		dao.updateUser(vo);
	}

	@Override
	public void updateUser2(UserVO vo){
		dao.updateUser2(vo);
	}

	@Override
	public void deleteUser(String user_id){
		dao.deleteUser(user_id);
	}
	
	@Override
	public UserVO getUser(UserVO vo){
		return dao.getUser(vo);
	}

	@Override
	public List<UserVO> getUserList(UserVO vo){
		return dao.getUserList(vo);
	}

	@Override
	public UserVO login(UserVO vo) throws Exception{
		return dao.login(vo);
	}

	@Override
	public int checkSignup(String user_id){
		return dao.checkSignup(user_id);
	}

	@Override
	public int checkSignup_email(String user_email){
		return dao.checkSignup_email(user_email);
	}
	
	@Override
	public	List<UserVO> getAllUserList(){
		return dao.getAllUserList();
	}
	
	@Override 
	public int count_user2(String keyword ) {
		return dao.count_user2(keyword);
	}
	
	@Override
	public List<UserVO> getRead2(int page, String keyword ){
		return dao.getRead2(page, keyword);
	}
	
	public List<UserVO> search_id(String id){
		return dao.search_id(id);
	}
	
}

