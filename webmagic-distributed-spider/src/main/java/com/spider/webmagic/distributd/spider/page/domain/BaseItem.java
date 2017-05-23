package com.spider.webmagic.distributd.spider.page.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class BaseItem<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String untopic = "kafka"; //标识数据所属种类
	
	private String unname; //标识数据 名称
	
	private List<T> undata = new ArrayList<>();

	public String getUntopic() {
		return untopic;
	}

	public void setUntopic(String untopic) {
		this.untopic = untopic;
	}

	public String getUnname() {
		return unname;
	}

	public void setUnname(String unname) {
		this.unname = unname;
	}

	public List<T> getUndata() {
		return undata;
	}

	public void setUndata(List<T> undata) {
		this.undata = undata;
	}

	@Override
	public String toString() {		
		return JSONObject.toJSONString(this);
	}	
	
}
