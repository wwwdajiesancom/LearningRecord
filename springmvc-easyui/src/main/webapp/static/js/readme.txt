extra.js
	这里面定义了一些系统常用的js方法,都是以Extra开头调用了
		一下写几个主要的：
			1.判断是否为空,isEmpty(a,b);其中b是可选的,只有当a={}时,b是a中的一个key
			2.ajax相关封装
			3.valid,form表单的验证
				
dialog-extra.js
	这里面封装了dialog里面的事件，主要有
		1.关闭按钮,
		2.保存按钮,ajax提交
		3.修改按钮,ajax提交
		
		这里面还有一些固定的配置是可以被修改的：
			this.envOptions = {
				success_msg : "保存成功.",//做为一个成功时默认提示
				fail_msg : "保存失败.",//做为一个失败时默认提示
				callbackPostSuccess : function() {
				},//在执行完所有的成功方法后,执行它,算是一个后置成功函数
				callbackPostFail : function() {
				},//在执行完所有的失败方法后,执行它,算是一个后置成功函数
				defaultCallbackSuccess:_this.ajaxSuccessDialog(result,{})//默认ajaxSuccess方法
			};
	
datagrid-extra.js
	这里面封装datagrid的一些常用操作，
		1.datagrid动态实例化
		2.toolbar中的search
		3.toolbar中的save,view,update,deletes封装
		
bind-extra.js
	这里面主要是一些绑定，根据特定调教进行绑定
		1.input自动设置样式,需要符合一下条件:<input zauto="true"/>,里面的属性zauto=true
		2.创建dialog事件的绑定,
		3.等 
		
exec-bind.js
	将bind-extra.js里面封装的绑定事件执行一遍,一般用于单独的add.jsp,udpate.jsp,view.jsp页面
	

1.动态创建Dialog,以下是用法：
	a.需要引用extra.js,dialog-extra.js,datagrid-extra.js
	b.动态创建Dialog的按钮样例：
		<a href="#" 
		    callbackDestroy=""
		    destroyContent="true"
			class="easyui-linkbutton" 
			tag="DatagridOptions.dialog"
			fhref=""
			action="" 
			buttons="save,close" 
			params="modal:true;width:350px;height: 250px;href:${contextPath}/jsp/dialog/view.html;"
		>动态创建一个dialog</a>
		解释:其中class是a标签的easyui样式,多出的就是Dialog的东西了
			1）tag="DatagridOptions.dialog",它是一个标记,标记该a标签会绑定一个创建Dialog的事件
			2）fhref,action是Dialog创建好,要加载到dialog-content中的url;这个Url连接到一个jsp页面
			3）buttons="save,update[value=更新;],close",它是动态创建Dialog下面按钮动属性
				要注意的是,我们这里面只有3个,就是save,update,close
					I,save,update它们会发动一个ajax提交,这对url地址连接到的页面,所以这个页面必须有form表单
						form表单样例：
							<form 
								action="${contextPath}/ajax/dialog/view.json" //ajax参数,url
								id="from_id"                                  //可选
								method="post"                                 //ajax参数 ,type
								contentType=""                                //可选,值可以是json,这样ajax提交是contentType="application/json"
								callbackValid=""                              //可选,默认的是用easyui的form表单验证,它作为一个form表单验证函数,返回boolean值;function(){return false;}
								callbackSuccess=""                            //可选,默认有一个处理函数,ajax成功后的success处理函数,function(data){}
								callbackSubSuccess=""                         //可选,它是默认处理函数中的一部分,在其它部分都执行完毕,才执行该函数,function(){}
								callbackParam=""                              //可选,默认有一个获取form表单数据的函数,但是它的功能比较标准有些处理不了,所以可以专门提供一个函数来获取form表单数据,这样就可以放弃默认的了,function(){return {};}
								callbackSubParam="">                          //可选,默认有一个获取form表单数据的函数,但是它的功能比较标准有些处理不了,所以比较复杂的用该函数处理,然后默认的和它组合到一块,function(){return {};}
								<table>
									<tr>
										<td>姓名：</td>
										<td><input type="text" name="name" class="easyui-validatebox" zauto="true"
											required="true" /></td>
									</tr>
									<tr>
										<td>Email：</td>
										<td><input type="text" name="email" class="easyui-validatebox" zauto="true"
											validType="email" /></td>
									</tr>
								</table>
							</form>
					
					II,close,这个是关闭动态创建的dialog

