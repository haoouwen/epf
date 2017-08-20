//isImgControl：缩略图片大小是否控制
//isYin：是否加水印
var isImgtemp;


function uploadImgControlAndYin(isControl){
	isImgtemp = isControl;
}
/******************* 开始 sysuploadImg、sysuploadOneImgMulti系统上传单张或多张图片指定到特定的路径下***********************/

//上传单张系统图片
function sysUploadImg(uploadifyfile,uploadresult,fileQueue){
	uploadCommonComponent("",uploadifyfile,uploadresult,fileQueue,"image",false,"sys");
}

//上传多张系统图片
function sysUploadOneImgMulti(){
	uploadCommonComponent("","uploadifyfile","uploadresult","fileQueue","image",true,"sys");
}
//上传规格系统图片工具,自定义规格图片
function sysuploadSpecImg(moveId,uploadifyfile,uploadresult,fileQueue){
	uploadComponent(moveId,uploadifyfile,uploadresult,fileQueue,"spec",false,"sys");
}
/********************结束**********************/


//上传图片工具,支持多张上传
function uploadSysGoodsImageMoreMulti(uploadifyfile,uploadresult,fileQueue){
	uploadComponent("",uploadifyfile,uploadresult,fileQueue,"sysImage",true);
}


//上传图片工具,支持多张上传
function uploadGoodsImageMoreMulti(uploadifyfile,uploadresult,fileQueue){
	uploadComponent("",uploadifyfile,uploadresult,fileQueue,"goods",true);
}

//上传商品图片工具,支持多张上传
function uploadSingleGoodsImage(uploadifyfile,uploadresult,fileQueue){
	uploadComponent("",uploadifyfile,uploadresult,fileQueue,"goods",false);
}


//上传商品图片工具,支持多张上传
function uploadGoodsImageMulti(){
	uploadComponent("","uploadifyfile","uploadresult","fileQueue","goods",true);
}

//上传一张图片工具,支持多张上传
function uploadOneImgMulti(){
	uploadComponent("","uploadifyfile","uploadresult","fileQueue","image",true);
}

//上传一张图片工具
function uploadOneImg(){
	uploadComponent("","uploadifyfile","uploadresult","fileQueue","image",false);
}

//上传一张图片工具,自定义控件值
function uploadImg(uploadifyfile,uploadresult,fileQueue){
	uploadComponent("",uploadifyfile,uploadresult,fileQueue,"image",false);
}

	
//上传一张图片工具,自定义控件值
function uploadOneSelfImg(moveId,uploadifyfile,uploadresult,fileQueue){
	uploadComponent(moveId,uploadifyfile,uploadresult,fileQueue,"image",false);
}

//上传规格图片工具,自定义规格图片
function uploadSelfSpecImg(moveId,uploadifyfile,uploadresult,fileQueue){
	uploadComponent(moveId,uploadifyfile,uploadresult,fileQueue,"selfspec",false);
}

//上传规格图片工具,自定义控件值
function uploadSpecImg(moveId,uploadifyfile,uploadresult,fileQueue){
	uploadComponent(moveId,uploadifyfile,uploadresult,fileQueue,"spec",false);
}

//上传视屏
function uploadFlash(moveId,uploadifyfile,uploadresult,fileQueue){
	uploadComponent(moveId,uploadifyfile,uploadresult,fileQueue,"flash",false);
}
//上传单张快递单背景图片
function uploadPrintBackgroupImg(uploadifyfile,uploadresult,fileQueue){
	uploadComponent("",uploadifyfile,uploadresult,fileQueue,"printBackgroupImg",false);
}
//上传公用组件
function uploadComponent(moveId,uploadifyfile,uploadresult,fileQueue,type,multi){
	uploadCommonComponent(moveId,uploadifyfile,uploadresult,fileQueue,type,multi,"");
}


