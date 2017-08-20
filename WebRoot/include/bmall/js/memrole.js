 var setting = {
		check: {
			enable: true
			
		},data: {
			key: {
				url: "xUrl",
				name:"menu_name",
				title:"menu_name"
			},simpleData: {
				enable: true,
				idKey: "menu_id",
				pIdKey: "up_menu_id"
			}
		}
	};

	
	
	$(function() { 
	$("input:checkbox").click(function() { 
		var cb_id = $(this).val();
		if(this.checked==true){
			$("input:checkbox[id*='"+cb_id+"']").attr("checked",true);
		}else{
			$("input:checkbox[id*='"+cb_id+"']").attr("checked",false);
		}
	}) 
	//回选菜单权限
	var menu_right = $("#menu_right").val();
	if(menu_right!=""){
		$(".menu_right").each(function(){
			 if(menu_right.indexOf($(this).val())>-1){
			 	 
			 	 this.checked=true;
			 }
		});
	}	
	//回选操作权限
	var oper_right = $("#oper_right").val();
	if(oper_right!=""){
		$(".oper_right").each(function(){
			 if(oper_right.indexOf($(this).val())>-1){
			 	 this.checked=true;
			 }
		});
	}	
}) 

	