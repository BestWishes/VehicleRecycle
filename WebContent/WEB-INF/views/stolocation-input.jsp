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
		<form id="stoLocationform" method="post">
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
					<td>库位名称:</td>
					<td><input class="easyui-validatebox" type="text"
						name="locName"
						data-options="required:true,validType:'length[0,10]',missingMessage:'请填写部门名称'" />
					</td>
				</tr>
				<tr>
					<td>所属仓库:</td>
					<td><input id='storageCombobox' class="easyui-combobox" type="text"
						name="storage.id"
						data-options="
						url:'${pageContext.request.contextPath}/stoLocation/getallstorages',
						method:'get',
						valueField:'id',
						textField:'storageName',
						required:true,
						missingMessage:'请选择所属仓库',
						panelHeight:'200px',
						editable:false
						" />
					</td>
				</tr>
								<tr>
					<td>是否空闲:</td>
					<td><input id='isEmptyCombobox' class="easyui-combobox" type="text"
						name="isEmpty"
						data-options="
						data:[{text:'是'},{text:'否'}],
						valueField:'text',
						panelHeight:'200px',
						editable:false
						" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>