/*
 
 * Package:com.rbt.action
 * FileName: InfoCountAction.java 
 */
package com.rbt.action;

import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.DateUtil;
import com.rbt.common.util.JsonUtil;
import com.rbt.function.AreaFuc;
import com.rbt.function.CategoryFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.service.IAreaService;
import com.rbt.service.ICategoryService;
import com.rbt.service.ICommparaService;
import com.rbt.service.IInfocountService;

/**
 * @function 功能 统计报表action类
 * @author 创建人 QJY
 * @date 创建日期 Mon Jul 11 12:15:32 CST 2015
 */
@Controller
public class InfocountAction extends AdminBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/


	/*******************业务层接口****************/
	@Autowired
	public ICommparaService commparaService;
	@Autowired
	public IInfocountService infocountService;
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private IAreaService areaService;

	/*********************集合******************/

	/*********************字段******************/
	public String sevendatatime = "";//坐标显示名称
	public String sevengoodsorder = "";//坐标显示名称
	public String xtitle;//标题
	public String starttime_s;//时间变量
	public String endtime_s;//时间变量
	public String datenow;//当天时间
	public String before7Day;//前7天
	public long timegap;//获取搜索日期差值
	public String momeny;//财务
	public String count;//统计
	public String x_axis=""; //x轴显示标题
	public double[] elements;//视图数据
	public double max = 0;   //最大
	public double steps = 0;//
    public String count_type;//统计类型
    public String jsontotal;//销售总额
    /******会员统计信息*******/
    
    public Map totalMemberMap = new HashMap();
    
    public Map growMemberMap = new HashMap();
    
    public Map totalAmountMap = new HashMap();
    
    public Map totalgoodsMap = new HashMap();
    
    public List memberList = new ArrayList();
    
    public List categoryList = new ArrayList();
    
    public List catgoodsnumList= new ArrayList();
    
    public String area_attr_s="";//地区编码
    
    public String store_code_s="";//门店编码
    
    public String memberPercent;
    
    //是否首次访问
    public String  firstAccess="";
    
    //销售统计
    public List salesAmountList;
    
    public List areaList;
    
    public List catList;
    
    public List salesByCatList;
    

	/**
	 * @Method Description : 导出统计信息
	 * @author: HXK
	 * @date : Apr 25, 2014 2:47:20 PM
	 * @param 
	 * @return return_type
	 */
