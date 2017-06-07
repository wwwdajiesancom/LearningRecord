package com.loujie.www.designpattern.struct.proxy.dynamicproxy;

import com.loujie.www.designpattern.struct.proxy.Star;

/**
 * 静态代理,除了唱歌其它的都可以代理
 * 
 * @author loujie
 *
 */
public class StaticProxyStar implements Star {

	private Star star;

	public StaticProxyStar(Star star) {
		super();
		this.star = star;
	}

	@Override
	public void negotiate() {
		System.out.println("StaticProxyStar.negotiate()");
	}

	@Override
	public void signContract() {
		System.out.println("StaticProxyStar.signContract()");
	}

	@Override
	public void sing() {
		// this.negotiate();
		// this.signContract();
		star.sing();
		// this.receivables();
	}

	@Override
	public void receivables() {
		System.out.println("StaticProxyStar.receivables()");
	}

}
