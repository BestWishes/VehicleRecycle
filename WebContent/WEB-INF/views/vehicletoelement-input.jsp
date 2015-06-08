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
		<form id="vehicleToElementform" method="post">
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
					<td>汽车详单:</td>
					<td><input id='detailcombobox' class="easyui-combobox" type="text"
						name="vehicleDetail.id"
						data-options="
						url:'${pageContext.request.contextPath}/vehicleToElement/getvehicledetailbystate',
						required:true,
						method:'get',
						valueField:'id',  
					    editable:false,  
			    		textField:'id' ,
						missingMessage:'请选择汽车详细信息单',
						panelHeight:'200px'
						" />
					</td>
				</tr>
				<tr>
					<td>元件名称:</td>
					<td><input id='elementbox' class="easyui-combobox" type="text"
						name="vehicleElement.id"
						data-options="
						required:true,
						missingMessage:'请选择元件',
						url:'${pageContext.request.contextPath}/vehicleToElement/getallvehicleelement',
					    method:'get',
					    valueField:'id',  
					    editable:false,  
			    		textField:'eleName',
			    		panelHeight:'200px',
			    		onSelect:function(data){
			    			$('#etype').val(data.eleType);
			    		}
						" />
					</td>
				</tr>
				<tr>
					<td>型号:</td>
					<td><input id='etype' class="easyui-validatebox" type="text"
						name="eleType"
						data-options="" />
					</td>
				</tr>
				<tr>
					<td>品质:</td>
					<td><input class="easyui-validatebox" type="text"
						name="eleQuality"
						data-options="required:true,validType:'length[0,10]',missingMessage:'请填写品质'" />
					</td>
				</tr>
				<tr>
					<td>生产商:</td>
					<td><input class="easyui-validatebox" type="text"
						name="eleProducer"
						data-options="required:true,validType:'length[0,10]',missingMessage:'请填写生产商'" />
					</td>
				</tr>
				<tr>
					<td>生产日期:</td>
					<td><input id='' class="easyui-datebox" type="text"
						name="eleProDate"
						data-options="
						required:true,
						missingMessage:'请填写生产日期',
						formatter:function(date){
							var y = date.getFullYear();
							var m = date.getMonth()+1;
							var d = date.getDate();
							return y+'-'+m+'-'+d;
						}
						" />
					</td>
				</tr>
				<tr>
					<td>价格:</td>
					<td><input class="easyui-validatebox" type="text"
						name="elePrice"
						data-options="required:true,validType:'length[0,10]',missingMessage:'请填写价格'" />
					</td>
				</tr>
				<tr>
					<td>数量:</td>
					<td><input class="easyui-validatebox" type="text"
						name="elenumber"
						data-options="required:true,validType:'length[0,10]',missingMessage:'请填写数量'" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>