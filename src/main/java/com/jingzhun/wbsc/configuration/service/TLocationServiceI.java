package com.jingzhun.wbsc.configuration.service;
import com.jingzhun.wbsc.configuration.entity.TLocationEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface TLocationServiceI extends CommonService {
	
 	public void delete(TLocationEntity entity) throws Exception;
 	
 	public Serializable save(TLocationEntity entity) throws Exception;
 	
 	public void saveOrUpdate(TLocationEntity entity) throws Exception;
 	
}
