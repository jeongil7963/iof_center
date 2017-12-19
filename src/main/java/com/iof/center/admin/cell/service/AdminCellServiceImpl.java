package com.iof.center.admin.cell.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iof.center.admin.cell.dao.AdminCellDao;
import com.iof.center.admin.cell.vo.AdminCellVO;

@Service("adminCellService")
public class AdminCellServiceImpl implements AdminCellService {
	
	@Resource(name="adminCellDao")
	private AdminCellDao dao;
	
	@Override
	public List<AdminCellVO> cell_search(int unit_idx) {
		return dao.cell_search(unit_idx);
	}
	
	@Override
	public void insert_cell(AdminCellVO vo) {
		dao.insert_cell(vo);
	}
	
	@Override
	public int count_cell(String keyword) {
		return dao.count_cell(keyword);
	}
	
	@Override
	public List<AdminCellVO> getRead_cell(int page, String keyword){
		return dao.getRead_cell(page, keyword);
	}
	
	@Override
	public void delete_cell(int cell_idx) {
		dao.delete_cell(cell_idx);
	}
	
	@Override
	public AdminCellVO get_cell(AdminCellVO vo) {
		return dao.get_cell(vo);
	}
	
	@Override
	public void update_cell(AdminCellVO vo) {
		dao.update_cell(vo);
	}
	
	@Override
	public void update_cell2(AdminCellVO vo) {
		dao.update_cell2(vo);
	}
	
	@Override
	public void cell_ras_update(AdminCellVO vo) {
		dao.cell_ras_update(vo);
	}
	
	@Override
	public List<AdminCellVO> adminCellList() {
		return dao.adminCellList();
	}
	
	@Override
	public int count_user_cell(String keyword, String user_id) {
		return dao.count_user_cell(keyword, user_id);
	}
	
	@Override
	public List<AdminCellVO> getRead_user_cell(int page, String keyword, String user_id) {
		return dao.getRead_user_cell(page, keyword, user_id);

	}
	
	@Override
	public List<AdminCellVO> cell_select_List(int unit_idx) {
		return dao.cell_select_List(unit_idx);
	}
	
	@Override
	public List<AdminCellVO> cell_detail_List(int unit_idx) {
		return dao.cell_detail_List(unit_idx);
	}
	
	@Override
	public void cell_ard_update(AdminCellVO vo) {
		dao.cell_ard_update(vo);
	}
	
	@Override
	public List<AdminCellVO> ard_cell_select_List(int unit_idx) {
		return dao.ard_cell_select_List(unit_idx);
	}
}
