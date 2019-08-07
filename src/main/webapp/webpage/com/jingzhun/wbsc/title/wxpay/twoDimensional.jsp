<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>会员积分</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form"></t:base>
	<SCRIPT type="text/javascript">
        var num=${money}
       alert("获取金额："+ num)

		/*+++++获取支付二维码++++*/
        $.ajax({
            type: "POST",
            url: "wxpay.do?wxPay",
//            url: "/rest/wxpay.do?pay",
            dataType: "JSON",
            data:{'money':num},
            success: function (jsondata) {
//                console.log("首页数据："+JSON.stringify(jsondata))
//                console.log(jsondata.obj + "  二维码")
				var str="data:image;base64,"+jsondata.obj;
//				alert(str);
                $("#twoDimensional").prop("src",str)
            }
        });
	</SCRIPT>
</head>
 <%--<body style="overflow:hidden;overflow-y:auto;">--%>
 <body >
 <div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
		<img id="twoDimensional" src="data:image;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAIAAAAiOjnJAAADR0lEQVR42u3dQW4kIRBFwbr/pe0b
WLIgfyYQbzszLpoKL1LQmu9HKuizBQJLYAksCSyBJbAksASWwJLAElgCSwJLYAksCSyBJbAksASW
wJLAElgCSwJLD8D6Uv393LqPUPfcITsJFlhggQUWWGCBBdarsGI/+V/PjfleeaNdOwkWWGCBBRZY
YIEFFljL+77yk7vI1j13yAQNFlhggQUWWGCBBRZY52z0xjWDBRZYYIEFFlhggQXWG7BWhsSuu1xH
7CRYYIEFFlhggQUWWK/Cmkk29nndxwILLLDAAgsssMACazCsulbYnfinsZ0ECyywwAILLLDAAusZ
WDMbcvHrncACCyywwAILLLDAOn8q3DhRbjzEWGEXczZzVWCBBRZYYIEFFlhgPQOrbjrr+gixZQxZ
JFhggQUWWGCBBRZY916bqRsSu6azrhtXsbcAFlhggQUWWGCBBda9sFbo1CnsOi3ZeAxVt1dggQUW
WGCBBRZYYIHV+kbrnlv3l+uOv8ACCyywwAILLLDAAmv3dNa1jCFvJTZ+ggUWWGCBBRZYYIEFVnZn
u95ZbJGmQrDAAgsssMACCyywslNh3bBWd1srtqq6XwawwAILLLDAAgsssMDa7axOQ92dqtjZ0Uzu
YIEFFlhggQUWWGBdBCt28tBlJXbhrOvsCCywwAILLLDAAgusZ2B1zX2xvYvBOnpIBAsssMACCyyw
wALrfFhdX0yoGzCHcJ8WWGCBBRZYYIEFFlgnwIp9BSA2UXbdAzvr2xNggQUWWGCBBRZYYF03FXYd
cWz8t7ELZ3Vj7/1nhWCBBRZYYIEFFlhglU+Fsa8t1C0jdv7z3H0ssMACCyywwAILLLCa/+vejT95
5juLXTgbN6SDBRZYYIEFFlhggXU8rNiU1HXiEbuAlT+lAQsssMACCyywwAILrE2wjjiVqptkp02U
YIEFFlhggQUWWGCBNeAAZOMi66bR9hESLLDAAgsssMACC6zzYQ15ozErGzUMEQwWWGCBBRZYYIEF
1r2wTjwe2fjcrmXEftvBAgsssMACCyywwDoZlgSWwBJYElgCS2AJLAksgSWwJLAElsCSwBJYAksC
S2AJLAksgSWwJLAElsCSwBJYeqdfo2tjZBbrEZUAAAAASUVORK5CYII="/></div>
	</div>
 </div>
<script type="text/javascript">
var subDlgIndex = '';
$(document).ready(function() {
    //单选框/多选框初始化
    $('.i-checks').iCheck({
        labelHover: false,
        cursor: true,
        checkboxClass: 'icheckbox_square-green',
        radioClass: 'iradio_square-green',
        increaseArea: '20%'
    });

})
</script>
</body>
</html>