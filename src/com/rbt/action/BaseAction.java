/*
 
 * Package:com.rbt.action
 * FileName: AdminBaseAction.java 
 */

package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.FieldValidateFromXmlUtil;
import com.rbt.common.util.PageUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.CommparaFuc;
import com.rbt.function.MalltemplateFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.function.RateFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.message.LetterInter;
import com.rbt.message.MailInter;
import com.rbt.message.RtxmsgInter;
import com.rbt.model.Audithistory;
import com.rbt.model.Logs;
import com.rbt.model.Messagealert;
import com.rbt.model.Msgcheck;
import com.rbt.service.IAudithistoryService;
import com.rbt.service.IAutofckService;
import com.rbt.service.IInfocountService;
import com.rbt.service.ILogsService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.ICommparaService;
import com.rbt.common.util.RandomStrUtil;

/**
 * @function 功能 所有struts2框架action的父类
 * @author 创建人 HXK
 * @date 创建日期 Jun 13, 2014
 */
@Controller
public class BaseAction extends ActionSupport implements Preparable {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -8089763167338830169L;
	
	/*******************实体层****************/
	public Messagealert messagealert;
	public Msgcheck msgcheck;
	public ValidateUtil validateFactory;

	/*******************业务层接口****************/
	@Autowired
	private ILogsService logsService;
	@Autowired
	public IAudithistoryService audithistoryService;
	@Autowired
	private IInfocountService infocountService;
	@Autowired
	private IAutofckService autofckService;
	@Autowired
	public ICommparaService commparaService;
	@Autowired
	public IMemberuserService memberuserService;
	@Autowired
	public IMemberService memberService;

	/*********************集合******************/
	public List scoreList;//评分列表
	public List auditList;// 审核历史列表
	public List msgcheckList;//信息检查
	public List infoStateList=CommparaFuc.getEnabledList("info_state");//信息状态集合

	/*********************字段******************/
	public boolean is_search = false;//是否搜索
	public List commparaList;//参数列表
	public String session_cust_id = this.getSessionFieldValue(Constants.CUST_ID);//会员cust_id
	public String session_user_id = this.getSessionFieldValue(Constants.USER_ID);//会员user_id
	public String session_user_name = this.getSessionFieldValue(Constants.USER_NAME);//会员账号
	public String session_cust_name = this.getSessionFieldValue(Constants.CUST_NAME);//会员名称
	public String session_cust_type = this.getSessionFieldValue(Constants.CUST_TYPE);//会员账号类型
	public String session_mem_type = this.getSessionFieldValue(Constants.MEM_TYPE);//会员类型
	public String session_level_code = this.getSessionFieldValue(Constants.LEVEL_CODE);//会员级别
	public String session_org_area_id = this.getSessionFieldValue(Constants.ORG_AREA_ID);//角色ID
	public String session_user_type = this.getSessionFieldValue(Constants.USER_TYPE);//会员账号类型
	public String session_radom_no = this.getSessionFieldValue(Constants.RADOM_NO);//店铺随机数
	public String session_last_login_time = this.getSessionFieldValue(Constants.LAST_LOGIN_TIME);//最后一次登陆时间
	public String no_spec_image = "/include/common/images/del.gif";// 自定义规格无图图片
	public String default_template = MalltemplateFuc.getMallTemplate();// 系统商城的默认模板
	public String cfg_nopic = SysconfigFuc.getSysValue("cfg_nopic");// 系统默认无图图片
	public String mall_type = Constants.MALL_TYPE_B2B;
	public final String VIEW = Constants.VIEW;// 跳转更新页面
	public final String ADD = Constants.ADD;// 跳转新增页面
	public final String AUDIT = Constants.AUDIT;// 跳转审核页面
	public final String INDEXLIST = Constants.INDEX;// 跳转列表页
	public final String AUDITLIST = Constants.AUDITLIST;// 跳转审核列表页
	public String catIdStr = "1111111111";// SysconfigFuc.getSysValue("cfg_topcatid");// 分类的父ID串
	public String cat_attr_s;// 分类的搜索串
	public String cat_attr;// 定义分类串
	public String good_cat_attr;
	public String area_attr;// 定义地区串
	public String area_attr_names;// 定义地区串
	public String tax_attr;//定义税率
	public String tax_attr_s;//定义税率taxDiv_s
	public String mb_cat_attr;//会员自定义分类
	public String areaIdStr = SysconfigFuc.getSysValue("cfg_topareaid");// 地区的父ID串
	public String taxIdStr = "1111111111";// SysconfigFuc.getSysValue("cfg_topcatid");// 分类的父ID串
	public String cfg_combo_audit = SysconfigFuc.getSysValue("cfg_IsAuditCombo");//会员发布套餐是否审核
	public String cfg_goods_audit = SysconfigFuc.getSysValue("cfg_IsAuditGoods");//会员发布商品是否审核
	public String cfg_jifen_audit = SysconfigFuc.getSysValue("cfg_IsAuditJiFen");//会员发布积分是否审核
	public String cfg_IsAuditMiaoSha = SysconfigFuc.getSysValue("cfg_IsAuditMiaoSha");//会员发布积分是否审核
	public String cfg_virtualGoods = SysconfigFuc.getSysValue("cfg_virtualGoods");//会员发布虚拟商品是否审核
	public String cfg_IsAuditgroup = SysconfigFuc.getSysValue("cfg_IsAuditgroup");//会员发布团购商品是否审核
	public String cfg_IsAuditMemberlink = SysconfigFuc.getSysValue("cfg_IsAuditMemberlink");//会员发布友情链接是否审核
	public String cfg_IsAuditYuShou = SysconfigFuc.getSysValue("cfg_IsAuditYuShou");
	public String cfg_topcatid = SysconfigFuc.getSysValue("cfg_topcatid");
	public String cfg_orderTime = SysconfigFuc.getSysValue("cfg_orderTime");//订单收货期限默认天数
	public String cfg_assessTime = SysconfigFuc.getSysValue("cfg_assessTime");//商品评价默认天数
	public String area_attr_s;	// 地区的搜索串
	public String web_openmall = SysconfigFuc.getSysValue("cfg_openmall");// 查看商城是否关闭 0：开启 1：关闭
	public String cfg_area_filter = SysconfigFuc.getSysValue("cfg_area_filter");// 是否开启地区过滤
	public String cfg_index = SysconfigFuc.getSysValue("cfg_index");// 获取系统变量首页地址
	public String cfg_basehost = SysconfigFuc.getSysValue("cfg_basehost");// 网站地址
	public String cfg_mobiledomain = SysconfigFuc.getSysValue("cfg_mobiledomain");// 网站地址
	public String cfg_webname = SysconfigFuc.getSysValue("cfg_webname");// 网站名称
	public String cfg_logourl = SysconfigFuc.getSysValue("cfg_logourl");// 网站logo
	public String cfg_mall_serviceterm = SysconfigFuc.getSysValue("cfg_mall_serviceterm");// 商城协议
	public String cfg_mb_isaudit = SysconfigFuc.getSysValue("cfg_mb_isaudit");// 是否开启会员注册是否审核 0:是 1:否
	public String cfg_area_ip = SysconfigFuc.getSysValue("cfg_area_ip");// 是否开启ip过滤 0开启过滤 1则关闭
	public String two_pages_link;// 判断是否是两个页面的跳转
	private String tokenErrorPage = "/include/tokenError.jsp";// 重复提交页面
	public int pages_s = 1;// 当前页码
	public int pageSize_s = 10;// 后台一页展示的信息数
	public int webPageSize_s = 40;// 前台一页展示的信息数
	public int webappPageSize_s = 10;// 前台一页展示的信息数
	
