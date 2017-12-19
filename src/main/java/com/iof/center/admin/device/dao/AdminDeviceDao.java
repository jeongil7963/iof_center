package com.iof.center.admin.device.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iof.center.admin.arduino.vo.AdminArduinoVo;
import com.iof.center.admin.raspberry.vo.AdminRaspberryVo;

import com.iof.center.admin.weather.vo.AdminWeatherVo;

@Repository("adminDeviceDao")
public class AdminDeviceDao {

	
	@Autowired
	private SqlSession session;
	
	//라즈베리 paging
	public int count_raspberry(String keyword) {
		return session.selectOne("count_raspberry", keyword);
	}
	
	public List<AdminRaspberryVo> getRead_raspberry(int page, String keyword) {
		Map<String, Object> map = new HashMap<String, Object>();
		page = (page - 1) * 10;
		map.put("page", page);
		map.put("keyword", keyword);
		return session.selectList("getRead_raspberry", map);
	}
	
	//아두이노 paging
	public int count_arduino(String keyword) {
		return session.selectOne("count_arduino", keyword);
	}
	
	public List<AdminArduinoVo> getRead_arduino(int page, String keyword) {
		Map<String, Object> map = new HashMap<String, Object>();
		page = (page - 1) * 10;
		map.put("page", page);
		map.put("keyword", keyword);
		return session.selectList("getRead_arduino", map);
	}
	
	//기상정보 paing
	public int count_weather(String keyword) {
		return session.selectOne("count_weather", keyword);
	}
	
	public List<AdminWeatherVo> getRead_weather(int page, String keyword) {
		Map<String, Object> map = new HashMap<String, Object>();
		page = (page - 1) * 10;
		map.put("page", page);
		map.put("keyword", keyword);
		return session.selectList("getRead_weather", map);
	}
	
	
	//-----------------페이징--
	/*라즈베리*/
	public int adminraspberryInsert(AdminRaspberryVo raspberry) {
		System.out.println("DeviceDao adminraspberryInsert");
		return session.insert("raspberryInsert",raspberry);
	}
	
	public int adminraspberryDelete(AdminRaspberryVo raspberry) {
		System.out.println("DeviceDao adminraspberryDelete");
		return session.delete("rasdel", raspberry);
	}
	
	public AdminRaspberryVo adminRasIdxRead(AdminRaspberryVo raspberry) {
		System.out.println("DeviceDao adminRasIdxRead");
		return session.selectOne("rasIdxRead", raspberry);
	}
	
	public List<AdminRaspberryVo> adminRasCheckList(){
		return session.selectList("adminRasCheckList");
	}
	
	public int adminraspberryUpdate(AdminRaspberryVo raspberry) {
		System.out.println("DeviceDao adminraspberryUpdate");
		return session.update("raspberryUpdate", raspberry);
	}
	
	public AdminRaspberryVo adminrasCellIdxRead(AdminRaspberryVo raspberry) {
		System.out.println("DeviceDao adminRasIdxRead");
		return session.selectOne("rasCellIdxRead", raspberry);
	}
	
	public AdminRaspberryVo adminRasIdxMax() {
		System.out.println("AdminraspberryDao adminRasIdxMax");
		return session.selectOne("adminRasIdxMax");
	}
	
	/*아두이노*/
	public int adminArduinoInsert(AdminArduinoVo Arduino) {
		return session.insert("arduinoInsert", Arduino);
	}
	
	public int adminArduinoDelete(AdminArduinoVo Arduino) {
		return session.delete("arddel", Arduino);
	}
	
	public AdminArduinoVo adminArdIdxRead(AdminArduinoVo Arduino) {
		return session.selectOne("ardIdxRead", Arduino);
	}
	
	public AdminArduinoVo adminArdIdxMax() {
		System.out.println("AdminArduinoDao adminArdIdxMax");
		return session.selectOne("adminArdIdxMax");
	}
	
	public int adminarduinoUpdate(AdminArduinoVo Arduino) {
		return session.update("arduinoUpdate", Arduino);
	}
	
	/*기상장비*/
	
	public int adminWeatherInsert( AdminWeatherVo Weather) {
		return session.insert("weatherInsert", Weather);
	}
	
	public int adminWeatherDelete(AdminWeatherVo Weather) {
		return session.delete("weatherdel", Weather);
	}
	
	public AdminWeatherVo adminWeatherIdxRead(AdminWeatherVo Weather) {
		return session.selectOne("weatherIdxRead", Weather);
	}
	
	public AdminWeatherVo adminWeatherIdxMax() {
		return session.selectOne("adminWeatherIdxMax");
	}
	
	public int adminWeatherUpdate(AdminWeatherVo Weather) {
		return session.update("weatherUpdate", Weather);
	}
	
}
