package com.jingzhun.wbsc.title.controller;
import com.jingzhun.wbsc.title.entity.TScheduleParamEntity;
import com.jingzhun.wbsc.title.service.TScheduleParamServiceI;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import java.util.HashMap;
import org.jeecgframework.core.util.ExceptionUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**   
 * @Title: Controller  
 * @Description: t_schedule_param
 * @author onlineGenerator
 * @date 2019-07-13 12:06:33
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/tScheduleParamController")
public class TScheduleParamController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(TScheduleParamController.class);

	@Autowired
	private TScheduleParamServiceI tScheduleParamService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * t_schedule_param列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jingzhun/wbsc/title/tScheduleParamList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(TScheduleParamEntity tScheduleParam,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TScheduleParamEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tScheduleParam, request.getParameterMap());
		try{
		//自定义追加查询条件
		
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.tScheduleParamService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除t_schedule_param
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(TScheduleParamEntity tScheduleParam, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		tScheduleParam = systemService.getEntity(TScheduleParamEntity.class, tScheduleParam.getId());
		message = "t_schedule_param删除成功";
		try{
			tScheduleParamService.delete(tScheduleParam);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "t_schedule_param删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除t_schedule_param
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "t_schedule_param删除成功";
		try{
			for(String id:ids.split(",")){
				TScheduleParamEntity tScheduleParam = systemService.getEntity(TScheduleParamEntity.class, 
				Integer.parseInt(id)
				);
				tScheduleParamService.delete(tScheduleParam);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "t_schedule_param删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加t_schedule_param
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(TScheduleParamEntity tScheduleParam, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "t_schedule_param添加成功";
		try{
			tScheduleParamService.save(tScheduleParam);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "t_schedule_param添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新t_schedule_param
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(TScheduleParamEntity tScheduleParam, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "t_schedule_param更新成功";
		TScheduleParamEntity t = tScheduleParamService.get(TScheduleParamEntity.class, tScheduleParam.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(tScheduleParam, t);
			tScheduleParamService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "t_schedule_param更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * t_schedule_param新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(TScheduleParamEntity tScheduleParam, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tScheduleParam.getId())) {
			tScheduleParam = tScheduleParamService.getEntity(TScheduleParamEntity.class, tScheduleParam.getId());
			req.setAttribute("tScheduleParam", tScheduleParam);
		}
		return new ModelAndView("com/jingzhun/wbsc/title/tScheduleParam-add");
	}
	/**
	 * t_schedule_param编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(TScheduleParamEntity tScheduleParam, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tScheduleParam.getId())) {
			tScheduleParam = tScheduleParamService.getEntity(TScheduleParamEntity.class, tScheduleParam.getId());
			req.setAttribute("tScheduleParam", tScheduleParam);
		}
		return new ModelAndView("com/jingzhun/wbsc/title/tScheduleParam-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","tScheduleParamController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(TScheduleParamEntity tScheduleParam,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(TScheduleParamEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tScheduleParam, request.getParameterMap());
		List<TScheduleParamEntity> tScheduleParams = this.tScheduleParamService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"t_schedule_param");
		modelMap.put(NormalExcelConstants.CLASS,TScheduleParamEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("t_schedule_param列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,tScheduleParams);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(TScheduleParamEntity tScheduleParam,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"t_schedule_param");
    	modelMap.put(NormalExcelConstants.CLASS,TScheduleParamEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("t_schedule_param列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<TScheduleParamEntity> listTScheduleParamEntitys = ExcelImportUtil.importExcel(file.getInputStream(),TScheduleParamEntity.class,params);
				for (TScheduleParamEntity tScheduleParam : listTScheduleParamEntitys) {
					tScheduleParamService.save(tScheduleParam);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	
	
}
