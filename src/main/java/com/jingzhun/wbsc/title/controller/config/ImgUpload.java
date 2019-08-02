package com.jingzhun.wbsc.title.controller.config;

import com.jingzhun.wbsc.util.JsonUtil;

import java.util.List;
import java.util.Map;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.reflect.annotation.ExceptionProxy;

/**
 * Created by Administrator on 2019/7/30 0030.
 */
public class ImgUpload {

    public static String chooseMethod(Document doc,String identification) throws Exception {
        String url="";
        switch (identification) {
            case "qinfabu":
                url = qinfabu(doc);
                break;
            case "eastsoo":
                url = eastsoo(doc);
                break;
            case "shengyibao":
                url = shengyibao(doc);
                break;
            case "dianzishangwu":
                url = dianzishangwu(doc);
                break;
            case "zhizaojiaoyi":
                url = zhizaojiaoyi(doc);
                break;
            case "qiyegu":
                url = qiyegu(doc);
                break;
            case "bafang":
                url = bafang(doc);
                break;
        }
        return url;
    }
    /**
     * 1. 勤发布
     * @param doc
     * @return
     */
        public static String qinfabu(Document doc) throws Exception{
            String text = doc.text();
            Map map = JsonUtil.toObject(text, Map.class);
            Object msg = map.get("msg");
//            //image.qinfabu.com/img/2019/7/30/20190730125137702.jpg
            System.out.println(msg.toString());
            return msg.toString();
        }

    /**
     * 2. 东方供应网
     * @param doc
     * @return
     */
    public static String eastsoo(Document doc) throws Exception{
            String file1 = doc.text();
            Map<String,List<String>> map = JsonUtil.toObject(file1, Map.class);
            System.out.println(file1);
            List<String> message = map.get("message");
//            http://img.eastsoo.com/eastsoo/191/3833608449/small_2019-07-30-13-01-28-2715.jpg
            System.out.println(message.get(1));
            return message.get(0);
        }


    /**
     *  3. 生意宝
     * @param doc
     * @return
     */
    public static String shengyibao(Document doc) throws Exception{
        String pic = doc.text();
        Map map = JsonUtil.toObject(pic, Map.class);
        Object msg = map.get("msg");
//            http://my.i.album.toocle.com/100-100-0-1/2019/07/30/9b/5d3fd0f57e89b.jpg
        StringBuilder sb=new StringBuilder("http://my.i.album.toocle.com/100-100-0-1/");
        sb.append(msg);
        System.out.println("pic:"+sb.toString());
        return sb.toString();
    }

    /**
     * 4. 电子商务  成功率偏低
     * @param doc
     * @return
     */
    public static String dianzishangwu(Document doc) throws Exception{
        String script = doc.body().select("script").get(0).toString();
        String substring = script.substring(script.indexOf("(")+2, script.indexOf(",")-1);
//              http://www.cebn.cn/file/upload/201907/30/14150172377370.jpg.thumb.jpg
        System.out.println(substring);
        return substring;
    }

    /**
     * 5. 制造交易网   参数为动态
     * @param doc
     * @return
     */
    public static String zhizaojiaoyi(Document doc) throws Exception{
        Map map1 = JsonUtil.toObject(doc.text(), Map.class);
        String result = map1.get("result").toString();
        StringBuilder sb = new StringBuilder("http://img.c-c.com/");
        StringBuilder append = sb.append(result);
//            http://img.c-c.com/NTimg/2019/07/30/15/bc5dd1497ebf7287b43af1a6bca854501.jpg
        System.out.println(append);
        return append.toString();
    }

    /**
     * 6. 企业谷
     * @param doc
     * @return
     */
    public static String qiyegu(Document doc) throws Exception{
        String script = doc.body().select("script").toString();
        String substring = script.substring(script.indexOf("(")+2, script.indexOf(",")-1);
//            http://www.qiyegu.com/file/upload/201907/30/15042449627538.jpg.thumb.jpg
        System.out.println(substring);
        return substring;
    }

    /**
     * 7. 八方资源网
     * @param doc
     * @return
     */
    public static String bafang(Document doc) throws Exception{
        Elements select = doc.select("body").select("script");
        Element element = select.get(0);
        String data = element.data();
        String result=data.split("}")[0];
        String imgWebUrl=result.substring(result.indexOf("src")+5,result.length()-1);
        System.out.println("上传图片url为："+imgWebUrl);
        return imgWebUrl;
    }



}
