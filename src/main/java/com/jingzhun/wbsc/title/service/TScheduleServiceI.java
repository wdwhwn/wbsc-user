package com.jingzhun.wbsc.title.service;
import com.jingzhun.wbsc.title.entity.TScheduleEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface TScheduleServiceI extends CommonService{
	
 	public void delete(TScheduleEntity entity) throws Exception;
 	
 	public Serializable save(TScheduleEntity entity) throws Exception;
 	
 	public void saveOrUpdate(TScheduleEntity entity) throws Exception;
 	
}
