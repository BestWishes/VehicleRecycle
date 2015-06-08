<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common.jsp"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>


<script type="text/javascript">
</script>
</head>
<body>
	<div style="padding: 10px 60px 20px 60px">
		<form id="employeeform" method="post">
			<table>
				<c:if test="${id!=null }">
					<tr>
						<td>ID:</td>
						<td><input class="easyui-validatebox" type="text"
							name="id" disabled="disabled"/>
						</td>
					</tr>
				</c:if>
				<tr>
					<td>姓名:</td>
					<td><input class="easyui-validatebox" type="text"
						name="employeeName"
						data-options="required:true,validType:'length[0,10]',missingMessage:'请填写员工姓名'" />
					</td>
				</tr>
				<tr>
					<td>密码:</td>
					<td><input class="easyui-validatebox" type="password"
						name="password"
						data-options="required:true,missingMessage:'请填写登录密码'" /></td>
				</tr>
				<tr>
					<td>邮箱:</td>
					<td><input class="easyui-validatebox" type="text" name="email"
						data-options="required:true,validType:'email',missingMessage:'请填写邮箱地址'" /></td>
				</tr>
				<tr>
					<td>身份证:</td>
					<td><input class="easyui-validatebox" type="text"
						name="idCard"
						data-options="required:true,validType:['length[18,18]'],missingMessage:'请填写员工身份证号码'" />
					</td>
				</tr>
				<tr>
					<td>性别:</td>
					<td><input id='sexcombobox' class="easyui-combobox"
						type="text" name="sex"
						data-options="
					        required:true,
					        missingMessage:'请选择性别',
					        data:[{sex:'1',sexName:'男'},{sex:'0',sexName:'女'}],
					        valueField:'sex',    
			    			textField:'sexName' ,
			    			editable:false,
			    			panelHeight:'200px'
				        " />
					</td>
				</tr>
				<tr>
					<td>职位:</td>
					<td><input id='positioncombobox' class="easyui-combobox"
						type="text" name="position.id"
						data-options="
					        required:true,
					        missingMessage:'请选择职位',
					        url:'${pageContext.request.contextPath}/employee/getallposition',
					        method:'get',
					        valueField:'id',  
					        editable:false,  
			    			textField:'positionName' ,
			    			panelHeight:'200px'
					        " />
					</td>
				</tr>
				<tr>
					<td>入职日期:</td>
					<td><input class="easyui-datebox" type="text" name="birthday"
						data-options="
						required:true,
						missingMessage:'请选择入职日期',
						formatter:function(date){
							var y = date.getFullYear();
							var m = date.getMonth()+1;
							var d = date.getDate();
							return y+'-'+m+'-'+d;
						}
						"/></td>
				</tr>
				<tr>
					<td>电话:</td>
					<td><input class="easyui-numberbox" type="text" name="tel"
						data-options="required:true,missingMessage:'请填写联系电话',validType:['length[0,255]']" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>