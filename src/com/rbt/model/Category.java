/*
 * Package:com.rbt.model
 * FileName: Category.java 
 */
package com.rbt.model;
import java.io.Serializable;
/**
 * @function 功能 分类实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:21 CST 2014
 */
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String cat_id;
	public String getCat_id() {
		return cat_id;
	}
	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}
	
	private String cat_name;
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	
	private String en_name;
	public String getEn_name() {
		return en_name;
	}
	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}
	
	private String word_index;
	public String getWord_index() {
		return word_index;
	}
	public void setWord_index(String word_index) {
		this.word_index = word_index;
	}
	
	private String up_cat_id;
	public String getUp_cat_id() {
		return up_cat_id;
	}
	public void setUp_cat_id(String up_cat_id) {
		this.up_cat_id = up_cat_id;
	}
	
	private String cat_level;
	public String getCat_level() {
		return cat_level;
	}
	public void setCat_level(String cat_level) {
		this.cat_level = cat_level;
	}
	
	private String module_type;
	public String getModule_type() {
		return module_type;
	}
	public void setModule_type(String module_type) {
		this.module_type = module_type;
	}
	
	private String is_display;
	public String getIs_display() {
		return is_display;
	}
	public void setIs_display(String is_display) {
		this.is_display = is_display;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String member_add;
	public String getMember_add() {
		return member_add;
	}
	public void setMember_add(String member_add) {
		this.member_add = member_add;
	}
	
	private String seotitle;
	public String getSeotitle() {
		return seotitle;
	}
	public void setSeotitle(String seotitle) {
		this.seotitle = seotitle;
	}
	
	private String seokeyword;
	public String getSeokeyword() {
		return seokeyword;
	}
	public void setSeokeyword(String seokeyword) {
		this.seokeyword = seokeyword;
	}
	
	private String seodesc;
	public String getSeodesc() {
		return seodesc;
	}
	public void setSeodesc(String seodesc) {
		this.seodesc = seodesc;
	}
	
	private String mem_type;
	public String getMem_type() {
		return mem_type;
	}
	public void setMem_type(String mem_type) {
		this.mem_type = mem_type;
	}
	
	private String cat_intr;
	public String getCat_intr() {
		return cat_intr;
	}
	public void setCat_intr(String cat_intr) {
		this.cat_intr = cat_intr;
	}
	
	private String cat_simple;
	public String getCat_simple() {
		return cat_simple;
	}
	public void setCat_simple(String cat_simple) {
		this.cat_simple = cat_simple;
	}
	
	private String cat_click;
	public String getCat_click() {
		return cat_click;
	}
	public void setCat_click(String cat_click) {
		this.cat_click = cat_click;
	}
	
	private String is_sys;
	public String getIs_sys() {
		return is_sys;
	}
	public void setIs_sys(String is_sys) {
		this.is_sys = is_sys;
	}
	//税率的字段
	private String tariff;
	public String getTariff(){
		return tariff;
	}
	public void setTariff(String tariff){
		this.tariff=tariff;
	}
	//商品分类图标
	private String img_ico;
	public String getImg_ico() {
		return img_ico;
	}
	public void setImg_ico(String img_ico) {
		this.img_ico = img_ico;
	}
	//商品分类属性
	private String cat_attribute;
	public String getCat_attribute() {
		return cat_attribute;
	}
	public void setCat_attribute(String cat_attribute) {
		this.cat_attribute = cat_attribute;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Category[");
		builder.append(", cat_id=");
		builder.append(this.cat_id);
		builder.append(", cat_name=");
		builder.append(this.cat_name);
		builder.append(", en_name=");
		builder.append(this.en_name);
		builder.append(", word_index=");
		builder.append(this.word_index);
		builder.append(", up_cat_id=");
		builder.append(this.up_cat_id);
		builder.append(", cat_level=");
		builder.append(this.cat_level);
		builder.append(", module_type=");
		builder.append(this.module_type);
		builder.append(", is_display=");
		builder.append(this.is_display);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", member_add=");
		builder.append(this.member_add);
		builder.append(", seotitle=");
		builder.append(this.seotitle);
		builder.append(", seokeyword=");
		builder.append(this.seokeyword);
		builder.append(", seodesc=");
		builder.append(this.seodesc);
		builder.append(", mem_type=");
		builder.append(this.mem_type);
		builder.append(", cat_intr=");
		builder.append(this.cat_intr);
		builder.append(", cat_simple=");
		builder.append(this.cat_simple);
		builder.append(", cat_click=");
		builder.append(this.cat_click);
		builder.append(", is_sys=");
		builder.append(this.is_sys);
		builder.append(", tariff=");
		builder.append(this.tariff);
		builder.append(", img_ico=");
		builder.append(this.img_ico);
		builder.append(", cat_attribute=");
		builder.append(this.cat_attribute);
		builder.append("]");
		return builder.toString();
	}

}