//uploadifyfile：文件路径输入框的ID
//uploadresult：上传文件成功后的文件保存路径
//fileQueue:文件上传效果队列
//type:上传文件类型 image,file,flash
//multi:多文件上传 true,false
//isImgtemp:1: 控制缩略图；2：水印；123：控制缩略图+水印
function uploadCommonComponent(moveId,uploadifyfile,uploadresult,fileQueue,type,multi,sys){
	var paraString;
	if(sys == 'sys'){
	  paraString = "?img_cust_id="+$("#img_cust_id").val()+",sys";
	}else{
	  paraString = "?img_cust_id="+$("#img_cust_id").val();
	}
	//请求路径
	var upAction;
	var sizeLimit;
	var fileDesc;
	var fileExt;
	var bite=1024*1024;
	if(type == 'image'){//普通图片
		upAction = '/uploadfy_executeUpimages.action'+paraString;
		sizeLimit = parseFloat($("#image_size").val())*bite;
		var ftype=$("#image_type").val();		
		fileDesc = '支持格式:'+ftype+".";
		var reg="\/";
		ftype=ftype.replace(new RegExp(reg,"gm"),";*.");
		fileExt="*."+ftype;
	}else if(type=='spec'){//系统规格
		upAction = '/uploadfy_executeUpimages.action'+paraString;
		sizeLimit = parseFloat($("#image_size").val())*bite;
		var ftype=$("#image_type").val();		
		fileDesc = '支持格式:'+ftype+".";
		var reg="\/";
		ftype=ftype.replace(new RegExp(reg,"gm"),";*.");
		fileExt="*."+ftype;
	}else if(type=='selfspec'){//系统自定义规格
		upAction = '/uploadfy_executeUpimages.action'+paraString;
		sizeLimit = parseFloat($("#image_size").val())*bite;
		var ftype=$("#image_type").val();		
		fileDesc = '支持格式:'+ftype+".";
		var reg="\/";
		ftype=ftype.replace(new RegExp(reg,"gm"),";*.");
		fileExt="*."+ftype;
	}else if(type == 'file'){
		upAction = '/uploadfy_executeUpFile.action'+paraString;
		sizeLimit = parseFloat($("#file_size").val())*bite;
		var ftype=$("#file_type").val();		
		fileDesc = '支持格式:'+ftype+".";
		var reg="\/";
		ftype=ftype.replace(new RegExp(reg,"gm"),";*.");
		fileExt="*."+ftype;
	}else if(type == 'flash'){//flash上传
		upAction = '/uploadfy_executeUpFlash.action'+paraString;
		sizeLimit = parseFloat($("#flash_size").val())*bite;
		var ftype=$("#flash_type").val();		
		fileDesc = '支持格式:'+ftype+".";
		var reg="\/";
		ftype=ftype.replace(new RegExp(reg,"gm"),";*.");
		fileExt="*."+ftype;
	}else if(type == 'goods'){//上传商品图片
		upAction = '/uploadfy_executeUpGoodsimages.action'+paraString;
		sizeLimit = parseFloat($("#image_size").val())*bite;
		var ftype=$("#image_type").val();		
		fileDesc = '支持格式:'+ftype+".";
		var reg="\/";
		ftype=ftype.replace(new RegExp(reg,"gm"),";*.");
		fileExt="*."+ftype;
	}else if(type == 'printBackgroupImg'){//快递单打印上传图片
		upAction = '/uploadfy_executeUpimages.action'+paraString;
		sizeLimit = parseFloat($("#image_size").val())*bite;
		var ftype=$("#image_type").val();		
		fileDesc = '支持格式:'+ftype+".";
		var reg="\/";
		ftype=ftype.replace(new RegExp(reg,"gm"),";*.");
		fileExt="*."+ftype;
	}
	
    //上传工具的路径
    var imagepath="/include/components/upload";
	$("#"+uploadifyfile).uploadify({
          'uploader'       : imagepath+'/uploadify.swf',//控制弹出选择文件框
          'script'         : upAction,//执行的xx.action
          'cancelImg'      :imagepath+'/cancel.png',//选择文件到文件队列中后的每一个文件上的关闭按钮图标
          'folder'         : 'uploadifyfile',//上传文件存放的目录 。
          'queueID'        : fileQueue,//文件队列的ID，该ID与存放文件队列的div的ID一致。
          'auto'           : true,//是否自动上传图片
          'multi'          : multi,//是否支持多上传
          'sizeLimit'      : sizeLimit,//上传文件的大小限制 。
          'displayData'    : 'speed',//有speed和percentage两种，一个显示速度，一个显示完成百分比
          'fileDesc'       : fileDesc, //这个属性值必须设置fileExt属性后才有效，用来设置选择文件对话框中的提示文本，如设置fileDesc为“请选择rar doc pdf文件”，打开文件选择框效果
          'simUploadLimit' : 2,//支持同时上传图片的个数
          'fileExt'        : fileExt,//设置可以选择的文件的类型，格式如：'*.doc;*.pdf;*.rar' 。
          'buttonText'     : 'Browser',//浏览按钮的文本，默认值：BROWSE 。
          'fileDataName'   : 'uploadifyfile',  //设置一个名字，在服务器处理程序中根据该名字来取上传文件的数据。默认为Filedata
          'height'         : 20,  //设置浏览按钮的高度 ，默认值：30。
		  'width'          : 60,   //设置浏览按钮的宽度 ，默认值：110。
		  'buttonImg'      : imagepath+'/upload.jpg',  //浏览按钮的图片的路径 。只要是用解决按钮文字显示不支持中文的办法
		  'wmode'          : 'transparent',//设置该项为transparent 可以使浏览按钮的flash背景文件透明，并且flash文件会被置为页面的最高层。 默认值：opaque 。
          'onComplete'     : function (event, queueID, fileObj, response, data){ 
         	//判断是否为多上上传图片
            if(multi==true){
             	    //根据不同的类型,系统规格
				    if(type=='goods'){
				    	dealGoods(multi,response,uploadresult,type);
				    }else{
				    	$("#"+uploadresult).val(addMultiImageUrl(uploadresult,response));//显示上传成功结果 
	             	    var resultImg=$("#"+uploadresult).val();
	             	    if(resultImg.lastIndexOf(",")>-1){
	             	    var lastImgLength=resultImg.lastIndexOf(",");             	    
	             	    resultImg=resultImg.substring(0,lastImgLength);
	             	    $("#"+uploadresult).val(resultImg);
				    }
             	 }             	 
             }else{
          	 	if(endWith(response,",")){
          	 		response = response.substring(0,response.length-1);
          	 	}
             	$("#"+uploadresult).val(response);//显示上传成功结果      
             	//根据不同的类型,系统规格
			    if(type=='spec'){
			    	$("#getimg"+moveId).attr("src",response);   	
			    }else if(type=='goods'){
			    	$("#listimgID").attr("src",response);
			    }
			    //根据自定义系统规格图片
			    if(type=='selfspec'){
			    	$("#showselfUpImg"+moveId).attr("src",response);   	
			    }
			    //快递单打印样式背景图片上传
			    if(type=='printBackgroupImg'){
					addBackgroupImg(response,uploadresult);
			    }
             }   
             //保存上传图片的路径 
             saveUploandImg(response);
         }
    });
}

