<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<!doctype html>
<html>
<head>
	<title>员工工资详情页面</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="${basePath }static/web/css/details.css">
	<link rel="stylesheet" href="${basePath }static/web/css/base.css">
	<link rel="stylesheet" href="${basePath }static/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${basePath }static/bootstrap/css/bootstrap-theme.min.css">
	<style type="text/css">
	body{padding: 0;margin: 0;}
	.info-list{margin-top: 20px; border: 1px solid #eee;}
	.info-header{padding: 20px 30px;}
	.info-header >label{line-height: 30px;}
	.info-body{padding: 0;line-height: 30px;}
	.item-group:after,.item-group section:after{clear: both;content: '';display: table}
	.item-group p{padding: 0 20px;border: 1px solid #eee;border-left: none; border-right: none;     margin-bottom: -1px;}
	.item-group p i{margin-top: 6px;cursor: pointer; font-size: 18px;}
	.item-group table{padding-bottom: 30px;}
	.item-group.active table{display: none;}
	.showDetails thead{font-size:2vw;color:#428BCA;}
	.showDetails {color: #0BA98A;}
</style>
</head>
<body>
	<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading">
			<button class="icon" disabled="disabled">
				<span class="glyphicon glyphicon-th-large"></span>
			</button>
			<span>员工工资详情</span>
			<div class="pull-right">
				<button type="button" class="btn btn-primary" id="addEvent">添加事件</button>
				<button type="button" class="btn btn-info" id="saveForm">保存</button>
				<button type="button" class="btn btn-danger" onclick="javascript:history.go(-1);">返回</button>
			</div>
		</div>
		<div class="panel-body ">
		<div class="col-sm-10">
			<form action="" method="post" class="form-horizontal" id="salaryDetailForm">
			
				<div class="form-group">
					<label class="col-sm-2 control-label">姓名:</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" placeholder="请输入姓名" value="${detail.stuffname}" readonly>
					</div>
					<label class="col-sm-2 control-label">职位:</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" placeholder="请输入职位"  value="${detail.position}" readonly>
					</div>
					<label class="col-sm-2 control-label">基本工资:</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" placeholder="请输入基本工资"  name="basesalary" value="${detail.basesalary}" readonly>
					</div>
				</div>
				<!-- end form-group  -->
				
				<div class="form-group">
					<label class="col-sm-2 control-label">年份:</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="year"  placeholder="请输入年份" value="${detail.year}" readonly>
					</div>
					<label class="col-sm-2 control-label">月份:</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="month" placeholder="请输入月份"  value="${detail.month}" readonly>
					</div>
					<label class="col-sm-2 control-label" >备注:</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" placeholder="备注" name="remark" value="${detail.remark}">
					</div>
					
				</div>
				<!-- end form-group  -->
				
				<div class="form-group">
					<label class="col-sm-2 control-label">提成:</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" placeholder="请输入提成"  value="${detail.shouldplus}" readonly>
					</div>
					<label class="col-sm-2 control-label">扣除:</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" placeholder="请输入扣除"  value="${detail.shouldminus}" readonly>
					</div>
					
					<label class="col-sm-2 control-label">请假天数：</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" placeholder="请输入请假天数" name="dayoff" value="${detail.dayoff}">
					</div>
				</div>
				<!-- end form-group  -->
				<div class="form-group">
					<label class="col-sm-2 control-label">应发:</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" placeholder="请输入应发"  value="${detail.should}" readonly>
					</div>
					<label class="col-sm-2 control-label">实发:</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" placeholder="${detail.actual}" name="actual"  >
					</div>
					<label class="col-sm-2 control-label">结余:</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" placeholder="请输入结余"  value="${detail.balance}" readonly>
					</div>
					
				</div>
			<!-- 	<div class="form-group">
					<div class="myButton">
					<button type="button" class="btn btn-warning" id="modifyButton" style="display: none;">修改 </button>
					<button type="button" class="btn btn-primary" id="subButton">保存</button>
					<button type="button" class="btn btn-danger" id="backButton" onclick="javascript:history.go(-1)">返回</button>
					</div>
				</div>		 -->		
				<input type="hidden" name="id" id="hiddenId" value="${detail.id}">
				
			</form>
		</div>
		</div>
	</div>
	<div class="col-sm-12 info-body  ">
		<div class="panel-heading item-group" style="font-size: 700;">
			<button class="icon plusIcon" disabled="disabled">
			<span class="glyphicon glyphicon-plus"  style="color:white;"></span>
				<!-- <span style="color:white">提成</span> -->
			</button>
			<span style="font-size:19px;margin-left:5px">奖金</span>
			<i class="glyphicon glyphicon-plus-sign pull-right"></i>
			<div class="col-sm-121"></div><!--分割bootstrap的排版问题  -->
			<div class="col-sm-10 col-sm-offset-1">
			<table class="table table-hover showDetails " id="salaryPlusTable">
					<thead>
					<tr>
					<th class="col-sm-3">事件</th>
					<th class="col-sm-3">金额</th>
					<th class="col-sm-3">时间</th>
					<th class="col-sm-3">操作</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="pluses" items="${plusDetail}">
					<tr>
						<td>${pluses.plusname}</td>
						<td>${pluses.plusmoney}</td>
						<td>${pluses.remark}</td>
						<td><a class="deletePlus" href="javascript:;" data-id="${pluses.plusid}">删除</a></td>
					</tr>
					</c:forEach>
					</tbody>
					</table>
			</div>
		</div>

		<div class="panel-heading item-group" style=" margin-top: 20px;">
			<button class="icon plusIcon" disabled="disabled">
				<span class="glyphicon glyphicon-minus"  style="color:white;"></span>
			</button>
			<span style="font-size:19px;margin-left:5px">扣除</span>
			<i class="glyphicon glyphicon-plus-sign pull-right"></i>
			<div class="col-sm-121"></div><!--分割bootstrap的排版问题  -->
			<div class="col-sm-10 col-sm-offset-1">
				<table class="table table-hover showDetails " id="salaryMinusTable">
					<thead>
						<tr>
							<th class="col-sm-3">事件</th>
							<th class="col-sm-3">金额</th>
							<th class="col-sm-3">时间</th>
							<th class="col-sm-3">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="minuses" items="${minusDetail}">
						<tr>
							<td>${minuses.minusname}</td>
							<td>${minuses.minusmoney}</td>
							<td>${minuses.remark}</td>
							<td><a class="deleteMinus" href="javascript:;" data-id="${minuses.minusid}">删除</a></td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="addEventModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">添加事件</h4>
      </div>
	  <div class="modal-body">
	  	<form class="form-horizontal">
		  	<div class="form-group">
		  		<label class="col-sm-4 col-md-4 control-label">请选择添加的类型:</label>
		  		<div class="col-sm-6">
				  	<select class="form-control" id="addType">
				  		<option value="1">添加奖金</option>
				  		<option value="2">添加罚款</option>
				  	</select>
			  	</div>
			  </div>
			<div class="form-group">
				<label class="col-sm-4 control-label">事件名称:</label>
				<div class="col-sm-6">
					<input class="form-control" type="text" id="EventName">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">涉及金额:</label>
				<div class="col-sm-6">
				<input class="form-control" type="text" id="eventMoney">
				</div>
			</div>
			
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="saveEvent"  data-dismiss="modal">确定</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript" src="${basePath}static/bootstrap/js/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}static/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}static/bootstrap/js/bootstrapValidator.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#saveForm").click(function(){
			var sendData=$("#salaryDetailForm").serialize();
			$.ajax({
				data:sendData,
				type:'post',
				dataType:'json',
				url:'${basePath}rest/stuff/updateSalaryDetail',
				success:function(data){
					if(data.msg="success"){
						parent.layer.msg("操作成功");
						location.reload();
					}else if(data.msg="error"){
						parent.layer.msg("操作失败,请联系管理员");
					}
				},error:function(){
					parent.layer.msg(" 系统错误！");
				}
			})
		})
		$('.item-group i').on('click',function(){
			var $this = $(this),
			$parents = $this.parents('.item-group');
			$parents.toggleClass('active');
		});
		
		$(".deleteMinus").click(function(){
			var minusid=$(this).data("id");
			var type=2;
			deleteEvent(minusid,type);
		});
		$(".deletePlus").click(function(){
			var plusid=$(this).data("id");
			var type=1;
			deleteEvent(plusid,type);
		});
		$("#addEvent").click(function(){
			$("#addEventModal").modal("show");
		});
		$("#saveEvent").click(function(){
			$.ajax({
				type:'post',
				data:{type:$("#addType").val(),eventName:$("#EventName").val(),eventMoney:$("#eventMoney").val(),userId:$("#hiddenId").val()},
				dataType:'json',
				url:'${basePath}rest/stuff/addEvent',
				success:function(data){
					if(data.msg="success"){
						parent.layer.msg("操作成功");
						location.reload();
					}else if(data.msg="error"){
						parent.layer.msg("操作失败,请联系管理员");
					}
				},error:function(){
					parent.layer.msg(" 系统错误！");
				}
			})
		})
		
		
		
		
		function deleteEvent(id,type){
			$.ajax({
				type:'post',
				dataType:'json',
				data:{id:id,type:type},
				url:'${basePath }rest/stuff/deleteEventById',
				success:function(data){
					if(data.msg="success"){
						parent.layer.msg("操作成功");
						location.reload();
					}else if(data.msg="error"){
						parent.layer.msg("操作失败,请联系管理员");
					}
				},error:function(){
					parent.layer.msg(" 系统错误！");
				}
			});//end ajax
		}
	})

</script>
</body>
</html>