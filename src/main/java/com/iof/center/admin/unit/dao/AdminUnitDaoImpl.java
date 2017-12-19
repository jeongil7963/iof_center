package com.iof.center.admin.unit.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iof.center.admin.unit.vo.AdminUnitNameVO;
import com.iof.center.admin.unit.vo.AdminUnitVO;

@Repository("adminUnitDao")
public class AdminUnitDaoImpl implements AdminUnitDao {
	
	@Autowired
	private SqlSession session;
	
	@Override
	public void Admin_unit_insert(AdminUnitVO vo) {
		session.insert("unitInsert",vo);
	}

	@Override
	public String adminRegionLastUnitCode(String unit_code) {
		return session.selectOne("lastUnitCode",unit_code);
	}
	
	@Override
	public int count_unit(String keyword) {
		return session.selectOne("count_unit",keyword);
	}
	
	@Override
	public List<AdminUnitVO> getRead_unit(int page, String keyword) {
		Map<String, Object> map = new HashMap<String, Object>();
		page = (page - 1) * 10;
		map.put("page", page);
		map.put("keyword", keyword);
		return session.selectList("getRead_unit", map);
	}
	
	@Override
	public void delete_unit(int unit_idx) {
		session.delete("delete_unit", unit_idx);
	}
	
	@Override
	public AdminUnitVO get_unit(int unit_idx) {
		return session.selectOne("get_unit",unit_idx);
	}
	
	@Override
	public void unit_modify(AdminUnitVO vo) {
		session.update("unit_modify", vo);
	}
	
	@Override
	public List<AdminUnitVO> total_unit_code(int region_idx) {
		return session.selectList("total_unit_code",region_idx);
	}
	
	@Override
	public List<AdminUnitNameVO> get_unit_name(){
		return session.selectList("get_unit_name");
	}
	
	@Override
	public void unit_weather_update(AdminUnitVO vo) {
		session.update("unit_weather_update", vo);
	}
}
