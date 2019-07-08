package com.jingzhun.wbsc.user.service.impl;

import com.jingzhun.wbsc.user.entity.TArticleEntity;
import com.jingzhun.wbsc.user.service.TArticleServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service("tArticleService")
@Transactional
public class TArticleServiceImpl extends CommonServiceImpl implements TArticleServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(TArticleEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(TArticleEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(TArticleEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}
 	
}