	public String pageBar;// 程序生成的分页字符串
	public String webPageBar;// 程序生成的前台分页字符串
	public String oldinfotitle;	// 信息标题,用于控制信息标题重复出现
	public String hidden_area_value;//定义地区串
	public String hidden_cat_value;//定义地区串
	private HttpServletRequest req = getRequest();//定义地区串
	private String actionName = req.getRequestURI();// 获取执行方法的action名
	public String templateStyle = SysconfigFuc.getSysValue("cfg_templatestyle");// 模板的样式，可在系统后台设置
	public String templateRoute = SysconfigFuc.getSysValue("cfg_templateroute");// 模板生成文件路径，可在系统后台设置
	public String templateFiles = SysconfigFuc.getSysValue("cfg_templatefolder");// 模板文件路径，可在系统后台设置
	public String templatePortalFolder = Constants.TEMPLATE_PORTAL_FOLDER;// 模板文件引用存放路径
	public boolean ifvalidatepass = false;// 输入验证是否通过true:通过，false:不通过
	public String listSearchHiddenField;// 搜索项值的保存
	public String selfTemplate;// 自定义前台展示模版名称
	public Double payMoneyDouble = 10000000.00;// 每次充值的最大金额
	public Double accountMaxMoneyDouble = 1000000000.00;// 帐户的最大金额
	public String random_num = RandomStrUtil.getNumberRand();// 随机数
	public String navPosition;// 导航位置
	public String rateMark = RateFuc.getDefaultRateMark();// 默认货币符号
	public String rateUnit = RateFuc.getDefaultUnit();// 默认货币单位
	public String video_width = SysconfigFuc.getSysValue("cfg_video_width");// 视频宽度
	public String video_height = SysconfigFuc.getSysValue("cfg_video_height");// 视频高度
	public String limit_request = SysconfigFuc.getSysValue("cfg_limit_request");// 是否开启禁止频繁请求
	public String limit_time = SysconfigFuc.getSysValue("cfg_limit_time");// 冻结频繁请求时间
	public String limit_num = SysconfigFuc.getSysValue("cfg_limit_num");// 连续频繁请求的次数100次
	public String cfg_filesize=SysconfigFuc.getSysValue("cfg_filesize");//文件大小
	public String cfg_flashsize=SysconfigFuc.getSysValue("cfg_flashsize");//视频大小
	public String cfg_imgtype=SysconfigFuc.getSysValue("cfg_imgtype");//图片格式
	public String cfg_imgsize=SysconfigFuc.getSysValue("cfg_imgsize");//图片格式
	public String cfg_mediatype=SysconfigFuc.getSysValue("cfg_mediatype");//视频格式
	public String cfg_attachtype=SysconfigFuc.getSysValue("cfg_attachtype");//文件类型
	public String cfg_directtimeout=SysconfigFuc.getSysValue("cfg_directtimeout");//预售下订单多少分钟内未付款自动取消订单
	public String cfg_yushouendpaytime=SysconfigFuc.getSysValue("cfg_yushouendpaytime");//预售尾款结束时间
	public String cfg_publictimeout=SysconfigFuc.getSysValue("cfg_publictimeout");//普通商品下订单多少分钟内未付款自动取消订单
	public int cfg_refund_limt_date=Integer.parseInt(SysconfigFuc.getSysValue("cfg_refund_limt_date"));//售后申请退款有效期
	public int cfg_regoods_limt_date=Integer.parseInt(SysconfigFuc.getSysValue("cfg_regoods_limt_date"));//售后申请退货有效期
	public int cfg_exchange_limt_date=Integer.parseInt(SysconfigFuc.getSysValue("cfg_exchange_limt_date"));//售后申请换货有效期
	public int cfg_maxaddressnumber=Integer.parseInt(SysconfigFuc.getSysValue("cfg_maxaddressnumber"));//商城保存收货地址最大数
	public int cfg_Cautionstock=Integer.parseInt(SysconfigFuc.getSysValue("cfg_Cautionstock"));//库存警告
	public String cfg_public_number=SysconfigFuc.getSysValue("cfg_public_number");//公众号二维码
	public String cfg_Phone_touch=SysconfigFuc.getSysValue("cfg_Phone_touch");//触屏版二维码
	public String cfg_App_android=SysconfigFuc.getSysValue("cfg_App_android");//android二维码
	public String cfg_App_iphone=SysconfigFuc.getSysValue("cfg_App_iphone");//IOS二维码
	//IOS下载地址
	public String cfg_ios_url=SysconfigFuc.getSysValue("cfg_ios_url");//IOS下载地址
	public String cfg_andorid_url=SysconfigFuc.getSysValue("cfg_andorid_url");//android下载地址
	public String selli;
	public String parentMenuId;
	
