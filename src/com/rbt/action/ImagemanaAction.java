/*
 
 * Package:com.rbt.action
 * FileName: ImagemanaAction.java 
 */
package com.rbt.action;

import java.io.File;
import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.common.Constants;
import com.rbt.common.util.FileUtil;
import com.rbt.common.util.PropertiesUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Imagemana;
import com.rbt.service.IImagemanaService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录图片管理表信息action类
 * @author 创建人 HZX
 * @date 创建日期 Thu Dec 27 11:11:10 CST 2014
 */
@Controller
public class ImagemanaAction extends AdminBaseAction implements Preparable {
	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	private Imagemana imagemana;

	/*******************业务层接口****************/
	@Autowired
	private IImagemanaService imagemanaService;

	/*********************集合******************/
	public List imagemanaList;//记录图片管理表信息信息集合

	/*********************字段******************/
	private String cust_name_s;//会员名称
	public String cust_name;//会员名称
	public String reImagepath;//更新时的图片路径
	public String lazyPic;//延时加载默认图片

	/**
	 * 方法描述：返回新增记录图片管理表信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录图片管理表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(imagemana);
		if (super.ifvalidatepass) {
			return add();
		}
		imagemana.setCust_id(this.session_cust_id);
		imagemana.setUser_id(this.session_user_id);
		imagemana.setType("0");
		this.imagemanaService.insert(imagemana);
		this.addActionMessage("新增记录图片管理表信息成功！");
		this.imagemana = null;
		return INPUT;
	}

	/**
	 * @author LHY 保存上传图片的路径
	 */
	public void saveImage() {
		String imag = this.getRequest().getParameter("imgPath");
		if (imag != null && !imag.equals("")) {
			imagemana.setImg_path(imag);
			this.imagemana.setCust_id(this.session_cust_id);
			this.imagemana.setUser_id(this.session_user_id);
			this.imagemana.setType("0");
		} else {
			imagemana = new Imagemana();
		}
		this.imagemanaService.insert(imagemana);
	}

	/**
	 * 方法描述：修改记录图片管理表信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String note=imagemana.getNote();
		if(ValidateUtil.isRequired(reImagepath)){
			this.addFieldError("reImagepath", "您还没上传图片！");
		}
		String id=this.imagemana.getSerial_id();
		boolean isDel=false;
		id = id.replace(" ", "");
		imagemana = this.imagemanaService.get(id);
		if (imagemana == null || imagemana.equals("")) {
			return list();
		}
		String img_path = this.imagemana.getImg_path();
		if (img_path == null && img_path.equals("") && img_path.length() <= 0) {
			return list();
		}
		String root_path = PropertiesUtil.getRootpath();
		if (root_path != null && !root_path.equals("")
				&& root_path.length() > 0) {
			FileUtil fileUtil=new FileUtil();
		    isDel=fileUtil.delFile(root_path + img_path);
		}
		if (super.ifvalidatepass) {
			return view();
		}
        if(isDel){
        	imagemana.setImg_path(reImagepath);
        	if(note!=null&&!note.equals("")){
        		imagemana.setNote(note);
        	}
        	this.imagemanaService.update(imagemana);
        	Map delMap=new HashMap();
        	delMap.put("cust_id", this.session_cust_id);
        	delMap.put("img_path", reImagepath);
        	List list =this.imagemanaService.getList(delMap);
        	if(list!=null&&list.size()>0){
        		Map dMap=(Map)list.get(1);
        		this.imagemanaService.delete(dMap.get("serial_id").toString());
        	}
    		this.addActionMessage("修改图片管理成功！");
        }else{
        	this.addActionMessage("修改图片管理失败！");
        }
		return list();
	}

	/**
	 * 方法描述：单个删除图片管理表信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id=imagemana.getSerial_id();
		if(id==null){
			return list();
		}
			dealDelImage(id);
		return list();
	}
	
	private void dealDelImage(String id){
		boolean isDel=false;
		imagemana = this.imagemanaService.get(id);
		if (imagemana == null || imagemana.equals("")) {
			return;
		}
		String img_path = this.imagemana.getImg_path();
		if (img_path == null && img_path.equals("") && img_path.length() <= 0) {
			return;
		}
		//获取根路径
		String root_path = PropertiesUtil.getRootpath();
		if (root_path != null && !root_path.equals("")
				&& root_path.length() > 0) {
			root_path = root_path.substring(0, root_path.length() - 1);
			FileUtil fileUtil=new FileUtil();
		    isDel=fileUtil.delFile(root_path + img_path);
		}
		if(isDel){
			//删除数据库记录
			this.imagemanaService.delete(id);
			this.addActionMessage("单个删除图片管理成功！");
		}else{
			this.addActionMessage("单个删除图片管理失败！");
		}
		
	}
	
	
	

	/**
	 * @author LSQ 批量删除图片
	 */
	public String alldelete() throws Exception {
		String sigel_id = "";
		if(chb_id==null){
			return list();
		}
		String[] ids = chb_id.split(",");
		for (int i = 0; i < ids.length; i++) {
			sigel_id = ids[i];
			imagemana = this.imagemanaService.get(sigel_id);
			if (imagemana == null || imagemana.equals("")) {
				return list();
			}
			String img_path = this.imagemana.getImg_path();
			if (img_path == null && img_path.equals("")
					&& img_path.length() <= 0) {
				return list();
			}
			String root_path = PropertiesUtil.getRootpath();
			if (root_path != null && !root_path.equals("")
					&& root_path.length() > 0) {
				root_path = root_path.substring(0, root_path.length() - 1);
				File imgFile = new File(root_path + img_path);
				if (imgFile.exists()) {
					imgFile.delete();
				}
			}
		}
		boolean flag = this.imagemanaService.delete(chb_id);
		if(flag){
			this.addActionMessage("批量删除图片管理成功");
		}else{
			this.addActionMessage("批量删除图片管理失败");
		}

		return list();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		// 根据页面提交的条件找出信息总数
		int count = this.imagemanaService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);

		imagemanaList = this.imagemanaService.getList(pageMap);
		lazyPic=SysconfigFuc.getSysValue("cfg_lazyPic");
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出记录图片管理表信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.imagemana.getSerial_id();
		if (id == null || id.equals("")) {
			imagemana = new Imagemana();
			return list();
		} else {
			imagemana = this.imagemanaService.get(id);
			cust_name=this.memberService.get(imagemana.getCust_id()).getCust_name();
		}
		return goUrl(VIEW);
	}
	
	/**
	 * @return the imagemana
	 */
	public Imagemana getImagemana() {
		return imagemana;
	}

	/**
	 * @param Imagemana
	 *            the imagemana to set
	 */
	public void setImagemana(Imagemana imagemana) {
		this.imagemana = imagemana;
	}

	/**
	 * @return the ImagemanaList
	 */
	public List getImagemanaList() {
		return imagemanaList;
	}

	/**
	 * @param imagemanaList
	 *            the ImagemanaList to set
	 */
	public void setImagemanaList(List imagemanaList) {
		this.imagemanaList = imagemanaList;
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (imagemana == null) {
			imagemana = new Imagemana();
		}
		String id = this.imagemana.getSerial_id();
		if (!this.validateFactory.isDigital(id)) {
			imagemana = this.imagemanaService.get(id);
		}
	}

	public String getCust_name_s() {
		return cust_name_s;
	}

	public void setCust_name_s(String cust_name_s) {
		this.cust_name_s = cust_name_s;
	}

}
