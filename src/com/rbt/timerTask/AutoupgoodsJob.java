package com.rbt.timerTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.rbt.common.util.DbUtil;
import com.rbt.function.BatchListFuc;
import com.rbt.function.CreateSpringContext;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Goods;
import com.rbt.service.IAutoupgoodsService;
import com.rbt.service.IGoodsService;
/**
 * @Method Description :定时自动上下架商品
 * @author : HZX
 * @date : Apr 8, 2014 10:31:52 AM
 */
public class AutoupgoodsJob extends CreateSpringContext implements Job {
   //商品对象
	public Goods goods;
	//获取对象接口
    IAutoupgoodsService autoupgoodsService=(IAutoupgoodsService)getContext().getBean("autoupgoodsService");
    IGoodsService goodsService=(IGoodsService)getContext().getBean("goodsService");
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {   
				String istosend = "";// 获取系统配置是否启用更新的值
				istosend = SysconfigFuc.getSysValue("cfg_Isautogoods");
				// 根据系统配置表取值，如果cfg_Isautogoods的值为0：表示启用；1：不启用
				if (istosend.equals("0")) {
					Autoupgoods();// 执行定时自动上下架商品
				}
				
		} catch (Exception e) {
			System.err.println("定时自动上下架商品出现异常情况");
			e.printStackTrace();
		}
		
	}
	private void Autoupgoods(){
		//获取所有符合条件的Autoupgoods数据条数，并分批处理
		//自动上架
		 Map uMap = new HashMap();
		 uMap.put("autoup","1");
		 int ucount=autoupgoodsService.getCount(uMap);
		 List uclist=BatchListFuc.batchList(ucount);
			for(int i=0;i<uclist.size();i++){
				Map stMap=(Map)uclist.get(i);
				Map auMap=new HashMap();
				auMap.put("autoup","1");
				auMap.put("start", 0);
			    auMap.put("limit", SysconfigFuc.getSysValue("cfg_define_row"));
			  //获取所有符合条件的Autoupgoods数据
			    List au_idList = autoupgoodsService.getList(auMap);
				if(au_idList!=null||au_idList.size()>0){
					Map map=new HashMap();
					for (int j = 0; j <au_idList.size(); j++) {
						//取出Autoupgoods 的goods_id
						map=(Map)au_idList.get(j);
						String goods_id=map.get("goods_id").toString();
						goods=this.goodsService.get(goods_id);
						this.goods.setIs_up("0");
						this.goodsService.update(goods);
						System.out.println("=============================成功上架goods_id:"+goods_id);
					}
				}
			}
			//自动下架
			Map dMap = new HashMap();
			 dMap.put("autodown","1");
			 int dcount=autoupgoodsService.getCount(dMap);
			 List dclist=BatchListFuc.batchList(dcount);
				for(int i=0;i<dclist.size();i++){
					Map stMap=(Map)dclist.get(i);
					Map adMap=new HashMap();
					adMap.put("delin_date","1");
					adMap.put("start", "0");
				    adMap.put("limit", SysconfigFuc.getSysValue("cfg_define_row"));
				  //获取所有符合条件的Autoupgoods数据
				    List ad_idList = autoupgoodsService.getList(adMap);
					if(ad_idList!=null||ad_idList.size()>0){
						Map map=new HashMap();
						for (int j = 0; j <ad_idList.size(); j++) {
							//取出Autoupgoods 的goods_id
							map=(Map)ad_idList.get(j);
							String goods_id=map.get("goods_id").toString();
							goods=this.goodsService.get(goods_id);
							this.goods.setIs_up("1");
							this.goodsService.update(goods);
							System.out.println("=============================成功下架goods_id:"+goods_id);
						}
					}
				}
		
	} 
	public static void main(String[]args){
		new AutoupgoodsJob().Autoupgoods();
	}

}
