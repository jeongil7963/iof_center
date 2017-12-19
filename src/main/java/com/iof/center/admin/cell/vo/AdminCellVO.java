package com.iof.center.admin.cell.vo;

public class AdminCellVO {
	public int cell_idx;
	public String cell_createtime;
	public String cell_updatetime;
	public String cell_code;
	public String user_id;
	public int unit_idx;
	public int api_idx;
	public String cell_name;
	public int crop_idx;
	

	//검색 list
	public String crop_name;
	public String unit_name;
	public String region_name;
	
	public int raspberry_check;
	public int arduino_check;
	
	

	public String getCrop_name() {
		return crop_name;
	}
	public void setCrop_name(String crop_name) {
		this.crop_name = crop_name;
	}
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	public String getRegion_name() {
		return region_name;
	}
	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
	public int getCrop_idx() {
		return crop_idx;
	}
	public void setCrop_idx(int crop_idx) {
		this.crop_idx = crop_idx;
	}
	public String getCell_name() {
		return cell_name;
	}
	public void setCell_name(String cell_name) {
		this.cell_name = cell_name;
	}
	@Override
	public String toString() {
		return "AdminCellVO [cell_idx=" + cell_idx + ", cell_createtime=" + cell_createtime + ", cell_updatetime=" + cell_updatetime + ", cell_code="
				+ cell_code + ", user_id=" + user_id + ", unit_idx=" + unit_idx + ", api_idx=" + api_idx + "]";
	}
	public int getCell_idx() {
		return cell_idx;
	}
	public void setCell_idx(int cell_idx) {
		this.cell_idx = cell_idx;
	}
	public String getCell_createtime() {
		return cell_createtime;
	}
	public void setCell_createtime(String cell_createtime) {
		this.cell_createtime = cell_createtime;
	}
	public String getCell_updatetime() {
		return cell_updatetime;
	}
	public void setCell_updatetime(String cell_updatetime) {
		this.cell_updatetime = cell_updatetime;
	}
	public String getCell_code() {
		return cell_code;
	}
	public void setCell_code(String cell_code) {
		this.cell_code = cell_code;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getUnit_idx() {
		return unit_idx;
	}
	public void setUnit_idx(int unit_idx) {
		this.unit_idx = unit_idx;
	}
	public int getApi_idx() {
		return api_idx;
	}
	public void setApi_idx(int api_idx) {
		this.api_idx = api_idx;
	}
	public int getRaspberry_check() {
		return raspberry_check;
	}
	public void setRaspberry_check(int raspberry_check) {
		this.raspberry_check = raspberry_check;
	}
	public int getArduino_check() {
		return arduino_check;
	}
	public void setArduino_check(int arduino_check) {
		this.arduino_check = arduino_check;
	}
	
}