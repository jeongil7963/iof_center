package com.iof.center.admin.farming.vo;


public class AdminFarmingVo {
	private int frm_idx;
	private String frm_title;
	private String frm_contents;
	private String frm_createtime;
	private String frm_updatetime;
	private int frm_idx_array[] = new int [50];
	private String crop_idx;
	private String crop_name;

	public AdminFarmingVo() {
		super();
		
	}
	
	public AdminFarmingVo(int frm_idx, String frm_title, String frm_contents, String frm_createtime, String frm_updatetime,  int frm_idx_array[]) {
		super();
		this.frm_idx = frm_idx;
		this.frm_title = frm_title;
		this.frm_contents = frm_contents;
		this.frm_createtime = frm_createtime;
		this.frm_updatetime = frm_updatetime;
		this.frm_idx_array = frm_idx_array;
	}
	
	public String getCrop_name() {
		return crop_name;
	}

	public void setCrop_name(String crop_name) {
		this.crop_name = crop_name;
	}

	public String getCrop_idx() {
		return crop_idx;
	}

	public void setCrop_idx(String crop_idx) {
		this.crop_idx = crop_idx;
	}

	public int getFrm_idx() {
		return frm_idx;
	}

	public void setFrm_idx(int frm_idx) {
		this.frm_idx = frm_idx;
	}

	public String getFrm_title() {
		return frm_title;
	}

	public void setFrm_title(String frm_title) {
		this.frm_title = frm_title;
	}
	
	public String getFrm_contents() {
		return frm_contents;
	}

	public void setFrm_contents(String frm_contents) {
		this.frm_contents = frm_contents;
	}
	
	public int[] getFrm_idx_array() {
		return frm_idx_array;
	}

	public void setFrm_idx_array(int[] frm_idx_array) {
		this.frm_idx_array = frm_idx_array;
	}
	
	
	public String getFrm_createtime() {
		return frm_createtime;
	}

	public void setFrm_createtime(String frm_createtime) {
		this.frm_createtime = frm_createtime;
	}

	public String getFrm_updatetime() {
		return frm_updatetime;
	}

	public void setFrm_updatetime(String frm_updatetime) {
		this.frm_updatetime = frm_updatetime;
	}

	@Override
	public String toString() {
		return "AdminFarmingVO [frm_idx=" + frm_idx + ", frm_createtime=" + frm_createtime + ", frm_title="
				+ frm_title +"]" + "frm_contents = "+ frm_contents + "frm_updatetime =" + frm_updatetime;
	}

	
	
}
