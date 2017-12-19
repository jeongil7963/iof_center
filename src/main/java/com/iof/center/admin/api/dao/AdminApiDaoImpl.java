package com.iof.center.admin.api.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iof.center.admin.api.vo.AdminApiVO;
import com.iof.center.admin.cell.vo.AdminCellVO;

@Repository("adminApiDao")
public class AdminApiDaoImpl implements AdminApiDao {
	
	@Autowired
	private SqlSession session;
	
	@Override
	public void insert_api(AdminApiVO vo) {
		session.insert("insert_api", vo);
	}
	
	@Override
	public AdminApiVO getApi(AdminApiVO vo) {
		return session.selectOne("getApi", vo);
	}
	
	@Override
	public AdminApiVO adminApiMaxRead(AdminApiVO vo) {
		return session.selectOne("apiMaxRead", vo);
	}
	
	@Override
	public int adminApiDelete(AdminApiVO vo) {
		return session.delete("apidel", vo);
	}
	
	@Override
	public AdminApiVO adminapiIdxRead(AdminApiVO vo) {
		return session.selectOne("apiIdxRead", vo);
	}
	
}
