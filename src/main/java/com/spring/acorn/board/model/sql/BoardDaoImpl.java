package com.spring.acorn.board.model.sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.acorn.board.model.vo.BoardVO;
import com.spring.acorn.board.model.vo.ReplyVO;

@Repository("boardDao")
public class BoardDaoImpl implements BoardDao {
	
	@Autowired
	private SqlSession session;
	
	@Override
	public List<BoardVO> listRow() {
		// TODO Auto-generated method stub
		System.out.println("Dao listRow");
		return session.selectList("list");
	}

	@Override
	public int insertRow(BoardVO obj) {
		// TODO Auto-generated method stub
		System.out.println("dao insertRow");
		System.out.println("param :" + obj.toString());
		return session.insert("insert", obj);
	}

	private void upCnt(BoardVO obj){
		System.out.print("Dao upCnt");
		session.update("com.spring.acorn.mapper.board.upcnt", obj);
	}
	
	@Override
	public BoardVO readRow(BoardVO obj) {
		// TODO Auto-generated method stub
		upCnt(obj);
		System.out.println("dao readRow");
		System.out.println("Dao param :" + obj.getBno());
		BoardVO board = session.selectOne("read",obj);
		List<ReplyVO> rlist = session.selectList("com.spring.acorn.mapper.board.readReply",obj);
		System.out.println("Dao rlist");
		board.setRlist(rlist);
		return board;
	}
	
	@Override
	public int removeRow(BoardVO obj) {
		// TODO Auto-generated method stub
		System.out.println("dao removeRow");
		System.out.println("param :" + obj.toString());
		return session.delete("remove", obj);
	}
	
	@Override
	public int modifyRow(BoardVO obj) {
		// TODO Auto-generated method stub
		System.out.println("dao modifyRow");
		System.out.println("param :" + obj.toString());
		return session.delete("modify", obj);
	}

	@Override
	public List<BoardVO> searchRow(String type, String keyword) {
		// TODO Auto-generated method stub
		System.out.println("Dao searchRow");
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", type);
		map.put("keyword", keyword);
		System.out.println("type : "+type+", keyword : "+keyword);
		return session.selectList("com.spring.acorn.mapper.board.search", map);
	}
	
	@Override
	public List<ReplyVO> rInsertRow(ReplyVO reply) {
		// TODO Auto-generated method stub
		System.out.println("Dao rInsertRow");
		int flag = session.insert("com.spring.acorn.mapper.board.rInsert",reply);
		List<ReplyVO> rlist = null;
		
		if(flag != 0){
			rlist = session.selectList("com.spring.acorn.mapper.board.readReply", reply) ;
		}
		return rlist;
	}
	
	@Override
	public List<ReplyVO> rRemoveRow(ReplyVO reply) {
		// TODO Auto-generated method stub
		System.out.println("Dao rRemoveRow");
		int flag = session.insert("com.spring.acorn.mapper.board.rRemove",reply);
		List<ReplyVO> rlist = null;
		
		if(flag != 0){
			rlist = session.selectList("com.spring.acorn.mapper.board.readReply", reply) ;
		}
		return rlist;
	}
}
