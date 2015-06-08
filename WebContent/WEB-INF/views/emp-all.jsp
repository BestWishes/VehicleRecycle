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
				    url:'${pageContext.request.contextPath}/employee/getemployees',  
				    method:'get',
				    idField:'id',
				    pagination:true,
				    cache:false,
				    height:505,
				    fitColumns:true,
				    singleSelect:true,
				    toolbar:[{
				    	  text: '添加', iconCls: 'icon-add', handler: function () {	
								$('#outdl').dialog({
									href:'${pageContext.request.contextPath}/employee/add',
									method:'get',
									title:'新增员工信息:',
									cache:false,
									width:380,    
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
											$('#employeeform').form('submit',{
												url:'${pageContext.request.contextPath}/employee/save',
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
																$('#outdl').window('close');
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
											$('#employeeform').form('clear');
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
				    },{
						text: '打印', iconCls: 'icon-add', handler: function () {
							$("#dg").printArea();
						}
					}],
				    pageList:[5,10,20],
					loadMsg:'信息加载中...',
				    columns:[[    
				              {field:'id',title:'ID',rowspan:2,width:80,sortable:true},    
				              {field:'employeeName',title:'姓名',rowspan:2,width:80,sortable:true},    
				              {field:'sex',title:'性别',rowspan:2,width:80,sortable:true,
				              	  formatter:function(value,row,index){
				              		  if(row.sex){
				              			return row.sex=='1'?'男':'女';
				              		  }
				              		  else{
				              			  return value;
				              		  }
				              	  }  
				              },    
				              {field:'password',title:'密码',rowspan:2,width:80,sortable:true,hidden:true},    
				              {field:'positionName',title:'职位',rowspan:2,width:80,sortable:true,
				            	  formatter: function(value,row,index){
				      				if (row.position){
				      					return row.position.positionName;
				      				} else {
				      					return value;
				      				}
				      			}

				              },    
				              {field:'birthday',title:'入职日期',rowspan:2,width:100,sortable:true,
				            	  formatter:function(value,row,index){
				            		  if(row.birthday){
				            			  var date=new Date(row.birthday);
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
				              },
				              {field:'email',title:'邮箱',rowspan:2,width:120,sortable:true},    
				              {field:'tel',title:'电话',rowspan:2,width:80,sortable:true},    
				              {field:'idCard',title:'身份证',rowspan:2,width:120,sortable:true},    
				              {field:'edit',title:'编辑',rowspan:2,width:120,sortable:true,
				            	  formatter:function(value,row,index){
				            		  var e = '<a href="#" class="aedit" onclick="dgedit(\''+ row.id + '\')"></a> ';  
				                      var d = '<a href="#"  class="adelete" onclick="dgdelete(\''+ row.id +'\')"></a> '; 
				            		  return e+d;
				            	  }
				              }     				             

				          ]] ,
				          onLoadSuccess:function(){
				        	  $('.aedit').linkbutton({text:'修改',plain:true,iconCls:'icon-edit'});
				        	  $('.adelete').linkbutton({text:'删除',plain:true,iconCls:'icon-cut'});
				          }
				});
				$('#dg-search').searchbox({
					searcher:function(value,name){
						if(value!=null&value!=""){
							if(name=='id'){
								$('#dg').datagrid('load',{
									id:value
								})
							}
							else if(name=='employeeName'){
								$('#dg').datagrid('load',{
									employeeName:value
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
					href:'${pageContext.request.contextPath}/employee/edit/'+rowid,
					method:'get',
					title:'修改员工信息:',
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
							$('#employeeform').form('submit',{
								url:'${pageContext.request.contextPath}/employee/save',
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
							$('#employeeform').form('clear');
						}
					},{
						text:'取消',
						iconCls:'icon-cancel',
						handler:function(){
							$('#outdl').dialog('close');
						}
					}],
					
					onLoad:function(){     //弹窗加载完成后执行
						$('#employeeform').form({  //设置弹窗里面form表单
							onLoadSuccess:function(employeedata){ //form表单加载完成后  参数:employeedata是加载的数据。
								$('#positioncombobox').combobox('setValue',employeedata.position.id); //设置form表单里面combobox的初始值
							}
						});
						$('#employeeform').form('load','${pageContext.request.contextPath}/employee/getedit/'+rowid);
					}
				});
			}
			
			 function dgdelete(rowid){  //删除操作  
				 
		            $.messager.confirm('确认','id='+rowid+'\r确认删除?',function(sure){  
		                if(sure){  
		                    var selectedRow = $('#dg').datagrid('getSelected');  //获取选中行  
		                    $.ajax({  
		                    	cache:false,
		                        url:'${pageContext.request.contextPath}/employee/delete/'+rowid,  
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
	<div>
		<div id="tdiv">
			<input id="dg-search"></input> 
			<div id="mm" style="width:120px"> 
				<div data-options="name:'id',iconCls:'icon-ok'">按ID查询</div> 
				<div data-options="name:'employeeName',iconCls:'icon-ok'">按姓名查询</div> 
			</div>
		</div>
		<div id='printdiv'>
			<table id="dg"></table>  	
			<div id="pp" style="background:#efefef;border:1px solid #ccc;"></div> 
		</div>
		<div id='outdl'>
		</div>
	</div>
	</body>
</html>