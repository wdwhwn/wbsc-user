<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>t_article</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form"></t:base>
</head>
 <body style="overflow:hidden;overflow-y:auto;">
 <div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
	<form class="form-horizontal" role="form" id="formobj" action="tArticleController.do?doUpdate" method="POST">
		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<input type="hidden" id="id" name="id" value="${tArticle.id}"/>
	<div class="form-group">
		<label for="title" class="col-sm-3 control-label">标题：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="title" name="title" value='${tArticle.title}' type="text" maxlength="50" class="form-control input-sm" placeholder="请输入标题"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="keywords" class="col-sm-3 control-label">关键词：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="keywords" name="keywords" value='${tArticle.keywords}' type="text" maxlength="80" class="form-control input-sm" placeholder="请输入关键词"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="parameter" class="col-sm-3 control-label">参数：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="parameter" name="parameter" value='${tArticle.parameter}' type="text" maxlength="255" class="form-control input-sm" placeholder="请输入参数"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="generationTime" class="col-sm-3 control-label">生成时间：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
      		    <input id="generationTime" name="generationTime" type="text" class="form-control input-sm laydate-date" placeholder="请输入生成时间"  ignore="ignore"  value="<fmt:formatDate pattern='yyyy-MM-dd' type='date' value='${tArticle.generationTime}'/>" />
                <span class="input-group-addon" ><span class="glyphicon glyphicon-calendar"></span></span>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="userId" class="col-sm-3 control-label">用户名称：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
               <t:dictSelect field="userId" type="list" extendJson="{class:'form-control input-sm'}"  datatype="n"   dictTable="t_user" dictField="id" dictText="username"  hasLabel="false"  title="用户名称" defaultVal="${tArticle.userId}"></t:dictSelect>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="taskBegin" class="col-sm-3 control-label">任务开始时间：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
      		    <input id="taskBegin" name="taskBegin" type="text" class="form-control input-sm laydate-date" placeholder="请输入任务开始时间"  ignore="ignore"  value="<fmt:formatDate pattern='yyyy-MM-dd' type='date' value='${tArticle.taskBegin}'/>" />
                <span class="input-group-addon" ><span class="glyphicon glyphicon-calendar"></span></span>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="taskDay" class="col-sm-3 control-label">任务持续天数：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="taskDay" name="taskDay" value='${tArticle.taskDay}' type="text" maxlength="10" class="form-control input-sm" placeholder="请输入任务持续天数"  datatype="n"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="taskEnd" class="col-sm-3 control-label">任务终止时间：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="taskEnd" name="taskEnd" value='${tArticle.taskEnd}' type="text" maxlength="255" class="form-control input-sm" placeholder="请输入任务终止时间"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="webId" class="col-sm-3 control-label">网站名称：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
               <t:dictSelect field="webId" type="list" extendJson="{class:'form-control input-sm'}"  datatype="n"   dictTable="t_web" dictField="id" dictText="web_name"  hasLabel="false"  title="网站名称" defaultVal="${tArticle.webId}"></t:dictSelect>
			</div>
		</div>
	</div>
					<div class="form-group">
					<label for="article" class="col-sm-3 control-label">文章：</label>
					<div class="col-sm-7">
					<div class="input-group" style="width:100%">
						  	 	<textarea name="article" class="form-control input-sm" rows="6"  ignore="ignore" >${tArticle.article}</textarea>
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">文章</label>
			          </div>
						</div>
	</form>
	</div>
 </div>
<script type="text/javascript">
var subDlgIndex = '';
$(document).ready(function() {
	$(".laydate-datetime").each(function(){
		var _this = this;
		laydate.render({
		  elem: _this,
		  format: 'yyyy-MM-dd HH:mm:ss',
		  type: 'datetime',
		  ready: function(date){
		  	 $(_this).val(DateJsonFormat(date,this.format));
		  }
		});
	});
	$(".laydate-date").each(function(){
		var _this = this;
		laydate.render({
		  elem: _this,
		  format: 'yyyy-MM-dd',
		  ready: function(date){
		  	 $(_this).val(DateJsonFormat(date,this.format));
		  }
		});
	});
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