	//友盟推送消息的appkey
	public String ym_android_appkey = SysconfigFuc.getSysValue("ym_android_appkey");//友盟安卓
	public String ym_android_appMasterSecret = SysconfigFuc.getSysValue("ym_android_appMasterSecret");//友盟安卓
	public String ym_ios_appkey = SysconfigFuc.getSysValue("ym_ios_appkey");//友盟IOS
	public String ym_ios_appMasterSecret = SysconfigFuc.getSysValue("ym_ios_appMasterSecret");//友盟IOS
	
	
	/**
	 * @author lin
	 * @date : May 10, 2014 10:06:25 PM
	 * @Method Description : 处理分类ID
	 */
	public void selectCat() {
		if (cat_attr != null && !cat_attr.equals("")) {
			cat_attr = cat_attr.replace(" ", "");
			catIdStr += "," + cat_attr;
			if (cat_attr.indexOf("0") > -1) {
				cat_attr = "";
			}
		}
	}

	/**
	 * @author lin
	 * @date : May 10, 2014 10:06:46 PM
	 * @Method Description : 进入更新页面返回串
	 */
	public void viewCat(String cat_attr) {
		if (catIdStr != null && catIdStr.equals("") || catIdStr.indexOf(cat_attr) == -1) {
			catIdStr += "," + cat_attr.replace(" ", "");
		}
	}

	
	/**
	 * @author XBY
	 * @date : May 10, 2014 10:06:46 PM
	 * @Method Description : 进入更新页面返回串
	 */
	public void viewTax(String mb_tax_attr) {
		if (taxIdStr != null && taxIdStr.equals("") || taxIdStr.indexOf(mb_tax_attr) == -1) {
			taxIdStr += "," + mb_tax_attr.replace(" ", "");
		}
	} 
	
	/**
	 * @Method Description :在进入更新,审核页面时进行回选
	 * @author : LJQ
	 * @date : Dec 6, 2014 2:26:50 PM
	 */
	public void backCategory(String catString) {
		if (catString != null) {
			hidden_cat_value = cfg_topcatid + "," + catString.replace(" ", "");
		}
	}
	/**
	 * @author lin
	 * @date : May 10, 2014 10:06:25 PM
	 * @Method Description : 处理地区ID
	 */
	public void selectArea() {
		if (area_attr_s != null && !area_attr_s.equals("")) {
			area_attr = area_attr_s;
		}
		if ((area_attr != null && !area_attr.equals(""))&&("1111111111").equals(areaIdStr)) {
			area_attr = area_attr.replace(" ", "");
			areaIdStr += "," + area_attr;
			if (area_attr.indexOf("0") > -1) {
				area_attr = "";
			}
		}
	}

	/**
	 * @author lin
	 * @date : May 10, 2014 10:06:46 PM
	 * @Method Description : 进入更新页面返回串
	 */
	public void viewArea(String area_attr) {
		if (areaIdStr != null && areaIdStr.equals("") || areaIdStr.indexOf(area_attr) == -1) {
			areaIdStr += "," + area_attr.replace(" ", "");
		}
		//if ((areaIdStr != null && areaIdStr.equals(""))) {
			//areaIdStr += "," + area_attr;
		//}
	}

