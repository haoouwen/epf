package com.rbt.index;

import java.io.IOException;
import java.util.List;


public class CreateIndex {
	// 查询增量公共索引语句
	private String com_deSql = "select oper_type,info_id as index_id from indexrecord where module_name=";
	// 公共删除索引记录表indexrecord的数据
	private String com_deIndex = "DELETE FROM indexrecord WHERE module_name=";
	// 新增商品sql
	private String add_good_sql = "select  DISTINCT(goods.goods_id),active_state,goods.goods_id AS index_id,goods.info_state AS info_state,brand_id,list_img,is_del,is_up,LPAD(ga.sale_price,20,'0') AS lpad_price,REPLACE(goods.cat_attr,',','/') AS cat_attr,REPLACE(sc.area_attr,',','/') AS area_attr,goods_name,market_price,sale_price, (SELECT COUNT(trade_id) FROM reviews WHERE  reviews.goods_id=goods.goods_id) AS eval_num,(SELECT IF(SUM(order_num) IS NULL,0,SUM(order_num)) FROM orderdetail WHERE orderdetail.goods_id=goods.goods_id) AS order_num FROM goods LEFT JOIN goodsattr ga ON goods.goods_id=ga.goods_id LEFT JOIN shopconfig sc ON goods.cust_id=sc.cust_id ";
	// 分类查询的sql
	private String cat_sql = "select *,cat_id as index_id from category";
	// 品牌查询sql
	private String goodsbrand_sql = "select *,brand_id as index_id from goodsbrand";
	// 地区查询的sql
	private String area_sql = "select *,area_id  as index_id from area";
    //商品查询
	private String goods_sql ="SELECT DISTINCT(g.goods_id),g.active_state,g.goods_id AS index_id,g.info_state AS info_state,g.depot_id,g.sale_num as goods_sale_num,"
		+"g.is_del AS is_del,g.cust_id AS cust_id,g.is_up AS is_up,LPAD(ga.sale_price,20,'0') AS lpad_price," 
		+" (SELECT MIN(gtt.sale_price) FROM goodsattr AS gtt WHERE gtt.goods_id=g.goods_id)AS lmin_price,"
		+"REPLACE(g.cat_attr,',','/') AS cat_attr,g.cat_attr as goods_cat,REPLACE(sc.area_attr,',','/') AS area_attr,g.goods_name AS goods_name, brand_id, list_img,"
					+" up_date, down_date, lab,  g.in_date,ga.logsweight,ga.volume,specstr,market_price,sale_price,cost_price,stock,g.sale_num,od.order_id,g.in_date AS in_date,click_num,"
					+"(SELECT COUNT(trade_id) FROM goodseval WHERE  goodseval.goods_id=g.goods_id) AS eval_num,"
					+" g.in_date AS collNum,"
					+"(SELECT IFNULL(SUM(ot.order_num),0) FROM goodsorder go LEFT JOIN orderdetail ot ON go.order_id = ot.order_id LEFT JOIN goods g1 ON g1.goods_id = ot.goods_id LEFT JOIN member m ON m.cust_id = go.buy_cust_id LEFT JOIN member m2 ON m2.cust_id = go.sell_cust_id WHERE ot.goods_id = g.goods_id AND order_state NOT IN ('0', '1')) AS order_num"
					+" FROM goods g LEFT JOIN goodsattr ga ON g.goods_id=ga.goods_id LEFT JOIN orderdetail od ON od.goods_id=g.goods_id" 
					 +" LEFT JOIN goodsorder go ON go.order_id=od.order_id" 
					 +" LEFT JOIN shopconfig sc ON g.cust_id=sc.cust_id  WHERE g.active_state in (0,1,5)"
					 +" GROUP BY g.goods_id";
	//联想词搜索
    private String association="SELECT ass_key_words_id,ass_key_words_title,cat_attr as " +
    		"search_cat_attr,cat_attr,cat_attr as goods_cat_last_name,ass_key_words_number,ass_key_words_number as gcount," +
    		"ass_key_words_show,in_date,en_name,sort_no  FROM associationkeywords  ";
    
  //标签商品
    private String  markgoods="SELECT n_id,n.goods_id,n.tab_id,n.sort_no,g.goods_name,g.cat_attr,g.depot_id,g.sale_num as goods_sale_num," +
		"g.in_date AS collNum,LPAD((SELECT MIN(ga.sale_price) FROM goodsattr ga WHERE ga.goods_id=g.goods_id),20,'0') AS lpad_price," +
		"n.tab_number,(SELECT MIN(ga.sale_price) FROM goodsattr ga WHERE ga.goods_id=g.goods_id) AS lmin_price," +
		"(SELECT MIN(ga.market_price) FROM goodsattr ga WHERE ga.goods_id=g.goods_id)  AS goods_market_price" +
		",g.is_up,g.is_del,g.info_state,g.list_img,g.img_path,(SELECT IFNULL(SUM(ot.order_num),0) FROM goodsorder go LEFT JOIN orderdetail ot ON go.order_id = ot.order_id LEFT JOIN goods g1 ON g1.goods_id = ot.goods_id LEFT JOIN member m ON m.cust_id = go.buy_cust_id LEFT JOIN member m2 ON m2.cust_id = go.sell_cust_id WHERE ot.goods_id = g.goods_id AND order_state NOT IN ('0', '1')) AS order_num," +
		"(SELECT COUNT(trade_id) FROM goodseval WHERE  goodseval.goods_id=g.goods_id) AS eval_num " +
		"FROM navigation n LEFT JOIN goods g ON n.goods_id = g.goods_id ";
    
