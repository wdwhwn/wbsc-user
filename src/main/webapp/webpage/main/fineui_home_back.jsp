<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--360浏览器优先以webkit内核解析-->
    <title>Jeecg 微云快速开发平台</title>
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
                    <h3 class="font-bold no-margins">200</h3>
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
                    <h3 class="font-bold no-margins">2000</h3>
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
                    <h3 class="font-bold no-margins">￥500.00</h3>
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
                    <h3 class="font-bold no-margins">200</h3>
                    <%--<div>近7天销售总额</div>
                    <small>￥5000.00</small>--%>
                </div>
            </div>
        </div>
        <%--<div class="col-md-1 col-cs-2 col-xs-4">
            <div class="widget  p-lg text-center" style="background: #89c997;">
                <div>
                    <i class="iconfont icon-quanshengmingzhouqiguanli" style="font-size: 30px;"></i>
                    <h3 class="font-bold no-margins"></h3>
                    <small>功能5</small>
                </div>
            </div>
        </div>--%>
        <%--<div class="col-md-1 col-cs-2 col-xs-4">
            <div class="widget  p-lg text-center" style="background: #88abda;">
                <div>
                    <i class="iconfont icon-jixiao" style="font-size: 30px;"></i>
                    <h3 class="font-bold no-margins"></h3>
                    <small>功能6</small>
                </div>
            </div>
        </div>--%>
        <%--<div class="col-md-1 col-cs-2 col-xs-4">
            <div class="widget  p-lg text-center" style="background: #8c97cb;">
                <div>
                    <i class="iconfont icon-fangdajing-copy" style="font-size: 30px;"></i>
                    <h3 class="font-bold no-margins"></h3>
                    <small>功能7</small>
                </div>
            </div>
        </div>--%>
        <%--<div class="col-md-1 col-cs-2 col-xs-4">
            <div class="widget  p-lg text-center" style="background: #c490bf;">
                <div>
                    <i class="iconfont icon--youhuajieduan" style="font-size: 30px;"></i>
                    <h3 class="font-bold no-margins"></h3>
                    <small>功能8</small>
                </div>
            </div>
        </div>--%>
        <%--<div class="col-md-1 col-cs-2 col-xs-4">
            <div class="widget  p-lg text-center" style="background: #f19ec2;">
                <div>
                    <i class="iconfont icon-duoren" style="font-size: 30px;"></i>
                    <h3 class="font-bold no-margins"></h3>
                    <small>功能9</small>
                </div>
            </div>
        </div>--%>

    </div>
    <div class="row  border-bottom white-bg dashboard-header">
        <%-- <div class="col-sm-12">
             <blockquote class="text-warning" style="font-size:14px">您是否需要一款企业级J2EE快速开发平台，提高开发效率，缩短项目周期…
                 <br>您是否一直在苦苦寻找一款强大的代码生成器，节省码农的繁琐重复工作…
                 <br>您是否想拥有移动报表能力、自定义表单设计能力、插件开发能力(可插拔)、工作流配置能力…
                 <br>…………
                 <h4 class="text-danger">那么，现在Jeecg来了</h4>
             </blockquote>
             <hr>
         </div>--%>
        <%--<div class="col-sm-3">
            <h2>Hello,Guest</h2>
            <small>移动设备访问请扫描以下二维码：</small>
            <br>
            <br>
            <img src="plug-in/login/images/jeecg.jpg" width="100%" style="max-width:264px;">
            <br>
        </div>--%>
        <div class="col-sm-3">
            <%--<h2>
                Jeecg 微云快速开发平台
            </h2>--%>
            <%--<h3 style="padding-top: 5px;padding-left: 10px">--%>
            <h3 style="padding-top:5px;padding-left: 10px">
                <span >待付款订单</span><span style="padding-left: 200px">10</span>
            </h3>
            <h3 style="padding-top:5px;padding-left: 10px">
                <span>代发货订单</span><span style="padding-left: 200px">10</span>
            </h3>
            <h3 style="padding-top:5px;padding-left: 10px">
                <span>已发货订单</span><span style="padding-left: 200px">10</span>
            </h3>
            <%-- <p>
                 <b>当前版本：</b>v_3.8
             </p>
             <p>
                 <span class="label label-warning">开源     &nbsp; | &nbsp; 免费  | &nbsp; 更多插件</span>
             </p>
             <br>
             <p>
                 <a class="btn btn-success btn-outline" href="http://yun.jeecg.org" target="_blank">
                     <i class="fa fa-cloud"></i> JEECG插件下载
                 </a>
                 <a class="btn btn-white btn-bitbucket" href="http://www.jeecg.org/forum.php?mod=viewthread&tid=1249&page=1&extra=#pid3689" target="_blank">
                     <i class="fa fa-qq"> </i> 联系我们
                 </a>
                 <a class="btn btn-white btn-bitbucket" href="http://jeecg3.mydoc.io" target="_blank">
                     <i class="fa fa-home"></i> 在线文档
                 </a>
             </p>--%>
        </div>
        <div class="col-sm-5">
            <%--<h2>
                Jeecg 微云快速开发平台
            </h2>--%>
            <h3 style="padding-top: 5px;padding-left: 150px">
                <span>已完成订单</span><span style="padding-left: 200px">10</span>
            </h3>
            <h3 style="padding-top: 5px;padding-left: 150px">
                <span>新缺货登记</span><span style="padding-left: 200px;">10</span>
            </h3>
            <h3 style="padding-top: 5px;padding-left: 150px">
                <span>待处理退货订单</span><span style="padding-left: 170px">10</span>
            </h3>
            <%-- <p>
                 <b>当前版本：</b>v_3.8
             </p>
             <p>
                 <span class="label label-warning">开源     &nbsp; | &nbsp; 免费  | &nbsp; 更多插件</span>
             </p>
             <br>
             <p>
                 <a class="btn btn-success btn-outline" href="http://yun.jeecg.org" target="_blank">
                     <i class="fa fa-cloud"></i> JEECG插件下载
                 </a>
                 <a class="btn btn-white btn-bitbucket" href="http://www.jeecg.org/forum.php?mod=viewthread&tid=1249&page=1&extra=#pid3689" target="_blank">
                     <i class="fa fa-qq"> </i> 联系我们
                 </a>
                 <a class="btn btn-white btn-bitbucket" href="http://jeecg3.mydoc.io" target="_blank">
                     <i class="fa fa-home"></i> 在线文档
                 </a>
             </p>--%>
        </div>
        <div class="col-sm-4">
            <%--<h2>
                Jeecg 微云快速开发平台
            </h2>--%>
            <h3 style="padding-top: 5px">
                <span>待确认收货订单</span><span style="padding-left: 200px">10</span>
            </h3>
            <h3 style="padding-top: 5px">
                <span>待处理退款申请</span><span style="padding-left: 200px">10</span>
            </h3>
            <h3 style="padding-top: 5px">
                <span>广告位即将到期</span><span style="padding-left: 200px">10</span>
            </h3>
            <%-- <p>
                 <b>当前版本：</b>v_3.8
             </p>
             <p>
                 <span class="label label-warning">开源     &nbsp; | &nbsp; 免费  | &nbsp; 更多插件</span>
             </p>
             <br>
             <p>
                 <a class="btn btn-success btn-outline" href="http://yun.jeecg.org" target="_blank">
                     <i class="fa fa-cloud"></i> JEECG插件下载
                 </a>
                 <a class="btn btn-white btn-bitbucket" href="http://www.jeecg.org/forum.php?mod=viewthread&tid=1249&page=1&extra=#pid3689" target="_blank">
                     <i class="fa fa-qq"> </i> 联系我们
                 </a>
                 <a class="btn btn-white btn-bitbucket" href="http://jeecg3.mydoc.io" target="_blank">
                     <i class="fa fa-home"></i> 在线文档
                 </a>
             </p>--%>
        </div>
        <%--<div class="col-sm-4">
            <h4>Jeecg具有以下特点：</h4>
            <ol>
                <li>采用主流J2EE框架，容易上手;</li>
                <li>采用主流J2EE框架，容易上手;</li>
                <li>采用主流J2EE框架，容易上手;</li>
                <li>更多……</li>
            </ol>
        </div>--%>

    </div>
    <%--<div class="row">

        <div class="col-sm-5">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>待办事项</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <table class="table table-hover no-margins">
                        <thead>
                            <tr>
                                <th>序号</th>
                                <th>类型</th>
                                <th>任务</th>
                                <th>数量</th></tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1</td>
                                <td>
                                    <span class="label label-warning">站内信</span></td>
                                <td>会议</td>
                                <td class="text-navy">5</td></tr>
                            <tr>
                                <td>2</td>
                                <td>
                                    <span class="label label-primary">通知</span></td>
                                <td>任务7</td>
                                <td class="text-navy">4</td></tr>
                            <tr>
                                <td>3</td>
                                <td>
                                    <span class="label label-warning">类型1</span></td>
                                <td>任务六</td>
                                <td class="text-navy">2</td></tr>
                            <tr>
                                <td>4</td>
                                <td>
                                    <span class="label label-primary">类型2</span></td>
                                <td>任务5</td>
                                <td class="text-navy">0</td></tr>
                            <tr>
                                <td>5</td>
                                <td>
                                    <span class="label label-warning">类型3</span></td>
                                <td>任务4</td>
                                <td class="text-navy">16</td></tr>
                            <tr>
                                <td>6</td>
                                <td>
                                    <span class="label label-primary">类型4</span></td>
                                <td>任务3</td>
                                <td class="text-navy">3</td></tr>
                            <tr>
                                <td>7</td>
                                <td>
                                    <span class="label label-warning">类型5</span></td>
                                <td>任务2</td>
                                <td class="text-navy">7</td></tr>
                            <tr>
                                <td>8</td>
                                <td>
                                    <span class="label label-primary">类型6</span></td>
                                <td>任务1</td>
                                <td class="text-navy">2</td></tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>--%>
    <div class="col-sm-4">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>订单统计</h5>
                <div class="pull-right">
                    <div class="btn-group">
                        <button type="button" class="btn btn-xs btn-white active">天</button>
                        <button type="button" class="btn btn-xs btn-white">月</button>
                        <button type="button" class="btn btn-xs btn-white">年</button></div>
                </div>
            </div>
            <div class="ibox-content">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="flot-chart" style="height:315px;">
                            <div class="flot-chart-content" id="chart4"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%--<div class="col-sm-3" style="padding-left: 10px;">
        <!-- <iframe src="http://tianqi.5ikfc.com:90/plugin-code/?style=1&dy=1&fs=12" frameborder="0" scrolling="no" width="300" height="60" id="weather_frame"></iframe> -->
        <iframe name="weather_inc" src="http://i.tianqi.com/index.php?c=code&id=7" style="border:solid 1px #7ec8ea" width="105%" height="90" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
        <div id="calendar"></div>
    </div>
