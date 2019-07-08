<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--360浏览器优先以webkit内核解析-->
    <title>Jeecg 微云快速开发平台</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>


    <link rel="shortcut icon" href="images/favicon.ico">
    <link href="plug-in/hplus/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="plug-in/hplus/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="plug-in/hplus/css/animate.css" rel="stylesheet">
    <link href="plug-in/hplus/css/style.css?v=4.1.0" rel="stylesheet">
    <link rel="stylesheet" href="plug-in/themes/fineui/main/iconfont.css">
	<script src="plug-in/laydate/laydate.js"></script> 
    <style type="text/css">
	.gray-bg{
		background-color: #e9ecf3;
	}
	.col-sm-2 {
	    width: 10%;
		padding-left: 5px;
		padding-right: 5px;
		float: left;
	}
    /*调节高度  height: 200px;*/
	.p-lg{
		padding:0px 0px 10px 0px;
        height: 200px;
	}
	.widget{
		margin-top: 0px;
	}
	.iconfont{
		font-size: 30px;
		color: white;
	}
	h2 {
        font-size: 19px;
    }
    .echart_div{
    	height:240px;width:100%;
    }
	.ibtn{
		cursor: pointer;
	}
	.flot-chart{
		height:400px;
	}
   /*  .top-navigation .wrapper.wrapper-content{padding:20px 5px !important;}
	.container {
    	 width:99% !important; margin:10px;
    	 padding-right: 1px !important;
    	 padding-left: 1px !important;
	}
	.color_red{color:#e55555;}
	.col-cs-2 {
	    width: 10%;
		padding-left: 5px;
		padding-right: 5px;
		float: left;
	}*/
	/*第二个  调节宽度 width: 25%;*/
	@media (min-width: 992px){
		.col-cs-2 {
		    /*width: 11.11%;*/
		    width: 25%;
			padding-left: 5px;
			padding-right: 5px;
			float: left;
		}
	}
    </style>

</head>
 <body class="gray-bg">
        <div class="wrapper wrapper-content">
            <div class="row">
                <div class="col-md-1 col-cs-2 col-xs-4">
					<div class="widget  p-lg text-center" style="background: #cfa972;">
						<div><!-- class="ibtn" -->
                            <%--<i class="iconfont icon-zhihuizhongxin" style="font-size: 100px;"></i>--%>
                            <i class="iconfont icon-zhihuizhongxin" style="font-size: 80px;"></i>
                            <h3 class="font-bold no-margins">积分商城总用户数</h3>
                            <br/>
                            <h3 class="font-bold no-margins" id="totalUser">200</h3>
                            <%--<div>今日订单总数</div>
                            <small>200</small>--%>
                        </div>
                    </div>
                </div>
                <div class="col-md-1 col-cs-2 col-xs-4">
					<div class="widget  p-lg text-center" style="background: #f29b76;">
						<div>
                            <%--<i class="iconfont icon-yujing" style="font-size: 30px;"></i>--%>
                            <i class="iconfont icon-yujing" style="font-size: 80px;"></i>
                            <h3 class="font-bold no-margins">近一个月新增用户数</h3>
                                <br/>
                            <h3 class="font-bold no-margins" id="resentUser">20</h3>
                            <%--<div>今日销售总额</div>
                            <small>功能2</small>--%>
                        </div>
                    </div>
                </div>
                <div class="col-md-1 col-cs-2 col-xs-4">
					<div class="widget  p-lg text-center" style="background: #acd598;">
						<div>
                            <%--<i class="iconfont icon-ln-" style="font-size: 30px;"></i>--%>
                            <i class="iconfont icon-ln-" style="font-size: 80px;"></i>
                            <h3 class="font-bold no-margins">近一个月售出洗碗机数量</h3>
                                <br/>
                            <h3 class="font-bold no-margins" id="resentDevice">500</h3>
                            <%--<div>昨日销售总额</div>
                            <small>功能3</small>--%>
                        </div>
                    </div>
                </div>
                <div class="col-md-1 col-cs-2 col-xs-4">
					<div class="widget  p-lg text-center" style="background: #84ccc9;">
						<div>
                            <i class="iconfont icon-wuliu" style="font-size: 30px;"></i>
                            <i class="iconfont icon-wuliu" style="font-size: 80px;"></i>
                            <h3 class="font-bold no-margins">总共售出的洗碗机数量</h3>
                            <br/>
                            <h3 class="font-bold no-margins" id="totalDevice">5000</h3>
                            <%--<div>近7天销售总额</div>
                            <small>￥5000.00</small>--%>
                        </div>
                    </div>
                </div>
            </div>
