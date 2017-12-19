package com.iof.center.admin.cell.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iof.center.admin.cell.vo.AdminCellVO;

@Repository("adminCellDao")
public class AdminCellDaoImpl implements AdminCellDao {
	
	@Autowired
	private SqlSession session;
	
	@Override
	public List<AdminCellVO> cell_search(int unit_idx) {
		return session.selectList("cell_search", unit_idx);
	}
	
	@Override
	public void insert_cell(AdminCellVO vo) {
		session.insert("insert_cell", vo);
	}
	
	@Override
	public int count_cell(String keyword) {
		return session.selectOne("count_cell",keyword);
	}
	
	@Override
	public List<AdminCellVO> getRead_cell(int page, String keyword) {
		Map<String, Object> map = new HashMap<String, Object>();
		page = (page - 1) * 10;
		map.put("page", page);
		map.put("keyword", keyword);
		return session.selectList("getRead_cell", map);
	}
	
	@Override
	public void delete_cell(int cell_idx) {
		session.delete("delete_cell", cell_idx);
	}
	
	@Override
	public AdminCellVO get_cell(AdminCellVO vo) {
		return session.selectOne("get_cell", vo);
	}
	
	@Override
	public void update_cell(AdminCellVO vo) {
		session.update("update_cell", vo);
	}
	
	@Override
	public void update_cell2(AdminCellVO vo) {
		session.update("update_cell2", vo);
	}
	
	@Override
	public void cell_ras_update(AdminCellVO vo) {
		session.update("cell_ras_update", vo);
	}
	
	@Override
	public List<AdminCellVO> adminCellList() {
		return session.selectList("cell_List");
	}
	
	@Override
	public int count_user_cell(String keyword, String user_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);
		map.put("user_id", user_id);
		return session.selectOne("count_user_cell", map);
	}
	
	@Override
	public List<AdminCellVO> getRead_user_cell(int page, String keyword, String user_id){
		Map<String, Object> map = new HashMap<String, Object>();
		page = (page - 1) * 10;
		map.put("page", page);
		map.put("keyword", keyword);
		map.put("user_id", user_id);
		return session.selectList("getRead_user_cell", map);

	}
	
	@Override
	public List<AdminCellVO> cell_select_List(int unit_idx) {
		return session.selectList("cell_select_List", unit_idx);
	}
	
	@Override
	public List<AdminCellVO> cell_detail_List(int unit_idx) {
		return session.selectList("cell_detail_List", unit_idx);
	}
	
	@Override
	public void cell_ard_update(AdminCellVO vo) {
		session.update("cell_ard_update", vo);
	}
	
	@Override
	public List<AdminCellVO> ard_cell_select_List(int unit_idx) {
		return session.selectList("ard_cell_select_List", unit_idx);
	}
}
