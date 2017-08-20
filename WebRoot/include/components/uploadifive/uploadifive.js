
/*单图上传
*is_multi:是否多图
*
*
*/
function uploadone(upload_id,file_url,is_multi,tip_queue){
   //alert("1111"+upload_id);
   commonupload(upload_id,file_url,is_multi,1,1,tip_queue);
}

/*多图上传
*
*
*
*/
function uploadmulti(upload_id,file_url,is_multi,upload_limit,queue_size_limit,tip_queue){
   commonupload(upload_id,file_url,is_multi,upload_limit,queue_size_limit,tip_queue);
}




function commonupload(com_id,com_url,com_multi,com_uploadlimit,com_queue_size_limit,com_tip_queue){
   $('#'+com_id).uploadifive({
 			 //'auto' : false,   //取消自动上传 
 	    'uploadScript' : '/plugin/uploadfy_upload.action',  //处理上传文件Action路径 
 	    'fileObjName' : 'file',        //文件对象
  	    'buttonText'   : '选择图片',   //按钮显示文字
  	    'multi'        : com_multi,     //是否允许多个文件上传，默认为true
  	    'queueID'      : com_tip_queue, //提示信息放置目标 
  	    'fileType'     : 'image/*',   //允许上传文件类型
  	    'fileSizeLimit' : 5242880,   //允许文件上传的最大尺寸
  	    'uploadLimit'  : com_uploadlimit,   //一次可以上传的最大文件数
        'queueSizeLimit'  : com_queue_size_limit,//允许队列中存在的最大文件数
  	    'removeCompleted' : true, //隐藏完成上传的文件，默认为false
  	    'onUploadComplete' : function(file, data) { //文件上传成功后执行 
  	             $("#"+com_url).val(addMultiImageUrl(com_url,data));//显示上传成功结果 
           	     var resultImg=$("#"+com_url).val();
           	     if(resultImg.lastIndexOf(",")>-1){
           	        var lastImgLength=resultImg.lastIndexOf(",");             	    
           	        resultImg=resultImg.substring(0,lastImgLength);
           	        $("#"+com_url).val(resultImg);
           	      }
 				  console.info('The file ' + file.name + ' uploaded successfully.');
 				  
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

