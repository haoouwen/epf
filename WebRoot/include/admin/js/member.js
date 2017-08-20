

  var areaProvince="";
  var areaProvinces=[];
  var areaMember="";
  var areaMembers=[];
  var allarea="";
  var allareas=[];
  var areaamount="";
  var areaamounts=[];
  var search_area_attr =""
  var picMember;
  $(document).ready(function(){
	  search_area_attr = $("#area_attr").val();
	  getArea(search_area_attr);
	  getAreaMember(search_area_attr); 
	  columnbasic('container_columnmember','区域会员数量分布图','基于系统数据',areaProvinces,'会员（个）',' 个','会员',areaMembers);
	  
	  getAreaTotalAmount(search_area_attr);
	  columnbasic('container_columnamount','区域会员消费金额分布图','基于系统数据',allareas,'金额（元）',' 元','金额',areaamounts);
	  
	  picMember = dealArray(search_area_attr);
      piebasic('区域会员数量分布图','基于系统数据',areaProvinces,'会员（个）',' 个','会员',picMember); 
  });
  
  
  
  /**/
  function getArea(areaPara){
     $.ajax({    
        type: "post",    	     
        url: "/member!gerArea.action?area_attr_s="+areaPara,       
        datatype:"json",
        async:false,
        success: function(data){	
          var arealist=eval(data);
      	for(var i=0;i<arealist.length;i++){
      	   areaProvince+=arealist[i].area_name;
      	   areaProvince+=","; 
      	}
      	   areaProvince = areaProvince.substring(0,areaProvince.length-1);	
      	   areaProvinces=areaProvince.split(',');
         }
	 });
  }
  
  /**/
  function getAreaMember(areaPara){
     
     $.ajax({    
        type: "post",    	     
        url: "/member!getAreaMember.action?area_attr_s="+areaPara,       
        datatype:"json",
        async:false,
        success: function(data){	
          var arealist=eval(data);
      	for(var i=0;i<arealist.length;i++){
      	   areaMember+=arealist[i].area_member_num;
      	   areaMember+=","; 
      	}
      	areaMember = areaMember.substring(0,areaMember.length-1);
      	areaMembers=areaMember.split(',');
      	/********将字符串数组转化成数字数组***********/
      	var member_num = [areaMembers.length];   
      	for(var i=0;i<areaMembers.length;i++){
           member_num[i]=Number(areaMembers[i]);
       }   
      	areaMembers = member_num;   
       }

	  });
    
  }
  
  
   /**/
  function getAreaTotalAmount(areaPara){
     
     $.ajax({    
        type: "post",    	     
        url: "/member!getAreaTotalAmount.action?area_attr_s="+areaPara,       
        datatype:"json",
        async:false,
        success: function(data){	
          var arealist=eval(data);
      	for(var i=0;i<arealist.length;i++){
      	  
      	   allarea+=arealist[i].area_attr;
      	   allarea+=","; 
      	   
      	   areaamount+=arealist[i].area_total_amount;
      	   areaamount+=",";
      	}
      	allarea = allarea.substring(0,allarea.length-1);
      	allareas=allarea.split(',');  	
      	
      	areaamount = areaamount.substring(0,areaamount.length-1);
      	areaamounts=areaamount.split(',');
      	
      	/********将字符串数组转化成数字数组***********/
      	var total_amount = [areaamounts.length];   
      	for(var i=0;i<areaamounts.length;i++){
           total_amount[i]=Number(areaamounts[i]);
        }   
      	areaamounts = total_amount;   
       }

	  });
    
  }
  
  /**
  *
  **/
  function dealArray(areaPara){
     var memberdata;
     $.ajax({    
        type: "post",    	     
        url: "/member!getPieAreaMember.action?area_attr_s="+areaPara,       
        datatype:"json",
        async:false,
        success: function(data){	
          
          memberdata = eval(data);
     
       }

	  });
    return memberdata;
    
  }

function changeChart(paraChart){
   if(paraChart=='column'){
     $("#container_pie").hide();
     $("#container_columnmember").show();
     $("#container_columnamount").show();
     $("#container_hr").show();
   }else if(paraChart=='pie'){
     $("#container_columnmember").hide();
     $("#container_columnamount").hide();
     $("#container_hr").hide();  
     $("#container_pie").show();
   }

}