 $(document).ready(function(){ 
	   //checkbox 点击事件 选择框
       $("#menu_oper input:checkbox").click(function(){       
       	  var cb=$(this).val();
	      var str="input:checkbox[id*="+cb+"]";
          if(this.checked)
          {	
	            $(str).each(function(){	                 	  
		                this.checked=true;  
		                var ccb=$(this).val();
		                var cstr="input:checkbox[id*="+ccb+"]";		               
		                $(cstr).each(function(){  	                 
		                     this.checked=true;  
		                     var rccb=$(this).val();
		                     var rcstr="input:checkbox[id*="+rccb+"]";	
		                     $(rcstr).each(function(){  	                 
				                 this.checked=true;  
				                }); 	
		                }); 		                		             
			    }); 	
		   }
		  else
		  {
		       $(str).each(function(){	                 	  
		                this.checked=false;  
		                var ccb=$(this).val();
		                var cstr="input:checkbox[id*="+ccb+"]";		               
		                $(cstr).each(function(){  	                 
		                     this.checked=false;  
		                     var rccb=$(this).val();
		                     var rcstr="input:checkbox[id*="+rccb+"]";	
		                     $(rcstr).each(function(){  	                 
				                 this.checked=false;  
				                }); 	
		                }); 		                		             
			    }); 	
		  }       
       });     
 });  

 
 
 
 
 
 
  $(document).ready(function(){ 	 	   
		     var mrl=$("#mr_list").val();
		     var opl=$("#op_list").val();		     
		     var arr = mrl.split(',');	  
		     var orr=opl.split(',');     	     
		     for(var i = 0; i < orr.length; i++){			         
		             $("input:checkbox").each(function(){	 
		                 if(orr[i]==$(this).val())
		                 {
		                   this.checked=true;  
		                 }		                 
		             });
             }	        
		     for(var i = 0; i < arr.length; i++){			         
		             $("input:checkbox").each(function(){	 
		                 if(arr[i]==$(this).val())
		                 {
		                   this.checked=true;  
		                 }		                 
		             });
             }	 
	  });  