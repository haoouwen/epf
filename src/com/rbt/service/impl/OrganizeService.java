/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: OrganizeService.java 
 */
package com.rbt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.common.Constants;
import com.rbt.dao.IOrganizeDao;
import com.rbt.model.Organize;
import com.rbt.service.IOrganizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录组织部门Service层业务接口实现
 * @author 创建人 LJQ
 * @date 创建日期 Mon Nov 07 13:37:36 CST 2014
 */
@Service
public class OrganizeService extends GenericService<Organize,String> implements IOrganizeService {

	/*
	 * 记录组织部门Dao层接口
	 */
	IOrganizeDao organizeDao;

	@Autowired
	public OrganizeService(IOrganizeDao organizeDao) {
		super(organizeDao);
		this.organizeDao = organizeDao;
	}

	public List getSysList(Map map) {
		
		return this.organizeDao.getSysList(map);
	}

	public int getCounts(Map map) {
		return this.organizeDao.getCounts(map);
	}

	/**
	 * @author :LSQ
	 * @date : Feb 17, 2014 4:00:04 PM
	 * @Method Description :获取所属部门
	 */
	public List getAll() {
		return organizeDao.getAll();
	}

	public List getDeleteList(Map map) {
		return this.organizeDao.getDeleteList(map);
	}
	/**
	 * @author : HZX
	 * @date : Feb 12, 2014 1:13:56 PM
	 * @Method Description :递归删除
	 */
	public boolean dealDelete(String id) {
		String org_id = "";
		Map map = new HashMap();
		map.put("up_org_id", id);
		//删除传进来的ID行
		this.organizeDao.delete(id);
		//通过对传进来的ID进行查询
		List orgaList = this.organizeDao.getList(map);
		//若存在有子ID
		if (orgaList.size() > 0) {
			for (int i = 0; i < orgaList.size(); i++) {
				Map upmap = new HashMap();
				upmap = (HashMap) orgaList.get(i);
				//如果通过上一级获取到本级的ID不为空，则进入递归继续查找并删除 
				if (upmap.get("org_id") != null && !upmap.get("org_id").equals("")) {
					org_id = upmap.get("org_id").toString();
					//进入递归循环
					dealDelete(org_id);
				}
			}
			return true;
		} else {
			return false;
		}
		
	}
	/**
	 * @author : HZX
	 * @date : Feb 12, 2014 12:57:13 PM
	 * @Method Description :获取要打印的html字符串
	 */
	public StringBuffer getHtmlStr(List list,String up_org_id) {
		StringBuffer sb=new StringBuffer();
		//获取要打印的html字符串
		Map map=new HashMap();
        String orga_level="";
        //判断查询的列表是否为空
		if(list!=null&&list.size()>0){
              map=(HashMap)list.get(0);
              if (map.get("org_level") != null) {
            	  orga_level = map.get("org_level").toString();
  			  }
              //地区DIV容器
              sb.append("<div class='areadiv' id=arealevel"+orga_level+">");
              sb.append("<h3 class='areatitle'>");
      	      sb.append("<div id=menutitle" + orga_level+ " class='spantitle'>一级部门</div></h3>" +
      	      		"<input id=menuvalue" + orga_level+ " type='hidden' value='1111111111'>");
              sb.append("<div class='areacontent'><ul>");
              for (int i = 0; i < list.size(); i++){
	  				map = (HashMap) list.get(i);
	  				@SuppressWarnings("unused")
	  				String orga_id = "", orga_name = "", level = "",sys_org="";
	  				if (map.get("org_id") != null) {
	  					orga_id = map.get("org_id").toString();
	  				}
	  				if (map.get("org_name") != null) {
	  					orga_name = map.get("org_name").toString();
	  				}
	  				if (map.get("org_level") != null) {
	  					level = map.get("org_level").toString();
	  				}
	  				if (map.get("sys_org") != null) {
	  					sys_org = map.get("sys_org").toString();
	  				}
	  				sb.append("<li>");
	      			sb.append("<span id='areali"+orga_id+"' class='areaspan' onclick='showorga(\""+orga_id+"\","+orga_level+");'>"+orga_name+"</span>");
	      			sb.append("<img class='edit' title='编辑部门' src='/include/common/images/bj.gif' onclick='updateorga(\""+orga_id+"\");'/>");
	      			if(sys_org.equals("0")){
	      			    sb.append("<img class='delete' title='删除部门' src='/include/common/images/delete.png' onclick='deleteOg(\""+orga_id+"\","+orga_level+");'/>");
	      			}
	  			    sb.append("</li>");  			    
  			  }
              sb.append("</ul></div>");
              if(up_org_id.equals("")){
            	  up_org_id=Constants.UP_ORG_ID;
              }
      		  sb.append("<div class='areaadd'><img class='add' title='新增部门' onclick='insertorga(\""+up_org_id+"\","+ orga_level+ ")' src='/include/common/images/add.png'/>");		
    		  sb.append("</div>");
              sb.append("</div>");
		}	
		return sb;
	}
	/**
	 * @author : HZX
	 * @date : Feb 12, 2014 1:06:10 PM
	 * @Method Description :获取要打印的查看页面html字符串
	 */
	public StringBuffer getViewHtmlStr(List list, String up_org_id) {
		StringBuffer sb = new StringBuffer();
		Map map = new HashMap();
		//定义级数
		String org_level = "";
		//判断列表是否为空，如果为空则把等级赋给定义的cat_level
		if (list != null && list.size() > 0) {
			map = (HashMap) list.get(0);
			if (map.get("org_level") != null) {
				org_level = map.get("org_level").toString();
			}

		}
		//重组HTML代码字符串
		sb.append("<select id='level" + org_level + "' name='org_attr' class='select' onchange='onlyshoworg(this.value,"+org_level+")'>");
	  	sb.append("<option value='0'>请选择</option>");	
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = (HashMap) list.get(i);
				String  org_name = "",org_id="";			
				if (map.get("org_id") != null) {
					org_id = map.get("org_id").toString();
				}
				sb.append("<option value="+org_id+">");
				if (map.get("org_name") != null) {
					org_name = map.get("org_name").toString();
				}		
				sb.append(org_name);
				sb.append("</option>");
			}
		}
		sb.append("</select >");
		return sb;
	}
	/**
	 * @author : HZX
	 * @date : Feb 12, 2014 1:15:37 PM
	 * @Method Description :删除当前部门，和所属子部门
	 */
	public void recuDelete(String id) {
		if(id==null || id.equals(""))
			return ;
		String chhid_id="";
		List list=new ArrayList();
		String[] str_id=id.split(",");
		for (int i = 0; i < str_id.length; i++) {
			if(str_id[i].trim().equals("")){
				return;
			}
			Map chiMap=new HashMap();
			chiMap.put("up_org_id",str_id[i]);
			//获取所有上级id为当前id的栏目
			List chi_list=this.organizeDao.getDeleteList(chiMap);
			System.out.println(chi_list);
			if(chi_list!=null && chi_list.size()>0){
				Map listMap=null;
				for (int j = 0; j < chi_list.size(); j++) {
					listMap=(HashMap) chi_list.get(j);
					if(listMap.get("org_id_str")!=null){
						chhid_id+=listMap.get("org_id_str")+",";
					}
				}
			}
		}
		//判断是否最后一个字符是否为逗号，是则删除
		if(chhid_id.lastIndexOf(",")>0){
			chhid_id=chhid_id.substring(0, chhid_id.lastIndexOf(","));
		}
		//删除ID
		this.organizeDao.delete(id);
		recuDelete(chhid_id);
		
	}




	
}

