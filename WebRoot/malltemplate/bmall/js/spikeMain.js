$(document).ready(function(){
	//开启定时器
	startclock(); 
	//tab页点击事件
	$(".tabbar ul li").click(function(){
		$("#spikeday").val($(this).index());
		$("#spike").submit();
	})
	//浮动
   $("#msfloatdiv").floatdiv("float-top");
   //遍历各个时段的秒杀，若不存在商品就移除该节点
   $(".msMianul").each(function(){
   		var i = $(this).find("ul li").length;
   		if(i == 0){
   			$(this).remove();
   		}
   });
   //开抢状态鼠标点击事件
   $(".msdatecont ul li").click(function(){
   		$(".msdatecont ul li").each(function(){
   			$(this).removeClass("hoverdate seldate");
   		});
   		if($(this).attr("class")!="panicdate"){
   			$(this).addClass("seldate");
   		}
   });
   //开抢状态鼠标滑过事件
   $(".msdatecont ul li").hover(function(){
   		if($(this).attr("class")!="panicdate" && $(this).attr("class")!="seldate" && $(this).attr("class")!="overdate seldate"){
   			$(this).addClass("hoverdate");
   		}
   },function(){
		$(this).removeClass("hoverdate");
   });
   //分类
   $('.classcont').mousemove(function(){
		$(this).find('#classlistId').show();
		$(this).find('h3,p').addClass('lclaconthover');
	});
	$('.classcont').mouseleave(function(){
		$(this).find('#classlistId').hide();
		$(this).find('h3,p').removeClass('lclaconthover');
	});
	$(".lclacont:odd").addClass("addclabg");
	$("#classMainId").hide();
	$("#h2classId").hover(
		function(){
			$("#classMainId").show();
			$(this).removeClass("h2down");
			$(this).addClass("h2up");
			$("#classMainId").hover(
			  function(){
				  $("#classMainId").show();
				  $("#h2classId").removeClass("h2down");
			      $("#h2classId").addClass("h2up");
			  },
			  function(){
				  $("#classMainId").hide();
				  $("#h2classId").removeClass("h2up");
				  $("#h2classId").addClass("h2down");
			  }
			);
		},
		function(){
			$("#classMainId").hide();
			$(this).removeClass("h2up");
			$(this).addClass("h2down");
		}
	);
});

//滚动到指定位置
function scorllpos(id){
   $this_spk=$("#block"+id);
   $this_spk.css("display","block");
   $('#msMian').scrollToID($this_spk,100);
}
//滚动到指定位置
function searchInfo(id){
  $("#time").val(id);
  $("#spike").submit();
}
