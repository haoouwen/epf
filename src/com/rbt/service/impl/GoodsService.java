/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: GoodsService.java 
 */
package com.rbt.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.dao.IAreaDao;
import com.rbt.dao.IAutoupgoodsDao;
import com.rbt.dao.ICartgoodsDao;
import com.rbt.dao.ICategoryDao;
import com.rbt.dao.ICollectDao;
import com.rbt.dao.IDepotDao;
import com.rbt.dao.IGoodfloormarkDao;
import com.rbt.dao.IGoodsDao;
import com.rbt.dao.IGoodsattrDao;
import com.rbt.dao.IGoodsbrandDao;
import com.rbt.dao.IInternationaltariffDao;
import com.rbt.dao.IMemberDao;
import com.rbt.dao.ISelfextendattrDao;
import com.rbt.dao.ISelfparagroupDao;
import com.rbt.dao.ISelfparavalueDao;
import com.rbt.dao.ISelfspecnameDao;
import com.rbt.dao.ISelfspecvalueDao;
import com.rbt.dao.IShiptemplateDao;
import com.rbt.dao.IShopconfigDao;
import com.rbt.dao.ISpecnameDao;
import com.rbt.dao.ISpecvalueDao;
import com.rbt.dao.ITaxrateDao;
import com.rbt.function.CategoryFuc;
import com.rbt.function.KeyWordInsertFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Autoupgoods;
import com.rbt.model.Cartgoods;
import com.rbt.model.Category;
import com.rbt.model.Depot;
import com.rbt.model.Goods;
import com.rbt.model.Goodsattr;
import com.rbt.model.Internationaltariff;
import com.rbt.model.Selfextendattr;
import com.rbt.model.Selfparagroup;
import com.rbt.model.Selfparavalue;
import com.rbt.model.Selfspecname;
import com.rbt.model.Selfspecvalue;
import com.rbt.model.Shiptemplate;
import com.rbt.model.Shopconfig;
import com.rbt.model.Taxrate;
import com.rbt.service.IAreasetService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IShiptemplateService;

import net.sf.json.JSONArray;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @function 功能 记录商品表信息Service层业务接口实现
 * @author 创建人 LJQ
 * @date 创建日期 Tue Jan 15 10:28:08 CST 2014
 */
