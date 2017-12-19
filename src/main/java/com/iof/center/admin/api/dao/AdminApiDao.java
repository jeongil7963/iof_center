package com.iof.center.admin.api.dao;

import com.iof.center.admin.api.vo.AdminApiVO;

public interface AdminApiDao {
	
	void insert_api(AdminApiVO vo);
	
	AdminApiVO getApi(AdminApiVO vo);
	
	AdminApiVO adminApiMaxRead(AdminApiVO vo);

	int adminApiDelete(AdminApiVO vo);
	
	public AdminApiVO adminapiIdxRead(AdminApiVO vo);
}
