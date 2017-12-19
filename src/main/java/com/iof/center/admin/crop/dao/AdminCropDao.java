package com.iof.center.admin.crop.dao;

import java.util.List;

import com.iof.center.admin.crop.vo.AdminCropVO;
import com.iof.center.admin.unit.vo.AdminUnitVO;

public interface AdminCropDao {	
	void insert_crop(AdminCropVO vo);
	
	List<AdminCropVO> getRead_crop(int page, String keyword);
	
	 int count_crop(String keyword);

	List<AdminCropVO> adminCropList();
	
	AdminCropVO adminCropidxRead(AdminCropVO vo);
	
	 void delete_crop(int crop_idx);

	 AdminCropVO get_crop(int crop_idx);
	 
	 void crop_modify(AdminCropVO vo);
	 
	 List<AdminCropVO> checkbox_crop(int unit_idx);
}
