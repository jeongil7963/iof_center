package com.iof.center.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.view.RedirectView;

import com.iof.center.user.service.UserService;
import com.iof.center.user.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/connect")
public class FacebookLoginController extends ConnectController {
	@Resource(name = "UserService")
	private UserService user_service;
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String TARGET_URL = new String();

	//@Resource(name = "signInUserDetailsService")
	//private SignInUserDetailsService signInUserDetailsService;

	@Resource(name = "inMemoryConnectionRepository")
	private ConnectionRepository connectionRepository;

	@Inject
	public FacebookLoginController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
		super(connectionFactoryLocator, connectionRepository);
	}

	@RequestMapping(value = "/{providerId}", method = RequestMethod.POST)
	public RedirectView connect(@PathVariable String providerId, NativeWebRequest request) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request.getNativeRequest();
		TARGET_URL = httpServletRequest.getHeader("REFERER");
		System.out.println("hiiii222222");
		return super.connect(providerId, request);
	}

	@RequestMapping(value = "/{providerId}", method = RequestMethod.GET, params = "code")
	public RedirectView oauth2Callback(@PathVariable String providerId, NativeWebRequest webRequest) {
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		HttpSession session = request.getSession();
		
		RedirectView redirectView = super.oauth2Callback(providerId, webRequest);
		// 사용자 정보 가져오기
		Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
		Facebook facebook = connection.getApi();
		String[] fields = { "id", "age_range", "email", "first_name", "gender", "last_name", "link", "locale", "name", "third_party_id", "verified" };
		User userProfile = facebook.fetchObject("me", User.class, fields);
		// 로그인 처리
		//signInUserDetailsService.onAuthenticationBinding(new MemberVO(), userProfile); 
		
		
		System.out.println("hiiii");
		System.out.println(userProfile.getEmail());
		System.out.println(userProfile.getId());
		System.out.println(userProfile.getThirdPartyId());
	
		UserVO vo = new UserVO();
		vo.setUser_id(userProfile.getId());
		vo = user_service.getUser(vo);
		if(vo == null) {
			session.setAttribute("nickname", userProfile.getEmail());
			session.setAttribute("user_name", userProfile.getName());
			session.setAttribute("user_email",userProfile.getEmail());
			session.setAttribute("user_id", userProfile.getId());
			redirectView.setUrl("/user/regist_facebook");
		}else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_id", userProfile.getId());
			map.put("user_level", 10);
			map.put("user_social", "facebook");
			session.setAttribute("user", map);	
			redirectView.setUrl("/ds");
		}
		return redirectView;
	}
}
