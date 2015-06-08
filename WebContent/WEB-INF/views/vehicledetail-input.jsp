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
		<form id="vehicleDetailform" method="post">
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
					<td>业务单:</td>
					<td><input id='vehicledetailcombobox' class="easyui-combobox" type="text"
						name="vehicleBasic.id"
						data-options="
						required:true,missingMessage:'请选择业务单',
						url:'${pageContext.request.contextPath}/vehicleDetail/getvehiclebasicbystate',
					    method:'get',
					    valueField:'id',  
					    editable:false,  
			    		textField:'id' ,
			    		panelHeight:'200px',
			    		onSelect:function(data){
							 if(data){
							 $('#clerk').val(data.clerk.employeeName);
							 $('#client').val(data.client.name);
							 $('#vehicleType').val(data.vehicleType);
							 $('#vehicleName').val(data.vehicleName);
							 $('#vehicleLicense').val(data.vehicleLicense);
							 }
							},
						" />
					</td>
				</tr>
				<tr>
					<td>业务员:</td>
					<td><input id='clerk' class="easyui-validatebox" type="text"
						name="vehicleBasic.clerk"
						data-options="" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<td>客户:</td>
					<td><input id='client' class="easyui-validatebox" type="text"
						name="vehicleBasic.client"
						data-options="" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<td>车辆名称:</td>
					<td><input id='vehicleName' class="easyui-validatebox" type="text"
						name="vehicleBasic.vehicleName" disabled="disabled"
						data-options="" />
					</td>
				</tr>
				<tr>
					<td>车辆型号:</td>
					<td><input id='vehicleType' class="easyui-validatebox" type="text"
						name="vehicleBasic.vehicleType" disabled="disabled"
						data-options="" />
					</td>
				</tr>
				<tr>
					<td>车牌号:</td>
					<td><input id='vehicleLicense' class="easyui-validatebox" type="text"
						name="vehicleBasic.vehicleLicense" disabled="disabled"
						data-options="" />
					</td>
				</tr>
				<tr>
					<td>汽车重量:</td>
					<td><input class="easyui-validatebox" type="text"
						name="vehicleWeght"
						data-options="required:true,validType:'length[0,20]',missingMessage:'请输入汽车重量'" />
					</td>
				</tr>
				<tr>
					<td>运输方式:</td>
					<td><input id='transcombobox' class="easyui-combobox" type="text"
						name="transportation.id"
						data-options="
						required:true,
						missingMessage:'请选择运输方式',
					    url:'${pageContext.request.contextPath}/vehicleDetail/getalltransportation',
					    method:'get',
					    valueField:'id',  
					    editable:false,  
			    		textField:'name' ,
			    		panelHeight:'200px'
						" />
					</td>
				</tr>
				<tr>
					<td>存放位置:</td>
					<td><input id='locationcombobox' class="easyui-combobox" type="text"
						name="stoLocation.id"
						data-options="
						required:true,
						missingMessage:'请选择存放位置',
					    url:'${pageContext.request.contextPath}/vehicleDetail/getemptystolocation',
					    method:'get',
					    valueField:'id',  
					    editable:false,  
			    		textField:'locName' ,
			    		panelHeight:'200px'
						" />
					</td>
				</tr>
				<tr>
					<td>入库日期:</td>
					<td><input id='stoDate' class="easyui-datebox" type="text"
						name="stoDate"
						data-options="
						required:true,
						missingMessage:'请选择入库日期',
						formatter:function(date){
							var y = date.getFullYear();
							var m = date.getMonth()+1;
							var d = date.getDate();
							return y+'-'+m+'-'+d;
						}
						"
						 />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>