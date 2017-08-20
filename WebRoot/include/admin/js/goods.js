var valStr="";
var valnameStr="";
var valImgStr="";
var linelength=1;
var market_val="";
var sale_val="";
var cost_val="";
var goods_no="";
var stock_val="";
var volume_val="";
var logsweight_val="";
var is_up_goods="";
//定义一个常量存放一个货品的html代码
var gsizeHtml="<table class='wwtable' cellpadding='8' cellspacing='1'><tbody><tr><td class='table_name'>原价<font color='red'>*</font</td>"+
"<td><input name='market_price_str' maxlength='11' value='' id='market_val' class='input' onkeyup='checkRMB(this);' type='text'><font>格式0.00</font></td></tr>"+
"<tr><td class='table_name'>售价<font color='red'>*</font></td>"+
"<td><input name='sale_price_str' maxlength='11' value='' id='sale_val' class='input' onkeyup='checkRMB(this);' type='text'></td></tr>"+
"<tr><td class='table_name'>成本价:</td><td><input name='cost_price_str' maxlength='11' value='' id='cost_val' class='input' onkeyup='checkRMB(this);' type='text'><font>格式0.00</font>;<span>前台不会显示，仅供后台使用</span></td></tr>"+
"<tr><td class='table_name'>货号:</td><td><input name='goods_item_str' maxlength='20' value='' id='weight_val' class='input' onkeyup='checkLength(this,20);' type='text'>(若不填则与商品编号一致)</td></tr>"+
"<tr><td class='table_name'>库存:</td><td><input name='stock_str' maxlength='11' value='0' id='stock_val' class='input' onkeyup='checkDigital(this);' type='text'><input name='specstr_str' value='' id='goodspost_specstr_str' type='hidden'>"+
"<tr><td class='table_name'>物流体积<font color='red'>*</font></td>"+
"<td><input name='volume_str' maxlength='11' value='' id='volume_val' class='input' onkeyup='checkFloat(this);' type='text'></td></tr>"+
"<tr><td class='table_name'>物流重量<font color='red'>*</font></td>"+
"<td><input name='logsweight_str' maxlength='11' value='' id='logsweight_val' class='input' onkeyup='checkFloat(this);' type='text'></td></tr>"+
"<input name='up_goods_str' value='0' id='goodspost_up_goods_str' type='hidden'></td></tr>"+										             	
"<tr><td class='table_name'>规格:</td><td><input id='showsize' value='开启规格' type='button'><font>开启规格前先填写以上价格等信息，可自动复制信息到货品</font></td></tr>"+
"</td></tr></tbody></table>";
//定义一个全局显示ID
var specshowid="";
var mark="##########";
$(document).ready(function(){
	isLimit();
	 //isKjt();
	//渲染进度条
	$("#loaderbar").jqm(); 
	 //$('#searchgoods,#searchsmode').jqm({overlay:30});
	 jQuery("#searchgoods,#searchsmode").jqm({
        modal: true,
        overlay: 0,
        onShow: function(h) {
            h.w.fadeIn(500);
        },
        onHide: function(h) {
            h.o.remove();
            h.w.fadeOut(500)
        }
    });
	$("#searchgoods,#searchsmode").jqm({
		overlay:30
	}); 
	//tab切换页
	$("#oper_div").mallTab({
		displayIndex:'0'
	});
	$("#poper_div").mallTabp({
		displayIndex:'0'
	});
	
	showmoresize();
	//提交表单
	$("#goodspost").submit(function(){
		if(!commonsave()){
			return false;
		}else{
			return true;
		}
	});
	sel_is_up_goods();
})
//上下架的选择改变值
function sel_is_up_goods(){
	$(".is_up_goods").each(function(){
		$(this).click(function(){
			if(this.checked){//相反
				$(this).parent("td").find("input:hidden[name='up_goods_str']").val(0);
			}else{
				$(this).parent("td").find("input:hidden[name='up_goods_str']").val(1);
			}
		});
	});
}


//点击开启规格
function showmoresize(){
	$("#showsize").click(function(){
		showgoodssizelist();
	});
}

//弹出规格层
function showgoodssizelist(){
	$("#sizelist").popup();
	$("#divPanel").easydrag();        
	$("#divPanel").setHandler("divTitle");  
	//选项卡
	$("#alreadysize").mallTab();
	$(".sv_code").each(function(){
		if(this.checked){
			var sv_id = this.value;
			$("#uploadoneimgbutton"+sv_id+"Uploader").remove();
			var loadscript = "<script>uploadSelfSpecImg('"+sv_id+"','uploadoneimgbutton"+sv_id+"','uploadoneslefimg"+sv_id+"','fileQueue');</script>";
			$("#uploadself"+sv_id).html(loadscript);
		}
	});
}

//选择规格项数据
function selsize(){
	//清除提示框
	$("#showsizevalmsg").remove();
	//清除原规格
	$("#specnamediv").html("");
	var li_count=0;
	$(".selsizelist_ul").find("input:checkbox").each(function(){
		 if(this.checked){
		 	var selspecname=$(this).parent("li").find("span").html();
		 	var show_type=$(this).parent("li").find(".show_type").val();
		 	loadspecvalue(this.value,show_type,selspecname);
		 	li_count++;
		 }
	});
	if(li_count>0){
		//规格值点击时判断规格ID是否被选中
		sv_code();
		//如果已经有值清空
		if($("#spectabbar").length>0){
			$("#alreadysize").html("<div id='showsizeval' class='showsizevalmsg'>暂未选择规格值</div>");
		}
	}else{
		jNotify("请先选择需要规格!"); 
		if($("#sizetable").length<=0){
			$("#specnamediv").html("<div id='showsizevalmsg' class='showsizevalmsg'>请先选择添加本商品需要的规格</div>");
		}
	}
}


