package com.jingzhun.wbsc.content.service.impl;

import com.jingzhun.wbsc.content.entity.TContentEntity;
import com.jingzhun.wbsc.content.service.TContentServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service("tContentService")
@Transactional
public class TContentServiceImpl extends CommonServiceImpl implements TContentServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(TContentEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(TContentEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(TContentEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}
 	
}