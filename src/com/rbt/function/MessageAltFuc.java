package com.rbt.function;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpSession;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.message.LetterInter;
import com.rbt.message.MailInter;
import com.rbt.message.RtxmsgInter;
import com.rbt.model.Commutemplate;
import com.rbt.model.Goodsorder;
import com.rbt.model.Memberuser;
import com.rbt.model.Messagealert;
import com.rbt.service.ICommutemplateService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IMessagealertService;

/**
 * @author : HXK
 * @date Feb 14, 2014 4:39:19 PM
 * @Method Description :统一信息提醒处理
 */
public class MessageAltFuc extends CreateSpringContext{
	
	public Messagealert messagealert;
	
	/**
	 * @author : HXK
	 * @Method Description :自动发送短信、站内信、邮件 msg_code：模版编号 rec_cust_id：接收人的ID
	 */
	public void messageAutoSend(String msg_code, String rec_cust_id)throws UnsupportedEncodingException {
		messageAutoSend(msg_code,rec_cust_id,null,"0","");
	}
	/**
	 * @author : CYC
	 * @Method Description :自动发送短信、站内信、邮件 msg_code：模版编号 rec_cust_id：接收人的ID  mgoods:订单对象
	 */
	public void messageAutoSend(String msg_code, String rec_cust_id,Goodsorder morder)throws UnsupportedEncodingException {
		messageAutoSend(msg_code,rec_cust_id,morder,"0","");
	}
	
