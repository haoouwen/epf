/*
 * Package:com.rbt.action
 * FileName: TokensInfoAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.BaseAction;
import com.rbt.model.TokensInfo;
import com.rbt.service.ITokensInfoService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 IOSAndroidTokenaction类
 * @author 创建人 HXK
 * @date 创建日期 Wed Jul 06 12:11:35 CST 2016
 */
@Controller
public class TokensInfoAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * IOSAndroidToken对象
	 */
	private TokensInfo tokensInfo;
	/*******************业务层接口****************/
	/*
	 * IOSAndroidToken业务层接口
	 */
	@Autowired
	private ITokensInfoService tokensInfoService;
	
	/*********************集合*******************/
	/*
	 * IOSAndroidToken信息集合
	 */
	public List tokensInfoList;
	/*********************字段*******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	
		
	/**
	 * 方法描述：返回新增IOSAndroidToken页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增IOSAndroidToken
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(tokensInfo);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.tokensInfoService.insert(tokensInfo);
		this.addActionMessage("新增IOSAndroidToken成功！");
		this.tokensInfo = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改IOSAndroidToken信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(tokensInfo);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.tokensInfoService.update(tokensInfo);
		this.addActionMessage("修改IOSAndroidToken成功！");
		return list();
	}
	/**
	 * 方法描述：删除IOSAndroidToken信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除IOSAndroidToken信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.tokensInfoService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除IOSAndroidToken信息成功!");
		}else{
			this.addActionMessage("删除IOSAndroidToken信息失败!");
		}
	}
	
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		
		//根据页面提交的条件找出信息总数
		int count=this.tokensInfoService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		tokensInfoList = this.tokensInfoService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出IOSAndroidToken信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.tokensInfo.getTok_id();
		if(id==null || id.equals("")){
			tokensInfo = new TokensInfo();
		}else{
			tokensInfo = this.tokensInfoService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the TokensInfoList
	 */
	public List getTokensInfoList() {
		return tokensInfoList;
	}
	/**
	 * @param tokensInfoList
	 *  the TokensInfoList to set
	 */
	public void setTokensInfoList(List tokensInfoList) {
		this.tokensInfoList = tokensInfoList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(tokensInfo == null){
			tokensInfo = new TokensInfo();
		}
		String id = this.tokensInfo.getTok_id();
		if(!this.validateFactory.isDigital(id)){
			tokensInfo = this.tokensInfoService.get(id);
		}
	}
	/**
	 * @return the tokensInfo
	 */
	public TokensInfo getTokensInfo() {
		return tokensInfo;
	}
	/**
	 * @param TokensInfo
	 *            the tokensInfo to set
	 */
	public void setTokensInfo(TokensInfo tokensInfo) {
		this.tokensInfo = tokensInfo;
	}
}

