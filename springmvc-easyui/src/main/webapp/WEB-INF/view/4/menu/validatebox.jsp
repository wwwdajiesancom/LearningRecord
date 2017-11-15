<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/component/all-taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/include/component/easyui-script-css.jsp"%>

<script type="text/javascript"
	src="${contextPath}/static/js/bind-extra.js"></script>

<title>easyui-验证框(validatebox)</title>
<script type="text/javascript">
<!--
	var easyuiarrs = [];
	easyuiarrs.push({selector:"*.easyui-textbox",target:"textbox",method:"destroy",type:"input"});
	easyuiarrs.push({selector:"*.easyui-combo",target:"combo",method:"destroy",type:"input"});
	easyuiarrs.push({selector:"*.easyui-combobox",target:"combobox",method:"destroy",type:"select"});
	easyuiarrs.push({selector:"*.easyui-combotree",target:"combotree",method:"destroy",type:"select"});
	easyuiarrs.push({selector:"*.easyui-numberbox",target:"numberbox",method:"destroy",type:"input"});
	easyuiarrs.push({selector:"*.easyui-combogrid",target:"combogrid",method:"destroy",type:"select"});
	easyuiarrs.push({selector:"*.easyui-datebox",target:"datebox",method:"destroy",type:"input"});
	easyuiarrs.push({selector:"*.easyui-datetimebox",target:"datetimebox",method:"destroy",type:"input"});
	easyuiarrs.push({selector:"*.easyui-datetimespinner",target:"datetimespinner",method:"destroy",type:"input"});
	easyuiarrs.push({selector:"*.easyui-numberspinner",target:"numberspinner",method:"destroy",type:"input"});
	easyuiarrs.push({selector:"*.easyui-timespinner",target:"timespinner",method:"destroy",type:"input"});
	easyuiarrs.push({selector:"*.easyui-slider",target:"slider",method:"destroy",type:"div"});
	easyuiarrs.push({selector:"*.easyui-filebox",target:"filebox",method:"destroy",type:"input"});
	easyuiarrs.push({selector:"*.easyui-dialog",target:"dialog",method:"destroy",type:"div"});
	easyuiarrs.push({selector:"*.easyui-window",target:"window",method:"destroy",type:"div"});
	
	function destory(){
	    for(var i in easyuiarrs){
	    	$("body").find(easyuiarrs[i]["selector"]).each(function(){
	    		try{
	    			$(this)[easyuiarrs[i]["target"]](easyuiarrs[i]["method"]);
	    		}catch(e){
	    			console.log(e);
	    		}	    		
	    	});
	    }	
	}
	
//-->
</script>
</head>
<body>
    
    <a href="javascript:;" onclick="destory();" class="easyui-linkbutton">删除</a>

	<input type="text" class="easyui-validatebox" zauto="true"
		required="true" />

    
	<div id="dd"  title="My Dialog" style="width:400px;height:200px;"   
	        data-options="iconCls:'icon-save',resizable:true,modal:false">   
	    Dialog Content.    
	</div>  

</body>
</html>