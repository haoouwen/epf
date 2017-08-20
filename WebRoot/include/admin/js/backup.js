/*
*功能：系统数据库的备份与还原操作。
*主要有：显示所有的备份文件名称、备份数据库、备份指定表、还原指定表、删除备份表的文件、删除备份表的文件等操作
*/
//显示所有的备份文件名称
function showSqlName(type){
		$.ajax({
           type: "post",
           url: "/include/filelist.jsp?type="+type+"&timeStamp=" + new Date().getTime()+"&ajaxRequestRandom="+Math.random(),       
           datatype:"json",
           async:true,
           cache:false,
           success: function(data){    
              $("#sqlNameDiv").html(data);
  	        } 
      });
}	
//备份数据库
function backDb(){
	if(window.confirm("确定要备份数据库吗?")) {
		document.forms[0].action='/admin_BackUp_backup.action';
		document.forms[0].submit();
	}
}	
//备份指定表
 function checkbu() {
 var a = $(":checkbox:checked");
 if(a.length < 1)
   {
    alert("请选择要备份的表");
    return false;
   }
   else
   {
    document.forms[0].action='/admin_BackUp_butable.action';
    document.forms[0].submit();
   }
 }
 //还原指定表
 function checkload(sqlName) {
 	if(window.confirm("确定要还原"+sqlName+"文件吗?")) {
 		document.getElementById('sqlFileName').value = sqlName; 
	    document.forms[0].action='/admin_BackUp_loadSql.action';
	    document.forms[0].submit();
   	}
 }
 //删除备份表的文件
 function deleteTable(sqlName){
     if(window.confirm("确定要删除"+sqlName+"文件吗?")) {
 		document.getElementById('sqlFileName').value = sqlName; 
	    document.forms[0].action='/admin_BackUp_deleteTab.action';
	    document.forms[0].submit();
   	}
 }
 //删除备份表的文件
 function downlaodTable(sqlName){
     if(window.confirm("确定要下载"+sqlName+"文件吗?")) {
 		document.getElementById('sqlFileName').value = sqlName; 
	    document.forms[0].action='/admin_BackUp_downloadTab.action';
	    document.forms[0].submit();
   	}
 }
 //初始化数据库
function initDb(){
	jConfirm('确定要初始化数据库吗？', '系统提示',function(r){ 
		    if(r){ 
		   $("#dbinit").popup({p_width:"275", p_height:"95", pop_title:"初始化数据库 "});
		        }
		  	});
}	