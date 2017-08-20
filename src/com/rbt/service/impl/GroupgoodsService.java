/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: GroupgoodsService.java 
 */
package com.rbt.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.rbt.common.util.ValidateUtil;
import com.rbt.dao.IAreaDao;
import com.rbt.dao.ICategoryDao;
import com.rbt.dao.IFundhistoryDao;
import com.rbt.dao.IGoodsDao;
import com.rbt.dao.IGroupgoodsDao;
import com.rbt.dao.IGroupladderDao;
import com.rbt.dao.IMemberDao;
import com.rbt.dao.IMemberfundDao;
import com.rbt.dao.IOrderdetailDao;
import com.rbt.dao.IShopconfigDao;
import com.rbt.function.AreaFuc;
import com.rbt.model.Area;
import com.rbt.model.Fundhistory;
import com.rbt.model.Goods;
import com.rbt.model.Groupgoods;
import com.rbt.model.Groupladder;
import com.rbt.model.Member;
import com.rbt.model.Memberfund;
import com.rbt.model.Shopconfig;
import com.rbt.service.IGroupgoodsService;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 团购商品信息Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Thu Mar 28 14:55:26 CST 2014
 */
@Service
public class GroupgoodsService extends GenericService<Groupgoods,String> implements IGroupgoodsService {
	@Autowired
	IGroupgoodsDao groupgoodsDao;
	@Autowired
	private IOrderdetailDao orderdetailDao;
	@Autowired
	private IGoodsDao goodsDao;
	@Autowired
	private IMemberDao memberDao;
	@Autowired
	private IShopconfigDao shopconfigDao;
	@Autowired
	IGroupladderDao groupladderDao;
	@Autowired
	private ICategoryDao categoryDao;
    @Autowired
	private IAreaDao areaDao;
    @Autowired
    private IMemberfundDao memberfundDao;
    @Autowired
    private IFundhistoryDao fundhistoryDao;
	
    private Groupgoods groupgoods;
    private Memberfund memberfund;
    
	@Autowired
	public GroupgoodsService(IGroupgoodsDao groupgoodsDao) {
		super(groupgoodsDao);
		this.groupgoodsDao = groupgoodsDao;
	}
	/**
	 * @Method Description :新增一口价
	 * @author : HZX
	 * @date : Apr 16, 2014 1:30:17 PM
	 */
	public void insertshot(String id,String shot){
		double dshot=Double.parseDouble(shot);
		Groupladder groupladder =new Groupladder();
		groupladder.setLownum("1");
		groupladder.setPrice(dshot);
		groupladder.setGroup_id(id);
		this.groupladderDao.insert(groupladder);
	}
	/**
	 * @Method Description :更新一口价
	 * @author : HZX
	 * @date : Apr 16, 2014 1:30:17 PM
	 */
	public void updateshot(String id,String shot){
		double dshot=Double.parseDouble(shot);
		Groupladder groupladder =this.groupladderDao.getByGroupID(id);
		groupladder.setLownum("1");
		groupladder.setPrice(dshot);
		groupladder.setGroup_id(id);
		this.groupladderDao.update(groupladder);
	}
	/**
	 * @Method Description :新增阶梯价格
	 * @author : HZX
	 * @date : Apr 16, 2014 10:34:14 AM
	 */
	public void insertladprice(String id,String lownums,String ladprices){
		String[] lownum = lownums.split(",");
		String[] ladprice = ladprices.split(",");
		for(int i=0;i<lownum.length;i++){
			 double dladprice=Double.parseDouble(ladprice[i]);
        	 Groupladder gl=new Groupladder();
        	 gl.setGroup_id(id);
        	 gl.setLownum(lownum[i]);
        	 gl.setPrice(dladprice);
             this.groupladderDao.insert(gl);
		}
	}
	
	
	/**
	 * @Method Description : 新增团购数据
	 * @author : HZX
	 * @date : Apr 16, 2014 8:33:11 PM
	 */
	public String insertGroupGoods(Groupgoods groupgoods,String shot,String lownums,String ladprices){
		//修改商品的活动状态
		Map map =new HashMap();
		map.put("goods_id", groupgoods.getGoods_id());
		map.put("active_state", "1");
		goodsDao.updateActiveState(map);
		
		String id  =this.groupgoodsDao.insertGetPk(groupgoods);
		//团购商品添加成功往阶梯价格表插入对应数据
		if(groupgoods.getGroup_type().equals("0")){
			//新增一口价
			insertshot(id,shot);
		}else{
			//新增阶梯价格
			insertladprice(id, lownums, ladprices);
		}
		return id;
	}
	
