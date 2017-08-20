var default_content="未设置地区";
var mName="件";
var mUint="件";
var $setareaps="";//设置存放地区域的位置
var $setareaidstr="";//设置存放地区域的id串
var mode_id="";
//设置配置区域
function setmodearea(obj,s_id){
	var modeTable="";
	var mode_tr="<tr><td>"+"<input type='hidden' name='end_area_str' class='ckb_end_area'/><input type='hidden' name='default_ship' value='1'/>"+
					"<div class='cot_area'>"+default_content+"</div><span class='cot_edit'><a onclick='editarea(this);'>编辑</a></span></td>"+
					"<td align='center'><input name='first_weight' type='text' style='width:50px' value='1'></td>"+
    				"<td align='center'><input name='first_price' type='text' style='width:50px'></td>"+
    				"<td align='center'><input name='cont_weight' type='text' style='width:50px' value='1'></td>"+
    				"<td align='center'><input name='cont_price' type='text' style='width:50px'></td>"+
    				"<td align='center'><a onclick='delarea_tr(this,"+s_id+");'>删除</a></td></tr>";
	if($("#other_"+s_id).length>0){	
		$("#other_"+s_id).append(mode_tr);		
	}else{
		modeTable+="<table id='other_"+s_id+"' width='100%' class='wwtable' cellspacing='1' cellpadding='8'>";
		modeTable+="<tr style='background-color:#f8f8f8'><td align='center' width='40%'>运送到</td>"+
					"<td align='center' width='10%'>首"+mName+"("+mUint+")</td><td align='center' width='10%'>首费(元)</td>"+
					"<td align='center' width='10%'>续"+mName+"("+mUint+")</td><td align='center' width='10%'>续费(元)</td>"+
					"<td align='center' width='10%'>操作</td></tr>";
		modeTable+=mode_tr;
		modeTable+="</table>";		    			
		//填充数据
		$("#setareadiv_"+s_id).html(modeTable);			    						    		
	}
}

//删除设置区
function delarea_tr(obj,s_id){
	if($("#other_"+s_id).find("tr").length>2){
		$(obj).parent("td").parent("tr").remove();
	}else{
		$("#setareadiv_"+s_id).html("");		
	}	
}


