package com.jingzhun.wbsc.vip.service;
import com.jingzhun.wbsc.vip.entity.TVipEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface TVipServiceI extends CommonService {
	
 	public void delete(TVipEntity entity) throws Exception;
 	
 	public Serializable save(TVipEntity entity) throws Exception;
 	
 	public void saveOrUpdate(TVipEntity entity) throws Exception;
 	
}
