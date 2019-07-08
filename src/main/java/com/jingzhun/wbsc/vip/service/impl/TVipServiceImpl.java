package com.jingzhun.wbsc.vip.service.impl;

import com.jingzhun.wbsc.vip.entity.TVipEntity;
import com.jingzhun.wbsc.vip.service.TVipServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service("tVipService")
@Transactional
public class TVipServiceImpl extends CommonServiceImpl implements TVipServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(TVipEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(TVipEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(TVipEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}
 	
}