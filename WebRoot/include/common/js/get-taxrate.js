//税率select
var tarNum=0;
var tax_div_id="taxDiv";
var tax_attr="tax_attr";
//设置税率默认DIV
function setAreaDiv(t_div){	
	if(t_div!=null&&t_div!=""){
	if('taxDiv_s'==t_div){
		tax_attr="tax_attr_s";
	}
		tax_div_id=t_div;		
	}	
}
//初始化加载税率框 回选select列表时的加载税率框方法
function loadTax(pidStr,num,t_div){
	setAreaDiv(t_div);
	var pid=pidStr.split(",");
	for(var i=0;i<pid.length;i++){
		if(pid[i]!=""){	
		    if(i>0){num=i;}		
			getTaxList(tax_div_id,pid[i],num);
			$("#tax"+pid[i]).attr("value",pid[i+1]);
		}		
	}
}

//通过AJAX方式加载分类list列表
function getTaxList(tax_div_id,pid,num){
	var dataUrl="/taxrate!childList.action?pId="+pid;
	$.ajax({
        type: "post",
        url: dataUrl,
        datatype:"json",
        async:false,
        success: function(data){ 
        	data=eval("(" +data+")");
        	//清除某id后的全部元素
        	$("."+tax_div_id+num).nextAll().remove();
        	//重新给分类select框个数重新赋值
        	tarNum=num;
        	if(data.length>0){
        	    tarNum+=1;
             	var taxStr="<select id=\"tax"+pid+"\" class=\"select "+tax_div_id+tarNum+" \" name="+tax_attr+" onchange=loadTax(this.value,"+tarNum+")>";
             	taxStr+="<option value=\"0\">请选择</option>";
             	for(var i=0;i<data.length;i++){
             		taxStr+="<option value=\""+data[i].tax_id+"\">"+data[i].tax_name+"("+data[i].tax_rate+"%)"+"</option>\"";
             	}
             	taxStr+="</select>";
             	$("#"+tax_div_id).append(taxStr);               	   		
        	}
        }	 
	}); 
}







