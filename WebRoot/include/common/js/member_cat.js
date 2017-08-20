//分类select
var catNum=0;
var div_id="selfcatDiv";

//设置分类默认DIV
function setDiv(c_div){	
	if(c_div!=null&&c_div!=""){
		div_id=c_div;		
	}	
}

//初始化加载分类框
function loadSelfCat(pidStr,num,c_div){
	setDiv(c_div);
	var pid=pidStr.split(",");
	//循环pid
	for(var i=0;i<pid.length;i++){
		if(pid[i]!=""){	
		    if(i>0){num=i;}	
		    //div_id的id  pid的下标	
			getCatList(div_id,pid[i],num);
			//设置属性
			$("#cat"+pid[i]).attr("value",pid[i+1]);
		}		
	}
}


//回选select列表时的加载分类框方法
function backSelfCat(pidStr,c_div){
	setDiv(c_div);
	var pid=pidStr.split(",");
	for(var i=0;i<pid.length;i++){
		if(pid[i]!=""){		
			getCatList(div_id,pid[i],i);
			$("#selfcat"+pid[i]).attr("value",pid[i+1]);
		}	
		catNum=i+1;
	}
}



//通过AJAX方式加载分类list列表,参数：1，div的id，2顶级分类id
function getCatList(div_id,pid,num){
	var dataUrl="/goods!childList.action?pId="+pid;
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
             	var catStr="<select id=\"selfcat"+pid+"\" class=\"select "+div_id+catNum+" \" name=\"self_cat_attr\" onchange=loadSelfCat(this.value,"+catNum+",'')>";
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

