function updateIsuse(state,field_name,actionName){
			var flag = false;
			var checks = document.getElementsByName(field_name);
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
			    var tip = '';
				if(state=='0'){
					tip = '确认设置启用吗?';
				}else if(state=='1'){
					tip = '确认设置禁用吗?';
				}
				art.dialog({
				title: '系统信息提示',
				content:'<div class="decorate">'+tip+'</div>',
				okValue: '确定',
				width: '238px',
				cancelValue: '取消',
			    ok: function () {
			        document.getElementById('admin_Sysmodule_state').value = state;
				    document.forms[0].action=actionName;
				    document.forms[0].submit();
			        return false;
			    },
			   cancel: function () {
						return true;
				    }
			    });
				
			}
		}
		
		
		

		
  function updatesort_no(actionName)
      {  
           var admin_group_id='';
	       var chks =document.getElementsByTagName('input');//得到所有input
           for(var i=0;i <chks.length;i++)
          { 
            
           if(chks[i].type.toLowerCase() == 'checkbox'&&chks[i].value!='on')
           {
                //得到所名为checkbox的input
                admin_group_id+=chks[i].value+',';
               
             }
           }
            var len=admin_group_id.length;
            var group_id=admin_group_id.substring(0,len-1);
            document.getElementById('admin_sysmodule_id').value = group_id;//用于隐藏所有的ID
			document.forms[0].action=actionName;
			document.forms[0].submit();
      }