 var IDMark_A = "_a";
 var setting = {
		data: {
			key: {
				name:"ch_name",
				title:"ch_name"
			},simpleData: {
				enable: true,
				idKey: "ch_id",
				pIdKey: "up_ch_id"
			}
		},view: {
			addDiyDom: addDiyDom
		}
	};


//系统后台自定义控件
function addDiyDom(treeId, treeNode) {
	if (treeNode.parentNode && treeNode.parentNode.id!=2) return;
	var aObj = $("#" + treeNode.tId + IDMark_A);
	//添加在什么后面
	var editStr2 = "<a href='###'  onclick='doHtmlPage(\"one\",\""+treeNode.ch_id+"\");'>仅更新所选栏目</a>";
	aObj.after(editStr2);
	
}

//网站栏目管理树
var Colsetting = {
		data: {
			key: {
				name:"ch_name",
				title:"ch_name"
			},simpleData: {
				enable: true,
				idKey: "ch_id",
				pIdKey: "up_ch_id"
			}
		},view: {
				addDiyDom: addColDiyDom
		}
	};

//系统后台自定义控件
function addColDiyDom(treeId, treeNode) {
	if (treeNode.parentNode && treeNode.parentNode.id!=2) return;
	var aObj = $("#" + treeNode.tId + IDMark_A);
	var editStr = "<input type='checkbox' checked='true' style='display:none;' name='channel.ch_id' value=\""+treeNode.ch_id+"\"/><input name='isort_no' type='text' style='width:30px;' value='"+treeNode.sort_no+"' id='diyBtn_" +treeNode.id+ "'/>";
	//在什么之前添加
	aObj.before(editStr);
	//添加在什么后面
	var editStr2 = "<a href='###'  onclick='delOneInfo(\"channel.ch_id\",\"/admin_Channel_delete.action\","+treeNode.ch_id+");'>删除</a>" +
					"<a href='/admin_Channel_add.action?channel.up_ch_id="+treeNode.ch_id+"&channel.ch_level="+(parseInt(treeNode.ch_level)+1)+"'>添加</a>"+
					"<a href='/admin_Channel_view.action?channel.ch_id="+treeNode.ch_id+"'>修改</a>";
	aObj.after(editStr2);
	
}
//排序
 function updatesort_no(actionName){  
       var admin_group_id='';
       var chks =document.getElementsByTagName('input');//得到所有input
       for(var i=0;i <chks.length;i++){ 
       if(chks[i].type.toLowerCase() == 'checkbox'&&chks[i].value!='on')
       {
            //得到所名为checkbox的input
            admin_group_id+=chks[i].value+',';
           
         }
       }
        var len=admin_group_id.length;
        var group_id=admin_group_id.substring(0,len-1);
        document.getElementById('admin_Sort_id').value = group_id;//用于隐藏所有的ID
		document.forms[0].action=actionName;
		document.forms[0].submit();
 }
