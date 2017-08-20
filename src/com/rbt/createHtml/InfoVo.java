/*
 
 * Package:com.rbt.createHtml
 * FileName: InfoVo.java
 */
package com.rbt.createHtml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.common.util.DbUtil;

/**
 * @function 功能 从数据库统一取数据
 * @author  创建人 HXK
 * @date  创建日期  2014-08-12
*/
public class InfoVo {
	private DbUtil jdbcDbm;
	
	public InfoVo(){
		jdbcDbm = new DbUtil();
	}
	
	//根据sql和map参数找出list信息
	public List getInfoList(String sql,Map map){

		int start = 0,row = 0;
		if( map != null && map.get("start") != null && !map.get("start").toString().equals("") ){
			start = Integer.parseInt( map.get("start").toString() );
		}
		if( map != null && map.get("row") != null && !map.get("row").toString().equals("") ){
			row = Integer.parseInt( map.get("row").toString() );
		}
		if( row != 0 ){
			sql = sql + " limit " + start + "," + row;
		}
		System.out.print(sql);
		return jdbcDbm.queryList(sql);
		
	}
	
	//找出一条信息
	public HashMap getInfoMap(String sql){
		List list = getInfoList(sql,null);
		if(list!=null && list.size()>0){
			return (HashMap)list.get(0);
		}else{
			return null;
		}
	}
	
	//执行删除条件语句
	public void excuteSql(String sql){
		jdbcDbm.excuteSql(sql);
	}

}