	/**
	 * @author : LJQ
	 * @date : Jan 7, 2014 4:52:24 PM
	 * @Method Description :验证分类是否选择
	 */
	public void validateCategoryIfSelect() {
		// 验证所属分类是否有选择
		if (ValidateUtil.isRequired(cat_attr) || cat_attr.indexOf("0") > -1) {
			this.addFieldError("cate_attr", "请选择分类");
		} else {
			cat_attr = cat_attr.replace(" ", "");
		}
	}

	/**
	 * @author : LJQ
	 * @date : Jan 7, 2014 4:52:35 PM
	 * @Method Description :验证地区是否选择
	 */
	public void validateAreaIfSelect() {
		// 验证所属地区是否有选择
		if (ValidateUtil.isRequired(area_attr) || area_attr.indexOf("0") > -1) {
			this.addFieldError("area_attr", "请选择地区");
		} else {
			area_attr = area_attr.replace(" ", "");
		}
	}

	/**
	 * @Method Description :提示操作信息
	 * @author : LJQ
	 * @date : Nov 15, 2014 10:43:36 AM
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void addActionMessage(String message) {
		super.addActionMessage(message);
		// 记录日志
		insertLogs(message, this.session_cust_id, this.session_user_id, this.session_user_name);
	}
	/**
	 * @Method Description :提示操作信息
	 * @author : LJQ
	 * @date : Nov 15, 2014 10:43:36 AM
	 * @param message:提示消息  ，logmessage：插入日记消息
	 */
	@SuppressWarnings("unchecked")
	public void addActionMessage(String message,String logmessage) {
		super.addActionMessage(message);
		// 记录日志
		insertLogs(logmessage, this.session_cust_id, this.session_user_id, this.session_user_name);
	}

	/**
	 * @author : LJQ
	 * @date : Jan 7, 2014 4:48:10 PM
	 * @Method Description :记录操作日志
	 */
	public void insertLogs(String message, String cust_id, String user_id, String user_name) {
		Logs logs = new Logs();
		logs.setIp(getRequest().getRemoteAddr());
		logs.setCust_id(cust_id);
		logs.setUser_id(user_id);
		logs.setUser_name(user_name);
		logs.setContent(message);
		this.logsService.insert(logs);
	}

	// 获取session中的值公共方法
	public String getSessionFieldValue(String fieldName) {
		HttpSession session = getSession();
		String fieldValue = "";
		if (session.getAttribute(fieldName) != null) {
			fieldValue = session.getAttribute(fieldName).toString();
		}
		return fieldValue;
	}

	// 获取request中的值的公共方法
	public String getRequestFieldValue(String fieldName) {
		return getRequestFieldValue(fieldName, true);
	}

	// 获取request中的值的公共方法
	public String getRequestFieldValue(String fieldName, boolean b) {
		HttpServletRequest request = this.getRequest();
		String fieldValue = "";
		if (b) {
			if (request.getParameter(fieldName) != null) {
				fieldValue = request.getParameter(fieldName);
			}
		} else {
			if (request.getAttribute(fieldName) != null) {
				fieldValue = request.getAttribute(fieldName).toString();
			}
		}
		return fieldValue;
	}

	/**
	 * 方法描述：分页统一处理
	 * 
	 * @date 2014-11-3
	 * @author HXK
	 * @param count
	 *            信息数量
	 * @param pageMap
	 *            信息参数
	 */
	@SuppressWarnings("unchecked")
	public Map pageTool(int count, Map pageMap) {
		PageUtil page = new PageUtil();
		// 判断是两个页面间的跳转则给page页面赋值
		if (two_pages_link != null && two_pages_link.equals("no")) {
			page.setCurPage(1);
			page.setPageSize(10);
		} else {
			page.setCurPage(pages_s);
			page.setPageSize(pageSize_s);
		}

		page.setTotalRow(count);
		pageBar = page.getToolsMenu();
		pageMap.put("start", page.getStart());
		pageMap.put("limit", page.getEnd());
		return pageMap;
	}
	
	@SuppressWarnings("unchecked")
	public Map webPageTool(int count,Map pageMap){
		PageUtil page = new PageUtil();
		page.setCurPage(pages_s);
		page.setPageSize(webPageSize_s);
		page.setTotalRow(count);
		webPageBar = page.getWebToolsMenu();
		pageMap.put("start", page.getStart());
		pageMap.put("limit", page.getEnd());
		return pageMap;
	}
	
	/**
	 * 方法描述：分页统一处理
	 * 
	 * @date 2014-11-3
	 * @author HXK
	 * @param count
	 *            信息数量
	 * @param pageMap
	 *            信息参数
	 */
	@SuppressWarnings("unchecked")
	public Map webAppPageTool(int count, Map pageMap) {	
		PageUtil page = new PageUtil();
		// 判断是两个页面间的跳转则给page页面赋值
		if (two_pages_link != null && two_pages_link.equals("no")) {
			page.setCurPage(1);
			page.setPageSize(10);
		} else {
			page.setCurPage(pages_s);
			page.setPageSize(pageSize_s);
		}

		page.setTotalRow(count);
		pageBar = page.getWebAppToolsMenu();
		pageMap.put("start", page.getStart());
		pageMap.put("limit", page.getEnd());
		return pageMap;
	}
	