	/**
	 * @author : HXK
	 * @Method Description :自动发送短信、站内信、邮件 msg_code：模版编号 rec_cust_id：接收人的ID  mgoods:订单对象
	 */
	public void messageAutoSend(String msg_code, String rec_cust_id,Goodsorder morder,String sum,String passwd)throws UnsupportedEncodingException {
		IMemberuserService memberuserService = (IMemberuserService)CreateSpringContext.getContext().getBean("memberuserService");
		IMessagealertService messagealertService = (IMessagealertService)CreateSpringContext.getContext().getBean("messagealertService");
		ICommutemplateService commutemplateService = (ICommutemplateService)CreateSpringContext.getContext().getBean("commutemplateService");
		
		// 站内信
		LetterInter letterInter = new LetterInter();
		// 邮件
		MailInter mailInter = new MailInter();
		// 短信
		RtxmsgInter rtxmsgInter = new RtxmsgInter();
		//卖家
		Memberuser sendMemUser = new Memberuser();
		//买家
		Memberuser recMemUser = new Memberuser();
		
		// 获取信息模版对象
		Commutemplate commutemplate = new Commutemplate();
		
		//获取模版内容(买家/卖家)
		String temp_content="";
		String temp_contentTwo="";
		// 获取接收人信息
		if(morder!=null){
			//判断rec_cust_id传值过来的是买家还是卖家
			sendMemUser = memberuserService.getPKByCustID("0");
			recMemUser = memberuserService.getPKByCustID(morder.getBuy_cust_id());
		}else{
			recMemUser = memberuserService.getPKByCustID(rec_cust_id);
		}
		if(!ValidateUtil.isRequired(passwd)){
			recMemUser.setPasswd(passwd);
		}
		if (msg_code != null && !"".equals(msg_code)) {
			// 获取模版提示信息
			messagealert = messagealertService.get(msg_code);
			if (messagealert != null) {
				/** 发送邮件**/
				if (messagealert.getIs_send_email() != null&& messagealert.getIs_send_email().equals("0")) {
					// 获取邮件详细模版信息
					commutemplate = commutemplateService
							.getByTempcode(messagealert.getEmail_code());
					//获取邮件模版内容(买家)
					if(commutemplate!=null&&commutemplate.getTemp_con()!=null&&!commutemplate.getTemp_con().equals("")){
						temp_content=commutemplate.getTemp_con();
						//替换模版的参数内容
						temp_content=replaceMessageContent(temp_content,morder,recMemUser,sum);
					}
					
					//获取邮件模版内容(卖家)
					if(commutemplate!=null&&commutemplate.getTemp_conTwo()!=null&&!commutemplate.getTemp_conTwo().equals("")){
						temp_contentTwo=commutemplate.getTemp_conTwo();
						//替换模版的参数内容
						temp_contentTwo=replaceMessageContent(temp_contentTwo,morder,sendMemUser,sum);
					}
					//1：买家,2：卖家,3：买家/卖家(这里1,2,3都是代表接收方)
					String flagSendWho=commutemplate.getSend_who();
					// 发送邮件
					if(ValidateUtil.isRequired(flagSendWho) && !ValidateUtil.isRequired(recMemUser.getEmail())){//兼容之前单一的发送
						mailInter.sendMail(commutemplate.getTemp_name(),temp_content,recMemUser.getEmail(),"html",messagealert.getEmail_code(),morder);
					}else{
						if("1".equals(flagSendWho)){
							mailInter.sendMail(commutemplate.getTemp_name(),temp_content,recMemUser.getEmail(),"html",messagealert.getEmail_code(),morder);
						}else if("2".equals(flagSendWho)&&sendMemUser!=null){
							if("0".equals(sendMemUser.getCust_id())){//运营商直接插入数据库不发送邮件
								mailInter.insertEmailHistory(commutemplate.getTemp_name(), temp_contentTwo, sendMemUser.getEmail(), messagealert.getEmail_code(),morder);
							}else{
								mailInter.sendMail(commutemplate.getTemp_name(),temp_contentTwo,sendMemUser.getEmail(),"html",messagealert.getEmail_code(),morder);
							}
						}else if("3".equals(flagSendWho)){
							if("0".equals(sendMemUser.getCust_id())){//运营商直接插入数据库不发送邮件
								mailInter.insertEmailHistory(commutemplate.getTemp_name(), temp_contentTwo, sendMemUser.getEmail(), messagealert.getEmail_code(),morder);
							}else{
								mailInter.sendMail(commutemplate.getTemp_name(),temp_contentTwo,sendMemUser.getEmail(),"html",messagealert.getEmail_code(),morder);
							}
							mailInter.sendMail(commutemplate.getTemp_name(),temp_content,recMemUser.getEmail(),"html",messagealert.getEmail_code(),morder);
						}
					}	
				}
				
				/** 发送短信**/
				if (messagealert.getIs_send_mobile() != null&& messagealert.getIs_send_mobile().equals("0")) {
					// 获取短息详细模版信息
					commutemplate = commutemplateService.getByTempcode(messagealert.getMobile_code());
					//获取短信模版内容(买家)
					if(commutemplate!=null&&commutemplate.getTemp_con()!=null&&!commutemplate.getTemp_con().equals("")){
						temp_content=commutemplate.getTemp_con();
						//替换模版的参数内容
						temp_content=replaceMessageContent(temp_content,morder,recMemUser,sum);
						//替换HTML代码
						temp_content=ToolsFuc.getChinese(temp_content);
					}
					
					//获取短信模版内容(卖家)
					if(commutemplate!=null&&commutemplate.getTemp_conTwo()!=null&&!commutemplate.getTemp_conTwo().equals("")){
						temp_contentTwo=commutemplate.getTemp_conTwo();
						//替换模版的参数内容
						temp_contentTwo=replaceMessageContent(temp_contentTwo,morder,sendMemUser,sum);
						//替换HTML代码
						temp_contentTwo=ToolsFuc.getChinese(temp_contentTwo);
					}
					String buy_user_tel="";
					if(morder!=null){
						buy_user_tel=morder.getMobile();
					}else{
						buy_user_tel=recMemUser.getCellphone();
					}
					//1：买家,2：卖家,3：买家/卖家(这里1,2,3都是代表接收方)
					String flagSendWho=commutemplate.getSend_who();
					System.out.println("=1==flagSendWho="+flagSendWho+"=========="+buy_user_tel+"================");
					// 发送短信的方法
					if(ValidateUtil.isRequired(flagSendWho) && !ValidateUtil.isRequired(buy_user_tel)){//兼容之前单一的发送
						rtxmsgInter.mt(buy_user_tel,commutemplate.getTemp_name(), temp_content, "", "", "");
					}else{
						if("1".equals(flagSendWho)){
							rtxmsgInter.mt(buy_user_tel,commutemplate.getTemp_name(), temp_content, "", "", "");
						}else if("2".equals(flagSendWho)&&sendMemUser!=null){
							if("0".equals(sendMemUser.getCust_id())){//运营商直接插入数据库不发送短信
								rtxmsgInter.insertSNSlHistory(commutemplate.getTemp_name(), temp_contentTwo, sendMemUser.getCellphone(), "");
							}else{
								rtxmsgInter.mt(sendMemUser.getCellphone(),commutemplate.getTemp_name(), temp_contentTwo, "", "", "");
							}
						}else if("3".equals(flagSendWho)){
							if("0".equals(sendMemUser.getCust_id())){//运营商直接插入数据库不发送短信
								mailInter.insertEmailHistory(commutemplate.getTemp_name(), temp_contentTwo, sendMemUser.getEmail(), messagealert.getEmail_code(),morder);
							}else{
								rtxmsgInter.mt(sendMemUser.getCellphone(),commutemplate.getTemp_name(), temp_contentTwo, "", "", "");
								
							}
							System.out.println("=2==flagSendWho="+flagSendWho+"=========="+buy_user_tel+"================");
							rtxmsgInter.mt(buy_user_tel,commutemplate.getTemp_name(), temp_content, "", "", "");
						}
					}
				}
				
				/** 发送站内信**/
				if (messagealert.getIs_send_letter() != null&& messagealert.getIs_send_letter().equals("0")) {
					// 获取站内行详细模版信息
					commutemplate = commutemplateService.getByTempcode(messagealert.getLetter_code());
					//获取站内信模版内容(买家)
					if(commutemplate!=null&&commutemplate.getTemp_con()!=null&&!commutemplate.getTemp_con().equals("")){
						temp_content=commutemplate.getTemp_con();
						//替换模版的参数内容
						temp_content=replaceMessageContent(temp_content,morder,recMemUser,sum);
					}
					//获取站内信模版内容(卖家)
					if(commutemplate!=null&&commutemplate.getTemp_conTwo()!=null&&!commutemplate.getTemp_conTwo().equals("")){
						temp_contentTwo=commutemplate.getTemp_conTwo();
						//替换模版的参数内容
						temp_contentTwo=replaceMessageContent(temp_contentTwo,morder,sendMemUser,sum);
					}
					
					
					//1：买家,2：卖家,3：买家/卖家(这里1,2,3都是代表接收方)
					String flagSendWho=commutemplate.getSend_who();
					// 发送站内信的方法
					if(ValidateUtil.isRequired(flagSendWho)){//兼容之前单一的发送
						// 发送站内信的方法
						if(morder!=null){
							LetterInter.sendLetter(morder.getSell_cust_id(), rec_cust_id,commutemplate.getTemp_name(),temp_content);
						}else{
							// session中的值
							String session_cust_id = this.getSessionFieldValue(Constants.CUST_ID);
							LetterInter.sendLetter(session_cust_id, rec_cust_id,commutemplate.getTemp_name(),temp_content);
						}
					}else{
						if("1".equals(flagSendWho)){
							if(morder!=null){
								LetterInter.sendLetter(morder.getSell_cust_id(), rec_cust_id,commutemplate.getTemp_name(),temp_content);
							}else{
								// session中的值
								String session_cust_id = this.getSessionFieldValue(Constants.CUST_ID);
								LetterInter.sendLetter(session_cust_id, rec_cust_id,commutemplate.getTemp_name(),temp_content);
							}
						}else if("2".equals(flagSendWho)&&sendMemUser!=null){
							if(morder!=null){
								LetterInter.sendLetter(rec_cust_id, morder.getSell_cust_id(),commutemplate.getTemp_name(),temp_contentTwo);
							}
						}else if("3".equals(flagSendWho)){
							//这里虽然要发送给双方的，但是如果一方没有编号则只发送给一方
							if(morder!=null){
								LetterInter.sendLetter(rec_cust_id, morder.getSell_cust_id(),commutemplate.getTemp_name(),temp_contentTwo);
								LetterInter.sendLetter(morder.getSell_cust_id(), rec_cust_id,commutemplate.getTemp_name(),temp_content);
							}else{
								// session中的值
								String session_cust_id = this.getSessionFieldValue(Constants.CUST_ID);
								LetterInter.sendLetter(session_cust_id, rec_cust_id,commutemplate.getTemp_name(),temp_content);
							}
						}
					}
					
					
				}
			}
		}
	}
	/**
	 * @author : HXK
	 * @param :temp_content：为替换前的模版内容，mgoods:商品对象  muser：接受人对象
	 * @Method Description :获取替换信息提醒模版参数 
	 */
    public String replaceMessageContent(String temp_content,Goodsorder morder,Memberuser muser){
    	return replaceMessageContent(temp_content,morder,muser,"0");
    }
	
