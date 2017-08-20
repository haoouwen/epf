function createTemplate(){
    var wf_title =$("#wf_title").val();
    var link_url =$("#link_url").val();
    var img_path =$("#uploadresult").val();
    var width =$("#width").val();
    var height =$("#height").val();
    var contants_width=$("#contants_width").val();
    var contants_height=$("#contants_height").val();
    if(contants_width==""){
    	jNotify("è¯·å¡«åå®¹å¨å®½åº¦!");
    	return ;
    }
    if(contants_height==""){
    	jNotify("è¯·å¡«åå®¹å¨é«åº¦!");
    	return ;
    }
    if(img_path==""){
    	jNotify("è¯·åä¸ä¼ å¾ç!");
    	return ;
    }
     if(width==""){
    	jNotify("è¯·å¡«åå¾çå®½åº¦!");
    	return ;
    }
     if(height==""){
    	jNotify("è¯·å¡«åå¾çé«åº¦!");
    	return ;
    }
    //è®¾ç½®å®¹å¨å¤§å°
    $("#templateShow").css({ width: contants_width, height: contants_height });
    //çå¸ç¼ç 
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
	$("#showInfo").val(wfHtml);
	//çå¸æµ
    $('.wf_main').imagesLoaded(function(){
		 $('.wf_main').masonry({
			itemSelector : '.item'
		 });
	 });
	//æ¸²æ
	removeItem();
	$("#editDIV").ccover();
}

function showAddDiv(){
 //çå¸ç¼ç 
    var wfHtml="<table width='100%' border='0' cellspacing='0'>"+
    "<tr ><td  class='searchDiv_td'>é¾æ¥æ é¢:</td>"+
		  "<td style='margin-left:-10px;'><input id='wf_title' name='wf_title'  style='width:200px;' onkeyup='checkLength(this,100);' maxlength='100'  cssStyle='width:300px;margin-left:-4px;'/></td>"+
	"</tr>"+
	"<tr>"+
		"<td class='searchDiv_td'>é¾æ¥å°å:</td>"+
		"<td><input id='link_url' name='wf_link_url'  style='width:200px;' onkeyup='checkLength(this,200);' maxlength='200'  cssStyle='width:300px;margin-left:-4px;'/></td>"+
	"</tr>"+
	"<tr>"+
		"<td class='searchDiv_td'>å¾çä¸ä¼ :</td>"+
		"<td style='padding:0px;margin:0px;'>"+
 			"<table border='0' cellpadding='0' cellspacing='0' style='padding:0px;margin:0px;'>"+
         		"<tr style='padding:0px;margin:0px;'>"+
         			"<td style='padding:0px;margin:0px;'>"+
         			    "<div id='fileQueue'style='padding:0px;margin:0px;'></div>"+
              			"<input  id='uploadresult' name='wf_imgpath'   style='width:200px;'/>"+
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
		"<td class='searchDiv_td'>å¾çå®½*é«:</td>"+
		"<td>"+
 			"<input id='width' name='wf_width' style='width:30px;'/>px * "+
 			"<input id='height' name='wf_height'  style='width:30px;'/>px"+
 			"&nbsp;"+
 			"<input type='button' value='çæ' onclick='createTemplate();' />"+
 			"<input type='hidden' id='alertid'/>"+
		"</td>"+
	"</tr>"+
	"</table>";
     
     $("#editDIV").html(wfHtml);
     //å¼¹åºå±
     $("#editDIV").popup_search({move_top:0 ,p_height:"150px",pop_title:"æ°å¢å¸å±"});
}


//æäº¤è¡¨å
function savetemplate(){
	//è¯»åtemplateShow HTMLæ ç­¾
	var templateShowHtml = $("#templateShow").html();
	$("#template").val(templateShowHtml);
	$("#water").submit();
}


//åå§åå è½½
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
	//çå¸æµ
    $('.wf_main').imagesLoaded(function(){
		 $('.wf_main').masonry({
			itemSelector : '.item'
		});
	});
	//æ¸²æ
	removeItem();
});