	// 是否开启地区过滤管理功能
	// 控制运营商后台地区过滤
	// user_type是4，代表为运营商后台子账户
	@SuppressWarnings("unchecked")
	public Map areafilter(Map pageMap) {
		if (cfg_area_filter.equals("0") && this.session_cust_type.equals(Constants.ADMIN_TYPE) && this.getSessionFieldValue(Constants.USER_TYPE).equals("4")) {
			String area_filter = this.getSessionFieldValue(Constants.AREA_MANAGER);
			if (!area_filter.equals("") && !area_filter.equals("0")) {
				pageMap.put("area_attr", area_filter);
			}
		}
		return pageMap;
	}

	/**
	 * 方法描述：保存列表搜索的参数域和值，以至于修改、删除、新增动作后返回列表页仍然是之前搜索时所留下的值
	 * 这里对搜索的字段名有个控制，字段名必须是"_s"结尾才会被保存
	 * 
	 * @date 2014-12-16
	 * @author HXK
	 */
	public void saveRequestParameter() throws IOException {
		if (this.req.getParameter("top_menu") != null && !this.req.getParameter("top_menu").equals("")) {
			getSession().setAttribute("first_menu_id", this.req.getParameter("top_menu").toString());
		}
		Enumeration reqParamNames = this.req.getParameterNames();
		StringBuffer sb = new StringBuffer();
		int i = 0;
		while (reqParamNames.hasMoreElements()) {
			String fieldName = (String) reqParamNames.nextElement();
			String fieldValue = "";
			if (this.req.getParameter(fieldName) != null) {
				fieldValue = this.req.getParameter(fieldName);
			}
			if (fieldName.endsWith("_s")) {
				sb.append("<input type='hidden' name='" + fieldName + "' value='" + fieldValue + "' id='rsrvId" + (i++) + "'/>\n");
			}
		}
		sb.append("<input type='hidden' name='rsrvNum' value='" + i + "' id='rsrvNum'/>\n");
		listSearchHiddenField = sb.toString();
	}

	/**
	 * @author : LJQ
	 * @date : Apr 9, 2014 9:51:22 PM
	 * @Method Description :前台AJAX分页转json格式数据的转换
	 */
	public String pageList(List list, int totalNum) {
		StringBuffer buf = new StringBuffer();
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		HttpServletRequest request = getRequest();
		String currentPage = request.getParameter("cp");
		String pageSize = request.getParameter("ps");
		for (int i = 0; i < list.size(); i++) {
			Object obj = list.get(i);
			jsonArr.add(obj);
		}
		jsonObj.put("currentPage", currentPage);
		jsonObj.put("pageSize", pageSize);
		jsonObj.put("totalCount", totalNum);
		jsonObj.put("data", jsonArr);
		buf.append(jsonObj.toString());
		return buf.toString();
	}

	/**
	 * @author : LJQ
	 * @date : Apr 10, 2014 4:05:10 PM
	 * @Method Description :AJAX分页搜索条件的过滤
	 */
	public Map ajaxMap(Map map) {
		HttpServletRequest request = getRequest();
		String currentPage = "1", pageSize = "20";
		if (request.getParameter("cp") != null) {
			currentPage = request.getParameter("cp");
		}
		if (request.getParameter("ps") != null) {
			pageSize = request.getParameter("ps");
		}
		int startRow = (Integer.parseInt(currentPage) - 1) * Integer.parseInt(pageSize);// 计算开始行
		map.put("start", startRow);
		map.put("limit", pageSize);
		return map;
	}

	/**
	 * 方法描述：判断信息标题是否存在 strnewtilte:修改后的标题
	 * stroldtitle:原来的标题。只要用用于更新操作,如果是新增信息的话，直接填入空值就可以了 tablename:表名
	 * tablecocolumn: 表的标题列名称
	 * @date 2014-11-24
	 * @author HXK
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Boolean existsTitle(String strnewtilte, String stroldtitle, String tablename, String tablecocolumn) {
		// falges为：true的时候就是重复出现标题，为false的时候没有重复出现标题
		boolean falges = false;
		HashMap titleMap = new HashMap();
		if (strnewtilte != null) {
			strnewtilte = strnewtilte.replace(" ", "");
		}
		// 对应列的值
		titleMap.put("trtitle", strnewtilte);
		// 表名
		titleMap.put("tablename", tablename);
		// 列名
		titleMap.put("titlecol", tablecocolumn);
		int count = this.infocountService.getRepeatTitle(titleMap);
//		if (stroldtitle != null || !stroldtitle.equals("")) {
//			stroldtitle = stroldtitle.trim();
//		}
		// 更新页面使用的判断
		if (stroldtitle != null && !stroldtitle.equals("")) {
			if (!strnewtilte.equals(stroldtitle.trim())) {
				if (count != 0)
					falges = true;// 重复出现
			}
		} else {// 新增页面使用的判断
			if (count != 0)
				falges = true;// 重复出现
		}
		return falges;// 没有重复出现
	}

	/**
	 * @return the pageBar
	 */
	public String getPageBar() {
		return pageBar;
	}

	/**
	 * @param pageBar
	 *            the pageBar to set
	 */
	public void setPageBar(String pageBar) {
		this.pageBar = pageBar;
	}


