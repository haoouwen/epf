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
	var editStr = "<input type='checkbox' style='display:none;' name='sysmenu.menu_id' value=\""+treeNode.menu_id+"\"/><input name='isort_no' type='text' style='width:30px;' value='"+treeNode.sort_no+"' id='diyBtn_" +treeNode.id+ "'/>";
	//在什么之前添加
	aObj.before(editStr);
	//添加在什么后面
	var editStr2 = "<a href='###'  onclick='delMenu("+treeNode.menu_id+");'>删除</a>" +
					"<a href='/admin_Sysmenu_add.action?sysmenu.up_menu_id="+treeNode.menu_id+"&sysmenu.menu_level="+(parseInt(treeNode.menu_level)+1)+"&menu_code=sys'>添加</a>"+
					"<a href='/admin_Sysmenu_view.action?sysmenu.menu_id="+treeNode.menu_id+"&menu_code=sys'>修改</a>";
	aObj.after(editStr2);
	
}


//会员后台自定义控件
function addMallDiyDom(treeId, treeNode) {
	if (treeNode.parentNode && treeNode.parentNode.id!=2) return;
	var aObj = $("#" + treeNode.tId + IDMark_A);
	var editStr = "<input type='checkbox' style='display:none;' name='sysmenu.menu_id' value=\""+treeNode.menu_id+"\"/><input name='isort_no' type='text' style='width:30px;' value='"+treeNode.sort_no+"' id='diyBtn_" +treeNode.id+ "'/>";
	//在什么之前添加
	aObj.before(editStr);
	//添加在什么后面
	var editStr2 = "<a href='###'  onclick='delMenu("+treeNode.menu_id+");'>删除</a>" +
				   "<a href='/admin_Sysmenu_add.action?sysmenu.up_menu_id="+treeNode.menu_id+"&sysmenu.menu_level="+(parseInt(treeNode.menu_level)+1)+"&menu_code=b2c'>添加</a>"+
				   "<a href='/admin_Sysmenu_view.action?sysmenu.menu_id="+treeNode.menu_id+"&menu_code=b2c'>修改</a>";
	aObj.after(editStr2);
}







//排序
function updatesort_no(actionName){  
      var admin_group_id='';
      var chks =document.getElementsByTagName('input');//得到所有input
      for(var i=0;i <chks.length;i++){ 
      if(chks[i].type.toLowerCase() == 'checkbox'&&chks[i].value!='on'){
           //得到所名为checkbox的input
           admin_group_id+=chks[i].value+',';
        }
      }
      var len=admin_group_id.length;
      var group_id=admin_group_id.substring(0,len-1);
      document.getElementById('sysmenu_sortno_id').value = group_id;//用于隐藏所有的ID
   document.forms[0].action=actionName;
   document.forms[0].submit();
}





//删除菜单，并回选顶级菜单
function delMenu(menu){
	var fag=window.confirm("您确定要删除吗？");
	if(fag){
		var men=$("#menu_code").val();
		if(men==''){
			window.location='/admin_Sysmenu_deletemenu.action';
		}else{
			if(men=='sys'){
				window.location='/admin_Sysmenu_deletemenu.action?menuid='+menu+'&menu_code=${admin_menu?if_exists}';
			}else if(men='b2c'){
				window.location='/admin_Sysmenu_deletemenu.action?menuid='+menu+'&menu_code=${b2c_menu?if_exists}';
			}
		}
	}else{
		return false;
	}
}