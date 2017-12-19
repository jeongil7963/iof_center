package com.iof.center.restful.vo;

public class Cell_Insert_VO {
	
	private String sv_sensor1;
	private String sv_createtime;
	private String user_id;
	private String api_key;
	
	public String getApi_key() {
		return api_key;
	}
	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}
	public Cell_Insert_VO(String sv_sensor1, String sv_createtime, String user_id, String api_key) {
		super();
		this.sv_sensor1 = sv_sensor1;
		this.sv_createtime = sv_createtime;
		this.user_id = user_id;
		this.api_key = api_key;
	}
	
	public String getSv_sensor1() {
		return sv_sensor1;
	}
	public void setSv_sensor1(String sv_sensor1) {
		this.sv_sensor1 = sv_sensor1;
	}
	public String getSv_createtime() {
		return sv_createtime;
	}
	public void setSv_createtime(String sv_createtime) {
		this.sv_createtime = sv_createtime;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
}
