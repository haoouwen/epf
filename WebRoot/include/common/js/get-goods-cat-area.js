//分类select
var catNumg=0;
var div_idg="goodscatDiv";
//设置分类默认DIV
function setgDiv(c_div){	
	if(c_div!=null&&c_div!=""){
		div_idg=c_div;		
	}	
}
//初始化加载分类框  回选select列表时的加载分类框方法
function loadGoodsCat(pidStr,num,c_div,module_type){
	if(!$('#search_cat_attr').val()){
	}else{
		pidStr=pidStr+","+$('#search_cat_attr').val();
	}
	setgDiv(c_div);
	var pid=pidStr.split(",");
	if(module_type==""){
		module_type="goods"
	}
	for(var i=0;i<pid.length;i++){
		if(pid[i]!=""){	
		    if(i>0){num=i;}		
			getGoodsCatList(div_idg,module_type,pid[i],num);
			$("#gcat"+pid[i]).attr("value",pid[i+1]);
		}		
	}
}
//初始化加载分类框  回选select列表时的加载分类框方法用于控制显示多少级别
function loadGoodsCatMore(pidStr,num,c_div,module_type,leve){
	if(!$('#search_cat_attr').val()){
	}else{
		pidStr=pidStr+","+$('#search_cat_attr').val();
	}
	setgDiv(c_div);
	var pid=pidStr.split(",");
	if(module_type==""){
		module_type="goods"
	}
	for(var i=0;i<pid.length;i++){
		if(pid[i]!=""){	
		    if(i>0){num=i;}		
			getGoodsCatListMore(div_idg,module_type,pid[i],num,leve);
			$("#gcat"+pid[i]).attr("value",pid[i+1]);
		}		
	}
}
//通过AJAX方式加载分类list列表可以用于控制显示多少级别
function getGoodsCatListMore(div_id,mod_type,pid,num,leve){
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
        	catNumg=num;
        	if(data.length>0){
        	    catNumg+=1;
             	var catStr="<select id=\"gcat"+pid+"\" class=\"select "+div_id+catNumg+" \" name=\"good_cat_attr\" ";
             	var c_leve="1";
				c_leve=data[0].cat_level;
             	if(leve!=c_leve){
             		catStr+="onchange=loadGoodsCatMore(this.value,"+catNumg+",'',\""+mod_type+"\","+leve+")";
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
function getGoodsCatList(div_id,mod_type,pid,num){
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
        	catNumg=num;
        	if(data.length>0){
        	    catNumg+=1;
             	var catStr="<select id=\"gcat"+pid+"\" class=\"select "+div_id+catNumg+" \" name=\"good_cat_attr\" onchange=loadGoodsCat(this.value,"+catNumg+",'',\""+mod_type+"\")>";
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
function getGoodsCatListSeCat(div_id,mod_type,pid,num){
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
        	catNumg=num;
        	if(data.length>0){
        	    catNumg+=1;
             	var catStr="<select id=\"gcat"+pid+"\" class=\"select "+div_id+catNumg+" \" name=\"good_cat_attr\" >";
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
//通过ajax获取品牌
function getBrandAllList(div_id){
  var dataUrl="/goods!ajaxbrandalllist.action";
	$.ajax({
        type: "post",
        url: dataUrl,
        datatype:"json",
        async:false,
        success: function(data){ 
        	data=eval("(" +data+")");
        	if(data.length>0){
             	var twStr="<select id=\"brand_name_s\"  name=\"brand_name_s\" >";
             	twStr+="<option value=\"\">请选择</option>";
             	for(var i=0;i<data.length;i++){
             		twStr+="<option value=\""+data[i].brand_id+"\">"+data[i].brand_name+"</option>\"";
             	}
             	twStr+="</select>";
             	$("#"+div_id).append(twStr);               	   		
        	}
        }	 
	}); 
}
//通过ajax获取贸易方式
function gettradeType(div_id){
    var dataUrl="/goods!ajaxtradewaylist.action";
	$.ajax({
        type: "post",
        url: dataUrl,
        datatype:"json",
        async:false,
        success: function(data){ 
        	data=eval("(" +data+")");
        	if(data.length>0){
             	var twStr="<select id=\"g_goods_depot_s\"  name=\"g_goods_depot_s\" >";
             	twStr+="<option value=\"\">请选择</option>";
             	for(var i=0;i<data.length;i++){
             		twStr+="<option value=\""+data[i].depot_id+"\">"+data[i].depot_name+"</option>\"";
             	}
             	twStr+="</select>";
             	$("#"+div_id).append(twStr);               	   		
        	}
        }	 
	}); 
}

//通过ajax获取国家
function gettopAreaType(div_id){
    var dataUrl="/goods!ajaxtoparealist.action";
	$.ajax({
        type: "post",
        url: dataUrl,
        datatype:"json",
        async:false,
        success: function(data){ 
        	data=eval("(" +data+")");
        	if(data.length>0){
             	var twStr="<select id=\"g_goods_place_s\"  name=\"g_goods_place_s\" >";
             	twStr+="<option value=\"\">请选择</option>";
             	for(var i=0;i<data.length;i++){
             		twStr+="<option value=\""+data[i].area_id+"\">"+data[i].area_name+"</option>\"";
             	}
             	twStr+="</select>";
             	$("#"+div_id).append(twStr);               	   		
        	}
        }	 
	}); 
}
//通过ajax获取商城活动
function gettopActive(div_id){
    var dataUrl="/goods!ajaxactivelist.action";
	$.ajax({
        type: "post",
        url: dataUrl,
        datatype:"json",
        async:false,
        success: function(data){ 
        	data=eval("(" +data+")");
        	if(data.length>0){
             	var twStr="<select id=\"g_goods_sale_id_s\"  name=\"g_goods_sale_id_s\" >";
             	twStr+="<option value=\"\">请选择</option>";
             	for(var i=0;i<data.length;i++){
             		twStr+="<option value=\""+data[i].sale_id+"\">"+data[i].sale_name+"</option>\"";
             	}
             	twStr+="</select>";
             	$("#"+div_id).append(twStr);               	   		
        	}
        }	 
	}); 
}




