var myScroll,pullUpEl, pullUpOffset,generatedCount = 0,cp=1,is_goods="1";
/**
 * 初始化iScroll控件
 */
function loaded() {
	if(is_goods=='999'){
		return ;
	}
	pullUpEl = document.getElementById('pullUp');	
	
	
	myScroll = new iScroll('wrapper', {
		scrollbarClass: 'myScrollbar',
		useTransition: false, 
		
		onScrollMove: function () {
			if (this.y < (this.maxScrollY - 5) && !pullUpEl.className.match('flip')) {
				pullUpEl.className = 'flip';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '松手开始更新...';
				this.maxScrollY = this.maxScrollY;
			} else if (this.y > (this.maxScrollY + 5) && pullUpEl.className.match('flip')) {
				pullUpEl.className = '';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';
				this.maxScrollY = pullUpOffset;
			}
		},
		onScrollEnd: function () {
			if (pullUpEl.className.match('flip')) {
				pullUpEl.className = 'loading';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';				
				pullUpAction();	// Execute custom function (ajax call?)
			}
		}
	});
	
	setTimeout(function () { document.getElementById('wrapper').style.left = '0'; }, 800);
	if(is_goods=='0'){
		pullUpEls = document.getElementById('pullUps');	
	myScrolls = new iScroll('wrappers', {
		scrollbarClass: 'myScrollbar',
		useTransition: false, 
		
		onScrollMove: function () {
			if (this.y < (this.maxScrollY - 5) && !pullUpEls.className.match('flip')) {
				pullUpEls.className = 'flip';
				pullUpEls.querySelector('.pullUpLabel').innerHTML = '松手开始更新...';
				this.maxScrollY = this.maxScrollY;
			} else if (this.y > (this.maxScrollY + 5) && pullUpEls.className.match('flip')) {
				pullUpEls.className = '';
				pullUpEls.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';
				this.maxScrollY = pullUpOffset;
			}
		},
		onScrollEnd: function () {
			if (pullUpEls.className.match('flip')) {
				pullUpEls.className = 'loading';
				pullUpEls.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';				
				pullUpActions();	// Execute custom function (ajax call?)
			}
		}
	});
	
	setTimeout(function () { document.getElementById('wrappers').style.left = '0'; }, 800);
	}
}


document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
document.addEventListener('DOMContentLoaded', loaded, false); 

function pullUpAction () {
	setTimeout(function () {	// <-- Simulate network congestion, remove setTimeout from production!
		var el, li, i;
		el = document.getElementById('thelist');
		cp=cp+1;
		$.ajax({  	 
          type: "post",    	     
          url: "/webapp/collection!pageList.action?cp="+ cp,
          datatype:"json",
          success: function(data){
          if(data==''){
          	$("#pullUp").remove();
          }else{
          	   li = document.createElement('div');
			li.innerHTML =data;
			el.appendChild(li, el.childNodes[0]);
          }
         
          }
      }); 
		
		myScroll.refresh();		
	}, 1000);	
}