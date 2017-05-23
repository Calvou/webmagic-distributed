package com.spider.webmagic.distributd.domain;

import java.io.Serializable;
import java.sql.Date;

public class LJob implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String jobid;
	private String name;
	private String group;
	private String spiderid;
	private Date yxsjq;
	private Date yxsjz;
	private Date sczxsj;
	private Date xczxsj;
	private String unit;
	private String unitvalue;
	private String isresume;
	private String isuse;
	
	public String getJobid() {
		return jobid;
	}
	public void setJobid(String jobid) {
		this.jobid = jobid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getSpiderid() {
		return spiderid;
	}
	public void setSpiderid(String spiderid) {
		this.spiderid = spiderid;
	}
	public Date getYxsjq() {
		return yxsjq;
	}
	public void setYxsjq(Date yxsjq) {
		this.yxsjq = yxsjq;
	}
	public Date getYxsjz() {
		return yxsjz;
	}
	public void setYxsjz(Date yxsjz) {
		this.yxsjz = yxsjz;
	}
	public Date getSczxsj() {
		return sczxsj;
	}
	public void setSczxsj(Date sczxsj) {
		this.sczxsj = sczxsj;
	}
	public Date getXczxsj() {
		return xczxsj;
	}
	public void setXczxsj(Date xczxsj) {
		this.xczxsj = xczxsj;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getUnitvalue() {
		return unitvalue;
	}
	public void setUnitvalue(String unitvalue) {
		this.unitvalue = unitvalue;
	}
	public String getIsresume() {
		return isresume;
	}
	public void setIsresume(String isresume) {
		this.isresume = isresume;
	}
	public String getIsuse() {
		return isuse;
	}
	public void setIsuse(String isuse) {
		this.isuse = isuse;
	}
			
	
}
