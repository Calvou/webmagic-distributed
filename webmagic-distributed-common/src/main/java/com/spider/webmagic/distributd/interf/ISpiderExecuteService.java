package com.spider.webmagic.distributd.interf;

import com.spider.webmagic.distributd.domain.LTask;

public interface ISpiderExecuteService {

	public boolean execute(LTask t)  throws Exception;
	
}
