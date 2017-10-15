/*
 * dialogId_:dialog的Id
 * options_={
 * 
 * 
 * 
 * }
 * */
function DialogExtra(dialogId_,options_){
	//1.定义一些常用的变量
	var _this = this;//当前function
	this.id = "#"+dialogId_;//dialog的Id
	
	//2.定义一些常用的方法
	
	/**
	 * 判断是否为空
	 * 分为两种判断：
	 * 第一种是传递一个参数,为string,boolean,number
	 * 第二种是传递二个参数,第一个参数为{},第二个为string(其实是一个键值)
	 */
	this.isEmpty = function(isEmpty_options,isEmpty_jsonkey){
		//1.先判断第一个参数
		if(isEmpty_options==undefined||isEmpty_options==null||isEmpty_options==''){
			return true;
		}
		//2.如果类型分为object,就需要用到第二个参数了
		if(typeof (isEmpty_options) == "object"){
			if(_this.isEmpty(isEmpty_jsonkey)){
				return false;
			}
			var _value = isEmpty_options[isEmpty_jsonkey];
			if(_this.isEmpty(_value)){
				return true;
			}
			//不为空
			return false;
		}
		//3.可能是string,number,boolean
		return false;
	}
	
	
	/**
	 * 初始化方法
	 * init_options={dialogId:"dialog的Id"}
	 */
	this.init = function(init_options){
		if(!_this.isEmpty(init_options,"dialogId")){			
			_this.id = _this.setId(init_options["dialogId"]);
		}
	}
	
	/**
	 * 设置dialog的Id
	 * 
	 */
	this.setId = function(setId_dialogId){
		if(!_this.isEmpty(setId_dialogId)){			
			_this.id = "#" + setId_dialogId;
		}
	}
	
	/**
	 * 获取dialog的属性信息
	 * 
	 */
	this.options = function(){
		return $(_this.id).dialog().dialog("options");
	}
	
	/**
	 * 打开dialog
	 */
	this.open = function(){
		$(_this.id).dialog('open');
		return false;
	}
	
	/**
	 * 关闭dialog
	 */
	this.close = function(){
		$(_this.id).dialog("close");
		return false;
	}
	
	/**
	 * 保存form表单信息
	 * selector=a[tag='save'];选择器
	 * 可扩展,
	 */
	this.save = function(save_a){
		//1.找到form表单(怎么找呢？【可以分为2中,一种是直接找,另一种是根据Id查找】;Id从什么地方而来呢？【从选择器的属性中来，属性名字叫做form_id】)
		//2.找到form表单,先选取第二种,如果没有form_id,再用第一中方法
		var $form;
		var form_id = $(save_a).attr('form_id');
		if(form_id==undefined){
			$form = $(_this.id).closest("div").find('form:eq(0)');
		}else{
			$form = $('#'+form_id);			
		}
		if($form==undefined){
			return;
		}
		//3.提交
		//valid
		//处理返回结果
		//提示
		//是否要关闭dialog
	}
	
	/**
	 * 更新form表单
	 * 可扩展,
	 */
	this.update = function(){
		
		_this.save();
	}
	
	
	/**
	 * 清空dialog中的内容
	 */
	this.clearForm = function(){
		
	}
	
	/**
	 * 关闭按钮事件绑定方法
	 * selector=[a[tag='close'],a[tag='closeAndTip']]
	 */
	this.btClose = function(){
		if(!_this.isEmpty(_this.options(),"buttons")){
			//绑定bt中的close事件,直接关闭dialog
			
			$(_this.options()["buttons"]).find("a[tag='close']").each(function(){
				if($(this).attr("bindclick")==undefined){					
					$(this).attr("bindclick",true).bind('click',function(){
						_this.close();
					});
				}
			});
			//绑定bt中的closeAndTip事件,关闭dialog并提示
			$(_this.options()["buttons"]).find("a[tag='closeAndTip']").each(function(){
				if($(this).attr("bindclick")==undefined){
					$(this).attr("bindclick",true).bind('click',function(){
						//TODO tip,提示
						//关闭窗体
						_this.close();
					});
				}
			});
			
		}else{
		}
	}
	
	/**
	 * 保存按钮事件绑定
	 * selector=a[tag='save'] or a[tag='update']
	 */
	this.btSave = function(){
		if(!_this.isEmpty(_this.options(),"buttons")){
			//绑定bt中的保存事件,
			$(_this.options()["buttons"]).find("a[tag='save']").each(function(){
				if($(this).attr("bindclick")==undefined){					
					$(this).attr("bindclick",true).bind('click',function(){
						_this.save($(this));
					});
				}
			});
			//绑定bt中的更新事件,
			$(_this.options()["buttons"]).find("a[tag='update']").each(function(){
				if($(this).attr("bindclick")==undefined){					
					$(this).attr("bindclick",true).bind('click',function(){
						_this.update($(this));
					});
				}
			});
		}		
	}
	
	/**
	 * Id是否为guid
	 */
	this.isIdGuid = function(){
		//	#dcafb407-96c9-8131-f35c-4849564b5833
		if(_this.id.length==37){
			var idArrs = _this.id.split("-");
			if(idArrs.length==5&&idArrs[0].length==9&&idArrs[1].length==4&&idArrs[2].length==4&&idArrs[3].length==4&&idArrs[4].length==12){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * dialog关闭时调用的方法
	 */
	this.onClose = function(){
		$(_this.id).dialog({
			onClose:function(){
				//当是自己动态创建的dialog的时候就给他删除了
				if(_this.isIdGuid()){
					$(_this.id).dialog("destroy");					
				}
			}
		});
	}
	
	/**
	 * 绑定事件方法
	 * eventName事件名称,
	 */
	this.bindEvent = function(eventName){
		if(_this.isEmpty(eventName)){
			return false;
		}
		switch(eventName){
			case "btClose"://绑定bt中的关闭按钮事件
				_this.btClose();
				break;
			case "btSave"://绑定bt中的保存按钮事件
				_this.btSave();
				break;
			case "onClose"://dialog关闭时触发的事件
				_this.onClose();
				break;
		}
		return false;
	}
	
	//3.绑定一些事件
	//3.1关闭标签绑定事件
	this.bindEvent("btClose");
	//3.2保存/更新标签绑定事件
	this.bindEvent("btSave");
	//绑定清空事件，在关闭dialog的时候
	this.bindEvent("onClose");
	//
	
}