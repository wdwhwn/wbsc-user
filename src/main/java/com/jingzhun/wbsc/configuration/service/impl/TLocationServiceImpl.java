package com.jingzhun.wbsc.configuration.service.impl;

import com.jingzhun.wbsc.configuration.entity.TLocationEntity;
import com.jingzhun.wbsc.configuration.service.TLocationServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service("tLocationService")
@Transactional
public class TLocationServiceImpl extends CommonServiceImpl implements TLocationServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(TLocationEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(TLocationEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(TLocationEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}
 	
}