<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>t_image</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="bootstrap,bootstrap-table,layer,validform,webuploader,bootstrap-form"></t:base>
</head>
 <body style="overflow:hidden;overflow-y:auto;">
 <div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
	<form class="form-horizontal" role="form" id="formobj" action="tImageController.do?doAdd" method="POST">
		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<input type="hidden" id="id" name="id"/>
		<%--<div class="form-group">
			<label for="userKey" class="col-sm-3 control-label">用户Key：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="userKey" name="userKey" type="text" maxlength="255" class="form-control input-sm" placeholder="请输入用户Key"  ignore="ignore" />
				</div>
			</div>
		</div>--%>
		<div class="form-group">
			<label for="url" class="col-sm-3 control-label">图片：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
				<t:webUploader name="url" outJs="true" auto="true" showImgDiv="filediv_url" type="image" buttonText='添加图片' displayTxt="false"></t:webUploader>
				<div class="form" id="filediv_url"></div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="webId" class="col-sm-3 control-label">网站：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<t:dictSelect field="webId" type="list" extendJson="{class:'form-control input-sm'}"  datatype="n"   dictTable="t_web" dictField="id" dictText="web_name"  hasLabel="false"  title="网站" defaultVal="${sessionScope.web.id}"></t:dictSelect>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="webUsername" class="col-sm-3 control-label">网站用户名：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="webUsername" name="webUsername" value='${sessionScope.userWeb.username}' type="text" maxlength="255" class="form-control input-sm" placeholder="请输入用户名称"  ignore="ignore" />
				</div>
			</div>
		</div>
		<%--<div class="form-group">
			<label for="webImgName" class="col-sm-3 control-label">网站图片路径：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="webImgName" name="webImgName" type="text" maxlength="255" class="form-control input-sm" placeholder="请输入网站图片路径"  ignore="ignore" />
				</div>
			</div>
		</div>--%>
	</form>
	</div>
 </div>
<script type="text/javascript">
var subDlgIndex = '';
$(document).ready(function() {
	//单选框/多选框初始化
	$('.i-checks').iCheck({
		labelHover : false,
		cursor : true,
		checkboxClass : 'icheckbox_square-green',
		radioClass : 'iradio_square-green',
		increaseArea : '20%'
	});
	
	//表单提交
	$("#formobj").Validform({
		tiptype:function(msg,o,cssctl){
			if(o.type==3){
				validationMessage(o.obj,msg);
			}else{
				removeMessage(o.obj);
			}
		},
		btnSubmit : "#btn_sub",
		btnReset : "#btn_reset",
		ajaxPost : true,
		beforeSubmit : function(curform) {
		},
		usePlugin : {
			passwordstrength : {
				minLen : 6,
				maxLen : 18,
				trigger : function(obj, error) {
					if (error) {
						obj.parent().next().find(".Validform_checktip").show();
						obj.find(".passwordStrength").hide();
					} else {
						$(".passwordStrength").show();
						obj.parent().next().find(".Validform_checktip").hide();
					}
				}
			}
		},
		callback : function(data) {
			var win = frameElement.api.opener;
			if (data.success == true) {
				frameElement.api.close();
			    win.reloadTable();
			    win.tip(data.msg);
			} else {
			    if (data.responseText == '' || data.responseText == undefined) {
			        $.messager.alert('错误', data.msg);
			        $.Hidemsg();
			    } else {
			        try {
			            var emsg = data.responseText.substring(data.responseText.indexOf('错误描述'), data.responseText.indexOf('错误信息'));
			            $.messager.alert('错误', emsg);
			            $.Hidemsg();
			        } catch (ex) {
			            $.messager.alert('错误', data.responseText + "");
			            $.Hidemsg();
			        }
			    }
			    return false;
			}
		}
	});
		
});
</script>
</body>
</html>