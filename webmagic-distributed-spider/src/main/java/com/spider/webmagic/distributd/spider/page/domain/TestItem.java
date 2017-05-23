package com.spider.webmagic.distributd.spider.page.domain;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

public class TestItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 935857570340521439L;
	
	private String title;
	private String author;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Override
	public String toString() {		
		return JSONObject.toJSONString(this);
	}
	
}
