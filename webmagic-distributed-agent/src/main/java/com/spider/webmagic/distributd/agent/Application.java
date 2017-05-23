package com.spider.webmagic.distributd.agent;

import java.util.UUID;

import com.spider.webmagic.distributd.agent.leader.LeaderListener;

//@SpringBootApplication
public class Application {

	public static void main(String[] args){
		//SpringApplication.run(Application.class, args);
		
		//系统启动的时候启动监听 , 与Spring无关，暂无需启动Spring相关配置(或者通过Spring加载相关配置)--后续可能需要Web界面等的需要
		//指定URL列表 生成定时任务或  一次性 爬虫任务
		//后续通过 Spring加载 XML的方式加载配置和初始化bean
		//通过功能或命令 停止当前 Agent，发出停止指令，当没有任务在执行时停止 状态包括 停止 不接收新任务等
		//发布无法实现增量或仅更新任务包模式，必须重启
		//生成执行 ID
		String uuid = UUID.randomUUID().toString();
		LeaderListener ll = new LeaderListener(uuid);
		ll.start();
	}
	
}
