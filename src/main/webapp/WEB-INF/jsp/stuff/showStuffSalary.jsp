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

</head>
<body>
<div class="title w clearfix">
	<div class="c_search clearfix">
		<form action="" id="formId" method="post">
			<div>
				<span>员工名称： </span>
			</div>
			<div>
				<select class="form-control" name="search" id="condition">
				<option value="">全部员工</option>
				<c:forEach var="stuffList" items="${stuffList}">
				<option value="${stuffList.id}">${stuffList.stuffname}</option>
				</c:forEach>
				</select>
			</div>
			<div>
				<button type="submit" class="btn" id="query_btn">搜索</button>
			</div>
			<input type="hidden" name="pageNum" value="1" id="pageNum">
		</form>
	</div>
</div>

</body>
</html>