//处理多图片上传的时候图片路径的添加
function addMultiImageUrl(uploadresult,responses){
  var myresult=$("#"+uploadresult).val();
  if(responses.length==0){
      myresult="";
      alert("请上传图片");
  }else{
      var len=myresult.length;
      var endstring=myresult.substring(len-1,len);
      if(endstring !=","&& myresult!=""){
           myresult=myresult+",";
      }
      myresult += responses+',';        
   }
  return myresult;
}

//判断字符串是否是以某个字符结尾
function endWith(s1,s2){  
	if(s1.length<s2.length)
		return	false;
	if(s1==s2)
		return	true;
	if(s1.substring(s1.length-s2.length)==s2)
		return	true;
	return	false;
}

//商品图片上传成功后处理上传后的图片
function dealGoods(multi,response,uploadresult,type){
   //判断是否为多上上传图片
      if(multi==true){
           MultiImageUrl(uploadresult,response);//显示上传成功结果
           $(".group").colorbox({rel:'group1'});//加载显示图片方式             	 
      }else{
     	 	if(endWith(response,",")){
     	 		response = response.substring(0,response.length-1);
     	 	}
   	 		//自定义列表页图片上传选择
         	$("#listimgID").attr("src",response);
        	//图列表显示
        	var picStr="";
			picStr="<li><input type='hidden' name='gimg' value="+response+"><a class='group' href="+response+"><img src="+response+"></a>"
			+"<div><a class='setimg' onclick='setImg(\""+response+"\");'></a>"+
		    "<a class='delimg' onclick='delImg(this);'></a></div></li>"
		    $(".show_pic").append(picStr);
		    //填充列表页选择框
            $("#"+uploadresult).val(response);//显示上传成功结果  
      }  
}

