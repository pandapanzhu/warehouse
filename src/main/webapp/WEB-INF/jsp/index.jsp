<%@page import="com.warehouse.javacode.domain.Login"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<!doctype html>
<html>
<head>
<title>主页</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${basePath }static/web/css/base.css">
<link rel="stylesheet" href="${basePath }static/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${basePath }static/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${basePath }static/web/css/index.css">
</head>
<body>
	<div class="head w">
		<div class="logo">
			<img src="${basePath }static/web/img/logo.png">
		</div>
		<div class="msg">
			<div class="show"></div>
			<div class="ctr">
				<button class="btn1"> <span class="glyphicon glyphicon-lock"></span>修改密码 </button>
				<button class="btn2"> <span class="glyphicon glyphicon-log-out"></span>安全退出 </button>
			</div>
		</div>
	</div>
	<!-- 根据用户是否登录显示相应的菜单 -->	
	<div class="w clearfix">
		<div class="menu fl">
			<div class="title">
				<span>主菜单</span>
			</div>
			<div class="list">
				<ul class="yiji">
				<%Login loginSession=(Login)session.getAttribute("loginSession");
				  if(loginSession==null){
				%>	
				<script type="text/javascript">
				top.location="${basePath}rest/page/toLogin"
				</script>  
				<%  }else{
					  int role=loginSession.getRole();
					  if(role==1){//超级管理员
						%>
						  <li>
						  	<a href="javascript:;" class="inactive"> 
						  		<span class="glyphicon glyphicon-fire"></span>员工信息管理
							</a>
							<ul style="display: none">
								<li><a href="${basePath }rest/stuff/toShowStuff"><span>O</span>员工信息总览</a></li>
								<li><a href="${basePath }rest/stuff/doShowStuffSalary"><span>O</span>员工工资总览</a></li>
							</ul>
							</li> 
					  <%}// end if
					 
				  }//end else
				%>
				<c:if test="${role==1}">
				<!-- 学生角色 -->
					<li><a href="javascript:;" class="inactive"> <span
							class="glyphicon glyphicon-fire"></span>信息查询
						</a>
						<ul style="display: none">
							<li><a href="${basePath }rest/studentAction!toUpdateStudent?id=${student.id}"><span>O</span>我的资料</a></li>
							<li><a href="${basePath }rest/studentControlAction!toShowMyArrangement"><span>O</span>我的课表</a></li>
							<li><a href="${basePath }rest/studentControlAction!toShowMyExamSchedule"><span>O</span>我的考试</a></li>
						</ul>
					</li>
					<%-- <li><a href="javascript:;" class="inactive"> <span
							class="glyphicon glyphicon-glass"></span>我的科研
					</a>
						<ul style="display: none">
							<li><a href="${basePath }rest/studentControlAction!toShowMyProject"><span>O</span>我的科研项目</a></li>
							<li><a href="${basePath }rest/studentControlAction!toShowMyAchievement"><span>O</span>成果展示</a></li>
						</ul>
					</li> --%>
					<li><a href="javascript:;" class="inactive"> <span
							class="glyphicon glyphicon-glass"></span>资料申请
					</a>
						<ul style="display: none">
							<li><a href="${basePath }rest/studentControlAction!toTrunMajor"><span>O</span>转专业申请</a></li>
							<li><a href="${basePath }rest/studentControlAction!toShowMyExamFile"><span>O</span>考试资料下载</a></li>
						</ul>
					</li>
					
				</c:if>
				
				<c:if test="${role==2 }">
					<!--普通教师权限  -->
					<c:if test="${teacher.type== '1' }">
					<li><a href="javascript:;" class="inactive"> <span
							class="glyphicon glyphicon-fire"></span>信息查询
					</a>
						<ul style="display: none">
							<li><a href="${basePath }rest/teacherAction!showTeacherDetail?id"=${teacher.id}><span>O</span>我的资料</a></li>
							
						</ul>
					</li>
					<li><a href="javascript:;" class="inactive"> <span
							class="glyphicon glyphicon-glass"></span>我的科研
					</a>
						<ul style="display: none">
							<li><a href="${basePath }rest/teacherControlAction!toShowProjectList"><span>O</span>我的科研项目</a></li>
							<li><a href="${basePath }rest/teacherControlAction!toShowAchievementList"><span>O</span>成果展示</a></li>
							<li><a href="${basePath }rest/project!showProjectMates"><span>O</span>我的团队成员</a></li>
						</ul>
					</li>
					<li><a href="javascript:;" class="inactive"> <span
							class="glyphicon glyphicon-glass"></span>教务办理
					</a>
						<ul style="display: none">
							<li><a href="${basePath }rest/teacherControlAction!toShowArrangement"><span>O</span>我的课表</a></li>
							<li><a href="${basePath }rest/teacherControlAction!toShowExamSchedule"><span>O</span>我的考试</a></li>
						</ul>
					</li>
					<li><a href="javascript:;" class="inactive"> <span
							class="glyphicon glyphicon-glass"></span>资料信息管理
					</a>
						<ul style="display: none">
							<li><a href="${basePath }rest/teacherControlAction!toShowExamFile"><span>O</span>考试资料信息上传</a></li>
						</ul>
					</li>
					</c:if>
					<c:if test="${teacher.type== '2' }">

					<li><a href="javascript:;" class="inactive"> <span
							class="glyphicon glyphicon-glass"></span>科研项目管理
						</a>
						<ul style="display: none">
							<li><a href="${basePath }rest/project!showProjects"><span>O</span>科研项目管理</a></li>
							<li><a href="${basePath }rest/achievement!showAchievement"><span>O</span>成果管理</a></li>
							<li><a href="${basePath }rest/project!showProjectMates"><span>O</span>团队成员管理</a></li>
							<li><a href="${basePath }rest/projectRecordAction!showProjectRecordList"><span>O</span>科研档案管理</a></li>
						</ul>
					</li>
					<li><a href="javascript:;" class="inactive"> <span
							class="glyphicon glyphicon-fire"></span>教务管理
						</a>
						<ul style="display: none">
							<li><a href="${basePath }rest/examScheduleAction!showExamScheduleList"><span>O</span>考试管理</a></li>
							<li><a href="javascript:;"><span>O</span>调停课管理</a></li>
							<li><a href="${basePath }rest/arrangementAction!toshowArrangement"><span>O</span>排课管理</a></li>
							<li><a href="${basePath }rest/majorAction!turnMajor"><span>O</span>转专业管理</a></li>
						</ul>
					</li>
					<li><a href="javascript:;" class="inactive"> <span
							class="glyphicon glyphicon-eye-open"></span>日常工作管理
						</a>
						<ul style="display: none">
							<li><a href="${basePath }rest/ConferenceAction!conferenceList"><span>O</span>会议管理</a></li>
							<li><a href="${basePath }rest/stampAction!toShowStampList"><span>O</span>印章管理</a></li>
							<li><a href="${basePath }rest/documentAction!doShowAllDocuments"><span>O</span>公文管理</a></li>
						</ul>
					</li>
					<li><a href="javascript:;" class="inactive"> <span
							class="glyphicon glyphicon-eye-open"></span>资料管理
						</a>
						<ul style="display: none">
							<li><a href="${basePath }rest/BooksAction!booksList"><span>O</span>图书资料管理管理</a></li>
							<li><a href="${basePath }rest/assetsAction!showAssetsList"><span>O</span>资产管理</a></li>
						</ul>
					</li>
					</c:if>
					<c:if test="${teacher.type== '3' }">
					<!-- 领导 -->
					<li><a href="javascript:;" class="inactive"> <span
							class="glyphicon glyphicon-fire"></span>信息查询
					</a>
						<ul style="display: none">
							<li><a href="${basePath }rest/teacherAction!showTeacherDetail?id"=${teacher.id}><span>O</span>我的资料</a></li>
						</ul>
					</li>
					<li><a href="javascript:;" class="inactive"> <span
							class="glyphicon glyphicon-eye-open"></span>工作审批
						</a>
						<ul style="display: none">
							<li><a href="${basePath }rest/teacherControlAction!toConfirmTurnMajor"><span>O</span>转专业审批</a></li>
							<li><a href="${basePath }rest/teacherControlAction!toConfirmLendStamp"><span>O</span>印章审批</a></li>
						</ul>
					</li>
					</c:if>
				</c:if>
				
				<c:if test="${role==3}">
					<li><a href="javascript:;" class="inactive"> <span
							class="glyphicon glyphicon-folder-open"></span>办公室老师信息管理
					</a>
						<ul style="display: none">
							<li><a href="${basePath }rest/teacherAction!toShowOfficeList"><span>O</span>办公室老师信息</a></li>
						</ul>
					</li>
					<li><a href="javascript:;" class="inactive"> <span
							class="glyphicon glyphicon-bold"></span>领导信息管理
					</a>
						<ul style="display: none">
							<li><a href="${basePath }rest/teacherAction!showLeaderList"><span>O</span>领导信息</a></li>
						</ul>
					</li>
					<li><a href="javascript:;" class="inactive"> <span
							class="glyphicon glyphicon-gift"></span>教师信息管理
					</a>
						<ul style="display: none">
							<li><a href="${basePath }rest/teacherAction!showAllTeachers"><span>O</span>教师信息</a></li>
						</ul>
					</li>
					<li><a href="javascript:;" class="inactive"> <span
							class="glyphicon glyphicon-info-sign"></span>学生信息管理
					</a>
						<ul style="display: none">
							<li><a href="${basePath }rest/studentAction!toShowStudentList"><span>O</span>学生信息</a></li>
						</ul>
					</li>
					<li><a href="javascript:;" class="inactive"> <span
							class="glyphicon glyphicon-picture	"></span>专业信息管理
					</a>
						<ul style="display: none">
							<li><a href="${basePath }rest/majorAction!showAllMajors"><span>O</span>专业信息</a></li>
						</ul>
					</li>
				</c:if>
				</ul>
			</div>
		</div>

	
	
	<div class="content fl">
		<div class="guide clearfix">
			<ul>
				<%-- <li data-url="${basePath }static/test/1.html">我的首页<span class="glyphicon glyphicon-remove"></span></li>
				<li data-url="${basePath }static/test/2.html" class="gactive">工程项目管理<span class="glyphicon glyphicon-remove"></span></li> --%>
			</ul>
		</div>
		<div class="frame">
			<%-- <iframe src="${basePath }static/test/1.html" frameborder="0" scrolling=true class="hide"></iframe>
			<iframe src="${basePath }static/test/2.html" frameborder="0" scrolling=true></iframe> --%>
		</div>
	</div>
</div>
	<script type="text/javascript" src="${basePath }static/bootstrap/js/jquery.min.js"></script>
	<script type="text/javascript" src="${basePath }static/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${basePath }static/layer/layer.js"></script>
	<script type="text/javascript" src="${basePath }static/web/js/index.js"></script>

</body>
</html>