package com.iof.center.user.dao;

import java.util.List;

import com.iof.center.user.vo.UserVO;

public interface UserDao {

	void insertUser(UserVO vo);
	
	void insertUser2(UserVO vo);

	void updateUser(UserVO vo);

	void updateUser2(UserVO vo);

	void deleteUser(String user_id);

	UserVO getUser(UserVO vo);

	List<UserVO> getUserList(UserVO vo);
	
	List<UserVO> getAllUserList();

	UserVO login(UserVO vo) throws Exception;

	int checkSignup(String user_id);

	int checkSignup_email(String user_email);
	
	int count_user2(String keyword );
	
	List<UserVO> getRead2(int page, String keyword );
	
	List<UserVO> search_id(String id);
}