2.DatagridExtra的使用，
	因为datagrid中的属性比较多,所以就封装了一下,除了几个比较重要的需要传递,其它的都设置成默认的了
	
	DatagridExtra里面的功能一共分为3部分：
		a）列表信息,查询
		b）toolbar中的view,add,update,deletes
		c）datagrid列表的一些功能,比如：编辑列，列弹出框，列删除等
	
	用到该东西页面需要用一下的模版：
		<!-- 1.设置datagrid的Id -->
		<c:set value="test_datagrid" var="datagridId"></c:set>
		
		<!-- datagrid的toolbar部分代码 -->
		<div id="${test_datagrid}_tb"  style="display: none;">
			<div>
				<a href="#" 
					fhref="${contextPath}/jsp/datagrid/add.html" 
					params="title:添加测试;width:350;height:250;" 
					buttons="save,close" 
					class="easyui-linkbutton" 
					tag="DatagridOptions.add" 
				>添加</a>
				<a href="#" 
					fhref="${contextPath}/jsp/datagrid/update.html" 
					params="width:350;height:250;" 
					buttons="save[value:修改],close" 
					class="easyui-linkbutton" 
					tag="DatagridOptions.update" 
				>修改</a>
				<a href="#" 
					class="easyui-linkbutton" 
					tag="DatagridOptions.deletes" 
				>删除</a>		
			</div>
			<div>
				<table id="${test_datagrid}_tb_search">
					<tr>
						<td>名称：</td>
						<td>
							<input type="text" name="name" zauto="true" />
						</td>
						<td>
							编号：
						</td>
						<td>
							<input type="text" name="id" zauto="true" />
						</td>
						<td>
							<a href="#" 
								class="easyui-linkbutton" 
								tag="DatagridOptions.search" 
							>查询</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
		
		<!-- datagrid代码 -->
		<table id="${datagridId}" 
			toolbar="#${test_datagrid}_tb" 
			action="${contextPath}/ajax/findpage.json">
			
		</table>
		
	解释：
		1.我们先说datagrid的table
			<table id="${datagridId}" //需要一个Id,这样才可以创建DatagridExtra对象
				toolbar="#${test_datagrid}_tb" //定义了toolbar部分的代码
				action="${contextPath}/ajax/findpage.json"//datagrid的数据就是通过该href来的
			>
			</table>
		2.然后我们说toolbar
			toolbar一共有2部分组成，
				按钮部分，它只有4种方式,view,add,update,deletes
				查询部分，他只有一种方式search
			
			按钮部分的规则,与动态创建dialog的一部分，
			查询部分,也一样
			
		3.datagrid行之中的操作：
			操作的标签,与上面的a标签属性一致,但需要添加click事件,
			一共定义了4种：Row.view(this,i),Row.update(this,i),Row.del(this,i),Row.other(this,i,callbackOther($this,i,datagridId));
			例如:
				 * <a href="#" 
				 *		class="easyui-linkbutton" 
				 *		tag="DatagridOptions.view"      可选
				 *		fhref=""        它需要是真路径,不能包含了参数
				 *		action="" 
				 *		buttons="save,close" 
				 *		params="modal:true;width:350px;height: 250px;href:${contextPath}/jsp/dialog/view.html;"
				 *      onClick="return Row.view(this,i);"
				 *	>
			
			
			
这下面是一个模版：			
			
			
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/component/all-taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/include/component/easyui-script-css.jsp"%>

