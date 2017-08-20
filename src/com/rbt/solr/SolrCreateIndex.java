package com.rbt.solr;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.rbt.common.Constants;
import com.rbt.index.LuceneUtil;

public class SolrCreateIndex {
	// 查询增量公共索引主键语句
	private String com_deSql="select info_id as index_id from indexrecord where module_name=";
	// 公共删除索引记录表indexrecord的数据
	private String com_deIndex="DELETE FROM indexrecord WHERE module_name=";
	// 分类查询的sql
	private String cat_sql="select cat_id,cat_name,en_name,word_index,up_cat_id,cat_level,module_type,is_display,sort_no,member_add,seotitle,seokeyword,seodesc,mem_type,cat_intr,cat_simple,cat_click,cat_id as index_id from category";
	// 品牌查询sql
	private String goodsbrand_sql="select brand_id,brand_name,brand_site,logo,content,goods_attr,is_recom,brand_id as index_id from goodsbrand gb";
	// 地区查询的sql
	private String area_sql="select *,area_id  as index_id from area";
	//商品信息索引SQL
	private String goods_sql="SELECT distinct(g.goods_id),g.goods_id,g.goods_id AS index_id,g.info_state as info_state,g.is_del as is_del," +
			"g.cust_id as cust_id,g.is_up as is_up,LPAD(ga.sale_price,20,'0') AS lpad_price, g.cat_attr,sc.area_attr," +
			"g.goods_name as goods_name, g.brand_id, list_img,"+
			"up_date, down_date, lab,  g.in_date,specstr,market_price,sale_price,cost_price,stock,g.sale_num,"+
			"IF(SUM(od.order_num) IS NULL,0,SUM(od.order_num)) AS order_num,g.in_date as in_date,COUNT(r.trade_id) AS eval_num,click_num	" +
			",COUNT(c.goods_id) as collNum"+
			" FROM goods g LEFT JOIN goodsattr ga ON g.goods_id=ga.goods_id LEFT JOIN orderdetail od ON od.goods_id=g.goods_id "+        
			" LEFT JOIN goodsorder go ON go.order_id=od.order_id LEFT JOIN reviews r ON r.goods_id=g.goods_id " +
			"LEFT JOIN shopconfig sc ON g.cust_id=sc.cust_id"+
			" LEFT JOIN collect c ON g.goods_id=c.goods_id ";
	
	/**	
	 * @author : WXP
	 * @param :sql
	 * @date Apr 27, 2014 12:46:10 PM
	 * @Method Description :删除索引记录
	 */
	private void deleteRecord(String sql){
		SolrUtil su = new SolrUtil();
		su.deleteInfo(sql);
	}

	/**	
	 * @author : WXP
	 * @param :
	 * @date Apr 24, 2014 4:24:02 PM
	 * @Method Description :创建商品索引
	 */
	public void CreateGoodsIndex(String limit_sql) throws IOException{
		String sql = goods_sql+"GROUP BY g.goods_id " + limit_sql;
		createModelIndex(sql);
		System.out.println(sql);
		System.out.println("商品信息全量索引创建成功!");
	}
	/**	
	 * @author : WXP
	 * @param :
	 * @date Apr 24, 2014 4:24:02 PM
	 * @Method Description :增量创建商品索引
	 */
	public void createGoodsIncreIndex() throws IOException{
		String com_sql = " LEFT JOIN indexrecord ir ON  g.goods_id = ir.info_id  WHERE ir.module_name='goods' AND ";
		String ord_gro_sql = " GROUP BY g.goods_id ORDER BY ir.trade_id ASC ";
		String aiu_sql = goods_sql+com_sql+"oper_type in(1,2)"+ord_gro_sql;
		String ad_sql = com_deSql+"'goods' and oper_type='3'";
		//新增或修改索引
		createModelIndex(aiu_sql);
		//删除索引
		deleteModelIndex(ad_sql);
		System.out.println("商品增量索引创建成功!");
		//删除索引记录
		deleteRecord(com_deIndex+"'goods'");
	}
	
