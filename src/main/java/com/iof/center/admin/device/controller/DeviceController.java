package com.iof.center.admin.device.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iof.center.admin.api.service.AdminApiService;
import com.iof.center.admin.api.vo.AdminApiVO;
import com.iof.center.admin.arduino.vo.AdminArduinoVo;
import com.iof.center.admin.cell.service.AdminCellService;
import com.iof.center.admin.cell.vo.AdminCellVO;
import com.iof.center.admin.device.service.AdminDeviceService;
import com.iof.center.admin.raspberry.vo.AdminRaspberryVo;
import com.iof.center.admin.region.service.AdminRegionService;
import com.iof.center.admin.region.vo.AdminRegionVO;
import com.iof.center.admin.unit.service.AdminUnitService;
import com.iof.center.admin.unit.vo.AdminUnitVO;
import com.iof.center.admin.weather.vo.AdminWeatherVo;
import com.iof.center.util.PageMaker;

@Controller
@RequestMapping("/admin/device")
public class DeviceController {

	@Autowired
	private AdminDeviceService device_service;

	@Autowired
	private AdminCellService cell_service;
	
	@Autowired
	private AdminUnitService unit_service;

	@Autowired
	private AdminApiService api_service;
	
	@Autowired
	private AdminRegionService region_service;
	
	/*라즈베리 Controller*/
	@RequestMapping(value = "/raspberry/list")
	public ModelAndView admin_raspberry_list_post(@RequestParam(defaultValue = "") String keyword, PageMaker pagemaker,
			Model model) {
		ModelAndView mav = new ModelAndView();

		System.out.println("keyword : " + keyword);
		int count = 0;
		count = device_service.count_raspberry(keyword);
		pagemaker.setPage(pagemaker.getPage());
		pagemaker.setCount(count);
		List<AdminRaspberryVo> raspberry_list = device_service.getRead_raspberry(pagemaker.getPage(), keyword);

		mav.addObject("raspberryList", raspberry_list);
		mav.addObject("pageMaker", pagemaker);
		mav.addObject("keyword", keyword);
		mav.setViewName("admin/device/raspberry/list");
		return mav;
	}
	
	@RequestMapping(value = "/raspberry/regist", method = RequestMethod.GET)
	public String registerForm(Model model) {
		System.out.println("raspberryController_get");
		List<AdminCellVO> cell_list = cell_service.adminCellList();
		List<AdminRegionVO> region = region_service.adminRegionList();
		
		model.addAttribute("region", region);
		model.addAttribute("cell_list", cell_list);

		return "admin/device/raspberry/regist2";
	}
	
