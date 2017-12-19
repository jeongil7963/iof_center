package com.iof.center.admin.uploadfiles.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iof.center.admin.uploadfiles.dao.AdminUploadfilesDao;
import com.iof.center.admin.uploadfiles.vo.AdminUploadfilesVo;

@Service("adminUploadfilesService")
public class AdminUploadfilesService {

	@Autowired
	private AdminUploadfilesDao dao;

	public int adminUploadfilesInsert(AdminUploadfilesVo uploadfiles) {
		System.out.println("uploads service / adminUploadfilesInsert");
		// TODO Auto-generated method stub
		return dao.adminUploadfilesInsert(uploadfiles);
	}

	public AdminUploadfilesVo adminUploadfilesRead(AdminUploadfilesVo uploadfiles) {
		System.out.println("uploads service / adminuploadfilesRead");
		return (AdminUploadfilesVo) dao.adminUploadfilesRead(uploadfiles);
	}
	
	public List<AdminUploadfilesVo> read_list(AdminUploadfilesVo uploadfiles){
		return dao.read_list(uploadfiles);
	}
	
	public int count_upload(String keyword) {
		return dao.count_upload(keyword);
	}
	
	public List<AdminUploadfilesVo> getRead_upload(int page, String keyword){
		return dao.getRead_upload(page, keyword);
	}
	
	public List<AdminUploadfilesVo> farm_f_idx_read_list(AdminUploadfilesVo uploadfiles){
		return dao.farm_f_idx_read_list(uploadfiles);
	}
	
	public void delUpload(String upload_idx){
		dao.delUpload(upload_idx);
	}

	public int adminUploadfilesDelete(AdminUploadfilesVo uploadfiles) {
		// TODO Auto-generated method stub
		System.out.println("uploads service / adminuploadfilesDelete");
		return dao.adminUploadfilesdelete(uploadfiles);
	}
	
	public int adminUploadfilesIdxDelete(AdminUploadfilesVo uploadfiles) {
		// TODO Auto-generated method stub
		System.out.println("uploads service / adminUploadfilesIdxDelete");
		return dao.adminUploadfilesIdxDelete(uploadfiles);
	}
	
	public AdminUploadfilesVo adminUploadfilesidxRead(AdminUploadfilesVo uploadfiles) {
		System.out.println("uploads service / adminUploadfilesidxRead");
		return (AdminUploadfilesVo) dao.adminUploadfilesidxRead(uploadfiles);
	}
	
	public int adminUploadfilesUpdate(AdminUploadfilesVo uploadfiles) {
		// TODO Auto-generated method stub
		System.out.println("uploads service / adminUploadfilesUpdate");
		return dao.adminUploadfilesUpdate(uploadfiles);
	}
	
	public int adminUploadmodifyUpdate(AdminUploadfilesVo uploadfiles) {
		// TODO Auto-generated method stub
		System.out.println("uploads service / adminUploadmodifyUpdate");
		return dao.adminUploadmodifyUpdate(uploadfiles);
	}
	
	
	
	
	
	//------------------------------ckeditor---------------------------------------------------------//
	
	public int adminCkfilesInsert(AdminUploadfilesVo uploadfiles) {
		System.out.println("uploads service / adminUploadfilesInsert");
		// TODO Auto-generated method stub
		return dao.adminCkfilesInsert(uploadfiles);
	}

	public AdminUploadfilesVo adminCkfilesRead(AdminUploadfilesVo uploadfiles) {
		System.out.println("uploads service / adminuploadfilesRead");
		return (AdminUploadfilesVo) dao.adminCkfilesRead(uploadfiles);
	}
	
	public AdminUploadfilesVo CkfilesReIdxRead(AdminUploadfilesVo uploadfiles) {
		System.out.println("uploads service / CkfilesReIdxRead");
		return (AdminUploadfilesVo) dao.CkfilesReIdxRead(uploadfiles);
	}

	public int adminCkfilesDelete(AdminUploadfilesVo uploadfiles) {
		// TODO Auto-generated method stub
		System.out.println("uploads service / adminuploadfilesDelete");
		return dao.adminCkfilesDelete(uploadfiles);
	}
	
	public AdminUploadfilesVo adminCkfilesidxRead(AdminUploadfilesVo uploadfiles) {
		System.out.println("uploads service / adminUploadfilesidxRead");
		return (AdminUploadfilesVo) dao.adminCkfilesRead(uploadfiles);
	}
	
	public int adminCkfilesUpdate(AdminUploadfilesVo uploadfiles) {
		// TODO Auto-generated method stub
		System.out.println("uploads service / adminUploadfilesUpdate");
		return dao.adminCkfilesUpdate(uploadfiles);
	}
	
	public AdminUploadfilesVo CkUploadRandom(AdminUploadfilesVo uploadfiles) {
		System.out.println("upload dao / CkUploadRandom");
		return dao.CkUploadRandom(uploadfiles);
	}
	

