package com.jingzhun.wbsc.login.config;

import com.jingzhun.wbsc.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/8/2 0002.
 */
@Slf4j
public class CookieJudge {

    /**
     * 根据缩略词获取方法
     * @param
     * @return
     * @throws Exception
     */
    public static boolean chooseContentPush(String identification, Map<String,String> cookies)  {
        boolean f=false;
        switch (identification) {
            case "qinfabu":
                f= qinfabu(cookies);
                break;
            case "eastsoo":
                f = eastsoo(cookies);
                break;
            case "shengyibao":
                f = shengyibao(cookies);
                break;
            case "dianzishangwu":
                f = dianzishangwu(cookies);
                break;
            case "zhizaojiaoyi":
                f = zhizaojiaoyi(cookies);
                break;
            case "qiyegu":
//                url = qiyegu(doc);
                break;
            case "bafang":
//                url = bafang(doc);
                break;
        }
        return f;
    }

    /**
     * 勤发布
     * @param
     * @return
     */
    public static boolean qinfabu( Map<String,String> cookies){
        return cookies.containsKey("newwjlogin");
    }

    /**
     * 东方供应网
     * @param
     * @return
     */
    public static boolean eastsoo( Map<String,String> cookies){
        return cookies.containsKey("u");
    }

    /**
     * 生意宝
     * @param
     * @return
     */
    public static boolean shengyibao( Map<String,String> cookies){
        return cookies.containsKey("member_LOGIN_USER");
    }

    /**
     * 电子商务
     * @param
     * @return
     */
    public static boolean dianzishangwu( Map<String,String> cookies){
        return cookies.containsKey("DC1_username");
    }

    /**
     * 制造交易网
     * @param
     * @return
     */
    public static boolean zhizaojiaoyi( Map<String,String> cookies){
        return cookies.containsKey("WebClient");
    }


}