//加载规格值数据
function loadspecvalue(selId,seltype,selName){
	var specTable="";
	if($("#sizetable").length<=0){
		specTable+="<table id='sizetable' border='1'>";
	}
	var trHtml="<tr id='seltr"+selId+"'>";
	if(selName.length>7){
		selName=selName.substring(0,7)+".."
	}
    trHtml+="<td class='size_td1' valign='middle'><input id='"+selId+"' type='hidden' class='selspecval' value='"+selId+"'/><span class='specspan'>"+selName+"</span></td>";
    trHtml+="<td class='size_td2' valign='middle'>";
    //获取规格值数据
    var svData = loadSpecValue(selId);
    svData=eval("(" +svData+")");
    for(var i=0;i<svData.length;i++){
    	trHtml+="<span style='display:-moz-inline-box; display:inline-block; width:250px;'>";
    	trHtml+="<input type='hidden' class='td_sort_no' value='"+svData[i].sort_no+"'/>";
    	trHtml+="<input type='hidden' class='seltype' value='"+seltype+"'/>";
    	trHtml+="<input id='td_svc"+selId+svData[i].sv_code+"' class='sv_code' type='checkbox' value='"+svData[i].sv_code+"'/>";
    	if(seltype=='1'){
	    	trHtml+="<span><img  class='specimg' src='"+svData[i].sv_img_path+"' alt='"+svData[i].sv_name+"'/></span>";
	    	trHtml+="<input type='hidden' id='showselfImg"+svData[i].sv_code+"' class='hidden_val' value='"+svData[i].sv_img_path+"'>";
	    }else{
	    	trHtml+="<span>"+svData[i].sv_name+"</span>";
	    	trHtml+="<input type='hidden' class='hidden_val' value='"+svData[i].sv_name+"'>";
	    }
	    trHtml+="<input type='hidden' id='ssval"+svData[i].sv_code+"' class='hidden_name' value='"+svData[i].sv_name+"'>";
	    trHtml+="</span>";
    }
    trHtml+="</td></tr>";
    specTable+=trHtml;
    if($("#sizetable").length<=0){
		specTable+="</table>";
	}
	if($("#sizetable").length>0){
		$("#sizetable").append(trHtml);
	}else{
		$("#specnamediv").append(specTable);
	}
}

