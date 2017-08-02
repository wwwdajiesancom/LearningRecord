package com.loujie.www.zk;

import java.io.IOException;

import org.apache.zookeeper.ZooKeeper;

public class ZkMain {

	public static void main(String[] args) {
		try {
			ZooKeeper zk = new ZooKeeper("192.68.139.128:2181", 30 * 60 * 1000, null);
			zk.getState();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
