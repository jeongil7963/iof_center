package com.iof.center.admin.farming.service;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iof.center.admin.farming.dao.AdminFarmingDao;
import com.iof.center.admin.farming.vo.AdminFarmingVo;
import com.iof.center.admin.unit.vo.AdminUnitVO;


/**
* 관리자 재배방법 정보 SERVICE 설정
* @author 우성정
* @since 2017.10.13
*/

@Service("adminFarmingService")
public class AdminFarmingService {

	//@Resource(name="adminFarmingDao")
	@Autowired
	private AdminFarmingDao dao;
	
	public AdminFarmingVo adminFarmingRead(AdminFarmingVo farming) {
		System.out.println("AdminFarmingService adminFarmingRead");
		return (AdminFarmingVo) dao.adminFarmingRead(farming);
	}
	
	public int adminFarmingInsert(AdminFarmingVo farming) {
		System.out.println("AdminFarmingService adminFarmingInsert");
		return dao.adminFarmingInsert(farming);
	}

	public int adminFarmingDelete(AdminFarmingVo farming) {
		System.out.println("AdminFarmingService adminFarmingDelete");
		
		return dao.adminFarmingdelete(farming);
	}
	
	public int adminFarmingUpdate(AdminFarmingVo farming) {
		System.out.println("AdminFarmingService.adminFarmingUpdate");
		int result = dao.adminFarmingUpdate(farming);
		return result;
	}
	
	public AdminFarmingVo adminFarmingOneRead(AdminFarmingVo farming) {
		System.out.println("AdminFarmingService Oneread");
		return (AdminFarmingVo) dao.adminFarmingOneRead(farming);
	}
	public List<AdminFarmingVo> getRead_farming(int page, String keyword){
		return dao.getRead_farming(page, keyword);
	}
	public int count_farming(String keyword) {
		return dao.count_farming(keyword);
	}
	
	
	
	/**
	* 지역정보 목록
	* @param 
	* @return List<AdminRegionVO>
	* @exception 
	*/
	public List<AdminFarmingVo> adminFarmingList() {
		System.out.println("AdminFarmingService adminFarmingList");
		return dao.adminFarmingList();
	}

	/**
	* 지역정보 최근 코드값 가져오기
	* @param 
	* @return String
	* @exception 
	*/
	
	
}
