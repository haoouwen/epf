
//*****************************************扩展参数操作开始********************************//
//新增添加扩展参数
function addextend_attr(){  
	createExtendTable();
	//加载显示
    is_show();
}

//拼是否显示的checkbox项
function is_show(){
	var is_show_str="";
	$(".cb_show").each(function(){
		if(this.checked){
			is_show_str+="0"+",";
		}else{
			is_show_str+="1"+",";
		}
	})
	is_show_str=deleteLastChar(is_show_str,",");
	$("#all_extend_infostate_atrr").val(is_show_str);
}

//创建扩展属性页面
//extendname:扩展属性名称
//extendtype：扩展属性类型
//extendtypevalue：扩展属性类型值
//extendshow:扩展属性是否显示
//extendsortno：扩展属性排序
function createExtendTable()
{
    var radid=getRndStr(10);//随机数ID
    var varextendname="";
    var varextendtypevalue="";
    var varextendsortno="0";
    var varextendtype="<option value='0'>选择项</option><option value='1'>输入项</option>";
    var varextendshow="<input  class='cb_show'  type='checkbox' value='0' checked='true' onclick='is_show();'/>";

	//构造扩展属性列表
	var tbTree="";
	tbTree +="<tr bgcolor='#F5F5F5' id=\'exttr"+radid+"\'>"
	+" <td align='center'><input name='all_extend_sortno_attr' onkeyup='checkDigital(this);' style='width:50px'  value='"+varextendsortno+"' /></td>"
    +"<td align='center'><input name='all_extend_name_attr' class='input' onkeyup='checkLength(this,20);'  style='width:160px;' " +varextendname+" /></td>"
    +" <td align='center'>"
    +"<select name='all_extend_type_atrr'>"+varextendtype+"</select></td>"
    +" <td align='center'><input name='all_extend_type_value_attr' style='width:400px;' onkeyup='checkLength(this,200);'   class='input' " +varextendname+" /></td>"
	+"<td align='center'>"+varextendshow+"</td>"
	+"<td align='center'><a onclick="+"delrow(\'exttr"+radid+"\')"+"><img src='/includes/common/images/delete.png' /></a></td>"
	+"</tr>";
	$("#extendsTable").append(tbTree);
}

//移除行
function delrow(menu_id){
   $("#"+menu_id+"").remove();    	
}
//*****************************************扩展属性操作结束********************************//
















//*****************************************规格操作开始********************************//

function addSpec(){
	$("#size-box").popup({
	  	p_width:"800",
		p_height:"480",
	  	pop_title:"选择规格项"
	});	
	//浮动变色
	var selColor = "rgb(255, 255, 204)";
	$(".sizeul").find("li").each(function(){
		$(this).hover(function(){
			if($(this).css("background-color")!=selColor){
				$(this).css("background","#F6DDA1");
			}
		},function(){
			if($(this).css("background-color")!=selColor){
				$(this).css("background","#FFFFFF");
			}
		});
	});
	//点击加载规格的规格值
	$(".sizeul").find("li").click(function(){
		$(".sizeul").find("li").removeClass("selval");
		$(".sizeul").find("li").css("background","#FFFFFF");
		$(this).css("background",selColor);
		$(this).addClass("selval");
		//获取规格ID
		var li_id =$(this).attr("id");
		var input_spec_name =$(this).find(".input_spec_name").val();
		var input_wrong_name =$(this).find(".input_wrong_name").val();
		var input_show_type =$(this).find(".input_show_type").val();
		var input_show_style =$(this).find(".input_show_style").val();
		//赋值
		$("#size_name").html(input_spec_name);
		$("#wrong_name").html(input_wrong_name);
		$("#show_size_type").html(input_show_type);
		//加载规格值信息
		$.ajax({
	        type: "post",
	        url: "/admin_Specvalue_getspecvalList.action?spec_id="+li_id,
	        datatype:"json",
	        async:false,
	        success: function(data){ 
	        	data=eval("(" +data+")");
	        	var szHtml=""
	        	for(var i=0;i<data.length;i++){
		        	szHtml+= "<li>";
		        	
		        	if(input_show_style=='0'){
		        		szHtml+="<span>"+data[i].value_name+"</span>";	
		        	}else{
		        		szHtml+="<img width='25px' height='25px' src='"+data[i].img_path+"'\/>";
		        	}
		        	szHtml+= "</li>";
	        	}
	        	$("#size_value_ul").html(szHtml);
	        }	 
		}); 
	});
}


//添加选择的规格
function addspec_attr(){
	//点击加载规格的规格值
	var count=0;
	var specid="",spectext="";;
	$(".sizeul").find("li.selval").each(function(){
		specid=$(this).attr("id");
		spectext = $(this).find(".input_spec_name").val();
		count+=1;
	});
	//判断是否有选中项
	if(count==0){
		jNotify("请先选择规格!");
	}else if(count==5){
		jNotify("您当前的类型最多只能添加五种规格!");
	}else if($("#specifid"+specid).length>0){
		jNotify("您已添加该规格!");
	}else{
		createSpecTable(specid,spectext);
		//关闭层
		$("#size-box").ccover();
	}
}


