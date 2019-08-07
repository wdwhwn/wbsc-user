package com.jingzhun.wbsc;

import com.jingzhun.wbsc.config.ProjectConst;
import com.jingzhun.wbsc.util.HttpRequest;
import com.jingzhun.wbsc.util.WXPayUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/8/5 0005.
 */
public class TestF{
    /**
    * 沙箱查看  支付结果
    *
    **/
    @Test
    public void test2() throws Exception {
        Map<String, String> stringStringHashMapap = new HashMap<String, String>();
        stringStringHashMapap.put("out_trade_no","1565006299463");
        stringStringHashMapap.put("mch_id", ProjectConst.MCHID);
        stringStringHashMapap.put("sign",WXPayUtil.generateSignature(stringStringHashMapap, "9e4db4c6020e9491853051244b95f39e"));
        String s = WXPayUtil.mapToXml(stringStringHashMapap);
        String ss = HttpRequest.sendPost("https://api.mch.weixin.qq.com/sandboxnew/pay/orderquery", s);
        System.out.println(ss);
    }

    /**
    *  沙箱对账单
     *  失败
    *
    **/
    @Test
    public void test3() throws Exception {
        Map<String, String> stringStringHashMapap = new HashMap<String, String>();
        stringStringHashMapap.put("mch_id",ProjectConst.MCHID);
        stringStringHashMapap.put("sign",WXPayUtil.generateSignature(stringStringHashMapap, "9e4db4c6020e9491853051244b95f39e"));
        String s = WXPayUtil.mapToXml(stringStringHashMapap);
        String ss = HttpRequest.sendPost(" https://api.mch.weixin.qq.com/sandboxnew/pay/downloadbill", s);
        System.out.println(ss);
    }

    /**
    * 查询订单信息
    **/
    @Test
    public void test4() throws Exception {
        String u="https://api.mch.weixin.qq.com/pay/orderquery";
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("appid",ProjectConst.APPID);
        stringStringHashMap.put("mch_id",ProjectConst.MCHID);
        stringStringHashMap.put("out_trade_no", "1565053617698");
        stringStringHashMap.put("nonce_str",WXPayUtil.generateNonceStr());
        stringStringHashMap.put("sign_type","MD5");
        String sign = WXPayUtil.generateSignature(stringStringHashMap, ProjectConst.PATERNERKEY);
        stringStringHashMap.put("sign",sign);
        String s = WXPayUtil.mapToXml(stringStringHashMap);
        String s1 = HttpRequest.sendPost(u, s);
        System.out.println(s1);
    }

   /**
   **/
   @Test
   public void test5() throws IOException {
//       String notifyUrl="http://wbsc.ngrok.xiaomiqiu.cn/pay.do?wxCallBack";
//       String notifyUrl="http://wbsc.ngrok.xiaomiqiu.cn/rest/pay.do?wxCallBack";
       String notifyUrl="http://wbsc.ngrok.xiaomiqiu.cn/rest/pay/wxCallBack";
//       String notifyUrl="http://127.0.0.1:8083/rest/wxpay/callBack";
       Document post = Jsoup.connect(notifyUrl).ignoreHttpErrors(true).ignoreContentType(true)
               .post();
       System.out.println(post);
   }

}
