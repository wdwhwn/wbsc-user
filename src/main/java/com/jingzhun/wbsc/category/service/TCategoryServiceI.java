package com.jingzhun.wbsc.category.service;
import com.jingzhun.wbsc.category.entity.TCategoryEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface TCategoryServiceI extends CommonService{
	
 	public void delete(TCategoryEntity entity) throws Exception;
 	
 	public Serializable save(TCategoryEntity entity) throws Exception;
 	
 	public void saveOrUpdate(TCategoryEntity entity) throws Exception;
 	
}
