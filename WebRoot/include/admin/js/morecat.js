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
	        	 	 $(".morecatdiv").append("<div id='show_add_cat' class='show_add_cat'></div>");
	        	 }
   		     	 $("#show_add_cat").append("<div style='line-height:20px;'><input type='hidden' name='all_cat_id_str' value=\""+add_cat_id+"\"/>"+add_cat_text+"<a class='oper' href='###' onclick='del_add_cat(this)'>[删除]</a></div>");
   		         jSuccess("新增分类成功!");  
   		    }else{
   		         jNotify("请选择分类!");  
   		    }
	     }
	}         
	 
	//删除分类
	function del_add_cat(obj){
			$(obj).parent("div").remove();    
			jSuccess("删除成功!");      	          		
	}	