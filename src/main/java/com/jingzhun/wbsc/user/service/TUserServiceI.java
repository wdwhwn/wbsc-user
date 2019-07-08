package com.jingzhun.wbsc.user.service;
import com.jingzhun.wbsc.user.entity.TUserEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface TUserServiceI extends CommonService {
	
 	public void delete(TUserEntity entity) throws Exception;
 	
 	public Serializable save(TUserEntity entity) throws Exception;
 	
 	public void saveOrUpdate(TUserEntity entity) throws Exception;
 	
}
