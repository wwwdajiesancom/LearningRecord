$(function(){
	var columns = [[{
			field:"id",
		},{
			field:"ids",
			checkbox:true,
			formatter: function(value,row,index){
				return row["id"];
			}
		},{
			title:"名称",
			field:"name",
		},{
			title:"登录账户",
			field:"loginName",
		},{
			title:"创建时间",
			field:"createTime",
			formatter: function(value,row,index){
				return Extra.formatDate(value,"yyyy-MM-dd HH:mm:ss");
			}
		},{
			title:"更新时间",
			field:"updateTime",
			formatter: function(value,row,index){
				return Extra.formatDate(value,"yyyy-MM-dd HH:mm:ss");
			}
		},{
			title:"操作",
			field:"opt",
			formatter: function(value,row,index){
				var result = "";
				result += "<a href='#' tag='rowview' class='easyui-linkbutton' action='/jsp/datagrid/view/"+row['id']+"' params='title:城市详情;width:350;height:250;' >View</a>|";
				result += "<a href='#' class='easyui-linkbutton' buttons='update,close' action='/jsp/datagrid/update/"+row['id']+"' onClick='return Row.update(this);' params='title:更新;width:350;height:250;' >Update</a>|";
				result += "<a href='#' class='easyui-linkbutton' action='/ajax/datagrid/delete/"+row['id']+"' onClick='return Row.del(this);' params='width:350;height:250;' >Delete</a>";
				return result;
			}
		}
	]];
	
	var options = {
			title:"用户列表",
			columns:columns,
			fit:false,
			rownumbers:false,
	};
	
	var datagrid = new DatagridExtra("datagrid_table",options);
	
	var options2 = {
			title:"用户列表2",
			columns:columns,
			fit:false,
			rownumbers:false,
	};
	
	var datagrid2 = new DatagridExtra("datagrid_table2",options2);
	
});



