package com.jingzhun.wbsc;

import com.jingzhun.wbsc.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2019/6/11 0011.
 */
@Controller

public class A {
    private static final Logger log = LoggerFactory.getLogger(A.class);
    public static void main(String[] args) {
        java.util.Calendar instance = java.util.Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH);
        int day = instance.get(Calendar.DAY_OF_MONTH);
        int hour = instance.get(Calendar.HOUR_OF_DAY);
        int minute = instance.get(Calendar.MINUTE);
        String format = String.format("\n年份：%s，\n月份：%s，\n天：%s，\n时：%s，\n分：%s", year + "", month + 1+"", day + "", hour + "", minute + "");
        log.error(format);

    }
}
