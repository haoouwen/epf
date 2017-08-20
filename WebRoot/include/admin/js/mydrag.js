(function($){
	$.fn.extend({
		mydrag:function(){
				var boxX = 0;		//元素在页面中的横坐标
				
				var boxY = 0;		//元素在页面中的纵坐标
				
				var dMouseX = 0;	//按下鼠标时的鼠标所在位置的横坐标
				
				var dMouseY = 0;	//按下鼠标时的鼠标所在位置的纵坐标
				
				var mMouseX = 0;    //移动鼠标时的鼠标所在位置的横坐标
				
				var mMouseY = 0;	//移动鼠标时的鼠标所在位置的纵坐标
				
				var moveLenX = 0;	//存放鼠标移动的距离，横向
				
				var moveLenY = 0;	//存放鼠标移动的距离，纵向
				
				var isMove = false;	//是否拖动层的一个输助"开关"
				
				var movingX = 0;	//移动中元素的LEFT值
				
				var movingY = 0;	//移动中元素的TOP值
				

				//可视区域最右边的值
				//var rightest = document.documentElement.clientWidth - $(".top").parent().outerWidth();
				//var rightest =800;
				var rightest = $(this).parent("div").outerWidth()-$(this).outerWidth();

				//可视区域最右边的值
				//var bottomest = document.documentElement.clientHeight - $(".top").parent().outerHeight();
				//var bottomest = 500;
				var bottomest =$(this).parent("div").outerHeight()-$(this).outerHeight();
				
				//获得移动鼠标时的鼠标所在位置的坐标
				var getMoveMouse = function(move,obj){
				mMouseX = move.pageX;
				mMouseY = move.pageY;
				//alert(obj.parent("div").html());
				}
				
				//获得元素在页面中的当前的位置
				var getbox = function(m,obj){
				boxX = obj.offset().left;
				boxY = obj.offset().top;
				}
				
				//获得鼠标按下时的坐标
				var getDownMouse = function(m){
				dMouseX = m.pageX;
				dMouseY = m.pageY;
				}
				
				//获得鼠标移动的距离值
				var getMoveLen = function(){
				moveLenX = mMouseX - dMouseX;
				moveLenY = mMouseY - dMouseY;
				}
				
				
				//鼠标UP时，关闭移动，即鼠标移动也不会让元素移动；
				$(document).mouseup(function(){
					isMove = false;
				})
				
				
				//给元素的TOP绑定事件
				//按下时获得元素的坐标和当前鼠标的坐档；
				//移动时获得移动的距离，设置元素的TOP和LEFT值；
				$(this).mousedown(function(e){
					var $this = $(this);
					getbox(e,$this);
					getDownMouse(e,$this);
					isMove = true;
				}).mousemove(function(e){
					var $this = $(this);
					getMoveMouse(e,$this);
					getMoveLen();
					if(isMove){
						$("#pos").html(rightest+"###"+movingX+"=========="+bottomest+"###"+movingY+"====="+$this.offset().left);
						//防止元素移出可视区域
						//可视区域浏览器最左边
						if(movingX<0){
							$this.css({"left":0});
							if(movingY<0){
								$this.css({"top":0});
							}else if(movingY > bottomest){
								$this.css({"top":bottomest});
							}else{
								$this.css({"top":boxY+moveLenY});
							}
						}
						//可视区域浏览器最上面
						else if(movingY<0){
							$this.css({"top":0});
							if(movingX>rightest){
								$this.css({"left":rightest});	
							}else{
								$this.css({"left":boxX+moveLenX});	
							}
						}
						//可视区域浏览器最右边
						else if(movingX > rightest){
							$this.css({"left":rightest});
							if(movingY > bottomest){
								$this.css({"top":bottomest});	
							}else{
								$this.css({"top":boxY+moveLenY});
							}
						}
						//可视区域浏览器最下边
						else if(movingY > bottomest){
							$this.css({"top":bottomest});	
							if(movingX<0){
								$this.css({"left":0});	
							}else{
								$this.css({"left":boxX+moveLenX});	
							}	
						}
						//其它情况，即在可视区域中间
						else{
							$this.css({"left":boxX+moveLenX,"top":boxY+moveLenY});	
						}
						movingX = boxX+moveLenX;
						movingY = boxY+moveLenY;
					}
			})	
		}	
	})	
})(jQuery)