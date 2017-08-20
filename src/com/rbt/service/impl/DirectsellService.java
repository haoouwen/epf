/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: DirectsellService.java 
 */
package com.rbt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.common.util.ValidateUtil;
import com.rbt.dao.IDirectladderDao;
import com.rbt.dao.IDirectorderdetailDao;
import com.rbt.dao.IDirectsellDao;
import com.rbt.dao.IGoodsDao;
import com.rbt.dao.IMemberDao;
import com.rbt.dao.IShopconfigDao;
import com.rbt.model.Directladder;
import com.rbt.model.Directsell;
import com.rbt.model.Goods;
import com.rbt.model.Member;
import com.rbt.model.Shopconfig;
import com.rbt.function.SysconfigFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.service.IDirectsellService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 预售商品Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Wed Jul 17 13:27:41 CST 2014
 */
@Service
public class DirectsellService extends GenericService<Directsell,String> implements IDirectsellService {
	
	IDirectsellDao directsellDao;
	@Autowired
	IDirectladderDao directladderDao;
	@Autowired
	private IGoodsDao goodsDao;
	@Autowired
	private IMemberDao memberDao;
	@Autowired
	private IShopconfigDao shopconfigDao;
	@Autowired
	private IDirectorderdetailDao directorderdetailDao; 
	@Autowired
	public DirectsellService(IDirectsellDao directsellDao) {
		super(directsellDao);
		this.directsellDao = directsellDao;
	}
	private String cfg_sc_pointsrule = SysconfigFuc.getSysValue("cfg_sc_pointsrule");//积分规则
	

	/**
	 * @author : HZX
	 * @date : Jul 17, 2014 4:57:50 PM
	 * @Method Description :新增阶梯价格
	 */
	public void insertladprice(String id,String lownums,String ladprices){
		String[] lownum = lownums.split(",");
		String[] ladprice = ladprices.split(",");
		for(int i=0;i<lownum.length;i++){
			 double dladprice=Double.parseDouble(ladprice[i]);
			 Directladder directladder =new Directladder();
        	 directladder.setDirect_id(id);
        	 directladder.setLownum(lownum[i]);
        	 directladder.setPrice(dladprice);
             this.directladderDao.insert(directladder);
		}
	}
	
	
	/**
	 * @author : HZX
	 * @date : Jul 17, 2014 4:58:04 PM
	 * @Method Description :新增预售数据
	 */
	public String insertDirectsell(Directsell directsell,String lownums,String ladprices){
		String id  =this.directsellDao.insertGetPk(directsell);
		//团购商品添加成功往阶梯价格表插入对应数据
			insertladprice(id, lownums, ladprices);
		return id;
	}
	
