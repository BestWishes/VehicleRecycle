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
		<form id="clientform" method="post">
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
						name="name"
						data-options="required:true,validType:'length[0,10]',missingMessage:'请填写用户名称'" />
					</td>
				</tr>
				<tr>
					<td>性别:</td>
					<td><input class="easyui-combobox" type="text"
						name="sex"
						data-options="
						required:true,
						valueField:'text',  
					    editable:false, 
						missingMessage:'请选择用户性别',
						data:[{text:'男'},{text:'女'}],
						panelHeight:'200px'
						" />
					</td>
				</tr>
				<tr>
					<td>身份证:</td>
					<td><input class="easyui-validatebox" type="text"
						name="idCard"
						data-options="required:true,validType:'length[18,18]',missingMessage:'请填写用户身份证号码'" />
					</td>
				</tr>
				<tr>
					<td>当前职业:</td>
					<td><input class="easyui-validatebox" type="text"
						name="job"
						data-options="required:true,validType:'length[0,10]',missingMessage:'请填写用户名称'" />
					</td>
				</tr>
				<tr>
					<td>所在单位:</td>
					<td><input class="easyui-validatebox" type="text"
						name="company"
						data-options="required:true,validType:'length[0,10]',missingMessage:'请填写用户名称'" />
					</td>
				</tr>
				<tr>
					<td>当前地址:</td>
					<td><input class="easyui-validatebox" type="text"
						name="address"
						data-options="required:true,validType:'length[0,10]',missingMessage:'请填写用户名称'" />
					</td>
				</tr>
				<tr>
					<td>联系电话:</td>
					<td><input class="easyui-validatebox" type="text"
						name="tel"
						data-options="required:true,disabled:'true'" />
					</td>
				</tr>
				<tr>
					<td>邮箱:</td>
					<td><input class="easyui-validatebox" type="text"
						name="email"
						data-options="required:true,disabled:'true'" />
					</td>
				</tr>
				<tr>
					<td>客户类型:</td>
					<td><input id='clientTypeCombobox' class="easyui-combobox" type="text"
						name="clientType.id"
						data-options="
						required:true,
						missingMessage:'请填写用户名称',
					    url:'${pageContext.request.contextPath}/client/getallclienttype',
					    method:'get',
					    valueField:'id',  
					    editable:false,  
			    		textField:'typeName' ,
			    		panelHeight:'200px'
						"
						 />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>