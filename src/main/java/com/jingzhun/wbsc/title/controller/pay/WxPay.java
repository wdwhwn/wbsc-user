package com.jingzhun.wbsc.title.controller.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.jingzhun.wbsc.config.AlipayConfig;
import com.jingzhun.wbsc.title.controller.TArticleController;
import com.jingzhun.wbsc.util.ApiBaseAction;
import com.jingzhun.wbsc.util.JsonUtil;
import com.jingzhun.wbsc.util.QrCodeUtils;
import com.jingzhun.wbsc.util.WXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/8/5 0005.
 */
@Controller
@RequestMapping("wxpay")
@Slf4j
public class WxPay extends ApiBaseAction{

    private static final Logger logger = LoggerFactory.getLogger(TArticleController.class);


    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/jingzhun/wbsc/title/wxpay/pay");
    }


    @RequestMapping(params = "wxPay")
    @ResponseBody
    public AjaxJson wxPay(HttpServletRequest req) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setMsg("获取二维码成功");
        String money = req.getParameter("money")+"";
        String ip=getClientIp(req);
        BigDecimal wxpay=new BigDecimal(money);
        String attach="43_23";
        System.out.println("中国");
        String notifyUrl="http://wbsc.ngrok.xiaomiqiu.cn/rest/pay/wxCallBack";
        Map<String, String> pay = WXPayUtil.pay(ip, wxpay, attach, notifyUrl, "456");
        if("1".equals(pay.get("code"))){
            ajaxJson.setMsg("生成二维码失败");
            ajaxJson.setObj("生成二维码失败，请联系管理员");
            return ajaxJson;
        }
        String binary = QrCodeUtils.creatRrCode(pay.get("data"), 200, 200);
        ajaxJson.setObj(binary);
        return ajaxJson;
    }
    /**
     * 微信支付弹出框
     * @return
     */
    @RequestMapping(params = "binary")
    public ModelAndView goAdd( HttpServletRequest req) {
        String money = req.getParameter("money")+"";
        ModelAndView modelAndView = new ModelAndView("com/jingzhun/wbsc/title/wxpay/twoDimensional");
        req.setAttribute("money", money);
        return modelAndView;
    }

    @RequestMapping(params = "aliPay")
    @ResponseBody
    public String aliPay(HttpServletRequest req,HttpServletResponse httpResponse) throws IOException {
        String money = req.getParameter("money");
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl,AlipayConfig.app_id,AlipayConfig.merchant_private_key,AlipayConfig.format,AlipayConfig.charset,AlipayConfig.alipay_public_key,AlipayConfig.sign_type);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("http://wbsc.ngrok.xiaomiqiu.cn/rest/pay/aliReturn");
        alipayRequest.setNotifyUrl("http://wbsc.ngrok.xiaomiqiu.cn/rest/pay/aliNotify");//在公共参数中设置回跳和通知地址
        HashMap<String, Object> param = new HashMap<String,Object>();
        param.put("out_trade_no",System.currentTimeMillis());
        param.put("product_code","FAST_INSTANT_TRADE_PAY");
        param.put("total_amount",money);
//        标题
        param.put("subject","测试商品");
//        描述
        param.put("body","测试商品");
        System.out.println(JsonUtil.toJson(param));
        alipayRequest.setBizContent(JsonUtil.toJson(param));//填充业务参数
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        log.error(form);
        return form ;
    }
}
