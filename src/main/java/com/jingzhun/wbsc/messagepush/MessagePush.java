package com.jingzhun.wbsc.messagepush;

import com.jingzhun.wbsc.util.JedisUtil;
import com.jingzhun.wbsc.util.JsonUtil;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2019/6/11 0011.
 *
 */
@Controller
@RequestMapping("/tMessagePush")
public class MessagePush {
    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("com/jingzhun/wbsc/messagepush/messagepush");
        
        /*++++++++++++++++++第1步：生成标题+++++++++++++++++*/
        Jedis jedis = JedisUtil.getJedis();
        String username = jedis.get("username");
        String titles = jedis.hget("title", username);
        List<String> lists = JsonUtil.toObject(titles, List.class);
        int i = (int) (Math.random() * lists.size());
        String title = lists.get(i);
        modelAndView.addObject("title",title);
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        
        /*++++++++++++++++++第2步：展示关键词+++++++++++++++++*/
        String keyWords = jedis.hget("keyWords", username);
        modelAndView.addObject("keywords",keyWords);
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        
        /*++++++++++++++++++第3步：生成文本+++++++++++++++++*/

        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
        return modelAndView;
    }

}
