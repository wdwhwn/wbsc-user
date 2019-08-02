package com.jingzhun.wbsc.title.controller;

import com.jingzhun.wbsc.title.entity.TArticleEntity;
import com.jingzhun.wbsc.util.HttpRequest;
import com.jingzhun.wbsc.util.JedisUtil;
import com.jingzhun.wbsc.util.JsonUtil;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WangDan  on 2019/7/24 0024.
 */
@Controller
@RequestMapping("/ScheduleController")
public class ScheduleController {
    private static final Logger logger = LoggerFactory.getLogger(TArticleController.class);


    @Autowired
    private SystemService systemService;

    /**
     * t_article列表 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/jingzhun/wbsc/title/messagePushResultList");
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
    public void datagrid(TArticleEntity tArticle, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        List<Map<String, Object>> resultMapList=new ArrayList<Map<String,Object>>();
       try{
            dataGrid.setTotal(0);
            dataGrid.setResults(resultMapList);
            logger.error(JsonUtil.toJson(dataGrid));
        } catch (Exception e) {
            logger.error(e.toString(),e);
            e.printStackTrace();
        }
        TagUtil.datagrid(response,dataGrid);
    }
}
