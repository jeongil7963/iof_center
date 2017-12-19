package com.spring.acorn.board.service;

import java.util.List;
import com.spring.acorn.board.model.vo.BoardVO;
import com.spring.acorn.board.model.vo.ReplyVO;


public interface BoardService {
	
	public List<BoardVO> list();
	public int insert(BoardVO obj);
	public BoardVO read(BoardVO obj);
	public int remove(BoardVO obj);
	public int modify(BoardVO obj);
	public List<BoardVO> search(String type, String keyword);
	public List<ReplyVO> rInsert(ReplyVO reply);
	public List<ReplyVO> rRemove(ReplyVO reply);
	
}
