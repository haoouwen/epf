var t = 91;
var timerID = null;  
var timerRunning = false; 
$(document).ready(function(){
	var trueToAdjus=$("#trueToAdjus").val();
	var ssuccessToAdjus=$("#ssuccessToAdjus").val();
	if(trueToAdjus!=null&&trueToAdjus=="true"){
		$("#zjgl").tipshow({p_width:"1080", p_height:"400", pop_title:"当前位置:财务管理 > 财务管理 > 运营商资金管理 "});
    }else{
    	if(ssuccessToAdjus!=null&&ssuccessToAdjus=="0"){
		jAlert("成功调整运营商资金！3秒后自动跳转到资金流查看","系统提示");
    	window.setInterval("toFundhistory()",1000*3);
		}else{
	    	jAlert("非法操作，3秒后系统自动返回！","系统提示");
	    	window.setInterval("toOut()",1000*3);
    	}
    }
    $("#zjgl").mouseleave(function(){
   		 stopclock(timerID);//移开时关闭定时器
 		jAlert("异常操作！如:鼠标超出红框，点击右键，金币格式不正确。请重新进入！3秒后自动退出","系统提示");
 		window.setInterval("toOut()",1000*3);
    });
	document.oncontextmenu=function(){
		jAlert("禁止鼠标右键菜单！","系统提示");
		return false;
	}
	document.onkeydown=function(e){
	e=e||event;
	//屏蔽向左的方向键
	if(e.keyCode==37){
		jAlert("禁止向左的方向键！","系统提示");
		return false;
	}
	//屏蔽向上的方向键
	if(e.keyCode==38){
		jAlert("禁止向上的方向键！","系统提示");
		return false;
	}
	//屏蔽向右的方向键
	if(e.keyCode==39){
		jAlert("禁止向右的方向键！","系统提示");
		return false;
	}
	//屏蔽向下的方向键
	if(e.keyCode==40){
		jAlert("禁止向下的方向键！","系统提示");
		return false;
	}
	//屏蔽F5刷新键
	if(e.keyCode==116){
		alert('禁止F5刷新!');
		return false;
	}
	//屏蔽Alt+☜
	if((e.altKey)&&(e.keyCode==37)){
		jAlert("禁止后退！","系统提示");
		return false;
	}
	//屏蔽Alt+→ 
	if((e.altKey)&&(e.keyCode==38)){
		jAlert("禁止前进！","系统提示");
		return false;
	}
}
	if(timerRunning) { 
		clearTimeout(timerID); 
	}
	timerID= fun(90000);
		
});

//function isOut(){
//		stopclock(timerID);//移开时关闭定时器
//		jAlert("已超出红框，请从新进入！","系统提示");
//		window.setInterval("toOut()",1000*3);
// }
function fun(difftime){
		var nd = 1000*24*60*60;
	    var nh = 1000*60*60;
	    var nm = 1000*60;
	    var ns = 1000;
	    var difmin = parseInt(difftime%nd%nh/nm);
	    var difsec = parseInt(difftime%nd%nh%nm/ns);
		difftime = difftime - 1000;
		timerID = setTimeout('fun(' + difftime + ')',1000);  
		timerRunning = true;  
		difmin = "0"+difmin;
		if(difftime<3000){
		    stopclock();
        	jAlert("时间到了，3秒后请重新进入！","系统提示");
        	window.setInterval("toOut()",1000*3);
		} 
		temp= difmin + '分' + difsec + '秒';
		$("#time").html(temp); 
		
		return timerID;
}

function stopclock(timerID) { 
		if(timerRunning)  
			clearTimeout(timerID);  
		timerRunning = false;  
} 

function toOut(){
	location.href = "/admin_Capitalmanagement_list.action";
}
function toFundhistory(){
	location.href = "/admin_Fundhistory_list.action";
}
function closec(){
	$("#zjgl").ccover();
}
