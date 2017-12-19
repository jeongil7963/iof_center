package com.iof.center.admin.api.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iof.center.admin.api.dao.AdminApiDao;
import com.iof.center.admin.api.vo.AdminApiVO;
import com.iof.center.admin.raspberry.vo.AdminRaspberryVo;

@Service("adminApiService")
public class AdminApiServiceImpl implements AdminApiService {
	
	@Resource(name="adminApiDao")
	private AdminApiDao dao;
	
	@Override
	public void insert_api(AdminApiVO vo) {
		dao.insert_api(vo);
	}
	
	
	@Override
	public AdminApiVO getApi(AdminApiVO vo) {
		return dao.getApi(vo);
	}
	
	@Override
	public AdminApiVO adminApiMaxRead(AdminApiVO vo) {
		return (AdminApiVO) dao.adminApiMaxRead(vo);
	}
	
	@Override
	public int adminApiDelete(AdminApiVO vo) {
		return dao.adminApiDelete(vo);
	}
	
	@Override
	public AdminApiVO adminapiIdxRead(AdminApiVO vo) {
		return (AdminApiVO) dao.adminapiIdxRead(vo);
	}
}
