package com.iof.center.user.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.api.plus.PlusOperations;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.iof.center.user.service.UserService;
import com.iof.center.user.vo.UserVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class GoogleLoginController {

	/* GoogleLogin */
	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;
	@Autowired
	private OAuth2Parameters googleOAuth2Parameters;
	@Resource(name = "UserService")
	private UserService user_service;

	// 구글 Callback호출 메소드
	@RequestMapping(value = "/callback/google", method = { RequestMethod.GET, RequestMethod.POST })
	public String googleCallback(HttpSession session, Model model, @RequestParam String code) throws IOException {
		System.out.println("여기는 googleCallback");

		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations(); 
		AccessGrant accessGrant = oauthOperations.exchangeForAccess(code, googleOAuth2Parameters.getRedirectUri(), null);
		String accessToken = accessGrant.getAccessToken();
		Long expireTime = accessGrant.getExpireTime(); 
		if (expireTime != null && expireTime < System.currentTimeMillis()) { 
			accessToken = accessGrant.getRefreshToken();
			//logger.info("accessToken is expired. refresh token = {}" , accessToken); 
			}
		Connection<Google>connection = googleConnectionFactory.createConnection(accessGrant);
		Google google = connection == null ? new GoogleTemplate(accessToken) : connection.getApi();
		PlusOperations plusOperations = google.plusOperations(); 
		Person person = plusOperations.getGoogleProfile();

		
		String id = person.getId();
		String email = person.getAccountEmail();
		String name = person.getDisplayName();
	
		UserVO vo = new UserVO();
		vo.setUser_id(id);
		vo = user_service.getUser(vo);
		
		String returnUrl = null;
		
		if(vo == null) {
			model.addAttribute("nickname", email);
			model.addAttribute("user_name", person.getDisplayName());
			model.addAttribute("user_email", email);
			model.addAttribute("user_id", id);	
			returnUrl = "/user/regist_google";
		}else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_id", person.getId());
			map.put("user_level", 10);
			map.put("user_social", "google");
			session.setAttribute("user", map);		
			returnUrl = "/ds";
		}
		return returnUrl;
	}
}
