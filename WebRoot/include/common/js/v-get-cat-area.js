//分类select
var catNum=0;
var div_id="catDiv";
//地区select
var areaNum=0;
var v_area_div_id="v_areaDiv";
//设置分类默认DIV
function setDiv(c_div){	
	if(c_div!=null&&c_div!=""){
		div_id=c_div;		
	}	
}
//设置地区默认DIV
function setAreaDiv(a_div){	
	if(a_div!=null&&a_div!=""){
		v_area_div_id=a_div;		
	}	
}
//初始化加载分类框  回选select列表时的加载分类框方法
function loadCat(pidStr,num,c_div,module_type){
	if(!$('#search_cat_attr').val()){
	}else{
		pidStr=pidStr+","+$('#search_cat_attr').val();
	}
	setDiv(c_div);
	var pid=pidStr.split(",");
	if(module_type==""){
		module_type="goods"
	}
	for(var i=0;i<pid.length;i++){
		if(pid[i]!=""){	
		    if(i>0){num=i;}		
			getCatList(div_id,module_type,pid[i],num);
			$("#cat"+pid[i]).attr("value",pid[i+1]);
		}		
	}
}
//初始化加载地区框 回选select列表时的加载地区框方法
function v_loadArea(pidStr,num,a_div){
	if(!$('#search_area_attr').val()){
	}else{
		pidStr=pidStr+","+$('#search_area_attr').val();
	}
	setAreaDiv(a_div);
	var pid=pidStr.split(",");
	for(var i=0;i<pid.length;i++){
		if(pid[i]!=""){	
		    if(i>0){num=i;}		
			v_getAreaList(v_area_div_id,pid[i],num);
			$("#v_area"+pid[i]).attr("value",pid[i+1]);
		}		
	}
}



//通过AJAX方式加载分类list列表
function getCatList(div_id,mod_type,pid,num){
	var dataUrl="/category!childList.action?pId="+pid+"&mod_type="+mod_type;
	$.ajax({
        type: "post",
        url: dataUrl,
        datatype:"json",
        async:false,
        success: function(data){ 
        	data=eval("(" +data+")");
        	//清除某id后的全部元素
        	$("."+div_id+num).nextAll().remove();
        	//重新给分类select框个数重新赋值
        	catNum=num;
        	if(data.length>0){
        	    catNum+=1;
             	var catStr="<select id=\"cat"+pid+"\" class=\"select "+div_id+catNum+" \" name=\"cat_attr\" onchange=loadCat(this.value,"+catNum+",'',\""+mod_type+"\")>";
             	catStr+="<option value=\"0\">请选择</option>";
             	for(var i=0;i<data.length;i++){
             		catStr+="<option value=\""+data[i].cat_id+"\">"+data[i].cat_name+"</option>\"";
             	}
             	catStr+="</select>";
             	$("#"+div_id).append(catStr);               	   		
        	}
        }	 
	}); 
}

//通过AJAX方式加载分类list列表
function v_getAreaList(v_area_div_id,pid,num){
	var dataUrl="/area!childList.action?pId="+pid;
	$.ajax({
        type: "post",
        url: dataUrl,
        datatype:"json",
        async:false,
        success: function(data){ 
        	data=eval("(" +data+")");
        	//清除某id后的全部元素
        	$("."+v_area_div_id+num).nextAll().remove();
        	//重新给分类select框个数重新赋值
        	areaNum=num;
        	if(data.length>0){
        	    areaNum+=1;
             	var v_areaStr="<select id=\"v_area"+pid+"\" class=\"select "+v_area_div_id+areaNum+" \" name=\"v_area_attr\" onchange=v_loadArea(this.value,"+areaNum+")>";
             	v_areaStr+="<option value=\"0\">请选择</option>";
             	for(var i=0;i<data.length;i++){
             		v_areaStr+="<option value=\""+data[i].area_id+"\">"+data[i].area_name+"</option>\"";
             	}
             	v_areaStr+="</select>";
             	$("#"+v_area_div_id).append(v_areaStr);               	   		
        	}
        }	 
	}); 
}

