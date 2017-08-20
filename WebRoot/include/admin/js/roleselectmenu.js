$(function(){
		//checkbox 点击事件选择框
       $("input:checkbox").click(function(){     
     	    var cb=$(this).val();
     	    if(this.checked){
	       	    $("input:checkbox[id*="+cb+"]").each(function(){	         	  
	                this.checked=true;                                 		             
		    	}); 	
		    }else{
	       	    $("input:checkbox[id*="+cb+"]").each(function(){	         	  
	                this.checked=false;                                 		             
		    	}); 			    
		    }
       });     
})

function loadrole(ids){
   if(ids!=""){   
      var roleIds=ids.split(",");
      for(var i=0;i<roleIds.length;i++){
      	 $("."+roleIds[i]).attr("checked",true);
      }
   }
}
