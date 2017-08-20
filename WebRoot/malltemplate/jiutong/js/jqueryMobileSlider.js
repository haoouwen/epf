/*
 *bruinHu  
 *20150414
 *PC自动和点击滑动和手机端滑动
 * $(".XX").mobileSlider();
 */
(function($){
	
    $.fn.mobileSlider = function(options){
		
        var defaultSettings = {
            width: 640,        //容器宽度
            height:320,        //容器高度
            during:5000,       //间隔时间
            speed:30,          //滑动速度
			scale:1            //收缩
        }
		
        options = $.extend(defaultSettings, options);
		
        return this.each(function(){
			
            var _this = $(this), s = options;     //$this就是 $(".XX") 
            var startX = 0, startY = 0;           //触摸开始时手势横纵坐标 
            var temPos;                           //滚动元素当前位置
            var iCurr = 0;                        //当前滚动屏幕数
            var timer = null;                    //计时器
            var oMover = $("ul",_this);          //滚动元素  oMover=$("ul",$(this))等价于oMover=$(this).find("ul")  oMove= -this.find("ul");  
            var oLi = $("li", oMover);           //滚动单元
            var num = oLi.length;                //滚动屏幕数
            var oPosition = {};                  //触点位置
            var moveWidth =  $(window).width(); //屏幕的宽
			var moveHeight = 0;                  //获取高
			var oFocus  = 0;
			
			
			initDiv(); //初始化宽高
		    initButton();//初始化按钮
            autoMove();//开启定时器
			
			//缩放屏幕
            $(window).bind('resize', function(){
				moveWidth =  $(window).width(); 
				initDiv();
            });
			
			
			 //判断设备
			 if(isMobile()){
				   //移动设备
                   touchEvent();
             }else{
				//PC端
			    oFocus.hover(function(){
                    iCurr = $(this).index() - 1;
                    stopMove();
                    doMove();
                }, function(){
                    autoMove();
                }) 
			 }
			  
			
			//初始化宽高
			function initDiv(){
				//设置宽	
				if(moveWidth >= s.width){
				 moveWidth= s.width;
				 _this.width(moveWidth)
				}else if(moveWidth <= 320){
					moveWidth=300;
					 _this.width(moveWidth)
				}else{
					moveWidth=moveWidth-20;
				}
				//设置高
				moveHeight = Math.round(moveWidth/s.scale);
				
				_this.width(moveWidth).height(moveHeight);
				
				 oLi.width(_this.width()).height(_this.height());
				 
				 oMover.width(num * oLi.width());
				 
                 _this.fadeIn(300);
					
			}	
			//按钮
			function initButton(){ 
				_this.append('<div class="focus"></div>');
				var oFocusContainer = $(".focus");
				for (var i = 0; i < num; i++) {
					oFocusContainer.append("<span></span>");
				}
				oFocus = $("span", oFocusContainer);
				oFocus.first().addClass("current");
			}
			//判断是否是移动设备
            function isMobile(){
                if (navigator.userAgent.match(/Android/i) || navigator.userAgent.indexOf('iPhone') != -1 || navigator.userAgent.indexOf('iPod') != -1 || navigator.userAgent.indexOf('iPad') != -1) {
                    return true;
                }
                else {
                    return false;
                }
            }
            //自动运动
            function autoMove(){ 
				timer = setInterval(doMove, s.during);
			}
            //停止自动运动
            function stopMove(){ 
				clearInterval(timer); 
			}
			//动画效果
            function doAnimate(iTarget, fn){
                oMover.stop().animate({
					left: iTarget},
					_this.speed ,
					function(){if(fn){fn()};}
				);
            }
			//运动效果
            function doMove(){
                iCurr = iCurr >= num - 1 ? 0 : iCurr + 1;
                doAnimate(-moveWidth * iCurr);
                oFocus.eq(iCurr).addClass("current").siblings().removeClass("current");
            }
            //绑定触摸事件
            function touchEvent(){
				 // oMover jquer对象
				 //oMover[0]或oMover.get(0) DOM 对象
                oMover[0].addEventListener('touchstart', touchStartFun, false);
                oMover[0].addEventListener('touchmove', touchMoveFun, false);
                oMover[0].addEventListener('touchend', touchEndFun, false);
            }
            //获取触点位置
            function touchPos(event){
				
                var touchList = event.changedTouches;//获取接触对象列表
				var touchObject=0,tagX=0,tagY=0;
				
                for (var i = 0; i < touchList.length; i++) {
					
                    touchObject = touchList[i];//获取接触对象
                    tagX = touchObject.clientX;
                    tagY = touchObject.clientY;
                }
                oPosition.x = tagX;
                oPosition.y = tagY;
				
                return oPosition;
            }
            //触摸开始
            function touchStartFun(event){
                stopMove();
                touchPos(event);
                startX = oPosition.x;
                startY = oPosition.y;
                temPos = oMover.position().left;
            }
            //触摸移动 
            function touchMoveFun(event){
                touchPos(event);
                var moveX = oPosition.x - startX;
                var moveY = oPosition.y - startY;
                if(Math.abs(moveX) > Math.abs(moveY)){
                    event.preventDefault();
                    oMover.css({left: temPos + moveX});
                }
            }
            //触摸结束
            function touchEndFun(event){
                touchPos(event);
                var moveX = oPosition.x - startX;
                var moveY = oPosition.y - startY;
                if (Math.abs(moveX) > Math.abs(moveY)) {
					//左向右移动 前面一页
                    if (moveX > 0) {
                        iCurr--;
                        if (iCurr >= 0) {
                            var moveX = iCurr * moveWidth;
                            doAnimate(-moveX, autoMove);
                        }
                        else {
                            doAnimate(0, autoMove);
                            iCurr = 0;
                        }
                    }
					//右向左移动 后面一页
                    else {
                        iCurr++;
                        if (iCurr < num && iCurr >= 0) {
                            var moveX = iCurr * moveWidth;
                            doAnimate(-moveX, autoMove);
                        }
                        else {
                            iCurr = num - 1;
                            doAnimate(-(num - 1) * moveWidth, autoMove);
                        }
                    }
                    oFocus.eq(iCurr).addClass("current").siblings().removeClass("current");
                }
            }
         
        });
    }
})(jQuery);
