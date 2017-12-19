package com.spring.acorn.board.ctrl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.acorn.board.model.vo.BoardVO;
import com.spring.acorn.board.model.vo.ReplyVO;
import com.spring.acorn.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardCtrl {
	
	@Resource(name="boardService")
	private BoardService service;
	
	@RequestMapping("/listPage")
	public String list(Model model){
		System.out.println("Ctrl list");
		List<BoardVO> list = service.list();
		model.addAttribute("list",list);
		return "/board/listPage";
	}
	
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String registerForm(){
		System.out.println("Ctrl register get");
		return "/board/register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(BoardVO obj){
		System.out.println("Ctrl register post");
		int flag = service.insert(obj);
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value="/readPage", method=RequestMethod.GET)
	public String read(BoardVO obj, Model model){
		System.out.println("Ctrl read get");
		BoardVO board = service.read(obj);
		model.addAttribute("board", board);
		return "/board/readPage";
	}
	
	@RequestMapping(value="/removePage", method=RequestMethod.GET)
	public String remove(BoardVO obj, Model model){
		System.out.println("Ctrl remove get");
		int flag = service.remove(obj);
		return "redirect:/board/listPage";
	}
	

	/*
	@RequestMapping(value="/modifyPage", method=RequestMethod.GET)
	public String modifyForm(@ModelAttribute("board") BoardVO obj){
		System.out.println("Ctrl modifyForm");
		return "/board/modifyPage";
	}
	*/
	
	@RequestMapping(value="/modifyPage", method=RequestMethod.GET)
	public String modifyForm(BoardVO obj, Model model){
		System.out.println("Ctrl remodify get");
		BoardVO board = service.read(obj);
		model.addAttribute("board", board);
		return "/board/modifyPage";
	}
	
	@RequestMapping(value="/modifyPage", method=RequestMethod.POST)
	public String modify(BoardVO obj){
		System.out.println("Ctrl remodify post");
		int flag = service.modify(obj);
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value="/search", method = RequestMethod.POST)
	@ResponseBody
	public List<BoardVO> search(String type, String keyword){
		System.out.println("ctrl search post");
		System.out.println("param(type) :"+type);
		System.out.println("param(keyword) :"+keyword);
		return service.search(type, keyword);
	}
	
	@RequestMapping(value="/replyInsert", method = RequestMethod.POST)
	@ResponseBody
	public List<ReplyVO> rinsert(ReplyVO reply){
		System.out.println("ctrl rinsert post");
		System.out.println("param :"+reply.getRwriter());
		System.out.println("param :"+reply.getRcontent());
		System.out.println("param :"+reply.getBno());
		return service.rInsert(reply);
	}
	
	@RequestMapping(value="/replyRemove", method = RequestMethod.POST)
	@ResponseBody
	public List<ReplyVO> rremove(ReplyVO reply){
		System.out.println("ctrl rinsert post");
		System.out.println("param :"+reply.getBno());
		return service.rRemove(reply);
	}
	
}
