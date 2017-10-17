/**
 * 定义列类
 * @param title
 * @param field
 * @param width
 * @param options_ {}
 * @returns
 */
function DatagridColumn(title,field,width,options_){
	var column = {};
	if(!Extra.isEmpty(title)){column["title"]=title;}//名称
	if(!Extra.isEmpty(field)){column["field"]=field;}//字段名称
	if(!Extra.isEmpty(width)){column["width"]=Extra.int(width);}//列宽
	
	//2.一些默认值修改
	column["align"] = "center";
	
	//3.从options_中获取
	if(!Extra.isEmpty(options_)){for(var key in options_){column[key]=options_[key];}};
	
	return column;
}
/**
 * 自定义
 * @param datagridId
 * @param options_
 * @returns
 */
function DatagridExtra(datagridId,options_){
	
	
	
}



