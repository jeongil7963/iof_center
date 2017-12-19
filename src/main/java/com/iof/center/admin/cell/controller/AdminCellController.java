package com.iof.center.admin.cell.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iof.center.admin.api.service.AdminApiService;
import com.iof.center.admin.api.vo.AdminApiVO;
import com.iof.center.admin.cell.service.AdminCellService;
import com.iof.center.admin.cell.vo.AdminCellVO;
import com.iof.center.admin.crop.service.AdminCropService;
import com.iof.center.admin.crop.vo.AdminCropVO;
import com.iof.center.admin.device.service.AdminDeviceService;
import com.iof.center.admin.region.service.AdminRegionService;
import com.iof.center.admin.region.vo.AdminRegionVO;
import com.iof.center.admin.unit.service.AdminUnitService;
import com.iof.center.util.PageMaker;

/**
 * 관리자 셀 관리
 * 
 * @author 우성정
 * @since 2017.10.16
 */
@Controller
@RequestMapping("/admin/cell")
public class AdminCellController {

	@Resource(name = "adminRegionService")
	private AdminRegionService region_service;

	@Resource(name = "AdminUnitService")
	private AdminUnitService unit_service;

	@Resource(name = "adminCropService")
	private AdminCropService crop_service;

	@Resource(name = "adminCellService")
	private AdminCellService cell_service;

	@Resource(name = "adminApiService")
	private AdminApiService api_service;
	
