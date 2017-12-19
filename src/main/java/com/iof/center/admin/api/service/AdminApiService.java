package com.iof.center.admin.api.service;

import com.iof.center.admin.api.vo.AdminApiVO;

public interface AdminApiService {
	
	void insert_api(AdminApiVO vo);
	
	AdminApiVO getApi(AdminApiVO vo);
	
	AdminApiVO adminApiMaxRead(AdminApiVO vo);
	
	int adminApiDelete(AdminApiVO vo);
	
	public AdminApiVO adminapiIdxRead(AdminApiVO vo);
}
