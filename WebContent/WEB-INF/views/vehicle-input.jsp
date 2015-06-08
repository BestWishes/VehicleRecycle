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

$(function(){
	$('#contactDate').datebox().datebox('calendar').calendar({
		validator: function(date){
			var now = new Date();
			var d1 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
			var d2 = new Date(now.getFullYear(), now.getMonth(), now.getDate()+10);
			return d1<=date && date<=d2;
		}
	});
});
</script>
</head>
<body>
	<div style="padding: 10px 60px 20px 60px">
		<form id="vehicleBasicform" method="post">
			<table>
				<c:if test="${id!=null }">
					<tr>
						<td>ID:</td>
						<td><input class="easyui-validatebox" type="text"
							name="id" 
							disabled="disabled"/>
						</td>
					</tr>
				</c:if>
					<tr>
					<td>客服:</td>
					<td><input id='servercombobox' class="easyui-combobox"
						type="text" name="server.id"  data-options="
					        required:true,
					        url:'${pageContext.request.contextPath}/vehicleBasic/getallserver',
					        method:'get',
					        valueField:'id', 
					        editable:false, 
			    			textField:'employeeName' ,
			    			panelHeight:'200px'
			    			
					        " 
						/>
					</td>
				</tr>
				<tr>
					<td>业务员:</td>
					<td><input id='clerkcombobox' class="easyui-combobox"
						type="text" name="clerk.id"  
						data-options="
					        required:true,
					        missingMessage:'请分派业务员',
					        url:'${pageContext.request.contextPath}/vehicleBasic/getallclerk',
					        method:'get',
					        valueField:'id', 
					        editable:false, 
			    			textField:'employeeName' ,
			    			panelHeight:'200px'
					        " />
					</td>
				</tr>
				<tr>
					<td>联系日期:</td>
					<td><input id='contactDate' class="easyui-datebox" type="text" name="contactDate"
						data-options="
						required:true,
						missingMessage:'请选择联系日期|日期范围今日起，10天以内',
						formatter:function(date){
							var y = date.getFullYear();
							var m = date.getMonth()+1;
							var d = date.getDate();
							return y+'-'+m+'-'+d;
						}
						"/></td>
				</tr>
				<tr>
					<td>车辆名称:</td>
					<td><input class="easyui-validatebox" type="text"
						name="vehicleName"
					    data-options="
					    required:true,
					    validType:['length[0,20]'],
					    missingMessage:'请填写车辆名称'
					    "/>
					</td>
				<tr>
				<tr>
					<td>车辆型号:</td>
					<td><input class="easyui-validatebox" type="text"
						name="vehicleType"
					    data-options="
					    required:true,
					    missingMessage:'请填写车辆型号',
					    validType:['length[0,20]']
					    "/>
					</td>
				<tr>
				<tr>
					<td>车牌号:</td>
					<td><input class="easyui-validatebox" type="text"
						name="vehicleLicense"
					    data-options="
					    required:true,
					    missingMessage:'请填写车牌号',
					    validType:['length[0,20]']
					    "/>
					</td>
				<tr>
				<tr>
					<td>客户:</td>
					<td><input id='clientCombobox' class="easyui-combobox" name="client.id" 
					 data-options="
							url: '${pageContext.request.contextPath}/vehicleBasic/getallclient',
							method: 'get',
							valueField:'id',
							textField:'name',
							onSelect:function(data){
							 if(data){
							 $('#tel').val(data.tel);
							 }
							},
							panelHeight:'200px'
						">
					</td>
				<tr>
				<tr>
					<td>联系电话:</td>
					<td><input id='tel' class="easyui-textbox" type="text"
						name="contactNumber" disabled="disabled"
					   />
					</td>
				<tr>				
				<tr>
					<td>业务状态:</td>
					<td><input id="vehicleStateCombobox" class="easyui-combobox" type="text"
						name="state"
					    data-options="
					    data:[{state:'已预约'},{state:'进行中'},{state:'已完成'},{state:'已撤单'}],
					    required:true,
					    missingMessage:'请选择业务状态',
					    valueField:'state',  
					    editable:false,
			    		textField:'state' ,
			    		panelHeight:'200px'
					    "/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>