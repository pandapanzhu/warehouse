<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工工资总览</title>
<link rel="stylesheet" href="${basePath }static/web/css/base.css">
<link rel="stylesheet" href="${basePath }static/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${basePath }static/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${basePath }static/web/css/list.css">
<link rel="stylesheet" href="${basePath }static/bootstrap/css/bootstrap-datetimepicker.min.css">
<style>
.stuffSearch{
	width:25%;
}
.dateSearch{
	width:20%;	
}
</style>

</head>
<body>
<div class="title w clearfix">
	<div class="c_search clearfix">
		<form action="" id="formId" method="post">
			<div>
				<span>员工名称： </span>
			</div>
			<div class='stuffSearch'>
				<select class="form-control" name="search" id="condition">
				<option value="">全部员工</option>
				<c:forEach var="stuffList" items="${stuffList}">
				<option value="${stuffList.id}">${stuffList.stuffname}</option>
				</c:forEach>
				</select>
			</div>
			<div style="margin-left：5px;">
				<span>日期：</span>
			</div>
			<div class="dateSearch">
				<input class="form-control datetimepicker" name="date" id="dateValue" readonly value="${myYear}-${myMonth}">
			</div>
			<div>
				<button type="submit" class="btn" id="query_btn">搜索</button>
			</div>
			<input type="hidden" name="pageNum" value="1" id="pageNum">
		</form>
	</div>
</div>

<div class="QueryList">
<table class="table table-bordered mytable" id="mytable">
		<thead>
			<tr style="background: #DBF1ED;">
				<th class="col-xs-1">序号</th>
				<th class="col-xs-2">姓名</th>
				<th class="col-xs-2">职位</th>
				<th class="col-xs-1">基本工资</th>
				<th class="col-xs-1">提成</th>
				<th class="col-xs-1">扣除</th>
				<th class="col-xs-1">应发工资</th>
				<th class="col-xs-1">实发工资</th>
				<th colspan="2"  class="col-xs-2">操作</th>
			</tr>
		</thead>
		<tbody>
		 	<c:forEach var="myList" varStatus="listNum" items="${page.list}">
			<tr
			<c:if test="${listNum.index%2==1 }">style="background-color: #ffffff"</c:if>
         	<c:if test="${listNum.index%2==0 }">style="background-color: #f9f9f9"</c:if>
			>	
			<td>${listNum.count}</td>
				<td>${myList.stuffname}</td>
				<td>${myList.position}</td>
				<td>${myList.basesalary}</td>
				<td>${myList.shouldplus}</td>
				<td>${myList.shouldminus}</td>
				<td>${myList.should }
				<td>${myList.actual }
				<td>
					<a href="${basePath }rest/stuff/toSaveOrupdateStuffSalary?id=${myList.id}" >详情</a>
				</td>
			</tr>
			</c:forEach>
			  
		</tbody>
	</table>
</div>

</body>
<script type="text/javascript" src="${basePath }static/bootstrap/js/jquery.min.js"></script>
<script type="text/javascript" src="${basePath }static/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath }static/layer/layer.js"></script>
<script type="text/javascript" src="${basePath }static/web/js/page.js"></script>
<script type="text/javascript" src="${basePath }static/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${basePath }static/bootstrap/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
$(function(){	
	$(".datetimepicker").datetimepicker({
		startView:"year",
		minView:"year",
        language: "zh-CN",
        initialDate: new Date(),
        autoclose: true,//选中之后自动隐藏日期选择框
        clearBtn: true,//清除按钮
        todayBtn: true,//今日按钮
        format: "yyyy-mm"//日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
	}) ;
})

</script>

</html>