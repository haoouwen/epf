/*
*功能：系统系统分类管理
*主要有：供应分类加载第一级菜单、查个某个分类的所有属性
*删除菜单名、页面加载运行主方法根据ID找出相应的分类、
*/
//定义全局变量
var mtype="";//定义所属分类的模块值
var code_value="supply";//定义select框所选的值
var mod_type=""//定义所属模块的参数值
var is_supply_insert="";
//创建个map数组
var  bb = new Map(); 	   	
//供应分类加载第一级菜单,第一个参数为父级ID，第二个参数为所属模块参数值
function load_supply(up_cat_id,type)
{    
     mtype=type;
     code_value=type;
     menutitle=$("#modletype option:selected").text()+"分类";
     $.ajax({  	 
         type: "post",    	     
         url: "/category!getlist.action?category.module_type="+mtype+"&cid="+up_cat_id+"&ajaxRequestRandom="+Math.random(),       
         datatype:"json",
         success: function(data){   
          if(data.length>0){     
             $("div[id^=level]").remove();
	         $("#divlist").append(data);
	         $("#menutitle1").html(menutitle);
	  		 //       if(code_value!="supply"&&code_value!="classified"){
	   		 //       $(".attr").remove();
	         //   }
	      }else{
	         $("div[id^=level]").remove();
	         //第一个参数为级数，第二个为当前控件个数，第三个为标题，第四个为父的ID值
	         var parentdiv=creatediv(0,1,menutitle,up_cat_id);	         
	         $("#divlist").append(parentdiv);
	         //if(code_value!="supply"&&code_value!="classified"){
	           //$(".attr").remove();
	         //}
	      }	      
	   }
  });  	
}     

 //设置分类串用于回选
 var catMap = new Map(); 	
 function set_cat_attr(id,level){
     catMap.put(level,id);
     var cat_str=""
     //判断当前点击的等级是否大于分类的个数
     if(level<catMap.size()){
        for(var i=level;i<catMap.size();i++){
	        catMap.remove(i);
	     }     
     }
     for(var i=1;i<catMap.size()+1;i++){
        cat_str+=catMap.get(i)+",";
     }
     if(cat_str.lastIndexOf(",")>-1){
          cat_str=cat_str.substring(0,cat_str.lastIndexOf(","));
     }
     $("#cate_attr_str").val(cat_str);
 }
 
 //用于分类管理的回选
 function cat_div_backselect(top_id,mod_type){
     var cate_str=$("#cate_attr_str").val();
     if(cate_str.indexOf(top_id)<=-1){
     	cate_str=top_id+','+cate_str;
     }
     var cat_attr=(cate_str).split(",");        
     
     for(var i=1;i<cat_attr.length+1;i++){
        if(cat_attr[i-1]!=''){
             showcate(cat_attr[i-1],i-1,mod_type,'no');  
         }          
     }          
 }   
 

 //查个某个分类的所有属性
 function attr(id,level)
 { 
        document.forms[0].action='/admin_Categoryattr_list.action?url_up_id='+id+"&level="+level+"&modtype_s="+code_value;
	    document.forms[0].submit();
 }
 //删除菜单名
 function del(id) { 
   hiConfirm('您要删除这些选项吗，有可能会对子分类造成影响.确定要删？', '系统提示',function(r){ 
         if(r){ 
           $.ajax({  	 
              type: "post",    	     
              url: "admin_Category_catdelete.action?ids="+id+"&ajaxRequestRandom="+Math.random(),    
              datatype:"json",
              success: function(data){
                $("#li"+id).remove();   
                  jSuccess("删除成功!");    
	  	        },error:function(){
	  	          jNotify("删除失败!"); 
                  return false; 
	  	        }
	        });  	
        }
    });   		
 }
 
 //新增分类事件
 function addcate(level) {    
	  var name=$("#menutitle"+level).html();  
	  var up_cat_id=$("#menuvalue"+level).val(); 
	  $("#level").val(level);
	  $("#up_cat").val(up_cat_id);
	  $("#type").val(mtype);
	  document.forms[0].action='/admin_Category_add.action';
	  document.forms[0].submit();
 }
 
 //查看分类详情
 function look(id,level) {
      var name=$("#menutitle"+level).html();
      $("#cat_id").val(id);
      document.forms[0].action='/admin_Category_view.action';	 
	  document.forms[0].submit();
 }
 
