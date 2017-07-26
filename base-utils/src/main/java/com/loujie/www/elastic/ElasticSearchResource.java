package com.loujie.www.elastic;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import com.loujie.www.properties.PropertiesUtils;

/**
 * 初始化参数
 * 
 * @author loujie
 *
 */
public class ElasticSearchResource {
	private static final String elastic_cluster_name = //
			PropertiesUtils.getProperty("elastic_cluster_name");// Elasticsearch集群名称

	private static final String elastic_ip = //
			PropertiesUtils.getProperty("elastic_ip");// 其中一台机器ip

	private static final int elastic_port = //
			Integer.parseInt(PropertiesUtils.getProperty("elastic_port"));// 端口

	private static Client client = null;

	static {
		// 初始化
		Settings settings = Settings.settingsBuilder()//
				.put("cluster.name", elastic_cluster_name)// 设置集群的名称
				.put("client.transport.sniff", true)// 可以自动扫描集群中的其它node节点
				.build();
		try {
			client = TransportClient.builder()//
					.settings(settings).build()//
					.addTransportAddress(new InetSocketTransportAddress//
			(InetAddress.getByName(elastic_ip), elastic_port));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 对外提供获取Client
	 * 
	 * @return
	 */
	public static Client getClient() {
		return client;
	}

}
