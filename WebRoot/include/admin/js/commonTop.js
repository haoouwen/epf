$(document).ready(function(){
	/*页面table*/
	$("#oftabId .tddiv:odd").addClass("addback");
	$("#oftabId .tddiv").click(function(){
	   	if($(this).hasClass("selback")){
			$(this).removeClass("selback");
			$(this).siblings(".tddivCont").hide();	
		}else{
			$(this).addClass("selback");
			$(this).siblings(".tddivCont").show();
			$(this).parent("td").parent("tr").siblings().find(".selback").removeClass("selback");
			$(this).parent("td").parent("tr").siblings().find(".tddivCont").hide();
		}
	});
});