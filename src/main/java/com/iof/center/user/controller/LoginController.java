package com.iof.center.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iof.center.user.dao.UserDao;
import com.iof.center.user.service.UserService;
import com.iof.center.user.vo.UserVO;

@Controller
@RequestMapping("/user")
public class LoginController {

	@Resource(name = "UserService")
	private UserService userDao;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	/* NaverLoginBO */
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;
	
	
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}

	//google login 설정
	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;
	@Autowired
	private OAuth2Parameters googleOAuth2Parameters;
		
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(HttpSession session, Model model) {
		try {
			//kakao url
			String KakaoAuthUrl = "https://kauth.kakao.com/oauth/authorize?";
			KakaoAuthUrl += "client_id=d14613a22ed01110bf5e5f667db7e4d1&";
			KakaoAuthUrl += "redirect_uri=http://localhost:8080/callback/kakao&response_type=code";
			model.addAttribute("kakao_url", KakaoAuthUrl);
			//NAVER URL
			String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
			model.addAttribute("url", naverAuthUrl);
			
			//구글 URL
			OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
			String url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
			model.addAttribute("google_url", url);
			
			System.out.println("Ctrl loginForm");
			if (session == null) {
				return "/user/login";
			}

			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) session.getAttribute("user");
			if (map == null) {
				return "/user/login";
			}
			Object str = map.get("user_id");
			if (str == null) {
				return "/user/login";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "/user/login";
		}

		return "redirect:/ds";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(UserVO user, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Ctrl login");
		UserVO result;
		String returnURL = "";
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			result = userDao.login(user);

			System.out.println("user : " + user.toString());
			System.out.println("result : " + result.toString());

			if (passwordEncoder.matches(user.getUser_pw(), result.getUser_pw())) {
				System.out.println("login sucess");
				map.put("user_id", result.getUser_id());
				map.put("user_level", result.getUser_level());
				request.getSession().setAttribute("user", map);
				returnURL = "redirect:/cell";
			} else {
				System.out.println("login fail");
				map.put("msg", "fail");
				request.getSession().setAttribute("user", map);
				returnURL = "redirect:/user/login";
			}
		} catch (Exception e) {
			System.out.println("login fail");
			map.put("msg", "fail");
			request.getSession().setAttribute("user", map);
			returnURL = "redirect:/user/login";
		}
		return returnURL;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpSession session) {
		System.out.println("Ctrl logout");
		session.invalidate();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", "logout");
		request.getSession().setAttribute("user", map);
		return "redirect:/user/login";
	}
}
