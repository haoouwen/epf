//新增分类 
function com_add_cat(){
     	 var add_cat_id=""; 
		 var add_cat_text="";	  
		 var sel_count=$("input:hidden[name='all_cat_id_str']").size();   	     
	     $(".select").each(function(){	   
       		    var sel_val =$(this).val();
       		    var sel_text=$(this).find("option:selected").text();
       		    //去掉请选择
       		    if(sel_val!="0"){
       		     	 add_cat_id+=sel_val+",";
       		    	 add_cat_text+=sel_text+",";		           		     
       		    }       		    
	     });	
	     //去除最后的一个逗号
	     var id_len=add_cat_id.lastIndexOf(",");
	     var text_len=add_cat_text.lastIndexOf(",");
	     if(id_len>0){
	     	add_cat_id=add_cat_id.substring(0,id_len);
	     }
	     if(text_len>0){
	     	add_cat_text=add_cat_text.substring(0,text_len);
	     }
	     //判断是否已经添加过该ID
	     var count=0;
	     if(add_cat_text!=""){	           		         
	         $("input:hidden[name='all_cat_id_str']").each(function(){	         
	               if($.trim($(this).val())==add_cat_id){	           		               	  
           		    	 count++;	           		               
	               }	               
	         });	           		     
	     }
	     //判断添加的个数是否达到上限
	     if(count>0){//如果已添加则提示信息,否则则添加成功
	      	jNotify("您已添加该分类!");  	           		        
	     }else{
	        if(add_cat_text!=""){
	        	 if($("#show_add_cat").length==0){
	        	 	 $(".morecatdiv").append(" <div id='show_add_cat' class='show_add_cat'>");
	        	 }
   		     	 $("#show_add_cat").append("<span><a class='oper' href='###' onclick='del_add_cat(this)'><img src='/include/admin/images/delete.png'></a><a href='#'>"+add_cat_text+"</a><input type='hidden' name='all_cat_id_str' value=\""+add_cat_id+"\"/> </span>");
   		         jSuccess("新增分类成功!");  
   		    }else{
   		         jNotify("请选择分类!");  
   		    }
	     }
	}         
//删除分类
function del_add_cat(obj){
		$(obj).parent("span").remove();    
		jSuccess("删除成功!");      	          		
}	
//瀑布布局

//提交表单
function savetemplate(){
	//读取templateShow HTML标签
	var templateShowHtml = $("#templateShow").html();
	$("#template").val(templateShowHtml);
	$("#water").submit();
}


