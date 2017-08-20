function articlesumit(){
  if(checkArticleName()){
    $("#modiy_form").submit();
    return true;
  }else{
    return false;
  }
}
//判断文章标题是否重复
function checkArticleName(){
var b_id="";
var b_name="";
var b_type="";
b_name=$("#article_tilte").val();
b_type=$("#b_type").val();
if($("#b_id").val()!=null&&$("#b_id").val()!=""){
   b_id=$("#b_id").val();  
}
var fage=false;
$.ajax({  	 
   type: "post",    	     
   url:"/admin_Article_ajaxArticleNameCopy.action",       
   data:{'b_id':b_id,'b_name':b_name,'b_type':b_type},		      
   datatype:"json",
   async:false,
   success: function(data){
        if(data=="1"){
         $("#ertip").html("<span class='errorSpan'>该文章标题已经存在,请勿重复添加</span>");
         fage=false;
        }else{
          $("#ertip").html("");
           fage= true;
        }
	}
});
return fage;
}