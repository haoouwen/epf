
/*
*功能：系统组织机构管理
*主要有：显示组织机构、对组织结构（新增、修改、删除）等操作
*创建一个地区框 、加载部门的级联select、验证是否需要回选、根据上级分类ID找出本级的分类添加到select中
*/
var areanum = 0;
//显示组织机构
function showorga(org_id, level) {	 
     //加载颜色分别
	$("#arealevel" + level + " span").css("border", "1px solid #FFF");
	$("#arealevel" + level + " li").css("background-color", "#FFF");
	$("#areali" + org_id).parent("li").css("background-color", "#e2efeb");
	$("#areali" + org_id).css("border", "1px solid #e2efeb");
      //加载部门    
	var title = $("#areali" + org_id).html();
	$.ajax({type:"post", url:"/admin_Organize_getList.action?up_org_id=" + org_id + "&ajaxRequestRandom=" + Math.random(), datatype:"json", success:function (data) {
		if (data.length > 0) {
			if (level <= areanum) {
				for (var i = (level + 1); i < (areanum + 1); i++) {
					$("#arealevel" + i).remove();
				}
				$("#orgalist").append(data);
				areanum = level + 1;      
                 //在下一级地区名称显示上一级名称跟上一级的ID值
				if (level != 0) {
					$("#menutitle" + areanum).html(title);
					$("#menuvalue" + areanum).val(org_id);
				}
			}
		} else {
			if (title == null) {
				creatediv(org_id, level, "\u4e00\u7ea7\u90e8\u95e8");
			} else {
				creatediv(org_id, level, title);
			}
		}
	}});
}	     
   //更新组织结构
function updateorga(org_id) {
	document.forms[0].action = "/admin_Organize_view.action?organize.org_id=" + org_id;
	document.forms[0].submit();
}
   //插入组织结构
function insertorga(org_id, level) {
	document.forms[0].action = "/admin_Organize_add.action?organize.up_org_id=" + org_id + "&organize.org_level=" + level;
	document.forms[0].submit();
}    

   //删除部门
function deleteOg(org_id, levl) {
	art.dialog({title:"\u53cb\u60c5\u63d0\u793a", content:"<div class=\"decorate\">" + "\u786e\u5b9a\u8981\u5220\u9664?" + "</div>", okValue:"\u786e\u5b9a", width:"238px", cancelValue:"\u53d6\u6d88", ok:function () {
		document.forms[0].action = "/admin_Organize_delete.action?id=" + org_id;
		document.forms[0].submit();
	}, cancel:function () {
		return true;
	}});
}
  //创建一个部门框 
function creatediv(area_id, level, title) {
	for (var i = (level + 1); i <= (areanum + 1); i++) {
		$("#arealevel" + i).remove();
	}
	var area_level = level + 1;
	$("#orgalist").append("<div class='areadiv' id=arealevel" + area_level + "><h3 class='areatitle'><div id=menutitle" + area_level + " class='spantitle'>" + title + "</div></h3><input id=menuvalue" + area_level + " type='hidden' value='" + area_id + "'><div class='areacontent'></div><div class='areaadd'><img class='add' titile='\u65b0\u589e\u90e8\u95e8' onclick='insertorga(\"" + area_id + "\"," + area_level + ")' src='/include/common/images/add.png'/></div></div>");
	areanum = area_level;
}

  //加载部门的级联select
var orgcount = 1;
function onlyshoworg(id, level) {
	$.ajax({type:"post", url:"admin_Organize_viewlist.action?cid=" + id + "&ajaxRequestRandom=" + Math.random(), datatype:"json", async:true, success:function (data) {
		if (data.length > 0) {
			if (level == orgcount) {
				$("#orgselect").append(data);
				orgcount += 1;
				return false;
			}
			if (level < orgcount) {
				for (var i = (level + 1); i < (orgcount + 1); i++) {
					$("#level" + i).remove();
				}
				$("#orgselect").append(data);
				orgcount = level + 1;
				return false;
			}
		} else {
			for (var i = (level + 1); i < (orgcount + 1); i++) {
				$("#level" + i).remove();//移除元素 	                      	                      
			}
			orgcount = level;
		}
	}});
}


   //验证是否需要回选
function checkback(id, level) {
	var value = $("#org_value").val();
	if (value != "") {
		var back_val = value.split(",");
		if (back_val.length > 0) {
			for (var i = 0; i < back_val.length; i++) {
				if (back_val[i] != "") {
	 				//根据上级找出本级部门
					getThisOrg(back_val[i]);
	 				//选中之前选择的部门
					selectCheckObj("level" + (i + 1), back_val[i + 1]);
				}
				orgcount = level + i + 1;
			}
		}
	} else {
		onlyshoworg(id, level);
	}
}
      
	//根据上级分类ID找出本级的分类添加到select中
function getThisOrg(up_org_id) {
	$.ajax({type:"post", url:"admin_Organize_viewlist.action?cid=" + up_org_id + "&ajaxRequestRandom=" + Math.random(), datatype:"json", async:false, success:function (data) {
		$("#orgselect").append(data);
	}});
}

