package com.spider.webmagic.distributd.master.schedule;

import java.util.UUID;

import org.apache.curator.framework.CuratorFramework;

import com.alibaba.fastjson.JSONObject;
import com.spider.webmagic.distributd.domain.LSpider;
import com.spider.webmagic.distributd.domain.LTask;
import com.spider.webmagic.distributd.master.producer.FIFOZooKeeper;

//@Service
public class ProductSpiderTask {
	
    private CuratorFramework masterclient;
    private String path;
    
    public ProductSpiderTask(){}
    public ProductSpiderTask(CuratorFramework maclient, String p){
    	masterclient = maclient;
    	path = p;
    }
	
	public boolean product() throws Exception{
		LTask t = initTask();
		System.out.println(t.getTaskid()+ "  -- 生成");
		//spiderAllocateService.addTask(t);
		
		FIFOZooKeeper.produce(masterclient, 1, path, JSONObject.toJSON(t).toString());
		
		System.out.println(t.getTaskid()+ "  -- 完成");
		return true;
	}
	
	public LTask initTask(){
		LTask t = new LTask();
		t.setTaskid(UUID.randomUUID().toString());		
		t.setName("task-5678h");
		LSpider sp = new LSpider();
		sp.setSpiderid("34323");
		sp.setName("wgrf");
		sp.setGroup("frfwe");
		sp.setParamclassname("com.spider.webmagic.distributd.spider.page.processor.GithubRepoPageProcessor");
		sp.setParamthreads(5);
		sp.setParamurl("https://github.com/code4craft");
		t.setSpider(sp);
		return t;
	}
	
	
}
