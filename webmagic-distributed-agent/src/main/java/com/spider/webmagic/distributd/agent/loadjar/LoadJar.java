package com.spider.webmagic.distributd.agent.loadjar;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class LoadJar {

	private URLClassLoader urlClassLoader;

	public LoadJar(URLClassLoader urlClassLoader) {
        this.urlClassLoader = urlClassLoader;  
    }

	public void loadJar(URL url) throws Exception {
		Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
		addURL.setAccessible(true);
		addURL.invoke(urlClassLoader, url);
	}


	public static void loadjar(LoadJar jarLoader, String path) throws MalformedURLException, Exception {
		File libdir = new File(path);
		if (libdir != null && libdir.isDirectory()) {

			File[] listFiles = libdir.listFiles(new FileFilter() {

				@Override
				public boolean accept(File file) {
					// TODO Auto-generated method stub
					return file.exists() && file.isFile() && file.getName().endsWith(".jar");
				}
			});

			for (File file : listFiles) {
				jarLoader.loadJar(file.toURI().toURL());
			}

		} else {
			System.out.println("[Console Message] Directory [" + path + "] does not exsit, please check it");
			System.exit(0);
		}
	}

	public static void main(String[] args) throws Exception {
		LoadJar jarLoader = new LoadJar((URLClassLoader) ClassLoader.getSystemClassLoader());

		//本意是想通过该类去实现任务包的动态加载
		//但由于JVM加载的类不是覆盖机制，不适合如此操作
		
		//loadjar(jarLoader, System.getProperty("user.dir") + "/lib");
		
		loadjar(jarLoader, "E:\\urun\\lib");

	}

}
