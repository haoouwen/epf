<script type="text/javascript"  src="/include/common/js/jquery.timers.js"></script>

<div class="autofck">
   <span>内容正文每5分钟自动保存一次。</span>&nbsp;
   <span id="lastsave"></span>
   <@s.hidden id="random_num" name="random_num"/> 
   <input type="button"value="保存编辑" onclick="submi();"/>
   <select id="sel_in_date" onchange="getcontent();"><option value="">------请选择------</option></select>
</div>
<div id="appdetail_conten_id" style="display:none;"></div>
<div class="clear"></div>

<script type="text/javascript" >

	//定时器，5分钟自动保存数据
	$(function(){
		setInterval(submi,300000);
		getData();
	});

	//提交数据
	function submi(){
		var conte=editor.document.getBody().getHtml();
		var num=$("#random_num").val();
		if(conte=="<br>" || conte==""){
			return false;
		}else{
			$.ajax({  	 
		         type: "post",    
		         data:{ 'random_num':num,'content':conte},	     
		         url:"/autofck!save.action",       
		         datatype:"json",
		         success: function(data){   
		         	getData();
				 }
			});
	  }  			
	}
	
	//获取数据
	function getData(){
		$("#sel_in_date").html("");
		$.ajax({  	 
	         type:"post",    
	         url:"/autofck!getList.action?random_num="+$("#random_num").val(),       
	         datatype:"json",
	         success: function(data){
	         	data=eval("(" +data+")");
	         	$("#sel_in_date").append("<option value=''>------请选择------</option>");
	         	for(var i=0;i<data.length;i++){
	         		$("#sel_in_date").append("<option value='"+data[i].id+"'>"+data[i].new_in_data+"</option>");
	         		if(i == 0){
	         			$("#lastsave").html("自动保存于:"+data[i].new_in_data);
	         		}
	         	}   
			 }
		});
	}
	
	//根据id获取对应的数据
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


