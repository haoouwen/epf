//灯泡提示信息
window.onload = function() {
	var offsetX = 20;
	var offsetY = 20;
	//获取img所有对象
	var imgs = document.images;
	for (var i=0; i<imgs.length; i++) {
	   (function() {
		    var div;
		    var pw = getPW();
		    var ph = getPH();
		    var timeout;
	    document.images[i].onmouseover = function(e) {
	     	 div = document.createElement("DIV");
	     	 div.className = "altbox";
		     //获取img提示的信息
		     div.innerHTML = this.id;
		     div.style.position = "absolute";
	     	this.id = '';
	     document.body.appendChild(div);
		     var p = getMouse(e);
		     var x = p.x + offsetX;
		     var y = p.y + offsetY;
	     if (div.offsetWidth + x < pw) {
	      	div.style.left = x + "px" ;
	     } else {
	      div.style.left = ( pw - div.offsetWidth ) + "px";
	     }
	     if (div.offsetHeight + y < ph) {
	      	div.style.top = y + "px";
	     } else {
	      	div.style.top = (ph - div.offsetHeight) + "px";
	     }
	     (function(img) {
	      timeout = setTimeout(function() {
	       if (div) {
		        img.id = div.innerHTML;
		        div.parentNode.removeChild(div);
		        document.onmousemove = null;
	       }
	       //6000是设置提示时间
	      },6000)
	     })(this);
	  //鼠标事件
     document.onmousemove = function(e) {
	      var p = getMouse(e);
	      var x = p.x + offsetX;
	      var y = p.y + offsetY;
	      if (div.offsetWidth + x < pw) {
	       		div.style.left = x + "px" ;
	      } 
	      if (div.offsetHeight + y < ph) {
	       		div.style.top = y + "px";
	      }
     }
 }
    document.images[i].onmouseout = function(e) {
	     if (div.parentNode) {
		      this.id = div.innerHTML;
		      div.parentNode.removeChild(div);
		      document.onmousemove = null;
		      clearTimeout(timeout);
	     }
    }
   })();
}
}
	//鼠标移动上去
	function getMouse(ev) {
		var ev = ev || window.event;
			if (ev.x) {
			   nx = ev.x;
			   ny = ev.y;
			} else {
			   nx = ev.pageX;
			   ny = ev.pageY;
			}
			return {x:nx,y:ny};
	}
	//获取框
	function getPW() {
		return document.documentElement.scrollWidth;
	}
	//获取高
	function getPH() {
		return document.documentElement.clientHeight > document.documentElement.scrollHeight ? document.documentElement.clientHeight : document.documentElement.scrollHeight;
	}