	/**	
	 * @author : WXP
	 * @param :
	 * @date Apr 24, 2014 4:24:02 PM
	 * @Method Description :创建分类索引
	 */
	public void createCatIndex(String limit_sql) throws IOException{
		String sql = cat_sql + limit_sql;
		createModelIndex(sql);
		System.out.println(sql);
		System.out.println("分类信息全量索引创建成功!");
	}
	/**	
	 * @author : WXP
	 * @param :
	 * @date Apr 24, 2014 4:24:02 PM
	 * @Method Description :增量创建分类索引
	 */
	public void createCatIncreIndex() throws IOException{
		String com_sql = " LEFT JOIN indexrecord ir ON  category.cat_id = ir.info_id  WHERE ir.module_name='category' AND ";
		String ord_sql = "ORDER BY ir.trade_id ASC ";
		String aiu_sql = cat_sql+com_sql+"oper_type in(1,2)"+ord_sql;
		String ad_sql = com_deSql+"'category' and oper_type='3'";
		//新增或修改索引
		createModelIndex(aiu_sql);
		//删除索引
		deleteModelIndex(ad_sql);
		System.out.println("分类增量索引创建成功!");
		//删除索引记录
		deleteRecord(com_deIndex+"'goods'");
	}
	
	/**	
	 * @author : WXP
	 * @param :
	 * @date Apr 24, 2014 4:24:02 PM
	 * @Method Description :创建地区索引
	 */
	public void createAreaIndex(String limit_sql) throws IOException{
		String sql = area_sql + limit_sql;
		createModelIndex(sql);
		System.out.println(sql);
		System.out.println("地区信息全量索引创建成功!");
	}
	
	/**	
	 * @author : WXP
	 * @param :
	 * @date Apr 24, 2014 4:24:02 PM
	 * @Method Description :增量创建地区索引
	 */
	public void createAreaIncreIndex() throws IOException{
		String com_sql = " LEFT JOIN indexrecord ir ON  area.area_id = ir.info_id  WHERE ir.module_name='area' AND ";
		String ord_sql = "ORDER BY ir.trade_id ASC ";
		String aiu_sql = area_sql+com_sql+"oper_type in(1,2)"+ord_sql;
		String ad_sql = com_deSql+"'category' and oper_type='3'";
		//新增或修改索引
		createModelIndex(aiu_sql);
		//删除索引
		deleteModelIndex(ad_sql);
		System.out.println("地区增量索引创建成功!");
		//删除索引记录
		deleteRecord(com_deIndex+"'goods'");
	}
	
	/**	
	 * @author : WXP
	 * @param :
	 * @date Apr 24, 2014 4:24:02 PM
	 * @Method Description :创建品牌索引
	 */
	public void createGoodsBrandIndex(String limit_sql) throws IOException{
		String sql = goodsbrand_sql + limit_sql;
		createModelIndex(sql);
		System.out.println(sql);
		System.out.println("品牌信息全量索引创建成功!");
	}
	/**	
	 * @author : WXP
	 * @param :
	 * @date Apr 24, 2014 4:24:02 PM
	 * @Method Description :增量创建商品索引
	 */
	public void createGoodsBrandIncreIndex() throws IOException{
		String com_sql = " LEFT JOIN indexrecord ir ON  gb.brand_id = ir.info_id  WHERE ir.module_name='goodsbrand' AND ";
		String ord_sql = "ORDER BY ir.trade_id ASC ";
		String aiu_sql = goodsbrand_sql+com_sql+"oper_type in(1,2)"+ord_sql;
		String ad_sql = com_deSql+"'goodsbrand' and oper_type='3'";
		//新增或修改索引
		createModelIndex(aiu_sql);
		//删除索引
		deleteModelIndex(ad_sql);
		System.out.println("品牌增量索引创建成功!");
		//删除索引记录
		deleteRecord(com_deIndex+"'goods'");
	}
	
	/**	
	 * @author : WXP
	 * @param :sql
	 * @date Apr 24, 2014 4:24:02 PM
	 * @Method Description :创建索引
	 */
	private void createModelIndex(String sql) throws IOException{
		SolrUtil su = new SolrUtil();
		List list = su.getSourceList(sql);
		System.out.println(list);
		if(list != null && list.size() > 0){
			su.createIndex(su.getHttpSolrServer(), list);
		}
	}
	
	/**	
	 * @author : WXP
	 * @param :sql
	 * @date Apr 27, 2014 4:24:02 PM
	 * @Method Description :删除索引
	 */
	private void deleteModelIndex(String sql) throws IOException{
		SolrUtil su = new SolrUtil();
		List list = su.getSourceList(sql);
		System.out.println(list);
		if(list != null && list.size() > 0){
			su.deleteIndex(list);
		}
	}
	
	/**
	 * @param args
	 * 运行的主方法
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		SolrCreateIndex sci = new  SolrCreateIndex();
		sci.createGoodsIncreIndex();
	}
	
}
