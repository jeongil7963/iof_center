package com.iof.center.admin.crop.service;

import java.util.List;

import com.iof.center.admin.crop.vo.AdminCropVO;

public interface AdminCropService {
	void insert_crop(AdminCropVO vo);
	
	List<AdminCropVO> getRead_crop(int page, String keyword);
	
	int count_crop(String keyword);
	
	void delete_crop(int crop_idx);
	
	AdminCropVO get_crop(int crop_idx);
	
	void crop_modify(AdminCropVO vo);
	
	List<AdminCropVO> checkbox_crop(int unit_idx);
	
	List<AdminCropVO> adminCropList();
	
	AdminCropVO adminCropidxRead(AdminCropVO vo);
}
