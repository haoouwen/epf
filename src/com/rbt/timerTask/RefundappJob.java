package com.rbt.timerTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.rbt.function.BatchListFuc;
import com.rbt.function.CreateSpringContext;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Fundhistory;
import com.rbt.model.Goodsorder;
import com.rbt.model.Memberfund;
import com.rbt.model.Refundapp;
import com.rbt.model.Shopconfig;
import com.rbt.service.IFundhistoryService;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IRefundappService;
import com.rbt.service.IShopconfigService;
import com.rbt.service.ISysfundService;
import com.rbt.service.ISysuserService;
import com.rbt.service.impl.FundhistoryService;
import com.rbt.service.impl.ShopconfigService;
/**
 * @Method Description :退款失败达到系统设定天数，自动将款打给卖家;退款中，卖家超过系统设定天数未处理，自动将款打给买家;定时卖家向买家发送地址时间；定时买家发送退货时间；定时卖家确认收货时间
 * @author : HZX
 * @date : Apr 27, 2014 3:13:20 PM
 */
public class RefundappJob extends CreateSpringContext implements Job {
	//获取对象
    IRefundappService RefundappService=(IRefundappService)getContext().getBean("refundappService");
    IGoodsorderService GoodsorderService=(IGoodsorderService)getContext().getBean("goodsorderService");
    IMemberfundService MemberfundService=(IMemberfundService)getContext().getBean("memberfundService");
    IFundhistoryService FundhistoryService=(FundhistoryService)getContext().getBean("fundhistoryService");
    IShopconfigService ShopconfigService=(ShopconfigService)getContext().getBean("shopconfigService");
    ISysuserService sysuserService=(ISysuserService)getContext().getBean("sysuserService");
    ISysfundService sysfundService=(ISysfundService)getContext().getBean("sysfundService");
    
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {   
				String isNoRefund = "";// 获取系统配置是否启用更新的值
				isNoRefund = SysconfigFuc.getSysValue("cfg_IsNoRefund");
				// 根据系统配置表取值，如果cfg_IsNoRefund的默认值为15
					AutoNoRefund(isNoRefund);// 执行自动将款打给卖家
				
		} catch (Exception e) {
			System.err.println("退款失败达到系统设定天数，自动将款打给卖家出现异常情况");
			e.printStackTrace();
		}finally{
				try {   
					String refundDealtime = "";// 获取系统配置是卖家处理时间
					refundDealtime = SysconfigFuc.getSysValue("cfg_RefundDealtime");
					// 根据系统配置表取值cfg_RefundDealtime执行自动将款打给卖家
						AutoAgree(refundDealtime);// 执行自动将款打给买家
						
				} catch (Exception e1) {
					System.err.println("自动将款打给买家出现异常情况");
					e1.printStackTrace();
				}
				finally{
					try {   
						String cfg_Refundsend_addr_time = "";// 获取系统配置是卖家向买家发送收货地址时间
						cfg_Refundsend_addr_time = SysconfigFuc.getSysValue("cfg_Refundsend_addr_time");
						// 根据系统配置表取值执行自动发送卖家店铺地址给买家
							AutoSend_addr(cfg_Refundsend_addr_time);// 执行自动发送卖家店铺地址给买家
							
					} catch (Exception e2) {
						System.err.println("自动发送卖家店铺地址给买家出现异常情况");
						e2.printStackTrace();
					}
					finally{
						try {   
							String cfg_Refundsend_goods_time = "";// 获取系统配置是买家向卖家发送退货商品时间
							cfg_Refundsend_goods_time = SysconfigFuc.getSysValue("cfg_Refundsend_goods_time");
							// 根据系统配置表取值执行自动取消退款
								AutoCancel(cfg_Refundsend_goods_time);// 执行自动取消退款
								
						} catch (Exception e3) {
							System.err.println("自动取消退款出现异常情况");
							e3.printStackTrace();
						}
						finally{
							try {   
								String cfg_Refund_receipts_time = "";// 获取系统配置卖家确认收货时间
								cfg_Refund_receipts_time = SysconfigFuc.getSysValue("cfg_Refund_receipts_time");
								// 根据系统配置表取值执行自动官方介入
									AutoIntervention(cfg_Refund_receipts_time);// 执行自动官方介入
									
							} catch (Exception e4) {
								System.err.println("自动官方介入出现异常情况");
								e4.printStackTrace();
							}
						}
					}
				}
		}
		
		
		
	}
	
	private void AutoIntervention(String cfg_Refund_receipts_time){
		//获取所有符合条件的退款数据条数，并分批处理
		 Map cMap = new HashMap();
		 cMap.put("send_time",cfg_Refund_receipts_time);//限制时间
		 int count=RefundappService.getCount(cMap);
		 List clist=BatchListFuc.batchList(count);
			for(int i=0;i<clist.size();i++){
				Map stMap=(Map)clist.get(i);
				Map rfMap=new HashMap();
			    rfMap.put("send_time",cfg_Refund_receipts_time);//限制时间
				rfMap.put("start", 0);
				rfMap.put("limit", SysconfigFuc.getSysValue("cfg_define_row"));
			    //获取所有符合条件的退款数据
			    List refund_idList = RefundappService.getList(rfMap);
				if(refund_idList!=null||refund_idList.size()>0){
					Map map=new HashMap();
					for (int j = 0; j <refund_idList.size(); j++) {
						map=(Map)refund_idList.get(j);
						//取出对象
						String trade_id=map.get("trade_id").toString();
						Refundapp refundapp=this.RefundappService.get(trade_id);
						if(trade_id!=null){
							refundapp.setInfo_state("2");//介入
							this.RefundappService.update(refundapp);
						}
					}
				}
			}
	}
	
	private void AutoCancel(String cfg_Refundsend_goods_time){
		//获取所有符合条件的退款数据条数，并分批处理
		 Map cMap = new HashMap();
		 cMap.put("is_return","1");//需退货
		 cMap.put("seller_state","0");//卖家同意退款
		 cMap.put("consigneeed","1");//已发送地址
		 cMap.put("cfg_Refundsend_goods_time",cfg_Refundsend_goods_time);//限制时间
		 int count=RefundappService.getCount(cMap);
		 List clist=BatchListFuc.batchList(count);
			for(int i=0;i<clist.size();i++){
				Map stMap=(Map)clist.get(i);
				Map rfMap=new HashMap();
				rfMap.put("is_return","1");//需退货
			    rfMap.put("seller_state","0");//卖家同意退款
			    rfMap.put("consigneeed","1");//已发送地址
			    rfMap.put("cfg_Refundsend_goods_time",cfg_Refundsend_goods_time);//限制时间
				rfMap.put("start", 0);
				rfMap.put("limit", SysconfigFuc.getSysValue("cfg_define_row"));
			    //获取所有符合条件的退款数据
			    List refund_idList = RefundappService.getList(rfMap);
				if(refund_idList!=null||refund_idList.size()>0){
					Map map=new HashMap();
					for (int j = 0; j <refund_idList.size(); j++) {
						//取出refundapp 的order_id
						map=(Map)refund_idList.get(j);
						String trade_id=map.get("trade_id").toString();
						Refundapp refundapp=this.RefundappService.get(trade_id);
						String order_id=map.get("order_id").toString();
						Goodsorder goodsorder=this.GoodsorderService.get(order_id);
						if(order_id!=null){
							//获取订单信息
							goodsorder=new Goodsorder();
							goodsorder = this.GoodsorderService.get(order_id);
							goodsorder.setOrder_state("6");//退款失败
							this.GoodsorderService.update(goodsorder);
							refundapp=RefundappService.getByOrderId(order_id);
							refundapp.setRefund_state("5");//退款关闭
							this.RefundappService.update(refundapp);
						}
					}
				}
			}
	}
	private void AutoSend_addr(String cfg_Refundsend_addr_time){
		//获取所有符合条件的退款数据条数，并分批处理
		 Map cMap = new HashMap();
		 cMap.put("is_return","1");//需退货
		 cMap.put("seller_state","0");//卖家同意退款
		 cMap.put("consignee","1");//未发送地址
		 cMap.put("cfg_Refundsend_addr_time",cfg_Refundsend_addr_time);//限制时间
		 int count=RefundappService.getCount(cMap);
		 List clist=BatchListFuc.batchList(count);
			for(int i=0;i<clist.size();i++){
				Map stMap=(Map)clist.get(i);
				Map rfMap=new HashMap();
				rfMap.put("is_return","1");//需退货
			    rfMap.put("seller_state","0");//卖家同意退款
			    rfMap.put("consignee","1");//未发送地址
			    rfMap.put("cfg_Refundsend_addr_time",cfg_Refundsend_addr_time);//限制时间
				rfMap.put("start", 0);
				rfMap.put("limit", SysconfigFuc.getSysValue("cfg_define_row"));
			    //获取所有符合条件的退款数据
			    List refund_idList = RefundappService.getList(rfMap);
				if(refund_idList!=null||refund_idList.size()>0){
					Map map=new HashMap();
					for (int j = 0; j <refund_idList.size(); j++) {
						//取出refundapp 的order_id
						map=(Map)refund_idList.get(j);
						String trade_id=map.get("trade_id").toString();
						Refundapp refundapp=this.RefundappService.get(trade_id);
						String sell_cust_id=refundapp.getSeller_cust_id();
						Shopconfig shopconfig =new Shopconfig();
						shopconfig=this.ShopconfigService.getByCustID(sell_cust_id);
						refundapp.setConsignee(shopconfig.getContant_man());
						refundapp.setMobile(shopconfig.getMobile());
						refundapp.setTelephone(shopconfig.getPhone());
						refundapp.setSell_address(shopconfig.getAddress());
						refundapp.setArea_attr(shopconfig.getArea_attr());
						refundapp.setSell_remark("系统自动发送卖家地址");
						this.RefundappService.update(refundapp);
					}
				}
			}
	}
	
	private void AutoAgree(String refundDealtime){
		
		//获取所有符合条件的退款数据条数，并分批处理
		 Map cMap = new HashMap();
		 cMap.put("refund_state","0");
		 cMap.put("refundDealtime",refundDealtime);
		 int count=RefundappService.getCount(cMap);
		 List clist=BatchListFuc.batchList(count);
			for(int i=0;i<clist.size();i++){
				Map stMap=(Map)clist.get(i);
				Map rfMap=new HashMap();
				rfMap.put("refund_state","0");
				rfMap.put("NoRefund_time",refundDealtime);
				rfMap.put("start", 0);
				rfMap.put("limit", SysconfigFuc.getSysValue("cfg_define_row"));
			    //获取所有符合条件的退款数据
			    List refund_idList = RefundappService.getList(rfMap);
				if(refund_idList!=null||refund_idList.size()>0){
					Map map=new HashMap();
					for (int j = 0; j <refund_idList.size(); j++) {
						//取出refundapp 的order_id
						map=(Map)refund_idList.get(j);
						String order_id=map.get("order_id").toString();
						String trade_id=map.get("trade_id").toString();
						Goodsorder goodsorder=this.GoodsorderService.get(order_id);
						Refundapp refundapp=this.RefundappService.get(trade_id);
						Double refund_money=Double.parseDouble(refundapp.getRefund_amount());
						if(goodsorder!=null){
							//订单总金额
							Double goods_amount=goodsorder.getTatal_amount();
							//应退款给正方
							Double refund_amount=Double.parseDouble(refundapp.getRefund_amount());
							//应退款另一方
							Double other_refund_amount=goods_amount-refund_amount;
							
							//解冻平台资金
							this.sysfundService.freezeNum(goods_amount, 1);
							//退款给买家
							double i1=MemberfundService.outAndInNum(goodsorder.getBuy_cust_id(), refund_amount, 1);
							//退款给卖家
							double i2=MemberfundService.outAndInNum(goodsorder.getSell_cust_id(), other_refund_amount, 1);
							
							//退款成功
							refundapp.setRefund_state("1");
							goodsorder.setOrder_state("5");
							refundapp.setInfo_state("1");
							this.RefundappService.update(refundapp);
							this.GoodsorderService.update(goodsorder);
							
							String sys_user_id="0";
							//获取系统默认管理的user_id,通过读取系统用户的user_type为3的用户
							HashMap sysuserMap=new HashMap();
							sysuserMap.put("user_type", "3");
							List sysuserList=new ArrayList();
							sysuserList=sysuserService.getList(sysuserMap);
							if(sysuserList!=null&&sysuserList.size()>0){
								HashMap susermap=new HashMap();
								susermap=(HashMap)sysuserList.get(0);
								if(susermap!=null&&susermap.get("user_id")!=null){
									sys_user_id=susermap.get("user_id").toString();
								}
							}
							
							//买家的资金异动
							Fundhistory buy_fh = new Fundhistory();
							buy_fh.setBalance(i1);
							buy_fh.setCust_id(goodsorder.getBuy_cust_id());
							buy_fh.setFund_in(refund_amount);
							buy_fh.setReason("买家收到订单号:"+order_id+" 退款"+refund_amount+"元");
							buy_fh.setFund_out(0.0);
							buy_fh.setUser_id(sys_user_id);
							this.FundhistoryService.insert(buy_fh);
							//卖家的资金异动
							Fundhistory sell_fh = new Fundhistory();
							sell_fh.setBalance(i2);
							sell_fh.setCust_id(goodsorder.getSell_cust_id());
							sell_fh.setFund_in(refund_amount);
							sell_fh.setReason("卖家收到订单号:"+order_id+" 退款补偿"+refund_amount+"元");
							sell_fh.setFund_out(0.0);
							sell_fh.setUser_id(sys_user_id);
							this.FundhistoryService.insert(sell_fh);
				   }
				  }
				}
						
		    }
	}
	private void AutoNoRefund( String isNoRefund){
		//获取所有符合条件的退款数据条数，并分批处理
		 Map cMap = new HashMap();
		 cMap.put("refund_state","2");
		 cMap.put("NoRefund_time",isNoRefund);
		 int count=RefundappService.getCount(cMap);
		 List clist=BatchListFuc.batchList(count);
			for(int i=0;i<clist.size();i++){
				Map stMap=(Map)clist.get(i);
				Map rfMap=new HashMap();
				rfMap.put("refund_state","2");
				rfMap.put("NoRefund_time",isNoRefund);
				rfMap.put("start", 0);
				rfMap.put("limit", SysconfigFuc.getSysValue("cfg_define_row"));
			    //获取所有符合条件的退款数据
			    List refund_idList = RefundappService.getList(rfMap);
				if(refund_idList!=null||refund_idList.size()>0){
					Map map=new HashMap();
					for (int j = 0; j <refund_idList.size(); j++) {
						//取出refundapp 的order_id
						map=(Map)refund_idList.get(j);
						String order_id=map.get("order_id").toString();
						String trade_id=map.get("trade_id").toString();
						Goodsorder goodsorder=this.GoodsorderService.get(order_id);
						Refundapp refundapp=this.RefundappService.get(trade_id);
						if(goodsorder!=null){
								     // 卖家
								    Memberfund mmf=new Memberfund();
									mmf=this.MemberfundService.get(goodsorder.getSell_cust_id());
									//解冻平台资金
									this.sysfundService.freezeNum(goodsorder.getTatal_amount(), 1);
									//退款给卖家
									double i2=MemberfundService.outAndInNum(goodsorder.getSell_cust_id(), goodsorder.getTatal_amount(), 1);
									
									refundapp.setInfo_state("1");
									this.RefundappService.update(refundapp);
									System.out.println("卖家当前金额："+"------"+(mmf.getUse_num()+goodsorder.getTatal_amount())+"原本"+mmf.getUse_num()+"退款流水号"+trade_id+"该单总金额"+goodsorder.getTatal_amount());
						
						}
				  }
				}
						
		    }
	} 
	public static void main(String[]args){
		String isNoRefund = "";// 获取系统配置的值
		isNoRefund = SysconfigFuc.getSysValue("cfg_IsNoRefund");
		String refundDealtime = "";// 获取系统配置是卖家处理时间
		refundDealtime = SysconfigFuc.getSysValue("cfg_RefundDealtime");
		new RefundappJob(). AutoNoRefund(isNoRefund);
		new RefundappJob(). AutoAgree(refundDealtime);
		String cfg_Refundsend_addr_time = "";// 获取系统配置是卖家向买家发送收货地址时间
		cfg_Refundsend_addr_time = SysconfigFuc.getSysValue("cfg_Refundsend_addr_time");
		// 根据系统配置表取值执行自动发送卖家店铺地址给买家
		new RefundappJob().AutoSend_addr(cfg_Refundsend_addr_time);// 执行自动发送卖家店铺地址给买家
		String cfg_Refundsend_goods_time = "";// 获取系统配置是买家向卖家发送退货商品时间
		cfg_Refundsend_goods_time = SysconfigFuc.getSysValue("cfg_Refundsend_goods_time");
		// 根据系统配置表取值执行自动取消退款
		new RefundappJob().	AutoCancel(cfg_Refundsend_goods_time);// 执行自动取消退款
		String cfg_Refund_receipts_time = "";// 获取系统配置卖家确认收货时间
		cfg_Refund_receipts_time = SysconfigFuc.getSysValue("cfg_Refund_receipts_time");
		// 根据系统配置表取值执行自动官方介入
		new RefundappJob().	AutoIntervention(cfg_Refund_receipts_time);// 执行自动官方介入
	}

}
