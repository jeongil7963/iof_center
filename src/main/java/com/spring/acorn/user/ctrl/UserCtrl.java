package com.spring.acorn.user.ctrl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.acorn.user.model.vo.UserVO;
import com.spring.acorn.user.service.UserService;

@Controller
@RequestMapping("/user")
//@SessionAttributes("loginUser")	//모델에 들어온 객체를 세션에 저장함
public class UserCtrl {

	//의존성 주입
	@Resource(name="userService")
	private UserService service;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginForm(){
		System.out.println("Ctrl loginForm");
		return "user/login";
	}
	
	/*
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(UserVO user, Model model){
		System.out.println("Ctrl login");
		//구현
		UserVO result = service.login(user);
		model.addAttribute("loginUser",result);
		return "redirect:/";
	}
	*/
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public void login(UserVO user, Model model){
		System.out.println("Ctrl login5555");
		//구현
		UserVO result = service.login(user);
		model.addAttribute("loginUser",result);
		//return "redirect:/";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session){
		System.out.println("Ctrl logout");
		session.invalidate();
		return "redirect:/";
	}
	
}
