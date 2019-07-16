package com.jingzhun.wbsc.title.service;
import com.jingzhun.wbsc.title.entity.TArticleEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface TArticleServiceI extends CommonService{
	
 	public void delete(TArticleEntity entity) throws Exception;
 	
 	public Serializable save(TArticleEntity entity) throws Exception;
 	
 	public void saveOrUpdate(TArticleEntity entity) throws Exception;
 	
}