//查规格值数据						             	 			
function  loadSpecValue(pId){
	var resultData="";
	$.ajax({
          type: "post",
          url: "/specvalue!childList.action?pId="+pId,
          datatype:"json",
          async:false,
          success: function(data){ 
          	resultData=data;
          }	 
	});   
	return resultData;
}						             	 			
			
						   
//通过选中规格值获取自定义规格值属性						   
function selfsizelist($thissv,sv_count){
	var spec_id = $thissv.closest("tr").find(".selspecval").attr("id");
	var spec_name = $thissv.closest("tr").find(".selspecval").parent("td").children("span").html();
	var selfsizeHtml="";
	//规格名称
	if($("#spectabbar").length <= 0){
		selfsizeHtml+="<div id='spectabbar' class='tabbar'>";
		selfsizeHtml+="<ul>";
		selfsizeHtml+="<li id='self_li_"+spec_id+"' class='selected'><font id='sfont_"+spec_id+"'>"+spec_name+"</font>(<span  class='li_span_count'  id='li_span_"+spec_id+"'>1</span>)<input type='hidden' class='sfontid_val' value='"+spec_id+"'/></li>";
		selfsizeHtml+="</ul>";
		selfsizeHtml+="</div>";
		selfsizeHtml+="<div class='clear'></div>"
	}
	//规格值列表
	if($("#self_tabdiv_"+spec_id).length<=0){
		//其它的tabdiv隐藏
		$("#alreadysize").find(".tabdiv").css("display","none");
		selfsizeHtml+="<div id='self_tabdiv_"+spec_id+"' class='tabdiv' style='display:block;'>";
		selfsizeHtml+="<table  cellspacing='1' cellpadding='5'>";
		selfsizeHtml+="<tr>";
		selfsizeHtml+="<th width='15%'>系统规格</th>";
		selfsizeHtml+="<th width='20%'>自定义规格值</th>";
		selfsizeHtml+="<th width='20%'>自定义规格图片</th>";
		selfsizeHtml+="<th width='30%'>关联商品相册图片</th>";
		selfsizeHtml+="<th width='15%'>操作</th>";
		selfsizeHtml+="</tr>";
	}
	var sv_id = $thissv.val();
	var sv_name = $thissv.parent("span").children(".hidden_name").val();
	var sv_img = $thissv.parent("span").children(".hidden_val").val();
	var sv_type = $thissv.parent("span").children(".seltype").val();
	var sv_sort = $thissv.parent("span").children(".td_sort_no").val();
	//是否存在这个规格值
	if($("#svtr_"+sv_id).length<=0){
		selfsizeHtml+="<tr id='svtr_"+sv_id+"'>";
		if(sv_type=='0'){
			selfsizeHtml+="<td align='center'><span class='selfspecspan'>"+sv_name+"</span></td>";
		}else{
			selfsizeHtml+="<td align='center'><img class='selfspecimg'  src='"+sv_img+"'/></td>";
		}
		selfsizeHtml+="<td align='center'><input id='getSelfSizeValue"+sv_id+"' name='selfsepcvalue.self_spec_value' type='text' value='"+sv_name+"'/></td>";
		if(sv_type=='1'){
			var attrStr="<td align='center'><span><img id='showselfUpImg"+sv_id+"'  class='selfspecimg'  src='"+sv_img+"'/></span>"+
			"<input type='file' id='uploadoneimgbutton"+sv_id+"'/>"+
			"<input type='hidden' value='"+sv_img+"' id='uploadoneslefimg"+sv_id+"'  name='selfsepcvalue.self_spec_img'/>"+
			"<span id='uploadself"+sv_id+"'><script>uploadSelfSpecImg('"+sv_id+"','uploadoneimgbutton"+sv_id+"','uploadoneslefimg"+sv_id+"','fileQueue');</script></span>"+"</td>";
			selfsizeHtml+=attrStr;
		}else{
			selfsizeHtml+="<td align='center'>-<input type='hidden' value='' name='selfsepcvalue.self_spec_img'/></td>";
		}
		selfsizeHtml+="<td align='center'><span></span><input type='hidden'   name='selfsepcvalue.self_img'  class='specimg' id='specimg"+sv_id+"'><input type='button' onclick='selupimg(this);' value='选择'/></td>";
		selfsizeHtml+="<td align='center'><input type='text' name='selfsepcvalue.sort_no' class='sepctxt' value='"+sv_sort+"'><input type='hidden' class='size_sv_id' value='"+sv_id+"'/></td>";
		selfsizeHtml+="</tr>";
	}
	
	if($("#self_tabdiv_"+spec_id).length<=0){
		selfsizeHtml+="</table>";
	}

	//判断是新添还是附加
	if($("#self_tabdiv_"+spec_id).length<=0){
		$("#alreadysize").append(selfsizeHtml);
	}else{
		$("#self_tabdiv_"+spec_id).find("table").append(selfsizeHtml);
	}
	//其它的tabbar隐藏
	$("#alreadysize").find(".tabbar").find("li").removeClass("selected");
 	if($("#self_li_"+spec_id).length<=0){
 		$("#spectabbar").children("ul").append("<li id='self_li_"+spec_id+"' class='selected' ><font id='sfont_"+spec_id+"'>"+spec_name+"</font>(<span class='li_span_count' id='li_span_"+spec_id+"'>1</span>)<input type='hidden'  class='sfontid_val' value='"+spec_id+"'/></li>");
 	}else{	
 		$("#self_li_"+spec_id).addClass("selected");
 		$("#alreadysize").find(".tabdiv").css("display","none");
 		$("#self_tabdiv_"+spec_id).css("display","block");
 	}
 	$("#li_span_"+spec_id).html(sv_count);
	//选项卡
	$("#alreadysize").mallTab();
}				   
     
            	 			
//规格值点击时判断规格ID是否被选中
function sv_code(){
	$(".sv_code").click(function(){
	    var sv_count=0;
	    var $thissv=$(this);
	     var spec_id = $thissv.closest("tr").find(".selspecval").attr("id");
		var spec_name = $thissv.closest("tr").find(".selspecval").parent("td").children("span").html();
		$(this).closest("tr").find(".sv_code").each(function(){
			 if(this.checked){
			 	sv_count++;
			 }
		});
		//规格个数
		if(sv_count>0){
			if(this.checked==true){
				//清除规格属性
				if($("#showsizeval").length>0){
					$("#alreadysize").html("");
				}
				selfsizelist($thissv,sv_count);
			}else{
				var svc_id=this.value;
				$("#svtr_"+svc_id).remove();
				// 清除样式
				$("#alreadysize").find(".tabbar").find("li").removeClass("selected");
				$("#alreadysize").find(".tabdiv").css("display","none");
				// 选定样式
				$("#self_li_"+spec_id).addClass("selected");
	 			$("#self_tabdiv_"+spec_id).css("display","block");
	 			//个数减一
	 			var un_count = $("#li_span_"+spec_id).html();
	 			$("#li_span_"+spec_id).html(un_count-1);
			}		
		}else{
			//索引
			var index = $("#self_li_"+spec_id).index();
			if(index>0){
				index=index-1;
			}else{
				index=0;
			}
			//tabli
			$("#self_li_"+spec_id).remove();
			//tabdiv
	 		$("#self_tabdiv_"+spec_id).remove();
	 		// 清除样式
			$("#alreadysize").find(".tabbar").find("li").removeClass("selected");
			$("#alreadysize").find(".tabdiv").css("display","none");
	 		//清除后显示第一个
	 		$("#spectabbar").find("li").eq(index).addClass("selected");
	 		$("#alreadysize").find(".tabdiv").eq(index).css("display","block");
	 		//选项卡
			$("#alreadysize").mallTab();
			//找出已选规格名称的个数
			var li_length = $("#spectabbar").find("li[id^=self_li_]").length;
			if(li_length==0){
				$("#alreadysize").html("<div id='showsizeval' class='showsizevalmsg'> 暂未选择规格值 </div>");
			}
		}
	});
}								             	 			