</div>
            <div class="wrapper wrapper-content">
               </div>
</div>
        <table class="table table-bordered">
            <%--<caption>洗碗机分期付款表格</caption>--%>
            <h1 align="center">洗碗机分期付款表格</h1>
            <thead>
            <tr>
                <th>洗碗机id</th>
                <th>洗碗机类型</th>
                <th>洗碗机编号</th>
                <th>洗碗机最近一次支付时间</th>
                <th>洗碗机本月支付时间</th>
                <th>洗碗机结束支付时间</th>
                <th>洗碗机安装地址</th>
                <th>购买洗碗机店铺</th>
                <th>销售员</th>
                <th>用户手机号</th>
                <th>用户姓名</th>
                <th>查看明细</th>>
            </tr>
            </thead>
            <tbody id="tbody">
            </tbody>
        </table>
        <ul class="pager">
            <li><span style="display: none">1</span><a href="#" onclick="prev1(this)" id="firstPage">首页</a></li>
            <li><span style="display: none"></span><a href="#" onclick="prev1(this)" id="prev">上一页</a></li>
            <li><a href="#"  >每页<span id="pageSize">5</span>条</a></li>
            <li><a href="#"  >第<span id="page"></span>页</a></li>
            <li><span style="display: none"></span><a href="#" onclick="prev1(this)" id="next">下一页</a></li>
            <li><a href="javaScript:void(0)" id="total">共页</a></li>
            <li><span style="display: none"></span><a href="javaScript:void(0)" onclick="prev1(this)" id="endPage">尾页</a></li>
        </ul>
<!-- 全局js -->
<script src="plug-in/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="plug-in/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<!-- 自定义js -->
<script src="plug-in/hplus/js/content.js"></script>
<script type="text/javascript" src="plug-in/echart/echarts.min.js"></script>
<script type="text/javascript" src="plug-in/jquery-plugs/i18n/jquery.i18n.properties.js"></script>
<t:base type="tools"></t:base>
<script type="text/javascript" src="plug-in/login/js/getMsgs.js"></script>
<script>


