package com.spring.acorn.board.model.vo;

/*
 
 create table reply(
 	rseq		 number primary key,
 	bno			 number references tbl_board(bno),
 	rwriter		 varchar2(20),
 	rcontent	 varchar2(1000) 
 );
 
 create sequence replyseq;
 */
public class ReplyVO {
	
	private int rseq, bno;
	private String rwriter, rcontent;
	
	
	
	public ReplyVO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public ReplyVO(int rseq, int bno, String rwriter, String rcontent) {
		super();
		this.rseq = rseq;
		this.bno = bno;
		this.rwriter = rwriter;
		this.rcontent = rcontent;
	}



	public int getRseq() {
		return rseq;
	}



	public void setRseq(int rseq) {
		this.rseq = rseq;
	}



	public int getBno() {
		return bno;
	}



	public void setBno(int bno) {
		this.bno = bno;
	}



	public String getRwriter() {
		return rwriter;
	}



	public void setRwriter(String rwriter) {
		this.rwriter = rwriter;
	}



	public String getRcontent() {
		return rcontent;
	}



	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}
	
	
}
