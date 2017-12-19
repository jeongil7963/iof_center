package com.spring.acorn.test2;

import java.util.Locale;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/admin/test")
public class Test2Controller {
	private static final Logger logger = LoggerFactory.getLogger(Test2Controller.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping("/test")
	public String test2(Locale locale) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		return "admin/test/test";
	}
	
	@RequestMapping(value = "/testInsert", method = RequestMethod.GET)
	public String testInsert(Test2VO test2,Locale locale) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		System.out.println(test2.getTest());
		return "test/test";
	}
}
