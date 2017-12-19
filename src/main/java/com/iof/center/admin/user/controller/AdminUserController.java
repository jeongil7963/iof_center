package com.iof.center.admin.user.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.iof.center.user.service.UserService;
import com.iof.center.user.vo.UserVO;
import com.iof.center.util.PageMaker;

/**
* 관리자 회원 관리 
* @author 우성정
* @since 2017.10.16
*/
@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

	@Resource(name = "UserService")
	private UserService service;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	/**
	* 회원 목록
	* @param request
	* @return view
	* @exception 
	*/
	
	@RequestMapping(value = "/list")
	public ModelAndView admin_user_list_post(@RequestParam(defaultValue="") String keyword, PageMaker pagemaker, Model model) {
		ModelAndView mav = new ModelAndView();
		int count = 0;
		count = service.count_user2(keyword);

		pagemaker.setPage(pagemaker.getPage());
		pagemaker.setCount(count);
		
		List<UserVO> list = service.getRead2(pagemaker.getPage(),keyword);
		
		mav.addObject("UserList", list);
		mav.addObject("pageMaker",pagemaker);
		mav.addObject("keyword",keyword);
		mav.setViewName("admin/user/list");
		return mav;
	}
	

	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ModelAndView delUser(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		String delUser = map.get("delSeqNo").toString();
		System.out.println("delUser : " + delUser);
		String[] userArr = delUser.split(",");
		for(int i=0; i<userArr.length; i++) {
			service.deleteUser(userArr[i]);
		}
		mav.setViewName("redirect:/admin/user/regist");
		return mav;
	}


	/**
	* 회원 등록 폼
	* @param request
	* @return view
	* @exception 
	*/
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public ModelAndView registerForm(Model model, UserVO vo) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/user/regist");
		return mav;
	}

	/**
	* 회원 등록
	* @param request
	* @return view
	* @exception SQLException
	*/
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public ModelAndView regist_post(@ModelAttribute UserVO vo, Model model) {
		System.out.println("admin user regist");
		System.out.println(vo.toString());
		ModelAndView mav = new ModelAndView();
		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current_time = transFormat.format(from);
		String encryptPassword = passwordEncoder.encode(vo.getUser_pw());
		System.out.println("pw : " + encryptPassword);	
		vo.setUser_pw(encryptPassword);
		vo.setUser_createtime(current_time);
		vo.setUser_updatetime("0000-00-00 00:00:00");
		vo.setUser_level(10);
		service.insertUser(vo);
		mav.setViewName("redirect:/admin/user/list");
		return mav;
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public ModelAndView modify_form_get(Model model, UserVO vo) {
		ModelAndView mav = new ModelAndView();
		UserVO res = service.getUser(vo);
		System.out.println(res.toString());
		
		mav.addObject("dto",res);
		mav.setViewName("admin/user/modify");
		return mav;
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public ModelAndView modify_form_post(Model model, UserVO vo) {
		ModelAndView mav = new ModelAndView();
		System.out.println(vo.toString());
		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current_time = transFormat.format(from);
		
		String pw = vo.getUser_pw();
		String encryptPassword = passwordEncoder.encode(vo.getUser_pw());
		vo.setUser_pw(encryptPassword);
		vo.setUser_updatetime(current_time);
		if(pw == "") {
			service.updateUser2(vo);
		}else {
			service.updateUser(vo);
		}
		mav.setViewName("redirect:/admin/user/list");
		return mav;
	}
}
