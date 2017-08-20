//创建百度地图
function createSitemap(num){
	$.ajax({
		type:"post",
		url:"/admin_Sitemap_createSitemap.action?flag="+num,
		datatype:"json",
        async:false,
		success: function(data){
			if(data=="1"){
				alert("生成商品sitemap");
			}else{
				alert("生成分类sitemap");
			}
		}
	});
}