</div>--%>
    <%--<div class="row">
        <div class="col-sm-4">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>用户浏览器分析</h5>
                    <div class="pull-right">
                        <div class="btn-group">
                            <button type="button" class="btn btn-xs btn-white active">天</button>
                            <button type="button" class="btn btn-xs btn-white">月</button>
                            <button type="button" class="btn btn-xs btn-white">年</button></div>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="flot-chart">
                                <div class="flot-chart-content" id="chart1"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>浏览器分析</h5>
                    <div class="pull-right">
                        <div class="btn-group">
                            <button type="button" class="btn btn-xs btn-white active">天</button>
                            <button type="button" class="btn btn-xs btn-white">月</button>
                            <button type="button" class="btn btn-xs btn-white">年</button></div>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="flot-chart">
                                <div class="flot-chart-content" id="chart2"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>仪表盘</h5>
                    <div class="pull-right">
                        <div class="btn-group">
                            <button type="button" class="btn btn-xs btn-white active">天</button>
                            <button type="button" class="btn btn-xs btn-white">月</button>
                            <button type="button" class="btn btn-xs btn-white">年</button>
                         </div>
                    </div>
                </div>
                <div class="ibox-content">
                     <div class="row">
                        <div class="col-sm-12">
                            <div class="flot-chart">
                                <div class="flot-chart-content" id="chart3"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>--%>

    <div class="wrapper wrapper-content">
        <%--<div class="row">
            <div class="col-sm-4">

                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>商业支持</h5>
                    </div>
                    <div class="ibox-content">
                        <p>我们提供基于Jeecg的二次开发服务，具体费用请联系官方。</p>
                        <p>同时，我们也提供以下服务：</p>
                        <ol>
                            <li>Jeecg工作流开发平台 (商业版)</li>
                        </ol>
                    </div>
                </div>
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>联系信息</h5>

                    </div>
                    <div class="ibox-content">
                        <p><i class="fa fa-send-o"></i> 官网：<a href="http://www.jeecg.org" target="_blank">http://www.jeecg.org</a>
                        </p>
                        <p><i class="fa fa-qq"></i> QQ群：<a href="javascript:;">190866569</a>
                        </p>
                        <p><i class="fa fa-weixin"></i>微信公众号：<a href="javascript:;">jeecg</a>
                        </p>
                        <p><i class="fa fa-credit-card"></i> 邮箱：<a href="javascript:;" class="邮箱">jeecg@sina.com</a>
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>版本升级日志</h5>
                    </div>
                    <div class="ibox-content no-padding">
                        <div class="panel-body">
                            <div class="panel-group" id="version">
                            <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#version" href="#v57">v3.8</a><code class="pull-right">2018.10.24</code>
                                        </h5>
                                    </div>
                                    <div id="v57" class="panel-collapse collapse in">
                                        <div class="panel-body">
                                            <div class="alert alert-warning">此版本系统安全性能得到大幅提升，代码重构优化，上传组件使用plupload重构，提升浏览器兼容性，新增adminlte风格支持一级菜单导航，极大提升美感和代码可控性，降低了表单的开发成本；</div>
                                            <ol>
                                                <li>【新功能】一套新的代码生成器模板，Bootstrap表单+EasyUI列表（单表、一对多）;</li>
                                            </ol>
                                        </div>
                                    </div>
                            </div>

                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#version" href="#v33">更多版本...</a><code class="pull-right"></code>
                                        </h5>
                                    </div>
                                    <div id="v33" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <ol>
                                                <li>更多版本，请详见论坛：www.jeecg.org</li>
                                            </ol>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>JEECG 适用范围</h5>
                    </div>
                    <div class="ibox-content">
                        <p>为什么选择jeecg？</p>
                        <ol>
                            <li>采用主流框架，容易上手，学习成本低；</li>
                        </ol>
                        <hr>
                        <div class="alert alert-warning">极大降低开发成本。
                        </div>
                    </div>
                </div>
            </div>
        </div>--%>
    </div>
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
        $.ajax({
            type : "POST",
            url : "homeController.do?home",
            success : function(jsondata) {
                jsondata=JSON.parse(jsondata);
                console.log(wahoigfhaowgwhoigfhskalhkflxzhfoueg)
                console.log(jsondata)
            }
        });
        $(function(){
//        今日订单总数 orderSumToday             今日销售总额 sallSumToday
//        昨日销售总额 sallSumYesterday          近七天销售总额 sallSumWeek
//        待付款订单 orderUnpaid                 待发货订单 orderUnshipped
//        已发货订单 orderShipped                已完成订单 orderCompleted
//        新缺货登记 Shortage                    待处理退货订单 orderUntreatedReturnGoods
//        确认收货订单 orderReceiptConfirmation  待处理退款申请 pendingRefundApplication
//        广告位即将到期 AdvertisingSpaceExpires
            /* $.ajax({
             type : "POST",
             url : "homeController.do?home",
             success : function(jsondata) {
             jsondata=JSON.parse(jsondata);
             console.log(wahoigfhaowgwhoigfhskalhkflxzhfoueg)
             console.log(jsondata)
             }
             });*/
        });
        $(document).ready(function() {
            //直接嵌套显示
            laydate.render({
                elem: '#calendar'
                ,position: 'static'
                ,theme: 'molv'

            });
            var chart1 = echarts.init(document.getElementById('chart1'));
            var chart2 = echarts.init(document.getElementById('chart2'));
            var chart3 = echarts.init(document.getElementById('chart3'));
            var chart4 = echarts.init(document.getElementById('chart4'));
            $.ajax({
                type : "POST",
                url : "jeecgListDemoController.do?broswerCount&reportType=pie",
                success : function(jsondata) {
                    jsondata=JSON.parse(jsondata);
                    var data=jsondata[0].data;
                    var xAxisData=[];
                    var seriesData=[];
                    var picData = [];
                    for(var i in data){
                        xAxisData.push(data[i].name);
                        seriesData.push(data[i].percentage);
                        picData.push({"value":data[i].percentage,"name":data[i].name});
                    }
                    chart1.setOption({
                        tooltip: {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        legend: {
                            data: xAxisData
                        },
                        series : [
                            {
                                name: "用户人数",
                                type: 'pie',
                                radius : '55%',
                                center: ['50%', '60%'],
                                data:picData,
                                itemStyle: {
                                    emphasis: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                    });
                }
            });


            var option3 = {
                tooltip : {
                    formatter: "{a} <br/>{b} : {c}%"
                },
                toolbox: {
                    feature: {
                        restore: {},
                        saveAsImage: {}
                    }
                },
                series: [
                    {
                        name: '业务指标',
                        type: 'gauge',
                        detail: {formatter:'{value}%'},
                        data: [{value: 50, name: '完成率'}]
                    }
                ]
            };

            $.ajax({
                type : "POST",
                url : "jeecgListDemoController.do?broswerCount&reportType=column",
                success : function(jsondata) {
                    jsondata=JSON.parse(jsondata);
                    var data=jsondata[0].data;
                    var xAxisData=[];
                    var seriesData=[];
                    for(var i in data){
                        xAxisData.push(data[i].name);
                        seriesData.push(data[i].percentage);
                    }
                    var option3 = {
                        tooltip: {},
                        legend: {
                            data:[jsondata[0].name],
                            left:'center'
                        },
                        xAxis: {
                            type: 'category',
                            data: xAxisData,
                            axisLabel:{
                                interval:0,//横轴信息全部显示
                                rotate:-30,//-10角度倾斜展示
                            }
                        },
                        yAxis: {},
                        series: [{
                            name: jsondata[0].name,
                            type: 'bar',
                            data: seriesData
                        }]
                    };
                    chart3.setOption(option3);
                }
            });

            $.ajax({
                type : "POST",
                url : "jeecgListDemoController.do?broswerCount&reportType=line",
                success : function(jsondata) {
                    jsondata=JSON.parse(jsondata);
                    var data=jsondata[0].data;
                    var xAxisData=[];
                    var seriesData=[];
                    for(var i in data){
                        xAxisData.push(data[i].name);
                        seriesData.push(data[i].percentage);
                    }
                    var option4 = {
                        color: ['#3398DB'],
                        tooltip : {
                            trigger: 'axis',
                            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                            }
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '10%',
                            containLabel: true
                        },
                        xAxis : [
                            {
                                type : 'category',
                                data : xAxisData,
                                axisTick: {
                                    alignWithLabel: true
                                },
                                axisLabel:{
                                    interval:0,//横轴信息全部显示
                                    rotate:-30,//-10角度倾斜展示
                                }
                            }
                        ],
                        yAxis : [
                            {
                                type : 'value'
                            }
                        ],
                        series : [
                            {
                                name:'用户人数',
                                type:'line',
                                barWidth: '60%',
                                data:seriesData
                            }
                        ]
                    };
                    chart4.setOption(option4, true);
                }
            });

            var colors = ['#5793f3', '#d14a61', '#675bba'];
            $.ajax({
                type : "POST",
                url : "graphReportController.do?datagridGraph",
                data:{
                    configId:'yhcztj'
                },
                success : function(jsondata) {
                    var data = jsondata.rows;
                    var xAxisData=[];
                    var seriesData=[];
                    var loginData=[];
                    if(data!=null){
                        for(var i = data.length-1; i >= 0; i--){
                            xAxisData.push(data[i].userid);	//用户名
                            seriesData.push(data[i].ct);	//操作次数
                            loginData.push(data[i].login_count);//登陆次数
                        }
                    }
                    var option2 = {
                        tooltip : {
                            trigger: 'axis',
                            axisPointer : {
                                type : 'shadow'
                            }
                        },
                        legend: {
                            data: ["操作次数",'登陆次数']
                        },
                        grid: {
                            left: '1%',
                            right: '6%',
                            bottom: '3%',
                            containLabel: true
                        },
                        xAxis:  {
                            type: 'value'
                        },
                        yAxis: {
                            type: 'category',
                            data: xAxisData
                        },
                        series: [
                            {
                                name: '操作次数',
                                type: 'bar',
                                stack: '总量',
                                label: {
                                    normal: {
                                        show: true,
                                        position: 'insideRight'
                                    }
                                },
                                data : seriesData
                            },
                            {
                                name: '登陆次数',
                                type: 'bar',
                                stack: '总量',
                                label: {
                                    normal: {
                                        show: true,
                                        position: 'insideRight'
                                    }
                                },
                                data : loginData
                            }
                        ]
                    };
                    chart2.setOption(option2, true);
                }
            });



            $(window).resize(chart1.resize);
            $(window).resize(chart2.resize);
            $(window).resize(chart3.resize);
            $(window).resize(chart4.resize);
        });
    </script>
    <!--统计代码，可删除-->
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>
</html>