	@RequestMapping(value = "/raspberry/regist", method = RequestMethod.POST)
	public String registerForm_post(Model model, AdminRaspberryVo raspberry, HttpSession session, HttpServletResponse response,
			@RequestParam(defaultValue = "") int ras_region_idx, @RequestParam(defaultValue = "") int ras_unit_idx,
			@RequestParam(defaultValue = "") boolean arduino_regist_bool) throws Exception {
		System.out.println("ras_unit_idx = " + ras_unit_idx);
		System.out.println("ras_region_idx = " + ras_region_idx);
		System.out.println("arduino_regist_bool = " + arduino_regist_bool);
		
		AdminCellVO cell_vo = new AdminCellVO();
		
		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current_time = transFormat.format(from);

		//List<AdminRegionVO> region = region_service.adminRegionList();
		//model.addAttribute("region", region);
		
		AdminApiVO apiVo = new AdminApiVO();
		AdminRaspberryVo ras = new AdminRaspberryVo();
		
		ras = device_service.adminRasIdxMax();
		int ras_code = ras.getRas_code()+1;
		
		String uuid = UUID.randomUUID().toString().replace("-", "");
		apiVo.setApi_key(uuid);
		api_service.insert_api(apiVo);
		apiVo = api_service.adminApiMaxRead(apiVo);
		raspberry.setApi_key(apiVo.getApi_key());
		raspberry.setRas_createtime(current_time);
		raspberry.setRas_updatetime(current_time);
		raspberry.setApi_idx(apiVo.getApi_idx());
		raspberry.setRegion_idx(ras_region_idx);
		raspberry.setUnit_idx(ras_unit_idx);
		raspberry.setRas_code(ras_code);
		
		int cell_idx = raspberry.getCell_idx();
		cell_vo.setCell_idx(cell_idx);
		cell_vo = cell_service.get_cell(cell_vo);
		cell_vo.setRaspberry_check(1);
		cell_service.cell_ras_update(cell_vo);
		device_service.adminraspberryInsert(raspberry);
		
		if(arduino_regist_bool == false) {
			model.addAttribute("msg", "등록되었습니다."); 
			model.addAttribute("url", "<c:url value='/admin/device/raspberry/list'/>");
			model.addAttribute("redirect", "/admin/device/raspberry/list");
			
			
			return "/admin/device/Message";
		}
		
		else {
			cell_idx = raspberry.getCell_idx();
			
			AdminArduinoVo arduino = new AdminArduinoVo();
			
			uuid = UUID.randomUUID().toString().replace("-", "");
			apiVo.setApi_key(uuid);
			api_service.insert_api(apiVo);
			apiVo = api_service.adminApiMaxRead(apiVo);
			
			arduino.setApi_key(apiVo.getApi_key());
			arduino.setArd_createtime(current_time);
			arduino.setApi_idx(apiVo.getApi_idx());
			arduino.setCell_idx(cell_idx);
			arduino.setUser_id(raspberry.getUser_id());
			arduino.setArd_name(raspberry.getRas_name());
			arduino.setArd_sensor1_title("토양수분센서");
			arduino.setArd_meno("자동 등록");
			arduino.setUnit_idx(raspberry.getUnit_idx());
			arduino.setRegion_idx(raspberry.getRegion_idx());
			device_service.adminArduinoInsert(arduino);
			
			model.addAttribute("msg", "라즈베리파이, 아두이노가 등록되었습니다.");
			model.addAttribute("url", "<c:url value='/admin/device/raspberry/list'/>");
			model.addAttribute("redirect", "/admin/device/raspberry/list");
			
			return "/admin/device/Message";
		}
		
	}
	
	@RequestMapping(value = "/raspberry/read", method = RequestMethod.GET)
	public ModelAndView read_get(Model model, AdminRaspberryVo raspberry) {
		
		ModelAndView mv = new ModelAndView("admin/device/raspberry/read");
		raspberry = device_service.adminRasIdxRead(raspberry);
		int unit_idx = raspberry.getUnit_idx();
		int region_idx = raspberry.getRegion_idx();
		int cell_idx = raspberry.getCell_idx();
		
		AdminUnitVO unit_vo = new AdminUnitVO();
		AdminRegionVO region_vo = new AdminRegionVO();
		AdminCellVO cell_vo = new AdminCellVO();
		
		cell_vo.setCell_idx(cell_idx);
		region_vo.setRegion_idx(region_idx);
		
		unit_vo = unit_service.get_unit(unit_idx);
		region_vo = region_service.adminRegionRead(region_vo);
		cell_vo=cell_service.get_cell(cell_vo);
		
		mv.addObject("cell", cell_vo);
		mv.addObject("unit", unit_vo);
		mv.addObject("region", region_vo);
		mv.addObject("ras", raspberry);
		
		return mv;
	}
	
	@RequestMapping(value="/raspberry/delete", method=RequestMethod.GET)
	public String delRaspberry(@RequestParam Map<String, Object> map, Model model, @RequestParam(defaultValue="") int ras_idx[]) {
		
		AdminRaspberryVo raspberry = new AdminRaspberryVo();
		AdminApiVO vo = new AdminApiVO();
		AdminCellVO cell_vo = new AdminCellVO();
		for(int i = 0; i<ras_idx.length; i++) {
			raspberry.setRas_idx(ras_idx[i]);
			raspberry = device_service.adminRasIdxRead(raspberry);
			cell_vo.setCell_idx(raspberry.getCell_idx());
			cell_vo.setRaspberry_check(0);
			vo.setApi_idx(raspberry.getApi_idx());
			api_service.adminApiDelete(vo);
			device_service.adminraspberryDelete(raspberry);
			
			try {
				cell_service.cell_ras_update(cell_vo);
			} catch (NullPointerException ne) {
				System.out.println("cell 테이블이 변경되어서 에러남");
				// TODO: handle exception
			}
		}
		model.addAttribute("msg", "삭제되었습니다."); 
		model.addAttribute("url", "<c:url value='/admin/device/raspberry/list'/>");
		model.addAttribute("redirect", "/admin/device/raspberry/list");
		
		return "/admin/device/Message";
	}
	
