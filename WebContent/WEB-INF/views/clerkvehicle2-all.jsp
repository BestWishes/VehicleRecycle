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
							height:505,
							singleSelect : true,
							pageList : [ 5, 10, 20 ],
							loadMsg : '信息加载中...',
							columns : [ [
									{
										field : 'id',
										title : 'ID',
										rowspan : 2,
										width : 80,
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
										width : 80,
										sortable : true
									}
							] ],
							///
							view:detailview,
						    detailFormatter:function(rowIndex,rowData){
						    	return '<div style="padding:2px"><table id="ddv-' + rowIndex + '"></table></div>';
						    },
						    
						    onExpandRow:function(rowIndex,rowData){
						    	$('#ddv-'+rowIndex).datagrid({ 
						    		url: '${pageContext.request.contextPath}/vehicleBasic/getvehicledetailsbyclient/'+rowData.id,
						    		//url:'${pageContext.request.contextPath}/vehicleDetail/getvehicleDetails',  
								    method:'get',
								    idField:'id',
								    pagination:true,
								    fitColumns:true,
								    cache:false,
								    height:200,
								    singleSelect:true,
								    pageList:[5,10,20],
									loadMsg:'信息加载中...',
								    columns:[[    
								              {field:'id',title:'ID',rowspan:2,width:80,sortable:true,hidden:true},    
									          {field:'clerk',title:'业务员',rowspan:2,width:80,
								            	 formatter:function(value,rowData,rowIndex){
								            		 if(rowData.vehicleBasic){
								            			 return rowData.vehicleBasic.clerk.employeeName;
								            		 }
								            		 else{
								            			 return value;
								            		 }
								            	 }
								         	  },
								         	  {field:'client',title:'客户',rowspan:2,width:80,
								            	 formatter:function(value,rowData,rowIndex){
								            		 if(rowData.vehicleBasic){
								            			 return rowData.vehicleBasic.client.name;
								            		 }
								            		 else{
								            			 return value;
								            		 }
								            	 }
								         	  },
								              {field:'vehicleName',title:'车辆名称',rowspan:2,width:80,
									            	 formatter:function(value,rowData,rowIndex){
									            		 if(rowData.vehicleBasic){
									            			 return rowData.vehicleBasic.vehicleName;
									            		 }
									            		 else{
									            			 return value;
									            		 }
									            	 }
									          }, 
									          {field:'vehicleType',title:'车辆型号',rowspan:2,width:80,
										            formatter:function(value,rowData,rowIndex){
										            		 if(rowData.vehicleBasic){
										            			 return rowData.vehicleBasic.vehicleType;
										            		 }
										            		 else{
										            			 return value;
										            		 }
										            	 }
										      },
										      {field:'vehicleLicense',title:'车牌号',rowspan:2,width:80,
											        formatter:function(value,rowData,rowIndex){
											        if(rowData.vehicleBasic){
											            return rowData.vehicleBasic.vehicleLicense;
											            }
											        else{
											        	return value;
											            }
											        }
											  },     
								              {field:'vehicleWeght',title:'汽车重量',rowspan:2,width:80}, 
								              {field:'transportation',title:'运输方式',rowspan:2,width:80,
								            	 formatter:function(value,rowData,rowIndex){
								            		 if(rowData.transportation){
								            			 return rowData.transportation.name;
								            		 }
								            		 else{
								            			 return value;
								            		 }
								            	 }
								              }, 
								              {field:'stoLocation',title:'存放位置',rowspan:2,width:80,
									            	 formatter:function(value,rowData,rowIndex){
									            		 if(rowData.stoLocation){
									            			 return rowData.stoLocation.storage.storageName+'|'+rowData.stoLocation.locName;
									            		 }
									            		 else{
									            			 return value;
									            		 }
									            	 }
									          },
									          {field:'stoDate',title:'入库日期',rowspan:2,width:80,
									            	 formatter:function(value,rowData,rowIndex){
									            		 if(rowData.stoDate){
									            			  var date=new Date(rowData.stoDate);
									            			  var y = date.getFullYear();
															  var m = date.getMonth()+1;
															  var d = date.getDate();
															  return y+'-'+m+'-'+d;
										            		  // return date.toLocaleDateString(); 
									            		  }
									            		  else{
									            			  return value;
									            		  }
									            	 }
									          }
								          ]] ,
									onLoadSuccess : function(data) {
										if(data.total==0)
										{
											$.messager.show({
												msg:'此业务无详细信息',
												title:'提示',
												timeout:2000
											});
										}
										
									}
						    	})
						    },
							///
							onLoadSuccess : function(data) {
								if(data.total==0)
									{
										$.messager.show({
											msg:'无此类数据',
											title:'提示',
											timeout:2000
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
					} else if (name == 'serverName') {
						$('#dg').datagrid('load', {
							serverName : value
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
</script>
</head>
<body>
	<div id="tdiv">
		<input id="dg-search"></input>
		<div id="mm" style="width: 120px">
			<div data-options="name:'id',iconCls:'icon-ok'">按ID查询</div>
						<div data-options="name:'serverName',iconCls:'icon-ok'">按客服姓名查询</div>
			
		</div>
	</div>
	<div>
		<table id="dg"></table>
		<div id="pp" style="background: #efefef; border: 1px solid #ccc;"></div>
	</div>
	<div id='outdl'></div>

</body>
</html>