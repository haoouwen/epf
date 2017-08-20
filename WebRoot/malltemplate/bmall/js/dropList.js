$.fn.extend({     
   dropList:function(){
	var listobj=this;
    var objs =$('dt',this);
	var view =0;//显示最后一个objs.length-1
	objs.each(
		function(i){
		$(this).mouseover(function(){$('dd',listobj).hide();$(this).next().show()})
		if(i!=view)
		{
			$(this).next().hide();
		}
		else
		{
			$(this).next().show();
		}
	});
   }
}); 