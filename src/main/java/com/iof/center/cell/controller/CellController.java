package com.iof.center.cell.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iof.center.admin.api.vo.AdminApiVO;
import com.iof.center.admin.cell.service.AdminCellService;
import com.iof.center.admin.cell.vo.AdminCellVO;
import com.iof.center.admin.region.service.AdminRegionService;
import com.iof.center.admin.region.vo.AdminRegionVO;
import com.iof.center.util.PageMaker;

@Controller
@RequestMapping("/cell")
public class CellController {
	
	@Resource(name = "adminRegionService")
	private AdminRegionService region_service;
	
	@Resource(name = "adminCellService")
	private AdminCellService cell_service;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String cell() {
		return "/home";
	}
		
	@RequestMapping(value = "/list")
	public ModelAndView admin_cell_list(@RequestParam(defaultValue="") String keyword, PageMaker pagemaker, Model model, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		System.out.println("keyword : " + keyword);
		int count = 0;
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) session.getAttribute("user");
		String user_id = (String) map.get("user_id");
		count = cell_service.count_user_cell(keyword, user_id);
		pagemaker.setPage(pagemaker.getPage());
		pagemaker.setCount(count);
		List<AdminCellVO> list = cell_service.getRead_user_cell(pagemaker.getPage(), keyword, user_id);
		
		mav.addObject("CellList", list);
		mav.addObject("pageMaker",pagemaker);
		mav.addObject("keyword",keyword);
		mav.setViewName("cell/list");
		return mav;
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public String cell_regist(Model model) {
		List<AdminRegionVO> region = region_service.adminRegionList();
		model.addAttribute("region", region);
		
		return "cell/regist";
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String cell_regist_post(Model model, AdminCellVO vo, @RequestParam String unit_code) {
		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current_time = transFormat.format(from);
		vo.setCell_updatetime(current_time);
		vo.setCell_createtime(current_time);
		int int_code = Integer.parseInt(vo.getCell_code());
		unit_code = unit_code + '-' + String.format("%04d", int_code); 
		vo.setCell_code(unit_code);
	
		cell_service.insert_cell(vo);
		return "redirect:/cell/list";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify_form_get(Model model, AdminCellVO vo) {
		List<AdminRegionVO> region = region_service.adminRegionList();
		AdminCellVO res = cell_service.get_cell(vo);
		model.addAttribute("region", region);
		model.addAttribute("cell",res);
		return "/cell/modify";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify_form_post(Model model, AdminCellVO vo, @RequestParam String unit_code){
		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current_time = transFormat.format(from);
		vo.setCell_updatetime(current_time);
		vo.setCell_createtime(current_time);
		if(vo.getCell_code() == null || vo.getCrop_idx() == 0 || vo.getUnit_idx() == 0 ) {
			System.out.println("update222");
			cell_service.update_cell2(vo);
		}
		else {
			int int_code = Integer.parseInt(vo.getCell_code());
			unit_code = unit_code + '-' + String.format("%04d", int_code); 
			vo.setCell_code(unit_code);
			System.out.println("update111");
			cell_service.update_cell(vo);
		}
	
		return "redirect:/cell/list";
	}
}
