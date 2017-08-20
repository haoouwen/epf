var timerID = null;  
var timerRunning = false;  
//秒杀页面定时器
function showtime() {  
	Today = new Date();  
	var NowMinute = Today.getMinutes();   
	var NowSecond = Today.getSeconds();  
	Today = null;  
	Minuteleft = 59 - NowMinute  
	Secondleft = 59 - NowSecond   
	timerID = setTimeout("showtime()",1000);  
	timerRunning = true;  
	if(Minuteleft <10 ){
		Minuteleft = "0"+Minuteleft;
	}
	if(Secondleft <10 ){
		Secondleft = "0"+Secondleft;
	}
	if (Minuteleft < 1 && Secondleft < 1){   
		stopclock();
	}
	if(Minuteleft==0 && Secondleft==0)
	Temp='本场已结束<b>00</b>时<b>'+Minuteleft+'</b>分<b>'+Secondleft+'</b>秒';
	else  
	Temp='离结束仅剩<b>00</b>时<b>'+Minuteleft+'</b>分<b>'+Secondleft+'</b>秒';
	$("#leftTime").html(Temp);
}  
//关闭定时器
function stopclock() {
	if(timerRunning)  
	clearTimeout(timerID);  
	timerRunning = false;  
}  
//打开计时器
function startclock() {  
	stopclock();  
	showtime();  
} 

function groupshowtime(difftime){
  Today = new Date(); 
    var nd = 1000*24*60*60;
    var nh = 1000*60*60;
    var nm = 1000*60;
    var ns = 1000;
    var diffday = parseInt(difftime/nd);
    var diffhour = parseInt(difftime%nd/nh);
    var difmin = parseInt(difftime%nd%nh/nm);
    var difsec = parseInt(difftime%nd%nh%nm/ns);
	Today = null;  
	difftime = difftime - 1000;
	ggtimerID = setTimeout('groupshowtime(' + difftime + ')',1000);  
	if(diffday <10 ){
		diffday = "0"+diffday;
	}
	if(diffhour <10 ){
		diffhour = "0"+diffhour;
	}
	if(difmin <10 ){
		difmin = "0"+difmin;
	}
	if(difsec <10 ){
		difsec = "0"+difsec;
	}
	
	if (diffday <1 && diffhour < 1 && difmin < 1 && difsec < 1){   
		ggstopclock();
	}	
	if(difftime<0){
	    ggstopclock();
	    return;
	}  
	temp='离结束仅剩 :<font color="red">' + diffday + '</font><span>天</span><font color="red">'+ diffhour + '</font><span>时</span><font color="red">' + difmin + '</font><span>分</span><font color="red">' + difsec + '</font><span>秒</span>';
	$("#ggleftTime").html(temp);
}

//团购关闭定时器
function ggstopclock() { 
	clearTimeout(ggtimerID); 
	$("#subg").attr("onclick", "");
	$("#subgimg").attr("src", "/malltemplate/bmall/images/overtgjsbut_03.gif");
	$("#ggleftTime").html("");
}  
  //秒杀详细页面倒计时
function spikeEnd(difftime){
  Today = new Date(); 
    var nd = 1000*24*60*60;
    var nh = 1000*60*60;
    var nm = 1000*60;
    var ns = 1000;
    var diffday = parseInt(difftime/nd);
    var diffhour = parseInt(difftime%nd/nh);
    var difmin = parseInt(difftime%nd%nh/nm);
    var difsec = parseInt(difftime%nd%nh%nm/ns);
	Today = null;  
	difftime = difftime - 1000;
	sketimerID = setTimeout('spikeEnd(' + difftime + ')',1000);  
	if(diffday <10 ){
		diffday = "0"+diffday;
	}
	if(diffhour <10 ){
		diffhour = "0"+diffhour;
	}
	if(difmin <10 ){
		difmin = "0"+difmin;
	}
	if(difsec <10 ){
		difsec = "0"+difsec;
	}
	
	if (diffday <1 && diffhour < 1 && difmin < 1 && difsec < 1){   
		skestopclock();
	}	
	if(difftime<0){
	    skestopclock();
	    return;
	} 
	    temp=diffhour+':'+ difmin + ':' + difsec  ;
	$("#skillend").html(temp);
}
//关闭离秒杀结束定时器
function skestopclock() {
	clearTimeout(sketimerID);  
}

