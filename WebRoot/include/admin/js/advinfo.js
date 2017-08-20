function updateAdvposBatch(actionName){
     var admin_pos_posid='';
     var chks =document.getElementsByTagName('input');//得到所有input
     for(var i=0;i <chks.length;i++){ 
      	if(chks[i].type.toLowerCase() == 'checkbox'&&chks[i].value!='on'){
           //得到所名为checkbox的input
           admin_pos_posid+=chks[i].value+',';
        }
     }
     var len=admin_pos_posid.length;
     var pos_id=admin_pos_posid.substring(0,len-1);
     document.getElementById('batch_update_hidden_posid').value = pos_id;//用于隐藏所有的ID
     document.forms[0].action=actionName;
	 document.forms[0].submit();
}   
//广告位，选择中广告类型改变页面显示
function selectType(actionName){
	var type=$("#advtype").val();
	$("#ad_type").val(type);
	document.forms[0].action=actionName;
	 document.forms[0].submit();
}
function addInfo(){
	alert("该广告位已添加广告！");
}



function isview(tablename)
{
    window.open("/admin_Advinfo_indexinfo.action?tablename="+tablename);
}
$(document).ready(function(){ 
     var selvalue=$("select[name='advpos.pos_type']").find('option:selected').text(); 
     $("#idvalue").html(selvalue);
})