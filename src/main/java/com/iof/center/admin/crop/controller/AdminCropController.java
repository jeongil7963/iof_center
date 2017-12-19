package com.iof.center.admin.crop.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iof.center.admin.crop.service.AdminCropService;
import com.iof.center.admin.crop.vo.AdminCropVO;
import com.iof.center.admin.region.service.AdminRegionService;
import com.iof.center.admin.region.vo.AdminRegionVO;
import com.iof.center.admin.unit.service.AdminUnitService;
import com.iof.center.admin.unit.vo.AdminUnitNameVO;
import com.iof.center.admin.unit.vo.AdminUnitVO;
import com.iof.center.util.PageMaker;

/**
* 관리자 셀 관리 
* @author 우성정
* @since 2017.10.16
*/
@Controller
@RequestMapping("/admin/crop")
public class AdminCropController {

	@Resource(name = "adminRegionService")
	private AdminRegionService region_service;

	@Resource(name = "AdminUnitService")
	private AdminUnitService unit_service;

	@Resource(name = "adminCropService")
	private AdminCropService crop_service;

	/**
	* 셀 목록
	* @param request
	* @return view
	* @exception 
	*/
	@RequestMapping("/list")
	public ModelAndView admin_user_list_post(@RequestParam(defaultValue = "") String keyword, PageMaker pagemaker, Model model) {
		ModelAndView mav = new ModelAndView();
		System.out.println("keyword : " + keyword);
		int count = 0;
		count = crop_service.count_crop(keyword);
		pagemaker.setPage(pagemaker.getPage());
		pagemaker.setCount(count);
		List<AdminCropVO> list = crop_service.getRead_crop(pagemaker.getPage(), keyword);
		List<AdminUnitNameVO> list2 = unit_service.get_unit_name();
		String str = new String();
		for(int i=0; i<list.size(); i++) {
			str = list.get(i).getUnit_idx();
			String[] unit_name = str.split(",");
			String name = "";
			for(int j=0; j<unit_name.length; j++) {
				for(int k=0; k<list2.size(); k++) {
					if( Integer.parseInt(unit_name[j]) == list2.get(k).getUnit_idx()) {
						name = name + " " + list2.get(k).getUnit_name();
					}	
				}				
				list.get(i).setUnit_name(name);
			}
		}
		mav.addObject("CropList", list);
		mav.addObject("pageMaker", pagemaker);
		mav.addObject("keyword", keyword);
		mav.setViewName("admin/crop/list");
		return mav;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delUser(@RequestParam Map<String, Object> map, Model model) {
		String delUser = map.get("delSeqNo").toString();
		System.out.println(delUser);
		String[] unit_idx = delUser.split(",");
		for(int i=0; i<unit_idx.length; i++) {
			crop_service.delete_crop(Integer.parseInt(unit_idx[i]));
		}
		return "redirect:/admin/crop/regist";
	}


	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public String registForm_get(Model model) {
		List<AdminRegionVO> region = region_service.adminRegionList();
		model.addAttribute("region", region);

		return "admin/crop/regist";
	}

	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String registForm_post(Model model, @RequestParam String crop_name, String crop_timing, String crop_soil, String user_id, String region_idx,
			String unit_idx) {
		AdminCropVO vo = new AdminCropVO();
		vo.setCrop_name(crop_name);
		vo.setCrop_soil(crop_soil);
		vo.setCrop_timing(crop_timing);
		vo.setRegion_idx(region_idx);
		vo.setUser_id(user_id);
		vo.setUnit_idx(unit_idx);
		crop_service.insert_crop(vo);
		return "redirect:/admin/crop/list";
	}

	@ResponseBody
	@RequestMapping(value = "/check_unit", method = RequestMethod.POST)
	public Map<String, Object> search_unit_post(Model model, @RequestParam Map<String, Object> map) {
		String delUser = map.get("delSeqNo").toString();
		System.out.println("delUser : " + delUser);
		if (delUser != "") {
			String[] userArr = delUser.split(",");
			List<AdminUnitVO> UnitList = new ArrayList<AdminUnitVO>();
			for (int i = 0; i < userArr.length; i++) {
				UnitList.addAll(unit_service.total_unit_code(Integer.parseInt(userArr[i])));
			}
			map.put("UnitList", UnitList);
		} else {
			map.put("UnitList", null);
		}
		return map;
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify_form_get(Model model, AdminCropVO vo) {
		List<AdminRegionVO> region = region_service.adminRegionList();
		AdminCropVO res = crop_service.get_crop(vo.getCrop_idx());
		model.addAttribute("region", region);
		model.addAttribute("crop",res);
		return "/admin/crop/modify";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify_form_POST(Model model, AdminCropVO vo) {
		crop_service.crop_modify(vo);
		return "redirect:/admin/crop/list";
	}
}
