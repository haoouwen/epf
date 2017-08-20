/*
 
 * Package:com.rbt.action
 * FileName: ShopconfigAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.action.AdminBaseAction;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Aftersales;
import com.rbt.model.Commpara;
import com.rbt.model.Member;
import com.rbt.model.Memberchannel;
import com.rbt.model.Memberchconfig;
import com.rbt.model.Printstyle;
import com.rbt.model.Printstyletem;
import com.rbt.model.Shopconfig;
import com.rbt.service.IAftersalesService;
import com.rbt.service.ICommparaService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberchannelService;
import com.rbt.service.IPrintstyleService;
import com.rbt.service.IPrintstyletemService;
import com.rbt.service.IShopconfigService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录商城设置信息action类
 * @author 创建人 HZX
 * @date 创建日期 Fri Jan 04 10:10:44 CST 2014
 */
@Controller
public class ShopconfigAction extends AdminBaseAction implements Preparable {
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Shopconfig shopconfig;
	private Commpara commpara;
	private Member member;
	private Memberchannel memberchannel;
	private Memberchconfig memberchconfig;
	private Aftersales aftersales;
	private Printstyle printstyle;
	private Printstyletem printstyletem;
	
	/*******************业务层接口****************/

	@Autowired
	private IShopconfigService shopconfigService;
	@Autowired
	private ICommparaService commparaService;
	@Autowired
	private IMemberchannelService memberchannelService;
	@Autowired
	private IMemberService memberService;
	@Autowired
	private IAftersalesService aftersalesService;
	@Autowired
	private IPrintstyleService printstyleService;
	@Autowired
	private IPrintstyletemService printstyletemService;
	/*********************集合********************/
	public List shopconfigList;
	public List commparaList;
	public List memberList;
	public List shoptradeList;
	public List memberchannelList;
	public List memberchconfigList;
	public List aftersalesList;
	public List printstyleList;
	public List printstyletemList;
	public List audithistoryList;
	/*********************字段********************/
	public String shopconfig_area_attr;//店铺所属地区
	public String shop_name_s;//店铺名称
	public String info_state_s;//店铺状态
	public String is_colse_s;//是否关闭
	public String is_update;//是否更新
	public String ss_user_name;//店主会员名
	public String reason;//原因
	public String cust_name;//店主会员名
	public String mem_cust_name;//店主会员名
	public String sale_time_start;//营业时间开始
	public String sale_time_end;//营业时间结束
	public String active_state;//店铺标识
	/**
	 * 方法描述：返回新增记录商城设置信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		
		return goUrl(ADD);
	}


	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 4:18:52 PM
	 * @Method Description :公共验证方法
	 */
	private void commonCheck(){
		
		// 用于所属地区的回选开始
		selectArea();
		// 验证地区是选择
		validateAreaIfSelect();
		this.shopconfig.setArea_attr(area_attr);
		this.shopconfig.setCust_id(this.session_cust_id);
		
	}
	
	
	/**
	 * 方法描述：新增记录商城设置信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		commonCheck();
		
		// 新增店铺审核数据操作流
		this.shopconfigService.insert(shopconfig);
		this.addActionMessage("新增地址信息成功！");
		this.shopconfig = null;
		return list();
	}

	/**
	 * 方法描述：修改记录商城设置信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		// 验证地区是选择
		validateAreaIfSelect();
		
		//保存地区
        if(area_attr==""||area_attr==null){
			this.addFieldError("areaDiv", "请选择地区");
		}else {
			this.shopconfig.setArea_attr(area_attr);
		}
        this.shopconfigService.update(shopconfig);
		this.addActionMessage("保存地址成功!");
		return view();
		
	}

	/**
	 * 方法描述：根据主键找出店铺设置信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		shopconfig = this.shopconfigService.getByCustID("0");
		area_attr=shopconfig.getArea_attr();
		area_attr = "9999999999," + area_attr;
		return goUrl(VIEW);
	}

	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		commonList();
		return goUrl(INDEXLIST);
	}

	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 4:27:14 PM
	 * @Method Description :公共查询列表
	 */
	private void commonList(){
		Map pageMap = new HashMap();
		if (shop_name_s != null && !shop_name_s.equals("")) {
			pageMap.put("shop_name", shop_name_s);
		}
		// 根据页面提交的条件找出信息总数
		int count = this.shopconfigService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		shopconfigList = this.shopconfigService.getList(pageMap);
		shopconfigList = ToolsFuc.replaceList(shopconfigList, "");
	}
	

	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 4:52:29 PM
	 * @Method Description :删除店铺信息
	 */
	private void commonDelete(){
		boolean flag = this.shopconfigService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除店铺信息成功");
		}else{
			this.addActionMessage("删除店铺信息失败");
		}
	}


	/**
	 * 方法描述：删除店铺设置信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDelete();
		return list();
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (shopconfig == null) {
			shopconfig = new Shopconfig();
		}
		String id = this.shopconfig.getShop_id();
		if (!this.validateFactory.isDigital(id)) {
			shopconfig = this.shopconfigService.get(id);
		}
	}

	public Commpara getCommpara() {
		return commpara;
	}

	public void setCommpara(Commpara commpara) {
		this.commpara = commpara;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Memberchannel getMemberchannel() {
		return memberchannel;
	}

	public void setMemberchannel(Memberchannel memberchannel) {
		this.memberchannel = memberchannel;
	}

	public Memberchconfig getMemberchconfig() {
		return memberchconfig;
	}

	public void setMemberchconfig(Memberchconfig memberchconfig) {
		this.memberchconfig = memberchconfig;
	}
	public Aftersales getAftersales() {
		return aftersales;
	}

	public void setAftersales(Aftersales aftersales) {
		this.aftersales = aftersales;
	}

	public Printstyle getPrintstyle() {
		return printstyle;
	}

	public void setPrintstyle(Printstyle printstyle) {
		this.printstyle = printstyle;
	}

	public Printstyletem getPrintstyletem() {
		return printstyletem;
	}

	public void setPrintstyletem(Printstyletem printstyletem) {
		this.printstyletem = printstyletem;
	}


	public Shopconfig getShopconfig() {
		return shopconfig;
	}


	public void setShopconfig(Shopconfig shopconfig) {
		this.shopconfig = shopconfig;
	}

}
