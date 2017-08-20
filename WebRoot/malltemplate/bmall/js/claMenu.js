/*
 *产品分类
 *bruinHu
*/
function createClaMenu(menuDiv,menu,hideItem,type){
	
	var SHOW_MENU = 1,
	    HIDE_MENU = 2;
		
	var $menuMian = $("."+menuDiv),//整体一块
	    $menu = $("."+menu),
	    $menuLi = $("."+menu+" "+"li"),
	    $addLiColor = $("."+menu+" "+"li:odd"),
	    liNum = $menuLi.length,
		hItem = "."+hideItem; //分类右边隐藏部分
	    $addLiColor.addClass("zebraLine");
	    $(hItem).hide();
	
	$menuLi.each(function (index){
		
		if (index < liNum) {
				
			$(this).mouseenter(function () {
				
				var offsetTop = $(this).offset().top - 280;//offsetTop 到顶部的距离
				var $Obj = $(this).find(hItem);//
				//当超过屏幕
				var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
				var objDistanceY = offsetTop +$Obj.height()+100-scrollTop;
				var distanceY = objDistanceY - $(window).height();
				
				if (objDistanceY > $(window).height()) {
					offsetTop = offsetTop - distanceY - 100;
				}			
				if (offsetTop < 0){
					offsetTop = 0;
				}
				
				$(this).addClass("addLi");
				$Obj.show();
				$menuLi.find(hItem).stop().animate({ "top": offsetTop });
				$Obj.stop().animate({ "top": offsetTop });
		   });//mouseenter end
		 
		   $(this).mouseleave(function () {
				$(this).removeClass("addLi");
				$(this).find(hItem).hide();
		   });//mouseleave end
	   }
	   	   
	});//each end
	
	if(type == SHOW_MENU){
		$menu.show();
	}
	if(type == HIDE_MENU){
		$menu.hide();
		$menuMian.hover(function () {
			$menu.show();
			},
		 function (){
			$menu.hide();
		  }
	   );
	}
}
