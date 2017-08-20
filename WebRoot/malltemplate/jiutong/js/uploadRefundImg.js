
$(function () {
	$("#dropbox, #uploadifyfile").html5Uploader({name:"uploadifyfile", postUrl:"/uploadfy_executeUpimages.action", onServerReadyStateChange:changeFileUrl});
});
function changeFileUrl() {
	if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
		var is_return = $("#is_return").val();
		var img_url = xmlHttpRequest.responseText;
		var total_img = "";
		if (is_return == "0" || is_return == "1") {
			total_img = $("#member_icon_refund").val();
		} else {
			if (is_return == "2") {
				total_img = $("#member_icon_ex").val();
			}
		}
		if (total_img == "" || total_img == null) {
			if (is_return == "0" || is_return == "1") {
				$("#member_icon_refund").val(img_url);
			} else {
				if (is_return == "2") {
					$("#member_icon_ex").val(img_url);
				}
			}
		} else {
			if (is_return == "0" || is_return == "1") {
				$("#member_icon_refund").val(total_img + "," + img_url);
			} else {
				if (is_return == "2") {
					$("#member_icon_ex").val(total_img + "," + img_url);
				}
			}
		}
		var show_img = "";
		if (is_return == "0" || is_return == "1") {
			show_img = $("#member_icon_refund").val();
		} else {
			if (is_return == "2") {
				show_img = $("#member_icon_ex").val();
			}
		}
		var img_strs = show_img.split(",");
		var img_src = "";
		img_src += "<div class='sctpDiv'>";
		for (i = 0; i < img_strs.length; i++) {
			img_src += "<span id=" + i + "><img id='img" + i + "' src='" + img_strs[i] + "'/><a href='javascript:void(0);' onclick='delImg(" + i + ");'><br/><img style='width:14px;height:10px;' src='/include/admin/images/delimg.gif'/></a></span>";
		}
		img_src += "</div>";
		if (is_return == "0" || is_return == "1") {
			$("#img_src").html(img_src);
			if (img_strs.length >= 3) {
				$("#upload_refund").css("display", "none");
				$(".upload_refund").css("display", "none");
			}
		} else {
			if (is_return == "2") {
				$("#img_src_ex").html(img_src);
				if (img_strs.length >= 3) {
					$("#upload_ex").css("display", "none");
					$(".upload_ex").css("display", "none");
				}
			}
		}
	}
}

//删除商品图片
function delImg(obj) {
	$("#" + obj).remove();
	var show_img = "";
	$("img[id^='img']").each(function () {
		show_img += this.src + ",";
	});
	show_img = show_img.substring(0, show_img.length - 1);
	var is_return = $("#is_return").val();
	if (show_img != null || show_img != "") {
		var img_strs = show_img.split(",");
		var img_src = "";
		img_src += "<div class='sctpDiv'>";
		for (i = 0; i < img_strs.length; i++) {
			img_src += "<span id=" + i + "><img id='img" + i + "' src='" + img_strs[i] + "'/><a href='javascript:void(0);' onclick='delImg(" + i + ");'><br/><img style='width:14px;height:10px;' src='/include/admin/images/delimg.gif'/></a></span>";
		}
		img_src += "</div>";
		if (img_strs.length < 3) {
			if (is_return == "0" || is_return == "1") {
				$("#upload_refund").css("display", "block");
				$(".upload_refund").css("display", "block");
			} else {
				if (is_return == "2") {
					$("#upload_ex").css("display", "block");
					$(".upload_ex").css("display", "block");
				}
			}
		}
	}
	if (is_return == "0" || is_return == "1") {
		$("#img_src").html(img_src);
		$("#member_icon_refund").val(show_img);
	} else {
		if (is_return == "2") {
			$("#img_src_ex").html(img_src);
			$("#member_icon_ex").val(show_img);
		}
	}
}

