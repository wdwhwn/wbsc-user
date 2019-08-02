package com.jingzhun.wbsc.title.controller.config;

import com.jingzhun.wbsc.util.JsonUtil;
import com.jingzhun.wbsc.util.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/8/1 0001.
 */
@Slf4j
public class ContentPush {
    /**
     * 根据缩略词获取方法
     * @param
     * @return
     * @throws Exception
     */
    public static Map<String, String> chooseContentPush(Map<String,String>paramMap0,Map<String,String> cookies) throws Exception {
        Map<String, String> paramMap = new HashMap<>();
        switch (paramMap0.get("identification")) {
            case "qinfabu":
                paramMap= qinfabu(paramMap0,cookies);
                break;
            case "eastsoo":
                paramMap = eastsoo(paramMap0,cookies);
                break;
            case "shengyibao":
                paramMap = shengyibao(paramMap0,cookies);
                break;
            case "dianzishangwu":
                paramMap = dianzishangwu(paramMap0,cookies);
                break;
            case "zhizaojiaoyi":
                paramMap = zhizaojiaoyi(paramMap0,cookies);
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




    /**
     * 勤发布
     * @param
     * @return
     */
    public static Map<String,String> qinfabu(Map<String,String>paramMap0,Map<String,String> cookies) throws IOException {
        /*++++++++++++++++++第1步：准备参数+++++++++++++++++*/
        List<String> list = JsonUtil.toObject(paramMap0.get("titleList"), List.class);
        int i = (int) (Math.random() * list.size());
        String title = list.get(i);
//        typeid 类型id
//      unit  单位
        String unit = "件";
//      price 单价
        String price = "";
//        keyWords
//        id
        String id = "";
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
        /*++++++++++++++++++第1步：+++++++++++++++++*/
        String url = "http://my.qinfabu.com/Product/Add";
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("title=TITLE")
                .append("&pics=PICS")
                .append("&typeid=TYPEID")
                .append("&AttrValue[0][name]=品牌/厂家")
                .append("&AttrValue[0][value]=ATTRVALUE[BRAND]")
                .append("&AttrValue[0][typeid]='1'")
                .append("&AttrValue[1][name]='型号'")
                .append("&AttrValue[1][value]=ATTRVALUE[MODEL]")
                .append("&AttrValue[1][typeid]='1'")
                .append("&price=")
                .append("&unit=UNIT")
                .append("&id=")
                .append("&data=DATA");
        String param = stringBuilder.toString();
        String replaceParam = param.replace("TITLE", title)
                .replace("KEYWORD", paramMap0.get("keywords"))
                .replace("PICS", paramMap0.get("img"))
                .replace("TYPEID", paramMap0.get("categoryId"))
                .replace("DATA", paramMap0.get("content"))
                .replace("ATTRVALUE[BRAND]", paramMap0.get("ATTRVALUE[BRAND]"))
                .replace("ATTRVALUE[MODEL]", paramMap0.get("ATTRVALUE[MODEL]"));
        replaceParam = replaceParam.replace("'", "");
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
        String identification = paramMap0.get("identification");
        StringBuilder sb=new StringBuilder("headfile/");
        sb.append(identification);
        sb.append("/");
        sb.append(identification);
        sb.append("_xxts.properties");
        Map<String, String> xxtsHeadMap = PropertiesUtil.getProperties(sb.toString());
//        Map<String, String> xxtsHeadMap = PropertiesUtil.getProperties("headfile/qinfabu/qinfabu_xxts.properties");
        Document doc = Jsoup.connect(url).ignoreContentType(true).ignoreHttpErrors(true)
                .headers(xxtsHeadMap)
                .cookies(cookies)
                .requestBody(replaceParam)
                .followRedirects(false)
                .post();
        String text = doc.text();
        System.out.println("text:"+text);
        String s = "true";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("param", replaceParam);
        map.put("data", s);
        return map;
    }

    /**
     * 东方供应网
     * @param
     * @return
     */
    public static Map<String,String> eastsoo(Map<String,String>paramMap0,Map<String,String> cookies) throws IOException {
        /*++++++++++++++++++第1步：准备参数+++++++++++++++++*/
        /*List<String> list = JsonUtil.toObject(paramMap0.get("titleList"), List.class);
        int i = (int) (Math.random() * list.size());
        String title = list.get(i);*/
        String title="测试苏荷晒带亲朋好选择酒水";
        String content="公司提供中餐、西餐及食材的加工和销售，餐饮管理公司是掌握管理技能和独特技术、运用品牌、专利和服务优势，开展餐饮运营的经济组织。餐饮管理公司的组织机构一般采用职能部制，只有开展连锁经营业务的公司，采取事业部制";
        String url = "http://user.eastsoo.com/biz/business_add.do?cmd=submit&ajax_submit_random=0.09541667255170852";
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder
                .append("title=TITLE")
                .append("pic_id=PICS")
                .append("sort_id=TYPEID")
                .append("sort_custom_id=")
                .append("content=DATA")
                .append("unit=UNIT")
                .append("num=NUM")
                .append("price_w=PRICEW")
                .append("num_w=NUMW")
                .append("id=0")
                .append("status=0")
                .append("price=PRICE");
        String param=stringBuilder.toString();

        String replaceParam = param.replace("TITLE", title)
                .replace("PICS", paramMap0.get("img"))
                .replace("TYPEID", paramMap0.get("categoryId"))
                .replace("DATA", content)
                .replace("UNIT", paramMap0.get("unit"))
                .replace("NUM", paramMap0.get("num"))
                .replace("PRICEW", paramMap0.get("price_w"))
                .replace("NUMW", paramMap0.get("num_w"))
                .replace("PRICE", paramMap0.get("price"));
        replaceParam = replaceParam.replace("'", "");
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
        String identification = paramMap0.get("identification");
        StringBuilder sb=new StringBuilder("headfile/");
        sb.append(identification);
        sb.append("/");
        sb.append(identification);
        sb.append("_xxts.properties");
        Map<String, String> xxtsHeadMap = PropertiesUtil.getProperties(sb.toString());
//        Map<String, String> xxtsHeadMap = PropertiesUtil.getProperties("headfile/eastsoo/qinfabu_xxts.properties");
        Document doc = Jsoup.connect(url).ignoreContentType(true).ignoreHttpErrors(true)
                .headers(xxtsHeadMap)
                .cookies(cookies)
                .requestBody(replaceParam)
                .post();
        String text = doc.text();
        System.out.println("信息发布结果为:"+text);
        String s = "true";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("param", replaceParam);
        map.put("data", s);
        return map;
    }


    /**
     * 生意宝
     * @param
     * @return
     */
    public static Map<String,String> shengyibao(Map<String,String>paramMap0,Map<String,String> cookies) throws IOException {
        /*++++++++++++++++++第1步：准备参数+++++++++++++++++*/
        /*List<String> list = JsonUtil.toObject(paramMap0.get("titleList"), List.class);
        int i = (int) (Math.random() * list.size());
        String title = list.get(i);*/
        String title="诚德丰餐饮大酬宾";
        String content="公司提供中餐、西餐及食材的加工和销售，餐饮管理公司是掌握管理技能和独特技术、运用品牌、专利和服务优势，开展餐饮运营的经济组织。餐饮管理公司的组织机构一般采用职能部制，只有开展连锁经营业务的公司，采取事业部制";
        String url = "http://leads.toocle.com/?_a=main&f=trade_create";
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder
                .append("category=CATEGORY")
                .append("intro= INTRO")
                .append("title=TITLE")
                .append("info=")
                .append("type=2")
                .append("pic1=PIC1")
                .append("pic2=")
                .append("pic3=")
                .append("cate_id=")
                .append("sign=1917c7190e2113fd472143960dd2135b");
        String param=stringBuilder.toString();
        String replaceParam = param.replace("TITLE", title)
                .replace("PICS", paramMap0.get("img"))
                .replace("CATEGORY", paramMap0.get("categoryId"))
                .replace("INTRO", content);
        replaceParam = replaceParam.replace("'", "");
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
        String identification = paramMap0.get("identification");
        StringBuilder sb=new StringBuilder("headfile/");
        sb.append(identification);
        sb.append("/");
        sb.append(identification);
        sb.append("_xxts.properties");
        Map<String, String> xxtsHeadMap = PropertiesUtil.getProperties(sb.toString());
//        Map<String, String> xxtsHeadMap = PropertiesUtil.getProperties("headfile/eastsoo/qinfabu_xxts.properties");
        Document doc = Jsoup.connect(url).ignoreContentType(true).ignoreHttpErrors(true)
                .headers(xxtsHeadMap)
                .cookies(cookies)
                .requestBody(replaceParam)
                .post();
        String text = doc.text();
        log.error("信息发布结果为:"+text);
        String s = "true";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("param", replaceParam);
        map.put("data", s);
        return map;
    }

    /**
     * 中国电子商务
     * @param
     * @return
     */
    public static Map<String,String> dianzishangwu(Map<String,String>paramMap0,Map<String,String> cookies) throws IOException {
        /*++++++++++++++++++第1步：准备参数+++++++++++++++++*/
        /*List<String> list = JsonUtil.toObject(paramMap0.get("titleList"), List.class);
        int i = (int) (Math.random() * list.size());
        String title = list.get(i);*/
        String title="苏荷分丰餐饮大酬宾";
        String content="公司提供中餐、西餐及食材的加工和销售，餐饮管理公司是掌握管理技能和独特技术、运用品牌、专利和服务优势，开展餐饮运营的经济组织。餐饮管理公司的组织机构一般采用职能部制，只有开展连锁经营业务的公司，采取事业部制";
        String url = "http://www.cebn.cn/member/my.php";
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder
                .append("action=add")
                .append("mid=5")                //                6_求购   5_供应
                .append("itemid=0")
                .append("forward=http://www.cebn.cn/member/")
                .append("post[typeid]=0")//                0_供应  1_提供服务  2_提供二手 3_提供加工  4_提供合作  5_库存
                .append("post[title]=TITLE") //                标题
                .append("color=")
                .append("post[catid]=CATEGORY") //                类别
                .append("post[brand]=BRAND")//                品牌
                .append("post[content]=CONTENT")//                文本内容
                .append("post[thumb]=IMG")//                图片
                .append("post[thumb1]=")
                .append("post[thumb2]=")
                .append("post[totime]=")
                .append("post[n1]=")
                .append("post[v1]=")
                .append("post[n2]=")
                .append("post[v2]=")
                .append("post[n3]=")
                .append("post[v3]=")
                .append("post[unit]=UNIT")//                单位
                .append("post[price]=PRICE")//                价格
                .append("post[minamount]=MINAMOUNT")//                最少订购数量
                .append("post[amount]=AMOUNT")//                供货总量
                .append("post[days]=DAYS")//                3天内发货
                .append("post[mycatid]=0")//                自定义分类id
                .append("post[elite]=0")//                我的推荐 否_0
                .append("submit= 提 交");
        String param=stringBuilder.toString();
        String replaceParam = param.replace("TITLE", title)
                .replace("CATEGORY", paramMap0.get("categoryId"))
                .replace("BRAND", paramMap0.get("brand"))
                .replace("CONTENT", content)
                .replace("IMG", paramMap0.get("post[unit]"))
                .replace("PRICE", paramMap0.get("post[price]"))
                .replace("MINAMOUNT", paramMap0.get("post[minamount]"))
                .replace("AMOUNT", paramMap0.get("post[amount]"))
                .replace("DAYS", paramMap0.get("post[days]"));
        replaceParam = replaceParam.replace("'", "");
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
        String identification = paramMap0.get("identification");
        StringBuilder sb=new StringBuilder("headfile/");
        sb.append(identification);
        sb.append("/");
        sb.append(identification);
        sb.append("_xxts.properties");
        Map<String, String> xxtsHeadMap = PropertiesUtil.getProperties(sb.toString());
//        Map<String, String> xxtsHeadMap = PropertiesUtil.getProperties("headfile/eastsoo/qinfabu_xxts.properties");
        Document doc = Jsoup.connect(url).ignoreContentType(true).ignoreHttpErrors(true)
                .headers(xxtsHeadMap)
                .cookies(cookies)
                .requestBody(replaceParam)
                .post();
        String text = doc.text();
        log.error(doc.toString());
        log.error("信息发布结果为:"+text);
        String s = "true";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("param", replaceParam);
        map.put("data", s);
        return map;
    }

    /**
     * 中国电子商务
     * @param
     * @return
     */
    public static Map<String,String> zhizaojiaoyi(Map<String,String>paramMap0,Map<String,String> cookies) throws IOException {
        /*++++++++++++++++++第1步：准备参数+++++++++++++++++*/
        /*List<String> list = JsonUtil.toObject(paramMap0.get("titleList"), List.class);
        int i = (int) (Math.random() * list.size());
        String title = list.get(i);*/
        String title="苏荷分丰餐饮大酬宾";
        String protitle="protitle";
        String content="公司提供中餐、西餐及食材的加工和销售，餐饮管理公司是掌握管理技能和独特技术、运用品牌、专利和服务优势，开展餐饮运营的经济组织。餐饮管理公司的组织机构一般采用职能部制，只有开展连锁经营业务的公司，采取事业部制";
        String url = "http://myv2.cn.c-c.com/member/Trade/addInfo";
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder
                .append("protitle=PROTITLE")//                产品名称
                .append("title=TITLE")//                 推广标题
                .append("key=KEY1")//                 关键词1
                .append("key=KEY2")//                 关键词2
                .append("key=KEY3")//                 关键词3
                .append("pAtt=0001002800020001")//              产品属性
                .append("pAtta0=BRAND")//              品牌
                .append("musth=0|input")
                .append("pAtta1=")
                .append("pAtta2=")
                .append("pAtta3=")
                .append("pAtta4=")
                .append("pAtta5=52")
                .append("musth=5|input")
                .append("pAtta6=高粱")
                .append("musth=6|input")
                .append("pAtta7=")
                .append("pAtta8=请选择")
                .append("pAttb8=")
                .append("pAtta9=")
                .append("pAtta10=请选择")
                .append("pAttb10=")
                .append("pAtta11=请选择")
                .append("pAttb11=")
                .append("pAtta12=请选择")
                .append("pAttb12=")
                .append("pAtta13=请选择")
                .append("pAttb13=")
                .append("tCon=0001002800020001")
                .append("tCona0=TCONA0")//                        单位
                .append("tCona1=TCONA1")//                        单价
                .append("tCona2=TCONA2")//                        起订量
                .append("tCona3=TCONA3")//                        供货总量
                .append("tCona4=TCONA4")//                        3天内发货
                .append("ValidityDate=VALIDITYDATE")//                        有效期
                .append("hImg1=IMG")//                        图片
                .append("hImg2=")
                .append("hImg3=")
                .append("editor=CONTENT")
                .append("editImg=")
                .append("ddlCategory=CATEGORY")//              类别
                .append("tuijia=1") //                        1_推荐  2_特别推荐
                .append("hTitle=")
                .append("id=")
                .append("Content=")
                .append("tradeType=0|1|0|0")
//                批量发送时的参数
                .append("topCategoryId=00010028") //                        1级类别
                .append("secondCategoryId=000100280002")//                        二级类别
                .append("thirdCategoryId=0001002800020001")//                        3级类别
                .append("autoName=")
                .append("autoValue=")
                .append("categoryName=")
                .append("CatagoryId=")
                .append("5LOOR08AFA5LOOR08AFASXG=5LOOR08AFASXG")
                .append("5LOOR08AFASXG5LOOR08AFA=5LOOR08AFASXG")
                .append("isVouch=0")
                .append("X9831261=625178")
                .append("X-Requested-With=XMLHttpRequest");
        String param=stringBuilder.toString();
        String replaceParam = param.replace("TITLE", title)
                .replace("PROTITLE", protitle)
                .replace("BRAND", paramMap0.get("brand"))
                .replace("KEY1", paramMap0.get("keywords"))
                .replace("KEY2", paramMap0.get("keywords1"))
                .replace("KEY3", paramMap0.get("keywords2"))
                .replace("TCONA0", paramMap0.get("tCona0"))
                .replace("TCONA1", paramMap0.get("tCona1"))
                .replace("TCONA2", paramMap0.get("tCona2"))
                .replace("TCONA3", paramMap0.get("tCona3"))
                .replace("TCONA4", paramMap0.get("tCona4"))
                .replace("VALIDITYDATE", paramMap0.get("ValidityDate"))
                .replace("CATEGORY", paramMap0.get("categoryId"));
        replaceParam = replaceParam.replace("'", "");
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
        String identification = paramMap0.get("identification");
        StringBuilder sb=new StringBuilder("headfile/");
        sb.append(identification);
        sb.append("/");
        sb.append(identification);
        sb.append("_xxts.properties");
        Map<String, String> xxtsHeadMap = PropertiesUtil.getProperties(sb.toString());
//        Map<String, String> xxtsHeadMap = PropertiesUtil.getProperties("headfile/eastsoo/qinfabu_xxts.properties");
        Document doc = Jsoup.connect(url).ignoreContentType(true).ignoreHttpErrors(true)
                .headers(xxtsHeadMap)
                .cookies(cookies)
                .requestBody(replaceParam)
                .post();
        String text = doc.text();
        log.error(doc.toString());
        log.error("信息发布结果为:"+text);
        String s = "true";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("param", replaceParam);
        map.put("data", s);
        return map;
    }
}
