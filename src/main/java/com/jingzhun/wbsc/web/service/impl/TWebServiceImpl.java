package com.jingzhun.wbsc.web.service.impl;

import com.jingzhun.wbsc.web.entity.TWebEntity;
import com.jingzhun.wbsc.web.service.TWebServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service("tWebService")
@Transactional
public class TWebServiceImpl extends CommonServiceImpl implements TWebServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(TWebEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(TWebEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(TWebEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}
 	
}