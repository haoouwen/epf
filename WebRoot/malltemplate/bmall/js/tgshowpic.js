function getid(id){return document.getElementById(id);}
document.getElementsByClassName = function(cl){
	var retnode = [];
	var myclass = new RegExp('\\b'+cl+'\\b');
	var elem = this.getElementsByTagName('*');
	for (var i = 0; i < elem.length; i++) {
		var classes = elem[i].className;
		if (myclass.test(classes)) retnode.push(elem[i]);
	}
	return retnode;
}
var MyMar;
var speed = 1; //速度，越大越慢
var spec = 1; //每次滚动的间距, 越大滚动越快
var ipath = '/malltemplate/bmall/images/'; //图片路径
var thumbs = document.getElementsByClassName('smimg');
for (var i=0; i<thumbs.length; i++){
	thumbs[i].onmouseover = function (){getid('mainimg').src=this.getAttribute('rel');};
}
getid('gotop').onmouseover = function() {this.src = ipath + 'gotop2.gif'; MyMar=setInterval(gotop,speed);}
getid('gotop').onmouseout = function() {this.src = ipath + 'gotop.gif'; clearInterval(MyMar);}
getid('gobottom').onmouseover = function() {this.src = ipath + 'gobottom2.gif'; MyMar=setInterval(gobottom,speed);}
getid('gobottom').onmouseout = function() {this.src = ipath + 'gobottom.gif'; clearInterval(MyMar);}
function gotop() {getid('showulid').scrollTop-=spec;}
function gobottom() {getid('showulid').scrollTop+=spec;}