package com.jingzhun.wbsc.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2019080666145167";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCG/0cGmGPiQDw2IGbkdBEbiCyFrHolfFXw0QEjpFN1OcUWNaZ9ZlAD7UZXkvsOzU73vWWVjh3NSX1jlUyudI1olFZ2iuop4Bh/tBBUUd++PgC9Q8EzFrKd18u65GcZHx1SZipEPu4HapYSRJi6Xw4kwe9ibZbrPq3kJNSZc1QUcBEWv3mTznL5/9/mbu3XUjXnfZyFn2DeFKcC77eZWibgAX4WKUGK1nsguSRjLy2skefVkylWVkyfCuRjKYg3suh4SDOnPcn6QRRRD5L6uKS+G4wHvzXFGFEZwd93OTZCr4t2yoj+1A7CcUwqut54QHX4PCSsBp9mbqRfd+OsyGrbAgMBAAECggEAXm4lLbzdab9LHwWNU9LIwLyBgEQE8UHHD/vzUw44fvCZJ48B1ggCgqywHsQmr9T3nx4x9+qAeEAm4Mjn6cSHwha5YJSNdhtWp8W6AnF/FCFk/P9vHqCK8xSnYyUv5Qe5yBDFEhX/BNsO/NpVB1FEoHu0SuKjxgyDMOp+eJSOxPgSghnlVsUdvYZP0wr6tVatJdwQkLbE2P4ZwLeoWpFRbTnAv1cXwEABg41htJrvtIdCtyx++XoV/M+wnSJfAR1d472nT7CdemqAQ6cCI0HKhpOrZohWl6Rz36eRSP8EOsmL7jGKQNVvPRY0WnTS9t7j2ga0zbL/VfIgYYdq9kIpoQKBgQDgzrORUCgMCa6iLsyKLsdFMrNVzKCfNfGCN5nTLpOrfrryWcSA2GvVgbbh/LjrrN7xTP3Svw27wOutm1coIOaWiSFjV93vuZ1IMDZDippoOKi28HNhcV38/V1pJftEgOcZhG75V9WCKzJzmpsAvcooHhJQAiqssDitY6JA5bFrywKBgQCZunXjiUcezZU2AOJnOwQjgN3JDLrA68Y6dlibFrSzny0gWYk3sLyxRbvLqABCo2+TgU+6unVfHMHJYrOnzLQwvCzgwPs6gL9YrZla0TZbKm3pqv2ZO/6SNRyPMp8neSTa9HRonylNT/zWW+1gVzmJnZfdP/c0aqtx6Y7szJE7MQKBgELTDqueURkq859AHArjVtap+IcDmadZ6J1Vm8MbsEelFzsdFf1a18sSkONiNTHRJEBEK5susY1jDmt/povGI+URhDw8jTtKSIeB1U+EbtuWJAilNxb7FP4z7kVoReWaD5gVNsuloVDdOFUG2xaAVAxSzAy+aCMHCwH3gO6bmH67AoGAc9ROp1b0Q31bhAk+jdGl2KpsceQpgtsjzUEGmjSNixnQgqN0BIYiZt/IFDdNmuhSVa4FSQjdDwE38mlwPGq9Ckt+9QnzQa1of4SuHAOvFRnvHzU7R44wzjE+h5b43GEnJ+MxIvNpx1sRguJZBVIzhEE9//MGbHRDId19V2b7tsECgYBLYtELVpvOSpyHsFPf5tNea9/6prrhwe4TfIwpbsJ2K+NjhSiNxk4pJ/+UBdf2OOvch4QIG8pwIfW1TypO5oAQLuAzrkfO+y01tqoVNKmJUtLTD3addLD0i/S32pJ7WXs8xlkjD8YrsQffD0hqMNHCNP9Lakqwq1BDRFGEF7XTVQ==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhv9HBphj4kA8NiBm5HQRG4gshax6JXxV8NEBI6RTdTnFFjWmfWZQA+1GV5L7Ds1O971llY4dzUl9Y5VMrnSNaJRWdorqKeAYf7QQVFHfvj4AvUPBMxayndfLuuRnGR8dUmYqRD7uB2qWEkSYul8OJMHvYm2W6z6t5CTUmXNUFHARFr95k85y+f/f5m7t11I1532chZ9g3hSnAu+3mVom4AF+FilBitZ7ILkkYy8trJHn1ZMpVlZMnwrkYymIN7LoeEgzpz3J+kEUUQ+S+rikvhuMB781xRhRGcHfdzk2Qq+LdsqI/tQOwnFMKrreeEB1+DwkrAafZm6kX3fjrMhq2wIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://wbsc.ngrok.xiaomiqiu.cn/rest/pay/alipay/notify";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://wbsc.ngrok.xiaomiqiu.cn/rest/pay/alipay/return";

	// 签名方式
	public static String sign_type = "RSA2";

	// 数据格式
	public static String format = "json";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