//ç§»é¤åç´ 
function removeItem(){
	$(".item").hover(
	  function () {
	     var position =$(this).css("position");
	     var width =$(this).css("width");
	     var height =$(this).css("height");
	     var left = parseInt(width)-40;
	     var top = parseInt(height)-25;
	     $(this).append("<span class='remove'><img onclick='editIm(this);' src='/include/common/images/bj.gif' alt='ä¿®æ¹'>&nbsp;<img onclick='removeIm(this);' src='/include/common/images/delete.png' alt='ç§»é¤'/></span>");
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
//ç§»é¤æ¹æ³
function removeIm(obj){
	$(obj).closest(".item").remove();
	//çå¸æµ
    $('.wf_main').imagesLoaded(function(){
		 $('.wf_main').masonry({
			itemSelector : '.item'
		});
	});
	//èµå¼
	$("#template").val($("#templateShow").html()); 
}

//ç¼è¾æ¹æ³
function editIm(obj){
	var item_id = $(obj).closest(".item").attr("id");
	var img_path = $("#"+item_id).find("img").attr("src");
	var len = img_path.indexOf("/uploads");
	var img_path = img_path.substring(len,img_path.length);
	var img_width=$("#"+item_id).find("img").attr("width");
	var img_height=$("#"+item_id).find("img").attr("height");
	var img_alt = $("#"+item_id).find("img").attr("alt");
	var a_href = $("#"+item_id).find("a").attr("href");
	//çå¸ç¼ç 
    var wfHtml="<table width='100%' border='0' cellspacing='0'>"+
    "<tr ><td  class='searchDiv_td'>é¾æ¥æ é¢:</td>"+
		  "<td style='margin-left:-10px;'><input id='wf_title' name='wf_title'  style='width:200px;' onkeyup='checkLength(this,100);' maxlength='100'  cssStyle='width:300px;margin-left:-4px;' value='"+img_alt+"'/></td>"+
	"</tr>"+
	"<tr>"+
		"<td class='searchDiv_td'>é¾æ¥å°å:</td>"+
		"<td><input id='link_url' name='wf_link_url'   style='width:200px;' onkeyup='checkLength(this,200);' maxlength='200'  cssStyle='width:300px;margin-left:-4px;' value='"+a_href+"'/></td>"+
	"</tr>"+
	"<tr>"+
		"<td class='searchDiv_td'>å¾çä¸ä¼ :</td>"+
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
		"<td class='searchDiv_td'>å¾çå®½*é«:</td>"+
		"<td>"+
 			"<input id='width' name='wf_width' style='width:30px;'     value='"+img_width+"'/>px * "+
 			"<input id='height' name='wf_height'  style='width:30px;'   value='"+img_height+"'/>px"+
 			"&nbsp;"+
 			"<input type='button' value='ä¿®æ¹' onclick='updateTemplate();' />"+
 			"<input type='hidden' id='alertid' value='"+item_id+"'/>"+
		"</td>"+
	"</tr>"+
	"</table>";
     
     $("#editDIV").html(wfHtml);
     //å¼¹åºå±
     $("#editDIV").popup_search({move_top:0 ,p_height:"150px",pop_title:"ä¿®æ¹å¸å±"});
}

//ä¿®æ¹é¦é¡µå¾ç
function updateTemplate(){
	var wf_title =$("#wf_title").val();
    var link_url =$("#link_url").val();
    var img_path =$("#uploadresult").val();
    var width =$("#width").val();
    var height =$("#height").val();
    var contants_width=$("#contants_width").val();
    var contants_height=$("#contants_height").val();
    if(contants_width==""){
    	jNotify("è¯·å¡«åå®¹å¨å®½åº¦!");
    	return ;
    }
    if(contants_height==""){
    	jNotify("è¯·å¡«åå®¹å¨é«åº¦!");
    	return ;
    }
    if(img_path==""){
    	jNotify("è¯·åä¸ä¼ å¾ç!");
    	return ;
    }
     if(width==""){
    	jNotify("è¯·å¡«åå¾çå®½åº¦!");
    	return ;
    }
     if(height==""){
    	jNotify("è¯·å¡«åå¾çé«åº¦!");
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

//æ¾ç¤ºä»£ç æèæ¯ææå¾
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


