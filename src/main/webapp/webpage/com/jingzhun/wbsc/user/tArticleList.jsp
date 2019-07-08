<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="tArticleList" checkbox="true" pagination="true" fitColumns="true"  actionUrl="tArticleController.do?datagrid" idField="id" sortName="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="标题"  field="title"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="关键词"  field="keywords"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="参数"  field="parameter"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="文章"  field="article"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="生成时间"  field="generationTime"  formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="用户名称"  field="userId"  queryMode="group"  dictionary="t_user,id,username"  width="120"></t:dgCol>
   <t:dgCol title="任务开始时间"  field="taskBegin"  formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="任务持续天数"  field="taskDay"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="任务终止时间"  field="taskEnd"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="网站名称"  field="webId"  queryMode="group"  dictionary="t_web,id,web_name"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tArticleController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="tArticleController.do?goAdd" funname="add"  width="768"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tArticleController.do?goUpdate" funname="update"  width="768"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="tArticleController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tArticleController.do?goUpdate" funname="detail"  width="768"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'tArticleController.do?upload', "tArticleList");
}

//导出
function ExportXls() {
	JeecgExcelExport("tArticleController.do?exportXls","tArticleList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("tArticleController.do?exportXlsByT","tArticleList");
}

//bootstrap列表图片格式化
function btListImgFormatter(value,row,index){
	return listFileImgFormat(value,"image");
}
//bootstrap列表文件格式化
function btListFileFormatter(value,row,index){
	return listFileImgFormat(value);
}

</script>
