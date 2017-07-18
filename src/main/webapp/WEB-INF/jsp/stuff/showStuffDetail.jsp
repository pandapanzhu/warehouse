<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<!doctype html>
<html>
<head>
	<title>员工详情页面</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="${basePath }static/web/css/base.css">
	<link rel="stylesheet" href="${basePath }static/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${basePath }static/bootstrap/css/bootstrap-theme.min.css">
	<style type="text/css">
	.panel {
		margin-top: 40px;
	}
	.panel-heading {
		background: #F1F9F8 !important;
		color: #2EB8F9 !important;
		font-weight: bold;
		font-size: 14px;
	}
	.icon {
		background-color: #2EB8F9 !important;
		border-radius: 50%;
		width: 30px;
		height: 30px;
		border: none;
	}
	.glyphicon-th-large{
	    background: #FFFFFF;
	}
	.myButton{
		text-align: center;
		margin-top: 20px;
	}
	</style>
</head>
<body>
<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading">
			<button class="icon" disabled="disabled">
				<span class="glyphicon glyphicon-th-large"></span>
			</button>
			<c:if test="${pageType eq 'add'}">
			添加员工信息
			</c:if>
			<c:if test="${pageType eq 'modify'}">
			修改员工信息
			</c:if>
		</div>
		<div class="panel-body ">
		<div class="col-sm-10">
			<form action="" method="post" class="form-horizontal">
			
				<div class="form-group">
					<label class="col-sm-2 control-label">姓名:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" placeholder="请输入姓名" name="stuffname">
					</div>
					
					<label class="col-sm-2 control-label">身份证号码:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" placeholder="请输入身份证号码" name="idcard">
					</div>
				</div>
				<!-- end form-group  -->
				
				<div class="form-group">
					<label class="col-sm-2 control-label">电话号码:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" placeholder="请输入电话号码，11位纯数字" name="phone">
					</div>
					
					<label class="col-sm-2 control-label">住址:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" placeholder="请输入家庭住址" name="address">
					</div>
				</div>
				<!-- end form-group  -->
				
				<div class="form-group">
					<label class="col-sm-2 control-label">职位:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" placeholder="请输入职位" name="position">
					</div>
					
					<label class="col-sm-2 control-label">基本工资:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" placeholder="请输入工资，纯数字，精确到个位" name="basesalary">
					</div>
				</div>
				<!-- end form-group  -->
				
				<div class="form-group">
					<div class="myButton">
					<button type="button" class="btn btn-warning" id="modifyButton" style="display: none;">修改 </button>
					<button type="button" class="btn btn-primary" id="subButton">保存</button>
					<button type="button" class="btn btn-danger" id="backButton" onclick="javascript:history.go(-1)">返回</button>
					</div>
				</div>
				
				<input type="hidden" name="id" id="hiddenId">
			</form>
		</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${basePath}static/bootstrap/js/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}static/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}static/bootstrap/js/bootstrapValidator.min.js"></script>
<script type="text/javascript">
$(function(){
	var pageType="${pageType}";
	if(pageType=="modify"){
		//数据填充
		$("input[name='id']").empty().val('${stuff.id}').attr("readonly",true);
		$("input[name='stuffname']").empty().val('${stuff.stuffname}').attr("readonly",true);
		$("input[name='idcard']").empty().val('${stuff.idcard}').attr("readonly",true);
		$("input[name='phone']").empty().val('${stuff.phone}').attr("readonly",true);
		$("input[name='address']").empty().val('${stuff.address}').attr("readonly",true);
		$("input[name='position']").empty().val('${stuff.position}').attr("readonly",true);
		$("input[name='basesalary']").empty().val('${stuff.basesalary}').attr("readonly",true);
	
		$("#subButton").hide();
		$("#modifyButton").show();
	}
	$("#modifyButton").click(function(){
		$("#subButton").show();
		$("#modifyButton").hide();
		$("input").attr("readonly",false);
	});//点击了修改按钮
	
	
	$("#subButton").click(function(){
		$("form").bootstrapValidator('validate').submit();
		//var formValue= $("form").serialize();
		var flag=$("form").data("bootstrapValidator").isValid();
		if(flag){
			$.ajax({
				url:'${basePath}rest/stuff/doSaveOrupdateStuff',
				data:$("form").serialize(),
				type:'post',
				dataType:'json',
				success:function(data){
					var msg=data.msg;
					if(msg=='success'){
						parent.layer.confirm("保存成功,是否返回到主页",{
							btn:['确定','取消'],//按钮
							shade:false//不显示遮罩
						},function(){
							location.href="${basePath }rest/stuff/toShowStuff";
							parent.layer.closeAll('dialog');
						},function(){
							parent.layer.closeAll('dialog');
						});
					}else{
						parent.layer.msg("保存失败");
					}
				},error:function(){
					parent.layer.msg("系统错误，请联系管理员");
				}
				
			})
		}else{
			return flag;
		}
	});//end submit click
	
	
	
	
/* 利用 bootstrapValidator 实现填充校验*/
	$("form").bootstrapValidator({
        message: '信息有误',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        live: "disabled",
        fields: {
        	stuffname: {
                /* message: '姓名填写错误!', */
                validators: {
                    notEmpty: {
                        message: '姓名不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 10,
                        message: '姓名的长度应该在2-10之间'
                    }
                }
            },
            idcard: {
                validators: {
                    notEmpty: {
                        message: '身份证不能为空'
                    },
                    stringLength: {
                        min: 16,
                        max: 18,
                        message: '身份证长度应该在16-19之间'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9]+$/,
                        message: '身份证只能由字母,数字组成'
                    }
                }
            },
            phone:{
            	validators: {
                    notEmpty: {
                        message: '电话号码不能为空'
                    },
                    regexp:{
                    	regexp:/^[0-9]+$/,
                    	message:'电话号码只能由11位的数字组成'
                    },
                    stringLength: {
                        min: 10,
                        max: 12,
                        message: '电话号码只能由11位的数字组成'
                    }
                }
            },
            position:{
            	validators: {
                    notEmpty: {
                        message: '职位不能为空'
                    }
                }
            },
            basesalary:{
            	validators: {
                    notEmpty: {
                        message: '基本工资不能为空'
                    }
                }
            }
        }
    })
})


        
        
 
 



</script>
</body>
</html>