function spikeStart(difftime){
  Today = new Date(); 
    var nd = 1000*24*60*60;
    var nh = 1000*60*60;
    var nm = 1000*60;
    var ns = 1000;
    var diffday = parseInt(difftime/nd);
    var diffhour = parseInt(difftime%nd/nh);
    var difmin = parseInt(difftime%nd%nh/nm);
    var difsec = parseInt(difftime%nd%nh%nm/ns);
	Today = null;  
	difftime = difftime - 1000;
	skstimerID = setTimeout('spikeStart(' + difftime + ')',1000);  
	if(diffday <10 ){
		diffday = "0"+diffday;
	}
	if(diffhour <10 ){
		diffhour = "0"+diffhour;
	}
	if(difmin <10 ){
		difmin = "0"+difmin;
	}
	if(difsec <10 ){
		difsec = difsec;
	}
	
	if (diffday <1 && diffhour < 1 && difmin < 1 && difsec < 1){   
		sksstopclock();
	}	
	if(difftime<0){
	    sksstopclock();
	    return;
	}  
	temp=diffhour+':'+ difmin + ':' + difsec ;
	$("#skillstart").html(temp);
}
//关闭离秒开始定时器
function sksstopclock() {
	clearTimeout(skstimerID);  
}
//离商品品牌热卖结束时间
function hotEnd(difftime){
  Today = new Date(); 
    var nd = 1000*24*60*60;
    var nh = 1000*60*60;
    var nm = 1000*60;
    var ns = 1000;
    var diffday = parseInt(difftime/nd);
    var diffhour = parseInt(difftime%nd/nh);
    var difmin = parseInt(difftime%nd%nh/nm);
    var difsec = parseInt(difftime%nd%nh%nm/ns);
	Today = null;  
	difftime = difftime - 1000;
	sketimerID = setTimeout('spikeEnd(' + difftime + ')',1000);  
	if(diffday <10 ){
		diffday = "0"+diffday;
	}
	if(diffhour <10 ){
		diffhour = "0"+diffhour;
	}
	if(difmin <10 ){
		difmin = "0"+difmin;
	}
	if(difsec <10 ){
		difsec = "0"+difsec;
	}
	
	if (diffday <1 && diffhour < 1 && difmin < 1 && difsec < 1){   
		skestopclock();
	}	
	if(difftime<0){
	    skestopclock();
	    return;
	} 
	temp=diffhour+':'+ difmin + ':' + difsec  ;
	$("#skillend").html(temp);
}
//关闭离秒杀结束定时器
function hotStopClock() {
	clearTimeout(sketimerID);  
}
//手机秒杀定时器
function appindextime(difftime){
  Today = new Date(); 
    var nd = 1000*24*60*60;
    var nh = 1000*60*60;
    var nm = 1000*60;
    var ns = 1000;
    var diffday = parseInt(difftime/nd);
    var diffhour = parseInt(difftime%nd/nh);
    var difmin = parseInt(difftime%nd%nh/nm);
    var difsec = parseInt(difftime%nd%nh%nm/ns);
	Today = null;  
	difftime = difftime - 1000;
	ggtimerID = setTimeout('appindextime(' + difftime + ')',1000);  
	if(diffday <10 ){
		diffday = "0"+diffday;
	}
	if(diffhour <10 ){
		diffhour = "0"+diffhour;
	}
	if(difmin <10 ){
		difmin = "0"+difmin;
	}
	if(difsec <10 ){
		difsec = "0"+difsec;
	}
	
	if (diffday <1 && diffhour < 1 && difmin < 1 && difsec < 1){   
		appindextimeclock();
	}	
	if(difftime<0){
	    appindextimeclock();
	    return;
	}  
	temp='<span>'+ diffday +'</span>天<span>'+ diffhour + '</span>时<span>' + difmin + '</span>分<span>' + difsec + '</span>秒';
	$("#ggleftTime").html(temp);
}

//app手机秒杀关闭定时器
function appindextimeclock() { 
	clearTimeout(ggtimerID); 
	$("#ggleftTime").html("<span>0</span>天<span>0</span>时<span>0</span>分<span>0</span>秒");
}  

//列表手机秒杀定时器
function appindextimelist(difftime,mytid){
  Today = new Date(); 
    var nd = 1000*24*60*60;
    var nh = 1000*60*60;
    var nm = 1000*60;
    var ns = 1000;
    var diffday = parseInt(difftime/nd);
    var diffhour = parseInt(difftime%nd/nh);
    var difmin = parseInt(difftime%nd%nh/nm);
    var difsec = parseInt(difftime%nd%nh%nm/ns);
	Today = null;  
	difftime = difftime - 1000;
	ggtimerID = setTimeout('appindextimelist(' + difftime + ',\''+mytid+'\')',1000);  
	if(diffday <10 ){
		diffday = "0"+diffday;
	}
	if(diffhour <10 ){
		diffhour = "0"+diffhour;
	}
	if(difmin <10 ){
		difmin = "0"+difmin;
	}
	if(difsec <10 ){
		difsec = "0"+difsec;
	}
	
	if (diffday <1 && diffhour < 1 && difmin < 1 && difsec < 1){   
		appindextimelistclock(mytid);
	}	
	if(difftime<0){
	    appindextimelistclock(mytid);
	    return;
	}  
	temp='<b>'+ diffday +'</b>天<b>'+ diffhour + '</b>时<b>' + difmin + '</b>分<b>' + difsec + '</b>秒';
	$("#"+mytid).html(temp);
}

//app手机秒杀关闭定时器
function appindextimelistclock(tipid) { 
	clearTimeout(ggtimerID); 
	$("#"+tipid).html("<b>0</b>天<b>0</b>时<b>0</b>分<b>0</b>秒");
}  












