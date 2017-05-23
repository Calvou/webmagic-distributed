package com.spider.webmagic.distributd.interf;

import com.spider.webmagic.distributd.domain.LTask;

public interface ISpiderAllocateService {

	public boolean addTask(LTask t) throws Exception;
	public boolean getATask()  throws Exception;
	public boolean feedBackTask(LTask t) throws Exception;
	
}