	/**
	 * @author : HZX
	 * @date : Jul 17, 2014 5:29:42 PM
	 * @Method Description :更新阶梯价格
	 */
	public void updateDirectladder(String id,String lownums,String ladprices){
		//团购商品更新成功往阶梯价格表更新对应数据
		
			//更新阶梯价格:先删除，后新增
			this.directladderDao.deleteDirectID(id);	
			//新增阶梯价格
			insertladprice(id, lownums, ladprices);

	}
	/**
	 *@author :HXM
	 *@date Dec 10, 2014 9:43:10 AM
	 *@Method description:修改库存 
	 */
	public void updateStock(Map map){
		this.directsellDao.updateStock(map);
		
	}
	/**
	 * @author：XBY
	 * @date：Feb 17, 2014 10:43:56 AM
	 * @Method Description：预售价格详细
	 */
	public Map detail(String directs_id)throws Exception{
		//对象定义
		Goods goods=new Goods();
		Member member=new Member();
		Directsell directsell=new Directsell();
		Shopconfig shopconfig=new Shopconfig(); 
		//字段定义
		long difftime=0;//倒计时
		int ordernum=0,bnum=0;//订单数量
		int total_stock=0;//库存
		String	gid="";//商品标识
		String oneimg="";//单张图片
		String timeout="0";//时间结束
		String min_sale_price="";//原价即促销最低价
	    String earnest="";//定金
	    String directprice="";//预售价格
		String pricediff="";//获取预售差价
		String pricepercent="";//获取折扣价
		String diffday="";
	    //集合定义
	    List imageList=new ArrayList();
	    List scoreList=new ArrayList();
	    List catgroupList=new ArrayList();	
		List directsellList=new ArrayList();
	    List directladderList=new ArrayList();
	    List shopconfigList =new ArrayList();
		if(directs_id!=null&&!directs_id.equals("")){
			//获取预售信息
		 directsell = directsellDao.get(directs_id);
			//获取库存
		 total_stock=Integer.parseInt(directsell.getStock());
		 gid = directsell.getGoods_id();
			//获取该预售分类下推荐的4个商品
		    if(directsell.getCat_attr()!=null){
		    	String[] catstr = directsell.getCat_attr().split(",");
		    	if(catstr.length>0){
		    		String catvalue = catstr[catstr.length-1];
		    		HashMap catMap = new HashMap();
		    		catMap.put("cat_attr", catvalue);
		    		catMap.put("start", "0");
		    		catMap.put("limit", "4");
		    		catgroupList = directsellDao.getList(catMap);
		    	}
		    	
		    }
			//获取预售对应商品的名称
			if(directsell!=null && directsell.getGoods_id()!=null){
			goods = goodsDao.get(directsell.getGoods_id());
				
				//获取商品图片列表 以前是获取预售图片列表 hong注释
				if(!ValidateUtil.isRequired(goods.getImg_path())){
					String[] arrayStr =new String[]{};
					arrayStr = goods.getImg_path().split(",");
					oneimg = arrayStr[0];
					imageList = java.util.Arrays.asList(arrayStr);
				}
				//获取用户资料
				if(goods!=null && goods.getCust_id()!=null){
					 member = memberDao.get(goods.getCust_id());
						if(member!=null && member.getCust_id()!=null){
						//获取用户店铺信息
						HashMap shopMap = new HashMap();
						shopMap.put("cust_id", member.getCust_id());
						shopconfigList = shopconfigDao.getList(shopMap);
						
						
					}
						//获取购买人数
						HashMap countMap = new HashMap();
						countMap.put("pay_state","1,2");
						countMap.put("direct_id", directs_id);
						ordernum = directorderdetailDao.getCount(countMap);
						
						//获取购买人数
						HashMap mMap = new HashMap();
						mMap.put("trade_id", directs_id);
						List dList=this.directsellDao.getList(mMap);
						Map dMap=(Map) dList.get(0);
						if(dMap.get("buynum")==null){
							bnum=0;
						}else {
							bnum =Integer.parseInt(dMap.get("buynum").toString()) ;
						}
						
				}
				
			}
            //获取剩余时间
			diffday = ToolsFuc.dayhourminute(directsell.getEnd_time());
			if("0".equals(diffday)){
				timeout = "1";
			}
			//获取原价
			HashMap directsellMap = new HashMap();
			directsellMap.put("trade_id", directs_id);
			directsellList = directsellDao.getList(directsellMap);
			if(directsellList != null && directsellList.size() > 0){
				HashMap directsells = (HashMap)directsellList.get(0);
				if(directsells != null && directsells.get("min_market_price")!=null&&directsells.get("earnest")!=null){
					min_sale_price = directsells.get("min_market_price").toString();
					earnest=directsells.get("earnest").toString();
				}
			}
			
			
			//获取预售价格
			HashMap ladderMap = new HashMap();
			//目前采用一口价交易方式
			ladderMap.put("onlylownum", "1");
			String direct_id = "";
			if(directsell!=null){
				direct_id = directsell.getTrade_id();
			}	
			ladderMap.put("direct_id", direct_id);
		    directladderList = this.directladderDao.getList(ladderMap);
			if(directladderList!=null && directladderList.size()>0){
				HashMap laddervalue = (HashMap)directladderList.get(0);
				if(laddervalue!=null && laddervalue.get("price")!=null){
					directprice = laddervalue.get("price").toString();
				}
				
			}
	}
		//获取价格
		if(directsell!=null && min_sale_price!=null && directprice!=null){
			//获取预售差价
			pricediff =  String.valueOf(Double.parseDouble(min_sale_price) - Double.parseDouble(directprice));
			//获取折扣价
			pricepercent = String.valueOf((Double.parseDouble(directprice)/Double.parseDouble(min_sale_price))*10);
		}
		 String give_inter_str  =  String.valueOf((Double.parseDouble(directprice) * Double.parseDouble(cfg_sc_pointsrule))/100);
		//把对象封装到Map
		 Map directSellMap=new HashMap();
		 directSellMap.put("oneimg",oneimg);
		 directSellMap.put("ordernum",ordernum);
		 directSellMap.put("bnum",bnum);
		 directSellMap.put("imageList", imageList);
		 directSellMap.put("total_stock", total_stock);
		 directSellMap.put("directsell", directsell);
		 directSellMap.put("catgroupList", catgroupList);
		 directSellMap.put("goods", goods);
		 directSellMap.put("difftime", difftime);
		 directSellMap.put("timeout", timeout);
		 directSellMap.put("shopconfigList", shopconfigList);
		 directSellMap.put("shopconfig", shopconfig);
		 directSellMap.put("scoreList", scoreList);
		 directSellMap.put("ordernum", ordernum);
		 directSellMap.put("directsellList", directsellList);
		 directSellMap.put("min_sale_price", min_sale_price);
		 directSellMap.put("earnest", earnest);
		 directSellMap.put("directladderList", directladderList);
		 directSellMap.put("directprice", directprice);
		 directSellMap.put("gid", gid);
		 directSellMap.put("pricediff", pricediff);
		 directSellMap.put("pricepercent", pricepercent);
		 directSellMap.put("member", member);
		 directSellMap.put("diffday", diffday);
		 directSellMap.put("give_inter_str", give_inter_str);
		 return	directSellMap;
	
	}
	
	/**
	 * @author : CYC
	 * @date : Apr 18, 2014 10:17:04 AM
	 * @Method Description :获取数据列表
	 */
	public List getdeliverpay(Map map) {
		return directsellDao.getdeliverpay(map);
	}
	
	/**
	 * @author : QJY
	 * @date :  
	 * @Method Description :预售商品逻辑删除
	 */
	public boolean deletetorecycle(String chb_id, String state) {
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
				linkMap.put("trade_id", chidStr[i]);
				chList.add(linkMap);

			}
		}
		this.directsellDao.deletetorecycle(chList);
		return true;
	}
	
	
}

	
