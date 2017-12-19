package com.iof.center.admin.region.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iof.center.admin.crop.vo.AdminCropVO;
import com.iof.center.admin.region.service.AdminRegionService;
import com.iof.center.admin.region.vo.AdminRegionVO;
import com.iof.center.user.vo.UserVO;
import com.iof.center.util.PageMaker;

/**
* 관리자 지역정보 관리 
* @author 우성정
* @since 2017.10.13
*/
@Controller
@RequestMapping("/admin/region")
public class AdminRegionController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminRegionController.class);
	
	//의존성 주입
	@Resource(name="adminRegionService")
	private AdminRegionService service;
	
	/**
	* 지역정보 목록
	* @param request
	* @return view
	* @exception 
	*/
	@RequestMapping("/list")
	public ModelAndView admin_region_list_post(@RequestParam(defaultValue="") String keyword, PageMaker pagemaker, Model model) throws Exception {
		System.out.println("admin_region_list_post list");
		ModelAndView mav = new ModelAndView();
		System.out.println("keyword : " + pagemaker.getPage());
		int count = 0;
		count = service.count_region(keyword);
		pagemaker.setPage(pagemaker.getPage());
		pagemaker.setCount(count);
		
		List<AdminRegionVO> list = service.getRead_region(pagemaker.getPage(),keyword);
		
		mav.addObject("list", list);
		mav.addObject("pageMaker",pagemaker);
		mav.addObject("keyword",keyword);
		mav.setViewName("admin/region/list");
		return mav;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ModelAndView delRegion(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		String delRegion = map.get("delSeqNo").toString();
		System.out.println("delRegion : " + delRegion);
		String[] userArr = delRegion.split(",");
		for(int i=0; i<userArr.length; i++) {
			service.delRegion(userArr[i]);
		}
		mav.setViewName("redirect:/admin/user/regist");
		return mav;
	}
	
	/**
	* 지역정보 등록 폼
	* @param request
	* @return view
	* @exception 
	*/
	@RequestMapping(value="/regist", method=RequestMethod.GET)
	public String registerForm(Model model){
		return "admin/region/regist";
	}
	
	/**
	* 지역정보 등록
	* @param request
	* @return view
	* @exception SQLException
	*/
	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public String regist(AdminRegionVO region, Model model){
		
		/*
		 * 1. region 객체 유효성 체크
		 * 2. 지역 코드 발급 ( 가장 최근 지역 코드 가져오기 -> 코드부여 )
		 * 3. 지역 정보 등록
		 */
		
		//지역 정보 값 유효성 체크
		
		//가장최근 코드 가져오기
		String region_code = service.adminRegionLastRegionCode();
		System.out.println(region.toString());
		System.out.println(region_code);
		//지역 코드 부여
		int int_code = 0;
		
		if( region_code == null ||  region_code.length() == 0 ) {
			int_code = 1;
		}else {
			int_code = Integer.parseInt(region_code);
			int_code++;
		}
		

		System.out.println(int_code);
		
		region_code = String.format("%04d", int_code); 
		region.setRegion_code(region_code);
		
		//지역 정보 insert		
		int result = service.adminRegionInsert(region);
		
		
		return "redirect:/admin/region/list";
	}
	
	/**
	* 지역정보 수정 폼
	* @param request
	* @return view
	* @exception 
	*/
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modifyForm(AdminRegionVO region, Model model){
		/*
		 * 1. idx 값으로 지역 정보 가져오기
		 * 2. 뷰단으로 객체를 넘겨준다.
		 */
		
		//지역 정보를 읽어온다.
		region = service.adminRegionRead(region);
		//뷰단으로 객체를 넘겨준다.
		model.addAttribute("regionVO",region);
		
		return "admin/region/modify";
	}
	
	/**
	* 지역정보 수정
	* @param request
	* @return view
	* @exception SQLException
	*/
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(AdminRegionVO region, Model model){
		
		/*
		 * 1. idx 값 체크
		 * 2. 지역 정보 등록
		 */
		System.out.println("before");
		//지역 정보 값 유효성 체크
		if( "".equals(region.getRegion_idx()) || region.equals("") ){
			
			return "admin/region/list";
		
		}
		System.out.println("region");
		
		//가장최근 코드 가져오기
		int result = service.adminRegionModify(region);
		
		System.out.println("after");
		
		return "redirect:/admin/region/modify?region_idx="+region.getRegion_idx();
		
	}
	
}
