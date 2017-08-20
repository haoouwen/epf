$(document).ready(function(){
	/*商品详情*/
	$("#goodsduulId").floatdiv("float-top");
	/*返回首页*/
	$("#returnTopId").floatdiv("float-right-bottom");
	/*下拉菜单*/
	$(".deprocont ul li").menu();
	/*热门排行*/
	$('#grankidf').dropList();
	$('#grankids').dropList();
	
	if(!$('#slidePic')[0]){return;}
	var i = 0, p = $('#slidePic ul'), pList = $('#slidePic ul li'), len = pList.length;
	var elePrev = $('#prev'), eleNext = $('#next');
	var w = 175, num =3;
	/*if(navigator.userAgent.indexOf("MSIE")>0){w=175;}
	else{w=175;}*/
	if (len <= num)
	eleNext.addClass('gray');
	function prev(){
		if (elePrev.hasClass('gray')){return;}//已经是第一张
		p.animate({marginTop:-(--i) * w},500);
		if (i == 0){elePrev.addClass('gray');}
		if (i < len - num){eleNext.removeClass('gray');}
	}
	function next(){
		if (eleNext.hasClass('gray')){return;}//已经是最后一张
		p.animate({marginTop:-(++i) * w},500);
		if (i != 0){elePrev.removeClass('gray');}
		if (i == len - num){eleNext.addClass('gray');}
	}
	elePrev.bind('click',prev);
	eleNext.bind('click',next);
	
});

function re_show(val,num,btnName,divName,btncss1,btncss2){
		for(var i=1;i<=num;i++)
		{
			if(val==i)
			{
				document.getElementById(divName+i).style.display = 'block';
				document.getElementById(btnName+i).className = btncss1;
			}
			else{
				document.getElementById(divName+i).style.display = 'none';
				document.getElementById(btnName+i).className = btncss2;
			}
		}
}
