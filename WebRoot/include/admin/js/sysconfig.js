//排序
      function updatesort_no(actionName){  
      var admin_group_id='';
      var chks =document.getElementsByTagName('input');//得到所有input
      for(var i=0;i <chks.length;i++){ 
      if(chks[i].type.toLowerCase() == 'checkbox'&&chks[i].value!='on'){
           //得到所名为checkbox的input
           admin_group_id+=chks[i].value+',';
        }
      }
      var len=admin_group_id.length;
      var group_id=admin_group_id.substring(0,len-1);
      document.getElementById('sysconfig_sortno_id').value = group_id;//用于隐藏所有的ID
   document.forms[0].action=actionName;
   document.forms[0].submit();
}




	function setHiddenVal(val_name,val){
		document.getElementById("set_"+val_name).value = val;
	}

function updateSysconfigBatch(actionName){
     art.dialog({
			title: '系统信息提示',
			content:'<div class="decorate">'+'您确定要修改吗？'+'</div>',
			okValue: '确定',
			width: '238px',
			cancelValue: '取消',
		    ok: function () {
		       	  var cfg_count=0;
				   $("input:[name='sysconfig.var_value']").each(function(){
				        if($(this).val()==''){
				        	cfg_count+=1;
				        }   
				   });
				   if(cfg_count>0){
				  	  jNotify("文本框不能为空!"); 
				   }else{
					   var admin_config_varid='';
				       var chks = document.getElementsByTagName('input');//得到所有input
			           for(var i=0;i <chks.length;i++)
			           { 
			              if(chks[i].id.toLowerCase() == 'varid')
			              {
			                admin_config_varid+=chks[i].value+',';               
			              }               
			           }           
			           var len=admin_config_varid.length;
			           var var_id=admin_config_varid.substring(0,len-1);
			           document.getElementById('admin_sysconfig_varid').value = var_id;//用于隐藏所有的ID           
					   document.forms[0].action=actionName;
					   document.forms[0].submit();		   
				   }
		    },
		    cancel: function () {
				return true;
		    }
		});
}

      function deleteVal(var_id,mod){
      
      art.dialog({
			title: '系统提示',
			content:'<div class="decorate">'+'确定要删除？'+'</div>',
			okValue: '确定',
			width: '238px',
			cancelValue: '取消',
		    ok: function () {
		        location.href='/admin_Sysconfig_del.action?sysconfig.var_id='+var_id;
	        return false;
		    },
		    cancel: function () {
				return true;
		    }
		});
      }
      
      function update(var_id){
      		if(var_id==null||var_id==""){
      			alert("请选择对应的数据！");
      			return false;
      		}else{
      			document.location.href="/admin_Sysconfig_view.action?var_id="+var_id;
      		}
      }
      
      //验证不能为空，且如果是英文逗号的话把它替换成中文
      function checkNull_dou(obj){
          var obj_value=$(obj).val();
          if(obj_value==''){
              jNotify("该文本框不能为空!"); 
              return;
          }else if(obj_value.indexOf(','>-1)){          
              var obj_val=obj_value.replace(",","，");
              $(obj).val(obj_val);
          }
      }
      //获取菜单的索引，并赋值隐藏域
	  function getindex(index){
	  	if(index!=''){
	  		$("#men_index").val(index);
	  	}else{
	  		$("#men_index").val(0);
	  	}
	  	//$("#myform").attr("action","/admin_Sysconfig_newlist.action").submit();
	  }
	  //系统参数配置提交
	  function syssubmi(){
		var mark="##########";
		var des="";
		var valu="";
		var sort_no="";
		$(".desc").each(function(i){
			//获取var_id并拼接
			des+=$(this).val()+mark;
			//根据id找到对应的值var_value
			valu+=$(".input").eq(i).val()+mark;
			sort_no+=$(".sortinput").eq(i).val()+mark;
		});
		des=deleteLastChar(des,mark);
		valu=deleteLastChar(valu,mark);
		sort_no=deleteLastChar(sort_no,mark);
		$("#sysdesc").val(des);
		$("#sysvalue").val(valu);
		$("#syssort").val(sort_no);
		document.getElementById("myform").submit()   
	}
	
      