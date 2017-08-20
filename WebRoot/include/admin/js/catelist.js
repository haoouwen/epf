//初始化加载
$(document).ready(function(){
   	  getChildMenu("1111111111");
});

//获取菜单的子菜单
function getChildMenu(pId){
	$.ajax({
          type: "post",
          url: "/admin_Category_childList.action?pId="+pId,
          datatype:"json",
          async:false,
          success: function(data){ 
          	 data=eval("(" +data+")");    
			 var tbTree=createTalbe(data);	
			 $("#menuTable").append(tbTree);	
          }	 
	});    	     
}

//显示子菜单
function  showChild(obj,pId){
	    var img_id=$(obj).children("img");
		if($(obj).attr("class")==1){
	    	$.ajax({
		          type: "post",
		          url: "/admin_Category_childList.action?pId="+pId,
		          datatype:"json",
		          async:false,
		          success: function(data){ 
		          	 data=eval("(" +data+")");    
					 var tbTree=createTalbe(data);	
					 $(tbTree).insertAfter($(obj).parent("td").parent("tr"));
					 //赋给a class的值为0，用于判断是否再次加载数据
					 $(obj).removeClass();
					 $(obj).addClass("0");
					 img_id.attr("src","/plugin/gridTree/images/minus.gif");
		          }	 
			});    
		}else{
			delrow(pId);
			$(obj).removeClass();
			$(obj).addClass("1");
			img_id.attr("src","/plugin/gridTree/images/plus.gif");
		}
}

//递归删除行
function delrow(menu_id){
    $(".tr"+menu_id).each(function(){
    	delrow($(this).attr("id"));
    	$(this).remove();    	
    });
}

//重构表第一级菜单结构
function createTalbe(data){
	var strTable="";
	var imgLeaf="<img src='/plugin/gridTree/images/plus.gif'>";
    for(var i=0;i<data.length;i++){
      var is_enable="<img src='/includes/common/images/close.gif'/>";
      var left_px=25;
      left_px=left_px*data[i].cat_level;
      if(data[i].is_display=="0"){
      		is_enable="<img src='/includes/common/images/success.png'/>"
      }
      if(data[i].isLeaf=="0"){
      		imgLeaf="<img src='/plugin/gridTree/images/leaf.gif'>";
      }   
      strTable+="<tr id='"+data[i].cat_id+"' class='tr"+data[i].up_cat_id+"' bgcolor='#FFFFFF'>"
      +"<td align='center'><input class='cb_box' type='checkbox' name='category.cat_id' value="+data[i].cat_id+"></td>"
      +"<td align='center'>"+"<input type='text' name=\"category.sort_no\" value=\""+data[i].sort_no+"\" onkeyup='checkDigital(this)'></td>"
      +"<td align='left' style='padding-left:"+left_px+"px'>"+"<a class='1' onclick='showChild(this,"+data[i].cat_id+");'>"+imgLeaf+"</a>"+data[i].cat_name+"</td>"
      +"<td align='center'>"+is_enable+"</td>"
      +"<td align='center'><a onclick=\"linkToInfo('/admin_Category_add.action','category.up_cat_id="+data[i].cat_id+"');\"><img src='/includes/common/images/addclass.jpg'/></td>"
      +"<td align='center'><a onclick=\"linkToInfo('/admin_Category_view.action','category.cat_id="+data[i].cat_id+"');\"><img src='/includes/common/images/edit.png'/></td>"
      +"<td align='center'><a onclick=\"linkToInfo('/admin_Category_delete.action','category.cat_id="+data[i].cat_id+"');\"><img src='/includes/common/images/delete.png'/></td>"
	  +"</tr>";
    }  
    return strTable;
}


