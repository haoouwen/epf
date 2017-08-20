 /*
*功能：系统会员自定义分类
*主要有：更新会员自定义分类排序
*/

//排序批量操作
function updateSortNo(){
   var admin_cat_id='';
   var admin_sort_no='';
      var chks = document.getElementsByTagName('input');//得到所有input
       var inputchks=document.getElementsByTagName('input');//得到所有input排序控件的值
         for(var i=0;i <chks.length;i++){           
	          if(chks[i].type.toLowerCase() == 'checkbox'&&chks[i].value!='on')
	           {
	               for(var j=0;j<inputchks.length;j++)
	               {
	                 	if(chks[i].value==inputchks[j].id)
	                 	{
	                  	 	//得到所名为checkbox的input
	                        admin_cat_id+=chks[i].value+',';
	                    	admin_sort_no+=inputchks[j].value+',';	          
	                    	if(inputchks[j].value==''){
	                    		jNotify("排序文本框不能为空!"); 
	                    		return;
	                    	}         
	                	 }
	               } 
	            }
         }
          //移除获取最后的逗号即是：','
          var len=admin_cat_id.length;
          var len1=admin_sort_no.length;
          var cat_id=admin_cat_id.substring(0,len-1);
          var sort_no=admin_sort_no.substring(0,len1-1);
          document.getElementById('admin_cat_id').value = cat_id;//用于隐藏所有的ID
          document.getElementById('admin_sort_no').value = sort_no;//用于隐藏所有的排序值
		  document.forms[0].action='bmall_Membercat_updateSortNo.action';
		  document.forms[0].submit();
}


//更新会员自定义分类排序
 function updatemembercats(actionName)
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
		 alertShow('请至少选择一条记录!','warning');
		 runCount(3);
		 return false;
	 	}
	 	if(flag==true){	 	   
		       var member_cat_id='';
	           var member_cat_name='';
	           var member_sort_no='';
		       var chks =document.getElementsByTagName('input');//得到所有input
		       var inputchks=document.getElementsByTagName('input');//得到所有input
		       var selectchk=document.getElementsByTagName('select');//得到所有select
	           for(var i=0;i <chks.length;i++){   
	           		if(chks[i].type.toLowerCase() == 'checkbox'&&chks[i].value!='on')
	          		 {
	                	if(chks[i].checked)
	                	{
	                		 for(var j=0;j<inputchks.length;j++)
	                		   {
	                   			 if(chks[i].value==inputchks[j].id)//用于获取更新排序的值
	                   			 {
	                     			 member_sort_no+=inputchks[j].value+',';
	                     			 if(inputchks[j].value==''){
		                     			 jNotify("排序文本框不能为空!"); 
		                    			 return;
	                     			 }	                     
	                  			 }
	                  			 if(chks[i].value==inputchks[j].name)//用户获取分类更新名称
	                  			 {
	                  			   if(inputchks[j].value=="")
	                  			   {
	                  			       jNotify("分类名称不能为空!"); 
		 							   runCount(3);
									   return false;
	                  			   }else{
	                  			       member_cat_name+=inputchks[j].value+',';
	                  			    }
	                  			  }
	                		   }
	                		   member_cat_id+=chks[i].value+',';//用于获取选择更新的分类ID
	                      }
	                  }
	            }
	            //移除获取最后的逗号即是：','
	            var lencatid=member_cat_id.length;
	            var catid=member_cat_id.substring(0,lencatid-1);
	            
	            var lencatname=member_cat_name.length;
	            var catname=member_cat_name.substring(0,lencatname-1);
	            
	            var lensort=member_sort_no.length;
	            var sortno=member_sort_no.substring(0,lensort-1);
	            
	            document.getElementById('member_cat_id').value = catid;//用于隐藏被选中所有ID的值
	            document.getElementById('member_sort_no').value = sortno;//用于隐藏被选中输入排序的值
	            document.getElementById('member_cat_name').value = catname;//用于隐藏被选中输入分类名称的值
		    	art.dialog({
				title: '系统信息提示',
		    	content: '<div class="decorate">确认修改？</div>',
		    	width: '15%',
		    	icon: 'question',
		    	yesFn: function () {
		    	document.forms[0].action=actionName;
				document.forms[0].submit();
		        return false;
		    },
		    noText: '关闭',
		    noFn: true //为true等价于function(){}
		    });
	 	}
}
      