//加载显示分类
 var num=1;//已出现几个DIV记数
 function showcate(id,level,type) {
      //如果不是顶级ID查询的话存入Map对象  
      if($("#top_cat_id").val()!=id){
          //保存点击分类的值
 	      set_cat_attr(id,level);
      }
      //加载颜色分别
      $("#level"+level+" li div").css("border","1px solid #FFF"); 
      $("#level"+level+" li").css("background-color","#FFF"); 
      $("#li"+id).css("background-color","#e2efeb");  
      $("#li"+id+" div").css("border","1px solid #e2efeb");  
      //加载下级菜单
      var title=$("#cate"+id).html();         
      $.ajax({  	 
          type: "post",    	     
          url: "category!getlist.action?cid="+id+"&category.module_type="+type+"&ajaxRequestRandom="+Math.random(),       
          datatype:"json",
          async:false,
          success: function(data){     
          if(data.length>0){
                 if(level+1<=num){
                      for(var i=(level+1);i<(num+1);i++){
                          $("#level"+i).remove(); 		                      
                      }
                      $("#divlist").append(data);	                     
                      num=level+1;
                 }else if(level==num){              
  	                  $("#divlist").append(data);
  	                   num=num+1;//个数加1	    	     
 	             } 
 	             //判断是不是顶级的ID分类
 	             if(level!=0){
 	            	 $("#menutitle"+(level+1)).html(title);  
 	             }else{
 	             	 $("#menutitle"+(level+1)).html($("#cat_select_moudle").val());  
 	             }				      
 	             $("#menuvalue"+(level+1)).val(id);    	           	             
           }else{
               if(level==num){          
                   var i=level+1;
                   $("#level"+i).remove();       
                   creatediv(level,num,title,id); 
                   num=num+1;
                 }    
	            else{
               	  for(var i=(level+1);i<(num+1);i++)
                     {
                       $("#level"+i).remove(); 		                      
                     }
                  creatediv(level,num,title,id); 
	            }                      	              
           }
        }	             
    });  	 
}

//创建div对象
function creatediv(level,num,title,id){
   for(var i=(level+1);i<(num+1);i++)
     {
       $("#level"+i).remove(); 		                      
     }
   var cat_level=level+1;
   $("#divlist").append("<div id=level"+cat_level+" class='parentdiv'><h3 >"+
   "<div id=menutitle"+cat_level+" class='spantitle'></div></h3><input id=menuvalue"+cat_level+" type='hidden' >"+
   "<div class=\"catelist\"><li class='cateli' style='text-align:center;width:260px' >您还未添加子分类</li></div><h3><img class='addclass' title='添加当前列表的子分类' onclick='addcate("+cat_level+")' src='/include/common/images/add.png'/>"+
   "</h3></div>")
   $("#menuvalue"+(level+1)).val(id);
   $("#menutitle"+(level+1)).html(title); //给标题赋值  
}

//自定义map对象
function Map(){
 this.keys = new Array();
 this.data = new Array();
 
 this.put = function(key,value){
  if(this.data[key] == null){
   this.keys.push(value);
  }
  this.data[key] = value;
 };
 
 this.get = function(key){
  return this.data[key];
 };
 
 this.remove = function(key){
     for(var i=key+1;i<this.keys.length+1;i++){          
     	this.data[i] = null;
     }
     this.keys.length=key;
 };
 
 this.isEmpty = function(){
  return this.keys.length == 0;
 };
 
 this.size = function(){
  return this.keys.length;
 };
}
