package com.jingzhun.wbsc.title.service;
import com.jingzhun.wbsc.title.entity.TScheduleParamEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface TScheduleParamServiceI extends CommonService{
	
 	public void delete(TScheduleParamEntity entity) throws Exception;
 	
 	public Serializable save(TScheduleParamEntity entity) throws Exception;
 	
 	public void saveOrUpdate(TScheduleParamEntity entity) throws Exception;
 	
}
