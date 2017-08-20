package com.rbt.webappaction;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.lucene.queryParser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.Preparable;
import com.rbt.model.Goods;
import com.rbt.model.Member;
import com.rbt.model.Navtab;
import com.rbt.model.Receivebox;
import com.rbt.model.Sendbox;
import com.rbt.service.IGoodsService;
import com.rbt.service.IGoodsbrandService;
import com.rbt.service.INavigationService;
import com.rbt.service.INavtabService;
import com.rbt.service.IReceiveboxService;
import com.rbt.service.ISendboxService;

public class WebAppreceiveboxAction extends WebAppgoodsModelAction implements Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9214286118832400642L;

	/*******************实体层****************/
	public Receivebox receivebox;
	public  Sendbox sendbox;
	/*******************业务层接口****************/
	@Autowired
	private IReceiveboxService receiveboxService;
	@Autowired
	private ISendboxService sendboxService;
	/*********************集合******************/
	public List receiveboxList;//收件箱
	/*********************字段******************/
	public String receivebox_id="";
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		//0：表示逻辑删  1：没有删除  2：物理删除
		pageMap.put("is_get_del", "1");
		//会员显示自己的
		pageMap.put("cust_id", this.session_cust_id);
		//根据页面提交的条件找出信息总数
		int count=this.receiveboxService.getCount(pageMap);
		//分页插件
		pageMap = super.webAppPageTool(count,pageMap);
		receiveboxList = this.receiveboxService.getList(pageMap);
		return goUrl("mbNews");
	}
	/**
	 * 方法描述：根据主键找出记录收件箱信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(receivebox_id==null || receivebox_id.equals("")){
			return list();
		}else{
			receivebox = this.receiveboxService.get(receivebox_id);
			sendbox=this.sendboxService.get(receivebox.getSend_id());
		}
		if(sendbox==null){
			sendbox=new Sendbox();
		}
		if(receivebox.getIs_read()!=null && receivebox.getIs_read().equals("1")){
			//设为已读
			this.receivebox.setIs_read("0");
			this.receiveboxService.update(receivebox);
		}
		return goUrl("mbNewsDetail");
	}
	public void prepare() throws Exception {
	}

}
