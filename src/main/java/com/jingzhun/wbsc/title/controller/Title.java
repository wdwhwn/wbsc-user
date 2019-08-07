package com.jingzhun.wbsc.title.controller;

import com.jingzhun.wbsc.category.entity.TCategoryEntity;
import com.jingzhun.wbsc.configuration.entity.TLocationEntity;
import com.jingzhun.wbsc.img.entity.TImageEntity;
import com.jingzhun.wbsc.login.config.CookieJudge;
import com.jingzhun.wbsc.schedule.MyJob;
import com.jingzhun.wbsc.schedule.QuartzManager;
import com.jingzhun.wbsc.title.controller.config.ContentPush;
import com.jingzhun.wbsc.title.controller.config.ImgUpload;
import com.jingzhun.wbsc.title.controller.config.ParamObtain;
import com.jingzhun.wbsc.title.entity.TArticleEntity;
import com.jingzhun.wbsc.title.entity.TScheduleEntity;
import com.jingzhun.wbsc.title.service.TArticleServiceI;
import com.jingzhun.wbsc.title.service.TitleService;
import com.jingzhun.wbsc.user.entity.TUserWebEntity;
import com.jingzhun.wbsc.util.JsonUtil;
import com.jingzhun.wbsc.util.PropertiesUtil;
import com.jingzhun.wbsc.web.entity.TWebEntity;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Administrator on 2019/6/11 0011.
 *
 */

@Controller
@RequestMapping("/tTitle")
public class Title {
    private static final Logger log = LoggerFactory.getLogger(Title.class);
    @Autowired
    private SystemService systemService;

    @Autowired
    private QuartzManager quartzManager;

    @Autowired
    private TitleService titleService;

    @Autowired
    private TArticleServiceI tArticleServiceI;
    private static Integer i = 1;

    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {

        HttpSession session = request.getSession();

        Map<String,String> cookies =(Map<String,String>) session.getAttribute("cookies");
        TWebEntity web = (TWebEntity) session.getAttribute("web");

        if(web==null||!CookieJudge.chooseContentPush(web.getIdentification(),cookies)){
            return new ModelAndView("com/jingzhun/wbsc/title/error");
        }
        String identification = web.getIdentification();
        return new ModelAndView("com/jingzhun/wbsc/title/xxts/"+identification);
    }

