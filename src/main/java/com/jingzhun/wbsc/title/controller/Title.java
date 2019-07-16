package com.jingzhun.wbsc.title.controller;

import com.alibaba.fastjson.JSONObject;
import com.jingzhun.wbsc.configuration.entity.TLocationEntity;
import com.jingzhun.wbsc.img.entity.TImageEntity;
import com.jingzhun.wbsc.title.entity.Msg;
import com.jingzhun.wbsc.title.entity.TArticleEntity;
import com.jingzhun.wbsc.title.entity.TScheduleParamEntity;
import com.jingzhun.wbsc.title.service.TArticleServiceI;
import com.jingzhun.wbsc.title.service.TScheduleParamServiceI;
import com.jingzhun.wbsc.title.service.TitleService;
import com.jingzhun.wbsc.user.entity.TUserEntity;
import com.jingzhun.wbsc.util.HttpRequest;
import com.jingzhun.wbsc.util.JedisUtil;
import com.jingzhun.wbsc.util.JsonUtil;
import com.jingzhun.wbsc.category.entity.TCategoryEntity;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.timer.DynamicTask;
import org.jeecgframework.core.util.IpUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.jwt.util.JwtHttpUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSTimeTaskEntity;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.TimeTaskServiceI;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Administrator on 2019/6/11 0011.
 */

@Controller
@RequestMapping("/tTitle")
public class Title implements Job{
    private static final Logger log = LoggerFactory.getLogger(Title.class);
    @Autowired
    private SystemService systemService;
    @Autowired
    private TimeTaskServiceI timeTaskService;

    @Autowired(required=false)
    private DynamicTask dynamicTask;

