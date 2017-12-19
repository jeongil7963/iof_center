package com.iof.center.ds.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DsController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main() {
		System.out.println("Ctrl main");
		return "/user/login";
	}
	
	@RequestMapping(value = "/ds", method = RequestMethod.GET)
	public String loginForm() {
		System.out.println("Ctrl DS");
		return "/ds";
	}
}
