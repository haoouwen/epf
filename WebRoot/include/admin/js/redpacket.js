//弹出下载框
function showDownDiv(red_id){
//下载红包的ID
$("#red_id").val(red_id);
exportShowDIV('downDiv','300px','130px','下载红包')

}

//下载红包
function downCoupon(actionName){
  var down_nmu=$("#down_num").val();
  if(down_nmu==''){
	alert("请填写下载数量");
	return false ;
  }else{
   $("#down_nmu_s").val(down_nmu);
  }
  var red_id=$("#red_id").val();
  $.ajax({
		type:"post",
		url:"/redpacket!maxredpacket.action?red_id="+red_id+"&down_max="+down_nmu,
		datatype:"json",
		async:false,
		success:function(data){
			if(data=="0"){
				alert("请重新填写下载数量");
				return false 
			}else{
			 //判断红包下载数量是否操作红包总数控制
			  if(window.confirm("确定要下载红包吗?")) {
				document.forms["indexForm"].action=actionName;
				document.forms["indexForm"].submit();
			   }
			}
		}
	});
 
}