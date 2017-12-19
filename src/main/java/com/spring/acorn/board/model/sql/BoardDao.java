package com.spring.acorn.board.model.sql;

import java.util.List;

import com.spring.acorn.board.model.vo.BoardVO;
import com.spring.acorn.board.model.vo.ReplyVO;

public interface BoardDao {
	
	public List<BoardVO> listRow();
	public int insertRow(BoardVO obj);
	public BoardVO readRow(BoardVO obj);
	public int removeRow(BoardVO obj);
	public int modifyRow(BoardVO obj);
	public List<BoardVO> searchRow(String type, String keyword);
	public List<ReplyVO> rInsertRow(ReplyVO reply);
	public List<ReplyVO> rRemoveRow(ReplyVO reply);
}
