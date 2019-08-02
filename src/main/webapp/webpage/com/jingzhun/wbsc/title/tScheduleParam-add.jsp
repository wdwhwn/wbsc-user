<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>t_schedule_param</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form"></t:base>
</head>
 <body style="overflow:hidden;overflow-y:auto;">
 <div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
	<form class="form-horizontal" role="form" id="formobj" action="tScheduleParamController.do?doAdd" method="POST">
		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<input type="hidden" id="id" name="id"/>
		<div class="form-group">
			<label for="userKey" class="col-sm-3 control-label">用户名称：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="userKey" name="userKey" type="text" maxlength="10" class="form-control input-sm" placeholder="请输入用户名称"  datatype="n"  ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="picsid" class="col-sm-3 control-label">图片id：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="picsid" name="picsid" type="text" maxlength="255" class="form-control input-sm" placeholder="请输入图片id"  ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="second" class="col-sm-3 control-label">秒数：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="second" name="second" type="text" maxlength="10" class="form-control input-sm" placeholder="请输入秒数"  datatype="n"  ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="minute" class="col-sm-3 control-label">分数：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="minute" name="minute" type="text" maxlength="10" class="form-control input-sm" placeholder="请输入分数"  datatype="n"  ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="hour" class="col-sm-3 control-label">小时：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="hour" name="hour" type="text" maxlength="10" class="form-control input-sm" placeholder="请输入小时"  datatype="n"  ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="daybegin" class="col-sm-3 control-label">开始天数：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="daybegin" name="daybegin" type="text" maxlength="10" class="form-control input-sm" placeholder="请输入开始天数"  datatype="n"  ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="dayend" class="col-sm-3 control-label">结束天数：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="dayend" name="dayend" type="text" maxlength="10" class="form-control input-sm" placeholder="请输入结束天数"  datatype="n"  ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="month" class="col-sm-3 control-label">月份：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="month" name="month" type="text" maxlength="10" class="form-control input-sm" placeholder="请输入月份"  datatype="n"  ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="year" class="col-sm-3 control-label">年份：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="year" name="year" type="text" maxlength="10" class="form-control input-sm" placeholder="请输入年份"  datatype="n"  ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="type" class="col-sm-3 control-label">type：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="type" name="type" type="text" maxlength="10" class="form-control input-sm" placeholder="请输入type"  datatype="n"  ignore="ignore" />
				</div>
			</div>
		</div>
					<div class="form-group">
						<label for="params" class="col-sm-3 control-label">参数：</label>
						<div class="col-sm-7">
				    <div class="input-group" style="width:100%">
						  	 	<textarea name="params" value = "${tScheduleParam.params}" class="form-control input-sm" rows="6"  ignore="ignore" ></textarea>
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">参数</label>
			          </div>
						</div>
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