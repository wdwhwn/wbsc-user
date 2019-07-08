package com.jingzhun.wbsc.img.service;
import com.jingzhun.wbsc.img.entity.TImageEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface TImageServiceI extends CommonService{
	
 	public void delete(TImageEntity entity) throws Exception;
 	
 	public Serializable save(TImageEntity entity) throws Exception;
 	
 	public void saveOrUpdate(TImageEntity entity) throws Exception;
 	
}
