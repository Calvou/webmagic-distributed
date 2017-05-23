package com.spider.webmagic.distributd.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class LTask implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String taskid;
	private String name;
	private String group;
	private String spiderid;
	private String jobid;
	private Timestamp createtime;
	private Timestamp exectime;
	private Timestamp endtime;
	private String execip;
	private String execport;
	private String status;	
	
	private LSpider spider;	
	
	
	public String getExecip() {
		return execip;
	}
	public void setExecip(String execip) {
		this.execip = execip;
	}
	public String getExecport() {
		return execport;
	}
	public void setExecport(String execport) {
		this.execport = execport;
	}
	public LSpider getSpider() {
		return spider;
	}
	public void setSpider(LSpider spider) {
		this.spider = spider;
	}
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getJobid() {
		return jobid;
	}
	public void setJobid(String jobid) {
		this.jobid = jobid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public Timestamp getExectime() {
		return exectime;
	}
	public void setExectime(Timestamp exectime) {
		this.exectime = exectime;
	}
	public Timestamp getEndtime() {
		return endtime;
	}
	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}
	
}
