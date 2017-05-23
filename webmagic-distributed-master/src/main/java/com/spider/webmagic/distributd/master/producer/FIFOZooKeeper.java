package com.spider.webmagic.distributd.master.producer;

import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;

public class FIFOZooKeeper {

    public static void initQueue(CuratorFramework client,String path) throws Exception {
        if (client.checkExists().forPath(path)==null) {
            System.out.println("create "+path+" task-queue-fifo");
            client.create().withMode(CreateMode.PERSISTENT).forPath(path,"task-queue-fifo".getBytes());
        } else {
            System.out.println(path+" is exist!");
        }
    }

    public static String produce(CuratorFramework client, int x, String path,String data) throws Exception {
        System.out.println("create "+path+"/x" + x + " x" + x);
        return client.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath(path+"/x" + x,data.getBytes());
    }

    public static byte[] cosume(CuratorFramework client, String path) throws Exception {
        List<String> list = client.getChildren().forPath(path);
        if (list.size() > 0) {
            long min = Long.MAX_VALUE;
            for (String num : list) {
                if (min > Long.parseLong(num.substring(1))) {
                    min = Long.parseLong(num.substring(1));
                }
            }
            System.out.println(Thread.currentThread().getId()+" delete "+path+"/x" + min);
            return client.getData().forPath(path+"/x" + min);
            //client.delete().forPath(path+"/x" + min);
        } else {
            System.out.println(Thread.currentThread().getId()+"No node to cosume");
        }
		return null;
    }
    
    public static String getMinPath(CuratorFramework client, String path) throws Exception{
    	 List<String> list = client.getChildren().forPath(path);
         if (list.size() > 0) {
             long min = Long.MAX_VALUE;
             for (String num : list) {
                 if (min > Long.parseLong(num.substring(1))) {
                     min = Long.parseLong(num.substring(1));
                 }
             }
             System.out.println(Thread.currentThread().getId()+" delete "+path+"/x" + min);
             return path+"/x" + min;
         } else {
             System.out.println(Thread.currentThread().getId()+"No node to cosume");
         }
 		return null;
    }    
    
}