	/**
	 * @param args
	 *            运行的主方法
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		 CreateIndex ci = new CreateIndex();
		 ci.createMarkGoodsIndex("","4");
		// 测试全量索引
		// ci.CreateGoodsIndex();
		// 分类
		// ci.createCatIndex();
		// ci.createGoodsIncreIndex();


	}

	/**
	 * @author LHY
	 * @throws IOException
	 * @date:2014-4-9
	 * @Method:建立品牌索引
	 */
	public void createGoodsBrandIndex(String limit_sql, String type) throws IOException {
		String sql = goodsbrand_sql + limit_sql;
		System.out.println(sql);
		this.createModelIndex(sql, "goodsbrand", type);
		System.out.println("商品品牌索引成功！");
	}
	
	/**
	 * @author HXK
	 * @throws IOException
	 * @date:2015-9-9
	 * @Method:建立联想词索引
	 */
	public void createAssociationIndex(String limit_sql, String type) throws IOException {
		String sql = association + limit_sql;
		System.out.println(sql);
		this.createModelIndex(sql, "association", type);
		System.out.println("联想词索引成功！");
	}
	
	/**
	 * @author HXK
	 * @throws IOException
	 * @date:2015-12-9
	 * @Method:建立商城标签商品
	 */
	public void createMarkGoodsIndex(String limit_sql, String type) throws IOException {
		String sql = markgoods + limit_sql;
		System.out.println(sql);
		this.createModelIndex(sql, "navigation", type);
		System.out.println("标签商品索引成功！");
	}
	

	/**
	 * @author : LJQ
	 * @date : Apr 13, 2014 12:32:44 PM
	 * @Method Description : 商品增量索引品牌
	 */
	public void createGoodsBrandIncreIndex() throws IOException {
		String com_sql = " LEFT JOIN indexrecord r ON  goodsbrand.brand_id = r.info_id  WHERE r.module_name='goodsbrand' AND ";
		String ord_sql = " ORDER BY r.trade_id ASC";
		String ai_sql = goodsbrand_sql + com_sql + "oper_type='1'" + ord_sql;
		String au_sql = goodsbrand_sql + com_sql + "oper_type='2'" + ord_sql;
		String ad_sql = com_deSql + "'goodsbrand' and oper_type='3'";
		// 新增索引
		createModelIndex(ai_sql, "goodsbrand", "1");
		// 修改索引
		createModelIndex(au_sql, "goodsbrand", "2");
		// 删除索引
		createModelIndex(ad_sql, "goodsbrand", "3");
		System.out.println("分类增量索引创建成功!");
		// 删除需要的记录
		deleteRecord(com_deIndex + "'goodsbrand'");
	}

	/**
	 * @author : LJQ
	 * @throws IOException
	 * @date : Aug 1, 2014 5:18:04 PM
	 * @Method Description : 建立分类索引
	 */
	public void createCatIndex(String limit_sql, String type) throws IOException {
		String sql = cat_sql + limit_sql;
		createModelIndex(sql, "category", type);
		System.out.println("分类全量索引创建成功!");
	}

	/**
	 * @author : LJQ
	 * @date : Aug 29, 2014 10:03:18 AM
	 * @Method Description : 建立分类的增量索引
	 */
	public void createCatIncreIndex() throws IOException {
		String com_sql = " LEFT JOIN indexrecord r ON  category.cat_id = r.info_id  WHERE r.module_name='category' AND ";
		String ord_sql = " ORDER BY r.trade_id ASC";
		String ai_sql = cat_sql + com_sql + "oper_type='1'" + ord_sql;
		String au_sql = cat_sql + com_sql + "oper_type='2'" + ord_sql;
		String ad_sql = com_deSql + "'category' and oper_type='3'";
		// 新增索引
		createModelIndex(ai_sql, "category", "1");
		// 修改索引
		createModelIndex(au_sql, "category", "2");
		// 删除索引
		createModelIndex(ad_sql, "category", "3");
		System.out.println("分类增量索引创建成功!");
		// 删除需要的记录
		deleteRecord(com_deIndex + "'category'");
	}

