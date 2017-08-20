var curPage=$("input[name='curPage']").val();
var totalPage=$("input[name='totalPage']").val();
$(document).ready(function(){
	//翻页
    $("#page").html(curPage+"/"+totalPage);
//	//初始化加载省份
//	loadProvinceList("allArea");
//	/*地区选择*/
//	$(".termArea").hover(function(){
//		$(".selarea").show();
//		$(".selarea").hover(function(){
//			$(".selarea").show();
//			}, function(){$(".selarea").hide();
//		});
//	}, function(){
//	    $(".selarea").hide();	
//	});
//	$("#selareaulid > li").hover(function(){
//		//获取子级地区
//		var cityStr=getCityList($(this).attr("id"));
//		$(this).append(cityStr);
//		$(this).addClass("xzhover");
//	}, function() {		
//		$(this).removeClass();
//	});
	 //商品品牌回选高亮
	 //var brand_id=$("#brand_id").val();
     //   $("#brand a").each(function(){	 
     //       if(brand_id==$(this).attr("id"))
     //       {
     //         $(this).addClass("selected");
     //       }		                 
     //   });
	//商品规格回选高亮
	 //var specstr=$("#specstr").val();
      //  $("#spec a").each(function(){	 
       //     if(specstr==$(this).attr("id")) 
        //    {
        //      $(this).addClass("selected");
         //   }		                 
       // });
});
//上一页
function  lastPage(){
	var prev=1
	if(eval(curPage)>1){
		prev=eval(curPage)-1;
		document.forms[0].pages_s.value=prev;document.forms[0].submit();
	}
}
//下一页
function  nextPage(){
	var next=curPage;
	if(eval(curPage)<eval(totalPage)){
		next=eval(curPage)+1;
		document.forms[0].pages_s.value=next;document.forms[0].submit();
	}
}

function toSubmit(sort_type){
	$("#sort").val(sort_type);
	document.forms[0].submit();
}