//主要是为了销毁一些垃圾
var EasyuiDesotry = {
	//需要销毁组件的列表
	easyui_destory_selectors:function(){
		var easyuiarrs = [];		
		//form表单中的
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
		
		//窗体类,因为窗体中无法包含窗体,导致dialog,window都不在原有的地方
		//easyuiarrs.push({selector:"*.easyui-dialog",target:"dialog",method:"destroy",type:"div"});
		//easyuiarrs.push({selector:"*.easyui-window",target:"window",method:"destroy",type:"div"});
		return easyuiarrs;
	},
	/**
	 * 这个是panel里面的一个方法,在ajax调用成功之后返回的text
	 */
	extractor:function(data){
		//从data中找到相关的数据window数据
		var $div = $("<div></div>");
		//将data加进去
		$div.append(data);
		
		//找到.easyui-dialog,.easyui-window
		var ids = [];
		$div.find(".easyui-dialog,.easyui-window").each(function(){var id=$(this).attr("id");if(Extra.isEmpty(id))id=Extra.guid();ids.push("#"+id);});
		$div.remove();
		return data+"<div autoDestroyWindow='"+ids.toString()+"'></div>";
	},
	/**
	 * 调用这个函数的时候,需要和函数EasyuiDesotry.extractor一块使用,这个是收集的用处
	 */
	destroyWindow:function(parent_selector){
		//1.判断是否为空
		if(Extra.isEmpty(parent_selector)){
			return;
		}
		//2.删除窗体
		$(parent_selector).find("div[autoDestroyWindow]").each(function(){
			//找到相关的Id
			var autoDestroyWindow = $(this).attr("autoDestroyWindow");
			if(!Extra.isEmpty(autoDestroyWindow)){
				var ids = autoDestroyWindow.split(",");
				for(var index in ids){
					EasyuiDesotry.destroyGeneric(ids[index]);
				}
			}
		});
	},
	destroyGeneric:function(selector){
		$(selector).each(function(){
			try{
				EasyuiDesotry.destroyInput($(this));
				$(this).panel("destroy");
			}catch(e){}			
		});
	},
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