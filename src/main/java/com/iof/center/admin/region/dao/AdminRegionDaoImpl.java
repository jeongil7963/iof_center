package com.iof.center.admin.region.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iof.center.admin.region.vo.AdminRegionVO;
import com.iof.center.user.vo.UserVO;

/**
* 관리자 지역정보 DB Access 설정
* @author 우성정
* @since 2017.10.13
*/
@Repository("adminRegionDao")
public class AdminRegionDaoImpl implements AdminRegionDao {
	
	@Autowired
	private SqlSession session;
	
	/**
	* 지역정보 등록
	* @param region
	* @return int
	* @exception 
	*/
	@Override
	public int adminRegionInsert(AdminRegionVO region) {
		// TODO Auto-generated method stub
		return session.insert("regionInsert",region);
	}

	@Override
	public int adminRegionModify(AdminRegionVO region) {
		// TODO Auto-generated method stub
		return session.update("regionModify",region);
	}

	@Override
	public int adminRegionDelete(AdminRegionVO region) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	* 지역정보 목록
	* @param region
	* @return List<AdminRegionVO>
	* @exception 
	*/
	@Override
	public List<AdminRegionVO> adminRegionList() {
		// TODO Auto-generated method stub
		return session.selectList("regionList");
	}

	@Override
	public AdminRegionVO adminRegionRead(AdminRegionVO region) {
		// TODO Auto-generated method stub
		return session.selectOne("regionRead",region);
	}
	
	/**
	* 지역정보 등록
	* @param 
	* @return String
	* @exception 
	*/
	@Override
	public String adminRegionLastRegionCode() {
		// TODO Auto-generated method stub
		return session.selectOne("lastRegionCode");
	}
	@Override
	public void delRegion(String region_idx) {
		session.delete("deleteRegion", region_idx);
	}
	
	@Override
	public int count_region(String keyword) {
		return session.selectOne("count_region", keyword);
	}
	
	@Override
	public List<AdminRegionVO> getRead_region(int page, String keyword) {
		Map<String, Object> map = new HashMap<String, Object>();
		page = (page - 1) * 10;
		map.put("page", page);
		map.put("keyword", keyword);
		return session.selectList("getRead_region", map);
	}

}
