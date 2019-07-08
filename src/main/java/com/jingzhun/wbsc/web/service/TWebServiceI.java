package com.jingzhun.wbsc.web.service;
import com.jingzhun.wbsc.web.entity.TWebEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface TWebServiceI extends CommonService {
	
 	public void delete(TWebEntity entity) throws Exception;
 	
 	public Serializable save(TWebEntity entity) throws Exception;
 	
 	public void saveOrUpdate(TWebEntity entity) throws Exception;
 	
}
