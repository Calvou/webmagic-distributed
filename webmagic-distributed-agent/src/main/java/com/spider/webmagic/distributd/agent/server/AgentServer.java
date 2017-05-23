package com.spider.webmagic.distributd.agent.server;

import java.lang.reflect.Constructor;
import java.net.URLClassLoader;

import org.apache.curator.framework.CuratorFramework;

import com.alibaba.fastjson.JSONObject;
import com.spider.webmagic.distributd.agent.consumer.FIFOZooKeeper;
import com.spider.webmagic.distributd.agent.loadjar.LoadJar;
import com.spider.webmagic.distributd.domain.LSpider;
import com.spider.webmagic.distributd.domain.LTask;
import com.spider.webmagic.distributd.spider.page.pipline.WebmagicPipLine;
import com.spider.webmagic.distributd.spider.page.processor.GithubRepoPageProcessor;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class AgentServer extends Thread {

	// @Autowired
	// private ProductSpiderTask pstask;

	private String runid;
	private String data;
	private CuratorFramework client;
	private String failed_path;
	private LTask task;
	private Spider sp;

	public AgentServer(String rid, String d, CuratorFramework c, String p) {
		runid = rid;
		data = d;
		client = c;
		failed_path = p;
	}

	@Override
	public void run() {
		System.out.println(runid + " AgentServer 启动!");
		try {
			JSONObject t = JSONObject.parseObject(data);
			System.out.println(t);
			execute(jsonToTask(t));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(runid + " 异常!");
			// 如果任务执行失败则写入 异常队列
			try {
				FIFOZooKeeper.produce(client, 2, failed_path, data);
			} catch (Exception e1) { // TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println(runid + " 失败任务写入错误! --- " + data);
			}
		}
		System.out.println(runid + " AgentServer 结束!");
	}

	public boolean execute(LTask t) throws Exception {
		System.out.println(" SpiderExecuteImpl Execute :  " + t.getTaskid());
		/*
		 * Spider sp = Spider.create(new GithubRepoPageProcessor());
		 * sp.addUrl("https://github.com/code4craft").thread(5).run();
		 * sp.getSite();
		 */

		String classname = t.getSpider().getParamclassname();
		String url = t.getSpider().getParamurl();
		int threds = t.getSpider().getParamthreads();
		String piplineclassname = t.getSpider().getPiplineclassname();

		// 加载要反射的类
		Class clz = Class.forName(classname);
		Class clzpip = Class.forName(piplineclassname);
		// 找到类的构造方法
		Constructor constructor = clz.getDeclaredConstructor();
		Constructor constructorpip = clzpip.getDeclaredConstructor();
		// 使用找到的构造方法创建实例
		PageProcessor instance = (PageProcessor) constructor.newInstance();
		Pipeline instancepip = (Pipeline) constructorpip.newInstance();
		// 异步执行启用 runAsync 同步执行 run
		sp = Spider.create(instance);
		sp.addUrl(url).addPipeline(instancepip).thread(threds).run();

		boolean isfals = true;
		while (isfals) {
			System.out.println(sp.getStatus());
			if ("Stopped".equalsIgnoreCase(sp.getStatus().toString())) {
				isfals = false;
			}
		}
		System.out.println(" Over :  " + t.getTaskid());
		return true;
	}

	public LTask jsonToTask(JSONObject obj) {
		LTask t = new LTask();
		t.setTaskid(obj.getString("taskid"));
		// **********
		JSONObject nbj = obj.getJSONObject("spider");
		LSpider sp = new LSpider();
		sp.setSpiderid(nbj.getString("spiderid"));
		sp.setGroup(nbj.getString("group"));
		sp.setName(nbj.getString("name"));
		sp.setParamclassname(nbj.getString("paramclassname"));
		sp.setParamthreads(nbj.getIntValue("paramthreads"));
		sp.setParamurl(nbj.getString("paramurl"));
		sp.setPiplineclassname(nbj.getString("piplineclassname"));
		t.setSpider(sp);
		task = t;
		return t;
	}

	public static void main(String[] args) throws Exception {
		
		Spider sp = Spider.create(new GithubRepoPageProcessor());
		sp.addUrl("https://github.com/code4craft").thread(5).run();
		
		boolean isfals = true;
		while (isfals) {
			System.out.println(sp.getStatus());
			if ("Stopped".equalsIgnoreCase(sp.getStatus().toString())) {
				isfals = false;
			}
		}		
		
		try {
			//加载类包
			LoadJar jarLoader = new LoadJar((URLClassLoader) ClassLoader.getSystemClassLoader());
			LoadJar.loadjar(jarLoader, "E:\\urun\\lib");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		String ss = "com.spider.webmagic.distributd.spider.page.processor.HuxiuProcessor";
		 //加载要反射的类 
		Class clz = Class.forName(ss); //找到类的构造方法 
		Constructor constructor = clz.getDeclaredConstructor(); //使用找到的构造方法创建实例
		PageProcessor instance = (PageProcessor) constructor.newInstance();
		
		String ss1 = "com.spider.webmagic.distributd.spider.page.pipline.WebmagicPipLine";
		 //加载要反射的类 
		Class clz1 = Class.forName(ss1); //找到类的构造方法 
		Constructor constructor1 = clz1.getDeclaredConstructor(); //使用找到的构造方法创建实例
		Pipeline instance1 = (Pipeline) constructor1.newInstance();
		
		sp = Spider.create(instance);
		sp.addUrl("http://www.huxiu.com/").addPipeline(instance1).thread(1).run();
		
		isfals = true;
		while (isfals) {
			System.out.println(sp.getStatus());
			if ("Stopped".equalsIgnoreCase(sp.getStatus().toString())) {
				isfals = false;
			}
		}
		
	}

}
