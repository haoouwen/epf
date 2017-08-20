$(document).ready(function(){
   $('.classcont').mousemove(function(){
		$(this).find('#classlistId').show();
		$(this).find('h3,p').addClass('lclaconthover');
	});
	$('.classcont').mouseleave(function(){
		$(this).find('#classlistId').hide();
		$(this).find('h3,p').removeClass('lclaconthover');
	});
	$(".lclacont:odd").addClass("addclabg");
	$("#classMainId").hide();
	$("#h2classId").hover(
		function(){
			$("#classMainId").show();
			$(this).removeClass("h2down");
			$(this).addClass("h2up");
			$("#classMainId").hover(
			  function(){
				  $("#classMainId").show();
				  $("#h2classId").removeClass("h2down");
			      $("#h2classId").addClass("h2up");
			  },
			  function(){
				  $("#classMainId").hide();
				  $("#h2classId").removeClass("h2up");
				  $("#h2classId").addClass("h2down");
			  }
			);
		},
		function(){
			$("#classMainId").hide();
			$(this).removeClass("h2up");
			$(this).addClass("h2down");
		}
	);
});