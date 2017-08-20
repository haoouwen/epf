//初始化加载
$(document).ready(function(){
	//选择商品规格(标签)
    $(".specstr > .selsize > a").click(function(){
    	var css = $(this).attr("class");
    	var id = $(this).attr("id");
    	//禁止点击
    	if(css == "disable"){
    		return;
    	}
    	//规格串
	    var specstr_str = "";
    	if(css == "checked"){
    		$(this).removeClass();
    	}else{
			$(this).prevAll().removeClass();
    		$(this).nextAll().removeClass();
    		$(this).addClass("checked");
    	}
    	//遍历选中规格
    	$(".specstr > p > a").each(function(){
    		if($(this).attr("class") == "checked" || $(this).children("i").attr("class") == "checked"){
    			var id = $(this).attr("id");
    			if(specstr_str == ""){
    				specstr_str = id;
    			}else{
    				specstr_str = specstr_str+","+id;
    			}
    		}
    	});
    	$("#spec_id_str").val(specstr_str);
    });
    //选择商品规格(图片)
    $(".specstr > .selcolor > a").click(function(){
    	var css = $(this).children("i").attr("class");
    	var id = $(this).children("i").attr("id");
		//禁止点击
    	if($(this).attr("class") == "disable"){
    		return;
    	}
    	//规格串
	    var specstr_str = "";
    	if(css == "checked"){
    		$(this).children("i").removeClass();
    	}else{
			$(this).prevAll().children("i").removeClass();
    		$(this).nextAll().children("i").removeClass();
    		$(this).children("i").addClass("checked");
    	}
    	//遍历选中规格
    	$(".specstr > p > a").each(function(){
    		if($(this).attr("class") == "checked" || $(this).children("i").attr("class") == "checked"){
    			var id = $(this).attr("id");
    			if(specstr_str == ""){
    				specstr_str = id;
    			}else{
    				specstr_str = specstr_str+","+id;
    			}
    		}
    	});
    	$("#spec_id_str").val(specstr_str);
    });
});