//打开选择图片框
function selupimg(obj){
	specshowid=$(obj).parent("td").find(".specimg").attr("id");
	$("#showulimg").html("");//清空   
    $("input:hidden[name='gimg']").each(function(){
   		var imgli="";
   		var specimg=$("#"+specshowid).val();
   		if(specimg.indexOf(this.value)>-1){
   			imgli="<li><input type='checkbox' checked='true'><img class='sulimig' src="+this.value+"></li>"
   		}else{
   			imgli="<li><input type='checkbox'><img class='sulimig' src="+this.value+"></li>"
   		}        
    	$("#showulimg").append(imgli);
    });	
    //if($("#showulimg").html()==""){
    	//jNotify("请先上传图片!");
   // }else{
    	$("#imgbox").popup({
    		 opacity:"0.3",
		 	 p_width:"600",
		 	 p_height:"500",
		 	 pop_title:"选择图片",
		 	 parert_id:"imgDivPanel",
		 	 parert_title:"imgDivTitle",
		 	 parent_cover:"img_p_cover",
		 	 cover_index:"9001",
		 	 cover_img:"close_img",
		 	 move_left:100,
	 	 	 move_top:-60
    	});
   // }
}						             	 			
						             	 			
//选定图片框中的图片
function ralateImg(){
    var imgsrcStr="";
    var countimg=0;
    //清空
    $("#"+specshowid).parent("td").find("span").html("");
    $("#"+specshowid).val("");
	$("#showulimg").find("input:checkbox").each(function(){
		if(this.checked){
			var imgsrc = $(this).parent("li").find("img").attr("src");
			imgsrcStr+=imgsrc+"|";
			countimg++;
			if(countimg<=3){
			    var ongimg="<img class='selfspecimg' src="+imgsrc+">";
				$("#"+specshowid).parent("td").find("span").append(ongimg);				
			}
		}		
	});
	if(countimg>10){
		jNotify("最多只能选择10张相关图片!");	
		return;
	}
	if(imgsrcStr.indexOf("|")>-1){
		imgsrcStr=imgsrcStr.substring(0,(imgsrcStr.length-1));
	}		
	$("#"+specshowid).val(imgsrcStr);
	if(countimg>3){
		$("#"+specshowid).parent("td").find("span").append("...");				
	}
	specshowid="";
	$("#imgDivPanel").hide();
	$("#img_p_cover").remove();
}						             	 			
						             	 			
//生成货品头部和货品排列数
function selfmoresize(){	
	var li_length = $("#spectabbar").find("li[id^=self_li_]").length;
	if(li_length==0){
		jNotify("请选择规格!");	
		return;	
	}
	if(li_length>5){
		jNotify("最多只能选择五种规格!");	
		return;	
	}
	jConfirm('确定要生成所有货品，重新生成会丢失当前的货品数据,确定生成?', '友情提醒!', function(r) {
	   if(r){
		    var selfTable="";
			    selfTable+="<table  class='op_zi_table' cellspacing='1' cellpadding='5' width='100%'>";
				selfTable+=selfsize_th();
				selfTable+=selfsize_td();
				selfTable+="</table>";
			  	$("#selfsize").html(selfTable);
			  	$("#divPanel").hide();
				$("#p_cover").remove();
			  	//关闭单个规格	  	
			  	$("#op_div").html(""); 
			  	//显示规格组合
			    $("#op_sizeattr").css("display","block");
				//重新渲染tab页
				$(".indexTab").mallTable({
					even_color:"#FFFFFF",//偶数行颜色
				  	odd_color:"#F8F8F8", //奇数行颜色 	   
				  	float_color:"#FFFFCC" //鼠标移动tr上颜色
				});	
				//赋值多属性选择
				$("#is_more_attr").val("0");
				//赋值给规格名称
				var fv_str = "";
				var fv_count="";
				$(".sfontid_val").each(function(i){
					fv_str+=$(this).val()+",";
					fv_count+=$(".li_span_count").eq(i).html()+",";
				});
				$("#sel_spec_name").val(fv_str);
				$("#sel_spec_count").val(fv_count);
				//规格值表
				var self_spec_value_str="";
				var self_spec_self_spec_img_str="";
				var self_spec_self_img_str="";
				var self_spec_sort_no_str="";
				var self_size_id = "";
				$("input:text[name='selfsepcvalue.self_spec_value']").each(function(i){
					  if($(this).val()!=""){
					  	self_spec_value_str+=$(this).val()+",";
					  }else{
					  	self_spec_value_str+="0,";
					  }
					  
					  if($("input:hidden[name='selfsepcvalue.self_spec_img']").eq(i).val()!=""){
					  	self_spec_self_spec_img_str+=$("input:hidden[name='selfsepcvalue.self_spec_img']").eq(i).val()+",";
					  }else{
					  	self_spec_self_spec_img_str+="0,";
					  }
					  
					  if($("input:hidden[name='selfsepcvalue.self_img']").eq(i).val()!=""){
					  	self_spec_self_img_str+=$("input:hidden[name='selfsepcvalue.self_img']").eq(i).val()+",";
					  }else{
					  	self_spec_self_img_str+="0,";
					  }
			
					  if($("input:text[name='selfsepcvalue.sort_no']").eq(i).val()!=""){
					  	self_spec_sort_no_str+=$("input:text[name='selfsepcvalue.sort_no']").eq(i).val()+",";
					  }else{
					  	self_spec_sort_no_str+="0,";
					  }
					  
					  if($(".size_sv_id").eq(i).val()!=""){
					  	self_size_id+=$(".size_sv_id").eq(i).val()+",";
					  }
				});
				
				self_spec_value_str=deleteLastChar(self_spec_value_str,',');
				$("#self_goods_size_value").val(self_spec_value_str);
				self_spec_self_spec_img_str=deleteLastChar(self_spec_self_spec_img_str,',');
				$("#self_goods_img_value").val(self_spec_self_spec_img_str);
				self_spec_self_img_str=deleteLastChar(self_spec_self_img_str,',');
				$("#self_goods_relate_img_value").val(self_spec_self_img_str);
				self_spec_sort_no_str=deleteLastChar(self_spec_sort_no_str,',');
				$("#self_goods_sort_value").val(self_spec_sort_no_str);
				self_size_id=deleteLastChar(self_size_id,',');
				$("#self_size_id").val(self_size_id);
				
				//上下架管理渲染
				sel_is_up_goods();
				
				//标记删除之前的规格数据
				$("#is_must_delete_spec").val("1");
		 }
	 });
}


