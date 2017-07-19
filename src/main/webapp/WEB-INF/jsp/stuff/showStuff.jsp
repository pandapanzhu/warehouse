<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工基本信息</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
				<input type="text" name="search" id="condition" class="form-control">		
			</div>
			<div>
				<button type="submit" class="btn" id="query_btn">搜索</button>
			</div>
			<input type="hidden" name="pageNum" value="1" id="pageNum">
		</form>
	</div>
	<div class="c_new">
		<a href="${basePath }rest/stuff/toSaveOrupdateStuff">
		<button class="btn" id="newObject">添加员工</button>
		</a>
	</div>
</div>

<div class="QueryList">
<table class="table table-bordered mytable" id="mytable">
		<thead>
			<tr style="background: #DBF1ED;">
				<th class="col-xs-1">序号</th>
				<th class="col-xs-2">姓名</th>
				<th class="col-xs-2">职位</th>
				<th class="col-xs-1">电话</th>
				<th class="col-xs-1">基本工资</th>
				<th class="col-xs-1">状态</th>
				<th colspan="2"  class="col-xs-2">操作</th>
			</tr>
		</thead>
		<tbody>
		 	<c:forEach var="myList" varStatus="listNum" items="${page.list}">
			<tr
			<c:if test="${listNum.index%2==1 }">style="background-color: #ffffff"</c:if>
         	<c:if test="${listNum.index%2==0 }">style="background-color: #f9f9f9"</c:if>
			>	<td>${listNum.count}</td>
				<td>${myList.stuffname}</td>
				<td>${myList.position}</td>
				<td>${myList.phone}</td>
				<td>${myList.basesalary}</td>
				<c:if test="${myList.status == 1}">
				<td>离职</td>
				</c:if> 
				<c:if test="${myList.status == 0}">
				<td>在职</td>
				</c:if>
				
				
				<td>
					<a href="${basePath }rest/stuff/toSaveOrupdateStuff?id=${myList.id}" >查看</a>&nbsp;|&nbsp;
					<a href="javascript:;" class="dltStuff" style="color:#D9534F" data-id="${myList.id}">删除</a>&nbsp;|&nbsp;
					<c:if test="${myList.status == 1}">
					<button class="btn btn-info leaveButton" data-id="${myList.id}">报道</button>
					</c:if> 
					<c:if test="${myList.status == 0}">
					<button class="btn btn-info workButton" data-id="${myList.id}">离职</button>
					</c:if>
				</td>
			</tr>
			</c:forEach>
			  
		</tbody>
	</table>
</div>

<div id="pageDiv" class="pagination pull-right"></div>


<script type="text/javascript" src="${basePath }static/bootstrap/js/jquery.min.js"></script>
<script type="text/javascript" src="${basePath }static/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath }static/layer/layer.js"></script>
<script type="text/javascript" src="${basePath }static/web/js/page.js"></script>
<script type="text/javascript">
$(function(){
	//分页初始化
	var pageNum=${page.currentPage}
	var totalPage=${page.totalPage}
	initPageHtml(pageNum, totalPage);
	
	$(".dltStuff").click(function(){
		$.ajax({
			url:'${basePath}rest/stuff/dltStuff',
			data:{id:$(this).data("id")},
			type:'post',
			dataType:'json',
			success:function(data){
				var msg=data.msg;
				if(msg=='success'){
					parent.layer.msg('删除成功');
					location.reload();
				}else{
					parent.layer.msg('删除失败');
				}
			}
		})
	})
	
	$(".leaveButton").click(function () {
		var id=$(this).data("id");
		var status=0;
		leaveFunction(id,status);
	});
	$(".workButton").click(function () {
		var id=$(this).data("id");
		var status=1;
		leaveFunction(id,status);
	})
	
	function leaveFunction(id,status){
		//var status=status*1//转化为数字
		$.ajax({
			url:'${basePath}rest/stuff/leaveStuff',
			data:{id:id,status:status},
			type:'post',
			dataType:'json',
			success:function(data){
				var msg=data.msg;
				if(msg=='success'){
					parent.layer.msg('操作成功');
					location.reload();
				}else{
					parent.layer.msg('操作失败');
				}
			}
		})
	}
	
})


</script>

</body>
</html>