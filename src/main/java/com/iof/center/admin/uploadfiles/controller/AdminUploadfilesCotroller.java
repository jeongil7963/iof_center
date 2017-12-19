package com.iof.center.admin.uploadfiles.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iof.center.admin.farming.service.AdminFarmingService;
import com.iof.center.admin.uploadfiles.service.AdminUploadfilesService;
import com.iof.center.admin.uploadfiles.vo.AdminUploadfilesVo;
import com.iof.center.admin.uploadfiles.vo.FileDelete;
import com.iof.center.util.ByteCal;
import com.iof.center.util.PageMaker;

/**
* 관리자 셀 관리 
* @author 우성정
* @since 2017.10.16
*/
@Controller
@RequestMapping("/admin/uploadfiles")
public class AdminUploadfilesCotroller {
	
	@Autowired
	private AdminUploadfilesService fileservice;
	@Autowired
	private AdminFarmingService farmservice;
	/**
	* 셀 목록
	* @param request
	* @return view
	* @exception 
	*/
	
	
	@RequestMapping(value = "/list")
	public ModelAndView list_post(@RequestParam(defaultValue = "") String Keyword, PageMaker pagemaker, Model model) {
		System.out.println("Controller list");
		ModelAndView mav = new ModelAndView();
		System.out.println("list_post");
		System.out.println("keyword : " + Keyword);
		System.out.println("keyword : " + pagemaker.getPage());
		int count = 0;
		count = fileservice.count_upload(Keyword);
		pagemaker.setPage(pagemaker.getPage());
		pagemaker.setCount(count);
		int Page_Scale = 10;
		int curPage = pagemaker.getPage();
		int pageBegin = (curPage-1)*Page_Scale+1; //시작번호
		int pageEnd = pageBegin+Page_Scale-1; // 끝번호
		
		System.out.println("curPage"+curPage);
		System.out.println("pageBegin"+pageBegin);
		System.out.println("pageEnd"+pageEnd);
		
		

		System.out.println("count :" + count);
		List<AdminUploadfilesVo> upload_list = fileservice.getRead_upload(pagemaker.getPage(), Keyword);

		for(int i = 0; i<upload_list.size(); i++) {
			upload_list.get(i).setFrm_f_size(ByteCal.byteCalculation(upload_list.get(i).getFrm_f_size()));
			upload_list.get(i).setPageBegin(pageBegin);
			pageBegin++;
		}
		mav.addObject("pageMaker", pagemaker);
		mav.addObject("Keyword", Keyword);
		mav.addObject("upload_list", upload_list);
		mav.setViewName("admin/uploadfiles/list");
			return mav;
		}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ModelAndView delRegion(@RequestParam Map<String, Object> map, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		String RootPath = request.getSession().getServletContext().getRealPath("/");
		String delRegion = map.get("delSeqNo").toString();
		System.out.println("delRegion : " + delRegion);
		String[] uploadArr = delRegion.split(",");
		AdminUploadfilesVo uploadfiles = new AdminUploadfilesVo();
		int idx_array [] = new int [10];
		for(int i=0; i<uploadArr.length; i++) {
			idx_array[i] =(int) Integer.parseInt(uploadArr[i]);
			uploadfiles.setFrm_f_idx(idx_array[i]);
			uploadfiles = fileservice.adminUploadfilesidxRead(uploadfiles);
			FileDelete.OnlyFileDelete(RootPath, uploadfiles.getFrm_f_filepath());
			fileservice.delUpload(uploadArr[i]);
		}
		mav.setViewName("redirect:/admin/uploadfiles/list");
		return mav;
	}
	
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ModelAndView callDownload(@RequestParam(value = "frm_f_idx") int frm_f_idx, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("Controller download_get");
		AdminUploadfilesVo uploadfiles = new AdminUploadfilesVo();
		uploadfiles.setFrm_f_idx(frm_f_idx);
		List<AdminUploadfilesVo> upload_list = fileservice.farm_f_idx_read_list(uploadfiles);
		String uploadpath = "";
		for(int i = 0; i<upload_list.size(); i++) {
		uploadpath = upload_list.get(i).getFrm_f_filepath();
		String fullpath = request.getSession().getServletContext().getRealPath("/")+uploadpath;
		File downloadFile = new File(fullpath);

		if (!downloadFile.canRead()) {
			throw new Exception("File can't read(파일을 찾을 수 없습니다)");
		}
		return new ModelAndView("fileDownloadView", "downloadFile", downloadFile);
		}
		return null;
		
	}
	
	

	@RequestMapping(value="/ckeditorImageUpload")
	public void ckeditorImageUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload,
									@RequestParam(value = "random") Integer frm_f_random) throws Exception {
	
		System.out.println("uploadfiles controller / ckeditorImageUpload");
		System.out.println("random=" + frm_f_random);
		String fileUrl = fileservice.ckeditorImageUpload(request, response, upload, frm_f_random);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset-utf-8");
		String callback = request.getParameter("CKEditorFuncNum");
		
		
		

		//String fileUrl = fileservice.ckeditorImageUpload(request, response, upload);
		PrintWriter writer = response.getWriter();
		writer.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
	               + callback
	               + ",'"
	               + fileUrl
	               + "','이미지를 업로드 하였습니다.'"
	               + ")</script>");
	}
	
	
	/*
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset-utf-8");
	String callback = request.getParameter("CKEditorFuncNum");

	PrintWriter printWriter = null;	
	String fileUrl = fileservice.ckeditorImageUpload(request, response, upload);
	System.out.println(callback);
	System.out.println(fileUrl);
	printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
               + callback
               + ",'"
               + fileUrl
               + "','이미지를 업로드 하였습니다.'"
               + ")</script>");
       printWriter.flush();
       return ;
*/	
	/**
	* 셀 등록 폼
	* @param request
	* @return view
	* @exception 
	*/
	@RequestMapping(value="/regist", method=RequestMethod.GET)
	public String registerForm(Model model){
		return "admin/uploadfiles/regist";
	}
	
	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public String regist(Model model){
		return "redirect:/admin/uploadfiles/list";
	}
	/*
	public @ResponseBody String boardFileDelete(int a) {
		AdminUploadfilesVo uploadfiles = new AdminUploadfilesVo();
		AdminUploadfilesVo upload = new AdminUploadfilesVo();
		uploadfiles.setFrm_f_idx(a);
		upload = fileservice.adminUploadfilesRead(uploadfiles);
		System.out.println(upload.getFrm_f_filepath());
		String path = '"'+upload.getFrm_f_filepath()+'"';// 삭제할 파일의 경로
				
 
	File file = new File(path);
		if(file.exists() == true){
		file.delete();
	}
	return null;
} */
}
