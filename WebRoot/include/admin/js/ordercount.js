//曲线图  默认生成y坐标
//    title: 标题
//  clabelX: x轴显示文字如：一月 二月  三月
//  clabelX: X坐标显示的名称 如周一,周二,周三,周四,周五,周六,周天  数据格式：一维数组
//   cwidth: 报表的宽度 
//  cheight：报表的高度
//    cdata：统计的数据源 二维数组
//showDivID：显示DIV的ID
function diagramOfCurves(ctitle,clabelX,cwidth,cheight,cdata,showDivID){	 
  var scaleYmax=0;
  var scaleYgap=0;
  var scaleYmin=0;
  var sdatas=cdata.split(',');
  var maxdata=Math.max.apply(null, sdatas);
  if(parseInt(maxdata)<=10){
	  scaleYmax=10;
	  scaleYgap=1;
  }else{
   scaleYmax=maxdata+10;
   scaleYgap=scaleYmax/10
  }
  diagramOfCurvesOrg(ctitle,clabelX,scaleYmin,scaleYmax,scaleYgap,cwidth,cheight,cdata,showDivID);
}
//曲线图原始方法
//    title: 标题
//  clabelX: x轴显示文字如：一月 二月  三月
//  clabelX: X坐标显示的名称 如周一,周二,周三,周四,周五,周六,周天  数据格式：一维数组
//scaleYmax: y坐标显示的最大数字
//scaleYmin: y坐标显示最小数字
//scaleYgap: y坐标上面显示单元格的间距数字的大小，如0 100 200 300 400 
//   cwidth: 报表的宽度 
//  cheight：报表的高度
//    cdata：统计的数据源 二维数组
//showDivID：显示DIV的ID
function diagramOfCurvesOrg(ctitle,clabelX,scaleYmin,scaleYmax,scaleYgap,cwidth,cheight,cdata,showDivID){	 
  var cdatas= "[["+cdata+"]]"
  var clabelXs=clabelX.split(',');
  //个人设置样式  
  var diy_chartSetting={  
     config : {  
         title : ctitle,  
         titleLeft: 80,//图表头部说明距离画布最左端的距离  
         titleTop:20,//图表头部说明距离画布最上端的距离  
         labelX : clabelXs,  
         scaleY : {min: scaleYmin,max:scaleYmax,gap:scaleYgap},  
         width: cwidth, //有影响  
         height: cheight, //有影响  
         chartHeigth:250,  
         chartWidth:800,  
         paddingT : 50, //有影响  
         paddingR : 50, //有影响  
         paddingB : 50, //有影响  
         paddingL : 50 //有影响  
     },  
     data : eval(cdatas) 
   };  
   $("#"+showDivID).jQchart(diy_chartSetting);  
}






















































//原始配置方法
$(document).ready(function(){  
   //x坐标显示名称
     var start_time=$("#txtstartDate").val();
     var end_time=$("#txtendDate").val();
     var xtitle = $("#xtitle").val();
     var jsontotal = $("#jsontotal").val();
     jsontotal=data=eval("(" +jsontotal+")");
     var tempDate =show(start_time,end_time);
     var date_str=tempDate.split(",");
     var jsonstr="";
     for(var i=0;i<date_str.length;i++){
        var flag=true;
        for(var j=0;j<jsontotal.length;j++){
         if(date_str[i]==jsontotal[j].timetype){
           jsonstr+=jsontotal[j].totalamount+",";
           flag=false;
         }
       }
       if(flag){
        jsonstr+="0,";
       }
     }
     jsonstr=jsonstr.substring(0,jsonstr.length).split(",");
    //个人设置样式  
    var diy_chartSetting={  
       config : {  
           title : xtitle,  
           titleLeft: 80,//图表头部说明距离画布最左端的距离  
           titleTop:20,//图表头部说明距离画布最上端的距离  
           labelX : date_str,  
           scaleY : {min: 0,max:200,gap:20},  
           width: 800, //有影响  
           height: 300, //有影响  
           chartHeigth:250,  
           chartWidth:800,  
           paddingT : 50, //有影响  
           paddingR : 50, //有影响  
           paddingB : 50, //有影响  
           paddingL : 50 //有影响  
       },  
       data : [  
           jsonstr  
       ]  
     };  
     jQuery('#canvasMyID').jQchart(diy_chartSetting);  
});  



function show(value1,value2){
  var start_time=value1.replace("-","");
  var end_time=value2.replace("-","");
  start_time=start_time.replace("-","");
  end_time=end_time.replace("-","");
  var date_str=start_time+",";
  var getDate=function(str){
   var tempDate=new Date();
   var list=str.split("-");
   tempDate.setFullYear(list[0]);
   tempDate.setMonth(list[1]-1);
   tempDate.setDate(list[2]);
   return tempDate;
  }
  var date1=getDate(value1);
  var date2=getDate(value2);
  if(date1>date2){
   var tempDate=date1;
   date1=date2;
   date2=tempDate;
  }
  date1.setDate(date1.getDate()+1);
  while(!(date1.getFullYear()==date2.getFullYear()&&date1.getMonth()==date2.getMonth()&&date1.getDate()==date2.getDate())){
   var month="";
   var day="";
   if((date1.getMonth()+1)<10){
    month="0"+(date1.getMonth()+1);
   }else{
    month=date1.getMonth()+1;
   }
   if((date1.getDate())<10){
    day="0"+date1.getDate();
   }else{
    day=date1.getDate();
   }
   date_str+=date1.getFullYear()+""+month+""+day+",";
   date1.setDate(date1.getDate()+1);
  }
  date_str+=end_time;
  return date_str;
 }










