	var LODOP; //声明为全局变量
	$(document).ready(function(){
		CreatePage();
	});
	function CreatePage(){
	    var num = generateMixed(10);   
		LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM')); 
		LODOP.PRINT_INITA(0,0,760,321,"打印模版_"+num);
		eval($("#content_id").val());
		LODOP.SET_SHOW_MODE("SETUP_IN_BROWSE",1);//打印维护界面是否内嵌到网页内部
		LODOP.SET_SHOW_MODE("MESSAGE_NOSET_PROPERTY",'');//打印维护界面企图进入属性设置的警示信息
		LODOP.SET_SHOW_MODE("SETUP_ENABLESS","11111111000000");//隐藏关闭(叉)按钮
		LODOP.SET_SHOW_MODE("HIDE_PBUTTIN_SETUP",1);//隐藏打印功能
		LODOP.SET_SHOW_MODE("HIDE_DISBUTTIN_SETUP",1);//隐藏打印维护窗口那些已经禁止操作的无效按钮
		LODOP.PRINT_SETUP();
	};
	//单选按钮添加套打内容
	function Moditify(item){
		LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM')); 	
        	if ((!LODOP.GET_VALUE("ItemIsAdded",item.name))&&(item.checked)){
		LODOP.ADD_PRINT_TEXTA(item.name,56,32,175,30,item.value); } else {
		LODOP.SET_PRINT_STYLEA(item.name,'Deleted',!item.checked);}
	}	
	//把打印信息赋值给变量	
	function getProgram() {	
		LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM')); 	
		$("#content_id").val(LODOP.GET_VALUE("ProgramCodes",0));
	};
	// 把参数拼接成字符串
	function getParamString(){
		var print_param="";
		$(":input[class='print_param_class']:checked").each(function(){
			print_param+=this.name+",";
		});
		print_param=deleteLastChar(print_param,",");
	    $("#param_id").val(print_param);
	}
	//在提交的时候做些什么
	function doSomething() {
		getProgram();
		getParamString();
	};
	//背景图片处理
	function addBackgroupImg(response){
		LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM')); 
        LODOP.ADD_PRINT_SETUP_BKIMG("<img border='0' src='"+response+"'>");	
	}
	//显示代码或者是效果图
	function showEffact(o){
			if(o=="content_daima"){
				$("#content_daima").show();
				$("#content_xiaoguo").hide();
				$("#dm").attr("class","selected");
				$("#xg").attr("class","");
			}else{
				$("#content_daima").hide();
				$("#content_xiaoguo").html($("#content").val());
				$("#content_xiaoguo").show();
				$("#dm").attr("class","");
				$("#xg").attr("class","selected");
			}
	}	
//生成随机数
function generateMixed(n) {
  var chars = ['0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
  var res = "";
  for(var i = 0; i < n ; i ++) {
      var id = Math.ceil(Math.random()*35);
      res += chars[id];
  }
  return res;
}