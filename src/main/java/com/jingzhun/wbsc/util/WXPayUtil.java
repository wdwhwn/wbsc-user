package com.jingzhun.wbsc.util;

import com.alibaba.fastjson.JSONException;
import com.jingzhun.wbsc.config.MyX509TrustManager;
import com.jingzhun.wbsc.util.WXPayConstants.SignType;
import com.jingzhun.wbsc.config.ProjectConst;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.jeecgframework.p3.core.common.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import redis.clients.jedis.Jedis;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.*;

import static org.jsoup.nodes.Document.OutputSettings.Syntax.xml;

@Slf4j
public class WXPayUtil {
    private static final Logger logger = LoggerFactory.getLogger(WXPayUtil.class);

    private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final Random RANDOM = new SecureRandom();

    public static Map<String, String> pay( String ip, BigDecimal wxpay, String attach, String notifyUrl,String productId) {
        Map<String, String> stringObjectHashMap = new HashMap<>(4);
        try {
            /*++++++++++++++++++第1步：拼接 统一下单接口的参数+++++++++++++++++*/
            //拼接统一下单地址参数
            Map<String, String> paraMap = new HashMap<String, String>(17);
            paraMap.put("product_id", productId);
            //获取请求ip地址
            paraMap.put("spbill_create_ip", ip);
            paraMap.put("appid", ProjectConst.APPID);
            String body = "会员积分";
            paraMap.put("body", body);
//            商户id
            paraMap.put("mch_id", ProjectConst.MCHID);
            String nonceStr=WXPayUtil.generateNonceStr();
            paraMap.put("nonce_str", nonceStr);
            //订单号
            String outTradeNo = System.currentTimeMillis() + "";
            paraMap.put("out_trade_no", outTradeNo);
//              换算为分
            wxpay = wxpay.multiply(new BigDecimal(100));
            String totalFee = wxpay + "";
            if (totalFee.contains(".")) {
                totalFee = totalFee.substring(0, totalFee.indexOf('.'));
            }
            paraMap.put("total_fee", totalFee);
            paraMap.put("trade_type", "NATIVE");
            paraMap.put("attach", attach);
            log.error("回调函数：" + notifyUrl);
            // 此路径是微信服务器调用支付结果通知路径随意写
            paraMap.put("notify_url", notifyUrl);

//              沙箱环境
            /*++++++++++++++++++第步：沙箱环境+++++++++++++++++*/
            String sign ="";
            if(ProjectConst.UNIFIEDORDER_URL.contains("sandboxnew")) {
                HashMap<String, String> stringStringHashMap = new HashMap<>();
                stringStringHashMap.put("mch_id", ProjectConst.MCHID);
                stringStringHashMap.put("nonce_str", WXPayUtil.generateNonceStr());
                stringStringHashMap.put("sign", WXPayUtil.generateSignature(stringStringHashMap, ProjectConst.PATERNERKEY));
                String s = WXPayUtil.mapToXml(stringStringHashMap);
                String ss = HttpRequest.sendPost(ProjectConst.SIGNKEY_URL, s);
                log.error("沙箱key：" + ss);
                String sandbox_signkey = "";
                if (ss.indexOf("SUCCESS") != -1) {
                    Map<String, String> map = WXPayUtil.xmlToMap(ss);
                    sandbox_signkey = map.get("sandbox_signkey");
                }
                sign = WXPayUtil.generateSignature(paraMap, sandbox_signkey);
            }else{
                //            正式环境
            sign = WXPayUtil.generateSignature(paraMap, ProjectConst.PATERNERKEY);
            }
            /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

            paraMap.put("sign", sign);

            //将所有参数(map)转xml格式
            String xml = WXPayUtil.mapToXml(paraMap);
            log.error("请求获取预支付id发送的xml  " + xml);
            /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

            /*++++++++++++++++++第2步：通过调用统一支付接口，获取到预支付id+++++++++++++++++*/
            String unifiedorderUrl = ProjectConst.UNIFIEDORDER_URL;
            //发送post请求"统一下单接口"返回预支付id:prepay_id
            String xmlStr = HttpRequest.sendPost(unifiedorderUrl, xml);
            //以下内容是返回前端页面的json数据
            log.error("获取预支付id接收的xml" + xmlStr);
            //预支付id
            String prepayId = "";
            String code_url="";
            if (xmlStr.indexOf("SUCCESS") != -1) {
                Map<String, String> map = WXPayUtil.xmlToMap(xmlStr);
                prepayId = map.get("prepay_id");
                code_url = map.get("code_url");
            }
            /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

            stringObjectHashMap.put("code", "0");
            stringObjectHashMap.put("message", "支付成功");
            stringObjectHashMap.put("data", code_url);
            return stringObjectHashMap;
            /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        stringObjectHashMap.put("code", "1");
        stringObjectHashMap.put("message", "支付失败");
        return stringObjectHashMap;
    }

    /**
     * XML格式字符串转换为Map
     *
     * @param strXML XML字符串
     * @return XML数据转换后的Map
     * @throws Exception
     */
    public static Map<String, String> xmlToMap(String strXML) throws Exception {
        try {
            Map<String, String> data = new HashMap<String, String>(3);
            DocumentBuilder documentBuilder = WXPayXmlUtil.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
            org.w3c.dom.Document doc = documentBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int idx = 0; idx < nodeList.getLength(); ++idx) {
                Node node = nodeList.item(idx);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                    data.put(element.getNodeName(), element.getTextContent());
                }
            }
            try {
                stream.close();
            } catch (Exception ex) {
                // do nothing
            }
            return data;
        } catch (Exception ex) {
            WXPayUtil.getLogger().warn("Invalid XML, can not convert to map. Error message: {}. XML content: {}", ex.getMessage(), strXML);
            throw ex;
        }

    }

    /**
     * 将Map转换为XML格式的字符串
     *
     * @param data Map类型数据
     * @return XML格式的字符串
     * @throws Exception
     */
    public static String mapToXml(Map<String, String> data) throws Exception {
        org.w3c.dom.Document document = WXPayXmlUtil.newDocument();
        org.w3c.dom.Element root = document.createElement("xml");
        document.appendChild(root);
        for (String key : data.keySet()) {
            String value = data.get(key);
            if (value == null) {
                value = "";
            }
            value = value.trim();
            org.w3c.dom.Element filed = document.createElement(key);
            filed.appendChild(document.createTextNode(value));
            root.appendChild(filed);
        }
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMSource source = new DOMSource(document);
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);
        String output = writer.getBuffer().toString();
        try {
            writer.close();
        } catch (Exception ex) {
        }
        return output;
    }


    /**
     * 生成带有 sign 的 XML 格式字符串
     *
     * @param data Map类型数据
     * @param key  API密钥
     * @return 含有sign字段的XML
     */
    public static String generateSignedXml(final Map<String, String> data, String key) throws Exception {
        return generateSignedXml(data, key, SignType.MD5);
    }

    /**
     * 生成带有 sign 的 XML 格式字符串
     *
     * @param data     Map类型数据
     * @param key      API密钥
     * @param signType 签名类型
     * @return 含有sign字段的XML
     */
    public static String generateSignedXml(final Map<String, String> data, String key, SignType signType) throws Exception {
        String sign = generateSignature(data, key, signType);
        data.put(WXPayConstants.FIELD_SIGN, sign);
        return mapToXml(data);
    }


    /**
     * 判断签名是否正确
     *
     * @param xmlStr XML格式数据
     * @param key    API密钥
     * @return 签名是否正确
     * @throws Exception
     */
    public static boolean isSignatureValid(String xmlStr, String key) throws Exception {
        Map<String, String> data = xmlToMap(xmlStr);
        if (!data.containsKey(WXPayConstants.FIELD_SIGN)) {
            return false;
        }
        String sign = data.get(WXPayConstants.FIELD_SIGN);
        return generateSignature(data, key).equals(sign);
    }

    /**
     * 判断签名是否正确，必须包含sign字段，否则返回false。使用MD5签名。
     *
     * @param data Map类型数据
     * @param key  API密钥
     * @return 签名是否正确
     * @throws Exception
     */
    public static boolean isSignatureValid(Map<String, String> data, String key) throws Exception {
        return isSignatureValid(data, key, SignType.MD5);
    }

    /**
     * 判断签名是否正确，必须包含sign字段，否则返回false。
     *
     * @param data     Map类型数据
     * @param key      API密钥
     * @param signType 签名方式
     * @return 签名是否正确
     * @throws Exception
     */
    public static boolean isSignatureValid(Map<String, String> data, String key, SignType signType) throws Exception {
        if (!data.containsKey(WXPayConstants.FIELD_SIGN)) {
            return false;
        }
        String sign = data.get(WXPayConstants.FIELD_SIGN);
        return generateSignature(data, key, signType).equals(sign);
    }

    /**
     * 生成签名
     *
     * @param data 待签名数据
     * @param key  API密钥
     * @return 签名
     */
    public static String generateSignature(final Map<String, String> data, String key) throws Exception {
        return generateSignature(data, key, SignType.MD5);
    }


    /**
     * 生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
     *
     * @param data     待签名数据
     * @param key      API密钥
     * @param signType 签名方式
     * @return 签名
     */
    public static String generateSignature(final Map<String, String> data, String key, SignType signType) throws Exception {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (k.equals(WXPayConstants.FIELD_SIGN)) {
                continue;
            }
            // 参数值为空，则不参与签名
//            logger.error(JsonUtil.toJson(data));
//            logger.error(k + "   " + JsonUtil.toJson(data.get(k)));
            if (data.get(k).trim().length() > 0) {
                sb.append(k).append("=").append(data.get(k).trim()).append("&");
            }
        }
        sb.append("key=").append(key);
        if (SignType.MD5.equals(signType)) {
            return MD5(sb.toString()).toUpperCase();
        } else if (SignType.HMACSHA256.equals(signType)) {
            return HMACSHA256(sb.toString(), key);
        } else {
            throw new Exception(String.format("Invalid sign_type: %s", signType));
        }
    }


    /**
     * 获取随机字符串 Nonce Str
     *
     * @return String 随机字符串
     */
    public static String generateNonceStr() {
        char[] nonceChars = new char[32];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }


    /**
     * 生成 MD5
     *
     * @param data 待处理数据
     * @return MD5结果
     */
    public static String MD5(String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 生成 HMACSHA256
     *
     * @param data 待处理数据
     * @param key  密钥
     * @return 加密结果
     * @throws Exception
     */
    public static String HMACSHA256(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secretKey);
        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 日志
     *
     * @return
     */
    public static Logger getLogger() {
        Logger logger = LoggerFactory.getLogger("wxpay java sdk");
        return logger;
    }


    /**
     * 输入流转换为字符串
     * @param tInputStream
     * @return
     */
    public static String getStreamString(InputStream tInputStream) {
        if (tInputStream != null) {
            try {
                BufferedReader tBufferedReader = new BufferedReader(new InputStreamReader(tInputStream));
                StringBuffer tStringBuffer = new StringBuffer();
                String sTempOneLine = new String("");
                while ((sTempOneLine = tBufferedReader.readLine()) != null) {
                    tStringBuffer.append(sTempOneLine);
                }
                return tStringBuffer.toString();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }


    public static net.sf.json.JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        net.sf.json.JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod)) {
                httpUrlConn.connect();
            }

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = net.sf.json.JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            log.error("Weixin server connection timed out.");
        } catch (Exception e) {
            log.error("https request error:{}", e);
        }
        return jsonObject;
    }

    /**
     * 获取当前时间戳，单位秒
     *
     * @return
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 获取当前时间戳，单位毫秒
     *
     * @return
     */
    public static long getCurrentTimestampMs() {
        return System.currentTimeMillis();
    }

}
