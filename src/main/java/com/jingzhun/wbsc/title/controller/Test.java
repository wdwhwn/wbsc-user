package com.jingzhun.wbsc.title.controller;


import com.alibaba.fastjson.JSONObject;
import com.jingzhun.wbsc.util.JsonUtil;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.timer.DynamicTask;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.jwt.util.JwtHttpUtil;
import org.jeecgframework.web.system.pojo.base.TSTimeTaskEntity;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.TimeTaskServiceI;
import org.jeecgframework.web.system.service.impl.SystemServiceImpl;
import org.jeecgframework.web.system.service.impl.TimeTaskServiceImpl;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.spring.web.json.Json;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2019/7/12 0012.
 */
public class Test {
       @Autowired
    private  TimeTaskServiceI timeTaskService;

    @Autowired(required=false)
    private DynamicTask dynamicTask;
    @Autowired
    private  SystemService systemService;
    private static final Logger log = LoggerFactory.getLogger(Test.class);

}
