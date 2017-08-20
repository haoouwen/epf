//列表选择默认
function changgedefault(id)
{
	$.ajax({
        type: "post",
        url: "/admin_Rate_changgeDefault.action?rId="+id,
        datatype:"json",
        async:false,
        success: function(data){ 
        }	 
	}); 
}
//是否启用汇率
function changeifuse(obj,id)
{
	var if_use="1";
	if($(obj).attr("checked")=="checked")
	{
		if_use="0"
	}
	$.ajax({
        type: "post",
        url: "/admin_Rate_changgeUse.action?rId="+id+"&isused="+if_use,
        datatype:"json",
        async:false,
        success: function(data){ 
        }	 
	}); 
}
