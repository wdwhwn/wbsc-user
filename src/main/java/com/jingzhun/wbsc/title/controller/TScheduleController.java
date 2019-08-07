package com.jingzhun.wbsc.title.controller;
import com.jingzhun.wbsc.login.config.CookieJudge;
import com.jingzhun.wbsc.schedule.QuartzManager;
import com.jingzhun.wbsc.title.entity.TScheduleEntity;
import com.jingzhun.wbsc.title.service.TScheduleServiceI;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jingzhun.wbsc.web.entity.TWebEntity;
import org.quartz.SchedulerException;
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
 * @Description: t_schedule
 * @author onlineGenerator
 * @date 2019-07-24 11:43:49
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/tScheduleController")
public class TScheduleController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(TScheduleController.class);

	@Autowired
	private TScheduleServiceI tScheduleService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private QuartzManager quartzManager;

	/**
	 * t_schedule列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Map<String,String> cookies = (Map<String,String>)session.getAttribute("cookies");
		TWebEntity tWebEntity=(TWebEntity)session.getAttribute("web");
		if(tWebEntity==null||!CookieJudge.chooseContentPush(tWebEntity.getIdentification(),cookies)){
			return new ModelAndView("com/jingzhun/wbsc/title/error");
		}
		return new ModelAndView("com/jingzhun/wbsc/title/tScheduleList");
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
	public void datagrid(TScheduleEntity tSchedule,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TScheduleEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSchedule, request.getParameterMap());
		try{
		//自定义追加查询条件
		
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.tScheduleService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 暂停t_schedule
	 *
	 * @return
	 */
	@RequestMapping(params = "pause")
	@ResponseBody
	public AjaxJson pause(TScheduleEntity tSchedule, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		tSchedule = systemService.getEntity(TScheduleEntity.class, tSchedule.getId());
		message = tSchedule.getScheduleJobname()+"暂停成功";
        String scheduleJobname = tSchedule.getScheduleJobname();
		quartzManager.pauseJob(scheduleJobname,scheduleJobname);
		j.setMsg(message);
		return j;
	}

	/**
	 * 重启t_schedule
	 *
	 * @return
	 */
	@RequestMapping(params = "restart")
	@ResponseBody
	public AjaxJson restart(TScheduleEntity tSchedule, HttpServletRequest request) throws SchedulerException {
		String message = null;
		AjaxJson j = new AjaxJson();
		tSchedule = systemService.getEntity(TScheduleEntity.class, tSchedule.getId());
        String scheduleJobname = tSchedule.getScheduleJobname();
        message = tSchedule.getScheduleJobname()+"启动成功";
		quartzManager.resumeJob(scheduleJobname,scheduleJobname);
		j.setMsg(message);
		return j;
	}

	/**
	 * 删除t_schedule
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(TScheduleEntity tSchedule, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		tSchedule = systemService.getEntity(TScheduleEntity.class, tSchedule.getId());
		message = "t_schedule删除成功";
		try{
			tScheduleService.delete(tSchedule);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "t_schedule删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除t_schedule
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "t_schedule删除成功";
		try{
			for(String id:ids.split(",")){
				TScheduleEntity tSchedule = systemService.getEntity(TScheduleEntity.class, 
				Integer.parseInt(id)
				);
				tScheduleService.delete(tSchedule);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "t_schedule删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加t_schedule
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(TScheduleEntity tSchedule, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "t_schedule添加成功";
		try{
			tScheduleService.save(tSchedule);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "t_schedule添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新t_schedule
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(TScheduleEntity tSchedule, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "t_schedule更新成功";
		TScheduleEntity t = tScheduleService.get(TScheduleEntity.class, tSchedule.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(tSchedule, t);
			tScheduleService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "t_schedule更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * t_schedule新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(TScheduleEntity tSchedule, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tSchedule.getId())) {
			tSchedule = tScheduleService.getEntity(TScheduleEntity.class, tSchedule.getId());
			req.setAttribute("tSchedule", tSchedule);
		}
		return new ModelAndView("com/jingzhun/wbsc/title/tSchedule-add");
	}
	/**
	 * t_schedule编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(TScheduleEntity tSchedule, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tSchedule.getId())) {
			tSchedule = tScheduleService.getEntity(TScheduleEntity.class, tSchedule.getId());
			req.setAttribute("tSchedule", tSchedule);
		}
		return new ModelAndView("com/jingzhun/wbsc/title/tSchedule-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","tScheduleController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(TScheduleEntity tSchedule,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(TScheduleEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSchedule, request.getParameterMap());
		List<TScheduleEntity> tSchedules = this.tScheduleService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"t_schedule");
		modelMap.put(NormalExcelConstants.CLASS,TScheduleEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("t_schedule列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,tSchedules);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(TScheduleEntity tSchedule,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"t_schedule");
    	modelMap.put(NormalExcelConstants.CLASS,TScheduleEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("t_schedule列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<TScheduleEntity> listTScheduleEntitys = ExcelImportUtil.importExcel(file.getInputStream(),TScheduleEntity.class,params);
				for (TScheduleEntity tSchedule : listTScheduleEntitys) {
					tScheduleService.save(tSchedule);
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
