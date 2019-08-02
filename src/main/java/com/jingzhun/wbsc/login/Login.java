package com.jingzhun.wbsc.login;

import com.jingzhun.wbsc.user.entity.TUserWebEntity;
import com.jingzhun.wbsc.util.*;
import com.jingzhun.wbsc.web.entity.TWebEntity;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/6/11 0011.
 *
 */
@Controller
@RequestMapping("/tLoginController")
public class Login extends ApiBaseAction{
    private static final Logger log = LoggerFactory.getLogger(Login.class);
    @Autowired
    private SystemService systemService;
    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/jingzhun/wbsc/login/login");
    }
    @RequestMapping(params = "login")
    @ResponseBody
    public String  login(HttpServletRequest request)  {
        /*++++++++++++++++++第1步：获取用户账号密码、网址+++++++++++++++++*/
        String webId = request.getParameter("webId");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        TWebEntity tWebEntity = this.systemService.get(TWebEntity.class, Integer.parseInt(webId));
        System.out.println("tWebEntity:"+tWebEntity);
        Map<String, String> cookies = null;
        while(true) {
            try {
                cookies = getCookie(tWebEntity, username, password);
            } catch (UnknownHostException e) {
                e.printStackTrace();
                continue;
            }catch(Exception e){
                e.printStackTrace();
                return JsonUtil.toJson(toResponsFail("网络不稳定，请联系管理员"));
            }
            break;
        }
        if(cookies==null){
            return JsonUtil.toJson(toResponsFail("账号密码错误"));
        }

        TSUser user = ResourceUtil.getSessionUser();
        System.out.println("用户key："+user.getUserKey());
        String userKey = user.getUserKey();
        String sql="select id id,web_id webId,username username,password,user_key userKey,date date from t_user_web where  web_id="+webId +" and user_key='"+userKey+"' and username='" +username+"'";
        log.error(sql);
        List<Map<String, Object>> entity = this.systemService.findForJdbc(sql);
        TUserWebEntity tUserWebEntity = new TUserWebEntity();
        tUserWebEntity.setPassword(password);
        tUserWebEntity.setUsername(username);
        tUserWebEntity.setWebId(Integer.valueOf(webId));
        tUserWebEntity.setUserKey(userKey);
        tUserWebEntity.setDate(new Date());
        log.error(JsonUtil.toJson(tUserWebEntity));
        Integer userWebId=null;
        if(entity==null || entity.size()==0 ){
         this.systemService.save(tUserWebEntity);
            //        获取userWebId
            String sql1="select id id,web_id webId,username username,password,user_key userKey,date date from t_user_web where  web_id="+webId +" and user_key='"+userKey+"'";
            log.error(sql1);
            List<Map<String, Object>> entity1 = this.systemService.findForJdbc(sql1);
            userWebId = Integer.parseInt(entity1.get(0).get("id").toString());
        }else{
            userWebId=Integer.parseInt(entity.get(0).get("id").toString());
        }
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
        System.out.println("当前用户的userWebId: "+userWebId);
        /*++++++++++++++++++第3步：cookie存储到session中+++++++++++++++++*/

        HttpSession session = request.getSession();
        if(tUserWebEntity!=null&&tWebEntity!=null) {
            session.setAttribute("cookies", cookies);
            session.setAttribute("web", tWebEntity);
            session.setAttribute("userWeb",tUserWebEntity);
        }
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
    return JsonUtil.toJson(toResponsMsgSuccess("登录成功"));
    }

    public Map<String,String> getCookie(TWebEntity tWebEntity,String username,String password) throws  Exception {

        String loginUrl = tWebEntity.getUrl();
        String identification = tWebEntity.getIdentification();
        //        登录，根据data，判断用户名和密码是否正确
        String paramLogin = tWebEntity.getParamLogin();
        paramLogin=paramLogin.replace("USERNAME",username).replace("PASSWORD",password);
        String cookie=null;
        StringBuilder sb=new StringBuilder("headfile/");
        sb.append(identification);
        sb.append("/");
        sb.append(identification);
        sb.append("_login.properties");
        Map<String, String> loginHeadMap = PropertiesUtil.getProperties(sb.toString());
        Map<String, String> cookies = Jsoup.connect(loginUrl).ignoreContentType(true).ignoreHttpErrors(true)
                .headers(loginHeadMap)
                .requestBody(paramLogin)
                .method(Connection.Method.POST)
                .followRedirects(false)
                .execute()
                .cookies();
        return cookies;
    }
    @RequestMapping(params = "loginTest")
    @ResponseBody
    public Map<String,Object>  loginTest(HttpServletRequest request) throws IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        /*++++++++++++++++++第1步：获取用户账号密码、网址+++++++++++++++++*/
        TSUser user = ResourceUtil.getSessionUser();
        System.out.println("用户key："+user.getUserKey());
        String userKey = user.getUserKey();
        Jedis jedis = JedisUtil.getJedis();
        String cookie = jedis.hget("cookie", userKey);
        String code="UTF-8";
        String webId = request.getParameter("webId");
        TWebEntity tWebEntity = this.systemService.get(TWebEntity.class, Integer.parseInt(webId));
        String webName = tWebEntity.getWebName();
        /*++++++++++++++++++第1：勤发布+++++++++++++++++*/
        if(cookie!=null && "勤发布网站".equals(webName)){
            String url="http://my.qinfabu.com/Product/Add";
            String param1="cateid=3000101101";
            HttpRequest.sendGet(url,param1,cookie,code);
        }
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

         /*++++++++++++++++++第2：东方供应网+++++++++++++++++*/
       else if(cookie!=null&& "东方供应网".equals(webName)) {
            String url = "http://user.eastsoo.com/user/index.do";
            String param1 = "";
            HttpRequest.sendGet(url, param1, cookie, code);
        }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        /*++++++++++++++++++第3：搜好货网+++++++++++++++++*/
      else if(cookie!=null && "搜好货网".equals(webName)) {
            String url = "http://www.912688.com/sellercenter";
            String param1 = "";
            HttpRequest.sendGet(url, param1, cookie, code);
        }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

       /*++++++++++++++++++第4：中国电子商务+++++++++++++++++*/
       else if(cookie!=null&& "中国电子商务网".equals(webName) ) {
            String url = "http://www.cebn.cn/member/";
            String param1 = "";
            HttpRequest.sendGet(url, param1, cookie, code);
        }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        /*++++++++++++++++++第5：必途网+++++++++++++++++*/
       else if(cookie!=null && "必途网".equals(webName)) {
            String url = "http://biz.b2b.cn/manage/Member/UserContact.aspx";
            String param1 = "";
            code = "GB2312";
            HttpRequest.sendGet(url, param1, cookie, code);
        }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        /*++++++++++++++++++第6：制造交易网+++++++++++++++++*/
       else if(cookie!=null && "制造交易网（中交网）".equals(webName)) {
            String url="http://myv2.cn.c-c.com/member/smrz/";
            String param1="";
            HttpRequest.sendGet(url,param1,cookie,code);
        }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

       /*++++++++++++++++++第7：化工网+++++++++++++++++*/
       else if(cookie!=null && "化工网".equals(webName)) {
            String url="https://china.chemnet.com/member/Action.cgi";
            String param1="t=main1&hnjzsoft";
            code="GB2312";
            HttpRequest.sendGet(url,param1,cookie,code);
        }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        /*++++++++++++++++++第8：第一枪+++++++++++++++++*/
       else if(cookie!=null && "第一枪".equals(webName)) {
            String url="https://www.d17.cc/d17/user/main";
            String param1="";
            HttpRequest.sendGet(url,param1,cookie,code);
        }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        /*++++++++++++++++++第9：企业谷+++++++++++++++++*/
       else if(cookie!=null&& "企业谷".equals(webName)) {
            String url="http://www.qiyegu.com/member/edit.php";
            String param1="tab=2";
            HttpRequest.sendGet(url,param1,cookie,code);
        }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        /*++++++++++++++++++第10：八方资源网+++++++++++++++++*/
       else if(cookie!=null&& "八方资源网".equals(webName)) {
            String url="http://m.b2b168.com/Index.aspx";
            String param1="pg=index";
            HttpRequest.sendGet(url,param1,cookie,code);
        }
       /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
       jedis.close();
        return toResponsSuccess("成功");
    }
}
