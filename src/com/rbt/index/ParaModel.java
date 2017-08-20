package com.rbt.index;

//lucene搜索参数模型实体

public class ParaModel {
	
	private String search_type;
	/*
	 * multi:多字段查询
	 * one：单字段查询
	 * range：范围查询
	 * normal：普通字段的查询
	 */
	
	private String[] fields;
	
	private String search_key;
	
	private String search_value;
	
	private String start_value;
	
	private String end_value;

	public String getSearch_type() {
		return search_type;
	}

	public void setSearch_type(String search_type) {
		this.search_type = search_type;
	}

	public String[] getFields() {
		return fields;
	}

	public void setFields(String[] fields) {
		this.fields = fields;
	}

	public String getSearch_key() {
		return search_key;
	}

	public void setSearch_key(String search_key) {
		this.search_key = search_key;
	}

	public String getSearch_value() {
		return search_value;
	}

	public void setSearch_value(String search_value) {
		this.search_value = search_value;
	}

	public String getStart_value() {
		return start_value;
	}

	public void setStart_value(String start_value) {
		this.start_value = start_value;
	}

	public String getEnd_value() {
		return end_value;
	}

	public void setEnd_value(String end_value) {
		this.end_value = end_value;
	}
	

	public ParaModel() {
		super();
	}

	public ParaModel(String search_key, String search_value) {
		super();
		this.search_type = Constants.NORMAL;
		this.search_key = search_key;
		this.search_value = search_value;
	}
	
	public ParaModel(String search_key, String start, String end){
		super();
		this.search_type = Constants.RANGE;
		this.search_key = search_key;
		this.start_value = start;
		this.end_value = end;
	}
	
	
	
	
	
	
	
}