	/**
	 * Convenience method to get the request
	 * 
	 * @return current request
	 */
	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * Convenience method to get the response
	 * 
	 * @return current response
	 */
	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * Convenience method to get the session. This will create a session if one
	 * doesn't exist.
	 * 
	 * @return the session from the request (request.getSession()).
	 */
	protected HttpSession getSession() {
		return getRequest().getSession();
	}


	public String redirectUrl;

	public String goUrl(String redirectUrl) {
		if (this.selfTemplate != null && !this.selfTemplate.equals("")) {
			this.redirectUrl = this.selfTemplate;
		} else {
			this.redirectUrl = redirectUrl;
		}
		return "redirectUrl";
	}

	/*
	 * 验证字段 obj：需要验证的标题提交字段
	 */
	public void commonValidateField(Object obj) {
		commonValidateField(obj, "public");
	}

	/*
	 * 验证字段 obj：需要验证的标题提交字段 methodName：特殊方法的验证名称
	 */
	public void commonValidateField(Object obj, String methodName) {

		// 获取对象名称，小写
		String className = obj.getClass().getSimpleName().toLowerCase();

		Map objMap = ValidateUtil.convertObjToMap(obj);

		// 从xml文档中获取验证规则ArrayList
		List validateList = FieldValidateFromXmlUtil.getObjValidateList(className, methodName);

		if (validateList != null && validateList.size() > 0) {
			for (int i = 0; i < validateList.size(); i++) {

				HashMap vtableMap = (HashMap) validateList.get(i);
				String name = "", type = "", length = "", required = "", cnname = "";
				if (vtableMap.get("name") != null)
					name = vtableMap.get("name").toString();
				if (vtableMap.get("type") != null)
					type = vtableMap.get("type").toString();
				if (vtableMap.get("length") != null)
					length = vtableMap.get("length").toString();
				if (vtableMap.get("required") != null)
					required = vtableMap.get("required").toString();
				if (vtableMap.get("cnname") != null)
					cnname = vtableMap.get("cnname").toString();

				// 获取表单提交的值
				String fieldValue = "";
				if (objMap.get(name) != null) {
					fieldValue = objMap.get(name).toString();
				}
				// 验证字段为必填
				if (required.equals("true") && fieldValue.trim().equals("")) {
					this.addFieldError(className + "." + name, cnname + "不能为空");
				}
				if (!fieldValue.trim().equals("")) {
					// type：字段类型
					// string为字符串, int为整数数字, email为电子邮件, tel为固定电话, mobile为移动电话,
					// double为浮点型, chinese为中文, idcard为18位身份证格式, ip为IP格式,
					// time为时间格式,
					// alpha为字母, repeat为重复字符 alphas 为数字、字母、下划线, rmb人民币类型
					// alphasfirst由字母开头和数字、字母、下划线组成 ,alphaslimt 数字、字母、下划线组成6-20位
					// 验证字段类型为：int为整数数字
					// 验证字段类型为：post为邮政编码
					if (fieldValue.length() > Integer.parseInt(length)) {
						this.addFieldError(className + "." + name, cnname + "的长度限制在" + length + "个字符");
					} else {
						if (type.equals("int") && ValidateUtil.isDigital(fieldValue.trim())) {
							this.addFieldError(className + "." + name, cnname + "只能为数字(0-9)");
						}
						// 验证字段类型为：email为电子邮件
						if (type.equals("email") && ValidateUtil.isEmail(fieldValue.trim())) {
							this.addFieldError(className + "." + name, cnname + "格式不正确,格式如:xxxx@xxx.com");
						}
						// 验证字段类型为：tel为固定电话
						if (type.equals("tel") && ValidateUtil.isTelephone(fieldValue.trim())) {
							this.addFieldError(className + "." + name, cnname + "格式不正确,格式如:xxx-xxxxxxx");
						}
						// 验证字段类型为：mobile为移动电话
						if (type.equals("mobile") && ValidateUtil.isMobile(fieldValue.trim())) {
							this.addFieldError(className + "." + name, cnname + "格式不正确");
						}
						// 验证字段类型为：double为浮点型
						if (type.equals("double") && ValidateUtil.isDouble(fieldValue.trim())) {
							this.addFieldError(className + "." + name, cnname + "只能为浮点型");
						}
						// 验证字段类型为：chinese为中文
						if (type.equals("chinese") && ValidateUtil.isChinese(fieldValue.trim())) {
							this.addFieldError(className + "." + name, cnname + "只能为汉字");
						}
						// 验证字段类型为：idcard为18位身份证格式
						if (type.equals("idcard") && ValidateUtil.isIdcard_18(fieldValue.trim())) {
							this.addFieldError(className + "." + name, cnname + "只能为18位身份证格式");
						}
						// 验证字段类型为：ip为IP格式
						if (type.equals("ip") && ValidateUtil.isIP(fieldValue.trim())) {
							this.addFieldError(className + "." + name, cnname + "只能为IP地址格式");
						}
						// 验证字段类型为：time为时间格式
						if (type.equals("time") && ValidateUtil.isTime(fieldValue.trim())) {
							this.addFieldError(className + "." + name, cnname + "格式不正确,格式如:1900-01-01");
						}
						// 验证字段类型为：alpha为字母
						if (type.equals("alpha") && ValidateUtil.isAlpha(fieldValue.trim())) {
							this.addFieldError(className + "." + name, cnname + "只能为字母(a-z A-Z)");
						}
						// 验证字段类型为：repeat为重复字符
						if (type.equals("repeat") && ValidateUtil.hasRepeat(fieldValue.trim())) {
							this.addFieldError(className + "." + name, cnname + "不能出现重复");
						}
						// 验证字段类型为：alphas 为数字、字母、下划线
						if (type.equals("alphas") && ValidateUtil.isAlphas(fieldValue.trim())) {
							this.addFieldError(className + "." + name, cnname + "由数字、字母或下划线组成");
						}
						// 验证字段类型为：rmb人民币类型
						if (type.equals("rmb") && ValidateUtil.isRmb(fieldValue.trim())) {
							this.addFieldError(className + "." + name, cnname + "格式不正确,格式如:100.000");
						}
						// 验证字段类型为：alphasfirst由字母开头和数字、字母、下划线结束
						if (type.equals("alphasfirst") && ValidateUtil.isAlphasFirst(fieldValue.trim())) {
							this.addFieldError(className + "." + name, cnname + "以字母开头,由数字、字母或下划线组成");
						}
						// 验证字段类型为：alphaslimt 数字、字母、下划线组成6-20位
						if (type.equals("alphaslimt") && ValidateUtil.isAlphasLimtLength(fieldValue.trim())) {
							this.addFieldError(className + "." + name, cnname + "由6-32位的数字、字母或下划线组成");
						}
						// 验证字段类型为：URL格式验证
						 if(type.equals("url")&&ValidateUtil.isURL(fieldValue.trim()) ){
						    this.addFieldError(className+"."+name, cnname + "格式不正确,格式如:www.xxx.com"); 
						}
						// 验证字段类型为：POST格式验证
						 if(type.equals("post")&&ValidateUtil.isPOST(fieldValue.trim()) ){
						    this.addFieldError(className+"."+name, cnname + "格式不正确"); 
						}
					}
				}

			}

		}
	}

