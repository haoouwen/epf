//分类select
var catNum=0;
var div_id="catDiv";
//地区select
var areaNum=0;
var area_div_id="areaDiv";
//设置分类默认DIV
function setDiv(c_div){	
	if(c_div!=null&&c_div!=""){
		div_id=c_div;		
	}	
}
//设置地区默认DIV
function setAreaDiv(a_div){	
	if(a_div!=null&&a_div!=""){
		area_div_id=a_div;		
	}	
}
//初始化加载分类框  回选select列表时的加载分类框方法
function loadCat(pidStr,num,c_div,module_type){
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
function loadArea(pidStr,num,a_div){
	setAreaDiv(a_div);
	var pid=pidStr.split(",");
	for(var i=0;i<pid.length;i++){
		if(pid[i]!=""){	
		    if(i>0){num=i;}		
			getAreaList(area_div_id,pid[i],num);
			$("#area"+pid[i]).attr("value",pid[i+1]);
		}		
	}
}


//初始化加载分类框  回选select列表时的加载分类框方法用于控制显示多少级别
function loadCatMore(pidStr,num,c_div,module_type,leve){
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
			getCatListMore(div_id,module_type,pid[i],num,leve);
			$("#cat"+pid[i]).attr("value",pid[i+1]);
		}		
	}
}
//通过AJAX方式加载分类list列表可以用于控制显示多少级别
function getCatListMore(div_id,mod_type,pid,num,leve){
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
             	var catStr="<select id=\"cat"+pid+"\" class=\"select "+div_id+catNum+" \" name=\"cat_attr\" ";
             	var c_leve="1";
				c_leve=data[0].cat_level;
             	if(leve!=c_leve){
             		catStr+="onchange=loadCatMore(this.value,"+catNum+",'',\""+mod_type+"\","+leve+")";
             	}
             	catStr+=">";
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
function getAreaList(area_div_id,pid,num){
	var dataUrl="/area!childList.action?pId="+pid;
	$.ajax({
        type: "post",
        url: dataUrl,
        datatype:"json",
        async:false,
        success: function(data){ 
        	data=eval("(" +data+")");
        	//清除某id后的全部元素
        	$("."+area_div_id+num).nextAll().remove();
        	//重新给分类select框个数重新赋值
        	areaNum=num;
        	if(data.length>0){
        	    areaNum+=1;
             	var areaStr="<select id=\"area"+pid+"\" class=\"select "+area_div_id+areaNum+" \" name=\"area_attr\" onchange=loadArea(this.value,"+areaNum+")>";
             	areaStr+="<option value=\"0\">请选择</option>";
             	for(var i=0;i<data.length;i++){
             		areaStr+="<option value=\""+data[i].area_id+"\">"+data[i].area_name+"</option>\"";
             	}
             	areaStr+="</select>";
             	$("#"+area_div_id).append(areaStr);               	   		
        	}
        }	 
	}); 
}


//初始化加载二级分类 点击不出现下级分类
function loadCatSeCat(pidStr,num,c_div,module_type){
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
			getCatListSeCat(div_id,module_type,pid[i],num);
			$("#cat"+pid[i]).attr("value",pid[i+1]);
		}		
	}
}
//通过AJAX方式加载分类list列表
function getCatListSeCat(div_id,mod_type,pid,num){
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
             	var catStr="<select id=\"cat"+pid+"\" class=\"select "+div_id+catNum+" \" name=\"cat_attr\" >";
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