	/**
	 * @author : HXK
	 * @param :temp_content：为替换前的模版内容，mgoods:商品对象  muser：接受人对象
	 * @Method Description :获取替换信息提醒模版参数 
	 */
    public String replaceMessageContent(String temp_content,Goodsorder morder,Memberuser muser,String sum){
    	
    	String temp_order_id="${order_id}",temp_totalmomey="${totalmomey}",temp_username="${username}",
    		temp_birthday="${birthday}",temp_mark_id="${mark_id}",temp_passwd="${passwd}";
    	//订单对象
    	if(morder!=null){
    		String order_id=morder.getOrder_id();
        	//替换订单号
        	if(temp_content.contains(temp_order_id)){
        		temp_content=temp_content.replace(temp_order_id, order_id);
        	}
        	//替换订单总额
        	if(temp_content.contains(temp_totalmomey)){
        		if("0".equals(sum)){
        			temp_content=temp_content.replace(temp_totalmomey, morder.getTatal_amount().toString());
        		}else{
        			temp_content=temp_content.replace(temp_totalmomey,sum);
        			
        		}
        		
        	}
        	//替换虚拟商品随机码
        	if(temp_content.contains(temp_mark_id)){
        		if(morder.getMark_id()!=null){
        			temp_content=temp_content.replace(temp_mark_id, morder.getMark_id().toString());
        		}else{
        			temp_content=temp_content.replace(temp_mark_id, "");
        		}
        	}
        	//自定义扩展替换标签
    	}
    	//会员对象
    	if(muser!=null){
    		//替换用户名
        	if(temp_content.contains(temp_username)){
        		temp_content=temp_content.replace(temp_username,muser.getUser_name());
        	}
        	//替换生日
        	if(temp_content.contains(temp_birthday)){
        		temp_content=temp_content.replace(temp_birthday,muser.getBirthday());
        	}
        	//替换密码
        	if(temp_content.contains(temp_passwd)){
        		temp_content=temp_content.replace(temp_passwd, muser.getPasswd());
        	}
    	}
    	
    	return temp_content;
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
	
}