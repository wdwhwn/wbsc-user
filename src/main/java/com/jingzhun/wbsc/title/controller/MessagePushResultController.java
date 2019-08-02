package com.jingzhun.wbsc.title.controller;

import com.jingzhun.wbsc.title.controller.config.ResultList;
import com.jingzhun.wbsc.title.entity.TArticleEntity;
import com.jingzhun.wbsc.util.*;

import com.jingzhun.wbsc.web.entity.TWebEntity;
import org.jeecgframework.core.common.model.json.DataGrid;

import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author WangDan  on 2019/7/24 0024.
 */
@Controller
@RequestMapping("/messagePushResultController")
public class MessagePushResultController {
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

        HttpSession session = request.getSession();
        Object cookies = session.getAttribute("cookies");
        if(cookies==null){
            return new ModelAndView("com/jingzhun/wbsc/title/error");
        }
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

        List<Map<String, Object>> resultMapList=new ArrayList<Map<String,Object>>();
        HttpSession session = request.getSession();
        try {
                TSUser user = ResourceUtil.getSessionUser();
                System.out.println("用户key："+user.getUserKey());
                String userKey = user.getUserKey();
//                    获取cookie
//                String cookie = jedis.hget("cookie",userKey);
            Map<String,String> cookies = (Map<String,String>)session.getAttribute("cookies");
            TWebEntity web = (TWebEntity) session.getAttribute("web");
            String identification = web.getIdentification();
            String resultlistUrl = web.getResultlistUrl();
            String paramResultlist = web.getParamResultlist();
                /*++++++++++++++++++第1步：+++++++++++++++++*/
                Document doc=new Document("www.baidu.com");
                 if(cookies!=null){
//                String url="http://my.qinfabu.com/Product/List";
//                String param1="status=1";
//                Map<String, String> stringStringMap = HttpRequest.sendGet(url, param1, cookie, "UTF-8");
//                 data = stringStringMap.get("data");
                     StringBuilder sb=new StringBuilder("headfile/");
                     sb.append(identification);
                     sb.append("/");
                     sb.append(identification);
                     sb.append("_resultlist.properties");
                     Map<String, String> resultListHeadMap = PropertiesUtil.getProperties(sb.toString());
                     if(identification.equals("zhizaojiaoyi")) {
                         doc = Jsoup.connect(resultlistUrl).ignoreContentType(true).ignoreHttpErrors(true)
                                 .followRedirects(false)
                                 .cookies(cookies)
                                 .headers(resultListHeadMap)
                                 .get();
                     }else{
                         doc = Jsoup.connect(resultlistUrl).ignoreContentType(true).ignoreHttpErrors(true)
                                 .headers(resultListHeadMap)
                                 .requestBody(paramResultlist)
                                 .cookies(cookies)
                                 .post();
                     }
                 }

                /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
            dataGrid = ResultList.chooseResultList(doc, identification, dataGrid);

            logger.error(JsonUtil.toJson(dataGrid));
            } catch (Exception e) {
                logger.error(e.toString(),e);
                e.printStackTrace();
            }
            TagUtil.datagrid(response,dataGrid);
    }   


}
