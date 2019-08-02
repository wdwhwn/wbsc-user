package com.jingzhun.wbsc.title.controller.config;

import com.jingzhun.wbsc.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/8/1 0001.
 */
@Slf4j
public class ResultList {
    /**
     * 获取用户发送成功的信息列表
     * @param doc  获取的document对象
     * @param identification
     * @param dataGrid
     * @return
     * @throws Exception
     */
    public static DataGrid chooseResultList(Document doc, String identification, DataGrid dataGrid) throws Exception {
        switch (identification) {
            case "qinfabu":
                dataGrid = qinfabu(doc,dataGrid);
                break;
            case "eastsoo":
                dataGrid = eastsoo(doc,dataGrid);
                break;
            case "shengyibao":
                dataGrid = shengyibao(doc,dataGrid);
                break;
            case "dianzishangwu":
                dataGrid = dianzishangwu(doc,dataGrid);
                break;
            case "zhizaojiaoyi":
                dataGrid = zhizaojiaoyi(doc,dataGrid);
                break;
            case "qiyegu":
//                dataGrid = qiyegu(doc,dataGrid);
                break;
            case "bafang":
//                dataGrid = bafang(doc,dataGrid);
                break;
        }
        return dataGrid;
    }

    /**
     * 1. 勤发布
     * @param doc
     * @return
     */
    public static DataGrid qinfabu(Document doc, DataGrid dataGrid) throws Exception{
        List<Map<String, Object>> resultMapList=new ArrayList<Map<String,Object>>();
        Elements elements = doc.select("ul[class=itemer]").select("div[class=m-mid]");
        if(elements.size()==0){
            System.out.println("没有结果");
            dataGrid.setTotal(0);
        }else{
            for (int i = 0; i < elements.size(); i++) {
                Map<String, Object> conditionMap = new HashMap<String, Object>();
                conditionMap.put("id",i+1);
                Elements a = elements.get(i).select("a");
                String href = a.attr("href");
                href=href.substring(2);
                conditionMap.put("url",href);
                String title = a.attr("title");
                conditionMap.put("title",title);
                Elements span = elements.get(i).select("span[class=time-now]");
                String text = span.text();
                text= text.substring(5);
                conditionMap.put("time",text);
                resultMapList.add(conditionMap);
            }
            dataGrid.setTotal(elements.size());
            dataGrid.setResults(resultMapList);
        }
                /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        return dataGrid;
    }


    /**
     * 2. 东方供应网
     * @param doc
     * @return
     */
    public static DataGrid eastsoo(Document doc, DataGrid dataGrid) throws Exception{
        List<Map<String, Object>> resultMapList=new ArrayList<Map<String,Object>>();

        if(true){
            System.out.println("没有结果");
            dataGrid.setTotal(0);
        }else{

//            dataGrid.setTotal(selects.size());
//            dataGrid.setResults(resultMapList);
        }
                /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        return dataGrid;
    }

    /**
     * 3.生意宝
     * @param doc
     * @return
     */
    public static DataGrid shengyibao(Document doc, DataGrid dataGrid) throws Exception{
        List<Map<String, Object>> resultMapList=new ArrayList<Map<String,Object>>();
        Elements elements = doc.getElementsByTag("ul").get(3).getElementsByTag("li");
        if(elements.size()<=0){
            System.out.println("没有结果");
            dataGrid.setTotal(0);
        }else{
            for (int i = 0; i < elements.size(); i++) {
                Map<String, Object> conditionMap = new HashMap<String, Object>();
                conditionMap.put("id",i+1);
                String href = elements.get(i).select("a[href]").attr("href");
                log.error("第{}个商机的url链接为:{}",i,href);
                conditionMap.put("url",href);
                String title = elements.get(i).select("a[href]").text();
                log.error("第{}个商机的标题为:{}",i,title.substring(0,title.indexOf("删除")));
                conditionMap.put("title",title.substring(0,title.indexOf("删除")));
                String text = elements.get(i).select("div[class=fjtime]").get(0).text();
                log.error("第{}个商机的发布时间为:{}",i,text.substring(5));
                conditionMap.put("time",text.substring(5));
                resultMapList.add(conditionMap);
            }
            dataGrid.setTotal(elements.size());
            dataGrid.setResults(resultMapList);
        }
                /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        return dataGrid;
    }

    /**
     * 4. 中国电子商务
     * @param doc
     * @return
     */
    public static DataGrid dianzishangwu(Document doc, DataGrid dataGrid) throws Exception{
        List<Map<String, Object>> resultMapList=new ArrayList<Map<String,Object>>();
        Elements elements = doc.select("tr[onmouseover]");
        if(elements.size()<=0){
            System.out.println("没有结果");
            dataGrid.setTotal(0);
        }else{
            for (int i = 0; i < elements.size(); i++) {
                Map<String, Object> conditionMap = new HashMap<String, Object>();
                conditionMap.put("id",i+1);
                String href = elements.get(i).select("td[title]").select("a[href]").attr("href");
                log.error("第{}个商机的url链接为:{}",i,href);
                conditionMap.put("url",href);
                String title = elements.get(i).select("td[title]").attr("title");
                log.error("第{}个商机的标题为:{}",i,title);
                conditionMap.put("title",title);
                String text = elements.get(i).select("td[class=f_gray px11]").get(0).attr("title").substring(5);
                log.error("第{}个商机的发布时间为:{}",i,text);
                conditionMap.put("time",text);
                resultMapList.add(conditionMap);
            }
            dataGrid.setTotal(elements.size());
            dataGrid.setResults(resultMapList);
        }
                /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        return dataGrid;
    }

    /**
     * 5. 制造交易网
     * @param doc
     * @return
     */
    public static DataGrid zhizaojiaoyi(Document doc, DataGrid dataGrid) throws Exception{
        List<Map<String, Object>> resultMapList=new ArrayList<Map<String,Object>>();
        System.out.println(doc.toString());
        String body = doc.select("body").text();
        body=body.substring(1,body.lastIndexOf("\""));
        body=body.replace("\\","");
        List<Map<String,String>> list = JsonUtil.toObject(body, List.class);
        if(list.size()<=0){
            System.out.println("没有结果");
            dataGrid.setTotal(0);
        }else{
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> conditionMap = new HashMap<String, Object>();
                conditionMap.put("id",i+1);

                //                url
                String href="http://hnjzsoft.ce.c-c.com/productinfo/"+list.get(i).get("id");
                System.out.println(href);
                conditionMap.put("url",href);
//                标题
                String title=list.get(i).get("title");
                System.out.println(title);
                conditionMap.put("title",title);
//                时间
                String text=list.get(i).get("updateTime");
                System.out.println(text);
                conditionMap.put("time",text);
                resultMapList.add(conditionMap);
            }
            dataGrid.setTotal(list.size());
            dataGrid.setResults(resultMapList);
        }
                /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        return dataGrid;
    }
}
