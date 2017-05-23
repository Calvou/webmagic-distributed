package com.spider.webmagic.distributd.agent.leader;

import java.util.UUID;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.utils.EnsurePath;

import com.spider.webmagic.distributd.agent.consumer.FIFOZooKeeper;
import com.spider.webmagic.distributd.agent.server.AgentServer;

public class LeaderListener {

	/** Zookeeper info */
	private String ZK_ADDRESS = "192.168.124.128:2181";
	private String ZK_PATH = "/spideragent";
	private String ZK_TASK_PATH = "/queue-fifo";
	private String ZK_TASK_FAILED_PATH = "/queue-fifo-failed";
	private AgentServer agent;
	private String runid;
	private CuratorFramework agclient;

	public LeaderListener(String rid) {
		runid = rid;
	}

	public LeaderListener(String rid, String zkaddr, String zkpath) {
		ZK_ADDRESS = zkaddr;
		ZK_PATH = zkpath;
		runid = rid;
	}

	public void start() {

		try {
			// 与ZK建立连接
			agclient = connectClient();
			FIFOZooKeeper.initQueue(agclient, ZK_TASK_PATH);
			FIFOZooKeeper.initQueue(agclient, ZK_TASK_FAILED_PATH);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			System.out.println(runid + " agclient queue init failed !");
			return;
		}

		/**
		 * 监听对象
		 */
		LeaderSelectorListener listener = new LeaderSelectorListener() {
			@Override
			public void takeLeadership(CuratorFramework client) throws Exception {
				System.out.println(runid + " take leadership! ");

				// 获取到锁之后
				// 从任务队列中获取数据 并启动执行线程

				String minpath = FIFOZooKeeper.getMinPath(client, ZK_TASK_PATH);
				if (minpath == null) {
					Thread.sleep(60000L);
					System.out.println(runid + " null relinquish leadership!");
					return;
				}
				byte[] data = client.getData().forPath(minpath);

				String ruid = UUID.randomUUID().toString();
				agent = new AgentServer(ruid, new String(data), agclient, ZK_TASK_FAILED_PATH);
				agent.start();

				client.delete().forPath(minpath);

				System.out.println(runid + " relinquish leadership!");

				// 在此启动业务线程， 如何控制业务线程出问题时 -- 释放Leader ， 或者释放Leader时先结束业务线程
				// 如果LeaderListener没有获取到Master那么其会一直在等待
			}

			@Override
			public void stateChanged(CuratorFramework client, ConnectionState state) {

			}
		};

		// 该处相当于主线程
		// 由主线程发起监听，如果监听到业务锁，则启动工作线程
		// 检查工作线程的状态，如果工作线程发生异常则结束锁并关掉工作线程
		// 主线程重新发起锁监听

		System.out.println(runid + " 注册获取锁!");
		registerListener(agclient, listener);

		try {
			System.out.println(runid + " 等待一分钟检查工作线程是否正常!");
			Thread.sleep(60000L);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		// 主监听线程是否存活检查
		while (true) {
			if (!agent.isAlive()) {
				try {
					agent = null;
					Thread.sleep(2000L);
				} catch (Exception e) {

				}
				// 检测到工作线程未存活则重新建立监听 -- 建立前先释放资源
				registerListener(agclient, listener);
			}
			System.out.println(runid + " 等待一分钟检查工作线程是否正常 2!");
			try {
				Thread.sleep(60000L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 初始化 ZKClient
	 * 
	 * @return
	 */
	private CuratorFramework connectClient() {
		CuratorFramework client = CuratorFrameworkFactory.newClient(ZK_ADDRESS, new RetryNTimes(10, 5000));
		client.start();
		return client;
	}

	/**
	 * 创建 client 并注册监听到目录
	 * 
	 * @param listener
	 */
	private void registerListener(CuratorFramework client, LeaderSelectorListener listener) {
		// 2.Ensure path
		try {
			new EnsurePath(ZK_PATH).ensure(client.getZookeeperClient());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 3.Register listener
		LeaderSelector selector = new LeaderSelector(client, ZK_PATH, listener);
		selector.autoRequeue();
		selector.start();

	}

}
