var ScrollWin = {
	w3c : document.getElementById,
	iex : document.all,
	scrollLoop : false,
	scrollInterval : null, // setInterval id
	currentBlock : null,   // object reference
	getWindowHeight : function(){
	if(this.iex) return (document.documentElement.clientHeight) ?
		document.documentElement.clientHeight : document.body.clientHeight;
	else return window.innerHeight;
	},
	getScrollLeft : function(){
	if(this.iex) return (document.documentElement.scrollLeft) ?
		document.documentElement.scrollLeft : document.body.scrollLeft;
	else return window.pageXOffset;
	},
	getScrollTop : function(){
	if(this.iex) return (document.documentElement.scrollTop) ?
		document.documentElement.scrollTop : document.body.scrollTop;
	else return window.pageYOffset;
	},
	getElementYpos : function(el){
		var y = 0;
		if(el != null)
		while(el.offsetParent){
			y += el.offsetTop
			el = el.offsetParent;
		}
		return y;
	},
	scroll : function(num){
		if(!this.w3c){
			location.href = "#"+this.anchorName+num;
			return;
		}
		if(this.scrollLoop){
			clearInterval(this.scrollInterval);
			this.scrollLoop = false;
			this.scrollInterval = null;
		}
		//this.currentBlock.className = this.offClassName;
		this.currentBlock = document.getElementById(this.blockName+num);
		//this.currentBlock.className = this.onClassName;
		var doc = document.getElementById(this.containerName);
		var documentHeight = this.getElementYpos(doc) + doc.offsetHeight;
		var windowHeight = this.getWindowHeight();
		var ypos = this.getElementYpos(this.currentBlock);
		if(ypos > documentHeight - windowHeight) ypos = documentHeight - windowHeight;
			this.scrollTo(0,ypos-100);
	},
	scrollTo : function(x,y){
		if(this.scrollLoop){
			var left = this.getScrollLeft();
			var top = this.getScrollTop();
		if(Math.abs(left-x) <= 1 && Math.abs(top-y) <= 1){
			window.scrollTo(x,y);
		clearInterval(this.scrollInterval);
			this.scrollLoop = false;
			this.scrollInterval = null;
		}else{
			window.scrollTo(left+(x-left)/10, top+(y-top)/10);
		}
		}else{
			this.scrollInterval = setInterval("ScrollWin.scrollTo("+x+","+y+")",20);
			this.scrollLoop = true;
		}
	}
};
ScrollWin.containerName = "spikeTab";
ScrollWin.anchorName    = "anchor";
ScrollWin.blockName     = "block";
//ScrollWin.onClassName   = "active";
//ScrollWin.offClassName  = "visited";