	/**
	 * 셀 목록 @param request @return view @exception
	 */
	@RequestMapping(value = "/list")
	public ModelAndView admin_cell_list(@RequestParam(defaultValue = "") String keyword, PageMaker pagemaker,
			Model model) {
		ModelAndView mav = new ModelAndView();

		System.out.println("keyword : " + keyword);
		int count = 0;
		count = cell_service.count_cell(keyword);
		pagemaker.setPage(pagemaker.getPage());
		pagemaker.setCount(count);
		List<AdminCellVO> list = cell_service.getRead_cell(pagemaker.getPage(), keyword);

		mav.addObject("CellList", list);
		mav.addObject("pageMaker", pagemaker);
		mav.addObject("keyword", keyword);
		mav.setViewName("admin/cell/list");
		return mav;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delUser(@RequestParam Map<String, Object> map, Model model) {
		String delUser = map.get("delSeqNo").toString();
		System.out.println(delUser);
		String[] unit_idx = delUser.split(",");
		for (int i = 0; i < unit_idx.length; i++) {
			cell_service.delete_cell(Integer.parseInt(unit_idx[i]));
		}
		return "redirect:/admin/cell/regist";
	}

	/**
	 * 셀 등록 폼 @param request @return view @exception
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public String registerForm(Model model) {

		List<AdminRegionVO> region = region_service.adminRegionList();
		model.addAttribute("region", region);

		return "admin/cell/regist";
	}

	@ResponseBody
	@RequestMapping(value = "/check_crop", method = RequestMethod.POST)
	public Map<String, Object> search_crop_post(Model model, @RequestParam Map<String, Object> map) {
		String delUser = map.get("delSeqNo").toString();
		System.out.println("delUser : " + delUser);
		if (delUser != "") {
			String[] userArr = delUser.split(",");
			List<AdminCropVO> CropList = new ArrayList<AdminCropVO>();
			for (int i = 0; i < userArr.length; i++) {
				CropList.addAll(crop_service.checkbox_crop(Integer.parseInt(userArr[i])));
			}
			map.put("CropList", CropList);
		} else {
			map.put("CropList", null);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/check_cell", method = RequestMethod.POST)
	public Map<String, Object> search_cell_post(Model model, @RequestParam Map<String, Object> map) {
		String delUser = map.get("delSeqNo").toString();
		System.out.println("delUser : " + delUser);
		if (delUser != "") {
			String[] userArr = delUser.split(",");
			List<AdminCellVO> CellList = new ArrayList<AdminCellVO>();
			for (int i = 0; i < userArr.length; i++) {
				CellList = cell_service.cell_search(Integer.parseInt(userArr[i]));
			}
			map.put("CellList", CellList);
		} else {
			map.put("CellList", null);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/select_cell", method = RequestMethod.POST)
	public Map<String, Object> select_cell_post(Model model, @RequestParam Map<String, Object> map) {
		//AdminRaspberryVo raspberry = new AdminRaspberryVo(); 
		String delUser = map.get("delSeqNo").toString();
		System.out.println("delUser : " + delUser);
		if (delUser != "") {
			String[] userArr = delUser.split(",");
			List<AdminCellVO> CellTempList = new ArrayList<AdminCellVO>();
			for (int i = 0; i < userArr.length; i++) {
				CellTempList = cell_service.cell_select_List(Integer.parseInt(userArr[i]));
			}
			map.put("CellTempList", CellTempList);
		} else {
			map.put("CellTempList", null);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ard_select_cell", method = RequestMethod.POST)
	public Map<String, Object> ard_select_cell_post(Model model, @RequestParam Map<String, Object> map) {
		//AdminRaspberryVo raspberry = new AdminRaspberryVo(); 
		String delUser = map.get("delSeqNo").toString();
		System.out.println("delUser : " + delUser);
		if (delUser != "") {
			String[] userArr = delUser.split(",");
			List<AdminCellVO> CellTempList = new ArrayList<AdminCellVO>();
			for (int i = 0; i < userArr.length; i++) {
				CellTempList = cell_service.ard_cell_select_List(Integer.parseInt(userArr[i]));
			}
			map.put("CellTempList", CellTempList);
		} else {
			map.put("CellTempList", null);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/detail_cell", method = RequestMethod.POST)
	public Map<String, Object> detail_cell_post(Model model, @RequestParam Map<String, Object> map) {
		//AdminRaspberryVo raspberry = new AdminRaspberryVo(); 
		String delUser = map.get("delSeqNo").toString();
		System.out.println("delUser : " + delUser);
		if (delUser != "") {
			String[] userArr = delUser.split(",");
			List<AdminCellVO> CellTempList = new ArrayList<AdminCellVO>();
			for (int i = 0; i < userArr.length; i++) {
				CellTempList = cell_service.cell_detail_List(Integer.parseInt(userArr[i]));
			}
			map.put("CellTempList", CellTempList);
		} else {
			map.put("CellTempList", null);
		}
		return map;
	}
		
	/**
	 * 셀 등록
	 * 
	 * @param request
	 * @return view
	 * @exception SQLException
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String regist(Model model, AdminCellVO vo, @RequestParam String unit_code) {
		AdminApiVO apiVo = new AdminApiVO();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		apiVo.setApi_key(uuid);
		api_service.insert_api(apiVo);

		// api_idx 값 반환
		AdminApiVO apiVo2 = api_service.getApi(apiVo);

		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current_time = transFormat.format(from);
		vo.setCell_updatetime(current_time);
		vo.setCell_createtime(current_time);
		vo.setApi_idx(apiVo2.getApi_idx());
		int int_code = Integer.parseInt(vo.getCell_code());
		unit_code = unit_code + '-' + String.format("%04d", int_code);
		vo.setCell_code(unit_code);
		cell_service.insert_cell(vo);

		return "redirect:/admin/cell/list";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify_form_get(Model model, AdminCellVO vo) {
		List<AdminRegionVO> region = region_service.adminRegionList();
		AdminCellVO res = cell_service.get_cell(vo);
		model.addAttribute("region", region);
		model.addAttribute("cell", res);
		return "/admin/cell/modify";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify_form_post(Model model, AdminCellVO vo, @RequestParam String unit_code) {
		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current_time = transFormat.format(from);
		vo.setCell_updatetime(current_time);
		vo.setCell_createtime(current_time);
		if (vo.getCell_code() == null || vo.getCrop_idx() == 0 || vo.getUnit_idx() == 0) {
			System.out.println("update222");
			cell_service.update_cell2(vo);
		} else {
			int int_code = Integer.parseInt(vo.getCell_code());
			unit_code = unit_code + '-' + String.format("%04d", int_code);
			vo.setCell_code(unit_code);
			System.out.println("update111");
			cell_service.update_cell(vo);
		}

		return "redirect:/admin/cell/list";
	}
}
