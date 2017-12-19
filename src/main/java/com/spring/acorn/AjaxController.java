package com.spring.acorn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.acorn.user.model.vo.UserVO;

@Controller
public class AjaxController {
	
	@RequestMapping("/vo")
	@ResponseBody
	public UserVO getVOJson(){
		System.out.println("Ajax ~~ ");
		return new UserVO("jslim","jslim","임정섭",1000);
	}
	
	
	@RequestMapping("/ary")
	@ResponseBody
	public ArrayList<UserVO> getAryJson() {
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		list.add(new UserVO("jslim","jslim","임정섭",1000));
		list.add(new UserVO("jslim","jslim","임정섭",1000));
		return list;
	}
	
	@RequestMapping("/map")
	@ResponseBody
	public HashMap<String, List> getMapson() {
		HashMap<String, List> map = new HashMap<String, List>();
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		list.add(new UserVO("jslim","jslim","임정섭",1000));
		list.add(new UserVO("jslim","jslim","임정섭",1000));
		map.put("list01", list);
		map.put("list02", list);
		return map;
	}
}
