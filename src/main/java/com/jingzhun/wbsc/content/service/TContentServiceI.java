package com.jingzhun.wbsc.content.service;
import com.jingzhun.wbsc.content.entity.TContentEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface TContentServiceI extends CommonService {
	
 	public void delete(TContentEntity entity) throws Exception;
 	
 	public Serializable save(TContentEntity entity) throws Exception;
 	
 	public void saveOrUpdate(TContentEntity entity) throws Exception;
 	
}
