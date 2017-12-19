package com.iof.center.admin.region.service;

import java.util.List;

import com.iof.center.admin.region.vo.AdminRegionVO;
import com.iof.center.user.vo.UserVO;

public interface AdminRegionService {
	//지역정보 등록
	public  int adminRegionInsert(AdminRegionVO region);
	
	//지역정보 수정
	public  int adminRegionModify(AdminRegionVO region);
	
	//지역정보 삭제
	public  int adminRegionDelete(AdminRegionVO region);
	
	public List<AdminRegionVO> adminRegionList();
	
	//선택된 지역정보 가져오기
	public  AdminRegionVO adminRegionRead(AdminRegionVO region);
	
	//가장 최근에 등록된 코드값 가져오기
	public String adminRegionLastRegionCode();

	public int count_region(String keyword);
	
	public List<AdminRegionVO> getRead_region(int page, String keyword);
	
	void delRegion(String region_idx);
}



