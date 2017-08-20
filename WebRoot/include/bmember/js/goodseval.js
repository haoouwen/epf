$(document).ready(function(){
	//商品评价
	if($("div").hasClass("opeDiv")){
		$(".opeTab tr table .evaSpan").each(function(){
			$(this).toggle(function(){
				$(this).parents("tr").siblings(".evasTr").show();
				$(this).parents("tr").siblings().find(".evasTr").hide();
			},
			function(){
				$(this).parents("tr").siblings(".evasTr").hide();
			})
		})
	}
})


//提交评价
function submintEval(num,goods_id,order_id){
  	 jConfirm('您确定提交评价？', '系统提示',function(r){ 
     if(r){ 
  	 var isCheked=false;
  	 var g_eval="";
  	 var g_comment=$("#comment"+num).val();
  	 var share_pic=$("#uploadresult"+num).val();
  	 $("input[name='eval"+num+"']").each(function(){
		 isCheckd = $(this).attr("checked");
		 if(isCheckd==true)
		 g_eval=$(this).val();
	 });
	 
	 if(g_eval==""){
	  jNotify("请选择评分！");
	  return;
	 }
	 if(g_comment==""){
	  jNotify("请填写评价内容！");
	  return;
	 }
	 //ajax提价评价信息
	 $.ajax({
	  type:"post",
	  url:"/goodseval!orderEvaluate.action",
	  data:{"g_eval":g_eval,"g_comment":g_comment,"goods_id":goods_id,"order_id":order_id,"share_pic":share_pic},
	  datatype:"json",
	  success: function(data){
	  if(data=='1'){
     		jNotify("请先登录！");
     	}else if(data=='2'){
     	    jNotify("评价成功！");
     		$("#indexForm").submit();
     	}
	  }
	 });
	  } 
    });  
}


//进入修改页面
function deleteEval(actionName,paraStr){
		art.dialog({
		title: '友情提示',
		content:'<div class="decorate">'+'确定要删除?'+'</div>',
		okValue: '确定',
		width: '238px',
		cancelValue: '取消',
	    ok: function () {
	        	$("#hidden_div").html("");//清空
				var ppstr = paraStr.split("&");
				if(ppstr.length > 0){
					for(var i=0;i<ppstr.length;i++){
						var para = ppstr[i];
						if(para.indexOf("=") > -1){
							var paraSon = para.split("=");
							if(paraSon.length > 1){
								var key = paraSon[0];
								var value = paraSon[1];
								var hidden_name ="<input type='hidden' name='"+key+"' value='"+value+"' />";
								$("#hidden_div").append(hidden_name);
							}
						}
					}
				}
				document.getElementById("commonForm").action=actionName;
				document.getElementById("commonForm").submit();
	    },
	    cancel: function () {
			return true;
	    }
   });
}

//商品评论提交执行的方法
function submitEvaluate(){
  jConfirm('您确定提交评价？', '系统提示',function(r){ 
    if(r){ 
			//获取商品的个数
		 	var detailcount=$("#count_list_numbe_id").val();
		 	//商品ID串
		 	var str_goods_id="";
		 	//商品评分串
		 	var str_goods_feng="";
		 	//商品评价内容串
		 	var str_goods_content="";
		 	//晒图串
		 	var str_share_pic="";
		 	
		 	//判断商品个数是否不等于空且不等于0
			if(detailcount!=null&&detailcount!="0"){
			   
			   for(var i=0;i<detailcount;i++){
			     //评分选择按钮的状态
			     var g_feng=$('input:radio[name="radio_'+i+'"]:checked').val();
			     //商品ID
			     var g_goods_id=$("#goods_id"+i).val();
			     //商品评价内容
			     var g_content=$("#evaluate_content"+i).val();
			     //晒图
			     var g_share_pic=$("#uploadresult"+i).val();
			     if(g_content==null||g_content==""){
			     	g_content="暂无";
			     }
			     
			     if(g_share_pic==null||g_share_pic==""){
			         g_share_pic="暂无晒图";
			     }
			     str_goods_id+=g_goods_id+",";
			     str_goods_feng+=g_feng+",";
			     str_goods_content+=g_content+"##########";
			     str_share_pic+=g_share_pic+"##########";
			     
			     
			   }
			  
			   //获取所有商品ID串
			   if(str_goods_id!=null){
			   		
			   		str_goods_id=str_goods_id.substring(0,str_goods_id.length-1);
			   		$("#str_goods_id").val(str_goods_id);
			   
			   }
			   
			   //获取所以评分串
			   if(str_goods_feng!=null){
			   		
			   		str_goods_feng=str_goods_feng.substring(0,str_goods_feng.length-1);
			   		$("#str_goods_feng").val(str_goods_feng);
			   
			   }
			   
			   //获取所有评价内容串
			   if(str_goods_content!=null){
			   		
			   		str_goods_content=str_goods_content.substring(0,str_goods_content.length-10);
			   		$("#str_goods_content").val(str_goods_content);
			   
			   }
			   
			   //获取所有晒图串
			   if(str_share_pic!=null){
			   		
			   		str_share_pic=str_share_pic.substring(0,str_share_pic.length-10);
			   		$("#str_share_pic").val(str_share_pic);
			   
			   }
			   
			   //评价商品的个数
			   $("#count_list_numbe_id").val(str_goods_id.split(",").length);
			  if(str_goods_content=="暂无"){
					alert("请填写评价内容");		
					return false;  
			  }
			   $("#tradeForm").submit();
			}
		 } 
    });  
    
}