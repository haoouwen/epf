(function($){
    $.fn.viewload=function(options){
        var settings={
            _before:200,//加載圖片時的提前量,0表示即時加載,如设置为200,表示滚动条在离目标位置还有200像素时就开始加载图片,可以做到不让用户察觉.
            _moreimg:0,//該插件默认在找到第一张不在可见区的图片时则不再继续加载,但当HTML容器混乱的时候可能出现可见区域内图片并没正常加载的情况,該參數意在加载N张可见区域外的图片,以避免出现这个问题
            _event:"scroll",//事件触发时才加载,click(点击),mouseover(鼠标經過),scroll(滾動條滾動),這裡的值可以查閱jquery的事件
            _show:"fadeIn",//使用何种效果载入,show(直接显示),fadeIn(淡入),slideDown(下拉),這裡的值可以查閱jquery的動畫效果
            _object:window,//值为容器對象.該插件默认在拉动浏览器滚动条时生效,这个参数可以让你在拉动某容器的滚动条时依次加载其中的图片,例如:$("div #box")
            _oimg:"img/blank.gif"//提前占位用的图片,值为某一图片路径.此图片用来占据将要加载的图片的位置,当图片加载时,该占位图则会隐藏
        };
        if(options){$.extend(settings,options);}
        var elements=this;
        if(settings._event=="scroll"){
            $(settings._object).bind("scroll",function(event){
                var counter=0;
                elements.each(function(){
                    if(!$.s_y(this,settings) && !$.s_x(this,settings)){
                        $(this).trigger("appear");
                    }else{
                        if(counter++ > settings._moreimg){
                            return false;
                        }
                    }
                });
                var temp=$.grep(elements,function(element){
                    return !element.loaded;
                });
                elements=$(temp);
            })
        }
        return this.each(function(){
            var self=this;
            $(self).attr("original",$(self).attr("src"));
            if(settings._event!="scroll" || $.s_y(self,settings) || $.s_x(self,settings)){
                if(settings._oimg){
                    $(self).attr("src",settings._oimg);
                }else{
                    $(self).removeAttr("src");
                }
                self.loaded=false;
            }else{
                self.loaded=true;
            }
            $(self).one("appear",function(){
                if(!this.loaded){
                    $("<img />").bind("load",function(){
                        $(self).hide().attr("src",$(self).attr("original"))[settings._show](settings.effectspeed);
                        self.loaded=true;
                    }).attr("src",$(self).attr("original"));
                }
            });
            if(settings._event!="scroll"){
                $(self).bind(settings._event,function(event){
                    if(!self.loaded){
                        $(self).trigger("appear");
                    }
                })
            }
        })
    };
    $.s_y=function(element,settings){
        if(settings._object===undefined || settings._object===window){
            var fold=$(window).height() + $(window).scrollTop();
        }else{
            var fold=$(settings._object).offset().top + $(settings._object).height();
        }
        return fold<=$(element).offset().top - settings._before;
    };
    $.s_x=function(element,settings){
        if(settings._object===undefined || settings._object===window){
            var fold=$(window).width() + $(window).scrollLeft();
        }else{
            var fold=$(settings._object).offset().left + $(settings._object).width();
        }
        return fold<=$(element).offset().left - settings._before;
    };
    $.extend($.expr[':'],{
        "below-the-fold":"$.s_y(a,{_before:0,_object:window})","above-the-fold":"!$.s_y(a,{_before:0,_object:window})",
        "right-of-fold":"$.s_x(a,{_before:0,_object:window})","left-of-fold":"!$.s_x(a,{_before:0,_object:window})"
    })
})(jQuery);

 

//调用方法欢迎转载本文,但请注明出处,Seven的部落格:http://hi.baidu.com/see7di/homeEmail:See7di@Gmail.com
$(function(){
    $("img").viewload({
        //下边所有的参数均可使用缺省值欢迎转载本文,但请注明出处,Seven的部落格:http://hi.baidu.com/see7di/homeEmail:See7di@Gmail.com

            //_before:0,//加載圖片時的提前量,0表示即時加載,如设置为200,表示滚动条在离目标位置还有200像素时就开始加载图片,可以做到不让用户察觉.
            //_moreimg:0,//該插件默认在找到第一张不在可见区的图片时则不再继续加载,但当HTML容器混乱的时候可能出现可见区域内图片并没正常加载的情况,該參數意在加载N张可见区域外的图片,以避免出现这个问题
            //_event:"scroll",//事件触发时才加载,click(点击),mouseover(鼠标經過),scroll(滾動條滾動),這裡的值可以查閱jquery的事件
            //_show:"fadeIn",//使用何种效果载入,show(直接显示),fadeIn(淡入),slideDown(下拉),這裡的值可以查閱jquery的動畫效果
            // _object:window,//值为容器對象.該插件默认在拉动浏览器滚动条时生效,这个参数可以让你在拉动某容器的滚动条时依次加载其中的图片,例如:$("div #box")
            //_oimg:"img/blank.gif"//提前占位用的图片(一個长度和宽度均为1像素的图片),值为某一图片路径.此图片用来占据将要加载的图片的位置,当图片加载时,该占位图则会隐藏
    });
});


 