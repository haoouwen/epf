$(document).ready(function(){ 
           //根据微博编号获取微博唯一识别号
	       $.ajax({  
		        type: "GET",
		        url:"https://api.weibo.com/2/statuses/mentions.json",  
		        dataType:'jsonp',  
		        data:'',  
		        jsonp:'callback',  
		        success:function(result) {  	
		             alert(result["user"].id);
			    }
			    
	        });      
});