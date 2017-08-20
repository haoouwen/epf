<html>
  <head>
    <title>添加在线编辑器</title>
	<script type="text/javascript"  src="/include/js/jquery.timers-1.1.2.js"></script>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Autofck_insert.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
		           <tr>
		             <td class="table_name">ff<font color='red'>*</font></td>
		             <td>
		             	<@s.textarea  name="autofck.content" id="content" cssClass="txtinput" />
		             	<script type="text/javascript"  src="/include/components/ckeditor/ckeditor.js"></script>
						<script type="text/javascript">
					     var editor=CKEDITOR.replace('content');
						</script>  
		             	<@s.fielderror><@s.param>autofck.content</@s.param></@s.fielderror>
		             </td>
		           </tr>
         </table>
	     <div class="fixbuttom">
	       <@s.token/> 
	       <@s.hidden id="random_num" name="random_num" value="1234567890"/> 
	       ${listSearchHiddenField?if_exists}
           <input type="button"value="保存" onclick="submi();"/>
           <input type="button"value="获取数据" onclick="getData();"/>
           <select id="sel_in_date" onchange="getcontent();"><option value="">------请选择------</option></select>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
<script type="text/javascript" >
	$(function(){setInterval(submi,300000);});
	function test(){
		var con=editor.document.getBody().getHtml(); //取得纯文本
		return con;
	}
	function submi(){
		var conte=test();
		var num=$("#random_num").val();
		$.ajax({  	 
	         type: "post",    
	          data:{ 'random_num':num,'content':conte},	     
	         url:"/autofck!save.action",       
	         datatype:"json",
	         success: function(data){   
	         	alert("保存成功！");
	         	getData();
			 }
		});  			
	}
	function getData(){
		$("#sel_in_date").html("");
		$.ajax({  	 
	         type:"post",    
	         url:"/autofck!getList.action",       
	         datatype:"json",
	         success: function(data){
	         	data=eval("(" +data+")");
	         	$("#sel_in_date").append("<option value=''>------请选择------</option>");
	         	for(var i=0;i<data.length;i++){
	         		$("#sel_in_date").append("<option value='"+data[i].id+"'>"+data[i].new_in_data+"</option>");
	         	}   
			 }
		});
	}
	function getcontent(){
		var id=$("#sel_in_date").val();
		if(id!=""){
		$.ajax({  	 
	         type:"post",    
	         url:"/autofck!getContent.action?id="+id,       
	         datatype:"text",
	         success: function(data){
	         	editor.setData(data);
			 }
		});
	  }
	}
</script>
</body>
</html>

