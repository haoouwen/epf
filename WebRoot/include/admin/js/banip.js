/*
*功能：系统IP地址拦截操作
*主要有：更新IP地址、更新IP检查输入框是否正确、鼠标触发文本框的时候，弹出提示输入IP格式、检查输入的IP地址是否有效的IP地址
*检查输入List的IP地址是否有效的IP地址
*/
//更新IP地址
 function updatebanip(actionName)
      {
    	 var flag = false;
		 var checks = document.getElementsByTagName('input');//得到所有input
		 for(var i=0;i<checks.length;i++){
			if(checks[i].checked){
				flag=true;
				break;
				}
			}
		 if(flag==false){
		 alert('请至少选择一条记录!','warning');
		 runCount(3);
		 return false;
	 	 }
	 	if(flag==true){
	 	     
           var admin_ip_id='';//
           var admin_ip='';
           var ckflages=true;
	       var chks =document.getElementsByTagName('input');//得到所有input
	       var inputchks=document.getElementsByTagName('input');//得到所有input
           for(var i=0;i <chks.length;i++)
         	 {   
           		if(chks[i].type.toLowerCase() == 'checkbox'&&chks[i].value!='on')
          		 {
                	if(checks[i].checked)
                	{
                		 for(var j=0;j<inputchks.length;j++)
                		   {
                   			 if(chks[i].value==inputchks[j].id)
                   			 {
                   			     if(!chkipsture(chks[i].value,inputchks[j].value))
                                 {
                                   ckflages=false;
                                 }
                    			 admin_ip_id+=chks[i].value+',';
                     			 admin_ip+=inputchks[j].value+',';
                  			 }
                		   }
                  
                    }
               
                  }
            }
            var len=admin_ip_id.length;
            var ip_id=admin_ip_id.substring(0,len-1);
            var lenip=admin_ip.length;
            var adminip=admin_ip.substring(0,lenip-1);
            document.getElementById('admin_ipid').value = ip_id;//用于隐藏被选中所有IP的值
            document.getElementById('adminip').value = adminip;//用于所有输入IP的值
            if(ckflages)
            {
               art.dialog({
	          title: '系统信息提示',
              content: '<div class="decorate">您确定更新IP吗？</div>',
              okValue: '确定',
		 	  width: '238px',
			  cancelValue: '取消',
              ok: function () {
              $("#indexForm").attr("action",actionName);
			  $("#indexForm").submit();
            },
           cancel: function () {
						return true;
				    }
           });
			   
			}
			else
			{
			  alertShow('IP段输入不正确!','warning');
		 	  runCount(3);
		 	  return false;
			}
            return false;
           
        }
      }
      //更新IP检查输入框是否正确
    function checkupdateIp(obj)
    {
        var objids=obj.id;
     	var vartext=obj.value;
     	chkipsture(objids,vartext);
    }
    //鼠标触发文本框的时候，弹出提示输入IP格式
    function tipsget(obj)
    {
     var objids=obj.id;
     var objtips="#tip_"+objids;
     $(objtips).css("display","");
    }
     //检查输入的IP地址是否有效的IP地址
      function checkip(name,nameend){
             
    		 	var str =document.getElementById(name).value;
   			 	var strlength= str.length; 
   			 	if(strlength <1){
   			    	alertShow('输入的起始IP不能为空!','warning');
   			    	return false; 
		       	    runCount(3); 
   			 	}
   			 	else
   			 	{ 
      			if(strlength>15||strlength <7)          //IP的字段长度的限制 
       			 { 
       		  	 	 alertShow('您输入的起始IP长度不正确，必须是7到15位!','warning');
       		  	 	 return false; 
		        	runCount(3); 
        	 	} 
        		var patrn =/^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/;//正则表达式，\d为数字,{1,3}一位或为三位. 
        		if(!patrn.exec(str)){
        	    	alertShow('您输入的起始IP格式不正确，必须是000.000.000.000格式!','warning');
        	    	return false; 
		        	runCount(3); 
            	
        		}    
        		var laststr; 
        		laststr= str.split(".");    //用.把字符串str分开 
        		var last_patrn=/^\d{1,3}$/; 
        		if(parseInt(laststr[0])>255||parseInt(laststr[1])>255||parseInt(laststr[2])>255||parseInt(laststr[3])>255) //判断IP每位的大小 
        		{ 
        	    	alertShow('您输入的起始IP范围不正确，必须是0~255之间!','warning');
        	     	return false;
		        	runCount(3); 
                
       		 	} 
        		if(!last_patrn.exec(laststr[3])) 
        		{ 
             	 	alertShow('您输入的起始IP格式不正确，必须是000.000.000.000格式!','warning');
             	  	return false; 
		         	runCount(3); 
           		
        		} 
        		var strend=document.getElementById(nameend).value;
    		 	var strendlength=strend.length;
        	 	if(strendlength <1){
   			    	alertShow('输入的结束IP不能为空!','warning');
   			    	return false; 
		        	runCount(3); 
   			 	}else{ 
      			if(strendlength>15||strendlength <7)          //IP的字段长度的限制 
       			 { 
       		    	alertShow('您输入的结束IP长度不正确，必须是7到15位!','warning');
       		    	return false; 
		        	runCount(3); 
        	 	} 
        		var patrn =/^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/;//正则表达式，\d为数字,{1,3}一位或为三位. 
        		if(!patrn.exec(strend)){
        	    	alertShow('您输入的结束IP格式不正确，必须是000.000.000.000格式!','warning');
        	    	return false; 
		        	runCount(3); 
            	
        		}    
        		var laststrend; 
        		laststrend= strend.split(".");    //用.把字符串str分开 
        		var last_patrnend=/^\d{1,3}$/; 
        		if(parseInt(laststrend[0])>255||parseInt(laststrend[1])>255||parseInt(laststrend[2])>255||parseInt(laststrend[3])>255) //判断IP每位的大小 
        		{ 
        	    	alertShow('您输入的结束IP范围不正确，必须是0~255之间!','warning');
        	    	 return false;
		        	runCount(3); 
                
       			 } 
        		if(!last_patrnend.exec(laststrend[3])) 
        		{ 
             	 	alertShow('您输入的IP格式不正确，必须是000.000.000.000格式!','warning');
             	 	 return false; 
		         	runCount(3); 
           		
        		} 
        	}
        	return true; 
        	} 
        	} 
            //检查输入List的IP地址是否有效的IP地址
          function checklistip(name)
          {
    		 	var str =name;
   			 	var strlength= str.length; 
   			 	if(strlength <1){
   			    	//alertShow('输入的IP不能为空!','warning');
   			    	return false; 
		       	 	//runCount(3); 
   			 	}
   			 	else
   			 	{ 
      				if(strlength>15||strlength <7)          //IP的字段长度的限制 
       			 	{ 
       		  	 	 	//alertShow('您输入的IP长度不正确，必须是7到15位!','warning');
       		  	 	 	return false; 
		        		//runCount(3); 
        	 		} 
        			var patrn =/^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/;//正则表达式，\d为数字,{1,3}一位或为三位. 
        			if(!patrn.exec(str)){
        	    		//alertShow('您输入的IP格式不正确，必须是000.000.000.000格式!','warning');
        	    		return false; 
		        		//runCount(3); 
            	
        			}    
        			var laststr; 
        			laststr= str.split(".");    //用.把字符串str分开 
        			var last_patrn=/^\d{1,3}$/; 
        			if(parseInt(laststr[0])>255||parseInt(laststr[1])>255||parseInt(laststr[2])>255||parseInt(laststr[3])>255) //判断IP每位的大小 
        			{ 
        	    		//alertShow('您输入的IP范围不正确，必须是0~255之间!','warning');
        	     		return false;
		        		//runCount(3); 
                
       		 		} 
        			if(!last_patrn.exec(laststr[3])) 
        			{ 
             	 		//alertShow('您输入的IP格式不正确，必须是000.000.000.000格式!','warning');
             	  		return false; 
		         		//runCount(3); 
           		
        			} 
        	        return true; 
        	   } 
        	} 
     //改变CSS显示错误提示的样式
   function changescss(objtips1, objimageerror1) {
	var objtips = objtips1;
	var objimageerror = objimageerror1;
	$(objimageerror).css("display", "");
	$(objtips).css("display", "");
	$(objtips).css("color", "red");
}
  //检查输入的IP地址是否有效的IP地址END
   function chkipsture(ids, names) {
	var objids = ids;
	var vartext = names;
	var objtips = "#tip_" + objids;
	$(objtips).css("display", "none");
	$(objtips).css("color", "blue");
	var flages = false;
	var fagess = true;
	var objimageerror = "#imes_" + objids;
	$(objimageerror).css("display", "none");
	//判断IP是否为空
	if (vartext != "") {
	    //判断IP是否含有"-"
		if (vartext.indexOf("-") > -1) {
			if (vartext.length > 31) {
				fagess = false;
			} else {
				var countnum = 0;
				countnum = (vartext.split("-")).length - 1;
				//判断IP是否包含有多个"-"
				if (countnum == 1) {
					var strip = vartext.split("-");
					if (strip.length == 2) {
					   //判断起始IP是否正确
						if (!checklistip(strip[0])) {
							fagess = false;
						}
						//判断结束IP是否正确
						if (!checklistip(strip[1])) {
							fagess = false;
						}
					} else {
						fagess = false;
					}
				} else {
					fagess = false;
				}
			}
		} else {
		    //判断单个IP是否正确
			if (!checklistip(vartext)) {
				fagess = false;
			}
		}
	} else {
		fagess = false;
	}
	//如果IP不正确，改变CSS样式提示错误信息
	if (fagess == false) {
		changescss(objtips, objimageerror);
	}
	return fagess;
}
//添加ip地址并验证
function subCheck(ipname,ipnametwo){
	var val=checkipaatt(ipname,ipnametwo);
	if(val){
		document.forms["addForm"].submit();
	}else{
		return;
	}
}
 	//检查输入的IP地址是否有效的IP地址
      function checkipaatt(name,nameend){
             
    		 	var str =document.getElementById(name).value;
   			 	var strlength= str.length; 
   			 	if(strlength <1){
   			    	alert('输入的起始IP不能为空!','warning');
   			    	return false; 
   			 	}
   			 	else
   			 	{ 
      			if(strlength>15||strlength <7)          //IP的字段长度的限制 
       			 { 
       		  	 	 alert('您输入的起始IP长度不正确，必须是7到15位!','warning');
       		  	 	 return false; 
        	 	} 
        		var patrn =/^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/;//正则表达式，\d为数字,{1,3}一位或为三位. 
        		if(!patrn.exec(str)){
        	    	alert('您输入的起始IP格式不正确，必须是000.000.000.000格式!','warning');
        	    	return false; 
            	
        		}    
        		var laststr; 
        		laststr= str.split(".");    //用.把字符串str分开 
        		var last_patrn=/^\d{1,3}$/; 
        		if(parseInt(laststr[0])>255||parseInt(laststr[1])>255||parseInt(laststr[2])>255||parseInt(laststr[3])>255) //判断IP每位的大小 
        		{ 
        	    	alert('您输入的起始IP范围不正确，必须是0~255之间!','warning');
        	     	return false;
                
       		 	} 
        		if(!last_patrn.exec(laststr[3])) 
        		{ 
             	 	alert('您输入的起始IP格式不正确，必须是000.000.000.000格式!','warning');
             	  	return false; 
           		
        		} 
        		var strend=document.getElementById(nameend).value;
    		 	var strendlength=strend.length;
        	 	if(strendlength <1){
   			    	alert('输入的结束IP不能为空!','warning');
   			    	return false; 
   			 	}else{ 
      			if(strendlength>15||strendlength <7)          //IP的字段长度的限制 
       			 { 
       		    	alert('您输入的结束IP长度不正确，必须是7到15位!','warning');
       		    	return false; 
        	 	} 
        		var patrn =/^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/;//正则表达式，\d为数字,{1,3}一位或为三位. 
        		if(!patrn.exec(strend)){
        	    	alert('您输入的结束IP格式不正确，必须是000.000.000.000格式!','warning');
        	    	return false; 
            	
        		}    
        		var laststrend; 
        		laststrend= strend.split(".");    //用.把字符串str分开 
        		var last_patrnend=/^\d{1,3}$/; 
        		if(parseInt(laststrend[0])>255||parseInt(laststrend[1])>255||parseInt(laststrend[2])>255||parseInt(laststrend[3])>255) //判断IP每位的大小 
        		{ 
        	    	alert('您输入的结束IP范围不正确，必须是0~255之间!','warning');
        	    	 return false;
                
       			 } 
        		if(!last_patrnend.exec(laststrend[3])) 
        		{ 
             	 	alert('您输入的IP格式不正确，必须是000.000.000.000格式!','warning');
             	 	 return false; 
           		
        		} 
        	}
        	return true; 
        } 
      }          
          
          
          
         
         