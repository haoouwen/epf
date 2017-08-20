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
		        document.getElementById('paravalue_sortno_id').value = group_id;//用于隐藏所有的ID
				document.forms[0].action=actionName;
				document.forms[0].submit();
}