<title>easyui-分页table(datagrid)</title>
<script type="text/javascript">
<!--
	$(function() {

		var columns = [ [ {
			title : "代码",
			field : "id",
			checkbox:true,
		}, {
			title : "名称",
			field : "name",
			sortable:true,
		}, {
			title : "缩写",
			field : "abbr"
		}, {
			title : "创建时间",
			field : "createdAt",
			formatter: function(value,row,index){
				return Extra.formatDate(value,"yyyy-MM-dd HH:mm:ss");
			}
		},{
			title : "操作",
			field: "opt",
			formatter: function(value,row,index){
				return "<a href='#' class='easyui-linkbutton' action='${contextPath}/jsp/dialog/view.html?id="+row['id']+"' onClick='return Row.view(this);' >View</a>";
			}
		}] ];
		
		var options_ = {
			title : "测试table",
			columns : columns,
			
		};
		
		var datagrid = new DatagridExtra("test_datagrid",options_);
		
		var datagrid2 = new DatagridExtra("test_datagrid2",options_);

	});
		
//-->
</script>
</head>
<body>
	<!-- 设置datagrid的Id -->
	<c:set value="test_datagrid" var="datagridId"></c:set>
	
	<!-- datagrid的toolbar部分代码 -->
	<div id="${test_datagrid}_tb"  style="display: none;">
		<div>
			<a href="#" action="${contextPath}/jsp/dialog/view.html?id={id}" params="title:添加测试;width:350;height:250;" buttons="save,close" class="easyui-linkbutton" tag="DatagridOptions.view" >视图</a>
			<a href="#" action="${contextPath}/jsp/datagrid/add.html" params="title:添加测试;width:350;height:250;" buttons="save,close" class="easyui-linkbutton" tag="DatagridOptions.add" >添加</a>
			<a href="#" action="${contextPath}/jsp/datagrid/update.html?id={id}" params="width:350;height:250;" buttons="update[value:修改],close" class="easyui-linkbutton" tag="DatagridOptions.update" >修改</a>
			<a href="#" class="easyui-linkbutton" tag="DatagridOptions.deletes" action="${contextPath}/ajax/datagrid/delete.json?ids={id}&name={name}" >删除</a>		
		</div>
		<div>
			<table id="${test_datagrid}_tb_search">
				<tr>
					<td>名称：</td>
					<td>
						<input type="text" name="name" zauto="true" />
					</td>
					<td>
						编号：
					</td>
					<td>
						<input type="text" name="id" zauto="true" />
					</td>
					<td>
						<a href="#" class="easyui-linkbutton" tag="DatagridOptions.search" >查询</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
	
	<!-- datagrid的toolbar部分代码 -->
	<div id="${test_datagrid}_tb2"  style="display: none;">
		<div>
			<a href="#" action="${contextPath}/jsp/dialog/view.html?id={id}" params="title:添加测试;width:350;height:250;" buttons="save,close" class="easyui-linkbutton" tag="DatagridOptions.view" >视图</a>
			<a href="#" action="${contextPath}/jsp/datagrid/add.html" params="title:添加测试;width:350;height:250;" buttons="save,close" class="easyui-linkbutton" tag="DatagridOptions.add" >添加</a>
			<a href="#" action="${contextPath}/jsp/datagrid/update.html?id={id}" params="width:350;height:250;" buttons="update[value:修改],close" class="easyui-linkbutton" tag="DatagridOptions.update" >修改</a>
			<a href="#" class="easyui-linkbutton" tag="DatagridOptions.deletes" action="${contextPath}/ajax/datagrid/delete.json?ids={id}&name={name}" >删除</a>		
		</div>
		<div>
			<table id="${test_datagrid}_tb_search">
				<tr>
					<td>名称：</td>
					<td>
						<input type="text" name="name" zauto="true" />
					</td>
					<td>
						编号：
					</td>
					<td>
						<input type="text" name="id" zauto="true" />
					</td>
					<td>
						<a href="#" class="easyui-linkbutton" tag="DatagridOptions.search" >查询</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
	
	<!-- datagrid代码 -->
	<table id="${datagridId}" toolbar="#${test_datagrid}_tb" action="${contextPath}/ajax/findpage.json">
		
	</table>
	
	<!-- datagrid代码 -->
	<table id="${datagridId}2" toolbar="#${test_datagrid}_tb2" action="${contextPath}/ajax/findpage.json">
		
	</table>
</body>
</html>