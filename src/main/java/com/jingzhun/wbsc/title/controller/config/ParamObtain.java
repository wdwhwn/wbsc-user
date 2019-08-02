package com.jingzhun.wbsc.title.controller.config;

import org.jsoup.nodes.Document;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2019/7/31 0031.
 */
public class ParamObtain {


   /* String categoryName = paramMap0.get("categoryName");
    String locationId = paramMap0.get("locationId");
    String keywords = paramMap0.get("keywords");
    String brand = paramMap0.get("brand");
    String model = paramMap0.get("model");
    String product = paramMap0.get("product");*/

    /**
     * 根据缩略词获取方法  获取前端页面传递的参数
     * @param request
     * @param identification
     * @return
     * @throws Exception
     */
    public static Map<String, String> chooseParamMap(HttpServletRequest request, String identification)  {
        Map<String, String> paramMap = new HashMap<>();
        switch (identification) {
            case "qinfabu":
                paramMap= qinfabu(request);
                break;
            case "eastsoo":
                paramMap = eastsoo(request);
                break;
            case "shengyibao":
                paramMap = shengyibao(request);
                break;
            case "dianzishangwu":
                paramMap = dianzishangwu(request);
                break;
            case "zhizaojiaoyi":
                paramMap = zhizaojiaoyi(request);
                break;
            case "qiyegu":
//                url = qiyegu(doc);
                break;
            case "bafang":
//                url = bafang(doc);
                break;
        }
        return paramMap;
    }


    /*public String paramObtain(String locationId, String categoryName, String keywords, String keywords1,
                              String keywords2, String brand, String model, Integer day, Integer hour,
                              Integer minute, Integer second, String product,HttpServletRequest request) throws Exception {*/
    /**
     * 勤发布
     * @param request
     * @return
     */
    public static Map<String,String> qinfabu(HttpServletRequest request){
        Map<String,String> parameterMap = getParameterMap(request);
        String keywords = parameterMap.get("keywords");
        String keywords1 = parameterMap.get("keywords1");
        String keywords2 = parameterMap.get("keywords2");
        if (keywords1 != null) {
            keywords = keywords + " " + keywords1;
        }
        if (keywords2 != null) {
            keywords = keywords + " " + keywords2;
        }
        parameterMap.put("keywords",keywords);
        parameterMap.put("ATTRVALUE[BRAND]","白底");
        parameterMap.put("ATTRVALUE[MODEL]","452");
        parameterMap.put("categoryId","3000101101");
        return parameterMap;
    }


    /**
     * 东方供应网
     * @param request
     * @return
     */
    public static Map<String,String> eastsoo(HttpServletRequest request){
        Map<String,String> parameterMap = getParameterMap(request);
        parameterMap.put("keywords","");
        parameterMap.put("locationId","");
        parameterMap.put("brand","");
        parameterMap.put("model","");
        parameterMap.put("product","");
        parameterMap.put("categoryId","10111078");
        return parameterMap;
    }

    /**
     * 生意宝
     * @param request
     * @return
     */
    public static Map<String,String> shengyibao(HttpServletRequest request){
        Map<String,String> parameterMap = getParameterMap(request);
//        生成标题使用参数
        parameterMap.put("keywords","");
        parameterMap.put("locationId","");
        parameterMap.put("brand","");
        parameterMap.put("model","");
        parameterMap.put("product","");
//        从前端获取的是name
        parameterMap.put("categoryId","13");
        return parameterMap;
    }

    /**
     * 中国电子商务
     * @param request
     * @return
     */
    public static Map<String,String> dianzishangwu(HttpServletRequest request){
        Map<String,String> parameterMap = getParameterMap(request);
//        生成标题使用参数
        parameterMap.put("keywords","");
        parameterMap.put("locationId","");
        parameterMap.put("brand","");
        parameterMap.put("model","");
        parameterMap.put("product","");
//        从前端获取的是name
        parameterMap.put("categoryId","6168095");
        return parameterMap;
    }


    /**
     * 制造交易
     * @param request
     * @return
     */
    public static Map<String,String> zhizaojiaoyi(HttpServletRequest request){
        Map<String,String> parameterMap = getParameterMap(request);
//        生成标题使用参数
        parameterMap.put("keywords","");
        parameterMap.put("locationId","");
//        parameterMap.put("brand","");
        parameterMap.put("model","");
        parameterMap.put("product","");
//        从前端获取的是name
        parameterMap.put("categoryId","72441");
        return parameterMap;
    }

    public static Map<String,String> getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map properties = request.getParameterMap();
        // 返回值Map
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i=0;i<values.length;i++){
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length()-1);
            }else{
                value = valueObj.toString();
            }
            returnMap.put(name, value);
            System.out.println(name+"----->"+value);
        }

        return returnMap;
    }



}
