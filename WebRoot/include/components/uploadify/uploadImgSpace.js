 //isImgControl：缩略图片大小是否控制

//上传一张图片工具,支持多张上传
function uploadOneImgMulti(){
    var imgpath=$("#idpath").val();
	uploadImgMultis("uploadifyfile","uploadresult","fileQueue",imgpath);
}

//上传一张图片工具,支持多张上传
function uploadImgMultis(uploadifyfile,uploadresult,fileQueue,imgpath){
 uploadCommonComponent(uploadifyfile,uploadresult,fileQueue,"image",true,imgpath);
}
//uploadifyfile：文件路径输入框的ID
//uploadresult：上传文件成功后的文件保存路径
//fileQueue:文件上传效果队列
//type:上传文件类型 image,file,flash
//multi:多文件上传 true,false
//isImgtemp:1: 控制缩略图；2：水印；123：控制缩略图+水印
function uploadCommonComponent(uploadifyfile,uploadresult,fileQueue,type,multi,imgpath){
	var paraString="";
	paraString+="?imgpath="+imgpath;
	//请求路径
	var upAction;
	var sizeLimit;
	var fileDesc;
	var fileExt;
	var bite=1024*1024;
	if(type == 'image'){//普通图片
		upAction = '/uploadfy_executeUpimagesSpace.action'+paraString;
		sizeLimit = parseFloat($("#image_size").val())*bite;
		var ftype=$("#image_type").val();		
		fileDesc = '支持格式:'+ftype+".";
		var reg="\/";
		ftype=ftype.replace(new RegExp(reg,"gm"),";*.");
		fileExt="*."+ftype;
	}
	else if(type == 'file'){
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
	}
    //上传工具的路径
    var imagepath="/include/components/uploadify";
	$("#"+uploadifyfile).uploadify({
	 //开启调试
        'debug' : false,
        //是否自动上传
        'auto':true,
        //超时时间
        'successTimeout':99999,
        //flash<link rel="stylesheet" href="/include/components/uploadify/uploadify.css" />  
        'swf': "/include/components/uploadify/uploadify.swf",
        //不执行默认的onSelect事件
        'overrideEvents' : ['onDialogClose'],
        //文件选择后的容器ID
        'queueID':fileQueue,
        //上传处理程序
        'uploader':upAction,
        //浏览按钮的背景图片路径
        'buttonImage':(imagepath+'/upload.jpg'),
         //服务器端脚本使用的文件对象的名称 $_FILES个['upload']
        'fileObjName':'uploadifyfile',
        //浏览按钮的宽度
        'width':'60',
        //浏览按钮的高度
        'height':'20',
        'multi'          : multi,  
        //在浏览窗口底部的文件类型下拉菜单中显示的文本
        'fileTypeDesc':fileDesc,
        //允许上传的文件后缀
        'fileTypeExts':fileExt,
        //上传文件的大小限制
        'fileSizeLimit':sizeLimit,
        //上传数量
        'queueSizeLimit' : 5,
        //上传进度条 设置上传进度显示方式，percentage显示上传百分比，speed显示上传速度
        'progressData' 	:'percentage',
        'removeTimeout' : 1,
        //返回一个错误，选择文件的时候触发
        'onSelectError':function(file, errorCode, errorMsg){
            switch(errorCode) {
                case -100:
                    alert("上传的文件数量已经超出系统限制的"+$('#'+uploadifyfile).uploadify('settings','queueSizeLimit')+"个文件！");
                    break;
                case -110:
                    alert("文件 ["+file.name+"] 大小超出系统限制的"+$('#'+uploadifyfile).uploadify('settings','fileSizeLimit')+"大小！");
                    break;
                case -120:
                    alert("文件 ["+file.name+"] 大小异常！");
                    break;
                case -130:
                    alert("文件 ["+file.name+"] 类型不正确！");
                    break;
            }
        },
        //检测FLASH失败调用
        'onFallback':function(){
            alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
        },
        //上传到服务器，服务器返回相应信息到data里
        'onUploadSuccess':function(file, data, response){
            //response为TRUE 表示上传成功，如果为false表示上传失败
            if(response==true){
             //判断是否为多上上传图片
            if(multi==true){
		    	    $("#"+uploadresult).val(addMultiImageUrl(uploadresult,data));//显示上传成功结果 
            	    var resultImg=$("#"+uploadresult).val();
            	    if(resultImg.lastIndexOf(",")>-1){
            	    var lastImgLength=resultImg.lastIndexOf(",");             	    
            	    resultImg=resultImg.substring(0,lastImgLength);
            	    $("#"+uploadresult).val(resultImg);
             	 }             	 
             }else{
          	 	if(endWith(data,",")){
          	 		data = data.substring(0,data.length-1);
          	 	}
             	$("#"+uploadresult).val(data);//显示上传成功结果      
             }   
            }else{
              //上传失败
              alert("上传失败");
            }
        }
    });
     
}

