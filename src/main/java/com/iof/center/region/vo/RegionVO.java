package com.iof.center.region.vo;
/*
 * 지역 정보 VO
 */
public class RegionVO {
	
	private int region_idx;			// index 값
	private String region_name;		// 지역이름
	private String region_addr;		// 지역주소
	private String region_code;		// 지역코드
	
	
	public RegionVO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RegionVO(int region_idx, String region_name, String region_addr, String region_code) {
		super();
		this.region_idx = region_idx;
		this.region_name = region_name;
		this.region_addr = region_addr;
		this.region_code = region_code;
	}


	public int getRegion_idx() {
		return region_idx;
	}


	public void setRegion_idx(int region_idx) {
		this.region_idx = region_idx;
	}


	public String getRegion_name() {
		return region_name;
	}


	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}


	public String getRegion_addr() {
		return region_addr;
	}


	public void setRegion_addr(String region_addr) {
		this.region_addr = region_addr;
	}


	public String getRegion_code() {
		return region_code;
	}


	public void setRegion_code(String region_code) {
		this.region_code = region_code;
	}


	@Override
	public String toString() {
		return "RegionVO [region_idx=" + region_idx + ", region_name=" + region_name + ", region_addr="
				+ region_addr + ", region_code=" + region_code + "]";
	}
	
	
}
