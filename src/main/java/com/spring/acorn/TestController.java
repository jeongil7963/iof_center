package com.spring.acorn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@RequestMapping("/pre.do")
	public void pre() {
		System.out.println("TestController pre()");
	}
	
	@RequestMapping("/post.do")
	public void post() {
		System.out.println("TestController post()");
	}
}
