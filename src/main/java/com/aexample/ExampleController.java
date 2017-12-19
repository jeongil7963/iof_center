package com.aexample;



import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.acorn.test2.Test2Controller;

@Controller
@RequestMapping("/admin/example")
public class ExampleController {
	private static final Logger logger = LoggerFactory.getLogger(Test2Controller.class);
	
	@RequestMapping(value = "/example", method = RequestMethod.GET)
	public String example(Locale locale) {
		logger.info("Welcome home! The client locale is {}.", locale);
		return "example/example";
		
	}
	
	@RequestMapping("/list")
	public String list(Model model){
		return "admin/example/list";
	}
	
	@RequestMapping(value="/regist", method=RequestMethod.GET)
	public String registerForm(Model model){
		return "admin/example/regist";
	}
	
	
}