    @RequestMapping(params = "getTreeData", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public AjaxJson getTreeData(TSDepart depatr, HttpServletResponse response, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        try {
            /*++++++++++++++++++第1步：取得集合id  pid  name+++++++++++++++++*/
            String sql = "select id ,pid,category_name categoryName from t_category ";
            List<Map<String, Object>> forJdbc = this.systemService.findForJdbc(sql);
            ArrayList<TCategoryEntity> tCategoryEntities = new ArrayList<TCategoryEntity>();
            for (int i = 0; i < forJdbc.size(); i++) {
                TCategoryEntity categoryEntity = new TCategoryEntity();
                categoryEntity.setId((Integer) forJdbc.get(i).get("id"));
                categoryEntity.setPid(forJdbc.get(i).get("pid").toString());
                categoryEntity.setCategoryName(forJdbc.get(i).get("categoryName").toString());
                tCategoryEntities.add(categoryEntity);
            }
            /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
            /*++++++++++++++++++第2步：将数据存储到map中+++++++++++++++++*/
            List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
            Map<String, Object> map = null;
            for (TCategoryEntity tCategoryEntity : tCategoryEntities) {
                String sqls = null;
                Object[] paramss = null;
                map = new HashMap<String, Object>();
                map.put("id", tCategoryEntity.getId());
                map.put("name", tCategoryEntity.getCategoryName());
                if (tCategoryEntity.getPid() != null) {
                    map.put("pId", tCategoryEntity.getPid());
                    map.put("open", false);
                } else {
                    map.put("pId", "0");
                    map.put("open", false);
                }
                sqls = "select count(1) from t_category t where t.pid= " + tCategoryEntity.getId();
                Long counts = this.systemService.getCountForJdbc(sqls);
                if (counts > 0) {
                    dataList.add(map);
                } else {
                    TCategoryEntity de = this.systemService.get(TCategoryEntity.class, tCategoryEntity.getId());
                    if (de != null) {
                        map.put("id", de.getId());
                        map.put("name", de.getCategoryName());
                        if (tCategoryEntity.getPid() != null) {
                            map.put("pId", tCategoryEntity.getPid());
                            map.put("open", false);
                        } else {
                            map.put("pId", "0");
                            map.put("open", false);
                        }
                        dataList.add(map);
                    } else {
                        map.put("open", false);
                        dataList.add(map);
                    }
                }
            }
            /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
            j.setObj(dataList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j;
    }

    @RequestMapping(params = "paramObtain")
    @ResponseBody
    /*public String paramObtain(String locationId, String categoryName, String keywords, String keywords1,
                              String keywords2, String brand, String model, Integer day, Integer hour,
                              Integer minute, Integer second, String product,HttpServletRequest request) throws Exception {*/
    public String paramObtain( Integer day, Integer hour,
                              Integer minute, Integer second,HttpServletRequest request)  {
        HashMap<String, String> objectObjectHashMap = new HashMap<>();
//        Jedis jedis = JedisUtil.getJedis();
        HttpSession session = request.getSession();
        TWebEntity web = (TWebEntity)session.getAttribute("web");
        String identification = web.getIdentification();
//        String identification = session.getAttribute("identification").toString();
        /*++++++++++++++++++第0步：获取参数+++++++++++++++++*/
        Map<String, String> paramMap0= null;
        paramMap0 = ParamObtain.chooseParamMap(request,identification);
        String categoryName = paramMap0.get("categoryName");
        String locationId = paramMap0.get("locationId");
        String keywords = paramMap0.get("keywords");
//        品牌
        String brand = paramMap0.get("brand");
//        型号
        String model = paramMap0.get("model");
//        产品名
        String product = paramMap0.get("product");
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
    /*++++++++++++++++++第1步：生成标题+++++++++++++++++*/
    String locationName="";
        TSUser user = ResourceUtil.getSessionUser();
        System.out.println("用户key：" + user.getUserKey());
        String userKey = user.getUserKey();
    if(StringUtil.isNotEmpty(locationId)) {
        TLocationEntity tLocationEntity = this.titleService.get(TLocationEntity.class, Integer.parseInt(locationId));
        log.error(tLocationEntity.toString());

        locationName = tLocationEntity.getLocationName();
    }
        String titleList = createTitle(categoryName, locationName, keywords, brand, model, product);
    /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

    /*++++++++++++++++++第2步：获取到图片+++++++++++++++++*/
//        String img = jedis.hget("img", userKey);
        if (session.getAttribute("img") == null) {
            objectObjectHashMap.put("status", "false");
            objectObjectHashMap.put("message", "请重新登陆");
            return JsonUtil.toJson(objectObjectHashMap);
        }
        String img1=session.getAttribute("img").toString();
        List<String> list = JsonUtil.toObject(img1, List.class);
        String img=list.get(0);
        log.error("当前使用图片为：" + img);
    /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

    /*++++++++++++++++++第3步：生成文本+++++++++++++++++*/
        String ss = "";
        String sql1 = "select id,category_name categoryName,pid from t_category where category_name= '" + categoryName + "'";
        List<Map<String, Object>> forJdbc = this.systemService.findForJdbc(sql1);
        String id = forJdbc.get(0).get("id").toString();
        String sql = "select id,content,category_id categoryId from t_content where category_id=" + id;
        List<Map<String, Object>> forJdbc1 = this.systemService.findForJdbc(sql);
        for (int i1 = 0; i1 < forJdbc1.size(); i1++) {
            Object content = forJdbc1.get(i1).get("content");
            ss += content.toString();
        }
        String content = content(ss);
    /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/


    /*++++++++++++++++++第步：整合参数+++++++++++++++++*/

//        identification,paramMap0,img,cookies,content,titleList
    /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
        String[] attrValue = null;
        String categoryId = "3000101101";
//        String cookieValue = jedis.hget("cookie", userKey);

        Map<String,String> cookies = (Map<String,String>)session.getAttribute("cookies");
        if(cookies ==null){
            objectObjectHashMap.put("status", "false");
            objectObjectHashMap.put("message", "请重新登陆");
            return JsonUtil.toJson(objectObjectHashMap);
        }

        paramMap0.put("identification",identification);
        paramMap0.put("img",img);
        paramMap0.put("content",content);
        paramMap0.put("titleList",titleList);
        paramMap0.put("userKey",userKey);
        TUserWebEntity tUserWebEntity = (TUserWebEntity) session.getAttribute("userWeb");
        Integer webid=tUserWebEntity.getWebId();
        paramMap0.put("username", tUserWebEntity.getUsername());
        paramMap0.put("password", tUserWebEntity.getPassword());
        paramMap0.put("webId", webid + "");
        //     获取标题集合、获取图片集合、获取文本
        String jobName = "0";
    /*++++++++++++++++++第5步：添加定时任务+++++++++++++++++*/
        if (day != null || hour != null || minute != null || second != null) {
            Map<String, Object> stringObjectMap = null;
            try {
                stringObjectMap = testA(userKey, JsonUtil.toJson(paramMap0), day, hour, minute, second, webid, tUserWebEntity.getUsername());
            } catch (Exception e) {
                e.printStackTrace();
                objectObjectHashMap.put("status", "定时任务处理失败");
                return  JsonUtil.toJson(objectObjectHashMap);
            }
            Object data = stringObjectMap.get("data");
            jobName = data.toString();
        } else {
    /*++++++++++++++++++第4步：信息发布+++++++++++++++++*/
            Map<String, String> map = null;
            try {
                map = ContentPush.chooseContentPush(paramMap0,cookies);
            } catch (Exception e) {
                e.printStackTrace();
                objectObjectHashMap.put("status", "网络不稳定，请稍后重试");

             return   JsonUtil.toJson(objectObjectHashMap);
            }
    /*++++++++++++++++++第6步：发布任务入库+++++++++++++++++*/
            TArticleEntity tArticleEntity = new TArticleEntity();
            tArticleEntity.setParameter(map.get("param"));
            tArticleEntity.setGenerationTime(new Date());
            tArticleEntity.setScheduleid(jobName);
            tArticleEntity.setUserKey(userKey);
            tArticleEntity.setWebid(webid);
            try {
                tArticleServiceI.save(tArticleEntity);
            } catch (Exception e) {
                e.printStackTrace();
                objectObjectHashMap.put("status", "任务入库出现异常");

                return  JsonUtil.toJson(objectObjectHashMap);
            }
        }
        objectObjectHashMap.put("status", "success");
        return JsonUtil.toJson(objectObjectHashMap);
    }

    /**
     * 添加定时任务
     * @param userKey 用户id
     * @param param   文本生成参数
     * @throws Exception
     */
    public Map<String, Object> testA(String userKey, String param, Integer day, Integer hour, Integer minute, Integer second, Integer webid, String username) throws Exception {
        String no = userKey + "_" + System.currentTimeMillis();
        HashMap<String, Object> map = new HashMap<String, Object>();
        ArrayList<String> list = new ArrayList<String>();
        CronTriggerImpl trigger = new CronTriggerImpl();
        if (day != null || hour != null || minute != null || second != null) {
            List<String> strings = new ArrayList<String>();
            if (second != null) {
                strings.add(second + " ");
            } else {
                strings.add("0 ");
            }
            if (minute != null) {
                strings.add(minute + " ");
            } else {
                strings.add("0 ");
            }
            if (hour != null) {
                strings.add(hour + " ");
            } else {
                strings.add("0 ");
            }
            Calendar instance = Calendar.getInstance();
//            月
            int month = instance.get(Calendar.MONTH);
//            日
            int date = instance.get(Calendar.DATE);
//            年
            int year = instance.get(Calendar.YEAR);
//            获取当月最大天数
            instance.set(year, month + 1, 0);
            int maxDay = instance.get(Calendar.DAY_OF_MONTH);
//            String s = String.join("", strings);
            String s="";
            for (int i1 = 0; i1 < strings.size(); i1++) {
                s+=strings.get(i1);
            }
            log.error("表达式：" + s);
            if (day == null) {
                day = 0;
            }
            int i = date + day;
//            1. 不超过1个月
            if (i <= maxDay) {

                s += (date + "-" + i) + " " + (month + 1) + " ? " + year;
//                s="0/5 * * * * ?";
                s = "0 0/2 16 2 8 ? 2019";
                map.put("count", "1");
                list.add(no);
                map.put("data", list);
                try {
                    trigger.setCronExpression(s);
                } catch (ParseException e) {
                    log.error("cron表达式错误");
                }
                log.error("定时任务："+no);
                quartzManager.addJob(no, no, no, no, MyJob.class, s, param);
                TScheduleEntity tScheduleEntity = new TScheduleEntity();
                tScheduleEntity.setParam(param);
                tScheduleEntity.setScheduleJobname(no);
                tScheduleEntity.setUserKey(userKey);
                tScheduleEntity.setCreateDate(new Date());
                String dayBegin = date < 10 ? "0" + date : "" + date;
                String dayEnd = i < 10 ? "0" + i : "" + i;
                String month_ = (month + 1) < 10 ? "0" + (month + 1) : "" + month + 1;
                s = year + "." + month_ + "." + dayBegin + "-" + year + "." + month_ + "." + dayEnd;
                tScheduleEntity.setScheduleCron(s);
                tScheduleEntity.setWebId(webid);
                tScheduleEntity.setWebUsername(username);
                this.systemService.save(tScheduleEntity);
            } else if (month != 11 && i > maxDay) {
//            2. 超过1个月，没有隔年
                map.put("count", "2");
                String s1 = s;
                s += (date + "-" + maxDay) + " " + (month + 1) + " ? " + year;
                no = no + "_I";
                list.add(no);
                try {
                    trigger.setCronExpression(s);
                } catch (ParseException e) {
                    log.error("cron表达式错误");
                }
                quartzManager.addJob(no, no, no, no, MyJob.class, s, param);
                TScheduleEntity tScheduleEntity = new TScheduleEntity();
                tScheduleEntity.setParam(param);
                tScheduleEntity.setScheduleJobname(no);
                tScheduleEntity.setUserKey(userKey);
                tScheduleEntity.setCreateDate(new Date());
                String dayBegin = date < 10 ? "0" + date : "" + date;
                String dayEnd = maxDay < 10 ? "0" + maxDay : "" + maxDay;
                String month_ = (month + 1) < 10 ? "0" + (month + 1) : "" + month + 1;
                s = year + "." + month_ + "." + dayBegin + "-" + year + "." + month_ + "." + dayEnd;
                tScheduleEntity.setScheduleCron(s);
                tScheduleEntity.setWebId(webid);
                tScheduleEntity.setWebUsername(username);
                this.systemService.save(tScheduleEntity);
                no = no.replace("I", "II");
                list.add(no);
                s1 += (1 + "-" + (i - maxDay)) + " " + (month + 2) + " ? " + year;
                try {
                    trigger.setCronExpression(s1);
                } catch (ParseException e) {
                    log.error("cron表达式错误");
                }
                quartzManager.addJob(no, no, no, no, MyJob.class, s1, param);
                TScheduleEntity tScheduleEntity1 = new TScheduleEntity();
                tScheduleEntity1.setParam(param);
                tScheduleEntity1.setUserKey(userKey);
                tScheduleEntity1.setWebId(webid);
                tScheduleEntity.setCreateDate(new Date());
                tScheduleEntity1.setScheduleJobname(no);
                tScheduleEntity1.setWebUsername(username);
                String dayEnd_ = (i - maxDay) < 10 ? "0" + (i - maxDay) : "" + (i - maxDay);
                String month1_ = (month + 2) < 10 ? "0" + (month + 2) : "" + month + 1;
                s1 = year + "." + month1_ + ".01" + "-" + year + "." + month1_ + "." + dayEnd_;
                tScheduleEntity1.setScheduleCron(s1);
                this.systemService.save(tScheduleEntity1);
                map.put("data", list);
            } else if (month == 11 && i > maxDay) {
                //            3. 隔年
                map.put("count", "2");
                String s1 = s;
                s += (date + "-" + maxDay) + " " + (month + 1) + " ? " + year;
                no = no + "_I";
                list.add(no);
                try {
                    trigger.setCronExpression(s);
                } catch (ParseException e) {
                    log.error("cron表达式错误");
                }
                quartzManager.addJob(no, no, no, no, MyJob.class, s, param);
                TScheduleEntity tScheduleEntity = new TScheduleEntity();
                tScheduleEntity.setParam(param);
                tScheduleEntity.setScheduleJobname(no);
                tScheduleEntity.setUserKey(userKey);
                tScheduleEntity.setCreateDate(new Date());
                String dayBegin = date < 10 ? "0" + date : "" + date;
                String dayEnd = maxDay < 10 ? "0" + maxDay : "" + maxDay;
                String month_ = (month + 1) < 10 ? "0" + (month + 1) : "" + month + 1;
                s = year + "." + month_ + "." + dayBegin + "-" + year + "." + month_ + "." + dayEnd;
                tScheduleEntity.setScheduleCron(s);
                tScheduleEntity.setWebUsername(username);
                tScheduleEntity.setWebId(webid);
                this.systemService.save(tScheduleEntity);
                s1 += (1 + "-" + (i - maxDay)) + "" + (month + 2) + "?" + (year + 1);
                no = no.replace("I", "II");
                list.add(no);
                try {
                    trigger.setCronExpression(s1);
                } catch (ParseException e) {
                    log.error("cron表达式错误");
                }
                quartzManager.addJob(no, no, no, no, MyJob.class, s1, param);
                TScheduleEntity tScheduleEntity1 = new TScheduleEntity();
                tScheduleEntity1.setParam(param);
                tScheduleEntity1.setUserKey(userKey);
                tScheduleEntity1.setWebId(webid);
                tScheduleEntity1.setScheduleJobname(no);
                tScheduleEntity.setCreateDate(new Date());
                String dayEnd_ = (i - maxDay) < 10 ? "0" + (i - maxDay) : "" + (i - maxDay);
                String month1_ = (month + 2) < 10 ? "0" + (month + 2) : "" + month + 1;
                s1 = year + "." + month1_ + ".01" + "-" + year + "." + month1_ + "." + dayEnd_;
                tScheduleEntity1.setScheduleCron(s1);
                tScheduleEntity1.setWebUsername(username);
                this.systemService.save(tScheduleEntity1);
                map.put("data", list);
            } else {
                throw new Exception("不能超过28天");
            }
        }
        return map;
    }

    /**
     * 批量添加 图片
     * 如果图片已经被上传到web，从数据库中获取其web中图片路径
     * 否则将图片上传到web中，获取其web中图片路径，并存储到数据库中
     * @return
     */
    @RequestMapping(params = "doBatchDel")
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
        String message = null;
        HttpSession session = request.getSession();
        AjaxJson j = new AjaxJson();
        message = "图片上传成功";
        String path=ResourceUtil.getConfigByName("webUploadpath");//demo中设置为D://upFiles,实际项目应因事制宜
//        String path = "E://jeecg/wdwhwn-master/wbsc-user/src/main/webapp/images/server/";
        TWebEntity tWebEntity =(TWebEntity) session.getAttribute("web");
        if(tWebEntity==null){
            j.setMsg("其重新登陆");
            return j;
        }
//        获取上传url
        String uploadUrl = tWebEntity.getUploadUrl();
//      获取jsoup的上传图片时的map
        String paramImgupload = tWebEntity.getParamImgupload();
        String identification = tWebEntity.getIdentification();
        System.out.println("测试："+paramImgupload);
        Map<String,String> imgUploadMap = JsonUtil.toObject(paramImgupload, Map.class);
        if("zhizaojiaoyi".equals(identification)){
            imgUploadMap.put("md5",System.currentTimeMillis()+"");
        }
//        获取对应的name
        String imgUploadName = tWebEntity.getImgUploadName();
        Map<String,String> cookies=(Map<String,String>)session.getAttribute("cookies");
        if(!CookieJudge.chooseContentPush(identification,cookies)){
            j.setMsg("其重新登陆");
            return j;
        }
//        Map<String, String> cookies = (Map<String, String>) session.getAttribute("cookies");
        log.error("cookieVal: " + cookies);
        List<String> imgList = new ArrayList<String>();
        /*++++++++++++++++++第1步：判断图片是否已被上传到web中+++++++++++++++++*/
        try {
            for (String id : ids.split(",")) {
                TImageEntity tImage = systemService.getEntity(TImageEntity.class,
                        Integer.parseInt(id)
                );
                String webImgName = tImage.getWebImgName();
//                userKey= tImage.getUserKey();
                /*++++++++++++++++++第2步：如果没有上传，则上传到web中+++++++++++++++++*/
                if (StringUtil.isEmpty(webImgName)) {
                    String url = tImage.getUrl();
                    path = path + url;
//                    String upload = HttpRequest.postFile(path, uploadUrl, null, cookieVal);
//                    Msg msg = JsonUtil.toObject(upload, Msg.class);
                    File file = new File(path);
                    FileInputStream fileInputStream = new FileInputStream(file);

                    StringBuilder sb=new StringBuilder("headfile/");
                    sb.append(identification);
                    sb.append("/");
                    sb.append(identification);
                    sb.append("_imgupload.properties");
                    Map<String, String> imgUploadHeadMap = PropertiesUtil.getProperties(sb.toString());
                    Document doc = Jsoup.connect(uploadUrl).ignoreHttpErrors(true).ignoreContentType(true)
                            .cookies(cookies)
                            .headers(imgUploadHeadMap)
                            .data(imgUploadName, file.getName(), fileInputStream)
                            .data(imgUploadMap)
                            .post();
                    System.out.println("图片上传："+doc);
                    webImgName = ImgUpload.chooseMethod(doc, identification);
                    /*++++++++++++++++++第3步：将web中图片路径  入库+++++++++++++++++*/
                    titleService.update(tImage.getId(), webImgName);
                }
                imgList.add(webImgName);
            }
                   /*++++++++++++++++++第4步：获取用户id+++++++++++++++++*/
            session.setAttribute("img",JsonUtil.toJson(imgList));
        } catch (Exception e) {
            e.printStackTrace();
            message = "图片上传失败，请联系管理员";
            j.setMsg(message);
            throw new BusinessException(e.getMessage());
        }
        return j;
    }



    /**
     * 随机生成文本
     * @param ss
     * @return
     */
    public String content(String ss) {

        ss = ss.replace("\n", "");
        String[] split = ss.split("，");
        String content = "";
//        第一段
        for (int i1 = 0; i1 < 3; i1++) {
            int i = (int) (Math.random() * split.length);
            content += split[i];
        }
        char c = content.charAt(content.length() - 1);
        if (c != '。') {
            content += "。\r\n";
        } else {
            content += "\r\n";
        }
//        第二段
        for (int i1 = 0; i1 < 10; i1++) {
            int i = (int) (Math.random() * split.length);
            content += split[i];
        }
        if (c != '。') {
            content += "。\r\n";
        } else {
            content += "\r\n";
        }
//        第三段
        for (int i1 = 0; i1 < 5; i1++) {
            int i = (int) (Math.random() * split.length);
            content += split[i];
        }
        if (c != '。') {
            content += "。\r\n";
        } else {
            content += "\r\n";
        }
        return content;
    }
    /**
     * 生成标题
     * @param categoryName 类别名称
     * @param locationName 地区名称
     * @param keyWords   关键词
     * @param brand 品牌
     * @param model 型号
     * @param product  产品名称
     * @return
     */
    public String createTitle(String categoryName, String locationName, String keyWords, String brand, String model, String product) {
        try {
        /*++++++++++++++++++第1步：通过参数id，获取对应的参数名称+++++++++++++++++*/
            String[] strings = keyWords.split(" ");
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        /*++++++++++++++++++第2步：生成标题+++++++++++++++++*/
            List<String> titleList = new ArrayList<String>();
            String title = "";
            log.error("总共产生" + strings.length + "个标题");
            for (int i2 = 0; i2 < strings.length; i2++) {
                title = locationName + categoryName + brand + model + strings[i2] + product;
                titleList.add(title);
                log.error("第{}个标题，题目为：{}", i2, title);
            }
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
            return JsonUtil.toJson(titleList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }
}
