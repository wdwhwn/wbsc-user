package com.jingzhun.wbsc.img.service;
import com.jingzhun.wbsc.img.entity.TImageEntity;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface TImageServiceI extends CommonService{
	
 	public void delete(TImageEntity entity) throws Exception;
 	
 	public Serializable save(TImageEntity entity) throws Exception;
 	
 	public void saveOrUpdate(TImageEntity entity) throws Exception;


    List<Map<String,Object>> getSelectAll(Map<String, Object> conditionMap, DataGrid dataGrid);
}
