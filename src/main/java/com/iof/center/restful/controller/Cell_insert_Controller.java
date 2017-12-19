package com.iof.center.restful.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.iof.center.restful.service.RestfulService;
import com.iof.center.restful.vo.Cell_Insert_VO;

@Controller
public class Cell_insert_Controller {
	
	@Resource(name = "RestfulService")
	private RestfulService rest_service;
	
	@ResponseBody
	@RequestMapping("/api/cell/insert")
    public Cell_Insert_VO cell_insert_vo(@RequestParam String api_key, String sv_sensor1) {
		
		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current_time = transFormat.format(from);
		
		
		Cell_Insert_VO vo = new Cell_Insert_VO(sv_sensor1,current_time,"jeongil" ,api_key);
		rest_service.insert_rest(vo);
		return vo;
    }
}
