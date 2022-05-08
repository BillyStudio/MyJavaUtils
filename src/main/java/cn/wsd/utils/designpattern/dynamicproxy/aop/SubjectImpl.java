package cn.wsd.utils.designpattern.dynamicproxy.aop;

import java.util.Date;

public class SubjectImpl implements Subject {
	@Override
	public Subject request(String name) {
		System.out.println("调用request方法: " + name);
		return this;
	}

	@Override
	public String getTime() {
		System.out.println("调用getTime方法");
		return new Date().toString();
	}

	@Override
	public void test() {
		System.out.println("调用无参方法");
	}
}
