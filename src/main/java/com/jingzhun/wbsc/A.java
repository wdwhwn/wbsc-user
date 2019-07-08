package com.jingzhun.wbsc;

/**
 * Created by Administrator on 2019/6/11 0011.
 */
public class A {
    public static void main(String[] args) {
        String ss="String param1=\"title=北京餐饮培训西本85265饼干 面包 咖啡糕点培训\"+\n" +
                "                \"&keyWord=饼干 面包 咖啡\"\n" +
                "                +\"&pics=//image.qinfabu.com/img/2019/6/12/20190612145045863.png\"\n" +
                "                +\"&typeid=3003001\"\n" +
                "                +\"&AttrValue[0][name]='品牌/厂家'\"\n" +
                "                +\"&AttrValue[0][value]=西本\"\n" +
                "                +\"&AttrValue[0][typeid]='1'\"\n" +
                "                +\"&AttrValue[1][name]='型号'\"\n" +
                "                +\"&AttrValue[1][value]=85265\"\n" +
                "                +\"&AttrValue[1][typeid]='1'\"\n" +
                "                +\"&price=\"\n" +
                "                +\"&unit=件\"\n" +
                "                +\"&id=\"\n" +
                "                +\"&data=可是到时候牛百叶会变老用水浸泡会失去原有的特征。\";";

        String str=ss.replace("'","");
        System.out.println("BBBBBBBB");
        System.out.println(str+"AAAAAAA");
    }
}
