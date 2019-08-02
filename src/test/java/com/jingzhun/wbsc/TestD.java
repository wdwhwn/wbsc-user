package com.jingzhun.wbsc;

import com.jingzhun.wbsc.util.JsonUtil;
import com.jingzhun.wbsc.util.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文本生成
 */
@Slf4j
public class TestD {
    String code="UTF-8";
    @Test
    public void test0(){
        String ss="{\"ErrorMsg\":{\"ErrCode\":0,\"ErrMsg\":\"\"},\"Data\":{\"access_token\":\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiMTM5MzkwNTU1NTMiLCJNZW1iZXJJZCI6IjhlODk4YzEwLWJlNjAtNDkxOS05OTc4LTA5ZDY1MDJiYWJiZiIsImh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9jbGFpbXMvZXhwaXJhdGlvbiI6IjIwMTkvNy8yMCA1OjU1OjEzICswODowMCIsIm5iZiI6MTU2MzUxMzMxMywiZXhwIjoxNTYzNTczMzEzLCJpc3MiOiJ6amwiLCJhdWQiOiJldmVyeW9uZSJ9.h8LrgYIxOGMgA7IjIydSG1sDlGwvcvT4u2fAZ5Ewpjk\",\"expires_in\":\"60000000.0\"}}";
        Map<String,Object> map = JsonUtil.toObject(ss, Map.class);
        Object erorMsg = map.get("ErrorMsg");
        String s = JsonUtil.toJson(erorMsg);
//        log.error(s);
        Map map1 = JsonUtil.toObject(s, Map.class);
//        log.error(map1.get("ErrCode").toString());
    }

    /**
     * 勤发布-成功
     */
    @Test
    public void test1() throws IOException {
        String str0="jsoup";
        String str1="urlConnection";
        String str2="jsoup";
        String surl="http://my.qinfabu.com/Login";
        String param="username=13939055553&password=liu0119596";
        Map<String, String> loginHeadMap = PropertiesUtil.getProperties("headfile/qinfabu/qinfbu_login.properties");
        /*++++++++++++++++++第1步：模拟登录+++++++++++++++++*/
//       httpconnection 模拟登陆
        String cookie="";
        if(str0.equals(str1)) {
             cookie = login(surl, param);
        }
//        jsoup 模拟登陆
        Map<String, String> cookies=new HashMap<>();
        if(str0.equals(str2)){
            cookies= Jsoup
                    .connect(surl)
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .requestBody(param)
                    .method(Connection.Method.POST)
                    .headers(loginHeadMap)
                    .followRedirects(false)
                    .execute()
                    .cookies();
            log.error("勤发布："+cookies);
        }
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
        String url="http://my.qinfabu.com/Product/List";
        String param1="status=1";
        Map<String, String> headMap = PropertiesUtil.getProperties("headfile/qinfabu/qinfabu_xxts.properties");
        if(false&&str0.equals(str2)) {
            Document parse = Jsoup.connect(url).requestBody(param1).cookies(cookies).headers(headMap).post();
        /*++++++++++++++++++第3步：查询发布结果+++++++++++++++++*/
            Elements selects = parse.select("ul[class=itemer]").select("div[class=m-mid]");
            System.out.println("元素为：" + selects);
            if (selects.size() == 0) {
                System.out.println("没有结果");
            } else {
                for (int i = 0; i < selects.size(); i++) {
                    Elements a = selects.get(i).select("a");
                    String href = a.attr("href");
                    href = href.substring(2);
                    System.out.println("第" + i + "个产品的超链接为：" + href);
                    String title = a.attr("title");
                    System.out.println("第" + i + "个产品的标题为：" + title);
                    Elements span = selects.get(i).select("span[class=time-now]");
                    String text = span.text();
                    text = text.substring(5);
                    System.out.println("第" + i + "个产品的通过审核时间为：" + text);
                }
            }
            System.out.println("通过审核产品个数为：" + selects.size());
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/



        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
        }

     /*++++++++++++++++++第4步：信息推送+++++++++++++++++*/
        String param3="title=上海办公设备白泉桌椅齐全123&keyWord=桌椅 白泉 设备&pics[]=//image.qinfabu.com/img/2019/7/30/20190730171859235.jpg"+
                "&data=为此不少网友也总结了吃各种材料在火锅中的涮的时间牙龈出血运用专业的番茄熬汤机器进行4个多小时的熬制时刻将“以顾客为中心”做为服务理念建议痊愈后再吃火锅。 在吃火锅时使用甲醛可使毛肚吃起来更脆。 先用工业烧碱浸泡有些人常常要和一些火锅汤。他们认为：很多食材的的精华都在汤里面。其实在火锅汤里有许多嘌呤和亚盐。他们的浓度都很高就得到金仔！ 金仔的锅底选用的是锅中锅 中间的是番茄汤 纯番茄熬制 吃火锅之前来碗金仔番茄汤 保护你的肠胃健康。 金仔的番茄汤是由金仔聘请拥有30多年中餐经验的周师傅熬制而成不要混合。如果在液面交界处出现紫色环将会患上胃溃疡等疾病。" +
                "&typeid=3000101101\n" +
                "&AttrValue[0][name]=品牌/厂家\n" +
                "&AttrValue[0][value]=白泉\n" +
                "&AttrValue[0][typeid]=1\n" +
                "&AttrValue[1][name]=型号\n" +
                "&AttrValue[1][value]=13456\n" +
                "&AttrValue[1][typeid]=1\n" +
                "&price=\n" +
                "&unit=件\n" +
                "&id=";
        String xxtsUrl="http://my.qinfabu.com/Product/Add";
        Map<String, String> xxtsHeadMap = PropertiesUtil.getProperties("headfile/qinfabu/qinfabu_xxts.properties");
        if(str0.equals(str2)){
            Document doc = Jsoup.connect(xxtsUrl).ignoreContentType(true).ignoreHttpErrors(true)
                    .headers(xxtsHeadMap)
                    .cookies(cookies)
                    .requestBody(param3)
                    .post();
            System.out.println("结果为："+doc);
        }
        /*++++++++++++++++++第3步：图片上传+++++++++++++++++*/
       String imgUploadUrl="http://my.qinfabu.com/Upload/Upload";
        Map<String, String> imgUploadMap = PropertiesUtil.getProperties("headfile/qinfabu/qinfabu_imgupload.properties");
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("id","WU_FILE_0");
        stringStringHashMap.put("name","123.jpg");
        stringStringHashMap.put("type","image/jpeg");
        stringStringHashMap.put("lastModifiedDate","Fri Jul 26 2019 17:20:12 GMT+0800");
        stringStringHashMap.put("size","10001");
        File file = new File("E:\\jeecg\\wdwhwn-master\\wbsc-user\\src\\main\\resources\\123.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        if(false&&str0.equals(str2)){
            Document doc = Jsoup.connect(imgUploadUrl).ignoreContentType(true).ignoreHttpErrors(true)
                    .headers(imgUploadMap)
                    .cookies(cookies)
                    .data("file", file.getName(), fileInputStream)
                    .data(stringStringHashMap)
                    .post();
            String text = doc.text();
            Map map = JsonUtil.toObject(text, Map.class);
            Object msg = map.get("msg");
//            //image.qinfabu.com/img/2019/7/30/20190730125137702.jpg
            System.out.println(msg.toString());
        }
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
    }

