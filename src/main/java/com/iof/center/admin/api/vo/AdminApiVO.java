package com.iof.center.admin.api.vo;

public class AdminApiVO {
	public int api_idx;
	public String api_key;
	public String api_channel;
	
	
	
	public int getApi_idx() {
		return api_idx;
	}



	public void setApi_idx(int api_idx) {
		this.api_idx = api_idx;
	}



	public String getApi_key() {
		return api_key;
	}



	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}



	public String getApi_channel() {
		return api_channel;
	}



	public void setApi_channel(String api_channel) {
		this.api_channel = api_channel;
	}



	@Override
	public String toString() {
		return "AdminApiVO [api_idx=" + api_idx + ", api_key=" + api_key + ", api_channel=" + api_channel + "]";
	}
	
	
}
