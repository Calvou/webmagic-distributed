package com.spider.webmagic.distributd.master.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Calvou
 *这里主要是定时扫描爬虫调度列表--调度表单由业务自定义：调度ID，调度名，调度组，爬虫类，爬虫线程数，爬虫起始URL，起始时间，有效时间止，周期单位，周期值，是否断点续传，是否有效
 *可启动四个线程处理任务生成 ：分  时  天  月 
 *
 *生成爬虫任务后发送到  任务分发中心
 *任务分发中心分配任务到 执行节点处理
 *    任务分配后 需要接收并记录 执行节点IP端口
 *执行节点接收任务后立即返回本机IP端口表示已经接收到，同时启动线程执行爬虫任务
 *    在爬虫任务中  反馈执行结果
 *
 */
//@Component
public class Schedule {

	//@Autowired
	private ProductSpiderTask pst ;
	
	//@Scheduled(cron="0/20 * * * * ?") 
    public void SpiderTask() {
        // 间隔5秒生成, 一个爬虫任务
		
		//
		try {
			pst.product();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//System.out.println("异常:");
		}
        Thread current = Thread.currentThread();  
        System.out.println("定时任务1:"+current.getId());

    }
	
	
	/*
    @Scheduled(cron="0 0/2 8-20 * * ?") 
    public void executeFileDownLoadTask() {

        // 间隔2分钟,执行工单上传任务     
        Thread current = Thread.currentThread();  
        System.out.println("定时任务1:"+current.getId());
        logger.info("ScheduledTest.executeFileDownLoadTask 定时任务1:"+current.getId()+ ",name:"+current.getName());
    }

    @Scheduled(cron="0 0/1 8-20 * * ?") 
    public void executeUploadTask() {

        // 间隔1分钟,执行工单上传任务              
        Thread current = Thread.currentThread();  
        System.out.println("定时任务2:"+current.getId());
        logger.info("ScheduledTest.executeUploadTask 定时任务2:"+current.getId() + ",name:"+current.getName());
    }

    @Scheduled(cron="0 0/3 5-23 * * ?") 
    public void executeUploadBackTask() {

        // 间隔3分钟,执行工单上传任务                          
        Thread current = Thread.currentThread();  
        System.out.println("定时任务3:"+current.getId());
        logger.info("ScheduledTest.executeUploadBackTask 定时任务3:"+current.getId()+ ",name:"+current.getName());
    }*/
	
}