//    表格内容  显示快要到期的  用户
var prev;
var next;
var totalPage;
var page;
var pageSize=2;
$(function() {
    /*+++++从后台获取统计数据++++*/
    $.ajax({
        type: "POST",
        url: "tDishwasherDeviceController.do?home",
        dataType: "JSON",
        success: function (jsondata) {
            console.log("首页数据："+JSON.stringify(jsondata))
            console.log(jsondata.obj.totalUser + "  totalUser")
            console.log(jsondata.obj.resentUser + "  resentUser")
            console.log(jsondata.obj.resentDevice + "  resentDevice")
            console.log(jsondata.obj.totalDevice + "  totalDevice")
                $("#totalUser").html(jsondata.obj.totalUser)
                $("#resentUser").html(jsondata.obj.totalUser)
                $("#resentDevice").html(jsondata.obj.resentDevice)
                $("#totalDevice").html(jsondata.obj.totalDevice)
        }
    });

    $.ajax({
        type: "POST",
        url: "tDishwasherDeviceController.do?expiring",
        data:{pageSize:pageSize,page:"1"},
        dataType: "JSON",
        success: function (jsondata) {

            page=jsondata.obj.page;
            prev=page-1;
            $("#prev").prev().html(prev)
            next=page+1;
            $("#next").prev().html(next)
            totalPage=jsondata.obj.totalPage;

            $("#page").html(page);
            $("#total").html("共"+totalPage+"页");
            $("#endPage").prev().html(totalPage)

            if(prev<=0){
                $("#firstPage").hide();
                $("#prev").hide();
            }else{
                $("#firstPage").show();
                $("#prev").show();
            }

            if(page==totalPage){
                $("#next").hide();
                $("#endPage").hide();
            }else{
                $("#endPage").show();
                $("#next").show();
            }
            $("#tbody").children().remove();

            for(var i=0;i<jsondata.obj.data.length;i++){
                $("#tbody").append(" <tr><td>"+jsondata.obj.data[i].id+"</td><td>"+jsondata.obj.data[i].deviceName+"</td><td>"+jsondata.obj.data[i].deviceNumber+"</td><td>"+jsondata.obj.data[i].deviceRenewDate+"</td><td>"+jsondata.obj.data[i].deviceExpireDate+"</td><td>"+jsondata.obj.data[i].deviceEndDate+"</td><td>"+jsondata.obj.data[i].deviceAddress+"</td><td>"+jsondata.obj.data[i].shop_name+"</td><td>"+jsondata.obj.data[i].deviceSalesperson+"</td><td>"+jsondata.obj.data[i].deviceUserMobile+"</td><td>"+jsondata.obj.data[i].deviceUserName+"</td><td><span style='display:none'>"+jsondata.obj.data[i].id+"</span><span  onclick='query(this)'>查看</span></td></tr>");
            }

        }
    });


});


function prev1(obj){
    var pageCurrent=$(obj).prev().html();
    $.ajax({
        type: "POST",
        url: "tDishwasherDeviceController.do?expiring",
        data:{pageSize:pageSize,page:pageCurrent},
        dataType: "JSON",
        success: function (jsondata) {
            page=jsondata.obj.page;
            prev=page-1;
            $("#prev").prev().html(prev)
            next=page+1;
            $("#next").prev().html(next)
            totalPage=jsondata.obj.totalPage;

            $("#page").html(page);
            $("#total").html("共"+totalPage+"页");
            $("#endPage").prev().html(totalPage)

            if(prev<=0){
                $("#firstPage").hide();
                $("#prev").hide();
            }else{
                $("#firstPage").show();
                $("#prev").show();
            }
            if(page==totalPage){
                $("#next").hide();
                $("#endPage").hide();
            }else{
                $("#endPage").show();
                $("#next").show();
            }
            $("#tbody").children().remove();
            for(var i=0;i<jsondata.obj.data.length;i++){
                $("#tbody").append(" <tr><td>"+jsondata.obj.data[i].id+"</td><td>"+jsondata.obj.data[i].deviceName+"</td><td>"+jsondata.obj.data[i].deviceNumber+"</td><td>"+jsondata.obj.data[i].deviceRenewDate+"</td><td>"+jsondata.obj.data[i].deviceExpireDate+"</td><td>"+jsondata.obj.data[i].deviceEndDate+"</td><td>"+jsondata.obj.data[i].deviceAddress+"</td><td>"+jsondata.obj.data[i].shop_name+"</td><td>"+jsondata.obj.data[i].deviceSalesperson+"</td><td>"+jsondata.obj.data[i].deviceUserMobile+"</td><td>"+jsondata.obj.data[i].deviceUserName+"</td><td><span style='display:none'>"+jsondata.obj.data[i].id+"</span><span  onclick='query(this)'>查看</span></td></tr>");
            }
        }
    });
}

function query(obj){
//    var id=$(this).prev().html();
//    alert($("#query").prev())
//    alert($(this).html());
//    alert(id1+"id1")
//    alert(e.target());

    var id=$(obj).prev().html()
    console.log("id:"+id)
    self.parent.addOneTab('积分明细','tDishwasherScoreController.do?list1&scoreDeviceId='+id,'icon-add')
}

</script>
</body>
</html>