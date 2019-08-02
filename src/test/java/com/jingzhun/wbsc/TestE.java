package com.jingzhun.wbsc;

import com.jingzhun.wbsc.util.JsonUtil;
import com.jingzhun.wbsc.web.entity.TWebEntity;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.oracore.TDSPatch;
import org.jeecgframework.core.util.MD5Util;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/7/25 0025.
 */
@Slf4j
public class TestE {

    /**
     *
     **/
    @Test
    public void testA() throws IOException {
        String originalPath="E:\\jeecg\\wdwhwn-master\\wbsc-user\\src\\main\\resources\\headfile\\";
        String filePath0="zhizaojiaoyi";
        File path = new File(originalPath + filePath0);
        if(!path.exists()){
            path.mkdirs();
        }
        File file1=new File( originalPath+filePath0,
                "zhizaojiaoyi_resultlist.properties");
        if (!file1.exists()) {
            file1.createNewFile();
        }
        String absolutePath = file1.getAbsolutePath();
        log.error("路径：" + absolutePath);
        FileOutputStream out = new FileOutputStream(absolutePath);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(out);
        PrintWriter pw = new PrintWriter(outputStreamWriter);

        String resourceLocation = "classpath:headfile/test.test";
        File file = ResourceUtils.getFile(resourceLocation);
        String filePath = file.getAbsolutePath();
        InputStream in = null;
        in = new FileInputStream(filePath);

        Reader reader = new InputStreamReader(in, "UTF-8");
        BufferedReader br = new BufferedReader(reader);
        while(true){
            String line=br.readLine();

            if(line==null){
                break;
            }
            int i = line.indexOf(":");
            String str="";
            if(i!=-1) {
                str=line.substring(0, i) + "=" + line.substring(i + 1);
                System.out.println(str);
            }
            pw.println(str);
        }
        pw.close();
        br.close();

    }
    /**
    *
    **/
    @Test
    public void test(){
        HashMap<String, String> stringStringHashMap = new HashMap<>();
//       动态
        stringStringHashMap.put("md5", "MD5");
        stringStringHashMap.put("btnId","");
        stringStringHashMap.put("isW","1");
        stringStringHashMap.put("id","WU_FILE_0");
        stringStringHashMap.put("name","2019.jpg");
        stringStringHashMap.put("type","image/jpeg");
        stringStringHashMap.put("lastModifiedDate","Mon Jul 29 2019 14:04:12 GMT+0800");
        stringStringHashMap.put("size","24070");
        String s = JsonUtil.toJson(stringStringHashMap);
        System.out.println(s);
    }
    /**
    *
    *
    **/
    @Test
    public void test1() throws UnsupportedEncodingException {
        String str="protitle=PROTITLE" +
//                 推广标题
                "&title=TITLE" +
//                 关键词1
                "&key=KEY1" +
//                 关键词2
                "&key=KEY2" +
//                 关键词3
                "&key=KEY3" +
//              产品属性
                "&pAtt=0001002800020001" +
//              品牌
                "&pAtta0=BRAND" +
                "&musth=0|input" +
                "&pAtta1=" +
                "&pAtta2=" +
                "&pAtta3=" +
                "&pAtta4=" +
                "&pAtta5=52" +
                "&musth=5|input" +
                "&pAtta6=高粱" +
                "&musth=6|input" +
                "&pAtta7=" +
                "&pAtta8=请选择" +
                "&pAttb8=" +
                "&pAtta9=" +
                "&pAtta10=请选择" +
                "&pAttb10=" +
                "&pAtta11=请选择" +
                "&pAttb11=" +
                "&pAtta12=请选择" +
                "&pAttb12=" +
                "&pAtta13=请选择" +
                "&pAttb13=" +
                "&tCon=0001002800020001" +
//                        单位
                "&tCona0=TCONA0" +
                "&tConb0=" +
//                        单价
                "&tCona1=TCONA1" +
//                        起订量
                "&tCona2=TCONA2" +
//                        供货总量
                "&tCona3=TCONA3" +
//                        3天内发货
                "&tCona4=TCONA4" +
                "&tConb4=" +
//                        有效期
                "&ValidityDate=VALIDITYDATE" +
//                        图片
                "&hImg1=IMG" +
                "&hImg2=" +
                "&hImg3=" +
                "&editor=CONTENT"+
                "&editImg=" +
//              类别
                "&ddlCategory=CATEGORY" +
//                        1_推荐  2_特别推荐
                "&tuijia=1" +
                "&hTitle=" +
                "&id=" +
                "&Content=" +
                "&tradeType=0|1|0|0" +
//                        1级类别
                "&topCategoryId=00010028" +
//                        二级类别
                "&secondCategoryId=000100280002" +
//                        3级类别
                "&thirdCategoryId=0001002800020001" +
                "&autoName=" +
                "&autoValue=" +
                "&categoryName=" +
                "&CatagoryId=" +
                "&5LOOR08AFA5LOOR08AFASXG=5LOOR08AFASXG" +
                "&5LOOR08AFASXG5LOOR08AFA=5LOOR08AFASXG" +
                "&isVouch=0" +
                "&X9831261=625178" +
                "&X-Requested-With=XMLHttpRequest";
        String ss=str;
        String[] split = ss.split("&");
        String s0="StringBuilder stringBuilder=new StringBuilder();\nstringBuilder\n";
        for (int i = 0; i < split.length; i++) {
            if(i!=split.length-1) {
                s0 += ".append(\"" + split[i] + "\")\n";
            }else{
                s0 += ".append(\"" + split[i] + "\");";
            }

        }
        s0=s0+"\n String param=stringBuilder.toString();";
        System.out.println(s0);
    }

    /**
    *
    *
    **/
    @Test
    public void test2() throws SQLException {
        List<TWebEntity> array = new ArrayList<TWebEntity>();

        for (int i = 0; i < 3; i++) {
            TWebEntity tWebEntity = new TWebEntity();
            tWebEntity.setId(i);
            array.add(tWebEntity);
        }
        System.out.println(array);

    }
}
