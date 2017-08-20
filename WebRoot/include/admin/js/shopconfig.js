	//新增物流配置
	function set(){
		//判断是否存在该控件
		if($("#s_area").length>0){	
			 //拼接开始地区串	
			 var add_area_id=""; 
			 var add_area_text="";	  
			 var sel_count=0;   		     
		     $("#s_area").find(".select").each(function(){	   	           		             		     
	       		    var sel_val =  $(this).val();
	       		    var sel_text=$(this).find("option:selected").text();
	      		    add_area_id+=sel_val+",";   		    
		     });	
		     //去除最后的一个逗号
		     var id_len=add_area_id.lastIndexOf(",");
		     var text_len=add_area_text.lastIndexOf(",");
		     if(id_len>0){
		     	add_area_id=add_area_id.substring(0,id_len);
		     }
		     if(add_area_id.indexOf("0")>-1){
		     	 jNotify("请选择开始地区");  
		     	 return ;
		     }else{
		     	 $("#start_area_str").val(add_area_id);
		     }
		 }
	     //拼接配送方式和到达地区串
	     var modearea="";
	     $(".sendmodetype").each(function(){
	    	  var s_id=$(this).val();
	    	  var li_name_str="";
	    	  var end_area_str="";
	    	  $("#show_area_set_"+s_id).find("span").each(function(){
	    			var li_name=$(this).html();	
	    			li_name_str+=li_name+"=";  		
	    	  });
	    	  modearea+=s_id+"|"+li_name_str+":";	    	  
	     });
	     $("#end_area_str").val(modearea);
	     //提交表单
	     $("#sendset").submit();
	}
	
	//新增地区 
	function mode_add_area(a_id){	           			           		      
		 var add_area_id=""; 
		 var add_area_text="";
		 var sel_count=0;   		     
	     $("#s_area_"+a_id).find(".select").each(function(){	   	           		             		     
       		    var sel_val =  $(this).val();
       		    var sel_text=$(this).find("option:selected").text();
       		    //去掉请选择
       		    if(sel_val!="0"){
       		     	 add_area_id+=sel_val+",";
       		    	 add_area_text+=sel_text+",";		           		     
       		    }       		    
	     });	
	     //去除最后的一个逗号
	     var id_len=add_area_id.lastIndexOf(",");
	     var text_len=add_area_text.lastIndexOf(",");
	     if(id_len>0){
	     	add_area_id=add_area_id.substring(0,id_len);
	     }
	     if(text_len>0){
	     	add_area_text=add_area_text.substring(0,text_len);
	     }
	     var count=0;
	     if(add_area_text!=""){	           		         
	         $("#show_area_set_"+a_id).find("input:hidden[name='area_set_val']").each(function(){	           		         
	               if($(this).val()==add_area_id){	           		               	  
           		    	 count++;	           		               
	               }
	         });	           		     
	     }
	     if(count>0){//如果已添加则提示信息,否则则添加成功
	      	jNotify("您已添加该地区!");  	           		        
	     }else{
	        if(add_area_text!=""){	  
	     		 $("#show_area_set_"+a_id).append("<li class='area_li' ><input type='hidden' name='area_set_val' value=\""+add_area_id+"\"/><span>"+add_area_text+"</span><a onclick='delarea(this);'><img src='/include/common/images/admin/delete.png' style='vertical-align:middle;'/></a></li>");
   		         jSuccess("新增地区成功!");  
   		    }else{
   		         jNotify("请选择地区!");  
   		    }
	     }
	}         
 
	//删除地区
	function delarea(obj){
		$(obj).parent("li").remove();    
		jSuccess("删除成功!");      	          		
	}	
	
	//复制地区
	function mode_copy_area(a_id){
		var area_html=$("#show_area_set_"+a_id).html();
		$(".cal_area").each(function(){		
			$(this).html(area_html);
		});
	}
	
	var areaHtml ="";	
	//修改发货地区
	function updatearea(){
		areaHtml = $("#startdiv").html();
		$("#startdiv").html("<div id='s_area' style='float:left;'></div><a style='cursor:pointer;' onclick='backarea();'>[返回]</a>");
		area_select($("#cfg_topid").val(),"s_area");
	}
	
	//返回发货地区
	function backarea(){
		$("#startdiv").html(areaHtml);
	}
	
	
	
	//取色器
    function getColor(e){  
      
        obj = document.all ? event.srcElement : e.target;  
        obj.style.position = "relative";  
        var inputTop = getTop(obj);  
        var inputLeft = getLeft(obj);  
          
        var htmlStr = "visibility:visible;position:absolute;";  
        //  
        var colorPacker = document.getElementById("colorPacker");  
        var colorPackerShadow = document.getElementById("colorPackerShadow");  
      
        if (!colorPacker) {  
            colorPacker = document.createElement("div");  
            colorPackerShadow = document.createElement("div");  
      
            colorPacker.id = "colorPacker";  
            colorPackerShadow.id = "colorPackerShadow";  
              
            colorPacker.style.cssText = htmlStr;  
            colorPackerShadow.style.cssText = htmlStr+"opacity:0.3;filter:alpha(opacity=20);";  
              
            colorPacker.style.zIndex = 34535;  
            colorPackerShadow.style.zIndex = 34534;  
              
            colorPacker.style.backgroundColor = "#6997EF" ;  
            colorPackerShadow.style.backgroundColor = "#000000" ;  
              
            colorPacker.style.height = "230px";  
            colorPackerShadow.style.height = "230px";  
              
            colorPacker.style.width = "300px";  
            colorPackerShadow.style.width = "300px";  
              
            var titleStyle = "width:95%;background:#6997EF;color:#ffffff;font-size:12px;margin-left:5px;padding-right:10px;";  
            var contetnStyle = "width:95%;text-align:center;margin:auto;background:#6997EF;" ;  
              
            var colorPackerTitle = "<div style='"+titleStyle+"'>取色器</div>" ;  
            var colorPackerContent = "<div style='"+contetnStyle+"'>"+bulid()+"<div>" ;  
              
            document.body.appendChild(colorPacker);  
            document.body.appendChild(colorPackerShadow);  
              
            colorPacker.innerHTML = colorPackerTitle+ colorPackerContent;  
              
        }  
        else {  
            document.getElementById("colorPacker").style.visibility = "visible";  
            document.getElementById("colorPackerShadow").style.visibility = "visible";  
              
        }  
          
        colorPacker.style.left = (inputLeft) + "px";  
        colorPacker.style.top = (inputTop + obj.clientHeight) + "px";  
      
        colorPackerShadow.style.left = (inputLeft+2) + "px";  
        colorPackerShadow.style.top = (inputTop + obj.clientHeight+2) + "px";  
          
        if(!colorPacker.onclick){  
            colorPacker.onclick = function(oEvent){  
                e = oEvent || window.event;  
                var ev = document.all ? event.srcElement : e.target ;  
                  
                obj.value = ev.bgColor;
                this.style.visibility = "hidden";  
                document.getElementById("colorPackerShadow").style.visibility = "hidden";  
                if (document.all) {  
                    e.cancelBubble = true;  
                }  
                else {  
                    e.stopPropagation();  
                }  
            };  
        }  
        if(!document.all){  
            colorPacker.setAttribute('flag','flag');  
            colorPackerShadow.setAttribute('flag','flag');  
            obj.setAttribute('flag','flag');  
      
        }else{  
            colorPacker.flag = "flag";  
            colorPackerShadow.flag = "flag";  
            obj.flag = "flag";  
              
        }  
      
        if(!document.onclick){  
            document.onclick = function(e){  
                var ev = document.all ? event.srcElement : e.target ;  
                if (ev.getAttribute("flag")==null){  
                    document.getElementById("colorPacker").style.visibility = "hidden";  
                    document.getElementById("colorPackerShadow").style.visibility = "hidden";  
                }  
            };  
        }  
          
    }  
      
    var hexch = new Array('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F');  
    var cnum = new Array(1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0);  
      
    function ToHex(n){  
        var h, l;  
          
        n = Math.round(n);  
        l = n % 16;  
        h = Math.floor((n / 16)) % 16;  
        return (hexch[h] + hexch[l]);  
    }  
      
    function DoColor(c, l){  
        var r, g, b;  
          
        r = '0x' + c.substring(1, 3);  
        g = '0x' + c.substring(3, 5);  
        b = '0x' + c.substring(5, 7);  
          
        if (l > 120) {  
            l = l - 120;  
              
            r = (r * (120 - l) + 255 * l) / 120;  
            g = (g * (120 - l) + 255 * l) / 120;  
            b = (b * (120 - l) + 255 * l) / 120;  
        }  
        else {  
            r = (r * l) / 120;  
            g = (g * l) / 120;  
            b = (b * l) / 120;  
        }  
          
        return '#' + ToHex(r) + ToHex(g) + ToHex(b);  
    }  
      
    function wc(r, g, b, n){  
        r = ((r * 16 + r) * 3 * (15 - n) + 0x80 * n) / 15;  
        g = ((g * 16 + g) * 3 * (15 - n) + 0x80 * n) / 15;  
        b = ((b * 16 + b) * 3 * (15 - n) + 0x80 * n) / 15;  
          
        return '<TD  BGCOLOR=#' + ToHex(r) + ToHex(g) + ToHex(b) + ' height=6 width=6></TD>';  
    }  
      
    function bulid(){  
        var trStr = "<table CELLPADDING=0 CELLSPACING=0>";  
        for (i = 0; i < 16; i++) {  
            trStr += '<TR>';  
            for (j = 0; j < 30; j++) {  
                n1 = j % 5;  
                n2 = Math.floor(j / 5) * 3;  
                n3 = n2 + 3;  
                  
                trStr += wc((cnum[n3] * n1 + cnum[n2] * (5 - n1)), (cnum[n3 + 1] * n1 + cnum[n2 + 1] * (5 - n1)), (cnum[n3 + 2] * n1 + cnum[n2 + 2] * (5 - n1)), i);  
            }  
              
            trStr += '</TR>';  
        }  
        trStr += "</table>";  
        return trStr;  
          
    }  
      
    function getTop(e){  
        var offset = e.offsetTop;  
        if (e.offsetParent != null)   
            offset += getTop(e.offsetParent);  
        return offset;  
    }  
      
    function getLeft(e){  
        var offset = e.offsetLeft;  
        if (e.offsetParent != null)   
            offset += getLeft(e.offsetParent);  
        return offset;  
    }  

