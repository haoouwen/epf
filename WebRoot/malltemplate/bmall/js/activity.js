function submit_url(url){
	var t = $("#time_run").val();
	if(t=="ok"){
		window.open(url);
	}else{
		alert("活动暂未开始，请耐心等待！");
	}
}