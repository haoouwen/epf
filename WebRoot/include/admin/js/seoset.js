//加载选项值
var showHtml="";

//统一的SEO设置
function set_unified(id,obj){
	var unified_code="${webname},${webtitle},${webkeyword},${webdescription},${separator}";
	var unified_name="网站名称,SEO标题,SEO关键字,SEO描述,分隔符";
	setVal(id,obj,unified_code,unified_name);
}

//商城首页
function set_index(id,obj){
	var unified_code="${webname},${webtitle},${webkeyword},${webdescription},${separator}";
	var unified_name="网站名称,SEO标题,SEO关键字,SEO描述,分隔符";
	setVal(id,obj,unified_code,unified_name);
}

//商品列表页
function set_goodsList(id,obj){
	var unified_code="${search_wd},${webname},${goods_cat},${separator}";
	var unified_name="搜索关键字,网站名称,商品分类,分隔符";
	setVal(id,obj,unified_code,unified_name);
}


//商品的SEO设置
function set_goods(id,obj){
	var unified_code="${webname},${goods_name},${goods_cat},${goods_no},${brand},${goods_seo_title},${goods_seo_keyword},${goods_seo_desc},${goods_wd},${shopname},${separator}";
	var unified_name="网站名称,商品名称,商品分类,商品编号,商品品牌,商品SEO标题,商品SEO关键字,商品SEO描述,商品关键字,店铺名称,分隔符";
	setVal(id,obj,unified_code,unified_name);
}

//品牌的SEO设置
function set_brand(id,obj){
	var unified_code="${webname},${brand_cat},${brand_name},${separator}";
	var unified_name="网站名称,品牌分类,品牌名称,分隔符";
	setVal(id,obj,unified_code,unified_name);
}

//文章列表的SEO设置
function set_articleList(id,obj){
	var unified_code="${webname},${article_cat},${separator}";
	var unified_name="网站名称,文章分类,分隔符";
	setVal(id,obj,unified_code,unified_name);
}

//文章的SEO设置
function set_article(id,obj){
	var unified_code="${webname},${article_name},${article_cat},${article_seo_title},${article_seo_keyword},${article_seo_desc},${separator}";
	var unified_name="网站名称,文章标题,文章分类,文章SEO标题,文章SEO关键字,文章SEO描述,分隔符";
	setVal(id,obj,unified_code,unified_name);
}


//公共设置值的方法
function setVal(id,obj,code,name){
	$(".optionDiv").remove();//移除
	if($(obj).parent("td").find(".optionDiv").length==0){
		createDiv(id,code,name);
	}
	//append到文本框后中
	$(obj).parent("td").append(showHtml);
}

//设置SEO值
function addval(id,tagName){
	$("#"+id).insertContent(tagName); 
}


//创建选择框DIV
function createDiv(id,code,name){
	showHtml="<div class='optionDiv'>";
	var code_s = code.split(",");
	var name_s = name.split(",");
	for(var i=0;i<code_s.length;i++){
		showHtml+="<span class='option' onclick=addval('"+id+"','"+code_s[i]+"');>"+name_s[i]+"</span>";
	}
	showHtml+="<div class='clear'></div>";
	showHtml+="<div class='backoption'><span  class='operoption'  onclick=seoclear('"+id+"')>清空</span></div>";
	showHtml+="</div>";
}
	              			
//清空
function seoclear(id){
	$("#"+id).val("");
}	              			
	          
	          
	          
	        