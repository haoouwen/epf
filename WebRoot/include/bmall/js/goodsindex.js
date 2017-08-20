$(document).ready(function(){
	      //提交表单
		  $("#indexForm").submit(function(){
	          var cat_attr_str="";
	          $("input:hidden[name='cat_attr_str']").each(function(){
	              cat_attr_str+=$(this).val()+"|";
	          }) 
	          $("#cat_attr_str").val(cat_attr_str);     
	          return true;
		   });
	    });
	   
	    //批量操作
    	function updateStatedown(is_ip,field_name,actionName){
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
				if(is_ip=='1'){
					tip = '确认下架吗?';
				}
				art.dialog({
				title: '系统信息提示',
				content:'<div class="decorate">'+tip+'</div>',
				okValue: '确定',
				width: '238px',
				cancelValue: '取消',
			    ok: function () {
			        document.getElementById('bmall_goods_is_ip').value = is_ip;
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
		
		
		
		//批量操作
    	function updateStateup(is_ip,field_name,actionName){
			var flag = false;
			var checks = document.getElementsByName(field_name);
			for(var i=0;i<checks.length;i++){
				if(checks[i].checked){
					flag=true;
					break;
				}
			}
			if(flag==false){
			    art.dialog({
				title: '系统提示',
			    content: '请至少选择一条记录!'
			    });
				return false;
			}
			if(flag==true){
			    var tip = '';
				if(is_ip=='1'){
					tip = '确认上架吗?';
				}
				art.dialog({
				title: '系统信息提示',
				content:'<div class="decorate">'+tip+'</div>',
				okValue: '确定',
				width: '238px',
				cancelValue: '取消',
			    ok: function () {
			        document.getElementById('bmall_goods_is_ip').value = is_ip;
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