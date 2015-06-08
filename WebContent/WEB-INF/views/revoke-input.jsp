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
		<form id="revokeBillform" method="post">
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
					<td>业务单ID:</td>
					<td><input id='vehicleBasiccombobox' class="easyui-combobox"
						type="text" name="vehicleBasic.id"
						data-options="
					        required:true,
					        missingMessage:'请选择业务单',
					        url:'${pageContext.request.contextPath}/revokeBill/getallvehicleBasics',
					        method:'get',
					        valueField:'id',  
					        editable:false,  
			    			textField:'id' ,
			    			panelHeight:'200px'
					        " />
					</td>
				</tr>
				<tr>
					<td>撤单原因:</td>
					<td><input class="easyui-validatebox" type="text"
						name="revokeReason"
						data-options="multiline:true,required:true,validType:'length[0,254]',missingMessage:'请填写撤单原因'" />
					</td>
				</tr>
				<tr>
					<td>撤单日期:</td>
					<td><input class="easyui-datebox" id='revokedate'
						type="text" name="revokeDate" 
						data-options="
						formatter:function(date){
							var y = date.getFullYear();
							var m = date.getMonth()+1;
							var d = date.getDate();
							return y+'-'+m+'-'+d;
						}
						"
						value=new Date()/>
					</td>
				</tr>

			</table>
		</form>
	</div>
</body>
</html>