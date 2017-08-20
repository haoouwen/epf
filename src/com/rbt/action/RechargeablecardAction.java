/*
 * Package:com.rbt.action
 * FileName: RechargeablecardAction.java 
 */
package com.rbt.action;

import java.text.SimpleDateFormat;
import java.util.*;

import com.rbt.action.BaseAction;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Rechargeablecard;
import com.rbt.model.Rechargeablecards;
import com.rbt.service.IRechargeablecardService;
import com.rbt.service.IRechargeablecardsService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 充值卡action类
 * @author 创建人 CYC
 * @date 创建日期 Sun Oct 11 11:07:29 CST 2015
 */
@Controller
public class RechargeablecardAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 充值卡对象
	 */
	private Rechargeablecard rechargeablecard;
	private Rechargeablecards rechargeablecards;
	/*******************业务层接口****************/
	/*
	 * 充值卡业务层接口
	 */
	@Autowired
	private IRechargeablecardService rechargeablecardService;
	/*
	 * 充值卡业务层接口
	 */
	@Autowired
	private IRechargeablecardsService rechargeablecardsService;
	
	/*********************集合*******************/
	/*
	 * 充值卡信息集合
	 */
	public List rechargeablecardList;
	public List excardList;
	/*********************字段*******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	public String cardid;//充值卡编号
	public String exccardid;//导出ID
	
		
	/**
	 * 方法描述：返回新增充值卡页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增充值卡
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(rechargeablecard);
		if(super.ifvalidatepass){
			return add();
		}
		if(!isInteger(rechargeablecard.getCardmoney())){
			this.addFieldError("rechargeablecard.cardmoney", "请输入数字!");
			return add();
		}
		
		if(!isInteger(rechargeablecard.getCardnum())){
			this.addFieldError("rechargeablecard.cardnum", "请输入数字!");
			return add();
		}
		
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHmmss");//可以方便地修改日期格式
		String timestamp = dateFormat.format( now ); 
		rechargeablecard.setCardid(timestamp);
		rechargeablecard.setCardused("0");
		rechargeablecard.setCardstate("0");//0表示未生成充值卡
		
		this.rechargeablecardService.insert(rechargeablecard);
		this.addActionMessage("新增充值卡成功！");
		this.rechargeablecard = null;
		return INPUT;
	}
	
	 public static boolean isInteger(String value) {
		  try {
		   Integer.parseInt(value);
		   return true;
		  } catch (NumberFormatException e) {
		   return false;
		  }
		 }
	
	
	public  String createcard() throws Exception{
		//判断是否对象不为空
		String id = this.rechargeablecard.getCardid();
		if(id==null || id.equals("")){
			rechargeablecard = new Rechargeablecard();
		}else{
			rechargeablecard = this.rechargeablecardService.get(id);
		}
		if(rechargeablecard!=null&&"0".equals(rechargeablecard.getCardstate())){
			String cardnum = rechargeablecard.getCardnum();
			int num = Integer.parseInt(cardnum);
			//批量生成充值卡和卡密
			for(int i =0;i<num;i++){
				//随机生成4位随机码
				String random_num = RandomStrUtil.getCardnumRand() +"-"+RandomStrUtil.getCardnumRand()+"-"+RandomStrUtil.getCardnumRand()+"-"+RandomStrUtil.getCardnumRand();
				rechargeablecards = new Rechargeablecards();
				rechargeablecards.setCardid(rechargeablecard.getCardid());
				rechargeablecards.setCardskey(random_num);
				rechargeablecards.setCardsmoney(rechargeablecard.getCardmoney());
				rechargeablecards.setCardsdate(rechargeablecard.getCarddate());
				rechargeablecards.setCardsstate("0");
				rechargeablecardsService.insert(rechargeablecards);
			}
			//把充值卡表的是否生成状态改掉
			rechargeablecard.setCardstate("1");
			rechargeablecardService.update(rechargeablecard);
		}
		
		this.addActionMessage("充值卡生成成功！");
		this.rechargeablecard = null;
		return list();
		
	}

	/**
	 * 方法描述：修改充值卡信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		   super.commonValidateField(rechargeablecard);
			if(super.ifvalidatepass){
				return view();
			}
			if(!isInteger(rechargeablecard.getCardmoney())){
				this.addFieldError("rechargeablecard.cardmoney", "请输入数字!");
				return view();
			}
			
			if(!isInteger(rechargeablecard.getCardnum())){
				this.addFieldError("rechargeablecard.cardnum", "请输入数字!");
				return view();
			}
		this.rechargeablecardService.update(rechargeablecard);
		this.addActionMessage("修改充值卡成功！");
		return list();
	}
	
	
	/**
	 * @Method Description : 导出统计信息
	 * @param 
	 * @return return_type
	 */
	public String exportcardInfo() throws Exception{
		Map pageMap = new HashMap();
		pageMap.put("exccardid", exccardid);
		excardList = this.rechargeablecardsService.getList(pageMap);
		excardList = ToolsFuc.replaceList(excardList, "");
		if(this.rechargeablecardsService.exprotcradExcel(excardList, getResponse())) {
			   this.addActionMessage("数据导出成功！");	
			}else {
			   this.addActionMessage("数据导出成功！");
			}
	   
	    return list();
	}
	/**
	 * 方法描述：删除充值卡信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除充值卡信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.rechargeablecardService.delete(chb_id);
		this.rechargeablecardsService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除充值卡信息成功!");
		}else{
			this.addActionMessage("删除充值卡信息失败!");
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
		if(cardid!=null&&!"".equals(cardid)){
			pageMap.put("cardid", cardid);
		}
		//根据页面提交的条件找出信息总数
		int count=this.rechargeablecardService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		rechargeablecardList = this.rechargeablecardService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出充值卡信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.rechargeablecard.getCardid();
		if(id==null || id.equals("")){
			rechargeablecard = new Rechargeablecard();
		}else{
			rechargeablecard = this.rechargeablecardService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the RechargeablecardList
	 */
	public List getRechargeablecardList() {
		return rechargeablecardList;
	}
	/**
	 * @param rechargeablecardList
	 *  the RechargeablecardList to set
	 */
	public void setRechargeablecardList(List rechargeablecardList) {
		this.rechargeablecardList = rechargeablecardList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(rechargeablecard == null){
			rechargeablecard = new Rechargeablecard();
		}
		String id = this.rechargeablecard.getCardid();
		if(!this.validateFactory.isDigital(id)){
			rechargeablecard = this.rechargeablecardService.get(id);
		}
	}
	/**
	 * @return the rechargeablecard
	 */
	public Rechargeablecard getRechargeablecard() {
		return rechargeablecard;
	}
	/**
	 * @param Rechargeablecard
	 *            the rechargeablecard to set
	 */
	public void setRechargeablecard(Rechargeablecard rechargeablecard) {
		this.rechargeablecard = rechargeablecard;
	}
}

