package com.iof.center.user.vo;

public class UserVO {
	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_email;
	private String user_phone;
	private String user_addr1;
	private String user_addr2;
	private String user_addr3;
	private String user_zipcode;
	private String user_createtime;
	private String user_updatetime;
	private String user_lastlogin;
	private int	user_level;
	private String user_nickname;
	private String user_social;
	private String user_token;
	
	public String getUser_token() {
		return user_token;
	}

	public void setUser_token(String user_token) {
		this.user_token = user_token;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public String getUser_social() {
		return user_social;
	}

	public void setUser_social(String user_social) {
		this.user_social = user_social;
	}

	public UserVO(String user_id, String user_pw) {
		super();
		this.user_id = user_id;
		this.user_pw = user_pw;
		}
	
	public UserVO(String user_id, String user_pw, String user_name, String user_email, String user_phone, String user_addr1, String user_addr2,
			String user_addr3, String user_zipcode, String user_createtime, String user_updatetime, String user_lastlogin, int user_level) {
		super();
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.user_email = user_email;
		this.user_phone = user_phone;
		this.user_addr1 = user_addr1;
		this.user_addr2 = user_addr2;
		this.user_addr3 = user_addr3;
		this.user_zipcode = user_zipcode;
		this.user_createtime = user_createtime;
		this.user_updatetime = user_updatetime;
		this.user_lastlogin = user_lastlogin;
		this.user_level = user_level;
	}

	public UserVO() {
		super();
	}

	@Override
	public String toString() {
		return "UserVO [user_id=" + user_id + ", user_pw=" + user_pw + ", user_name=" + user_name + ", user_email="
				+ user_email + ", user_phone=" + user_phone + ", user_addr1=" + user_addr1 + ", user_addr2="
				+ user_addr2 + ", user_addr3=" + user_addr3 + ", user_zipcode=" + user_zipcode + ", user_createtime="
				+ user_createtime + ", user_updatetime=" + user_updatetime + ", user_lastlogin=" + user_lastlogin
				+ ", user_level=" + user_level + "]";
	}
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String string) {
		this.user_id = string;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_addr1() {
		return user_addr1;
	}
	public void setUser_addr1(String user_addr1) {
		this.user_addr1 = user_addr1;
	}
	public String getUser_addr2() {
		return user_addr2;
	}
	public void setUser_addr2(String user_addr2) {
		this.user_addr2 = user_addr2;
	}
	public String getUser_addr3() {
		return user_addr3;
	}
	public void setUser_addr3(String user_addr3) {
		this.user_addr3 = user_addr3;
	}
	public String getUser_zipcode() {
		return user_zipcode;
	}
	public void setUser_zipcode(String user_zipcode) {
		this.user_zipcode = user_zipcode;
	}
	public String getUser_createtime() {
		return user_createtime;
	}
	public void setUser_createtime(String user_createtime) {
		this.user_createtime = user_createtime;
	}
	public String getUser_updatetime() {
		return user_updatetime;
	}
	public void setUser_updatetime(String user_updatetime) {
		this.user_updatetime = user_updatetime;
	}
	public String getUser_lastlogin() {
		return user_lastlogin;
	}
	public void setUser_lastlogin(String user_lastlogin) {
		this.user_lastlogin = user_lastlogin;
	}
	public int getUser_level() {
		return user_level;
	}
	public void setUser_level(int user_level) {
		this.user_level = user_level;
	}
}
