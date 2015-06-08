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
		<form id="positionform" method="post">
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
					<td>职位:</td>
					<td><input class="easyui-validatebox" type="text"
						name="positionName"
						data-options="required:true,validType:'length[0,10]',missingMessage:'请填写职位名称'" />
					</td>
				</tr>
				<tr>
					<td>部门:</td>
					<td><input id='departmentcombobox' class="easyui-combobox"
						type="text" name="department.id"
						data-options="
					        required:true,
					        missingMessage:'请选择部门',
					        url:'${pageContext.request.contextPath}/position/getalldepartment',
					        method:'get',
					        valueField:'id',  
					        editable:false,  
			    			textField:'deptName' ,
			    			panelHeight:'200px'
				        " />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>