//添加规格到列表中
function createSpecTable(specid,spectext){
	 var specTable="";
	 specTable+=" <tr bgcolor='#F5F5F5' id=\'sepci"+specid+"\' >"
	 +"<td align='center'>"+spectext+""
	 +" <input name='all_specif_id_attr' type='hidden' value='"+specid+"' id='specifid"+specid+"'  />" 
	 +"<input name='all_specif_values_attr' type='hidden' id='specifvalues"+spectext+"' value='"+spectext+"' /> </td>"
	 +"<td align='center'><a onclick="+"delrow(\'sepci"+specid+"\')"+"><img src='/includes/common/images/delete.png' /></a></td>"
	 +"</tr>";
	 $("#sepcisTable").append(specTable);
}


//*****************************************规格操作结束********************************//



//*****************************************详细参数操作开始********************************//
//新增添加详细参数组
function adddetail_attr()
{
	createDetail_attTable();
}
//创建详细参数组表格
function createDetail_attTable()
{
	var radid=getRndStr(10);//随机数ID
	var trdetailTable="";
	 trdetailTable+="<tr  id=\'detail"+radid+"\' style='background:#FFFFFF;'><td >"
	 +"<table id=\'comparagroupValueTable"+radid+"\' >"
	 +"<tr>"
	 +"<td class='ctd'>参数组名称:</td>"
	 +"<td> <input name='all_comparagroup_name_attr' type='text'  /></td>"
	 +"<td style='padding-left:6px;'> <a onclick="+"adddetail_attr_value(\'"+radid+"\')"+" >"
	 +"<img src='/includes/common/images/add.png'/>为这个参数组添加一个参数</a></td>"
	 +"<td> <a onclick="+"delrow(\'detail"+radid+"\')"+"  ><img src='/includes/common/images/delete.png' />删除此参数组</a>"
	 +" <input name='all_comparagroup_count_attr' type='hidden' id='detailcount"+radid+"' value='0'/></td>"
	 +"</tr></table></td></tr>";
	 //添加到主表中
	$("#comparagroupTable").append(trdetailTable);
	//添加参数值
	adddetail_attr_value(radid);
}


//新增添加详细参数值
function adddetail_attr_value(para_id)
{
	var radid=getRndStr(10);//随机数ID
	var trdetailValueTable="";
	trdetailValueTable +=" <tr style='background:#FFFFFF;' id=\'detailvalue"+radid+"\' >"
	 +" <td class='ctd'>参数名:</td>"
	 +" <td><input class='gv_"+para_id+"' name='all_comparagroup_value_attr' type='text'  /></td>"
	 +" <td style='padding-left:6px;'> <a onclick="+"delrowdetailcompara(\'"+radid+"\',\'"+para_id+"\')"+" >"
	 +" <img src='/includes/common/images/delete.png' /></a></td>"
	 +"</tr>";
	$("#comparagroupValueTable"+para_id+"").append(trdetailValueTable);
	//计算当前参数组的参数数
	var row_count = $("#detailcount"+para_id).val();
	$("#detailcount"+para_id).val(parseInt(row_count)+1);
}

//删除详细参数值行
function delrowdetailcompara(para_id,gp_id){
   //计算当前参数组的参数数
   var row_count = $("#detailcount"+gp_id).val();
   $("#detailcount"+gp_id).val(parseInt(row_count)-1);	
    //移除行
   $("#detailvalue"+para_id+"").remove();  
}
//*****************************************详细参数操作结束********************************//


//获取随机数方法
function getRndStr(le){
  var str='0123456789';
  var l = parseInt(le)||Math.ceil(Math.random() * 5);
  var ret = '';
  for(var i=0;i<l;i++){
   ret += str.charAt(Math.ceil(Math.random()*(str.length-1)));
  }
  return ret;
}
//页面切换
function re_show(val,num,btnName,divName,btncss1,btncss2){
	for(var i=1;i<=num;i++)
	{
		if(val==i)
		{
			document.getElementById(divName+i).style.display = 'block';
			document.getElementById(btnName+i).className = btncss1;
		}
		else{
			document.getElementById(divName+i).style.display = 'none';
			document.getElementById(btnName+i).className = btncss2;
		}
	}
}

//提交规格表单的时候，验证是否全部输入数据
function subSpecInfo(actionName)
{
	var flage=0;
	$("#specform").find("input:text").each(function(){
		if($(this).val()==null||$(this).val()=="")
		{
			flage=1;
		}
	});
	if(flage=="1")
	{ 
		jNotify("请填写完整信息!");
	}
	else
	{
		$("#subspecform").action=actionName;
		$("#subspecform").submit();
	}
}
function showchangge(speid)
{
  $(".specvalueattrs").css("display","none");
  $("#"+speid).css("display","block");
}










