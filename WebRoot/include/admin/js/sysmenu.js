 var IDMark_A = "_a";

//系统后台树
var SysSetting = {
		data: {
			key: {
				url: "xUrl",
				name:"menu_name",
				title:"menu_name"
			},simpleData: {
				enable: true,
				idKey: "menu_id",
				pIdKey: "up_menu_id"
			}
		},view: {
				addDiyDom: addSysDiyDom
		}
	};
	
//会员后台树
var MallSetting = {
		data: {
			key: {
				url: "xUrl",
				name:"menu_name",
				title:"menu_name"
			},simpleData: {
				enable: true,
				idKey: "menu_id",
				pIdKey: "up_menu_id"
			}
		},view: {
				addDiyDom: addMallDiyDom
		}
	};

//系统后台自定义控件
function addSysDiyDom(treeId, treeNode) {
	if (treeNode.parentNode && treeNode.parentNode.id!=2) return;
	var aObj = $("#" + treeNode.tId + IDMark_A);
	var editStr = "<input type='checkbox' checked='true' style='display:none;' name='sysmenu.menu_id' value=\""+treeNode.menu_id+"\"/><input name='isort_no' type='text' style='width:30px;' value='"+treeNode.sort_no+"' id='diyBtn_" +treeNode.id+ "'/>";
	//在什么之前添加
	aObj.before(editStr);
		var show_memu=treeNode.enabled;
	var show_tip="";
	if(show_memu=="1"){
	  show_tip="<font color='red'>[无效]</font>";
	}else{
	  show_tip="<font color='green'>[有效]</font>"
	}
	//添加在什么后面
	var editStr2 =show_tip+"&nbsp;&nbsp;<a href='###'  onclick='delMenu("+treeNode.menu_id+");'>删除</a>" +
					"<a href='/admin_Sysmenu_add.action?sysmenu.up_menu_id="+treeNode.menu_id+"&sysmenu.menu_level="+(parseInt(treeNode.menu_level)+1)+"&menu_code=sys'>添加</a>"+
					"<a onclick='getId(this)' href='/admin_Sysmenu_view.action?sysmenu.menu_id="+treeNode.menu_id+"&menu_code=sys'>修改</a>";
	aObj.after(editStr2);
	
}

//会员后台自定义控件
function getId(obj) {
	
}

//会员后台自定义控件
function addMallDiyDom(treeId, treeNode) {
	if (treeNode.parentNode && treeNode.parentNode.id!=2) return;
	var aObj = $("#" + treeNode.tId + IDMark_A);
	var editStr = "<input type='checkbox' checked='true' style='display:none;' name='sysmenu.menu_id' value=\""+treeNode.menu_id+"\"/><input name='isort_no' type='text' style='width:30px;' value='"+treeNode.sort_no+"' id='diyBtn_" +treeNode.id+ "'/>";
	//在什么之前添加
	aObj.before(editStr);
	var show_memu=treeNode.enabled;
	var show_tip="";
	if(show_memu=="1"){
	  show_tip="<font color='red'>[无效]</font>";
	}else{
	  show_tip="<font color='green'>[有效]</font>"
	}
	//添加在什么后面
	var editStr2 = show_tip+"&nbsp;&nbsp;<a href='###'  onclick='delMenu("+treeNode.menu_id+");'>删除</a>" +
				   "<a href='/admin_Sysmenu_add.action?sysmenu.up_menu_id="+treeNode.menu_id+"&sysmenu.menu_level="+(parseInt(treeNode.menu_level)+1)+"&menu_code=b2c'>添加</a>"+
				   "<a href='/admin_Sysmenu_view.action?sysmenu.menu_id="+treeNode.menu_id+"&menu_code=b2c'>修改</a>";
	aObj.after(editStr2);
}


//删除菜单，并回选顶级菜单
function delMenu(menu){
	art.dialog({
		title: '系统提示',
		content:'<div class="decorate">'+'确定要删除?'+'</div>',
		okValue: '确定',
		width: '238px',
		cancelValue: '取消',
	    ok: function () {
	        var men=$("#menu_code").val();
			if(men==''){
				window.location='/admin_Sysmenu_deletemenu.action';
			}else{
				if(men=='sys'){
					window.location='/admin_Sysmenu_deletemenu.action?menuid='+menu+'&menu_code='+men;
				}else if(men='b2c'){
					window.location='/admin_Sysmenu_deletemenu.action?menuid='+menu+'&menu_code='+men;
				}
			}
	    },
	    cancel: function () {
			return true;
	    }
   });
}
//添加
function addMenu(){
	var sel_id = $(".tabbar").find(".selected").attr("id");
	document.forms[0].action="/admin_Sysmenu_add.action?sysmenu.up_menu_id=1111111111&sysmenu.menu_level=1&menu_code="+sel_id;
	document.forms[0].submit();
}

//判断更新有效是否将所属的子菜单也更新
function update_menu(){
	var flag=$("input[name:sysmenu.enabled]:checked").val();
	if(flag=='0'){
		art.dialog({
				title: '系统提示',
				content:'<div class="decorate">'+'是否将子菜单设为有效！'+'</div>',
				okValue: '是',
				width: '238px',
				cancelValue: '否',
			    ok: function () {
			        document.getElementById('men_form').action="/admin_Sysmenu_update.action?type=1";
			    	document.getElementById('men_form').submit();	  
			    },
			    cancel: function () {
					document.getElementById('men_form').action="/admin_Sysmenu_update.action?type=2";
			    	document.getElementById('men_form').submit();
			    }
			});
	}else{
		document.getElementById('men_form').action="/admin_Sysmenu_update.action";
		document.getElementById('men_form').submit();
	}
}

