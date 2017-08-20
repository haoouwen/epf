//地区select
var areasNum=0;
var areas_div_id="areaDiv";
//设置地区默认DIV
function setAreasDiv(a_div){	
	if(a_div!=null&&a_div!=""){
		areas_div_id=a_div;		
	}	
}
//初始化加载地区框 回选select列表时的加载地区框方法
function loadAreas(pidStr,num,a_div){
	if(!$('#search_area_attr').val()){
	}else{
		pidStr=pidStr+","+$('#search_area_attr').val();
	}
	setAreasDiv(a_div);
	var pid=pidStr.split(",");
	for(var i=0;i<pid.length;i++){
		if(pid[i]!=""){	
		    if(i>0){num=i;}		
			getAreasList(areas_div_id,pid[i],num);
			$("#area"+pid[i]).attr("value",pid[i+1]);
		}		
	}
}

//通过AJAX方式加载分类list列表
function getAreasList(areas_div_id,pid,num){
	var dataUrl="/area!childList.action?pId="+pid;
	$.ajax({
        type: "post",
        url: dataUrl,
        datatype:"json",
        async:false,
        success: function(data){ 
        	data=eval("(" +data+")");
        	//清除某id后的全部元素
        	$("."+areas_div_id+num).nextAll().remove();
        	//重新给分类select框个数重新赋值
        	areasNum=num;
        	if(data.length>0){
        	    areasNum+=1;
             	var areaStr="<select id=\"areas"+pid+"\" class=\"select "+areas_div_id+areasNum+" \" name=\"area_attr\" onchange=loadAreas(this.value,"+areasNum+")>";
             	areaStr+="<option value=\"0\">请选择</option>";
             	for(var i=0;i<data.length;i++){
             		areaStr+="<option value=\""+data[i].area_id+"\">"+data[i].area_name+"</option>\"";
             	}
             	areaStr+="</select>";
             	$("#"+areas_div_id).append(areaStr);               	   		
        	}
        }	 
	}); 
}

