function createWaterFall(){
    var wf_title =$("#wf_title").val();
    var link_url =$("#link_url").val();
    var img_path =$("#uploadresult").val();
    var width =$("#width").val();
    var height =$("#height").val();
    var contants_width=$("#contants_width").val();
    var contants_height=$("#contants_height").val();
    if(contants_width==""){
    	jNotify("请填写容器宽度!");
    	return ;
    }
    if(contants_height==""){
    	jNotify("请填写容器高度!");
    	return ;
    }
    if(img_path==""){
    	jNotify("请先上传图片!");
    	return ;
    }
     if(width==""){
    	jNotify("请填写图片宽度!");
    	return ;
    }
     if(height==""){
    	jNotify("请填写图片高度!");
    	return ;
    }
    //设置容器大小
    $("#templateShow").css({ width: contants_width, height: contants_height });
    //瀑布编码
    var wfHtml="";
    wfHtml+="<div id='item0' class='item'>";
    wfHtml+="<a target='_blank' href='"+link_url+"'>";
   wfHtml+="<img src='"+img_path+"' width='"+width+"px' height='"+height+"px' alt='"+wf_title+"'/>";
	wfHtml+="</a>";
	wfHtml+="</div>";
    if($("#templateShow").find(".wf_main").length > 0 ){
    	var aleadyHtml = "";
    	$(".wf_main").find(".item").each(function(i){
    		aleadyHtml+="<div  id='item"+i+1+"'  class='item'>"+$(this).html()+"</div>";
    	})
    	wfHtml=aleadyHtml+wfHtml;
    } 
    wfHtml="<div class='wf_main' style='width:"+contants_width+"px;height:"+contants_height+"px;'>"+wfHtml+"</div>";
	$("#templateShow").html(wfHtml);
	//瀑布流
    $('.wf_main').imagesLoaded(function(){
		 $('.wf_main').masonry({
			itemSelector : '.item'
		 });
	 });
	//渲染
	removeItem();
}

//提交表单
function savetemplate(){
	//读取templateShow HTML标签
	var templateShowHtml = $("#templateShow").html();
	$("#template").val(templateShowHtml);
	$("#water").submit();
}


//初始化加载
$(document).ready(function(){
	var contants_width=$("#contants_width").val();
    var contants_height=$("#contants_height").val();
	if(contants_width!="" && contants_height!=""){
		$("#templateShow").css("width",contants_width);
		$("#templateShow").css("height",contants_height);
		$("#templateShow").css("border","1px solid #e1e2e3");
	}else{
		$("#templateShow").css("border","none");
	}
	//瀑布流
    $('.wf_main').imagesLoaded(function(){
		 $('.wf_main').masonry({
			itemSelector : '.item'
		});
	});
	//渲染
	removeItem();
});

//移除元素
function removeItem(){
	$(".item").hover(
	  function () {
	     var position =$(this).css("position");
	     var width =$(this).css("width");
	     var height =$(this).css("height");
	     var left = parseInt(width)-40;
	     var top = parseInt(height)-25;
	     $(this).append("<span class='remove'><img onclick='editIm(this);' src='/include/common/images/bj.gif' alt='修改'>&nbsp;</span>");
	     $(this).find(".remove").css({
	     	"position":position,
	     	"left":left,
	     	"top":top
	     });
	  },
	  function () {
	     $(this).find(".remove").remove();
	  });
}
//移除方法
function removeIm(obj){
	$(obj).closest(".item").remove();
	//瀑布流
    $('.wf_main').imagesLoaded(function(){
		 $('.wf_main').masonry({
			itemSelector : '.item'
		});
	});
	//赋值
	$("#template").val($("#templateShow").html()); 
}

