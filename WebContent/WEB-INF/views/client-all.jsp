<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ include file="/common.jsp" %> 
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		<script type="text/javascript">
			$(function() {
				$('#dg').datagrid({    
				    url:'${pageContext.request.contextPath}/client/getclients',  
				    method:'get',
				    idField:'id',
				    pagination:true,
				    fitColumns:true,
				    cache:false,
				    height:505,
				    singleSelect:true,
				    toolbar:[{
				    	text: '添加', iconCls: 'icon-add', handler: function () {	
							$('#outdl').dialog({
								href:'${pageContext.request.contextPath}/client/add',
								method:'get',
								title:'新增客户信息:',
								cache:false,
								width:400,    
								height:400,    
								modal:true ,
								collapsible:false,
								minimizable:false,
								maximizable:false,
								resizable:true,
								shadow:true,
								inline:false,
								buttons:[{
									text:'保存',
									iconCls:'icon-save',
									handler:function(){
										$('#clientform').form('submit',{
											url:'${pageContext.request.contextPath}/client/save',
											method:'post',
											onSubmit: function(submitdata){
												var isValid = $(this).form('validate');
												if (isValid){
													}
												return isValid;	// 返回false终止表单提交
											},
											success: function(data){
												$.messager.confirm('新增成功', '是否继续添加？', function(sure){
													if(sure){
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
									},{
										text:'清空',
										iconCls:'icon-reload',
										handler:function(){
											$('#departmentform').form('clear');
										}
									},{
										text:'取消',
										iconCls:'icon-cancel',
										handler:function(){
											$('#outdl').dialog('close');
											$('#dg').datagrid('reload'); 
										}
									}] 
								});
				            }
				    }],
				    pageList:[5,10,20],
					loadMsg:'信息加载中...',
				    columns:[[    
				              {field:'id',title:'ID',rowspan:2,width:80,sortable:true,hidden:true},    
				              {field:'name',title:'姓名',rowspan:2,width:40},  
				              {field:'sex',title:'性别',rowspan:2,width:40},      				             
				              {field:'idCard',title:'身份证',rowspan:2,width:120},      				             
				              {field:'tel',title:'联系电话',rowspan:2,width:80}, 
				              {field:'email',title:'邮箱',rowspan:2,width:80}, 
				              {field:'clientType',title:'客户类型',rowspan:2,width:80,
				            	 formatter:function(value,rowData,rowIndex){
				            		 if(rowData.clientType){
				            			 return rowData.clientType.typeName;
				            		 }
				            		 else{
				            			 return value;
				            		 }
				            	 }
				              }, 
				              {field:'job',title:'当前职业',rowspan:2,width:80}, 
				              {field:'company',title:'所属单位',rowspan:2,width:80}, 
				              {field:'address',title:'居住地址',rowspan:2,width:80}, 
				              {field:'edit',title:'编辑',rowspan:2,width:120,
				            	  formatter:function(value,row,index){
				            		  var e = '<a href="#" class="aedit" onclick="dgedit(\''+ row.id + '\')"></a> ';  
				                      var d = '<a href="#"  class="adelete" onclick="dgdelete(\''+ row.id +'\')"></a> '; 
				            		  return e+d;
				            	  }
				              }     				             

				          ]] ,
				    view:detailview,
				    detailFormatter:function(rowIndex,rowData){
				    	return '<div style="padding:2px"><table id="ddv-' + rowIndex + '"></table></div>';
				    },
				    
				    onExpandRow:function(rowIndex,rowData){
				    	$('#ddv-'+rowIndex).datagrid({ 
				    		url: '${pageContext.request.contextPath}/client/getallvehicles/'+rowData.id,
							method : 'get',
				    		idField : 'id',
							cache : false,
							fitColumns:true,
							singleSelect : true,
							loadMsg : '信息加载中...',
							columns : [ [
									{
										field : 'id',
										title : '业务单ID',
										rowspan : 2,
										width : 80,
										sortable : true
									},
									{
										field : 'vehicleName',
										title : '车辆名称',
										rowspan : 2,
										width : 80,
										sortable : true
									},{
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
										field : 'state',
										title : '业务状态',
										rowspan : 2,
										width : 80,
										sortable : true
									}
							] ],
							onLoadSuccess : function(data) {
								if(data.total==0)
								{
									$.messager.show({
										msg:'此用户当前无业务',
										title:'提示',
										timeout:2000
									});
								}
								
							}
				    	})
				    },
				    onLoadSuccess:function(){
				         $('.aedit').linkbutton({text:'修改',plain:true,iconCls:'icon-edit'});
				         $('.adelete').linkbutton({text:'删除',plain:true,iconCls:'icon-cut'});
				    }
				});
				$('#dg-search').searchbox({
					searcher:function(value,name){
						if(value!=null&value!=""){
							 if(name=='clientName'){
								$('#dg').datagrid('load',{
									name:value
								})
							}						
						}else{
							$('#dg').datagrid('load',{
							})
						}
					},
					menu:'#mm',
					prompt:'请输入查询条件',

				});
			})
					
		function dgedit(rowid){
				$('#outdl').dialog({
					href:'${pageContext.request.contextPath}/client/edit/'+rowid,
					method:'get',
					title:'修改客户信息:',
					cache:false,
					width:380,    
					height:420,    
					modal:true ,
					collapsible:false,
					minimizable:false,
					maximizable:false,
					resizable:true,
					shadow:true,
					inline:false,
					buttons:[{
						text:'保存',
						iconCls:'icon-save',
						handler:function(){
							$('#clientform').form('submit',{
								url:'${pageContext.request.contextPath}/client/save',
								method:'post',
								success: function(){
									$.messager.show({
										title:'提示',
										msg:'id='+rowid+'\n\r修改成功',
										timeout:1000
									});
									$('#outdl').dialog('close');
		                        	$('#dg').datagrid('reload'); 
								}
							});
						}
					},{
						text:'清空',
						iconCls:'icon-reload',
						handler:function(){
							$('#clientform').form('clear');
						}
					},{
						text:'取消',
						iconCls:'icon-cancel',
						handler:function(){
							$('#outdl').dialog('close');
						}
					}],
					
					onLoad:function(){     //弹窗加载完成后执行
						$('#clientform').form({
							onLoadSuccess:function(data){
								$('#clientTypeCombobox').combobox('setValue',data.clientType.id);
							}
						})
						$('#clientform').form('load','${pageContext.request.contextPath}/client/getedit/'+rowid);
					}
				});
			}
			
			 function dgdelete(rowid){  //删除操作  
				 
		            $.messager.confirm('确认','id='+rowid+'\r确认删除?',function(sure){  
		                if(sure){  
		                    var selectedRow = $('#dg').datagrid('getSelected');  //获取选中行  
		                    $.ajax({  
		                    	cache:false,
		                        url:'${pageContext.request.contextPath}/client/delete/'+rowid,  
		                        method:'post',
		                        success:function(){
		                        	$.messager.show({
		                        	title:'提示',
		                        	msg:'id='+rowid+'\r删除成功',
		                        	timeout:1000,
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
			<div id="mm" style="width:120px"> 
				<div data-options="name:'clientName',iconCls:'icon-ok'">按客户姓名查询</div> 
			</div>
		</div>
		<div>
			<table id="dg"></table>  	
			<div id="pp" style="background:#efefef;border:1px solid #ccc;"></div> 
		</div>
		<div id='outdl'>
		</div>

	</body>
</html>