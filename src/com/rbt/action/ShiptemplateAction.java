/*
 
 * Package:com.rbt.action
 * FileName: ShiptemplateAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rbt.action.AdminBaseAction;
import com.rbt.common.Constants;
import com.rbt.common.util.JsonUtil;
import com.rbt.function.AreaFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Areaset;
import com.rbt.model.Shiptemplate;
import com.rbt.service.IAreaService;
import com.rbt.service.IAreasetService;
import com.rbt.service.ICommparaService;
import com.rbt.service.ISendshipmodeService;
import com.rbt.service.IShiptemplateService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录运费模板信息action类
 * @author 创建人 LJQ
 * @date 创建日期 Thu May 24 15:00:05 CST 2014
 */
@Controller
public class ShiptemplateAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	
	private Shiptemplate shiptemplate;
	private Areaset areaset;
	/*******************业务层接口****************/
	@Autowired
	private IShiptemplateService shiptemplateService;
	@Autowired
	public ISendshipmodeService sendshipmodeService;	
	@Autowired
	private IAreaService areaService;
	@Autowired
	public ICommparaService commparaService;
	@Autowired
	public IAreasetService areasetService;
	
	/*********************集合********************/
	public List shiptemplateList;
	public List commparalist;
	public List sendmodeList;
	public List areaList = new ArrayList();
	public List shipList;
	/*********************字段********************/
	
	public String smode_id;//配送方式串
	public String default_ship;//默认模板
	public String first_weight;//首重
	public String first_price; //首重收费
	public String cont_weight;//续重
	public String cont_price;//续费
	public String smode_num;
	public String end_area_str;
	public String smodeName;
	public String is_update;//是否更新
	public String is_smode_attr;
	public String is_insert;//是否新增
	public String cs_smode_id;
	public String custId;
	public String ship_name;//模板名称
	public String ship_name_s;
	public String valuation_mode_s;
	public String sel_value;


	/**
	 * 方法描述：返回新增记录运费模板信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		infoMsg();
		//清空
		if(is_insert==null){
			is_smode_attr=null;
			this.shiptemplate.setSmode_attr("");
		}
		// 未成功插入时，回选地区
		selectArea();
		return goUrl(ADD);
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 15, 2014 2:41:04 PM
	 * @Method Description : 根据ID获取子地区
	 */
	private void infoMsg(){	
		areaList=AreaFuc.three_areaList;
		if(AreaFuc.three_areaList==null){
			areaList=this.shiptemplateService.getChinaListByidStr(Constants.UP_AREA_ID);
			AreaFuc.three_areaList=areaList;
		}
		Map map=new HashMap();
		sendmodeList=this.sendshipmodeService.getList(map);		
		commparalist = commparaService.getCommparaList("area_type");
	}
	/**
	 * 方法描述：新增记录运费模板信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {		
		dealData();
		String v_id=shiptemplate.getValuation_mode();	
		shiptemplate.setCust_id(this.session_cust_id);
		super.commonValidateField(shiptemplate);
		if(super.ifvalidatepass){
			return add();
		}	
		//插队表中
		this.shiptemplateService.insertShipMode(shiptemplate,shipList);
		this.addActionMessage("新增记录运费模板信息成功！");
		shiptemplate=new Shiptemplate();
		shiptemplate.setValuation_mode(v_id);
		this.shipList=null;
		return add();
	}
	
	
	
	/**
	 * @author : LJQ
	 * @date : Jun 5, 2014 2:25:44 PM
	 * @Method Description :插入数据与更新数据的方法
	 */
	public void dealData(){
		// 验证地区是选择
		// validateAreaIfSelect();
		if(!area_attr.equals("0"))
		 shiptemplate.setStart_area(area_attr);
		//配送方式串
		if(smode_id!=null&&!smode_id.equals("")){
			is_smode_attr=smode_id.replace(" ","");
			shiptemplate.setSmode_attr(is_smode_attr);
		}
		//配送到其它地区表设置
		shipList=new ArrayList();
		String areasetstr="";
		String fw_note="",fp_note="",cw_note="",cp_note="";
		String shiptype=shiptemplate.getValuation_mode();
		
		if(shiptype.equals("1")){
			fw_note="首件";
			cw_note="续件";
		}else if(shiptype.equals("2")){
			fw_note="首重";
			cw_note="续重";
		}else if(shiptype.equals("3")){
			fw_note="首体积";
			cw_note="续体积";
		}		
		fp_note="首费";
		cp_note="续费";
		
		if(default_ship!=null){
			String[] d_ship=default_ship.split(",");
			String[] f_weight=first_weight.split(",");
			String[] f_price=first_price.split(",");
			String[] c_weight=cont_weight.split(",");
			String[] c_price=cont_price.split(",");
			String[] s_mname=smodeName.split(",");
			String[] csi=cs_smode_id.split(",");
			
			String[] s_attr=is_smode_attr.split(",");
			String[] s_num=smode_num.split(",");
			String[] s_area=end_area_str.split(",");
			
			int plus_size=0;	
			int k_num=0;
			for(int i=0;i<s_attr.length;i++){
				//计算起始的位置				
				int c_posi=0;
				for(int l=0;l<csi.length;l++){
					if(csi[l].trim().equals(s_attr[i].trim())){
						c_posi=l;
						k_num+=Integer.parseInt((s_num[c_posi]).trim());
					}
				}
				//获取当前对应的配送方式的长度
				int attr_length=Integer.parseInt((s_num[c_posi]).trim());
				for(int j=(k_num-attr_length);j<k_num;j++){
					Map listMap=new HashMap();
					listMap.put("d_ship", d_ship[j].trim());
					listMap.put("f_weight", f_weight[j].trim());
					listMap.put("f_price", f_price[j].trim());
					listMap.put("c_weight", c_weight[j].trim());
					listMap.put("c_price", c_price[j].trim());
					listMap.put("c_mid", s_attr[i].trim());
					listMap.put("c_num", s_num[c_posi].trim());
					listMap.put("c_area",s_area[j].trim());
					listMap.put("c_area_attr",s_area[j].trim());
					
					//首重量
					if(ValidateUtil.isDouble(f_weight[j].trim())){
						if(areasetstr.equals("")){
							areasetstr=s_mname[c_posi]+fw_note+"格式不正确";										
						}
					}
					if(f_weight[j].trim().length()>8){
						if(areasetstr.equals("")){
							areasetstr=s_mname[c_posi]+fw_note+"不能超过8位";										
						}
					}
					//首重运费
					if(ValidateUtil.isRmb(f_price[j].trim())){
						if(areasetstr.equals("")){
							areasetstr=s_mname[c_posi]+fp_note+"格式不正确";
						}
					}
					if(f_price[j].trim().length()>8){
						if(areasetstr.equals("")){
							areasetstr=s_mname[c_posi]+fp_note+"不能超过8位";										
						}
					}
					//续重量
					if(ValidateUtil.isDouble(c_weight[j].trim())){
						if(areasetstr.equals("")){
							areasetstr=s_mname[c_posi]+cw_note+"格式不正确";
						}					
					}		
					if(c_weight[j].trim().length()>8){
						if(areasetstr.equals("")){
							areasetstr=s_mname[c_posi]+cw_note+"不能超过8位";										
						}
					}
					//续重运费
					if(ValidateUtil.isRmb(c_price[j].trim())){
						if(areasetstr.equals("")){
							areasetstr=s_mname[c_posi]+cp_note+"格式不正确";
						}
					}
					if(c_price[j].trim().length()>8){
						if(areasetstr.equals("")){
							areasetstr=s_mname[c_posi]+cp_note+"不能超过8位";										
						}
					}
					
					if(j==(k_num-1)){
						plus_size=plus_size+Integer.parseInt(s_num[c_posi].trim());
						listMap.put("c_size", plus_size);
					}				
					shipList.add(listMap);	
				}
			}
			shipList=ToolsFuc.replaceList(shipList,"");
		}
		if(!areasetstr.equals("")){
			this.addFieldError("areasetval",areasetstr);	
		}
		
	}

	/**
	 * 方法描述：修改记录运费模板信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		//保存地区
		selectArea();
		this.shiptemplate.setStart_area(area_attr);
		dealData();
		String v_id=shiptemplate.getValuation_mode();	
		//会员标识
		super.commonValidateField(shiptemplate);
		if(super.ifvalidatepass){
			return view();
		}	
		this.shiptemplateService.updateShipMode(shiptemplate,shipList);
		this.addActionMessage("修改记录运费模板信息成功！");
		return list();
	}
	
	/**
	 * 方法描述：删除记录运费模板信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.shiptemplateService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除记录运费模板信息成功");
		}else{
			this.addActionMessage("删除记录运费模板信息成功失败");
		}
		return list();
	}
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		//根据模板名称查询
		if (ship_name_s != null && !ship_name_s.equals("")) {
			pageMap.put("ship_name", ship_name_s);
		}
		//根据计价方式查询
		if (valuation_mode_s != null && !valuation_mode_s.equals("")) {
			pageMap.put("valuation_mode", valuation_mode_s);
		}
		if(custId!=null && !"".equals(custId)){
				pageMap.put("cust_id",custId);
		}
		else{
			if(this.session_cust_type.equals(Constants.MEMBER_TYPE)){
				pageMap.put("cust_id",this.session_cust_id);
			}
		}
		//根据页面提交的条件找出信息总数
		int count=this.shiptemplateService.getCount(pageMap);	
		//分页插件
		pageMap = super.pageTool(count,pageMap);		
		shiptemplateList = this.shiptemplateService.getList(pageMap);
		shiptemplateList = ToolsFuc.replaceList(shiptemplateList,"");
		return goUrl(INDEXLIST);
		
	}
	
	/**
	 * @author : LJQ
	 * @date : Feb 28, 2014 1:36:58 PM
	 * @Method Description : AJAX查找模板记录
	 */
	public void searchShip() throws IOException{
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//输出内容格式，兼容火狐输出XML格式
		response.setContentType( "text/html"); 
		Map shipMap=new HashMap();
		shipMap.put("cust_id", this.session_cust_id);
		if(ship_name!=null && !ship_name.equals("")){
			shipMap.put("ship_name",ship_name);
		}
		shipMap=ajaxMap(shipMap);
		int totalNum=this.shiptemplateService.getCount(shipMap);
		List list=this.shiptemplateService.getList(shipMap);
		list = ToolsFuc.replaceList(list, "");
		String jsonStr=pageList(list,totalNum);
		PrintWriter out = response.getWriter();
		// 如果列表存在数据则输出，否则则输出空
		out.write(jsonStr);	
	}
	
	
	
	
	
	/**
	 * 方法描述：根据主键找出记录运费模板信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(shiptemplate.getCust_id()!=null){
			if(accessControl(shiptemplate.getCust_id())){
				return list();
			}
		}
		String id = this.shiptemplate.getShip_id();
		String v_mode=this.shiptemplate.getValuation_mode();
		if(id==null || id.equals("")){
			return list();
		}else{
			shiptemplate = this.shiptemplateService.get(id);
		}			
		// 初始化加载信息
		infoMsg();	
		if(is_update==null){
			// 根据ID获取所属地区的串
			viewArea(this.shiptemplate.getStart_area());								
			//配送到其它地区表设置
			shipList=new ArrayList();
			Map map=new HashMap();		
			if(shiptemplate.getShip_id()!=null){
				map.put("ship_id", shiptemplate.getShip_id());
			}
			List list = this.areasetService.getList(map);
			//计算对应的配送方式底下区域设置的个数
			String c_num_str="";
			int c_len=0;
			for(int i=0;i<list.size();i++){
				Map listMap=new HashMap();
				listMap=(HashMap)list.get(i);		
				if(listMap.get("default_ship")!=null){
					String d_ship=listMap.get("default_ship").toString();	
					//当clen等于或者i等于列表长度时				
					if(d_ship.equals("0")){
						c_num_str+=(i-c_len)+",";
						c_len=i;
					}
					if(i==(list.size()-1)){
						c_num_str+=((i+1)-c_len)+",";
					}
				}			
			}
			String[] c_nums=c_num_str.split(",");
			// 重组用于回选的list
			int c_size=0;
			for(int j=0;j<c_nums.length-1;j++){
				c_size+=Integer.parseInt(c_nums[j+1]);
				for(int i=(c_size-Integer.parseInt(c_nums[j+1]));i<c_size;i++){
					Map listMap=new HashMap();
					Map shipMap=new HashMap();
					listMap=(HashMap)list.get(i);
					shipMap.put("c_num", c_nums[j+1]);
					if(listMap.get("end_area")!=null){
						String end_area=listMap.get("end_area").toString();
						shipMap.put("c_area", end_area);
						shipMap.put("c_area_attr", end_area);
					}
					if(listMap.get("default_ship")!=null){
						String d_ship=listMap.get("default_ship").toString();
						shipMap.put("d_ship", d_ship);					
					}
					if(listMap.get("first_weight")!=null){
						String f_weight=listMap.get("first_weight").toString();
						shipMap.put("f_weight", f_weight);
					}
					if(listMap.get("first_price")!=null){
						String f_price=listMap.get("first_price").toString();
						shipMap.put("f_price", f_price);
					}
					if(listMap.get("cont_weight")!=null){
						String c_weight=listMap.get("cont_weight").toString();
						shipMap.put("c_weight", c_weight);
					}
					if(listMap.get("cont_price")!=null){
						String c_price=listMap.get("cont_price").toString();
						shipMap.put("c_price", c_price);
					}
					if(listMap.get("smode_id")!=null){
						String c_mid=listMap.get("smode_id").toString();
						shipMap.put("c_mid",c_mid);
					}
					
					if(i==c_size-1){
						shipMap.put("c_size", c_size);
					}else{
						shipMap.put("c_size","");
					}
					shipList.add(shipMap);
					
				}
			}
			shipList=ToolsFuc.replaceList(shipList,"");
		}else{
			this.shiptemplate.setSmode_attr(is_smode_attr);
			this.shiptemplate.setValuation_mode(v_mode);
		}		
		//更新地区
		area_attr=shiptemplate.getStart_area();
		selectArea();
		if(!ValidateUtil.isRequired(sel_value)){
			this.shiptemplate.setValuation_mode(sel_value);
		}
		return goUrl(VIEW);
	}
	
	/**
	 * @author : CYC
	 * @throws IOException 
	 * @date : May 17, 2014 4:48:58 PM
	 * @Method Description : 找出商品对应的配置模板
	 */
	public void getTemplate() throws IOException{
		//AJAX获取操作获取运费
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Map pageMap = new HashMap();
		pageMap.put("cust_id",this.session_cust_id);
		shiptemplateList = this.shiptemplateService.getList(pageMap);
		shiptemplateList = ToolsFuc.replaceList(shiptemplateList, "");
		JsonUtil json=new JsonUtil();
		String shopStr = json.list2json(shiptemplateList);
		out.write(shopStr);	
	}
	
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(shiptemplate == null){
			shiptemplate = new Shiptemplate();
		}
		String id = this.shiptemplate.getShip_id();
		if(!this.validateFactory.isDigital(id)){
			shiptemplate = this.shiptemplateService.get(id);
		}
	}

	/**
	 * @return the ShiptemplateList
	 */
	public List getShiptemplateList() {
		return shiptemplateList;
	}
	
	/**
	 * @param shiptemplateList
	 *  the ShiptemplateList to set
	 */
	public void setShiptemplateList(List shiptemplateList) {
		this.shiptemplateList = shiptemplateList;
	}
	
	/**
	 * @return the shiptemplate
	 */
	public Shiptemplate getShiptemplate() {
		return shiptemplate;
	}
	/**
	 * @param Shiptemplate
	 *            the shiptemplate to set
	 */
	public void setShiptemplate(Shiptemplate shiptemplate) {
		this.shiptemplate = shiptemplate;
	}

	public Areaset getAreaset() {
		return areaset;
	}

	public void setAreaset(Areaset areaset) {
		this.areaset = areaset;
	}

	public String getSmode_id() {
		return smode_id;
	}

	public void setSmode_id(String smode_id) {
		this.smode_id = smode_id;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}
	

}

