function brandsumit(){
  var cat_attr_str="";
  $("input:hidden[name='all_cat_id_str']").each(function(){
      cat_attr_str+=$(this).val()+"|";
  }) 
  $("#cat_attr_str").val(cat_attr_str);   
  if(checkBrandName()){
    $("#gpform").submit();
    return true;
  }else{
    return false;
  }
}
//判断品牌名称是否重复
function checkBrandName(){

var b_id="";
var b_name="";
var b_type="";
b_name=$("#brand_name").val();
b_type=$("#b_type").val();
if($("#b_id").val()!=null&&$("#b_id").val()!=""){
   b_id=$("#b_id").val();  
}
var fage=false;
$.ajax({  	 
   type: "post",    	     
   url:"/admin_Goodsbrand_ajaxBrandNameCopy.action",       
   data:{'b_id':b_id,'b_name':b_name,'b_type':b_type},		      
   datatype:"json",
   async:false,
   success: function(data){
        if(data=="1"){
         $("#ertip").html("<span class='errorSpan'>该品牌已经存在,请勿重复添加</span>");
         fage=false;
        }else{
          $("#ertip").html("");
           fage= true;
        }
	}
});
return fage;
}