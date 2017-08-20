<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.rbt.function.AdvinfoFuc" %>
<%@ page import="com.rbt.model.Advpos" %>
<% 
	String pos_id = "",area_id = "",cat_id= "";
	String class_style="";//获取图片的class样式
	if(request.getParameter("pos_id")!=null){
		pos_id = request.getParameter("pos_id");
	}
	if(request.getParameter("area_id")!=null){
		area_id = request.getParameter("area_id");
	}
	if(request.getParameter("cat_id")!=null){
	   cat_id=request.getParameter("cat_id");
	}
	//获取图片的class样式
	if(request.getParameter("class_style")!=null){
		class_style ="class=\""+ request.getParameter("class_style")+"\"";
	}
	if(!pos_id.equals("")){
	
		Advpos advpos = AdvinfoFuc.getAdvposByPk(pos_id);
		
		//pos_type a:代码广告 b:文字链接 c:图片广告 d:Flash广告 e:幻灯片广告 f:排名广告 g:赞助商广告
		//module_type 所属模型
		//default_code 无广告时显示的默认代码		
		String pos_type = "",module_type="",default_code="",p_width="",p_height="";
		if(advpos!=null){
			pos_type = advpos.getPos_type();
			module_type = advpos.getModule_type();
			default_code = advpos.getDefault_code();
			p_width = advpos.getP_width();
			p_height = advpos.getP_height();
		}
	
		List advList=null;
		if(!"".equals(area_id)){
		advList = AdvinfoFuc.getDisplayAdv(pos_id,area_id,"area");
		}else if(!"".equals(cat_id)){
		advList = AdvinfoFuc.getDisplayAdv(pos_id,cat_id,"cate");
		}else{
		 advList = AdvinfoFuc.getDisplayAdv(pos_id,"","");
		}
		if(advList!=null && advList.size()>0){
			//adv_code 广告代码
			String adv_code = "",title="",content="",link_url="",img_path="",flash_url="";
			HashMap advMap = (HashMap)advList.get(0);
			if(advMap.get("adv_code")!=null){
				adv_code = advMap.get("adv_code").toString();
			}
			if(advMap.get("title")!=null){
				title = advMap.get("title").toString();
			}
			if(advMap.get("img_path")!=null){
				img_path = advMap.get("img_path").toString();
			}
			if(advMap.get("content")!=null){
				content = advMap.get("content").toString();
			}
			if(advMap.get("link_url")!=null){
				link_url = advMap.get("link_url").toString();
			}
			if(advMap.get("flash_url")!=null){
				flash_url = advMap.get("flash_url").toString();
			}
			//a:代码广告
			if(pos_type.equals("a")){
				adv_code = adv_code.replace("'","");
				out.println("document.write('"+adv_code+"');");
			}else if(pos_type.equals("b")){
				//文字广告
				title = title.replace("'","");
				content = content.replace("'","");
				out.println("document.write('<a href=\"/mall/advpos!updateaddnum.action?link_url="+link_url+"&pos_id=" + pos_id + "\" target=\"_blank\" "+class_style+" title=\""+content+"\">"+title+"</a>');");
			}
			else if(pos_type.equals("c")){
				//图片广告
				img_path = img_path.replace("'","");
				out.println("document.write('<a href=\"/mall/advpos!updateaddnum.action?link_url="+link_url+"&pos_id=" + pos_id + "\" target=\"_blank\"><img src=\""+img_path+"\" "+class_style+" width=\""+p_width+"\" height=\""+p_height+"\"/></a>');");
			}
			else if(pos_type.equals("d")){
				//flash广告
				flash_url = flash_url.replace("'","");
				out.println("document.write('<embed src=\""+flash_url+"\" width=\""+p_width+"\" "+class_style+" height=\""+p_height+"\" type=\"application/x-shockwave-flash\" extendspage=\"http://get.adobe.com/flashplayer/\" autostart=\"true\" quality=\"high\" allowfullscreen=\"true\"></embed>');");
			}
			else if(pos_type.equals("e")){
				//幻灯片广告
				img_path = img_path.replace("'","");
				
				out.println("var pic_width="+p_width+";"); //图片宽度
				out.println("var pic_height="+p_height+";"); //图片高度
				out.println("var button_pos=4;"); //按扭位置 1左 2右 3上 4下
				out.println("var stop_time=3000;"); //图片停留时间(1000为1秒钟)
				out.println("var show_text=0;"); //是否显示文字标签 1显示 0不显示
				out.println("var txtcolor=\"000000\";"); //文字色
				out.println("var bgcolor=\"DDDDDD\";"); //背景色
				out.println("var imag=new Array();");
				out.println("var link=new Array();");
				out.println("var text=new Array();");
				
				
				for(int i=0;i<advList.size();i++){
					HashMap aMap = (HashMap)advList.get(i);
					String himg_path = "",hlink_url="",htitle="";
					if(aMap.get("img_path")!=null){
						himg_path = aMap.get("img_path").toString();
					}	
					if(aMap.get("link_url")!=null){
						hlink_url = aMap.get("link_url").toString();
					}	
					if(aMap.get("title")!=null){
						htitle = aMap.get("title").toString();
					}
					out.println("imag["+(i+1)+"]=\""+himg_path+"\";");
					out.println("link["+(i+1)+"]=\""+hlink_url+"\";");
					out.println("text["+(i+1)+"]=\""+htitle+"\";");
				}
				
				
				//可编辑内容结束
				out.println("var swf_height=show_text==1?pic_height+20:pic_height;");
				out.println("var pics=\"\", links=\"\", texts=\"\";");
				out.println("for(var i=1; i<imag.length; i++){");
				out.println("	pics=pics+(\"|\"+imag[i]);");
				out.println("	links=links+(\"|\"+link[i]);");
				out.println("	texts=texts+(\"|\"+text[i]);");
				out.println("}");
				out.println("pics=pics.substring(1);");
				out.println("links=links.substring(1);");
				out.println("texts=texts.substring(1);");
				out.println("document.write('<object classid=\"clsid:d27cdb6e-ae6d-11cf-96b8-444553540000\" codebase=\"http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cabversion=6,0,0,0\" width=\"'+ pic_width +'\" height=\"'+ swf_height +'\">');");
				out.println("document.write('<param name=\"movie\" value=\"/include/advswf/focus.swf\">');");
				out.println("document.write('<param name=\"quality\" value=\"high\"><param name=\"wmode\" value=\"opaque\">');");
				out.println("document.write('<param name=\"FlashVars\" value=\"pics='+pics+'&links='+links+'&texts='+texts+'&pic_width='+pic_width+'&pic_height='+pic_height+'&show_text='+show_text+'&txtcolor='+txtcolor+'&bgcolor='+bgcolor+'&button_pos='+button_pos+'&stop_time='+stop_time+'\">');");
				out.println("document.write('<embed src=\"/include/advswf/focus.swf\" FlashVars=\"pics='+pics+'&links='+links+'&texts='+texts+'&pic_width='+pic_width+'&pic_height='+pic_height+'&show_text='+show_text+'&txtcolor='+txtcolor+'&bgcolor='+bgcolor+'&button_pos='+button_pos+'&stop_time='+stop_time+'\" quality=\"high\" width=\"'+ pic_width +'\" height=\"'+ swf_height +'\" allowScriptAccess=\"sameDomain\" type=\"application/x-shockwave-flash\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" />');");
				out.println("document.write('</object>');");

			}
		}else{
			if(default_code != null){
				default_code = default_code.replace("'","");
				out.println("document.write('"+default_code+"');");
			}
		}
		
	}
%>
