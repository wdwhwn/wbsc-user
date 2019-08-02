
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
        function parse(data){
            var parsed = [];
            alert(1)
           /* $.each(data.rows,function(index,row){

                parsed.push({data:row,result:row,value:row.id});
            });
            return parsed;*/
        }
        /**
         * 选择后回调
         *
         * @param {Object} data
         */
        function callBack(data) {
            alert(2)
            alert(data)
//            $("#user").val(data.userName);
        }

        /**
         * 每一个选择项显示的信息
         *
         * @param {Object} data
         */
        function formatItem(data) {
            alert(3)

//            return data.userName + "-->" + " " + data.realName;
        }

        function setContentc(){
            alert("表单提交前想干点啥呢");
        }
        function test(){
            alert("表单提交后要干点啥呢");
        }

    </SCRIPT>
</head>
<body>
<%--<t:formvalid formid="formobj" dialog="false" layout="div" callback="callBack" action="tTitle.do?paramObtain" beforeSubmit="setContentc">--%>
<t:formvalid formid="formobj" dialog="false" layout="div" callback="callBack" action="tTitle.do?paramObtain" beforeSubmit="setContentc">
    <fieldset class="step" style="padding-bottom: 20px;">
        <legend>生成标题(网站：${sessionScope.web.webName} &nbsp; &nbsp; 用户名：${sessionScope.userWeb.username})</legend>
        <table>
            <tr>
            <td align="center" width="100px"><label class="Validform_label">选择地区:</label></td>
            <td class="value">
                <t:dictSelect field="locationId" type="list" extendJson="{class:'form-control input-sm'}"  datatype="n"   dictTable="t_location" dictField="id" dictText="location_name"  hasLabel="false"  title="父类地区"></t:dictSelect>
                    <%--<t:dictSelect field="webId" type="list" extendJson="{class:'form-control input-sm'}"  datatype="n"   dictTable="t_web" dictField="id" dictText="web_name"  hasLabel="false"  title="网站名称"></t:dictSelect>--%>
                <span class="Validform_checktip"></span>
            </td>
        </tr>
            <tr>
                <td align="center" width="100px"><label class="Validform_label">选择类别:</label></td>
                <td class="value">
                    <t:selectZTree id="categoryName" url="tTitle.do?getTreeData" windowWidth="400px"></t:selectZTree> <span class="Validform_checktip"></span></td>
            </tr>
        </table>
        <div class="form">
            <label class="Validform_label"> 关键词1： </label>
            <input type="text" name="keywords" id="keywords" datatype="*" errormsg="该字段不为空">
            <span class="Validform_checktip"></span>
        </div>
        <div class="form">
            <label class="Validform_label"> 关键词2： </label>
            <input type="text" name="keywords1" id="keywords1"  >
            <%--<span class="Validform_checktip"></span>--%>
        </div>
        <div class="form">
            <label class="Validform_label"> 关键词3： </label>
            <input type="text" name="keywords2" id="keywords2"  >
            <%--<span class="Validform_checktip"></span>--%>
        </div>
        <div class="form">
            <label class="Validform_label"> 品牌： </label>
            <input type="text" name="brand" id="brand" datatype="*" errormsg="该字段不为空">
            <span class="Validform_checktip"></span>
        </div>
        <div class="form">
            <label class="Validform_label"> 型号： </label>
            <input type="text" name="model" id="model" datatype="*" errormsg="该字段不为空">
            <span class="Validform_checktip"></span>
        </div>
            <div class="form">
                <label class="Validform_label"> 产品名称： </label>
                <input type="text" name="product" id="product" datatype="*" errormsg="该字段不为空">
                <span class="Validform_checktip"></span>
            </div>
        <div class="form">
            <label class="Validform_label"> 推送多少天： </label>
            <%--<input type="text" name="day" id="day" datatype="*" errormsg="该字段不为空">天--%>
            <t:dictSelect field="day" type="list" extendJson="{class:'form-control input-sm'}"   typeGroupCode="day"  hasLabel="false"  title="是否启用"></t:dictSelect><span>天</span>
            <span class="Validform_checktip"></span>
        </div>
        <div class="form">
            <label class="Validform_label"> 推送时间： </label>
            <t:dictSelect field="hour" type="list" extendJson="{class:'form-control input-sm'}"   typeGroupCode="hour"  hasLabel="false"  title="是否启用"></t:dictSelect><span>时<span>

            <%--<span class="Validform_checktip"></span>--%>
        </div>
        <div class="form">
            <label class="Validform_label"> 推送时间： </label>
            <t:dictSelect field="minute" type="list" extendJson="{class:'form-control input-sm'}"   typeGroupCode="minute"  hasLabel="false"  title="是否启用"></t:dictSelect>分
            <%--<span class="Validform_checktip"></span>--%>
        </div>
        <div class="form">
            <%--<label class="Validform_label"> 上传图片： </label>--%>
            <label class="Validform_label" onclick="alertId()"> 上传图片： </label>
        </div>
        </fieldset>
        </fieldset>

    <div style="text-align:center"><input class="btn" type="submit" value="提交" style="height:30px;width:100px !important;border-radius:5px"></div>
</t:formvalid>
</body>
<script>
function alertId(){
    self.parent.addOneTab('弹出窗','tImageController.do?list','icon-add')
}
</script>
</html>
