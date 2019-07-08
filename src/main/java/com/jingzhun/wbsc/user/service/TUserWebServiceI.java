package com.jingzhun.wbsc.user.service;
import com.jingzhun.wbsc.user.entity.TUserWebEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface TUserWebServiceI extends CommonService{
	
 	public void delete(TUserWebEntity entity) throws Exception;
 	
 	public Serializable save(TUserWebEntity entity) throws Exception;
 	
 	public void saveOrUpdate(TUserWebEntity entity) throws Exception;
 	
}