//编辑方法
function editIm(obj){
	var item_id = $(obj).closest(".item").attr("id");
	var img_path = $("#"+item_id).find("img").attr("src");
	var len = img_path.indexOf("/uploads");
	var img_path = img_path.substring(len,img_path.length);
	var img_width=$("#"+item_id).find("img").attr("width");
	var img_height=$("#"+item_id).find("img").attr("height");
	var img_alt = $("#"+item_id).find("img").attr("alt");
	var a_href = $("#"+item_id).find("a").attr("href");
     //瀑布编码
    var wfHtml="<table width='100%' border='0' cellspacing='0'>"+
    "<tr ><td  class='searchDiv_td'>链接标题:</td>"+
		  "<td style='margin-left:-10px;'><input id='wf_title' name='wf_title'  style='width:200px;' onkeyup='checkLength(this,100);' maxlength='100'  cssStyle='width:300px;margin-left:-4px;' value='"+img_alt+"'/></td>"+
	"</tr>"+
	"<tr>"+
		"<td class='searchDiv_td'>链接地址:</td>"+
		"<td><input id='link_url' name='wf_link_url'   style='width:200px;' onkeyup='checkLength(this,200);' maxlength='200'  cssStyle='width:300px;margin-left:-4px;' value='"+a_href+"'/></td>"+
	"</tr>"+
	"<tr>"+
		"<td class='searchDiv_td'>图片上传:</td>"+
		"<td style='padding:0px;margin:0px;'>"+
 			"<table border='0' cellpadding='0' cellspacing='0' style='padding:0px;margin:0px;'>"+
         		"<tr style='padding:0px;margin:0px;'>"+
         			"<td style='padding:0px;margin:0px;'>"+
         			    "<div id='fileQueue'style='padding:0px;margin:0px;'></div>"+
              			"<input  id='uploadresult' name='wf_imgpath'   style='width:200px;' value='"+img_path+"'/>"+
         			"</td>"+
         			"<td style='padding-left:3px;'>"+
         				"<input type='file' name='uploadifyfile' id='uploadifyfile'/>"+
         			"</td>"+
              			"<script>uploadOneImg();</script>"+
         		"</tr>"+
         	" </table>"+
		 "</td>"+
	"</tr>"+
	"<tr>"+
		"<td class='searchDiv_td'>图片宽*高:</td>"+
		"<td>"+
 			"<input id='width' name='wf_width' style='width:30px;'    readonly='true' value='"+img_width+"'/>px * "+
 			"<input id='height' name='wf_height'  style='width:30px;'  readonly='true' value='"+img_height+"'/>px"+
 			"&nbsp;"+
 			"<input type='button' value='修改' onclick='updateWaterFall();' />"+
 			"<input type='hidden' id='alertid' value='"+item_id+"'/>"+
		"</td>"+
	"</tr>"+
	"</table>";
     
     $("#editDIV").html(wfHtml);
     //弹出层
     $("#editDIV").popup_search({move_top:0 ,p_height:"150px",pop_title:"修改布局"});

}

//修改首页图片
function updateWaterFall(){
	var wf_title =$("#wf_title").val();
    var link_url =$("#link_url").val();
    var img_path =$("#uploadresult").val();
    var width =$("#width").val();
    var height =$("#height").val();
    var contants_width=$("#contants_width").val();
    var contants_height=$("#contants_height").val();
    if(contants_width==""){
    	jNotify("请填写容器宽度!");
    	return ;
    }
    if(contants_height==""){
    	jNotify("请填写容器高度!");
    	return ;
    }
    if(img_path==""){
    	jNotify("请先上传图片!");
    	return ;
    }
     if(width==""){
    	jNotify("请填写图片宽度!");
    	return ;
    }
     if(height==""){
    	jNotify("请填写图片高度!");
    	return ;
    }
	var item_id =  $("#alertid").val();
	if(item_id==""){
		return;
	}
	$("#"+item_id).find("img").attr("src",$("#uploadresult").val());
	$("#"+item_id).find("img").attr("width",$("#width").val());
	$("#"+item_id).find("img").attr("height",$("#height").val());
	$("#"+item_id).find("img").attr("alt",$("#wf_title").val());
	$("#"+item_id).find("a").attr("href",$("#link_url").val());
	$("#alertid").val("");
	$("#editDIV").ccover();
}
function selected(){
  var template_id=$("#wf_code").val();
  if(template_id!=null&&template_id!=""){
    $.ajax({
      type:"post",
      url:"/admin_Template_getTemplateInfo.action?template_id="+template_id,
      async:false,
	  success: function(data){ 
		 $("#templateShow").html(data);
		 $("#showInfo").val(data);
		 removeItem();
	   }
    });
  }else{
  $("#templateShow").html(""); 
  }
}

//显示代码或者是效果图
function showEffact(o){
			if(o=="templateShowInfo"){
				$("#templateShowInfo").show();
				$("#showInfo").val($("#templateShow").html());
				$("#templateShow").hide();
				$("#dm").attr("class","selected");
				$("#xg").attr("class","");
			}else{
				$("#templateShowInfo").hide();
				$("#templateShow").show();
				$("#dm").attr("class","");
				$("#xg").attr("class","selected");
			}
}	


