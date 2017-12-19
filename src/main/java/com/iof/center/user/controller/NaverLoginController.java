package com.iof.center.user.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.iof.center.user.service.UserService;
import com.iof.center.user.vo.UserVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class NaverLoginController {

	/* NaverLoginBO */
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;

	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	
	@Resource(name = "UserService")
	private UserService user_service;

	//네이버 로그인 성공시 callback호출 메소드
	@RequestMapping(value = "/callback/naver", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session) throws IOException {
		try {
			System.out.println("여기는 callback");
			OAuth2AccessToken oauthToken;
			oauthToken = naverLoginBO.getAccessToken(session, code, state);
			//로그인 사용자 정보를 읽어온다.
			apiResult = naverLoginBO.getUserProfile(oauthToken);
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(apiResult);
			JSONObject response = (JSONObject) jsonObject.get("response");
			UserVO vo = new UserVO();
			vo.setUser_id(response.get("enc_id").toString());
			vo = user_service.getUser(vo);
			System.out.println(response);
			if(vo == null) {
				model.addAttribute("nickname", response.get("nickname"));
				model.addAttribute("user_name", response.get("name"));
				model.addAttribute("user_email", response.get("email"));
				model.addAttribute("user_id", response.get("enc_id"));	
				return "user/regist_naver";
			}else {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("user_id", response.get("enc_id"));
				map.put("user_level", 10);
				map.put("user_social", "naver");
				session.setAttribute("user", map);				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/ds";
	}
	
	@RequestMapping(value = "/callback/regist", method = RequestMethod.POST)
	public ModelAndView regist_post(UserVO vo, Model model, HttpSession session) {
		ModelAndView mav = new ModelAndView();

		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current_time = transFormat.format(from);
		vo.setUser_createtime(current_time);
		vo.setUser_updatetime(current_time);
		vo.setUser_lastlogin(current_time);
		vo.setUser_social("naver");
		vo.setUser_level(10);
		vo.setUser_pw("null");
		
		int rowcount = user_service.checkSignup_email(vo.getUser_email());
		
		if(rowcount == 1) {
			mav.setViewName("redirect:/user/overlap");
		}else {
			System.out.println(vo);
			user_service.insertUser2(vo);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_id", vo.getUser_nickname());
			map.put("user_level", 10);
			map.put("user_social", "naver");
			session.setAttribute("user", map);	
			mav.setViewName("redirect:/ds");
		}

		
		return mav;
	}
}