//获取规格的名称,值,图片
function getsizeName(){
    var sizeNameArray=new Array()
    valStr="";
	valnameStr="";
	valImgStr="";
    linelength=1;
	$(".sfontid_val").each(function(){
		 var cb_count=0;
		 var spec_id=$(this).val();
		 $("#seltr"+spec_id).find(".sv_code").each(function(){
		 	  if(this.checked==true){
		 	  	  cb_count++;
		 	  	  //组合值
		 	  	  valStr+=this.value+",";
		 	  	  //组合名称
		 	  	  valnameStr+=$("#getSelfSizeValue"+this.value).val()+",";
		 	  	  //组合图片
		 	  	  if($("#showselfUpImg"+this.value).length>0){
		 	  	  	valImgStr+=$("#showselfUpImg"+this.value).attr("src")+",";
		 	  	  }else{
		 	  	  	valImgStr+="no"+",";
		 	  	  }	 	  	      	
		 	  }		 	  
		 }); 
		 if(cb_count>0){
		 	valStr+="|"; 	
		 	valnameStr+="|";
		 	valImgStr+="|";
		 	linelength=linelength*cb_count;
 	        sizeNameArray.push($("#sfont_"+spec_id).html());//获取规格名称
 	        return;
 	     }    
  	});
  	return sizeNameArray;
}



//排列标题名称
function selfsize_th(){
    var sizeNameArray=getsizeName();
	var thStr="";
	thStr="<tr>";
	thStr+="<th>货号</th>";		
	for(var i=0;i<sizeNameArray.length;i++){
		thStr+="<th>"+sizeNameArray[i]+"</th>";	
	}  
	thStr+="<th>库存</th><th>原价<font color='red'>*</font></th><th>售价<font color='red'>*</font></th><th>成本价</th><th>物流体积<font color='red'>*</font></th><th>物流重量<font color='red'>*</font></th><th>上架</th><th>操作</th>";	
	thStr+="</tr>";
	return thStr;
}