@Service
public class GoodsService extends GenericService<Goods, String> implements
		IGoodsService {

	IGoodsDao goodsDao;

	@Autowired
	private ISelfspecnameDao selfspecnameDao;
	@Autowired
	private ISelfspecvalueDao selfspecvalueDao;
	@Autowired
	private IGoodsattrDao goodsattrDao;
	@Autowired
	private IAutoupgoodsDao autoupgoodsDao;
	@Autowired
	private ISelfextendattrDao selfextendattrDao;
	@Autowired
	private ISelfparagroupDao selfparagroupDao;
	@Autowired
	private ISelfparavalueDao selfparavalueDao;
	@Autowired
	private ICategoryDao categoryDao;
	@Autowired
	private ISpecnameDao specnameDao;
	@Autowired
	private ISpecvalueDao specvalueDao;
	@Autowired
	private ICartgoodsDao cartgoodsDao;
	@Autowired
	private IMemberDao memberDao;
	@Autowired
	private IAreaDao areaDao;
	@Autowired
	private IShopconfigDao shopconfigDao;
	@Autowired
	private ITaxrateDao taxrateDao;
	@Autowired
	private ICollectDao collectDao;
	@Autowired
	private IGoodsbrandDao goodsbrandDao;
	@Autowired 
	IShiptemplateDao shiptemplateDao;
	@Autowired
	private IDepotDao depotDao;
	@Autowired
	IInternationaltariffDao internationaltariffDao;
	@Autowired
	private IGoodfloormarkDao goodfloormarkDao;
	
	
	private String table_name = "goods";
	private String cfg_sc_pointsrule = SysconfigFuc.getSysValue("cfg_sc_pointsrule");// 积分规则

	private List selfspecnameList;// 自定义规格名称相关
	private List selfsepcvalueList;// 自定义规格值相关

	/**
	 * @author : HZX
	 * @date : Sep 11, 2014 9:03:56 AM
	 * @Method Description :
	 */
	private static ApplicationContext context;

	static {
		if (context == null) {
			context = new ClassPathXmlApplicationContext(
					"applicationContext*.xml");
		}
	}

	public static ApplicationContext getContext() {
		return context;
	}

	@Autowired
	public GoodsService(IGoodsDao goodsDao) {
		super(goodsDao);
		this.goodsDao = goodsDao;
	}

	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 9:04:05 PM
	 * @Method Description :查询前台条数
	 */
	public int getWebCount(Map map) {
		return this.goodsDao.getWebCount(map);
	}

	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 9:04:21 PM
	 * @Method Description :查询前台列表
	 */
	public List getWebList(Map map) {
		return this.goodsDao.getWebList(map);
	}

	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 9:03:52 PM
	 * @Method Description :获取热门收藏
	 */
	public List getsumList(Map map) {
		return this.goodsDao.getsumList(map);
	}

	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 9:03:52 PM
	 * @Method Description :热销商品数据
	 */
	public List getHotSaleList(Map map) {
		return this.goodsDao.gethotsaleList(map);
	}

	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 9:04:36 PM
	 * @Method Description :上下架管理
	 */
	public boolean updateIsup(Goods goods, String state) {
		if (goods == null || goods.getGoods_id() == null) {
			return false;
		}
		String goods_id = goods.getGoods_id();
		if (goods_id == null || goods_id.equals("")) {
			return false;
		}
		goods_id = goods_id.replace(" ", "");
		String chidStr[] = goods_id.split(",");
		List chList = new ArrayList();
		if (chidStr.length > 0) {
			for (int i = 0; i < chidStr.length; i++) {
				if (chidStr[i].equals("")) {
					continue;
				}
				Map linkMap = new HashMap();
				linkMap.put("is_up", state);
				linkMap.put("goods_id", chidStr[i]);
				chList.add(linkMap);
			}
		}
		this.goodsDao.updateIsup(chList);
		return true;
	}

	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 9:04:36 PM
	 * @Method Description :商品逻辑删除
	 */
	public boolean updateIsdel(String chb_id, String state) {
		String id = chb_id;
		if (id == null || id.equals("")) {
			return false;
		}
		id = id.replace(" ", "");
		String chidStr[] = id.split(",");
		List chList = new ArrayList();
		if (chidStr.length > 0) {
			for (int i = 0; i < chidStr.length; i++) {
				if (chidStr[i].equals("")) {
					continue;
				}
				Map linkMap = new HashMap();
				linkMap.put("is_del", state);
				linkMap.put("goods_id", chidStr[i]);
				chList.add(linkMap);

			}
		}
		this.goodsDao.updateIsdel(chList);
		return true;
	}

	/**
	 * @author : LJQ
	 * @date : Apr 18, 2014 9:57:34 AM
	 * @Method Description :添加修改商品数据
	 */
	public void insertGoods(String goods_id, Goods goods, String random_num,
			List goodsattrList, String goods_item_str, String specstr_str,
			String market_price_str, String sale_price_str,
			String cost_price_str, String stock_str, String volume_str,
			String logsweight_str, String up_goods_str,
			String self_goods_size_value, String self_goods_img_value,
			String self_goods_relate_img_value, String self_goods_sort_value,
			String self_size_id, String sel_spec_name,
			String is_must_delete_spec, String sel_spec_count,
			String goods_up_str, String goods_down_str,
			Selfextendattr selfextendattr, String ex_attr_value,
			Selfparagroup selfparagroup, String para_num,
			Selfparavalue selfparavalue, String slef_para_value, String reason,
			String session_cust_id, String session_user_id,
			String session_user_name, Map commonMap) {
		String info_state = goods.getInfo_state();
		if (goods_id == null) {
			// 保存数据库
			goods_id = goodsDao.insertGetPk(goods);
		} else {
			this.goodsDao.update(goods);
		}
		// 保存FCK
		insertFckContent(goods.getCust_id(), table_name, goods_id, random_num);
		// 商品属性表数据处理
		goodsattrList = new ArrayList();
		if (goods_item_str == null) {
			return;
		}
		String[] goods_item = goods_item_str.split(",");
		String[] spec_attr = specstr_str.split(",");
		String[] market_price = market_price_str.split(",");
		String[] sale_price = sale_price_str.split(",");
		String[] cost_price = cost_price_str.split(",");
		String[] stock = stock_str.split(",");
		String[] volume = volume_str.split(",");
		String[] logsweight = logsweight_str.split(",");
		String[] up_goods = up_goods_str.split(",");

		// 保存属性
		for (int i = 0; i < goods_item.length; i++) {
			HashMap listMap = new HashMap();
			Goodsattr ga = new Goodsattr();
			ga.setGoods_id(goods_id);
			if (goods_item[i].trim().equals("")) {
				ga.setGoods_item(goods.getGoods_no());
				listMap.put("goods_item", goods.getGoods_no());
			} else {
				ga.setGoods_item(goods_item[i].trim());
				listMap.put("goods_item", goods_item[i].trim());
			}

			listMap.put("specstr_str", spec_attr[i].trim());
			listMap.put("market_price_str", market_price[i].trim());
			listMap.put("sale_price_str", sale_price[i].trim());
			listMap.put("cost_price_str", cost_price[i].trim());
			listMap.put("volume_str", volume[i].trim());
			listMap.put("logsweight_str", logsweight[i].trim());
			listMap.put("stock_str", stock[i].trim());
			listMap.put("sale_num", "0");
			listMap.put("up_goods_str", up_goods[i].trim());

			goodsattrList.add(i, listMap);

			ga.setSpecstr(spec_attr[i].trim());
			if (!market_price[i].trim().equals("")) {
				ga.setMarket_price(Double.parseDouble(market_price[i].trim()));
			}
			if (!sale_price[i].trim().equals("")) {
				ga.setSale_price(Double.parseDouble(sale_price[i].trim()));
			} else {
				continue;
			}
			if (!cost_price[i].trim().equals("")) {
				ga.setCost_price(Double.parseDouble(cost_price[i].trim()));
			}
			if (!stock[i].trim().equals("")) {
				if (Integer.parseInt(stock[i].trim()) <= 0) {
					ga.setStock("0");
				}
				ga.setStock(stock[i].trim());
			}
			ga.setSale_num("0");
			ga.setUp_goods(up_goods[i].trim());
			ga.setVolume(volume[i].trim());
			ga.setLogsweight(logsweight[i].trim());

			this.goodsattrDao.insert(ga);
		}
		// 保存自定义规格名称
		String sepc_id_str = "";
		// 保存自定义规格值
		String[] self_goods_size_value_str = self_goods_size_value.split(",");
		String[] self_goods_img_value_str = self_goods_img_value.split(",");
		String[] self_goods_relate_img_value_str = self_goods_relate_img_value
				.split(",");
		String[] self_goods_sort_value_str = self_goods_sort_value.split(",");
		String[] self_size_id_str = self_size_id.split(",");
		if (sel_spec_name != null && !sel_spec_name.equals("")) {
			// 在自定义规格名称不为空时，则先删除数据库的数据
			/*
			 * if (is_must_delete_spec != null &&
			 * is_must_delete_spec.equals("1")) {
			 * this.selfspecnameDao.deleteByGoodsid(goods_id); }
			 */
			try {
				this.selfspecnameDao.deleteByGoodsid(goods_id);
			} catch (Exception e) {
				// TODO: handle exception
			}
			String[] sel_spec = sel_spec_name.split(",");
			String[] sel_specval_count = sel_spec_count.split(",");
			int k = 0, num = 0;
			for (int i = 0; i < sel_spec.length; i++) {
				if (sel_specval_count[i].trim().equals("")) {
					return;
				}
				// 第一个规格名称的个数
				num = Integer.parseInt(sel_specval_count[i].trim());
				// 规格值对象
				Selfspecname ssc = new Selfspecname();
				ssc.setSpec_code(sel_spec[i]);
				ssc.setGoods_id(goods_id);
				String spec_id = this.selfspecnameDao.insertGetPk(ssc);
				// 循环存入自定义规格值表
				for (int j = k; j < k + num; j++) {
					if (self_goods_sort_value_str[j] != null
							&& !self_goods_sort_value_str[j].equals("")) {
						Selfspecvalue selfspecvalue = new Selfspecvalue();
						selfspecvalue.setSpec_serial_id(spec_id);
						if (self_goods_size_value_str[j].trim() != null
								&& !self_goods_size_value_str[j].trim().equals(
										"0")) {
							selfspecvalue
									.setSelf_spec_value(self_goods_size_value_str[j]
											.trim());
						}
						if (self_goods_img_value_str[j].trim() != null
								&& !self_goods_img_value_str[j].trim().equals(
										"0")) {
							selfspecvalue
									.setSelf_spec_img(self_goods_img_value_str[j]
											.trim());
						}
						selfspecvalue.setSort_no(self_goods_sort_value_str[j]
								.trim());
						selfspecvalue
								.setSelf_img(self_goods_relate_img_value_str[j]
										.trim());
						selfspecvalue.setSelf_size_id(self_size_id_str[j]
								.trim());
						this.selfspecvalueDao.insert(selfspecvalue);
					}
				}
				k = k + num;
				// 规格名称ID串
				sepc_id_str += spec_id + ",";
			}
		}
		// sepc_id_str=backLastPosStr(sepc_id_str);
		// 查询规格名称表值
		// Map specNameMap=new HashMap();
		// specNameMap.put("goods_id", goods_id);
		// specselfNameList=this.selfspecnameService.getList(specNameMap);

		// 查询规格值表
		// Map specNameValueMap=new HashMap();
		// specNameValueMap.put("spec_serial_id", sepc_id_str);
		// specselfValueList=this.selfspecvalueService.getList(specNameMap);

		// 处理商品自动上下架信息
		if (goods_up_str != null && !goods_up_str.equals("")) {
			String[] goods_ups = goods_up_str.split("##########");
			String[] goods_downs = goods_down_str.split("##########");
			if (goods_ups.length > 0) {
				for (int i = 0; i < goods_ups.length; i++) {
					if (!goods_ups[i].trim().equals("")) {
						Autoupgoods autoupgoods = new Autoupgoods();
						autoupgoods.setCust_id(goods.getCust_id());
						autoupgoods.setGoods_id(goods_id);
						if (goods_ups[i] != null && !goods_ups[i].equals("")) {
							autoupgoods.setUp_time(goods_ups[i].trim());
						}
						if (goods_downs.length > 0) {
							if (goods_downs[i] != null
									&& !goods_downs[i].equals("")) {
								autoupgoods.setDown_time(goods_downs[i].trim());
							}
						}
						this.autoupgoodsDao.insert(autoupgoods);
					}
				}
			}
		}

		// 处理商品自定义属性
		if (selfextendattr != null) {
			String[] ex_attr_id_str = selfextendattr.getEx_attr_id().split(",");
			String[] ex_attr_alias_str = selfextendattr.getEx_attr_alias()
					.split(",");
			String[] ex_attr_value_str = ex_attr_value.split("##########");
			for (int i = 0; i < ex_attr_id_str.length; i++) {
				if (!ex_attr_id_str[i].trim().equals("")) {
					Selfextendattr sfa = new Selfextendattr();
					sfa.setEx_attr_id(ex_attr_id_str[i].trim());
					sfa.setGoods_id(goods_id);
					sfa.setEx_attr_alias(ex_attr_alias_str[i].trim());
					if ((i + 1) <= ex_attr_value_str.length
							&& ex_attr_value_str[i] != null
							&& !ex_attr_value_str[i].equals("")) {
						sfa.setEx_attr_value(ex_attr_value_str[i].trim());
					} else {
						sfa.setEx_attr_value("");
					}
					this.selfextendattrDao.insert(sfa);
				}
			}
		}

		// 处理商品自定义参数组表
		if (selfparagroup != null) {
			String[] para_group_str = selfparagroup.getPara_group_id().split(
					",");
			String[] para_group_sort_str = selfparagroup.getSort_no()
					.split(",");
			String[] para_num_str = para_num.split(",");
			// 自定义参数值
			if (selfparavalue == null) {
				return;
			}
			String[] para_id_str = selfparavalue.getPara_id().split(",");
			String[] para_sort_str = selfparavalue.getSort_no().split(",");
			String[] para_value = slef_para_value.split("##########");
			int k = 0, num = 0;
			for (int i = 0; i < para_group_str.length; i++) {
				if (para_group_str[i].trim().equals("")) {
					return;
				}
				Selfparagroup spg = new Selfparagroup();
				spg.setPara_group_id(para_group_str[i].trim());
				spg.setSort_no(para_group_sort_str[i].trim());
				spg.setGoods_id(goods_id);
				String self_para_group_id = this.selfparagroupDao
						.insertGetPk(spg);
				// 处理自定义参数值
				if (selfparavalue != null) {
					num = Integer.parseInt(para_num_str[i].trim());
					for (int j = k; j < k + num; j++) {
						Selfparavalue spv = new Selfparavalue();
						spv.setSlef_para_group_id(self_para_group_id);
						if ((j + 1) <= para_value.length
								&& para_value[j] != null
								&& !para_value[j].equals("")) {
							spv.setSlef_para_value(para_value[j].trim());
						} else {
							spv.setSlef_para_value("");
						}
						spv.setPara_id(para_id_str[j].trim());
						spv.setSort_no(para_sort_str[j].trim());
						this.selfparavalueDao.insert(spv);
					}
					k = k + num;
				}
			}
		}

		// 新增数据操作流
		insertAudit(table_name, goods_id, info_state, reason, session_cust_id,
				session_user_id, session_user_name);
	}

	/**
	 * @author : LJQ
	 * @date : Apr 18, 2014 1:09:41 PM
	 * @Method Description : 修改商品信息
	 */
	public void updateGoods(String goods_id, Goods goods, String random_num,
			List goodsattrList, String goods_item_str, String specstr_str,
			String market_price_str, String sale_price_str,
			String cost_price_str,String stock_str, String volume_str,
			String logsweight_str,String up_goods_str,
			String self_goods_size_value, String self_goods_img_value,
			String self_goods_relate_img_value, String self_goods_sort_value,
			String self_size_id, String sel_spec_name,
			String is_must_delete_spec, String sel_spec_count,
			String goods_up_str, String goods_down_str,
			Selfextendattr selfextendattr, String ex_attr_value,
			Selfparagroup selfparagroup, String para_num,
			Selfparavalue selfparavalue, String slef_para_value, String reason,
			String session_user_id, String session_user_name, Map commonMap) {
		// 删除之前的数据记录开始
		if (goods_id != null) {
			this.goodsattrDao.deleteByGoodsid(goods_id);
			this.autoupgoodsDao.deleteByGoodsId(goods_id);
			this.selfparagroupDao.deleteByGoodsid(goods_id);
			this.selfextendattrDao.deleteByGoodsid(goods_id);
		}
		// 调用新增方法
		insertGoods(goods_id, goods, random_num, goodsattrList, goods_item_str,
				specstr_str, market_price_str, sale_price_str, cost_price_str,
				stock_str, volume_str, logsweight_str, up_goods_str,
				self_goods_size_value, self_goods_img_value,
				self_goods_relate_img_value, self_goods_sort_value,
				self_size_id, sel_spec_name, is_must_delete_spec,
				sel_spec_count, goods_up_str, goods_down_str, selfextendattr,
				ex_attr_value, selfparagroup, para_num, selfparavalue,
				slef_para_value, reason, goods.getCust_id(), session_user_id,
				session_user_name, commonMap);
	}

	/**
	 * @author : LJQ
	 * @date : Apr 18, 2014 2:00:00 PM
	 * @Method Description :更新审核方法
	 */
	public void updateAuditState(Goods goods, String reason,
			String session_cust_id, String session_user_id,
			String session_user_name) {
		this.goodsDao.update(goods);
		// 新增数据操作流
		insertAudit("goods", goods.getGoods_id(), goods.getInfo_state(),
				reason, session_cust_id, session_user_id, session_user_name);
	}

	/**
	 * @author : LJQ
	 * @date : Apr 18, 2014 5:27:43 PM
	 * @Method Description : 新增虚拟商品
	 */
	public void insertVirtualGoods(Goods goods, String goods_up_str,
			String goods_down_str, String session_cust_id,
			String session_user_id, String session_user_name,
			String random_num, String reason, Double market_price_str,
			Double cost_price_str, Double sale_price_str) {

		// 保存数据库
		String goods_id = this.goodsDao.insertGetPk(goods);
		Goodsattr goodsattr = new Goodsattr();
		goodsattr.setMarket_price(market_price_str);
		goodsattr.setCost_price(cost_price_str);
		goodsattr.setSale_price(sale_price_str);
		goodsattr.setGoods_item(goods.getGoods_no());
		goodsattr.setGoods_id(goods_id);
		this.goodsattrDao.insert(goodsattr);
		autoUp(goods_up_str, goods_down_str, session_cust_id, goods_id);
		// 保存FCK
		insertFckContent(goods.getCust_id(), table_name, goods_id, random_num);
		// 新增数据操作流
		insertAudit(table_name, goods_id, goods.getInfo_state(), reason,
				session_cust_id, session_user_id, session_user_name);
	}

	/**
	 * @author : LJQ
	 * @date : Apr 18, 2014 6:10:27 PM
	 * @Method Description :更新虚拟商品
	 */
	public void updatetVirtualGoods(Goods goods, String goods_up_str,
			String goods_down_str, String session_cust_id,
			String session_user_id, String session_user_name,
			String random_num, String reason) {
		String goods_id = goods.getGoods_id();
		// 删除之前的数据记录开始
		this.goodsattrDao.deleteByGoodsid(goods_id);
		this.autoupgoodsDao.deleteByGoodsId(goods_id);
		this.goodsDao.update(goods);
		autoUp(goods_up_str, goods_down_str, session_cust_id, goods_id);
		// 保存FCK
		insertFckContent(goods.getCust_id(), table_name, goods_id, random_num);
		// 新增数据操作流
		insertAudit(table_name, goods_id, goods.getInfo_state(), reason,
				session_cust_id, session_user_id, session_user_name);
	}

	/**
	 * @author : LJQ
	 * @date : Apr 18, 2014 6:14:21 PM
	 * @Method Description :自动上下架
	 */
	private void autoUp(String goods_up_str, String goods_down_str,
			String session_cust_id, String goods_id) {
		// 处理商品自动上下架信息
		if (goods_up_str != null && !goods_up_str.equals("")) {
			String[] goods_ups = goods_up_str.split("##########");
			String[] goods_downs = goods_down_str.split("##########");
			if (goods_ups.length > 0) {
				for (int i = 0; i < goods_ups.length; i++) {
					if (!goods_ups[i].trim().equals("")) {
						Autoupgoods autoupgoods = new Autoupgoods();
						autoupgoods.setCust_id(session_cust_id);
						autoupgoods.setGoods_id(goods_id);
						if (goods_ups[i] != null && !goods_ups[i].equals("")) {
							autoupgoods.setUp_time(goods_ups[i].trim());
						}
						if (goods_downs.length > 0) {
							if (goods_downs[i] != null
									&& !goods_downs[i].equals("")) {
								autoupgoods.setDown_time(goods_downs[i].trim());
							}
						}
						this.autoupgoodsDao.insert(autoupgoods);
					}
				}
			}
		}
	}

	/**
	 * @Method Description :通过主键查找未删除的商品
	 * @author : HZX
	 * @date : Apr 19, 2014 10:54:59 AM
	 */
	public Goods getByPkNoDel(String goods_id) {
		return (Goods) this.goodsDao.getByPkNoDel(goods_id);
	}

	/**
	 * @author : LJQ
	 * @date : May 9, 2014 5:19:55 PM
	 * @Method Description :获取商品
	 */
	public List getAll(Map map) {
		return this.goodsDao.getAll(map);
	}

	/**
	 * @author : LJQ
	 * @date : May 9, 2014 5:20:02 PM
	 * @Method Description : 获取热门收藏排行
	 */
	public List getHotCollectList(String cust_id) {
		Map collmap = new HashMap();
		collmap.put("cust_id", cust_id);
		// 是否删除，状态为审核
		collmap.put("info_state", "1");
		collmap.put("is_del", "1");
		collmap.put("is_up", "0");
		// 热门收藏排行
		collmap.put("start", 0);
		collmap.put("limit", 9);
		List collectList = this.goodsDao.getsumList(collmap);
		return collectList;
	}

	/**
	 * @author : LJQ
	 * @date : May 9, 2014 5:26:15 PM
	 * @Method Description :热门销售
	 */
	public List getHotSaleList(String cust_id, String cat_attr) {
		Map salemap = new HashMap();
		// 判断shopconfig的cust_id是否为空
		if (cust_id != null && !cust_id.equals("")) {
			salemap.put("cust_id", cust_id);
		}
		// 判断同分类的id是否有，如果有就执行同分类销售排行
		if (cat_attr != null && !"".equals(cat_attr)) {
			if (cat_attr.indexOf(",") > -1) {
				cat_attr = cat_attr.substring(cat_attr.lastIndexOf(",") + 1,
						cat_attr.length());
			}
			salemap.put("cat_attr", cat_attr);
		}
		salemap.put("info_state", "1");
		salemap.put("is_del", "1");
		salemap.put("is_up", "0");
		salemap.put("start", 0);
		salemap.put("limit", "9");
		salemap.put("order_state", "7,8");
		List hotsaleList = this.goodsDao.gethotsaleList(salemap);
		return hotsaleList;
	}

	/**
	 * @author : LHY
	 * @date : Feb 26, 2014 10:53:52 AM
	 * @Method Description : 根据不同的商品类型，获取不同的数据
	 */
	public List getGoodsLabList(String cust_id, String label, int limit) {
		Map labMap = new HashMap();
		labMap.put("cust_id", cust_id);
		labMap.put("lab", label);
		labMap.put("start", 0);
		if (limit != -1) {
			labMap.put("limit", limit);
		}
		// 是否删除，状态为审核
		labMap.put("info_state", "1");
		labMap.put("is_del", "1");
		labMap.put("is_up", "0");
		List list = this.goodsDao.getWebList(labMap);
		return list;
	}

	/**
	 * @author：XBY
	 * @date：Feb 17, 2014 2:59:30 PM
	 * @Method Description：获取商品信息列表
	 */
	public Map getGoodsList(String catEn_name,String catId, StringBuilder postsb) {
		HttpServletRequest request = ServletActionContext.getRequest();
		String catList_id = "";
		// 导航
		String postName = "";
		// 获取分类名称
		String catName = "";
		List specnameList = new ArrayList();
		List specsizevalueList = new ArrayList();
		// 根据标识符查找分类ID
		if(!ValidateUtil.isRequired(catId)){
			catList_id=catId;
		}else {
			if (catEn_name != null && !catEn_name.equals("")) {
				catList_id = getCat_id(catEn_name, "goods");
			} else {
				if (request.getParameter("en_name") != null && !"".equals(request.getParameter("en_name"))) {
					catEn_name = request.getParameter("en_name");
					catList_id = getCat_id(catEn_name, "goods");
				}
			}
		}
		if (catList_id != null && !catList_id.equals("")) {
			// 获取导航
			String postStrID = catList_id.substring(
					catList_id.lastIndexOf(",") + 1, catList_id.length());
			postsb.setLength(0);
			postName = getPathUrl(getpostID(postStrID, postsb));
			// 根据条件查找商品规格
			Map specMap = new HashMap();
			specMap.put("cat_attr", catList_id);
			specnameList = this.specnameDao.getList(specMap);
			// 获取规格值列表
			Map specvalueMap = new HashMap();
			specsizevalueList = specvalueDao.getList(specvalueMap);
			// 获取分类名称
			catName = CategoryFuc.getCateName(catList_id);
		}
		Map map = new HashMap();
		map.put("catList_id", catList_id);
		map.put("postName", postName);
		map.put("catName", catName);
		map.put("specnameList", specnameList);
		map.put("specsizevalueList", specsizevalueList);
		return map;
	}

	/**
	 * @author : HZX
	 * @throws UnsupportedEncodingException
	 * @date : Jun 24, 2014 10:47:45 AM
	 * @Method Description :
	 */
	public ModifiableSolrParams toSolrIndex(Map map)
			throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String key_word = map.get("key_word").toString();
		String module_type = map.get("module_type").toString();
		String brand_id_s = map.get("brand_id_s").toString();
		String specstr_s = map.get("specstr_s").toString();
		String goods_cat_attr_s = map.get("goods_cat_attr_s").toString();
		String area_attr_s = map.get("area_attr_s").toString();
		String max_price = map.get("min_price").toString();
		String min_price = map.get("min_price").toString();
		String catList_id = map.get("catList_id").toString();
		String uppri = map.get("sort").toString();
		String sort = map.get("sort").toString();
		String downpri = map.get("downpri").toString();
		String shop_cust_id = map.get("shop_cust_id").toString();
		String selName = map.get("selName").toString();
		boolean is_souch = false;
		// ------------------------索引查询开始--------------------------------
		// 构造Map索引条件
		ModifiableSolrParams params = new ModifiableSolrParams();
		params.add(CommonParams.Q, "*:*");
		// 过滤器
		SolrQuery filterQuery = new SolrQuery();

		filterQuery.addFilterQuery("info_state:1");// 审核通过
		// 返回按关键字查找列表的次数，插入关键字表
		if (key_word == null || key_word.equals("")) {
			if (request.getParameter("wd") != null
					&& !request.getParameter("wd").equals("")
					&& request.getParameter("en_wd") != null
					&& !request.getParameter("en_wd").equals("")) {
				key_word = URLDecoder.decode(request.getParameter("wd"),
						"UTF-8");
				String en_name = URLDecoder.decode(request
						.getParameter("en_wd"), "UTF-8");
				// 插入关键字表中
				try {
					KeyWordInsertFuc.wdInsert(key_word, en_name, module_type);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				is_souch = true;
			}
		}
		List expressList = new ArrayList();
		if (key_word != null && !key_word.equals("")) {
			// 搜索商品名称
			params.remove(CommonParams.Q);
			params.add(CommonParams.Q, "goods_name:" + key_word);
			// 直通车列表
			Map expreeMap = new HashMap();
			expreeMap.put("spread_name", key_word);
			expreeMap.put("in_start_end", "in_start_end");
			expreeMap.put("enough_pay", "enough_pay");// 投放价格足够支付点击费用
			expreeMap.put("put_price_desc", "put_price_desc");// 推广价格降序
		}
		// 商品品牌
		if (brand_id_s != null && !"".equals(brand_id_s)) {
			filterQuery.addFilterQuery("brand_id:" + brand_id_s);
		}
		// 商品规格
		if (specstr_s != null && !"".equals(specstr_s)) {
			filterQuery.addFilterQuery("specstr:*" + specstr_s + "*");
		}
		// 分类筛选
		if (goods_cat_attr_s != null && !"".equals(goods_cat_attr_s)) {
			filterQuery.addFilterQuery("cat_attr:*" + goods_cat_attr_s + "*");
		} else {
			if (catList_id != null && !"".equals(catList_id)) {
				filterQuery.addFilterQuery("cat_attr:*" + catList_id + "*");
			}
		}
		// 地区过滤
		if (area_attr_s != null && !"".equals(area_attr_s)) {
			filterQuery.addFilterQuery("area_attr:" + area_attr_s);
		}
		// 未被删除商品
		filterQuery.addFilterQuery("is_del:1");
		// 上架商品
		filterQuery.addFilterQuery("is_up:0");
		// 排序
		if (sort != null && !"".equals(sort)) {
			if (sort.equals("0")) {
			} else if (sort.equals("price_asc")) {
				params.add(CommonParams.SORT, "lpad_price asc");// 价格升序
			} else if (sort.equals("price_desc")) {
				params.add(CommonParams.SORT, "lpad_price desc");// 价格降序
			} else if (sort.equals("sale_num_desc")) {
				params.add(CommonParams.SORT, "order_num desc");// 销售量降序
			} else if (sort.equals("in_date_desc")) {
				params.add(CommonParams.SORT, "in_date desc");// 订单降序
			}
		}
		// 价格搜索
		if (min_price != null && !"".equals(min_price) && max_price != null
				&& !"".equals(max_price)) {
			// 最低价
			String l_price = ToolsFuc.fullBit(min_price);
			l_price = ToolsFuc.isRmb(l_price);
			// 最高价
			String h_price = ToolsFuc.fullBit(max_price);
			l_price = ToolsFuc.isRmb(l_price);
			filterQuery.addFilterQuery("lpad_price:[" + l_price + " TO "
					+ h_price + "]");// 范围搜索
		}

		// 价格搜索
		if (uppri != null && !"".equals(uppri) && downpri != null
				&& !"".equals(downpri)) {
			// 最低
			String l_price = ToolsFuc.fullBit(uppri);
			l_price = ToolsFuc.isRmb(l_price);
			// 最高
			String h_price = ToolsFuc.fullBit(downpri);
			l_price = ToolsFuc.isRmb(l_price);
			filterQuery.addFilterQuery("lpad_price:[" + l_price + " TO "
					+ h_price + "]");// 范围搜索
		}

		// 根据商品的店铺id搜索相关商品
		if (shop_cust_id != null && !"".equals(shop_cust_id)) {
			filterQuery.addFilterQuery("cust_id:" + shop_cust_id);
		}
		// 所属分类
		String cat_id = request.getParameter("cat_id");
		if (cat_id != null && !cat_id.equals("")) {
			filterQuery.addFilterQuery("cat_attr:" + cat_id);
		}
		// 加入过滤条件
		params.add(filterQuery);
		// 左边部分关键字搜索，和价格搜索
		if (selName != null && !"".equals(selName)) {
			params.remove(CommonParams.Q);
			params.add(CommonParams.Q, "goods_name:" + selName);
		}
		return params;
	}

	/**
	 * @Method Description :根据分类拼音和模块名称查找分来标识
	 * @author : WXP
	 * @param :
	 *            en_name: 分类拼音 module_type：模块名称
	 * @date Feb 4, 2014 3:01:58 PM
	 */
	public String getCat_id(String en_name, String module_type) {
		String cat_id = "";
		Map map = new HashMap();
		map.put("en_name", en_name);
		map.put("module_type", module_type);
		List categoryList = this.categoryDao.getList(map);
		Map mapValue = new HashMap();
		if (categoryList != null && categoryList.size() > 0) {
			mapValue = (HashMap) categoryList.get(0);
		}
		if (mapValue.get("cat_id") != null) {
			cat_id = mapValue.get("cat_id").toString();
		}
		return cat_id;
	}

	/**
	 * @author : LJQ
	 * @date : May 14, 2014 3:03:31 PM
	 * @Method Description :递归查找 指定分类ID向顶级ID查找拼成ID串 导航
	 */
	public String getpostID(String id, StringBuilder postsb) {
		if (id == null || id.equals(""))
			return "";
		Category category = categoryDao.get(id);
		String up_id = "";
		postsb.append(id + ",");
		if (category != null && !"1111111111".equals(category.getUp_cat_id())) {
			up_id = category.getUp_cat_id();
			getpostID(up_id, postsb);
		}
		return postsb.toString();
	}

	/**
	 * @author : LJQ
	 * @date : May 7, 2014 10:10:25 AM
	 * @Method Description :获取导航
	 */
	public String getPathUrl(String postStrID) {
		String indexName = SysconfigFuc.getSysValue("cfg_index");
		String s = "";
		StringBuilder strsb = new StringBuilder();
		if (postStrID != null && !postStrID.equals("")) {
			postStrID = postStrID.substring(0, postStrID.length() - 1);
			String str[] = postStrID.split(",");
			strsb.append("<a href=\"" + indexName + "\">首页</a>");
			strsb.append("&nbsp;>&nbsp;");
			for (int i = str.length - 1; i >= 0; i--) {
				strsb.append("<span><a href=\"/mall-goodsListNav-"
						+str[i]+ ".html\">"
						+ CategoryFuc.getCateName(str[i]) + "</a></span>");
				if (i > 0) {
					strsb.append("&nbsp;>&nbsp;");
				}
			}
		}
		return strsb.toString();
	}

	/**
	 * @author：XBY
	 * @date：Feb 17, 2014 4:30:38 PM
	 * @Method Description：运费处理
	 */
	public List hualFeer(String goods_id_str, String buy_num_str,String end_area_attr, String spec_id_str) {
		double v_all = 0.0;
		double w_all = 0.0;
		String ief_id = "1";//系统默认国际运费模版
		String ship_id = "1";//系统默认国内运费模版
		double sea_ship = 0.0;
		double ief_over_price = 0.0;
		double shop_ship_free=0.0;//国内运费
		double shop_ship_free_all=0.0;//运费总和
		String order_type="1";//订单类型 直邮和保税 1直邮 0保税
		// 运费集合
		List shipList = new ArrayList();
		if (goods_id_str.indexOf(",") > -1) {
			String goods_id[] = goods_id_str.split(",");
			String buy_num_strs[] = buy_num_str.split(",");
			String spec_id[] = spec_id_str.split(",");
			String is_ship_str = "";
			for (int i = 0; i < goods_id.length; i++) {
				Goods goods = this.goodsDao.get(goods_id[i]);
				//获取商品的是直邮 还是保税
				if(i==0){
					//仓库位置
					if(goods.getDepot_id()!=null&&!"".equals(goods.getDepot_id())){
						Depot depot=new Depot();
						depot=depotDao.get(goods.getDepot_id());
						if(depot.getIs_international()!=null&&!"".equals(depot.getIs_international())){
							order_type=depot.getIs_international();
						}
					}
				}
				if (goods != null && goods.getIs_ship() != null&& !goods.getIs_ship().equals("")) {
					is_ship_str = is_ship_str + goods.getIs_ship() + ",";
				}
				// 拼接体积串
				String tVolume = "0";
				if (goods != null&& getKey(goods_id[i], spec_id[i], "volume") != null&& !getKey(goods_id[i], spec_id[i], "volume").equals("")) {
					tVolume = getKey(goods_id[i], spec_id[i], "volume");
					v_all += Double.parseDouble(tVolume)* Integer.parseInt(buy_num_strs[i]);
				}
				// 拼接重量串
				String tLogsweight = "0";
				if (goods != null&& getKey(goods_id[i], spec_id[i], "logsweight") != null&& !getKey(goods_id[i], spec_id[i], "logsweight").equals("")) {
					    tLogsweight = getKey(goods_id[i], spec_id[i], "logsweight");
						w_all += Double.parseDouble(tLogsweight)* Integer.parseInt(buy_num_strs[i]);
				}
			}
			
		} else {// 单个商品
			    Goods goods = this.goodsDao.get(goods_id_str);
			  //获取商品的是直邮 还是保税
				if(goods.getDepot_id()!=null&&!"".equals(goods.getDepot_id())){
					Depot depot=new Depot();
					depot=depotDao.get(goods.getDepot_id());
					if(depot.getIs_international()!=null&&!"".equals(depot.getIs_international())){
						order_type=depot.getIs_international();
					}
				}
				w_all += Double.parseDouble(getKey(goods_id_str, spec_id_str,"logsweight"))* Integer.parseInt(buy_num_str);
				v_all += Double.parseDouble(getKey(goods_id_str, spec_id_str,"volume"))* Integer.parseInt(buy_num_str);
		}
		
		Internationaltariff ief = new Internationaltariff();
		ief = this.internationaltariffDao.get(ief_id);
		String ief_v = ief.getIef_cubic();
		String ief_over_w = ief.getIef_overweight();
		String ief_price = ief.getIef_price();
		ief_over_price = Double.parseDouble(ief.getIef_overweight_price());
		double v_w = 0.0;
		if ((v_all / Double.parseDouble(ief_v)) > w_all) {
			v_w = v_all / Double.parseDouble(ief_v);
		} else {
			v_w = w_all;
		}
		 //计算运费
		shipList = dealShipPrice("1",ship_id,"1","0",String.valueOf(v_w),end_area_attr);
		if(shipList!=null&&shipList.size()>0){
			Map ship_freeMap=(Map) shipList.get(0);
			shop_ship_free=Double.parseDouble(ship_freeMap.get("ship_price").toString());
		}
		//直邮运费算法
		if("1".equals(order_type)){
			//判断是否超重
			if(v_w<Double.parseDouble(ief_over_w)){
				//没有超重 获取默认超重重量 也就是取默认首重价格
				//获取默认最低价格
				if(v_w<=1){
					v_w=1;
				}
				sea_ship=v_w*Double.parseDouble(ief_price);
			}else {
				//超重 价格算法  3*首重价格+超重重量*超重价格
				sea_ship=(Double.parseDouble(ief_over_w)*Double.parseDouble(ief_price))+(ief_over_price*(w_all-Double.parseDouble(ief_over_w)));
			}
	   }else {
		   //如果是保税区的运费为0
		   sea_ship=0;
	   }
		//总运费
		shop_ship_free_all=shop_ship_free+sea_ship;
		//判断直邮
		if("1".equals(order_type)){
			//判断如果直邮运费大于480 直接算为480
			if(shop_ship_free_all>480){
				shop_ship_free_all=480;
			}
		}
		Map shipMap = new HashMap();
		//系统默认就一种运费模版
		Shiptemplate gShiptemplate=new Shiptemplate();
		gShiptemplate=shiptemplateDao.get("1");
		shipMap.put("smode_id", gShiptemplate.getSmode_attr());
		// shipMap.put("ship_name", t_ship_name);
		shipMap.put("ship_price", String.format("%.2f", (shop_ship_free_all)));
		List newList = new ArrayList();
		newList.add(shipMap);
		return newList;
	}

	public String getKey(String goods_id, String spec_id, String key) {
		String new_key = null;
		Map priMap = new HashMap();
		priMap.put("goods_id", goods_id.replace(" ", ""));
		if (spec_id != null && !"".equals(spec_id)
				&& !"0".equals(spec_id.replace(" ", ""))) {
			priMap.put("spec_id", spec_id.replace(" ", ""));
		}
		List goodsList = this.goodsattrDao.getWebList(priMap);
		if (goodsList != null && goodsList.size() > 0) {
			Map pMap = (Map) goodsList.get(0);
			if (pMap.get(key) != null) {
				new_key = pMap.get(key).toString();
			}
		}
		return new_key;
	}

	/**
	 * @author : WXP
	 * @param :ship_id,buy_num,volume,logsweight,end_area
	 * @date Apr 11, 2014 3:50:14 PM
	 * @Method Description :处理运费
	 */
	public List dealShipPrice(String is_ship_str, String ship_id_str,
			String buy_num_str, String volume_str, String logsweight_str,
			String end_area) {
		IShiptemplateService shiptemplateService = (IShiptemplateService) getContext()
				.getBean("shiptemplateService");
		// 配送方式集合
		List shipList = new ArrayList();
		// 运费模板
		Shiptemplate shiptemplate = new Shiptemplate();
		// 计价方式
		String valuation_mode = "";
		// 配送方式
		String smode_id[] = null;
		if (ship_id_str.indexOf(",") > -1) {
			String is_ship[] = is_ship_str.split(",");// 是否免运费
			String ship_id[] = ship_id_str.split(",");// 运费模板
			String buy_num[] = buy_num_str.split(",");// 购买数量
			String logsweight[] = logsweight_str.split(",");// 重量
			String volume[] = volume_str.split(",");// 体积
			// 临时变量，用于判断最高运费
			String t_smode_id = "";
			String t_ship_name = "";
			Double t_ship_price = 0.0;
			// 多个商品运费总和
			Double total_ship = 0.0;
			String all_ship_free = "";
			for (int i = 0; i < ship_id.length; i++) {
				if (ship_id[i] == null || ship_id[i].equals("0")
						|| ship_id[i].equals("")) {
					t_ship_price = 0.0;
					shiptemplate = null;
				} else {
					// 运费模板实例化
					shiptemplate = shiptemplateService.get(ship_id[i]);
				}
				if (shiptemplate != null) {
					valuation_mode = shiptemplate.getValuation_mode();
					if (shiptemplate.getSmode_attr() != null) {
						smode_id = shiptemplate.getSmode_attr().split(",");
					}
				}
				if (smode_id != null) {
					for (int j = 0; j < smode_id.length; j++) {
						// 获取区域设置
						List areaSetList = getAreaSet(ship_id[i], smode_id[j],
								end_area);
						// 区域设置Map对象
						HashMap areaSetMap = new HashMap();
						// 区域设置属性:配送方式,首重/件/立方,首重/件/立方价格,续重/件/立方,续重/件/立方价格
						String smode_name = "", first_weight = "", first_price = "", cont_weight = "", cont_price = "";
						if (areaSetList != null && areaSetList.size() > 0) {
							areaSetMap = (HashMap) areaSetList.get(0);
						}
						// 获取区域设置属性
						if (areaSetMap.get("smode_name") != null) {
							// 配送方式名称
							smode_name = areaSetMap.get("smode_name")
									.toString();
						}
						if (areaSetMap.get("first_weight") != null) {
							// 首重/件/立方
							first_weight = areaSetMap.get("first_weight")
									.toString();
						}
						if (areaSetMap.get("first_price") != null) {
							// 首重/件/立方价格
							first_price = areaSetMap.get("first_price")
									.toString();
						}
						if (areaSetMap.get("cont_weight") != null) {
							// 续重/件/立方
							cont_weight = areaSetMap.get("cont_weight")
									.toString();
						}
						if (areaSetMap.get("cont_price") != null) {
							// 续重/件/立方价格
							cont_price = areaSetMap.get("cont_price")
									.toString();
						}
						// 运费
						Double ship_price;
						if (is_ship[i].equals("0")) {
							ship_price = 0.0;
							t_ship_price = ship_price;
						} else {
							ship_price = calShipPrice(valuation_mode,
									buy_num[i], first_weight, first_price,
									cont_weight, cont_price, logsweight[i],
									volume[i]);
						}
						if (ship_price > 0) {
							t_smode_id = smode_id[j];
							t_ship_price = ship_price;
							// t_ship_name = smode_name;
						}
					}
				}
				total_ship += t_ship_price;

			}

			Map shipMap = new HashMap();
			shipMap.put("smode_id", t_smode_id);
			// shipMap.put("ship_name", t_ship_name);
			shipMap.put("ship_price", total_ship);
			shipList.add(shipMap);
		} else {
			// 运费模板实例化
			shiptemplate = shiptemplateService.get(ship_id_str.trim());
			if (shiptemplate != null) {
				valuation_mode = shiptemplate.getValuation_mode();
				smode_id = shiptemplate.getSmode_attr().split(",");
			}
			if (shiptemplate != null) {
				valuation_mode = shiptemplate.getValuation_mode();
				smode_id = shiptemplate.getSmode_attr().split(",");
			}
			if (smode_id != null) {
				for (int j = 0; j < smode_id.length; j++) {
					// 获取区域设置
					List areaSetList = getAreaSet(ship_id_str, smode_id[j],
							end_area);
					// 区域设置Map对象
					HashMap areaSetMap = new HashMap();
					String smode_name = "", first_weight = "", first_price = "", cont_weight = "", cont_price = "";
					if (areaSetList != null && areaSetList.size() > 0) {
						areaSetMap = (HashMap) areaSetList.get(0);
					}
					// 获取区域设置属性
					if (areaSetMap.get("smode_name") != null) {
						// 配送方式名称
						smode_name = areaSetMap.get("smode_name").toString();
					}
					if (areaSetMap.get("first_weight") != null) {
						// 首重/件/立方
						first_weight = areaSetMap.get("first_weight")
								.toString();
					}
					if (areaSetMap.get("first_price") != null) {
						// 首重/件/立方价格
						first_price = areaSetMap.get("first_price").toString();
					}
					if (areaSetMap.get("cont_weight") != null) {
						// 续重/件/立方
						cont_weight = areaSetMap.get("cont_weight").toString();
					}
					if (areaSetMap.get("cont_price") != null) {
						// 续重/件/立方价格
						cont_price = areaSetMap.get("cont_price").toString();
					}
					// 运费
					Double ship_price;
					if (is_ship_str.equals("0")) {
						ship_price = 0.0;
					} else {
						ship_price = calShipPrice(valuation_mode, buy_num_str,
								first_weight, first_price, cont_weight,
								cont_price, logsweight_str, volume_str);
					}
					Map shipMap = new HashMap();
					shipMap.put("smode_id", smode_id[j]);
					// shipMap.put("ship_name", smode_name);
					shipMap.put("ship_price", ship_price);
					shipList.add(shipMap);
				}
			}
		}
		return shipList;
	}

	/**
	 * @author : WXP
	 * @param :ship_id,smode_id,end_area
	 * @date Apr 7, 2014 3:28:28 PM
	 * @Method Description :获取某个配送方式的运费计算方式，按地区过滤
	 */
	public static List getAreaSet(String ship_id, String smode_id,
			String end_area) {
		Map map = new HashMap();
		map.put("ship_id", ship_id);// 模板id
		map.put("smode_id", smode_id);// 配送方式id
		map.put("end_area", end_area);// 到达地址
		IAreasetService areasetService = (IAreasetService) getContext()
				.getBean("areasetService");
		List list = areasetService.getList(map);
		if (list == null || list.size() == 0) {// 不存在则获取默认运费
			Map dmap = new HashMap();
			dmap.put("ship_id", ship_id);// 模板id
			dmap.put("smode_id", smode_id);// 配送方式id
			dmap.put("default_ship", "0");// 默认运费
			list = areasetService.getList(dmap);
		}
		return list;
	}

	/**
	 * @author : WXP
	 * @param :valuation_mode,
	 *            buy_num, first_weight, first_price, cont_weight, cont_price,
	 *            logsweight, volume
	 * @date Apr 12, 2014 10:43:37 AM
	 * @Method Description :计算运费
	 */
	public static Double calShipPrice(String valuation_mode, String buy_num,
			String first_weight, String first_price, String cont_weight,
			String cont_price, String logsweight, String volume) {
		// 运费
		Double ship_price = 0.0;
		if (valuation_mode != null && !valuation_mode.equals("")) {
			if (valuation_mode.equals("1")) {// 按件数计价
				Double subNum = Double.parseDouble(buy_num)
						- Double.parseDouble(first_weight);
				if (subNum > 0) {// 购买个数大于首件个数
					ship_price = Double.parseDouble(first_price)
							+ Math.ceil(subNum
									/ Double.parseDouble(cont_weight))
							* Double.parseDouble(cont_price);
				} else {
					ship_price = Double.parseDouble(first_price);
				}
			} else if (valuation_mode.equals("2")) {// 按重量计价
				Double subWeight = Double.parseDouble(buy_num)* Double.parseDouble(logsweight)
						- Double.parseDouble(first_weight);
				if (subWeight > 0) {// 购买商品重量大于首重
					ship_price = Double.parseDouble(first_price)
							+ Math.ceil(subWeight
									/ Double.parseDouble(cont_weight))
							* Double.parseDouble(cont_price);
				} else {
					ship_price = Double.parseDouble(first_price);
				}
			} else if (valuation_mode.equals("3")) {// 按体积计价
				Double subVolume = Double.parseDouble(buy_num)
						* Double.parseDouble(volume)
						- Double.parseDouble(first_weight);
				if (subVolume > 0) {// 购买商品体积大于首体积
					ship_price = Double.parseDouble(first_price)
							+ Math.ceil(subVolume
									/ Double.parseDouble(cont_weight))
							* Double.parseDouble(cont_price);
				} else {
					ship_price = Double.parseDouble(first_price);
				}
			}
		}
		return ship_price;
	}

	/**
	 * @Method Description :购物车处理
	 * @author : HZX
	 * @date : Apr 29, 2014 12:49:09 PM
	 */
	public void dealCart(Cookie[] cookies, String session_cust_id)
			throws UnsupportedEncodingException {
		for (Cookie c : cookies) {
			if (c.getName().contains("twgl")) {
				JSONArray goodsList = JSONArray.fromObject(URLDecoder.decode(c
						.getValue(), "UTF-8"));
				dealCartApp(goodsList, session_cust_id);
			}
		}
		try {
			clearCookie(cookies, "ccn");
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			clearCookie(cookies, "twgl");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @Method Description :触屏版购物车处理
	 * @author : HZX
	 * @throws UnsupportedEncodingException
	 * @date : Apr 11, 2015 10:51:34 AM
	 */
	public String dealCartApp(List goodsList, String session_cust_id)
			throws UnsupportedEncodingException {
		try {
			Map cookieMap = new HashMap();
			List cartgoodsList = new ArrayList();
			Goods goodsmodel = new Goods();
			Goodsattr goodsattr = new Goodsattr();
			int buy_num = 0;
			String stock = "0";
			// {"i":"0|2|708|7080"} spec_id|buy_nums|goodsid|cookie_id
			for (int i = 0; i < goodsList.size(); i++) {
				Map goodsMap = (Map) goodsList.get(i);
				// 获取对象 以"|"分割
				String goodsinfos[] = goodsMap.get("i").toString().split("\\|");
				String ck_spec_id = "", ck_buy_nums = "", ck_goodsid = "", ck_cookie_id = "";
				if (goodsinfos[3] == null
						|| "".equals(goodsinfos[3].toString())) {
					// 获取 cookie唯一标识ID
					continue;
				}
				if (goodsinfos[1] == null
						|| "".equals(goodsinfos[1].toString())) {
					// 获取buy_nums 购买数量
					continue;
				}
				if (goodsinfos[2] != null) {
					ck_goodsid = goodsinfos[2].toString();// 商品ID
					goodsmodel = this.goodsDao.get(ck_goodsid);
				}
				// 获取cookies的值
				if (goodsinfos[0] != null) {
					ck_spec_id = goodsinfos[0].toString();// 规格ID
					String str_spec_id = " ";
					if (ck_spec_id != null && !"0".equals(ck_spec_id)) {
						str_spec_id = ck_spec_id;
					}
					// 商品属性详情
					goodsattr = this.goodsattrDao.getGoodsattr(ck_goodsid,
							str_spec_id);
				}
				if (goodsinfos[1] != null) {
					ck_buy_nums = goodsinfos[1].toString();// 购买数量
				}
				if (goodsinfos[3] != null) {
					ck_cookie_id = goodsinfos[3].toString();// cookiesID
				}

				cookieMap.put("cust_id", session_cust_id);
				cookieMap.put("cookie_id", ck_cookie_id);
				cartgoodsList = this.cartgoodsDao.getList(cookieMap);
				// 判断购物车里面的cookie值 是否已经存在我们的额数据库购物车表里面，如果存在，只增加数量，不插入新的购物车条目
				if (cartgoodsList.size() == 1) {
					buy_num = Integer.parseInt(ck_buy_nums);
					// 如果是异常数据,跳过处理，必须保证购物车的数量为正整数
					if (buy_num > 0) {
						Map cart = (HashMap) cartgoodsList.get(0);
						buy_num = (Integer) cart.get("buy_num") + buy_num;
						String cart_id = cart.get("trade_id").toString();
						Cartgoods cg = new Cartgoods();
						cg = this.cartgoodsDao.get(cart_id);
						String spec_id = cg.getSpec_id();
						String c_buy_num = cg.getBuy_num();
						String c_goods_id = cg.getGoods_id();
						Map goodsattrMap = new HashMap();
						if (spec_id != null && !"".equals(spec_id)
								&& !"0".equals(spec_id.replace(" ", ""))) {
							goodsattrMap.put("specstr", spec_id);
						}
						goodsattrMap.put("goods_id", c_goods_id);
						Goods goods = this.goodsDao.get(c_goods_id);
						String limit_num = goods.getLimit_num();
						String is_limit = goods.getIs_limit();
						if ("0".equals(is_limit)) {
							int limit_nums = Integer.parseInt(limit_num);
							int all_num = buy_num + Integer.parseInt(c_buy_num);
							if (all_num > limit_nums) {
								buy_num = limit_nums;
							}
						}
						List goodsattrList = this.goodsattrDao
								.getList(goodsattrMap);
						Iterator iterator = goodsattrList.iterator();
						if (iterator.hasNext()) {
							Map attrMap = (Map) iterator.next();
							stock = attrMap.get("stock").toString();
							int all_num = buy_num + Integer.parseInt(c_buy_num);
							if (all_num > Integer.parseInt(stock)) {
								buy_num = Integer.parseInt(stock);
							}
							if ("0".equals(is_limit)) {
								int limit_nums = Integer.parseInt(limit_num);
								if (all_num > limit_nums) {
									buy_num = limit_nums;
								}
							}
						}
						cg.setBuy_num((buy_num + "").trim());
						this.cartgoodsDao.update(cg);
					}
					continue;
				} else {
					// 插入新的购物车条目
					Cartgoods newC = new Cartgoods();
					if (ck_cookie_id != null) {
						newC.setCookie_id(ck_cookie_id);
						if (goodsmodel != null&& goodsmodel.getCust_id() != null) {
							newC.setShop_cust_id(goodsmodel.getIs_international());
							String user_name = memberDao.get(goodsmodel.getCust_id()).getCust_name();
							newC.setUser_name(user_name);
						}
						if (goodsattr != null&& goodsattr.getSale_price() != null) {
							newC.setSale_price(Double.parseDouble(goodsattr.getSale_price().toString()));
							// 设置送的积分
							newC.setIntegral(Double.parseDouble(cfg_sc_pointsrule)* Double.parseDouble(goodsattr.getSale_price().toString()) / 100);
						}
						if (ck_spec_id != null) {
							newC.setSpec_id(ck_spec_id.toString());
						}
						if (goodsmodel != null&& goodsmodel.getIs_virtual() != null) {
							newC.setIs_virtual((goodsmodel.getIs_virtual().toString()));
						}
						if (goodsmodel != null&& goodsmodel.getGoods_name() != null) {
							newC.setGoods_name(goodsmodel.getGoods_name().toString());
						}
						if (goodsmodel != null&& goodsmodel.getList_img() != null) {
							newC.setImg_path(goodsmodel.getList_img().toString());
						}
						if (ck_goodsid != null) {
							newC.setGoods_id((ck_goodsid.toString()));
						}
						if (ck_buy_nums != null) {
							Map goodsattrMap = new HashMap();
							if (newC.getSpec_id() != null
									&& !"".equals(newC.getSpec_id())
									&& !"0".equals(newC.getSpec_id())) {
								goodsattrMap.put("specstr", newC.getSpec_id());
							}
							goodsattrMap.put("goods_id", newC.getGoods_id());
							Goods goods = this.goodsDao.get(newC.getGoods_id());
							String limit_num = goods.getLimit_num();
							String is_limit = goods.getIs_limit();
							if ("0".equals(is_limit)) {
								int limit_nums = Integer.parseInt(limit_num);
								int all_num = Integer.parseInt(ck_buy_nums);
								if (all_num > limit_nums) {
									ck_buy_nums = limit_nums + "";
								}
							}
							List goodsattrList = this.goodsattrDao
									.getList(goodsattrMap);
							Iterator iterator = goodsattrList.iterator();
							if (iterator.hasNext()) {
								Map attrMap = (Map) iterator.next();
								stock = attrMap.get("stock").toString();
								int all_num = Integer.parseInt(ck_buy_nums);
								if (all_num > Integer.parseInt(stock)) {
									ck_buy_nums = stock;
								}
								if ("0".equals(is_limit)) {
									int limit_nums = Integer
											.parseInt(limit_num);
									if (all_num > limit_nums) {
										ck_buy_nums = limit_nums + "";
									}
								}
							}
							newC.setBuy_num(ck_buy_nums);
						}

						Shopconfig shopconfig = new Shopconfig();
						shopconfig = shopconfigDao.getByCustID(goodsmodel
								.getCust_id());
						if (shopconfig != null && shopconfig.getQq() != null) {
							newC.setShop_qq((shopconfig.getQq().toString()));
						}
						if (shopconfig != null&& shopconfig.getShop_name() != null) {
							newC.setShop_name(shopconfig.getShop_name().toString());
						}
						if (shopconfig != null&& shopconfig.getRadom_no() != null) {
							newC.setRadom_no(shopconfig.getRadom_no()
									.toString());
						}
						String spec_name = "";
						if (ck_spec_id != null && !"".equals(ck_spec_id)
								&& !"0".equals(ck_spec_id)) {
							spec_name = getSepcValueTosepcId(ck_goodsid,
									ck_spec_id);
						}

						if (spec_name != null) {
							newC.setSpec_name(spec_name.toString());
						}

						newC.setCust_id(session_cust_id);
						// 如果ID不存在，默认该条cookie为异常数据，运营商的默认店铺的cust_id为0，且购物车数量不为空值
						if (!ValidateUtil.isRequired(newC.getShop_cust_id())) {
							// 控制购物车的的数量 必须大于0的数字
							if (Integer.parseInt(newC.getBuy_num()) > 0) {
								this.cartgoodsDao.insert(newC);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			return "2";
		}

		return "1";
	}

	/**
	 * @author : HXK
	 * @Method Description :获取商品自定义规格名称跟规格值
	 */
	public String getSepcValueTosepcId(String goods_id, String ck_spec_id) {
		Map ssnMap = new HashMap();
		ssnMap.put("goods_id", goods_id);
		selfspecnameList = this.selfspecnameDao.getList(ssnMap);
		if (selfspecnameList != null && selfspecnameList.size() > 0) {
			String flagId = "";
			for (int i = 0; i < selfspecnameList.size(); i++) {
				Map map = (HashMap) selfspecnameList.get(i);
				if (map.get("spec_serial_id") != null) {
					if (!flagId.equals("")) {
						flagId = flagId + ","
								+ map.get("spec_serial_id").toString();
					} else {
						flagId = map.get("spec_serial_id").toString();
					}
				}
			}
			if (!ValidateUtil.isRequired(flagId)) {
				// 获取自定义规格值列表
				Map ssvMap = new HashMap();
				ssvMap.put("id_asc", "id_asc");
				ssvMap.put("spec_serial_id", flagId);
				selfsepcvalueList = this.selfspecvalueDao.getList(ssvMap);
			}
		}
		String spec_name = "";
		String ck_spec_ids[] = ck_spec_id.split(":");
		if (ck_spec_ids != null && ck_spec_ids.length > 0) {
			// 先循环获得规格ID 格式12345678:12345678
			for (int j = 0; j < ck_spec_ids.length; j++) {
				// 循环规格组名称
				for (int k = 0; k < selfspecnameList.size(); k++) {
					Map ssnameMap = new HashMap();
					// 获取规格组
					ssnameMap = (HashMap) selfspecnameList.get(k);
					if (ssnameMap != null) {
						// 循环规格值
						for (int z = 0; z < selfsepcvalueList.size(); z++) {
							Map sspMap = new HashMap();
							sspMap = (HashMap) selfsepcvalueList.get(z);
							if (sspMap != null
									&& sspMap.get("spec_serial_id") != null) {
								if (ssnameMap.get("spec_serial_id").toString()
										.equals(
												sspMap.get("spec_serial_id")
														.toString())) {
									// 获取商品的规则组下的规格值
									if (ck_spec_ids[j].toString().equals(
											sspMap.get("self_size_id")
													.toString())) {
										// 前台获得规格ID跟商品自身的规格ID匹配，就获取规格的名称
										spec_name += ssnameMap.get("sname")
												.toString()
												+ ":"
												+ sspMap.get("self_spec_value")
														.toString() + "<br/>";
										break;
									}
								} else {
									continue;
								}

							}
						}
					}
				}
			}
		}
		return spec_name;
	}

	/**
	 * @Method Description :购物车处理
	 * @author : HZX
	 * @date : Apr 29, 2014 12:49:09 PM
	 */
	public void dealCart_old(Cookie[] cookies, String session_cust_id)
			throws UnsupportedEncodingException {
		Map cookieMap = new HashMap();
		List cartgoodsList = new ArrayList();
		int buy_num = 0;
		String stock = "0";
		for (Cookie c : cookies) {
			if (c.getName().contains("twgl")) {
				JSONArray goodsList = JSONArray.fromObject(URLDecoder.decode(c
						.getValue(), "UTF-8"));
				for (int i = 0; i < goodsList.size(); i++) {
					Map goodsMap = (Map) goodsList.get(i);
					if (goodsMap.get("cookie_id") == null
							|| "".equals(goodsMap.get("cookie_id").toString())) {
						continue;
					}
					if (goodsMap.get("buy_nums") == null
							|| "".equals(goodsMap.get("buy_nums").toString())) {
						continue;
					}
					cookieMap.put("cust_id", session_cust_id);
					cookieMap.put("cookie_id", URLDecoder.decode(goodsMap.get(
							"cookie_id").toString(), "UTF-8"));
					cartgoodsList = this.cartgoodsDao.getList(cookieMap);
					// 判断购物车里面的cookie值 是否已经存在我们的额数据库购物车表里面，如果存在，只增加数量，不插入新的购物车条目
					if (cartgoodsList.size() == 1) {
						buy_num = Integer.parseInt(goodsMap.get("buy_nums")
								.toString());
						// 如果是异常数据,跳过处理，必须保证购物车的数量为正整数
						if (buy_num > 0) {
							Map cart = (HashMap) cartgoodsList.get(0);
							buy_num = (Integer) cart.get("buy_num") + buy_num;
							String cart_id = cart.get("trade_id").toString();
							Cartgoods cg = new Cartgoods();
							cg = this.cartgoodsDao.get(cart_id);
							String spec_id = cg.getSpec_id();
							String c_buy_num = cg.getBuy_num();
							String c_goods_id = cg.getGoods_id();
							Map goodsattrMap = new HashMap();
							goodsattrMap.put("specstr", spec_id);
							goodsattrMap.put("goods_id", c_goods_id);
							Goods goods = this.goodsDao.get(c_goods_id);
							String limit_num = goods.getLimit_num();
							String is_limit = goods.getIs_limit();
							if ("0".equals(is_limit)) {
								int limit_nums = Integer.parseInt(limit_num);
								int all_num = buy_num
										+ Integer.parseInt(c_buy_num);
								if (all_num > limit_nums) {
									buy_num = limit_nums;
								}
							}
							List goodsattrList = this.goodsattrDao
									.getList(goodsattrMap);
							Iterator iterator = goodsattrList.iterator();
							if (iterator.hasNext()) {
								Map attrMap = (Map) iterator.next();
								stock = attrMap.get("stock").toString();
								int all_num = buy_num
										+ Integer.parseInt(c_buy_num);
								if (all_num > Integer.parseInt(stock)) {
									buy_num = Integer.parseInt(stock);
								}
								if ("0".equals(is_limit)) {
									int limit_nums = Integer
											.parseInt(limit_num);
									if (all_num > limit_nums) {
										buy_num = limit_nums;
									}
								}
							}
							cg.setBuy_num((buy_num + "").trim());
							this.cartgoodsDao.update(cg);
							// commonClearCookie( trade_id,cookies);
						}
						continue;
					} else {
						// 插入新的购物车条目
						Cartgoods newC = new Cartgoods();
						if (goodsMap.get("cookie_id") != null) {
							newC.setCookie_id(URLDecoder.decode(goodsMap.get(
									"cookie_id").toString(), "UTF-8"));
							String trade_id1 = c.getName().substring(9,
									c.getName().length());
							if (goodsMap.get("shop_cust_id") != null) {
								newC.setShop_cust_id(goodsMap.get(
										"shop_cust_id").toString());
								String user_name = memberDao
										.get(
												goodsMap.get("shop_cust_id")
														.toString())
										.getCust_name();
								newC.setUser_name(user_name);
							}
							if (goodsMap.get("sale_price") != null) {
								newC.setSale_price(Double.parseDouble(goodsMap
										.get("sale_price").toString()));
								// 设置送的积分
								newC
										.setIntegral(Double
												.parseDouble(cfg_sc_pointsrule)
												* Double.parseDouble(goodsMap
														.get("sale_price")
														.toString()) / 100);
							}
							if (goodsMap.get("spec_id") != null) {
								newC.setSpec_id(URLDecoder.decode(goodsMap.get(
										"spec_id").toString(), "UTF-8"));
							}
							if (goodsMap.get("is_virtual") != null) {
								newC.setIs_virtual((goodsMap.get("is_virtual")
										.toString()));
							}
							if (goodsMap.get("goods_name") != null) {
								newC
										.setGoods_name(URLDecoder.decode(
												goodsMap.get("goods_name")
														.toString(), "UTF-8"));
							}
							if (goodsMap.get("list_img") != null) {
								newC.setImg_path(URLDecoder.decode(goodsMap
										.get("list_img").toString(), "UTF-8"));
							}
							if (goodsMap.get("goods_id") != null) {
								newC.setGoods_id((goodsMap.get("goods_id")
										.toString()));
							}
							if (goodsMap.get("buy_nums") != null) {
								String c_buy_num = goodsMap.get("buy_nums")
										.toString();
								Map goodsattrMap = new HashMap();
								goodsattrMap.put("specstr", newC.getSpec_id());
								goodsattrMap
										.put("goods_id", newC.getGoods_id());
								Goods goods = this.goodsDao.get(newC
										.getGoods_id());
								String limit_num = goods.getLimit_num();
								String is_limit = goods.getIs_limit();
								if ("0".equals(is_limit)) {
									int limit_nums = Integer
											.parseInt(limit_num);
									int all_num = Integer.parseInt(c_buy_num);
									if (all_num > limit_nums) {
										c_buy_num = limit_nums + "";
									}
								}
								List goodsattrList = this.goodsattrDao
										.getList(goodsattrMap);
								Iterator iterator = goodsattrList.iterator();
								if (iterator.hasNext()) {
									Map attrMap = (Map) iterator.next();
									stock = attrMap.get("stock").toString();
									int all_num = Integer.parseInt(c_buy_num);
									if (all_num > Integer.parseInt(stock)) {
										c_buy_num = stock;
									}
									if ("0".equals(is_limit)) {
										int limit_nums = Integer
												.parseInt(limit_num);
										if (all_num > limit_nums) {
											c_buy_num = limit_nums + "";
										}
									}
								}
								newC.setBuy_num(c_buy_num);
							}
							if (goodsMap.get("shop_qq") != null) {
								newC.setShop_qq((goodsMap.get("shop_qq")
										.toString()));
							}
							if (goodsMap.get("shop_name") != null) {
								newC.setShop_name(URLDecoder.decode(goodsMap
										.get("shop_name").toString(), "UTF-8"));
							}
							if (goodsMap.get("spec_name") != null) {
								newC.setSpec_name(URLDecoder.decode(goodsMap
										.get("spec_name").toString(), "UTF-8"));
							}
							if (goodsMap.get("radom_no") != null) {
								newC.setRadom_no(goodsMap.get("radom_no")
										.toString());
							}
							newC.setCust_id(session_cust_id);
							// 如果店铺ID不存在，默认该条cookie为异常数据，运营商的默认店铺的cust_id为0，且购物车数量不为空值
							if (!ValidateUtil
									.isRequired(newC.getShop_cust_id())) {
								// 控制购物车的的数量 必须大于0的数字
								if (Integer.parseInt(newC.getBuy_num()) > 0) {
									this.cartgoodsDao.insert(newC);
								}
							}
						}
					}
				}
			}
		}
		try {
			clearCookie(cookies, "ccn");
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			clearCookie(cookies, "twgl");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @Method Description :清除cookie
	 * @author : HZX
	 * @date : Apr 22, 2014 9:22:47 PM
	 */
	public void clearCookie(Cookie[] cookies, String cookie_name) {
		Cookie cookie_clear = null;
		cookie_clear = getCookieByName(cookies, cookie_name);
		if (cookie_clear != null) {
			cookie_clear.setMaxAge(0);
			cookie_clear.setPath("/");
			HttpServletResponse response = ServletActionContext.getResponse();
			response.addCookie(cookie_clear);
		}
	}

	/**
	 * @Method Description :
	 * @author : HZX
	 * @date : Apr 22, 2014 10:35:48 AM
	 */
	public Cookie getCookieByName(Cookie[] cookies, String cookie_name) {
		Cookie c1 = null;
		for (Cookie c : cookies) {
			if (c.getName().equals(cookie_name)) {
				return c;
			}
		}
		return c1;
	}

	/**
	 * @author : WXP
	 * @param :ship_id,smode_id
	 * @date Apr 7, 2014 3:28:28 PM
	 * @Method Description :获取某个配送方式的默认运费计算方式
	 */
	public String getshiparea(String uparea) {
		StringBuilder Ssb = new StringBuilder();
		Map sareamap = new HashMap();
		sareamap.put("up_area_id", uparea);
		List sareaList = areaDao.getList(sareamap);
		if (sareaList != null && sareaList.size() > 0) {
			Ssb.append("<div id='sareadiv'><ul>");
			for (int i = 0; i < sareaList.size(); i++) {
				Map cnamemap = new HashMap();
				cnamemap = (HashMap) sareaList.get(i);
				String areaName = cnamemap.get("area_name").toString();
				String areaid = cnamemap.get("area_id").toString();
				String up_areaid = cnamemap.get("up_area_id").toString();
				Ssb.append("<li id='" + areaid
						+ "'  class='shiparea' onclick=\"shipfoc('" + areaid
						+ "','" + up_areaid + "','" + areaName + "');\">"
						+ areaName + "</li>");
			}
			Ssb.append("</ul></div>");
		}
		return Ssb.toString();
	}

	/**
	 * @author : WXP
	 * @param :specstr
	 *            商品规格串
	 * @date Feb 26, 2014 10:35:17 AM
	 * @Method Description :获取商品属性
	 */
	public List getGoodsAttr(String specstr, String goods_id) {
		// 获取对应商品属性
		Map attrMap = new HashMap();
		String specStr[] = specstr.split(",");
		if (specStr.length > 0) {
			for (int i = 0; i < specStr.length; i++) {
				attrMap.put("specstr_like" + i, specStr[i]);
			}
		}
		attrMap.put("goods_id", goods_id);
		attrMap.put("sale_price_asc", "sale_price_asc");
		List goodsAttrList = this.goodsattrDao.getWebList(attrMap);
		return goodsAttrList;
	}

	/**
	 * @author:HXM
	 * @date:May 6, 201410:52:42 AM
	 * @param:
	 * @Description:修改商品的活动状态
	 */
	public void updateActiveState(Map map) {
		goodsDao.updateActiveState(map);
	}

	/**
	 * @author:QJY
	 * @date:2015-08-24
	 * @param:map
	 * @Description:更新商品的销售数量
	 */
	public void updateSalesVolume(Map map) throws Exception {
		this.goodsDao.updateSalesVolume(map);
	}

    
	/**
	 * 导入商品
	 */
	public  boolean importGoods(String file) throws IOException{
		  //创建一个list 用来存储读取的内容
		    List list = new ArrayList();
		    WorkbookSettings settings = new WorkbookSettings();
		    Workbook rwb = null;
		    Cell cell = null;
		    //创建输入流
		    InputStream stream = new FileInputStream(file);
		    //获取Excel文件对象
		    try {
		    	settings.setEncoding("utf-8");
				rwb = Workbook.getWorkbook(stream, settings);
				//获取文件的指定工作表 默认的第一个
			    Sheet sheet = rwb.getSheet(0);  
			    String cat_attr, goods_name, goods_en_name, goods_no, bar_code, unit, quality, goods_place, producer, goods_source, product_address, export, depot_id, tax_attr, logsweight, volume,weight, stock,goods_width,goods_height,goods_long,warehouse_number,brand_id = "";
			    double sale_price, market_price = 0;
				//创建一个数组 用来存储第一行的值
				String[] first = new String[sheet.getColumns()];
				//行数(表头的目录不需要，从1开始)
			     //列数
			     for(int i=0; i<sheet.getColumns(); i++){
			      //获取第i行，第j列的值
			      cell = sheet.getCell(i,0);    
			      first[i] = cell.getContents();
			     }
			     //储存商品
			     //行数(表头的目录不需要，从1开始)
			     for(int i=1; i<sheet.getRows(); i++){
			    	//判断多余或者空行
			    	if(!ValidateUtil.isRequired((sheet.getCell(3,i)).getContents())){
			    	warehouse_number = (sheet.getCell(0,i)).getContents().trim();
			    	cat_attr = (sheet.getCell(2,i)).getContents().replace("#", ",").trim();// 分类编码
					goods_name = (sheet.getCell(3,i)).getContents().trim();// 商品名称
					goods_en_name = (sheet.getCell(4,i)).getContents().trim();// 英文名称
					goods_no = (sheet.getCell(5,i)).getContents().trim();// 商品编号
					bar_code = (sheet.getCell(6,i)).getContents().trim();// 条形码
					unit = (sheet.getCell(7,i)).getContents().trim();// 计量单位
					weight = (sheet.getCell(8,i)).getContents().trim();// 重量
					quality = (sheet.getCell(9,i)).getContents().trim();// 保质期
					goods_place = (sheet.getCell(10,i)).getContents().trim();// 产地
					producer = (sheet.getCell(11,i)).getContents().trim();// 生产商
					goods_source = (sheet.getCell(12,i)).getContents().trim();// 商品来源
					product_address = (sheet.getCell(13,i)).getContents().trim();// 生产地址
					export = (sheet.getCell(14,i)).getContents().trim();// 出口经销商
					depot_id = (sheet.getCell(15,i)).getContents().trim();// 贸易方式
					tax_attr = (sheet.getCell(16,i)).getContents().replace("#", ",").trim();// 税率编码
					market_price = Double.valueOf((sheet.getCell(17,i)).getContents().trim());// 原价
					sale_price = Double.valueOf((sheet.getCell(18,i)).getContents().trim());// 售价
					stock = (sheet.getCell(19,i)).getContents().trim();// 库存
					volume = (sheet.getCell(20,i)).getContents().trim();// 物流体积
					logsweight = (sheet.getCell(21,i)).getContents().trim();// 物流重量
					goods_long = (sheet.getCell(22,i)).getContents().trim();//商品长度
					goods_width = (sheet.getCell(23,i)).getContents().trim();//商品宽度
					goods_height = (sheet.getCell(24,i)).getContents().trim();//商品高度
					brand_id = (sheet.getCell(25,i)).getContents().trim();//商品品牌
					if(ValidateUtil.isRequired(warehouse_number)) {
						warehouse_number = "185";
					}
					if(ValidateUtil.isRequired(stock)||ValidateUtil.isDigital(stock)){
						stock = "9999";
					}
					if(volume.equals("")){
						volume = "1";
					}
					
					if(ValidateUtil.isRequired(goods_long)) {
						goods_long = "1";
					}
					
					if(ValidateUtil.isRequired(goods_width)) {
						goods_width = "1";
					}
					
					if(ValidateUtil.isRequired(goods_height)) {
						goods_height = "1";
					}
					
					if(ValidateUtil.isRequired(goods_no)) {
						goods_no = RandomStrUtil.getRand("10");
					}else {
						Map map = new HashMap();
						map.put("goods_item", goods_no);
						List goodsattrlist = this.goodsattrDao.getList(map);
						if(goodsattrlist != null && goodsattrlist.size() > 0) {
							goods_no = RandomStrUtil.getRand("10");
						}
					}
					if(ValidateUtil.isRequired(brand_id)||ValidateUtil.isDigital(brand_id)){
						brand_id="0";
					}
					
					
					
					// 创建商品对象
					Goods goods = new Goods();
					// 商品对象赋值
					goods.setCust_id("0");
					goods.setCat_attr(cat_attr.replace("#", ",").trim());
					goods.setGoods_name(goods_name);
					goods.setGoods_no(goods_no);
					goods.setBrand_id(brand_id);
					goods.setSeo_title(goods_name);
					goods.setSeo_keyword(goods_name);
					goods.setSeo_desc(goods_name);
					goods.setUnit(unit);
					goods.setIs_del("1");
					goods.setInfo_state("1");
					goods.setIs_up("0");
					goods.setIs_ship("1");
					SimpleDateFormat df = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");// 设置日期格式
					String nowtime = df.format(new Date());
					goods.setIn_date(nowtime);
					goods.setUser_id("efpo2oadmin");
					goods.setClick_num(0);
					goods.setIs_virtual("1");
					goods.setSort_order("100");
					goods.setIs_limit("1");
					goods.setGoods_place(goods_place);
					goods.setActive_state("0");
					goods.setWeight(weight);
					goods.setGoods_source(goods_source);
					goods.setUse_integral("0");
					goods.setDepot_id(depot_id);
					goods.setTax_attr(tax_attr);
					goods.setTax_rate(getTaxrate(tax_attr).replace("#", ",").trim());
					goods.setIef_id("1");
					goods.setBar_code(bar_code);
					goods.setGoods_en_name(goods_en_name);
					goods.setProducer(producer);
					goods.setProduct_address(product_address);
					goods.setExport(export);
					goods.setIs_customs("/include/admin/images/hai.png");
					goods.setInspection("/include/admin/images/gou.png");
					goods.setQuality(quality);
					goods.setWarehouse_number(warehouse_number);
					goods.setShip_id("1");
					goods.setGoods_long(goods_long);
					goods.setGoods_width(goods_width);
					goods.setGoods_height(goods_height);
					goods.setBrand_id(brand_id);
					String goods_id = this.goodsDao.insertGetPk(goods);

					// 创建goodsattr对象
					Goodsattr goodsattr = new Goodsattr();
					goodsattr.setGoods_id(goods_id);
					goodsattr.setMarket_price(market_price);
					goodsattr.setSale_price(sale_price);
					goodsattr.setStock(stock);
					goodsattr.setLogsweight(logsweight);
					goodsattr.setVolume(volume);
					goodsattr.setGoods_item(goods_no);
					goodsattr.setSpecstr("");
					goodsattr.setUp_goods("0");
					goodsattr.setSale_num("0");
					this.goodsattrDao.insert(goodsattr);

					// 属性对象
					for (int j = 26; j < first.length; j++) {
						if(!ValidateUtil.isRequired(first[j])&&":".equals(first[j])) {
							String self[] = first[j].split(":");
							Selfextendattr selfextendattr = new Selfextendattr();
							selfextendattr.setGoods_id(goods_id);
							selfextendattr.setEx_attr_id(self[0]);
							selfextendattr.setEx_attr_alias(self[1]);
							selfextendattr.setEx_attr_value((sheet.getCell(j,i)).getContents().trim());
							this.selfextendattrDao.insert(selfextendattr);
						}else {
							break;
						}
					}
			      }
			   }
			     
			    return true;
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    return false;
		  }
   
	
	/**
	 * 导入商品,检查商品数据是否合格
	 * @throws BiffException 
	 */
	public  String importCheckGoods(String file) throws IOException, BiffException{
		    String rwNumber="";//用来检查存在错误的数据行
		    //创建一个list 用来存储读取的内容
		    WorkbookSettings settings = new WorkbookSettings();
		    Workbook rwb = null;
		    Cell cell = null;
		    //创建输入流
		    InputStream stream = new FileInputStream(file);
		    //获取Excel文件对象
	    	settings.setEncoding("utf-8");
			rwb = Workbook.getWorkbook(stream, settings);
			//获取文件的指定工作表 默认的第一个
		    Sheet sheet = rwb.getSheet(0);  
		    int count=0;
		    //
		    
		    
		     //行数(表头的目录不需要，从1开始)
		     for(int i=1; i<sheet.getRows(); i++){
		    	 //判断多余或者空行
		    	 if(!ValidateUtil.isRequired((sheet.getCell(3,i)).getContents())){
		    		 count++;
		    		//warehouse_number = (sheet.getCell(0,i)).getContents().trim();//仓库编码
			    	 if(ValidateUtil.isRequired((sheet.getCell(0,i)).getContents())||ValidateUtil.isDigital((sheet.getCell(0,i)).getContents())){
			    		rwNumber+=(i+1)+",";
			    		continue;
			    	}
			    	//cat_attr = (sheet.getCell(2,i)).getContents().replace("#", ",").trim();// 分类编码
			    	if(ValidateUtil.isRequired((sheet.getCell(2,i)).getContents())){
			    		rwNumber+=(i+1)+",";
			    		continue;
			    	}
					//goods_name = (sheet.getCell(3,i)).getContents().trim();// 商品名称
			    	if(ValidateUtil.isRequired((sheet.getCell(3,i)).getContents())){
			    		rwNumber+=(i+1)+",";
			    		continue;
			    	}
					//goods_en_name = (sheet.getCell(4,i)).getContents().trim();// 英文名称
					//goods_no = (sheet.getCell(5,i)).getContents().trim();// 商品编号
					//if(ValidateUtil.isRequired((sheet.getCell(5,i)).getContents())){
			    	//	rwNumber+=(i+1)+",";
			    	//	continue;
			    	//}else{
			    	//	String  goods_no="";
			    	//	goods_no = (sheet.getCell(5,i)).getContents().trim().replaceAll(" ", "");
			    	//	Map map = new HashMap();
					//	map.put("goods_item", goods_no);
					//	List goodsattrlist = this.goodsattrDao.getList(map);
					//	if(goodsattrlist != null && goodsattrlist.size() > 0) {
					//		rwNumber+=(i+1)+",";
				    //		continue;
					//	}
			    	//}
					//bar_code = (sheet.getCell(6,i)).getContents().trim();// 条形码
					//if(ValidateUtil.isRequired((sheet.getCell(6,i)).getContents())){
			    	//	rwNumber+=(i+1)+",";
			    	//	continue;
			    	//}
					//unit = (sheet.getCell(7,i)).getContents().trim();// 计量单位
					//weight = (sheet.getCell(8,i)).getContents().trim();// 重量
					//quality = (sheet.getCell(9,i)).getContents().trim();// 保质期
					//goods_place = (sheet.getCell(10,i)).getContents().trim();// 产地
					if(ValidateUtil.isRequired((sheet.getCell(10,i)).getContents())){
			    		rwNumber+=(i+1)+",";
			    		continue;
			    	}
					//producer = (sheet.getCell(11,i)).getContents().trim();// 生产商
					//goods_source = (sheet.getCell(12,i)).getContents().trim();// 商品来源
					if(ValidateUtil.isRequired((sheet.getCell(12,i)).getContents())||ValidateUtil.isDigital((sheet.getCell(12,i)).getContents())){
			    		rwNumber+=(i+1)+",";
			    		continue;
			    	}
					//product_address = (sheet.getCell(13,i)).getContents().trim();// 生产地址
					//export = (sheet.getCell(14,i)).getContents().trim();// 出口经销商
					//depot_id = (sheet.getCell(15,i)).getContents().trim();// 贸易方式
					if(ValidateUtil.isRequired((sheet.getCell(15,i)).getContents())||ValidateUtil.isDigital((sheet.getCell(15,i)).getContents())){
			    		rwNumber+=(i+1)+",";
			    		continue;
			    	}
					//tax_attr = (sheet.getCell(16,i)).getContents().replace("#", ",").trim();// 税率编码
					if(ValidateUtil.isRequired((sheet.getCell(16,i)).getContents())){
			    		rwNumber+=(i+1)+",";
			    		continue;
			    	}
					//market_price = Double.valueOf((sheet.getCell(17,i)).getContents().trim());// 原价
					if(ValidateUtil.isRequired((sheet.getCell(17,i)).getContents())||ValidateUtil.isDouble((sheet.getCell(17,i)).getContents())){
			    		rwNumber+=(i+1)+",";
			    		continue;
			    	}
					//sale_price = Double.valueOf((sheet.getCell(18,i)).getContents().trim());// 售价
					if(ValidateUtil.isRequired((sheet.getCell(18,i)).getContents())||ValidateUtil.isDouble((sheet.getCell(18,i)).getContents())){
			    		rwNumber+=(i+1)+",";
			    		continue;
			    	}
					//stock = (sheet.getCell(19,i)).getContents().trim();// 库存
					if(ValidateUtil.isRequired((sheet.getCell(19,i)).getContents())||ValidateUtil.isDigital((sheet.getCell(19,i)).getContents())){
			    		rwNumber+=(i+1)+",";
			    		continue;
			    	}
					//volume = (sheet.getCell(20,i)).getContents().trim();// 物流体积2
					if(ValidateUtil.isRequired((sheet.getCell(20,i)).getContents())){
			    		rwNumber+=(i+1)+",";
			    		continue;
			    	}
					//logsweight = (sheet.getCell(21,i)).getContents().trim();// 物流重量
					if(ValidateUtil.isRequired((sheet.getCell(21,i)).getContents())){
			    		rwNumber+=(i+1)+",";
			    		continue;
			    	}
					//goods_long = (sheet.getCell(22,i)).getContents().trim();//商品长度
					//goods_width = (sheet.getCell(23,i)).getContents().trim();//商品宽度
					//goods_height = (sheet.getCell(24,i)).getContents().trim();//商品高度
					//brand_id = (sheet.getCell(25,i)).getContents().trim();//商品品牌
					if(ValidateUtil.isRequired((sheet.getCell(25,i)).getContents())){
			    		rwNumber+=(i+1)+",";
			    		continue;
			    	}
		    		 
		    	 }
		    	
		     }
		     if(!ValidateUtil.isRequired(rwNumber)){
		    	 rwNumber=rwNumber.substring(0,rwNumber.length()-1);
		    	 rwNumber="请检查第<"+rwNumber+">行的数据!";
		     }else {
				if(count==0){
					rwNumber="导入失败,获取到<"+count+">条数据,请检查表格数据!";
				}
			}
		    return rwNumber;
    }
	
    /**
     * 导入跨境通商品
     */
	public  boolean importKtjGoods(String file) throws IOException{
		  //创建一个list 用来存储读取的内容
		    List list = new ArrayList();
		    WorkbookSettings settings = new WorkbookSettings();
		    Workbook rwb = null;
		    Cell cell = null;
		    
		    
		    //创建输入流
		    InputStream stream = new FileInputStream(file);
		    
		    //获取Excel文件对象
		    try {
		    	settings.setEncoding("utf-8");
				rwb = Workbook.getWorkbook(stream, settings);
				//获取文件的指定工作表 默认的第一个
			    Sheet sheet = rwb.getSheet(0);  
			    String goods_id,goods_no,kjt_id,warehouse_number = "",bar_code;
			    
			     
			     //储存商品
			     //行数(表头的目录不需要，从1开始)
			     for(int i=1; i<sheet.getRows(); i++){
			    	 
			    	 goods_id = (sheet.getCell(0,i)).getContents().trim(); //商品ID
			    	 goods_no = (sheet.getCell(1,i)).getContents().trim(); //商品编号
			    	 bar_code = (sheet.getCell(2,i)).getContents().trim(); //跨境通ID
			    	 warehouse_number = (sheet.getCell(5,i)).getContents().trim(); //仓库编号
			    	 kjt_id = (sheet.getCell(6,i)).getContents().trim(); //跨境通ID
			    	//判断商品ID不为空 
					if(!ValidateUtil.isRequired(goods_id)) {
						// 创建商品对象
						Goods goods = this.goodsDao.get(goods_id);
						//判断商品对象不为空
						if(goods != null) {
							//判断商品编号不为空 
							if(!ValidateUtil.isRequired(goods_no)) {
								goods.setGoods_no(goods_no);
							}
							//判断仓库编号不为空 
							if(!ValidateUtil.isRequired(warehouse_number)) {
								goods.setWarehouse_number(warehouse_number);
							}
							//判断跨境通ID不为空 
							if(!ValidateUtil.isRequired(kjt_id)) {
								goods.setKjt_id(kjt_id);
							}
							//判断条形码不为空 
							if(!ValidateUtil.isRequired(bar_code)) {
								goods.setBar_code(bar_code);
							}
							//修改商品
							this.goodsDao.update(goods);
						}
						
					}
					

			     }
			     return true;
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    return false;
		  }
	
	
	/**
	 * 导出商品
	 * @param goodsList 商品集合
	 * @param response 
	 * @return
	 * @throws IOException
	 */
	public boolean exportGoods(List goodsList, HttpServletResponse response) throws Exception {
		boolean flag = false;
		if(goodsList != null && goodsList.size() > 0) {
		response.reset();
	    response.setContentType("application/vnd.ms-excel");
	    DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	    String nowtime = df.format(new Date());
	    response.setHeader("Content-Disposition", "filename="+nowtime+"Goods.xls");//attachment // WritableWorkbook是JexcelApi的一个类。
	    // 以下可以理解为创建一个excel文件，然后在excel里面创建一个表
	    OutputStream os = response.getOutputStream();//取得输出流
	    WritableWorkbook workbook = Workbook.createWorkbook(os);
	    WritableSheet sheet = workbook.createSheet("商品列表", 0); // 组织excel文件的内容
	    jxl.write.Label label = null;
	    int excelCol = 0;
	    int row = 0;
	    try {
	    	WritableCellFormat wc = new WritableCellFormat();
	    	wc.setVerticalAlignment(VerticalAlignment.CENTRE); 
	        wc.setAlignment(Alignment.CENTRE); 
	        
	        label = new jxl.write.Label(excelCol++, row, "商品ID", wc);
	        sheet.setColumnView(0,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "商品编号", wc);
	        sheet.setColumnView(1,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "商品条形码", wc);
	        sheet.setColumnView(2,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "商品名称", wc);
	        sheet.setColumnView(3,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "商品分类", wc);
	        sheet.setColumnView(4,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "仓库编号", wc);
	        sheet.setColumnView(5,35);
	        sheet.addCell(label);
	       
	        label = new jxl.write.Label(excelCol++, row, "跨境通商品ID", wc);
	        sheet.setColumnView(6,35);
	        sheet.addCell(label);
	        
	        
	        jxl.write.DateTime dateTime;
	        jxl.write.DateFormat customDateFormat = new jxl.write.DateFormat("yyyy-MM-dd");//时间格式
	        WritableCellFormat dateFormat = new WritableCellFormat(customDateFormat);       
	          for(int i = 0; i < goodsList.size(); i ++){
	            
	            Map goodsMap = (Map)goodsList.get(i);
	            excelCol = 0;
	            row = i + 1;      
	            String goods_id,goods_name,cat_attr,goods_no,warehouse_number,kjt_id,bar_code;
	            
	            if(goodsMap.get("goods_id") != null){
	            	goods_id = goodsMap.get("goods_id").toString();
	            }else {
	            	goods_id = "";
				}
	            
	            if(goodsMap.get("bar_code") != null){
	            	bar_code = goodsMap.get("bar_code").toString();
	            }else {
	            	bar_code = "";
				}
	            
	            
	            if(goodsMap.get("goods_name") != null){
	            	goods_name = goodsMap.get("goods_name").toString();
	            }else {
	            	goods_name = "";
				}
	            
	            if(goodsMap.get("goods_no") != null){
	            	goods_no = goodsMap.get("goods_no").toString();
	            }else {
	            	goods_no = "";
				}
	            
	            if(goodsMap.get("cat_attr") != null){
	            	cat_attr = goodsMap.get("cat_attr").toString();
	            }else {
	            	cat_attr = "";
				}
	            
	            if(goodsMap.get("warehouse_number") != null){
	            	warehouse_number = goodsMap.get("warehouse_number").toString();
	            }else {
	            	warehouse_number = "";
				}
	            
	            if(goodsMap.get("kjt_id") != null){
	            	kjt_id = goodsMap.get("kjt_id").toString();
	            }else {
	            	kjt_id="";
				}
	            
	          //商品商品ID 
	           label = new jxl.write.Label(excelCol++, row, goods_id, wc);
	           sheet.addCell(label);
	           
	           //商品编码
	           label = new jxl.write.Label(excelCol++, row, goods_no, wc);
	           sheet.addCell(label);  
	           
	           //商品条形码
	           label = new jxl.write.Label(excelCol++, row, bar_code, wc);
	           sheet.addCell(label); 
	            
	           //商品名称
	           label = new jxl.write.Label(excelCol++, row, goods_name, wc);
	           sheet.addCell(label);   
	           
	           //商品分类
	           label = new jxl.write.Label(excelCol++, row,  cat_attr, wc);
	           sheet.addCell(label);
	           
	           //仓库编号
	           label = new jxl.write.Label(excelCol++, row, warehouse_number, wc);
	           sheet.addCell(label);
	            
	           //跨境通商品ID
	           label = new jxl.write.Label(excelCol++, row, kjt_id, wc);
	           sheet.addCell(label);
	            
	           }
	         flag = true; 
	       //生成excel文件
		   workbook.write();
	    }catch (Exception e) {
	        e.printStackTrace();
	    } finally{
	          workbook.close();  
	  	      os.flush();  
		  	  os.close();  
		  	  os=null;  
	  	      response.flushBuffer(); 	        
	    }
	   }
		return flag;
	}
	
	/**
	 * 商品导出
	 * @param goodsList 红包集合
	 * @throws IOException 
	 * @throws WriteException 
	 */
	public boolean exprotExcel(List exList, HttpServletResponse response) throws IOException, WriteException {
		boolean flag = false;
		if(exList != null && exList.size()> 0) {
		response.reset();
	    response.setContentType("application/vnd.ms-excel");
	    DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	    String nowtime = df.format(new Date());
	    response.setHeader("Content-Disposition", "filename="+nowtime+"goods.xls");//attachment // WritableWorkbook是JexcelApi的一个类。
	    // 以下可以理解为创建一个excel文件，然后在excel里面创建一个表
	    OutputStream os = response.getOutputStream();//取得输出流
	    WritableWorkbook workbook = Workbook.createWorkbook(os);
	    WritableSheet sheet = workbook.createSheet("商品列表", 0); // 组织excel文件的内容
	    jxl.write.Label label = null;
	    int excelCol = 0;
	    int row = 0;
	    try {
	    	WritableCellFormat wc = new WritableCellFormat();
	    	wc.setVerticalAlignment(VerticalAlignment.CENTRE); 
	        wc.setAlignment(Alignment.CENTRE); 
	        
	        label = new jxl.write.Label(excelCol++, row, "商品ID", wc);
	        sheet.setColumnView(0,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "商品名称", wc);
	        sheet.setColumnView(1,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "外文名称", wc);
	        sheet.setColumnView(1,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "商品编号", wc);
	        sheet.setColumnView(2,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "条形码", wc);
	        sheet.setColumnView(3,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "品牌", wc);
	        sheet.setColumnView(4,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "计量单位", wc);
	        sheet.setColumnView(5,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "净含量", wc);
	        sheet.setColumnView(6,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "长度(厘米)", wc);
	        sheet.setColumnView(7,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "宽度(厘米)", wc);
	        sheet.setColumnView(8,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "高度(厘米)", wc);
	        sheet.setColumnView(9,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "保质期", wc);
	        sheet.setColumnView(10,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "产地", wc);
	        sheet.setColumnView(11,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "生产商", wc);
	        sheet.setColumnView(12,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "商品来源", wc);
	        sheet.setColumnView(13,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "生产地址", wc);
	        sheet.setColumnView(14,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "出口经销商", wc);
	        sheet.setColumnView(15,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "贸易方式", wc);
	        sheet.setColumnView(16,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "海关商品ID", wc);
	        sheet.setColumnView(17,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "仓库编码", wc);
	        sheet.setColumnView(18,35);
	        sheet.addCell(label);
	        
	        
	        label = new jxl.write.Label(excelCol++, row, "原价(元)", wc);
	        sheet.setColumnView(19,35);
	        sheet.addCell(label);
	        
	        
	        label = new jxl.write.Label(excelCol++, row, "销售价(元)", wc);
	        sheet.setColumnView(20,35);
	        sheet.addCell(label);
	        
	        
	        label = new jxl.write.Label(excelCol++, row, "库存", wc);
	        sheet.setColumnView(21,35);
	        sheet.addCell(label);
	        
	        
	        label = new jxl.write.Label(excelCol++, row, "物流体积(立方厘米)", wc);
	        sheet.setColumnView(22,35);
	        sheet.addCell(label);
	        
	        
	        label = new jxl.write.Label(excelCol++, row, "物流重量(千克)", wc);
	        sheet.setColumnView(23,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "税率", wc);
	        sheet.setColumnView(24,35);
	        sheet.addCell(label);
	        
	        
	         
	        jxl.write.DateTime dateTime;
	        jxl.write.DateFormat customDateFormat = new jxl.write.DateFormat("yyyy-MM-dd");//时间格式
	        WritableCellFormat dateFormat = new WritableCellFormat(customDateFormat);       
	          for(int i = 0; i < exList.size(); i ++){
	            
	            Map goodsMap = (Map)exList.get(i);
	            excelCol = 0;
	            row = i + 1;      
	            String  goods_id,goods_name,goods_no,bar_code,
	            brand_id,unit,weight,goods_long,goods_width,goods_height,quality,goods_place,producer,goods_source,product_address,export,
	            depot_id,kjt_id,warehouse_number,goods_market_price,min_sale_price,cost_price,total_stock,volume,logsweight,goods_en_name,tax_rate;
	            
	            
                //商品名称	            
	            if(goodsMap.get("goods_id") != null){
	            	goods_id = goodsMap.get("goods_id").toString();
	            }else {
	            	goods_id = "";
				}
	            
	           //获取商品属性表信息
	            List goodsattrList=new ArrayList();
	            Map goodsattrMap=new HashMap();
	            Map ggattrMap=new HashMap();
	            if(!ValidateUtil.isRequired(goods_id)){
	                goodsattrMap.put("goods_id", goods_id);
	 	            goodsattrList=goodsattrDao.getList(goodsattrMap);
	 	            if(goodsattrList!=null&&goodsattrList.size()>0){
	 	            	ggattrMap=(HashMap)goodsattrList.get(0);
	 	            }
	            }
	            //商品名称
	            if(goodsMap.get("goods_name") != null){
	            	goods_name = goodsMap.get("goods_name").toString();
	            }else {
	            	goods_name = "";
				}
	            //外文名称
	            if(goodsMap.get("goods_en_name") != null){
	            	goods_en_name = goodsMap.get("goods_en_name").toString();
	            }else {
	            	goods_en_name = "";
				}
	            
	            //商品编号
	            if(goodsMap.get("goods_no") != null){
	            	goods_no = goodsMap.get("goods_no").toString();
	            }else {
	            	goods_no = "";
				}
	            //条形码
	            if(goodsMap.get("bar_code") != null){
	            	bar_code = goodsMap.get("bar_code").toString();
	            }else {
	            	bar_code = "";
				}
	            //品牌
	            if(goodsMap.get("brand_id") != null&&!"".equals(goodsMap.get("brand_id"))&&!ValidateUtil.isDigital(goodsMap.get("brand_id").toString())){
	            	String  id = goodsMap.get("brand_id").toString();
	            	if(goodsbrandDao.get(goodsMap.get("brand_id").toString())!=null){
	            		brand_id = goodsbrandDao.get(id).getBrand_name().toString();
	            	}else {
	            		brand_id="";
					}
	            	
	            }else {
	            	brand_id = "";
				}
	            //计量单位
	            if(goodsMap.get("unit") != null){
	            	unit = goodsMap.get("unit").toString();
	            }else {
	            	unit = "";
				}
	            //净含量
	            if(goodsMap.get("weight") != null){
	            	weight = goodsMap.get("weight").toString();
	            }else {
	            	weight = "";
				}
	            //长度
	            if(goodsMap.get("goods_long") != null){
	            	goods_long = goodsMap.get("goods_long").toString();
	            }else {
	            	goods_long = "";
				}
	           //宽度
	            if(goodsMap.get("goods_width") != null){
	            	goods_width = goodsMap.get("goods_width").toString();
	            }else {
	            	goods_width = "";
				}
	            
	            //高度
	            if(goodsMap.get("goods_height") != null){
	            	goods_height = goodsMap.get("goods_height").toString();
	            }else {
	            	goods_height = "";
				}
	            
	            //保质期
	            if(goodsMap.get("quality") != null){
	            	quality = goodsMap.get("quality").toString();
	            }else {
	            	quality = "";
				}

	            //产地
	            if(goodsMap.get("goods_place") != null&&!"".equals(goodsMap.get("goods_place"))&&areaDao.get(goodsMap.get("goods_place") .toString())!=null){
	            	String  id = goodsMap.get("goods_place").toString();
	            	goods_place = areaDao.get(id).getArea_name();
	            }else {
	            	goods_place = "";
				}
	            
	            //生产商
	            if(goodsMap.get("producer") != null){
	            	producer = goodsMap.get("producer").toString();
	            }else {
	            	producer = "";
				}
	            
	            
	            //商品来源
	            if(goodsMap.get("goods_source") != null){
	            	goods_source = goodsMap.get("goods_source").toString();
	            	if("0".equals(goods_source)){
	            		goods_source = "自主运营";
	            	}else if("1".equals(goods_source)){
	            		goods_source = "供应商供货";
	            	}
	            }else {
	            	goods_source = "";
				}
	            
	            //生产地址
	            if(goodsMap.get("product_address") != null){
	            	product_address = goodsMap.get("product_address").toString();
	            }else {
	            	product_address = "";
				}
	            
	            
	            //出口经销商
	            if(goodsMap.get("export") != null){
	            	export = goodsMap.get("export").toString();
	            }else {
	            	export = "";
				}
	            
	            
	            //贸易方式
	            if(goodsMap.get("depot_id") != null){
	            	depot_id = goodsMap.get("depot_id").toString();
	            	if("2".equals(depot_id)){
	            		depot_id = "保税仓";
	            	}else{
	            		depot_id = "跨境直邮";
	            	}
	            }else {
	            	depot_id = "";
				}
	            
	          //跨境通商品ID
	            if(goodsMap.get("kjt_id") != null){
	            	kjt_id = goodsMap.get("kjt_id").toString();
	            }else {
	            	kjt_id = "";
				}
	            
	           //仓库编码
	            if(goodsMap.get("warehouse_number") != null){
	            	warehouse_number = goodsMap.get("warehouse_number").toString();
	            }else {
	            	warehouse_number = "";
				}
	            
	            //原价
	            if(goodsMap.get("goods_market_price") != null){
	            	goods_market_price = goodsMap.get("goods_market_price").toString();
	            }else {
	            	goods_market_price = "";
				}
	            
	            //销售价
	            if(goodsMap.get("min_sale_price") != null){
	            	min_sale_price = goodsMap.get("min_sale_price").toString();
	            }else {
	            	min_sale_price = "";
				}
	            
	            
	            //库存
	            if(goodsMap.get("total_stock") != null){
	            	total_stock = goodsMap.get("total_stock").toString();
	            }else {
	            	total_stock = "";
				}
	            //物流体积
	            if(ggattrMap!=null&&ggattrMap.get("volume") != null){
	            	volume = ggattrMap.get("volume").toString();
	            }else {
	            	volume = "";
				}
	            
	            // 物流重量
	            if(ggattrMap!=null&&ggattrMap.get("logsweight") != null){
	            	logsweight = ggattrMap.get("logsweight").toString();
	            }else {
	            	logsweight = "";
				}
	            
	            //商品税率值
	            if(goodsMap.get("tax_rate") != null){
	            	tax_rate = goodsMap.get("tax_rate").toString()+"%";
	            }else {
	            	tax_rate = "0%";
				}
	            
	            
	            
	           
	           //商品ID
	           label = new jxl.write.Label(excelCol++, row, goods_id, wc);
	           sheet.addCell(label);  
	           
	           //商品名称
	           label = new jxl.write.Label(excelCol++, row, goods_name, wc);
	           sheet.addCell(label); 
	           
	           //外文名称
	           label = new jxl.write.Label(excelCol++, row, goods_en_name, wc);
	           sheet.addCell(label); 
	           
	           //商品编号
	           label = new jxl.write.Label(excelCol++, row, goods_no, wc);
	           sheet.addCell(label);
	           
	           //条形码
	           label = new jxl.write.Label(excelCol++, row, bar_code, wc);
	           sheet.addCell(label);  
	           
	           //品牌
	           label = new jxl.write.Label(excelCol++, row, brand_id, wc);
	           sheet.addCell(label); 
	           
	           //计量单位
	           label = new jxl.write.Label(excelCol++, row, unit, wc);
	           sheet.addCell(label);
	           
	           //净含量
	           label = new jxl.write.Label(excelCol++, row, weight, wc);
	           sheet.addCell(label);  
	           
	           //长度
	           label = new jxl.write.Label(excelCol++, row, goods_long, wc);
	           sheet.addCell(label); 
	           
	           //宽度
	           label = new jxl.write.Label(excelCol++, row, goods_width, wc);
	           sheet.addCell(label); 
	           
	           //高度
	           label = new jxl.write.Label(excelCol++, row, goods_height, wc);
	           sheet.addCell(label); 

	           //保质期
	           label = new jxl.write.Label(excelCol++, row, quality, wc);
	           sheet.addCell(label); 
	           
	           //产地
	           label = new jxl.write.Label(excelCol++, row, goods_place, wc);
	           sheet.addCell(label); 
	           
	           //生产商
	           label = new jxl.write.Label(excelCol++, row, producer, wc);
	           sheet.addCell(label); 
	           
	           //商品来源
	           label = new jxl.write.Label(excelCol++, row, goods_source, wc);
	           sheet.addCell(label); 
	           
	           //生产地址
	           label = new jxl.write.Label(excelCol++, row, product_address, wc);
	           sheet.addCell(label); 
	           
	           //出口经销商
	           label = new jxl.write.Label(excelCol++, row, export, wc);
	           sheet.addCell(label); 
	           
	           //贸易方式
	           label = new jxl.write.Label(excelCol++, row, depot_id, wc);
	           sheet.addCell(label); 
	           
	           //跨境通商品ID
	           label = new jxl.write.Label(excelCol++, row, kjt_id, wc);
	           sheet.addCell(label); 
	           
	           //仓库编码
	           label = new jxl.write.Label(excelCol++, row, warehouse_number, wc);
	           sheet.addCell(label); 
	           
	           //市场价
	           label = new jxl.write.Label(excelCol++, row, goods_market_price, wc);
	           sheet.addCell(label); 
	           
	           //销售价格
	           label = new jxl.write.Label(excelCol++, row, min_sale_price, wc);
	           sheet.addCell(label); 
	           
	           //库存
	           label = new jxl.write.Label(excelCol++, row, total_stock, wc);
	           sheet.addCell(label); 
	           
	           //物流体积
	           label = new jxl.write.Label(excelCol++, row, volume, wc);
	           sheet.addCell(label); 
	           
	           //物流重量
	           label = new jxl.write.Label(excelCol++, row, logsweight, wc);
	           sheet.addCell(label); 
	           
	           //税率值
	           label = new jxl.write.Label(excelCol++, row, tax_rate, wc);
	           sheet.addCell(label); 
	           

	           }
	         flag = true; 
	       //生成excel文件
		   workbook.write();
	    }catch (Exception e) {
	    	flag=false;
	    } finally{
	          workbook.close();  
	  	      os.flush();  
		  	  os.close();  
		  	  os=null;  
	  	      response.flushBuffer(); 	        
	    }
	   }
		return flag;
	}
	
	/**
	 * 导出商品
	 * @param goodsList 商品集合
	 * @param response 
	 * @return
	 * @throws IOException
	 */
	public boolean exportGoodsPic(List goodsList, HttpServletResponse response) throws Exception {
		boolean flag = false;
		if(goodsList != null && goodsList.size() > 0) {
		response.reset();
	    response.setContentType("application/vnd.ms-excel");
	    DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	    String nowtime = df.format(new Date());
	    response.setHeader("Content-Disposition", "filename="+nowtime+"GoodsPic.xls");//attachment // WritableWorkbook是JexcelApi的一个类。
	    // 以下可以理解为创建一个excel文件，然后在excel里面创建一个表
	    OutputStream os = response.getOutputStream();//取得输出流
	    WritableWorkbook workbook = Workbook.createWorkbook(os);
	    WritableSheet sheet = workbook.createSheet("商品列表", 0); // 组织excel文件的内容
	    jxl.write.Label label = null;
	    int excelCol = 0;
	    int row = 0;
	    try {
	    	WritableCellFormat wc = new WritableCellFormat();
	    	wc.setVerticalAlignment(VerticalAlignment.CENTRE); 
	        wc.setAlignment(Alignment.CENTRE); 
	        
	        label = new jxl.write.Label(excelCol++, row, "商品ID", wc);
	        sheet.setColumnView(0,20);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "商品编号", wc);
	        sheet.setColumnView(1,25);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "商品名称", wc);
	        sheet.setColumnView(2,100);
	        sheet.addCell(label);

	        jxl.write.DateTime dateTime;
	        jxl.write.DateFormat customDateFormat = new jxl.write.DateFormat("yyyy-MM-dd");//时间格式
	        WritableCellFormat dateFormat = new WritableCellFormat(customDateFormat);       
	          for(int i = 0; i < goodsList.size(); i ++){
	            
	            Map goodsMap = (Map)goodsList.get(i);
	            excelCol = 0;
	            row = i + 1;      
	            String goods_id,goods_name,cat_attr,goods_no,warehouse_number,kjt_id,bar_code;
	            
	            if(goodsMap.get("goods_id") != null){
	            	goods_id = goodsMap.get("goods_id").toString();
	            }else {
	            	goods_id = "";
				}
	            
	            if(goodsMap.get("goods_name") != null){
	            	goods_name = goodsMap.get("goods_name").toString();
	            }else {
	            	goods_name = "";
				}
	            
	            if(goodsMap.get("goods_no") != null){
	            	goods_no = goodsMap.get("goods_no").toString();
	            }else {
	            	goods_no = "";
				}
	            
	            
	          //商品商品ID 
	           label = new jxl.write.Label(excelCol++, row, goods_id, wc);
	           sheet.addCell(label);
	           
	           //商品编码
	           label = new jxl.write.Label(excelCol++, row, goods_no, wc);
	           sheet.addCell(label);  
	           
	           //商品名称
	           label = new jxl.write.Label(excelCol++, row, goods_name, wc);
	           sheet.addCell(label);   
	           }
	         flag = true; 
	       //生成excel文件
		   workbook.write();
	    }catch (Exception e) {
	        e.printStackTrace();
	    } finally{
	          workbook.close();  
	  	      os.flush();  
		  	  os.close();  
		  	  os=null;  
	  	      response.flushBuffer(); 	        
	    }
	   }
		return flag;
	}

	/**
	 * 返回税率
	 * 
	 * @param taxtate
	 * @return
	 */
	public String getTaxrate(String taxtate) {
		String tax_tate = "0";
		if (!ValidateUtil.isRequired(taxtate)) {
			String taxs[] = taxtate.split(",");
			Taxrate tax = taxrateDao.get(taxs[taxs.length - 1]);
			if (tax != null && !ValidateUtil.isRequired(tax.getTax_rate())) {
				tax_tate = tax.getTax_rate();
			} else {
				tax_tate = "0";
			}

		}
		return tax_tate;
	}
	
	
	
	/**
	 * @Method Description :物理删除商品
	 * @author: HXK
	 * @date : Oct 12, 2015 2:24:10 PM
	 * @param 
	 * @return return_type
	 */
	public boolean physicalDeleteGoods(String goodsIdStr) throws IOException{
		boolean flag=true;
		if(!ValidateUtil.isRequired(goodsIdStr)){
			String[]goodsIdStrs=goodsIdStr.split(",");
			if(goodsIdStrs!=null&&goodsIdStrs.length>0){
				for(int i=0;i<goodsIdStrs.length;i++){
					String goods_id="",spec_serial_id="";
					if(!ValidateUtil.isRequired(goodsIdStrs[i])){
						goods_id=goodsIdStrs[i].toString();
						//删除规格名称表selfspecname
						List selfspecnameDList=new ArrayList();
						Map<String, String> selfspecnameMap=new HashMap<String, String>();
						selfspecnameMap.put("goods_id",goods_id );
						selfspecnameDList=this.selfspecnameDao.getList(selfspecnameMap);
						if(selfspecnameDList!=null&&selfspecnameDList.size()>0){
							for(int k=0;k<selfspecnameDList.size();k++){
								HashMap snameMap=new HashMap();	
								snameMap=(HashMap)selfspecnameDList.get(k);
								if(snameMap!=null&&snameMap.get("spec_serial_id")!=null){
									spec_serial_id+=snameMap.get("spec_serial_id").toString();
								}
							}
							//删除规格名称
							selfspecnameDao.deleteByGoodsid(goods_id);
						}
						if(!ValidateUtil.isRequired(spec_serial_id)){
							//删除规格值表selfspecvalue
							this.selfspecvalueDao.deleteByspecSerialId(spec_serial_id);
						}
						//删除扩展属性表selfextendattr
						this.selfextendattrDao.deleteByGoodsid(goods_id);
						//删除审核历史audithistory
						this.audithistoryDao.delete(goods_id);
						//删除商品属性表goodsattr
						this.goodsattrDao.deleteByGoodsid(goods_id);
						//删除商品主表goods
						this.goodsDao.delete(goods_id);
						//删除会员收藏 collect
						this.collectDao.deleteByGoodsId(goods_id);
					}
				}
			}
			
		}
		
		return flag;
	}
	
	
	/**
	 * @author : yu
	 * @date : 2016-1-20
	 * @Method Description : 根据订单获取商品的详细信息
	 */
	public List getInfoByOrder(String order_ids){
		return this.goodsDao.getInfoByOrder(order_ids);
	}
	
	/**
	 * @Method Description :根据商品ID 查找商品楼层标签是否存在该商品
	 * @author: 胡惜坤
	 * @date : Mar 15, 2016 9:00:58 AM
	 * @param goods_id：商品ID串
	 * @return 商品编号
	 */
	public String rgGoodsInFloor(String goods_id){
		String goodsnumber="";
		List goosList=new ArrayList();
		Map linkMap = new HashMap();
		linkMap.put("sgis", goods_id);
		goosList=goodsDao.getList(linkMap);
		if(goosList!=null&&goosList.size()>0){
			for (int i = 0; i < goosList.size(); i++) {
				Map ggMap = new HashMap();
				ggMap=(HashMap)goosList.get(i);
				if(ggMap!=null&&ggMap.get("goods_id")!=null){
					Map tabMap = new HashMap();
					tabMap.put("fm_type", "0");
					tabMap.put("goods_id", ggMap.get("goods_id"));
					List goosfloorList=new ArrayList();
					goosfloorList=goodfloormarkDao.getGoodsFloorList(tabMap);
					if(goosfloorList!=null&&goosfloorList.size()>0){
						goodsnumber=goodsnumber+ggMap.get("goods_no")+",";
					}
				}
			}
			if(goodsnumber!=null&&!"".equals(goodsnumber)){
				goodsnumber=goodsnumber.substring(0,goodsnumber.length()-1);
			}
		}
		return goodsnumber;
	}
	
	/**
	 * @author : yu
	 * @date : 2016-5-9 11:14:25
	 * @Method Description :首页换一换
	 */
	public List getRandGoodsList(Map map){
		return this.goodsDao.getRandGoodsList(map);
	}
	
	
	
	
	
	
	
	
	

}