//处理商品多图片上传的时候图片路径的添加
function MultiImageUrl(uploadresult,responses){
	 var myresult=$("#"+uploadresult).val();
     var len=myresult.length;
     var endstring=myresult.substring(len-1,len);
     if(endstring !=","&& myresult!=""){
          myresult=myresult+",";
     }
     myresult += responses+','; 
     if(myresult.lastIndexOf(",")>-1){
   	 var lastImgLength=myresult.lastIndexOf(",");             	    
   	 resultImg=myresult.substring(0,lastImgLength);    	 
   	 $("#"+uploadresult).val(resultImg);
     }  
     //加载到显示列表中
     showpicList(responses);
     return myresult;   
}

//显示商品图片列表
function showpicList(responses){
  if($(".show_pic").html()==""){
  	 $("#listimgID").attr("src",responses);
  	 $("#uploadoneimage").val(responses);
  }
  var picStr="";
  picStr="<li><input type='hidden' name='gimg' value="+responses+"><a class='group' href="+responses+"><img src="+responses+"></a>"
  +"<div class='imgsshowdiv'><a onclick='setImg(\""+responses+"\");'><img class='imgstyle' src='/include/admin/images/setimg.gif'/></a>"+"<a onclick='delImg(this);'><img  class='imgstyle' src='/include/admin/images/delimg.gif'/></a></div></li>";
  $(".show_pic").append(picStr);
}


//删除商品图片
function delImg(obj){
    if($(obj).parent("div").parent("li").next("li").html()!=null){
    	var nextimg=$(obj).parent("div").parent("li").next("li").find("img").attr("src");
    	$("#listimgID").attr("src",nextimg);
		$("#uploadoneimage").val(nextimg);
		$(obj).parent("div").parent("li").remove();
    }else {    	
    	var $prev_li=$(obj).parent("div").parent("li").prev("li");
    	if($prev_li.html()!=null){
	    	var beforeimg=$prev_li.find("img").attr("src");
	    	$("#listimgID").attr("src",beforeimg);
			$("#uploadoneimage").val(beforeimg);
		}else{
			$("#listimgID").attr("src","/include/admin/images/nopic.jpg");
			$("#uploadoneimage").val("");
		}
		//清除点击图片
		$(obj).parent("div").parent("li").remove();
    }
}

//设置商品列表页图片
function setImg(imgval){
	$("#listimgID").attr("src",imgval);
	$("#uploadoneimage").val(imgval);
}

//通过ajax保存上传图片返回来的信息
function saveUploandImg(imgPath){
	$.ajax({
		type:'post',
		url:'/imagemana!saveImage.action?imgPath='+imgPath,
		datatype:'json',
		success:function(){
		}
	});
}