//规格值排列组合
function selfsize_td(){
	//自定义五个数组
	var ss1=new Array();
	var ss2=new Array();
	var ss3=new Array();
	var ss4=new Array();
	var ss5=new Array();
	
	var name1=new Array();
	var name2=new Array();
	var name3=new Array();
	var name4=new Array();
	var name5=new Array();
	
	var img1=new Array();
	var img2=new Array();
	var img3=new Array();
	var img4=new Array();
	var img5=new Array();

   var val=valStr.split("|");
   var nameval=valnameStr.split("|");
   var imgval=valImgStr.split("|");

   for(var z=0;z<val.length-1;z++){ 
	  	if(z==0){
	  		ss1=val[z].split(",");
	  		name1=nameval[z].split(",");
	  		img1=imgval[z].split(",");
	  	}
	  	
	  	if(z==1){
	  		ss2=val[z].split(",");
	  		name2=nameval[z].split(",");
	  		img2=imgval[z].split(",");
	  	}
	  	if(z==2){
	  		ss3=val[z].split(",");
	  		name3=nameval[z].split(",");
	  		img3=imgval[z].split(",");
	  	}
	  	
	  	if(z==3){
	  		ss4=val[z].split(",");
	  		name4=nameval[z].split(",");
	  		img4=imgval[z].split(",");
	  	}
	  	
	  	if(z==4){
	  		ss5=val[z].split(",");
	  		name5=nameval[z].split(",");
	  		img5=imgval[z].split(",");
	  	}
 	}

 	var tdStr="";
    var valength=val.length-1;
    //获取基本值
    getbaseVal();
 	for(var k=0;k<linelength;k++){ 
	 	tdStr+="<tr id='linetr"+k+"'><td align='center'><input name='goods_item_str' style='width:100px;' type='text' value='"+goods_no+"_"+k+"'/></td>";
	 	
	 	var idvals="";
	 	
	 	var ssl1=ss1.length-1;
	 	var ssl2=ss2.length-1;
	 	var ssl3=ss3.length-1;
	 	var ssl4=ss4.length-1;
	 	var ssl5=ss5.length-1;
	 	
	 	ks1=k%ssl1;
	 	ks2=parseInt((k%(ssl1*ssl2))/ssl1);
	 	ks3=parseInt((k%(ssl1*ssl2*ssl3))/(ssl1*ssl2));
	 	ks4=parseInt((k%(ssl1*ssl2*ssl3*ssl4))/(ssl1*ssl2*ssl3));
	 	ks5=parseInt((k%(ssl1*ssl2*ssl3*ssl4*ssl5))/(ssl1*ssl2*ssl3*ssl4));
	 	
	 	if(valength>=1){
	 		ss1=val[0].split(","); 
 			tdStr+="<td align='center'>";
 			if(img1[ks1]!='no'){
 				tdStr+="<img class='setsizeimg' src='"+img1[ks1]+"' alt='"+name1[ks1]+"' title='"+name1[ks1]+"'/>";
 			}else{
 				tdStr+=name1[ks1];
 			}
 			tdStr+="</td>";
 			idvals+=ss1[ks1]+":";
	 	}
		if(valength>=2){
		  	ss2=val[1].split(",");  			
	 		tdStr+="<td align='center'>";
 			if(img2[ks2]!=='no'){
 				tdStr+="<img class='setsizeimg' src='"+img2[ks2]+"' alt='"+name2[ks2]+"' title='"+name2[ks2]+"'/>";
 			}else{
 				tdStr+=name2[ks2];
 			}
 			tdStr+="</td>";//用于两个规格值时
	 		idvals+=ss2[ks2]+":";
	  	}
	  	if(valength>=3){
		  	ss3=val[2].split(",");  	
	 		tdStr+="<td align='center'>";
 			if(img3[ks3]!='no'){
 				tdStr+="<img class='setsizeimg'  src='"+img3[ks3]+"'  alt='"+name3[ks3]+"' title='"+name3[ks3]+"'/>";
 			}else{
 				tdStr+=name3[ks3];
 			}
 			tdStr+="</td>";//用于三个规格值时	
			idvals+=ss3[ks3]+";";  	
	  	}
	  	if(valength>=4){
		  	ss4=val[3].split(",");  	
	 		tdStr+="<td align='center'>";
 			if(img4[ks4]!='no'){
 				tdStr+="<img class='setsizeimg'  src='"+img4[ks4]+"'  alt='"+name4[ks4]+"' title='"+name4[ks4]+"'/>";
 			}else{
 				tdStr+=name4[ks4];
 			}
 			tdStr+="</td>";//用于四个规格值时	
			idvals+=ss4[ks4]+":";  	
	  	}
	  	if(valength>=5){
		  	ss5=val[4].split(",");  	
			tdStr+="<td align='center'>";
 			if(img5[ks5]!='no'){
 				tdStr+="<img  class='setsizeimg' src='"+img5[ks5]+"'  alt='"+name5[ks5]+"' title='"+name5[ks5]+"'/>";
 			}else{
 				tdStr+=name5[ks5];
 			}
 			tdStr+="</td>";//用于五个规格值时
			idvals+=ss5[ks5]+":";	  	
	  	}
	  	//去掉最后的逗号
	  	if(idvals.indexOf(":")>0){
	  		idvals=idvals.substring(0,idvals.length-1);
	  	}
	  	tdStr+="<td  align='center'><input name='specstr_str' type='hidden' value='"+idvals+"'>"+
	  	"<input  name='stock_str'  style='width:60px;' onkeyup='checkDigital(this);' type='text' value='"+stock_val+"'/></td>";
	  	tdStr+="<td  align='center'><input name='market_price_str'  style='width:60px;' type='text'   onkeyup='checkRMB(this);'  value='"+market_val+"'/></td>";
	  	tdStr+="<td  align='center'><input name='sale_price_str'  style='width:60px;' type='text'  onkeyup='checkRMB(this);'    value='"+sale_val+"'/></td>";
	  	tdStr+="<td  align='center'><input name='cost_price_str'  style='width:60px;' type='text'  onkeyup='checkRMB(this);'   value='"+cost_val+"'/></td>";
	  	tdStr+="<td  align='center'><input name='volume_str'  style='width:60px;' type='text'  onkeyup='checkFloat(this);'    value='"+volume_val+"'/></td>";
	  	tdStr+="<td  align='center'><input name='logsweight_str'  style='width:60px;' type='text'  onkeyup='checkFloat(this);'   value='"+logsweight_val+"'/></td>";
	  	if(is_up_goods=='0'){
	  		tdStr+="<td  align='center'><input class='is_up_goods'  type='checkbox' value='0' checked='true'/>";
	  		tdStr+="<input  name='up_goods_str'  type='hidden' value='0'/></td>"; 
	  	}else{
	  		tdStr+="<td  align='center'><input class='is_up_goods'  type='checkbox' value='1'/>";
	  		tdStr+="<input  name='up_goods_str'  type='hidden' value='1'/></td>"; 
	  	}
	  	tdStr+="<td  align='center'><input style='width:60px;' type='button' value='删除' onclick='dellinetr("+k+");'/></td>";
	  	tdStr+="</tr>"
 	}
 	return tdStr;
}

//删除排列组合行
function dellinetr(t_id){
	$("#linetr"+t_id).remove();
	//如多个属性都删除完，让单个属性显示
	var input=$("input[name=goods_item_str]")
	if(input.length==0){
		showOnegoods();
	}
}

//获取基本值
function getbaseVal(){
	market_val="";
	cost_val="";
	stock_val="";
	goods_no="";
	volume_val="";
	logsweight_val="";
	if($("#market_val").length>0){
		market_val=$("#market_val").val();
	}
	if($("#sale_val").length>0){
		sale_val=$("#sale_val").val();
	}
	if($("#cost_val").length>0){
		cost_val=$("#cost_val").val();
	}
	if($("#stock_val").length>0){
		stock_val=$("#stock_val").val();
	}
	if($("#volume_val").length>0){
		volume_val=$("#volume_val").val();
	}
	if($("#logsweight_val").length>0){
		logsweight_val=$("#logsweight_val").val();
	}	
	if($("#goods_no").length>0){
		goods_no=$("#goods_no").val();
	}
	if($("input:radio[goods.is_up]").length>0){
		is_up_goods=$("input:radio[goods.is_up]:checked").val();
	}
}