	public String ckeditorImageUpload(HttpServletRequest request, HttpServletResponse response, MultipartFile file, Integer frm_f_random) throws Exception {
		
		System.out.println("ckeditorImageUpload / frm_f_random =" + frm_f_random);
		OutputStream out = null;
		AdminUploadfilesVo uploadfiles = new AdminUploadfilesVo();
		String size = Long.toString(file.getSize());
		Date from = new Date();
		SimpleDateFormat trans = new SimpleDateFormat("yyyy-MM-dd");
		String path_time = trans.format(from);
		byte[] bytes = file.getBytes();
		String type = file.getOriginalFilename();
		int partidx = type.indexOf(".");
		
		String fseperate = type.substring(partidx + 1); // 확장자 짤라오기
		String savename = uploadfiles.uploadFile(file.getOriginalFilename(), file.getBytes(), fseperate);
		String localpath =  "c:/workspace/iof_ch/src/main/webapp/resources/upload/editor/" + savename;
		String url = "/resources/upload/editor/"+savename;
		
		File f = new File(localpath);
		if (AdminUploadfilesVo.badFileExtIsReturnBoolean(f)) {
			System.out.println("파일 확장자가 적절하지 않습니다.");
		} else {
			if (!f.exists())
				f.mkdirs();
			// /resources/upload/editor/ed76a316-b4b6-40d2-83bd-79bbfe30f741.PNG
			uploadfiles.setFrm_f_filepath(localpath);
			uploadfiles.setFrm_f_filename(savename);
			uploadfiles.setFrm_f_filename_org(file.getOriginalFilename());
			uploadfiles.setFrm_f_size(size);
			uploadfiles.setFrm_f_type(file.getContentType());
			uploadfiles.setRandom(frm_f_random);
			// /resources/upload/editor/3016ec59-57ab-4d7f-b21a-ec55c57a5531.PNG
			//3016ec59-57ab-4d7f-b21a-ec55c57a5531.PNG
			file.transferTo(f);
			
			// Create the file on server
			int result2 = dao.adminCkfilesInsert(uploadfiles);
			// fileservice.adminUploadfilesRead(uploadfiles); //?
		}
		
		String fileUrl = url; //url 경로
		System.out.println("fileUrl ="+fileUrl);
		//되는것 = String fileUrl = "/resources/upload/editor/노트북.jpg";
	       return fileUrl;
	}
	
}







/*
public String ckeditorImageUpload(HttpServletRequest request, HttpServletResponse response, MultipartFile file, Integer Random) throws Exception {
	
	AdminUploadfilesVo uploadfiles = new AdminUploadfilesVo();
	String size = Long.toString(file.getSize());
	
	Date from = new Date();
	SimpleDateFormat trans = new SimpleDateFormat("yyyy-MM-dd");
	String path_time = trans.format(from);
	
	OutputStream out = null;
	
	byte[] bytes = file.getBytes();
	String downpath = "webapp/resources/upload/";
	String urlcheck = "/resources/upload/";
	String tmppath = request.getSession().getServletContext().getRealPath("")+downpath;
	System.out.println("ImageUpload :  "+tmppath);
	

	
	String type = file.getOriginalFilename();
	int partidx = type.indexOf(".");
	String fseperate = type.substring(partidx + 1); // 확장자 짤라오기
	String savename = uploadfiles.uploadFile(file.getOriginalFilename(), file.getBytes(), fseperate);
	
	String uploadPath =  tmppath +path_time + "/" + savename;
	
	String dapath = downpath + path_time + "/" + savename;
	String url = urlcheck + path_time+"/"+savename;
	System.out.println(uploadPath);
	File f = new File(uploadPath);
	if (AdminUploadfilesVo.badFileExtIsReturnBoolean(f)) {
		System.out.println("파일 확장자가 적절하지 않습니다.");
	} else {
		if (!f.exists())
			f.mkdirs();
		
		uploadfiles.setFrm_f_filepath(dapath);
		uploadfiles.setFrm_f_filename(savename);
		uploadfiles.setFrm_f_filename_org(file.getOriginalFilename());
		uploadfiles.setFrm_f_size(size);
		uploadfiles.setFrm_f_type(file.getContentType());
		uploadfiles.setRandom(Random);
		
		
		file.transferTo(f);
		
		// Create the file on server
		int result2 = dao.adminCkfilesInsert(uploadfiles);
		// fileservice.adminUploadfilesRead(uploadfiles); //?
	}
	
	String fileUrl = url; //url 경로
	
       return fileUrl;
}

}
/*
 * public AdminUploadfilesVo adminUploadfilesRead(AdminUploadfilesVo upvo) {
 * return (AdminUploadfilesVo) dao.AdminUploadfilesDao(upvo); }
 * 
 * public AdminUploadfilesVo adminFarmingModify(AdminUploadfilesVo upvo) { //
 * TODO Auto-generated method stub return null; }
 * 
 * public int adminFarmingDelete(AdminUploadfilesVo upvo) { // TODO
 * Auto-generated method stub System.out.println("service delete");
 * 
 * return dao.adminFarmingdelete(upvo); }
 * 
 * public int adminFarmingModify2(AdminUploadfilesVo upvo) { int result =
 * dao.adminFarmingModify(upvo); return result; }
 */

/**
 * 지역정보 목록 @param @return List<AdminRegionVO> @exception
 */
/*
 * public List<AdminUploadfilesVo> adminFarmingList() { // TODO Auto-generated
 * method stub return dao.adminFarmingList(); }
 */
/**
 * 지역정보 최근 코드값 가져오기 @param @return String @exception
 */
