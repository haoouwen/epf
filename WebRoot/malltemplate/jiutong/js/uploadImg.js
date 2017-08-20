
$(function() {  
    $("#dropbox, #uploadifyfile").html5Uploader({  
          name: "uploadifyfile",
          postUrl: "/uploadfy_executeUpimages.action",
          onServerReadyStateChange:changeFileUrl
    });  
});  

function changeFileUrl(){
   if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200){    
        var img_url = xmlHttpRequest.responseText;
        $("#member_icon").val(img_url);       
    }    
}

function submitIcon(){
  
  $("#uploadIconForm").submit();
}
