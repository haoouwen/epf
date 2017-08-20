/*
 * Package:com.rbt.action
 * FileName: SeosetAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Seoset;
import com.rbt.service.ISeosetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 seo优化设置表action类
 * @author 创建人 lin
 * @date 创建日期 Tue Oct 30 14:14:12 CST 2014
 */
@Controller
public class SeosetAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Seoset seoset;
	
	/*******************业务层接口****************/

	@Autowired
	private ISeosetService seosetService;
	/*********************集合********************/
	
	
	/*********************字段********************/
	public List seosetList;
	public String code;//seo代码(主键)
	public String title;//标题
	public String keyword;//关键字
	public String descri;//描述
	public String men_index="";
	
	/**
	 * 方法描述：返回新增seo优化设置表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增seo优化设置表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		this.men_index=men_index;
		if(code!=null && !code.equals("")){
			String code_s[] = code.split(",");
			String title_s[] = title.split(",");
			String keyword_s[] = keyword.split(",");
			String descri_s[] = descri.split(",");
			for(int i=0;i<code_s.length;i++){
				seoset =new Seoset();
				seoset.setSeo_code(code_s[i].trim());
				seoset.setSeo_title(title_s[i].trim());
				seoset.setSeo_keyword(keyword_s[i].trim());
				seoset.setSeo_decri(descri_s[i].trim());
				if(code_s[i].trim()!=null && !code_s[i].trim().equals("")){
					Seoset ss = this.seosetService.get(code_s[i].trim());
					if(ss==null){
						this.seosetService.insert(seoset);
					}else{
						this.seosetService.update(seoset);
					}
				}
			}
		}
		this.addActionMessage("seo优化设置成功！");
		this.seoset = null;
		this.code=null;
		this.title=null;
		this.keyword=null;
		this.descri=null;
		return list();
	}

	/**
	 * 方法描述：修改seo优化设置表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		this.men_index=men_index;
		//数据验证
		super.commonValidateField(seoset);
		if(super.ifvalidatepass){
			return add();
		}
		this.seosetService.update(seoset);
		this.addActionMessage("修改seo优化设置表成功！");
		return list();
	}
	/**
	 * 方法描述：删除seo优化设置表信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.seosetService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除seo优化设置表成功");
		}else{
			this.addActionMessage("删除seo优化设置表失败");
		}

		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map pageMap = new HashMap();
		//根据页面提交的条件找出信息总数
		int count=this.seosetService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		seosetList = this.seosetService.getList(pageMap);
		if(men_index.equals("")){
			men_index="0";
		}
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出seo优化设置表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.seoset.getSeo_code();
		if(id==null || id.equals("")){
			seoset = new Seoset();
		}else{
			seoset = this.seosetService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the SeosetList
	 */
	public List getSeosetList() {
		return seosetList;
	}
	/**
	 * @param seosetList
	 *  the SeosetList to set
	 */
	public void setSeosetList(List seosetList) {
		this.seosetList = seosetList;
	}
	/**
	 * @return the seoset
	 */
	public Seoset getSeoset() {
		return seoset;
	}
	/**
	 * @param Seoset
	 *            the seoset to set
	 */
	public void setSeoset(Seoset seoset) {
		this.seoset = seoset;
	}
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(seoset == null){
			seoset = new Seoset();
		}
		String id = this.seoset.getSeo_code();
		if(!ValidateUtil.isDigital(id)){
			seoset = this.seosetService.get(id);
		}
	}

}

