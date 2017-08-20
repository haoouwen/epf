 /*
*功能：系统企业站的栏目选择
*主要有：更新企业站栏目的排序
*/
      
//更新企业站栏目内容
function updateChannel(actionName){   
	var admin_group_id='';
	var sort='';
	var name='';
	var num='';
	var sort_no=document.getElementsByName('isort_no');//获得所有排序列表框的值
	var ch_name=document.getElementsByName('ch_name');//获取所有栏目名称
	var ch_num=document.getElementsByName('chnum');//获取所有数量的值
	
  	var sortflag=false;	
    var cnameflag=false;
    var numflag=false;
    
    $(".ch_box").each(function(i){
     	  if(this.checked){
     	  	  //得到所名为checkbox的input
              admin_group_id+=this.value+","; 
              sort+=sort_no[i].value+',';
              name+=ch_name[i].value+',';
              num+=ch_num[i].value+',';      
              if(sort_no[i].value=""){
              	sortflag = true;
              }
              if(ch_name[i].value==""){
              	cnameflag = true;
              }
              if(ch_num[i].value==""){
              	numflag = true;
              }             
     	  }     	  
     })
     if(admin_group_id==''){
     	jNotify("请先选择要排序记录!"); 
		return;	
     } 
     if(sortflag){
     	jNotify("排序文本框不能为空!"); 
		return;	
     }
     if(cnameflag){
     	jNotify("栏目名称不能为空!"); 
		return;	
     }
      if(numflag){
     	jNotify("数量不能为空!"); 
		return;	
     }
    
     var len=admin_group_id.length;
     var group_id=admin_group_id.substring(0,len-1);
     $("#member_memberchannel_id").val(group_id);
     
     var lensort=sort.length;
     var sort_nos=sort.substring(0,lensort-1);
     $("#member_sort").val(sort_nos);
      
     var lenname=name.length;
     var ch_names=name.substring(0,lenname-1);
     $("#member_name").val(ch_names);
      
     var lennum=num.length;
     var ch_nums=num.substring(0,lennum-1);
     $("#member_num").val(ch_nums);
      
	 document.forms[0].action=actionName;
	 document.forms[0].submit();
}
      