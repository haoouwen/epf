<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.rbt.common.Constants" %>
<%@ page import="com.rbt.function.*" %>
<%@page import="com.rbt.common.util.ValidateUtil"%>
<% 
	List<Map<String,String>> catnavlist=new ArrayList();
	Map cmap=new HashMap();
	catnavlist=NavCatFuc.getCatnavList(cmap);
%>
document.write('<div class="claMeun">');
     document.write('<ul>');
          <%
             if(catnavlist!=null&&catnavlist.size()>0){
             	for(Map<String,String> cnmap:catnavlist){
                  %>
                     document.write('<li>');
                          <%
                               //获取二级分类名称
                               String cat_attrs[]=cnmap.get("cat_attr").toString().split("\\|");
                               String cat_ids="",cat_names="";
								for(String c_attr:cat_attrs){
									//获取组合的分类的名称和链接
									cat_names +="<a href=\"/mall-goodslist-"+CategoryFuc.getLastCateEnName(c_attr.trim())+".html\">"+CategoryFuc.getLastCateName(c_attr.trim())+"</a>"+"、";
									//获取分类ID
									cat_ids+=CategoryFuc.getLastCateID(c_attr)+",";
								}
								if(!ValidateUtil.isRequired(cat_names)){
								  cat_names=cat_names.substring(0,cat_names.length()-1);
								}
								if(!ValidateUtil.isRequired(cat_ids)){
								  cat_ids=cat_ids.substring(0,cat_ids.length()-1);
								}
                           %>
				          document.write('<h3><%=cat_names %></h3>');
				           document.write('<div class="claItem">');
				               document.write('<div class="claItemCont">');
				                  <%
				                      //获取二级分类及其下级分类
				                      String two_cat_attrs[]=cat_ids.split(",");
										for(String t_c_attr:two_cat_attrs){
										     String two_cat_names="",three_cat_names="";
											//获取组合的分类的名称和链接
											two_cat_names ="<a href=\"/mall-goodslist-"+CategoryFuc.getLastCateEnName(t_c_attr.trim())+".html\">"+CategoryFuc.getLastCateName(t_c_attr.trim())+"</a>";
											%>
											  document.write('<h4><img src="/malltemplate/bmall/images/1.png"  /><%=two_cat_names %></h4>');
											  <%
											    //获取三级分类
											     List<Map<String,String>> threecatnavlist=new ArrayList();
												 Map threecmap=new HashMap();
												 threecmap.put("up_cat_id",t_c_attr);
												 threecatnavlist=CategoryFuc.getCategoryList(threecmap);
											     for(Map<String,String> threemap:threecatnavlist){
											      String three_cat_ids="";
											      if(threemap!=null){
											         three_cat_ids=threemap.get("cat_id").toString().trim();
											      }
											      three_cat_names+="<a href=\"/mall-goodslist-"+CategoryFuc.getLastCateEnName(three_cat_ids)+".html\">"+CategoryFuc.getLastCateName(three_cat_ids)+"</a>";
											     }
											   %>
				                 			  document.write(' <p><%=three_cat_names %></p>');
											<%
										}
				                   %>
				               document.write('</div>');
				           document.write('</div>');
				     document.write('</li>');
                  <% 
                }
             }
             
          %>
     document.write('</ul>');
document.write('</div>');
