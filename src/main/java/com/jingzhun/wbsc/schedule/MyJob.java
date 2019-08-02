package com.jingzhun.wbsc.schedule;

import com.jingzhun.wbsc.login.Login;
import com.jingzhun.wbsc.title.controller.config.ContentPush;
import com.jingzhun.wbsc.title.entity.TArticleEntity;
import com.jingzhun.wbsc.title.service.TArticleServiceI;
import com.jingzhun.wbsc.util.JsonUtil;
import com.jingzhun.wbsc.web.entity.TWebEntity;
import org.jeecgframework.web.system.service.SystemService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangDan
 * @data 2019/7/17 0017 19:26
 */
public class MyJob implements Job {

    private static final Logger log = LoggerFactory.getLogger(MyJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jdMap = jobExecutionContext.getJobDetail().getJobDataMap();
        System.out.println(jdMap.get("param"));
        String param = jdMap.get("param").toString();
        Map<String, String> paramMap = JsonUtil.toObject(param, Map.class);
        String userKey = paramMap.get("userKey");
        String webId = paramMap.get("webId");
//        获取用户名和密码    发送请求获取cookie
        String username = paramMap.get("username");
        String password = paramMap.get("password");
        Login login = ApplicationContextHelper.getBean(Login.class);
        SystemService systemService = ApplicationContextHelper.getBean(SystemService.class);
        TWebEntity tWebEntity = systemService.get(TWebEntity.class, Integer.parseInt(webId));
        Map<String, String> cookies = new HashMap<>();
        while(true) {
            try {
                cookies = login.getCookie(tWebEntity, username, password);
                if (cookies == null) {
                    System.out.println("获取cookie失败");
                    continue;
                }
                break;
            }catch (UnknownHostException e) {
                e.printStackTrace();
                log.error("网络不稳定，获取cookies失败");
                continue;
            } catch (Exception e) {
                e.printStackTrace();
                log.error("服务器端出错，请联系管理员");
                return;
            }
        }
        Map<String, String> map = new HashMap<String, String>();
        while(true) {
            try {
                map = ContentPush.chooseContentPush(paramMap, cookies);
            }catch (UnknownHostException e) {
                e.printStackTrace();
                log.error("网络不稳定，获取cookies失败");
                continue;
            } catch (Exception e) {
                e.printStackTrace();
                log.error("服务器端出错，请联系管理员");
                return;
            }
            break;
        }
        log.error("发布任务结果为：" + map.get("data"));
        if ("true".equals(map.get("data"))) {
            //        发布任务入库
            TArticleEntity tArticleEntity = new TArticleEntity();
            tArticleEntity.setParameter(map.get("param"));
            tArticleEntity.setGenerationTime(new Date());
            tArticleEntity.setScheduleid("0");
            tArticleEntity.setUserKey(userKey);
            tArticleEntity.setWebid(Integer.parseInt(webId));
            try {
                TArticleServiceI tArticleServiceI = ApplicationContextHelper.getBean(TArticleServiceI.class);
                tArticleServiceI.save(tArticleEntity);
                log.error("定时任务  入库成功");
            } catch (Exception e) {
                e.printStackTrace();
                log.error("任务历史入库失败");
                return ;
            }
            System.out.println(new Date() + ": " + jdMap.get("param") + " doing something...");
        }
    }


}