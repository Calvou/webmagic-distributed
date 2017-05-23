package com.spider.webmagic.distributd.spider;

import java.lang.reflect.Constructor;

import com.spider.webmagic.distributd.domain.LTask;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class SpiderManager {

	public static boolean execute(LTask t) throws Exception {

		String classname = t.getSpider().getParamclassname();
		String url = t.getSpider().getParamurl();
		int threds = t.getSpider().getParamthreads();

		// 加载要反射的类
		Class clz = Class.forName(classname);
		// 找到类的构造方法
		Constructor constructor = clz.getDeclaredConstructor();
		// 使用找到的构造方法创建实例
		PageProcessor instance = (PageProcessor) constructor.newInstance();

		Spider.create(instance).addUrl(url).thread(threds).run();

		return true;
	}

	public static void main(String[] args) throws Exception {

		String ss = "com.spider.webmagic.distributd.spider.page.processor.HuxiuProcessor";
		// 加载要反射的类 
		Class clz = Class.forName(ss); //找到类的构造方法 
		Constructor constructor = clz.getDeclaredConstructor(); // 使用找到的构造方法创建实例
		PageProcessor instance = (PageProcessor) constructor.newInstance();

		Spider sp = Spider.create(instance);
		sp.addUrl("http://www.huxiu.com/");
		sp.thread(5).run();
		
		boolean isfals = true;
		while (isfals) {
			System.out.println(sp.getStatus());
			if ("Stopped".equalsIgnoreCase(sp.getStatus().toString())) {
				isfals = false;
			}
		}
		System.out.println(" Over :  " );
		
	}

}