//初始化加载
$(document).ready(function(){
	 $("#ntTabDivId").setTab("ntTitle","tabDiv","selli");
	 var tip_html="";
	 if($("#uploadresult_ad").val()==null||$("#uploadresult_ad").val()==""){
	       tip_html="<img src='/include/common/images/selectfalse.png'    title='请上传图片'  />";
	 }else{
	       tip_html="<img src='/include/common/images/success.png'  title='已经上传图片,可以点击预览查看图片' />";
	 }
	 $("#show_pic_tip").html(tip_html);
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

 //提交表单
function submitinfo(){
   jConfirm('您确定要提交保存？', '系统提示',function(r){ 
 		if(r){ 
			 if($("#uploadresult_ad").val()==null||$("#uploadresult_ad").val()==""){
			       jNotify("请上传左上角第一张广告!");
			       return false;
			   }
			   if($("#tab0_pos1_url").val()==null||$("#tab0_pos1_url").val()==""){
			       jNotify("请上传左上角第一张广告的链接地址,如果没有地址,请输入#");
			       return false;
			   }
			   //获取分类
			   var cat_attr_str="";
			   $("input:hidden[name='all_cat_id_str']").each(function(){
			       cat_attr_str+=$(this).val()+"|";
			   }) 
			   if(cat_attr_str==null||cat_attr_str==""){
			       jNotify("请选择分类!");
			       return false;
			   }else{
			     cat_attr_str=cat_attr_str.substring(0,cat_attr_str.length-1);
			   	 $("#cat_attr_str").val(cat_attr_str); 
			    }  
			    $("#modiy_form").submit();
			    return true;
		 }
    });  
  
}

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
	var img_width_height=$("#"+item_id).find(" div input").val();
	var img_number=$("#img_number_"+item_id).val();
	var wfHtml="";
	if(img_number!=null&&img_number!=""&&img_number>1){
	 //多个广告位的编辑
	  wfHtml=updateMoreImg(img_width_height,item_id,img_number);
	}else{
	    //单个广告位的编辑
		var img_path = $("#img_"+item_id).val();
		var len = img_path.indexOf("/uploads");
		var img_path = img_path.substring(len,img_path.length);
		var img_alt = $("#title_"+item_id).val();
		var a_href = $("#href_"+item_id).val();
	    wfHtml=updateOneImg(img_width_height,img_alt,a_href,img_path,item_id);
	}
     $("#editDIV").html(wfHtml);
     //弹出层
     $("#editDIV").popup_search({move_top:0 ,p_height:"150px",pop_title:"编辑广告信息"});
}

//编辑单个图片链接
function updateMoreImg(img_width_height,item_id,img_number){
    //瀑布编码
    var wfHtml="<table width='100%' border='0' cellspacing='0' >"+
   	"<tr >"+
	"<td class='searchDiv_td'>图片大小:</td>"+
	"<td><font color='red'>["+img_width_height+"]</font> [宽*高]</td>"+
	"</tr>";
	for(i=1;i<=img_number;i++){
	    var img_path = $("#img_"+i+"_"+item_id).val();
		var len = img_path.indexOf("/uploads");
		var img_path = img_path.substring(len,img_path.length);
		var img_alt = $("#title_"+i+"_"+item_id).val();
		var a_href = $("#href_"+i+"_"+item_id).val();
		 wfHtml+="<tr ><td  class='searchDiv_td'>链接标题"+i+":</td>"+
			  "<td style='margin-left:-10px;'><input id='wf_title"+i+"' name='wf_title'  style='width:200px;' onkeyup='checkLength(this,100);' maxlength='100'  cssStyle='width:300px;margin-left:-4px;' value='"+img_alt+"'/></td>"+
		"</tr>"+
		"<tr>"+
			"<td class='searchDiv_td'>链接地址"+i+":</td>"+
			"<td><input id='link_url"+i+"' name='wf_link_url'   style='width:200px;' onkeyup='checkLength(this,200);' maxlength='200'  cssStyle='width:300px;margin-left:-4px;' value='"+a_href+"'/>(没有链接,请输入#)</td>"+
		"</tr>"+
		"<tr>"+
			"<td class='searchDiv_td'>图片上传"+i+":</td>"+
			"<td style='padding:0px;margin:0px;'>"+
	 			"<table border='0' cellpadding='0' cellspacing='0' style='padding:0px;margin:0px;'>"+
	         		"<tr style='padding:0px;margin:0px;'>"+
	         			"<td style='padding:0px;margin:0px;'>"+
	         			    "<div id='fileQueue00"+i+"'style='padding:0px;margin:0px;'></div>"+
	              			"<input  id='uploadresult00"+i+"' name='wf_imgpath'   style='width:200px;' value='"+img_path+"'/>"+
	         			"</td>"+
	         			"<td style='padding-left:3px;'>"+
	         				"<input type='file' name='uploadifyfile' id='uploadifyfile00"+i+"'/>"+
	         			"</td>"+
	              			"<script>sysUploadImg('uploadifyfile00"+i+"','uploadresult00"+i+"','fileQueue00"+i+"');</script>"+
	         		"</tr>"+
	         	" </table>"+
			 "</td>"+
		"</tr>";
	}
	wfHtml+="<td colspan='2' style='text-align: center;'><input type='button' value='确定修改' onclick='updateWaterFallAll("+img_number+");' />&nbsp;&nbsp;&nbsp;<input type='button' value='关闭' onclick='closef();' /><input type='hidden' id='alertid' value='"+item_id+"'/></td>"+
	"</tr>"+
	"</table>";
  return wfHtml;
}

//修改多张首页图片
function updateWaterFallAll(img_number){
    for(i=1;i<=img_number;i++){
	    var wf_title =$("#wf_title"+i).val();
	    var link_url =$("#link_url"+i).val();
	    var img_path =$("#uploadresult00"+i).val();
	    if(wf_title==""){
	    	jNotify("请填写链接标题"+i+"!");
	    	return ;
	    }
	    if(link_url==""){
	    	jNotify("请输入链接地址"+i+"");
	    	return ;
	    }
	    if(img_path==""){
	    	jNotify("请先上传图片"+i+"!");
	    	return ;
	    }
    }
	var item_id =  $("#alertid").val();
	if(item_id==""){
		return;
	}
	var all_img_path="";
	for(i=1;i<=img_number;i++){
	  var wf_title=$("#wf_title"+i).val();
	  var link_url =$("#link_url"+i).val();
      var img_path =$("#uploadresult00"+i).val();
	  if(i==1){
        //默认显示第一张图片
    	$("#"+item_id).find("img").attr("src",img_path);
		$("#"+item_id).find("img").attr("title",wf_title);
	  }
	  $("#href_"+i+"_"+item_id).val(link_url);
	  $("#img_"+i+"_"+item_id).val(img_path);
	  $("#title_"+i+"_"+item_id).val(wf_title);
	  all_img_path=all_img_path+img_path+",";
	}
	if(all_img_path!=null&&all_img_path!=""){
	  all_img_path=all_img_path.substring(0,all_img_path.length-1);
	}
	$("#img_"+item_id).val(all_img_path);
	$("#alertid").val("");
	$("#editDIV").ccover();
}
function closef(){
$("#alertid").val("");
$("#editDIV").ccover();
}




//编辑单个图片链接
function updateOneImg(img_width_height,img_alt,a_href,img_path,item_id){
    //瀑布编码
    var wfHtml="<table width='100%' border='0' cellspacing='0' class='showupimg'>"+
   	"<tr>"+
	"<td class='searchDiv_td'>图片大小:</td>"+
	"<td><font color='red'>["+img_width_height+"]</font> [宽*高]</td>"+
	"</tr>"+
    "<tr ><td  class='searchDiv_td'>链接标题:</td>"+
		  "<td style='margin-left:-10px;'><input id='wf_title' name='wf_title'  style='width:200px;' onkeyup='checkLength(this,100);' maxlength='100'  cssStyle='width:300px;margin-left:-4px;' value='"+img_alt+"'/></td>"+
	"</tr>"+
	"<tr>"+
		"<td class='searchDiv_td'>链接地址:</td>"+
		"<td><input id='link_url' name='wf_link_url'   style='width:200px;' onkeyup='checkLength(this,200);' maxlength='200'  cssStyle='width:300px;margin-left:-4px;' value='"+a_href+"'/>(没有链接,请输入#)</td>"+
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
	"<td colspan='2' style='text-align: center;'><input type='button' value='确定修改' onclick='updateWaterFall();' />&nbsp;&nbsp;&nbsp;<input type='button' value='关闭' onclick='closef();' /><input type='hidden' id='alertid' value='"+item_id+"'/></td>"+
	"</tr>"+
	"</table>";
  return wfHtml;
}

//修改单张首页图片
function updateWaterFall(){
	var wf_title =$("#wf_title").val();
    var link_url =$("#link_url").val();
    var img_path =$("#uploadresult").val();
    if(wf_title==""){
    	jNotify("请填写链接标题!");
    	return ;
    }
    if(link_url==""){
    	jNotify("请输入链接地址");
    	return ;
    }
    if(img_path==""){
    	jNotify("请先上传图片!");
    	return ;
    }
	var item_id =  $("#alertid").val();
	if(item_id==""){
		return;
	}
	$("#"+item_id).find("img").attr("src",$("#uploadresult").val());
	$("#"+item_id).find("img").attr("alt",$("#wf_title").val());
	$("#href_"+item_id).val($("#link_url").val());
	$("#img_"+item_id).val($("#uploadresult").val());
	$("#title_"+item_id).val($("#wf_title").val());
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