//处理多图片上传的时候图片路径的添加
function addMultiImageUrl(uploadresult,data){
  var myresult=$("#"+uploadresult).val();
  if(data.length==0){
      myresult="";
      alert("请上传图片");
  }else{
      var len=myresult.length;
      var endstring=myresult.substring(len-1,len);
      if(endstring !=","&& myresult!=""){
           myresult=myresult+",";
      }
      myresult += data+',';        
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
function dealGoods(multi,data,uploadresult,type){
   //判断是否为多上上传图片
      if(multi==true){
           MultiImageUrl(uploadresult,data);//显示上传成功结果
           $(".group").colorbox({rel:'group1'});//加载显示图片方式             	 
      }else{
     	 	if(endWith(response,",")){
     	 		data = data.substring(0,data.length-1);
     	 	}
   	 		//自定义列表页图片上传选择
         	$("#listimgID").attr("src",data);
        	//图列表显示
        	var picStr="";
			picStr="<li><input type='hidden' name='gimg' value="+data+"><img src="+data+" width='50px' height='50px' />"
           +"<div class='imgsshowdiv'><a onclick='setImg(\""+data+"\");'><img class='imgstyle' src='/include/admin/images/setimg.gif'/></a>"+"<a onclick='delImg(this);'><img  class='imgstyle' src='/include/admin/images/delimg.gif'/></a></div></li>";
		    $(".show_pic").append(picStr);
		    //填充列表页选择框
            $("#"+uploadresult).val(data);//显示上传成功结果  
      }  
}

//处理商品多图片上传的时候图片路径的添加
function MultiImageUrl(uploadresult,data){
	 var myresult=$("#"+uploadresult).val();
     var len=myresult.length;
     var endstring=myresult.substring(len-1,len);
     if(endstring !=","&& myresult!=""){
          myresult=myresult+",";
     }
     myresult += data+','; 
     if(myresult.lastIndexOf(",")>-1){
   	 var lastImgLength=myresult.lastIndexOf(",");             	    
   	 resultImg=myresult.substring(0,lastImgLength);    	 
   	 $("#"+uploadresult).val(resultImg);
     }  
     //加载到显示列表中
     showpicList(data);
     return myresult;   
}

//显示商品图片列表
function showpicList(data){
  if($(".show_pic").html()==""){
  	 $("#listimgID").attr("src",data);
  	 $("#uploadoneimage").val(data);
  }
  var picStr="";
  picStr="<li><input type='hidden' name='gimg' value="+data+"><img src="+data+" width='50px' height='50px' />"
  +"<div class='imgsshowdiv'><a onclick='setImg(\""+data+"\");'><img class='imgstyle' src='/include/admin/images/setimg.gif'/></a>"+"<a onclick='delImg(this);'><img  class='imgstyle' src='/include/admin/images/delimg.gif'/></a></div></li>";
  $(".show_pic").append(picStr);
  myfun();
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
			$("#listimgID").attr("src","/include/common/images/nopicture.png");
			$("#uploadoneimage").val("");
		}
		//清除点击图片
		$(obj).parent("div").parent("li").remove();
    }
}

//设置商品列表页图片
function setImg(imgval){
art.dialog({
	title: '系统提示',
	content:'<div class="decorate">'+'确定设置为列表图?'+'</div>',
	okValue: '确定',
	width: '238px',
	cancelValue: '取消',
    ok: function () {
	    $("#listimgID").attr("src",imgval);
		$("#uploadoneimage").val(imgval);
    },
    cancel: function () {
		return true;
    }
});
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

