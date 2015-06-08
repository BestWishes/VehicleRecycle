<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common.jsp"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>汽车资源回收管理系统</title>

<script type="text/javascript">
	$(function() {
		$('#tt').tree({
			data: [{
					id:4,
					text: '我的业务',
				//	iconCls:'icon-edit',
					state: 'closed',
					children: [{
						id:41,
						text: '预约业务',
						state: 'open',
						iconCls:'icon-edit',
						attributes:'vehicleBasic',
					},{
						id:42,
						text: '已分派业务',
						iconCls:'icon-redo',
						state: 'open',
						attributes:'vehicleBasic',
					},{
						id:43,
						text: '已完成业务',
						iconCls:'icon-ok',
						state: 'open',
						attributes:'vehicleBasic',
					},{
						id:44,
						text: '已撤单业务',
						iconCls:'icon-back',
						state: 'open',
						attributes:'vehicleBasic',
					}]
			},{
				id:2,
				text: '业务管理',
				state: 'closed',
				children: [{
					id:21,
					text: '业务单',
					state: 'open',
					iconCls:'icon-search',
					attributes:'vehicleBasic',
				},{
					id:'vehicleDetail',
					text: '汽车详细信息',
					state: 'open',
					iconCls:'icon-search',
					attributes:'vehicleDetail',
				},{
					id:'vehicleToElement',
					text: '汽车元件',
					state: 'open',
					iconCls:'icon-search',
					attributes:'vehicleToElement',
				}]
			},{
				id:5,
				text: '客户管理',
				//iconCls:'icon-user',
				state: 'closed',
				children: [{
					id:51,
					text: '客户信息查询',
					state: 'open',
					iconCls:'icon-search',
					attributes:'client',
				}]
			},{
				id:6,
				text: '日志查询',
				//iconCls:'icon-help',
				state: 'closed',
				children: [{
					id:61,
					text: '日志信息查询',
					state: 'open',
					iconCls:'icon-search',
					attributes:'log',
				}]
			}],
			onClick : function(node) {
					if($('#tt').tree('isLeaf',node.target)){
								$('#cc').panel({
									title:'当前位置：'+node.text
								});
								$('#pp').panel({
									method:'get',
									href : '${pageContext.request.contextPath}/'+node.attributes+'/getall',					
									fit:true,
									queryParams:{vehiclestate:node.id}
								});
								
					}
					else{
						node.state=='open'?$('#tt').tree('collapse',node.target):$('#tt').tree('expand',node.target);
					}
			}
		});
		
		
	})
	
	function realSysTime(clock){ 
		var now=new Date(); //创建Date对象 
		var year=now.getFullYear(); //获取年份 
		var month=now.getMonth(); //获取月份 
		var date=now.getDate(); //获取日期 
		var day=now.getDay(); //获取星期 
		var hour=now.getHours(); //获取小时 
		var minu=now.getMinutes(); //获取分钟 
		var sec=now.getSeconds(); //获取秒钟 
		month=month+1; 
		var arr_week=new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六"); 
		var week=arr_week[day]; //获取中文的星期 
		var time=year+"年"+month+"月"+date+"日 "+week+" "+hour+":"+minu+":"+sec; //组合系统时间 
		clock.innerHTML=time; //显示系统时间 
	}
	
	window.onload=function(){ 
		window.setInterval("realSysTime(clock)",1000); //实时获取并显示系统时间 
		} 
</script>

</head>
<body class="easyui-layout">
	<div data-options="region:'north',title:'汽车资源回收管理系统    欢迎：${employeeName }'"
		style="height: 80px;
		">
		<a href="outlog" >退出登录</a>
 		<div style="text-align: center;font-size: xx-large;" id='clock'></div>
	</div>

	<div id='ww' data-options="region:'west',title:'功能导航',split:true"
		style="width: 200px;">
			<ul id='tt' class="easyui-tree" data-options="animate:true,lines:true">		
			</ul>
	</div>

	<div id="cc" data-options="region:'center',title:'首页'"
	style="background-image: url('${pageContext.request.contextPath}/imgs/tree1.jpg');
		background-size:100% 100%;">
		 
		<div id='pp' class="easyui-panel" style="width:'100%';height:505;">
					<h1 style="text-align: center;">欢迎登录汽车资源回收管理系统</h1>
		</div>
		
	</div>
</body>
</html>