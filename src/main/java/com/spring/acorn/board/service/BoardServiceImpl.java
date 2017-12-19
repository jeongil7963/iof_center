package com.spring.acorn.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spring.acorn.board.model.sql.BoardDao;
import com.spring.acorn.board.model.vo.BoardVO;
import com.spring.acorn.board.model.vo.ReplyVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	@Resource(name="boardDao")
	private BoardDao dao;
	
	@Override
	public List<BoardVO> list() {
		// TODO Auto-generated method stub
		System.out.println("service list");
		return dao.listRow();
	}

	@Override
	public int insert(BoardVO obj) {
		// TODO Auto-generated method stub
		System.out.println("sevice insert");
		return dao.insertRow(obj);
	}

	@Override
	public BoardVO read(BoardVO obj) {
		// TODO Auto-generated method stub
		System.out.println("service read");
		return dao.readRow(obj);
	}
	
	@Override
	public int remove(BoardVO obj) {
		// TODO Auto-generated method stub
		System.out.println("sevice remove");
		return dao.removeRow(obj);
	}
	
	@Override
	public int modify(BoardVO obj) {
		// TODO Auto-generated method stub
		System.out.println("sevice remove");
		return dao.modifyRow(obj);
	}
	
	@Override
	public List<BoardVO> search(String type, String keyword) {
		// TODO Auto-generated method stub
		System.out.println("Service search");
		return dao.searchRow(type, keyword);
	}
	
	@Override
	public List<ReplyVO> rInsert(ReplyVO reply) {
		// TODO Auto-generated method stub
		System.out.println("Service rInsert");
		return dao.rInsertRow(reply);
	}
	
	@Override
	public List<ReplyVO> rRemove(ReplyVO reply) {
		// TODO Auto-generated method stub
		System.out.println("Service rRemove");
		return dao.rRemoveRow(reply);
	}
}
