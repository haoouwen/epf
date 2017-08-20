package com.rbt.webaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.Preparable;
import com.rbt.model.Advinfo;
import com.rbt.model.Goods;
import com.rbt.service.IAdvinfoService;
import com.rbt.service.IGoodsspreadService;
import com.rbt.action.BaseAction;
import com.rbt.common.util.PageUtil;
import com.rbt.function.AreaFuc;

/**
 * @author : CYC
 * @date : Jul 3, 2014 5:22:46 PM
 * @Method Description :跨进通
 */
public class WebkjtAction extends BaseAction implements Preparable{
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/

	/*******************业务层接口****************/
	/*********************集合********************/

	/*********************字段********************/

	
	/**
	 * @author CYC
	 * @throws IOException 
	 * @Method Description :品牌特卖首页绑定
	 */
	public String ordercallback() throws Exception{
		
		String qweqe="123123";
		return goUrl("success");
	}
	

	
	public void prepare() throws Exception {
		
	}
}
