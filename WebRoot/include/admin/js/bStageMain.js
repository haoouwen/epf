$(document).ready(function(){
	/*左边收缩*/
	$("#smiantdid").toggle(
		function(){
			$(".fmaintd").hide();
			$("#ssid").addClass("openpic");
		},
		function(){
			$(".fmaintd").show();
			$("#ssid").removeClass("openpic");
		}
	 );
	/*下拉菜单*/
	$(".leftcont > li").menu();
	/*页面table*/
	$(".rtable tr:even").addClass("addback");
	$(".rtable td:last-child").addClass("nborder");
	$(".rtable th:last").addClass("nborder");
	$(".rdtable td:last-child").addClass("nborder");
	/*页面内容的高*/
	resize();
});
/*调整窗口*/
function resize(){
	window.onresize = null;
	var winWidth =0;
	if(document.body && document.body.clientHeight){
		winHeight = document.body.clientHeight;
	}
	if(document.documentElement  && document.documentElement.clientHeight && document.documentElement.clientWidth){
		winHeight = document.documentElement.clientHeight;
	} 
	var tempHeight=winHeight-131;
	var rtdcontHeight=tempHeight-49;
	$('.mainCont').css({"width":"100%","height":tempHeight+"px","overflow":"hidden"});
	$('.maintab').css({"width":"100%","height":tempHeight+"px","overflow":"hidden"});
	$('.rtdcont').css({"width":"97.8%","height":rtdcontHeight+"px"});
	$('.leftcont').css({"width":"160px","height":(tempHeight-2)+"px","overflow":"hidden"});
	setTimeout(resizeEvent,10);
} 
function resizeEvent(){ 
	window.onresize = resize; 
}