	/**
	 * @author : LJQ
	 * @throws IOException
	 * @date : Sep 20, 2014 9:10:20 AM
	 * @Method Description : 建立商品索引
	 */
	public void CreateGoodsIndex(String limit_sql, String type) throws IOException {
		// 拼接sql语句
		String sql = goods_sql + limit_sql;
		System.out.println(sql);
		createModelIndex(sql, "goods", type);
		System.out.println("商品信息全量索引创建成功!");
	}

	/**
	 * @author : LHY
	 * @date : Aug 29, 2014 10:03:18 AM
	 * @Method Description : 建立商品的增量索引
	 */
	public void createGoodsIncreIndex() throws IOException {
		String com_sql = " LEFT JOIN indexrecord r ON  goods.goods_id = r.info_id  WHERE r.module_name='goods' AND ";
		String ord_sql = "GROUP BY goods.goods_id ORDER BY r.trade_id ASC";
		String ai_sql = add_good_sql + com_sql + "oper_type='1'" + ord_sql;
		String au_sql = add_good_sql + com_sql + "oper_type='2'" + ord_sql;
		String ad_sql = com_deSql + "'goods' and oper_type='3'";
		// 新增索引
		createModelIndex(ai_sql, "goods", "1");
		// 修改索引
		createModelIndex(au_sql,"goods","2");
		// 删除索引
		createModelIndex(ad_sql, "goods", "3");
		System.out.println("商品增量索引创建成功!");
		// 删除需要的记录
		deleteRecord(com_deIndex + "'goods'");
	}

	/**
	 * @author : LJQ
	 * @throws IOException
	 * @date : Aug 2, 2014 1:00:46 PM
	 * @Method Description : 建立地区的索引
	 */
	public void createAreaIndex(String limit_sql, String type) throws IOException {
		String sql = area_sql + limit_sql;
		createModelIndex(sql, "area", type);
		System.out.println("地区索引创建成功!");
	}

	/**
	 * @author : LJQ
	 * @date : Aug 29, 2014 10:47:04 AM
	 * @Method Description : 地区索引的增量更新
	 */
	public void createAreaIncreIndex() throws IOException {
		String com_sql = " LEFT JOIN indexrecord r ON  area.area_id = r.info_id  WHERE r.module_name='area' AND ";
		String ord_sql = " ORDER BY r.trade_id ASC";
		String ai_sql = area_sql + com_sql + "oper_type='1'" + ord_sql;
		String au_sql = area_sql + com_sql + "oper_type='2'" + ord_sql;
		String ad_sql = com_deSql + "'area' and oper_type='3'";
		// 新增索引
		createModelIndex(ai_sql, "area", "1");
		// 修改索引
		createModelIndex(au_sql, "area", "2");
		// 删除索引
		createModelIndex(ad_sql, "area", "3");
		System.out.println("地区增量索引创建成功!");
		// 删除需要的记录
		deleteRecord(com_deIndex + "'area'");
	}

	/**
	 * @author : LJQ
	 * @date : Aug 27, 2014 3:28:35 PM
	 * @Method Description : 更新索引方法
	 */
	private void createModelIndex(String sql, String folder, String index_type) throws IOException {
		LuceneUtil lu = new LuceneUtil();
		List list = lu.getSourceList(sql);
		System.out.println(list);
		if (list != null && list.size() > 0) {
			lu.createIndex(list, folder, index_type);
		}
	}

	/**
	 * @author : LJQ
	 * @date : Aug 28, 2014 2:27:40 PM
	 * @Method Description :删除信息记录
	 */
	private void deleteRecord(String sql) {
		LuceneUtil lu = new LuceneUtil();
		lu.deleteInfo(sql);
	}

	/**
	 * @author : HXK
	 * @param table_name:模块名称即（表名）
	 * @param table_pk：表主键
	 * @param info_sql：索引的SQL
	 * @throws IOException
	 * @Method Description : 建立模块的增量更新索引文件
	 */
	public void createInfoIncreIndex(String table_name, String table_pk, String info_sql) throws IOException {
		// 拼接sql语句
		String com_sql = " LEFT JOIN indexrecord r ON  " + table_name + "." + table_pk + " = r.info_id WHERE r.module_name='" + table_name + "' and ";
		String gro_sql = "  GROUP BY " + table_name + ".infoattr_id order by r.trade_id asc ";
		String ai_sql = info_sql + com_sql + " r.oper_type='1' " + gro_sql;
		String au_sql = info_sql + com_sql + " r.oper_type='2' " + gro_sql;
		String ad_sql = com_deSql + "'" + table_name + "' and oper_type='3'";
		// 新增索引
		createModelIndex(ai_sql, table_name, "1");
		// 修改索引
		createModelIndex(au_sql, table_name, "2");
		// 删除索引
		createModelIndex(ad_sql, table_name, "3");
		System.out.println(table_name + "模块增量索引创建成功!");
		//删除需要的记录
		deleteRecord(com_deIndex + "'" + table_name + "'");
	}

}
