/*
 
 * Package:com.rbt.action
 * FileName: SpikegoodsAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.CategoryFuc;
import com.rbt.model.Goods;
import com.rbt.model.Spikegoods;
import com.rbt.service.IGoodsService;
import com.rbt.service.ISpikegoodsService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 秒杀商品action类
 * @author 创建人 HZX
 * @date 创建日期 Fri Mar 29 15:32:29 CST 2014
 */
@Controller
public class SpikegoodsAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Spikegoods spikegoods;
	private Goods goods;
	/*******************业务层接口****************/
	@Autowired
	private ISpikegoodsService spikegoodsService;
	@Autowired
	private IGoodsService goodsService;
	
	/*********************集合********************/
	public CategoryFuc  categoryFuc;
	//秒杀商品
	public List spikegoodsList;
	
	/*********************字段********************/
	public String spikegoods_sortno_id;//批量排序
	public String isort_no;////排序
	public String reason;//操作流理由
	public String info_state_s;//审核状态
	public String goods_name;//商品名称
	public String cat_String;//转化为具体的分类
	public String cust_name_s;//会员名称
	public String st_put_date_s;
	public String en_put_date_s;
	public String st_en_date_s;
	public String en_en_date_s;
	public String hidden_goodsname;//商品名称
	public String spike_title_s;//秒杀标题
	public String cat_attr_s;//分类搜索
	

	/**
	 * 方法描述：返回新增秒杀商品页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	
	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 3:00:43 PM
	 * @Method Description :验证数据
	 */
	private String commonCheck(){
		//设置list_img
		String list_img;
		String img_paths=spikegoods.getImg_path();
		if(!ValidateUtil.isRequired(img_paths)){
			list_img=judgeList_img(img_paths);
			if(!ValidateUtil.isRequired(list_img)){
				spikegoods.setList_img(list_img);
			}
		}
		// 保存分类cat_attr
		selectCat();
		this.spikegoods.setCat_attr(cat_attr);
		if(spikegoods.getGoods_id()==null || spikegoods.getGoods_id().equals("")){
			this.addFieldError("spikegoods.goods_id","请添加商品！");
		}
		if(spikegoods.getImg_path().length()>600){
			this.addFieldError("spikegoods.img_path","您上传的图片太多，建议上传不超过10张！");
		}
		//判断会员发布秒杀商品是否审核
		String info_state=spikegoods.getInfo_state();
		if(this.session_cust_type.equals(Constants.MEMBER_TYPE)){
			if(cfg_IsAuditMiaoSha.equals("0")){
				info_state="0";
			}else{
				info_state="1";
			}
		}
		spikegoods.setInfo_state(info_state);
		return info_state;
	}
	
	/**
	 * 方法描述：新增秒杀商品
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		String info_state = commonCheck();
		spikegoods.setCust_id(this.session_cust_id);
		spikegoods.setUser_id(this.session_user_id);
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			spikegoods.setSort_no("0");
		}
		super.commonValidateField(spikegoods);
		if(super.ifvalidatepass){
			return add();
		}
		String id =this.spikegoodsService.insertGetPk(spikegoods);
		//秒杀商品数据操作流
		addAuditRecord(id,"spikegoods",info_state,"新增秒杀商品");
		this.addActionMessage("新增秒杀商品成功！");
		this.spikegoods = null;
		this.hidden_goodsname=null;
		this.cat_attr=null;
		return INPUT;
	}
	
	/**
	 * 方法描述：修改秒杀商品信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String id=spikegoods.getTrade_id();
		String info_state = commonCheck();
		super.commonValidateField(spikegoods);
		if(super.ifvalidatepass){
			return view();
		}
		this.spikegoodsService.update(spikegoods);
		//秒杀商品数据操作流
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			addAuditRecord(id,"spikegoods",info_state,"修改秒杀商品");
		}else{
			if (info_state.equals("1")) {
				reason = "审核通过状态";
			}
			addAuditRecord(id,"spikegoods",info_state,reason);
		}
		this.addActionMessage("修改秒杀商品成功！");
		return list();
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 3:25:39 PM
	 * @Method Description :公共删除
	 */
	private void commonDelete(){
		boolean flag = this.spikegoodsService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除秒杀商品成功");
		}else{
			this.addActionMessage("删除秒杀商品失败");
		}
	}
	
	
	/**
	 * 方法描述：删除秒杀商品信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDelete();
		return list();
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 3:37:55 PM
	 * @Method Description :秒杀商品审核页删除
	 */
	public String auditDelete() throws Exception {
		commonDelete();
		return auditList();
	}
	
	
	
	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 3:26:19 PM
	 * @Method Description :公共查询
	 */
	private void commonList(Map pageMap)throws Exception{
		// 获取搜索的分类
		if (info_state_s != null && !info_state_s.equals("")){
			pageMap.put("info_state_s", info_state_s);
		}
		if (cat_attr_s != null && !cat_attr_s.equals("")) {
			pageMap.put("cat_attr", cat_attr_s);
		}
		if (st_put_date_s != null && !st_put_date_s.equals("")){
			pageMap.put("st_put_date", st_put_date_s);
		}
		if (en_put_date_s != null && !en_put_date_s.equals("")){
			pageMap.put("en_put_date", en_put_date_s);
		}
		if (st_en_date_s != null && !st_en_date_s.equals("")){
			pageMap.put("st_en_date", st_en_date_s);
		}
		if (en_en_date_s != null && !en_en_date_s.equals("")){
			pageMap.put("en_en_date", en_en_date_s);
		}
		if (cust_name_s != null && !cust_name_s.equals("")){
			pageMap.put("cust_name",cust_name_s);
		}
		if (spike_title_s != null && !spike_title_s.equals("")){
			pageMap.put("spike_title",spike_title_s);
		}
		//根据页面提交的条件找出信息总数
		int count=this.spikegoodsService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		spikegoodsList =this.spikegoodsService.changList(this.spikegoodsService.getList(pageMap));
	}
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			pageMap.put("info_state","1,3");
		}else{
			pageMap.put("cust_id",this.session_cust_id);
		}
		commonList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * @Method Description :  运营商审核秒杀商品列表
	 * @author : HZX
	 * @date : Mar 29, 2014 10:14:54 AM
	 */
	@SuppressWarnings("unchecked")
	public String auditList() throws Exception {
		Map pageMap = new HashMap();
		pageMap.put("info_state","0,2");
		commonList(pageMap);
		return goUrl("auditindex");
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 3:51:33 PM
	 * @Method Description : 查看详情
	 */
	private boolean commonView(String id){
		if(id==null || id.equals("")){
			return true;
		}else{
			if(spikegoods.getGoods_id()!=null && !spikegoods.getGoods_id().equals("")){
				goods=this.goodsService.getByPkNoDel(spikegoods.getGoods_id());
				if(goods!=null && goods.getGoods_name()!=null){
					hidden_goodsname = goods.getGoods_name();
					return false;
				}else{
					hidden_goodsname = null;
					return false;
				}
			}
			
		}
		return true;
	}
	
	
	/**
	 * 方法描述：根据主键找出秒杀商品信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.spikegoods.getTrade_id();
		if(commonView(id)){
			return list();
		}
		viewCat(spikegoods.getCat_attr());
		//获取审核列表
		searchAuditList(id,"spikegoods");
		return goUrl(VIEW);
	}
	/**
	 * 方法描述：跳转到审核页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String audit() throws Exception {
		String id = this.spikegoods.getTrade_id();
		if(commonView(id)){
			return list();
		}
		//得到所属分类字符串
		String cat_id = spikegoods.getCat_attr();
		//转化为具体的分类
		cat_String = categoryFuc.getCateNameByMap(cat_id);
		//获取审核列表
		searchAuditList(id,"spikegoods");
		return goUrl("audit");
	}
	
	/**
	 * @Method Description :审核秒杀商品
	 * @author : HZX
	 * @date : Mar 29, 2014 10:32:55 AM
	 */
	public String auditState() throws Exception {
		String info_state = this.spikegoods.getInfo_state();
		String id = this.spikegoods.getTrade_id();
		if (id == null || id.equals("")) {
			return auditList();
		}
		if (info_state.equals("1")) {
			reason = "审核通过";
		}else if (info_state.equals("2")) {
			if(reason.equals("")){
				this.addFieldError("spikegoods.info_state", "您还未填写审核不通过理由！");
			}
		}else{
			this.addFieldError("spikegoods.info_state", "审核状态不能为空！");
		}
		if(super.ifvalidatepass){
			return audit();
		}
		// 获取数据库对象
		Spikegoods gd = this.spikegoodsService.get(id);
		gd.setInfo_state(info_state);
		// 更新秒杀商品数据
		this.spikegoodsService.update(gd);
		// 新增审核秒杀商品数据操作流
		addAuditRecord(id,"spikegoods", info_state,reason);
		this.addActionMessage("审核秒杀商品成功");
		return auditList();
	}
	
	/**
	 * 方法描述：批量排序
	 * 
	 * @return
	 * @throws Exception
	 */

	public String updateSort() throws Exception {
		commonSort();
		return list();
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 3:45:04 PM
	 * @Method Description :公用排序
	 */
	private void commonSort(){
		boolean flag = this.spikegoodsService.updateSort("trade_id", "sort_no",chb_id, sort_val);
		if(flag){
			this.addActionMessage("秒杀商品排序成功");
		}else{
			this.addActionMessage("秒杀商品排序失败");
		}
	}
	
	/**
	 * 方法描述:审核批量排序
	 * 
	 * @return
	 * @throws Exception
	 */
	public String auditUpdateSort() throws Exception {
		commonSort();
		return auditList();
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(spikegoods == null){
			spikegoods = new Spikegoods();
		}
		String id = this.spikegoods.getTrade_id();
		if(!this.validateFactory.isDigital(id)){
			spikegoods = this.spikegoodsService.get(id);
		}
	}

	public Spikegoods getSpikegoods() {
		return spikegoods;
	}

	public void setSpikegoods(Spikegoods spikegoods) {
		this.spikegoods = spikegoods;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

}

