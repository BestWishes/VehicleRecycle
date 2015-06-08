<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common.jsp"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
	$(function() {
		$('#dg').datagrid({
			url : '${pageContext.request.contextPath}/vehicleBasic/getvehicleBasics',
			method : 'get',
			idField : 'id',
			pagination : true,
			cache : false,
			fitColumns:true,
			height:500,
			singleSelect : true,
			toolbar : [ {
				text : '添加',
				iconCls : 'icon-add',
				handler : function() {
					$('#outdl').dialog({
						href : '${pageContext.request.contextPath}/vehicleBasic/add',
						method : 'get',
						title : '新增业务单:',
						onLoad:function(){
							/* 设置新增业务单初始状态：已预约*/
							$('#vehicleStateCombobox').combobox({
							isabled:true,
							onLoadSuccess:function(data){
							$('#vehicleStateCombobox').combobox('setValue','已预约'); //设置form表单里面combobox的初始值
							}
							});
							/* 设置新增业务单客服：执行当前操作的用户*/clerkcombobox
							$('#servercombobox').combobox({
								disabled:true,
								onLoadSuccess:function(data){
									$("#servercombobox").combobox('select',data[0].id);															//$('#servercombobox').combobox('setValue',''); //设置form表单里面combobox的初始值
									}
								});
									},
									cache : false,
									width : 440,
									height : 440,
									modal : true,
									collapsible : false,
									minimizable : false,
									maximizable : false,
									resizable : true,
									shadow : true,
									inline : false,
									buttons : [{
										text : '保存',
										iconCls : 'icon-save',
										handler : function() {
											$('#vehicleBasicform').form('submit',{
												url : '${pageContext.request.contextPath}/vehicleBasic/save',
												method : 'post',
												onSubmit : function(submitdata) {
													var isValid = $(this).form('validate');
													return isValid; // 返回false终止表单提交
													},
												success : function(data) {
													$.messager.confirm('新增成功','是否继续添加？',function(sure) {
														if (sure){
															$('#outdl').dialog('refresh');
															} 
														else{
															$('#outdl').dialog('close');
															$('#dg').datagrid('reload');
															}
														})
													}
												});
											}
										},
										{
																	text : '清空',
																	iconCls : 'icon-reload',
																	handler : function() {
																		$('#vehicleBasicform').form('clear');
																	}
																},
																{
																	text : '取消',
																	iconCls : 'icon-cancel',
																	handler : function() {
																		$('#outdl').dialog('close');
																		$('#dg').datagrid('reload');
																	}
																} ]
													});
								}
							} ],
							pageList : [ 5, 10, 20 ],
							loadMsg : '信息加载中...',
							columns : [ [
									{
										field : 'id',
										title : 'ID',
										rowspan : 2,
										width : 60,
										sortable : true
									},
									{
										field : 'serverName',
										title : '客服',
										rowspan : 2,
										width : 80,
										sortable : true,
										formatter : function(value, row, index) {
											if (row.server) {
												return row.server.employeeName;
											} else {
												return value;
											}
										}
									},
									{
										field : 'clerkName',
										title : '业务员',
										rowspan : 2,
										width : 80,
										sortable : true,
										formatter : function(value, row, index) {
											if (row.clerk) {
												return row.clerk.employeeName;
											} else {
												return value;
											}
										}
									},
									{
										field : 'vehicleName',
										title : '车辆名称',
										rowspan : 2,
										width : 80,
										sortable : true
									},
									{
										field : 'vehicleType',
										title : '车辆型号',
										rowspan : 2,
										width : 80,
										sortable : true
									},
									{
										field : 'vehicleLicense',
										title : '车牌号',
										rowspan : 2,
										width : 80,
										sortable : true
									},
									{
										field : 'client',
										title : '客户',
										rowspan : 2,
										width : 80,
										formatter:function(value,rowData,rowIndex){
											if(rowData.client){
											return rowData.client.name;
											}
											else{
												value;
											}
										}
									},
									{
										field : 'contactNumber',
										title : '联系电话',
										rowspan : 2,
										width : 80,
										formatter:function(value,rowData,rowIndex){
											if(rowData.client){
											return rowData.client.tel;
											}
											else{
												value;
											}
										}
									},
									{
										field : 'contactAddress',
										title : '地址',
										rowspan : 2,
										width : 160,
										formatter:function(value,rowData,rowIndex){
											if(rowData.client){
											return rowData.client.address;
											}
											else{
												value;
											}
										}
									},
									{
										field : 'contactDate',
										title : '联系日期',
										rowspan : 2,
										width : 80,
										sortable : true,
										formatter : function(value, row, index) {
											if (row.contactDate) {
												var date = new Date(
														row.contactDate);
												var y = date.getFullYear();
												var m = date.getMonth() + 1;
												var d = date.getDate();
												return y + '-' + m + '-' + d;
												//return date.toLocaleDateString(); 
											} else {
												return value;
											}
										}
									},
									{
										field : 'state',
										title : '状态',
										rowspan : 2,
										width : 60,
										sortable : true
									},
									{
										field : 'edit',
										title : '编辑',
										rowspan : 2,
										width : 120,
										sortable : true,
										formatter : function(value, row, index) {
											var e = '<a href="#" class="aedit" onclick="dgedit(\''
													+ row.id + '\')"></a> ';
											var d = '<a href="#"  class="adelete" onclick="dgdelete(\''
													+ row.id + '\')"></a> ';
											return e + d;
										}
									}

							] ],
							onLoadSuccess : function(data) {
								$('.aedit').linkbutton({
									text : '分派',
									plain : true,
									iconCls : 'icon-edit'
								});
								$('.adelete').linkbutton({
									text : '取消',
									plain : true,
									iconCls : 'icon-cut'
								});
								if(data.total==0)
								{
									$.messager.show({
										msg:'当前无预约业务',
										title:'提示',
										timeout:3000
									});
								}
							}
						});
		$('#dg-search').searchbox({
			searcher : function(value, name) {
				if (value != null & value != "") {
					if (name == 'id') {
						$('#dg').datagrid('load', {
							id : value
						})
					} 					
				} else {
					$('#dg').datagrid('load', {})
				}
			},
			menu : '#mm',
			prompt : '请输入查询条件',

		});
	})

	function dgedit(rowid) {
		$('#outdl').dialog({
			href : '${pageContext.request.contextPath}/vehicleBasic/edit/'+ rowid,
			method : 'get',
			title : '分派业务单:',
			cache : false,
			width : 440,
			height : 440,
			modal : true,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			resizable : true,
			shadow : true,
			inline : false,
			buttons : [
				{
				text : '保存',
				iconCls : 'icon-save',
				handler : function() {
				$('#vehicleBasicform').form('submit',{
					onSubmit : function(submitdata) {
						var isValid = $(this).form('validate');
						return isValid; // 返回false终止表单提交
						},
					url : '${pageContext.request.contextPath}/vehicleBasic/save',
					method : 'post',
					success : function() {
						$.messager.alert('提示','分派成功','info');
						$('#outdl').dialog('close');
						$('#dg').datagrid('reload');
						}
						});
						}
					},
									{
										text : '清空',
										iconCls : 'icon-reload',
										handler : function() {
											$('#vehicleBasicform')
													.form('clear');
										}
									}, {
										text : '取消',
										iconCls : 'icon-cancel',
										handler : function() {
											$('#outdl').dialog('close');
										}
									} ],
							onLoad : function() { //弹窗加载完成后执行
								$('#vehicleBasicform').form({ //设置弹窗里面form表单
													 onLoadSuccess : function(vehicleBasicdata) { //form表单加载完成后  参数:employeedata是加载的数据。
														 $('#vehicleStateCombobox').combobox({
																disabled:true,
																onLoadSuccess:function(data){
																	$('#vehicleStateCombobox').combobox('setValue','进行中'); //设置form表单里面combobox的初始值
																}
															});
															/* 设置新增业务单客服：执行当前操作的用户*/
														 $('#servercombobox').combobox({
																disabled:true,
																onLoadSuccess:function(data){
																	 $('#servercombobox').combobox('setValue',vehicleBasicdata.server.id); //设置form表单里面combobox的初始值
																}
															});
														 
														 $('#clientCombobox').combobox({
																onLoadSuccess:function(data){
																	 $('#clientCombobox').combobox('setValue',vehicleBasicdata.client.id); //设置form表单里面combobox的初始值
																	 $('#tel').val(vehicleBasicdata.client.tel);
																}
															});
															
														 $('#clerkcombobox').combobox('setValue',vehicleBasicdata.clerk.id); //设置form表单里面combobox的初始值
														
													}
												});
								$('#vehicleBasicform').form('load','${pageContext.request.contextPath}/vehicleBasic/getedit/'+ rowid);
							}
						});
	}

	function dgdelete(rowid) { //删除操作  

		$.messager.confirm('确认','id=' + rowid + '\r确认取消?',function(sure) {
							if (sure) {var selectedRow = $('#dg').datagrid('getSelected'); //获取选中行  
								$.ajax({
											cache : false,
											url : '${pageContext.request.contextPath}/vehicleBasic/delete/'+ rowid,
											method : 'post',
											success : function() {
												$.messager.show({
													title : '提示',
													msg : 'id=' + rowid
															+ '\r删除成功',
													timeout : 1000,
												});
												$('#dg').datagrid('reload');
											}
										});
							}
						})
	}
</script>
</head>
<body>
	<div id="tdiv">
		<input id="dg-search"></input>
		<div id="mm" style="width: 120px">
			<div data-options="name:'id',iconCls:'icon-ok'">按ID查询</div>
		</div>
	</div>
	<div>
		<table id="dg"></table>
		<div id="pp" style="background: #efefef; border: 1px solid #ccc;"></div>
	</div>
	<div id='outdl'></div>

</body>
</html>