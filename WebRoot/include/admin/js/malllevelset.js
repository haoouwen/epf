 //加载树
  var setting = {
		check: {
			enable: true
			
		},data: {
			key: {
				url: "xUrl",
				name:"menu_name",
				title:"menu_name"
			},simpleData: {
				enable: true,
				idKey: "menu_id",
				pIdKey: "up_menu_id"
			}
		}
	};
	function checkInter_height(){
		var inter_lower=$("#inter_lower").val();
		var inter_height=$("#inter_height").val();
		if(parseInt(inter_height)<=parseInt(inter_lower)){
		jAlert("积分上限必须大于积分下限","系统提示");
			return false;
		}
		return true;
	}