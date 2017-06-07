package com.loujie.www.designpattern.struct.proxy;

import org.junit.Test;

import com.loujie.www.designpattern.struct.proxy.staticproxy.StaticProxyStar;

public class ProxyDemo {

	@Test
	public void staticProxy() {
		// 测试静态代理

		// 1.周杰伦
		// Star zjl = new ZjlStar();
		// 2.刘德华
		Star ldh = new LdhStar();

		// 2.静态代理
		Star proxyStar = new StaticProxyStar(ldh);

		proxyStar.negotiate();
		proxyStar.signContract();
		// 3.唱歌,其它的步骤是代理做的
		proxyStar.sing();
		proxyStar.receivables();

	}

}
