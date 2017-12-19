package com.iof.center.admin.weather.vo;

public class AdminWeatherVo {
	
	private int weather_idx;
	private int unit_idx;
	private int region_idx;
	private int api_idx;
	private String weather_name;
	private String weather_createtime;
	private String weather_updatetime;
	private String user_id;
	private int weather_code;
	
	public AdminWeatherVo() {
		super();
	}
	public int getWeather_idx() {
		return weather_idx;
	}
	public void setWeather_idx(int weather_idx) {
		this.weather_idx = weather_idx;
	}
	public String getWeather_name() {
		return weather_name;
	}
	public void setWeather_name(String weather_name) {
		this.weather_name = weather_name;
	}
	public String getWeather_createtime() {
		return weather_createtime;
	}
	public void setWeather_createtime(String weather_createtime) {
		this.weather_createtime = weather_createtime;
	}
	public int getUnit_idx() {
		return unit_idx;
	}
	public void setUnit_idx(int unit_idx) {
		this.unit_idx = unit_idx;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getWeather_updatetime() {
		return weather_updatetime;
	}
	public void setWeather_updatetime(String weather_updatetime) {
		this.weather_updatetime = weather_updatetime;
	}
	public int getRegion_idx() {
		return region_idx;
	}
	public void setRegion_idx(int region_idx) {
		this.region_idx = region_idx;
	}
	public int getApi_idx() {
		return api_idx;
	}
	public void setApi_idx(int api_idx) {
		this.api_idx = api_idx;
	}
	public int getWeather_code() {
		return weather_code;
	}
	public void setWeather_code(int weather_code) {
		this.weather_code = weather_code;
	}
	
}
