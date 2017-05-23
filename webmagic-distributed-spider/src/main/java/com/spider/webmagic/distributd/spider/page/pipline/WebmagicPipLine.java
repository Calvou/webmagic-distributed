package com.spider.webmagic.distributd.spider.page.pipline;

import java.util.List;
import java.util.Map;

import com.spider.webmagic.distributd.spider.page.domain.BaseItem;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class WebmagicPipLine implements Pipeline {

	@Override
	public void process(ResultItems resultItems, Task task) {

		try {
			System.out.println("get page: " + resultItems.getRequest().getUrl());
			System.out.println("get page: " + resultItems.getAll().entrySet().size());
			for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
				System.out.println(entry.getKey() + ":\t" + entry.getValue());
				BaseItem obj = (BaseItem) entry.getValue();
				
				 List jarr = obj.getUndata();
				 //当数据量大时对数据进行重新组合，按照每100条一个批次 处理 
				 if(jarr.size() > 100){
					 for (Object oobj : jarr) 
					 {
						 System.out.println(oobj); 
					 } 
				}
				 
				processData(obj);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 处理爬取的数据--持久化 或 通过网络发送 比如将数据发送到 kafka
	 * 
	 * @param bitem
	 * @return
	 */
	public boolean processData(BaseItem bitem) {

		return true;
	}

}
