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
				    url:'${pageContext.request.contextPath}/vehicleDetail/getvehicleDetails',  
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
								href:'${pageContext.request.contextPath}/vehicleDetail/add',
								method:'get',
								title:'新增汽车详细信息:',
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
										$('#vehicleDetailform').form('submit',{
											url:'${pageContext.request.contextPath}/vehicleDetail/save',
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
											$('#vehicleDetailform').form('clear');
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
				              {field:'vehicleBasicid',title:'业务单编号',rowspan:2,width:80,
					            	 formatter:function(value,rowData,rowIndex){
					            		 if(rowData.vehicleBasic){
					            			 return rowData.vehicleBasic.id;
					            		 }
					            		 else{
					            			 return value;
					            		 }
					            	 }
					         	  },
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
					            	 formatter:function(value,row,index){
					            		 if(row.stoLocation){
					            			 return  row.stoLocation.locName;
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
					          },
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
				    		/////
				    		url: '${pageContext.request.contextPath}/vehicleDetail/getallvehicletoelements/'+rowData.id,
				    		//url:'${pageContext.request.contextPath}/vehicleToElement/getvehicletoelements',  
						    method:'get',
						    idField:'id',
						    pagination:true,
						    fitColumns:true,
						    cache:false,
						    height:200,
						    singleSelect:true,
						    pageList:[10,20,40,100],
							loadMsg:'信息加载中...',
						    columns:[[    
						              {field:'id',title:'ID',rowspan:2,width:80,sortable:true},  
						              {field:'vehicleDetail.id',title:'汽车详细信息单',rowspan:2,width:80,
								            formatter:function(value,rowData,rowIndex){
								            		 if(rowData.vehicleDetail){
								            			 return rowData.vehicleDetail.id;
								            		 }
								            		 else{
								            			 return value;
								            		 }
								            	 }
								      },
								      {field:'vehicleElementname',title:'名称',rowspan:2,width:80,
								            formatter:function(value,rowData,rowIndex){
							            		 if(rowData.vehicleElement){
							            			 return rowData.vehicleElement.eleName;
							            		 }
							            		 else{
							            			 return value;
							            		 }
							            	 }
							     	  },
							     	 {field:'vehicleElementtype',title:'型号',rowspan:2,width:80,
								            formatter:function(value,rowData,rowIndex){
								            		 if(rowData.vehicleElement){
								            			 return rowData.vehicleElement.eleType;
								            		 }
								            		 else{
								            			 return value;
								            		 }
								            	 }
								      },
						              {field:'eleQuality',title:'品质',rowspan:2,width:80,sortable:true},  
						              {field:'eleProducer',title:'生产商',rowspan:2,width:80,sortable:true},  
						              {field:'eleProDate',title:'生产日期',rowspan:2,width:80,sortable:true,
						            	  formatter:function(value,rowData,rowIndex){
							            		 if(rowData.eleProDate){
							            			  var date=new Date(rowData.eleProDate);
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
						              {field:'elePrice',title:'价格',rowspan:2,width:80,sortable:true},  
						              {field:'elenumber',title:'数量',rowspan:2,width:80,sortable:true},  
						          ]] ,
						          ////
							onLoadSuccess : function(data) {
								if(data.total==0)
								{
									$.messager.show({
										msg:'此车辆无元件',
										title:'提示',
										timeout:2000
									});
								}
								
							}
				    	})
				    },
				    ////
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
									clientName:value
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
					href:'${pageContext.request.contextPath}/vehicleDetail/edit/'+rowid,
					method:'get',
					title:'修改汽车详细信息:',
					cache:false,
					width:440,    
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
							$('#vehicleDetailform').form('submit',{
								url:'${pageContext.request.contextPath}/vehicleDetail/save',
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
							$('#vehicleDetailform').form('clear');
						}
					},{
						text:'取消',
						iconCls:'icon-cancel',
						handler:function(){
							$('#outdl').dialog('close');
						}
					}],
					
					onLoad:function(){     //弹窗加载完成后执行
						$('#vehicleDetailform').form({
							onLoadSuccess:function(data){
								$('#vehicledetailcombobox').combobox('setValue',data.vehicleBasic.id);
								 $('#clerk').val(data.vehicleBasic.clerk.employeeName);
								 $('#client').val(data.vehicleBasic.client.name);
								 $('#vehicleType').val(data.vehicleBasic.vehicleType);
								 $('#vehicleName').val(data.vehicleBasic.vehicleName);
								 $('#vehicleLicense').val(data.vehicleBasic.vehicleLicense);
								 $('#transcombobox').combobox('setValue',data.transportation.id);
								 $('#locationcombobox').combobox('setValue',data.stoLocation.id);
							}
						})
						$('#vehicleDetailform').form('load','${pageContext.request.contextPath}/vehicleDetail/getedit/'+rowid);
					}
				});
			}
			
			 function dgdelete(rowid){  //删除操作  
				 
		            $.messager.confirm('确认','id='+rowid+'\r确认删除?',function(sure){  
		                if(sure){  
		                    var selectedRow = $('#dg').datagrid('getSelected');  //获取选中行  
		                    $.ajax({  
		                    	cache:false,
		                        url:'${pageContext.request.contextPath}/vehicleDetail/delete/'+rowid,  
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