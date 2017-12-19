package com.iof.center.admin.farming.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.iof.center.admin.crop.service.AdminCropService;
import com.iof.center.admin.crop.vo.AdminCropVO;
import com.iof.center.admin.farming.service.AdminFarmingService;
import com.iof.center.admin.farming.vo.AdminFarmingVo;
import com.iof.center.admin.uploadfiles.service.AdminUploadfilesService;
import com.iof.center.admin.uploadfiles.vo.AdminUploadfilesVo;
import com.iof.center.admin.uploadfiles.vo.FileDelete;
import com.iof.center.user.vo.UserVO;
import com.iof.center.util.PageMaker;

@Controller
@RequestMapping("/admin/farming")
public class AdminFarmingController {

	// @Resource(name="adminFarmingService")
	@Autowired
	private AdminFarmingService service;

	@Autowired
	private AdminUploadfilesService fileservice;
	
	@Autowired
	private AdminCropService cropservice;
	// list DB값 불러오기, 페이징, 검색
	/*
	@RequestMapping(value = "/list")
	public ModelAndView list_post(@RequestParam(defaultValue = "") String Keyword, PageMaker pagemaker, Model model) {
		System.out.println("Controller list");
		ModelAndView mav = new ModelAndView();
		System.out.println("keyword : " + pagemaker.getPage());
		int count = 0;
		count = service.count_farming(Keyword);
		pagemaker.setPage(pagemaker.getPage());
		pagemaker.setCount(count);
		List<AdminCropVO> crop_list = null;
		List<String> name = null;
		
		List<AdminFarmingVo> farm_list = service.getRead_farming(pagemaker.getPage(), Keyword);

		int[] occurance = new int [farm_list.size()];
		String b [] = new String [occurance.length];
		b[0] = "";
		String array[][] = new String[10][10];
		AdminCropVO vo = new AdminCropVO();
		String crop_name [];
		
		for(int i = 0; i<occurance.length; i++) {
		occurance [i] = org.springframework.util.StringUtils.countOccurrencesOf(farm_list.get(i).getCrop_idx(), ",");
		crop_name = new String [occurance[i]+1];
		crop_list = new ArrayList<AdminCropVO>(occurance[i]+1);
		crop_name = farm_list.get(i).getCrop_idx().split(",");
		for(int j = 0; j<crop_name.length; j++) {
			System.out.println(crop_name[j]);
			int a = Integer.parseInt(crop_name[j]);
			vo.setCrop_idx(a);
			crop_list.add(vo=cropservice.adminCropidxRead(vo));
		}
		
		for(int w=0; w<crop_list.size(); w++) {
			//
			array[i][w] = crop_list.get(w).crop_name;
			System.out.println("array["+i+"]"+"["+w+"]"+array[i][w]);
			name = new ArrayList<String>(crop_list.size());
			name.add(array[i][w]);
			System.out.println("???");
			farm_list.get(i).setCrop_name(name);
			
		}
	
		}
		System.out.println("-----------------------------------");
		for(int i = 0; i<occurance.length; i++) {
			for(int w=0; w<crop_list.size(); w++) {
				//System.out.println("array["+i+"]"+"["+w+"]"+array[i][w]);
				mav.addObject("name", array[i][w]);
				if(array[i][w]!=null)
				b[i] = b[i] + array[i][w]+",";
				System.out.println("b["+i+"]"+"="  + b[i]);
				
				farm_list.get(i).setCrop_name2(b[i]+",");
			}
		}
		
		

		
		

		mav.addObject("name_list", name);
		mav.addObject("crop_list", crop_list);
		mav.addObject("farm_list", farm_list);
		mav.addObject("pageMaker", pagemaker);
		mav.addObject("Keyword", Keyword);
		mav.setViewName("admin/farming/list");
		
		return mav;
	}
	*/
	
