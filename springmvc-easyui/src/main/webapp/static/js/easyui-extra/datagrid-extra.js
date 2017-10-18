/**
 * 定义列类
 * 
 * @param title
 *            标题名称
 * @param field
 *            字段,
 * @param width
 *            宽度
 * @param options_ {}
 *            其它的携带属性
 * @returns
 */
function BaseColumn(title,field,width,options_){
	var column = {};
	if(!Extra.isEmpty(title)){column["title"]=title;}// 名称
	if(!Extra.isEmpty(field)){column["field"]=field;}// 字段名称
	if(!Extra.isEmpty(width)){column["width"]=Extra.int(width);}// 列宽
	
	// 2.一些默认值修改
	column["align"] = "center";
	
	// 3.从options_中获取
	if(!Extra.isEmpty(options_)){for(var key in options_){column[key]=options_[key];}};
	
	return column;
}

function FirstColumn(title,field,checkbox){
	var columns = new BaseColumn(title,filed,null);
	
	// 是否显示复选框
	columns["checkbox"] = checkbox;
	
	return columns;
}

function DatagridColumn(title,field,width){
	var columns = new BaseColumn(title,field,width);
	
	return columns;
}



/**
 * 自定义 datagrid它需要设置action,获取数据的url
 * 
 * @param datagridId
 *            datagrid的Id
 * 
 * @param options_
 *            datagrid所需要的参数
 * 
 * columns:字段数组；
 * 
 * @param eoptions_
 *            其它的参数{}
 * 
 * @returns
 */
