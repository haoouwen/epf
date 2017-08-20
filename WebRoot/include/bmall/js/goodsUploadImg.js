//上传商品图片工具,支持多张上传
function uploadImgMulti(){
	uploadComponent("uploadifyfile","uploadresult","fileQueue","goods",true,'');
}

//上传单张商品图片工具
function uploadOneImg(uploadifyfile,uploadresult,fileQueue){
	uploadComponent(uploadifyfile,uploadresult,fileQueue,"goods",false,'');
}

//系统自定义规格的图片上传控件
function uploadSelfImg(uploadifyfile,uploadresult,fileQueue,uploadImgID){
	uploadComponent(uploadifyfile,uploadresult,fileQueue,"sys",false,uploadImgID);	
}


//uploadifyfile：文件路径输入框的ID
//uploadresult：上传文件成功后的文件保存路径
//fileQueue:文件上传效果队列
//type:上传文件类型 image,file,flash,ads,model,sys
//multi:多文件上传 true,false
//moveId:动态ID,用于动态控件生成的上传图片控件
function uploadComponent(uploadifyfile,uploadresult,fileQueue,type,multi,moveId){

	var upAction;
	var sizeLimit;
	var fileDesc;
	var fileExt;
	if(type == 'image'){
		upAction = '/uploadfy_executeUpimages.action';
		sizeLimit = 6291456;
		fileDesc = '支持格式:jpg/gif/jpeg/png/bmp.';
		fileExt = '*.jpg;*.gif;*.jpeg;*.png;*.bmp';
	}else if(type=='goods'){
		upAction = '/uploadfy_executeUpGoodsImage.action';
		sizeLimit = 6291456;
		fileDesc = '支持格式:jpg/gif/jpeg/png/bmp.';
		fileExt = '*.jpg;*.gif;*.jpeg;*.png;*.bmp';
	}else if(type=='sys'){
		upAction = '/uploadfy_executeUpSysImage.action';
		sizeLimit = 6291456;
		fileDesc = '支持格式:jpg/gif/jpeg/png/bmp.';
		fileExt = '*.jpg;*.gif;*.jpeg;*.png;*.bmp';
	}
	//图片上传控件图片的图标路径
    var imagepath="/plugin/upload";
	$("#"+uploadifyfile).uploadify({
          'uploader'       : imagepath+'/uploadify.swf',//控制弹出选择文件框
          'script'         : upAction,//执行的xx.action
          'cancelImg'      : imagepath+'/cancel.png',//选择文件到文件队列中后的每一个文件上的关闭按钮图标
          'folder'         : 'uploadifyfile',//上传文件存放的目录 。
          'queueID'        : fileQueue,//文件队列的ID，该ID与存放文件队列的div的ID一致。
          'auto'           : true,//是否自动上传图片
          'multi'          : multi,//是否支持多上传
          'sizeLimit'      : sizeLimit,//上传文件的大小限制 。
          'displayData'    : 'percentage',//有speed和percentage两种，一个显示速度，一个显示完成百分比
          'fileDesc'       : fileDesc, //这个属性值必须设置fileExt属性后才有效，用来设置选择文件对话框中的提示文本，如设置fileDesc为“请选择rar doc pdf文件”，打开文件选择框效果
          'simUploadLimit' : 2,//支持同时上传图片的个数
          'fileExt'        : fileExt,//设置可以选择的文件的类型，格式如：'*.doc;*.pdf;*.rar' 。
          'buttonText'     : 'Browser',//浏览按钮的文本，默认值：BROWSE 。
          'fileDataName'   : 'uploadifyfile',  //设置一个名字，在服务器处理程序中根据该名字来取上传文件的数据。默认为Filedata
          'height'         : 30,  //设置浏览按钮的高度 ，默认值：30。
		  'width'          : 99,   //设置浏览按钮的宽度 ，默认值：110。
		  'buttonImg'      : '/includes/admin/images/uploadImg.gif',  //浏览按钮的图片的路径 。只要是用解决按钮文字显示不支持中文的办法
		  'wmode'          : 'transparent',//设置该项为transparent 可以使浏览按钮的flash背景文件透明，并且flash文件会被置为页面的最高层。 默认值：opaque 。
          'onComplete'     : function (event, queueID, fileObj, response, data){ 
           	   if(type=="goods"){
           	  	   dealGoods(multi,response,uploadresult,type);
           	   }else{
           	   		if(endWith(response,",")){
		     	 		response = response.substring(0,response.length-1);
		     	 	}
		     	 	if(moveId!=''){
			        	//自定义图片上传选择
			        	$("#showselfImg"+moveId).attr("src",response);
			       	}	             	
		          	$("#"+uploadresult).val(response);//显示上传成功结果 
           	   }
          }
    });
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
function MultiImageUrl(uploadresult,responses)
{
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
  +"<div><a class='setimg' onclick='setImg(\""+responses+"\");'></a>"+
  "<a class='delimg' onclick='delImg(this);'></a></div></li>"
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
			$("#listimgID").attr("src","/includes/admin/images/nopic.jpg");
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

//判断字符串是否是以某个字符结尾
function endWith(s1,s2)  
{  
	if(s1.length<s2.length)
		return	false;
	if(s1==s2)
		return	true;
	if(s1.substring(s1.length-s2.length)==s2)
		return	true;
	return	false;
}
