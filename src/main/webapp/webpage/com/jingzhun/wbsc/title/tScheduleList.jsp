<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="tScheduleList" checkbox="true" pagination="true" fitColumns="true"  actionUrl="tScheduleController.do?datagrid" idField="id" sortName="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="用户"  field="userKey" hidden="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="网站名称"  field="webId"  queryMode="group"  dictionary="t_web,id,web_name"  width="120"></t:dgCol>

   <t:dgCol title="用户账号"  field="webUsername"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="param"  field="param" hidden="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="任务调度标识"  field="scheduleJobname" hidden="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="任务调度表达式"  field="scheduleCron"  queryMode="group"  width="120"></t:dgCol>
      <t:dgCol title="创建时间"  field="CreateDate"  queryMode="group"    width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="暂停" url="tScheduleController.do?pause&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgDelOpt title="重启" url="tScheduleController.do?restart&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'tScheduleController.do?upload', "tScheduleList");
}

//导出
function ExportXls() {
	JeecgExcelExport("tScheduleController.do?exportXls","tScheduleList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("tScheduleController.do?exportXlsByT","tScheduleList");
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
