package com.iof.center.admin.device.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iof.center.admin.arduino.vo.AdminArduinoVo;
import com.iof.center.admin.device.dao.AdminDeviceDao;
import com.iof.center.admin.raspberry.vo.AdminRaspberryVo;
import com.iof.center.admin.weather.vo.AdminWeatherVo;


@Service("adminDeviceService")
public class AdminDeviceService {

	@Autowired
	private AdminDeviceDao dao;
	
	/*라즈베리*/
	public int count_raspberry(String keyword) {
		System.out.println("Raspberry service / count_raspberry");
		return dao.count_raspberry(keyword);
	}
	
	public List<AdminRaspberryVo> getRead_raspberry(int page, String keyword){
		return dao.getRead_raspberry(page, keyword);
	}
	
	/*아두이노*/
	public int count_arduino(String keyword) {
		System.out.println("arduino service / count_raspberry");
		return dao.count_arduino(keyword);
	}
	
	public List<AdminArduinoVo> getRead_arduino(int page, String keyword){
		return dao.getRead_arduino(page, keyword);
	}
	
	
	/*기상장비*/
	public int count_weather(String keyword) {
		System.out.println("arduino service / count_raspberry");
		return dao.count_weather(keyword);
	}
	
	public List<AdminWeatherVo> getRead_weather(int page, String keyword){
		return dao.getRead_weather(page, keyword);
	}
	
	public AdminRaspberryVo adminrasCellIdxRead(AdminRaspberryVo raspberry) {
		System.out.println("adminrasCellIdxRead adminRasIdxRead");
		return dao.adminrasCellIdxRead(raspberry);
	}
	
// ------------------------페이징----------------------------------------
	
	/*라즈베리*/
	
	public int adminraspberryInsert(AdminRaspberryVo raspberry) {
		System.out.println("AdminraspberryService adminraspberryInsert");
		return dao.adminraspberryInsert(raspberry);
	}
	
	public int adminraspberryDelete(AdminRaspberryVo raspberry) {
		System.out.println("AdminraspberryService adminraspberryDelete");
		return dao.adminraspberryDelete(raspberry);
	}
	
	public AdminRaspberryVo adminRasIdxRead(AdminRaspberryVo raspberry) {
		System.out.println("AdminraspberryService adminRasIdxRead");
		return dao.adminRasIdxRead(raspberry);
	}
	
	public List<AdminRaspberryVo> adminRasCheckList(){
		return dao.adminRasCheckList();
	}
	
	public int adminraspberryUpdate(AdminRaspberryVo raspberry) {
		System.out.println("AdminraspberryDao adminraspberryUpdate");
		return dao.adminraspberryUpdate(raspberry);
	}
	
	public AdminRaspberryVo adminRasIdxMax() {
		System.out.println("AdminraspberryService adminRasIdxMax");
		return dao.adminRasIdxMax();
	}
	
	
	/*아두이노*/
	
	public int adminArduinoInsert(AdminArduinoVo Arduino) {
		System.out.println("AdminArduinoService adminArduinoInsert");
		return dao.adminArduinoInsert(Arduino);
	}
	
	public int adminArduinoDelete(AdminArduinoVo Arduino) {
		return dao.adminArduinoDelete(Arduino);
	}
	
	public AdminArduinoVo adminArdIdxRead(AdminArduinoVo Arduino) {
		return dao.adminArdIdxRead(Arduino);
	}
	
	public AdminArduinoVo adminArdIdxMax() {
		System.out.println("AdminArduinoService adminArdIdxMax");
		return dao.adminArdIdxMax();
	}
	
	public int adminarduinoUpdate(AdminArduinoVo Arduino) {
		return dao.adminarduinoUpdate(Arduino);
	}
	
	/*기상장비*/
	
	public int adminWeatherInsert( AdminWeatherVo Weather) {
		return dao.adminWeatherInsert(Weather);
	}
	
	public int adminWeatherDelete(AdminWeatherVo Weather) {
		return dao.adminWeatherDelete(Weather);
	}
	
	public AdminWeatherVo adminWeatherIdxRead(AdminWeatherVo Weather) {
		return dao.adminWeatherIdxRead(Weather);
	}
	
	public AdminWeatherVo adminWeatherIdxMax() throws Exception{
		return dao.adminWeatherIdxMax();
	}
	
	public int adminWeatherUpdate(AdminWeatherVo Weather) {
		return dao.adminWeatherUpdate(Weather);
	}
	
	
}