$(document).ready(function(){

	$(".areaul1").find('span').click(function(){
		//隐藏所有框
		$(".areaul1").find(".threediv").css("display","none");
		$(".areaul1").find(">li").removeClass("select_li");
		//显示当前框
		$(this).parent("li").find(".threediv").css("display","block");
		$(this).parent("li").addClass("select_li");
		$(this).parent("li").css("z-index","999");
		$(this).parent("li").find(".threediv").css("z-index","1");
	});
	//关闭按钮
	$(".colsethree").click(function(){
		$(this).parent(".close").parent(".threediv").parent("li").removeClass("select_li");
		$(this).parent(".close").parent(".threediv").css("display","none");
	});
	//选择区域，选择该区域下的地区
	$(".cb_first").click(function(){
		 var c_id=$(this).val();
		 if(this.checked){
		 	$(".showareadiv1").find("input:checkbox[id*=ah"+c_id+"]").each(function(){
		 	 	this.checked=true;  
		 	 	if($(this).attr("class")=="cb_two"){
		 	 		var count=0;
		 	 		$(this).parent("li").find(".threediv").find(".cb_three").each(function(){
		 	 			count++;
		 	 		});
			 	 	//计算选中checkbox个数
					if(count>0){
						$(this).parent("li").find(".areanum").html("("+count+")");	
					}else{
					 	$(this).parent("li").find(".areanum").html("");	
					};
				}
		 	});
		 }else{
		 	$(".showareadiv1").find("input:checkbox[id*=ah"+c_id+"]").each(function(){
		 	 	this.checked=false;  
		 	 	if($(this).attr("class")=="cb_two"){
					 	$(this).parent("li").find(".areanum").html("");	
				}
		 	});
		 }		
	});	
	//第二级地区
	$(".cb_two").click(function(){
		 var c_id=$(this).val();		 
		 var count=0;
		 if(this.checked){
		 	$(".showareadiv1").find(".threeul1").find("input:checkbox[id*="+c_id+"]").each(function(){
		 	 	this.checked=true;  	
		 	});
		 	count=$(".showareadiv1").find(".threeul1").find("input:checkbox[id*="+c_id+"][checked]").length;
		 }else{
		 	$(".showareadiv1").find(".threeul1").find("input:checkbox[id*="+c_id+"]").each(function(){
		 	 	this.checked=false;  		 	 	
		 	});
		 }	
		 //计算选中checkbox个数
		 if(count>0){
		 	$(this).parent("li").find(".areanum").html("("+count+")");	
		 }else{
		 	$(this).parent("li").find(".areanum").html("");	
		 }
		 //判断是否选中第一级checkbox
		 ifCheckboxFirst(this);
	});	
	//第三级地区
	$(".cb_three").click(function(){
		 var c_id=$(this).val();
		 var $two=$(this).closest('.select_li')
		 var cb_box=$two.find(".threeul1").find("input:checkbox").length;
		 var cb_length=$two.find(".threeul1").find("input:checkbox[checked]").length;
		 if(cb_box==cb_length){
		 	$two.find('.cb_two').each(function(){
		 		this.checked=true;
		 	});
		 }else{
		 	$two.find('.cb_two').each(function(){
		 		this.checked=false;
		 	});
		 }
		 if(cb_length==0){
		 	 $two.find(".areanum").html("");
		 }else{
		 	 $two.find(".areanum").html("("+cb_length+")");
		 }
		 ifCheckboxFirst(this)
	});	
	
	//是否显示其它的运费
	$("input:radio[name='smode_id']").click(function(){
	     var s_id=$(this).val();
		 if(this.checked){
		 	 var mtype=$("input:radio[name=shiptemplate.valuation_mode]:checked").val();
		 	 if(mtype==1){
		 	 	mName="件";
				mUint="件";
		 	 }else if(mtype==2){
		 	 	mName="重";
				mUint="kg";
		 	 }else if(mtype==3){
		 	 	mName="体积";
				mUint="m³";
		 	 }
		 	 var defaultHtml="",operHtml="";
		 	 defaultHtml+="<div class='defalut_mode'><input type='hidden' name='end_area_str' class='ckb_end_area'  value=''/>"+
		 	 			"<input type='hidden' name='default_ship' value='0'/>"+
		    			"<span>默认运费：<input  name='first_weight' type='text' style='width:60px;'/> <font class='fd_unit'>"+mUint+"</font>内，</span>"+
		    			"<span><input  name='first_price' type='text' style='width:60px;'/> 元，</span>"+
		    			"<span>每增加<input name='cont_weight' type='text' style='width:60px;'/> <font class='fd_price'>"+mUint+"</font>，</span>"+
		    			"<span>增加运费<input  name='cont_price'  type='text'style='width:60px;'/> 元 </span></div><div id='setareadiv_"+s_id+"'></div>";
		    			
			 operHtml="<br/><span style='margin-left:20px;'><a onclick='setmodearea(this,"+s_id+");'>为指定地区城市设置运费</a></span>&nbsp;";
			 if($("#setareadiv_"+s_id).length<=0){
			 	defaultHtml+=operHtml;
			 }			    		
		 	 $(this).parent("div").parent("div").find(".modes_div").prepend(defaultHtml);
		 	 $(this).parent("div").parent("div").find(".modes_div").css("display","block");
		 }else{
		     $(this).parent("div").parent("div").find(".defalut_mode").remove();
			 $(this).parent("div").parent("div").find(".modes_div").css("display","none");
		 }
		 $("input:radio[name=smode_id]").each(function(){
		if(!this.checked){
			$(this).parent("div").parent("div").find(".defalut_mode").remove();
			 $(this).parent("div").parent("div").find(".modes_div").css("display","none");
		}
	});	
	});
});


//编辑地区
function editarea(obj){
	$setareaps=$(obj).parent("span").parent("td").children(".cot_area");
	$setareaidstr=$(obj).parent("span").parent("td").children(".ckb_end_area");
	var thisidstr=$setareaidstr.val();
	mode_id=$(obj).closest(".modes_div").parent("div").find("input:hidden[name=cs_smode_id]").val();
	//找出第一级的地区区域
	$(".cb_first").each(function(){
		this.checked=false;
	});
	
	if(thisidstr!=""){
		$(".td_content").find(".cb_three").each(function(){
			//去掉选择
			this.checked=false;
			var ts_id=$(this).val();
			if(thisidstr.indexOf(ts_id)>-1){
				this.checked=true;
			}			
		});
		$(".td_content").find(".cb_two").each(function(){
			 this.checked=false;
			 $(this).parent("li").find(".areanum").html("");			 
			 var c_id=$(this).val();		 
			 if(thisidstr.indexOf(c_id)>-1){
				this.checked=true;
			}	
			 var count=0;
		 	 count=$(".showareadiv1").find(".threeul1").find("input:checkbox[id*="+c_id+"][checked]").length;			
			 //计算选中checkbox个数
			 if(count>0){
			 	$(this).parent("li").find(".areanum").html("("+count+")");	
			 }else{
			 	$(this).parent("li").find(".areanum").html("");	
			 }
			 //判断是否选中第一级checkbox
			 ifCheckboxFirst(this);
		});
	}else{
		$(".td_content").find("input:checkbox").each(function(){
			$(this).parent("li").find(".areanum").html("");
			this.checked=false;
		});
	}
	$(".showareadiv1").shippopup({});
}