	/**
	 * @Method Description :更新阶梯价格
	 * @author : HZX
	 * @date : Apr 16, 2014 8:47:33 PM
	 */
	public void updateGroupladder(String group_type,String id,String shot,String lownums,String ladprices){
		//团购商品更新成功往阶梯价格表更新对应数据
		if(group_type.equals("0")){
			//价格类型为一口价，则进行删除当前团购商品的所有相关数据
			this.groupladderDao.deleteGroupID(id);
			double dshot=Double.parseDouble(shot);
			Groupladder groupladder =this.groupladderDao.getByGroupID(id);
			if(groupladder==null){
				//新增一口价
				insertshot(id,shot);
			}else{
			//更新一口价
				updateshot(id, shot);
			}
		}else{
			//更新阶梯价格:先删除，后新增
			this.groupladderDao.deleteGroupID(id);	
			//新增阶梯价格
			insertladprice(id, lownums, ladprices);
		}
	}
	
	/**
	 * @author：XBY
	 * @date：Feb 17, 2014 10:43:56 AM
	 * @Method Description：团购价格详细
	 */
	public Map detail(String trade_id)throws Exception{
		//对象定义
		Goods goods=new Goods();
		Member member=new Member();
		Groupgoods groupgoods=new Groupgoods();
		Shopconfig shopconfig=new Shopconfig(); 
		//字段定义
		long difftime=0;//倒计时
		int ordernum=0;//订单数量
		int total_stock=0;//库存
		String	gid="";//商品标识
		String oneimg="";//单张图片
		String timeout="0";//时间结束
		String max_sale_price="";//原价即促销最低价
	    String earnest="";//定金
	    String groupprice="";//团购价格
		String pricediff="";//获取团购差价
		String pricepercent="";//获取折扣价
	    //集合定义
	    List imageList=new ArrayList();
	    List scoreList=new ArrayList();
	    List catgroupList=new ArrayList();	
		List groupgoodsList=new ArrayList();
	    List groupladderList=new ArrayList();
	    List shopconfigList =new ArrayList();
		if(trade_id!=null&&!trade_id.equals("")){
			//获取预售信息
			groupgoods = groupgoodsDao.get(trade_id);
			//获取库存
		 gid = groupgoods.getGoods_id();
			//获取该预售分类下推荐的4个商品
		    if(groupgoods.getCat_attr()!=null){
		    	String[] catstr = groupgoods.getCat_attr().split(",");
		    	if(catstr.length>0){
		    		String catvalue = catstr[catstr.length-1];
		    		HashMap catMap = new HashMap();
		    		catMap.put("cat_attr", catvalue);
		    		catMap.put("start", "0");
		    		catMap.put("limit", "4");
		    		catgroupList = groupgoodsDao.getList(catMap);
		    	}
		    	
		    }
		    //获取团购对应商品的名称
			if(groupgoods!=null && groupgoods.getGoods_id()!=null){
			goods = goodsDao.get(groupgoods.getGoods_id());
				
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
						countMap.put("goods_id", goods.getGoods_id());
						ordernum = orderdetailDao.getCount(countMap);
				}
				//获取倒计时时间秒数
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				if(groupgoods.getEnd_date()!=null){
					Date date = sd.parse(groupgoods.getEnd_date());
					difftime =date.getTime() - new Date().getTime();
					if(0 > difftime){
						timeout = "1";
					}
				}
			}

			//获取原价
			HashMap groupgoodsMap = new HashMap();
			groupgoodsMap.put("trade_id", trade_id);
			groupgoodsList = groupgoodsDao.getList(groupgoodsMap);
			if(groupgoodsList != null && groupgoodsList.size() > 0){
				HashMap groupgoodss = (HashMap)groupgoodsList.get(0);
				if(groupgoods != null && groupgoodss.get("price")!=null){
					max_sale_price = groupgoodss.get("max_sale_price").toString();
				}
			}
			
			//获取预售价格
			HashMap ladderMap = new HashMap();
			//目前采用一口价交易方式
			ladderMap.put("onlylownum", "1");
			String group_id = "";
			if(groupgoods!=null){
				group_id = groupgoods.getTrade_id();
			}	
			ladderMap.put("group_id", group_id);
			groupladderList = this.groupladderDao.getList(ladderMap);
			if(groupladderList!=null && groupladderList.size()>0){
				HashMap laddervalue = (HashMap)groupladderList.get(0);
				if(laddervalue!=null && laddervalue.get("price")!=null){
					groupprice = laddervalue.get("price").toString();
				}
				
			}
	}
		//获取价格
		if(groupgoods!=null && max_sale_price!=null && groupprice!=null){
			//获取团购差价
			pricediff =  String.valueOf(Double.parseDouble(max_sale_price) - Double.parseDouble(groupprice));
			//获取折扣价
			pricepercent = String.valueOf((Double.parseDouble(groupprice)/Double.parseDouble(max_sale_price))*10);
		}
		//把对象封装到Map
		 Map directSellMap=new HashMap();
		 directSellMap.put("oneimg",oneimg);
		 directSellMap.put("ordernum",ordernum);
		 directSellMap.put("imageList", imageList);
		 directSellMap.put("total_stock", total_stock);
		 directSellMap.put("groupgoods", groupgoods);
		 directSellMap.put("catgroupList", catgroupList);
		 directSellMap.put("goods", goods);
		 directSellMap.put("difftime", difftime);
		 directSellMap.put("timeout", timeout);
		 directSellMap.put("shopconfigList", shopconfigList);
		 directSellMap.put("shopconfig", shopconfig);
		 directSellMap.put("scoreList", scoreList);
		 directSellMap.put("ordernum", ordernum);
		 directSellMap.put("groupgoodsList", groupgoodsList);
		 directSellMap.put("max_sale_price", max_sale_price);
		 directSellMap.put("earnest", earnest);
		 directSellMap.put("groupladderList", groupladderList);
		 directSellMap.put("groupprice", groupprice);
		 directSellMap.put("gid", gid);
		 directSellMap.put("pricediff", pricediff);
		 directSellMap.put("pricepercent", pricepercent);
		 directSellMap.put("member", member);
		 return	directSellMap;
	
	}
	
	/**
	 * @author：XBY
	 * @date：Feb 18, 2014 10:46:58 AM
	 * @Method Description：团购信息
	 */
	public Map groupList(String cat_attr,String en_name,String sele_area_id,String searchtype,String searchname,char price){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		//绑定改团购分类下的商品
		HashMap groupgoodsMap = new HashMap();
		String sprice="1",eprice="100000";
		int p=6;
		try {
			p=Integer.parseInt(price+"");
		} catch (Exception e) {
			// TODO: handle exception
		}
		switch (p) {
		case 1:
			sprice="1";
			eprice="99";
			break;
		case 2:
			sprice="100";
			eprice="299";
			break;
		case 3:
			sprice="300";
			eprice="599";
			break;	
		case 4:
			sprice="600";
			eprice="999";	
			break;
		case 5:
			sprice="1000";
			break;
		default:
			break;
		}
		groupgoodsMap.put("price", "price");
		groupgoodsMap.put("sprice", sprice);
		groupgoodsMap.put("eprice", eprice);
		//过滤分类团购商品
		if(!"".equals(cat_attr)){
			groupgoodsMap.put("cat_attr", cat_attr);
		}
		//排序
		if("price".equals(searchtype)){
			//按选择排序
			groupgoodsMap.put("pricedesc", "DESC");
		}else if("paynum".equals(searchtype)){
			//按销量降序
			groupgoodsMap.put("paynum", "paynum");
		}else{
			//默认排序
			groupgoodsMap.put("defaultSort", "defaultSort");
		}
		//搜索团购
		if(searchname != null && !"".equals(searchname)){
			groupgoodsMap.put("group_title", searchname);
		}
		//过滤开始和结束团购商品
		groupgoodsMap.put("difftime", "difftime");
		//地区过滤
		sele_area_id = AreaFuc.getAreaIdByEnName(en_name);
		String areaName = "";
		Area area=new Area();
		if(en_name!=null && !"all".equals(en_name)){
			if(en_name!=null && sele_area_id !=null && !"".equals(sele_area_id) && !"all".equals(sele_area_id)){
				area = areaDao.get(sele_area_id);
				if(area!=null){
					areaName = area.getArea_name();
					session.setAttribute("areaName", areaName);
				}
				groupgoodsMap.put("area_attr", sele_area_id);
			}
		}else{
			session.removeAttribute("areaName");
		}
		groupgoodsMap.put("info_state", "1");
		Map map=new HashMap();
		map.put("area", area);
		map.put("sele_area_id", sele_area_id);
		map.put("groupgoodsMap", groupgoodsMap);
		return map;
	}
	/**
	 * @author:HXM
	 * @date:May 9, 20149:30:42 AM
	 * @param:
	 * @Description:为集合添加一个标识来判断，团购是在开始前后，或是进行
	 */
	public List changList(List list) throws Exception {
		if(list!=null && list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				Map map=(HashMap)list.get(i);
				//获取倒计时时间秒数
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				long nowTime=new Date().getTime();
				long flagTime;
				long flagTime1;
				if(map.get("end_date")!=null&&map.get("start_date")!=null){
					Date date = sd.parse(map.get("end_date").toString());
					Date date1 =sd.parse(map.get("start_date").toString());
					flagTime =date.getTime() - nowTime;
					flagTime1=date1.getTime() - nowTime;
					if(flagTime <0){
						map.put("active_state", "3");//判断开团后
					}else if(flagTime1>0){
						map.put("active_state", "1");//判断开团前
					}else{
						map.put("active_state", "2");//判断开团中
					}
				}else{
					map.put("active_state", "4");
				}
				list.set(i, map);
			} 
		}
		return list;
	}
	/**
	 * @author:HXM
	 * @date:May 9, 20142:33:22 PM
	 * @param:
	 * @Description:修改资金冻结状态
	 */
	public void updateApply(Map map){
		groupgoods=groupgoodsDao.get(map.get("trade_id").toString());
		//运营商解除冻结
		if("3".equals(map.get("apply_state").toString()) && !"3".equals(groupgoods.getApply_state())){
			memberfund=memberfundDao.get(groupgoods.getCust_id());
			double i1=Double.valueOf(memberfund.getFreeze_num());
			double i2=Double.valueOf(memberfund.getUse_num());
			double d= i1 - groupgoods.getBond();
			double d2= groupgoods.getBond()+i2;
			memberfund.setFreeze_num(d+"");
			memberfund.setUse_num(d2+"");
			memberfundDao.update(memberfund);
			//插入资金记录表
			Fundhistory fh = new Fundhistory();
			fh.setBalance(i2);
			fh.setCust_id(groupgoods.getCust_id());
			fh.setFund_in(groupgoods.getBond());
			fh.setFund_out(0.0);
			fh.setUser_id("105");//运营商才有这样的操作
			fh.setAction_type("4");
			fh.setReason("团购【解冻】资金:团购名称为"+groupgoods.getGroup_title()+"编号为"+groupgoods.getTrade_id()+"解冻金额为"+groupgoods.getBond()+"元");
			this.fundhistoryDao.insert(fh);
			this.groupgoodsDao.updateApply(map);
		}else{
			this.groupgoodsDao.updateApply(map);
		}
	}
	public List getCatList(Map catMap) {
		List catList=this.categoryDao.getList(catMap);
		List cList=new ArrayList();
		if(catList!=null&&catList.size()>0){
			for(int i=0;i<catList.size();i++){
				int count=0;
				String cat_attr="";
				Map countMap =new HashMap ();
				countMap=(Map)catList.get(i);
				cat_attr=countMap.get("cat_id").toString();
				Map cMap =new HashMap ();
				cMap.put("cat_attr", cat_attr);
				cMap.put("difftime", "difftime");
				count=this.groupgoodsDao.getCount(cMap);
				countMap.put("count",count);
				cList.add(i, countMap);
			}
		}
		
		return cList;
	}
}

