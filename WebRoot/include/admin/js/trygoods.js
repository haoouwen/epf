 $(document).ready(function(){ 
		 loadCat("${catIdStr?if_exists}","","","trygoods");
		 var try_type= $("#id_try_type").val();
		 weiboaddress(try_type);
      });
 //链接微博
 function linkweibo(){
     var cfg_weibo_url = $("#cfg_weibo_url").val();
     var weiboid =  $("#weibo_id").val();
     window.open(weiboid);
   }
 //改变试用类型
function selected(obj){
	var try_type= $(obj).val();
	weiboaddress(try_type);
}
//是否显示微博地址
function weiboaddress(t_val){
  if(t_val=='0'){
	  $("#al").html("<font color='red'>*</font>");
	  $("#id_weiboaddress").hide();
  }else{
	$("#id_weiboaddress").show();
	$("#al").html(":");
  }
}