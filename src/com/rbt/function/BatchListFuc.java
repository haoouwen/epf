package com.rbt.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BatchListFuc {
	
	/**
	 * @author : LJQ
	 * @date : Mar 12, 2014 2:30:35 PM
	 * @Method Description :对大容量数据进行分批处理
	 * @param  count:符合条件的总条数
	 */
	public static List batchList(int count){	
		int define_row = Integer.parseInt(SysconfigFuc.getSysValue("cfg_define_row"));
		return batchListORG(count,define_row);
	}
	/**
	 * @author : LJQ
	 * @date : Mar 12, 2014 2:30:35 PM
	 * @Method Description :对大容量数据进行分批处理-跨境通使用
	 * @param  count:符合条件的总条数
	 */
	public static List batchListKJT(int count){	
		int define_row = Integer.parseInt(SysconfigFuc.getSysValue("cfg_define_row_kjt"));
		return batchListORG(count,define_row);
	}
	/**
	 * @author : LJQ
	 * @date : Mar 12, 2014 2:30:35 PM
	 * @Method Description :对大容量数据进行分批处理
	 * @param  count:符合条件的总条数
	 */
	public static List batchListORG(int count,int define_row){	
		//拼成list
		List list = new ArrayList();
		if(count<=define_row){
			HashMap listMap =new HashMap();
			listMap.put("start", 0);
			listMap.put("limit", count);
			list.add(listMap);
		}else{
			int proportion = count/define_row+1;
			for(int i=0;i<proportion;i++){
				HashMap listMap =new HashMap();
				int start=0;
				start = i * define_row;
				listMap.put("start", start);
				listMap.put("limit", define_row);
				list.add(listMap);
			}
		}
		return list;
	}
}
