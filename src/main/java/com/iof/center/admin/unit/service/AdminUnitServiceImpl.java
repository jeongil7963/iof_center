package com.iof.center.admin.unit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iof.center.admin.cell.vo.AdminCellVO;
import com.iof.center.admin.unit.dao.AdminUnitDao;
import com.iof.center.admin.unit.vo.AdminUnitNameVO;
import com.iof.center.admin.unit.vo.AdminUnitVO;

@Service("AdminUnitService")
public class AdminUnitServiceImpl implements AdminUnitService {
	
	@Resource(name="adminUnitDao")
	private AdminUnitDao dao;
	
	@Override
	public void Admin_unit_insert(AdminUnitVO vo) {
		dao.Admin_unit_insert(vo);
	}
	
	@Override
	public String adminRegionLastUnitCode(String unit_code) {
		return dao.adminRegionLastUnitCode(unit_code);
	}
	
	@Override
	public int count_unit(String keyword) {
		return dao.count_unit(keyword);
	}
	
	@Override
	public List<AdminUnitVO> getRead_unit(int page, String keyword){
		return dao.getRead_unit(page, keyword);
	}
	
	@Override
	public void delete_unit(int unit_idx) {
		dao.delete_unit(unit_idx);
	}
	
	@Override
	public AdminUnitVO get_unit(int unit_idx) {
		return dao.get_unit(unit_idx);
	}
	
	@Override
	public void unit_modify(AdminUnitVO vo) {
		dao.unit_modify(vo);
	}
	
	@Override
	public List<AdminUnitVO> total_unit_code(int region_idx) {
		return dao.total_unit_code(region_idx);
	}
	
	@Override 
	public List<AdminUnitNameVO> get_unit_name(){
		return dao.get_unit_name();
	}
	
	@Override
	public void unit_weather_update(AdminUnitVO vo) {
		dao.unit_weather_update(vo);
	}
}
