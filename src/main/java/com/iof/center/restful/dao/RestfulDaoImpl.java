package com.iof.center.restful.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.iof.center.restful.vo.Cell_Insert_VO;

@Repository("RestfulDao")
public class RestfulDaoImpl implements RestfulDao {

	@Autowired
	private SqlSession session;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public void insert_rest(Cell_Insert_VO vo) {
		session.insert("insert_rest", vo);
	}
	
}
