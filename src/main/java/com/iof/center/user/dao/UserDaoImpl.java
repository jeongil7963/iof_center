package com.iof.center.user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.iof.center.user.vo.UserVO;

@Repository("UserDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private SqlSession session;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	/* 회원 가입 시 토큰 추가 */
	@Override
	public void insertUser(UserVO vo) {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		vo.setUser_token(uuid);
		session.insert("insertUser", vo);
	}
	
	@Override
	public void insertUser2(UserVO vo) {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		vo.setUser_token(uuid);
		session.insert("insertUser2", vo);
	}

	@Override
	public void updateUser(UserVO vo) {
		session.update("updateUser", vo);
	}

	@Override
	public void updateUser2(UserVO vo) {
		session.update("updateUser2", vo);
	}

	@Override
	public void deleteUser(String user_id) {
		session.delete("deleteUser", user_id);
	}

	@Override
	public UserVO getUser(UserVO vo) {
		return session.selectOne("getUser", vo);
	}

	@Override
	public List<UserVO> getUserList(UserVO vo) {
		return session.selectList("getUserList", vo);
	}

	@Override
	public UserVO login(UserVO vo) throws Exception {
		UserVO result = session.selectOne("loginUser", vo);
		return result;
	}

	@Override
	public int checkSignup(String user_id) {
		int result = session.selectOne("checkSignup", user_id);
		return result;
	}

	@Override
	public int checkSignup_email(String user_email) {
		int result = session.selectOne("checkSignup_email", user_email);
		return result;
	}

	@Override
	public List<UserVO> getAllUserList() {
		return session.selectList("getAllUserList");
	}

	@Override
	public int count_user2(String keyword) {
		return session.selectOne("count_user2", keyword);
	}

	@Override
	public List<UserVO> getRead2(int page, String keyword) {
		Map<String, Object> map = new HashMap<String, Object>();
		page = (page - 1) * 10;
		map.put("page", page);
		map.put("keyword", keyword);
		return session.selectList("getRead2", map);
	}
	
	public List<UserVO> search_id(String id){
		return session.selectList("search_id", id);
	}
}
