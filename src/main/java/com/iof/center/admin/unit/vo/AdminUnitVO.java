package com.iof.center.admin.unit.vo;

public class AdminUnitVO {
	public int unit_idx;
	public String unit_manager;
	public String unit_name;
	public int unit_cell_number;
	public int unit_cell_row;
	public int unit_cell_col;
	public int unit_cell_use_cnt;
	public String unit_createtime;
	public String unit_updatetime;
	public String unit_code;
	public String user_id;
	public int region_idx;
	public int weather_check;
	
	
	
	public int getUnit_idx() {
		return unit_idx;
	}
	public void setUnit_idx(int unit_idx) {
		this.unit_idx = unit_idx;
	}
	public String getUnit_manager() {
		return unit_manager;
	}
	public void setUnit_manager(String unit_manager) {
		this.unit_manager = unit_manager;
	}
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	public int getUnit_cell_number() {
		return unit_cell_number;
	}
	public void setUnit_cell_number(int unit_cell_number) {
		this.unit_cell_number = unit_cell_number;
	}
	public int getUnit_cell_row() {
		return unit_cell_row;
	}
	public void setUnit_cell_row(int unit_cell_row) {
		this.unit_cell_row = unit_cell_row;
	}
	public int getUnit_cell_col() {
		return unit_cell_col;
	}
	public void setUnit_cell_col(int unit_cell_col) {
		this.unit_cell_col = unit_cell_col;
	}
	public int getUnit_cell_use_cnt() {
		return unit_cell_use_cnt;
	}
	public void setUnit_cell_use_cnt(int unit_cell_use_cnt) {
		this.unit_cell_use_cnt = unit_cell_use_cnt;
	}
	public String getUnit_createtime() {
		return unit_createtime;
	}
	public void setUnit_createtime(String unit_createtime) {
		this.unit_createtime = unit_createtime;
	}
	public String getUnit_updatetime() {
		return unit_updatetime;
	}
	public void setUnit_updatetime(String unit_updatetime) {
		this.unit_updatetime = unit_updatetime;
	}
	public String getUnit_code() {
		return unit_code;
	}
	public void setUnit_code(String unit_code) {
		this.unit_code = unit_code;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getRegion_idx() {
		return region_idx;
	}
	public void setRegion_idx(int region_idx) {
		this.region_idx = region_idx;
	}
	public int getWeather_check() {
		return weather_check;
	}
	public void setWeather_check(int weather_check) {
		this.weather_check = weather_check;
	}
	
	@Override
	public String toString() {
		return "AdminUnitVO [unit_manager=" + unit_manager + ", unit_name=" + unit_name + ", unit_cell_number=" + unit_cell_number + ", unit_cell_row="
				+ unit_cell_row + ", unit_cell_col=" + unit_cell_col + ", unit_cell_use_cnt=" + unit_cell_use_cnt + ", unit_createtime=" + unit_createtime
				+ ", unit_updatetime=" + unit_updatetime + ", unit_code=" + unit_code + ", user_id=" + user_id + ", region_idx=" + region_idx + "]";
	}
	
	
}
