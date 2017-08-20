$(document).ready(function(){ 
           //根据微博编号获取微博唯一识别号
           var mid = $("#mid").val();
           if(mid.indexOf("http://") > -1){
             mid=mid.substr(28, 9);
           }else{
             mid=mid.substr(21, 9);
           }
	       $.ajax({  
		        type: "GET",
		        url:"https://api.weibo.com/2/statuses/queryid.json?mid=" + mid + "&type=1&isBase62=1&access_token=2.00XG1geFcQdtBCcc35dd152ehAgEnD",  
		        dataType:'jsonp',  
		        data:'',  
		        jsonp:'callback',  
		        success:function(result) {  	
		             $("#weiid").val(result["data"].id);
		             weibo();
			    }
			    
	        });      
      });
      function weibo(){
	        //根据微博唯一识别号获取微博信息
	        var weiid = $("#weiid").val();
	        $.ajax({  
		        type: "GET",
		        url:"https://api.weibo.com/2/statuses/repost_timeline.json?id=" + weiid + "&access_token=2.00XG1geFcQdtBCcc35dd152ehAgEnD",  
		        dataType:'jsonp',  
		        data:'',  
		        jsonp:'callback',  
		        success:function(result) {  
		             var jsonStr = JSON.stringify(result["data"]);
		             $("#jsondata").val(jsonStr);	
		             jsondata();
			    }
	        }); 
      }
      function jsondata(){
         //获取的微博json传到java类进行解析
	         var jsondata = $("#jsondata").val();
	         $.ajax({
	          type: "POST",
	          url: "/mall/trygoods!ajaxweibo.action",
	          datatype:"json",
	          async:false,
	          data:"jsondata="+jsondata,
	          success: function(data){ 
	          	 $("#weibostr").html(data);
              }	 
	          });    	
      }