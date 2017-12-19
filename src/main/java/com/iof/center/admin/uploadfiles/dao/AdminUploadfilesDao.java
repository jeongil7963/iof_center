package com.iof.center.admin.uploadfiles.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iof.center.admin.farming.vo.AdminFarmingVo;
import com.iof.center.admin.uploadfiles.vo.AdminUploadfilesVo;



@Repository("adminUploadfilesDao")
public class AdminUploadfilesDao {
	
	@Autowired
	private SqlSession session;
	
	public int adminUploadfilesInsert(AdminUploadfilesVo uploadfiles) {
		// TODO Auto-generated method stub
		System.out.println("upload dao / fileUploadInsert");
		return session.insert("fileUploadInsert",uploadfiles);
	}
	
	public AdminUploadfilesVo adminUploadfilesRead(AdminUploadfilesVo uploadfiles) {
		System.out.println("upload dao / adminUploadfilesRead");
		return session.selectOne("fileUploadRead",uploadfiles);
	}
	
	public List<AdminUploadfilesVo> read_list(AdminUploadfilesVo uploadfiles){
		return session.selectList("fileUploadRead", uploadfiles);
	}
	
	public int adminUploadfilesdelete(AdminUploadfilesVo uploadfiles) {
		System.out.println("Dao delete");
		return session.delete("fileUploadDelete", uploadfiles);
	}
	
	public int adminUploadfilesIdxDelete(AdminUploadfilesVo uploadfiles) {
		System.out.println("Dao delete");
		return session.delete("fileUploadIdxDelete", uploadfiles);
	}
	
	public List<AdminUploadfilesVo> farm_f_idx_read_list(AdminUploadfilesVo uploadfiles){
		return session.selectList("fileUploadIdxRead", uploadfiles);
	}
	
	public AdminUploadfilesVo adminUploadfilesidxRead(AdminUploadfilesVo uploadfiles) {
		System.out.println("upload dao / adminUploadfilesidxRead");
		return session.selectOne("fileUploadIdxRead",uploadfiles);
	}
	
	public int adminUploadfilesUpdate(AdminUploadfilesVo uploadfiles) {
		System.out.println("upload dao / adminUploadfilesUpdate");
		return session.update("fileUpdate", uploadfiles);
	}
	
	public int adminUploadmodifyUpdate(AdminUploadfilesVo uploadfiles) {
		System.out.println("upload dao / adminUploadmodifyUpdate");
		return session.update("fileModifyUpdate", uploadfiles);
	}
	
	public int count_upload(String keyword) { 
		System.out.println("upload dao / count_upload");
		return session.selectOne("count_upload", keyword);
	}
	
	public List<AdminUploadfilesVo> getRead_upload(int page, String keyword) {
		System.out.println("upload dao / getRead_upload");
		Map<String, Object> map = new HashMap<String, Object>();
		page = (page - 1) * 10;
		map.put("page", page);
		map.put("keyword", keyword);
		return session.selectList("getRead_upload", map);
	}
	
	public void delUpload(String upload_idx) {
		session.delete("deleteUpload", upload_idx);
	}

	
	//-----------------------------------------------------------ckeditor------------------------------------------------------------//
	
	public int adminCkfilesInsert(AdminUploadfilesVo uploadfiles) {
		// TODO Auto-generated method stub
		System.out.println("upload dao / fileUploadInsert");
		return session.insert("CkUploadInsert",uploadfiles);
	}
	
	public AdminUploadfilesVo adminCkfilesRead(AdminUploadfilesVo uploadfiles) {
		System.out.println("upload dao / adminUploadfilesRead");
		return session.selectOne("CkUploadRead",uploadfiles);
	}
	
	public AdminUploadfilesVo CkfilesReIdxRead(AdminUploadfilesVo uploadfiles) {
		System.out.println("upload dao / CkfilesReIdxRead");
		return session.selectOne("CkUploadOneSelect", uploadfiles);
	}
	
	public AdminUploadfilesVo CkUploadRandom(AdminUploadfilesVo uploadfiles) {
		System.out.println("upload dao / CkUploadRandom");
		return session.selectOne("CkUploadRandom", uploadfiles);
	}
	
	public int adminCkfilesDelete(AdminUploadfilesVo uploadfiles) {
		System.out.println("Dao delete");
		return session.delete("CkUploadDelete", uploadfiles);
	}
	
	public AdminUploadfilesVo adminCkfilesidxRead(AdminUploadfilesVo uploadfiles) {
		System.out.println("upload dao / adminUploadfilesidxRead");
		return session.selectOne("CkUploadIdxRead",uploadfiles);
	}
	
	public int adminCkfilesUpdate(AdminUploadfilesVo uploadfiles) {
		System.out.println("upload dao / adminUploadfilesUpdate");
		return session.update("CkUpdate", uploadfiles);
	}
	

	
}
