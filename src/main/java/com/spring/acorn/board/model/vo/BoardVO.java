package com.spring.acorn.board.model.vo;

import java.util.Date;
import java.util.List;

/*
 
 create table tbl_board(
 
 	bno number primary key,
 	title varchar2(50),
 	content varchar2(2000),
 	writer varchar2(50),
 	regdate date default sysdate,
 	viewcnt number default 0
 
 );
 
 create sequence boardseq;
 
 insert into tbl_board values ( boardseq.nextval,'잘됨','뻥이야','임정섭',default,default);
 
 commit;
 
 */
public class BoardVO {
	private int bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private int viewcnt;
	private List<ReplyVO> rlist;
	
	
	public BoardVO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public BoardVO(int bno, String title, String content, String writer, Date regdate, int viewcnt) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.regdate = regdate;
		this.viewcnt = viewcnt;
	}


	public int getBno() {
		return bno;
	}


	public void setBno(int bno) {
		this.bno = bno;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Date getRegdate() {
		return regdate;
	}


	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}


	public int getViewcnt() {
		return viewcnt;
	}


	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}

	

	public List<ReplyVO> getRlist() {
		return rlist;
	}


	public void setRlist(List<ReplyVO> rlist) {
		this.rlist = rlist;
	}


	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title + ", content=" + content + ", writer=" + writer + ", regdate="
				+ regdate + ", viewcnt=" + viewcnt + "]";
	}
	
	
}