    /**
     * 东方供应网-成功  查询结果，需要20条才可以看到结果
     */
    @Test
    public void test2() throws IOException {

        String str0="jsoup";
        String str1="urlConnection";
        String str2="jsoup";
        String surl="http://user.eastsoo.com/user/login.do";
        String param="user_name=hnjzsoft&user_password=hnjz123soft";
        /*++++++++++++++++++第1步：模拟登录+++++++++++++++++*/
        String cookie="";
        Map<String, String> loginHeadMap = PropertiesUtil.getProperties("headfile/qinfabu/eastsoo_login.properties");
        if(str0.equals(str1)){
            cookie= login(surl, param);
            log.error("东方供应网模拟登录成功："+cookie);
        }
        Map<String, String> cookies1=new HashMap<>();
        if(str0.equals(str2)){
            cookies1= Jsoup
                    .connect(surl)
                    .requestBody(param)
                    .method(Connection.Method.POST)
                    .followRedirects(false)
                    .headers(loginHeadMap)
                    .execute()
                    .cookies();
            log.error("东方供应网模拟登录成功："+cookies1);
        }
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

       /*++++++++++++++++++第2步：+++++++++++++++++*/
        if(false&&cookie!=null ) {
            String url = "http://user.eastsoo.com/user/index.do";
            String param1 = "";
            sendGet(url, param1, cookie, code);
        }
        log.error(System.getProperty("file.encoding"));
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/


       /*++++++++++++++++++第步：图片上传+++++++++++++++++*/
//        String imgUrl="http://user.eastsoo.com/biz/business_add.do?cmd=upload&ajax_submit_random=0.35630989224661924&PHP_SESSION_UPLOAD_PROGRESS=upload_img&sort_id=";
        String imgUrl="http://user.eastsoo.com/biz/business_add.do?cmd=upload&ajax_submit_random=0.35630989224661924";
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("PHP_SESSION_UPLOAD_PROGRESS","upload_img");
        stringStringHashMap.put("sort_id","");
        File file = new File("E:\\jeecg\\wdwhwn-master\\wbsc-user\\src\\main\\resources\\456.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        Map<String, String> xxtsHeadMap1 = PropertiesUtil.getProperties("headfile/qinfabu/eastsoo_uploadimg.properties");
        if(false&&str0.equals(str2)){
            Document doc = Jsoup.connect(imgUrl).ignoreContentType(true).ignoreHttpErrors(true)
                    .headers(xxtsHeadMap1)
                    .cookies(cookies1)
                    .data(stringStringHashMap)
                    .data("file", file.getName(), fileInputStream)
                    .post();
            String file1 = doc.text();
            Map<String,List<String>> map = JsonUtil.toObject(file1, Map.class);
            System.out.println(file1);
            List<String> message = map.get("message");
//            http://img.eastsoo.com/eastsoo/191/3833608449/small_2019-07-30-13-01-28-2715.jpg
            System.out.println(message.get(0));
            System.out.println(message.get(1));
        }

       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        String url="http://user.eastsoo.com/biz/business_add.do?cmd=submit&ajax_submit_random=0.09541667255170852 ";
        String parma="title=测试东阳亲朋好选择酒水&pic_id=8948162&sort_id=10111078&sort_custom_id=&content=公司提供中餐、西餐及食材的加工和销售，餐饮管理公司是掌握管理技能和独特技术、运用品牌、专利和服务优势，开展餐饮运营的经济组织。餐饮管理公司的组织机构一般采用职能部制，只有开展连锁经营业务的公司，采取事业部制&unit=件&num=5&price_w=96&num_w=20&id=0&status=0&price=100";
      /*++++++++++++++++++第3步：信息推送+++++++++++++++++*/
        if(false&&str0.equals(str1)&&cookie!=null){
            sendPost1(url, parma, cookie);
        }
        Map<String, String> xxtsHeadMap = PropertiesUtil.getProperties("headfile/eastsoo/eastsoo_xxts.properties");
//        jsoup测试
       if(true&&str0.equals(str2)&&cookie!=null) {
           Connection connection = Jsoup
                   .connect(url)
                   .ignoreContentType(true)
                   .ignoreHttpErrors(true)
                   .requestBody(parma)
                   .followRedirects(false)
                   .cookies(cookies1).headers(xxtsHeadMap);
           String cookie1=connection .post().text();
           System.out.println(cookie1);
       }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
    }

   public Map<String,String> sendPost1(String url, String param,String cookieVal) {
        PrintWriter out = null;
        BufferedReader in = null;
        HashMap<String, String> resultMap = new HashMap<>();
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("Host", "user.eastsoo.com");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Content-Length", "1231");
            conn.setRequestProperty("Accept",  "no-cache");
            conn.setRequestProperty("Cache-Control",  "*/*");
            conn.setRequestProperty("Origin", "http://user.eastsoo.com");
            conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencode");
            conn.setRequestProperty("Referer", "http://user.eastsoo.com/biz/business_add.do");
            conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
            conn.setRequestProperty("Cookie", cookieVal);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            Map<String, List<String>> map = conn.getHeaderFields();
            System.out.println(map);
            List<String> strings = map.get("Set-Cookie");
            String cookie= listToString(strings);
            resultMap.put("cookie",cookie);
            while ((line = in.readLine()) != null) {
                System.out.println(line);
//                String decode = URLDecoder.decode(line);
//                log.error(decode);
                byte[] bytes = line.getBytes("ISO-8859-1");
                String s = new String(bytes, "utf-8");
                System.out.println("重构之后："+s);
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        resultMap.put("data",result);
       log.error(result);
        return resultMap;
    }

    /**
     *  搜好货网-成功  发布信息需要：营业执照、身份证、企业授权申明
     */
    @Test
    public void test3() throws IOException {
        String str0="jsoup";
        String str1="urlConnection";
        String str2="jsoup";
        String surl="http://www.912688.com/login.html";
        String param="mobile=13781981469&oldpass=wdwhwn123456789&password=D3ACBC72ED8EB6FDE1FFDA891DD433CE&returl=http://www.912688.com/";
       /*++++++++++++++++++第1步：模拟登录+++++++++++++++++*/
        String cookie="";
        if(str0.equals(str1)) {
            cookie = login(surl, param);
        }
        Map<String,String> cookies=null;
        Map<String, String> properties = PropertiesUtil.getProperties("headfile/souhaohuo/souhaohuo_login.properties");
        if(str0.equals(str2)){
          cookies = Jsoup
                    .connect(surl)
                    .requestBody(param)
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .method(Connection.Method.POST)
                    .headers(properties)
                    .followRedirects(false)
                    .execute()
                    .cookies();
        }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

       /*++++++++++++++++++第2步：个人中心+++++++++++++++++*/
        String url = "http://www.912688.com/sellercenter";
        String param1 = "";
       if(false&&str0.equals(str1)&& StringUtil.isNotEmpty(cookie)) {
            sendGet(url, param1, cookie, code);
        }
        Map<String, String> properties1 = PropertiesUtil.getProperties("headfile/souhaohuo/souhaohuo_usercenter.properties");
        if(false&&str0.equals(str2)&&cookies!=null){
            Document post = Jsoup
                    .connect(url)
                    .requestBody(param1)
                    .headers(properties1)
                    .cookies(cookies)
                    .ignoreHttpErrors(true)
                    .ignoreContentType(true)
                    .post();
            System.out.println(post);

        }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/


       /*++++++++++++++++++第3步：信息推送+++++++++++++++++*/

       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
    }

    /**
     *  全球贸易信息中心/生意宝 成功  全部实现
     *  有两个发布产品的入口，实现了其中一个
     */
    @Test
    public void test4() throws IOException {
        String str0="jsoup";
        String str1="urlConnection";
        String str2="jsoup";
        String surl="http://my.i.hub.toocle.com/index.php?language=cn&_a=login";
        String param="f=check_login&_a=login&language=cn&reurl=&login=wdwhwn&passwd=wdwhwn123456789";
        /*++++++++++++++++++第1步：模拟登录：将用户名和密码拼接到cookie中，直接登录+++++++++++++++++*/
        String cookie="";
        if (str0.equals(str1)) {
            cookie = login(surl, param);
            System.out.println("cookie:"+cookie);
        }
        Map<String,String> cookies=new HashMap<String,String>();
        Map<String,String> loginHeadMap= PropertiesUtil.getProperties("headfile/shengyibao/shengyibao_login.properties");
        if(str0.equals(str2)){
            cookies = Jsoup
                    .connect(surl)
                    .headers(loginHeadMap)
                    .method(Connection.Method.POST)
                    .maxBodySize(0)
                    .requestBody(param)
                    .followRedirects(false)
                    .timeout(600000)
                    .execute()
                    .cookies();
        }
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
//      上传图片
        String imgUrl="http://www.i.album.toocle.com/api//?f=image_upload_leads";
        Map<String,String> imgHeadMap= PropertiesUtil.getProperties("headfile/shengyibao/shengyibao_imgupload.properties");
        File file = new File("E:\\jeecg\\wdwhwn-master\\wbsc-user\\src\\main\\resources\\123.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        HashMap<String, String> uploadRequestMap = new HashMap<>();
        uploadRequestMap.put("login_id","10146698");
        if(false&&str0.equals(str2)){
          Document doc  = Jsoup.connect(imgUrl).ignoreHttpErrors(true).ignoreContentType(true)
                    .headers(imgHeadMap)
                    .cookies(cookies)
                    .data("pic", file.getName(), fileInputStream)
                    .data(uploadRequestMap)
                    .post();
            String pic = doc.text();
            Map map = JsonUtil.toObject(pic, Map.class);
            Object msg = map.get("msg");
//            http://my.i.album.toocle.com/100-100-0-1/2019/07/30/9b/5d3fd0f57e89b.jpg
//            2019/07/30/9b/5d3fd0f57e89b.jpg
            StringBuilder sb=new StringBuilder("http://my.i.album.toocle.com/100-100-0-1/");
            sb.append(msg);
            System.out.println("pic:"+sb.toString());
        }
            String url = " http://my.i.hub.toocle.com/?_a=member&language=cn ";
            String param1 = "";
        /*++++++++++++++++++第2步：个人中心+++++++++++++++++*/
       if(false&&str0.equals(str1)&&StringUtil.isNotEmpty(cookie)) {
            sendGet(url, param1, cookie, code);
       }
       log.error("cookies:"+cookies);
        Map<String, String> userCenterHeaderMap = PropertiesUtil.getProperties("headfile/souhaohuo/shengyibao_usercenter.properties");
        if(false&&str0.equals(str2)&&cookies!=null){
           String text = Jsoup
                   .connect(url)
                   .ignoreHttpErrors(true)
                   .ignoreHttpErrors(true)
                   .headers(userCenterHeaderMap)
                   .cookies(cookies)
                   .get()
                   .text();
           System.out.println(text);
       }
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        /*++++++++++++++++++第步：信息推送+++++++++++++++++*/
        String xxtsUrl="http://leads.toocle.com/?_a=main&f=trade_create ";
        String xxtsParam="&category=13&intro= 供应餐具&title=海南诚德丰餐饮优惠批发&info=&type=2&pic1=2019/07/26/e1/5d3ac64a492e1.jpg&pic2=&pic3=&cate_id=&sign=1917c7190e2113fd472143960dd2135b";
        Map<String, String> xxtsMap = PropertiesUtil.getProperties("headfile/souhaohuo/shengyibao_xxts.properties");
        if(false&&str0.equals(str2)){
            Document post = Jsoup.connect(xxtsUrl).ignoreContentType(true).ignoreHttpErrors(true)
                    .headers(xxtsMap)
                    .cookies(cookies)
                    .requestBody(xxtsParam)
                    .post();
//            System.out.println(post);
            String text = post.select("span[class=red3]").text();
            System.out.println("结果为："+text);
        }
        /*++++++++++++++++++第步：信息查询+++++++++++++++++*/
        String resultListUrl="http://leads.toocle.com/list.html";
        Map<String, String> resultListMap = PropertiesUtil.getProperties("headfile/souhaohuo/shengyibao_resultlist.properties");
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
        if(true&&str0.equals(str2)){
            Document document = Jsoup.connect(resultListUrl).ignoreHttpErrors(true).ignoreContentType(true)
                    .cookies(cookies)
                    .headers(resultListMap)
                    .post();
            Elements elementsByTag = document.getElementsByTag("ul").get(3).getElementsByTag("li");
            for (int i = 0; i < elementsByTag.size(); i++) {
                String src = elementsByTag.get(i).select("img[src]").attr("src");
                log.error("第{}个商机的图片url为:{}",i,src);
                String href = elementsByTag.get(i).select("a[href]").attr("href");
                log.error("第{}个商机的url链接为:{}",i,href);
                String title = elementsByTag.get(i).select("a[href]").text();
                log.error("第{}个商机的标题为:{}",i,title.substring(0,title.indexOf("删除")));
                String text = elementsByTag.get(i).select("div[class=fjtime]").get(0).text();
                log.error("第{}个商机的发布时间为:{}",i,text.substring(5));
            }
//            System.out.println(elementsByTag);
        }
    }



    /**
     *  中国电子商务-成功  登陆、上传、个人中心成、信息推送、查询结果成功
     *  货源供应-供应
     */
    @Test
    public void test5() throws IOException {
        String str0="jsoup";
        String str1="urlConnection";
        String str2="jsoup";
        String surl="http://www.cebn.cn/member/login.php";
        String param="action=login&auth=&username=hnjzsoft&password=hnjzsoft123456789&submit=登 录&forward=http://www.cebn.cn/member/";
        /*++++++++++++++++++第1步：模拟登录+++++++++++++++++*/
        String cookie="";
        if(str0.equals(str1)) {
            cookie = login(surl, param);
        }
        Map<String,String> loginHeadMap=PropertiesUtil.getProperties("headfile/dianzishangwu/dianzishangwu_login.properties");
        Map<String, String> cookies = new HashMap<>();
        if(str0.equals(str2)){
            cookies = Jsoup.connect(surl).ignoreHttpErrors(true).ignoreHttpErrors(true).followRedirects(false)
                    .headers(loginHeadMap).requestBody(param).method(Connection.Method.POST).execute().cookies();
        }
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        /*++++++++++++++++++第2步：个人中心+++++++++++++++++*/
        String url="http://www.cebn.cn/member/";
        String param1="";
        if(false&&str0.equals(str1)) {
            sendGet(url, param1, cookie, code);
        }
        Map<String, String> properties = PropertiesUtil.getProperties("headfile/dianzishangwu/dianzishangwu_usercenter.properties");
        if(false&&str0.equals(str2)){
            String text = Jsoup.connect(url).followRedirects(false).ignoreHttpErrors(true).ignoreContentType(true).headers(properties).cookies(cookies).get().text();
            System.out.println(text);

        }
        /*++++++++++++++++++第3步：图片上传 +++++++++++++++++*/
//         String imgUpload="http://www.cebn.cn/upload.php?fid=&moduleid=5&from=album&old=&isremote=0&remote=http://";
//         String imgUpload="http://www.cebn.cn/upload.php?fid=&moduleid=5&from=album&old=http://www.cebn.cn/file/upload/201907/26/18385059377370.jpg.thumb.jpg&isremote=0&remote=http://&width=100&height=100";
         String imgUpload="http://www.cebn.cn/upload.php";
        Map<String, String> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("fid","");
        objectObjectHashMap.put("moduleid","5");
        objectObjectHashMap.put("from","album");
        objectObjectHashMap.put("old","");
        objectObjectHashMap.put("isremote","0");
        objectObjectHashMap.put("remote","http://");
        objectObjectHashMap.put("width","100");
        objectObjectHashMap.put("height","100");
        File file = new File("E:\\jeecg\\wdwhwn-master\\wbsc-user\\src\\main\\resources\\123.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        Map<String, String> properties2 = PropertiesUtil.getProperties("headfile/dianzishangwu/dianzishangwu_imgupload.properties");
          if(false&&str0.equals(str2)){
              Document upalbum = Jsoup.connect(imgUpload).ignoreContentType(true).ignoreHttpErrors(true)
                      .cookies(cookies)
                      .headers(properties2)
                      .data("upalbum", file.getName(), fileInputStream)
                      .data(objectObjectHashMap)
                      .post();
              String script = upalbum.body().select("script").get(0).toString();
              String substring = script.substring(script.indexOf("(")+2, script.indexOf(",")-1);
//              http://www.cebn.cn/file/upload/201907/30/14150172377370.jpg.thumb.jpg
              System.out.println(substring);
          }
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        /*++++++++++++++++++第4步：信息推送+++++++++++++++++*/
        String  xxtsUrl="http://www.cebn.cn/member/my.php";
        String param3="action=add" +
//                6_求购   5_供应
                "&mid=5" +
                "&itemid=0" +
                "&forward=http://www.cebn.cn/member/" +
//                0_供应  1_提供服务  2_提供二手 3_提供加工  4_提供合作  5_库存
                "&post[typeid]=0&" +
//                标题
                "post[title]=供应恒亿盛世白酒系列123456" +
                "&color=" +
//                类别
                "&post[catid]=72441" +
//                品牌
                "&post[brand]=恒亿盛世" +
//                文本内容
                "&post[content]=供应恒亿盛世白酒系列" +
//                图片
                "&post[thumb]=http://www.cebn.cn/file/upload/201907/29/10463958377370.jpg.thumb.jpg" +
                "&post[thumb1]=" +
                "&post[thumb2]=" +
                "&post[totime]=" +
                "&post[n1]=" +
                "&post[v1]=" +
                "&post[n2]=" +
                "&post[v2]=" +
                "&post[n3]=" +
                "&post[v3]=" +
//                单位
                "&post[unit]=瓶" +
//                价格
                "&post[price]=298" +
//                最少订购数量
                "&post[minamount]=5" +
//                供货总量
                "&post[amount]=1000" +
//                3天内发货
                "&post[days]=3" +
//                自定义分类id
                "&post[mycatid]=0" +
//                我的推荐 否_0
                "&post[elite]=0" +
                "&submit= 提 交";
        Map<String, String> properties3 = PropertiesUtil.getProperties("headfile/dianzishangwu/dianzishangwu_xxts.properties");
        if(false&&str0.equals(str2)){
            Document post = Jsoup.connect(xxtsUrl).cookies(cookies)
                    .ignoreHttpErrors(true)
                    .ignoreContentType(true)
                    .headers(properties3)
//                    .followRedirects(false)
                    .requestBody(param3)
                    .post();
            System.out.println(post);
        }
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        /*++++++++++++++++++第5步：查询发布结果+++++++++++++++++*/
        String resultUrl="http://www.cebn.cn/member/my.php?mid=5";
        Map<String, String> properties4 = PropertiesUtil.getProperties("headfile/dianzishangwu/dianzishangwu_resultlist.properties");
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
        if(true&&str0.equals(str2)){
            Document document = Jsoup.connect(resultUrl).ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .headers(properties4)
                    .cookies(cookies)
                    .get();
//            System.out.println(document);
            Elements select = document.select("tr[onmouseover]");
            System.out.println(select.size());
            for (int i = 0; i < select.size(); i++) {
                System.out.println("第"+(i+1)+"标题为："+select.get(i).select("td[title]").attr("title"));
                System.out.println("第"+(i+1)+"url为："+select.get(i).select("td[title]").select("a[href]").attr("href"));
                System.out.println("第"+(i+1)+"更新时间为："+select.get(i).select("td[class=f_gray px11]").get(0).attr("title").substring(5));
            }
        }
    }

    /**
     *  必途网   编码格式为gb2312-成功   发布信息推广需要审核资格
     */
    @Test
    public void test6() throws IOException {
        String str0="jsoup";
        String str1="urlConnection";
        String str2="jsoup";
        String surl="http://www.b2b.cn/forum/login/loginhttp://www.b2b.cn/forum/login/login";
        String param="jsoncallback=jQuery1110037744900002829507_1564113137071&name=13781981469&pwd=hnjz123soft&guide=1&_=1564113137076";
        /*++++++++++++++++++第1步：模拟登录+++++++++++++++++*/
       String cookie="";
        if(str0.equals(str1)){
            Map<String, String> stringStringMap = sendGet(surl, param, "",code);
            cookie= stringStringMap.get("cookie");
        }
//        String cookie = login(surl, param);
        Map<String, String> properties = PropertiesUtil.getProperties("headfile/bitu/bitu_login.properties");
        Map<String, String> cookies = new HashMap<>();
        if(str0.equals(str2)){
            cookies= Jsoup.connect(surl+"?"+param).ignoreContentType(true).ignoreHttpErrors(true).headers(properties)
                    .method(Connection.Method.GET).execute().cookies();
        }
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        /*++++++++++++++++++第2步：+++++++++++++++++*/
        String url="http://biz.b2b.cn/Manage/Member/Person.aspx";
        String param1="";
        code="GB2312";
        if(str0.equals(str1)){
            sendGet(url,param1,cookie,code);
        }
        Map<String, String> properties1 = PropertiesUtil.getProperties("headfile/bitu/bitu_usercenter.properties");
        if(false&&str0.equals(str2)){
            Document document = Jsoup.connect(url).ignoreHttpErrors(false).ignoreContentType(false)
                    .cookies(cookies).headers(properties1).get();
            System.out.println(document);
        }

        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
    }

    /**
     *  制造交易网-成功   模拟登陆、个人中心、上传图片、信息推送成功；查询结果（需要通过审核之后）
     */
    @Test
    public void test7() throws IOException {
        String str0="jsoup";
        String str1="urlConnection";
        String str2="jsoup";
        /*++++++++++++++++++第1步：模拟登录+++++++++++++++++*/
        String surl="http://my.cn.c-c.com/member/signin.aspx?d=0.41923915246532784";
        String param="Type=Login1&UserName=hnjzsoft&PassWord=hnjz123soft&isSavePwd=0&SUrl&XC=XXXXXZZZXX";
        String cookie="";
        if(str0.equals(str1)) {
            cookie = login(surl, param);
        }
        Map<String,String> cookies=new HashMap<String,String>();
        Map<String,String> loginHeadMap=PropertiesUtil.getProperties("headfile/zhizaojiaoyi/zhizaojiaoyi_login.properties");
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
        if(str0.equals(str2)){
            cookies= Jsoup.connect(surl).ignoreContentType(true).ignoreHttpErrors(true)
                    .headers(loginHeadMap).requestBody(param).method(Connection.Method.POST).execute().cookies();
        }
        /*++++++++++++++++++第2步：个人中心+++++++++++++++++*/
        String url="http://myv2.cn.c-c.com/member/smrz/";
        String param1="";
       if(false&&str0.equals(str1)) {
           sendGet(url, param1, cookie, code);
       }
        Map<String,String> userCenterHeadMap=PropertiesUtil.getProperties("headfile/zhizaojiaoyi/zhizaojiaoyi_usercenter.properties");
       if(false&&str0.equals(str2)){
           Document document = Jsoup.connect(url).ignoreHttpErrors(true).ignoreContentType(true).cookies(cookies).headers(userCenterHeadMap).get();
           System.out.println(document);
       }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

       /*++++++++++++++++++第步：判断图片是否存在+++++++++++++++++*/
        String imgCheck="http://myv2.cn.c-c.com/api/ImgUpload/md5Check";
        File file = new File("E:\\jeecg\\wdwhwn-master\\wbsc-user\\src\\main\\resources\\cde.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        String s = DigestUtils.md5Hex(fileInputStream);
        System.out.println(s);
        String param2="status=md5Check&md5="+s+"1";
        Map<String,String> properties1=PropertiesUtil.getProperties("headfile/zhizaojiaoyi/zhizaojiaoyi_imgupload.properties");
        Map map=new HashMap();
        if(false&&str0.equals(str2)){
            Document doc= Jsoup.connect(imgCheck).ignoreContentType(true).ignoreHttpErrors(true)
                    .headers(properties1)
                    .cookies(cookies)
                    .requestBody(param2)
                    .post();
            String text=doc.text();
            text=text.replace("\\","").substring(1,text.length()-1);
            System.out.println(text);
             map = JsonUtil.toObject(text, Map.class);
            //            System.out.println(map);
//            System.out.println(map.get("NTimg"));
//            System.out.println(map.get("ifExist"));

        }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

       /*++++++++++++++++++第3步：图片上传+++++++++++++++++*/
        String imgUrl="http://myv2.cn.c-c.com/api/ImgUpload/upLoadFile?Uid=37237679&UserName=hnjzsoft&isC=1&isT=1&isSave=1";
        Map<String,String> imguploadHeadMap=PropertiesUtil.getProperties("headfile/zhizaojiaoyi/zhizaojiaoyi_imgupload.properties");
        HashMap<String, String> stringStringHashMap = new HashMap<>();
//       动态
        stringStringHashMap.put("md5","MD5");
        System.out.println("md5:"+(s+1));
        stringStringHashMap.put("btnId","");
        stringStringHashMap.put("isW","1");
        stringStringHashMap.put("id","WU_FILE_0");
        stringStringHashMap.put("name","2019.jpg");
        stringStringHashMap.put("type","image/jpeg");
        stringStringHashMap.put("lastModifiedDate","Mon Jul 29 2019 14:04:12 GMT+0800");
        stringStringHashMap.put("size","24070");
//        必须重新 生成输入流，经过md5后的输入流已经改变
        FileInputStream fileInputStream1 = new FileInputStream(file);
//        if(true&&map.get("ifExist").toString().equals("false")&&str0.equals(str2)){
        if(true&&str0.equals(str2)){
            System.out.println("上传");
            Document post = Jsoup.connect(imgUrl).ignoreHttpErrors(true).ignoreContentType(true)
                    .cookies(cookies)
                    .headers(imguploadHeadMap)
                    .data("file",file.getName(),fileInputStream1)
                    .data(stringStringHashMap)
                    .post();
            Map map1 = JsonUtil.toObject(post.text(), Map.class);
            String result = map1.get("result").toString();
            StringBuilder sb = new StringBuilder("http://img.c-c.com/");
            StringBuilder append = sb.append(result);
//            http://img.c-c.com/NTimg/2019/07/30/15/bc5dd1497ebf7287b43af1a6bca854501.jpg
            System.out.println(append);
        }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

       /*++++++++++++++++++第4步：信息发布+++++++++++++++++*/
        String xxfbUrl="http://myv2.cn.c-c.com/member/Trade/addInfo";
        String content="销售食品；物业管理；房地产开发；货物进出口、技术进出口、代理进出口；销售自行开发的商品房；出租商业用房、办公用房（不得作为有形市场经营用房）";
        String ss="<span style=\"color:#333333;font-family:&quot;Microsoft Yahei&quot;, Arial,sans-serif;font-size:14px;\">CONTENT</span>";
        ss=ss.replace("CONTENT",content);
        String param3=
//                产品名称
                "protitle=恒亿盛世白酒" +
//                 推广标题
                "&title=河南恒亿盛世酱香型白酒" +
//                 关键词1
                "&key=恒亿盛世" +
//                 关键词2
                "&key=酱香型" +
//                 关键词3
                "&key=白酒" +
//              产品属性
                "&pAtt=0001002800020001" +
//              品牌
                "&pAtta0=恒亿盛世" +
                "&musth=0|input" +
                "&pAtta1=" +
                "&pAtta2=" +
                "&pAtta3=" +
                "&pAtta4=" +
                "&pAtta5=52" +
                "&musth=5|input" +
                "&pAtta6=高粱" +
                "&musth=6|input" +
                "&pAtta7=" +
                "&pAtta8=请选择" +
                "&pAttb8=" +
                "&pAtta9=" +
                "&pAtta10=请选择" +
                "&pAttb10=" +
                "&pAtta11=请选择" +
                "&pAttb11=" +
                "&pAtta12=请选择" +
                "&pAttb12=" +
                "&pAtta13=请选择" +
                "&pAttb13=" +
                "&tCon=0001002800020001" +
//                        单位
                "&tCona0=瓶" +
                "&tConb0=" +
//                        单价
                "&tCona1=298" +
//                        起订量
                "&tCona2=5" +
//                        供货总量
                "&tCona3=1000" +
//                        3天内发货
                "&tCona4=3" +
                "&tConb4=" +
//                        有效期
                    "&ValidityDate=90" +
//                        图片
                "&hImg1=/NTimg/2019/07/29/14/5c3e71a406dd8431d5800f5aa4a810761.jpg" +
                "&hImg2=" +
                "&hImg3=" +
                "&editor="+ss+
                "&editImg=" +
//              类别
                "&ddlCategory=6168095" +
//                        1_推荐  2_特别推荐
                "&tuijia=1" +
                "&hTitle=" +
                "&id=" +
                "&Content=" +
                "&tradeType=0|1|0|0" +
//                        1级类别
                "&topCategoryId=00010028" +
//                        二级类别
                "&secondCategoryId=000100280002" +
//                        3级类别
                "&thirdCategoryId=0001002800020001" +
                "&autoName=" +
                "&autoValue=" +
                "&categoryName=" +
                "&CatagoryId=" +
                "&5LOOR08AFA5LOOR08AFASXG=5LOOR08AFASXG" +
                "&5LOOR08AFASXG5LOOR08AFA=5LOOR08AFASXG" +
                "&isVouch=0" +
                "&X9831261=625178" +
                "&X-Requested-With=XMLHttpRequest";
        Map<String,String> xxtsHeadMap=PropertiesUtil.getProperties("headfile/zhizaojiaoyi/zhizaojiaoyi_xxts.properties");
        if(false&str0.equals(str2)){
            Document post = Jsoup.connect(xxfbUrl).ignoreHttpErrors(true)
                    .ignoreContentType(true)
                    .headers(xxtsHeadMap)
                    .cookies(cookies)
                    .requestBody(param3)
                    .post();
            System.out.println(post);

        }

        String resultList="http://myv2.cn.c-c.com/api/trade/getInfo?p=1&pass=1&cid=&key=&pSize=20&sort=0 ";
        Map<String,String> resultListHeadMap=PropertiesUtil.getProperties("headfile/zhizaojiaoyi/zhizaojiaoyi_resultlist.properties");
        if(false&&str0.equals(str2)){
            Document doc = Jsoup.connect(resultList).ignoreContentType(true).ignoreHttpErrors(true)
                    .followRedirects(false)
                    .cookies(cookies)
                    .headers(resultListHeadMap)
                    .get();
            System.out.println(doc.toString());
            String body = doc.select("body").text();
            body=body.substring(1,body.lastIndexOf("\""));
            body=body.replace("\\","");
            List<Map<String,String>> list = JsonUtil.toObject(body, List.class);
            for (int i = 0; i < list.size(); i++) {
//                标题
                String title=list.get(i).get("title");
                System.out.println(title);
//                url
                String href="http://hnjzsoft.ce.c-c.com/productinfo/"+list.get(i).get("id");
                System.out.println(href);
//                时间
                String text=list.get(i).get("updateTime");
                System.out.println(text);
            }
        }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
    }

    /**
     *  化工网  编码格式为gb2312-成功   需要开通：产品供应功能
     */
    @Test
    public void test8() throws IOException {
        String str0="jsoup";
        String str1="urlConnection";
        String str2="jsoup";
       /*++++++++++++++++++第1步：模拟登录+++++++++++++++++*/
        String surl="https://china.chemnet.com/member/index.cgi";
        String param="username=hnjzsoft&password=hnjz123soft";
       String cookie="";
        if(str0.equals(str1)) {
            cookie = login(surl, param);
            System.out.println("cookie:"+cookie);
        }
        Map<String, String> cookies = new HashMap<>();
        Map<String, String> properties = PropertiesUtil.getProperties("headfile/zhizaojiaoyi/zhizaojiaoyi_login.properties");
        if(str0.equals(str2)){
             cookies = Jsoup.connect(surl).ignoreContentType(true).ignoreHttpErrors(true).headers(properties).requestBody(param).method(Connection.Method.POST).execute().cookies();
            System.out.println("cookies:"+JsonUtil.toJson(cookies));
        }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

       /*++++++++++++++++++第2步：个人中心+++++++++++++++++*/
        String url="https://china.chemnet.com/member/Action.cgi?t=minfo&f=edit&lang=chn&eid=1&0.216584258337633";
//        String param1="t=main1&hnjzsoft";
        String param1="";
        code="GB2312";
        if(str0.equals(str1)){
            sendGet(url,param1,cookie,code);
        }
        Map<String, String> properties1 = PropertiesUtil.getProperties("headfile/zhizaojiaoyi/zhizaojiaoyi_usercenter.properties");
        if(str0.equals(str2)){
            Document post = Jsoup.connect(url).ignoreHttpErrors(true).ignoreContentType(true).headers(properties1).cookies(cookies).get();
            System.out.println(post);
        }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
       
       
       /*++++++++++++++++++第3步：图片上传+++++++++++++++++*/

       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
    }

    /**
     *  第一枪-成功  需要三步
     *  */
    @Test
    public void test9() throws IOException {
        String str0="jsoup";
        String str1="urlConnection";
        String str2="jsoup";

        /*++++++++++++++++++第1步：获取token和cookie+++++++++++++++++*/
        String tokenUrl="https://www.d17.cc/d17/token/getToken";
        Map<String, String> resultMap = sendGet(tokenUrl, "", "",code);
        Map map = JsonUtil.toObject(resultMap.get("data"), Map.class);
        String cookie = resultMap.get("cookie");
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
       /*++++++++++++++++++第1步：模拟登录+++++++++++++++++*/
        String surl="https://www.d17.cc/d17/user/login";
        String param="userName=hnjzsoft&password=hnjz123soft&d17_form_token_="+map.get("token");
        Map<String, String> stringStringMap = sendPost(surl, param, cookie);
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

       /*++++++++++++++++++第2步：个人中心+++++++++++++++++*/
        String url="https://www.d17.cc/d17/user/main";
        String param1="";
        sendGet(url,param1,cookie,code);
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
    }

    /**
     *  企业谷-成功   登陆成功、个人中心成功、上传成功、信息发布（缺少积分）
     *  */
    @Test
    public void test10() throws IOException {
        String str0="jsoup";
        String str1="urlConnection";
        String str2="jsoup";
       /*++++++++++++++++++第1步：模拟登录+++++++++++++++++*/
        String surl="http://www.qiyegu.com/member/login.php";
        String param="forward=http://www.qiyegu.com/&action=login&auth=&username=hnjzsoft&password=hnjz123soft&submit=登 录";
        String cookie="";
        if(false&&str0.equals(str1)) {
           cookie = login(surl, param);
       }
        Map<String, String> cookies = new HashMap<>();
        Map<String, String> properties = PropertiesUtil.getProperties("headfile/qiyegu/qiyegu_login.properties");
       if(str0.equals(str2)){
           cookies = Jsoup.connect(surl).ignoreContentType(true).ignoreHttpErrors(true).headers(properties).requestBody(param).followRedirects(false).method(Connection.Method.POST).execute().cookies();
       }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

       /*++++++++++++++++++第2步：个人中心+++++++++++++++++*/
        String url="http://www.qiyegu.com/member/edit.php";
        String param1="tab=2";
        if(false&&str0.equals(str1)) {
            sendGet(url, param1, cookie, code);
        }
        Map<String, String> properties1 = PropertiesUtil.getProperties("headfile/qiyegu/qiyegu_usercenter.properties");
        if(false&&str0.equals(str2)){
            Document document = Jsoup.connect(url).ignoreHttpErrors(true).ignoreContentType(true).headers(properties1).cookies(cookies).get();
            System.out.println(document);
        }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/


       /*++++++++++++++++++第3步：图片上传+++++++++++++++++*/
       String imgurl="http://www.qiyegu.com/upload.php";
        File file = new File("E:\\jeecg\\wdwhwn-master\\wbsc-user\\src\\main\\resources\\456.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        Map<String, String> properties2 = PropertiesUtil.getProperties("headfile/qiyegu/qiyegu_imgupload.properties");
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("fid","");
        stringStringHashMap.put("moduleid","27");
        stringStringHashMap.put("from","album");
        stringStringHashMap.put("old","");
        stringStringHashMap.put("isremote","");
        stringStringHashMap.put("remote","http://");
        stringStringHashMap.put("width","200");
        stringStringHashMap.put("height","200");
        if(str0.equals(str2)){
            Document upalbum = Jsoup.connect(imgurl).ignoreContentType(true).ignoreHttpErrors(true)
                    .headers(properties2)
                    .cookies(cookies)
                    .data("upalbum", file.getName(), fileInputStream)
                    .data(stringStringHashMap)
                    .post();
            String script = upalbum.body().select("script").toString();
            String substring = script.substring(script.indexOf("(")+2, script.indexOf(",")-1);
//            http://www.qiyegu.com/file/upload/201907/30/15042449627538.jpg.thumb.jpg
            System.out.println(substring);
        }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
    }

    /**
     *  八方资源网-成功    模拟登陆、个人中心、上传图片成功；信息发布（登陆频繁被封）
     *  信心发布 有验证码
     *  */
    @Test
    public void test11() throws IOException, InterruptedException {
        String str0="jsoup";
        String str1="urlConnection";
        String str2="jsoup";
        String surl = "http://m.b2b168.com/Index.aspx?spm=636983799257465537";
        String param = "User=hnjzsoft&Password=hnjz123soft&act=Login&Url=/Index.aspx";
       /*++++++++++++++++++第1步：模拟登录+++++++++++++++++*/
        String cookie="";
       if(str0.equals(str1)) {
           cookie = login(surl, param);
       }
        Map<String, String> cookies = new HashMap<>();
        Map<String, String> properties = PropertiesUtil.getProperties("headfile/bafang/bafang_login.properties");
       if(str0.equals(str2)){
            cookies = Jsoup.connect(surl).ignoreContentType(true).ignoreHttpErrors(true).headers(properties).requestBody(param).method(Connection.Method.POST).execute().cookies();
       }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

       /*++++++++++++++++++第2步：个人中心+++++++++++++++++*/
        String url="http://m.b2b168.com/Index.aspx";
        String param1="pg=index";
        if(false&&str0.equals(str1)){
            sendGet(url,param1,cookie,code);
        }
        String url1="http://m.b2b168.com/Index.aspx?pg=Person&spm=636997483699651059";
        Map<String, String> properties1 = PropertiesUtil.getProperties("headfile/bafang/bafang_usercenter.properties");
        if(false&&str0.equals(str2)){
            Document document = Jsoup.connect(url1).ignoreHttpErrors(true).ignoreContentType(true).headers(properties1).cookies(cookies).get();
            System.out.println(document);
        }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
       
       
       /*++++++++++++++++++第3步：图片上传+++++++++++++++++*/
       String imgUrl="http://m.b2b168.com/Index.aspx?pg=PhotoIn&r=0";
        File file = new File("E:\\jeecg\\wdwhwn-master\\wbsc-user\\src\\main\\resources\\123.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        Map<String, String> properties2 = PropertiesUtil.getProperties("headfile/bafang/bafang_usercenter.properties");
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("Desc","");
        stringStringHashMap.put("No","0");
        stringStringHashMap.put("AlbumId","11032516");
        stringStringHashMap.put("Submit","确定无误，保存");
        stringStringHashMap.put("Id","");
        stringStringHashMap.put("Photo","");
        stringStringHashMap.put("Add","");
        stringStringHashMap.put("act","Photo");
        if(false&&str0.equals(str2)){
            Document fuPhoto = Jsoup.connect(imgUrl).ignoreHttpErrors(true).ignoreContentType(true)
                    .data("fuPhoto", file.getName(), fileInputStream)
                    .data(stringStringHashMap)
                    .headers(properties2)
                    .cookies(cookies)
                    .post();
//            System.out.println(fuPhoto);
            Elements select = fuPhoto.select("body").select("script");
            Element element = select.get(0);
            String data = element.data();
            String result=data.split("}")[0];
            String imgWebUrl=result.substring(result.indexOf("src")+5,result.length()-1);
            System.out.println("上传图片url为："+imgWebUrl);
        }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
       
       
       /*++++++++++++++++++第4步：信息发布+++++++++++++++++*/
        String xxtsUrl="http://m.b2b168.com/Index.aspx?pg=Supply&spm=637000121704756231";
        Map<String, String> properties3 = PropertiesUtil.getProperties("headfile/bafang/bafang_xxts.properties");
        String param2=
//                类别
                "iClass=61011001" +
//                标题
                "&Name=恒亿盛世浓香型白酒" +
//                手机标题
                "&SubName=恒亿盛世浓香型白酒" +
//                关键词
                "&Tags=恒亿盛世" +
                "&Tags=浓香" +
                "&Tags=白酒" +
                "&ProSize=" +
//                单价
                "&fPrice=298" +
                "&Currency=0" +
//                产品数量
                "&fProNum=1000" +
//                单位
                "&ProUnit=瓶" +
                "&selUnit=8&Bdetails=" +
                "&MainDetails=销售食品；物业管理；房地产开发；货物进出口、技术进出口、代理进出口；销售自行开发的商品房；出租商业用房、办公用房（不得作为有形市场经营用房）" +
//                产品不分类（没有自定义分类）
                        "&sClass=1" +
//                        发货地
                "&Region=898031360" +
                "&ValidTime=0" +
//                        验证码
                "&VCode=7qsa" +
                "&Submit=确定无误，马上发布" +
                "&sStatus=0" +
                "&sciId=" +
//                        图片
                "&ImageFlag=201907291551177044864.jpg" +
                "&ImageUrl2=" +
                "&ImageUrl3=" +
                "&cStatus=" +
                "&q=" +
                "&MainId=0" +
                "&act=Supply";
        if(true&&str0.equals(str2)){
            Document post = Jsoup.connect(xxtsUrl).ignoreContentType(true).ignoreHttpErrors(true)
                    .cookies(cookies)
                    .requestBody(param2)
                    .headers(properties3)
                    .post();
            log.error(post.toString());
        }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/


       /*++++++++++++++++++第5步：结果查询+++++++++++++++++*/
        String resultListUrl="http://m.b2b168.com/Index.aspx?pg=SupplyList&spm=637000158789475016";
        Map<String, String> properties4 = PropertiesUtil.getProperties("headfile/bafang/bafang_resultlist.properties");
        if(false&str0.equals(str2)){
            Document document = Jsoup.connect(resultListUrl).ignoreHttpErrors(true).ignoreContentType(true)
                    .headers(properties4)
                    .cookies(cookies)
                    .get();
            Elements select = document.select("tbody tr");
            for (int i = 1; i < select.size(); i++) {
                System.out.println("第"+(i)+"个产品标题："+select.get(i).select("td").get(2).select("a[href]").text());
                System.out.println("第"+(i)+"个产品url："+select.get(i).select("td").get(2).select("a[href]").attr("href"));
                System.out.println("第"+(i)+"个产品审核通过日期："+select.get(i).select("td").get(7).text());
            }
        }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
    }

    /**
     *  中国网库  没有成功
     *
     *  */
    @Test
    public void test12() throws IOException {
        String str0="jsoup";
        String str1="urlConnection";
        String str2="jsoup";

       /*++++++++++++++++++第1步：模拟登录+++++++++++++++++*/
        String surl="http://checkin.99114.com/login";
        String param="lt=LT-642742-YxzXVcKeEqPuehhb16AMfKVHLpGs2o&execution=e6s1&_eventId=submit&username=13781981469&password=df32f4bbc592673b5e32572079c2c5bd&vcode=&phone_num=&mobileCode=";
       String cookie="";
        if(str0.equals(str1)) {
            cookie = login(surl, param);
        }
        String url1="http://checkin.99114.com/checkName.jsp ";
        String param1="name=13781981469";
        Map<String, String> cookies = new HashMap<>();
        Map<String, String> properties = PropertiesUtil.getProperties("headfile/wangku/wangku_login1.properties");
       String str="";
        if(false&&str0.equals(str2)){
            cookies= Jsoup.connect(url1).ignoreContentType(true).ignoreContentType(true).requestBody(param1).method(Connection.Method.POST).headers(properties).execute().cookies();
            System.out.println("cookies:"+JsonUtil.toJson(cookies));
        }
        str=cookies.get("JSESSIONID");
        System.out.println("str:"+str);

        String url3="http://checkin.99114.com/checkPwd.jsp";
        String param4="account=13781981469&pwd=df32f4bbc592673b5e32572079c2c5bd";
        Map<String, String> properties4 = PropertiesUtil.getProperties("headfile/wangku/wangku_login2.properties");
        if(false&&str0.equals(str2)){
//            String text = Jsoup.connect(url3).ignoreContentType(true).ignoreHttpErrors(true).requestBody(param4).cookies(cookies).headers(properties4).post().text();
//            System.out.println("cookies:"+text);
            Map<String, String> cookies1 = Jsoup.connect(url3).ignoreContentType(true).ignoreHttpErrors(true).requestBody(param4).cookies(cookies).headers(properties4).method(Connection.Method.POST).execute().cookies();
            System.out.println("cookies1:"+JsonUtil.toJson(cookies1));
        }

//        String url0=" http://checkin.99114.com/login;jsessionid="+str;
        String url0=" http://checkin.99114.com/login;jsessionid=6836B4B30E7D5C0E700FE275454C1216";
        String param0="lt=LT-38691-FbOEqdloiacoRFMOev9Dxm9aXpYje7&execution=e1s1&_eventId=submit&username=13781981469&password=df32f4bbc592673b5e32572079c2c5bd&vcode=&phone_num=&mobileCode=";
        Map<String, String> properties1 = PropertiesUtil.getProperties("headfile/wangku/wangku_login0.properties");
        if(str0.equals(str2)){
//            cookies=Jsoup.connect(url0).followRedirects(false).ignoreContentType(true).ignoreContentType(true).requestBody(param0).method(Connection.Method.POST).headers(properties).execute().cookies();
            String text = Jsoup.connect(url0).followRedirects(false).ignoreContentType(true)
                    .ignoreContentType(true)
                    .headers(properties1)
                    .requestBody(param0).post().text();
//            System.out.println("cookies:"+JsonUtil.toJson(cookies));
            System.out.println("cookies:"+text);
        }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
        System.out.println("AAAAAAAAAAAAAAAAAA");
       /*++++++++++++++++++第2步：个人中心+++++++++++++++++*/
        String url="http://m.b2b168.com/Index.aspx";
        String param3="pg=index";
        if(false&&str0.equals(str1)) {
            sendGet(url, param3, cookie, code);
        }
        String url2="http://center.99114.com/member;jsessionid="+str;
        Map<String, String> properties2 = PropertiesUtil.getProperties("headfile/wangku/wangku_usercenter.properties");
        if(false&&str0.equals(str2)){
            Document document = Jsoup.connect(url2).ignoreContentType(true).ignoreHttpErrors(true).cookies(cookies).headers(properties2).get();
            System.out.println(document);
        }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
    }

    /**
     *  黄页88  登录成功
     *  */
    @Test
    public void test13() throws IOException {
        String str0="jsoup";
        String str1="urlConnection";
        String str2="jsoup";
        String sur0="http://my.huangye88.com/login/save.html";
        String param0="user_name=13781981469&user_password=hnjz123soft&from=";
        Map<String, String> cookies=new HashMap<>();
        Map<String, String> properties2 = PropertiesUtil.getProperties("headfile/huangye88/huangye88_login.properties");
        Map<String, String> properties3 = PropertiesUtil.getProperties("headfile/huangye88/huangye88_usercenter.properties");
        if(str0.equals(str2)){
            cookies = Jsoup.connect(sur0).ignoreHttpErrors(true).ignoreContentType(true).headers(properties2).method(Connection.Method.POST).requestBody(param0).followRedirects(false).execute().cookies();
            System.out.println("cookies: "+cookies);
            Document document = Jsoup.connect("http://my.huangye88.com").ignoreHttpErrors(true).ignoreContentType(true).cookies(cookies).headers(properties3).get();
            System.out.println(document);
        }
       /*++++++++++++++++++第1步：模拟登录+++++++++++++++++*/
       if(str0.equals(str1)) {
           String surl = "http://my.huangye88.com/login/save.html";
           String param = "user_name=13781981469&user_password=hnjz123soft&from=http://my.huangye88.com/";
//        String param="user_name=13781981469&user_password=hnjz123soft";
           String u = "http://my.huangye88.com/ajax/login_name/";
           String param2 = "word=13781981469";
           Map<String, String> stringStringMap = sendGet(u, param2, "", code);
           String cookie = stringStringMap.get("cookie");
//        log.error("cookie: "+cookie);
           Map<String, String> stringStringMap1 = sendPost(surl, param, cookie);
           cookie = stringStringMap1.get("cookie");
//        String cookie = login(surl, param);
//        log.error("cookie1: "+cookie);
//         String cookie=" PHPSESSID=15628389840447-ef8280ea734db3724888a69317f2801268455118; hy88loginid=2997723; hy88username=hnjz123soft; hy88realname=%E7%8E%8B%E7%94%B3; hy88usergroup=11; hy88mobile=13781981469; hy88userqq=0; showcj=1";
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

       /*++++++++++++++++++第2步：个人中心+++++++++++++++++*/
           String url = "http://my.huangye88.com/";
           String param1 = "";
//        sendGet(url,param1,cookie,code);
       }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/


       /*++++++++++++++++++第3步：上传图片+++++++++++++++++*/
            String imgUploadUrl="http://com-hy88-images.oss-cn-beijing.aliyuncs.com/";
        File file = new File("E:\\jeecg\\wdwhwn-master\\wbsc-user\\src\\main\\resources\\123.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        Map<String, String> properties4 = PropertiesUtil.getProperties("headfile/huangye88/huangye88_imgupload.properties");
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("OSSAccessKeyId","eYd4iNJuZLvaoWaj");
        stringStringHashMap.put("policy","eyJleHBpcmF0aW9uIjoiMjAxOS0wNy0zMFQwODoxMToxMS4wMDBaIiwiY29uZGl0aW9ucyI6W3siYnVja2V0IjoiY29tLWh5ODgtaW1hZ2VzIn0sWyJzdGFydHMtd2l0aCIsIiRrZXkiLCJsaXZlL3VzZXIvMjk5NzcyMy8iXSxbImNvbnRlbnQtbGVuZ3RoLXJhbmdlIiwwLDEwNDg1NzYwXV19");
        stringStringHashMap.put("signature","1ttKXkEGxkRIJBlLNQS+kwp876Q=");
        stringStringHashMap.put("key","live/user/2997723/1564470671044048700-0.jpg");
        if(str0.equals(str2)){
            Document file1 = Jsoup.connect(imgUploadUrl).ignoreHttpErrors(true).ignoreContentType(true)
                    .headers(properties4)
                    .cookies(cookies)
                    .data("file", file.getName(), fileInputStream)
                    .data(stringStringHashMap)
                    .post();
            System.out.println(file1);
        }


        if(str0.equals(str2)){

        }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
    }

    public String login(String surl,String param) throws IOException{
//    public String login(String surl,String param) throws IOException{
   /*++++++++++++++++++第1步：模拟登录+++++++++++++++++*/
        // 连接地址（通过阅读html源代码获得，即为登陆表单提交的URL）
        BufferedReader in = null;
        String result = "";
        URL url = new URL(surl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setDoOutput(true);  //打开输出，向服务器输出参数（POST方式、字节）（写参数之前应先将参数的字节长度设置到配置"Content-Length"<字节长度>）
        connection.setDoInput(true);//打开输入，从服务器读取返回数据
        connection.setRequestMethod("POST"); //设置登录模式为POST（字符串大写）
        connection.setInstanceFollowRedirects(false);
        connection.connect();
        OutputStreamWriter out = new OutputStreamWriter(connection
                .getOutputStream(), "utf-8");
        out.write(param); // post的关键所在！
        out.flush();
        out.close();
        Map<String, List<String>> headerFields = connection.getHeaderFields();//格式:JSESSIONID=541884418E77E7F07363CCEE91D4FF7E; Path=/
        List<String> strings = headerFields.get("Set-Cookie");
        String s = listToString(strings);
//        printCookie(headerFields);
        connection.disconnect();
        return s;
    }

    public static String listToString(List<String> list) {
        if (list == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean first = true;
        //第一个前面不拼接","
        for (String string : list) {
            if (first) {
                first = false;
            } else {
                result.append(";");
            }
            result.append(string);
        }
        return result.toString();
    }
    private void printCookie(Map<String,List<String>> map){
        System.out.println("输出Cookie:");
        for(String key :map.keySet()){
            System.out.println("key= "+ key + " and value= " + map.get(key));
        }
    }

    private static String getSessionId(Map<String, List<String>> map){
        String sessionId=null;
        List<String> sResult = map.get("Set-Cookie");//从请求头中获取Cookie信息
        if (sResult != null && sResult.size() >= 1)//从Cookie中获取SessionId
        {
            sessionId = sResult.get(0);

            sessionId = sessionId.substring(0, sessionId.indexOf(";") + 1);

            System.out.println("获取sessionId:" + sessionId);
        }
        return sessionId;
    }
    public Map<String,String> sendGet(String url, String param, String cookieVal,String code) {
        String result = "";
        HashMap<String, String> resultMap = new HashMap<>();
        BufferedReader in = null;
        try {
            String urlNameString=null;
            if(param.length()==0){
                urlNameString= url;
            }else{
                urlNameString= url + "?" + param;
            }
            System.out.println(urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Cookie", cookieVal);

            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            List<String> strings = map.get("Set-Cookie");
            String cookie= listToString(strings);
            resultMap.put("cookie",cookie);

            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),code));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        resultMap.put("data",result);
        return resultMap;
    }


    public Map<String,String> sendGet1(String url, String param, String cookieVal,String code) {
        String result = "";
        HashMap<String, String> resultMap = new HashMap<>();
        BufferedReader in = null;
        try {
            String urlNameString=null;
            if(param.length()==0){
                urlNameString= url;
            }else{
                urlNameString= url + "?" + param;
            }
            System.out.println(urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Cookie", cookieVal);

            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            List<String> strings = map.get("Set-Cookie");
            String cookie= listToString(strings);
            resultMap.put("cookie",cookie);

            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),code));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                result += line;
            }
            IOUtils.write(result, new FileOutputStream("d:/index.html"));
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        resultMap.put("data",result);
        return resultMap;
    }

    /**
     * 向指定 URL 发送POST方法的请求  发送微信统一下单、查询订单接口
     * @param url   发送请求的 URL
     * @param param 请求参数,请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static Map<String,String> sendPost(String url, String param,String cookieVal) {
        PrintWriter out = null;
        BufferedReader in = null;
        HashMap<String, String> resultMap = new HashMap<>();
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Cookie", cookieVal);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            Map<String, List<String>> map = conn.getHeaderFields();
            List<String> strings = map.get("Set-Cookie");
            String cookie= listToString(strings);
            resultMap.put("cookie",cookie);
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        resultMap.put("data",result);
        return resultMap;
    }

    /**
    * 将配置文件装换为map
    **/
    @Test
    public void test() throws FileNotFoundException {
        Map<String, String> properties = PropertiesUtil.getProperties("url.properties");
        System.out.println(properties.get("img-url"));
    }


}