	@RequestMapping(value = "/list")
	public ModelAndView list_post(@RequestParam(defaultValue = "") String Keyword, PageMaker pagemaker, Model model) {
		System.out.println("Controller list");
		ModelAndView mav = new ModelAndView();
		System.out.println("keyword : " + pagemaker.getPage());
		int count = 0;
		count = service.count_farming(Keyword);
		pagemaker.setPage(pagemaker.getPage());
		pagemaker.setCount(count);
		AdminCropVO vo = new AdminCropVO();
		
		List<AdminFarmingVo> farm_list = service.getRead_farming(pagemaker.getPage(), Keyword);
		int idx[] = new int [farm_list.size()];
		for(int i = 0; i<farm_list.size(); i++) {
			idx[i] = Integer.parseInt(farm_list.get(i).getCrop_idx());
			vo.setCrop_idx(idx[i]);
			vo = cropservice.adminCropidxRead(vo);
			farm_list.get(i).setCrop_name(vo.getCrop_name());
		}
		
	
		mav.addObject("farm_list", farm_list);
		mav.addObject("pageMaker", pagemaker);
		mav.addObject("Keyword", Keyword);
		mav.setViewName("admin/farming/list");
		return mav;
	}
	

	
	
	// download 서버에 저장된 파일 다운로드
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ModelAndView callDownload(@RequestParam(value = "frm_f_idx") int frm_f_idx, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("Controller download_get");
		AdminUploadfilesVo uploadfiles = new AdminUploadfilesVo();
		uploadfiles.setFrm_f_idx(frm_f_idx);
		int frm_f_no = 0;
		List<AdminUploadfilesVo> upload_list = fileservice.farm_f_idx_read_list(uploadfiles);
		for(int i = 0; i<upload_list.size(); i++) {
			frm_f_no = upload_list.get(i).getFrm_f_no();
			frm_f_no++;
			upload_list.get(i).setFrm_f_no(frm_f_no);
			fileservice.adminUploadmodifyUpdate(upload_list.get(i));
		}
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
	
	@RequestMapping(value = "/editordownload", method = RequestMethod.GET)
	public ModelAndView editorcallDownload(@RequestParam(value = "frm_idx") int frm_idx, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("Controller download_get");
		AdminUploadfilesVo uploadfiles = new AdminUploadfilesVo();
		uploadfiles.setFrm_idx(frm_idx);
		uploadfiles = fileservice.adminCkfilesRead(uploadfiles);
		String fullpath = request.getSession().getServletContext().getRealPath("/")+uploadfiles.getFrm_f_filepath();
		File downloadFile = new File(fullpath);

		if (!downloadFile.canRead()) {
			throw new Exception("File can't read(파일을 찾을 수 없습니다)");
		}
		return new ModelAndView("fileDownloadView", "downloadFile", downloadFile);
	}

	// read 읽기 페이지 DB값 가져오기
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public ModelAndView read(Model model, AdminFarmingVo farming, AdminUploadfilesVo uploadfiles, AdminUploadfilesVo editorupload) {
		ModelAndView mv = new ModelAndView("admin/farming/read");

		System.out.println("Controller read_get");
		System.out.println("uploadfiles idx = " + uploadfiles.getFrm_idx());
		System.out.println("read" + farming.getFrm_idx());
		AdminFarmingVo farm = service.adminFarmingRead(farming);
		int cropname = Integer.parseInt(farm.getCrop_idx());
		AdminCropVO cropvo = new AdminCropVO();
		cropvo.setCrop_idx(cropname);
		cropvo = cropservice.adminCropidxRead(cropvo);
		farm.setCrop_name(cropvo.getCrop_name());
		
		
		List<AdminUploadfilesVo> upload_list = fileservice.read_list(uploadfiles);
	
	try {
		editorupload = fileservice.adminCkfilesRead(editorupload);
	} catch (NullPointerException ne) {
		System.out.println(" 에디터 첨부파일이 없습니다.");
	}
		mv.addObject("farm", farm);
		mv.addObject("editorupload", editorupload);
		mv.addObject("uploadedfile", upload_list);
		
		//model.addAttribute("editorupload", editorupload);
		//model.addAttribute("farm", farm);
		/*
		mav.addObject("farm_list", farm_list);
		mav.addObject("pageMaker", pagemaker);
		mav.addObject("Keyword", Keyword);*/
		return mv;
	}
	
	

	   @RequestMapping(value="/check", method = RequestMethod.GET) 
	   public String check(int [] frm_idx_array, AdminFarmingVo farming, AdminUploadfilesVo uploadfiles,
	         HttpServletRequest request, HttpServletResponse response) throws Exception{
	      System.out.println("Controller check_get");
	      String downpath = request.getSession().getServletContext().getRealPath("");
	      System.out.println("spath = "+downpath);
	      
	      for(int i = 0; i<farming.getFrm_idx_array().length; i++) {
	         farming.setFrm_idx_array(frm_idx_array);
	      }
	      for(int i = 0; i<farming.getFrm_idx_array().length; i++) {
	         System.out.println("check : " + frm_idx_array[i]);
	         farming.setFrm_idx(frm_idx_array[i]);
	         int result = service.adminFarmingDelete(farming);
	         
	         uploadfiles.setFrm_idx(frm_idx_array[i]);  //업로드 folder 삭제

	         try {
	            uploadfiles = fileservice.adminUploadfilesRead(uploadfiles);
	            String onpath = uploadfiles.getFrm_f_filepath();
	            System.out.println("onpath" + onpath);
	            System.out.println("downpath" + downpath);
	            FileDelete.OnlyFileDelete(onpath, downpath);
	            int delresult = fileservice.adminUploadfilesDelete(uploadfiles);
	            
	            uploadfiles.setFrm_idx(frm_idx_array[i]);
	            uploadfiles = fileservice.adminCkfilesRead(uploadfiles);
	            String editorpath = uploadfiles.getFrm_f_filepath();
	            FileDelete.OnlyFileDelete(editorpath, downpath);
	            int editorresult = fileservice.adminCkfilesDelete(uploadfiles);
	            
	         } catch (NullPointerException np) {
	            // TODO: handle exception
	            System.out.println("삭제할 사진이 없습니다");
	         }
	      }
	      return "redirect:/admin/farming/list";
	   }

	/*
	 * checkbox + 데이터 삭제
	 */
	// modify 수정페이지 DB값 뿌려주기
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify_get(Model model, AdminFarmingVo farming, AdminUploadfilesVo uploadfiles) {
		System.out.println("Controller modify_get");
		
		AdminFarmingVo farm = service.adminFarmingRead(farming);
		
		int cropname = Integer.parseInt(farm.getCrop_idx());
		AdminCropVO cropvo = new AdminCropVO();
		cropvo.setCrop_idx(cropname);
		cropvo = cropservice.adminCropidxRead(cropvo);
		farm.setCrop_name(cropvo.getCrop_name());
		List<AdminCropVO> crop = cropservice.adminCropList();
		List<AdminUploadfilesVo> uploaded;
		uploaded = fileservice.read_list(uploadfiles);
		
		model.addAttribute("crop", crop);
		model.addAttribute("uploaded", uploaded);
		model.addAttribute("farm", farm);
		return "admin/farming/modify";
	}
	// modify 수정페이지 수정된 값 DB 저장
	/*
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify_post(AdminFarmingVo farming, @RequestParam(value = "frm_idx") int frm_idx,
			@RequestParam(value="frm_f_idx", required=false) int frm_f_idx[],
			@RequestParam(defaultValue="") int [] frm_f_no,
			MultipartHttpServletRequest multipartRequest,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("Controller modify_post");

		String RootPath = request.getSession().getServletContext().getRealPath("/");
		
		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current_time = transFormat.format(from);
		farming.setFrm_updatetime(current_time);
		farming.setFrm_idx(frm_idx);
		int result = service.adminFarmingUpdate(farming);
		
		AdminUploadfilesVo uploadfiles = new AdminUploadfilesVo();
		
		uploadfiles.setFrm_idx(farming.getFrm_idx());
		
		List<AdminUploadfilesVo> upload_list = fileservice.read_list(uploadfiles); //1.frm_idx 기준 객체 리스트로 가져오기
		
		List<MultipartFile> files = multipartRequest.getFiles("file");
		
		for(int a = 0; a<files.size(); a++) {
			
			uploadfiles.setFrm_f_idx(frm_f_idx[a]);
			
			if(files.get(a).getSize()!=0) {
				
				uploadfiles = fileservice.adminUploadfilesidxRead(uploadfiles);
				
				FileDelete.OnlyFileDelete( RootPath, uploadfiles.getFrm_f_filepath());
				
				String readlFolder = request.getSession().getServletContext().getRealPath("/webapp/resources/upload/")+farming.getFrm_idx()+"/";
				
				File dir = new File(readlFolder+uploadfiles.getFrm_f_filename());
				
					if(AdminUploadfilesVo.badFileExtIsReturnBoolean(dir)) {
				
						System.out.println("파일 확장자가 적절하지 않습니다.");
						return "redirect:/admin/farming/list";}
					
					if(!dir.isDirectory()) {
						dir.mkdirs();
					}
						
						for(int i = 0; i<files.size(); i++) {
		        		
		        		String originalfileName = files.get(i).getOriginalFilename(); //files[i]번째의 진짜 이름
		        		String type = files.get(i).getOriginalFilename(); //files[i]번째의 타입
		    			int partidx = type.indexOf(".");
		    			String fseperate = type.substring(partidx + 1); // 확장자 짤라오기 //files[i]번째의 DB용 타입
		        		//String savefilename = uploadfiles.uploadFile(originalfileName, files.get(i).getBytes(), fseperate); //files[i]번째의 저장 이름
		        		String fileSize = Long.toString(files.get(i).getSize()); //files[i]번째 사이즈
		        		//String dbpath = "webapp/resources/upload/"+farming.getFrm_idx()+"/"+savefilename;
		        		// savedName = uploadFile(savedName, file.getBytes());
		        		
		        		String savePath = readlFolder + "/"+uploadfiles.getFrm_f_filename(); //저장될 파일 경로
		        		files.get(i).transferTo(new File(savePath));
		 
		        		
		        		uploadfiles.setFrm_f_filename_org(originalfileName);
		        		uploadfiles.setFrm_f_type(type);
		        		//uploadfiles.setFrm_f_filename(savefilename);
		        		uploadfiles.setFrm_f_size(fileSize); //스트링 변환 필요
		        		//uploadfiles.setFrm_f_filepath(dbpath);
		        		uploadfiles.setFrm_idx(farming.getFrm_idx());
		        		System.out.println(readlFolder);
		        		fileservice.adminUploadmodifyUpdate(uploadfiles);
		        	
		        
		        }
				
			}
			
			for(int i = 0; i<frm_f_no.length; i++) { 
				for(int j = 0; j<upload_list.size(); j++)
				
				if((upload_list.get(j).getFrm_f_no() == frm_f_no[i])) {
					try {
						String onpath1 = upload_list.get(i).getFrm_f_filepath();
						System.out.println("onpath1 = "+onpath1);
						FileDelete.OnlyFileDelete( RootPath, onpath1);
						int delete = fileservice.adminUploadfilesIdxDelete(upload_list.get(i));
					} catch ( NullPointerException ne) {
						System.out.println("체크박스값이 없음");
					}
					
				}
				else {
					System.out.println("흠");
				}
				}
	}
		return "redirect:/admin/farming/list";
	}
	
	
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify_post(AdminFarmingVo farming,@RequestParam(value = "frm_idx") int frm_idx,
			@RequestParam(value="frm_f_idx", required=false) int frm_f_idx[],
			@RequestParam(defaultValue="") int [] frm_f_no,
			MultipartHttpServletRequest multipartRequest,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("Controller modify_post");
		
		String RootPath = request.getSession().getServletContext().getRealPath("/");
		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current_time = transFormat.format(from);
		farming.setFrm_updatetime(current_time);
		farming.setFrm_idx(frm_idx);
		int result = service.adminFarmingUpdate(farming);
		
		AdminUploadfilesVo uploadfiles = new AdminUploadfilesVo();
		
		uploadfiles.setFrm_idx(farming.getFrm_idx());
		List<AdminUploadfilesVo> upload_list = fileservice.read_list(uploadfiles); //1.frm_idx 기준 객체 리스트로 가져오기
		List<MultipartFile> files = multipartRequest.getFiles("file");
		
		for(int i = 0; i<files.size(); i++) {
			System.out.println("filename = " + files.get(i).getName());
			System.out.println("filesize = " + files.get(i).getSize());
			if(files.get(i).getSize() != 0 && ) {
				files.get(i).
			}
		}
		
		return "redirect:/admin/farming/list";
	}
	*/
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify_post(AdminFarmingVo farming, @RequestParam(value = "frm_idx") int frm_idx,
			@RequestParam(value="frm_f_idx", required=false) int frm_f_idx[],
			@RequestParam(defaultValue="") int [] frm_f_no,
			MultipartHttpServletRequest multipartRequest,
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "crop_idx") String crop_idx) throws Exception {
		
		System.out.println("Controller modify_post");

		String RootPath = request.getSession().getServletContext().getRealPath("/");
		
		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current_time = transFormat.format(from);
		farming.setFrm_updatetime(current_time);
		farming.setFrm_idx(frm_idx);
		farming.setCrop_idx(crop_idx);
		
		AdminCropVO vo = new AdminCropVO();
		int tmp = Integer.parseInt(farming.getCrop_idx());
		vo.setCrop_idx(tmp);
		vo = cropservice.adminCropidxRead(vo);
		farming.setCrop_name(vo.getCrop_name());
		
		int result = service.adminFarmingUpdate(farming);
		
		AdminUploadfilesVo uploadfiles = new AdminUploadfilesVo();
		uploadfiles.setFrm_idx(farming.getFrm_idx());
		
		List<AdminUploadfilesVo> upload_list = fileservice.read_list(uploadfiles); //1.frm_idx 기준 객체 리스트로 가져오기
		List<MultipartFile> file_list = multipartRequest.getFiles("file");
		
		
		for(int i = 0; i<frm_f_no.length; i++) { 
			for(int j = 0; j<upload_list.size(); j++)
			
			if((upload_list.get(j).getFrm_f_no() == frm_f_no[i]) && file_list.get(j).getSize()==0) {
					System.out.println("체크박스만");
				try {
					String onpath1 = upload_list.get(i).getFrm_f_filepath();
					System.out.println("onpath1 = "+onpath1);
					FileDelete.OnlyFileDelete( RootPath, onpath1);
					int delete = fileservice.adminUploadfilesIdxDelete(upload_list.get(i));
				} catch ( NullPointerException ne) {
					System.out.println("체크박스값이 없음");
				}
				
			}
			else if((upload_list.get(j).getFrm_f_no() == frm_f_no[i]) && file_list.get(j).getSize()!=0) {
						System.out.println("첨부파일 + 체크박스");
					try {
						uploadfiles.setFrm_f_idx(upload_list.get(j).getFrm_f_idx());
						uploadfiles = fileservice.adminUploadfilesidxRead(uploadfiles);
						
						String onpath1 = upload_list.get(j).getFrm_f_filepath();
						System.out.println("onpath1 = "+onpath1);
						FileDelete.OnlyFileDelete( RootPath, uploadfiles.getFrm_f_filepath());
						
						String originalfileName = file_list.get(j).getOriginalFilename(); //files[i]번째의 진짜 이름
		        		String type = file_list.get(j).getOriginalFilename(); //files[i]번째의 타입
		    			int partidx = type.indexOf(".");
		    			String fseperate = type.substring(partidx + 1); // 확장자 짤라오기 //files[i]번째의 DB용 타입
		        		String fileSize = Long.toString(file_list.get(j).getSize()); //files[i]번째 사이즈
		        		String savefilename = uploadfiles.uploadFile(originalfileName, file_list.get(j).getBytes(), fseperate); //files[i]번째의 저장 이름
		        		String dbpath = "webapp/resources/upload/"+uploadfiles.getFrm_idx()+"/"+savefilename;
						
						String savePath = RootPath+"/webapp/resources/upload/"+uploadfiles.getFrm_idx()+"/"+savefilename;
						File dir = new File(savePath);
						if(AdminUploadfilesVo.badFileExtIsReturnBoolean(dir)) {
							System.out.println("파일 확장자가 적절하지 않습니다.");
							return "redirect:/admin/farming/list";
						}
						
						if(!dir.isDirectory()) {
							dir.mkdirs();
							System.out.println(dir.getPath());
						}
						file_list.get(j).transferTo(new File(savePath));
						uploadfiles.setFrm_f_filename_org(file_list.get(j).getOriginalFilename());
						uploadfiles.setFrm_f_filename(savefilename);
						uploadfiles.setFrm_f_size(fileSize);
						uploadfiles.setFrm_f_filepath(dbpath);
						uploadfiles.setFrm_f_type(file_list.get(j).getContentType());
						uploadfiles.setFrm_f_updatetime(current_time);
						fileservice.adminUploadmodifyUpdate(uploadfiles);
						
						
						//int delete = fileservice.adminUploadfilesIdxDelete(upload_list.get(i));
					} catch ( NullPointerException ne) {
						System.out.println("체크박스값이 없음");
					}
			}
			
			else if((upload_list.get(j).getFrm_f_no() != frm_f_no[i]) && file_list.get(j).getSize()!=0) {
				System.out.println("첨부파일+체크박스");
				uploadfiles.setFrm_f_idx(upload_list.get(j).getFrm_f_idx());
				
					uploadfiles = fileservice.adminUploadfilesidxRead(uploadfiles);
					FileDelete.OnlyFileDelete( RootPath, uploadfiles.getFrm_f_filepath());
					String originalfileName = file_list.get(j).getOriginalFilename(); //files[i]번째의 진짜 이름
	        		String type = file_list.get(j).getOriginalFilename(); //files[i]번째의 타입
	    			int partidx = type.indexOf(".");
	    			String fseperate = type.substring(partidx + 1); // 확장자 짤라오기 //files[i]번째의 DB용 타입
	        		String fileSize = Long.toString(file_list.get(j).getSize()); //files[i]번째 사이즈
	        		String savefilename = uploadfiles.uploadFile(originalfileName, file_list.get(j).getBytes(), fseperate); //files[i]번째의 저장 이름
	        		String dbpath = "webapp/resources/upload/"+uploadfiles.getFrm_idx()+"/"+savefilename;
					
					String savePath = RootPath+"/webapp/resources/upload/"+uploadfiles.getFrm_idx()+"/"+savefilename;
					File dir = new File(savePath);
					if(AdminUploadfilesVo.badFileExtIsReturnBoolean(dir)) {
						System.out.println("파일 확장자가 적절하지 않습니다.");
						return "redirect:/admin/farming/list";
					}
					
					if(!dir.isDirectory()) {
						dir.mkdirs();
						System.out.println(dir.getPath());
					}
					file_list.get(j).transferTo(new File(savePath));
					uploadfiles.setFrm_f_filename_org(file_list.get(j).getOriginalFilename());
					uploadfiles.setFrm_f_filename(savefilename);
					uploadfiles.setFrm_f_size(fileSize);
					uploadfiles.setFrm_f_filepath(dbpath);
					uploadfiles.setFrm_f_type(file_list.get(j).getContentType());
					uploadfiles.setFrm_f_updatetime(current_time);
					fileservice.adminUploadmodifyUpdate(uploadfiles);
				
				
			}
		}
		for(int a = 0; a<file_list.size(); a++) {
			try {
				if(file_list.get(a).getSize()!=0) {
				
				System.out.println("첨부파일만 수정");
				uploadfiles.setFrm_f_idx(upload_list.get(a).getFrm_f_idx());
				uploadfiles = fileservice.adminUploadfilesidxRead(uploadfiles);
				
				FileDelete.OnlyFileDelete( RootPath, uploadfiles.getFrm_f_filepath());
				
				String originalfileName = file_list.get(a).getOriginalFilename(); //files[i]번째의 진짜 이름
	    		String type = file_list.get(a).getOriginalFilename(); //files[i]번째의 타입
				int partidx = type.indexOf(".");
				String fseperate = type.substring(partidx + 1); // 확장자 짤라오기 //files[i]번째의 DB용 타입
	    		String fileSize = Long.toString(file_list.get(a).getSize()); //files[i]번째 사이즈
	    		String savefilename = uploadfiles.uploadFile(originalfileName, file_list.get(a).getBytes(), fseperate); //files[i]번째의 저장 이름
	    		String dbpath = "webapp/resources/upload/"+uploadfiles.getFrm_idx()+"/"+savefilename;
				
				String savePath = RootPath+"/webapp/resources/upload/"+uploadfiles.getFrm_idx()+"/"+savefilename;
				File dir = new File(savePath);
				if(AdminUploadfilesVo.badFileExtIsReturnBoolean(dir)) {
					System.out.println("파일 확장자가 적절하지 않습니다.");
					return "redirect:/admin/farming/list";
				}
				
				if(!dir.isDirectory()) {
					dir.mkdirs();
					System.out.println(dir.getPath());
				}
				file_list.get(a).transferTo(new File(savePath));
				uploadfiles.setFrm_f_filename_org(file_list.get(a).getOriginalFilename());
				uploadfiles.setFrm_f_filename(savefilename);
				uploadfiles.setFrm_f_size(fileSize);
				uploadfiles.setFrm_f_filepath(dbpath);
				uploadfiles.setFrm_f_type(file_list.get(a).getContentType());
				uploadfiles.setFrm_f_updatetime(current_time);
				fileservice.adminUploadmodifyUpdate(uploadfiles);
			}
			} catch (NullPointerException ne) {
				// TODO: handle exception
			}
		}
		return "redirect:/admin/farming/list";
	}

