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
		<form id="vehicleElementform" method="post">
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
					<td>元件名称:</td>
					<td><input class="easyui-validatebox" type="text"
						name="eleName"
						data-options="required:true,validType:'length[0,20]',missingMessage:'请填写元件名称'" />
					</td>
				</tr>
				<tr>
					<td>元件型号:</td>
					<td><input class="easyui-validatebox" type="text"
						name="eleType"
						data-options="required:true,validType:'length[0,20]',missingMessage:'请填写元件型号'" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>