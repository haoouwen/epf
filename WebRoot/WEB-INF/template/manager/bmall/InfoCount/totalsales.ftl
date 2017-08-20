<html>
<head>
	<title>销售额总览</title>
	<script type="text/javascript" src="/include/common/js/common.js"></script>
    <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script>
      <!--制作图像必备的canvas插件  这个是将canvas标签转换为IE认识的vml-->  
            <!--[if IE]><script src="/include/common/js/excanvas-compressed.js" type="text/javascript" ></script><![endif]-->  
            <script src="/include/common/js/jquery-1.4.4.min.js" type="text/javascript"></script>  
             <script src="/include/admin/js/infocount.js" type="text/javascript"></script>  
             <script src="/include/components/print/LodopFuncs.js" type="text/javascript"></script> 
     <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 

	 
           
            <!--jQuery图标插件-->  
            <script src="/include/common/js/jquery.jqchart.js" type="text/javascript" charset="utf-8"></script>   
            <script type="text/javascript">  
                
                function submittotal(){
                   $("#Formtotal").submit();
                }
                
            </script>  
            <script language="javascript" type="text/javascript"> 
				var LODOP; //声明为全局变量 
				function CreatePage(intChartType,strTableHTML) {
					LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM')); 
					LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_图表显示打印二");
					LODOP.ADD_PRINT_CHART(0,0,768,428,intChartType,strTableHTML);
				};
				function myPreview7() {		
					CreatePage(7,document.getElementById("Div01").innerHTML);//甘特图的代码是7
					LODOP.SET_PRINT_STYLEA(1,"ChartStyle","33--0-3-01---------");
					LODOP.PREVIEW();
				};
				function myDesign7() {		
					CreatePage(7,document.getElementById("Div01").innerHTML);
					LODOP.SET_PRINT_STYLEA(1,"ChartStyle","33--0-3-01---------");		
					LODOP.PRINT_DESIGN();
				};	
				function myPreview8() {		
					CreatePage(8,document.getElementById("Div02").innerHTML);//箭头图的代码是8
					LODOP.PREVIEW();
				};	
				function myPreview9() {		
					CreatePage(9,document.getElementById("Div03").innerHTML);//气泡图的代码是9
					LODOP.PREVIEW();
				};	
				function myPreview10() {		
					CreatePage(10,document.getElementById("Div04").innerHTML);//几何图的代码是10
					LODOP.PREVIEW();
			//		LODOP.PRINT_DESIGN();
				};			
						
			</script> 
</head>
<body>
	<@s.form action="/bmall_InfoCount_totalsales.action" id="Formtotal" method="post" validate="true" >
	   	<@s.hidden  id="sevendatatime"  name="sevendatatime" />
	   	<@s.hidden  id="sevengoodsorder"  name="sevengoodsorder" />
	   	<@s.hidden  id="xtitle"  name="xtitle" />
	   	<@s.hidden  id="jsontotal"  name="jsontotal" />
<div class="rightside f_right">
   <div class="postion">
	统计数据</a><span>></span><a href="#">销售统计</a><span>></span><a href="#">销售额总览</a>
   </div>
   <div class="ropot">
       <h2 class="rotitle">
       		<span><td class="fthstyle1">销售额总览</td></span>
       </h2>
       
       <div class="rosearch">
           <tr>
		             <td>    
		             日期 ：        
			<@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate"  readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
				      &nbsp;至&nbsp;
		    <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate"readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
				     <input type="button" name="search" value="搜索" onclick="submittotal();"/>&nbsp;
		             </td>
		    </tr>
       </div>
       
	   
   	
   	
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
        <@s.hidden id="datenow" value="${datenow?if_exists}"/>
        
        
		 
		    
           <tr>
             <td>
	            <canvas id="canvasMyID" height="400" width="680"></canvas> 
             </td>
           </tr>
         </table>

        <div class="listbottom">
        	${pageBar?if_exists}
        </div>
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

















