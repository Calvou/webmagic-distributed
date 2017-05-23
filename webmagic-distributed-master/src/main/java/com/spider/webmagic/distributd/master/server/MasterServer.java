package com.spider.webmagic.distributd.master.server;

import org.apache.curator.framework.CuratorFramework;

import com.spider.webmagic.distributd.master.schedule.ProductSpiderTask;

public class MasterServer extends Thread {

	// @Autowired
	// private ProductSpiderTask pstask;
	private CuratorFramework masterclient;
	private String path;
	private String runid;

	public MasterServer() {
	}

	public MasterServer(String rid) {
		runid = rid;
	}

	public MasterServer(String rid, CuratorFramework mc, String p) {
		runid = rid;
		masterclient = mc;
		path = p;
	}

	@Override
	public void run() {
		System.out.println(runid + " MasterServer 启动!");
		ProductSpiderTask pstask = new ProductSpiderTask(masterclient, path);
		// 产生任务
		while (true) {
			try {
				pstask.product();
				Thread.sleep(30000L);
				/*double ss = Math.random() * 100;
				if (ss > 50d) {
					System.out.println(runid + " 执行异常!");
					throw new Exception("rtyuhi");
				}*/
			} catch (Exception e) {
				System.out.println(runid + " 异常!");
				break;
			}
		}
		System.out.println(runid + " MasterServer 结束!");

	}

}
