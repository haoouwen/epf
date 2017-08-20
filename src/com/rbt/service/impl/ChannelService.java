/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: ChannelService.java 
 */
package com.rbt.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.common.util.ValidateUtil;
import com.rbt.createHtml.DoHtml;
import com.rbt.dao.IChannelDao;
import com.rbt.function.CreateSpringContext;
import com.rbt.model.Channel;
import com.rbt.service.IChannelService;

/**
 * @function 功能 记录网站栏目信息Service层业务接口实现
 * @author 创建人 HXK
 * @date 创建日期 Mon Aug 15 10:57:10 CST 2014
 */
@Service
public class ChannelService extends GenericService<Channel,String>  implements IChannelService {

	/*
	 * 记录网站栏目信息Dao层接口
	 */
	
	 IChannelDao channelDao;

	@Autowired
	public ChannelService(IChannelDao channelDao) {
		super(channelDao);
		this.channelDao = channelDao;
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.IChannelService#getChannelByType(java.lang.String)
	 */
	public Channel getChannelByType(String type) {
		return this.channelDao.getChannelByType(type);
	}

	public List getDeleteList(Map map) {
		return this.channelDao.getDeleteList(map);
	}
	/**
	 * @author : LHY
	 * @Method Description : 递归调用删除数据
	 */
	public void recuDelete(String id){
		if(id==null || id.equals(""))
			return ;
		String chhid_id="";
		List list=new ArrayList();
		String[] str_id=id.split(",");
		for (int i = 0; i < str_id.length; i++) {
			if(str_id[i].trim().equals("")){
				return;
			}
			Map chiMap=new HashMap();
			chiMap.put("up_ch_id",str_id[i]);
			//获取所有上级id为当前id的栏目
			List chi_list=this.channelDao.getDeleteList(chiMap);
			System.out.println(chi_list);
			if(chi_list!=null && chi_list.size()>0){
				Map listMap=null;
				for (int j = 0; j < chi_list.size(); j++) {
					listMap=(HashMap) chi_list.get(j);
					if(listMap.get("ch_id")!=null){
						chhid_id+=listMap.get("ch_id")+",";
					}
				}
			}
		}
		//判断是否最后一个字符是否为逗号，是则删除
		if(chhid_id.lastIndexOf(",")>0){
			chhid_id=chhid_id.substring(0, chhid_id.lastIndexOf(","));
		}
		//删除ID
		this.channelDao.delete(id);
		recuDelete(chhid_id);
	}
	
	/**
	 * @author：XBY
	 * @date：Feb 12, 2014 2:24:45 PM
	 * @Method Description： 用于判断是否出现相同的栏目名称
	 */
	public int getChanneName(String name, String id) throws Exception {
		Map map = new HashMap();
		map.put("ch_name", name);
		int count = 0;
		if (id != null && !id.equals("0")) {
			Channel chans = new Channel();
			chans = channelDao.get(id);
			if (name.equals(chans.getCh_name())) {
				count = 0;
			} else {
				count = channelDao.getCount(map);
			}
		} else {
			count = channelDao.getCount(map);
		}
		return count;
	}
	
	/**
	 * @author：XBY
	 * @date：Feb 12, 2014 2:50:48 PM
	 * @Method Description：将List的值，转换为对象的值
	 */
	public Channel switchChannel(HashMap myMap)
	{
		 Channel myChannel=new Channel();
		 myChannel.setArticle_rule(myMap.get("article_rule").toString());
		 myChannel.setArticle_temp(myMap.get("article_temp").toString());
		 myChannel.setCh_id(myMap .get("ch_id").toString());
		 myChannel.setCh_level(myMap.get("ch_level").toString());
		 myChannel.setCh_name(myMap.get("ch_name").toString());
		 myChannel.setCh_title(myMap.get("ch_title").toString());
		 myChannel.setFile_name(myMap.get("file_name").toString());
		 myChannel.setMeta_desc(myMap.get("meta_desc").toString());
		 myChannel.setMeta_keyword(myMap.get("meta_keyword").toString());
		 myChannel.setModule_type(myMap.get("module_type").toString());
		 myChannel.setRemark(myMap.get("remark").toString());
		 myChannel.setSave_dir(myMap.get("save_dir").toString());
		 myChannel.setSort_no(myMap.get("sort_no").toString());
		 myChannel.setTemp_path(myMap.get("temp_path").toString());
		 myChannel.setUp_ch_id(myMap.get("up_ch_id").toString());
		 return myChannel;
	}
	
	//type不为空，则递归更新子栏目
	public static void doArticleHtml(String type,String ch_id){	
		DoHtml doHtml = new DoHtml();
		HashMap map = new HashMap();
		map.put("ch_id", ch_id);
		List chList = getChannelList(map);
		if(chList!=null && chList.size()>0){
			HashMap chMap = new HashMap();
			for(Iterator it = chList.iterator();it.hasNext();){
				chMap = (HashMap)it.next();
				doHtml.doArticleHtml(chMap);
			}
			//以下为递归更新
			if(type.equals("1")){
				List downList = getDownChList(ch_id);
				if(downList!=null && downList.size()>0){
					HashMap downMap = new HashMap();
					HashMap downChMap = new HashMap();
					for(Iterator its = downList.iterator();its.hasNext();){
						downMap = (HashMap)its.next();
						String ch_id_down = "";
						if(downMap.get("ch_id")!=null){
							ch_id_down = downMap.get("ch_id").toString();
							downChMap.put("ch_id", ch_id_down);
							doArticleHtml(ch_id_down,type);
						}
						
					}
				}
			}
		}
	}
	
	//更新详细页
	//ch_id all：更新所有
	//type 1：递归更新下级栏目详细页
	public  String updateArticleHtml(String type,String ch_id,DoHtml doHtml) throws IOException{
        String outString="";
		if(type.equals("all")){
			List chList = getChannelList(new HashMap());
			if(chList!=null && chList.size()>0){
				HashMap chMap = new HashMap();
				for(Iterator it = chList.iterator();it.hasNext();){
					chMap = (HashMap)it.next();
					doHtml.doArticleHtml(chMap);
				}
			}
			outString="1";
		}else{
			doArticleHtml(type,ch_id);
			outString="1";
		}
		return outString;
	}
	
	
	//更新前台栏目页面
	//type：更新类型 all：更新全部栏目，one：更新当前栏目首页，many：更新下级栏目
	//ch_id：栏目标识 type=all时，此值可为空
	//1：表示更新成功！0：更新失败
	public String updateHtmlPage(String ch_id,String is_pc_webapp) throws IOException{
		String outString="";
		//更新栏目
         if(!ValidateUtil.isRequired(ch_id) && !ValidateUtil.isRequired(is_pc_webapp)){
			HashMap chMap = new HashMap();
			chMap.put("ch_id", ch_id);
			chMap.put("is_webapp", is_pc_webapp);
			doHtml(chMap,"1");
			outString="1";
		}else {
			outString="0";//更新失败
		}
		return outString;
	}
	
	public static void doHtml(HashMap map){
		doHtml(map,"");
	}
	
	//type不为空，则递归更新子栏目
	public static void doHtml(HashMap map,String type){
		
		DoHtml doHtml = new DoHtml();
		List chList = getChannelList(map);
		if(chList!=null && chList.size()>0){
			HashMap chMap = new HashMap();
			for(Iterator it = chList.iterator();it.hasNext();){
				chMap = (HashMap)it.next();
				doHtml.doIndexHtml(chMap);
			}
			//以下为递归更新
			if(type.equals("1")){
				String ch_id  = "";
				if(map.get("ch_id")!=null) ch_id = map.get("ch_id").toString();
				List downList = getDownChList(ch_id);
				if(downList!=null && downList.size()>0){
					Map downMap = new HashMap();
					HashMap downChMap = new HashMap();
					for(Iterator its = downList.iterator();its.hasNext();){
						downMap = (HashMap)its.next();
						String ch_id_down = "";
						if(downMap.get("ch_id")!=null){
							ch_id_down = downMap.get("ch_id").toString();
							downChMap.put("ch_id", ch_id_down);
							doHtml(downChMap,type);
						}
						
					}
				}
			}
		}
	}
	
	/**
	 * 方法描述：AJAX更新栏目信息的方法
	 * 
	 * @throws Exception
	 */
	public String updateChannelPage(String update_state,String strall,String strtwo,String update_type,Channel	channel,String id,String strone) throws Exception {
		String preview="";//栏目跟新成功添加预览
		String failString="";// 用于存储失败的栏目名称，用于提示；
		String flagString = "";// 1:表示完全成功，0：表示更新失败；2：部分失败！
		String outString="";//返回前台页面提示的字符串；
		List mychannelList = new ArrayList();
		Map pageMap = new HashMap();
			if (update_state.equals(strall)||update_state .equals(strtwo))//如果请求为：strall:更新全部页，strtwo为请求更新子栏目页
			{	
				if(update_state.equals(strtwo))//strtwo:为请求更新子栏目页。
				{
					pageMap.put("up_ch_id", id);
				}
				mychannelList = this.channelDao.getList(pageMap);
				if (mychannelList != null && mychannelList.size() != 0){
					HashMap myMap=new HashMap();
					for(int i=0;i<mychannelList.size()-1;i++)
					{
						myMap=(HashMap)mychannelList.get(i);
						try {
							 chanvalue(switchChannel(myMap),update_type);//执行更新的动作
							 
						} catch (Exception e) {
							 failString+=myMap.get("ch_name").toString()+",";//获取更新失败的栏目名称通过","累加
							 flagString="2"; //flagString=2：表示为更新为完全成功！
						} 
					}
					if(!flagString.equals("2")){
						flagString="1";//更新全部成功的标识串
						
					}
				}
				else {
						flagString = "0";//更新失败的标识串
						channel = this.channelDao.get(id);
						preview="<a href='/"+channel.getSave_dir()+channel.getFile_name()+"'>预览</a>";
				}
	        }
			// strone:当选择更行本栏目
		    if (update_state.equals(strone)||update_state .equals(strtwo))
			{
				try {
						channel = this.channelDao.get(id);
					chanvalue(channel,update_type);//执行更行动作的判断
					flagString=flagstrs(flagString);

				} catch (Exception e) {
					//出现异常的时候，先去判断请求是否为更新子栏目页，和是否在更新子栏目有中有没有出现栏目更新的失败的；
					if(update_state .equals(strtwo)&&!failString.equals(""))
					{
						flagString = "2";//2：表示更新未全部成功，也就是说，有部分栏目更新失败！
						failString+=channel.getCh_name();
					}
					else {
						flagString = "0";
					}
				}
     		} 
		 outString=outputString(flagString,failString,preview);//用于处理返回的方法
		 return outString;
	}
	
    /**
     *执行更行动作的判断
     * @param mchannel
     * @param type
     */
	public void chanvalue(Channel mchannel,String type)
    {
		 DoHtml pHtml = new DoHtml();
		 if(type.equals("1"))//判断请求的动作是：更新栏目页，还是更新详细栏目页；1为：更新栏目页；0：更新详细栏目
		 {
		   //pHtml.doArticleHtml(mchannel);TODO
		 }
		 else {
			 //pHtml.doIndexHtml(mchannel);TODO
		}
    }
    /**
     * 判断状态标识
     * @param flagString
     * @return
     */
	public String flagstrs(String flagString)
	{
		String retFlages="";
		if(flagString.equals("2"))
		{
			retFlages="2";
		}
		else {
			retFlages = "1";						
		}
		return retFlages;
	}
	/**
	 * 用于处理返回值的方法flagStr：标识字符串1:表示完全成功，0：表示更新失败；2：部分失败！；failStr：错误提示字符串
	 * @param flagStr
	 * @param failStr
	 * @return
	 */
   public String outputString( String flagStr,String failStr,String preview)
   {
	   String outString="";
		if(flagStr.equals("2"))
		{
			outString=flagStr+"@"+failStr;//与"@"隔开的字符串的格式为"2@首页,资讯..."
			int len=outString.length();
			outString=outString.substring(0, len-1);
		}
		else 
		{
			outString=flagStr+"@"+preview;
		}
	   return outString;
   }
	//获取栏目信息List
	public static List getChannelList(HashMap chMap){
		IChannelService channel_Service = (IChannelService)CreateSpringContext.getContext().getBean("channelService");
		return channel_Service.getList(chMap);
	}
	//根据栏目标识找出下级栏目
	public static List getDownChList(String ch_id){
		HashMap chMap = new HashMap();
		chMap.put("up_ch_id", ch_id);
		return getChannelList(chMap);
	}
	//通过模型类型module_type找出栏目标识ch_id
	public static String getChidByModuletype(String module_type){
		HashMap map = new HashMap();
		map.put("moduletype", module_type);
		List list = getChannelList(map);
		String ch_id = "";
		if(list!=null && list.size()>0){
			Map cMap = (HashMap)list.get(0);
			if(cMap.get("ch_id")!=null) ch_id = cMap.get("ch_id").toString();
		}
		return ch_id;
	}
}