//确定地区
function checkareaid(){
	var checkIdStr='';
	var checkIdName='';
	//拼出该配送方式下已选的地区
	var already_area="";
	$("#other_"+mode_id).find("input:hidden[name=end_area_str]").each(function(){
		already_area+=$(this).val()+"|";
	});
	//确定地区
	var flag=false;
	$(".showareadiv1").find("input:checkbox[name='end_area']").each(function(){	
		if(this.checked){
			var c_id=$(this).val();
			if(already_area.indexOf(c_id)>-1){
				flag=true;
			}else{
				checkIdStr+=c_id+"|";
				checkIdName+=$("#gan_"+c_id).html()+",";
			}
		}
		if(flag){			
			return;
		}
	});
	if(flag){
		jNotify("该配送方式下已存在该地区,请重新选择!"); 
		$(this).parent(".cot_edit").parent("td").find(".cot_area").html(default_content);
	}else{
		$setareaps.html(checkIdName);
		$setareaidstr.val(checkIdStr);		
	}
	//关闭层
	colseShipCover();
}


//判断是否选中第一级的checkbox
function ifCheckboxFirst(obj){
	var cb_val=$(obj).attr("id");
	var cb_ah="";
	var len=cb_val.indexOf("_");
	if(len>-1){
		cb_ah=cb_val.substring(0,len);
	}
	var flag=false;
	$("input:checkbox[id*="+cb_ah+"_]").each(function(){
		 if(this.checked==false){
		 	flag=true;
		 }
	});
	//选中第一级
	if(flag==false){
		$("#"+cb_ah).attr('checked',true);
	}else{
		$("#"+cb_ah).attr('checked',false);
	}
}


 // 创建弹出层一个闭包  
(function($) {  
	  // 插件的定义  
	  $.fn.shippopup= function(options) {   
	  	  //获取设置插件的选项	 
	      var opts = $.extend({}, $.fn.shippopup.defaults, options);
	      //初始化方法
	      this.each(function(){    
	          //获取窗体的宽度与高度
    	   	  var _scrollHeight = $(document).scrollTop(),//获取当前窗口距离页面顶部高度
    	      	_documentHeight =  $(document).height(),//获取当前文档的高度
			  _windowHeight = $(window).height(),//获取当前窗口高度
			  _windowWidth = $(window).width(),//获取当前窗口宽度
			  _popupHeight = $(this).height(),//获取弹出层高度
			  _popupWeight = $(this).width();//获取弹出层宽度
			  var _posiTop = (_windowHeight - _popupHeight)/2 + _scrollHeight;
			  var _posiLeft = (_windowWidth - _popupWeight)/2;
	          //新建一个DIV遮盖层
    	   	  $("<div id='shipcover' class='shipcover'></div>").appendTo("body");	
    	   	  $("#shipcover").height(_documentHeight);
    	   	  $("#shipcover").width("100%");
    	   	  $("#shipcover").css({"opacity": "0.3", background: "black","position":"absolute","left":"0px","top":"0px","z-index":888});
    	   	  $("#shipcover").fadeIn("slow");
			  $(this).css({"left": _posiLeft + "px","top":_posiTop + "px","display":"block","position":"absolute","z-index":9999});//设置position
		  }); 
	  };  
	  // 插件的defaults默认配置  
	  $.fn.shippopup.defaults = {  
	 
	  };	
// 闭包结束  
})(jQuery); 

//关闭弹出层
function colseShipCover(){
	$(".shipcover").remove();
	$(".showareadiv1").css("display","none");
}

//提交表单
function shipsubmit(){
	$("input:checkbox[name=smode_id]").each(function(){
		if(this.checked){
			// 获取每个对应运费的个数
			var num_length=$(this).parent("div").parent("div").find("input:hidden[name=default_ship]").length;
			$(this).parent("div").children(".cs_num").val(num_length);
			// 
		}
	});	
	$("#shipfrom").submit();
}
//提交表单
function submiForm(){
	$("#shipfrom").submit();
	window.parent.bottom.location.reload();
}

//获取单选值
function clickradio(){
		var sel_sub=$("#sel_submit").val();
		if(sel_sub==1){
			$("#shipfrom").attr("action","/bmall_Shiptemplate_add.action").submit();
		}else if(sel_sub==2){
			$("#shipfrom").attr("action","/bmall_Shiptemplate_add.action").submit();
		}else if(sel_sub==3){
			$("#shipfrom").attr("action","/bmall_Shiptemplate_add.action").submit();
		}else if(sel_sub==4){
			$("#shipfrom").attr("action","/bmall_Shiptemplate_add.action").submit();
		}
}

//单选值
function selradio(){
  var val=$("input[name='shiptemplate.valuation_mode']:checked").val();;
  $("#sel").val(val);
  $("#shipfrom").attr("action","/bmall_Shiptemplate_view.action").submit();
}