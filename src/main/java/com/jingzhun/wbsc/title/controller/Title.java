package com.jingzhun.wbsc.title.controller;

import com.jingzhun.wbsc.configuration.entity.TLocationEntity;
import com.jingzhun.wbsc.img.entity.TImageEntity;
import com.jingzhun.wbsc.img.service.TImageServiceI;
import com.jingzhun.wbsc.title.entity.Msg;
import com.jingzhun.wbsc.title.service.TitleService;
import com.jingzhun.wbsc.user.entity.TUserEntity;
import com.jingzhun.wbsc.util.HttpRequest;
import com.jingzhun.wbsc.util.JedisUtil;
import com.jingzhun.wbsc.util.JsonUtil;
import com.jingzhun.wbsc.category.entity.TCategoryEntity;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by Administrator on 2019/6/11 0011.
 */
@Controller
@RequestMapping("/tTitle")

public class Title {
    private static final Logger log = LoggerFactory.getLogger(Title.class);
    @Autowired
    private SystemService systemService;
    @Autowired
    private TImageServiceI tImageService;

    @Autowired
    private TitleService titleService;
    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/jingzhun/wbsc/title/title");
    }

    @RequestMapping(params="getTreeData",method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public AjaxJson getTreeData(TSDepart depatr, HttpServletResponse response, HttpServletRequest request ){
        AjaxJson j = new AjaxJson();
        try{
            /*++++++++++++++++++第1步：取得集合id  pid  name+++++++++++++++++*/
            String sql="select id ,pid,category_name categoryName from t_category ";
            List<Map<String, Object>> forJdbc = this.systemService.findForJdbc(sql);
            ArrayList<TCategoryEntity> tCategoryEntities = new ArrayList<>();
            for (int i = 0; i < forJdbc.size(); i++) {
                TCategoryEntity categoryEntity = new TCategoryEntity();
                categoryEntity.setId((Integer)forJdbc.get(i).get("id"));
                categoryEntity.setPid(forJdbc.get(i).get("pid").toString());
                categoryEntity.setCategoryName(forJdbc.get(i).get("categoryName").toString());
                tCategoryEntities.add(categoryEntity);
            }
            /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

            /*++++++++++++++++++第2步：将数据存储到map中+++++++++++++++++*/
            List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
            Map<String,Object> map = null;
            for (TCategoryEntity tCategoryEntity : tCategoryEntities) {
                String sqls = null;
                Object[] paramss = null;
                map = new HashMap<String,Object>();
                map.put("id", tCategoryEntity.getId());
                map.put("name", tCategoryEntity.getCategoryName());
                if (tCategoryEntity.getPid() != null) {
                    map.put("pId", tCategoryEntity.getPid());
                    map.put("open",false);
                }else {
                    map.put("pId", "0");
                    map.put("open",false);
                }
                sqls="select count(1) from t_category t where t.pid= "+tCategoryEntity.getId();
                Long counts= this.systemService.getCountForJdbc(sqls);
                if(counts>0){
                    dataList.add(map);
                }else{
                    TCategoryEntity de = this.systemService.get(TCategoryEntity.class, tCategoryEntity.getId());
                    if (de != null) {
                        map.put("id", de.getId());
                        map.put("name", de.getCategoryName());
                        if(tCategoryEntity.getPid()!=null){
                            map.put("pId", tCategoryEntity.getPid());
                            map.put("open",false);
                        }else{
                            map.put("pId", "0");
                            map.put("open",false);
                        }
                        dataList.add(map);
                    }else{
                        map.put("open",false);
                        dataList.add(map);
                    }
                }
            }
            /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
            j.setObj(dataList);
        }catch(Exception e){
            e.printStackTrace();
        }
        return j;
    }

    @RequestMapping(params = "paramObtain")
    @ResponseBody
    public String paramObtain(String locationId,String categoryName,String keywords,String keywords1,
                              String keywords2,String brand,String model,Integer day,Integer hour,
                              Integer minute,Integer second,String product) {
        Jedis jedis = JedisUtil.getJedis();
    /*++++++++++++++++++第1步：生成标题+++++++++++++++++*/
       /* String[] split = categoryName.split(",");
        List<String> categoryNameList = Arrays.asList(split);*/
        TLocationEntity tLocationEntity = this.titleService.get(TLocationEntity.class, Integer.parseInt(locationId));
        log.error(tLocationEntity.toString());
        if(keywords1!=null) {
            keywords = keywords + " " + keywords1;
        }
        if(keywords2!=null){
            keywords = keywords + " " + keywords2;
        }
        String userId = jedis.get("userId");
        String code = createTitle(categoryName, tLocationEntity.getLocationName(), keywords, Integer.parseInt(userId),brand,model,product);
        String title=null;
        if("success".equals(code)){
            String titles = jedis.hget("title", userId);
            List<String> lists = JsonUtil.toObject(titles, List.class);
            int i = (int) (Math.random() * lists.size());
            title = lists.get(i);
            log.error("标题为："+title);
        }
    /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

    /*++++++++++++++++++第2步：获取到图片+++++++++++++++++*/
        String img = jedis.hget("img", userId);
        log.error("图片为："+img);
    /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

    /*++++++++++++++++++第3步：生成文本+++++++++++++++++*/
        String ss=null;
        String sql1="select id,category_name categoryName,pid from t_category where category_name= '"+categoryName+"'";
        log.error(sql1);
        List<Map<String, Object>> forJdbc = this.systemService.findForJdbc(sql1);
        String id = forJdbc.get(0).get("id").toString();
        String sql="select id,content,category_id categoryId from t_content where category_id="+id;
        List<Map<String, Object>> forJdbc1 = this.systemService.findForJdbc(sql);
        for (int i1 = 0; i1 < forJdbc1.size(); i1++) {
            Object content = forJdbc1.get(i1).get("content");
            ss+=content.toString();
        }
        String content = content(ss);
        log.error("文本为："+content);
    /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

    /*++++++++++++++++++第4步：信息发布+++++++++++++++++*/
        String[] attrValue=null;
        String categoryId="3000101101";
        String cookieValue = jedis.get(userId);
        contentPush(attrValue,Integer.parseInt(userId),categoryId,keywords,content,cookieValue,brand,model);
    /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("status","success");
        jedis.close();
        return JsonUtil.toJson(objectObjectHashMap);
    }

    /**
     * 批量添加 图片
     * 如果图片已经被上传到web，从数据库中获取其web中图片路径
     *否则将图片上传到web中，获取其web中图片路径，并存储到数据库中
     *
     * @return
     */
    @RequestMapping(params = "doBatchDel")
    @ResponseBody
    public AjaxJson doBatchDel(String ids,HttpServletRequest request){
        String message = null;
        Jedis jedis = JedisUtil.getJedis();
        AjaxJson j = new AjaxJson();
        message = "图片上传成功";
        String path="H://jeecg/wdwhwn-master/wbsc-user/src/main/webapp/images/server/";
        String uploadUrl="http://my.qinfabu.com/Upload/Upload";
        String userId1 = jedis.get("userId");
        String cookieVal=jedis.get(userId1);
        log.error("cookieVal: "+cookieVal);
        Integer userId=null;
        List<String> imgList=new ArrayList<>();

        /*++++++++++++++++++第1步：判断图片是否已被上传到web中+++++++++++++++++*/
        try{
            for(String id:ids.split(",")){
                TImageEntity tImage = systemService.getEntity(TImageEntity.class,
                        Integer.parseInt(id)
                );
                String webImgName = tImage.getWebImgName();
                userId = tImage.getUserId();
                /*++++++++++++++++++第2步：如果没有上传，则上传到web中+++++++++++++++++*/
//                cookieVal="newwjlogin=CfDJ8CCUgNvfCiNBtDoTIkh_tCpMSB5zIL8mF1ggYe3sMzfYBREr6T951eHPS1phn1Zg3lTHcfF4sBaE624QA8QM7qEUgN-ZqrdxY_RauAGROM6n8FVvJRKwFw0qbdaft_s32VUjP5YGzqJamoH0z1cPgVg4Qt8ehqFIeRSr9vKTkiqDBNU-zYUEyRT3c_2gzO3l1BUY8nSxF0OSita5e7OfDkqN10PLfZKqh64WzYVJnu_mJfx7pVE4mBGf807UBKMyo1UB0sZ5qy2P3-XYbiaJztgMrlKkcTt9v8UQTmXp3czXCvkB449_HLWBtI9Sm7I7v8HFca9mQ331n3INOmoF1vl-PRA9kE2mk5anSFx2GsdzdT63HTkpv9pksdRo27JBkl0C4i6NCdrfr3fQXShH8aY7GLKg4bKWEQidZD9m8Cxap_SGmqFYSxEy2umPFMPILpbHUuTSZrUZV0misJ_CpovFC15lHmvKDf7mQMfZswo6GxDlKyz_gl9a9iLvpujJ08HFJ0kaJ3JrHahLXCKAQ-ccyqknpBMdcyCdLwmV7J5XGQ7EfqhYwxKnSqZiJnB-GBx5iaGoufqeOdt1OQFY3wpWViHZlQwaYQxkSNSjLdRAigkRY9PAa_0zBp2A7-hXE7YCCJq2G4H1AuTvM2alVqepaCQ-owGT7eqqG7G-gaB5zS5un4MEqn6yrczsUFKBFdCGaTfXygrVEq6OreDIiczAh5120G7iK6Wo_zMAUVXjY4SPqOX9YPlYpmWbKoC4eB2eV7VY42tLwZ1xwW9TkzRRa8sOEv1M8rSpeWC1gNzFN4wR8Fh5RXWK6tGTHyZ76Npuxll0iq7yYeQCJ4yy-Jhpkjoom8pPXZmKqV0nQvC_wCNgYfoGbtMco0G92Z4gl35PvLroG4A8jn88-5zhLEmOxkGiNXoXVn1So_Ra9nAAVH9fStJjNxpNfwHhUC9bAA; expires=Sat, 15 Jun 2019 22:39:48 GMT; domain=.qinfabu.com; path=/; samesite=lax; httponly";
                if(StringUtil.isEmpty(webImgName)){
                    String url = tImage.getUrl();
                    path=path+url;
                    String upload = HttpRequest.postFile(path, uploadUrl, null, cookieVal);
                    Msg msg = JsonUtil.toObject(upload, Msg.class);
                    /*++++++++++++++++++第3步：将web中图片路径  入库+++++++++++++++++*/
                    webImgName= msg.getMsg();
                    titleService.update(tImage.getId(),webImgName);
                    log.error(upload);
//                    HttpRequest.sendPost();
                }
                imgList.add(webImgName);
            }
                   /*++++++++++++++++++第4步：获取用户id+++++++++++++++++*/
            TUserEntity de = this.systemService.get(TUserEntity.class,userId );
            jedis.hset("img",userId+"",JsonUtil.toJson(imgList));

        }catch(Exception e){
            e.printStackTrace();
            message = "t_image删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        jedis.close();
        return j;
    }

    /**
     *  信息推送

     * @param AttrValue
     * @return
     */

    public String contentPush( String[] AttrValue,Integer userId,String typeid,String keyWords,String data,String cookieValue,String brand,String model) {
        Jedis jedis = JedisUtil.getJedis();
        /*++++++++++++++++++第1步：准备参数+++++++++++++++++*/
//        title 标题
        String titles = jedis.hget("title", userId + "");
        List<String> list = JsonUtil.toObject(titles, List.class);
        int i = (int) (Math.random() * list.size());
        String title = list.get(i);
//        pics 图片
        String picsList = jedis.hget("img", userId + "");
        List<String> picsList1 = JsonUtil.toObject(picsList, List.class);
        String pics=picsList1.get(0);
        if(picsList1.size()>1){
            for (int i1 = 0; i1 < picsList1.size(); i1++) {
                pics+="&pics="+picsList1.get(i1);
            }
        }
//        typeid 类型id
//      unit  单位
        String unit="件";
//      price 单价
        String price="";
//        keyWords
//        id
        String id="";
//      data  文章

        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
        String userId1 = jedis.get("userId");
        String cookieVal=jedis.get(userId1);
        /*++++++++++++++++++第1步：+++++++++++++++++*/
        String url="http://my.qinfabu.com/Product/Add";
        String param="title=TITLE"+
                "&keyWord=KEYWORD"+
                "&pics=PICS"+
                "&typeid=TYPEID"+
                "&AttrValue[0][name]='品牌/厂家'"+
                "&AttrValue[0][value]=ATTRVALUE[BRAND]"+
                "&AttrValue[0][typeid]='1'"+
                "&AttrValue[1][name]='型号'"
                +"&AttrValue[1][value]=ATTRVALUE[MODEL]"
                +"&AttrValue[1][typeid]='1'"
                +"&price="
                +"&unit=件"
                +"&id="
                +"&data=DATA";
//        title="北京餐饮培训米兰西典852饼干";
//        data="<p>石锅拌饭早源于韩国，因此有被大众称为韩国拌饭，石锅拌饭是韩国特有的米饭料理。它的发源地为韩国光州，曾经是朝鲜时代向中国进贡的菜肴，后来演变为韩国的代表性食物。石锅是陶做成的，厚重的黑色陶锅可直接拿到炉具烹煮，而且保温效果好，细嚼慢咽的人可安心享用，不用怕饭菜冷掉。石锅拌饭材料并不新奇特别，主要为米饭、肉类、鸡蛋，以及黄豆芽、蕈菇类和各式野菜;菜的种类并无一定，采用当季对味的季节蔬菜去调配即可。它的烹饪方式也不是很难，成为众多餐饮创业朋友的考虑项目.报价：石锅拌饭套餐学习费用：（详细咨询在线客服老师)(包括食宿材料费 、技术转让费、中餐费、学习材料费、资料费等，中途不收任何费用)学习内容：核心酱料的制作以及各种口味石锅拌饭配方比例操作流程过程到成品!学习安排：一对一教学、现场参观、随到随学、实践操作!</p><p><br/></p>";
//        pics="//image.qinfabu.com/img/2019/6/12/20190612145045863.png";
//        keyWords="餐饮";
        String replaceParam = param.replace("TITLE", title)
                .replace("KEYWORD", keyWords)
                .replace("PICS", pics)
                .replace("TYPEID", typeid + "")
                .replace("DATA",data)
                .replace("ATTRVALUE[BRAND]",brand)
                .replace("ATTRVALUE[MODEL]",model);
        replaceParam=replaceParam.replace("'","");
        log.error("参数为："+replaceParam);
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
//        String param="title=新乡室内精准测量仪离心机4689"+
//                "&keyWord=电子秤"+
//                "&pics=//image.qinfabu.com/img/2019/6/17/20190617204223852.png"+
//                "&typeid=3000101101"+
//                "&AttrValue[0][name]=品牌/厂家"+
//                "&AttrValue[0][value]=企宝推"+
//                "&AttrValue[0][typeid]=1"+
//                "&AttrValue[1][name]=型号"+
//                "&AttrValue[1][value]=4689"+
//                "&AttrValue[1][typeid]=1"+
//                "&price="+
//                "&unit=件"+
//                "&id="+
//                "&data=<p>卧式刮刀卸料离心机是连续运转、间歇操作的过滤式离心机，其控制方式为自动控制，也可手动控制。离心机操作过程中的进料、分离、洗涤、脱水、卸料及滤布再生等过程一般均在全速状态下完成，单次循环时间短，处理量大，并可获得较干的滤渣和良好的洗涤效果。GK 系列卧式刮刀卸料离心机广泛应用于化工、食品、轻工、制药、淀粉等行业，对含粗、中、细颗粒的悬浮液均适用，如硫铵、碳铵、聚氯乙烯、木署改性淀粉等。GKH 系列虹吸式卧式离心机在普通卧式刮刀卸料离心机的基础上，利用虹吸原理增加过滤推动力，透过过滤介质的滤液全部进入滤液室，滤液通过虹吸管 ( 撇液管 ) 排出转鼓，调节虹吸管吸入口位置可改变虹吸室内液面深度，以改变过滤推动力，调节过滤速度、处理能力、滤饼的含湿量以及洗涤效率。</p><p><br/></p>";
        String s = HttpRequest.sendPost1(url, replaceParam, cookieValue);
        jedis.close();
        return s;
    }

    @RequestMapping(params = "contentPush1")
    @ResponseBody
    public String contentPush1() {
//        Jedis jedis = JedisUtil.getJedis();
        /*++++++++++++++++++第1步：准备参数+++++++++++++++++*/
//        title 标题
//        String titles = jedis.hget("title", userId + "");
//        List<String> list = JsonUtil.toObject(titles, List.class);
//        int i = (int) (Math.random() * list.size());
//        String title = list.get(i);
//        pics 图片
//        String picsList = jedis.hget("img", userId + "");
//        List<String> picsList1 = JsonUtil.toObject(picsList, List.class);
//        String pics=picsList1.get(0);
//        if(picsList1.size()>1){
//            for (int i1 = 0; i1 < picsList1.size(); i1++) {
//                pics+="&pics="+picsList1.get(i1);
//            }
//        }
//        typeid 类型id
//      unit  单位
//        String unit="件";
//      price 单价
//        String price="";
//        keyWords
//        id
//        String id="";
//      data  文章

        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        Jedis jedis = JedisUtil.getJedis();
        String userId1 = jedis.get("userId");
        String cookieVal=jedis.get(userId1);
        /*++++++++++++++++++第1步：+++++++++++++++++*/
        String url="http://my.qinfabu.com/Product/Add";
//        String param="title=TITLE&keyWord=KEYWORD&pics=PICS&typeid=TYPEID&AttrValue[0][name]='品牌/厂家'&AttrValue[0][value]=ATTRVALUE[BRAND]&AttrValue[0][typeid]='1'&AttrValue[0][name]='型号'&AttrValue[0][value]=ATTRVALUE[MODEL]&AttrValue[0][typeid]='1'&price=PRICE&unit=UNIT&id=''&data=DATA";
//        String replaceParam = param.replace("TITLE", title).replace("KEYWORD", keyWords).replace("PICS", pics).replace("TYPEID", typeid + "").replace("PRICE",price).replace("UNIT",unit).replace("DATA",data).replace("ATTRVALUE[BRAND]",brand).replace("ATTRVALUE[MODEL]",model);
//        log.error("参数为："+replaceParam);
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
        String param1="title=北京餐饮培训西本85265饼干 面包 咖啡糕点培训"+
                "&keyWord=饼干 面包 咖啡"
                +"&pics=//image.qinfabu.com/img/2019/6/12/20190612145045863.png"
                +"&typeid=3003001"
                +"&AttrValue[0][name]='品牌/厂家'"
                +"&AttrValue[0][value]=西本"
                +"&AttrValue[0][typeid]='1'"
                +"&AttrValue[1][name]='型号'"
                +"&AttrValue[1][value]=85265"
                +"&AttrValue[1][typeid]='1'"
                +"&price="
                +"&unit=件"
                +"&id="
                +"&data=可是到时候牛百叶会变老用水浸泡会失去原有的特征。";
//        1. 去掉单引号
//
        String param="title=上海户外精准测量仪方向仪78956"+
                "&keyWord=搅拌机"+
                "&pics=//image.qinfabu.com/img/2019/6/17/20190617204223852.png"+
                "&typeid=3000101101"+
                "&AttrValue[0][name]=品牌/厂家"+
                "&AttrValue[0][value]=企宝推"+
                "&AttrValue[0][typeid]=1"+
                "&AttrValue[1][name]=型号"+
                "&AttrValue[1][value]=78956"+
                "&AttrValue[1][typeid]=1"+
                "&price="+
                "&unit=件"+
                "&id="+
                "&data=<p>陀螺仪是一种既古老而又很有生命力的仪器，从第一台真正实用的陀螺仪器问世以来已有大半个世纪，但直到现在，陀螺仪仍在吸引着人们对它进行研究，这是由于它本身具有的特性所决定的。陀螺仪最主要的基本特性是它的稳定性和进动性。人们从儿童玩的地陀螺中早就发现高速旋转的陀螺可以竖直不倒而保持与地面垂直，这就反映了陀螺的稳定性。研究陀螺仪运动特性的理论是绕定点运动刚体动力学的一个分支，它以物体的惯性为基础，研究旋转物体的动力学特性</p><p><br/></p>";

        //        String replaceParam="title='北京出国意大利4578出国工作 工薪 高薪薪资'&keyWord='出国工作'&pics='//image.qinfabu.com/img/2019/6/15/20190615144251118.png'&typeid='3003001'&AttrValue[0][name]='品牌/厂家'&AttrValue[0][value]=奥本&AttrValue[0][typeid]='1'&AttrValue[1][name]='型号'&AttrValue[1][value]=456789&AttrValue[1][typeid]='1'&price=&unit=件&id=''&data=让我们携手共创美好未来！！！联系人：吴经理 泰州海陵区凤凰东路68号建工大厦26层2602室江苏永腾人力专业从事劳务输出。本公司多年的经营经验积累了能够外派几十个国家及地区的外派能力同时需要具有翻译资质的翻译机构盖章。若需要签证翻译服务不允许私自打工。6、家属赴澳：工作满半年的劳工可申请有工作能力的家属赴澳及未满18周岁的子女免费赴澳大利亚就学。1、报名资料：有效护原件（没有可以后期提供）、结证、身证及户本复印件、白底两寸彩色照片8-10张、近3个月全身照4张、劳务申请表和报名须知各一份（由本公司提供）。2、后期材料：出生公证、无犯罪公证、体证明四、申请周期：3个月五、总费用：6.5万（含单程机票 六、付款方式在国内支付9000。管工作餐每周工作5天。加班需提出申请住宿自理.加班另算.要求 ：精通小笼包住宿自理.加班另算.要求 精通小笼包可能有加班江苏永腾人力资源有限公司专业从事劳务输出。本公司多年的经营经验积累了能够外派几十个国家及地区的外派能力用工方报销返程机票。不回国另外每年请假不超过7天补齐到5000元人民币无残疾每天工作8小时每天工作8小时签证文件对翻译要求非常严格可续签二、工种范围电子企业、缝纫纺织行业、驾驶司机类、厨师帮厨面点师服务员、星级酒店行业、电气焊、电工、细木工、车床铣床、机械加工维修、机械组装、电机机器组装每年多给1个月的薪水。休息期间可以自由活动可能有加班江苏永腾人力资源有限公司专业从事劳务输出。本公司多年的经营经验积累了能够外派几十个国家及地区的外派能力月薪1万-5万元不等。具体收入以招聘简章为准。七、出国总费用及交费流程：出国服务费：17000-35000元";
//        String cookie="newwjlogin=CfDJ8CCUgNvfCiNBtDoTIkh_tCo5YWWvu_QyF4Edbpy53RH6-bF8ty54PLgMh84QP46hhmkJ1L7f1NpizodIRQMCB64jPNVDYriCx4Ptxd1t59KYJSPNLKcYIjxEsgQEC8DS9OOqXlZreZX5z4ZHzENB1wfkXW2X9Nr1v6kbFJnMo0hqDwAN_wiLX5T5LqMSuDxwTDaYSZOZOLsp-VoqsTBsWhyk3eBhTMQAdv6fVufOMKP1EhalBzE0GTvdJFAlNnylsO5D8hM7PWAx2MiEyVmgSesXs2ISTjmPQrAyWzjptwOic58zSX1xtnaO4_hEejFlIUHX93n-DBhh41x99xpjocENjuH4P-a7C6HWkLDA9XccJ3UpUMw3HM3hSVwayPXqyhVwSNQsXykJBbAwK6ZEEjOUlYZqlCfc5bMzZC8gO3wcl8kivC4VT_BLaMG8ZNqEpyrNiDjtLvaZYQMwkfv4MKLVeRkSyD8YgkOPZ7tobGVhKzMDcam4dmTFJF9ExneBxyx0-t4AIPb08jsDfYXhB6dvCclNQNYodJixKKDIG0qiH40YmVHTScobFGpJgEd91k58nuobfTcHWtuqlg2tFonbkVbz4Gqv-g84ICL1TwYFCCRXCuWTstvGBAZaqD8Qk0_P8VsmLIvCVyWpgl2N-e5kiSJQjb5aqwx0CqWt1yfWKesPdgia7-KGi4g9YEk9y9ku4Y_iXOWYpFOEyqK06nu12LOH8OY-rEBjMIAa4kSKzUERebermy00NoWJfC4rTjDAJrWjPH2tCG75kdj-51jt6FUs_YWhIbdQMk8yHD3ums9JpRIwTzRmsjjWbVwLOA21aylMwh4_ZgtWtewWPm8G4wG-r43JqvVmOgHH5ttyD1PAAtjt-e77gcQ-j9BAwwO8XqtS_MaufhX6ZwEIyKIWTx5eFZzhUOWv6b_qfYI-LrEfvv1Uu-KtriU81RWiTA; expires=Tue, 18 Jun 2019 17:27:19 GMT; domain=.qinfabu.com; path=/; samesite=lax; httponly";
        String s = HttpRequest.sendPost1(url, param, cookieVal);
        jedis.close();
        return s;
    }


    /**
     * 随机生成文本
     * @param ss
     * @return
     */
    public String content(String ss){

        ss = ss.replace("\n", "");
        String[] split = ss.split("，");
        String content="";
//        第一段
        for (int i1 = 0; i1 < 3; i1++) {
            int i = (int) (Math.random() * split.length);
            content+=split[i];
        }
        char c = content.charAt(content.length()-1);
        if(c!='。'){
            content+="。\r\n";
        }else{
            content+="\r\n";
        }

//        第二段
        for (int i1 = 0; i1 < 10; i1++) {
            int i = (int) (Math.random() * split.length);
            content+=split[i];
        }
        if(c!='。'){
            content+="。\r\n";
        }else{
            content+="\r\n";
        }

//        第三段
        for (int i1 = 0; i1 < 5; i1++) {
            int i = (int) (Math.random() * split.length);
            content+=split[i];
        }
        if(c!='。'){
            content+="。\r\n";
        }else{
            content+="\r\n";
        }
        return content;
    }

    /**
     *  生成标题
     * @param
     * @param keyWords
     * @return
     */
    public String createTitle(String categoryName,String locationName,String keyWords,Integer userId,String brand,String model,String product) {

        /*++++++++++++++++++第1步：通过参数id，获取对应的参数名称+++++++++++++++++*/
        List<String> strings = Arrays.asList(keyWords);
        String[] stirngs = keyWords.split(" ");
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        /*++++++++++++++++++第2步：生成标题+++++++++++++++++*/
        List<String> titleList=new ArrayList<>();
        for (int i2 = 0; i2 < strings.size(); i2++) {
            titleList.add(locationName+categoryName+brand+model+strings.get(i2)+product);
        }
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/

        /*++++++++++++++++++第3步：标题列表存储到session中+++++++++++++++++*/
        Jedis jedis = JedisUtil.getJedis();

        jedis.hset("title",userId+"", JsonUtil.toJson(titleList));
        jedis.close();
        /*+++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++*/
        return "success";
    }
}
