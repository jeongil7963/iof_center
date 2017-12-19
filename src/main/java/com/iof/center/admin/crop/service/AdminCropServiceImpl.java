package com.iof.center.admin.crop.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iof.center.admin.crop.dao.AdminCropDao;
import com.iof.center.admin.crop.vo.AdminCropVO;

@Service("adminCropService")
public class AdminCropServiceImpl implements AdminCropService {
	
	@Resource(name="adminCropDao")
	private AdminCropDao dao;
	
	@Override
	public void insert_crop(AdminCropVO vo) {
		dao.insert_crop(vo);
	}
	
	@Override
	public List<AdminCropVO> adminCropList() {
		return dao.adminCropList();
	}
	
	@Override
	public AdminCropVO adminCropidxRead(AdminCropVO vo) {
		return dao.adminCropidxRead(vo);
	}
	
	
	@Override
	public List<AdminCropVO> getRead_crop(int page, String keyword){
		return dao.getRead_crop(page, keyword);
	}
	
	@Override
	public int count_crop(String keyword) {
		return dao.count_crop(keyword);
	}
	
	@Override
	public void delete_crop(int crop_idx) {
		dao.delete_crop(crop_idx);
	}
	
	@Override
	public  AdminCropVO get_crop(int crop_idx) {
		return dao.get_crop(crop_idx);
	}
	
	@Override
	 public void crop_modify(AdminCropVO vo) {
		dao.crop_modify(vo);
	}
	
	@Override
	public  List<AdminCropVO> checkbox_crop(int unit_idx){
		return dao.checkbox_crop(unit_idx);
	}
}
