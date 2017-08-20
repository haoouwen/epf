//加载打印订单商品详细列表信息
//html_tr_id:详细商品列表的数据模版行，存在于模版中
//html_table_id：详细商品的数据表格ID，存在于模版中
function IntPrintList(html_tr_id,html_table_id){
	var listhtml=$("#"+html_tr_id).html();
	if(listhtml!=null&&listhtml!=""){
		var order_id_s=$("#order_id_s").val();
		var list_content="";
		$.ajax({
	          type: "post",
	          url: "/goodsorder!ajaxgoodsdetaillist.action?order_id_s="+order_id_s,
	          datatype:"json",
	          async:false,
	          success: function(data){ 
		          	data=eval("(" +data+")");
					 if(data!=null&&data!=""){
						for(var i=0;i<data.length;i++){
							var objhtml=listhtml;
							var goods_id="-",goods_name="-",goods_price="-",order_num="-",goods_momey="-",all_momey="0.0";
							if(data[i].goods_id!=null&&data[i].goods_id!=""){
								goods_id=data[i].goods_id;
							}
							if(data[i].goods_name!=null&&data[i].goods_name!=""){
								goods_name=data[i].goods_name;
							}
							if(data[i].goods_price!=null&&data[i].goods_price!=""){
								goods_price=data[i].goods_price;
							}
							if(data[i].order_num!=null&&data[i].order_num!=""){
								order_num=data[i].order_num;
							}
							if(data[i].goods_momey!=null&&data[i].goods_momey!=""){
								goods_momey=data[i].goods_momey;
							}
							if(objhtml.indexOf("${goods_id}")>-1){
							  objhtml=objhtml.replace("${goods_id}",goods_id);
							}
							if(objhtml.indexOf("${goods_name}")>-1){
							  objhtml=objhtml.replace("${goods_name}",goods_name);
							}
							if(objhtml.indexOf("${goods_price}")>-1){
							  objhtml=objhtml.replace("${goods_price}",goods_price);
							}
							if(objhtml.indexOf("${buy_number}")>-1){
							  objhtml=objhtml.replace("${buy_number}",order_num);
							}
							if(objhtml.indexOf("${goods_momey}")>-1){
							  objhtml=objhtml.replace("${goods_momey}",goods_momey);
							}else{
								objhtml=objhtml.replace("${goods_momey}",goods_momey);
							}
						    list_content+="<tr>"+objhtml+"</tr>";
					  }
				  }
	          }	 
		});   
		$("#"+html_table_id).append(list_content); 	
		$("#"+html_tr_id).remove(); 	
	}
}
