 /*
*功能：系统栏目管理
*主要有：更新栏目选择更新模板、栏目排序批量操作、实现树的全选功能
*页面模板选中文件、详细页模板 选中文件、判断栏目名称是否已经存在、AJAX调用更新网站的方法所有栏目
*/
 //更新栏目选择更新模板
 function selectchanneltemp_path(template_code)
  {
     window.open("/include/fileselectlist.jsp?id=tempvalue&template_code="+template_code,null, "height=580,width=700,status=no,toolbar=no,menubar=no,location=no");
  }
 //更新详细页选择更新模板
 function selectchannelarticle_temp(template_code)
 {
   window.open("/include/fileselectlist.jsp?id=tempvalueart&template_code="+template_code,null, "height=580,width=700,status=yes,toolbar=no,menubar=no,location=no");
 }     
 
//栏目排序批量操作
function updateChannelSortNo(actionName) {
		var admin_ch_id = "";
		var chks = document.getElementsByTagName("input");//得到所有input
		for (var i = 0; i < chks.length; i++) {
			if (chks[i].type.toLowerCase() == "checkbox") {
                //得到所名为checkbox的input
				admin_ch_id += chks[i].value + ",";
			}
		}
		var len = admin_ch_id.length;
		if (len == "0") {
			alert("\u64cd\u4f5c\u5931\u8d25\uff01\u8bf7\u91cd\u65b0\u64cd\u4f5c\uff01");
		} else {
			var sort_no_id = admin_ch_id.substring(0, len - 1);
			document.getElementById("admin_Sort_id").value = sort_no_id;//用于隐藏所有的ID
			document.forms[0].action = "admin_Channel_updateAllSortNo.action";
			document.forms[0].submit();
		}
}
//实现树的全选功能
function checkStatus(no, chkBox) {
	var chks = document.getElementsByTagName("input");//得到所有input
	for (var i = 0; i < chks.length; i++) {
		if (chks[i].type.toLowerCase() == "checkbox") {
                //得到所名为checkbox的input
			if (chks[i].className == no) {
                    //ID等于传进父目录ID时
				chks[i].checked = chkBox.checked;//保持选中状态和父目录一致
				this.checkStatus(chks[i].value, chks[i]);//递归保持所有的子目录选中
			}
		}
	}
}
/////////////////////////////////////
  function  selectFile()
	{
		var objdiv =document.getElementById("searchDiv");
	    objdiv.style.display="block"; 
	} 
/////////////////////////////////////////////////	
//新版的网站更新功能
 function updateNewPage(flageName,updateId ,update_ch_name,update_type)
 {
   if(flageName=="all"){
   		updateId = "0";
   		update_ch_name = "全部";
   }
   updateAllChannelPages(updateId,update_ch_name,flageName,update_type);
}

 ///////////////////////////////////////////////////
  //AJAX调用更新网站的方法所有栏目
    function updateAllChannelPages(update_ch_id,update_ch_name ,flages,update_type){
    $("#msg2").html("&nbsp;");
 	$.ajax({  	 
            type: "post",    	     
            url: "/admin_Channel_updateChannelPage.action?update_type="+update_type+"&update_state="+flages+"&update_ch_id="+update_ch_id+"&ajaxRequestRandom="+Math.random(),       
            datatype:"json",
            async:false,
            success: function(data){
             var datastr=data.split('@');
             var datastring=datastr[0].toString();
             var prestring="";
             if(datastr.length>1){
             prestring= datastr[1].toString();
             }
              if(datastring=="0")
              {
                $("#msg2").html("更新<"+update_ch_name+">栏目失败,请重新操作！");
              }
               if(datastring=="1")
              {
               $("#msg2").html("更新<"+update_ch_name+">栏目成功！"+prestring);
              }
              if(datastring=="2")
              {
               $("#msg2").html("更新未全部成功！存在<"+datastr[1].toString()+">栏目,更新失败！请重新操作！");
              }
            
   	        } 
       });
        $("#loading").ajaxStart(function(){$(this).css("display", "block");});//用于显示加载图标
		$("#loading").ajaxSuccess(function(){$(this).css("display", "none");});	
       } 
//////////////////////////////////////////////////
	 function updatePage()//更新栏目
       {
       var o=document.getElementById("channelid");
       var pagesvalue=o.options[o.options.selectedIndex].value; 
       var pagestext=o.options[o.options.selectedIndex].text; 
       if(pagesvalue==-1)
       {
        alert("请选择网站栏目！");
       }
       else
       {
       updateChannelPages(pagesvalue,pagestext);
       }
       }