//显示单个规格值
function showOnegoods(){
	//隐藏多属性容器
	$("#op_sizeattr").css("display","none");
	//关闭多属性
	$("#selfsize").html("");
  	//开启单个规格	  	
  	$("#op_div").html(gsizeHtml); 
  	//渲染开启规格
  	showmoresize();
  	//赋值多属性选择
	$("#is_more_attr").val("1");
}

//公共保存方法
function commonsave(){
	if($("#goodsName").val()==""){
		jNotify("请填写商品名称!"); 
		return false;
	}
	if($("#weight").val()==""){
		jNotify("请填写商品净含量!"); 
		return false;
	}	
	if($("#goods_place").val()==""){
		jNotify("请填写商品产地!"); 
		return false;
	}
		if($("#kjt_ids").val()==""){
		jNotify("请填写跨境通商品ID!"); 
		return false;
	}		
	if($("#warehouse_number").val()==""){
		jNotify("请填写仓库编码!"); 
		return false;
	}
	if($("#tax1111111111").val()=="0"){
		jNotify("请选择税率!"); 
		return false;
	}		
	if($("#market_val").val()==""){
		jNotify("请填写商品原价格!"); 
		return false;
	}	
	if($("#sale_val").val()==""){
		jNotify("请填写商品售价格!"); 
		return false;
	}
	if($("#volume_val").val()==""){
		jNotify("请填写商品物流体积!"); 
		return false;
	}	
	if($("#logsweight_val").val()==""){
		jNotify("请填写商品物流重量!"); 
		return false;
	}
	if($("#ship_id").val()==""){
		jNotify("请选择运费模板!"); 
		return false;
	}
	var oInput=$("input[name='gimg']");
	if(oInput!=null && oInput.length>60){
		jNotify("上传图片不能超过50张!"); 
		return false;
	}
	if($(".op_zi_table").length > 0){
		var s_flag=false;
		$(".op_zi_table").find("input:text[name='sale_price_str']").each(function(){
			if($(this).val()==""){
				s_flag=true;
			}
		});
		if(s_flag){
			jNotify("请填写对应货号商品的售价格!"); 
			return  false;
		}
		var sm_flag=false;
		$(".op_zi_table").find("input:text[name='market_price_str']").each(function(){
			if($(this).val()==""){
				sm_flag=true;
			}
		});
		if(sm_flag){
			jNotify("请填写对应货号商品的原价格!"); 
			return  false;
		}
		
		var v_flag=false;
		$(".op_zi_table").find("input:text[name='volume_str']").each(function(){
			if($(this).val()==""){
				v_flag=true;
			}
		});
		if(v_flag){
			jNotify("请填写对应货号商品的物流体积!"); 
			return  false;
		}
		var l_flag=false;
		$(".op_zi_table").find("input:text[name='logsweight_str']").each(function(){
			if($(this).val()==""){
				l_flag=true;
			}
		});
		if(l_flag){
			jNotify("请填写对应货号商品的物流重量!"); 
			return  false;
		}		
		
		
	}
	//定时上下架处理开始
	var goods_up_str="";
	var goods_down_str="";
	var flagUp=false;//标记用于校验上架时间是否全部填写
	var flagDown=false;//标记用于校验下架时间是否全部填写
	var cheked =$('input[name="goods.is_up"]:checked').val();
	if(cheked=="1"){
		$(".upWdate").each(function(i){
			if($(this).val()==""||$(".downWdate").eq(i).val()==""){
				if($(this).val()==""){
					flagUp=true;
				}else{
					flagDown=true;
				}
				return;
			}
			goods_up_str+=$(this).val()+mark;
			goods_down_str+=$(".downWdate").eq(i).val()+mark;
			return;
		});
	}
	if(flagUp){
		jNotify("您有上架时间未填写!");
		return false;
	}
	if(flagDown){
		jNotify("您有下架时间未填写!");
		return false;
	}	
	goods_up_str=deleteLastChar(goods_up_str,mark);
	goods_down_str=deleteLastChar(goods_down_str,mark);
	$("#goods_up_str").val(goods_up_str);
	$("#goods_down_str").val(goods_down_str);
	//处理扩展属性串的值
	var ex_attr_value="";
	$(".ex_class").each(function(){
		ex_attr_value+=$(this).val()+mark;
	});
	ex_attr_value=deleteLastChar(ex_attr_value,mark);
	$("#ex_attr_value").val(ex_attr_value);
	//处理参数值
	var slef_para_value="";
	$("input:text[name='selfparavalue.slef_para_value']").each(function(){
		slef_para_value+=$(this).val()+mark;
	});
	slef_para_value=deleteLastChar(slef_para_value,mark);
	$("#slef_para_value").val(slef_para_value);
	return true;
}

//添加相似的商品
function savegoods(url){
	if(!commonsave()){
		return ;
	}
	//定时上下架处理结束
	$("#loaderbar").jqmShow();
	var postForm = $("#goodspost").serialize();
	$.ajax({  	 
         type:"post",  
         data:postForm,
         url:url,       
         datatype:"json",
         success: function(data){
	         $("#loaderbar").jqmHide();
	         $("#goods_no").val(data);
	         jSuccess("保存成功!"); 
		 },error: function () {
             jNotify("保存失败!");
             $("#loaderbar").jqmHide();
         }
	});
}



