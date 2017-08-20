
//批量打印商品二维码 type 打印类型：1：第一次打印，2表示补打快递
function goods_qrcode_print(type) {
    var m_tip="打印";
    var p_tip="";
    if(type=="1"){
      m_tip="打印";
    }else if(type=="2"){
      m_tip="补打";
      p_tip="";
    }
     var g_count=0;
     var goods_id="";
     $("input:checkbox[name='goods.goods_id']").each(function(i){
			if(this.checked==true){
				goods_id+=$(this).val()+",";
				g_count++;
			}	      
      });
      //id串
      goods_id=deleteLastChar(goods_id,",");
      if(g_count==0){
		  art.dialog({
			title: '系统提示',
			content:'<div style="font-size:16px;width:150px;color:#FE7E03;text-align:center;">'+'请选择一个商品'+'</div>',
			cancelValue: '确定',
			width: '300px',
		    cancel: function () {
				return true;
		    }
		 });
	  }else{
            art.dialog({
			title: '<font style="font-weight:bold;font-size:15px;">'+m_tip+'商品二维码</font>',
			content:'<div style="font-size:16px;width:300px;color:#FE7E03;text-align:center;"><font color="black">'+'您确认要'+m_tip+'所选【<font size="5px;" color="#CD3700;">'+g_count+'</font>】个商品二维码吗?',
			okValue: '确定',
			width: '400px',
			cancelValue: '取消',
		    ok: function () {
		         var checksgid = goods_id.split(',');
					for(var i=0;i<checksgid.length;i++){
				     $.ajax({
				          type: "post",
				          url: "/asysuser!getGoodsQRcode.action?goods_id="+checksgid[i],
				          contentType:"application/x-www-form-urlencoded;charset=utf-8", 
				          datatype:"json",
				          async:false,
				          success: function(data){ 
					         if(data!=""){
					         eval(data);
					          LODOP.PRINT();
					          jNotify("打印中...");
					         } 	
				          }	 
					 });  
				}
		        //重新加载页面
		        location.reload();
				return true;
		    },
		    cancel: function () {
				return true;
		    }
		 });
	}
}


//批量打印门店二维码 type 打印类型：1：第一次打印，2表示补打快递
function store_qrcode_print(type) {
    var m_tip="打印";
    var p_tip="";
    if(type=="1"){
      m_tip="打印";
    }else if(type=="2"){
      m_tip="补打";
      p_tip="";
    }
     var s_count=0;
     var store_id="";
     $("input:checkbox[name='asysuser.user_id']").each(function(i){
			if(this.checked==true){
				store_id+=$(this).val()+",";
				s_count++;
			}	      
      });
      //id串
      store_id=deleteLastChar(store_id,",");
      if(s_count==0){
		  art.dialog({
			title: '系统提示',
			content:'<div style="font-size:16px;width:150px;color:#FE7E03;text-align:center;">'+'请选择一个门店'+'</div>',
			cancelValue: '确定',
			width: '300px',
		    cancel: function () {
				return true;
		    }
		 });
	  }else{
            art.dialog({
			title: '<font style="font-weight:bold;font-size:15px;">'+m_tip+'门店二维码</font>',
			content:'<div style="font-size:16px;width:300px;color:#FE7E03;text-align:center;"><font color="black">'+'您确认要'+m_tip+'所选【<font size="5px;" color="#CD3700;">'+s_count+'</font>】个门店二维码吗?',
			okValue: '确定',
			width: '400px',
			cancelValue: '取消',
		    ok: function () {
		         var checksgid = store_id.split(',');
					for(var i=0;i<checksgid.length;i++){
				     $.ajax({
				          type: "post",
				          url: "/asysuser!getStoreQRcode.action?store_id="+checksgid[i],
				          contentType:"application/x-www-form-urlencoded;charset=utf-8", 
				          datatype:"json",
				          async:false,
				          success: function(data){ 
					         if(data!=""){
					         eval(data);
					          LODOP.PRINT();
					          jNotify("打印中...");
					         } 	
				          }	 
					 });  
				}
		        //重新加载页面
		        location.reload();
				return true;
		    },
		    cancel: function () {
				return true;
		    }
		 });
	}
}  