	@RequestMapping(value = "/raspberry/detail", method = RequestMethod.GET)
	public ModelAndView detail_get(Model model, AdminRaspberryVo raspberry) throws Exception{
		
		ModelAndView mv = new ModelAndView("admin/device/raspberry/detail");
		List<AdminCellVO> cell_list = cell_service.adminCellList();
		List<AdminRegionVO> region_list = region_service.adminRegionList();
		
		raspberry = device_service.adminRasIdxRead(raspberry);
		int unit_idx = raspberry.getUnit_idx();
		int region_idx = raspberry.getRegion_idx();
		int cell_idx = raspberry.getCell_idx();
		
		AdminUnitVO unit_vo = new AdminUnitVO();
		AdminRegionVO region_vo = new AdminRegionVO();
		AdminCellVO cell_vo = new AdminCellVO();
		
		cell_vo.setCell_idx(cell_idx);
		region_vo.setRegion_idx(region_idx);
		
		unit_vo = unit_service.get_unit(unit_idx);
		region_vo = region_service.adminRegionRead(region_vo);
		cell_vo=cell_service.get_cell(cell_vo);
		
		model.addAttribute("region_list", region_list);
		model.addAttribute("cell_list", cell_list);
		mv.addObject("cell", cell_vo);
		mv.addObject("unit", unit_vo);
		mv.addObject("region", region_vo);
		mv.addObject("ras", raspberry);
		
		return mv;
	}
	
	@RequestMapping(value = "/raspberry/detail", method = RequestMethod.POST)
	public String detailForm_post(Model model, AdminRaspberryVo raspberry, HttpSession session, HttpServletResponse response,
			@RequestParam(defaultValue = "") int ras_region_idx, @RequestParam(defaultValue = "") int ras_unit_idx, @RequestParam(defaultValue = "") int org_ras_cell_idx) throws Exception {
		
		System.out.println("detailForm_post / controller");
		System.out.println("ras_unit_idx = " + ras_unit_idx);
		System.out.println("ras_region_idx = " + ras_region_idx);
		System.out.println("org_ras_cell_idx =" + org_ras_cell_idx);
		System.out.println("ras_idx =" + raspberry.getRas_idx());
		
		
		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current_time = transFormat.format(from);
		raspberry.setRas_updatetime(current_time);
		System.out.println(current_time);
		device_service.adminraspberryUpdate(raspberry);
		
		AdminCellVO cell_org_vo = new AdminCellVO();
		cell_org_vo.setCell_idx(org_ras_cell_idx);  //기존 셀 정보
		cell_org_vo = cell_service.get_cell(cell_org_vo);
		cell_org_vo.setRaspberry_check(0);
		cell_service.cell_ras_update(cell_org_vo);
		
		AdminCellVO cell_vo = new AdminCellVO();
		cell_vo.setCell_idx(raspberry.getCell_idx()); //새로운 셀 정보
		cell_vo = cell_service.get_cell(cell_vo);
		cell_vo.setRaspberry_check(1);
		cell_service.cell_ras_update(cell_vo);
		
		model.addAttribute("msg", "수정되었습니다."); 
		model.addAttribute("url", "<c:url value='/admin/device/raspberry/list'/>");
		model.addAttribute("redirect", "/admin/device/raspberry/list");
		
		
		return "/admin/device/Message";
	}
	
