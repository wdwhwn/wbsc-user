
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>uitags</title>
    <t:base type="jquery,easyui,tools,autocomplete,DatePicker"></t:base>
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
            $.each(data.rows,function(index,row){
                parsed.push({data:row,result:row,value:row.id});
            });
            return parsed;
        }
        /**
         * 选择后回调
         *
         * @param {Object} data
         */
        function callBack(data) {
            $("#user").val(data.userName);
        }

        /**
         * 每一个选择项显示的信息
         *
         * @param {Object} data
         */
        function formatItem(data) {
            return data.userName + "-->" + " " + data.realName;
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
<t:formvalid formid="formobj" dialog="false" layout="div" callback="test" action="tLoginController.do?login" beforeSubmit="setContentc">
    <fieldset class="step" style="padding-bottom: 20px;">
        <legend>生成标题</legend>
        <table>
            <tr>
                <td align="center" width="100px"><label class="Validform_label">请选择网站:</label></td>
                <td class="value">
                    <t:dictSelect field="webId" type="list" extendJson="{class:'form-control input-sm'}"  datatype="n"   dictTable="t_web" dictField="id" dictText="web_name"  hasLabel="false"  title="网站名称"></t:dictSelect>
                    <span class="Validform_checktip"></span>
                </td>
            </tr>
        </table>
    </fieldset>
    <fieldset>

        <table>
            <tr>
                <td align="center" width="100px"><label class="Validform_label">选择地区:</label></td>
                <td class="value"><t:comboTree url="jeecgFormDemoController.do?getComboTreeData" value="123" name="depid" id="depid" width="200"></t:comboTree>
                    <span class="Validform_checktip"></span></td>
            </tr>

            <tr>
                <td align="center" width="100px"><label class="Validform_label">选择类别:</label></td>
                <td class="value"><t:selectZTree id="citySel" url="jeecgFormDemoController.do?getTreeData" windowWidth="400px"></t:selectZTree> <span class="Validform_checktip"></span></td>
            </tr>
        </table>
        <div class="form">
            <label class="Validform_label"> 关键词1： </label>
            <input type="text" name="demotitle" id="demotitle" datatype="*" errormsg="该字段不为空">
            <span class="Validform_checktip"></span>
        </div>
        <div class="form">
            <label class="Validform_label"> 关键词2： </label>
            <input type="text" name="demotitle1" id="demotitle1" datatype="*" errormsg="该字段不为空">
            <span class="Validform_checktip"></span>
        </div>
        <div class="form">
            <label class="Validform_label"> 关键词3： </label>
            <input type="text" name="demotitle2" id="demotitle2" datatype="*" errormsg="该字段不为空">
            <span class="Validform_checktip"></span>
        </div>
        <div class="form">
            <label class="Validform_label"> 关键词4： </label>
            <input type="text" name="demotitle3" id="demotitle3" datatype="*" errormsg="该字段不为空">
            <span class="Validform_checktip"></span>
        </div>
        <div class="form">
            <label class="Validform_label"> 关键词5： </label>
            <input type="text" name="demotitle5" id="demotitle5" datatype="*" errormsg="该字段不为空">
            <span class="Validform_checktip"></span>
        </div>
    </fieldset>
    <div style="text-align:center"><input class="btn" type="submit" value="提交" style="height:30px;width:100px !important;border-radius:5px"></div>
</t:formvalid>

</body>
</html>
