package com.iof.center.restful.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iof.center.restful.dao.RestfulDao;
import com.iof.center.restful.vo.Cell_Insert_VO;


@Service("RestfulService")
public class RestfulServiceImpl implements RestfulService {

	@Resource(name="RestfulDao")
	private RestfulDao dao;
	
	@Override
	public void insert_rest (Cell_Insert_VO vo) {
		dao.insert_rest(vo);
	}
	
}

