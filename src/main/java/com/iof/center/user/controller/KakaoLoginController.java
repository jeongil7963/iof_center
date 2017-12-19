package com.iof.center.user.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.iof.center.user.service.UserService;
import com.iof.center.user.vo.UserVO;

@Controller
public class KakaoLoginController {

	@Resource(name = "UserService")
	private UserService user_service;
	
	@RequestMapping(value = "/callback/kakao", produces = "application/json", method = { RequestMethod.GET, RequestMethod.POST })
	public String kakalogin(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response, Model model,HttpSession session) {
		System.out.println("kakao");
		System.out.println(code);
		
		JsonNode jsonToken = getAccessToken(code);
		System.out.println("JSON 반환 : " + jsonToken.get("access_token"));
		
		JsonNode userInfo = getKakaoUserInfo(jsonToken.get("access_token").toString());
        
        // Get id
 		String id = userInfo.path("id").asText();
 		String nickname = null;
 		String thumbnailImage = null;
 		String profileImage = null;
 		String email = userInfo.path("kaccount_email").asText();
     		
        // 유저정보 카톡에서 가져오기 Get properties
		JsonNode properties = userInfo.path("properties");
		if (properties.isMissingNode()) {
			// if "name" node is missing
		} else {
			nickname = properties.path("nickname").asText();
			thumbnailImage = properties.path("thumbnail_image").asText();
			profileImage = properties.path("profile_image").asText();
		}
		
		
		UserVO vo = new UserVO();
		vo.setUser_id(id);
		vo = user_service.getUser(vo);
		if(vo == null) {
			model.addAttribute("nickname", email);
			model.addAttribute("user_name", nickname);
			model.addAttribute("user_email", email);
			model.addAttribute("user_id", id);	
			return "user/regist_kakao";
		}else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_id", id);
			map.put("user_level", 10);
			map.put("user_social", "kakao");
			session.setAttribute("user", map);	
			return "redirect:/ds";
		}
	}
	
	public static JsonNode getAccessToken(String autorize_code){ 
	    final String RequestUrl = "https://kauth.kakao.com/oauth/token";

	    final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
	    postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
	    postParams.add(new BasicNameValuePair("client_id", "d14613a22ed01110bf5e5f667db7e4d1"));    // REST API KEY
	    postParams.add(new BasicNameValuePair("redirect_uri", "http://localhost:8080/callback/kakao"));    // 리다이렉트 URI
	    postParams.add(new BasicNameValuePair("code", autorize_code));    // 로그인 과정중 얻은 code 값

	    final HttpClient client = HttpClientBuilder.create().build();
	    final HttpPost post = new HttpPost(RequestUrl);
	    JsonNode returnNode = null;
	
	    try {
	      post.setEntity(new UrlEncodedFormEntity(postParams));
	      final HttpResponse response = client.execute(post);
	      final int responseCode = response.getStatusLine().getStatusCode();

	      System.out.println("\nSending 'POST' request to URL : " + RequestUrl);
	      System.out.println("Post parameters : " + postParams);
	      System.out.println("Response Code : " + responseCode);
	     
	      //JSON 형태 반환값 처리
	      ObjectMapper mapper = new ObjectMapper();
	      returnNode = mapper.readTree(response.getEntity().getContent());

	    } catch (UnsupportedEncodingException e) {
	      e.printStackTrace();
	    } catch (ClientProtocolException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    } finally {
	        // clear resources
	    }
	    
	    return returnNode;
	}
	
	 public static JsonNode getKakaoUserInfo(String autorize_code) {
			
		 final String RequestUrl = "https://kapi.kakao.com/v1/user/me";
		    
		    String CLIENT_ID = "d14613a22ed01110bf5e5f667db7e4d1"; // REST API KEY
		    String REDIRECT_URI = "http://localhost:8080/callback/kakao"; // 리다이렉트 URI
		    String code = autorize_code; // 로그인 과정중 얻은 토큰 값

		    final HttpClient client = HttpClientBuilder.create().build();
		    final HttpPost post = new HttpPost(RequestUrl);
		    
		    // add header
		    post.addHeader("Authorization", "Bearer " + autorize_code);
		    
		    JsonNode returnNode = null;
		    
		    try {
		      final HttpResponse response = client.execute(post);
		      final int responseCode = response.getStatusLine().getStatusCode();

		      System.out.println("\nSending 'POST' request to URL : " + RequestUrl);
		      System.out.println("Response Code : " + responseCode);

		      //JSON 형태 반환값 처리
		      ObjectMapper mapper = new ObjectMapper();
		      returnNode = mapper.readTree(response.getEntity().getContent());
		      
		    } catch (UnsupportedEncodingException e) {
		      e.printStackTrace();
		    } catch (ClientProtocolException e) {
		      e.printStackTrace();
		    } catch (IOException e) {
		      e.printStackTrace();
		    } finally {
		        // clear resources
		    }
		    return returnNode;
		}
}
