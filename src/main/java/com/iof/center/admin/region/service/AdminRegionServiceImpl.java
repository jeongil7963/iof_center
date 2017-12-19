package com.iof.center.admin.region.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iof.center.admin.region.dao.AdminRegionDao;
import com.iof.center.admin.region.vo.AdminRegionVO;
import com.iof.center.user.vo.UserVO;

/**
* 관리자 지역정보 SERVICE 설정
* @author 우성정
* @since 2017.10.13
*/
@Service("adminRegionService")
public class AdminRegionServiceImpl implements AdminRegionService {
	
	@Resource(name="adminRegionDao")
	private AdminRegionDao dao;
	
	@Override
	public AdminRegionVO adminRegionRead(AdminRegionVO region) {
		// TODO Auto-generated method stub
		return dao.adminRegionRead(region);
	}
	
	/**
	* 지역정보 등록
	* @param region
	* @return int
	* @exception 
	*/
	@Override
	public int adminRegionInsert(AdminRegionVO region) {
		// TODO Auto-generated method stub
		return dao.adminRegionInsert(region);
	}

	@Override
	public int adminRegionModify(AdminRegionVO region) {
		// TODO Auto-generated method stub
		return dao.adminRegionModify(region);
	}

	@Override
	public int adminRegionDelete(AdminRegionVO region) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	* 지역정보 목록
	* @param 
	* @return List<AdminRegionVO>
	* @exception 
	*/
	
	@Override
	public List<AdminRegionVO> adminRegionList() {
		// TODO Auto-generated method stub
		return dao.adminRegionList();
	}
	

	@Override
	public int count_region(String keyword ) {
		return dao.count_region(keyword);
	}
	
	@Override
	public List<AdminRegionVO> getRead_region(int page, String keyword ){
		return dao.getRead_region(page, keyword);
	}
	
	@Override
	public void delRegion(String region_idx){
		dao.delRegion(region_idx);
	}
	/**
	* 지역정보 최근 코드값 가져오기
	* @param 
	* @return String
	* @exception 
	*/
	@Override
	public String adminRegionLastRegionCode() {
		// TODO Auto-generated method stub
		return dao.adminRegionLastRegionCode();
	}

}
