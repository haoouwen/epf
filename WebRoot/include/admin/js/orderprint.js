    var LODOP; //声明为全局变量
    //购物单打印预览
	function order_print_preview() {
		CreateOneFormPage();	
		LODOP.PREVIEW();	
	};
	//购物单打印
	function order_print_print() {		
		CreateOneFormPage();
		LODOP.PRINT();
		updateState();	
	};
	//购物单打印页面设计
	function CreateOneFormPage(){
	    var order_id_s=$("#order_id_s").val();
		LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));  
		LODOP.SET_PRINT_STYLE("fontsize",18);
		LODOP.SET_PRINT_STYLE("Bold",1);
		LODOP.SET_PRINT_PAGESIZE(1,2090,1400,"");//设置纸张大小
		LODOP.ADD_PRINT_HTM(0,0,0,460,document.getElementById("form1").innerHTML);
		LODOP.SET_PRINT_STYLE("fontsize",8);
		LODOP.SET_PRINT_STYLE("Bold",0);
		LODOP.ADD_PRINT_BARCODE(30,530,250,40,"128Auto",order_id_s);
		
	};	
	//打印预览当前页面  
	function all_prn_preview(){
		LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));  
		LODOP.ADD_PRINT_HTM(20,40,700,900,document.documentElement.innerHTML);
		LODOP.PREVIEW();	
	};	
	function prn_preview_all(){
		LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));  
		LODOP.ADD_PRINT_HTM(0,0,700,1000,document.documentElement.innerHTML);
		LODOP.PREVIEW();	
	};	
	function custom_close(){
		if (confirm("您确定要关闭本页吗？")){
			window.opener=null;
			window.open('','_self');
			window.close();
		}
	}
    //批量打印购物单
function order_print_all(type) {
	    var str="";
	    if(type=="1"){
	      str="您确定批量打印购物单吗？";
	    }else{
	      str="您确定批量打印发货单吗？";
	    }		
		if (confirm(str)){
			// 值拼串
				var filedVal = '';	
				var checks = document.getElementsByName("goodsorder.order_id");
				for(var i=0;i<checks.length;i++){
					if(checks[i].checked){
						if(checks[i].value!=""){
							filedVal += checks[i].value+',';
							$.ajax({
							          type: "post",
							          url: "/goodsorder!getOrderPrint.action?order_id="+checks[i].value+"&type="+type,
							          contentType:"application/x-www-form-urlencoded;charset=utf-8", 
							          datatype:"json",
							          async:false,
							          success: function(data){ 
								         if(data!=""){
								          CreateOneFormPages(data);
								          LODOP.PRINT();
								          jNotify("打印中...");
								         } 	
							          }	 
								});   
								
								
						}
					}
				}
				// 删除最后一个字符
				filedVal = deleteLastChar(filedVal,",");
				if(filedVal==''){
					art.dialog({
						title: '系统提示',
					    content: '请至少选择一条记录!'
					});
					return false;
				}
				 //重新加载页面
		        location.reload();
		
      }
}             
	//购物单打印页面设计
	function CreateOneFormPages(Html){
		LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));  
		LODOP.SET_PRINT_STYLE("fontsize",18);
		LODOP.SET_PRINT_STYLE("Bold",1);
		LODOP.SET_PRINT_PAGESIZE(1, 0, 0, "A4") ; //A4纸张纵向打印
		LODOP.ADD_PRINT_HTM("0%", "0%", "100%", "100%", Html);
		
	};	
	
	//修改打印状态
	function updateState(){
	 var order_id=$("#order_id_s").val();
	 var type=$("#type").val();
	 if(order_id!=''&&type!=''){
		 $.ajax({
		          type: "post",
		          url: "/goodsorder!updateState.action?order_id="+order_id+"&type="+type,
		          datatype:"json",
		          async:false,
		          success: function(data){ 
		          }	 
			}); 
		 }	  
	}