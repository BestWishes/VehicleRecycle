<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta content="charset=UTF-8">

<title>汽车资源回收管理系统</title>

<script type="text/javascript">
	// function submitForm(){
	//		$('#ff').form('submit');
	//	}
	//	function clearForm(){
	//		$('#ff').form('clear');
	//	}

	function submitForm() {
		$.messager.progress();
		$('#ff').form('submit',{
							url : '${pageContext.request.contextPath}/employee/login',
							onSubmit : function() {
								var isValid = $(this).form('validate');
								var employeeName = $('#employeeName').val();
								var password = $('#password').val();

								if (!isValid) {
									$.messager.progress('close');// hide progress bar while the form is invalid
								}
								return isValid;// return false will stop the form submission

							},
							success : function(resultdata) {
								$.messager.progress('close');// 隐藏进度条
								var data=JSON.parse(resultdata).employee;
								if (data.id == -1) {
									$.messager.show({
										title:'提醒：',
										msg:'用户名或密码无效',
										timeout:3000,
									});
									$('#ff').form('clear');
									//$.messager.alert("提示",'用户名或密码无效',"info");
								} else {
									window.location.href = "${pageContext.request.contextPath}/employee/welcome";
								}
							} 
						});
	}
	function clearForm() {
		$('#ff').form('clear');
	}
	$('#submitpanel').panel({
		  width:500,    
		  height:450,  
		  left:200,    
		  top:200 
	})
	
</script>



<style scoped="scoped">
.textbox {
	height: 20px;
	margin: 0;
	padding: 0 2px;
	box-sizing: content-box;
}
</style>
</head>
<body>
	<h2 style="text-align: center;">
		<c:if test="${employeeName==null }">
			<h1>${employeeName }</h1>
		</c:if>
		
		<h1 style="text-align: center;">汽车资源回收管理系统</h1>
		

		<div style="padding:10px 60px 20px 60px">
		
		<form id="ff" method="get" action="${pageContext.request.contextPath}/employee/login">
			<table cellpadding="5" align="center">
				<tr>
					<td>账    号:</td>
					<td><input class="easyui-validatebox textbox"
						id="employeeName" type="text" name="employeeName"
						data-options="required:true,validType:['length[0,20]']," ></input></td>
				</tr>
				<tr>
					<td>密       码:</td>
					<td><input class="easyui-validatebox textbox" id="password"
						type="password" name="password" data-options="required:true,validType:['length[0,20]'],"></input></td>
				</tr>
			</table>
			<div style="text-align: center; padding: 5px">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="submitForm()">-登录-</a> <a href="javascript:void(0)"
					class="easyui-linkbutton" onclick="clearForm()">-清除-</a>
			</div>
		</form>

</div>

	</h2>

</body>
</html>