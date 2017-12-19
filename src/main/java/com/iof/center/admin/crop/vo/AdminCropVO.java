package com.iof.center.admin.crop.vo;

public class AdminCropVO {
	public int crop_idx;
	public String crop_name;
	public String crop_timing;
	public String crop_soil;
	public String region_idx;
	public String unit_idx;
	public String user_id;
	public String unit_name;
	
	
	
	
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	public int getCrop_idx() {
		return crop_idx;
	}
	public void setCrop_idx(int crop_idx) {
		this.crop_idx = crop_idx;
	}
	public String getCrop_name() {
		return crop_name;
	}
	public void setCrop_name(String crop_name) {
		this.crop_name = crop_name;
	}
	public String getCrop_timing() {
		return crop_timing;
	}
	public void setCrop_timing(String crop_timing) {
		this.crop_timing = crop_timing;
	}
	public String getCrop_soil() {
		return crop_soil;
	}
	public void setCrop_soil(String crop_soil) {
		this.crop_soil = crop_soil;
	}
	public String getRegion_idx() {
		return region_idx;
	}
	public void setRegion_idx(String region_idx) {
		this.region_idx = region_idx;
	}
	public String getUnit_idx() {
		return unit_idx;
	}
	public void setUnit_idx(String unit_idx) {
		this.unit_idx = unit_idx;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "AdminCropVO [crop_name=" + crop_name + ", crop_timing=" + crop_timing + ", crop_soil=" + crop_soil + ", region_idx=" + region_idx
				+ ", unit_idx=" + unit_idx + ", user_id=" + user_id + "]";
	}
	
	
}
