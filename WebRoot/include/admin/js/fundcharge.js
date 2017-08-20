function export_fundcharge(){
	var remark_action = $("#indexForm").attr("action");
	$("#indexForm").attr("action","/admin_Fundrecharge_listExport.action");
	$("#indexForm").submit();
	$("#indexForm").attr("action",remark_action);
	
}