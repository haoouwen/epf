package com.rbt.webappaction;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Exredbag;
import com.rbt.model.Redsumer;
import com.rbt.service.IExredbagService;
import com.rbt.service.IRedsumerService;

public class WebAppredsumerAction extends WebAppgoodsModelAction implements Preparable {

	/*******************实体层****************/

	/*******************业务层接口****************/
	@Autowired
	private IRedsumerService redsumerService;
	@Autowired
	private IExredbagService exredbagService;

	/*********************集合******************/
	
	public List redsumerList;//红包列表


	/*********************字段******************/
    
	public String red_no;//红包号码
	

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
        Map pageMap=new HashMap();
        pageMap.put("use_state", "0");
        pageMap.put("date", "now");
        pageMap.put("cust_id", this.session_cust_id);
		// 根据页面提交的条件找出信息总数
		int count = this.redsumerService.getCount(pageMap);
		// 分页插件
		pageMap = super.webAppPageTool(count, pageMap);
        redsumerList = this.redsumerService.getList(pageMap);
        redsumerList = ToolsFuc.replaceList(redsumerList, "");
		return goUrl("mbRedBag");
	}
	
	
	/**
	 * 兑换红包
	 * @throws IOException
	 */
	public String exRedbag() throws Exception {
		if(!ValidateUtil.isRequired(red_no)){
			Map map = new HashMap();
			map.put("red_no", red_no);
			List list = this.exredbagService.getList(map);
			
			//判断是否已经兑换过
			if (list != null && list.size() > 0 ) {
				  Map couponMap = (HashMap) list.get(0);
				  if(couponMap.get("ex_state") != null && couponMap.get("ex_state").toString().equals("1")) {
					  //红包已经兑换过 
					  this.addFieldError("red_no", "红包已经兑换过！");
					  return goUrl("mbExRedbag");
				   }else {
					  String ex_id = couponMap.get("ex_id").toString();
					  Exredbag exredbag = this.exredbagService.get(ex_id);
					  //插入红包
					  Redsumer redsumer = new Redsumer();
					  redsumer.setRedsumer_no(red_no);
					  redsumer.setRed_id(exredbag.getRed_id());
					  redsumer.setCust_id(this.session_cust_id);
					  redsumer.setUse_state("0");
					  this.redsumerService.insert(redsumer);
					  //修改兑换状态
					  exredbag.setEx_state("1");
					  this.exredbagService.update(exredbag);
					  return list();
				   }
			}else { 
			   //判断红包号码不存在
				this.addFieldError("red_no", "红包号码错误！");
				return goUrl("mbExRedbag");
			   
			}
		}else{
			this.addFieldError("red_no", "请选填写红包号码!");
			return goUrl("mbExRedbag");
		}
		
		
	}
	
	
	
	public void prepare() throws Exception {
	}

}
