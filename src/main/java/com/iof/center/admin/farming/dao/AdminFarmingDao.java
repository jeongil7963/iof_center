package com.iof.center.admin.farming.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.iof.center.admin.farming.vo.AdminFarmingVo;

@Repository("adminFarmingDao")
public class AdminFarmingDao {
	@Autowired
	private SqlSession session;
	
	public int adminFarmingInsert(AdminFarmingVo farming) {
		System.out.println("AdminFarmingDao adminFarmingInsert");
		return session.insert("farmingInsert",farming);
	}
	public List<AdminFarmingVo> adminFarmingList() {
		System.out.println("AdminFarmingService adminFarmingList");
		return session.selectList("farmingList");
	}
	public AdminFarmingVo adminFarmingRead(AdminFarmingVo farming) {
		System.out.println("AdminFarmingService adminFarmingRead");
		return session.selectOne("farmingRead",farming);
	}
	public int adminFarmingUpdate(AdminFarmingVo farming) {
		System.out.println("AdminFarmingService adminFarmingUpdate");
		return session.update("farmingUpdate", farming);
	}
	public int adminFarmingdelete(AdminFarmingVo farming) {
		System.out.println("AdminFarmingService adminFarmingdelete");
		return session.delete("farmingDelete", farming);
	}
	public AdminFarmingVo adminFarmingOneRead(AdminFarmingVo farming) {
		System.out.println("AdminFarmingService adminFarmingOneRead");
		return session.selectOne("farmingOneRead",farming);
	}
	public List<AdminFarmingVo> getRead_farming(int page, String keyword) {
		System.out.println("AdminFarmingService getRead_farming");
		Map<String, Object> map = new HashMap<String, Object>();
		page = (page - 1) * 10;
		map.put("page", page);
		map.put("keyword", keyword);
		return session.selectList("getRead_farming", map);
	}
	public int count_farming(String keyword) { 
		System.out.println("AdminFarmingService count_farming");
		return session.selectOne("count_farming", keyword);
	}
}
