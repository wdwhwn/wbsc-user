<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">
        <t:datagrid name="tArticleList" title="网站：${sessionScope.web.webName} 用户名：${sessionScope.userWeb.username}" checkbox="true" pageSize="15" pagination="true" fitColumns="true"  actionUrl="messagePushResultController.do?datagrid" idField="id" sortName="id" fit="true" queryMode="group">
            <t:dgCol title="id"  field="id"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
            <t:dgCol title="标题"  field="title"  queryMode="group"   width="120"></t:dgCol>
            <t:dgCol title="url"  field="url"    width="120"></t:dgCol>
            <t:dgCol title="审核通过时间"  field="time"  queryMode="group"  width="120"></t:dgCol>
        </t:datagrid>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function(){
    });
</script>