	/*아두이노 Controller*/
	@RequestMapping(value = "/arduino/list")
	public ModelAndView admin_ardu_list_post(@RequestParam(defaultValue = "") String keyword, PageMaker pagemaker,
			Model model) {
		ModelAndView mav = new ModelAndView();

		System.out.println("keyword : " + keyword);
		int count = 0;
		count = device_service.count_arduino(keyword);
		pagemaker.setPage(pagemaker.getPage());
		pagemaker.setCount(count);
		List<AdminArduinoVo> arduino_list = device_service.getRead_arduino(pagemaker.getPage(), keyword);
		System.out.println("arduino_list.get(0).getCell_idx()" + arduino_list.get(0).getCell_idx());
		AdminCellVO cell_vo = new AdminCellVO();
		for(int i = 0; i<arduino_list.size(); i++) {
			cell_vo.setCell_idx(arduino_list.get(i).getCell_idx());
			cell_vo = cell_service.get_cell(cell_vo);
			arduino_list.get(i).setArd_cell_name(cell_vo.getCell_name());
		}
		mav.addObject("arduinoList", arduino_list);
		mav.addObject("pageMaker", pagemaker);
		mav.addObject("keyword", keyword);
		mav.setViewName("admin/device/arduino/list");
		return mav;
	}
	
	@RequestMapping(value = "/arduino/regist", method = RequestMethod.GET)
	public ModelAndView Arduino_registerForm(Model model, HttpSession session ) throws Exception{
		ModelAndView mv = new ModelAndView("admin/device/arduino/regist");
		
		List<AdminCellVO> cell_list = cell_service.adminCellList();
		List<AdminRegionVO> region_list = region_service.adminRegionList();
		
		model.addAttribute("region_list", region_list);
		model.addAttribute("cell_list", cell_list);

		return mv;
	}
	
	@RequestMapping(value = "/arduino/regist", method = RequestMethod.POST)
	public String Arduino_registerForm_post(Model model, AdminArduinoVo arduino, HttpSession session) {
		
		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current_time = transFormat.format(from);
		AdminCellVO cell_vo = new AdminCellVO();
		AdminApiVO apiVo = new AdminApiVO();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		apiVo.setApi_key(uuid);
		api_service.insert_api(apiVo);
		apiVo = api_service.adminApiMaxRead(apiVo);
		
		AdminArduinoVo ard = new AdminArduinoVo();
		
		ard = device_service.adminArdIdxMax();
		int ard_code = ard.getArd_code()+1;
		
		
		
		arduino.setApi_key(apiVo.getApi_key());
		arduino.setArd_createtime(current_time);
		arduino.setApi_idx(apiVo.getApi_idx());
		arduino.setArd_updatetime(current_time);
		arduino.setArd_code(ard_code);
		int cell_idx = arduino.getCell_idx();
		cell_vo.setCell_idx(cell_idx);
		cell_vo = cell_service.get_cell(cell_vo);
		cell_vo.setArduino_check(1);
		cell_service.cell_ard_update(cell_vo);
		device_service.adminArduinoInsert(arduino);

		model.addAttribute("msg", "등록되었습니다."); 
		model.addAttribute("url", "<c:url value='/admin/device/arduino/list'/>");
		model.addAttribute("redirect", "/admin/device/arduino/list");
		
		return "/admin/device/Message";
	}
	
	@RequestMapping(value="/arduino/delete", method=RequestMethod.GET)
	public String delArduino(@RequestParam Map<String, Object> map, Model model, @RequestParam(defaultValue="") int ard_idx[]) {
		
		AdminCellVO cell_vo = new AdminCellVO();
		AdminArduinoVo arduino = new AdminArduinoVo(); 
		AdminApiVO vo = new AdminApiVO();
		for(int i = 0; i<ard_idx.length; i++) {
			System.out.println("ras_idx["+i+"] =" + ard_idx[i]);
			arduino.setArd_idx(ard_idx[i]);
			arduino = device_service.adminArdIdxRead(arduino);
			cell_vo.setCell_idx(arduino.getCell_idx());
			cell_vo.setArduino_check(0);
			vo.setApi_idx(arduino.getApi_idx());
			api_service.adminApiDelete(vo);
			device_service.adminArduinoDelete(arduino);
		}
		
		try {
			cell_service.cell_ard_update(cell_vo);
		} catch (NullPointerException ne) {
			System.out.println("cell 테이블이 변경되어서 에러남");
			// TODO: handle exception
		}
		
		model.addAttribute("msg", "삭제되었습니다."); 
		model.addAttribute("url", "<c:url value='/admin/device/arduino/list'/>");
		model.addAttribute("redirect", "/admin/device/arduino/list");
		
		return "/admin/device/Message";
	}
	
