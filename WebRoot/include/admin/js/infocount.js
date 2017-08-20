
  var salestime="";
  var salestimes=[];
  var salescount="";
  var salescounts=[];
  var salesamount="";
  var salesamounts=[];
  var salesarea="";
  var salesareas=[];
  var salescat="";
  var salescats=[];
  var salesmember="";
  var salesmembers=[];
  var starttime_s="";
  var endtime_s="";
  var count_type="";
  var firstAccess="";
  
  var search_area_attr ="";
  var search_cat_attr="";
  
  var linesalestime="";
  var linesalestimes=[];
  var linesalescount="";
  var linesalescounts=[];
  var linesalesamount="";
  var linesalesamounts=[];
  

  
  /**
  * 销售统计：按时间
  */

  function countsalesbytime(){
       
      starttime_s=$("#txtstartDate").val();
	  endtime_s=$("#txtendDate").val();
	  count_type=$("#count_type").val();
	  firstAccess=$("#firstAccess").val();
	  
	  getCountSalesByTime(starttime_s,endtime_s,count_type,firstAccess);
	  columnbasic('container_columnamount','订单成交额','基于订单数据',salestimes,'订单成交额（元）',' 元','成交额',salesamounts);
	  columnbasic('container_columncount','订单成交量','基于订单数据',salestimes,'订单成交量（笔）',' 笔','成交量',salescounts);
  }
  
  function linesalesbytime(){
      starttime_s=$("#txtstartDate").val();
	  endtime_s=$("#txtendDate").val();
	  count_type=$("#count_type").val();
	  firstAccess=$("#firstAccess").val();
	  
	  getLineCountSalesByTime(starttime_s,endtime_s,count_type,firstAccess);
	  linebasic('container_lineamount','订单成交额','基于订单数据',linesalestimes,'订单成交额（元）',' 元','成交额',linesalesamounts);
	  linebasic('container_linecount','订单成交量','基于订单数据',linesalestimes,'订单成交量（笔）',' 笔','成交量',linesalescounts);
  }
  
  /**
  * 销售统计：按地区
  */
  function countsalesbyarea(){
      
      search_area_attr=$("#area_attr").val();
	  getCountSalesByArea(search_area_attr);
	  columnbasic('container_columnamount','订单成交额','基于订单数据',salesareas,'成交额（元）',' 元','成交额',salesamounts);
	  columnbasic('container_columncount','订单成交量','基于订单数据',salesareas,'成交量（笔）',' 笔','成交量',salescounts);
  
  }
  
   /**
  * 销售统计：按商品种类
  */
  function countsalesbycat(){
      
      starttime_s=$("#txtstartDate").val();
	  endtime_s=$("#txtendDate").val();
	  firstAccess=$("#firstAccess").val(); 
      search_cat_attr=$("#search_cat_attr").val();
	  
	  getCountSalesByCat(starttime_s,endtime_s,search_cat_attr,firstAccess);
	  columnbasic('container_columnamount','订单成交额','基于订单数据',salescats,'订单成交额（元）',' 元','成交额',salesamounts);
	  columnbasic('container_columncount','订单成交量','基于订单数据',salescats,'订单成交量（笔）',' 笔','成交量',salescounts);

  }
  
  /**
  * 销售统计：按会员购买量排序统计
  */
  function countsalesbypurchases(){
      starttime_s=$("#txtstartDate").val();
	  endtime_s=$("#txtendDate").val();
	  count_type=$("#count_type").val();
	  firstAccess=$("#firstAccess").val();
	  getCountSalesByPurchases(starttime_s,endtime_s,count_type,firstAccess);
	  columnbasic('container_columngoodscount','会员购买量','',salesmembers,'购买量（个）',' 个','购买量',salescounts);
  
  
  }
  
  
   /**
  * 销售统计：按时间
  */
  function getCountSalesByTime(timePara1,timePara2,typePara,flagAccess){
    
     $.ajax({    
        type: "post",    	     
        url: "/infocount!getCountSalesByTime.action?&count_type="+typePara+"&starttime_s="+timePara1+"&endtime_s="+timePara2+"&firstAccess="+flagAccess,       
        datatype:"json",
        async:false,
        success: function(data){	
          var amountlist=eval(data);
          
      	for(var i=0;i<amountlist.length;i++){
      	  
      	   salestime+=amountlist[i].sales_time;
      	   salestime+=",";
      	    
      	   salesamount+=amountlist[i].order_total_amount;
      	   salesamount+=",";
      	   
      	   salescount+=amountlist[i].order_count;
      	   salescount+=",";
      	}
      	salestime = salestime.substring(0,salestime.length-1);
      	salestimes=salestime.split(',');  	
      	
      	salescount = salescount.substring(0,salescount.length-1);
      	salescounts=salescount.split(',');
      	
      	salesamount = salesamount.substring(0,salesamount.length-1);
      	salesamounts=salesamount.split(',');
      	
      	/********将字符串数组转化成数字数组:订单成交金额***********/
      	var sales_amounts = [salesamounts.length];   
      	for(var i=0;i<salesamounts.length;i++){
           sales_amounts[i]=Number(salesamounts[i]);
        }   
      	salesamounts = sales_amounts;   
      	
      	/********将字符串数组转化成数字数组：订单成交数量***********/
      	var sales_counts = [salescounts.length];   
      	for(var i=0;i<salescounts.length;i++){
           sales_counts[i]=Number(salescounts[i]);
        }   
      	salescounts = sales_counts;   
       
       }
       
	  });
    
  }
  
   /**
  * 销售统计：按时间 折线图
  */
  function getLineCountSalesByTime(timePara1,timePara2,typePara,flagAccess){
    
     $.ajax({    
        type: "post",    	     
        url: "/infocount!getCountSalesByTime.action?&count_type="+typePara+"&starttime_s="+timePara1+"&endtime_s="+timePara2+"&firstAccess="+flagAccess,       
        datatype:"json",
        async:false,
        success: function(data){	
          var amountlist=eval(data);
          
      	for(var i=0;i<amountlist.length;i++){
      	  
      	   linesalestime+=amountlist[i].sales_time;
      	   linesalestime+=",";
      	    
      	   linesalesamount+=amountlist[i].order_total_amount;
      	   linesalesamount+=",";
      	   
      	   linesalescount+=amountlist[i].order_count;
      	   linesalescount+=",";
      	}
      	linesalestime = linesalestime.substring(0,linesalestime.length-1);
      	linesalestimes=linesalestime.split(',');  	
      	
      	linesalescount = linesalescount.substring(0,linesalescount.length-1);
      	linesalescounts=linesalescount.split(',');
      	
      	linesalesamount = linesalesamount.substring(0,linesalesamount.length-1);
      	linesalesamounts=linesalesamount.split(',');
      	
      	/********将字符串数组转化成数字数组:订单成交金额***********/
      	var linesales_amounts = [linesalesamounts.length];   
      	for(var i=0;i<linesalesamounts.length;i++){
           linesales_amounts[i]=Number(linesalesamounts[i]);
        }   
      	linesalesamounts = linesales_amounts;   
      	
      	/********将字符串数组转化成数字数组：订单成交数量***********/
      	var linesales_counts = [linesalescounts.length];   
      	for(var i=0;i<linesalescounts.length;i++){
           linesales_counts[i]=Number(linesalescounts[i]);
        }   
      	linesalescounts = linesales_counts;   
       
       }
       
	  });
    
  }
  
  
  
   /**
  * 销售统计：按地区
  */
  function getCountSalesByArea(areaPara){
    
     $.ajax({    
        type: "post",    	     
        url: "/infocount!getCountSalesByArea.action?area_attr_s="+areaPara,       
        datatype:"json",
        async:false,
        success: function(data){	
          var amountlist=eval(data);
          
      	for(var i=0;i<amountlist.length;i++){
      	    
      	   salesamount+=amountlist[i].order_total_amount;
      	   salesamount+=",";
      	   
      	   salescount+=amountlist[i].order_count;
      	   salescount+=",";
      	}
      	
      	salescount = salescount.substring(0,salescount.length-1);
      	salescounts=salescount.split(',');
      	
      	salesamount = salesamount.substring(0,salesamount.length-1);
      	salesamounts=salesamount.split(',');
      	
      	/********将字符串数组转化成数字数组:订单成交金额***********/
      	var sales_amounts = [salesamounts.length];   
      	for(var i=0;i<salesamounts.length;i++){
           sales_amounts[i]=Number(salesamounts[i]);
        }   
      	salesamounts = sales_amounts;   
      	
      	/********将字符串数组转化成数字数组：订单成交数量***********/
      	var sales_counts = [salescounts.length];   
      	for(var i=0;i<salescounts.length;i++){
           sales_counts[i]=Number(salescounts[i]);
        }   
      	salescounts = sales_counts;   
       
       }
       
	  });
	  
	  $.ajax({    
        type: "post",    	     
        url: "/infocount!getSalesByArea.action?area_attr_s="+areaPara,       
        datatype:"json",
        async:false,
        success: function(data){	
          var amountlist=eval(data);
          
      	for(var i=0;i<amountlist.length;i++){
      	  
      	   salesarea+=amountlist[i].area_name;
      	   salesarea+=",";
      	    
      	}
      	salesarea = salesarea.substring(0,salesarea.length-1);
      	salesareas=salesarea.split(',');  	 
       
       }
       
	  });
	  
  }
  
   /**
  * 销售统计：按商品分类
  */
  function getCountSalesByCat(timePara1,timePara2,catPara,flagAccess){
    
     $.ajax({    
        type: "post",    	     
        url: "/infocount!getCountSalesByCat.action?starttime_s="+timePara1+"&endtime_s="+timePara2+"&cat_attr_s="+catPara+"&flagAccess="+flagAccess,       
        datatype:"json",
        async:false,
        success: function(data){	
          var amountlist=eval(data);
          
      	for(var i=0;i<amountlist.length;i++){
      	    
      	   salesamount+=amountlist[i].order_total_amount;
      	   salesamount+=",";
      	   
      	   salescount+=amountlist[i].order_count;
      	   salescount+=",";
      	}
      	
      	salescount = salescount.substring(0,salescount.length-1);
      	salescounts=salescount.split(',');
      	
      	salesamount = salesamount.substring(0,salesamount.length-1);
      	salesamounts=salesamount.split(',');
      	
      	/********将字符串数组转化成数字数组:订单成交金额***********/
      	var sales_amounts = [salesamounts.length];   
      	for(var i=0;i<salesamounts.length;i++){
           sales_amounts[i]=Number(salesamounts[i]);
        }   
      	salesamounts = sales_amounts;   
      	
      	/********将字符串数组转化成数字数组：订单成交数量***********/
      	var sales_counts = [salescounts.length];   
      	for(var i=0;i<salescounts.length;i++){
           sales_counts[i]=Number(salescounts[i]);
        }   
      	salescounts = sales_counts;   
       
       }
       
	  });
	  
	  $.ajax({    
        type: "post",    	     
        url: "/infocount!getSalesByCat.action?cat_attr_s="+catPara,       
        datatype:"json",
        async:false,
        success: function(data){	
          var amountlist=eval(data);
          
      	for(var i=0;i<amountlist.length;i++){
      	  
      	   salescat+=amountlist[i].cat_name;
      	   salescat+=",";
      	    
      	}
      	salescat = salescat.substring(0,salescat.length-1);
      	salescats=salescat.split(',');  	
      	
        }
        
	  });
    
  }
  
     /**
  * 销售统计：按地区
  */
  function getCountSalesByPurchases(timePara1,timePara2,typePara,flagAccess){
    
     $.ajax({    
        type: "post",    	     
        url: "/infocount!getCountSalesByPurchases.action?&count_type="+typePara+"&starttime_s="+timePara1+"&endtime_s="+timePara2+"&firstAccess="+flagAccess,       
        datatype:"json",
        async:false,
        success: function(data){	
          var amountlist=eval(data);
         
      	for(var i=0;i<amountlist.length;i++){
      	  
      	   salesmember+=amountlist[i].user_name;
      	   salesmember+=",";
      	   
      	   salescount+=amountlist[i].mem_goods_num;
      	   salescount+=",";
      	}
      	salesmember = salesmember.substring(0,salesmember.length-1);
      	salesmembers=salesmember.split(',');  	
      	
      	salescount = salescount.substring(0,salescount.length-1);
      	salescounts=salescount.split(',');
      	
      	/********将字符串数组转化成数字数组：订单成交数量***********/
      	var sales_counts = [salescounts.length];   
      	for(var i=0;i<salescounts.length;i++){
           sales_counts[i]=Number(salescounts[i]);
        }   
      	salescounts = sales_counts;   
       
       }
       
	  });
    
  }
  

function changeColumnChart(paraChart){
   if(paraChart=='column_sales'){
     $("#container_columncount").hide();
     $("#container_columnamount").show();
   }else if(paraChart=='column_count'){
     $("#container_columnamount").hide();  
     $("#container_columncount").show();
   }
}

function changeLineChart(paraChart){
   if(paraChart=='line_sales'){
     $("#container_linecount").hide();
     $("#container_lineamount").show();
   }else if(paraChart=='line_count'){
     $("#container_lineamount").hide();  
     $("#container_linecount").show();
   }
}

//
function changeChartType(chartType){
    if(chartType=='poll'){
     $("#line_ul").hide();
     $("#column_ul").show();
     $("#container_columnamount").show();
     $("#container_columncount").hide();
     $("#container_lineamount").hide();
     $("#container_linecount").hide();
   }else if(chartType=='zline'){
     $("#column_ul").hide();  
     $("#line_ul").show();
     $("#container_columnamount").hide();
     $("#container_columncount").hide();
     $("#container_lineamount").show();
     $("#container_linecount").hide();
   }
}