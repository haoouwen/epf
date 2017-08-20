//定义数据长度
var bar_length=0;
//更新进度值
var currce_index=0;
//判断是否索引已经在更新中
var is_update=0;
//模块名称
var mod="";
//保存页面是否被执行过
var is_run=false;
//页面初始值
var is_change=false;
//是否停止变量
var is_stop=false;
$(document).ready(function(){
	$("#loaderbar").jqm({
		overlay:30
	}); 
	//定时器
	setInterval(readyLoad,5000);
});


//全量更新选中模块索引
function updateAllIndex(field_name){
		// 值拼串
	var mod = '';	
	var checks = document.getElementsByName(field_name);
	for(var i=0;i<checks.length;i++){
		if(checks[i].checked){
			if(checks[i].value!=""){
				mod += checks[i].value+',';
			}
		}
	}
	// 删除最后一个字符
	mod = deleteLastChar(mod,",");
	//判断是否已选择模块
	if(mod==""){
	 	jNotify("请选择需要更新的索引模块!"); 
		return;
	}
	//初始长度
	$('#msg').progressBar(0);
	$("#successmsg").html("");
	//显示加载
	$("#updatebar").show();
	//判断是否已在更新索引
	is_update=ajaxUrl("/lucene!isup.action","",false);
	if(is_update==-1){
		jNotify("索引后台更新中,请等待!"); 
		if(!is_run){
			reloadprocess();
		}
		return;
	}
	//获取更新模块的批次长度
	bar_length=ajaxUrl("/lucene!dealListSize.action","?mod="+mod,false);
	//更新模块记录方法(根据开关选择索引方法)
	ajaxUrl("/lucene!updateIndex.action","?mod="+mod,true);
	//更新模块记录方法(采用solr)
	//ajaxUrl("/lucene!updateIndexBySolr.action","?mod="+mod_type,true);
	//初始化值
	is_run=false;
	currce_index=0;
	//显示停止更新按钮
	$("#cancelIndex").show();	
}

//读取进度
function readyLoad(){
	if(parseInt(currce_index)==-1){
		return;
	}
	if(parseInt(currce_index)!=bar_length){
		currce_index=ajaxUrl("/lucene!loadlength.action","",false);
	}else{
		if(!is_run){
			//清空变量
			changeBar(currce_index);
			is_run=true;
		}
		return;
	}
    if(parseInt(currce_index)>0){
    	changeBar(currce_index);
    }
}

//更新进度条改变
function changeBar(currce_index){
 	   var rate_index = (parseInt(currce_index))/bar_length;
 	   $("#msg").progressBar(rate_index*100);
       //$("#text").append((parseInt(currce_index))+"===="+bar_length+"===="+rate_index+"<br/>");
       if(rate_index==1){
        	$("#successmsg").html("&nbsp;<font color='green'>更新成功</font>");
        	//清空变量
        	successindex();
       }
}


//重新加载已更新进度
function reloadprocess(){
	bar_length=ajaxUrl("/lucene!dealListSize.action","?mod="+mod,false);
}

//停止更新索引
function stopindex(){
	$("#loaderbar").jqmShow();
	is_stop=false;
	ajaxUrl("/lucene!stopIndex.action","",true);//停止方法
	var msg = ajaxUrl("/lucene!stoprocess.action","",false);
	setInterval(stoprocess,5000);
	
}

//请求停止索引进度
function stoprocess(){
	if(!is_stop){
		var msg = ajaxUrl("/lucene!stoprocess.action","",false);
		if(msg==0){
			jSuccess("停止更新索引成功!");
			//隐藏停止更新按钮
			$("#cancelIndex").hide();	
			is_stop=true;
		}
		$("#loaderbar").jqmHide();
	}
}

//更新完成
function successindex(){
	ajaxUrl("/lucene!stopIndex.action","",false);
	//隐藏停止更新按钮
	$("#cancelIndex").hide();
}


//ajax请求公共方法
function ajaxUrl(url,mod,flag){
	var msg="";
	$.ajax({
          type: "post",
          url: url+mod,
          datatype:"json",
          async:flag,
          success: function(data){ 
          	  msg=data;
		  }	 
	});
	return msg;
}

//增量更新索引
function createAddIndex(field_name){
	// 值拼串
	var mod = '';	
	var checks = document.getElementsByName(field_name);
	for(var i=0;i<checks.length;i++){
		if(checks[i].checked){
			if(checks[i].value!=""){
				mod += checks[i].value+',';
			}
		}
	}
	// 删除最后一个字符
	mod = deleteLastChar(mod,",");
	//判断是否已选择模块
	if(mod==""){
	 	jNotify("请选择需要更新的索引模块!"); 
		return;
	}
	$("#successmsg").html("");
	$("#loaderbar").jqmShow();
	//更新模块记录方法
	var msg=ajaxUrl("/lucene!isAddIndex.action","?mod="+mod,false);
	//var msg=ajaxUrl("/lucene!updateAddIndexBySolr.action","?mod="+mod,false);
	if(msg=='0'){
		$("#loaderbar").jqmHide();
		jSuccess("增量更新索引成功!");
	}
}