	@RequestMapping(value = "/arduino/read", method = RequestMethod.GET)
	public ModelAndView ard_read_get(Model model, AdminArduinoVo ard) {
		
		ModelAndView mv = new ModelAndView("admin/device/arduino/read");
		ard = device_service.adminArdIdxRead(ard);
		int unit_idx = ard.getUnit_idx();
		int region_idx = ard.getRegion_idx();
		int cell_idx = ard.getCell_idx();
		
		AdminUnitVO unit_vo = new AdminUnitVO();
		AdminRegionVO region_vo = new AdminRegionVO();
		AdminCellVO cell_vo = new AdminCellVO();
		
		cell_vo.setCell_idx(cell_idx);
		region_vo.setRegion_idx(region_idx);
		
		unit_vo = unit_service.get_unit(unit_idx);
		region_vo = region_service.adminRegionRead(region_vo);
		cell_vo=cell_service.get_cell(cell_vo);
		
		mv.addObject("cell", cell_vo);
		mv.addObject("unit", unit_vo);
		mv.addObject("region", region_vo);
		mv.addObject("ard", ard);
		
		return mv;
	}
	
	
	
	
	
	@RequestMapping(value = "/arduino/detail", method = RequestMethod.GET)
	public ModelAndView arduino_detail_get(Model model, AdminArduinoVo ard) throws Exception{
		
		ModelAndView mv = new ModelAndView("admin/device/arduino/detail");
		List<AdminCellVO> cell_list = cell_service.adminCellList();
		List<AdminRegionVO> region_list = region_service.adminRegionList();
		
		ard = device_service.adminArdIdxRead(ard);
		int unit_idx = ard.getUnit_idx();
		int region_idx = ard.getRegion_idx();
		int cell_idx = ard.getCell_idx();
		int api_idx = ard.getApi_idx();
		
		System.out.println("ard_api_idx= " + ard.getApi_idx() );
		
		
		AdminUnitVO unit_vo = new AdminUnitVO();
		AdminRegionVO region_vo = new AdminRegionVO();
		AdminCellVO cell_vo = new AdminCellVO();
		AdminApiVO api_vo = new AdminApiVO();
		api_vo.setApi_idx(api_idx);
		api_vo = api_service.adminapiIdxRead(api_vo);
		
		ard.setApi_key(api_vo.getApi_key());
		cell_vo.setCell_idx(cell_idx);
		region_vo.setRegion_idx(region_idx);
		
		unit_vo = unit_service.get_unit(unit_idx);
		region_vo = region_service.adminRegionRead(region_vo);
		cell_vo=cell_service.get_cell(cell_vo);
		
		model.addAttribute("region_list", region_list);
		model.addAttribute("cell_list", cell_list);
		mv.addObject("cell", cell_vo);
		mv.addObject("unit", unit_vo);
		mv.addObject("region", region_vo);
		mv.addObject("ard", ard);
		
		return mv;
	}
	
