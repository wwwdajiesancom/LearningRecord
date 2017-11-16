/**
 * 主要是为了销毁一些easyui组件垃圾
 * 
 * 因为组件自身的缘故或者组件之间不能相互包含的缘故，easyui框架有时会将dom信息打乱，这样会导致在关闭某一个dialog、panel、window时
 * 出现一些无法删除的对象
 * 
 */
var EasyuiDesotry = {
	tags:function(which){
		var tags = [];
		tags[0]="autoDestroyWindow";
		tags["window"]=tags[0];		
		if(Extra.isEmpty(which)){
			return tags;
		}		
		else{
			return tags[which];
		}			
	},
	//可以销毁组件的列表
	easyui_destory_selectors:function(){
		var easyuiarrs = [];		
		//form表单中的
		easyuiarrs.push({selector:"*.easyui-textbox,*.js-textbox",target:"textbox",method:"destroy",type:"input"});
		easyuiarrs.push({selector:"*.easyui-combo,*.js-combo",target:"combo",method:"destroy",type:"input"});
		easyuiarrs.push({selector:"*.easyui-combobox,*.js-combobox",target:"combobox",method:"destroy",type:"select"});
		easyuiarrs.push({selector:"*.easyui-combotree,*.js-combotree",target:"combotree",method:"destroy",type:"select"});
		easyuiarrs.push({selector:"*.easyui-numberbox,*.js-numberbox",target:"numberbox",method:"destroy",type:"input"});
		easyuiarrs.push({selector:"*.easyui-combogrid,*.js-combogrid",target:"combogrid",method:"destroy",type:"select"});
		easyuiarrs.push({selector:"*.easyui-datebox,*.js-datebox",target:"datebox",method:"destroy",type:"input"});
		easyuiarrs.push({selector:"*.easyui-datetimebox,*.js-datetimebox",target:"datetimebox",method:"destroy",type:"input"});
		easyuiarrs.push({selector:"*.easyui-datetimespinner,*.js-datetimespinner",target:"datetimespinner",method:"destroy",type:"input"});
		easyuiarrs.push({selector:"*.easyui-numberspinner,*.js-numberspinner",target:"numberspinner",method:"destroy",type:"input"});
		easyuiarrs.push({selector:"*.easyui-timespinner,*.js-timespinner",target:"timespinner",method:"destroy",type:"input"});
		easyuiarrs.push({selector:"*.easyui-slider,*.js-slider",target:"slider",method:"destroy",type:"div"});
		easyuiarrs.push({selector:"*.easyui-filebox,*.js-filebox",target:"filebox",method:"destroy",type:"input"});
		
		//窗体类,因为窗体中无法包含窗体,导致dialog,window都不在原有的地方
		//easyuiarrs.push({selector:"*.easyui-dialog",target:"dialog",method:"destroy",type:"div"});
		//easyuiarrs.push({selector:"*.easyui-window",target:"window",method:"destroy",type:"div"});
		return easyuiarrs;
	},
	/**
	 * 收集器,
	 * 这个是panel里面的一个方法extractor,在ajax调用成功之后返回的对data进行处理的方法
	 * 
	 * 这里面主要是将data中需要删除的dialog,window记录下来,在以后销毁的时候可以销毁
	 * 因为当前尚未渲染,所以data中还存在；但是到页面上渲染后,里面的dialog就会出现在其它的地方,所以要记录下来
	 */
	extractor:function(data){
		//从data中找到相关的数据window数据
		var $div = $("<div></div>");
		//将data加进去
		$div.append(data);
		
		//找到.easyui-dialog,.easyui-window
		//如果是需要js动态声明的,需要有一个标记class="jswindow"
		var ids = [];
		$div.find(".easyui-dialog,.easyui-window,.js-window,.js-dialog").each(function(){var id=$(this).attr("id");if(Extra.isEmpty(id))id=Extra.guid();ids.push("#"+id);});
		$div.remove();
		return data+"<div "+EasyuiDesotry.tags("window")+"='"+ids.toString()+"'></div>";
	},
	/**
	 * 删除收集器中的信息，
	 * 调用这个函数的时候,需要和函数EasyuiDesotry.extractor一块使用,这个是收集的用处
	 */
	destroyWindow:function(parent_selector){
		//1.判断是否为空
		if(Extra.isEmpty(parent_selector)){
			return;
		}
		//2.删除窗体
		$(parent_selector).find("div["+EasyuiDesotry.tags("window")+"]").each(function(){
			//找到相关的Id
			var autoDestroyWindow = $(this).attr(EasyuiDesotry.tags("window"));
			if(!Extra.isEmpty(autoDestroyWindow)){
				var ids = autoDestroyWindow.split(",");
				for(var index in ids){
					EasyuiDesotry.destroyGeneric(ids[index]);
				}
			}
		});
	},
	/**
	 * 销毁dialog及里面的信息
	 */
	destroyGeneric:function(selector){
		$(selector).each(function(){
			try{
				EasyuiDesotry.destroyInput($(this));
				$(this).panel("destroy");
			}catch(e){}			
		});
	},
	/**
	 * 销毁input
	 */
	destroyInput:function(parent_selector){
		//1.判断是否为空
		if(Extra.isEmpty(parent_selector)){
			return;
		}
		//2.删除input
		var selectorsObject = this.easyui_destory_selectors();
		for(var i in selectorsObject){
			$(parent_selector).find(selectorsObject[i]["selector"]).each(function(){
				try{
					$(this)[selectorsObject[i]["target"]](selectorsObject[i]["method"]);
				}catch(e){
					$(this).remove();
				}
			});
		}
	},
	/**
	 * 综合销毁
	 */
	destroy:function(parent_selector){
		//1.判断是否为空
		if(Extra.isEmpty(parent_selector)){
			return;
		}
		//2.删除窗体
		EasyuiDesotry.destroyWindow(parent_selector);
		//3.删除窗体之外的东西
		EasyuiDesotry.destroyInput(parent_selector);
	}
};