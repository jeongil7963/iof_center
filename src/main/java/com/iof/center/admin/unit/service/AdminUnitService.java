package com.iof.center.admin.unit.service;

import java.util.List;

import com.iof.center.admin.unit.vo.AdminUnitNameVO;
import com.iof.center.admin.unit.vo.AdminUnitVO;

public interface AdminUnitService {

	void Admin_unit_insert(AdminUnitVO vo);
	
	String adminRegionLastUnitCode(String unit_code);

	int count_unit(String keyword);
	
	List<AdminUnitVO> getRead_unit(int page, String keyword);
	
	void delete_unit(int unit_idx);
	
	AdminUnitVO get_unit(int unit_idx);
	
	void unit_modify(AdminUnitVO vo);
	
	List<AdminUnitVO> total_unit_code(int region_idx);
	
	List<AdminUnitNameVO> get_unit_name();
	
	public void unit_weather_update(AdminUnitVO vo);
}