 $(document).ready(function(){ 	    
	     $("#abutid").click(function(){
			 $("#aconver").popup({
				    opacity:"0.3",
					p_bg_color:"#e6e6e6",
					bg_color:"#fff",
					p_width:"990",
					p_height:"400",
					pop_title:"地区选择"
			  });
			areacountry();
  });
  });
	  
	   function areacountry(){
		    var areastr="";
			$.ajax({
		          type: "post",
		          url: "/mall/groupbuy!getarea.action",
		          datatype:"json",
		          async:false,
		          success: function(data){ 
			          data=eval("(" +data+")");
			          //地区显示
			          for(var i=0;i<data[0].length;i++){
			          	areastr += "<p><b>" + data[0][i].en_name.toLocaleUpperCase() + ".</b><span>";
			           for(var j=0;j<data[1].length;j++){
			               if(data[0][i].en_name==data[1][j].en_name.substr(0,1)){
			               	areastr+='<a href="/mall-grouparea-'+data[1][j].en_name+'.html">' + data[1][j].area_name + '</a>';
			               }
			            }
			          	areastr+='</span></p>';
			          }
			          $("#accontid1").html(areastr);
		          }
			});	
	}
