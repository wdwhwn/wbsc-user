<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="tScheduleParamList" checkbox="true" pagination="true" fitColumns="true" title="t_schedule_param" actionUrl="tScheduleParamController.do?datagrid" idField="id" sortName="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="用户"  field="userKey"  queryMode="group"    width="120"></t:dgCol>
   <t:dgCol title="参数"  field="params"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="图片id"  field="picsid"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="秒数"  field="second"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="分数"  field="minute"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="小时"  field="hour"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="开始天数"  field="daybegin"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="结束天数"  field="dayend"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="月份"  field="month"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="年份"  field="year"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="type"  field="type"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tScheduleParamController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="tScheduleParamController.do?goAdd" funname="add"  width="768"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tScheduleParamController.do?goUpdate" funname="update"  width="768"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="tScheduleParamController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tScheduleParamController.do?goUpdate" funname="detail"  width="768"></t:dgToolBar>
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
	openuploadwin('Excel导入', 'tScheduleParamController.do?upload', "tScheduleParamList");
}

//导出
function ExportXls() {
	JeecgExcelExport("tScheduleParamController.do?exportXls","tScheduleParamList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("tScheduleParamController.do?exportXlsByT","tScheduleParamList");
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
