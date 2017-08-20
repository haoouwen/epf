$(document).ready(function(){
	/*品牌分类是否隐藏,改变选中索引样式*/
	var en_index = $("#en_index").val();
	if(en_index != "" && en_index == "All"){
		$(".searbrandcla").hide();
	}else{
		$(".searBrandul > li > a").each(function(){
			if($(this).text() == en_index){
				$(this).addClass("currentA");
			}
		});
	}
	/*分类回选*/
	var cat_id = $("#cat_id").val();
	if(cat_id != ""){
		$(".rsearcla > a").each(function(){
			if($(this).attr("id") == cat_id){
				$(this).addClass("currentCat");
			}
		});
	}
	/*分类定位*/
	$("#proclaId").floatdiv("float-top");
	/*返回首页*/
	$("#returnTopId").floatdiv("float-right-bottom");
	//修改底部样式
    $("#bottom").removeClass("botrmag");
	/*分类显示和隐藏*/
	$('.classcont').mousemove(function(){
		$(this).find('#classlistId').show();
		$(this).find('p').show();
		$(this).find('h3,p').addClass('lclaconthover');
	});
	$('.classcont').mouseleave(function(){
		$(this).find('#classlistId').hide();
		var showType = $("#fcbutId").attr("class");
		if(showType == "fcdown"){
			$(this).find('p').hide();
		}
		$(this).find('h3,p').removeClass('lclaconthover');
	});
	$(".lclacont:odd").addClass("addclabg");
	$("#fcbutId").toggle(
		function (){
			$(".lclacont > p").hide("fast");
			$("#fcbutId").removeClass("fcup");
			$("#fcbutId").addClass("fcdown");
		},
		function (){
			$(".lclacont > p").show("fast");
			$("#fcbutId").removeClass("fcdown");
			$("#fcbutId").addClass("fcup");
			
		}
	);
});