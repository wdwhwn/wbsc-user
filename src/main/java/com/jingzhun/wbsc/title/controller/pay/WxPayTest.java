package com.jingzhun.wbsc.title.controller.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.jingzhun.wbsc.config.AlipayConfig;
import com.jingzhun.wbsc.util.JsonUtil;
import com.jingzhun.wbsc.util.WXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.alipay.api.AlipayConstants.CHARSET;
import static com.alipay.api.AlipayConstants.SIGN_TYPE;

/**
 * Created by Administrator on 2019/8/6 0006.
 */
@Controller
@RequestMapping("pay")
@Slf4j
public class WxPayTest {
    @RequestMapping(value = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/jingzhun/wbsc/title/wxpay/pay");
    }
    /**
     *  微信支付成功的回调函数
     * @param request
     * @return
     */
    @RequestMapping(value = "wxCallBack")
    @ResponseBody
    public String callBack(HttpServletRequest request, HttpServletResponse response) {
        InputStream is = null;
        log.error("回调参数");
        try {
            //获取请求的流信息(这里是微信发的xml格式所有只能使用流来读)
            is = request.getInputStream();
            String xml = WXPayUtil.getStreamString(is);
            //将微信发的xml转map
            Map<String, String> notifyMap = WXPayUtil.xmlToMap(xml);
            log.error("回调map：" + JsonUtil.toJson(notifyMap));
            if ("SUCCESS".equals(notifyMap.get("return_code"))) {
                if ("SUCCESS".equals(notifyMap.get("result_code"))) {
                    //商户订单号
                    String payId = notifyMap.get("out_trade_no");
                    //支付金额
                    String totalFee = notifyMap.get("total_fee");
                    String attach = notifyMap.get("attach");
                    String[] split = attach.split("_");
                    log.error(JsonUtil.toJson("回调函数中参数：" + JsonUtil.toJson(split)));
                }
            }
            log.error("+++++++++++++++++++++++++++++++++支付回调：支付成功++++++++++++++++++++++++++++++++++++++++++++++++");
            //告诉微信服务器收到信息了，不要在调用回调action了========这里很重要回复微信服务器信息用流发送一个xml即可
            response.getWriter().write("<xml><return_code><![CDATA[SUCCESS]]></return_code></xml>");
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  支付宝支付成功的回调函数
     * @param request
     * @return
     */
    @RequestMapping(value = "aliNotify")
    @ResponseBody
    public String notify(HttpServletRequest request, HttpServletResponse response) throws IOException, AlipayApiException {
// 获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        log.error(JsonUtil.toJson(requestParams));
        Set<String> keySet = requestParams.keySet();
        String out_trade_no = "";
        String trade_status = "";
        String total_amount = "";
        for (String key : keySet) {
            StringBuffer buffer = new StringBuffer();
            for (String string : requestParams.get(key)) {
                buffer.append(string);
            }
            params.put(key, buffer.toString());
            if (key.equals("out_trade_no")) {
                out_trade_no = buffer.toString();// 商户订单号
                System.out.println(key + " : " + buffer.toString());
            } else if (key.equals("trade_status")) {
                trade_status = buffer.toString();// 交易状态
                System.out.println(key + " : " + buffer.toString());
            } else if (key.equals("total_amount")) {
                total_amount = buffer.toString().substring(0,buffer.toString().length()-3) + "";// 充值金额
                System.out.println(key + " : " + total_amount);
            }
        }
        System.out.println("支付异步回调"+JsonUtil.toJson(params));
//        Map<String, String> paramsMap =  //将异步通知中收到的所有参数都存放到 map 中
//        boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap, ALIPAY_PUBLIC_KEY, CHARSET, SIGN_TYPE); //调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
        System.out.println("total_amount"+signVerified);
        if(signVerified){
            // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
            System.out.println("支付成功");
            return "success";
        }else{
            // TODO 验签失败则记录异常日志，并在response中返回failure.
            System.out.println("支付失败");
        }
        return "fail";
    }

    /**
     *  支付宝支付成功的回调函数
     * @param request
     * @return
     */
    @RequestMapping(value = "aliReturn")
    @ResponseBody
    public String returnPath(HttpServletRequest request, HttpServletResponse response) throws IOException, AlipayApiException {
        // 获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        Set<String> keySet = requestParams.keySet();
        String out_trade_no = "";
        String trade_status = "";
        String total_amount = "";
        for (String key : keySet) {
            StringBuffer buffer = new StringBuffer();
            for (String string : requestParams.get(key)) {
                buffer.append(string);
            }
            params.put(key, buffer.toString());
            if (key.equals("out_trade_no")) {
                out_trade_no = buffer.toString();// 商户订单号
                System.out.println(key + " : " + buffer.toString());
            } else if (key.equals("trade_status")) {
                trade_status = buffer.toString();// 交易状态
                System.out.println(key + " : " + buffer.toString());
            } else if (key.equals("total_amount")) {
                total_amount = buffer.toString().substring(0,buffer.toString().length()-3) + "";// 充值金额
                System.out.println(key + " : " + total_amount);
            }
        }
        System.out.println("支付同步回调");
//        Map<String, String> paramsMap =  //将异步通知中收到的所有参数都存放到 map 中
//        boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap, ALIPAY_PUBLIC_KEY, CHARSET, SIGN_TYPE); //调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
        if(signVerified){
            // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
            System.out.println("支付成功");
            return "success";
        }else{
            // TODO 验签失败则记录异常日志，并在response中返回failure.
            System.out.println("支付失败");
        }
        return "fail";
    }
}
