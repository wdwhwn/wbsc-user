package com.jingzhun.wbsc.config;

/**
 * @author scw
 * @create 2018-01-18 15:31
 * @desc 项目相关的静态量
 **/
public class ProjectConst {
    /**
     * 创建自定义菜单
     */
    public final static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**
     * 用于获取当前与微信公众号交互的用户信息的接口（一般是用第二个接口地址）
     */
    public static final String GET_WEIXIN_USER_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
    public final static String GET_PAGE_USERS_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    /**
     * 用于进行网页授权验证的接口URL，通过这个才可以得到opendID等字段信息
     */
    public final static String GET_WEBAUTH_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    /**
     * 用于进行当点击按钮的时候，能够在网页授权之后获取到code，再跳转到自己设定的一个URL路径上的接口，这个主要是为了获取之后于
     * 获取openId的接口相结合
     * 注意：参数：toselfURL  表示的是当授权成功后，跳转到的自己设定的页面，所以这个要根据自己的需要进行修改
     */
    public final static String GET_WEIXINPAGE_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=toselfURL&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";

    /**
     * 获取access_token的URL
     */
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     *  判断access_token是否失效
     */
    public static final String ACCESS_CHECK_URL = "https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID"; /**
     /*
     *  刷新access_token
     *  */
    public static final String ACCESS_REFRESH_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=ACCESS_TOKEN";


    /**
     * 统一支付接口
     */
//    public static final String UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/sandboxnew/pay/unifiedorder";
    public static final String UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**
     * 查询订单接口
     */
    public static final String ORDERQUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";


    /**
     * 发送模板消息
     */
    public static final String TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";/**

     /**
     * 沙箱环境获取signkey
     */
    public static final String SIGNKEY_URL = "https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey";



    /**
     * 获取临时素材
     */
    public static final String MEDIA_GETURL = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";

    public final static String APPID = "wxb1bc1bc7377fe4e4";
//    public final static String APPSECRET = "910415b15c96d7268cc1ee899f5fc8cd";
    public final static String MCHID = "1514408471";
    public final static String PATERNERKEY = "mcccmf9lX4TCTDGVJnEASSFGCUMp1HQx";
    public final static String TOKEN = "jingzhun";


    /**
     *  请求消息类型：事件
     */
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    /**
     * 事件类型：subscribe(关注)
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

    /**
     * 事件类型：unsubscribe(取消关注)
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

    /**
     * 事件类型：CLICK(自定义菜单点击事件)
     */
    public static final String EVENT_TYPE_CLICK = "CLICK";


    /**
     * 类型：IMAGE
     */
    public static final String REQ_MESSAGE_TYPE_IMAGE = "IMAGE";

    /**
     * 类型：LOCATION
     */
    public static final String REQ_MESSAGE_TYPE_LOCATION = "LOCATION";

    /**
     * 类型：LINK
     */
    public static final String REQ_MESSAGE_TYPE_LINK = "LINK";

    /**
     * 类型：TEXT
     */
    public static final String REQ_MESSAGE_TYPE_TEXT = "TEXT";

    /**
     * 类型：VOICE
     */
    public static final String REQ_MESSAGE_TYPE_VOICE = "VOICE";



}