	@RequestMapping(value = "/arduino/detail", method = RequestMethod.POST)
	public String arduino_detailForm_post(Model model, AdminArduinoVo ard, HttpSession session, HttpServletResponse response,
			@RequestParam(defaultValue = "") int ard_region_idx, @RequestParam(defaultValue = "") int ard_unit_idx, @RequestParam(defaultValue = "") int org_ard_cell_idx) throws Exception {
		
		System.out.println("detailForm_post / controller");
		System.out.println("ard_unit_idx = " + ard_unit_idx);
		System.out.println("ard_region_idx = " + ard_region_idx);
		System.out.println("org_ard_cell_idx =" + org_ard_cell_idx);
		System.out.println("ard_idx =" + ard.getArd_idx());
		
		
		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current_time = transFormat.format(from);
		ard.setArd_updatetime(current_time);
		device_service.adminarduinoUpdate(ard);
		
		AdminCellVO cell_org_vo = new AdminCellVO();
		cell_org_vo.setCell_idx(org_ard_cell_idx);  //기존 셀 정보
		cell_org_vo = cell_service.get_cell(cell_org_vo);
		cell_org_vo.setArduino_check(0);
		cell_service.cell_ard_update(cell_org_vo);
		
		AdminCellVO cell_vo = new AdminCellVO();
		cell_vo.setCell_idx(ard.getCell_idx()); //새로운 셀 정보
		cell_vo = cell_service.get_cell(cell_vo);
		cell_vo.setArduino_check(1);
		cell_service.cell_ard_update(cell_vo);
		
		model.addAttribute("msg", "수정되었습니다."); 
		model.addAttribute("url", "<c:url value='/admin/device/arduino/list'/>");
		model.addAttribute("redirect", "/admin/device/arduino/list");
		
		return "/admin/device/Message";
	}
	
	
	
	
	/*기상정보 Controller*/
	@RequestMapping(value = "/weather/list")
	public ModelAndView admin_weather_list(@RequestParam(defaultValue = "") String keyword, PageMaker pagemaker,
			Model model) {
		ModelAndView mav = new ModelAndView();

		System.out.println("keyword : " + keyword);
		int count = 0;
		count = device_service.count_weather(keyword);
		pagemaker.setPage(pagemaker.getPage());
		pagemaker.setCount(count);
		List<AdminWeatherVo> weather_list = device_service.getRead_weather(pagemaker.getPage(), keyword);

		mav.addObject("weatherList", weather_list);
		mav.addObject("pageMaker", pagemaker);
		mav.addObject("keyword", keyword);
		mav.setViewName("admin/device/weather/list");
		return mav;
	}
	
	
	@RequestMapping(value = "/weather/regist", method = RequestMethod.GET)
	public ModelAndView weather_registerForm(Model model, HttpSession session ) throws Exception{
		ModelAndView mv = new ModelAndView("admin/device/weather/regist");
		
		List<AdminCellVO> cell_list = cell_service.adminCellList();
		List<AdminRegionVO> region_list = region_service.adminRegionList();
		
		model.addAttribute("region_list", region_list);
		model.addAttribute("cell_list", cell_list);

		return mv;
	}
	
	@RequestMapping(value = "/weather/regist", method = RequestMethod.POST)
	public String weather_registerForm_post(Model model, AdminWeatherVo weather, HttpSession session) throws Exception {
		
		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current_time = transFormat.format(from);
		AdminUnitVO unit_vo = new AdminUnitVO();
		AdminApiVO apiVo = new AdminApiVO();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		apiVo.setApi_key(uuid);
		api_service.insert_api(apiVo);
		apiVo = api_service.adminApiMaxRead(apiVo);
		
		AdminWeatherVo tmpweath = new AdminWeatherVo();
		int weather_code;
		
		try {
			tmpweath = device_service.adminWeatherIdxMax();
			weather_code = 0;
			weather_code = tmpweath.getWeather_code()+1;
		} catch (Exception e) {
			tmpweath.setWeather_code(0);
			weather_code = tmpweath.getWeather_code()+1;
			// TODO: handle exception
		}
		
		weather.setWeather_createtime(current_time);
		weather.setApi_idx(apiVo.getApi_idx());
		weather.setWeather_updatetime(current_time);
		weather.setWeather_code(weather_code);
		int unit_idx = weather.getUnit_idx();
		unit_vo.setUnit_idx(unit_idx);
		unit_vo = unit_service.get_unit(unit_idx);
		unit_vo.setWeather_check(1);
		unit_service.unit_weather_update(unit_vo);
		device_service.adminWeatherInsert(weather);

		model.addAttribute("msg", "등록되었습니다."); 
		model.addAttribute("url", "<c:url value='/admin/device/weather/list'/>");
		model.addAttribute("redirect", "/admin/device/weather/list");
		
		return "/admin/device/Message";
	}
	
	
}


