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
	$(function(){
		$('#vehicletabs').tabs({
			fit:true,
			onSelect:function(title){
			//
				$('#pp1').panel({
					method:'get',
					href : '${pageContext.request.contextPath}/vehicleBasic/getall',					
					fit:true,
				//	queryParams:{vehiclestate:21}
					});
			//	
			}
		})
	})
</script>
</head>
<body>
<div id="vehicletabs" class="easyui-tabs" style="width:500px;height:250px;">   
    <div title="已预约业务" data-options="iconCls:'icon-help',closable:true" style="padding:20px;display:none;">   
    	<div id='pp1' class="easyui-panel" style="width:'100%';height:505;">
    	</div>
    </div>   
    <div title="进行中业务" data-options="iconCls:'icon-reload',closable:true" style="overflow:auto;padding:20px;display:none;">   
    </div>   
    <div title="已完成业务" data-options="iconCls:'icon-ok',closable:true" style="padding:20px;display:none;">   
    </div>    
    <div title="已撤销业务" data-options="iconCls:'icon-undo',closable:true" style="padding:20px;display:none;">   
    </div>   
</div>

</body>
</html>