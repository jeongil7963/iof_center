package com.iof.center.user.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iof.center.admin.cell.vo.AdminCellVO;
import com.iof.center.user.service.UserService;
import com.iof.center.user.vo.UserVO;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource(name = "UserService")
	private UserService service;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@SuppressWarnings({ "unused", "unchecked" })
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public String regist_get(HttpSession session) {

		try {
			System.out.println("Ctrl regist");
			String returnURL = "";
			Map<String, Object> map = (Map<String, Object>) session.getAttribute("user");
			Object str = map.get("user_id");
			if (session == null) {
				return "/user/regist";
			}
			if (str == null) {
				return "/user/regist";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "/user/regist";
		}

		return "redirect:/ds";
	}

	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public ModelAndView regist_post(@RequestParam String id, String pw, String name, String email, String phone, String zipcode, String addr1, String addr2) {
		ModelAndView mav = new ModelAndView();

		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current_time = transFormat.format(from);
		String encryptPassword = passwordEncoder.encode(pw);
		System.out.println("pw : " + encryptPassword);
		UserVO vo = new UserVO(id, encryptPassword, name, email, phone, addr1, addr2, "NULL", zipcode, current_time, current_time, current_time, 10);
		service.insertUser(vo);
		mav.setViewName("redirect:/ds");
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/check_id", method = RequestMethod.POST)
	public String checkSignup_id(HttpServletRequest request, Model model) {
		String id = request.getParameter("user_id");
		int rowcount = service.checkSignup(id);
		System.out.println("rowcount : " + rowcount);
		return String.valueOf(rowcount);
	}
	
	@ResponseBody
	@RequestMapping(value = "/search_id", method = RequestMethod.POST)
	public Map<String, Object> search_id(Model model, @RequestParam Map<String, Object> map) throws Exception {
		
		String SearchID = map.get("user_id").toString();
		System.out.println("user_id = " + map.get("user_id").toString());
		
		if(SearchID != "") {
			List<UserVO> user_list = new ArrayList();
			user_list = service.search_id(SearchID);
			map.put("user_list", user_list);
		}else {
			map.put("user_list", null);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/check_email", method = RequestMethod.POST)
	public String checkSignup_email(HttpServletRequest request, Model model) {
		String email = request.getParameter("user_email");
		int rowcount = service.checkSignup_email(email);
		System.out.println("rowcount : " + rowcount);
		return String.valueOf(rowcount);
	}

	@RequestMapping(value = "/modify_check", method = RequestMethod.GET)
	public ModelAndView modifiy_check_get(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/modify_check");
		
		Map<String, Object> map = (Map<String, Object>) session.getAttribute("user");
		String str = (String) map.get("user_social");
		if(str != null) {
			mav.setViewName("redirect:/user/modify");
			session.setAttribute("modify", "sucess");
		}
		return mav;
	}

	@RequestMapping(value = "/modify_check", method = RequestMethod.POST)
	public String modifiy_check_post(@RequestParam String user_pw, HttpSession session) throws Exception {
		System.out.println("Ctrl modify_check");
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) session.getAttribute("user");
		String str = (String) map.get("user_id");
		UserVO tmp = new UserVO(str, user_pw);
		UserVO result;
		String returnURL = "";
		logger.info(tmp.toString());
		try {
			result = service.login(tmp);

			if (passwordEncoder.matches(tmp.getUser_pw(), result.getUser_pw())) {
				session.setAttribute("modify", "sucess");
				returnURL = "redirect:/user/modify";
			} else {
				session.setAttribute("msg", "check_fail");
				returnURL = "redirect:/user/modify_check";
			}
		} catch (Exception e) {
			session.setAttribute("msg", "check_fail");
			returnURL = "redirect:/user/modify_check";
		}
		return returnURL;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public ModelAndView modify_get(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if ("sucess" == session.getAttribute("modify")) {
			Map<String, Object> map = (Map<String, Object>) session.getAttribute("user");
			String str = (String) map.get("user_id");
			UserVO tmp = new UserVO();
			tmp.setUser_id(str);

			UserVO vo = service.getUser(tmp);
			System.out.println(vo.toString());

			mav.setViewName("user/modify");
			mav.addObject("dto", vo);
			return mav;
		} else {
			mav.setViewName("redirect:/user/modify_check");
			return mav;
		}
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public ModelAndView modify_post(@RequestParam String id, String pw, String name, String email, String phone, String zipcode, String addr1, String addr2,
			HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current_time = transFormat.format(from);
		String encryptPassword = passwordEncoder.encode(pw);
		UserVO vo = new UserVO(id, encryptPassword, name, email, phone, addr1, addr2, "null", zipcode, current_time, current_time, current_time, 10);
		if (pw == "") {
			service.updateUser2(vo);
		} else {
			service.updateUser(vo);
		}
		session.removeAttribute("modify");
		mav.setViewName("redirect:/ds");
		return mav;
	}

	@RequestMapping(value = "/regist_facebook", method = RequestMethod.GET)
	public ModelAndView regist_facebook_get(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/regist_facebook");
		return mav;
	}

	@RequestMapping(value = "/regist_facebook", method = RequestMethod.POST)
	public String regist_facebook_post(HttpSession session, Model model, UserVO vo) {

		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current_time = transFormat.format(from);
		vo.setUser_createtime(current_time);
		vo.setUser_updatetime(current_time);
		vo.setUser_lastlogin(current_time);
		vo.setUser_social("facebook");
		vo.setUser_level(10);
		vo.setUser_pw("null");
		
		int rowcount = service.checkSignup_email(vo.getUser_email());
		if(rowcount == 1) {
			return "redirect:/user/overlap";
		}


		System.out.println(vo);
		service.insertUser2(vo);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", vo.getUser_nickname());
		map.put("user_level", 10);
		map.put("user_social", "facebook");
		session.setAttribute("user", map);

		return "redirect:/ds";
	}

	//kkako 회원 가입 처리
	@RequestMapping(value = "/regist_kakao", method = RequestMethod.POST)
	public String regist_kakao_post(HttpSession session, Model model, UserVO vo) {

		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current_time = transFormat.format(from);
		vo.setUser_createtime(current_time);
		vo.setUser_updatetime(current_time);
		vo.setUser_lastlogin(current_time);
		vo.setUser_social("kakao");
		vo.setUser_level(10);
		vo.setUser_pw("null");
		
		int rowcount = service.checkSignup_email(vo.getUser_email());
		if(rowcount == 1) {
			return "redirect:/user/overlap";
		}

		System.out.println(vo);
		service.insertUser2(vo);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", vo.getUser_nickname());
		map.put("user_level", 10);
		map.put("user_social", "kakao");
		session.setAttribute("user", map);

		return "redirect:/ds";
	}

	/*구글로 회원가입*/
	@RequestMapping(value = "/regist_google", method = RequestMethod.POST)
	public String regist_google_post(HttpSession session, Model model, UserVO vo) {

		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current_time = transFormat.format(from);
		vo.setUser_createtime(current_time);
		vo.setUser_updatetime(current_time);
		vo.setUser_lastlogin(current_time);
		vo.setUser_social("google");
		vo.setUser_level(10);
		vo.setUser_pw("null");
		
		int rowcount = service.checkSignup_email(vo.getUser_email());
		if(rowcount == 1) {
			return "redirect:/user/overlap";
		}
		
		System.out.println(vo);
		service.insertUser2(vo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", vo.getUser_nickname());
		map.put("user_level", 10);
		map.put("user_social", "google");
		session.setAttribute("user", map);

		return "redirect:/ds";
	}
	
	@RequestMapping(value = "/overlap")
	public String overlap(HttpServletRequest request, Model model) {
		
		return "/user/overlap";
	}
}