    @Autowired
    private TitleService titleService;
    @Autowired
    private TScheduleParamServiceI tScheduleParamServiceI;
    @Autowired
    private TArticleServiceI tArticleServiceI;
    private static Integer i=1;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.error("this对象title："+this);
        i++;
        System.out.println("测试定时任务"+i);
        /*++++++++++++++++++第1步：获取当前时间，利用当前时间检索到schedule+++++++++++++++++*/
        java.util.Calendar instance = java.util.Calendar.getInstance();
        int year = instance.get(java.util.Calendar.YEAR);
        int month = instance.get(java.util.Calendar.MONTH);
        int day = instance.get(java.util.Calendar.DAY_OF_MONTH);
        int hour = instance.get(java.util.Calendar.HOUR_OF_DAY);
        int minute = instance.get(java.util.Calendar.MINUTE);
        String format = String.format("\n年份：%s，\n月份：%s，\n天：%s，\n时：%s，\n分：%s", year + "", month + 1+"", day + "", hour + "", minute + "");
        log.error(format);
        String sql="select * from (select * from t_schedule_param where minute="+minute+" and hour="+hour+" and "+day+" BETWEEN daybegin and dayend and month="+(month+1)+" and year="+year+")t inner join t_s_timetask on t.timetaskid=t_s_timetask.id where t_s_timetask.IS_START=1 and t_s_timetask.IS_EFFECT=1";
        log.error("sql:"+sql);
        List<Map<String, Object>> forJdbc = systemService.findForJdbc(sql);
        log.error("当前的调度的个数："+forJdbc.size());
        int num = forJdbc.size();
        if(num==1){
        /*++++++++++++++++++第2步：获取param，调用方法+++++++++++++++++*/
            Map<String, Object> stringObjectMap = forJdbc.get(0);
            Object params = stringObjectMap.get("params");
            log.error("获取的参数为："+ params);
            Map map = JsonUtil.toObject(params.toString(), Map.class);
            try {
                log.error("方法参数为："+map.get("locationId").toString()+map.get("categoryName").toString()+map.get("keywords").toString()+map.get("keywords1").toString()+map.get("keywords2").toString()+map.get("brand").toString()+map.get("model").toString()+map.get("product").toString());
                paramObtain(map.get("locationId").toString(),map.get("categoryName").toString(),map.get("keywords").toString(),map.get("keywords1").toString(),map.get("keywords2").toString(),map.get("brand").toString(),map.get("model").toString(),null,null,null,null,map.get("product").toString());
//                JwtHttpUtil.httpRequest("http://localhost:8083/title","paramObtain",null);

//                HttpRequest.sendPost1("http://localhost:8083/title.do/paramObtain","userId=1",null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
        }else if(num>1){

        }
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
    }
    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/jingzhun/wbsc/title/title");
    }
    @RequestMapping(params="getTreeData",method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public AjaxJson getTreeData(TSDepart depatr, HttpServletResponse response, HttpServletRequest request ){
        AjaxJson j = new AjaxJson();
        try{
            /*++++++++++++++++++第1步：取得集合id  pid  name+++++++++++++++++*/
            String sql="select id ,pid,category_name categoryName from t_category ";
            List<Map<String, Object>> forJdbc = this.systemService.findForJdbc(sql);
            ArrayList<TCategoryEntity> tCategoryEntities = new ArrayList<>();
            for (int i = 0; i < forJdbc.size(); i++) {
                TCategoryEntity categoryEntity = new TCategoryEntity();
                categoryEntity.setId((Integer)forJdbc.get(i).get("id"));
                categoryEntity.setPid(forJdbc.get(i).get("pid").toString());
                categoryEntity.setCategoryName(forJdbc.get(i).get("categoryName").toString());
                tCategoryEntities.add(categoryEntity);
            }
            /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

            /*++++++++++++++++++第2步：将数据存储到map中+++++++++++++++++*/
            List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
            Map<String,Object> map = null;
            for (TCategoryEntity tCategoryEntity : tCategoryEntities) {
                String sqls = null;
                Object[] paramss = null;
                map = new HashMap<String,Object>();
                map.put("id", tCategoryEntity.getId());
                map.put("name", tCategoryEntity.getCategoryName());
                if (tCategoryEntity.getPid() != null) {
                    map.put("pId", tCategoryEntity.getPid());
                    map.put("open",false);
                }else {
                    map.put("pId", "0");
                    map.put("open",false);
                }
                sqls="select count(1) from t_category t where t.pid= "+tCategoryEntity.getId();
                Long counts= this.systemService.getCountForJdbc(sqls);
                if(counts>0){
                    dataList.add(map);
                }else{
                    TCategoryEntity de = this.systemService.get(TCategoryEntity.class, tCategoryEntity.getId());
                    if (de != null) {
                        map.put("id", de.getId());
                        map.put("name", de.getCategoryName());
                        if(tCategoryEntity.getPid()!=null){
                            map.put("pId", tCategoryEntity.getPid());
                            map.put("open",false);
                        }else{
                            map.put("pId", "0");
                            map.put("open",false);
                        }
                        dataList.add(map);
                    }else{
                        map.put("open",false);
                        dataList.add(map);
                    }
                }
            }
            /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
            j.setObj(dataList);
        }catch(Exception e){
            e.printStackTrace();
        }
        return j;
    }
    @RequestMapping(params = "paramObtain1")
    @ResponseBody
    public String paramObtain1() throws Exception {
        testA("1",1,1,1,1,new TScheduleParamEntity());
        return null;
    }
    @RequestMapping(params = "paramObtain")
    @ResponseBody
    public String paramObtain(String locationId,String categoryName,String keywords,String keywords1,
                              String keywords2,String brand,String model,Integer day,Integer hour,
                              Integer minute,Integer second,String product) throws Exception {
        Jedis jedis = JedisUtil.getJedis();
    /*++++++++++++++++++第1步：生成标题+++++++++++++++++*/
        TLocationEntity tLocationEntity = this.titleService.get(TLocationEntity.class, Integer.parseInt(locationId));
        log.error(tLocationEntity.toString());
        if(keywords1!=null) {
            keywords = keywords + " " + keywords1;
        }
        if(keywords2!=null){
            keywords = keywords + " " + keywords2;
        }
        String userId = jedis.get("userId");
        String code = createTitle(categoryName, tLocationEntity.getLocationName(), keywords, Integer.parseInt(userId),brand,model,product);

    /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

    /*++++++++++++++++++第2步：获取到图片+++++++++++++++++*/
        String img = jedis.hget("img", userId);
        log.error("当前使用图片为："+img);
    /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

    /*++++++++++++++++++第3步：生成文本+++++++++++++++++*/
        String ss=null;
        String sql1="select id,category_name categoryName,pid from t_category where category_name= '"+categoryName+"'";
        log.error(sql1);
        List<Map<String, Object>> forJdbc = this.systemService.findForJdbc(sql1);
        String id = forJdbc.get(0).get("id").toString();
        String sql="select id,content,category_id categoryId from t_content where category_id="+id;
        List<Map<String, Object>> forJdbc1 = this.systemService.findForJdbc(sql);
        for (int i1 = 0; i1 < forJdbc1.size(); i1++) {
            Object content = forJdbc1.get(i1).get("content");
            ss+=content.toString();
        }
        String content = content(ss);
        log.error("当前文本为："+content);
    /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

    /*++++++++++++++++++第4步：信息发布+++++++++++++++++*/
        String[] attrValue=null;
        String categoryId="3000101101";
        String cookieValue = jedis.get(userId);
        String s = contentPush(attrValue, Integer.parseInt(userId), categoryId, keywords, content, cookieValue, brand, model);
        Map<String,String> map = JsonUtil.toObject(s, Map.class);

    /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
    
    
    /*++++++++++++++++++第5步：添加定时任务+++++++++++++++++*/
        if (day != null || hour != null || minute != null || second != null) {
            TScheduleParamEntity tScheduleParamEntity = new TScheduleParamEntity();
            tScheduleParamEntity.setHour(hour);
            tScheduleParamEntity.setMinute(minute);
            tScheduleParamEntity.setPicsid(jedis.hget("imgid",userId));
            tScheduleParamEntity.setSecond(second);
            tScheduleParamEntity.setUserid(Integer.parseInt(userId));
            HashMap<String, String> paramMap = new HashMap<>();
            paramMap.put("locationId",locationId);
            paramMap.put("categoryName",categoryName);
            paramMap.put("keywords",keywords);
            paramMap.put("keywords1",keywords1);
            paramMap.put("keywords2",keywords2);
            paramMap.put("brand",brand);
            paramMap.put("model",model);
            paramMap.put("day",day+"");
            paramMap.put("hour",hour+"");
            paramMap.put("minute",minute+"");
            paramMap.put("hour",hour+"");
            paramMap.put("second",second+"");
            paramMap.put("product",product);
            tScheduleParamEntity.setParams(JsonUtil.toJson(paramMap));
            testA(userId, 1, 1, 1, 1,tScheduleParamEntity);
        }
    /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
    
    
    /*++++++++++++++++++第6步：发布任务入库+++++++++++++++++*/
        //        发布任务入库
        TArticleEntity tArticleEntity = new TArticleEntity();
        tArticleEntity.setParameter(map.get("param"));
        tArticleEntity.setGenerationTime(new Date());
        tArticleEntity.setScheduleid(jedis.hget("scheduleid",userId));
        tArticleEntity.setUserid(Integer.parseInt(userId));
        tArticleEntity.setWebid(Integer.parseInt(jedis.hget("webid",userId)));
        tArticleServiceI.save(tArticleEntity);
    /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("status","success");
        jedis.close();
        return JsonUtil.toJson(objectObjectHashMap);
    }

    public  void testA(String userId,Integer day,Integer hour,Integer minute,Integer second,TScheduleParamEntity tScheduleParamEntity) throws Exception {
        TSTimeTaskEntity tsTimeTaskEntity = new TSTimeTaskEntity();
        tsTimeTaskEntity.setTaskId(System.currentTimeMillis() + "");
        tsTimeTaskEntity.setTaskDescribe(userId + "的定时任务");
//        0_禁用
        tsTimeTaskEntity.setIsEffect("1");
//        1_运行
        tsTimeTaskEntity.setIsStart("0");
        tsTimeTaskEntity.setRunServerIp("本地");
        tsTimeTaskEntity.setRunServer("本地");
        tsTimeTaskEntity.setClassName("com.jingzhun.wbsc.schedule.Schedule");
        if (day != null || hour != null || minute != null || second != null) {
            List<String> strings = new ArrayList<>();
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
            String s = String.join("", strings);
            log.error("表达式："+s);

            if(day==null){
                day=0;
            }
            int i = date + day;
//            1. 不超过1个月
            if (i<= maxDay) {
                s += (date + "-" + i) + " " + (month + 1) + " ? " + year;
                s="0/5 * * * * ?";
                tsTimeTaskEntity.setCronExpression(s);
                saveSchedule(tsTimeTaskEntity,tScheduleParamEntity,date,i,month+1,year,0);
            } else if (month != 11 && i > maxDay) {
                //            2. 超过1个月，没有隔年
                String s1 = s;
                s += (date + "-" + maxDay) + " " + (month + 1) + " ? " + year;
                tsTimeTaskEntity.setCronExpression(s);
                saveSchedule(tsTimeTaskEntity,tScheduleParamEntity,date,maxDay,month + 1,year,1);

                s1 += (1 + "-" + (i - maxDay)) + " " + (month + 2) + " ? " + year;
                tsTimeTaskEntity.setCronExpression(s1);
                tsTimeTaskEntity.setTaskId(System.currentTimeMillis()+"");
                saveSchedule(tsTimeTaskEntity,tScheduleParamEntity,1,(i - maxDay),(month + 2),year,1);
            } else if (month == 11 && i > maxDay) {
                //            3. 隔年
                String s2 = s;
                s += (date + "-" + maxDay) + " " + (month + 1) + " ? " + year;
                tsTimeTaskEntity.setCronExpression(s);
                saveSchedule(tsTimeTaskEntity,tScheduleParamEntity,date,maxDay,(month + 1),year,1);


                s2 += (1 + "-" + (i - maxDay)) + "" + (month + 2) + "?" + (year + 1);
                tsTimeTaskEntity.setCronExpression(s2);
                tsTimeTaskEntity.setTaskId(System.currentTimeMillis()+"");
                saveSchedule(tsTimeTaskEntity,tScheduleParamEntity,1,(i - maxDay),(month + 2),year+1,1);
            }else{
//
                throw new Exception("不能超过28天");
            }
        }
    }

//    保存定时任务
    public void saveSchedule(TSTimeTaskEntity tsTimeTaskEntity,TScheduleParamEntity tScheduleParamEntity,Integer dayBegin,Integer dayEnd,Integer month,Integer year,Integer type) throws Exception {
        Jedis jedis = JedisUtil.getJedis();
        //                保存
        AjaxJson save = save(tsTimeTaskEntity);
        TSTimeTaskEntity tsTimeTaskEntity1 = new TSTimeTaskEntity();
        tsTimeTaskEntity1.setId(tsTimeTaskEntity.getId());
        tsTimeTaskEntity1.setIsStart("1");
        Integer userid = tScheduleParamEntity.getUserid();
        startOrStopTask(tsTimeTaskEntity1,userid);
//                定时任务入库
        tScheduleParamEntity.setDaybegin(dayBegin);
        tScheduleParamEntity.setDayend(dayEnd);
        tScheduleParamEntity.setMonth(month);
        tScheduleParamEntity.setYear(year);
        tScheduleParamEntity.setType(type);
        tScheduleParamEntity.setTimetaskid(jedis.hget("timetaskid",userid+""));
        tScheduleParamServiceI.save(tScheduleParamEntity);
        log.error("测试id:"+tScheduleParamEntity.getId());
        jedis.hset("scheduleid",userid+"",tScheduleParamEntity.getId()+"");
        jedis.close();
    }
//    启动定时任务
    public AjaxJson startOrStopTask(TSTimeTaskEntity timeTask,Integer userId) {
        AjaxJson j = new AjaxJson();
        boolean isStart = timeTask.getIsStart().equals("1");
        String sql="";
        timeTask = timeTaskService.get(TSTimeTaskEntity.class, timeTask.getId());
        Jedis jedis = JedisUtil.getJedis();
        jedis.hset("timetaskid",userId+"",timeTask.getId());
        jedis.close();
        boolean isSuccess = false;
        if ("0".equals(timeTask.getIsEffect())) {
            j.setMsg("该任务为禁用状态，请解除禁用后重新启动");
            return j;
        }
        if (isStart && "1".equals(timeTask.getIsStart())) {
            j.setMsg("该任务当前已经启动，请停止后再试");
            return j;
        }
        if (!isStart && "0".equals(timeTask.getIsStart())) {
            j.setMsg("该任务当前已经停止，重复操作");
            return j;
        }
        //String serverIp = InetAddress.getLocalHost().getHostAddress();
        List<String> ipList = IpUtil.getLocalIPList();
        String runServerIp = timeTask.getRunServerIp();
        if((ipList.contains(runServerIp) || StringUtil.isEmpty(runServerIp) || "本地".equals(runServerIp)) && (runServerIp.equals(timeTask.getRunServer()))){//当前服务器IP匹配成功
            isSuccess = dynamicTask.startOrStop(timeTask ,isStart);
        }else{
            try {
                String url = "http://"+timeTask.getRunServer()+"/timeTaskController.do?remoteTask";//spring-mvc.xml
                String param = "id="+timeTask.getId()+"&isStart="+(isStart ? "1" : "0");
                JSONObject json = org.jeecgframework.core.util.HttpRequest.sendPost(url, param);
                isSuccess = json.getBooleanValue("success");
            } catch (Exception e) {
                j.setMsg("远程主机‘"+timeTask.getRunServer()+"’响应超时");
                return j;
            }
        }
        j.setMsg(isSuccess?"定时任务管理更新成功":"定时任务管理更新失败");
        return j;
    }
//  定时任务入库
    public  AjaxJson save(TSTimeTaskEntity timeTask) {
        log.error("Test.save , param:{timeTask = [" + timeTask + "]} ");
        String message = null;
        AjaxJson j = new AjaxJson();
        CronTriggerImpl trigger = new CronTriggerImpl();
        try {
            trigger.setCronExpression(timeTask.getCronExpression());
        } catch (ParseException e) {
            j.setMsg("Cron表达式错误");
            log.error(JsonUtil.toJson(j));
            return j;
        }
        if (StringUtil.isNotEmpty(timeTask.getId())) {
            TSTimeTaskEntity t = timeTaskService.get(TSTimeTaskEntity.class, timeTask.getId());
            if ("1".equals(t.getIsStart())) {
                message = "任务运行中不可编辑，请先停止任务";
            }else{
                message = "定时任务管理更新成功";
                try {
                    if(!timeTask.getCronExpression().equals(t.getCronExpression())){
                        timeTask.setIsEffect("0");
                    }
                    MyBeanUtils.copyBeanNotNull2Bean(timeTask, t);
                    timeTaskService.saveOrUpdate(t);
                    systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
                } catch (Exception e) {
                    e.printStackTrace();
                    message = "定时任务管理更新失败";
                }
            }

        } else {
            message = "定时任务管理添加成功";
            timeTaskService.save(timeTask);
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        }
        j.setMsg(message);
        log.error(JsonUtil.toJson(j));
        return j;
    }
    /**
     * 批量添加 图片
     * 如果图片已经被上传到web，从数据库中获取其web中图片路径
     *否则将图片上传到web中，获取其web中图片路径，并存储到数据库中
     *
     * @return
     */
    @RequestMapping(params = "doBatchDel")
    @ResponseBody
    public AjaxJson doBatchDel(String ids,HttpServletRequest request){
        String message = null;
        Jedis jedis = JedisUtil.getJedis();
        AjaxJson j = new AjaxJson();
        message = "图片上传成功";
        String path="H://jeecg/wdwhwn-master/wbsc-user/src/main/webapp/images/server/";
        String uploadUrl="http://my.qinfabu.com/Upload/Upload";
        String userId1 = jedis.get("userId");
        String cookieVal=jedis.get(userId1);
        log.error("cookieVal: "+cookieVal);
        Integer userId=null;
        List<String> imgList=new ArrayList<>();

        /*++++++++++++++++++第1步：判断图片是否已被上传到web中+++++++++++++++++*/
        try{
            for(String id:ids.split(",")){
                TImageEntity tImage = systemService.getEntity(TImageEntity.class,
                        Integer.parseInt(id)
                );
                String webImgName = tImage.getWebImgName();
                userId = tImage.getUserId();
                /*++++++++++++++++++第2步：如果没有上传，则上传到web中+++++++++++++++++*/
                if(StringUtil.isEmpty(webImgName)){
                    String url = tImage.getUrl();
                    path=path+url;
                    String upload = HttpRequest.postFile(path, uploadUrl, null, cookieVal);
                    Msg msg = JsonUtil.toObject(upload, Msg.class);
                    /*++++++++++++++++++第3步：将web中图片路径  入库+++++++++++++++++*/
                    webImgName= msg.getMsg();
                    titleService.update(tImage.getId(),webImgName);
                    log.error(upload);
                }
                imgList.add(webImgName);
            }
                   /*++++++++++++++++++第4步：获取用户id+++++++++++++++++*/
            TUserEntity de = this.systemService.get(TUserEntity.class,userId );
            jedis.hset("imgIds",userId+"",ids);
            jedis.hset("img",userId+"",JsonUtil.toJson(imgList));
        }catch(Exception e){
            e.printStackTrace();
            message = "t_image删除失败";
            j.setMsg(message);
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        jedis.close();
        return j;
    }

    /**
     *  信息推送

     * @param AttrValue
     * @return
     */

    public String contentPush( String[] AttrValue,Integer userId,String typeid,String keyWords,String data,String cookieValue,String brand,String model) {
        Jedis jedis = JedisUtil.getJedis();
        /*++++++++++++++++++第1步：准备参数+++++++++++++++++*/
//        title 标题
        String titles = jedis.hget("title", userId + "");
        List<String> list = JsonUtil.toObject(titles, List.class);
        int i = (int) (Math.random() * list.size());
        String title = list.get(i);

//        pics 图片
        String picsList = jedis.hget("img", userId + "");
        List<String> picsList1 = JsonUtil.toObject(picsList, List.class);
        String pics=picsList1.get(0);
        if(picsList1.size()>1){
            for (int i1 = 0; i1 < picsList1.size(); i1++) {
                pics+="&pics="+picsList1.get(i1);
            }
        }
//        typeid 类型id
//      unit  单位
        String unit="件";
//      price 单价
        String price="";
//        keyWords
//        id
        String id="";
//      data  文章

        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
        String userId1 = jedis.get("userId");
        String cookieVal=jedis.get(userId1);
        /*++++++++++++++++++第1步：+++++++++++++++++*/
        String url="http://my.qinfabu.com/Product/Add";
        String param="title=TITLE"+
                "&keyWord=KEYWORD"+
                "&pics=PICS"+
                "&typeid=TYPEID"+
                "&AttrValue[0][name]='品牌/厂家'"+
                "&AttrValue[0][value]=ATTRVALUE[BRAND]"+
                "&AttrValue[0][typeid]='1'"+
                "&AttrValue[1][name]='型号'"
                +"&AttrValue[1][value]=ATTRVALUE[MODEL]"
                +"&AttrValue[1][typeid]='1'"
                +"&price="
                +"&unit=件"
                +"&id="
                +"&data=DATA";
        String replaceParam = param.replace("TITLE", title)
                .replace("KEYWORD", keyWords)
                .replace("PICS", pics)
                .replace("TYPEID", typeid + "")
                .replace("DATA",data)
                .replace("ATTRVALUE[BRAND]",brand)
                .replace("ATTRVALUE[MODEL]",model);
        replaceParam=replaceParam.replace("'","");

        log.error("参数为："+replaceParam);
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
//        String param="title=新乡室内精准测量仪离心机4689"+
//                "&keyWord=电子秤"+
//                "&pics=//image.qinfabu.com/img/2019/6/17/20190617204223852.png"+
//                "&typeid=3000101101"+
//                "&AttrValue[0][name]=品牌/厂家"+
//                "&AttrValue[0][value]=企宝推"+
//                "&AttrValue[0][typeid]=1"+
//                "&AttrValue[1][name]=型号"+
//                "&AttrValue[1][value]=4689"+
//                "&AttrValue[1][typeid]=1"+
//                "&price="+
//                "&unit=件"+
//                "&id="+
//                "&data=<p>卧式刮刀卸料离心机是连续运转、间歇操作的过滤式离心机，其控制方式为自动控制，也可手动控制。离心机操作过程中的进料、分离、洗涤、脱水、卸料及滤布再生等过程一般均在全速状态下完成，单次循环时间短，处理量大，并可获得较干的滤渣和良好的洗涤效果。GK 系列卧式刮刀卸料离心机广泛应用于化工、食品、轻工、制药、淀粉等行业，对含粗、中、细颗粒的悬浮液均适用，如硫铵、碳铵、聚氯乙烯、木署改性淀粉等。GKH 系列虹吸式卧式离心机在普通卧式刮刀卸料离心机的基础上，利用虹吸原理增加过滤推动力，透过过滤介质的滤液全部进入滤液室，滤液通过虹吸管 ( 撇液管 ) 排出转鼓，调节虹吸管吸入口位置可改变虹吸室内液面深度，以改变过滤推动力，调节过滤速度、处理能力、滤饼的含湿量以及洗涤效率。</p><p><br/></p>";
//        String s = HttpRequest.sendPost1(url, replaceParam, cookieValue);
        log.error("信息发布");
        String s = "";
        jedis.close();
        HashMap<String, String> map = new HashMap<>();
        map.put("param",replaceParam);
        map.put("data",s);
        return JsonUtil.toJson(map);
    }

    @RequestMapping(params = "contentPush1")
    @ResponseBody
    public String contentPush1() {
//        Jedis jedis = JedisUtil.getJedis();
        /*++++++++++++++++++第1步：准备参数+++++++++++++++++*/
//        title 标题
//        String titles = jedis.hget("title", userId + "");
//        List<String> list = JsonUtil.toObject(titles, List.class);
//        int i = (int) (Math.random() * list.size());
//        String title = list.get(i);
//        pics 图片
//        String picsList = jedis.hget("img", userId + "");
//        List<String> picsList1 = JsonUtil.toObject(picsList, List.class);
//        String pics=picsList1.get(0);
//        if(picsList1.size()>1){
//            for (int i1 = 0; i1 < picsList1.size(); i1++) {
//                pics+="&pics="+picsList1.get(i1);
//            }
//        }
//        typeid 类型id
//      unit  单位
//        String unit="件";
//      price 单价
//        String price="";
//        keyWords
//        id
//        String id="";
//      data  文章

        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        Jedis jedis = JedisUtil.getJedis();
        String userId1 = jedis.get("userId");
        String cookieVal=jedis.get(userId1);
        /*++++++++++++++++++第1步：+++++++++++++++++*/
        String url="http://my.qinfabu.com/Product/Add";
//        String param="title=TITLE&keyWord=KEYWORD&pics=PICS&typeid=TYPEID&AttrValue[0][name]='品牌/厂家'&AttrValue[0][value]=ATTRVALUE[BRAND]&AttrValue[0][typeid]='1'&AttrValue[0][name]='型号'&AttrValue[0][value]=ATTRVALUE[MODEL]&AttrValue[0][typeid]='1'&price=PRICE&unit=UNIT&id=''&data=DATA";
//        String replaceParam = param.replace("TITLE", title).replace("KEYWORD", keyWords).replace("PICS", pics).replace("TYPEID", typeid + "").replace("PRICE",price).replace("UNIT",unit).replace("DATA",data).replace("ATTRVALUE[BRAND]",brand).replace("ATTRVALUE[MODEL]",model);
//        log.error("参数为："+replaceParam);
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
//        1. 去掉单引号
//
        String param="title=上海户外精准测量仪方向仪78956"+
                "&keyWord=搅拌机"+
                "&pics=//image.qinfabu.com/img/2019/6/17/20190617204223852.png"+
                "&typeid=3000101101"+
                "&AttrValue[0][name]=品牌/厂家"+
                "&AttrValue[0][value]=企宝推"+
                "&AttrValue[0][typeid]=1"+
                "&AttrValue[1][name]=型号"+
                "&AttrValue[1][value]=78956"+
                "&AttrValue[1][typeid]=1"+
                "&price="+
                "&unit=件"+
                "&id="+
                "&data=<p>陀螺仪是一种既古老而又很有生命力的仪器，从第一台真正实用的陀螺仪器问世以来已有大半个世纪，但直到现在，陀螺仪仍在吸引着人们对它进行研究，这是由于它本身具有的特性所决定的。陀螺仪最主要的基本特性是它的稳定性和进动性。人们从儿童玩的地陀螺中早就发现高速旋转的陀螺可以竖直不倒而保持与地面垂直，这就反映了陀螺的稳定性。研究陀螺仪运动特性的理论是绕定点运动刚体动力学的一个分支，它以物体的惯性为基础，研究旋转物体的动力学特性</p><p><br/></p>";

        //        String replaceParam="title='北京出国意大利4578出国工作 工薪 高薪薪资'&keyWord='出国工作'&pics='//image.qinfabu.com/img/2019/6/15/20190615144251118.png'&typeid='3003001'&AttrValue[0][name]='品牌/厂家'&AttrValue[0][value]=奥本&AttrValue[0][typeid]='1'&AttrValue[1][name]='型号'&AttrValue[1][value]=456789&AttrValue[1][typeid]='1'&price=&unit=件&id=''&data=让我们携手共创美好未来！！！联系人：吴经理 泰州海陵区凤凰东路68号建工大厦26层2602室江苏永腾人力专业从事劳务输出。本公司多年的经营经验积累了能够外派几十个国家及地区的外派能力同时需要具有翻译资质的翻译机构盖章。若需要签证翻译服务不允许私自打工。6、家属赴澳：工作满半年的劳工可申请有工作能力的家属赴澳及未满18周岁的子女免费赴澳大利亚就学。1、报名资料：有效护原件（没有可以后期提供）、结证、身证及户本复印件、白底两寸彩色照片8-10张、近3个月全身照4张、劳务申请表和报名须知各一份（由本公司提供）。2、后期材料：出生公证、无犯罪公证、体证明四、申请周期：3个月五、总费用：6.5万（含单程机票 六、付款方式在国内支付9000。管工作餐每周工作5天。加班需提出申请住宿自理.加班另算.要求 ：精通小笼包住宿自理.加班另算.要求 精通小笼包可能有加班江苏永腾人力资源有限公司专业从事劳务输出。本公司多年的经营经验积累了能够外派几十个国家及地区的外派能力用工方报销返程机票。不回国另外每年请假不超过7天补齐到5000元人民币无残疾每天工作8小时每天工作8小时签证文件对翻译要求非常严格可续签二、工种范围电子企业、缝纫纺织行业、驾驶司机类、厨师帮厨面点师服务员、星级酒店行业、电气焊、电工、细木工、车床铣床、机械加工维修、机械组装、电机机器组装每年多给1个月的薪水。休息期间可以自由活动可能有加班江苏永腾人力资源有限公司专业从事劳务输出。本公司多年的经营经验积累了能够外派几十个国家及地区的外派能力月薪1万-5万元不等。具体收入以招聘简章为准。七、出国总费用及交费流程：出国服务费：17000-35000元";
//        String cookie="newwjlogin=CfDJ8CCUgNvfCiNBtDoTIkh_tCo5YWWvu_QyF4Edbpy53RH6-bF8ty54PLgMh84QP46hhmkJ1L7f1NpizodIRQMCB64jPNVDYriCx4Ptxd1t59KYJSPNLKcYIjxEsgQEC8DS9OOqXlZreZX5z4ZHzENB1wfkXW2X9Nr1v6kbFJnMo0hqDwAN_wiLX5T5LqMSuDxwTDaYSZOZOLsp-VoqsTBsWhyk3eBhTMQAdv6fVufOMKP1EhalBzE0GTvdJFAlNnylsO5D8hM7PWAx2MiEyVmgSesXs2ISTjmPQrAyWzjptwOic58zSX1xtnaO4_hEejFlIUHX93n-DBhh41x99xpjocENjuH4P-a7C6HWkLDA9XccJ3UpUMw3HM3hSVwayPXqyhVwSNQsXykJBbAwK6ZEEjOUlYZqlCfc5bMzZC8gO3wcl8kivC4VT_BLaMG8ZNqEpyrNiDjtLvaZYQMwkfv4MKLVeRkSyD8YgkOPZ7tobGVhKzMDcam4dmTFJF9ExneBxyx0-t4AIPb08jsDfYXhB6dvCclNQNYodJixKKDIG0qiH40YmVHTScobFGpJgEd91k58nuobfTcHWtuqlg2tFonbkVbz4Gqv-g84ICL1TwYFCCRXCuWTstvGBAZaqD8Qk0_P8VsmLIvCVyWpgl2N-e5kiSJQjb5aqwx0CqWt1yfWKesPdgia7-KGi4g9YEk9y9ku4Y_iXOWYpFOEyqK06nu12LOH8OY-rEBjMIAa4kSKzUERebermy00NoWJfC4rTjDAJrWjPH2tCG75kdj-51jt6FUs_YWhIbdQMk8yHD3ums9JpRIwTzRmsjjWbVwLOA21aylMwh4_ZgtWtewWPm8G4wG-r43JqvVmOgHH5ttyD1PAAtjt-e77gcQ-j9BAwwO8XqtS_MaufhX6ZwEIyKIWTx5eFZzhUOWv6b_qfYI-LrEfvv1Uu-KtriU81RWiTA; expires=Tue, 18 Jun 2019 17:27:19 GMT; domain=.qinfabu.com; path=/; samesite=lax; httponly";
        String s = HttpRequest.sendPost1(url, param, cookieVal);
        jedis.close();
        return s;
    }


    /**
     * 随机生成文本
     * @param ss
     * @return
     */
    public String content(String ss){

        ss = ss.replace("\n", "");
        String[] split = ss.split("，");
        String content="";
//        第一段
        for (int i1 = 0; i1 < 3; i1++) {
            int i = (int) (Math.random() * split.length);
            content+=split[i];
        }
        char c = content.charAt(content.length()-1);
        if(c!='。'){
            content+="。\r\n";
        }else{
            content+="\r\n";
        }

//        第二段
        for (int i1 = 0; i1 < 10; i1++) {
            int i = (int) (Math.random() * split.length);
            content+=split[i];
        }
        if(c!='。'){
            content+="。\r\n";
        }else{
            content+="\r\n";
        }

//        第三段
        for (int i1 = 0; i1 < 5; i1++) {
            int i = (int) (Math.random() * split.length);
            content+=split[i];
        }
        if(c!='。'){
            content+="。\r\n";
        }else{
            content+="\r\n";
        }
        return content;
    }

    /**
     *  生成标题
     * @param
     * @param keyWords
     * @return
     */
    public String createTitle(String categoryName,String locationName,String keyWords,Integer userId,String brand,String model,String product) {

        try {
        /*++++++++++++++++++第1步：通过参数id，获取对应的参数名称+++++++++++++++++*/
            String[] strings = keyWords.split(" ");
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        /*++++++++++++++++++第2步：生成标题+++++++++++++++++*/
            List<String> titleList=new ArrayList<>();
            String title="";
            log.error("总共产生"+strings.length+"个标题");
            for (int i2 = 0; i2 < strings.length; i2++) {
                title=locationName+categoryName+brand+model+strings[i2]+product;
                titleList.add(title);
                log.error("第{}个标题，题目为：{}",i2,strings[i2]);
            }
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        /*++++++++++++++++++第3步：标题列表存储到session中+++++++++++++++++*/
            Jedis jedis = JedisUtil.getJedis();
            jedis.hset("title",userId+"", JsonUtil.toJson(titleList));
            jedis.close();
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }


}
