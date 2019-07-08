package com.jingzhun.wbsc.user.service.impl;

import com.jingzhun.wbsc.user.entity.TUserEntity;
import com.jingzhun.wbsc.user.service.TUserServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service("tUserService")
@Transactional
public class TUserServiceImpl extends CommonServiceImpl implements TUserServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(TUserEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(TUserEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(TUserEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}
 	
}