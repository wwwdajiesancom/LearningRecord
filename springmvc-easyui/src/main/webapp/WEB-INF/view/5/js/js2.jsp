<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/component/all-taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/include/component/easyui-script-css.jsp"%>

<script type="text/javascript">
	
	window.onload=function(){
		
	}
	
	function addTd(){
		$("table tr:eq(0)").append("<td> <input tag='alert' type='button' value='value3' class='ztextbox' /> </td>");		
	}
	
	function bindClick(){
		$("html").undelegate("input[tag='alert']","click").delegate("input[tag='alert']","click",function(){			
			alert($(this).attr("value"));
		});
		
		$("html").undelegate("input[tag='alert']","click").delegate("input[tag='alert']","click",function(){			
			alert($(this).attr("value"));
		});
		
		$("html").delegate("input[tag='add']","click",function(){			
			addTd();
		});
		
		$("html").delegate("td","click",function(){
			console.log(this);
		});
	}
	
	bindClick();
	
</script>

<title>js2学习</title>
</head>
<body >
	
	<table>
		<tr>
			<td> <input tag="alert" type="button" value="value1" class="ztextbox" /> </td>
			<td> <input tag="alert" type="button" value="value2" class="ztextbox" /> </td>
			<td> <input tag="alert" type="button" value="value3" class="ztextbox" /> </td>
			<td> <input tag="add" type="button" value="value4" class="ztextbox" /> </td>
			
		</tr>
		
		<tr>
			<td> <input type="checkbox" name="ch1.name" value="1" />1 <input type="checkbox" name="ch1.name" znone="true" value="2" />2 <input type="checkbox" name="ch1.name" value="3" />3 <input type="checkbox" name="ch1.name" value="4" />4 </td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</table>
	
</body>
</html>