/*	public String exportInfo() throws Exception{
		
		if(count_type.equals("totalsales")){
			//总销售额
			comomTotalsales();
		}else if(count_type.equals("ranking")){
			//销售量（额）排行
			commonRanking();
			infototalList=inforankingList;
		}else if(count_type.equals("buycount")){
			//会员购物量排行
			commonBuycount();
			infototalList=infobuycountList;
		}else if(count_type.equals("ordernum")){
			//订单数
			commonOrdernum();
		}else if(count_type.equals("membernum")){
			//会员数
			commonMembernum();
		}else if(count_type.equals("moneycount")){
			//财务报表
			commonMoneycount();
			infototalList=moneycountList;
		}
		this.infocountService.exportExcel(infototalList,count_type);
	    this.addActionMessage("数据导出成功!");
	    return goUrl(count_type);
	}*/	

	/**
	 * @author QJY
	 * @function 会员统计：会员增长数量/百分比_会员消费总金额
	 * @date 2015-08-05
	 * @return
	 * @throws Exception
	 */
	public String countmemberinfo()throws Exception{
		
		Map areaMap = new HashMap();
		areaMap.put("area_level", "2");
	    areaList = areaService.getList(areaMap);
		
		Map map = new HashMap();
		// 获取搜索日期差值
		if(count_type != null && !"".equals(count_type)){
			if(count_type.equals("todayMember")){
				setCurrentToday();
			}
			if(count_type.equals("weekMember")){
				setCurrentWeek();	
			}
			if(count_type.equals("monthMember")){
				setCurrentMonth();
			}
			if(count_type.equals("yearMember")){
				setCurrentYear();	
			}
		}
		
		//默认获取本周的信息
		if(firstAccess == null || "".equals(firstAccess)){
			setCurrentWeek();
			firstAccess = "1";
		}
		
		if(area_attr_s != null && !"".equals(area_attr_s)){	
			map.put("area_attr", area_attr_s); //地区串
		}
		if(store_code_s != null && !"".equals(store_code_s)){
			map.put("membernum", store_code_s);	//门店码
		}
		if(starttime_s != null && !"".equals(starttime_s)){
			map.put("starttime", starttime_s);
		}
		
		if(endtime_s != null && !"".equals(endtime_s)){
			map.put("endtime", endtime_s);
		}
		
		map.put("cust_id_no", "0");//排除cust_id为0的会员
		
		// 根据页面提交的条件找出信息总数
		int count = this.memberService.getCount(map);
		// 分页插件
		map = super.pageTool(count, map);
		memberList = this.memberService.getList(map);
		memberList = ToolsFuc.replaceList(memberList,"");
		
		map.put("order_state", "7,8");
		
		
		//会员总数量
		Map memMap = new HashMap();
		memMap.put("cust_id", "0");
		totalMemberMap = infocountService.getTotalMember(memMap);
		//新增会员数量
		growMemberMap = infocountService.getGrowMember(map);
		
		//百分比
		if(totalMemberMap !=null && growMemberMap!=null){
			NumberFormat nt = NumberFormat.getPercentInstance();
			//设置百分数精确度2即保留两位小数
			nt.setMinimumFractionDigits(2);
			String growcount = growMemberMap.get("growMember").toString();
			String totalcount = totalMemberMap.get("totalMember").toString();
			
			Double grownum = Double.valueOf(growcount);
			Double totalnum = Double.valueOf(totalcount);
			Double precent = grownum/totalnum;
			
			memberPercent=nt.format(precent);
			
		}
		
		//会员消费总金额
		totalAmountMap = infocountService.getTotalAmount(map);
		
		return goUrl("countmemberinfo");
	}
	
	/**
	 * @author QJY
	 * @function 会员统计：被会员消费的商品数量_被消费同类商品数量
	 * @date 2015-08-05
	 * @return
	 * @throws Exception
	 */
	public String countgoodsinfo()throws Exception{
		
		Map areaMap = new HashMap();
		areaMap.put("area_level", "2");
	    areaList = areaService.getList(areaMap);
		
		Map map = new HashMap();
		// 获取搜索日期差值
		if(count_type != null && !"".equals(count_type)){
			if(count_type.equals("todayMember")){
				setCurrentToday();
			}
			if(count_type.equals("weekMember")){
				setCurrentWeek();	
			}
			if(count_type.equals("monthMember")){
				setCurrentMonth();
			}
			if(count_type.equals("yearMember")){
				setCurrentYear();	
			}
		}
		
		//默认获取本周的信息
		if(firstAccess == null || "".equals(firstAccess)){
			setCurrentWeek();
			firstAccess = "1";
		}
		
		if(area_attr_s != null && !"".equals(area_attr_s)){	
			map.put("area_attr", area_attr_s);
		}
		if(store_code_s != null && !"".equals(store_code_s)){
			map.put("store_code", store_code_s);	
		}
		if(starttime_s != null && !"".equals(starttime_s)){
			map.put("starttime", starttime_s);
		}
		
		if(endtime_s != null && !"".equals(endtime_s)){
			map.put("endtime", endtime_s);
		}
		
		map.put("cust_id", "0");
		
		map.put("order_state", "2,7,8");
		
		//会员消费的商品数量
		totalgoodsMap =  this.infocountService.getTotalGoods(map);
		//被会员消费的同类商品数量
		Map catMap = new HashMap();
		catMap.put("module_type", "goods");
		catMap.put("cat_level", "2");
		categoryList = this.categoryService.getList(catMap);
		if(categoryList!=null && categoryList.size()>0){
			Map catgoodsMap = new HashMap();
			for(int i=0;i<categoryList.size();i++){
				Map catIdMap = (Map) categoryList.get(i);
				if(catIdMap!=null && catIdMap.get("cat_id")!=null)
				map.put("cat_attr", catIdMap.get("cat_id"));//外部条件集合Map
				
				catgoodsMap = this.infocountService.getTotalCatGoods(map);
				catgoodsnumList.add(catgoodsMap);
			}
		}
		
		return goUrl("countgoodsinfo");
	}
	
	
	/**
	 * @author QJY
	 * @function 销售统计：按时间 统计
	 * @date 2015-08-17
	 * @return
	 * @throws Exception
	 */
    public String countsalesbytime() throws Exception{
    	Map map = new HashMap();
		// 获取搜索日期差值
		if(count_type != null && !"".equals(count_type)){
			if(count_type.equals("todayAmount")){
				setCurrentToday();
			}
			if(count_type.equals("weekAmount")){
				setCurrentWeek();	
			}
			if(count_type.equals("monthAmount")){
				setCurrentMonth();
			}
			if(count_type.equals("yearAmount")){
				setCurrentYear();	
			}
		}
		
		//默认获取本周的信息
		if(firstAccess == null || "".equals(firstAccess)){
			setCurrentWeek();
			firstAccess = "1";
		}
		
		if(starttime_s != null && !"".equals(starttime_s)){
			map.put("starttime", starttime_s);
		}
		
		if(endtime_s != null && !"".equals(endtime_s)){
			map.put("endtime", endtime_s);
		}
		
		
		map.put("order_state", "7,8");//确认收货，订单成交
		map.put("sure_time", "1");//加入该条件
		
		// 根据页面提交的条件找出信息总数
		int count = this.infocountService.getOperationCount(map);
		// 分页插件
		map = super.pageTool(count, map);
		salesAmountList = this.infocountService.getSalesAmountList(map);
		
    	return goUrl("countsalesbytime");
    }
    
    /**
     * @author QJY
     * @function 按时间销售额柱状图数据
     * @date 2015-08-17
     * @return
     * @throws Exception
     */
    public String getCountSalesByTime() throws Exception{
    	HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		Map map = new HashMap();
		
		// 获取搜索日期差值
		if(request.getParameter("count_type")!=null && !"".equals(request.getParameter("count_type"))){
			if(count_type.equals("todayAmount")){
				setCurrentToday();
			}
			if(count_type.equals("weekAmount")){
				setCurrentWeek();	
			}
			if(count_type.equals("monthAmount")){
				setCurrentMonth();
			}
			if(count_type.equals("yearAmount")){
				setCurrentYear();	
			}
		}
		
		//默认获取本周的信息
		if(request.getParameter("firstAccess")==null || "".equals(request.getParameter("firstAccess"))){
			setCurrentWeek();
			firstAccess = "1";
		}
		
		if(request.getParameter("starttime_s")!=null && !"".equals(request.getParameter("starttime_s"))){
			map.put("starttime", request.getParameter("starttime_s"));
		}
		
		if(request.getParameter("endtime_s")!=null && !"".equals(request.getParameter("endtime_s"))){
			map.put("endtime", request.getParameter("endtime_s"));
		}
		
		map.put("order_state", "7,8");//确认收货，订单成交
		map.put("sure_time", "1");//加入该条件
		
		salesAmountList = this.infocountService.getSalesAmountList(map);
		
		String outStr = JsonUtil.list2json(salesAmountList);
		out.print(outStr);
		System.out.println(outStr+"================");
    	return null;
    }
    
    /**
	 * @author QJY
	 * @function 销售统计：按产地（区域） 统计
	 * @date 2015-08-17
	 * @return
	 * @throws Exception
	 */
    public String countsalesbyarea() throws Exception{
    	Map areaMap = new HashMap();
		areaMap.put("area_level", "2");
	    areaList = areaService.getList(areaMap);
    	return goUrl("countsalesbyarea");
    }
    
    /**
	 * @author QJY
	 * @function 销售统计：按产地（区域） 统计
	 * @date 2015-08-17
	 * @return
	 * @throws Exception
	 */
    public String getCountSalesByArea() throws Exception{
    	HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
    	Map map = new HashMap();
			
    	if(request.getParameter("area_attr_s")!=null && !"".equals(request.getParameter("area_attr_s"))){
			map.put("area_attr", request.getParameter("area_attr_s").toString());
		}
		
    	map.put("order_state", "7,8");//确认收货，订单成交
    	
		salesAmountList = this.infocountService.getCountSalesByArea(map);
    	
		String outStr = JsonUtil.list2json(salesAmountList);
		out.print(outStr);
		//System.out.println(outStr+"================");
		
    	return null;
    }
    /**
     * 
     * @throws Exception
     */
    public void getSalesByArea()throws Exception{
    	HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
    	Map map = new HashMap();
		String area_attr="",area_name="";
    	if(request.getParameter("area_attr_s")!=null && !"".equals(request.getParameter("area_attr_s")) ){
    		area_attr =request.getParameter("area_attr_s").toString();
			area_name = AreaFuc.getAreaNameByMap(area_attr);
        	if(area_name !=null && !area_name.equals("")){
        		area_name = area_name.replace(",", "_");
        	}
		}else{
			area_name = "全部区域";
		}
    	
    	List areaList = new ArrayList();
		Map areaMap = new HashMap();
		areaMap.put("area_name", area_name);
		areaList.add(areaMap);
		String outStr = JsonUtil.list2json(areaList);
		out.print(outStr);
		//System.out.println(outStr+"================");
		
    }
    
    /**
	 * @author QJY
	 * @function 销售统计：按商品种类 统计
	 * @date 2015-08-17
	 * @return
	 * @throws Exception
	 */
    public String countsalesbycat() throws Exception{

	    Map map = new HashMap();
		
		//默认获取本周的信息
		if(firstAccess == null || "".equals(firstAccess)){
			setCurrentWeek();
			firstAccess = "1";
		}
		
		if(starttime_s != null && !"".equals(starttime_s)){
			map.put("starttime", starttime_s);
		}
		
		if(endtime_s != null && !"".equals(endtime_s)){
			map.put("endtime", endtime_s);
		}
		
		if(cat_attr_s !=null && !"".equals(cat_attr_s)){
			map.put("cat_attr", cat_attr_s);
			//回选分类
			viewCat(cat_attr_s);
		}
		
		map.put("order_state", "7,8");//确认收货，订单成交
		map.put("goods_name", "1");//按照商品名称分组
		
		// 根据页面提交的条件找出信息总数
		int count = this.infocountService.getSaleByCatCount(map);
		// 分页插件
		map = super.pageTool(count, map);
		salesByCatList = this.infocountService.getSalesByCatList(map);
	    
    	return goUrl("countsalesbycat");
    }
    
    /**
	 * @author QJY
	 * @function 销售统计： 按商品种类
	 * @date 2015-08-17
	 * @return
	 * @throws Exception
	 */
    public String getCountSalesByCat()throws Exception{
    	
    	HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
    	Map map = new HashMap();
		
		//默认获取本周的信息
		if(request.getParameter("firstAccess")==null || "".equals(request.getParameter("firstAccess"))){
			setCurrentWeek();
			firstAccess = "1";
		}
		
		if(request.getParameter("starttime_s")!=null && !"".equals(request.getParameter("starttime_s"))){
			map.put("starttime", request.getParameter("starttime_s"));
		}
		
		if(request.getParameter("endtime_s")!=null && !"".equals(request.getParameter("endtime_s"))){
			map.put("endtime", request.getParameter("endtime_s"));
		}
    	
    	if(request.getParameter("cat_attr_s")!=null && !"".equals(request.getParameter("cat_attr_s"))){
			map.put("cat_attr", request.getParameter("cat_attr_s").toString());
		}
		
    	map.put("order_state", "7,8");//确认收货，订单成交
    	map.put("goods_name", "1");//按照商品名称分组
    	
		salesAmountList = this.infocountService.getCountSalesByCat(map);
		salesAmountList = ToolsFuc.replaceList(salesAmountList, "");
    	
		String outStr = JsonUtil.list2json(salesAmountList);
		out.print(outStr);
		//System.out.println(outStr+"================");
    	
    	
    	return null;
    }
    
    /**
     * 
     * @throws Exception
     */
    public void getSalesByCat() throws Exception{
    	HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		String cat_attr = "",cat_name="";
    	if(request.getParameter("cat_attr_s")!=null && !"".equals(request.getParameter("cat_attr_s"))){
    		cat_attr=request.getParameter("cat_attr_s").toString();
    		cat_name = CategoryFuc.getCateNameByMap(cat_attr);
        	if(cat_name !=null && !cat_name.equals("")){
        		cat_name = cat_name.replace(",", "_");
        		
        	}
		}else{
			cat_name = "全部类别";
		}
    	
    	List catList = new ArrayList();
		Map catMap = new HashMap();
		catMap.put("cat_name", cat_name);
		catList.add(catMap);
		String outStr = JsonUtil.list2json(catList);
		out.print(outStr);
		//System.out.println(outStr+"================");
    	
    }
	
    /**
	 * @author QJY
	 * @function 销售统计： 按会员的购买数量 统计
	 * @date 2015-08-17
	 * @return
	 * @throws Exception
	 */
    public String countsalesbypurchases()throws Exception{
    	
    	Map map = new HashMap();
    	
    	// 获取搜索日期差值
		if(count_type != null && !"".equals(count_type)){
			if(count_type.equals("todayAmount")){
				setCurrentToday();
			}
			if(count_type.equals("weekAmount")){
				setCurrentWeek();	
			}
			if(count_type.equals("monthAmount")){
				setCurrentMonth();
			}
			if(count_type.equals("yearAmount")){
				setCurrentYear();	
			}
		}
		
		//默认获取本周的信息
		if(firstAccess == null || "".equals(firstAccess)){
			setCurrentMonth();
			firstAccess = "1";
		}
		
		if(starttime_s != null && !"".equals(starttime_s)){
			map.put("starttime", starttime_s);
		}
		
		if(endtime_s != null && !"".equals(endtime_s)){
			map.put("endtime", endtime_s);
		}
    	
        map.put("order_state", "7,8");//确认收货，订单成交
        map.put("memberbuy", "1");
		
    	map.put("start", 0);
		map.put("limit", 30);
    	
		// 根据页面提交的条件找出信息总数
		int count = this.infocountService.getCountByPurchases(map);
		// 分页插件
		map = super.pageTool(count, map);
		salesAmountList = this.infocountService.getCountSalesByPurchases(map);
    	
    	return goUrl("countsalesbypurchases");
    }
    
    /**
     * 
     * @return
     * @throws Exception
     */
    public String getCountSalesByPurchases()throws Exception{
    	
    	HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
    	Map map = new HashMap();
		
    	
    	// 获取搜索日期差值
		if(request.getParameter("count_type")!=null && !"".equals(request.getParameter("count_type"))){
			if(count_type.equals("todayAmount")){
				setCurrentToday();
			}
			if(count_type.equals("weekAmount")){
				setCurrentWeek();	
			}
			if(count_type.equals("monthAmount")){
				setCurrentMonth();
			}
			if(count_type.equals("yearAmount")){
				setCurrentYear();	
			}
		}
		
		//默认获取本周的信息
		if(request.getParameter("firstAccess")==null || "".equals(request.getParameter("firstAccess"))){
			setCurrentMonth();
			firstAccess = "1";
		}
		
		if(request.getParameter("starttime_s")!=null && !"".equals(request.getParameter("starttime_s"))){
			map.put("starttime", request.getParameter("starttime_s"));
		}
		
		if(request.getParameter("endtime_s")!=null && !"".equals(request.getParameter("endtime_s"))){
			map.put("endtime", request.getParameter("endtime_s"));
		}
		
    	
    	
    	map.put("memberbuy", "1");
    	map.put("order_state", "7,8");//确认收货，订单成交
    	
		salesAmountList = this.infocountService.getCountSalesByPurchases(map);
	
		String outStr = JsonUtil.list2json(salesAmountList);
		out.print(outStr);
		//System.out.println(outStr+"================");
    	
    	return null;
    }
    
	/**
	 * QJY
	 * 今天
	 * @throws Exception
	 */
	public void setCurrentToday() throws Exception{
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		String currentToday = DateUtil.parseDateYMR(cal.getTime());
		starttime_s = currentToday;
		endtime_s = currentToday;
	}
	
	/**
	 * QJY
	 * 本周的第一天和最后一天
	 * 
	 * @throws Exception
	 */
    public void setCurrentWeek() throws Exception{
    	Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了

		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		String weekBegin = DateUtil.parseDateYMR(cal.getTime());	
		cal.add(Calendar.DATE, 6);	
		String weekEnd = DateUtil.parseDateYMR(cal.getTime());
		
		starttime_s = weekBegin;
		endtime_s = weekEnd;
    }
    /**
     * QJY
     * 本月的第一天和最后一天
     * @throws Exception
     */
    public void setCurrentMonth()throws Exception{
    	Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));//本月的第一天
		String monthBegin = DateUtil.parseDateYMR(cal.getTime());
		
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));//本月的最后一天
		String monthEnd =DateUtil.parseDateYMR(cal.getTime());
		
		starttime_s = monthBegin;
		endtime_s= monthEnd;	
    }
    
    /**QJY
     * 获取今年的第一天和最后一天
     * @throws Exception
     */
    public void setCurrentYear()throws Exception{
    	Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_YEAR, 1);//本年第一天
		String yearBegin = DateUtil.parseDateYMR(c.getTime());
		
		
		c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_YEAR, 1);//本年最后一天
		c.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天
		
		String yearEnd = DateUtil.parseDateYMR(c.getTime());

		starttime_s = yearBegin;
		endtime_s = yearEnd;
    }
	
	/**
	 * 方法描述：在执行这个类中的其他方法前先调用此方法，保证存在实体类
	 * 
	 * @return
	 * @throws Exception
	 */
	public void prepare() throws Exception {

		super.saveRequestParameter();
	}
	
}
