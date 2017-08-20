//单个生成手机详情
function toSetAppDetail(){
	var goods_id=$("#flag_goods_id").val();
	var contentdetail=$("#content").val();
	var imgsrc;
	$("#appdetail_conten_id").html(contentdetail);
	$("#appdetail_conten_id").find("img").each(function(){
		$(this).removeAttr("style");
		$(this).removeAttr("alt");
		imgsrc+=$(this).attr("src")+"#";
	});
	if(imgsrc!=null){
	  imgsrc=imgsrc.substring(0,imgsrc.length-1);
	}
	contentdetail=$("#appdetail_conten_id").html();
	$.ajax({  	 
            type: "post",    	     
            url:"/admin_Goods_toSetAppDetailOne.action",       
            data:{'content':contentdetail,'imgs':imgsrc,'goods_id':goods_id},		      
            datatype:"json",
            async:false,
            success: function(data){
    		   $("#phone_goods_detail").val(data);   
				jNotify("手机端生成成功！");
				//清空隐藏域
				$("#appdetail_conten_id").html();
				window.location.reload();
		}
	});
	
}
//批量生成手机详情
function toSetAppDetailMore(id){
var tips="",titletip="";
if(id=="gid"){
 //全量生成手机版宝贝详情
 tips="全量生成手机详情页将造成现有详情页会被全部覆盖，确认要全量生成吗？";
 titletip="全量生成提示";
}else{
//增量生成手机版宝贝详情
 titletip="增量生成提示";
 tips="增量生成手机详情页只对新添加商品生成手机详情，确认要增量生成吗？";
}
art.dialog({
	title: titletip,
	content:'<div class="decorate">'+tips+'</div>',
	okValue: '确定',
	width: '238px',
	cancelValue: '取消',
    ok: function () {
    	 //隐藏操作按钮
		    $("#scbtn_id").hide();
		    //显示进度条
		    $("#show_id").show();
		    //显示结果
		    $("#show_success_id").show();
		    $("#success_id").html("0");
		    $("#faile_id").html("0");
			var goods_id=$("#"+id).val();
			var goods_ids=goods_id.split(',');
			for(var i=0;i<goods_ids.length;i++){
			    var contentdetail;
			    var imgsrc="";
			    var g_id=goods_ids[i];
			    //获取内容
			    $.ajax({  	 
		            type: "post",    	     
		            url:"/admin_Goods_toSetAppDetailGoodsdContent.action",       
		            data:{'id':g_id},		      
		            datatype:"json",
		            async:false,
		            success: function(data){
						contentdetail=data;
				   }
			    });
			    $("#appdetail_conten_id").html(contentdetail);
				$("#appdetail_conten_id").find("img").each(function(){
					$(this).removeAttr("style");
					$(this).removeAttr("alt");
					imgsrc+=$(this).attr("src")+"#";
				});
				if(imgsrc!=null){
				  imgsrc=imgsrc.substring(0,imgsrc.length-1);
				}
				contentdetail=$("#appdetail_conten_id").html();
				$.ajax({  	 
			            type: "post",    	     
			            url:"/admin_Goods_toSetAppDetailMore.action",       
			            data:{'content':contentdetail,'imgs':imgsrc,'goods_id':g_id},		      
			            datatype:"json",
			            async:false,
			            success: function(data){
			                if(data=="1"){
			                   //成功加一
			                	var gn=$("#success_id").html();
							    $("#success_id").html(parseInt(gn)+parseInt(1));
			                }else{
			                 //失败加一
			                    var gn=$("#faile_id").html();
							    $("#faile_id").html(parseInt(gn)+parseInt(1));
			                }
						
					}
				});
				$("#appdetail_conten_id").html("");
			}
			 $("#scbtn_id").show();
		     $("#show_id").hide(); 
    },
    cancel: function () {
		return true;
    }
});
   
}


//批量检查商品详细图片异常的
function toDetailPic(){
var tips="",titletip="";
//增量生成手机版宝贝详情
 titletip="全部检测商品图片";
 tips="全部检查商品图片，会影响服务的速度和网站速度，确认要检查吗？";
art.dialog({
	title: titletip,
	content:'<div class="decorate">'+tips+'</div>',
	okValue: '确定',
	width: '238px',
	cancelValue: '取消',
    ok: function () {
    	 //隐藏操作按钮
		    $("#scbtn_id").hide();
		    //显示进度条
		    $("#show_id").show();
		    //显示结果
		    $("#show_success_id").show();
		    $("#success_id").html("0");
		    $("#faile_id").html("0");
			var goods_id=$("#gid").val();
			var goods_ids=goods_id.split(',');
			var  goodsimgNumber="";//记录存在异常商品ID
			for(var i=0;i<goods_ids.length;i++){
			    var contentdetail;
			    var imgsrc="";
			    var g_id=goods_ids[i];
			    //获取内容
			    $.ajax({  	 
		            type: "post",    	     
		            url:"/admin_Goods_toSetAppDetailGoodsdContent.action",       
		            data:{'id':g_id},		      
		            datatype:"json",
		            async:false,
		            success: function(data){
						contentdetail=data;
				   }
			    });
			    $("#appdetail_conten_id").html(contentdetail);
				$("#appdetail_conten_id").find("img").each(function(){
					imgsrc+=$(this).attr("src")+"#";
				});
				if(imgsrc!=null){
				  imgsrc=imgsrc.substring(0,imgsrc.length-1);
				}
				$.ajax({  	 
			            type: "post",    	     
			            url:"/admin_Goods_toDetailPic.action",       
			            data:{'imgs':imgsrc,'goods_id':g_id},		      
			            datatype:"json",
			            async:false,
			            success: function(data){
			                if(data=="1"){
			                   //存在图片异常加一
			                	var gn=$("#success_id").html();
							    $("#success_id").html(parseInt(gn)+parseInt(1));
							    goodsimgNumber=goodsimgNumber+g_id+",";
			                }else{
			                 //不存在图片异常加一
			                    var gn=$("#faile_id").html();
							    $("#faile_id").html(parseInt(gn)+parseInt(1));
			                }
						
					}
				});
				$("#appdetail_conten_id").html("");
			}
			if(goodsimgNumber!=null&&goodsimgNumber!=""){
			  goodsimgNumber=goodsimgNumber.substring(0,goodsimgNumber.length-1);
			}
			$("#scbtn_id").show();
		    $("#show_id").hide(); 
		    //执行导出文件
			$("#pic_goods_id").val(goodsimgNumber);
			$("#indexFormpic").submit();
    },
    cancel: function () {
		return true;
    }
});
   
}





















