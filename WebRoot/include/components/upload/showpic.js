//显示图片预览DIV
function showpicture(name) {
	$("#showUpImage").remove();
	var pathvalue = "";
	pathvalue = document.getElementById(name).value;
	if(pathvalue==""){
	 	jNotify("没有可以预览的图片！");
	 	return;
	}else{
	 	var len=pathvalue.length;
	 	var endstring=pathvalue.substring(len-1,len);
	 	if(endstring==","){//用户判断，如果上传的图片串最后一个字符带有','的话，先去掉','，在进行处理；
	   	 	pathvalue=pathvalue.substring(0,len-1);
	 	}
		var divappends = "";
		var arrypaht = pathvalue.split(",");
		$("body").append("<div id='showUpImage' style='display:none;'></div>");
		for (var i = 0; i < arrypaht.length; i++) {
			$("#showUpImage").append("<p><a class='showpic'  href='"+arrypaht[i].toString()+"' ></a></p>");
		}
	}
	//渲染
	$(".showpic").colorbox({
		rel:'group'
	});
	//自动点击第一个
	$(".showpic").eq(0).click();
}

//获取图片大小
function getImageSize(imgE){
     var i = new Image(); //新建一个图片对象
      i.src = imgE.src;//将图片的src属性赋值给新建的图片对象的src
      return new Array(i.width,i.height);
}  
