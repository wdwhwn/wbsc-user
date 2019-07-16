package com.jingzhun.wbsc.schedule;

import com.jingzhun.wbsc.title.controller.Title;
import com.jingzhun.wbsc.util.JsonUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.quartz.Calendar;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/7/12 0012.
 */
@Service("schedule")
public class Schedule implements Job{
    private static final Logger log = LoggerFactory.getLogger(Schedule.class);
    private static Integer i=1;
    @Autowired
    private SystemService systemService;
    @Override
    public synchronized  void  execute(JobExecutionContext context) throws JobExecutionException {
        i++;
        System.out.println("测试定时任务"+i);
        log.error("this对象："+this);
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
            Title title = new Title();
            try {
                log.error("方法参数为："+map.get("locationId").toString()+map.get("categoryName").toString()+map.get("keywords").toString()+map.get("keywords1").toString()+map.get("keywords2").toString()+map.get("brand").toString()+map.get("model").toString()+map.get("product").toString());
                title.paramObtain(map.get("locationId").toString(),map.get("categoryName").toString(),map.get("keywords").toString(),map.get("keywords1").toString(),map.get("keywords2").toString(),map.get("brand").toString(),map.get("model").toString(),null,null,null,null,map.get("product").toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
        }else if(num>1){

        }
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

    }
}
