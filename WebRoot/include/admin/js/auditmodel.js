//新增审核人员
function add_audit_member(){	           			           		      
		 var add_mem_id=""; 
		 var add_mem_text="";	  
		 var sel_count=$("input:hidden[name='all_member_id_str']").size()+1;  
		 $("#mem_count").val(sel_count);
	     add_mem_id=jQuery("#sel_mem_id  option:selected").val();
	     add_mem_text=jQuery("#sel_mem_id  option:selected").text();
	     //判断是否已经添加过该ID
	     var count=0;
	     if(add_mem_text!=""){	           		         
	         $("input:hidden[name='all_member_id_str']").each(function(){	         
	               if($.trim($(this).val())==add_mem_id){	           		               	  
           		    	 count++;	           		               
	               }	               
	         });	           		     
	     }
	     if(add_mem_text=="请选择"){
		      jNotify("请选择审核人员!");  
		      return;
	     }
	     if(count>0){//如果已添加则提示信息,否则则添加成功
	      	jNotify("您已添加该审核人员!");  	           		        
	     }else{
	        if(add_mem_text!=""){
	             var strallapp="";	  
   		     	 strallapp="<div style='line-height:20px;' id='div_"+sel_count+"'>"
   		     	 +"<span style='width:166px;display:inline-block;'>["+sel_count+"].&nbsp;"
   		     	 +""+add_mem_text+"&nbsp;&nbsp;&nbsp;&nbsp;</span>";
   		    	 
   		     	 strallapp+="<a style='width:14px;display:inline-block;margin-right: 5px;*margin-right: 5px;'   href='javascript:void(0);' onclick='up_member_opt("+sel_count+")'>"
   		     	 +"<img title='向上排序' id='img_up_"+sel_count+"' class='img_up'  src='/include/admin/images/upmem.jpg' /></a>";
   		     	 
   		     	 strallapp+="<a style='width:14px;display:inline-block;margin-right: 5px;*margin-right: 5px;'   href='javascript:void(0);' onclick='down_member_opt("+sel_count+")'>"
   		     	 +"<img title='向下排序' id='img_down_"+sel_count+"' class='img_down'  src='/include/admin/images/downmem.jpg' /></a>";
   		     	 
   		     	 strallapp+="<a style='width:14px;display:inline-block;margin-right: 5px;*margin-right: 5px;' href='javascript:void(0);' onclick=\"del_add_mem(this,'"+sel_count+"')\">"
   		     	 +"<img title='删除' src='/include/admin/images/del_mem.jpg' /></a><input type='hidden' name='all_member_id_str' id='hidden_"+add_mem_text+"' value=\""+add_mem_id+"\"/></div>";
   		     	 
   		     	 $("#show_add_mem").append(strallapp);
   		     	 
   		         jSuccess("添加审核人员成功!");  
   		         removeupanddown();
   		    }else{
   		         jNotify("请选择审核人员!");  
   		    }
	     }
	}         
//删除审核人员
function del_add_mem(obj,mid){

	$(obj).parent("div").remove();   
	var sel_count=$("input:hidden[name='all_member_id_str']").size();  
	$("#mem_count").val(sel_count);  
	var varcontent=$("#show_add_mem").html();
	var scount=$("#mem_count").val();
	scount=parseInt(scount)+1;
	for(i=mid;i<scount;i++){
		varcontent=varcontent.replace("div_"+(parseInt(i)+1)+"","div_"+i+"");
		varcontent=idreplace(varcontent,parseInt(i)+1,i);
	}
	$("#show_add_mem").html(varcontent);
	 removeupanddown();
	jSuccess("删除成功!");      	   
	      		
}
//向下排序
function down_member_opt(id)
{
 var maxcount=$("#mem_count").val();
 if(id<=maxcount){
  var downid=parseInt(id)+1;
  
  var down_html=$("#div_"+downid).html();
  var cur_hmtl=$("#div_"+id).html();
  down_html=idreplace(down_html,downid,id);
  cur_hmtl=idreplace(cur_hmtl,id,downid);
  $("#div_"+downid).html(cur_hmtl);
  $("#div_"+id).html(down_html);
   removeupanddown();
 }
}
//向上排序
function up_member_opt(id)
{
 if(id>1){
  var upid=parseInt(id)-1;
  var up_html=$("#div_"+upid).html();
  var cur_hmtl=$("#div_"+id).html();
  up_html=idreplace(up_html,upid,id);
  cur_hmtl=idreplace(cur_hmtl,id,upid);
  $("#div_"+upid).html(cur_hmtl);
  $("#div_"+id).html(up_html);
  removeupanddown();
 }
}
//去掉第一个和最后一个的向上和向下
function removeupanddown()
{
 $(".img_up").each(function(){	  
 	      $(this).css('display','inline');
  });	
  $(".img_down").each(function(){	  
 	      $(this).css('display','inline');
  });           	
 var ccount=$("#mem_count").val();
 if(ccount!=0)
 {
    $("#img_up_1").css('display','none');
    $("#img_down_"+ccount).css('display','none');
 }
}

//替换原来DIV的ID	
function idreplace(objstr,objid,curid)
{
 objstr=objstr.replace("["+objid+"]","["+curid+"]").replace("up_member_opt("+objid+")","up_member_opt("+curid+")");
 objstr=objstr.replace("img_down_"+objid+"","img_down_"+curid+"").replace("down_member_opt("+objid+")","down_member_opt("+curid+")").replace("img_up_"+objid+"","img_up_"+curid+"");
 objstr=objstr.replace("del_add_mem(this,'"+objid+"')","del_add_mem(this,'"+curid+"')");
 return objstr;
}
	
	
	
	
	
	
	
	
	
	
	
	
	