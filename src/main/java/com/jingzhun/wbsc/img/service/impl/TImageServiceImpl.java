package com.jingzhun.wbsc.img.service.impl;
import com.jingzhun.wbsc.img.service.TImageServiceI;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.jingzhun.wbsc.img.entity.TImageEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.io.Serializable;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;

import org.jeecgframework.minidao.util.FreemarkerParseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.jeecgframework.core.util.ResourceUtil;

@Service("tImageService")
@Transactional
public class TImageServiceImpl extends CommonServiceImpl implements TImageServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(TImageEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(TImageEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(TImageEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}

	@Override
	public List<Map<String, Object>> getSelectAll(Map<String, Object> conditionMap, DataGrid dataGrid) {
		List<Map<String, Object>> resultMapList=new ArrayList<Map<String,Object>>();
		//		logger.error("id为："+id);
		String sql=null;
		sql="SELECT\n" +
				"\tid,\n" +
				"\tuser_key userKey,\n" +
				"\turl url,\n" +
				"\tweb_id webId,\n" +
				"\tweb_username webUsername,\n" +
				"\tweb_img_name webImgName\n" +
				"FROM\n" +
				"\tt_image\n" +
				"WHERE\n" +
				"\tweb_id = '" +conditionMap.get("webId")+"' "+
				"AND web_username = '"+conditionMap.get("webUsername")+"' ";



		if(dataGrid!=null){
		    List<Map<String, Object>> totalList = super.findForJdbc("select count(0) total from (" + sql + ")t");
		    Integer total = Integer.valueOf(totalList.get(0).get("total") + "");
		    dataGrid.setTotal(total);
		    if(total>0){
		        resultMapList= super.findForJdbcParam(sql+"order by id" , dataGrid.getPage(), dataGrid.getRows());
		    }
		}else{
		    resultMapList=this.findForJdbc(sql+"order by id");
		}
		return resultMapList;
	}

}