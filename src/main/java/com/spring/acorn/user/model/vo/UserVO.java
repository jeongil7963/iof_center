package com.spring.acorn.user.model.vo;

/*
 
 create table tbl_user2(
 	id  varchar2(20) primary key,
 	upw  varchar2(20),
 	uname  varchar2(20),
 	upoint  number
 );
 	
insert into tbl_user2 values('acorn','acorn','관리자',0);
 
 commit;
  
 */
public class UserVO {
	private String id, upw, uname;
	private int upoint;
	
	
	
	public UserVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserVO(String id, String upw, String uname, int upoint) {
		super();
		this.id = id;
		this.upw = upw;
		this.uname = uname;
		this.upoint = upoint;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUpw() {
		return upw;
	}
	public void setUpw(String upw) {
		this.upw = upw;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getUpoint() {
		return upoint;
	}
	public void setUpoint(int upoint) {
		this.upoint = upoint;
	}
	
	@Override
	public String toString() {
		return "UserVO [id=" + id + ", upw=" + upw + ", uname=" + uname + ", upoint=" + upoint + "]";
	}
	
	
}