///////////////////////////////////////////////////       

   //AJAX调用更新网站的方法
    function updateChannelPages(update_ch_id,update_ch_name){
 	$.ajax({  	 
            type: "post",    	     
            url: "/admin_Channel_updateChannelPage.action?update_ch_id="+update_ch_id+"&ajaxRequestRandom="+Math.random(),       
            datatype:"json",
            async:false,
            success: function(data){    
              if(data=="0")
              {
               $("#msg").html("更新<"+update_ch_name+">栏目失败,请重新操作！");
              }
              else
              {
                 $("#msg").html("更新"+update_ch_name+"栏目成功！");
              }
            
   	        } 
       });
        $("#loading").ajaxStart(function(){$(this).css("display", "block");});//用于显示加载图标
		$("#loading").ajaxSuccess(function(){$(this).css("display", "none");});	
       } 
 /////////////////////////////////////////////      
//判断栏目名称是否已经存在
function checkcname()
     {
      var cnames=$("#cnames").val();
      $.ajax({  	 
            type: "post",    	     
            url: "/admin_Channel_getCName.action?cname="+cnames,       
            datatype:"json",
            async:false,
            success: function(data){   
              if(data !="0")
              {
               $("#cname").html("*(已经存在该栏目名称！)");
              }
              else
              {
              $("#cname").html("");
              }
             
   	        } 
       });
     }
 ////////////////////////////////////////    
  //判断栏目名称是否已经存在
function updatecheckcname()
     {
      var cnames=$("#cnames").val();
      var hid=$("#hid").val();
      $.ajax({  	 
            type: "post",    	     
            url: "/admin_Channel_getCName.action?channel_id="+hid+"&cname="+cnames+"&ajaxRequestRandom="+Math.random(),       
            datatype:"json",
            async:false,
            success: function(data){   
              if(data !="0")
              {
               $("#cname").html("*(已经存在该栏目名称！)");
              }
              else
              {
              $("#cname").html("");
              }
             
   	        } 
       });
     }
//////////////////////////////////////



/**更新PC端或者触屏版栏目页**/
function doHtmlPage(ch_id,pc_webapp){
	vch_id="";
	var msgValue="";
	var checks = document.getElementsByName(ch_id);
	 for(var i=0;i<checks.length;i++){
		if(checks[i].checked){
			if(checks[i].value!=""){
				vch_id += checks[i].value+',';
			}
		}
	}
	// 删除最后一个字符
	vch_id = deleteLastChar(vch_id,",");
	if(vch_id==''){
       jNotify("请选择要更新的栏目!");
       return;
	}else{
	    if(pc_webapp=="0"){//PC端
	       msgValue="#msg2";
	    }else if(pc_webapp=="1"){//触屏版
	       msgValue="#msg3";
	    }
	    $(msgValue).html("<img src='/include/common/images/loaderbar.gif'/>更新中...").css("color","red");
		$.ajax({  	 
            type: "post",    	     
            url: "/channel!updateHtmlPage.action?ch_id="+vch_id+"&is_pc_webapp="+pc_webapp+"&timeStamp=" + new Date().getTime()+"&ajaxRequestRandom="+Math.random(),       
            datatype:"json",
            async:true,
            cache:false,
            success: function(data){ 
	             if(data=="1"){   
	                $(msgValue).html("更新成功").css("color","green");
	             }
	             else if(data=="0"){
	                $(msgValue).html("更新失败");
	             }
	             else if(data=="2"){
	                $(msgValue).html("更新失败,用户已过期,请重新登录");
	                alert("更新失败,用户已过期,请重新登录");
	             }
   	        } 
       });
	}
} 

function doArticlePage(type,ch_id){

	$("#msg").html("<img src='/include/images/admin/upLoading.gif'/>更新中...");
	var vtype="",vch_id="";
	if(type!=null){
		vtype = type;
	}
	if(ch_id!=null){
		vch_id = ch_id;
	}
	if(vtype!=""){
 	$.ajax({
            type: "post",
            url: "/channel!updateArticleHtml.action?type="+vtype+"&ch_id="+vch_id+"&timeStamp=" + new Date().getTime()+"&ajaxRequestRandom="+Math.random(),       
            datatype:"json",
            async:true,
            cache:false,
            success: function(data){    
	            if(data=="0")
	            {
	               $("#msg").html("更新失败");
	            }
	            else if(data=="1")
	            {
	             $("#msg").html("更新成功");
	            }
   	        } 
       });
    }
    else
    {
     $("#msg").html("更新失败");
    }
} 
//排序
 function updatesort_no(actionName){  
      var admin_group_id='';
      var chks =document.getElementsByTagName('input');//得到所有input
      for(var i=0;i <chks.length;i++){ 
      if(chks[i].type.toLowerCase() == 'checkbox'&&chks[i].value!='on')
      {
           //得到所名为checkbox的input
           admin_group_id+=chks[i].value+',';
          
        }
      }
       var len=admin_group_id.length;
       var group_id=admin_group_id.substring(0,len-1);
       document.getElementById('admin_Sort_id').value = group_id;//用于隐藏所有的ID
	document.forms[0].action=actionName;
	document.forms[0].submit();
 }

		  
		  
		  