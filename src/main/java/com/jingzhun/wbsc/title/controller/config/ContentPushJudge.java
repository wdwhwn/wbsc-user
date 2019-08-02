package com.jingzhun.wbsc.title.controller.config;

import com.jingzhun.wbsc.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2019/8/2 0002.
 */
@Slf4j
public class ContentPushJudge {
    /**
     * 根据缩略词获取方法
     * @param
     * @return
     * @throws Exception
     */
    public static Map<String,String> chooseContentPush(String identification,Document document )  {
        Map<String, String> resultMap = new HashMap<>();
        switch (identification) {
            case "qinfabu":
                resultMap= qinfabu(document);
                break;
            case "eastsoo":
                resultMap = eastsoo(document);
                break;
            case "shengyibao":
                resultMap = shengyibao(document);
                break;
            case "dianzishangwu":
                resultMap = dianzishangwu(document);
                break;
            case "zhizaojiaoyi":
                resultMap = zhizaojiaoyi(document);
                break;
            case "qiyegu":
//                url = qiyegu(doc);
                break;
            case "bafang":
//                url = bafang(doc);
                break;
        }
        return resultMap;
    }

    /**
     * 勤发布
     * @param
     * @return
     */
    public static Map<String,String> qinfabu( Document document){
        log.error(document.toString());
        HashMap<String, String> resultMap = new HashMap<>();
        Map<String,Map<String,Object>> map= JsonUtil.toObject(document.body().text(),Map.class);
        if(!"0".equals(map.get("ErrorMsg").get("ErrCode"))) {
            String message=map.get("ErrorMsg").get("ErrMsg").toString();
            resultMap.put("status","fail");
            resultMap.put("message",message);
            System.out.println("结果为：" + map.get("ErrorMsg").get("ErrMsg"));
            return resultMap;
        }
        resultMap.put("status","success");
        resultMap.put("message","发布成功");
        return resultMap;
    }

    /**
     * 东方供应网
     * @param
     * @return
     */
    public static Map<String,String> eastsoo( Document document){
        //           失败  {"status":"err","message":"\u6807\u9898\u5df2\u5b58\u5728","id":null}
//           成功  {"status":"ok","message":"","id":null}
        String text=document.text();
        System.out.println(text);
        HashMap<String, String> resultMap = new HashMap<>();
        Map<String,String> map = JsonUtil.toObject(text, Map.class);
        if(!"ok".equals(map.get("status"))) {
            String message=map.get("message");
            resultMap.put("status","fail");
            resultMap.put("message",message);
            return resultMap;
        }
        resultMap.put("status","success");
        resultMap.put("message","发布成功");
        return resultMap;
    }

    /**
     * 生意宝
     * @param
     * @return
     */
    public static Map<String,String> shengyibao( Document document){
        //            成功 <span class="red3">添加成功，等待审核！</span>
//            失败  <span class="red3">请不要重复发布！</span>
        String text = document.select("span[class=red3]").text();
        System.out.println("结果为："+text);
        HashMap<String, String> resultMap = new HashMap<>();
        if(!"添加成功，等待审核！".equals(text)) {
            String message=text;
            resultMap.put("status","fail");
            resultMap.put("message",message);
             return resultMap;
        }
        resultMap.put("status","success");
        resultMap.put("message","发布成功");
        return resultMap;
    }

    /**
     * 电子商务
     * @param
     * @return
     */
    public static Map<String,String> dianzishangwu( Document document){
        //        失败时    alert('您所在的会员组没有权限使用此功能，请升级');window.history.back();
//        失败      alert('余额不足')
        String script = document.body().select("script").toString();
        String alert = script.substring(script.indexOf("alert"), script.lastIndexOf(";"));
        System.out.println(alert);
        HashMap<String, String> resultMap = new HashMap<>();
        if(!"余额不足".equals(alert)){
            resultMap.put("status","fail");
            resultMap.put("message",alert);
            return resultMap;
        }
        if(!"您所在的会员组没有权限使用此功能，请升级".equals(alert)){
            resultMap.put("status","fail");
            resultMap.put("message",alert);
            return resultMap;
        }
        resultMap.put("status","success");
        resultMap.put("message","发布成功");
        return resultMap;
    }

    /**
     * 制造交易网
     * @param
     * @return
     */
    public static Map<String,String> zhizaojiaoyi( Document document){
        //            成功时   <body>1|140034139</body>
//             失败时
        String str = document.text();
        str=str.substring(2);
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        boolean f=pattern.matcher(str).matches();
        log.error(document.toString());
        HashMap<String, String> resultMap = new HashMap<>();
        if(!f) {
            resultMap.put("status","fail");
            resultMap.put("message","发布失败");
            return resultMap;
        }
        resultMap.put("status","success");
        resultMap.put("message","发布成功");
        return resultMap;
    }
}
