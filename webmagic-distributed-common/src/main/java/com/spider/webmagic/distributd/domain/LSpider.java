package com.spider.webmagic.distributd.domain;

import java.io.Serializable;

public class LSpider implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String spiderid;
	private String name;
	private String group;
	private String paramclassname;
	private int paramthreads;
	private String paramurl;
	private String piplineclassname;
	
	public String getSpiderid() {
		return spiderid;
	}
	public void setSpiderid(String spiderid) {
		this.spiderid = spiderid;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParamclassname() {
		return paramclassname;
	}
	public void setParamclassname(String paramclassname) {
		this.paramclassname = paramclassname;
	}
	public int getParamthreads() {
		return paramthreads;
	}
	public void setParamthreads(int paramthreads) {
		this.paramthreads = paramthreads;
	}
	public String getParamurl() {
		return paramurl;
	}
	public void setParamurl(String paramurl) {
		this.paramurl = paramurl;
	}
	public String getPiplineclassname() {
		return piplineclassname;
	}
	public void setPiplineclassname(String piplineclassname) {
		this.piplineclassname = piplineclassname;
	}
	
}