	/**
	 * @author : LJQ
	 * @date : Jun 14, 2014 3:55:24 PM
	 * @Method Description :不同的会员只能操作属于本身的会员标识权限控制
	 */
	public boolean accessControl(String cust_id) {
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if (cust_id.equals(this.session_cust_id)) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	/**
	 * @author : LJQ
	 * @date : Jan 10, 2014 1:00:48 PM
	 * @Method Description :重写父类addFieldError
	 */
	public void addFieldError(String fieldName, String errorMessage) {
		super.addFieldError(fieldName, errorMessage);
		this.ifvalidatepass = true;
	}


	/**
	 * @author : LJQ
	 * @date : Feb 17, 2014 11:00:29 AM
	 * @Method Description : fck自动保存
	 */
	public void fckAutoSave(String id, String table_name) {
		Map fckMap = new HashMap();
		if (this.session_cust_id != null && !"".equals(this.session_cust_id)) {
			fckMap.put("cust_id", this.session_cust_id);
		}
		if (id != null && !"".equals(id)) {
			fckMap.put("table_id", id);
		}
		fckMap.put("table_name", table_name);
		if (random_num != null && !"".equals(random_num)) {
			fckMap.put("random_num", random_num);
		}
		autofckService.updaterandom(fckMap);
	}

	/**
	 * @author : LJQ
	 * @date : Feb 17, 2014 11:21:36 AM
	 * @Method Description : 加载对应的已保存的FCK数据
	 */
	public void loadFckData(String id, String table_name) {
		Map map = new HashMap();
		map.put("table_id", id);
		map.put("table_name", table_name);
		List list = this.autofckService.getList(map);
		if (list!=null&&list.size() > 0) {
			HashMap listMap = (HashMap) list.get(0);
			if (listMap.get("random_num") != null) {
				random_num = listMap.get("random_num").toString();
			}
		}
	}


	/**
	 * @author : LJQ
	 * @date : Mar 1, 2014 2:18:00 PM
	 * @Method Description : 查找出审核历史记录
	 */
	public List searchAuditList(String info_id, String table_name) {
		Map auditListMap = new HashMap();
		if (info_id != null && !info_id.equals(""))
			auditListMap.put("info_id", info_id);
		if (table_name != null && !table_name.equals(""))
			auditListMap.put("module_type", table_name);
		auditList = this.audithistoryService.getList(auditListMap);
		return auditList;
	}

	/**
	 * @author : LJQ
	 * @date : Mar 1, 2014 2:59:24 PM
	 * @Method Description : 新增数据操作流
	 */
	public void addAuditRecord(String info_id, String table_name, String info_state, String no_reason) {
		Audithistory ah = new Audithistory();
		ah.setCust_id(this.session_cust_id);
		ah.setUser_id(this.session_user_id);
		ah.setUser_name(this.session_user_name);
		ah.setInfo_state(info_state);
		ah.setNo_reason(no_reason);
		ah.setModule_type(table_name);
		ah.setInfo_id(info_id);
		this.audithistoryService.insert(ah);
	}

	/**
	 * @author : LJQ
	 * @date : Mar 8, 2014 3:45:16 PM
	 * @Method Description : 邮箱发送的公共方法
	 */
	public void sendEmail(String title, String content, String toMailAddr) {
		new MailInter().sendBatchMail(title, content, toMailAddr);
	}

	/**
	 * @author : LJQ
	 * @throws UnsupportedEncodingException
	 * @date : Mar 11, 2014 9:46:58 AM
	 * @Method Description :发送默认格式的短信
	 */
	public void SendMsg(String mobile,String title,String content) throws UnsupportedEncodingException {
		sendBaseMsg(mobile,title, content, "", "", "");
	}

	/**
	 * @author : LJQ
	 * @throws UnsupportedEncodingException
	 * @date : Mar 11, 2014 9:30:53 AM
	 * @Method Description : 发送短信接口的公共方法 参
	 *         数：mobile,content,ext,stime,rrid(手机号，内容，扩展码，定时时间，唯一标识) 返 回
	 *         值：唯一标识，如果不填写rrid将返回系统生成的
	 */
	public void sendBaseMsg(String mobile,String title, String content, String ext, String stime, String rrid) throws UnsupportedEncodingException {
		// 短信发送
		content = ToolsFuc.getChinese(content).replace(" ", "");// 清除html代码
		 new RtxmsgInter().mt(mobile, title,content, ext, stime, rrid);
		
	}

	/**
	 * @author : LJQ
	 * @date : Mar 11, 2014 10:07:16 AM
	 * @Method Description : 发送站内信
	 */
	public void sendLetter(String send_cust_id, String get_cust_id, String title, String content) {
		LetterInter let = new LetterInter();
		let.sendLetter(send_cust_id, get_cust_id, title, content);
	}


	/**	
	 * @author : WXP
	 * @param :cust_id
	 * @date May 15, 2014 3:07:33 PM
	 * @Method Description :获取用户购物车商品个数 并插入cookie
	 */
	/*public void getCartgoodsNum(String cust_id) {
		HttpServletResponse response = getResponse();
		Map map = new HashMap();
		map.put("cust_id", cust_id);
		//获取购物车cookie
		HttpServletRequest request = getRequest();
		Cookie[] cookies = request.getCookies();
        for(Cookie c :cookies ){
      	  if(c.getName().equals("cookie_id")){
      		 map.put("cookie_id", c.getValue());
      		 System.out.println(c.getName()+"--->"+c.getValue());
      	  }
        }
		int count = this.cartgoodsService.getCount(map);
		Cookie cookie = new Cookie("cart_cookieNum", String.valueOf(count));
		cookie.setPath("/");
		cookie.setMaxAge(7 * 24 * 60 * 60);
		response.addCookie(cookie);
	}*/
	/**
	 * @author : HZX
	 * @date : Nov 10, 2014 5:16:07 PM
	 * @Method Description :ajax返回服务器时间
	 */
	public void nowTime() throws IOException{
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String nowtime=df.format(new Date())+".0";
		out.write(nowtime);
	}
	/**
	 * @author : HZX
	 * @date : Nov 28, 2014 1:07:58 PM
	 * @Method Description :获取上次登录时间或是第一次登录时间
	 */
	public  void get_Last_Login_Time(String user_id){
		//获取最后一次的登录时间
		String login_time="";
		//获取上次登录时间或是第一次登录时间
		HashMap map=new HashMap();
		map.put("user_id",user_id);
		map.put("start",0);
		map.put("limit",2);
		map.put("content", Constants.MEMBER_LOGIN_LOG);//日志内容：会员登录
		//找出会员近两次的登录记录
		List logsList=logsService.getList(map);
		Map logmap=new HashMap();
		if(logsList!=null && logsList.size() >0 ){
			if(logsList.size()==1){
				logmap=(HashMap)logsList.get(0);
			}else{
				logmap=(HashMap)logsList.get(1);
			}
			login_time=logmap.get("in_date").toString();
		}else{
			Date now = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			 login_time=df.format(new Date());
		}
		getSession().setAttribute(Constants.LAST_LOGIN_TIME, login_time);
	}
	
	/**
	 * @author : LJQ
	 * @date : Mar 29, 2014 12:59:09 PM
	 * @Method Description :初始化AdminBaseAction加载方法
	 */

	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		//		if(limit_request.equals("0")){
		//			//获取用户登录的IP
		//			String ip = getRequest().getRemoteAddr();
		//		    Map msgckeckMap = new HashMap();
		//			msgckeckMap.put("cp_phone", ip);
		//			msgckeckMap.put("cp_type", "5");
		//			// 获取请求时间在5内
		//     		msgckeckMap.put("limit_time_msg", limit_time);
		//			msgcheckList = this.msgcheckService.getList(msgckeckMap);
		//			int limit_nums=Integer.parseInt(limit_num);
		//			if(msgcheckList!=null && msgcheckList.size()>limit_nums){
		//				this.getResponse().sendRedirect("某个页面");
		//			}
		//			Msgcheck msgcheck = new Msgcheck();
		//			msgcheck.setCp_check("");
		//			msgcheck.setCp_phone(ip);
		//			msgcheck.setCp_type("5");
		//	        this.msgcheckService.insert(msgcheck);
		//		}
	}
}
