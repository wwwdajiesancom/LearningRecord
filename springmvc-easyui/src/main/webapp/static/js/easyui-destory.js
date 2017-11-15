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
		
		//窗体类
		easyuiarrs.push({selector:"*.easyui-dialog",target:"dialog",method:"destroy",type:"div"});
		easyuiarrs.push({selector:"*.easyui-window",target:"window",method:"destroy",type:"div"});
		return easyuiarrs;
	},
	destroy:function(parent_selector){
		if(Extra.isEmpty(parent_selector)){
			return;
		}
		var selectorsObject = this.easyui_destory_selectors();
		for(var i in selectorsObject){
			$(parent_selector).find(selectorsObject[i]["selector"]).each(function(){
				try{
					$(this)[selectorsObject[i]["target"]](selectorsObject[i]["method"]);
				}catch(e){}
			});
		}
	}
};