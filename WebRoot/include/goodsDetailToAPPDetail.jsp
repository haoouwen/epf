<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.rbt.function.GoodsDetailToAppDetailFuc" %>
<% 
	String goodsnum="0";
	String goodsid="";
	List glist=new ArrayList();
	glist=GoodsDetailToAppDetailFuc.getGoodList();
	if(glist!=null&&glist.size()>0){
	  //获取商品个数
	  goodsnum=String.valueOf(glist.size());
	  for(int i=0;i<glist.size();i++){
		  HashMap gMap=new HashMap();
		  gMap=(HashMap)glist.get(i);
	  	  if(gMap!=null&&gMap.get("goods_id")!=null){
	  	    goodsid+=gMap.get("goods_id").toString()+",";
	  	  }
	  }
	  if(goodsid!=null){
	    goodsid=goodsid.substring(0,goodsid.length()-1);
	  }
	}
%>
<html>
  <head>
     <title>生成手机详细</title>
     <script src="/include/admin/js/goodsdetailToAPPDetail.js" type="text/javascript"></script>
  </head>
<body >
<!--当前位置开始-->
<div class="postion">
 当前位置:网站管理 > 网站更新 > 生成手机详细
</div>
 <div class="rtdcont">
	       
	        <div class="tabdiv">
				<div class="rdtable">
					<table class="wwtable"  width="100%" cellspacing="1" cellpadding="8" >
			             <tr>
						  <td align="center" height="30">
						    <% 
						      if(goodsnum.equals("0")){
						      %>
						      <span ><span>系统提示:</span><span>找到商品</span></span>
						      <% 
						      }else{
						      %>
						         <span style="float:left;">&nbsp;
							      <a href="###" onclick="toSetAppDetail();" title="批量生成手机版宝贝详情"><img src="/include/admin/images/scsjxq.jpg"></a>
							      </span>
								<span ><span>商品个数:</span><span><%=goodsnum %></span></span>
								<span ><span>更新成功:</span><span id="success_id">0</span></span>
						      <% 
						      }
						     %>
						  </td>
					    </tr>
						<tr><div class="bsbut"><b>&nbsp;系统提示: </b>执行批量生成手机详细页,之前的手机详细页将会被覆盖,请谨慎操作！</div></tr>
			         </table>
				</div>
	       </div>
</div>
<span id="gid" style="display:none;"><%=goodsid %></span>
<div id="appdetail_conten_id" style="display:none;"></div>
<div class="clear"></div>  
</body>
</html>