//扩展属性多选框点击后保存值
function attrSelCheck(obj){
	var check_attr_value="";
	$(obj).parent("td").find("input:checkbox").each(function(){
	 	if(this.checked){
			check_attr_value+=this.value+",";
		}
	});
	check_attr_value = deleteLastChar(check_attr_value,",");
	$(obj).parent("td").find("input:hidden[name='selfextendattr.ex_attr_value']").val(check_attr_value);
}



//删除上下架时间
function deluptr(id){
	$("#uptr"+id).remove();
}

//新增上下架时间
function addupdowntime(){
	var is_up=$("input:radio[name='goods.is_up']:checked").val();
	if(is_up=='1'){
		$("#updowngoods_tr").hide();
		return ;
	}else{
		$("#updowngoods_tr").show();
	}
    var upLength=$(".addWdate").length;
	var uptrHtml="<tr id='uptr"+upLength+"'><td width='60px;' align='center'><img onclick=\"deluptr('"+upLength+"');\" src='/include/common/images/delete.png'></td>";
	if(upLength==0){
		uptrHtml+="<td class='table_name'>上架时间:</td>";
		uptrHtml+="<td><input type=\"text\" class=\"Wdate addWdate upWdate  upstr\" style=\"width:142px;\" id=\"updown"+(upLength+1)+"\" onFocus=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\\'updown"+(upLength+2)+"\\',{M:0,d:0});}'})\"/></td>";
		uptrHtml+="<td class='table_name'>下架时间:</td>";		
		uptrHtml+="<td><input type=\"text\" class=\"Wdate addWdate downWdate downstr\" style=\"width:142px;\" id=\"updown"+(upLength+2)+"\" onFocus=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\\'updown"+(upLength+1)+"\\',{M:0,d:0});}',maxDate:'2020-4-3'})\"/></td>";			
	}else{
		uptrHtml+="<td class='table_name'>上架时间:</td>";
		uptrHtml+="<td><input type=\"text\" class=\"Wdate addWdate upWdate  upstr\" style=\"width:142px;\" id=\"updown"+(upLength+1)+"\" onFocus=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\\'updown"+(upLength)+"\\',{M:0,d:0});}',maxDate:'#F{$dp.$D(\\'updown"+(upLength+2)+"\\',{M:0,d:0});}'})\"/></td>";
		uptrHtml+="<td class='table_name'>下架时间:</td>";		
		uptrHtml+="<td><input type=\"text\" class=\"Wdate addWdate downWdate downstr\" style=\"width:142px;\" id=\"updown"+(upLength+2)+"\" onFocus=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\\'updown"+(upLength+1)+"\\',{M:0,d:0});}',maxDate:'2020-4-3'})\"/></td>";			
	}
	uptrHtml+="</tr>";		
	$("#updowngoods_tr").append(uptrHtml);
}
//修改商品的活动状态
function cancelActiveByAjax(){
	var goods_id=$("#flag_goods_id").val();
	$.ajax({  	 
            type: "post",    	     
            url:"/admin_Goods_updateActiveByAjax.action?goods_id="+goods_id,       
            datatype:"json",
            async:false,
            success: function(data){
            if(data=="1"){
                $("input:radio[name='goods.is_up']").attr("disabled",false);
                $("#active_state_tr").show();
				$("#cancelActiveByAjax").hide();
				$("#active_state_cancel").hide();
				jNotify("取消活动成功！");
            }else if(data=="2"){
				jNotify("该商品处于预售前或者预售中，不能取消");
			}else{
				jNotify("取消活动失败！");
			}
		}
	});
}

function isAuto(){
	var is_up=$("input:radio[name='goods.is_up']:checked").val();
	if(is_up=='1'){
		$("#updowngoods_tr").hide();
	}else{
		$("#updowngoods_tr").show();
	}
		
}

function isLimit(){
	var is_up=$("input:radio[name='goods.is_limit']:checked").val();
	if(is_up=='1'){
		$("#is_limit").hide();
	}else{
		$("#is_limit").show();
	}
}
function isKjt(){
	var is_up=$("input:radio[name='goods.depot_id']:checked").val();
	if(is_up=='1'){
		$("#kjt_id").hide();
	}else{
		$("#kjt_id").show();
	}
}

//移除手机详情里面的样式
function removeAppDetailStyle(){
 	var contentdetail=$("#phone_goods_detail").val();
	$("#appdetail_conten_id").html(contentdetail);
	$("#appdetail_conten_id").find("img").each(function(){
		$(this).removeAttr("style");
		$(this).removeAttr("alt");
	});
	contentdetail=$("#appdetail_conten_id").html();
	$("#phone_goods_detail_hide").val(contentdetail);
    $("#goodspost").submit();
}
//列表调整库存
function updatestock_no(actionName){  
     var admin_goods_id='';
     var chks =document.getElementsByTagName('input');//得到所有input
     for(var i=0;i <chks.length;i++){ 
     if(chks[i].type.toLowerCase() == 'checkbox'&&chks[i].value!='on')
     {
          //得到所名为checkbox的input
          admin_goods_id+=chks[i].value+',';
         
       }
     }
      var len=admin_goods_id.length;
      var goods_id=admin_goods_id.substring(0,len-1);
      document.getElementById('goods_stock_id').value = goods_id;//用于隐藏所有的ID
	document.forms[0].action=actionName;
	document.forms[0].submit();
}








				             	 			