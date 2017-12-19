package com.iof.center.admin.unit.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.iof.center.admin.region.controller.AdminRegionController;
import com.iof.center.admin.region.service.AdminRegionService;
import com.iof.center.admin.region.vo.AdminRegionVO;
import com.iof.center.admin.unit.service.AdminUnitService;
import com.iof.center.admin.unit.vo.AdminUnitVO;
import com.iof.center.util.PageMaker;

/**
* 관리자 회원 관리 
* @author 우성정
* @since 2017.10.16
*/
@Controller
@RequestMapping("/admin/unit")
public class AdminUnitController {

	private static final Logger logger = LoggerFactory.getLogger(AdminRegionController.class);

	@Resource(name = "adminRegionService")
	private AdminRegionService region_service;

	@Resource(name = "AdminUnitService")
	private AdminUnitService unit_service;

	/**
	* 유닛 목록
	* @param request
	* @return view
	* @exception 
	*/
	@RequestMapping(value = "/list")
	public ModelAndView admin_user_list_post(@RequestParam(defaultValue="") String keyword, PageMaker pagemaker, Model model) {
		ModelAndView mav = new ModelAndView();
		
		System.out.println("keyword : " + keyword);
		int count = 0;
		count = unit_service.count_unit(keyword);
		pagemaker.setPage(pagemaker.getPage());
		pagemaker.setCount(count);
		List<AdminUnitVO> list = unit_service.getRead_unit(pagemaker.getPage(), keyword);
		
		mav.addObject("UnitList", list);
		mav.addObject("pageMaker",pagemaker);
		mav.addObject("keyword",keyword);
		mav.setViewName("admin/unit/list");
		return mav;
	}
	
	/**
	* 유닛 선택 삭제
	* @param request
	* @return view
	* @exception 
	*/
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delUser(@RequestParam Map<String, Object> map, Model model) {
		String delUser = map.get("delSeqNo").toString();
		System.out.println(delUser);
		String[] unit_idx = delUser.split(",");
		for(int i=0; i<unit_idx.length; i++) {
			unit_service.delete_unit(Integer.parseInt(unit_idx[i]));
		}
		return "redirect:/admin/unit/regist";
	}

	/**
	* 유닛 등록 폼
	* @param request
	* @return view
	* @exception 
	*/
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public String registerForm(Model model) {
		List<AdminRegionVO> region = region_service.adminRegionList();
		model.addAttribute("region", region);

		return "admin/unit/regist";
	}

	/**
	* 유닛 등록
	* @param request
	* @return view
	* @exception SQLException
	*/
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String registerForm_post(Model model, AdminUnitVO vo) {
		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current_time = transFormat.format(from);
		vo.setUnit_updatetime(current_time);
		vo.setUnit_createtime(current_time);
		vo.setUnit_cell_number(vo.getUnit_cell_row() * vo.getUnit_cell_col());

		//가장최근 코드 가져오기
		String unit_code_p = unit_service.adminRegionLastUnitCode(vo.getUnit_code());

		System.out.println(unit_code_p);

		if (unit_code_p != null) {
			String[] unit_code = unit_code_p.split("-");
			System.out.println(unit_code[0]);
			//유닛 코드 부여
			int int_code = 0;
			if (unit_code[1] == null || unit_code[1].length() == 0) {
				int_code = 1;
			} else {
				int_code = Integer.parseInt(unit_code[1]);
				int_code++;
			}
			unit_code[1] = String.format("%04d", int_code);
			unit_code[1] = unit_code[0] + "-" + unit_code[1];
			vo.setUnit_code(unit_code[1]);
		} else {
			unit_code_p = vo.getUnit_code() + "-" + "0001";
			vo.setUnit_code(unit_code_p);
		}

		logger.info("vo is {} ", vo);
		unit_service.Admin_unit_insert(vo);
		return "redirect:/admin/unit/list";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify_form_get(Model model, AdminUnitVO vo) {
		List<AdminRegionVO> region = region_service.adminRegionList();
		AdminUnitVO res = unit_service.get_unit(vo.getUnit_idx());
		model.addAttribute("region", region);
		model.addAttribute("unit",res);
		return "/admin/unit/modify";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify_form_POST(Model model, AdminUnitVO vo, @RequestParam String unit_code_compare ) {
		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current_time = transFormat.format(from);
		vo.setUnit_updatetime(current_time);
		vo.setUnit_cell_number(vo.getUnit_cell_row() * vo.getUnit_cell_col());
		
		String unit_code_p = vo.getUnit_code();
		String[] unit_code = unit_code_p.split("-");
		System.out.println("unit_code_p : " + unit_code_p);
		System.out.println("unit_code : " + unit_code[0]);
		System.out.println("unit_code_compare : " + unit_code_compare);
		
		if(!unit_code[0].equals(unit_code_compare)) {
			unit_code_p = unit_service.adminRegionLastUnitCode(unit_code_compare);
			if (unit_code_p != null) {
				unit_code = unit_code_p.split("-");
				System.out.println(unit_code[0]);
				//유닛 코드 부여
				int int_code = 0;
				if (unit_code[1] == null || unit_code[1].length() == 0) {
					int_code = 1;
				} else {
					int_code = Integer.parseInt(unit_code[1]);
					int_code++;
				}
				unit_code[1] = String.format("%04d", int_code);
				unit_code[1] = unit_code[0] + "-" + unit_code[1];
				vo.setUnit_code(unit_code[1]);
			} else {
				unit_code_p = unit_code_compare + "-" + "0001";
				vo.setUnit_code(unit_code_p);
			}
		}
		
		logger.info("vo is {} ", vo);
		unit_service.unit_modify(vo);
		return "redirect:/admin/unit/list";
	}
}
