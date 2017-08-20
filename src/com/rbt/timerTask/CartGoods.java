package com.rbt.timerTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.rbt.function.CreateSpringContext;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Cartgoods;
import com.rbt.model.Goods;
import com.rbt.service.ICartgoodsService;
/**
 * @Method Description :定时自动上下架商品
 * @author : HZX
 * @date : Apr 8, 2014 10:31:52 AM
 */
public class CartGoods extends CreateSpringContext implements Job {
   //商品对象
	public Cartgoods cartgoods;
	//获取对象接口
    ICartgoodsService cartGoodsService=(ICartgoodsService)getContext().getBean("cartgoodsService");
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		//获取清除天数
		int datenum = 30;
		String datestr = SysconfigFuc.getSysValue("cfg_catgoodsdate");
		//判断datenum是否为数字
		boolean isNum = datestr.matches("[0-9]+"); 
		if(isNum){
			datenum = Integer.parseInt(datestr);
			
			//获取当天时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String nowdate = df.format(new Date());
			//获取购物车列表
			HashMap map = new HashMap();
			List cartgoodsList = cartGoodsService.getList(map);
			if(cartgoodsList!=null&&cartgoodsList.size()>0){
				for(int i=0;i<cartgoodsList.size();i++){
					HashMap  valuemap = new HashMap();
					valuemap = (HashMap)cartgoodsList.get(i);
					String cartdate = valuemap.get("in_date").toString();
					//获取购物车时间与今天进行比对
					int contrast;
					try {
						contrast = daysBetween(nowdate,cartdate);
						if(contrast>datenum){
							String trade_id = valuemap.get("trade_id").toString();
							cartGoodsService.delete(trade_id);
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//符合条件的对购物车进行删除
				
				}
			}
		}
		
		
		
		
	}
	
	/**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
	public static int daysBetween(String nowdate,String cartdate) throws ParseException{  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(sdf.parse(nowdate));    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(sdf.parse(cartdate));    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time1-time2)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));     
    }  
	
	

}
