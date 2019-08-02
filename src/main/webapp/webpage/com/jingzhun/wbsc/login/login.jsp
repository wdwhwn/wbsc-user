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
            json=JSON.parse(data);
            alert(json.errmsg);
        }


        /**
         * 每一个选择项显示的信息
         * @param {Object} data
         */
        function formatItem(data) {
            alert("测试函数功能");
            return data.userName + "-->" + " " + data.realName;
        }

        function setContentc(){
//            alert("表单提交前想干点啥呢");
        }
        function test(data){
            alert(data.errmsg);
//            alert("表单提交后要干点啥呢");
        }

	</SCRIPT>
</head>
<body>

<t:formvalid formid="formobj" dialog="false" layout="div" callback="callBack" action="tLoginController.do?login" beforeSubmit="setContentc">
	<fieldset class="step" style="padding-bottom: 20px;">
		<legend>模拟登陆</legend>
		<table>
			<tr>
				<td align="center" width="100px"><label class="Validform_label">请选择网站:</label></td>
				<td class="value">
					<t:dictSelect field="webId" type="list" extendJson="{class:'form-control input-sm'}"  datatype="n"   dictTable="t_web" dictField="id" dictText="web_name"  hasLabel="false"  title="网站名称"></t:dictSelect>
					<span class="Validform_checktip"></span>
				</td>
			</tr>
		</table>
		<div class="form">
			<label class="Validform_label"> 用户名： </label>
			<input type="text" name="demotitle" id="username" datatype="*" errormsg="该字段不为空">
			<span class="Validform_checktip"></span>
		</div>
		<div class="form">
			<label class="Validform_label"> 密码： </label>
			<input type="text" name="demotitle1" id="password" datatype="*" errormsg="该字段不为空">
			<span class="Validform_checktip"></span>
		</div>
		<div style="text-align:center"><input class="btn" type="submit" value="提交" style="height:30px;width:100px !important;border-radius:5px"></div>
	</fieldset>
</t:formvalid>

<t:formvalid formid="formobj" dialog="false" layout="div" callback="test" action="tLoginController.do?loginTest" beforeSubmit="setContentc">
	<fieldset class="step" style="padding-bottom: 20px;">
		<legend>登录测试</legend>
		<table>
			<tr>
				<td align="center" width="100px"><label class="Validform_label">请选择网站:</label></td>
				<td class="value">
					<t:dictSelect field="webId" type="list" extendJson="{class:'form-control input-sm'}"  datatype="n"   dictTable="t_web" dictField="id" dictText="web_name"  hasLabel="false"  title="网站名称"></t:dictSelect>
					<span class="Validform_checktip"></span>
				</td>
			</tr>
		</table>
		<div style="text-align:center"><input class="btn" type="submit" value="提交" style="height:30px;width:100px !important;border-radius:5px"></div>
	</fieldset>
</t:formvalid>

</body>
</html>