	// regist 재배방법 등록
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public String registerForm(Model model, HttpSession session) {
		AdminFarmingVo farming = new AdminFarmingVo();
		farming = service.adminFarmingOneRead(farming);
		List<AdminCropVO> crop = cropservice.adminCropList();
		Random ran = new Random();
		Integer random = ran.nextInt();
		model.addAttribute("random", random);
		model.addAttribute("farming", farming);
		model.addAttribute("crop", crop);
		
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) session.getAttribute("user");
		String str = (String) map.get("user_id");
		
		return "admin/farming/regist";
	}

	// regist 재배방법 파일 업로드, 텍스트 저장
/*
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String process(AdminFarmingVo farming, AdminUploadfilesVo ckupload, AdminUploadfilesVo uploadfiles, 
			HttpSession session, @RequestParam("file") MultipartHttpServletRequest file, HttpServletRequest request,  HttpServletResponse response, Model model) throws Exception {
	
		List<MultipartFile> files = file.getFiles("file");
		
		
		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current_time = transFormat.format(from);
		farming.setFrm_updatetime(current_time);
		farming.setFrm_createtime(current_time);
		SimpleDateFormat trans = new SimpleDateFormat("yyyy-MM-dd");
		String path_time = trans.format(from);
		int result = service.adminFarmingInsert(farming);
		farming = service.adminFarmingOneRead(farming);
		ckupload = fileservice.CkfilesReIdxRead(ckupload);
		
		if (file.getSize() == 0 ) {
			System.out.println("파일이 없습니다.");
			try {
				ckupload = fileservice.adminCkfilesidxRead(ckupload);
				if(ckupload.getFrm_idx()==0) {
					ckupload.setFrm_idx(farming.getFrm_idx());
					fileservice.adminCkfilesUpdate(ckupload);
				}
			}
			catch (NullPointerException ne) {
				System.out.println("ckediotr 사진이 업로드되지 않았습니다.");
				// TODO: handle exception
			}
			return "redirect:/admin/farming/list";
		} 
		
			try {
				ckupload = fileservice.adminCkfilesidxRead(ckupload);
				if(ckupload.getFrm_idx()==0) {
				ckupload.setFrm_idx(farming.getFrm_idx());
				fileservice.adminCkfilesUpdate(ckupload);
				}
			} catch (NullPointerException ne) {
				System.out.println("ckediotr 사진이 업로드되지 않았습니다.");
				// TODO: handle exception
			}
			uploadfiles.setFrm_idx(farming.getFrm_idx());
			uploadfiles.setFrm_f_filename_org(file.getOriginalFilename());
			// savedName = uploadFile(savedName, file.getBytes());
			String type = file.getOriginalFilename();
			int partidx = type.indexOf(".");
			String fseperate = type.substring(partidx + 1); // 확장자 짤라오기
			String savename = uploadfiles.uploadFile(uploadfiles.getFrm_f_filename_org(), file.getBytes(), fseperate);
			//local path =  "c:/workspace/iof_ch/src/main/webapp/resources/dist/img/upload/" + savename;
			
			String path = request.getSession().getServletContext().getRealPath("/webapp/resources/upload/");
			String dbpath = "webapp/resources/upload/"+path_time+"/"+savename;
			String size = Long.toString(file.getSize());
			
			File f = new File(path + path_time + "/" + savename); // 파일 생성
			
			if (AdminUploadfilesVo.badFileExtIsReturnBoolean(f)) {
				System.out.println("파일 확장자가 적절하지 않습니다.");
				return "redirect:/admin/farming/regist";
			}
				if (!f.exists())
					f.mkdirs();
				
				uploadfiles.setFrm_f_filepath(dbpath);
				uploadfiles.setFrm_f_filename(savename);
				uploadfiles.setFrm_f_filename_org(file.getOriginalFilename());
				uploadfiles.setFrm_f_size(size);
				uploadfiles.setFrm_f_type(file.getContentType());
				file.transferTo(f);
				// Create the file on server
				int result2 = fileservice.adminUploadfilesInsert(uploadfiles);
				// fileservice.adminUploadfilesRead(uploadfiles); //?
		return "redirect:/admin/farming/list";
	}
	*/
	
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public ModelAndView regist_post(HttpServletRequest request, HttpServletResponse response, AdminFarmingVo farming, HttpSession session, 
		AdminUploadfilesVo uploadfiles, @RequestParam(value = "crop_idx") String crop_idx, 
		MultipartHttpServletRequest multipartRequest, @RequestParam(value = "random") Integer random) throws IOException, Exception{
		ModelAndView mv = new ModelAndView("redirect:/admin/farming/list");
		
		System.out.println("regist_post");
		System.out.println("random = " + random);
		int tmp_random = random;
		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current_time = transFormat.format(from);
		farming.setFrm_updatetime(current_time);
		farming.setFrm_createtime(current_time);
		farming.setCrop_idx(crop_idx);

		AdminUploadfilesVo ckeditor = new AdminUploadfilesVo();
		
		AdminCropVO vo = new AdminCropVO();
		int tmp = Integer.parseInt(farming.getCrop_idx());
		vo.setCrop_idx(tmp);
		vo = cropservice.adminCropidxRead(vo);
		farming.setCrop_name(vo.getCrop_name());
		System.out.println("crop_idx =" + crop_idx);
		int result = service.adminFarmingInsert(farming);
		

		
		AdminUploadfilesVo ckupload = new AdminUploadfilesVo();
		//ckupload = fileservice.CkfilesReIdxRead(ckupload);
		
		farming = service.adminFarmingOneRead(farming);
		
		ckeditor.setRandom(random);
		try {
			ckeditor = fileservice.CkUploadRandom(ckeditor);
			int ck_random = ckeditor.getRandom();
			if(tmp_random == ck_random) {
				System.out.println("if 밑");
			ckeditor.setFrm_idx(farming.getFrm_idx());
			fileservice.adminCkfilesUpdate(ckeditor);
		}
		} catch (NullPointerException ne) {
			System.out.println("ckeditor의 random값을 찾을 수 없다.");
			// TODO: handle exception
		}
		
		
		
		//넘어온 파일을 리스트로 저장
		List<MultipartFile> files = multipartRequest.getFiles("file");
		
		if(files.get(0).getSize()==0) {
			return mv;
		}
		
		String readlFolder = request.getSession().getServletContext().getRealPath("/webapp/resources/upload/")+farming.getFrm_idx();
		File dir = new File(readlFolder);
		if(AdminUploadfilesVo.badFileExtIsReturnBoolean(dir)) {
		System.out.println("파일 확장자가 적절하지 않습니다.");
			return mv;}
		if(!dir.isDirectory()) {
			dir.mkdirs();
		}
		

		
		if (files.size() == 1 && files.get(0).getOriginalFilename().equals("")) {
           
        } else {
        	for(int i = 0; i<files.size(); i++) {
        		
        		String originalfileName = files.get(i).getOriginalFilename(); //files[i]번째의 진짜 이름
        		String type = files.get(i).getOriginalFilename(); //files[i]번째의 타입
    			int partidx = type.indexOf(".");
    			String fseperate = type.substring(partidx + 1); // 확장자 짤라오기 //files[i]번째의 DB용 타입
        		String savefilename = uploadfiles.uploadFile(originalfileName, files.get(i).getBytes(), fseperate); //files[i]번째의 저장 이름
        		String fileSize = Long.toString(files.get(i).getSize()); //files[i]번째 사이즈
        		String dbpath = "webapp/resources/upload/"+farming.getFrm_idx()+"/"+savefilename;
        		// savedName = uploadFile(savedName, file.getBytes());
        		
        		String savePath = readlFolder + "/"+savefilename; //저장될 파일 경로
        		files.get(i).transferTo(new File(savePath));
 
        		
        		uploadfiles.setFrm_f_filename_org(originalfileName);
        		uploadfiles.setFrm_f_type(type);
        		uploadfiles.setFrm_f_filename(savefilename);
        		uploadfiles.setFrm_f_size(fileSize); //스트링 변환 필요
        		uploadfiles.setFrm_f_filepath(dbpath);
        		uploadfiles.setFrm_idx(farming.getFrm_idx());
        		uploadfiles.setFrm_f_no(i);
        		System.out.println(readlFolder);
        		
        		fileservice.adminUploadfilesInsert(uploadfiles);
        	}
        
        }
	return mv;
	}
}

