<%@ page language="java"  import="java.util.*,java.sql.*"  pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>用户管理</title>
    <link rel="stylesheet" type="text/css" href="web/css/index.css">
    <%request.setCharacterEncoding("utf-8"); %>

   
  </head>
  <body class="mian_bj" >
  
<div class="mian_top_01">
<div class="mian_top_r"></div>
<div class="mian_top_l"></div>
<div class="mian_top_c">
<!--<ul>
<li><a><p>车辆信息列表</p></a></li>
</ul>  -->
</div>
<div class="mian_b">
<div class="mian_b1">
 
<form action="" method="post">
<p class="mian_b1_sousuo"><input name="Chezhu" type="text" id="xm">
<!--  <input name="button" type="button" value="搜索" class="mian_b1_a3" onclick="ssyh()" />-->
</p>
</form>
<a href="#" title="搜索" class="mian_b1_a3"></a>
<a href="#" title="最后" class="mian_b1_a4"></a>
<a href="#" title="下一页" class="mian_b1_a5"></a>
<p class="mian_b1_list">1 2 3 4……</p>
<a href="#" title="上一页" class="mian_b1_a6"></a>
<a href="#" title="最前" class="mian_b1_a7"></a>
</div>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mian_b_bg">
<tr>
<td width="12%" class="mian_b_bg_lm"><span></span>序号</td>
<td width="12%" class="mian_b_bg_lm"><span></span>车牌号</td>
<td width="12%" class="mian_b_bg_lm"><span></span>车主</td>
<td width="12%" class="mian_b_bg_lm"><span></span>车辆信息</td>
<!-- <td width="8%" class="mian_b_bg_lm"><span></span>紧急程度</td>
<td class="mian_b_bg_lm"><span></span>操作</td> -->
</tr>
        



</table>
</div>
</div>
</body>
 
</html>
