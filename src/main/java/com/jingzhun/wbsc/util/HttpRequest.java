package com.jingzhun.wbsc.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;


public class HttpRequest {
    private static final Logger log = LoggerFactory.getLogger(HttpRequest.class);
    public static void main(String[] args) {

    }


    /**
     * 向指定URL发送GET方法的请求
     * 文本生成  登录 get请求
     */
    public static String sendGet(String url, String param, String cookieVal) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
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
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
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
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求  发送微信统一下单、查询订单接口
     *
     * @param url   发送请求的 URL
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param,String cookieVal) {
//        String param = StringToUTF8.toUTF8(param1);
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Cookie", cookieVal);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);

            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
//            out.print(param.getBytes("UTF-8"));
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
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
        return result;
    }

    /**
     * 文本生成
     * post请求
     */
    public static String sendPost1(String restUrl, String param,String cookieVal) {
        String result = "";
        int responseCode;
        HttpURLConnection conn = null;
        try {
            // 打开URL
            StringBuilder sbURL = new StringBuilder(restUrl);
            URL url = new URL(sbURL.toString());
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setRequestProperty("Cookie", cookieVal);
            // 请求体参数设置
            //System.out.println("request param:" + param);
            // 加入请求体
            conn.setDoOutput(true);
            //输入流
            //OutputStream os = conn.getOutputStream();
            OutputStreamWriter  os = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            os.write(param);
            os.flush();
            // 输出response code
            responseCode = conn.getResponseCode();
            // 输出response
            if(responseCode == 200){
                //输出流
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
                result = reader.readLine();
            }else{
                result="false";
            }
            // 断开连接
            conn.disconnect();
        } catch (Exception e) {
            result="false";
            conn.disconnect();
            log.error("post请求提交失败:" + restUrl, e);
        }
        return result;
    }

//  文本生成-图片上传
    public static String postFile(String filePath, String url, Map<String, String> map,String cookieVal)
    throws ClientProtocolException,IOException    {
            String line = "";
            FileBody bin = null;
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);
                    httppost.setHeader("Cookie",cookieVal);
            MultipartEntity entity = new MultipartEntity();
            //entity.addPart("Cookie",new StringBody(cookieVal));
            /*for (String key : map.keySet()) {
            log.info("key:" + key + "-------value:" + map.get(key));
            entity.addPart(key, new StringBody(map.get(key), Charset.forName("UTF-8")));
            }*/
            if (StringUtils.isNotEmpty(filePath)) {
            FileBody fileBody = new FileBody(new File(filePath));
            //上传文件的key
            entity.addPart("file", fileBody);
            }
                httppost.setEntity(entity);
                HttpResponse response = httpclient.execute(httppost);
                if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entitys = response.getEntity();
                BufferedReader reader = new BufferedReader(new InputStreamReader(entitys.getContent()));
                line = reader.readLine();
                log.info("=======>>line" + line);
            } else {
                HttpEntity r_entity = response.getEntity();
                line = EntityUtils.toString(r_entity);
                log.info("错误码是：" + response.getStatusLine().getStatusCode() + "  " + response.getStatusLine().getReasonPhrase());
                log.info("出错原因是：" + line);
                // 你需要根据出错的原因判断错误信息，并修改
            }
            httpclient.getConnectionManager().shutdown();
            return line;
}


}


