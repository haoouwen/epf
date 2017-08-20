/* 
* tableAjaxPageUI 1.0
* Copyright (c) 2012 linjunqin 
* Date: 2012-04-10 
* 使用tableAjaxPageUI来实现无刷新加载表格数据,tab页
*/ 

// 创建table和ajaxPage的一个闭包  
(function($) {  
	  // 插件的定义  
	  $.fn.mall = function(options) {   
	  	  //获取设置插件的选项	 
	      var opts = $.extend({}, $.fn.mall.defaults, options);
	      var pt=pageMethod;	      
	      //初始化方法
	      this.each(function(){    
	      	   //初始化加载数据  
	      	   var jsonData=RequestData(opts,"1");
	      	   //填充数据
	      	   showData(this,opts,jsonData)
		  }); 
	  };  
  	 
  	 //为容器填充数据
     function showData(obj,opts,jsonData){     	
     	var pt=pageMethod;
     	//清空渲染容器
     	$(obj).html("");
	 	if(opts.showStyle!=""){
     		createFloatDiv(obj,opts,jsonData);
     	}else{
     		createTable(obj,opts,jsonData);
     	}        
		pt._currentPage(obj,opts);   
		pt._prevClickPage(obj,opts);
		pt._nextClickPage(obj,opts);
 	    pt._tableUI(obj,opts);
 	    pt._trhover(obj,opts);
 	    //渲染列标题
 	    pt._thrender(obj,opts); 	
 	    //如果其它方法不为空则执行方法    
 	    if(opts.otherMethod!=null){
 	        var md=opts.otherMethod;
 	        var count=jsonData.totalCount;
 	        var md=md+"("+count+")";
 	    	eval(md);	 	    	
 	    }
     }
  
      //私有方法 
      var pageMethod={
          _currentPage:function(obj,opts){
          		$(obj).find(".pageBar").find("a").click(function(){
		       		 var jsonData=RequestData(opts,$(this).html());	       		 
		       		 showData(obj,opts,jsonData); 
		        });
          },          
          _prevClickPage:function(obj,opts){
	          	$(obj).find(".pprev").click(function(){
	  	  		      var curp=$(obj).find(".pageBar").find(".current").html();
	  	  		      var upp=parseInt(curp)-1;
	  	  		      if(upp>0){
	    	  		      var jsonData=RequestData(opts,upp);	       		 
		       		  	  showData(obj,opts,jsonData); 	  		      
	  	  		      }
				});
          }, 
	  	  _nextClickPage: function(obj,opts) {
				$(obj).find(".pnext").click(function(){
	  	  		      var curp=$(obj).find(".pageBar").find(".current").html();
	  	  		      var lastcur=$(obj).find(".pageBar").find("a:last").html();
	  	  		      var upp=parseInt(curp)+1;
	  	  		      if(upp>0 && upp<=parseInt(lastcur)){
	    	  		      var jsonData=RequestData(opts,upp);	       		 
		       		  	  showData(obj,opts,jsonData); 	  		      
	  	  		      }
				});
		 },
		 _tableUI:function(obj,opts){		 		
		 },
		 _trhover:function(obj,opts){
		  },
		  _thrender:function(obj,opts){
		  		var contain=$(obj).attr("id");		 
		  		for(var i=0;i<opts.columnModel.length;i++){
		  			 var columnWidth=opts.columnWidth;
		 	    	 if(typeof(opts.columnModel[i].width)!= "undefined" ) {
		 	    	 	 columnWidth=opts.columnModel[i].width;  	 
		 	    	 }
		 	    	 $("."+contain+"_th"+i).css("width",columnWidth);
				}
		  }
	 }
     //填充表格处理
     /*
      <!--月成交记录-->
        <div class="evaluation" id="recordId">
           <div class="recordh2">月成交记录</div>
           <!--成交记录内容-->
            <div class="record">
              <table width="100%" cellpadding="0" cellspacing="0">      
                 <tr><th width="15%">买家[登录后可见]</th><th width="30%">商品名称</th><th width="15%">商品属性</th>
                      <th width="10%">价格</th><th width="10%">购买数量</th><th width="10%">成交时间</th><th width="10%">状态</th></tr>
                 <tr>
                   <td class="bluetd">城**向</td><td>爆款 呢大衣 女 毛呢外套 冬装 韩版毛呢大衣 修身毛领呢子外套女</td>
                   <td><p>尺码：X</p><p>颜色：红色</p></td>
                   <td class="redtd">248.00</td><td>1</td><td>2014-01-15</td><td class="bluetd">成交</td>
                 </tr>
              </table>
            </div>      
           <!--翻页控件-->
           <div class="page">翻页控件</div>
        </div>
     */
     
	 function  createTable(obj,opts,jsonData){	
	 		  //添加列表头
	 		  if(opts.tabletitle!=""){
	 		//  	$(obj).append("<div class='recordh2'>"+opts.tabletitle+"</div><div class='record'>");	
	 		  }
	          //表格数据的读取与拼串
		      var tableStr="";
		      //获取容器ID
		      var contain=$(obj).attr("id");
		      tableStr+="<table  width='100%' cellpadding='0' cellspacing='0'><tr>";
		      /**生成表格头部标题开始**/
		      for(var i=0;i<opts.columnModel.length;i++){
		          if(opts.columnModel[i].header != ''){
		          tableStr+="<th class="+contain+"_th"+i+">"+opts.columnModel[i].header+"</th>"
		          }
		      }
		      tableStr+="</tr>";
		      /**生成表格头部标题结束**/
		     
		      /**如果为空数据则返回开始**/
		      if(jsonData.data.length==0){		      	 
		         $(obj).append("<div class='nulldata'>"+opts.nullData+"</div>");
		      	 return;
		      }
		      /**如果为空数据则返回结束**/
		          
		      /**渲染表格内容开始**/
		      for(var i=0;i<jsonData.data.length;i++){
		      		tableStr+="<tr>";
		      		for(var j=0;j<opts.columnModel.length;j++){	
		      		     var  columnName=opts.columnModel[j].headerIndex;   
		      		     var  columnContent="";
		      		     if(columnName!=""&&typeof(eval("jsonData.data[i]."+columnName))!="undefined"){
		      		     	 columnContent=eval("jsonData.data[i]."+columnName);
		      		     }		      		     
		      		     //截取字符长度判断
		      		     var colLen;
		      		     if(typeof(opts.columnModel[j].cLen)!="undefined"&&opts.columnModel[j].cLen!=""){
		      		     	  colLen=parseInt(opts.columnModel[j].cLen);
		      		     	  columnContent=columnContent.substring(0,colLen);
		      		     }else if(opts.cLen!=""){
		      		     	  colLen=parseInt(opts.cLen);
		      		     	  columnContent=columnContent.substring(0,colLen);
		      		     }
		      		     //设置单元格的居中形式
		      		     var tdpos=typeof(opts.columnModel[j].cpos)=="undefined"?opts.cpos:opts.columnModel[j].cpos;	      		     
		      		     //设置是否支持自定义HTML代码
		      		     var tphtmlStr="";
		      		     if(typeof(opts.columnModel[j].tphtml)!="undefined"){
		      		     	  //对多个htmls标签以|线隔开处理数据	
		      		          var htmlArr=opts.columnModel[j].tphtml.split("|");
		      		          var htmlvalArr=opts.columnModel[j].tphtmlval.split("|");
		      		          for(var k=0;k<htmlArr.length;k++){
		      		          	  var tphtml=htmlArr[k];
		      		          	  //判断字段是否需要截取长度
		      		          	  var htmlindexval="",htmlindexlen=0;
		      		          	  if(htmlvalArr[k].indexOf("?")>-1){
		      		          	  	   var htmllen = htmlvalArr[k].indexOf("?");
		      		          	  	   htmlindexval = htmlvalArr[k].substring(0,htmllen);
		      		          	  	   htmlindexlen = htmlvalArr[k].substring(htmllen+1,htmlvalArr[k].length);  	
		      		          	  }else{
		      		          	  	   htmlindexval = htmlvalArr[k]; 	
		      		          	  }		  
		      		          	  //获取数据库数据    		          	 
			      		          var tphtmlval=eval("jsonData.data[i]."+htmlindexval);
			      		          //是否截取html逗号的位置
			      		          if(tphtmlval!=null){
			      		              var commaLen=tphtmlval.toString().indexOf(",");
				      		          if(opts.iscomma==true&&commaLen>-1){
				      		          		tphtmlval=tphtmlval.substring(0,commaLen);
				      		          }	
			      		          }		
			      		          //截取列的长度
			      		          if(htmlindexlen>0){
			      		          	  tphtmlval = tphtmlval.substring(0,htmlindexlen);
			      		          }	      		         
			      		          tphtmlStr+=tphtml.replace("$",tphtmlval);
		      		          }
		      		          columnContent=tphtmlStr+columnContent;
		      		     }
		      		     //判断是否需要值转换
		      		     if(typeof(opts.columnModel[j].changeval)!="undefined"){
		      		     	 var ces = opts.columnModel[j].changeval.split(",");;
		      		     	 for(var k=0;k<ces.length;k++){
		      		     	 	 var ces_val = ces[k];
		      		     	 	 if(ces_val.indexOf(":")>0){
		      		     	 	 	 var ces_len = ces_val.indexOf(":");
		      		     	 	 	 var ces_val_index=ces_val.substring(0,ces_len);
		      		     	 	 	 var ces_val_content = ces_val.substring(ces_len+1,ces_val.length);
		      		     	 	 	 if(columnContent==ces_val_index){
		      		     	 	 	 	columnContent=ces_val_content;
		      		     	 	 	 }
		      		     	 	 }
		      		     	 }
		      		     }
		      		     if(j==0 && opts.columnModel[j].header == ""){
		      		       tableStr+="<th>"+columnContent+"</th>";
		      		     }else {
		      		       tableStr+="<td>"+columnContent+"</td>";
		      		     }
		      		}
		      		tableStr+="</tr>";
		      }
		      /**渲染表格内容结束**/		      
		      tableStr+="</table> ";	      
		      $(obj).append(tableStr);
		      //调用生成分页插件方法
		      createPageBar(obj,opts,jsonData);	
	  }
  
	  //填充分页控件,分页控件的处理
	  function createPageBar(obj,opts,jsonData){  
	  	      //初始化分页信息
		      var pBar=opts.pageBar;
		      var pSize=opts.pageSize;
		      var pCount=jsonData.totalCount;
		      var cPage=jsonData.currentPage;
		      //判断是否开启	      
		      if(pBar==true&&pCount>0){
		          var barDiv="";
		          barDiv+="<div class='pageBar'>";		          
		          if(pSize!=''){
		          	 var pageCount=parseInt(pCount/pSize);	          	 
		          	 //确定分页数是否加一
		          	 if(pageCount!=(pCount/pSize)){
		          	 	pageCount=pageCount+1;
		          	 }
		          	 var pageNum=parseInt(cPage)+5;
		          	 var prevNum=parseInt(cPage)-5;
		          	 var firstPage=1;
		          	 //判断循环开始页的位置
		          	 if(prevNum>0){
		          	 	firstPage=prevNum;
		          	 } 	          	 
		          	 //判断循环结束页的位置
		          	 if(pageNum>=pageCount){
		          	 	pageNum=pageCount;
		          	 }
		          	 //上一页
		          	 barDiv+="<span id='test' class='pprev'>"+opts.prevName+"</span>";      
		          	 //前两页处理    	 	          	 
		          	 if(cPage>7){
		          	    for(var i=1;i<3;i++){
		          	       barDiv+="<a>"+i+"</a>";
		          	       if(i==2){barDiv+="...";}
		          	    }  
	          	     }
		          	 //分页循环
		          	 for(var i=firstPage;i<pageNum+1;i++){	   	     	   
	          	    	if(cPage==i){
		          	 		barDiv+="<a class='current'>"+i+"</a>";
		          	 	}else{
		          	 		barDiv+="<a>"+i+"</a>";
		          	 	}          	     	 	
		          	 }	          	 
		          	 //后两页处理
		          	 if((parseInt(cPage)+7)<pageCount){		          	 
		          	    for(var i=(pageCount-1);i<pageCount+1;i++){
		          	       if(i==(pageCount-1)){barDiv+="...";}
		          	       barDiv+="<a>"+i+"</a>";	          	       
		          	    }  
	          	     }
		          	 //下一页
		          	 barDiv+="<span class='pnext'>"+opts.nextName+"</span>";
		          }	  
		          barDiv+="</div>";
		      	  //为容器渲染数据
		          if(opts.showStyle!=""){
		          	$(obj).append("<div class='posBar'>"+barDiv+"</div>");
		          }else{
		          	$(obj).append(barDiv);
		          }
		     }
	  }

	  // AJAX请求数据
	  function RequestData(opts,currentPage) {   
	 	  // 定义常量	
	 	  var requestData="";  
	  	  var ps=opts.pageSize;
	  	  var cp=currentPage;  
	  	  var dataUrl=opts.actionName;//请求路径
	  	  var dataPara="?ps="+ps+"&cp="+cp;//构造参数
	  	  if(dataUrl.indexOf("?")>0){
	  		  dataPara="&ps="+ps+"&cp="+cp;
	  	  }
	      // 请求数据     
	      $.ajax({  	 
	           type: "post",    	     
	           url: dataUrl+dataPara,       
	           datatype:"json",
	           async:false,
	           success: function(data){ 
	            	requestData=data;
	           }                 
	      }); 
	      var jsonData="";
	      //转换成json格式数据
	   	  jsonData=eval("("+requestData+")");  
	      return jsonData; 	  
	 };  
	  
	 // 插件的defaults默认配置  
	 $.fn.mall.defaults = {  
	  	 //列标题对象
	  	 columnModel:{},
	  	 //是否启动分页
	     pageBar:true,
	     tableEven:"#FFFFFF",
	     tableOdd:"#FFFCF5",
	     tableTrFirst:"#FFFFFF",
	     hoverColor:"#FFFFCC",	
	     //列的条数
	     pageSize:6,
	     prevName:"上一页",
	     nextName:"下一页",
	     //默认列宽
	     columnWidth:"10%",
	     //标题长度的截取设置
	     cLen:"",
	     //内容页td中位置设置
	     cpos:"center",
	     //自定义html代码
	     tphtml:null,
	     //自定义html代码所替代值
	     tphtmlval:null,
	     //是否截取html代码中逗号位置
	     iscomma:true,
	     //是否调用其它方法处理数据，方法名
	     otherMethod:null,
	     //当查询出数据为空时显示内容
	     nullData:"暂无数据",
	     //显示的样式,表格或DIV
	     showStyle:"",
	     //值转换,例(1:件,2:重量)
	     changeval:"",
	     //表格表头的名称          
	     tabletitle:""        
	  };

})(jQuery);   
// 闭包结束  

//表格参数开始
var ColumnTitle = [
             {
		header : '买家',
		headerIndex : 'buy_cust_name',
		width:"10%"			
	}, {
		header : '宝贝名称',
		headerIndex : 'goods_name',
		width:"30%",
		cLen:"30"
	}, {
		header : '出价',
		headerIndex : 'order_price',
		width:"10%"			
	}, {
		header : '购买数量',
		headerIndex : 'o_goods_num',
		width:"10%"
	}, {
		header : '交易时间',
		headerIndex : 'sale_time',
		width:"20%"
	}];
//表格参数结束

