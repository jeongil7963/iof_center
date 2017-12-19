package com.iof.center.admin.cell.service;

import java.util.List;
import com.iof.center.admin.cell.vo.AdminCellVO;

public interface AdminCellService {

	List<AdminCellVO> cell_search(int unit_idx);
	
	void insert_cell(AdminCellVO vo);
	
	int count_cell(String keyword);
	
	List<AdminCellVO> getRead_cell(int page, String keyword);
	
	void delete_cell(int cell_idx);
	
	AdminCellVO get_cell(AdminCellVO vo);

	void update_cell(AdminCellVO vo);
	
	void update_cell2(AdminCellVO vo);
	
	List<AdminCellVO> adminCellList();
		
	int count_user_cell(String keyword, String user_id);
	
	List<AdminCellVO> getRead_user_cell(int page, String keyword, String user_id);
	
	List<AdminCellVO> cell_select_List(int unit_idx);
	
	public void cell_ras_update(AdminCellVO vo);
	
	public List<AdminCellVO> cell_detail_List(int unit_idx);
	
	public void cell_ard_update(AdminCellVO vo);
	
	public List<AdminCellVO> ard_cell_select_List(int unit_idx);
}
