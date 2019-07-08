package com.jingzhun.wbsc.login;

import com.jingzhun.wbsc.user.entity.TUserWebEntity;
import com.jingzhun.wbsc.util.ApiBaseAction;
import com.jingzhun.wbsc.util.JedisUtil;
import com.jingzhun.wbsc.util.JsonUtil;
import com.jingzhun.wbsc.web.entity.TWebEntity;
import org.jeecgframework.web.system.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
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
    public Map<String,Object>  login(HttpServletRequest request) throws IOException {
        /*++++++++++++++++++第1步：获取用户账号密码、网址+++++++++++++++++*/
        HttpSession session = request.getSession();
        String webId = request.getParameter("webId");
        TWebEntity tWebEntity = this.systemService.get(TWebEntity.class, Integer.parseInt(webId));
        String surl = tWebEntity.getUrl();
        String username = request.getParameter("demotitle");
        String password = request.getParameter("demotitle1");
        Object userId1 = session.getAttribute("userId");
        System.out.println(userId1.toString());
        Integer userId = Integer.parseInt(userId1.toString());
        String sql="select * from t_user_web where user_id="+userId+" and web_id="+webId;
        log.error(sql);
        List<Map<String, Object>> entity = this.systemService.findForJdbc(sql);
       log.error(JsonUtil.toJson(entity));
       log.error(JsonUtil.toJson("0".equals(entity)));
       log.error(JsonUtil.toJson("".equals(entity)));
       log.error(JsonUtil.toJson(entity.size()));
        TUserWebEntity tUserWebEntity = new TUserWebEntity();
        tUserWebEntity.setPassword(password);
        tUserWebEntity.setUsername(username);
        tUserWebEntity.setWebId(Integer.valueOf(webId));
        tUserWebEntity.setUserId(userId);
        log.error(JsonUtil.toJson(tUserWebEntity));
        if(entity==null || entity.size()==0 ){
            this.systemService.save(tUserWebEntity);
        }else{
            String sql1="select id,user_id userId,web_id webId,username,password from t_user_web where username="+username+" and password="+password;
            List<Map<String, Object>> forJdbc = this.systemService.findForJdbc(sql);
            if(forJdbc==null){
                return toResponsFail("账号密码错误");
            }
        }
        System.out.println(webId + " " + username + " " + password);
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        /*++++++++++++++++++第2步：获取cookie+++++++++++++++++*/
        URL url = new URL(surl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);  //打开输出，向服务器输出参数（POST方式、字节）（写参数之前应先将参数的字节长度设置到配置"Content-Length"<字节长度>）
        connection.setDoInput(true);  //打开输入，从服务器读取返回数据
        connection.setRequestMethod("POST"); //设置登录模式为POST（字符串大写）
        connection.setInstanceFollowRedirects(false);
        connection.connect();
        OutputStreamWriter out = new OutputStreamWriter(connection
                .getOutputStream(), "utf-8");
        out.write("username="+username+"&password="+password);
        out.flush();
        out.close();
        String cookieVal = connection.getHeaderField("Set-Cookie");  //格式:JSESSIONID=541884418E77E7F07363CCEE91D4FF7E; Path=/
        log.error("cookie: " + cookieVal);
        connection.disconnect();
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        /*++++++++++++++++++第3步：cookie存储到session中+++++++++++++++++*/
        Jedis jedis = JedisUtil.getJedis();
        String loginName=null;
        jedis.set("userId",userId+"");
        if(cookieVal!=null){
            jedis.set(userId+"", cookieVal);
        }
        jedis.close();
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
    return toResponsMsgSuccess("成功 ");
    }
}
