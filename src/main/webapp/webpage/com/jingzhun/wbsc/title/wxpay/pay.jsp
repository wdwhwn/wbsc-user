
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>uitags</title>
    <t:base type="jquery,easyui,tools,,DatePicker"></t:base>
    <style>
        <!--
        .ac_over {
            background: #E0ECFF;
            cursor: pointer;
            color: #416AA3;
        }
        .ac_results {
            border: 1px solid rgb(172, 216, 236);
        }
        -->

    </style>
    <SCRIPT type="text/javascript">
        var num;
        function test(){
             num=$("#money").val()
            alert("num:"+num)
        }
//        console.log(num)
       function textA () {
            console.log(0)
           var str=$('input:radio[name="payStyle"]:checked').val()
           if("wx"==str) {
               $.ajax({
                   type: "POST",
                   url: "wxpay.do?binary",
                   dataType: "JSON",
                   data: {'money': num},
               })
           }else if("ali"==str){
               $.ajax({
                   type: "POST",
                   url: "wxpay.do?aliPay",
//                url: "/rest/wxpay.do?binary",
                   dataType: "JSON",
                   data: {'money': num},
                   success: function (jsondata) {
                        div=document.createElement('div');
                       div.innerHTML=jsondata; // data就是接口返回的form 表单字符串
                       document.body.appendChild(div);
                       document.forms[0].submit();
                   }
               })
           }else{
               alert("请选择支付方式");
           }
        }
    </SCRIPT>
</head>
<body>
<%--<t:formvalid formid="formobj" dialog="false" layout="div" callback="callBack" action="tTitle.do?paramObtain" beforeSubmit="setContentc">--%>
    <fieldset class="step" style="padding-bottom: 20px;">
        <legend>购买积分(网站：${sessionScope.web.webName} &nbsp; &nbsp; 用户名：${sessionScope.userWeb.username})</legend>
        <%--<label><input name="payStyle" id="wx" type="radio" value="wx" />微信 </label>--%>
        <input name="payStyle" id="wx" type="radio" value="wx" />微信
        <%--<label><input name="payStyle" id="ali" type="radio" value="ali" />支付宝 </label>--%>
        <input name="payStyle" id="ali" type="radio" value="ali" />支付宝
        <div class="form">
            <label class="Validform_label"> 支付金额： </label>
            <input type="text" name="money" id="money" onblur="test()" datatype="*" errormsg="该字段不为空">
            <span class="Validform_checktip"></span>
        </div>
        </fieldset>
        </fieldset>
    <%--<a href="wxpay.do?binary" id="test">测试</a>--%>
<input class="btn" onclick="textA()" type="button" value="提交" style="height:30px;width:100px !important;border-radius:5px">
</body>
<script>
function alertId(){
    self.parent.addOneTab('弹出窗','tImageController.do?list','icon-add')
}
</script>
</html>
