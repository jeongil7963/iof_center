package com.iof.center.admin.crop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iof.center.admin.crop.vo.AdminCropVO;

@Repository("adminCropDao")
public class AdminCropDaoImpl implements AdminCropDao {
	
	@Autowired
	private SqlSession session;
	
	@Override
	public void insert_crop(AdminCropVO vo) {
		session.insert("insert_crop", vo);
	}
	
	@Override
	public List<AdminCropVO> getRead_crop(int page, String keyword){
		Map<String, Object> map = new HashMap<String, Object>();
		page = (page - 1) * 10;
		map.put("page", page);
		map.put("keyword", keyword);
		return session.selectList("getRead_crop", map);
	}
	
	@Override
	public List<AdminCropVO> adminCropList(){
		
		return session.selectList("adminCropList");
	}
	
	@Override
	public AdminCropVO adminCropidxRead(AdminCropVO vo) {
		return session.selectOne("adminCropidxRead", vo);
	}
	
	
	
	
	@Override
	public int count_crop(String keyword) {
		return session.selectOne("count_crop",keyword);
	}
	
	@Override
	public void delete_crop(int crop_idx) {
		session.delete("delete_crop", crop_idx);
	}
	
	@Override
	public  AdminCropVO get_crop(int crop_idx) {
		return session.selectOne("get_crop",crop_idx);
	}
	
	@Override
	 public void crop_modify(AdminCropVO vo) {
		session.update("crop_modify", vo);
	}
	
	@Override
	public  List<AdminCropVO> checkbox_crop(int unit_idx){
		return session.selectList("checkbox_crop",unit_idx);
	}
}