function DatagridExtra(datagridId,options_,eoptions_){
	var _this = this;
	this.id = "#" + datagridId;
	
	/**
	 * 初始化,声明成datagrid
	 */
	this.init = function(){
		// 获取声明datagrid所需要的参数
		var datagridOptions = _this.datagridOptions();
		// 1.声明datagrid
		$(_this.id).datagrid(datagridOptions);		
	}
	
	/**
	 * 获取datagrid声明时需要的参数
	 */
	this.datagridOptions = function(){	
		var result = options_;
		// 2.设置基本属性
		var attrOptions = _this.copy(_this.datagridAttrOptions(),result,"c");
		// 3.设置分页
		var pageOptions = _this.copy(_this.datagridPageOptions(),result,"c");
		// 4.设置获取数据的url
		var dataOptions = _this.copy(_this.datagridDataOptions(),result,"c");
		// 5.字段数组
		var columnOptions = _this.copy(_this.datagridColumnOptions(),result,"c");		
		return result;
	};
	
	/**
	 * 将a拷贝到b,
	 * 
	 * o:类型["a","b","c"], a,将a全部拷贝到b中; c,将a全部拷贝到b中,如果b中拥有则放弃;
	 * b,将a部分拷贝到b中,只拷贝b中拥有的;
	 */
	this.copy=function(a,b,o){
		switch(o){
			case "a":
				for(var key in a){
					b[key]=a[key];
				}
				break;
			case "c":
				for(var key in a){
					if(Extra.isEmpty(b,key)){
						b[key]=a[key];
					}
				}
				break;
			case "b":
				for(var key in b){
					if(!Extra.isEmpty(a,key)){
						b[key]=a[key];
					}
				}
				break;
		}
		return b;
	}
	
	/**
	 * toolbar标签
	 * 
	 * 可分两种加载方式： 1.通过id加载
	 * 
	 * 2.动态的加载,生成html
	 * 
	 */
	this.datagridToolbarOptions = function(){
		
	}
	
	/**
	 * datagrid的字段属性
	 */
	this.datagridColumnOptions = function(){
		var options = {
				columns : [[]],
				frozenColumns : [[]]// 同列属性，但是这些列将会被冻结在左侧。格式与columns是一样的
		};
		return options;
	}
	
	/**
	 * datagrid的基本属性
	 */
	this.datagridAttrOptions = function(){
		var options = {
				striped:true,// 显示斑马线
				rownumbers:true,// 显示行号
				
				// 与选择行有关系
				singleSelect:true,// true,只允许选择一行
				ctrlSelect:false,// 是否要启动Ctrl键,进行多选；不过在singleSelect=true时失效
				
				// 与选择checkbox有关系
				checkOnSelect:true,// 如果为true，当用户点击行的时候该复选框就会被选中或取消选中。
									// 如果为false，当用户仅在点击该复选框的时候才会呗选中或取消。
				selectOnCheck:false,// 如果为true，单击复选框将永远选择行。
									// 如果为false，选择行将不选中复选框。
		};
		return options;
	}
	
	/**
	 * datagrid分页所需要的参数
	 * 
	 * 里面暂时没有什么好修改的
	 */
	this.datagridPageOptions = function(){
		var options = { pagination : true, 
						pagePosition : "bottom",// 'top','bottom','both'
						pageNumber : 1, 
						pageSize : 10, 
						pageList : [ 10, 20, 30, 40, 50 ]};
		return options;
	}
	
	/**
	 * datagrid加载数据需要的参数
	 * 
	 * url:获取数据的地址,可以从datagrid的属性中获取,action/url
	 * 
	 */
	this.datagridDataOptions = function(){
		var options = {loadMsg : "加载中......",url:''
			// ,loadFilter:undefined,//处理url返回的json数据{total:100,rows:[{},{},{},{}]};这里不需要处理,除非特殊的
		};
		// 从属性中获取,属性可以是action,也可以是url
		if(!Extra.isEmpty($(_this.id).attr("action"))){
			options["url"]=$(_this.id).attr("action");
		}else{
			if(!Extra.isEmpty($(_this.id).attr("url")))options["url"]=$(_this.id).attr("url");
		}		
		return options;
	}
	
	/**
	 * 获取datagrid的options参数,通过options方法
	 */
	this.options = function(){
		return $(_this.id).datagrid("options");
	}
	
	/**
	 * toolbar中的查询事件绑定
	 */
	this.tbSearch = function(){
		var options = _this.options();
		if(!Extra.isEmpty(options,"toolbar")){
			$(options["toolbar"]).find("a[tag='search']").each(function(){
				if($(this).attr("bindclick")==undefined){					
					$(this).attr("bindclick",true).bind('click',function(){
						_this.search($(this));
					});
				}
			});
		}
	}
	
	/*
	 * toolbar中的查询
	 *<div id="${test_datagrid}_tb"  style="display: none;">
	 *	<div>
	 *		<a href="#" class="easyui-linkbutton" tag="add" >添加</a>
	 *		<a href="#" class="easyui-linkbutton" tag="update" >修改</a>
	 *		<a href="#" class="easyui-linkbutton" tag="deletes" >删除</a>		
	 *	</div>
	 *	<div>
	 *		<table id="${test_datagrid}_tb_search">
	 *			<tr>
	 *				<td>名称：</td>
	 *				<td>
	 *					<input type="text" name="name" zauto="true" />
	 *				</td>
	 *				<td>
	 *					编号：
	 *				</td>
	 *				<td>
	 *					<input type="text" name="id" zauto="true" />
	 *				</td>
	 *				<td>
	 *					<a href="#" class="easyui-linkbutton" tag="search" parentId="${test_datagrid}_tb_search" callbackParam="">查询</a>
	 *				</td>
	 *			</tr>
	 *		</table>
	 *	</div>
	 *</div>
	 *
	 *
	 *查看a[tag='search']部分,其中除了tag,其它的附加参数都可缺省
	 *
	 *parentId：标记了parent的Id,这样就可以容易查找input了
	 *callbackParam:一个处理参数的函数,因为一些特殊的原因,parent中的input不容易变成{}对象,就专门写一个函数处理
	 *
	 */
	this.search = function($this){
		//1.找到$this的父对像,方便查找里面的input
		var $parent;
		if(!Extra.isEmpty($this.attr("parentId"))){
			$parent = $("#"+$this.attr("parentId"));
		}else{
			$parent = $this.closest("table");
		}
		//2.找到里面的参数
		var param = {};
		if(!Extra.isEmpty($this.attr("callbackParam"))){
			var callbackParam = $this.attr("callbackParam");
			callbackParam = eval(callbackParam);
			try{param = callbackParam();}catch(e){}
		}else{
			param = ExtraAjax.ajaxData($parent);
		}
		//3.查询
		$(_this.id).datagrid('load', param);
		return false;
	}
	
	/**
	 * 绑定添加事件
	 */
	this.tbAdd = function(){
		var options = _this.options();
		if(!Extra.isEmpty(options,"toolbar")){
			$(options["toolbar"]).find("a[tag='add']").each(function(){
				if($(this).attr("bindclick")==undefined){					
					$(this).attr("bindclick",true).bind('click',function(){
						_this.add($(this));
					});
				}
			});
		}
	}
	
	/**
	 * 添加功能,
	 * 
	 * 
	 */
	this.add = function($this){
		//0.可以设置一个前置处理url的函数
		//也可以设置一个动态的处理属性的函数
		
		//1.弹出dialog
		var dialog = Easyui.createEasyuiDialog($this,{});
		
		//2.添加后置函数
		dialog.envOptions["callbackPostSuccess"] = function(){
			_this.reload();
		}
		return false;		
	}
	
	/**
	 * 绑定更新事件
	 */
	this.tbUpdate = function(){
		var options = _this.options();
		if(!Extra.isEmpty(options,"toolbar")){
			$(options["toolbar"]).find("a[tag='update']").each(function(){
				if($(this).attr("bindclick")==undefined){					
					$(this).attr("bindclick",true).bind('click',function(){
						_this.update($(this));
					});
				}
			});
		}
	}
	
	/**
	 * 处理href,返回正常的数据信息
	 */
	this.hrefHandler = function(href,rowData){
		var params = Extra.getHrefParam(href);
		for(var i in params){
			href = Extra.replaceAll(href,"{"+params[i]+"}",rowData[params[i]]);
		}
		return href;
	}
	
	/**
	 * 更新
	 */
	this.update = function($this){
		//1.可以用getChecked方法,不过谨慎考虑选择了用getSelections
		var selectArr = $(_this.id).datagrid('getSelections');// 我们用这个
		if(selectArr.length==0){selectArr = $(_this.id).datagrid('getChecked');}
		if (selectArr.length != 1) {
			$.messager.alert('警告', '请选中一行要修改的数据！', 'warning');
			return false;
		}
		
		//2.定一个url处理函数
		var options = {
			callbackHref:function(href){
				return _this.hrefHandler(href,selectArr[0]);
			}
		};
		
		//1.弹出dialog
		var dialog = Easyui.createEasyuiDialog($this,options);
		
		//2.添加后置函数
		dialog.envOptions["callbackPostSuccess"] = function(){
			_this.reload();
		}
		
		return false;
	}
	
	/**
	 * 绑定视图事件
	 */
	this.tbView = function(){
		var options = _this.options();
		if(!Extra.isEmpty(options,"toolbar")){
			$(options["toolbar"]).find("a[tag='view']").each(function(){
				if($(this).attr("bindclick")==undefined){					
					$(this).attr("bindclick",true).bind('click',function(){
						_this.view($(this));
					});
				}
			});
		}
	}
	
	/**
	 * 视图的显示
	 * 
	 * 获取了参数，
	 * 弹出了框
	 * 
	 */
	this.view = function($this){		
		//1.可以用getChecked方法,不过谨慎考虑选择了用getSelections
		var selectArr = $(_this.id).datagrid('getSelections');// 我们用这个
		if(selectArr.length==0){selectArr = $(_this.id).datagrid('getChecked');}
		if (selectArr.length != 1) {
			$.messager.alert('警告', '请选中一行要显示的数据！', 'warning');
			return false;
		}
		
		//2.定一个url处理函数
		var options = {
			callbackHref:function(href){
				return _this.hrefHandler(href,selectArr[0]);
			}
		};
		
		//1.弹出dialog
		var dialog = Easyui.createEasyuiDialog($this,options);	
		
		//2.添加后置函数,这里面它是不需要这个函数的,不过为了保险起见,还是加上去了
		dialog.envOptions["callbackPostSuccess"] = function(){
			_this.reload();
		}
		
		return false;
	}
	
	/**
	 * 绑定批量删除事件
	 */
	this.tbDeletes = function(){
		var options = _this.options();
		if(!Extra.isEmpty(options,"toolbar")){
			$(options["toolbar"]).find("a[tag='deletes']").each(function(){
				if($(this).attr("bindclick")==undefined){					
					$(this).attr("bindclick",true).bind('click',function(){
						_this.deletes($(this));
					});
				}
			});
		}
	}
	
	/**
	 * 批量删除
	 * 
	 * 绑定到此按钮上默认是有提示的,tip=true
	 * 
	 */
	this.deletes = function($this){
		//1.可以用getSelections方法,不过谨慎考虑选择了用getChecked
		var checkedArr = $(_this.id).datagrid('getChecked');// 我们用这个
		if (checkedArr.length <= 0) {
			$.messager.alert('警告', '请至少选择一行要删除的数据！', 'warning');
			return false;
		}
		
		//2.删除
		$.messager.confirm('确认删除','您确定要删除选中的记录吗？',function(flag){
			//1.是否要执行
			if(!flag)return false;
			//2.寻找ajax参数
			var href = "";
			if(!Extra.isEmpty($this.attr("fhref"))){href=$this.attr("fhref");}
			if(!Extra.isEmpty($this.attr("action"))){href=$this.attr("action")}
			
			var params = Extra.getHrefParam(href);
			for(var i in params){
				var param = [];
				for(var j in checkedArr){
					param.push(checkedArr[j][params[i]]);
				}
				href=Extra.replaceAll(href,"{"+params[i]+"}",param.toString());
			}			
			var type="post";
			if(!Extra.isEmpty($this.attr("method"))){type=$this.attr("method")}
			
			var options = {};
			options["url"]=href;
			options["type"]=type;
			options["progress.text"]="删除中....";
			options["callbackSubSuccess"]=function(result){
				_this.reload();
			}			
			
			//3.ajax调用
			ExtraAjax.ajax(options);
			
		});
		
		return false;
	}
	
	this.bindEvent = function(eventName){
		switch(eventName){
			case "tbSearch":
				_this.tbSearch();
				break;
			case "tbAdd":
				_this.tbAdd();
				break;
			case "tbUpdate":
				_this.tbUpdate();
				break;
			case "tbDeletes":
				_this.tbDeletes();
				break;
			case "tbView":
				_this.tbView();
				break;
		}
		
	}
	
	
	// 一些系统方法
	// 将datagrid变成加载中的形态
	this.beforeSend = function() {
		$(_this.id).datagrid('loading');
	};
	// 将datagrid的加载中状态取消
	this.complete = function() {
		$(_this.id).datagrid('loaded');
	};
	// 刷新datagrid页面,变成第一页
	this.refresh = function() {
		$(_this.id).datagrid('load');
	};
	// 刷新datagrid页面,保持在当前页
	this.reload = function() {
		$(_this.id).datagrid('reload');
	};
	// 取消选中datagrid
	this.unselectAll = function() {
		$(_this.id).datagrid('unselectAll');
	};
	

	// 执行初始化操作
	this.init();
	// 绑定事件
	//查询
	this.bindEvent("tbSearch");
	//添加
	this.bindEvent("tbAdd");
	//修改
	this.bindEvent("tbUpdate");
	//删除
	this.bindEvent("tbDeletes");
	//删除
	this.bindEvent("tbView");
}



